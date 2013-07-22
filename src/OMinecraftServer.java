import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.net.Proxy;
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

    private static OMinecraftServer l;
    private final OISaveFormat m;
    private final OPlayerUsageSnooper n = new OPlayerUsageSnooper("server", this, aq());
    private final File o;
    private final List p = new ArrayList();
    private final OICommandManager q;
    public final OProfiler a = new OProfiler();
    private String r;
    private int s = -1;
    //public OWorldServer[] b;
    private OServerConfigurationManager t;
    private boolean u = true;
    private boolean v;
    private int w;
    protected Proxy c;
    public String d;
    public int e;
    private boolean x;
    private boolean y;
    private boolean z;
    private boolean A;
    private boolean B;
    private String C;
    private int D;
    private long E;
    private long F;
    private long G;
    private long H;
    public final long[] f;
    public final long[] g;
    public final long[] h;
    public final long[] i;
    public final long[] j;
    //public long[][] k;
    private KeyPair I;
    private String J;
    private String K;
    private boolean M;
    private boolean N;
    private boolean O;
    private String P;
    private boolean Q;
    private long R;
    private String S;
    private boolean T;
    private boolean U;

    // CanaryMod start: Multiworld \o/
    public Map<String, OWorldServer[]> worlds = new HashMap<String, OWorldServer[]>(1);
    public Map<String, long[][]> worldTickNanos = new HashMap<String, long[][]>(1);
    // CanaryMod end

    // CanaryMod start: Stop Message
    private String stopMsg;
    // CanaryMod end

    public OMinecraftServer(File file1) {
        this.c = Proxy.NO_PROXY;
        this.f = new long[100];
        this.g = new long[100];
        this.h = new long[100];
        this.i = new long[100];
        this.j = new long[100];
        this.P = "";
        l = this;
        this.o = file1;
        this.q = new OServerCommandManager();
        this.m = new OAnvilSaveConverter(file1);
        this.ar();
    }

    private void ar() {
        ODispenserBehaviors.a();
    }

    protected abstract boolean d() throws IOException;

    protected void a(String s) {
        if (this.P().b(s)) {
            this.an().a("Converting map!");
            this.b("menu.convertingLevel");
            this.P().a(s, new OConvertingProgressUpdate(this));
        }
    }

    protected synchronized void b(String s) {
        this.S = s;
    }

    protected void a(String s, String s1, long i, OWorldType oworldtype, String s2) {
        this.a(s);
        this.b("menu.loadingLevel");

        OWorldServer[] toLoad = new OWorldServer[3];

        this.worlds.put(s, toLoad);
        this.worldTickNanos.put(s, new long[toLoad.length][100]);

        OISaveHandler oisavehandler = this.m.a(s, true);
        OWorldInfo oworldinfo = oisavehandler.d();
        OWorldSettings oworldsettings;

        if (oworldinfo == null) {
            oworldsettings = new OWorldSettings(i, this.h(), this.g(), this.j(), oworldtype);
            oworldsettings.a(s2);
        } else {
            oworldsettings = new OWorldSettings(oworldinfo);
        }

        if (this.N) {
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
                if (this.O()) {
                    toLoad[j] = new ODemoWorldServer(this, oisavehandler, s1, b0, this.a, this.an());
                } else {
                    toLoad[j] = new OWorldServer(this, oisavehandler, s1, b0, oworldsettings, this.a, this.an());
                }
            } else {
                toLoad[j] = new OWorldServerMulti(this, oisavehandler, s1, b0, oworldsettings, toLoad[0], this.a, this.an());
            }

            toLoad[j].a((OIWorldAccess) (new OWorldManager(this, toLoad[j])));
            if (!this.K()) {
                toLoad[j].N().a(this.h());
            }

            this.t.a(toLoad);
        }

        this.c(this.i());
        this.f(toLoad);
    }

    protected void f(OWorldServer[] toLoad) { // CanaryMod: add world array as parameter
        boolean flag = true;
        boolean flag1 = true;
        boolean flag2 = true;
        boolean flag3 = true;
        int i = 0;

        this.b("menu.generatingTerrain");
        byte b0 = 0;

        this.an().a("Preparing start region for level " + b0);
        OWorldServer oworldserver = toLoad[b0];
        OChunkCoordinates ochunkcoordinates = oworldserver.K();
        long j = aq();

        for (int k = -192; k <= 192 && this.o(); k += 16) {
            for (int l = -192; l <= 192 && this.o(); l += 16) {
                long i1 = aq();

                if (i1 - j > 1000L) {
                    this.a_("Preparing spawn area", i * 100 / 625);
                    j = i1;
                }

                ++i;
                oworldserver.b.c(ochunkcoordinates.a + k >> 4, ochunkcoordinates.c + l >> 4);
            }
        }

        this.l();
    }

    public abstract boolean g();

    public abstract OEnumGameType h();

    public abstract int i();

    public abstract boolean j();

    public abstract int k();

    protected void a_(String s, int i) {
        this.d = s;
        this.e = i;
        this.an().a(s + ": " + i + "%");
    }

    protected void l() {
        this.d = null;
        this.e = 0;
    }

    protected void a(boolean flag) {
        if (!this.O) {
            for (OWorldServer[] aoworldserver : this.worlds.values()) {
                int i = aoworldserver.length;

                for (int j = 0; j < i; ++j) {
                    OWorldServer oworldserver = aoworldserver[j];

                    if (oworldserver != null) {
                        if (!flag) {
                            this.an().a("Saving chunks for level \'" + oworldserver.N().k() + "\'/" + oworldserver.t.l());
                        }

                        try {
                            oworldserver.a(true, (OIProgressUpdate) null);
                        } catch (OMinecraftException ominecraftexception) {
                            this.an().b(ominecraftexception.getMessage());
                        }
                    }
                }
            }
        }
    }

    public void m() {
        if (!this.O) {
            this.an().a("Stopping server");
            if (this.ag() != null) {
                this.ag().a();
            }

            etc.getLoader().unloadPlugins(); // CanaryMod: unload plugins

            if (this.t != null) {
                this.an().a("Saving players");
                this.t.g();
                this.t.r(stopMsg); // CanaryMod: custom stop message added
            }

            this.an().a("Saving worlds");
            this.a(false);
            for (OWorldServer[] aoworldserver : this.worlds.values()) {
                for (int i = 0; i < aoworldserver.length; ++i) {
                    OWorldServer oworldserver = aoworldserver[i];

                    oworldserver.n();
                }
            } // CanaryMod: diff visibility

            if (this.n != null && this.n.d()) {
                this.n.e();
            }
        }
    }

    public String n() {
        return this.r;
    }

    public void c(String s) {
        this.r = s;
    }

    public boolean o() {
        return this.u;
    }

    public void p() {
        this.u = false;
    }

    public void run() {
        try {
            if (this.d()) {
                // CanaryMod: load once!
                if (!etc.getLoader().isLoaded()) {
                    etc.getLoader().loadPlugins();
                }

                long i = aq();

                for (long j = 0L; this.u; this.Q = true) {
                    long k = aq();
                    long l = k - i;

                    if (l > 2000L && i - this.R >= 15000L) {
                        this.an().b("Can\'t keep up! Did the system time change, or is the server overloaded?");
                        l = 2000L;
                        this.R = i;
                    }

                    if (l < 0L) {
                        this.an().b("Time ran backwards! Did the system time change?");
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
                        this.s();
                        j = 0L;
                    } else {
                        while (j > 50L) {
                            j -= 50L;
                            this.s();
                        }
                    }

                    Thread.sleep(1L);
                }
            } else {
                this.a((OCrashReport) null);
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            this.an().c("Encountered an unexpected exception " + throwable.getClass().getSimpleName(), throwable);
            OCrashReport ocrashreport = null;

            if (throwable instanceof OReportedException) {
                ocrashreport = this.b(((OReportedException) throwable).a());
            } else {
                ocrashreport = this.b(new OCrashReport("Exception in server tick loop", throwable));
            }

            File file1 = new File(new File(this.q(), "crash-reports"), "crash-" + (new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss")).format(new Date()) + "-server.txt");

            if (ocrashreport.a(file1, this.an())) {
                this.an().c("This crash report has been saved to: " + file1.getAbsolutePath());
            } else {
                this.an().c("We were unable to save this crash report to disk.");
            }

            this.a(ocrashreport);
        } finally {
            try {
                this.m();
                this.v = true;
            } catch (Throwable throwable1) {
                throwable1.printStackTrace();
            } finally {
                this.r();
            }
        }
    }

    protected File q() {
        return new File(".");
    }

    protected void a(OCrashReport ocrashreport) {}

    protected void r() {}

    protected void s() {
        long i = System.nanoTime();

        OAxisAlignedBB.a().a();
        ++this.w;
        if (this.T) {
            this.T = false;
            this.a.a = true;
            this.a.a();
        }

        this.a.a("root");
        this.t();
        if (this.w % 900 == 0) {
            this.a.a("save");
            this.t.g();
            this.a(true);
            this.a.b();
        }

        this.a.a("tallying");
        this.j[this.w % 100] = System.nanoTime() - i;
        this.f[this.w % 100] = OPacket.q - this.E;
        this.E = OPacket.q;
        this.g[this.w % 100] = OPacket.r - this.F;
        this.F = OPacket.r;
        this.h[this.w % 100] = OPacket.o - this.G;
        this.G = OPacket.o;
        this.i[this.w % 100] = OPacket.p - this.H;
        this.H = OPacket.p;
        this.a.b();
        this.a.a("snooper");
        if (!this.n.d() && this.w > 100) {
            this.n.a();
        }

        if (this.w % 6000 == 0) {
            this.n.b();
        }

        this.a.b();
        this.a.b();
    }

    public void t() {
        this.a.a("levels");

        int i;
        for (Map.Entry<String, OWorldServer[]> entry : this.worlds.entrySet()) {
            OWorldServer[] level = entry.getValue();
            String worldName = entry.getKey();

            for (i = 0; i < level.length; ++i) {
                long j = System.nanoTime();
                if (level[i] == null) continue; // CanaryMod: prevent NPE

                if (i == 0 || this.u()) {
                    OWorldServer oworldserver = level[i];

                    this.a.a(oworldserver.N().k());
                    this.a.a("pools");
                    oworldserver.V().a();
                    this.a.b();
                    if (this.w % 20 == 0) {
                        this.a.a("timeSync");
                        //CanaryMod: send packet for multiworld
                        this.t.sendPacketToDimension((OPacket) (new OPacket4UpdateTime(oworldserver.I(), oworldserver.J(), oworldserver.O().b("doDaylightCycle"))), worldName, oworldserver.t.i);
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
                    oworldserver.q().a();
                    this.a.b();
                    this.a.b();
                }

                this.worldTickNanos.get(worldName)[i][this.w % 100] = System.nanoTime() - j;
            }
        }

        this.a.c("connection");
        this.ag().b();
        this.a.c("players");
        this.t.b();
        this.a.c("tickables");

        for (i = 0; i < this.p.size(); ++i) {
            ((OIUpdatePlayerListBox) this.p.get(i)).a();
        }

        this.a.b();
    }

    public boolean u() {
        return true;
    }

    public void a(OIUpdatePlayerListBox oiupdateplayerlistbox) {
        this.p.add(oiupdateplayerlistbox);
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

            oilogagent = odedicatedserver.an();
            if (s != null) {
                odedicatedserver.j(s);
            }

            if (s2 != null) {
                odedicatedserver.k(s2);
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
                odedicatedserver.at();
            }

            odedicatedserver.v();
            Runtime.getRuntime().addShutdownHook(new OThreadDedicatedServer(odedicatedserver));
        } catch (Exception exception) {
            if (oilogagent != null) {
                oilogagent.c("Failed to start the minecraft server", exception);
            } else {
                Logger.getAnonymousLogger().log(Level.SEVERE, "Failed to start the minecraft server", exception);
            }
        }
    }

    public void v() {
        (new OThreadMinecraftServer(this, "Server thread")).start();
    }

    public File d(String s) {
        return new File(this.q(), s);
    }

    public void e(String s) {
        this.an().a(s);
    }

    public void f(String s) {
        this.an().b(s);
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

    public String w() {
        return this.r;
    }

    public int x() {
        return this.s;
    }

    public String y() {
        return this.C;
    }

    public String z() {
        return "1.6.1";
    }

    public int A() {
        return this.t.k();
    }

    public int B() {
        return this.t.l();
    }

    public String[] C() {
        return this.t.d();
    }

    public String D() {
        return "";
    }

    public String g(String s) {
        ORConConsoleSource.a.d();
        this.q.a(ORConConsoleSource.a, s);
        return ORConConsoleSource.a.e();
    }

    public boolean E() {
        return false;
    }

    public void h(String s) {
        this.an().c(s);
    }

    public void i(String s) {
        if (this.E()) {
            this.an().a(s);
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

        if (this.t != null) {
            ocrashreport.g().a("Player Count", (Callable) (new OCallableServerMemoryStats(this)));
        }

        return ocrashreport;
    }

    public List a(OICommandSender oicommandsender, String s) {
        ArrayList arraylist = new ArrayList();

        if (s.startsWith("/")) {
            s = s.substring(1);
            boolean flag = !s.contains(" ");
            List list = this.q.b(oicommandsender, s);

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
            String[] astring1 = this.t.d();
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

    public static OMinecraftServer F() {
        return l;
    }

    public String c_() {
        return "Server";
    }

    public void a(OChatMessageComponent ochatmessagecomponent) {
        this.an().a(ochatmessagecomponent.toString());
    }

    public boolean a(int i, String s) {
        return true;
    }

    public OICommandManager G() {
        return this.q;
    }

    public KeyPair H() {
        return this.I;
    }

    public int I() {
        return this.s;
    }

    public void b(int i) {
        this.s = i;
    }

    public String J() {
        return this.J;
    }

    public void j(String s) {
        this.J = s;
    }

    public boolean K() {
        return this.J != null;
    }

    public String L() {
        return this.K;
    }

    public void k(String s) {
        this.K = s;
    }

    public void a(KeyPair keypair) {
        this.I = keypair;
    }

    public void c(int i) {
        for (OWorldServer[] aworld : this.worlds.values()) {
            for (int j = 0; j < aworld.length; ++j) {
                OWorldServer oworldserver = aworld[j];

                if (oworldserver != null) {
                if (oworldserver.N().t()) {
                    oworldserver.r = 3;
                        oworldserver.a(true, true);
                } else if (this.K()) {
                    oworldserver.r = i;
                    oworldserver.a(oworldserver.r > 0, true);
                    } else {
                    oworldserver.r = i;
                    oworldserver.a(this.N(), this.y);
                    }
                }
            }
        } //
    }

    protected boolean N() {
        return true;
    }

    public boolean O() {
        return this.M;
    }

    public void b(boolean flag) {
        this.M = flag;
    }

    public void c(boolean flag) {
        this.N = flag;
    }

    public OISaveFormat P() {
        return this.m;
    }

    public void R() {
        this.O = true;
        this.P().d();

        for (OWorldServer[] level : this.worlds.values()) {
            for (int i = 0; i < level.length; ++i) {
                OWorldServer oworldserver = level[i];

                if (oworldserver != null) {
                    oworldserver.n();
                }
            }

            this.P().e(level[0].M().g());
        } //
        this.p();
    }

    public String S() {
        return this.P;
    }

    public void m(String s) {
        this.P = s;
    }

    public void a(OPlayerUsageSnooper oplayerusagesnooper) {
        oplayerusagesnooper.a("whitelist_enabled", Boolean.valueOf(etc.getInstance().isWhitelistEnabled()));
        oplayerusagesnooper.a("whitelist_count", Integer.valueOf(-1));
        oplayerusagesnooper.a("players_current", Integer.valueOf(this.A()));
        oplayerusagesnooper.a("players_max", Integer.valueOf(this.B()));
        oplayerusagesnooper.a("players_seen", Integer.valueOf(this.t.m().length));
        oplayerusagesnooper.a("uses_auth", Boolean.valueOf(this.x));
        oplayerusagesnooper.a("gui_state", this.ai() ? "enabled" : "disabled");
        oplayerusagesnooper.a("run_time", Long.valueOf((aq() - oplayerusagesnooper.g()) / 60L * 1000L));
        oplayerusagesnooper.a("avg_tick_ms", Integer.valueOf((int) (OMathHelper.a(this.j) * 1.0E-6D)));
        oplayerusagesnooper.a("avg_sent_packet_count", Integer.valueOf((int) OMathHelper.a(this.f)));
        oplayerusagesnooper.a("avg_sent_packet_size", Integer.valueOf((int) OMathHelper.a(this.g)));
        oplayerusagesnooper.a("avg_rec_packet_count", Integer.valueOf((int) OMathHelper.a(this.h)));
        oplayerusagesnooper.a("avg_rec_packet_size", Integer.valueOf((int) OMathHelper.a(this.i)));
        int i = 0;
        for (Map.Entry<String, OWorldServer[]> entry : this.worlds.entrySet()) {
            OWorldServer[] level = entry.getValue();
            String levelName = entry.getKey();

            for (int j = 0; j < level.length; ++j) {
                if (level[j] != null) {
                    OWorldServer oworldserver = level[j];
                    OWorldInfo oworldinfo = oworldserver.N();

                    oplayerusagesnooper.a("world[" + level + "][" + i + "][dimension]", Integer.valueOf(oworldserver.t.i));
                    oplayerusagesnooper.a("world[" + level + "][" + i + "][mode]", oworldinfo.r());
                    oplayerusagesnooper.a("world[" + level + "][" + i + "][difficulty]", Integer.valueOf(oworldserver.r));
                    oplayerusagesnooper.a("world[" + level + "][" + i + "][hardcore]", Boolean.valueOf(oworldinfo.t()));
                    oplayerusagesnooper.a("world[" + level + "][" + i + "][generator_name]", oworldinfo.u().a());
                    oplayerusagesnooper.a("world[" + level + "][" + i + "][generator_version]", Integer.valueOf(oworldinfo.u().c()));
                    oplayerusagesnooper.a("world[" + level + "][" + i + "][height]", Integer.valueOf(this.D));
                    oplayerusagesnooper.a("world[" + level + "][" + i + "][chunks_loaded]", Integer.valueOf(oworldserver.L().f()));
                    ++i;
                }
            }
        }

        oplayerusagesnooper.a("worlds", Integer.valueOf(i));
    }

    public void b(OPlayerUsageSnooper oplayerusagesnooper) {
        oplayerusagesnooper.a("singleplayer", Boolean.valueOf(this.K()));
        oplayerusagesnooper.a("server_brand", this.getServerModName());
        oplayerusagesnooper.a("gui_supported", GraphicsEnvironment.isHeadless() ? "headless" : "supported");
        oplayerusagesnooper.a("dedicated", Boolean.valueOf(this.V()));
    }

    public boolean T() {
        return true;
    }

    public int U() {
        return 16;
    }

    public abstract boolean V();

    public boolean W() {
        return this.x;
    }

    public void d(boolean flag) {
        this.x = flag;
    }

    public boolean X() {
        return this.y;
    }

    public void e(boolean flag) {
        this.y = flag;
    }

    public boolean Y() {
        return this.z;
    }

    public void f(boolean flag) {
        this.z = flag;
    }

    public boolean Z() {
        return this.A;
    }

    public void g(boolean flag) {
        this.A = flag;
    }

    public boolean aa() {
        return this.B;
    }

    public void h(boolean flag) {
        this.B = flag;
    }

    public abstract boolean ab();

    public String ac() {
        return this.C;
    }

    public void n(String s) {
        this.C = s;
    }

    public int ad() {
        return this.D;
    }

    public void d(int i) {
        this.D = i;
    }

    public boolean ae() {
        return this.v;
    }

    public OServerConfigurationManager af() {
        return this.t;
    }

    public void a(OServerConfigurationManager oserverconfigurationmanager) {
        this.t = oserverconfigurationmanager;
    }

    public void a(OEnumGameType oenumgametype) {
        OWorldServer[] level = F().worlds.get(this.L());
        for (int i = 0; i < level.length; ++i) {
            level[i].N().a(oenumgametype);
        }
    }

    public abstract ONetworkListenThread ag();

    public boolean ai() {
        return false;
    }

    public abstract String a(OEnumGameType oenumgametype, boolean flag);

    public int aj() {
        return this.w;
    }

    public void ak() {
        this.T = true;
    }

    public OChunkCoordinates b() {
        return new OChunkCoordinates(0, 0, 0);
    }

    public OWorld f_() {
        return this.getWorld(this.L(), 0);
    }

    public int am() {
        return etc.getInstance().getSpawnProtectionSize(); // CanaryMod
    }

    public boolean a(OWorld oworld, int i, int j, int k, OEntityPlayer oentityplayer) {
        return false;
    }

    public abstract OILogAgent an();

    public void i(boolean flag) {
        this.U = flag;
    }

    public boolean ao() {
        return this.U;
    }

    public Proxy ap() {
        return this.c;
    }

    public static long aq() {
        return System.currentTimeMillis();
    }

    public static OServerConfigurationManager a(OMinecraftServer ominecraftserver) {
        return ominecraftserver.t;
    }

    // CanaryMod start: Custom Stop Message
    public void stopServer(String stopMsg){
        this.stopMsg = stopMsg;
        this.p();
    }
    // CanaryMod end
}
