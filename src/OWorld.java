import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.Callable;

public abstract class OWorld implements OIBlockAccess {

    public boolean d = false;
    public List e = new ArrayList();
    protected List f = new ArrayList();
    public List g = new ArrayList();
    private List a = new ArrayList();
    private List b = new ArrayList();
    public List h = new ArrayList();
    public List i = new ArrayList();
    private long c = 16777215L;
    public int j = 0;
    protected int k = (new Random()).nextInt();
    protected final int l = 1013904223;
    protected float m;
    protected float n;
    protected float o;
    protected float p;
    public int q = 0;
    public int r;
    public Random s = new Random();
    public final OWorldProvider t;
    protected List u = new ArrayList();
    protected OIChunkProvider v;
    protected final OISaveHandler w;
    protected OWorldInfo x;
    public boolean y;
    public OMapStorage z;
    public final OVillageCollection A;
    protected final OVillageSiege B = new OVillageSiege(this);
    public final OProfiler C;
    private final OVec3Pool J = new OVec3Pool(300, 2000);
    private final Calendar K = Calendar.getInstance();
    protected OScoreboard D = new OScoreboard();
    private final OILogAgent L;
    private ArrayList M = new ArrayList();
    private boolean N;
    protected boolean E = true;
    protected boolean F = true;
    protected Set G = new HashSet();
    private int O;
    int[] H;
    public boolean I;

    // CanaryMod
    public final World world = new World((OWorldServer) this);
    boolean loadedpreload = false;
    public final String name;

    public OBiomeGenBase a(int i, int j) {
        if (this.f(i, 0, j)) {
            OChunk ochunk = this.d(i, j);

            if (ochunk != null) {
                return ochunk.a(i & 15, j & 15, this.t.d);
            }
        }

        return this.t.d.a(i, j);
    }

    public OWorldChunkManager t() {
        return this.t.d;
    }

    public OWorld(OISaveHandler oisavehandler, String s, OWorldSettings oworldsettings, OWorldProvider oworldprovider, OProfiler oprofiler, OILogAgent oilogagent) {
        this.O = this.s.nextInt(12000);
        this.H = new int['\u8000'];
        this.I = false;
        this.w = oisavehandler;
        this.C = oprofiler;
        this.z = new OMapStorage(oisavehandler);
        this.L = oilogagent;
        this.x = oisavehandler.d();
        if (oworldprovider != null) {
            this.t = oworldprovider;
        } else if (this.x != null && this.x.j() != 0) {
            this.t = OWorldProvider.a(this.x.j());
        } else {
            this.t = OWorldProvider.a(0);
        }

        if (this.x == null) {
            this.x = new OWorldInfo(oworldsettings, s);
        } else {
            this.x.a(s);
        }

        this.t.a(this);
        this.v = this.j();
        if (!this.x.w()) {
            try {
                this.a(oworldsettings);
            } catch (Throwable throwable) {
                OCrashReport ocrashreport = OCrashReport.a(throwable, "Exception initializing level");

                try {
                    this.a(ocrashreport);
                } catch (Throwable throwable1) {
                    ;
                }

                throw new OReportedException(ocrashreport);
            }

            this.x.d(true);
        }

        OVillageCollection ovillagecollection = (OVillageCollection) this.z.a(OVillageCollection.class, "villages");

        if (ovillagecollection == null) {
            this.A = new OVillageCollection(this);
            this.z.a("villages", (OWorldSavedData) this.A);
        } else {
            this.A = ovillagecollection;
            this.A.a(this);
        }

        this.y();
        this.a();

        this.name = s; // CanaryMod: store world name in an accessible place.
    }

    protected abstract OIChunkProvider j();

    protected void a(OWorldSettings oworldsettings) {
        this.x.d(true);
    }

    public int b(int i, int j) {
        int k;

        for (k = 63; !this.c(i, k + 1, j); ++k) {
            ;
        }

        return this.a(i, k, j);
    }

    public int a(int i, int j, int k) {
        if (i >= -30000000 && k >= -30000000 && i < 30000000 && k < 30000000) {
            if (j < 0) {
                return 0;
            } else if (j >= 256) {
                return 0;
            } else {
                OChunk ochunk = null;

                try {
                    ochunk = this.e(i >> 4, k >> 4);
                    return ochunk.a(i & 15, j, k & 15);
                } catch (Throwable throwable) {
                    OCrashReport ocrashreport = OCrashReport.a(throwable, "Exception getting block type in world");
                    OCrashReportCategory ocrashreportcategory = ocrashreport.a("Requested block coordinates");

                    ocrashreportcategory.a("Found chunk", Boolean.valueOf(ochunk == null));
                    ocrashreportcategory.a("Location", OCrashReportCategory.a(i, j, k));
                    throw new OReportedException(ocrashreport);
                }
            }
        } else {
            return 0;
        }
    }

    public boolean c(int i, int j, int k) {
        return this.a(i, j, k) == 0;
    }

    public boolean d(int i, int j, int k) {
        int l = this.a(i, j, k);

        return OBlock.r[l] != null && OBlock.r[l].t();
    }

    public int e(int i, int j, int k) {
        int l = this.a(i, j, k);

        return OBlock.r[l] != null ? OBlock.r[l].d() : -1;
    }

    public boolean f(int i, int j, int k) {
        return j >= 0 && j < 256 ? this.c(i >> 4, k >> 4) : false;
    }

    public boolean b(int i, int j, int k, int l) {
        return this.e(i - l, j - l, k - l, i + l, j + l, k + l);
    }

    public boolean e(int i, int j, int k, int l, int i1, int j1) {
        if (i1 >= 0 && j < 256) {
            i >>= 4;
            k >>= 4;
            l >>= 4;
            j1 >>= 4;

            for (int k1 = i; k1 <= l; ++k1) {
                for (int l1 = k; l1 <= j1; ++l1) {
                    if (!this.c(k1, l1)) {
                        return false;
                    }
                }
            }

            return true;
        } else {
            return false;
        }
    }

    protected boolean c(int i, int j) {
        return (this.v != null ? this.v.a(i, j) : false); // CanaryMod: fix NPE
    }

    public OChunk d(int i, int j) {
        return this.e(i >> 4, j >> 4);
    }

    public OChunk e(int i, int j) {
        return this.v.d(i, j);
    }

    public boolean f(int i, int j, int k, int l, int i1, int j1) {
        if (i >= -30000000 && k >= -30000000 && i < 30000000 && k < 30000000) {
            if (j < 0) {
                return false;
            } else if (j >= 256) {
                return false;
            } else {
                OChunk ochunk = this.e(i >> 4, k >> 4);
                int k1 = 0;

                if ((j1 & 1) != 0) {
                    k1 = ochunk.a(i & 15, j, k & 15);
                }

                boolean flag = false;
                // CanaryMod: Get Block Info
                Block block = this.world.getBlockAt(i, j, k);
                // CanaryMod ignore if new block is air
                if (l == 0 || !(Boolean) OEntity.manager.callHook(PluginLoader.Hook.BLOCK_UPDATE, block, l)) {
                    flag = ochunk.a(i & 15, j, k & 15, l, i1);
                }

                this.C.a("checkLight");
                this.A(i, j, k);
                this.C.b();
                if (flag) {
                    if ((j1 & 2) != 0 && (!this.I || (j1 & 4) == 0)) {
                        this.j(i, j, k);
                    }

                    if (!this.I && (j1 & 1) != 0) {
                        this.d(i, j, k, k1);
                        OBlock oblock = OBlock.r[l];

                        if (oblock != null && oblock.q_()) {
                            this.m(i, j, k, l);
                        }
                    }
                }

                return flag;
            }
        } else {
            return false;
        }
    }

    public OMaterial g(int i, int j, int k) {
        int l = this.a(i, j, k);

        return l == 0 ? OMaterial.a : OBlock.r[l].cO;
    }

    public int h(int i, int j, int k) {
        if (i >= -30000000 && k >= -30000000 && i < 30000000 && k < 30000000) {
            if (j < 0) {
                return 0;
            } else if (j >= 256) {
                return 0;
            } else {
                OChunk ochunk = this.e(i >> 4, k >> 4);

                i &= 15;
                k &= 15;
                return ochunk.c(i, j, k);
            }
        } else {
            return 0;
        }
    }

    public boolean b(int i, int j, int k, int l, int i1) {
        if (i >= -30000000 && k >= -30000000 && i < 30000000 && k < 30000000) {
            if (j < 0) {
                return false;
            } else if (j >= 256) {
                return false;
            } else {
                OChunk ochunk = this.e(i >> 4, k >> 4);
                int j1 = i & 15;
                int k1 = k & 15;
                boolean flag = ochunk.b(j1, j, k1, l);

                if (flag) {
                    int l1 = ochunk.a(j1, j, k1);

                    if ((i1 & 2) != 0 && (!this.I || (i1 & 4) == 0)) {
                        this.j(i, j, k);
                    }

                    if (!this.I && (i1 & 1) != 0) {
                        this.d(i, j, k, l1);
                        OBlock oblock = OBlock.r[l1];

                        if (oblock != null && oblock.q_()) {
                            this.m(i, j, k, l1);
                        }
                    }
                }

                return flag;
            }
        } else {
            return false;
        }
    }

    public boolean i(int i, int j, int k) {
        return this.f(i, j, k, 0, 0, 3);
    }

    public boolean a(int i, int j, int k, boolean flag) {
        int l = this.a(i, j, k);

        if (l > 0) {
            int i1 = this.h(i, j, k);

            this.e(2001, i, j, k, l + (i1 << 12));
            if (flag) {
                OBlock.r[l].c(this, i, j, k, i1, 0);
            }

            return this.f(i, j, k, 0, 0, 3);
        } else {
            return false;
        }
    }

    public boolean c(int i, int j, int k, int l) {
        return this.f(i, j, k, l, 0, 3);
    }

    public void j(int i, int j, int k) {
        for (int l = 0; l < this.u.size(); ++l) {
            ((OIWorldAccess) this.u.get(l)).a(i, j, k);
        }
    }

    public void d(int i, int j, int k, int l) {
        this.f(i, j, k, l);
    }

    public void e(int i, int j, int k, int l) {
        int i1;

        if (k > l) {
            i1 = l;
            l = k;
            k = i1;
        }

        if (!this.t.f) {
            for (i1 = k; i1 <= l; ++i1) {
                this.c(OEnumSkyBlock.a, i, i1, j);
            }
        }

        this.g(i, k, j, i, l, j);
    }

    public void g(int i, int j, int k, int l, int i1, int j1) {
        for (int k1 = 0; k1 < this.u.size(); ++k1) {
            ((OIWorldAccess) this.u.get(k1)).a(i, j, k, l, i1, j1);
        }
    }

    public void f(int i, int j, int k, int l) {
        this.g(i - 1, j, k, l);
        this.g(i + 1, j, k, l);
        this.g(i, j - 1, k, l);
        this.g(i, j + 1, k, l);
        this.g(i, j, k - 1, l);
        this.g(i, j, k + 1, l);
    }

    public void c(int i, int j, int k, int l, int i1) {
        if (i1 != 4) {
            this.g(i - 1, j, k, l);
        }

        if (i1 != 5) {
            this.g(i + 1, j, k, l);
        }

        if (i1 != 0) {
            this.g(i, j - 1, k, l);
        }

        if (i1 != 1) {
            this.g(i, j + 1, k, l);
        }

        if (i1 != 2) {
            this.g(i, j, k - 1, l);
        }

        if (i1 != 3) {
            this.g(i, j, k + 1, l);
        }
    }

    public void g(int i, int j, int k, int l) {
        if (!this.I) {
            int i1 = this.a(i, j, k);
            OBlock oblock = OBlock.r[i1];

            if (oblock != null) {
                try {
                    oblock.a(this, i, j, k, l);
                } catch (Throwable throwable) {
                    OCrashReport ocrashreport = OCrashReport.a(throwable, "Exception while updating neighbours");
                    OCrashReportCategory ocrashreportcategory = ocrashreport.a("Block being updated");

                    int j1;

                    try {
                        j1 = this.h(i, j, k);
                    } catch (Throwable throwable1) {
                        j1 = -1;
                    }

                    ocrashreportcategory.a("Source block type", (Callable) (new OCallableLvl1(this, l)));
                    OCrashReportCategory.a(ocrashreportcategory, i, j, k, i1, j1);
                    throw new OReportedException(ocrashreport);
                }
            }
        }
    }

    public boolean a(int i, int j, int k, int l) {
        return false;
    }

    public boolean l(int i, int j, int k) {
        return this.e(i >> 4, k >> 4).d(i & 15, j, k & 15);
    }

    public int m(int i, int j, int k) {
        if (j < 0) {
            return 0;
        } else {
            if (j >= 256) {
                j = 255;
            }

            return this.e(i >> 4, k >> 4).c(i & 15, j, k & 15, 0);
        }
    }

    public int n(int i, int j, int k) {
        return this.b(i, j, k, true);
    }

    public int b(int i, int j, int k, boolean flag) {
        if (i >= -30000000 && k >= -30000000 && i < 30000000 && k < 30000000) {
            if (flag) {
                int l = this.a(i, j, k);

                if (OBlock.w[l]) {
                    int i1 = this.b(i, j + 1, k, false);
                    int j1 = this.b(i + 1, j, k, false);
                    int k1 = this.b(i - 1, j, k, false);
                    int l1 = this.b(i, j, k + 1, false);
                    int i2 = this.b(i, j, k - 1, false);

                    if (j1 > i1) {
                        i1 = j1;
                    }

                    if (k1 > i1) {
                        i1 = k1;
                    }

                    if (l1 > i1) {
                        i1 = l1;
                    }

                    if (i2 > i1) {
                        i1 = i2;
                    }

                    return i1;
                }
            }

            if (j < 0) {
                return 0;
            } else {
                if (j >= 256) {
                    j = 255;
                }

                OChunk ochunk = this.e(i >> 4, k >> 4);

                i &= 15;
                k &= 15;
                return ochunk.c(i, j, k, this.j);
            }
        } else {
            return 15;
        }
    }

    public int f(int i, int j) {
        if (i >= -30000000 && j >= -30000000 && i < 30000000 && j < 30000000) {
            if (!this.c(i >> 4, j >> 4)) {
                return 0;
            } else {
                OChunk ochunk = this.e(i >> 4, j >> 4);

                return ochunk.b(i & 15, j & 15);
            }
        } else {
            return 0;
        }
    }

    public int g(int i, int j) {
        if (i >= -30000000 && j >= -30000000 && i < 30000000 && j < 30000000) {
            if (!this.c(i >> 4, j >> 4)) {
                return 0;
            } else {
                OChunk ochunk = this.e(i >> 4, j >> 4);

                return ochunk.p;
            }
        } else {
            return 0;
        }
    }

    public int b(OEnumSkyBlock oenumskyblock, int i, int j, int k) {
        if (j < 0) {
            j = 0;
        }

        if (j >= 256) {
            j = 255;
        }

        if (i >= -30000000 && k >= -30000000 && i < 30000000 && k < 30000000) {
            int l = i >> 4;
            int i1 = k >> 4;

            if (!this.c(l, i1)) {
                return oenumskyblock.c;
            } else {
                OChunk ochunk = this.e(l, i1);

                return ochunk.a(oenumskyblock, i & 15, j, k & 15);
            }
        } else {
            return oenumskyblock.c;
        }
    }

    public void b(OEnumSkyBlock oenumskyblock, int i, int j, int k, int l) {
        if (i >= -30000000 && k >= -30000000 && i < 30000000 && k < 30000000) {
            if (j >= 0) {
                if (j < 256) {
                    if (this.c(i >> 4, k >> 4)) {
                        OChunk ochunk = this.e(i >> 4, k >> 4);

                        ochunk.a(oenumskyblock, i & 15, j, k & 15, l);
                        etc.getLoader().callHook(PluginLoader.Hook.LIGHT_CHANGE, i, j, k, l); //CanaryMod: fire light change hook

                        for (int i1 = 0; i1 < this.u.size(); ++i1) {
                            ((OIWorldAccess) this.u.get(i1)).b(i, j, k);
                        }
                    }
                }
            }
        }
    }

    public void p(int i, int j, int k) {
        for (int l = 0; l < this.u.size(); ++l) {
            ((OIWorldAccess) this.u.get(l)).b(i, j, k);
        }
    }

    public float q(int i, int j, int k) {
        return this.t.g[this.n(i, j, k)];
    }

    public boolean u() {
        return this.j < 4;
    }

    public OMovingObjectPosition a(OVec3 ovec3, OVec3 ovec31) {
        return this.a(ovec3, ovec31, false, false);
    }

    public OMovingObjectPosition a(OVec3 ovec3, OVec3 ovec31, boolean flag) {
        return this.a(ovec3, ovec31, flag, false);
    }

    public OMovingObjectPosition a(OVec3 ovec3, OVec3 ovec31, boolean flag, boolean flag1) {
        if (!Double.isNaN(ovec3.c) && !Double.isNaN(ovec3.d) && !Double.isNaN(ovec3.e)) {
            if (!Double.isNaN(ovec31.c) && !Double.isNaN(ovec31.d) && !Double.isNaN(ovec31.e)) {
                int i = OMathHelper.c(ovec31.c);
                int j = OMathHelper.c(ovec31.d);
                int k = OMathHelper.c(ovec31.e);
                int l = OMathHelper.c(ovec3.c);
                int i1 = OMathHelper.c(ovec3.d);
                int j1 = OMathHelper.c(ovec3.e);
                int k1 = this.a(l, i1, j1);
                int l1 = this.h(l, i1, j1);
                OBlock oblock = OBlock.r[k1];

                if ((!flag1 || oblock == null || oblock.b(this, l, i1, j1) != null) && k1 > 0 && oblock.a(l1, flag)) {
                    OMovingObjectPosition omovingobjectposition = oblock.a(this, l, i1, j1, ovec3, ovec31);

                    if (omovingobjectposition != null) {
                        return omovingobjectposition;
                    }
                }

                k1 = 200;

                while (k1-- >= 0) {
                    if (Double.isNaN(ovec3.c) || Double.isNaN(ovec3.d) || Double.isNaN(ovec3.e)) {
                        return null;
                    }

                    if (l == i && i1 == j && j1 == k) {
                        return null;
                    }

                    boolean flag2 = true;
                    boolean flag3 = true;
                    boolean flag4 = true;
                    double d0 = 999.0D;
                    double d1 = 999.0D;
                    double d2 = 999.0D;

                    if (i > l) {
                        d0 = (double) l + 1.0D;
                    } else if (i < l) {
                        d0 = (double) l + 0.0D;
                    } else {
                        flag2 = false;
                    }

                    if (j > i1) {
                        d1 = (double) i1 + 1.0D;
                    } else if (j < i1) {
                        d1 = (double) i1 + 0.0D;
                    } else {
                        flag3 = false;
                    }

                    if (k > j1) {
                        d2 = (double) j1 + 1.0D;
                    } else if (k < j1) {
                        d2 = (double) j1 + 0.0D;
                    } else {
                        flag4 = false;
                    }

                    double d3 = 999.0D;
                    double d4 = 999.0D;
                    double d5 = 999.0D;
                    double d6 = ovec31.c - ovec3.c;
                    double d7 = ovec31.d - ovec3.d;
                    double d8 = ovec31.e - ovec3.e;

                    if (flag2) {
                        d3 = (d0 - ovec3.c) / d6;
                    }

                    if (flag3) {
                        d4 = (d1 - ovec3.d) / d7;
                    }

                    if (flag4) {
                        d5 = (d2 - ovec3.e) / d8;
                    }

                    boolean flag5 = false;
                    byte b0;

                    if (d3 < d4 && d3 < d5) {
                        if (i > l) {
                            b0 = 4;
                        } else {
                            b0 = 5;
                        }

                        ovec3.c = d0;
                        ovec3.d += d7 * d3;
                        ovec3.e += d8 * d3;
                    } else if (d4 < d5) {
                        if (j > i1) {
                            b0 = 0;
                        } else {
                            b0 = 1;
                        }

                        ovec3.c += d6 * d4;
                        ovec3.d = d1;
                        ovec3.e += d8 * d4;
                    } else {
                        if (k > j1) {
                            b0 = 2;
                        } else {
                            b0 = 3;
                        }

                        ovec3.c += d6 * d5;
                        ovec3.d += d7 * d5;
                        ovec3.e = d2;
                    }

                    OVec3 ovec32 = this.T().a(ovec3.c, ovec3.d, ovec3.e);

                    l = (int) (ovec32.c = (double) OMathHelper.c(ovec3.c));
                    if (b0 == 5) {
                        --l;
                        ++ovec32.c;
                    }

                    i1 = (int) (ovec32.d = (double) OMathHelper.c(ovec3.d));
                    if (b0 == 1) {
                        --i1;
                        ++ovec32.d;
                    }

                    j1 = (int) (ovec32.e = (double) OMathHelper.c(ovec3.e));
                    if (b0 == 3) {
                        --j1;
                        ++ovec32.e;
                    }

                    int i2 = this.a(l, i1, j1);
                    int j2 = this.h(l, i1, j1);
                    OBlock oblock1 = OBlock.r[i2];

                    if ((!flag1 || oblock1 == null || oblock1.b(this, l, i1, j1) != null) && i2 > 0 && oblock1.a(j2, flag)) {
                        OMovingObjectPosition omovingobjectposition1 = oblock1.a(this, l, i1, j1, ovec3, ovec31);

                        if (omovingobjectposition1 != null) {
                            return omovingobjectposition1;
                        }
                    }
                }

                return null;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public void a(OEntity oentity, String s, float f, float f1) {
        if (oentity != null && s != null) {
            for (int i = 0; i < this.u.size(); ++i) {
                ((OIWorldAccess) this.u.get(i)).a(s, oentity.u, oentity.v - (double) oentity.N, oentity.w, f, f1);
            }
        }
    }

    public void a(OEntityPlayer oentityplayer, String s, float f, float f1) {
        if (oentityplayer != null && s != null) {
            for (int i = 0; i < this.u.size(); ++i) {
                ((OIWorldAccess) this.u.get(i)).a(oentityplayer, s, oentityplayer.u, oentityplayer.v - (double) oentityplayer.N, oentityplayer.w, f, f1);
            }
        }
    }

    public void a(double d0, double d1, double d2, String s, float f, float f1) {
        if (s != null) {
            for (int i = 0; i < this.u.size(); ++i) {
                ((OIWorldAccess) this.u.get(i)).a(s, d0, d1, d2, f, f1);
            }
        }
    }

    public void a(double d0, double d1, double d2, String s, float f, float f1, boolean flag) {}

    public void a(String s, int i, int j, int k) {
        for (int l = 0; l < this.u.size(); ++l) {
            ((OIWorldAccess) this.u.get(l)).a(s, i, j, k);
        }
    }

    public void a(String s, double d0, double d1, double d2, double d3, double d4, double d5) {
        for (int i = 0; i < this.u.size(); ++i) {
            ((OIWorldAccess) this.u.get(i)).a(s, d0, d1, d2, d3, d4, d5);
        }
    }

    public boolean c(OEntity oentity) {
        this.i.add(oentity);
        return true;
    }

    public boolean d(OEntity oentity) {
        // CanaryMod: mob spawn hook
        if (oentity instanceof OEntityLiving && !(oentity instanceof OEntityPlayer)) {
            if ((etc.getInstance().getMobSpawnRate() < 100 && etc.getInstance().getMobSpawnRate() > 0 && etc.getInstance().getMobSpawnRate() > this.s.nextInt(100)) || etc.getInstance().getMobSpawnRate() <= 0 || (Boolean) (etc.getLoader().callHook(PluginLoader.Hook.MOB_SPAWN, new Mob((OEntityLiving) oentity)))) {
                return false;
            }
        }

        int i = OMathHelper.c(oentity.u / 16.0D);
        int j = OMathHelper.c(oentity.w / 16.0D);
        boolean flag = oentity.p;

        if (oentity instanceof OEntityPlayer) {
            flag = true;
        }

        if (!flag && !this.c(i, j)) {
            return false;
        } else {
            if (oentity instanceof OEntityPlayer) {
                OEntityPlayer oentityplayer = (OEntityPlayer) oentity;

                this.h.add(oentityplayer);
                this.c();
            }

            this.e(i, j).a(oentity);
            this.e.add(oentity);
            this.a(oentity);
            return true;
        }
    }

    protected void a(OEntity oentity) {
        for (int i = 0; i < this.u.size(); ++i) {
            ((OIWorldAccess) this.u.get(i)).a(oentity);
        }
    }

    protected void b(OEntity oentity) {
        for (int i = 0; i < this.u.size(); ++i) {
            ((OIWorldAccess) this.u.get(i)).b(oentity);
        }
    }

    public void e(OEntity oentity) {
        if (oentity.n != null) {
            oentity.n.a((OEntity) null);
        }

        if (oentity.o != null) {
            oentity.a((OEntity) null);
        }

        oentity.w();
        if (oentity instanceof OEntityPlayer) {
            this.h.remove(oentity);
            this.c();
        }
    }

    public void f(OEntity oentity) {
        oentity.w();
        if (oentity instanceof OEntityPlayer) {
            this.h.remove(oentity);
            this.c();
        }

        int i = oentity.aj;
        int j = oentity.al;

        if (oentity.ai && this.c(i, j)) {
            this.e(i, j).b(oentity);
        }

        this.e.remove(oentity);
        this.b(oentity);
    }

    public void a(OIWorldAccess oiworldaccess) {
        this.u.add(oiworldaccess);
    }

    public List a(OEntity oentity, OAxisAlignedBB oaxisalignedbb) {
        this.M.clear();
        int i = OMathHelper.c(oaxisalignedbb.a);
        int j = OMathHelper.c(oaxisalignedbb.d + 1.0D);
        int k = OMathHelper.c(oaxisalignedbb.b);
        int l = OMathHelper.c(oaxisalignedbb.e + 1.0D);
        int i1 = OMathHelper.c(oaxisalignedbb.c);
        int j1 = OMathHelper.c(oaxisalignedbb.f + 1.0D);

        for (int k1 = i; k1 < j; ++k1) {
            for (int l1 = i1; l1 < j1; ++l1) {
                if (this.f(k1, 64, l1)) {
                    for (int i2 = k - 1; i2 < l; ++i2) {
                        OBlock oblock = OBlock.r[this.a(k1, i2, l1)];

                        if (oblock != null) {
                            oblock.a(this, k1, i2, l1, oaxisalignedbb, this.M, oentity);
                        }
                    }
                }
            }
        }

        // CanaryMod: Implemented fix via M4411K4 VEHICLE_COLLISION hook
        BaseVehicle vehicle = null;

        if (oentity instanceof OEntityMinecart) {
            vehicle = ((OEntityMinecart) oentity).cart;
        } else if (oentity instanceof OEntityBoat) {
            vehicle = ((OEntityBoat) oentity).boat;
        }

        double d0 = 0.25D;
        List list = this.b(oentity, oaxisalignedbb.b(d0, d0, d0));

        for (int j2 = 0; j2 < list.size(); ++j2) {
            OEntity oentity1 = (OEntity) list.get(j2); // CanaryMod: split these two lines
            OAxisAlignedBB oaxisalignedbb1 = oentity1.D();

            if (oaxisalignedbb1 != null && oaxisalignedbb1.a(oaxisalignedbb)) {
                // CanaryMod start: this collided with a boat
                if (vehicle != null && (Boolean) etc.getLoader().callHook(PluginLoader.Hook.VEHICLE_COLLISION, vehicle, oentity1.entity)) {
                    continue;
                } // CanaryMod end
                this.M.add(oaxisalignedbb1);
            }

            oaxisalignedbb1 = oentity.g((OEntity) list.get(j2));
            if (oaxisalignedbb1 != null && oaxisalignedbb1.a(oaxisalignedbb)) {
                // CanaryMod start: this collided with entity
                if (vehicle != null && (Boolean) etc.getLoader().callHook(PluginLoader.Hook.VEHICLE_COLLISION, vehicle, oentity1.entity)) {
                    continue;
                } // CanaryMod end
                this.M.add(oaxisalignedbb1);
            }
        }

        return this.M;
    }

    public List a(OAxisAlignedBB oaxisalignedbb) {
        this.M.clear();
        int i = OMathHelper.c(oaxisalignedbb.a);
        int j = OMathHelper.c(oaxisalignedbb.d + 1.0D);
        int k = OMathHelper.c(oaxisalignedbb.b);
        int l = OMathHelper.c(oaxisalignedbb.e + 1.0D);
        int i1 = OMathHelper.c(oaxisalignedbb.c);
        int j1 = OMathHelper.c(oaxisalignedbb.f + 1.0D);

        for (int k1 = i; k1 < j; ++k1) {
            for (int l1 = i1; l1 < j1; ++l1) {
                if (this.f(k1, 64, l1)) {
                    for (int i2 = k - 1; i2 < l; ++i2) {
                        OBlock oblock = OBlock.r[this.a(k1, i2, l1)];

                        if (oblock != null) {
                            oblock.a(this, k1, i2, l1, oaxisalignedbb, this.M, (OEntity) null);
                        }
                    }
                }
            }
        }

        return this.M;
    }

    public int a(float f) {
        float f1 = this.c(f);
        float f2 = 1.0F - (OMathHelper.b(f1 * 3.1415927F * 2.0F) * 2.0F + 0.5F);

        if (f2 < 0.0F) {
            f2 = 0.0F;
        }

        if (f2 > 1.0F) {
            f2 = 1.0F;
        }

        f2 = 1.0F - f2;
        f2 = (float) ((double) f2 * (1.0D - (double) (this.i(f) * 5.0F) / 16.0D));
        f2 = (float) ((double) f2 * (1.0D - (double) (this.h(f) * 5.0F) / 16.0D));
        f2 = 1.0F - f2;
        return (int) (f2 * 11.0F);
    }

    public float c(float f) {
        return this.t.a(this.x.g(), f);
    }

    public int v() {
        return this.t.a(this.x.g());
    }

    public float d(float f) {
        float f1 = this.c(f);

        return f1 * 3.1415927F * 2.0F;
    }

    public int h(int i, int j) {
        return this.d(i, j).d(i & 15, j & 15);
    }

    public int i(int i, int j) {
        OChunk ochunk = this.d(i, j);
        int k = ochunk.h() + 15;

        i &= 15;

        for (j &= 15; k > 0; --k) {
            int l = ochunk.a(i, k, j);

            if (l != 0 && OBlock.r[l].cO.c() && OBlock.r[l].cO != OMaterial.j) {
                return k + 1;
            }
        }

        return -1;
    }

    public void a(int i, int j, int k, int l, int i1) {}

    public void a(int i, int j, int k, int l, int i1, int j1) {}

    public void b(int i, int j, int k, int l, int i1, int j1) {}

    public void h() {
        this.C.a("entities");
        this.C.a("global");

        int i;
        OEntity oentity;
        OCrashReport ocrashreport;
        OCrashReportCategory ocrashreportcategory;

        for (i = 0; i < this.i.size(); ++i) {
            oentity = (OEntity) this.i.get(i);

            try {
                ++oentity.ac;
                oentity.l_();
            } catch (Throwable throwable) {
                ocrashreport = OCrashReport.a(throwable, "Ticking entity");
                ocrashreportcategory = ocrashreport.a("Entity being ticked");
                if (oentity == null) {
                    ocrashreportcategory.a("Entity", "~~NULL~~");
                } else {
                    oentity.a(ocrashreportcategory);
                }

                throw new OReportedException(ocrashreport);
            }

            if (oentity.M) {
                this.i.remove(i--);
            }
        }

        this.C.c("remove");
        this.e.removeAll(this.f);

        int j;
        int k;

        for (i = 0; i < this.f.size(); ++i) {
            oentity = (OEntity) this.f.get(i);
            j = oentity.aj;
            k = oentity.al;
            if (oentity.ai && this.c(j, k)) {
                this.e(j, k).b(oentity);
            }
        }

        for (i = 0; i < this.f.size(); ++i) {
            this.b((OEntity) this.f.get(i));
        }

        this.f.clear();
        this.C.c("regular");

        for (i = 0; i < this.e.size(); ++i) {
            oentity = (OEntity) this.e.get(i);
            if (oentity.o != null) {
                if (!oentity.o.M && oentity.o.n == oentity) {
                    continue;
                }

                oentity.o.n = null;
                oentity.o = null;
            }

            this.C.a("tick");
            if (!oentity.M) {
                try {
                    this.g(oentity);
                } catch (Throwable throwable1) {
                    ocrashreport = OCrashReport.a(throwable1, "Ticking entity");
                    ocrashreportcategory = ocrashreport.a("Entity being ticked");
                    oentity.a(ocrashreportcategory);
                    throw new OReportedException(ocrashreport);
                }
            }

            this.C.b();
            this.C.a("remove");
            if (oentity.M) {
                j = oentity.aj;
                k = oentity.al;
                if (oentity.ai && this.c(j, k)) {
                    this.e(j, k).b(oentity);
                }

                this.e.remove(i--);
                this.b(oentity);
            }

            this.C.b();
        }

        this.C.c("tileEntities");
        this.N = true;
        Iterator iterator = this.g.iterator();

        while (iterator.hasNext()) {
            OTileEntity otileentity = (OTileEntity) iterator.next();

            if (!otileentity.r() && otileentity.o() && this.f(otileentity.l, otileentity.m, otileentity.n)) {
                try {
                    otileentity.h();
                } catch (Throwable throwable2) {
                    ocrashreport = OCrashReport.a(throwable2, "Ticking tile entity");
                    ocrashreportcategory = ocrashreport.a("Tile entity being ticked");
                    otileentity.a(ocrashreportcategory);
                    throw new OReportedException(ocrashreport);
                }
            }

            if (otileentity.r()) {
                iterator.remove();
                if (this.c(otileentity.l >> 4, otileentity.n >> 4)) {
                    OChunk ochunk = this.e(otileentity.l >> 4, otileentity.n >> 4);

                    if (ochunk != null) {
                        ochunk.f(otileentity.l & 15, otileentity.m, otileentity.n & 15);
                    }
                }
            }
        }

        this.N = false;
        if (!this.b.isEmpty()) {
            this.g.removeAll(this.b);
            this.b.clear();
        }

        this.C.c("pendingTileEntities");
        if (!this.a.isEmpty()) {
            for (int l = 0; l < this.a.size(); ++l) {
                OTileEntity otileentity1 = (OTileEntity) this.a.get(l);

                if (!otileentity1.r()) {
                    if (!this.g.contains(otileentity1)) {
                        this.g.add(otileentity1);
                    }

                    if (this.c(otileentity1.l >> 4, otileentity1.n >> 4)) {
                        OChunk ochunk1 = this.e(otileentity1.l >> 4, otileentity1.n >> 4);

                        if (ochunk1 != null) {
                            ochunk1.a(otileentity1.l & 15, otileentity1.m, otileentity1.n & 15, otileentity1);
                        }
                    }

                    this.j(otileentity1.l, otileentity1.m, otileentity1.n);
                }
            }

            this.a.clear();
        }

        this.C.b();
        this.C.b();
    }

    public void a(Collection collection) {
        if (this.N) {
            this.a.addAll(collection);
        } else {
            this.g.addAll(collection);
        }
    }

    public void g(OEntity oentity) {
        this.a(oentity, true);
    }

    public void a(OEntity oentity, boolean flag) {
        int i = OMathHelper.c(oentity.u);
        int j = OMathHelper.c(oentity.w);
        byte b0 = 32;

        if (!flag || this.e(i - b0, 0, j - b0, i + b0, 0, j + b0)) {
            oentity.U = oentity.u;
            oentity.V = oentity.v;
            oentity.W = oentity.w;
            oentity.C = oentity.A;
            oentity.D = oentity.B;
            if (flag && oentity.ai) {
                if (oentity.o != null) {
                    oentity.T();
                } else {
                    ++oentity.ac;
                    oentity.l_();
                }
            }

            this.C.a("chunkCheck");
            if (Double.isNaN(oentity.u) || Double.isInfinite(oentity.u)) {
                oentity.u = oentity.U;
            }

            if (Double.isNaN(oentity.v) || Double.isInfinite(oentity.v)) {
                oentity.v = oentity.V;
            }

            if (Double.isNaN(oentity.w) || Double.isInfinite(oentity.w)) {
                oentity.w = oentity.W;
            }

            if (Double.isNaN((double) oentity.B) || Double.isInfinite((double) oentity.B)) {
                oentity.B = oentity.D;
            }

            if (Double.isNaN((double) oentity.A) || Double.isInfinite((double) oentity.A)) {
                oentity.A = oentity.C;
            }

            int k = OMathHelper.c(oentity.u / 16.0D);
            int l = OMathHelper.c(oentity.v / 16.0D);
            int i1 = OMathHelper.c(oentity.w / 16.0D);

            if (!oentity.ai || oentity.aj != k || oentity.ak != l || oentity.al != i1) {
                if (oentity.ai && this.c(oentity.aj, oentity.al)) {
                    this.e(oentity.aj, oentity.al).a(oentity, oentity.ak);
                }

                if (this.c(k, i1)) {
                    oentity.ai = true;
                    this.e(k, i1).a(oentity);
                } else {
                    oentity.ai = false;
                }
            }

            this.C.b();
            if (flag && oentity.ai && oentity.n != null) {
                if (!oentity.n.M && oentity.n.o == oentity) {
                    this.g(oentity.n);
                } else {
                    oentity.n.o = null;
                    oentity.n = null;
                }
            }
        }
    }

    public boolean b(OAxisAlignedBB oaxisalignedbb) {
        return this.a(oaxisalignedbb, (OEntity) null);
    }

    public boolean a(OAxisAlignedBB oaxisalignedbb, OEntity oentity) {
        List list = this.b((OEntity) null, oaxisalignedbb);

        for (int i = 0; i < list.size(); ++i) {
            OEntity oentity1 = (OEntity) list.get(i);

            if (!oentity1.M && oentity1.m && oentity1 != oentity) {
                return false;
            }
        }

        return true;
    }

    public boolean c(OAxisAlignedBB oaxisalignedbb) {
        int i = OMathHelper.c(oaxisalignedbb.a);
        int j = OMathHelper.c(oaxisalignedbb.d + 1.0D);
        int k = OMathHelper.c(oaxisalignedbb.b);
        int l = OMathHelper.c(oaxisalignedbb.e + 1.0D);
        int i1 = OMathHelper.c(oaxisalignedbb.c);
        int j1 = OMathHelper.c(oaxisalignedbb.f + 1.0D);

        if (oaxisalignedbb.a < 0.0D) {
            --i;
        }

        if (oaxisalignedbb.b < 0.0D) {
            --k;
        }

        if (oaxisalignedbb.c < 0.0D) {
            --i1;
        }

        for (int k1 = i; k1 < j; ++k1) {
            for (int l1 = k; l1 < l; ++l1) {
                for (int i2 = i1; i2 < j1; ++i2) {
                    OBlock oblock = OBlock.r[this.a(k1, l1, i2)];

                    if (oblock != null) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public boolean d(OAxisAlignedBB oaxisalignedbb) {
        int i = OMathHelper.c(oaxisalignedbb.a);
        int j = OMathHelper.c(oaxisalignedbb.d + 1.0D);
        int k = OMathHelper.c(oaxisalignedbb.b);
        int l = OMathHelper.c(oaxisalignedbb.e + 1.0D);
        int i1 = OMathHelper.c(oaxisalignedbb.c);
        int j1 = OMathHelper.c(oaxisalignedbb.f + 1.0D);

        if (oaxisalignedbb.a < 0.0D) {
            --i;
        }

        if (oaxisalignedbb.b < 0.0D) {
            --k;
        }

        if (oaxisalignedbb.c < 0.0D) {
            --i1;
        }

        for (int k1 = i; k1 < j; ++k1) {
            for (int l1 = k; l1 < l; ++l1) {
                for (int i2 = i1; i2 < j1; ++i2) {
                    OBlock oblock = OBlock.r[this.a(k1, l1, i2)];

                    if (oblock != null && oblock.cO.d()) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public boolean e(OAxisAlignedBB oaxisalignedbb) {
        int i = OMathHelper.c(oaxisalignedbb.a);
        int j = OMathHelper.c(oaxisalignedbb.d + 1.0D);
        int k = OMathHelper.c(oaxisalignedbb.b);
        int l = OMathHelper.c(oaxisalignedbb.e + 1.0D);
        int i1 = OMathHelper.c(oaxisalignedbb.c);
        int j1 = OMathHelper.c(oaxisalignedbb.f + 1.0D);

        if (this.e(i, k, i1, j, l, j1)) {
            for (int k1 = i; k1 < j; ++k1) {
                for (int l1 = k; l1 < l; ++l1) {
                    for (int i2 = i1; i2 < j1; ++i2) {
                        int j2 = this.a(k1, l1, i2);

                        if (j2 == OBlock.av.cz || j2 == OBlock.G.cz || j2 == OBlock.H.cz) {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    public boolean a(OAxisAlignedBB oaxisalignedbb, OMaterial omaterial, OEntity oentity) {
        int i = OMathHelper.c(oaxisalignedbb.a);
        int j = OMathHelper.c(oaxisalignedbb.d + 1.0D);
        int k = OMathHelper.c(oaxisalignedbb.b);
        int l = OMathHelper.c(oaxisalignedbb.e + 1.0D);
        int i1 = OMathHelper.c(oaxisalignedbb.c);
        int j1 = OMathHelper.c(oaxisalignedbb.f + 1.0D);

        if (!this.e(i, k, i1, j, l, j1)) {
            return false;
        } else {
            boolean flag = false;
            OVec3 ovec3 = this.T().a(0.0D, 0.0D, 0.0D);

            for (int k1 = i; k1 < j; ++k1) {
                for (int l1 = k; l1 < l; ++l1) {
                    for (int i2 = i1; i2 < j1; ++i2) {
                        OBlock oblock = OBlock.r[this.a(k1, l1, i2)];

                        if (oblock != null && oblock.cO == omaterial) {
                            double d0 = (double) ((float) (l1 + 1) - OBlockFluid.d(this.h(k1, l1, i2)));

                            if ((double) l >= d0) {
                                flag = true;
                                oblock.a(this, k1, l1, i2, oentity, ovec3);
                            }
                        }
                    }
                }
            }

            if (ovec3.b() > 0.0D && oentity.aw()) {
                ovec3 = ovec3.a();
                double d1 = 0.014D;

                oentity.x += ovec3.c * d1;
                oentity.y += ovec3.d * d1;
                oentity.z += ovec3.e * d1;
            }

            return flag;
        }
    }

    public boolean a(OAxisAlignedBB oaxisalignedbb, OMaterial omaterial) {
        int i = OMathHelper.c(oaxisalignedbb.a);
        int j = OMathHelper.c(oaxisalignedbb.d + 1.0D);
        int k = OMathHelper.c(oaxisalignedbb.b);
        int l = OMathHelper.c(oaxisalignedbb.e + 1.0D);
        int i1 = OMathHelper.c(oaxisalignedbb.c);
        int j1 = OMathHelper.c(oaxisalignedbb.f + 1.0D);

        for (int k1 = i; k1 < j; ++k1) {
            for (int l1 = k; l1 < l; ++l1) {
                for (int i2 = i1; i2 < j1; ++i2) {
                    OBlock oblock = OBlock.r[this.a(k1, l1, i2)];

                    if (oblock != null && oblock.cO == omaterial) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public boolean b(OAxisAlignedBB oaxisalignedbb, OMaterial omaterial) {
        int i = OMathHelper.c(oaxisalignedbb.a);
        int j = OMathHelper.c(oaxisalignedbb.d + 1.0D);
        int k = OMathHelper.c(oaxisalignedbb.b);
        int l = OMathHelper.c(oaxisalignedbb.e + 1.0D);
        int i1 = OMathHelper.c(oaxisalignedbb.c);
        int j1 = OMathHelper.c(oaxisalignedbb.f + 1.0D);

        for (int k1 = i; k1 < j; ++k1) {
            for (int l1 = k; l1 < l; ++l1) {
                for (int i2 = i1; i2 < j1; ++i2) {
                    OBlock oblock = OBlock.r[this.a(k1, l1, i2)];

                    if (oblock != null && oblock.cO == omaterial) {
                        int j2 = this.h(k1, l1, i2);
                        double d0 = (double) (l1 + 1);

                        if (j2 < 8) {
                            d0 = (double) (l1 + 1) - (double) j2 / 8.0D;
                        }

                        if (d0 >= oaxisalignedbb.b) {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    public OExplosion a(OEntity oentity, double d0, double d1, double d2, float f, boolean flag) {
        return this.a(oentity, d0, d1, d2, f, false, flag);
    }

    public OExplosion a(OEntity oentity, double d0, double d1, double d2, float f, boolean flag, boolean flag1) {
        OExplosion oexplosion = new OExplosion(this, oentity, d0, d1, d2, f);

        oexplosion.a = flag;
        oexplosion.b = flag1;
        oexplosion.a();
        oexplosion.a(true);
        return oexplosion;
    }

    public float a(OVec3 ovec3, OAxisAlignedBB oaxisalignedbb) {
        double d0 = 1.0D / ((oaxisalignedbb.d - oaxisalignedbb.a) * 2.0D + 1.0D);
        double d1 = 1.0D / ((oaxisalignedbb.e - oaxisalignedbb.b) * 2.0D + 1.0D);
        double d2 = 1.0D / ((oaxisalignedbb.f - oaxisalignedbb.c) * 2.0D + 1.0D);
        int i = 0;
        int j = 0;

        for (float f = 0.0F; f <= 1.0F; f = (float) ((double) f + d0)) {
            for (float f1 = 0.0F; f1 <= 1.0F; f1 = (float) ((double) f1 + d1)) {
                for (float f2 = 0.0F; f2 <= 1.0F; f2 = (float) ((double) f2 + d2)) {
                    double d3 = oaxisalignedbb.a + (oaxisalignedbb.d - oaxisalignedbb.a) * (double) f;
                    double d4 = oaxisalignedbb.b + (oaxisalignedbb.e - oaxisalignedbb.b) * (double) f1;
                    double d5 = oaxisalignedbb.c + (oaxisalignedbb.f - oaxisalignedbb.c) * (double) f2;

                    if (this.a(this.T().a(d3, d4, d5), ovec3) == null) {
                        ++i;
                    }

                    ++j;
                }
            }
        }

        return (float) i / (float) j;
    }

    public boolean a(OEntityPlayer oentityplayer, int i, int j, int k, int l) {
        if (l == 0) {
            --j;
        }

        if (l == 1) {
            ++j;
        }

        if (l == 2) {
            --k;
        }

        if (l == 3) {
            ++k;
        }

        if (l == 4) {
            --i;
        }

        if (l == 5) {
            ++i;
        }

        if (this.a(i, j, k) == OBlock.av.cz) {
            this.a(oentityplayer, 1004, i, j, k, 0);
            this.i(i, j, k);
            return true;
        } else {
            return false;
        }
    }

    public OTileEntity r(int i, int j, int k) {
        if (j >= 0 && j < 256) {
            OTileEntity otileentity = null;
            int l;
            OTileEntity otileentity1;

            if (this.N) {
                for (l = 0; l < this.a.size(); ++l) {
                    otileentity1 = (OTileEntity) this.a.get(l);
                    if (!otileentity1.r() && otileentity1.l == i && otileentity1.m == j && otileentity1.n == k) {
                        otileentity = otileentity1;
                        break;
                    }
                }
            }

            if (otileentity == null) {
                OChunk ochunk = this.e(i >> 4, k >> 4);

                if (ochunk != null) {
                    otileentity = ochunk.e(i & 15, j, k & 15);
                }
            }

            if (otileentity == null) {
                for (l = 0; l < this.a.size(); ++l) {
                    otileentity1 = (OTileEntity) this.a.get(l);
                    if (!otileentity1.r() && otileentity1.l == i && otileentity1.m == j && otileentity1.n == k) {
                        otileentity = otileentity1;
                        break;
                    }
                }
            }

            return otileentity;
        } else {
            return null;
        }
    }

    public void a(int i, int j, int k, OTileEntity otileentity) {
        if (otileentity != null && !otileentity.r()) {
            if (this.N) {
                otileentity.l = i;
                otileentity.m = j;
                otileentity.n = k;
                Iterator iterator = this.a.iterator();

                while (iterator.hasNext()) {
                    OTileEntity otileentity1 = (OTileEntity) iterator.next();

                    if (otileentity1.l == i && otileentity1.m == j && otileentity1.n == k) {
                        otileentity1.w_();
                        iterator.remove();
                    }
                }

                this.a.add(otileentity);
            } else {
                this.g.add(otileentity);
                OChunk ochunk = this.e(i >> 4, k >> 4);

                if (ochunk != null) {
                    ochunk.a(i & 15, j, k & 15, otileentity);
                }
            }
        }
    }

    public void s(int i, int j, int k) {
        OTileEntity otileentity = this.r(i, j, k);

        if (otileentity != null && this.N) {
            otileentity.w_();
            this.a.remove(otileentity);
        } else {
            if (otileentity != null) {
                this.a.remove(otileentity);
                this.g.remove(otileentity);
            }

            OChunk ochunk = this.e(i >> 4, k >> 4);

            if (ochunk != null) {
                ochunk.f(i & 15, j, k & 15);
            }
        }
    }

    public void a(OTileEntity otileentity) {
        this.b.add(otileentity);
    }

    public boolean t(int i, int j, int k) {
        OBlock oblock = OBlock.r[this.a(i, j, k)];

        return oblock == null ? false : oblock.c();
    }

    public boolean u(int i, int j, int k) {
        return OBlock.l(this.a(i, j, k));
    }

    public boolean v(int i, int j, int k) {
        int l = this.a(i, j, k);

        if (l != 0 && OBlock.r[l] != null) {
            OAxisAlignedBB oaxisalignedbb = OBlock.r[l].b(this, i, j, k);

            return oaxisalignedbb != null && oaxisalignedbb.b() >= 1.0D;
        } else {
            return false;
        }
    }

    public boolean w(int i, int j, int k) {
        OBlock oblock = OBlock.r[this.a(i, j, k)];

        return this.a(oblock, this.h(i, j, k));
    }

    public boolean a(OBlock oblock, int i) {
        return oblock == null ? false : (oblock.cO.k() && oblock.b() ? true : (oblock instanceof OBlockStairs ? (i & 4) == 4 : (oblock instanceof OBlockHalfSlab ? (i & 8) == 8 : (oblock instanceof OBlockHopper ? true : (oblock instanceof OBlockSnow ? (i & 7) == 7 : false)))));
    }

    public boolean c(int i, int j, int k, boolean flag) {
        if (i >= -30000000 && k >= -30000000 && i < 30000000 && k < 30000000) {
            OChunk ochunk = this.v.d(i >> 4, k >> 4);

            if (ochunk != null && !ochunk.g()) {
                OBlock oblock = OBlock.r[this.a(i, j, k)];

                return oblock == null ? false : oblock.cO.k() && oblock.b();
            } else {
                return flag;
            }
        } else {
            return flag;
        }
    }

    public void y() {
        int i = this.a(1.0F);

        if (i != this.j) {
            this.j = i;
        }
    }

    public void a(boolean flag, boolean flag1) {
        this.E = flag;
        this.F = flag1;
    }

    public void b() {
        this.n();
    }

    private void a() {
        if (this.x.p()) {
            this.n = 1.0F;
            if (this.x.n()) {
                this.p = 1.0F;
            }
        }
    }

    protected void n() {
        if (!this.t.f) {
            int i = this.x.o();

            if (i <= 0) {
                if (this.x.n()) {
                    this.x.f(this.s.nextInt(12000) + 3600);
                } else {
                    this.x.f(this.s.nextInt(168000) + 12000);
                }
            } else {
                --i;
                this.x.f(i);
                if (i <= 0) {
                    // CanaryMod: Thunder hook
                    if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.THUNDER_CHANGE, this.world, !this.x.n())) {
                        this.x.a(!this.x.n());
                    } // CanaryMod: diff visibility
                }
            }

            int j = this.x.q();

            if (j <= 0) {
                if (this.x.p()) {
                    this.x.g(this.s.nextInt(12000) + 12000);
                } else {
                    this.x.g(this.s.nextInt(168000) + 12000);
                }
            } else {
                --j;
                this.x.g(j);
                if (j <= 0) {
                    // CanaryMod: Weather hook
                    if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.WEATHER_CHANGE, this.world, !this.x.p())) {
                        this.x.b(!this.x.p());
                    } // CanaryMod: diff visibility
                }
            }

            this.m = this.n;
            if (this.x.p()) {
                this.n = (float) ((double) this.n + 0.01D);
            } else {
                this.n = (float) ((double) this.n - 0.01D);
            }

            if (this.n < 0.0F) {
                this.n = 0.0F;
            }

            if (this.n > 1.0F) {
                this.n = 1.0F;
            }

            this.o = this.p;
            if (this.x.n()) {
                this.p = (float) ((double) this.p + 0.01D);
            } else {
                this.p = (float) ((double) this.p - 0.01D);
            }

            if (this.p < 0.0F) {
                this.p = 0.0F;
            }

            if (this.p > 1.0F) {
                this.p = 1.0F;
            }
        }
    }

    public void z() {
        this.x.g(1);
    }

    protected void A() {
        this.G.clear();
        this.C.a("buildList");

        int i;
        OEntityPlayer oentityplayer;
        int j;
        int k;

        for (i = 0; i < this.h.size(); ++i) {
            oentityplayer = (OEntityPlayer) this.h.get(i);
            j = OMathHelper.c(oentityplayer.u / 16.0D);
            k = OMathHelper.c(oentityplayer.w / 16.0D);
            byte b0 = 7;

            for (int l = -b0; l <= b0; ++l) {
                for (int i1 = -b0; i1 <= b0; ++i1) {
                    this.G.add(new OChunkCoordIntPair(l + j, i1 + k));
                }
            }
        }

        this.C.b();
        if (this.O > 0) {
            --this.O;
        }

        this.C.a("playerCheckLight");
        if (!this.h.isEmpty()) {
            i = this.s.nextInt(this.h.size());
            oentityplayer = (OEntityPlayer) this.h.get(i);
            j = OMathHelper.c(oentityplayer.u) + this.s.nextInt(11) - 5;
            k = OMathHelper.c(oentityplayer.v) + this.s.nextInt(11) - 5;
            int j1 = OMathHelper.c(oentityplayer.w) + this.s.nextInt(11) - 5;

            this.A(j, k, j1);
        }

        this.C.b();
    }

    protected void a(int i, int j, OChunk ochunk) {
        this.C.c("moodSound");
        if (this.O == 0 && !this.I) {
            this.k = this.k * 3 + 1013904223;
            int k = this.k >> 2;
            int l = k & 15;
            int i1 = k >> 8 & 15;
            int j1 = k >> 16 & 127;
            int k1 = ochunk.a(l, j1, i1);

            l += i;
            i1 += j;
            if (k1 == 0 && this.m(l, j1, i1) <= this.s.nextInt(8) && this.b(OEnumSkyBlock.a, l, j1, i1) <= 0) {
                OEntityPlayer oentityplayer = this.a((double) l + 0.5D, (double) j1 + 0.5D, (double) i1 + 0.5D, 8.0D);

                if (oentityplayer != null && oentityplayer.e((double) l + 0.5D, (double) j1 + 0.5D, (double) i1 + 0.5D) > 4.0D) {
                    this.a((double) l + 0.5D, (double) j1 + 0.5D, (double) i1 + 0.5D, "ambient.cave.cave", 0.7F, 0.8F + this.s.nextFloat() * 0.2F);
                    this.O = this.s.nextInt(12000) + 6000;
                }
            }
        }

        this.C.c("checkLight");
        ochunk.o();
    }

    protected void g() {
        this.A();
    }

    public boolean x(int i, int j, int k) {
        return this.d(i, j, k, false);
    }

    public boolean y(int i, int j, int k) {
        return this.d(i, j, k, true);
    }

    public boolean d(int i, int j, int k, boolean flag) {
        OBiomeGenBase obiomegenbase = this.a(i, k);
        float f = obiomegenbase.j();

        if (f > 0.15F) {
            return false;
        } else {
            if (j >= 0 && j < 256 && this.b(OEnumSkyBlock.b, i, j, k) < 10) {
                int l = this.a(i, j, k);

                if ((l == OBlock.F.cz || l == OBlock.E.cz) && this.h(i, j, k) == 0) {
                    if (!flag) {
                        return true;
                    }

                    boolean flag1 = true;

                    if (flag1 && this.g(i - 1, j, k) != OMaterial.h) {
                        flag1 = false;
                    }

                    if (flag1 && this.g(i + 1, j, k) != OMaterial.h) {
                        flag1 = false;
                    }

                    if (flag1 && this.g(i, j, k - 1) != OMaterial.h) {
                        flag1 = false;
                    }

                    if (flag1 && this.g(i, j, k + 1) != OMaterial.h) {
                        flag1 = false;
                    }

                    if (!flag1) {
                        return true;
                    }
                }
            }

            return false;
        }
    }

    public boolean z(int i, int j, int k) {
        OBiomeGenBase obiomegenbase = this.a(i, k);
        float f = obiomegenbase.j();

        if (f > 0.15F) {
            return false;
        } else {
            if (j >= 0 && j < 256 && this.b(OEnumSkyBlock.b, i, j, k) < 10) {
                int l = this.a(i, j - 1, k);
                int i1 = this.a(i, j, k);

                if (i1 == 0 && OBlock.aW.c(this, i, j, k) && l != 0 && l != OBlock.aX.cz && OBlock.r[l].cO.c()) {
                    return true;
                }
            }

            return false;
        }
    }

    public void A(int i, int j, int k) {
        if (!this.t.f) {
            this.c(OEnumSkyBlock.a, i, j, k);
        }

        this.c(OEnumSkyBlock.b, i, j, k);
    }

    private int a(int i, int j, int k, OEnumSkyBlock oenumskyblock) {
        if (oenumskyblock == OEnumSkyBlock.a && this.l(i, j, k)) {
            return 15;
        } else {
            int l = this.a(i, j, k);
            int i1 = oenumskyblock == OEnumSkyBlock.a ? 0 : OBlock.v[l];
            int j1 = OBlock.t[l];

            if (j1 >= 15 && OBlock.v[l] > 0) {
                j1 = 1;
            }

            if (j1 < 1) {
                j1 = 1;
            }

            if (j1 >= 15) {
                return 0;
            } else if (i1 >= 14) {
                return i1;
            } else {
                for (int k1 = 0; k1 < 6; ++k1) {
                    int l1 = i + OFacing.b[k1];
                    int i2 = j + OFacing.c[k1];
                    int j2 = k + OFacing.d[k1];
                    int k2 = this.b(oenumskyblock, l1, i2, j2) - j1;

                    if (k2 > i1) {
                        i1 = k2;
                    }

                    if (i1 >= 14) {
                        return i1;
                    }
                }

                return i1;
            }
        }
    }

    public void c(OEnumSkyBlock oenumskyblock, int i, int j, int k) {
        if (this.b(i, j, k, 17)) {
            int l = 0;
            int i1 = 0;

            this.C.a("getBrightness");
            int j1 = this.b(oenumskyblock, i, j, k);
            int k1 = this.a(i, j, k, oenumskyblock);
            int l1;
            int i2;
            int j2;
            int k2;
            int l2;
            int i3;
            int j3;
            int k3;
            int l3;

            if (k1 > j1) {
                this.H[i1++] = 133152;
            } else if (k1 < j1) {
                this.H[i1++] = 133152 | j1 << 18;

                while (l < i1) {
                    l1 = this.H[l++];
                    i2 = (l1 & 63) - 32 + i;
                    j2 = (l1 >> 6 & 63) - 32 + j;
                    k2 = (l1 >> 12 & 63) - 32 + k;
                    l2 = l1 >> 18 & 15;
                    i3 = this.b(oenumskyblock, i2, j2, k2);
                    if (i3 == l2) {
                        this.b(oenumskyblock, i2, j2, k2, 0);
                        if (l2 > 0) {
                            j3 = OMathHelper.a(i2 - i);
                            l3 = OMathHelper.a(j2 - j);
                            k3 = OMathHelper.a(k2 - k);
                            if (j3 + l3 + k3 < 17) {
                                for (int i4 = 0; i4 < 6; ++i4) {
                                    int j4 = i2 + OFacing.b[i4];
                                    int k4 = j2 + OFacing.c[i4];
                                    int l4 = k2 + OFacing.d[i4];
                                    int i5 = Math.max(1, OBlock.t[this.a(j4, k4, l4)]);

                                    i3 = this.b(oenumskyblock, j4, k4, l4);
                                    if (i3 == l2 - i5 && i1 < this.H.length) {
                                        this.H[i1++] = j4 - i + 32 | k4 - j + 32 << 6 | l4 - k + 32 << 12 | l2 - i5 << 18;
                                    }
                                }
                            }
                        }
                    }
                }

                l = 0;
            }

            this.C.b();
            this.C.a("checkedPosition < toCheckCount");

            while (l < i1) {
                l1 = this.H[l++];
                i2 = (l1 & 63) - 32 + i;
                j2 = (l1 >> 6 & 63) - 32 + j;
                k2 = (l1 >> 12 & 63) - 32 + k;
                l2 = this.b(oenumskyblock, i2, j2, k2);
                i3 = this.a(i2, j2, k2, oenumskyblock);
                if (i3 != l2) {
                    this.b(oenumskyblock, i2, j2, k2, i3);
                    if (i3 > l2) {
                        j3 = Math.abs(i2 - i);
                        l3 = Math.abs(j2 - j);
                        k3 = Math.abs(k2 - k);
                        boolean flag = i1 < this.H.length - 6;

                        if (j3 + l3 + k3 < 17 && flag) {
                            if (this.b(oenumskyblock, i2 - 1, j2, k2) < i3) {
                                this.H[i1++] = i2 - 1 - i + 32 + (j2 - j + 32 << 6) + (k2 - k + 32 << 12);
                            }

                            if (this.b(oenumskyblock, i2 + 1, j2, k2) < i3) {
                                this.H[i1++] = i2 + 1 - i + 32 + (j2 - j + 32 << 6) + (k2 - k + 32 << 12);
                            }

                            if (this.b(oenumskyblock, i2, j2 - 1, k2) < i3) {
                                this.H[i1++] = i2 - i + 32 + (j2 - 1 - j + 32 << 6) + (k2 - k + 32 << 12);
                            }

                            if (this.b(oenumskyblock, i2, j2 + 1, k2) < i3) {
                                this.H[i1++] = i2 - i + 32 + (j2 + 1 - j + 32 << 6) + (k2 - k + 32 << 12);
                            }

                            if (this.b(oenumskyblock, i2, j2, k2 - 1) < i3) {
                                this.H[i1++] = i2 - i + 32 + (j2 - j + 32 << 6) + (k2 - 1 - k + 32 << 12);
                            }

                            if (this.b(oenumskyblock, i2, j2, k2 + 1) < i3) {
                                this.H[i1++] = i2 - i + 32 + (j2 - j + 32 << 6) + (k2 + 1 - k + 32 << 12);
                            }
                        }
                    }
                }
            }

            this.C.b();
        }
    }

    public boolean a(boolean flag) {
        return false;
    }

    public List a(OChunk ochunk, boolean flag) {
        return null;
    }

    public List b(OEntity oentity, OAxisAlignedBB oaxisalignedbb) {
        return this.a(oentity, oaxisalignedbb, (OIEntitySelector) null);
    }

    public List a(OEntity oentity, OAxisAlignedBB oaxisalignedbb, OIEntitySelector oientityselector) {
        ArrayList arraylist = new ArrayList();
        int i = OMathHelper.c((oaxisalignedbb.a - 2.0D) / 16.0D);
        int j = OMathHelper.c((oaxisalignedbb.d + 2.0D) / 16.0D);
        int k = OMathHelper.c((oaxisalignedbb.c - 2.0D) / 16.0D);
        int l = OMathHelper.c((oaxisalignedbb.f + 2.0D) / 16.0D);

        for (int i1 = i; i1 <= j; ++i1) {
            for (int j1 = k; j1 <= l; ++j1) {
                if (this.c(i1, j1)) {
                    this.e(i1, j1).a(oentity, oaxisalignedbb, arraylist, oientityselector);
                }
            }
        }

        return arraylist;
    }

    public List a(Class oclass, OAxisAlignedBB oaxisalignedbb) {
        return this.a(oclass, oaxisalignedbb, (OIEntitySelector) null);
    }

    public List a(Class oclass, OAxisAlignedBB oaxisalignedbb, OIEntitySelector oientityselector) {
        int i = OMathHelper.c((oaxisalignedbb.a - 2.0D) / 16.0D);
        int j = OMathHelper.c((oaxisalignedbb.d + 2.0D) / 16.0D);
        int k = OMathHelper.c((oaxisalignedbb.c - 2.0D) / 16.0D);
        int l = OMathHelper.c((oaxisalignedbb.f + 2.0D) / 16.0D);
        ArrayList arraylist = new ArrayList();

        for (int i1 = i; i1 <= j; ++i1) {
            for (int j1 = k; j1 <= l; ++j1) {
                if (this.c(i1, j1)) {
                    this.e(i1, j1).a(oclass, oaxisalignedbb, arraylist, oientityselector);
                }
            }
        }

        return arraylist;
    }

    public OEntity a(Class oclass, OAxisAlignedBB oaxisalignedbb, OEntity oentity) {
        List list = this.a(oclass, oaxisalignedbb);
        OEntity oentity1 = null;
        double d0 = Double.MAX_VALUE;

        for (int i = 0; i < list.size(); ++i) {
            OEntity oentity2 = (OEntity) list.get(i);

            if (oentity2 != oentity) {
                double d1 = oentity.e(oentity2);

                if (d1 <= d0) {
                    oentity1 = oentity2;
                    d0 = d1;
                }
            }
        }

        return oentity1;
    }

    public abstract OEntity a(int i);

    public void b(int i, int j, int k, OTileEntity otileentity) {
        if (this.f(i, j, k)) {
            this.d(i, k).e();
        }
    }

    public int a(Class oclass) {
        int i = 0;

        for (int j = 0; j < this.e.size(); ++j) {
            OEntity oentity = (OEntity) this.e.get(j);

            if (oclass.isAssignableFrom(oentity.getClass())) {
                ++i;
            }
        }

        return i;
    }

    public void a(List list) {
        this.e.addAll(list);

        for (int i = 0; i < list.size(); ++i) {
            this.a((OEntity) list.get(i));
        }
    }

    public void b(List list) {
        this.f.addAll(list);
    }

    public boolean a(int i, int j, int k, int l, boolean flag, int i1, OEntity oentity, OItemStack oitemstack) {
        int j1 = this.a(j, k, l);
        OBlock oblock = OBlock.r[j1];
        OBlock oblock1 = OBlock.r[i];
        OAxisAlignedBB oaxisalignedbb = oblock1.b(this, j, k, l);

        if (flag) {
            oaxisalignedbb = null;
        }

        if (oaxisalignedbb != null && !this.a(oaxisalignedbb, oentity)) {
            return false;
        } else {
            if (oblock != null && (oblock == OBlock.E || oblock == OBlock.F || oblock == OBlock.G || oblock == OBlock.H || oblock == OBlock.av || oblock.cO.j())) {
                oblock = null;
            }

            return oblock != null && oblock.cO == OMaterial.q && oblock1 == OBlock.cl ? true : i > 0 && oblock == null && oblock1.a(this, j, k, l, i1, oitemstack);
        }
    }

    public OPathEntity a(OEntity oentity, OEntity oentity1, float f, boolean flag, boolean flag1, boolean flag2, boolean flag3) {
        this.C.a("pathfind");
        int i = OMathHelper.c(oentity.u);
        int j = OMathHelper.c(oentity.v + 1.0D);
        int k = OMathHelper.c(oentity.w);
        int l = (int) (f + 16.0F);
        int i1 = i - l;
        int j1 = j - l;
        int k1 = k - l;
        int l1 = i + l;
        int i2 = j + l;
        int j2 = k + l;
        OChunkCache ochunkcache = new OChunkCache(this, i1, j1, k1, l1, i2, j2, 0);
        OPathEntity opathentity = (new OPathFinder(ochunkcache, flag, flag1, flag2, flag3)).a(oentity, oentity1, f);

        this.C.b();
        return opathentity;
    }

    public OPathEntity a(OEntity oentity, int i, int j, int k, float f, boolean flag, boolean flag1, boolean flag2, boolean flag3) {
        this.C.a("pathfind");
        int l = OMathHelper.c(oentity.u);
        int i1 = OMathHelper.c(oentity.v);
        int j1 = OMathHelper.c(oentity.w);
        int k1 = (int) (f + 8.0F);
        int l1 = l - k1;
        int i2 = i1 - k1;
        int j2 = j1 - k1;
        int k2 = l + k1;
        int l2 = i1 + k1;
        int i3 = j1 + k1;
        OChunkCache ochunkcache = new OChunkCache(this, l1, i2, j2, k2, l2, i3, 0);
        OPathEntity opathentity = (new OPathFinder(ochunkcache, flag, flag1, flag2, flag3)).a(oentity, i, j, k, f);

        this.C.b();
        return opathentity;
    }

    public int j(int i, int j, int k, int l) {
        int i1 = this.a(i, j, k);

        return i1 == 0 ? 0 : OBlock.r[i1].c((OIBlockAccess) this, i, j, k, l);
    }

    public int B(int i, int j, int k) {
        byte b0 = 0;
        int l = Math.max(b0, this.j(i, j - 1, k, 0));

        if (l >= 15) {
            return l;
        } else {
            l = Math.max(l, this.j(i, j + 1, k, 1));
            if (l >= 15) {
                return l;
            } else {
                l = Math.max(l, this.j(i, j, k - 1, 2));
                if (l >= 15) {
                    return l;
                } else {
                    l = Math.max(l, this.j(i, j, k + 1, 3));
                    if (l >= 15) {
                        return l;
                    } else {
                        l = Math.max(l, this.j(i - 1, j, k, 4));
                        if (l >= 15) {
                            return l;
                        } else {
                            l = Math.max(l, this.j(i + 1, j, k, 5));
                            return l >= 15 ? l : l;
                        }
                    }
                }
            }
        }
    }

    public boolean k(int i, int j, int k, int l) {
        return this.l(i, j, k, l) > 0;
    }

    public int l(int i, int j, int k, int l) {
        if (this.u(i, j, k)) {
            return this.B(i, j, k);
        } else {
            int i1 = this.a(i, j, k);

            return i1 == 0 ? 0 : OBlock.r[i1].b(this, i, j, k, l);
        }
    }

    public boolean C(int i, int j, int k) {
        return this.l(i, j - 1, k, 0) > 0 ? true : (this.l(i, j + 1, k, 1) > 0 ? true : (this.l(i, j, k - 1, 2) > 0 ? true : (this.l(i, j, k + 1, 3) > 0 ? true : (this.l(i - 1, j, k, 4) > 0 ? true : this.l(i + 1, j, k, 5) > 0))));
    }

    public int D(int i, int j, int k) {
        int l = 0;

        for (int i1 = 0; i1 < 6; ++i1) {
            int j1 = this.l(i + OFacing.b[i1], j + OFacing.c[i1], k + OFacing.d[i1], i1);

            if (j1 >= 15) {
                return 15;
            }

            if (j1 > l) {
                l = j1;
            }
        }

        return l;
    }

    public OEntityPlayer a(OEntity oentity, double d0) {
        return this.a(oentity.u, oentity.v, oentity.w, d0);
    }

    public OEntityPlayer a(double d0, double d1, double d2, double d3) {
        double d4 = -1.0D;
        OEntityPlayer oentityplayer = null;

        for (int i = 0; i < this.h.size(); ++i) {
            OEntityPlayer oentityplayer1 = (OEntityPlayer) this.h.get(i);
            double d5 = oentityplayer1.e(d0, d1, d2);

            if ((d3 < 0.0D || d5 < d3 * d3) && (d4 == -1.0D || d5 < d4)) {
                d4 = d5;
                oentityplayer = oentityplayer1;
            }
        }

        return oentityplayer;
    }

    public OEntityPlayer b(OEntity oentity, double d0) {
        return this.b(oentity.u, oentity.v, oentity.w, d0);
    }

    public OEntityPlayer b(double d0, double d1, double d2, double d3) {
        double d4 = -1.0D;
        OEntityPlayer oentityplayer = null;

        for (int i = 0; i < this.h.size(); ++i) {
            OEntityPlayer oentityplayer1 = (OEntityPlayer) this.h.get(i);

            if (!oentityplayer1.ce.a && oentityplayer1.R()) {
                double d5 = oentityplayer1.e(d0, d1, d2);
                double d6 = d3;

                if (oentityplayer1.ag()) {
                    d6 = d3 * 0.800000011920929D;
                }

                if (oentityplayer1.ai()) {
                    float f = oentityplayer1.ca();

                    if (f < 0.1F) {
                        f = 0.1F;
                    }

                    d6 *= (double) (0.7F * f);
                }

                if ((d3 < 0.0D || d5 < d6 * d6) && (d4 == -1.0D || d5 < d4)) {
                    d4 = d5;
                    oentityplayer = oentityplayer1;
                }
            }
        }

        return oentityplayer;
    }

    public OEntityPlayer a(String s) {
        for (int i = 0; i < this.h.size(); ++i) {
            if (s.equals(((OEntityPlayer) this.h.get(i)).bS)) {
                return (OEntityPlayer) this.h.get(i);
            }
        }

        return null;
    }

    public void E() throws OMinecraftException {
        this.w.c();
    }

    public long F() {
        return this.x.b();
    }

    public long G() {
        return this.x.f();
    }

    public long H() {
        return this.x.g();
    }

    public void b(long i) {
        if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.TIME_CHANGE, this.world, i)) {
            this.x.c(i);
        } // CanaryMod: diff visibility
    }

    public OChunkCoordinates I() {
        return new OChunkCoordinates(this.x.c(), this.x.d(), this.x.e());
    }

    public boolean a(OEntityPlayer oentityplayer, int i, int j, int k) {
        return true;
    }

    public void a(OEntity oentity, byte b0) {}

    public OIChunkProvider J() {
        return this.v;
    }

    public void d(int i, int j, int k, int l, int i1, int j1) {
        if (l > 0) {
            OBlock.r[l].b(this, i, j, k, i1, j1);
        }
    }

    public OISaveHandler K() {
        return this.w;
    }

    public OWorldInfo L() {
        return this.x;
    }

    public OGameRules M() {
        return this.x.x();
    }

    public void c() {}

    public float h(float f) {
        return (this.o + (this.p - this.o) * f) * this.i(f);
    }

    public float i(float f) {
        return this.m + (this.n - this.m) * f;
    }

    public boolean N() {
        return (double) this.h(1.0F) > 0.9D;
    }

    public boolean O() {
        return (double) this.i(1.0F) > 0.2D;
    }

    public boolean F(int i, int j, int k) {
        if (!this.O()) {
            return false;
        } else if (!this.l(i, j, k)) {
            return false;
        } else if (this.h(i, k) > j) {
            return false;
        } else {
            OBiomeGenBase obiomegenbase = this.a(i, k);

            return obiomegenbase.c() ? false : obiomegenbase.d();
        }
    }

    public boolean G(int i, int j, int k) {
        OBiomeGenBase obiomegenbase = this.a(i, k);

        return obiomegenbase.e();
    }

    public void a(String s, OWorldSavedData oworldsaveddata) {
        this.z.a(s, oworldsaveddata);
    }

    public OWorldSavedData a(Class oclass, String s) {
        return this.z.a(oclass, s);
    }

    public int b(String s) {
        return this.z.a(s);
    }

    public void d(int i, int j, int k, int l, int i1) {
        for (int j1 = 0; j1 < this.u.size(); ++j1) {
            ((OIWorldAccess) this.u.get(j1)).a(i, j, k, l, i1);
        }
    }

    public void e(int i, int j, int k, int l, int i1) {
        this.a((OEntityPlayer) null, i, j, k, l, i1);
    }

    public void a(OEntityPlayer oentityplayer, int i, int j, int k, int l, int i1) {
        try {
            for (int j1 = 0; j1 < this.u.size(); ++j1) {
                ((OIWorldAccess) this.u.get(j1)).a(oentityplayer, i, j, k, l, i1);
            }
        } catch (Throwable throwable) {
            OCrashReport ocrashreport = OCrashReport.a(throwable, "Playing level event");
            OCrashReportCategory ocrashreportcategory = ocrashreport.a("Level event being played");

            ocrashreportcategory.a("Block coordinates", OCrashReportCategory.a(j, k, l));
            ocrashreportcategory.a("Event source", oentityplayer);
            ocrashreportcategory.a("Event type", Integer.valueOf(i));
            ocrashreportcategory.a("Event data", Integer.valueOf(i1));
            throw new OReportedException(ocrashreport);
        }
    }

    public int P() {
        return 256;
    }

    public int Q() {
        return this.t.f ? 128 : 256;
    }

    public OIUpdatePlayerListBox a(OEntityMinecart oentityminecart) {
        return null;
    }

    public Random H(int i, int j, int k) {
        long l = (long) i * 341873128712L + (long) j * 132897987541L + this.L().b() + (long) k;

        this.s.setSeed(l);
        return this.s;
    }

    public OChunkPosition b(String s, int i, int j, int k) {
        return this.J().a(this, s, i, j, k);
    }

    public OCrashReportCategory a(OCrashReport ocrashreport) {
        OCrashReportCategory ocrashreportcategory = ocrashreport.a("Affected level", 1);

        ocrashreportcategory.a("Level name", (this.x == null ? "????" : this.x.k()));
        ocrashreportcategory.a("All players", (Callable) (new OCallableLvl2(this)));
        ocrashreportcategory.a("Chunk stats", (Callable) (new OCallableLvl3(this)));

        try {
            this.x.a(ocrashreportcategory);
        } catch (Throwable throwable) {
            ocrashreportcategory.a("Level Data Unobtainable", throwable);
        }

        return ocrashreportcategory;
    }

    public void f(int i, int j, int k, int l, int i1) {
        for (int j1 = 0; j1 < this.u.size(); ++j1) {
            OIWorldAccess oiworldaccess = (OIWorldAccess) this.u.get(j1);

            oiworldaccess.b(i, j, k, l, i1);
        }
    }

    public OVec3Pool T() {
        return this.J;
    }

    public Calendar U() {
        if (this.G() % 600L == 0L) {
            this.K.setTimeInMillis(System.currentTimeMillis());
        }

        return this.K;
    }

    public OScoreboard V() {
        return this.D;
    }

    public void m(int i, int j, int k, int l) {
        for (int i1 = 0; i1 < 4; ++i1) {
            int j1 = i + ODirection.a[i1];
            int k1 = k + ODirection.b[i1];
            int l1 = this.a(j1, j, k1);

            if (l1 != 0) {
                OBlock oblock = OBlock.r[l1];

                if (OBlock.cp.g(l1)) {
                    oblock.a(this, j1, j, k1, l);
                } else if (OBlock.l(l1)) {
                    j1 += ODirection.a[i1];
                    k1 += ODirection.b[i1];
                    l1 = this.a(j1, j, k1);
                    oblock = OBlock.r[l1];
                    if (OBlock.cp.g(l1)) {
                        oblock.a(this, j1, j, k1, l);
                    }
                }
            }
        }
    }

    public OILogAgent W() {
        return this.L;
    }
}
