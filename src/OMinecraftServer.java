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

public abstract class OMinecraftServer implements Runnable, OIPlayerUsage, OICommandSender {

    public static Logger a = Logger.getLogger("Minecraft");
    private static OMinecraftServer l = null;
    private final OISaveFormat m;
    private final OPlayerUsageSnooper n = new OPlayerUsageSnooper("server", this);
    private final File o;
    private final List p = new ArrayList();
    private final OICommandManager q;
    public final OProfiler b = new OProfiler();
    private String r;
    private int s = -1;
    //public OWorldServer[] c;
    protected OServerConfigurationManager t;
    private boolean u = true;
    private boolean v = false;
    private int w = 0;
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
    public final long[] f = new long[100];
    public final long[] g = new long[100];
    public final long[] h = new long[100];
    public final long[] i = new long[100];
    public final long[] j = new long[100];
    //public long[][] k;
    private KeyPair I;
    private String J;
    private String K;
    private boolean M;
    private boolean N;
    private boolean O;
    private String P = "";
    private boolean Q = false;
    private long R;
    private String S;
    private boolean T;

    // CanaryMod start: Multiworld \o/
    public Map<String, OWorldServer[]> worlds = new HashMap<String, OWorldServer[]>(1);
    public Map<String, long[][]> worldTickNanos = new HashMap<String, long[][]>(1);
    // CanaryMod end

    public OMinecraftServer(File file1) {
        l = this;
        this.o = file1;
        this.q = new OServerCommandManager();
        this.m = new OAnvilSaveConverter(file1);
        this.al();
    }

    private void al() {
        OBlockDispenser.a.a(OItem.l, new OBehaviorArrowDispense(this));
        OBlockDispenser.a.a(OItem.aP, new OBehaviorEggDispense(this));
        OBlockDispenser.a.a(OItem.aD, new OBehaviorSnowballDispense(this));
        OBlockDispenser.a.a(OItem.bD, new OBehaviorExpBottleDispense(this));
        OBlockDispenser.a.a(OItem.bs, new OBehaviorPotionDispense(this));
        OBlockDispenser.a.a(OItem.bC, new OBehaviorMobEggDispense(this));
        OBlockDispenser.a.a(OItem.bE, new OBehaviorDispenseFireball(this));
        OBehaviorDispenseMinecart obehaviordispenseminecart = new OBehaviorDispenseMinecart(this);

        OBlockDispenser.a.a(OItem.az, obehaviordispenseminecart);
        OBlockDispenser.a.a(OItem.aN, obehaviordispenseminecart);
        OBlockDispenser.a.a(OItem.aO, obehaviordispenseminecart);
        OBlockDispenser.a.a(OItem.aE, new OBehaviorDispenseBoat(this));
        OBehaviorBucketFullDispense obehaviorbucketfulldispense = new OBehaviorBucketFullDispense(this);

        OBlockDispenser.a.a(OItem.ay, obehaviorbucketfulldispense);
        OBlockDispenser.a.a(OItem.ax, obehaviorbucketfulldispense);
        OBlockDispenser.a.a(OItem.aw, new OBehaviorBucketEmptyDispense(this));
    }

    protected abstract boolean c();

    protected void b(String s) {
        if (this.N().b(s)) {
            a.info("Converting map!");
            this.c("menu.convertingLevel");
            this.N().a(s, new OConvertingProgressUpdate(this));
        }
    }

    protected synchronized void c(String s) {
        this.S = s;
    }

<<<<<<<
    protected void a(String s, String s1, long i, OWorldType oworldtype) {
        this.c(s);
        this.d("menu.loadingLevel");
        
        OWorldServer[] toLoad = new OWorldServer[3];

        this.worlds.put(s, toLoad);
        this.worldTickNanos.put(s, new long[toLoad.length][100]);
        
|||||||
    protected void a(String s, String s1, long i, OWorldType oworldtype) {
        this.c(s);
        this.d("menu.loadingLevel");
        this.c = new OWorldServer[3];
        this.k = new long[this.c.length][100];
=======
    protected void a(String s, String s1, long i, OWorldType oworldtype, String s2) {
        this.b(s);
        this.c("menu.loadingLevel");
        this.c = new OWorldServer[3];
        this.k = new long[this.c.length][100];
>>>>>>>
        OISaveHandler oisavehandler = this.m.a(s, true);
        OWorldInfo oworldinfo = oisavehandler.d();
        OWorldSettings oworldsettings;

        if (oworldinfo == null) {
            oworldsettings = new OWorldSettings(i, this.g(), this.f(), this.i(), oworldtype);
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
<<<<<<<
                if (this.L()) {
                    toLoad[j] = new ODemoWorldServer(this, oisavehandler, s1, b0, this.b);
|||||||
                if (this.L()) {
                    this.c[j] = new ODemoWorldServer(this, oisavehandler, s1, b0, this.b);
=======
                if (this.M()) {
                    this.c[j] = new ODemoWorldServer(this, oisavehandler, s1, b0, this.b);
>>>>>>>
                } else {
                    toLoad[j] = new OWorldServer(this, oisavehandler, s1, b0, oworldsettings, this.b);
                }
            } else {
                toLoad[j] = new OWorldServerMulti(this, oisavehandler, s1, b0, oworldsettings, toLoad[0], this.b);
            }

<<<<<<<
            toLoad[j].a((OIWorldAccess) (new OWorldManager(this, toLoad[j])));
            if (!this.H()) {
                toLoad[j].H().a(this.f());
|||||||
            this.c[j].a((OIWorldAccess) (new OWorldManager(this, this.c[j])));
            if (!this.H()) {
                this.c[j].H().a(this.f());
=======
            this.c[j].a((OIWorldAccess) (new OWorldManager(this, this.c[j])));
            if (!this.I()) {
                this.c[j].J().a(this.g());
>>>>>>>
            }

            this.t.a(toLoad);
        }

<<<<<<<
        this.c(this.g());
        this.d(toLoad);
|||||||
        this.c(this.g());
        this.d();
=======
        this.c(this.h());
        this.e();
>>>>>>>
    }

<<<<<<<
    protected void d(OWorldServer[] toLoad) { // CanaryMod: add world array as parameter
        short short1 = 196;
        long i = System.currentTimeMillis();
|||||||
    protected void d() {
        short short1 = 196;
        long i = System.currentTimeMillis();
=======
    protected void e() {
        int i = 0;
>>>>>>>

        this.c("menu.generatingTerrain");
        byte b0 = 0;

<<<<<<<
        for (int j = 0; j < 1; ++j) {
            a.info("Preparing start region for level " + j);
            OWorldServer oworldserver = toLoad[j];
            OChunkCoordinates ochunkcoordinates = oworldserver.E();
|||||||
        for (int j = 0; j < 1; ++j) {
            a.info("Preparing start region for level " + j);
            OWorldServer oworldserver = this.c[j];
            OChunkCoordinates ochunkcoordinates = oworldserver.E();
=======
        a.info("Preparing start region for level " + b0);
        OWorldServer oworldserver = this.c[b0];
        OChunkCoordinates ochunkcoordinates = oworldserver.G();
        long j = System.currentTimeMillis();
>>>>>>>

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
        this.d = s;
        this.e = i;
        a.info(s + ": " + i + "%");
    }

    protected void j() {
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
                        a.info("Saving chunks for level \'" + oworldserver.J().k() + "\'/" + oworldserver.v.l());
                        }

                        oworldserver.a(true, (OIProgressUpdate) null);
                    }
                }
            }
        }
    }

    public void k() {
        if (!this.O) {
            a.info("Stopping server");
            if (this.ae() != null) {
                this.ae().a();
            }

            if (this.t != null) {
                a.info("Saving players");
                this.t.g();
                this.t.r();
            }

            a.info("Saving worlds");
            this.a(false);
            for (OWorldServer[] aoworldserver : this.worlds.values()) {
                int i = aoworldserver.length;

                for (int j = 0; j < i; ++j) {
                    OWorldServer oworldserver = aoworldserver[j];

                oworldserver.m();
                }
            } // CanaryMod: diff visibility

            if (this.n != null && this.n.d()) {
                this.n.e();
            }
        }
    }

    public String l() {
        return this.r;
    }

    public void d(String s) {
        this.r = s;
    }

    public boolean m() {
        return this.u;
    }

    public void n() {
        this.u = false;
    }

    public void run() {
        try {
<<<<<<<
            if (this.b()) {
|||||||
            if (this.b()) {
                long i = System.currentTimeMillis();

                for (long j = 0L; this.u; this.Q = true) {
=======
            if (this.c()) {
                long i = System.currentTimeMillis();

                for (long j = 0L; this.u; this.Q = true) {
>>>>>>>
                // CanaryMod: load once!
                if (!etc.getLoader().isLoaded()) {
                    etc.getLoader().loadPlugins();
                }

                long i = System.currentTimeMillis();

                for (long j = 0L; this.u; this.Q = true) {
                    long k = System.currentTimeMillis();
                    long l = k - i;

                    if (l > 2000L && i - this.R >= 15000L) {
                        a.warning("Can\'t keep up! Did the system time change, or is the server overloaded?");
                        l = 2000L;
                        this.R = i;
                    }

                    if (l < 0L) {
                        a.warning("Time ran backwards! Did the system time change?");
                        l = 0L;
                    }

                    j += l;
                    i = k;
<<<<<<<
                    // CanaryMod start: multiworld sleeping
                    boolean allSleeping = true;

                    for (OWorldServer[] level : this.worlds.values()) {
                        allSleeping &= level[0].e();
                    }
                    // CanaryMod end
                    if (allSleeping) {
                        this.p();
|||||||
                    if (this.c[0].e()) {
                        this.p();
=======
                    if (this.c[0].e()) {
                        this.q();
>>>>>>>
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
            a.log(Level.SEVERE, "Encountered an unexpected exception " + throwable.getClass().getSimpleName(), throwable);
            OCrashReport ocrashreport = null;

            if (throwable instanceof OReportedException) {
                ocrashreport = this.b(((OReportedException) throwable).a());
            } else {
                ocrashreport = this.b(new OCrashReport("Exception in server tick loop", throwable));
            }

            File file1 = new File(new File(this.o(), "crash-reports"), "crash-" + (new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss")).format(new Date()) + "-server.txt");

            if (ocrashreport.a(file1)) {
                a.severe("This crash report has been saved to: " + file1.getAbsolutePath());
            } else {
                a.severe("We were unable to save this crash report to disk.");
            }

            this.a(ocrashreport);
        } finally {
            try {
                this.k();
                this.v = true;
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
        ++this.w;
        if (this.T) {
            this.T = false;
            this.b.a = true;
            this.b.a();
        }

        this.b.a("root");
        this.r();
        if (this.w % 900 == 0) {
            this.b.a("save");
            this.t.g();
            this.a(true);
            this.b.b();
        }

        this.b.a("tallying");
        this.j[this.w % 100] = System.nanoTime() - i;
        this.f[this.w % 100] = OPacket.p - this.E;
        this.E = OPacket.p;
        this.g[this.w % 100] = OPacket.q - this.F;
        this.F = OPacket.q;
        this.h[this.w % 100] = OPacket.n - this.G;
        this.G = OPacket.n;
        this.i[this.w % 100] = OPacket.o - this.H;
        this.H = OPacket.o;
        this.b.b();
        this.b.a("snooper");
        if (!this.n.d() && this.w > 100) {
            this.n.a();
        }

        if (this.w % 6000 == 0) {
            this.n.b();
        }

        this.b.b();
        this.b.b();
    }

    public void r() {
        this.b.a("levels");

        for (Map.Entry<String, OWorldServer[]> entry : this.worlds.entrySet()) {
            OWorldServer[] level = entry.getValue();
            String worldName = entry.getKey();
            for (int i = 0; i < level.length; ++i) {
                long j = System.nanoTime();

<<<<<<<
                if (i == 0 || this.r()) {
                    OWorldServer oworldserver = level[i];
|||||||
            if (i == 0 || this.r()) {
                OWorldServer oworldserver = this.c[i];
=======
            if (i == 0 || this.s()) {
                OWorldServer oworldserver = this.c[i];
>>>>>>>

                this.b.a(oworldserver.J().k());
                this.b.a("pools");
                oworldserver.R().a();
                this.b.b();
                    if (this.w % 20 == 0) {
                        this.b.a("timeSync");
<<<<<<<
                        this.t.sendPacketToDimension((OPacket) (new OPacket4UpdateTime(oworldserver.D())), worldName, oworldserver.w.g);
|||||||
                    this.t.a((OPacket) (new OPacket4UpdateTime(oworldserver.D())), oworldserver.w.g);
=======
                    this.t.a((OPacket) (new OPacket4UpdateTime(oworldserver.E(), oworldserver.F())), oworldserver.v.h);
>>>>>>>
                        this.b.b();
                    }

                    this.b.a("tick");
                    oworldserver.b();
                            oworldserver.h();
                this.b.b();
                            this.b.a("tracker");
                oworldserver.p().a();
                            this.b.b();
                            this.b.b();
                }

                this.worldTickNanos.get(worldName)[i][this.w % 100] = System.nanoTime() - j;
            }
        }

        this.b.c("connection");
        this.ae().b();
        this.b.c("players");
        this.t.b();
        this.b.c("tickables");
        Iterator iterator = this.p.iterator();

        while (iterator.hasNext()) {
            OIUpdatePlayerListBox oiupdateplayerlistbox = (OIUpdatePlayerListBox) iterator.next();

            oiupdateplayerlistbox.a();
        }

        this.b.b();
    }

    public boolean s() {
        return true;
    }

    public void a(OIUpdatePlayerListBox oiupdateplayerlistbox) {
        this.p.add(oiupdateplayerlistbox);
    }

    public static void main(String[] astring) {
        OStatList.a();

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
                odedicatedserver.an();
            }

            odedicatedserver.t();
            Runtime.getRuntime().addShutdownHook(new OThreadDedicatedServer(odedicatedserver));
        } catch (Exception exception) {
            a.log(Level.SEVERE, "Failed to start the minecraft server", exception);
        }
    }

    public void t() {
        (new OThreadMinecraftServer(this, "Server thread")).start();
    }

    public File e(String s) {
        return new File(this.o(), s);
    }

    public void f(String s) {
        a.info(s);
    }

    public void g(String s) {
        a.warning(s);
    }

    public OWorldServer a(int i) {
        throw new UnsupportedOperationException("OMinecraftServer.a(int) has" +
                " been replaced by OMinecraftServer.getWorld(String, int).");
    }
    
    public OWorldServer getWorld(String s, int i) {
        int index = i == 0 ? 0 : i == -1 ? 1 : 2;

        return this.worlds.get(s)[index];
    }

    public String u() {
        return this.r;
    }

    public int v() {
        return this.s;
    }

    public String w() {
        return this.C;
    }

    public String x() {
        return "1.4.1";
    }

    public int y() {
        return this.t.k();
    }

    public int z() {
        return this.t.l();
    }

    public String[] A() {
        return this.t.d();
    }

    public String B() {
        return "";
    }

    public String h(String s) {
        ORConConsoleSource.a.c();
        this.q.a(ORConConsoleSource.a, s);
        return ORConConsoleSource.a.d();
    }

    public boolean C() {
        return false;
    }

    public void i(String s) {
        a.log(Level.SEVERE, s);
    }

    public void j(String s) {
        if (this.C()) {
            a.log(Level.INFO, s);
        }
    }

    public String getServerModName() {
        return "CanaryMod";
    }

    public OCrashReport b(OCrashReport ocrashreport) {
        ocrashreport.a("Is Modded", (Callable) (new OCallableIsServerModded(this)));
        ocrashreport.a("Profiler Position", (Callable) (new OCallableServerProfiler(this)));
        if (this.c != null && this.c.length > 0 && this.c[0] != null) {
            ocrashreport.a("Vec3 Pool Size", (Callable) (new OCallableServerMemoryStats(this)));
        }

        if (this.t != null) {
            ocrashreport.a("Player Count", (Callable) (new OCallablePlayers(this)));
        }

        if (this.worlds != null) {
            for (OWorldServer[] level : this.worlds.values()) {
                OWorldServer[] aoworldserver = level;
                int i = aoworldserver.length;

                for (int j = 0; j < i; ++j) {
                    OWorldServer oworldserver = aoworldserver[j];

                    if (oworldserver != null) {
                        oworldserver.a(ocrashreport);
                    }
                }
            }
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

    public static OMinecraftServer D() {
        return l;
    }

    public String c_() {
        return "Server";
    }

    public void a(String s) {
        a.info(OStringUtils.a(s));
    }

    public boolean a(int i, String s) {
        return true;
    }

    public String a(String s, Object... aobject) {
        return OStringTranslate.a().a(s, aobject);
    }

    public OICommandManager E() {
        return this.q;
    }

    public KeyPair F() {
        return this.I;
    }

    public int G() {
        return this.s;
    }

    public void b(int i) {
        this.s = i;
    }

    public String H() {
        return this.J;
    }

    public void k(String s) {
        this.J = s;
    }

    public boolean I() {
        return this.J != null;
    }

    public String J() {
        return this.K;
    }

    public void l(String s) {
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
                if (oworldserver.J().t()) {
                    oworldserver.t = 3;
                        oworldserver.a(true, true);
                } else if (this.I()) {
                    oworldserver.t = i;
                    oworldserver.a(oworldserver.t > 0, true);
                    } else {
<<<<<<<
                        oworldserver.u = i;
                        oworldserver.a(this.K(), this.y);
                    }
|||||||
                    oworldserver.u = i;
                    oworldserver.a(this.K(), this.y);
=======
                    oworldserver.t = i;
                    oworldserver.a(this.L(), this.y);
>>>>>>>
                }
            }
        }
    }

    protected boolean L() {
        return true;
    }

    public boolean M() {
        return this.M;
    }

    public void b(boolean flag) {
        this.M = flag;
    }

    public void c(boolean flag) {
        this.N = flag;
    }

    public OISaveFormat N() {
        return this.m;
    }

    public void P() {
        this.O = true;
        this.N().d();

        for (OWorldServer[] level : this.worlds.values()) {
            
            for (int i = 0; i < level.length; ++i) {
                OWorldServer oworldserver = level[i];

                if (oworldserver != null) {
                oworldserver.m();
                }
            }

<<<<<<<
            this.M().e(level[0].G().g());
        }
        this.m();
|||||||
        this.M().e(this.c[0].G().g());
        this.m();
=======
        this.N().e(this.c[0].I().g());
        this.n();
>>>>>>>
    }

    public String Q() {
        return this.P;
    }

    public void n(String s) {
        this.P = s;
    }

    public void a(OPlayerUsageSnooper oplayerusagesnooper) {
        oplayerusagesnooper.a("whitelist_enabled", Boolean.valueOf(false));
        oplayerusagesnooper.a("whitelist_count", Integer.valueOf(0));
        oplayerusagesnooper.a("players_current", Integer.valueOf(this.y()));
        oplayerusagesnooper.a("players_max", Integer.valueOf(this.z()));
        oplayerusagesnooper.a("players_seen", Integer.valueOf(this.t.m().length));
        oplayerusagesnooper.a("uses_auth", Boolean.valueOf(this.x));
        oplayerusagesnooper.a("gui_state", this.ag() ? "enabled" : "disabled");
        oplayerusagesnooper.a("avg_tick_ms", Integer.valueOf((int) (OMathHelper.a(this.j) * 1.0E-6D)));
        oplayerusagesnooper.a("avg_sent_packet_count", Integer.valueOf((int) OMathHelper.a(this.f)));
        oplayerusagesnooper.a("avg_sent_packet_size", Integer.valueOf((int) OMathHelper.a(this.g)));
<<<<<<<
|||||||
        for (int j = 0; j < this.c.length; ++j) {
            if (this.c[j] != null) {
                OWorldServer oworldserver = this.c[j];
                OWorldInfo oworldinfo = oworldserver.H();

                oplayerusagesnooper.a("world[" + i + "][dimension]", Integer.valueOf(oworldserver.w.g));
                oplayerusagesnooper.a("world[" + i + "][mode]", oworldinfo.q());
                oplayerusagesnooper.a("world[" + i + "][difficulty]", Integer.valueOf(oworldserver.u));
                oplayerusagesnooper.a("world[" + i + "][hardcore]", Boolean.valueOf(oworldinfo.s()));
                oplayerusagesnooper.a("world[" + i + "][generator_name]", oworldinfo.t().a());
                oplayerusagesnooper.a("world[" + i + "][generator_version]", Integer.valueOf(oworldinfo.t().c()));
                oplayerusagesnooper.a("world[" + i + "][height]", Integer.valueOf(this.D));
                oplayerusagesnooper.a("world[" + i + "][chunks_loaded]", Integer.valueOf(oworldserver.F().e()));
                ++i;
            }
        }
=======
        for (int j = 0; j < this.c.length; ++j) {
            if (this.c[j] != null) {
                OWorldServer oworldserver = this.c[j];
                OWorldInfo oworldinfo = oworldserver.J();

                oplayerusagesnooper.a("world[" + i + "][dimension]", Integer.valueOf(oworldserver.v.h));
                oplayerusagesnooper.a("world[" + i + "][mode]", oworldinfo.r());
                oplayerusagesnooper.a("world[" + i + "][difficulty]", Integer.valueOf(oworldserver.t));
                oplayerusagesnooper.a("world[" + i + "][hardcore]", Boolean.valueOf(oworldinfo.t()));
                oplayerusagesnooper.a("world[" + i + "][generator_name]", oworldinfo.u().a());
                oplayerusagesnooper.a("world[" + i + "][generator_version]", Integer.valueOf(oworldinfo.u().c()));
                oplayerusagesnooper.a("world[" + i + "][height]", Integer.valueOf(this.D));
                oplayerusagesnooper.a("world[" + i + "][chunks_loaded]", Integer.valueOf(oworldserver.H().e()));
                ++i;
            }
        }
>>>>>>>
        oplayerusagesnooper.a("avg_rec_packet_count", Integer.valueOf((int) OMathHelper.a(this.h)));
        oplayerusagesnooper.a("avg_rec_packet_size", Integer.valueOf((int) OMathHelper.a(this.i)));
        int i = 0;

        for (Map.Entry<String, OWorldServer[]> entry : this.worlds.entrySet()) {
            OWorldServer[] level = entry.getValue();
            String levelName = entry.getKey();
            for (int j = 0; j < level.length; ++j) {
                if (level[j] != null) {
                    OWorldServer oworldserver = level[j];
                    OWorldInfo oworldinfo = oworldserver.H();

                    oplayerusagesnooper.a("world[" + levelName + "][" + i + "][dimension]", Integer.valueOf(oworldserver.w.g));
                    oplayerusagesnooper.a("world[" + levelName + "][" + i + "][mode]", oworldinfo.q());
                    oplayerusagesnooper.a("world[" + levelName + "][" + i + "][difficulty]", Integer.valueOf(oworldserver.u));
                    oplayerusagesnooper.a("world[" + levelName + "][" + i + "][hardcore]", Boolean.valueOf(oworldinfo.s()));
                    oplayerusagesnooper.a("world[" + levelName + "][" + i + "][generator_name]", oworldinfo.t().a());
                    oplayerusagesnooper.a("world[" + levelName + "][" + i + "][generator_version]", Integer.valueOf(oworldinfo.t().c()));
                    oplayerusagesnooper.a("world[" + levelName + "][" + i + "][height]", Integer.valueOf(this.D));
                    oplayerusagesnooper.a("world[" + levelName + "][" + i + "][chunks_loaded]", Integer.valueOf(oworldserver.F().e()));
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
        return this.x;
    }

    public void d(boolean flag) {
        this.x = flag;
    }

    public boolean V() {
        return this.y;
    }

    public void e(boolean flag) {
        this.y = flag;
    }

    public boolean W() {
        return this.z;
    }

    public void f(boolean flag) {
        this.z = flag;
    }

    public boolean X() {
        return this.A;
    }

    public void g(boolean flag) {
        this.A = flag;
    }

    public boolean Y() {
        return this.B;
    }

    public void h(boolean flag) {
        this.B = flag;
    }

    public abstract boolean Z();

    public String aa() {
        return this.C;
    }

    public void o(String s) {
        this.C = s;
    }

    public int ab() {
        return this.D;
    }

    public void d(int i) {
        this.D = i;
    }

    public boolean ac() {
        return this.v;
    }

    public OServerConfigurationManager ad() {
        return this.t;
    }

    public void a(OServerConfigurationManager oserverconfigurationmanager) {
        this.t = oserverconfigurationmanager;
    }

    public void a(OEnumGameType oenumgametype) {
<<<<<<<
        OWorldServer[] level = C().worlds.get(this.I());
        for (int i = 0; i < level.length; ++i) {
            level[i].H().a(oenumgametype);
|||||||
        for (int i = 0; i < this.c.length; ++i) {
            C().c[i].H().a(oenumgametype);
=======
        for (int i = 0; i < this.c.length; ++i) {
            D().c[i].J().a(oenumgametype);
>>>>>>>
        }
    }

    public abstract ONetworkListenThread ae();

    public boolean ag() {
        return false;
    }

    public abstract String a(OEnumGameType oenumgametype, boolean flag);

    public int ah() {
        return this.w;
    }

    public void ai() {
        this.T = true;
    }

    public OChunkCoordinates b() {
        return new OChunkCoordinates(0, 0, 0);
    }

    public int ak() {
        return 16;
    }

    public static OServerConfigurationManager a(OMinecraftServer ominecraftserver) {
        return ominecraftserver.t;
    }
}
