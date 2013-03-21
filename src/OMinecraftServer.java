import java.awt.GraphicsEnvironment;
import java.io.File;
import java.security.KeyPair;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class OMinecraftServer implements OICommandSender, Runnable, OIPlayerUsage {

    private static OMinecraftServer k = null;
    private final OISaveFormat l;
    private final OPlayerUsageSnooper m = new OPlayerUsageSnooper("server", this);
    private final File n;
    private final List o = new ArrayList();
    private final OICommandManager p;
    public final OProfiler a = new OProfiler();
    private String q;
    private int r = -1;
    //public OWorldServer[] b;
    private OServerConfigurationManager s;
    private boolean t = true;
    private boolean u = false;
    private int v = 0;
    public String c;
    public int d;
    private boolean w;
    private boolean x;
    private boolean y;
    private boolean z;
    private boolean A;
    private String B;
    private int C;
    private long D;
    private long E;
    private long F;
    private long G;
    public final long[] e = new long[100];
    public final long[] f = new long[100];
    public final long[] g = new long[100];
    public final long[] h = new long[100];
    public final long[] i = new long[100];
    //public long[][] j;
    private KeyPair H;
    private String I;
    private String J;
    private boolean L;
    private boolean M;
    private boolean N;
    private String O = "";
    private boolean P = false;
    private long Q;
    private String R;
    private boolean S;

    // CanaryMod start: Multiworld \o/
    public Map<String, OWorldServer[]> worlds = new HashMap<String, OWorldServer[]>(1);
    public Map<String, long[][]> worldTickNanos = new HashMap<String, long[][]>(1);
    // CanaryMod end
    
    // CanaryMod start: Stop Message
    private String stopMsg;
    // CanaryMod end

    public OMinecraftServer(File file1) {
        k = this;
        this.n = file1;
        this.p = new OServerCommandManager();
        this.l = new OAnvilSaveConverter(file1);
        this.am();
    }

    private void am() {
        ODispenserBehaviors.a();
    }

    protected abstract boolean c();

    protected void b(String s) {
        if (this.N().b(s)) {
            this.al().a("Converting map!");
            this.c("menu.convertingLevel");
            this.N().a(s, new OConvertingProgressUpdate(this));
        }
    }

    protected synchronized void c(String s) {
        this.R = s;
    }

    protected void a(String s, String s1, long i, OWorldType oworldtype, String s2) {
        this.b(s);
        this.c("menu.loadingLevel");

        OWorldServer[] toLoad = new OWorldServer[3];

        this.worlds.put(s, toLoad);
        this.worldTickNanos.put(s, new long[toLoad.length][100]);

        OISaveHandler oisavehandler = this.l.a(s, true);
        OWorldInfo oworldinfo = oisavehandler.d();
        OWorldSettings oworldsettings;

        if (oworldinfo == null) {
            oworldsettings = new OWorldSettings(i, this.g(), this.f(), this.i(), oworldtype);
            oworldsettings.a(s2);
        } else {
            oworldsettings = new OWorldSettings(oworldinfo);
        }

        if (this.M) {
            oworldsettings.a();
        }

        for (int j = 0; j < toLoad.length; ++j) {
            byte b0 = 0;

            if (j == 1) {
                b0 = -1;
            }

            if (j == 2) {
                b0 = 1;
            }

            if (j == 0) {
                if (this.M()) {
                    toLoad[j] = new ODemoWorldServer(this, oisavehandler, s1, b0, this.a, this.al());
                } else {
                    toLoad[j] = new OWorldServer(this, oisavehandler, s1, b0, oworldsettings, this.a, this.al());
                }
            } else {
                toLoad[j] = new OWorldServerMulti(this, oisavehandler, s1, b0, oworldsettings, toLoad[0], this.a, this.al());
            }
            toLoad[j].a((OIWorldAccess) (new OWorldManager(this, toLoad[j])));
            if (!this.I()) {
                toLoad[j].L().a(this.g());
            }
            this.s.a(toLoad);
        }

        this.c(this.h());
        this.e(toLoad);
    }

    protected void e(OWorldServer[] toLoad) { // CanaryMod: add world array as parameter
        int i = 0;

        this.c("menu.generatingTerrain");
        byte b0 = 0;

        this.al().a("Preparing start region for level " + b0);
        OWorldServer oworldserver = toLoad[b0];
        OChunkCoordinates ochunkcoordinates = oworldserver.I();
        long j = System.currentTimeMillis();

        for (int k = -192; k <= 192 && this.m(); k += 16) {
            for (int l = -192; l <= 192 && this.m(); l += 16) {
                long i1 = System.currentTimeMillis();

                if (i1 - j > 1000L) {
                    this.a_("Preparing spawn area", i * 100 / 625);
                    j = i1;
                }

                ++i;
                oworldserver.b.c(ochunkcoordinates.a + k >> 4, ochunkcoordinates.c + l >> 4);
            }
        }

        this.j();
    }

    public abstract boolean f();

    public abstract OEnumGameType g();

    public abstract int h();

    public abstract boolean i();

    protected void a_(String s, int i) {
        this.c = s;
        this.d = i;
        this.al().a(s + ": " + i + "%");
    }

    protected void j() {
        this.c = null;
        this.d = 0;
    }

    protected void a(boolean flag) {
        if (!this.N) {
            for (OWorldServer[] aoworldserver : this.worlds.values()) {
                int i = aoworldserver.length;

                for (int j = 0; j < i; ++j) {
                    OWorldServer oworldserver = aoworldserver[j];

                    if (oworldserver != null) {
                        if (!flag) {
                            this.al().a("Saving chunks for level \'" + oworldserver.L().k() + "\'/" + oworldserver.t.l());
                        }

                        try {
                            oworldserver.a(true, (OIProgressUpdate) null);
                        } catch (OMinecraftException ominecraftexception) {
                            this.al().b(ominecraftexception.getMessage());
                        }
                    }
                }
            }
        }
    }

    public void k() {
        if (!this.N) {
            this.al().a("Stopping server");
            if (this.ae() != null) {
                this.ae().a();
            }

            etc.getLoader().unloadPlugins(); // CanaryMod: unload plugins

            if (this.s != null) {
                this.al().a("Saving players");
                this.s.g();
                this.s.r(stopMsg); // CanaryMod: custom stop message added
            }

            this.al().a("Saving worlds");
            this.a(false);
            for (OWorldServer[] aoworldserver : this.worlds.values()) {
                for (int i = 0; i < aoworldserver.length; ++i) {
                    OWorldServer oworldserver = aoworldserver[i];

                    oworldserver.m();
                }
            } // CanaryMod: diff visibility

            if (this.m != null && this.m.d()) {
                this.m.e();
            }
        }
    }

    public String l() {
        return this.q;
    }

    public void d(String s) {
        this.q = s;
    }

    public boolean m() {
        return this.t;
    }

    public void n() { //STOP
        this.t = false;
    }

    public void run() {
        try {
            if (this.c()) {
                // CanaryMod: load once!
                if (!etc.getLoader().isLoaded()) {
                    etc.getLoader().loadPlugins();
                }

                long i = System.currentTimeMillis();

                for (long j = 0L; this.t; this.P = true) {
                    long k = System.currentTimeMillis();
                    long l = k - i;

                    if (l > 2000L && i - this.Q >= 15000L) {
                        this.al().b("Can\'t keep up! Did the system time change, or is the server overloaded?");
                        l = 2000L;
                        this.Q = i;
                    }

                    if (l < 0L) {
                        this.al().b("Time ran backwards! Did the system time change?");
                        l = 0L;
                    }

                    j += l;
                    i = k;
                    // CanaryMod start: multiworld sleeping
                    boolean allSleeping = true;

                    for (OWorldServer[] level : this.worlds.values()) {
                        if (level.length != 0 && level[0] != null) {
                            allSleeping &= level[0].e();
                        }
                    }
                    // CanaryMod end
                    if (allSleeping) {
                        this.q();
                        j = 0L;
                    } else {
                        while (j > 50L) {
                            j -= 50L;
                            this.q();
                        }
                    }

                    Thread.sleep(1L);
                }
            } else {
                this.a((OCrashReport) null);
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            this.al().c("Encountered an unexpected exception " + throwable.getClass().getSimpleName(), throwable);
            OCrashReport ocrashreport = null;

            if (throwable instanceof OReportedException) {
                ocrashreport = this.b(((OReportedException) throwable).a());
            } else {
                ocrashreport = this.b(new OCrashReport("Exception in server tick loop", throwable));
            }

            File file1 = new File(new File(this.o(), "crash-reports"), "crash-" + (new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss")).format(new Date()) + "-server.txt");

            if (ocrashreport.a(file1, this.al())) {
                this.al().c("This crash report has been saved to: " + file1.getAbsolutePath());
            } else {
                this.al().c("We were unable to save this crash report to disk.");
            }

            this.a(ocrashreport);
        } finally {
            try {
                this.k();
                this.u = true;
            } catch (Throwable throwable1) {
                throwable1.printStackTrace();
            } finally {
                this.p();
            }
        }
    }

    protected File o() {
        return new File(".");
    }

    protected void a(OCrashReport ocrashreport) {}

    protected void p() {}

    protected void q() {
        long i = System.nanoTime();

        OAxisAlignedBB.a().a();
        ++this.v;
        if (this.S) {
            this.S = false;
            this.a.a = true;
            this.a.a();
        }

        this.a.a("root");
        this.r();
        if (this.v % 900 == 0) {
            this.a.a("save");
            this.s.g();
            this.a(true);
            this.a.b();
        }

        this.a.a("tallying");
        this.i[this.v % 100] = System.nanoTime() - i;
        this.e[this.v % 100] = OPacket.q - this.D;
        this.D = OPacket.q;
        this.f[this.v % 100] = OPacket.r - this.E;
        this.E = OPacket.r;
        this.g[this.v % 100] = OPacket.o - this.F;
        this.F = OPacket.o;
        this.h[this.v % 100] = OPacket.p - this.G;
        this.G = OPacket.p;
        this.a.b();
        this.a.a("snooper");
        if (!this.m.d() && this.v > 100) {
            this.m.a();
        }

        if (this.v % 6000 == 0) {
            this.m.b();
        }

        this.a.b();
        this.a.b();
    }

    public void r() {
        this.a.a("levels");

        int i;
        for (Map.Entry<String, OWorldServer[]> entry : this.worlds.entrySet()) {
            OWorldServer[] level = entry.getValue();
            String worldName = entry.getKey();

            for (i = 0; i < level.length; ++i) {
                long j = System.nanoTime();
                if (level[i] == null) continue; // CanaryMod: prevent NPE

                if (i == 0 || this.s()) {
                    OWorldServer oworldserver = level[i];

                    this.a.a(oworldserver.L().k());
                    this.a.a("pools");
                    oworldserver.T().a();
                    this.a.b();
                    if (this.v % 20 == 0) {
                        this.a.a("timeSync");
                        //CanaryMod: send packet for multiworld
                        this.s.sendPacketToDimension((OPacket) (new OPacket4UpdateTime(oworldserver.G(), oworldserver.H())), worldName, oworldserver.t.h);
                        this.a.b();
                    }

                this.a.a("tick");

                OCrashReport ocrashreport;

                try {
                    oworldserver.b();
                } catch (Throwable throwable) {
                    ocrashreport = OCrashReport.a(throwable, "Exception ticking world");
                    oworldserver.a(ocrashreport);
                    throw new OReportedException(ocrashreport);
                }

                try {
                    oworldserver.h();
                } catch (Throwable throwable1) {
                    ocrashreport = OCrashReport.a(throwable1, "Exception ticking world entities");
                    oworldserver.a(ocrashreport);
                    throw new OReportedException(ocrashreport);
                }

                this.a.b();
                this.a.a("tracker");
                    oworldserver.p().a();
                this.a.b();
                this.a.b();
                }

                this.worldTickNanos.get(worldName)[i][this.v % 100] = System.nanoTime() - j;
            }
        }

        this.a.c("connection");
        this.ae().b();
        this.a.c("players");
        this.s.b();
        this.a.c("tickables");

        for (i = 0; i < this.o.size(); ++i) {
            ((OIUpdatePlayerListBox) this.o.get(i)).a();
        }

        this.a.b();
    }

    public boolean s() {
        return true;
    }

    public void a(OIUpdatePlayerListBox oiupdateplayerlistbox) {
        this.o.add(oiupdateplayerlistbox);
    }

    public static void main(String[] astring) {
        OStatList.a();
        OILogAgent oilogagent = null;

        try {
            boolean flag = !GraphicsEnvironment.isHeadless();
            String s = null;
            String s1 = ".";
            String s2 = null;
            boolean flag1 = false;
            boolean flag2 = false;
            int i = -1;

            for (int j = 0; j < astring.length; ++j) {
                String s3 = astring[j];
                String s4 = j == astring.length - 1 ? null : astring[j + 1];
                boolean flag3 = false;

                if (!s3.equals("nogui") && !s3.equals("--nogui")) {
                    if (s3.equals("--port") && s4 != null) {
                        flag3 = true;

                        try {
                            i = Integer.parseInt(s4);
                        } catch (NumberFormatException numberformatexception) {
                            ;
                        }
                    } else if (s3.equals("--singleplayer") && s4 != null) {
                        flag3 = true;
                        s = s4;
                    } else if (s3.equals("--universe") && s4 != null) {
                        flag3 = true;
                        s1 = s4;
                    } else if (s3.equals("--world") && s4 != null) {
                        flag3 = true;
                        s2 = s4;
                    } else if (s3.equals("--demo")) {
                        flag1 = true;
                    } else if (s3.equals("--bonusChest")) {
                        flag2 = true;
                    }
                } else {
                    flag = false;
                }

                if (flag3) {
                    ++j;
                }
            }

            ODedicatedServer odedicatedserver = new ODedicatedServer(new File(s1));

            oilogagent = odedicatedserver.al();
            if (s != null) {
                odedicatedserver.k(s);
            }

            if (s2 != null) {
                odedicatedserver.l(s2);
            }

            if (i >= 0) {
                odedicatedserver.b(i);
            }

            if (flag1) {
                odedicatedserver.b(true);
            }

            if (flag2) {
                odedicatedserver.c(true);
            }

            if (flag) {
                odedicatedserver.ao();
            }

            odedicatedserver.t();
            Runtime.getRuntime().addShutdownHook(new OThreadDedicatedServer(odedicatedserver));
        } catch (Exception exception) {
            if (oilogagent != null) {
                oilogagent.c("Failed to start the minecraft server", exception);
            } else {
                Logger.getAnonymousLogger().log(Level.SEVERE, "Failed to start the minecraft server", exception);
            }
        }
    }

    public void t() {
        (new OThreadMinecraftServer(this, "Server thread")).start();
    }

    public File e(String s) {
        return new File(this.o(), s);
    }

    public void f(String s) {
        this.al().a(s);
    }

    public void g(String s) {
        this.al().b(s);
    }

    public OWorldServer a(int i) {
        throw new UnsupportedOperationException("OMinecraftServer.a(int) has" +
                " been replaced by OMinecraftServer.getWorld(String, int).");
    }

    public OWorldServer getWorld(String s, int i) {
        int index = i == 0 ? 0 : i == -1 ? 1 : 2;

        OWorldServer[] aows = this.worlds.get(s);
        return aows != null && aows.length > index ? aows[index] : null;
    }

    public String u() {
        return this.q;
    }

    public int v() {
        return this.r;
    }

    public String w() {
        return this.B;
    }

    public String x() {
        return "1.5.1";
    }

    public int y() {
        return this.s.k();
    }

    public int z() {
        return this.s.l();
    }

    public String[] A() {
        return this.s.d();
    }

    public String B() {
        return "";
    }

    public String h(String s) {
        ORConConsoleSource.a.c();
        this.p.a(ORConConsoleSource.a, s);
        return ORConConsoleSource.a.d();
    }

    public boolean C() {
        return false;
    }

    public void i(String s) {
        this.al().c(s);
    }

    public void j(String s) {
        if (this.C()) {
            this.al().a(s);
        }
    }

    public String getServerModName() {
        return "CanaryMod";
    }

    public OCrashReport b(OCrashReport ocrashreport) {
        ocrashreport.g().a("Profiler Position", (Callable) (new OCallableIsServerModded(this)));
        if (this.worlds != null && !this.worlds.isEmpty() && this.worlds.get(this.J())[0] != null) {
            ocrashreport.g().a("Vec3 Pool Size", (Callable) (new OCallableServerProfiler(this)));
        }

        if (this.s != null) {
            ocrashreport.g().a("Player Count", (Callable) (new OCallableServerMemoryStats(this)));
        }

        return ocrashreport;
    }

    public List a(OICommandSender oicommandsender, String s) {
        ArrayList arraylist = new ArrayList();

        if (s.startsWith("/")) {
            s = s.substring(1);
            boolean flag = !s.contains(" ");
            List list = this.p.b(oicommandsender, s);

            if (list != null) {
                Iterator iterator = list.iterator();

                while (iterator.hasNext()) {
                    String s1 = (String) iterator.next();

                    if (flag) {
                        arraylist.add("/" + s1);
                    } else {
                        arraylist.add(s1);
                    }
                }
            }

            return arraylist;
        } else {
            String[] astring = s.split(" ", -1);
            String s2 = astring[astring.length - 1];
            String[] astring1 = this.s.d();
            int i = astring1.length;

            for (int j = 0; j < i; ++j) {
                String s3 = astring1[j];

                if (OCommandBase.a(s2, s3)) {
                    arraylist.add(s3);
                }
            }

            return arraylist;
        }
    }

    public static OMinecraftServer D() {
        return k;
    }

    public String c_() {
        return "Server";
    }

    public void a(String s) {
        this.al().a(OStringUtils.a(s));
    }

    public boolean a(int i, String s) {
        return true;
    }

    public String a(String s, Object... aobject) {
        return OStringTranslate.a().a(s, aobject);
    }

    public OICommandManager E() {
        return this.p;
    }

    public KeyPair F() {
        return this.H;
    }

    public int G() {
        return this.r;
    }

    public void b(int i) {
        this.r = i;
    }

    public String H() {
        return this.I;
    }

    public void k(String s) {
        this.I = s;
    }

    public boolean I() {
        return this.I != null;
    }

    public String J() {
        return this.J;
    }

    public void l(String s) {
        this.J = s;
    }

    public void a(KeyPair keypair) {
        this.H = keypair;
    }

    public void c(int i) {
        for (OWorldServer[] aworld : this.worlds.values()) {
            for (int j = 0; j < aworld.length; ++j) {
                OWorldServer oworldserver = aworld[j];

                if (oworldserver != null) {
                if (oworldserver.L().t()) {
                    oworldserver.r = 3;
                        oworldserver.a(true, true);
                    } else if (this.I()) {
                    oworldserver.r = i;
                    oworldserver.a(oworldserver.r > 0, true);
                    } else {
                    oworldserver.r = i;
                    oworldserver.a(this.L(), this.x);
                    }
                }
            }
        } //
    }

    protected boolean L() {
        return true;
    }

    public boolean M() {
        return this.L;
    }

    public void b(boolean flag) {
        this.L = flag;
    }

    public void c(boolean flag) {
        this.M = flag;
    }

    public OISaveFormat N() {
        return this.l;
    }

    public void P() {
        this.N = true;
        this.N().d();

        for (OWorldServer[] level : this.worlds.values()) {
            for (int i = 0; i < level.length; ++i) {
                OWorldServer oworldserver = level[i];

                if (oworldserver != null) {
                    oworldserver.m();
                }
            }

            this.N().e(level[0].K().g());
        } //
        this.n();
    }

    public String Q() {
        return this.O;
    }

    public void n(String s) {
        this.O = s;
    }

    public void a(OPlayerUsageSnooper oplayerusagesnooper) {
        oplayerusagesnooper.a("whitelist_enabled", Boolean.valueOf(false));
        oplayerusagesnooper.a("whitelist_count", Integer.valueOf(0));
        oplayerusagesnooper.a("players_current", Integer.valueOf(this.y()));
        oplayerusagesnooper.a("players_max", Integer.valueOf(this.z()));
        oplayerusagesnooper.a("players_seen", Integer.valueOf(this.s.m().length));
        oplayerusagesnooper.a("uses_auth", Boolean.valueOf(this.w));
        oplayerusagesnooper.a("gui_state", this.ag() ? "enabled" : "disabled");
        oplayerusagesnooper.a("avg_tick_ms", Integer.valueOf((int) (OMathHelper.a(this.i) * 1.0E-6D)));
        oplayerusagesnooper.a("avg_sent_packet_count", Integer.valueOf((int) OMathHelper.a(this.e)));
        oplayerusagesnooper.a("avg_sent_packet_size", Integer.valueOf((int) OMathHelper.a(this.f)));
        oplayerusagesnooper.a("avg_rec_packet_count", Integer.valueOf((int) OMathHelper.a(this.g)));
        oplayerusagesnooper.a("avg_rec_packet_size", Integer.valueOf((int) OMathHelper.a(this.h)));
        int i = 0;
        for (Map.Entry<String, OWorldServer[]> entry : this.worlds.entrySet()) {
            OWorldServer[] level = entry.getValue();
            String levelName = entry.getKey();

            for (int j = 0; j < level.length; ++j) {
                if (level[j] != null) {
                    OWorldServer oworldserver = level[j];
                    OWorldInfo oworldinfo = oworldserver.L();

                    oplayerusagesnooper.a("world[" + i + "][dimension]", Integer.valueOf(oworldserver.t.h));
                    oplayerusagesnooper.a("world[" + i + "][mode]", oworldinfo.r());
                    oplayerusagesnooper.a("world[" + i + "][difficulty]", Integer.valueOf(oworldserver.r));
                    oplayerusagesnooper.a("world[" + i + "][hardcore]", Boolean.valueOf(oworldinfo.t()));
                    oplayerusagesnooper.a("world[" + i + "][generator_name]", oworldinfo.u().a());
                    oplayerusagesnooper.a("world[" + i + "][generator_version]", Integer.valueOf(oworldinfo.u().c()));
                    oplayerusagesnooper.a("world[" + i + "][height]", Integer.valueOf(this.C));
                    oplayerusagesnooper.a("world[" + i + "][chunks_loaded]", Integer.valueOf(oworldserver.J().e()));
                    ++i;
                }
            }
        }

        oplayerusagesnooper.a("worlds", Integer.valueOf(i));
    }

    public void b(OPlayerUsageSnooper oplayerusagesnooper) {
        oplayerusagesnooper.a("singleplayer", Boolean.valueOf(this.I()));
        oplayerusagesnooper.a("server_brand", this.getServerModName());
        oplayerusagesnooper.a("gui_supported", GraphicsEnvironment.isHeadless() ? "headless" : "supported");
        oplayerusagesnooper.a("dedicated", Boolean.valueOf(this.T()));
    }

    public boolean R() {
        return true;
    }

    public int S() {
        return 16;
    }

    public abstract boolean T();

    public boolean U() {
        return this.w;
    }

    public void d(boolean flag) {
        this.w = flag;
    }

    public boolean V() {
        return this.x;
    }

    public void e(boolean flag) {
        this.x = flag;
    }

    public boolean W() {
        return this.y;
    }

    public void f(boolean flag) {
        this.y = flag;
    }

    public boolean X() {
        return this.z;
    }

    public void g(boolean flag) {
        this.z = flag;
    }

    public boolean Y() {
        return this.A;
    }

    public void h(boolean flag) {
        this.A = flag;
    }

    public abstract boolean Z();

    public String aa() {
        return this.B;
    }

    public void o(String s) {
        this.B = s;
    }

    public int ab() {
        return this.C;
    }

    public void d(int i) {
        this.C = i;
    }

    public boolean ac() {
        return this.u;
    }

    public OServerConfigurationManager ad() {
        return this.s;
    }

    public void a(OServerConfigurationManager oserverconfigurationmanager) {
        this.s = oserverconfigurationmanager;
    }

    public void a(OEnumGameType oenumgametype) {
        OWorldServer[] level = D().worlds.get(this.J());
        for (int i = 0; i < level.length; ++i) {
            level[i].L().a(oenumgametype);
        }
    }

    public abstract ONetworkListenThread ae();

    public boolean ag() {
        return false;
    }

    public abstract String a(OEnumGameType oenumgametype, boolean flag);

    public int ah() {
        return this.v;
    }

    public void ai() {
        this.S = true;
    }

    public OChunkCoordinates b() {
        return new OChunkCoordinates(0, 0, 0);
    }

    public int ak() {
        return etc.getInstance().getSpawnProtectionSize(); // CanaryMod
    }

    public boolean a(OWorld oworld, int i, int j, int k, OEntityPlayer oentityplayer) {
        return false;
    }

    public abstract OILogAgent al();

    public static OServerConfigurationManager a(OMinecraftServer ominecraftserver) {
        return ominecraftserver.s;
    }
    
    // CanaryMod start: Custom Stop Message
    public void stopServer(String stopMsg){
    	this.stopMsg = stopMsg;
    	this.n();
    }
    // CanaryMod end
}
