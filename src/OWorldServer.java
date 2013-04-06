import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class OWorldServer extends OWorld {

    private final OMinecraftServer a;
    private final OEntityTracker J;
    private final OPlayerManager K;
    private Set L;
    private TreeSet M;
    public OChunkProviderServer b;
    public boolean c;
    private boolean N;
    private int O = 0;
    private final OTeleporter P;
    private OServerBlockEventList[] Q = new OServerBlockEventList[] { new OServerBlockEventList((OServerBlockEvent) null), new OServerBlockEventList((OServerBlockEvent) null)};
    private int R = 0;
    private static final OWeightedRandomChestContent[] S = new OWeightedRandomChestContent[] { new OWeightedRandomChestContent(OItem.E.cp, 0, 1, 3, 10), new OWeightedRandomChestContent(OBlock.B.cz, 0, 1, 3, 10), new OWeightedRandomChestContent(OBlock.N.cz, 0, 1, 3, 10), new OWeightedRandomChestContent(OItem.z.cp, 0, 1, 1, 3), new OWeightedRandomChestContent(OItem.v.cp, 0, 1, 1, 5), new OWeightedRandomChestContent(OItem.y.cp, 0, 1, 1, 3), new OWeightedRandomChestContent(OItem.u.cp, 0, 1, 1, 5), new OWeightedRandomChestContent(OItem.k.cp, 0, 2, 3, 5), new OWeightedRandomChestContent(OItem.V.cp, 0, 2, 3, 3)};
    private ArrayList T = new ArrayList();
    private OIntHashMap U;

    public OWorldServer(OMinecraftServer ominecraftserver, OISaveHandler oisavehandler, String s, int i, OWorldSettings oworldsettings, OProfiler oprofiler, OILogAgent oilogagent) {
        super(oisavehandler, s, oworldsettings, OWorldProvider.a(i), oprofiler, oilogagent);
        this.a = ominecraftserver;
        this.J = new OEntityTracker(this);
        this.K = new OPlayerManager(this, ominecraftserver.ad().o());
        if (this.U == null) {
            this.U = new OIntHashMap();
        }

        if (this.L == null) {
            this.L = new HashSet();
        }

        if (this.M == null) {
            this.M = new TreeSet();
        }

        this.P = new OTeleporter(this);
        this.D = new OServerScoreboard(ominecraftserver);
        OScoreboardSaveData oscoreboardsavedata = (OScoreboardSaveData) this.z.a(OScoreboardSaveData.class, "scoreboard");

        if (oscoreboardsavedata == null) {
            oscoreboardsavedata = new OScoreboardSaveData();
            this.z.a("scoreboard", (OWorldSavedData) oscoreboardsavedata);
        }

        oscoreboardsavedata.a(this.D);
        ((OServerScoreboard) this.D).a(oscoreboardsavedata);
    }

    public void b() {
        super.b();
        if (this.L().t() && this.r < 3) {
            this.r = 3;
        }

        this.t.d.b();
        if (this.e()) {
            boolean flag = false;

            if (this.E && this.r >= 1) {
                ;
            }

            if (!flag) {
                long i = this.x.g() + 24000L;

                // CanaryMod: Time hook
                if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.TIME_CHANGE, world, i - i % 24000L)) {
                    this.x.c(i - i % 24000L);
                }
                this.d();
            }
        }

        this.C.a("mobSpawner");
        if (this.M().b("doMobSpawning")) {
            OSpawnerAnimals.a(this, this.E, this.F, this.x.f() % 400L == 0L);
        }

        this.C.c("chunkSource");
        this.v.b();
        int j = this.a(1.0F);

        if (j != this.j) {
            this.j = j;
        }

        // CanaryMod: Time hook
        if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.TIME_CHANGE, world, this.x.f() + 1L)) {
            this.x.b(this.x.f() + 1L);
            this.x.c(this.x.g() + 1L);
        }
        this.C.c("tickPending");
        this.a(false);
        this.C.c("tickTiles");
        this.g();
        this.C.c("chunkMap");
        this.K.b();
        this.C.c("village");
        this.A.a();
        this.B.a();
        this.C.c("portalForcer");
        this.P.a(this.G());
        this.C.b();
        this.Y();
    }

    public OSpawnListEntry a(OEnumCreatureType oenumcreaturetype, int i, int j, int k) {
        List list = this.J().a(oenumcreaturetype, i, j, k);

        return list != null && !list.isEmpty() ? (OSpawnListEntry) OWeightedRandom.a(this.s, (Collection) list) : null;
    }

    public void c() {
        this.N = !this.h.isEmpty();
        Iterator iterator = this.h.iterator();

        while (iterator.hasNext()) {
            OEntityPlayer oentityplayer = (OEntityPlayer) iterator.next();

            if (!oentityplayer.bz()) {
                this.N = false;
                break;
            }
        }
    }

    protected void d() {
        this.N = false;
        Iterator iterator = this.h.iterator();

        while (iterator.hasNext()) {
            OEntityPlayer oentityplayer = (OEntityPlayer) iterator.next();

            if (oentityplayer.bz()) {
                oentityplayer.a(false, false, true);
            }
        }

        this.X();
    }

    private void X() {
        // CanaryMod: Weather hook
        if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.WEATHER_CHANGE, world, false)) {
            this.x.g(0);
            this.x.b(false);
        }
        // CanaryMod: Thunder hook
        if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.THUNDER_CHANGE, world, false)) {
            this.x.f(0);
            this.x.a(false);
        } // CanaryMod: diff visibility
    }

    public boolean e() {
        if (this.N && !this.I) {
            Iterator iterator = this.h.iterator();

            OEntityPlayer oentityplayer;

            do {
                if (!iterator.hasNext()) {
                    return true;
                }

                oentityplayer = (OEntityPlayer) iterator.next();
            } while (oentityplayer.cg());

            return false;
        } else {
            return false;
        }
    }

    protected void g() {
        super.g();
        int i = 0;
        int j = 0;
        Iterator iterator = this.G.iterator();

        while (iterator.hasNext()) {
            OChunkCoordIntPair ochunkcoordintpair = (OChunkCoordIntPair) iterator.next();
            int k = ochunkcoordintpair.a * 16;
            int l = ochunkcoordintpair.b * 16;

            this.C.a("getChunk");
            OChunk ochunk = this.e(ochunkcoordintpair.a, ochunkcoordintpair.b);

            this.a(k, l, ochunk);
            this.C.c("tickChunk");
            ochunk.k();
            this.C.c("thunder");
            int i1;
            int j1;
            int k1;
            int l1;

            if (this.s.nextInt(100000) == 0 && this.O() && this.N()) {
                this.k = this.k * 3 + 1013904223;
                i1 = this.k >> 2;
                j1 = k + (i1 & 15);
                k1 = l + (i1 >> 8 & 15);
                l1 = this.h(j1, k1);
                if (this.F(j1, l1, k1)) {
                    this.c(new OEntityLightningBolt(this, (double) j1, (double) l1, (double) k1));
                }
            }

            this.C.c("iceandsnow");
            int i2;

            if (this.s.nextInt(16) == 0) {
                this.k = this.k * 3 + 1013904223;
                i1 = this.k >> 2;
                j1 = i1 & 15;
                k1 = i1 >> 8 & 15;
                l1 = this.h(j1 + k, k1 + l);
                if (this.y(j1 + k, l1 - 1, k1 + l)) {
                    this.c(j1 + k, l1 - 1, k1 + l, OBlock.aX.cz);
                }

                if (this.O() && this.z(j1 + k, l1, k1 + l)) {
                    this.c(j1 + k, l1, k1 + l, OBlock.aW.cz);
                }

                if (this.O()) {
                    OBiomeGenBase obiomegenbase = this.a(j1 + k, k1 + l);

                    if (obiomegenbase.d()) {
                        i2 = this.a(j1 + k, l1 - 1, k1 + l);
                        if (i2 != 0) {
                            OBlock.r[i2].g(this, j1 + k, l1 - 1, k1 + l);
                        }
                    }
                }
            }

            this.C.c("tickTiles");
            OExtendedBlockStorage[] aoextendedblockstorage = ochunk.i();

            j1 = aoextendedblockstorage.length;

            for (k1 = 0; k1 < j1; ++k1) {
                OExtendedBlockStorage oextendedblockstorage = aoextendedblockstorage[k1];

                if (oextendedblockstorage != null && oextendedblockstorage.b()) {
                    for (int j2 = 0; j2 < 3; ++j2) {
                        this.k = this.k * 3 + 1013904223;
                        i2 = this.k >> 2;
                        int k2 = i2 & 15;
                        int l2 = i2 >> 8 & 15;
                        int i3 = i2 >> 16 & 15;
                        int j3 = oextendedblockstorage.a(k2, i3, l2);

                        ++j;
                        OBlock oblock = OBlock.r[j3];

                        if (oblock != null && oblock.s()) {
                            ++i;
                            oblock.a(this, k2 + k, i3 + oextendedblockstorage.d(), l2 + l, this.s);
                        }
                    }
                }
            }

            this.C.b();
        }
    }

    public boolean a(int i, int j, int k, int l) {
        ONextTickListEntry onextticklistentry = new ONextTickListEntry(i, j, k, l);

        return this.T.contains(onextticklistentry);
    }

    public void a(int i, int j, int k, int l, int i1) {
        this.a(i, j, k, l, i1, 0);
    }

    public void a(int i, int j, int k, int l, int i1, int j1) {
        ONextTickListEntry onextticklistentry = new ONextTickListEntry(i, j, k, l);
        byte b0 = 0;

        if (this.d && l > 0) {
            if (OBlock.r[l].l()) {
                if (this.e(onextticklistentry.a - b0, onextticklistentry.b - b0, onextticklistentry.c - b0, onextticklistentry.a + b0, onextticklistentry.b + b0, onextticklistentry.c + b0)) {
                    int k1 = this.a(onextticklistentry.a, onextticklistentry.b, onextticklistentry.c);

                    if (k1 == onextticklistentry.d && k1 > 0) {
                        OBlock.r[k1].a(this, onextticklistentry.a, onextticklistentry.b, onextticklistentry.c, this.s);
                    }
                }

                return;
            }

            i1 = 1;
        }

        if (this.e(i - b0, j - b0, k - b0, i + b0, j + b0, k + b0)) {
            if (l > 0) {
                onextticklistentry.a((long) i1 + this.x.f());
                onextticklistentry.a(j1);
            }

            if (!this.L.contains(onextticklistentry)) {
                this.L.add(onextticklistentry);
                this.M.add(onextticklistentry);
            }
        }
    }

    public void b(int i, int j, int k, int l, int i1, int j1) {
        ONextTickListEntry onextticklistentry = new ONextTickListEntry(i, j, k, l);

        onextticklistentry.a(j1);
        if (l > 0) {
            onextticklistentry.a((long) i1 + this.x.f());
        }

        if (!this.L.contains(onextticklistentry)) {
            this.L.add(onextticklistentry);
            this.M.add(onextticklistentry);
        }
    }

    public void h() {
        if (this.h.isEmpty()) {
            if (this.O++ >= 1200) {
                return;
            }
        } else {
            this.i();
        }

        super.h();
    }

    public void i() {
        this.O = 0;
    }

    public boolean a(boolean flag) {
        int i = this.M.size();

        if (i != this.L.size()) {
            throw new IllegalStateException("TickNextTick list out of synch");
        } else {
            if (i > 1000) {
                i = 1000;
            }

            this.C.a("cleaning");

            ONextTickListEntry onextticklistentry;

            for (int j = 0; j < i; ++j) {
                onextticklistentry = (ONextTickListEntry) this.M.first();
                if (!flag && onextticklistentry.e > this.x.f()) {
                    break;
                }

                this.M.remove(onextticklistentry);
                this.L.remove(onextticklistentry);
                this.T.add(onextticklistentry);
            }

            this.C.b();
            this.C.a("ticking");
            Iterator iterator = this.T.iterator();

            while (iterator.hasNext()) {
                onextticklistentry = (ONextTickListEntry) iterator.next();
                iterator.remove();
                byte b0 = 0;

                if (this.e(onextticklistentry.a - b0, onextticklistentry.b - b0, onextticklistentry.c - b0, onextticklistentry.a + b0, onextticklistentry.b + b0, onextticklistentry.c + b0)) {
                    int k = this.a(onextticklistentry.a, onextticklistentry.b, onextticklistentry.c);

                    if (k > 0 && OBlock.b(k, onextticklistentry.d)) {
                        try {
                            OBlock.r[k].a(this, onextticklistentry.a, onextticklistentry.b, onextticklistentry.c, this.s);
                        } catch (Throwable throwable) {
                            OCrashReport ocrashreport = OCrashReport.a(throwable, "Exception while ticking a block");
                            OCrashReportCategory ocrashreportcategory = ocrashreport.a("Block being ticked");

                            int l;

                            try {
                                l = this.h(onextticklistentry.a, onextticklistentry.b, onextticklistentry.c);
                            } catch (Throwable throwable1) {
                                l = -1;
                            }

                            OCrashReportCategory.a(ocrashreportcategory, onextticklistentry.a, onextticklistentry.b, onextticklistentry.c, k, l);
                            throw new OReportedException(ocrashreport);
                        }
                    }
                } else {
                    this.a(onextticklistentry.a, onextticklistentry.b, onextticklistentry.c, onextticklistentry.d, 0);
                }
            }

            this.C.b();
            this.T.clear();
            return !this.M.isEmpty();
        }
    }

    public List a(OChunk ochunk, boolean flag) {
        ArrayList arraylist = null;
        OChunkCoordIntPair ochunkcoordintpair = ochunk.l();
        int i = (ochunkcoordintpair.a << 4) - 2;
        int j = i + 16 + 2;
        int k = (ochunkcoordintpair.b << 4) - 2;
        int l = k + 16 + 2;

        for (int i1 = 0; i1 < 2; ++i1) {
            Iterator iterator;

            if (i1 == 0) {
                iterator = this.M.iterator();
            } else {
                iterator = this.T.iterator();
                if (!this.T.isEmpty()) {
                    System.out.println(this.T.size());
                }
            }

            while (iterator.hasNext()) {
                ONextTickListEntry onextticklistentry = (ONextTickListEntry) iterator.next();

                if (onextticklistentry.a >= i && onextticklistentry.a < j && onextticklistentry.c >= k && onextticklistentry.c < l) {
                    if (flag) {
                        this.L.remove(onextticklistentry);
                        iterator.remove();
                    }

                    if (arraylist == null) {
                        arraylist = new ArrayList();
                    }

                    arraylist.add(onextticklistentry);
                }
            }
        }

        return arraylist;
    }

    public void a(OEntity oentity, boolean flag) {
        if (!this.a.V() && (oentity instanceof OEntityAnimal || oentity instanceof OEntityWaterMob)) {
            oentity.w();
        }

        if (!this.a.W() && oentity instanceof OINpc) {
            oentity.w();
        }

        if (!(oentity.n instanceof OEntityPlayer)) {
            super.a(oentity, flag);
        }
    }

    public void b(OEntity oentity, boolean flag) {
        super.a(oentity, flag);
    }

    protected OIChunkProvider j() {
        OIChunkLoader oichunkloader = this.w.a(this.t);

        this.b = new OChunkProviderServer(this, oichunkloader, this.t.c());
        return this.b;
    }

    public List c(int i, int j, int k, int l, int i1, int j1) {
        ArrayList arraylist = new ArrayList();

        for (int k1 = 0; k1 < this.g.size(); ++k1) {
            OTileEntity otileentity = (OTileEntity) this.g.get(k1);

            if (otileentity.l >= i && otileentity.m >= j && otileentity.n >= k && otileentity.l < l && otileentity.m < i1 && otileentity.n < j1) {
                arraylist.add(otileentity);
            }
        }

        return arraylist;
    }

    public boolean a(OEntityPlayer oentityplayer, int i, int j, int k) {
        return !this.a.a(this, i, j, k, oentityplayer);
    }

    protected void a(OWorldSettings oworldsettings) {
        if (this.U == null) {
            this.U = new OIntHashMap();
        }

        if (this.L == null) {
            this.L = new HashSet();
        }

        if (this.M == null) {
            this.M = new TreeSet();
        }

        this.b(oworldsettings);
        super.a(oworldsettings);
    }

    protected void b(OWorldSettings oworldsettings) {
        // CanaryMod: load preload plugins once!
        if (!loadedpreload) {
            etc.getLoader().loadPreloadPlugins();
            loadedpreload = true;
        }
        // CanaryMod onSpawnpointCreate hook
        Location point = (Location) etc.getLoader().callHook(PluginLoader.Hook.SPAWNPOINT_CREATE, world);

        if (point != null) {
            this.x.a((int) point.x, (int) point.y, (int) point.z);
        } else if (!this.t.e()) {
            this.x.a(0, this.t.i(), 0);
        } else {
            this.y = true;
            OWorldChunkManager oworldchunkmanager = this.t.d;
            List list = oworldchunkmanager.a();
            Random random = new Random(this.F());
            OChunkPosition ochunkposition = oworldchunkmanager.a(0, 0, 256, list, random);
            int i = 0;
            int j = this.t.i();
            int k = 0;

            if (ochunkposition != null) {
                i = ochunkposition.a;
                k = ochunkposition.c;
            } else {
                this.W().b("Unable to find spawn biome");
            }

            int l = 0;

            while (!this.t.a(i, k)) {
                i += random.nextInt(64) - random.nextInt(64);
                k += random.nextInt(64) - random.nextInt(64);
                ++l;
                if (l == 1000) {
                    break;
                }
            }

            this.x.a(i, j, k);
            this.y = false;
            if (oworldsettings.c()) {
                this.k();
            }
        }
    }

    protected void k() {
        OWorldGeneratorBonusChest oworldgeneratorbonuschest = new OWorldGeneratorBonusChest(S, 10);

        for (int i = 0; i < 10; ++i) {
            int j = this.x.c() + this.s.nextInt(6) - this.s.nextInt(6);
            int k = this.x.e() + this.s.nextInt(6) - this.s.nextInt(6);
            int l = this.i(j, k) + 1;

            if (oworldgeneratorbonuschest.a(this, this.s, j, l, k)) {
                break;
            }
        }
    }

    public OChunkCoordinates l() {
        return this.t.h();
    }

    public void a(boolean flag, OIProgressUpdate oiprogressupdate) throws OMinecraftException {
        if (this.v.c()) {
            if (oiprogressupdate != null) {
                oiprogressupdate.a("Saving level");
            }

            this.a();
            if (oiprogressupdate != null) {
                oiprogressupdate.c("Saving chunks");
            }

            this.v.a(flag, oiprogressupdate);
        }
    }

    protected void a() throws OMinecraftException {
        this.E();
        this.w.a(this.x, this.a.ad().q());
        this.z.a();
    }

    protected void a(OEntity oentity) {
        super.a(oentity);
        this.U.a(oentity.k, oentity);
        OEntity[] aoentity = oentity.an();

        if (aoentity != null) {
            for (int i = 0; i < aoentity.length; ++i) {
                this.U.a(aoentity[i].k, aoentity[i]);
            }
        }
    }

    protected void b(OEntity oentity) {
        super.b(oentity);
        this.U.d(oentity.k);
        OEntity[] aoentity = oentity.an();

        if (aoentity != null) {
            for (int i = 0; i < aoentity.length; ++i) {
                this.U.d(aoentity[i].k);
            }
        }
    }

    public OEntity a(int i) {
        return (OEntity) this.U.a(i);
    }

    public boolean c(OEntity oentity) {
        if (super.c(oentity)) {
            this.a.ad().a(oentity.t, oentity.u, oentity.v, 512.0D, this.t.h, new OPacket71Weather(oentity), this.name); // CanaryMod: multiworld
            return true;
        } else {
            return false;
        }
    }

    public void a(OEntity oentity, byte b0) {
        OPacket38EntityStatus opacket38entitystatus = new OPacket38EntityStatus(oentity.k, b0);

        this.p().b(oentity, opacket38entitystatus);
    }

    public OExplosion a(OEntity oentity, double d0, double d1, double d2, float f, boolean flag, boolean flag1) {
        OExplosion oexplosion = new OExplosion(this, oentity, d0, d1, d2, f);

        oexplosion.a = flag;
        oexplosion.b = flag1;
        oexplosion.a();
        oexplosion.a(false);
        if (!flag1) {
            oexplosion.h.clear();
        }

        Iterator iterator = this.h.iterator();

        while (iterator.hasNext()) {
            OEntityPlayer oentityplayer = (OEntityPlayer) iterator.next();

            if (oentityplayer.e(d0, d1, d2) < 4096.0D) {
                ((OEntityPlayerMP) oentityplayer).a.b(new OPacket60Explosion(d0, d1, d2, f, oexplosion.h, (OVec3) oexplosion.b().get(oentityplayer)));
            }
        }

        return oexplosion;
    }

    public void d(int i, int j, int k, int l, int i1, int j1) {
        OBlockEventData oblockeventdata = new OBlockEventData(i, j, k, l, i1, j1);
        Iterator iterator = this.Q[this.R].iterator();

        OBlockEventData oblockeventdata1;

        do {
            if (!iterator.hasNext()) {
                this.Q[this.R].add(oblockeventdata);
                return;
            }

            oblockeventdata1 = (OBlockEventData) iterator.next();
        } while (!oblockeventdata1.equals(oblockeventdata));

    }

    private void Y() {
        while (!this.Q[this.R].isEmpty()) {
            int i = this.R;

            this.R ^= 1;
            Iterator iterator = this.Q[i].iterator();

            while (iterator.hasNext()) {
                OBlockEventData oblockeventdata = (OBlockEventData) iterator.next();

                if (this.a(oblockeventdata)) {
                    this.a.ad().a((double) oblockeventdata.a(), (double) oblockeventdata.b(), (double) oblockeventdata.c(), 64.0D, this.t.h, new OPacket54PlayNoteBlock(oblockeventdata.a(), oblockeventdata.b(), oblockeventdata.c(), oblockeventdata.f(), oblockeventdata.d(), oblockeventdata.e()), this.name); // CanaryMod: multiworld
                }
            }

            this.Q[i].clear();
        }
    }

    private boolean a(OBlockEventData oblockeventdata) {
        int i = this.a(oblockeventdata.a(), oblockeventdata.b(), oblockeventdata.c());

        return i == oblockeventdata.f() ? OBlock.r[i].b(this, oblockeventdata.a(), oblockeventdata.b(), oblockeventdata.c(), oblockeventdata.d(), oblockeventdata.e()) : false;
    }

    public void m() {
        this.w.a();
    }

    protected void n() {
        boolean flag = this.O();

        super.n();
        if (flag != this.O()) {
            if (flag) {
                this.a.ad().a((OPacket) (new OPacket70GameEvent(2, 0)));
            } else {
                this.a.ad().a((OPacket) (new OPacket70GameEvent(1, 0)));
            }
        }
    }

    public OMinecraftServer o() {
        return this.a;
    }

    public OEntityTracker p() {
        return this.J;
    }

    public OPlayerManager r() {
        return this.K;
    }

    public OTeleporter s() {
        return this.P;
    }

    /**
     * Get this worlds entity tracker to track and untrack players
     * @return
     */
    public EntityTracker getEntityTracker() {
        return this.p().getCanaryEntityTracker();
    }
}
