import java.util.Iterator;
import java.util.List;
import java.util.Random;


public abstract class OEntity {

    private static int b = 0;
    public int k;
    public double l;
    public boolean m;
    public OEntity n;
    public OEntity o;
    public OWorld p;
    public double q;
    public double r;
    public double s;
    public double t;
    public double u;
    public double v;
    public double w;
    public double x;
    public double y;
    public float z;
    public float A;
    public float B;
    public float C;
    public final OAxisAlignedBB D;
    public boolean E;
    public boolean F;
    public boolean G;
    public boolean H;
    public boolean I;
    protected boolean J;
    public boolean K;
    public boolean L;
    public float M;
    public float N;
    public float O;
    public float P;
    public float Q;
    public float R;
    public float S;
    private int c;
    public double T;
    public double U;
    public double V;
    public float W;
    public float X;
    public boolean Y;
    public float Z;
    protected Random aa;
    public int ab;
    public int ac;
    protected int d; // CanaryMod: private -> protected
    protected boolean ad;
    public int ae;
    private boolean e;
    protected boolean af;
    protected ODataWatcher ag;
    private double f;
    private double g;
    public boolean ah;
    public int ai;
    public int aj;
    public int ak;
    public boolean al;
    public boolean am;
    public int an;
    protected boolean ao;
    private int h;
    public int ap;
    protected int aq;
    public OEnumEntitySize ar;
    // CanaryMod Start
    BaseEntity entity = new BaseEntity(this);
    public static PluginLoader manager = etc.getLoader();
    // CanaryMod end


    public OEntity(OWorld oworld) {
        this.k = b++;
        this.l = 1.0D;
        this.m = false;
        this.D = OAxisAlignedBB.a(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
        this.E = false;
        this.H = false;
        this.I = false;
        this.K = true;
        this.L = false;
        this.M = 0.0F;
        this.N = 0.6F;
        this.O = 1.8F;
        this.P = 0.0F;
        this.Q = 0.0F;
        this.R = 0.0F;
        this.S = 0.0F;
        this.c = 1;
        this.W = 0.0F;
        this.X = 0.0F;
        this.Y = false;
        this.Z = 0.0F;
        this.aa = new Random();
        this.ab = 0;
        this.ac = 1;
        this.d = 0;
        this.ad = false;
        this.ae = 0;
        this.e = true;
        this.af = false;
        this.ag = new ODataWatcher();
        this.ah = false;
        this.aq = 0;
        this.ar = OEnumEntitySize.b;
        this.p = oworld;
        this.b(0.0D, 0.0D, 0.0D);
        if (oworld != null) {
            this.ap = oworld.v.h;
        }

        this.ag.a(0, Byte.valueOf((byte) 0));
        this.ag.a(1, Short.valueOf((short) 300));
        this.a();
    }

    protected abstract void a();

    public ODataWatcher v() {
        return this.ag;
    }

    public boolean equals(Object object) {
        return object instanceof OEntity ? ((OEntity) object).k == this.k : false;
    }

    public int hashCode() {
        return this.k;
    }

    public void x() {
        this.L = true;
    }

    protected void a(float f, float f1) {
        this.N = f;
        this.O = f1;
        float f2 = f % 2.0F;

        if ((double) f2 < 0.375D) {
            this.ar = OEnumEntitySize.a;
        } else if ((double) f2 < 0.75D) {
            this.ar = OEnumEntitySize.b;
        } else if ((double) f2 < 1.0D) {
            this.ar = OEnumEntitySize.c;
        } else if ((double) f2 < 1.375D) {
            this.ar = OEnumEntitySize.d;
        } else if ((double) f2 < 1.75D) {
            this.ar = OEnumEntitySize.e;
        } else {
            this.ar = OEnumEntitySize.f;
        }
    }

    protected void b(float f, float f1) {
        this.z = f % 360.0F;
        this.A = f1 % 360.0F;
    }

    public void b(double d0, double d1, double d2) {
        this.t = d0;
        this.u = d1;
        this.v = d2;
        float f = this.N / 2.0F;
        float f1 = this.O;

        this.D.b(d0 - (double) f, d1 - (double) this.M + (double) this.W, d2 - (double) f, d0 + (double) f, d1 - (double) this.M + (double) this.W + (double) f1, d2 + (double) f);
    }

    public void j_() {
        this.y();
    }

    public void y() {
        this.p.E.a("entityBaseTick");
        if (this.o != null && this.o.L) {
            this.o = null;
        }

        ++this.ab;
        this.P = this.Q;
        this.q = this.t;
        this.r = this.u;
        this.s = this.v;
        this.C = this.A;
        this.B = this.z;
        int i;

        if (!this.p.J && this.p instanceof OWorldServer) {
            OMinecraftServer ominecraftserver = ((OWorldServer) this.p).o();

            i = this.z();
            if (this.ao) {
                if (ominecraftserver.s()) {
                    if (this.o == null && this.h++ >= i) {
                        this.h = i;
                        this.an = this.ab();
                        byte b0;

                        if (this.p.v.h == -1) {
                            b0 = 0;
                        } else {
                            b0 = -1;
                        }

                        this.b(b0);
                    }

                    this.ao = false;
                }
            } else {
                if (this.h > 0) {
                    this.h -= 4;
                }

                if (this.h < 0) {
                    this.h = 0;
                }
            }

            if (this.an > 0) {
                --this.an;
            }
        }

        int j;

        if (this.ai() && !this.H()) {
            int k = OMathHelper.c(this.t);

            i = OMathHelper.c(this.u - 0.20000000298023224D - (double) this.M);
            j = OMathHelper.c(this.v);
            int l = this.p.a(k, i, j);

            if (l > 0) {
                this.p.a("tilecrack_" + l, this.t + ((double) this.aa.nextFloat() - 0.5D) * (double) this.N, this.D.b + 0.1D, this.v + ((double) this.aa.nextFloat() - 0.5D) * (double) this.N, -this.w * 4.0D, 1.5D, -this.y * 4.0D);
            }
        }

        if (this.I()) {
            if (!this.ad && !this.e) {
                float f = OMathHelper.a(this.w * this.w * 0.20000000298023224D + this.x * this.x + this.y * this.y * 0.20000000298023224D) * 0.2F;

                if (f > 1.0F) {
                    f = 1.0F;
                }

                this.p.a(this, "liquid.splash", f, 1.0F + (this.aa.nextFloat() - this.aa.nextFloat()) * 0.4F);
                float f1 = (float) OMathHelper.c(this.D.b);

                float f2;
                float f3;

                for (j = 0; (float) j < 1.0F + this.N * 20.0F; ++j) {
                    f3 = (this.aa.nextFloat() * 2.0F - 1.0F) * this.N;
                    f2 = (this.aa.nextFloat() * 2.0F - 1.0F) * this.N;
                    this.p.a("bubble", this.t + (double) f3, (double) (f1 + 1.0F), this.v + (double) f2, this.w, this.x - (double) (this.aa.nextFloat() * 0.2F), this.y);
                }

                for (j = 0; (float) j < 1.0F + this.N * 20.0F; ++j) {
                    f3 = (this.aa.nextFloat() * 2.0F - 1.0F) * this.N;
                    f2 = (this.aa.nextFloat() * 2.0F - 1.0F) * this.N;
                    this.p.a("splash", this.t + (double) f3, (double) (f1 + 1.0F), this.v + (double) f2, this.w, this.x, this.y);
                }
            }

            this.S = 0.0F;
            this.ad = true;
            this.d = 0;
        } else {
            this.ad = false;
        }

        if (this.p.J) {
            this.d = 0;
        } else if (this.d > 0) {
            if (this.af) {
                this.d -= 4;
                if (this.d < 0) {
                    this.d = 0;
                }
            } else {
                if (this.d % 20 == 0) {
                    // CanaryMod Damage hook: Periodic burn damage
                    if (!(Boolean) manager.callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.FIRE_TICK, null, entity, 1)) {
                        this.a(ODamageSource.b, 1);
                    }
                }

                --this.d;
            }
        }

        if (this.J()) {
            this.A();
            this.S *= 0.5F;
        }

        if (this.u < -64.0D) {
            this.C();
        }

        if (!this.p.J) {
            this.a(0, this.d > 0);
            this.a(2, this.o != null);
        }

        this.e = false;
        this.p.E.b();
    }

    public int z() {
        return 0;
    }

    protected void A() {
        if (!this.af) {
            // CanaryMod Damage hook: Lava
            if (this instanceof OEntityLiving) {
                if ((Boolean) manager.callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.LAVA, null, entity, 4)) {
                    return;
                }
            }
            this.a(ODamageSource.c, 4);
            this.c(15);
        }

    }

    public void c(int i) {
        int j = i * 20;

        if (this.d < j) {
            this.d = j;
        }

    }

    public void B() {
        this.d = 0;
    }

    protected void C() {
        this.x();
    }

    public boolean c(double d0, double d1, double d2) {
        OAxisAlignedBB oaxisalignedbb = this.D.c(d0, d1, d2);
        List list = this.p.a(this, oaxisalignedbb);

        return !list.isEmpty() ? false : !this.p.d(oaxisalignedbb);
    }

    public void d(double d0, double d1, double d2) {
        if (this.Y) {
            this.D.d(d0, d1, d2);
            this.t = (this.D.a + this.D.d) / 2.0D;
            this.u = this.D.b + (double) this.M - (double) this.W;
            this.v = (this.D.c + this.D.f) / 2.0D;
        } else {
            this.p.E.a("move");
            this.W *= 0.4F;
            double d3 = this.t;
            double d4 = this.u;
            double d5 = this.v;

            if (this.J) {
                this.J = false;
                d0 *= 0.25D;
                d1 *= 0.05000000074505806D;
                d2 *= 0.25D;
                this.w = 0.0D;
                this.x = 0.0D;
                this.y = 0.0D;
            }

            double d6 = d0;
            double d7 = d1;
            double d8 = d2;
            OAxisAlignedBB oaxisalignedbb = this.D.c();
            boolean flag = this.E && this.ah() && this instanceof OEntityPlayer;

            if (flag) {
                double d9;

                for (d9 = 0.05D; d0 != 0.0D && this.p.a(this, this.D.c(d0, -1.0D, 0.0D)).isEmpty(); d6 = d0) {
                    if (d0 < d9 && d0 >= -d9) {
                        d0 = 0.0D;
                    } else if (d0 > 0.0D) {
                        d0 -= d9;
                    } else {
                        d0 += d9;
                    }
                }

                for (; d2 != 0.0D && this.p.a(this, this.D.c(0.0D, -1.0D, d2)).isEmpty(); d8 = d2) {
                    if (d2 < d9 && d2 >= -d9) {
                        d2 = 0.0D;
                    } else if (d2 > 0.0D) {
                        d2 -= d9;
                    } else {
                        d2 += d9;
                    }
                }

                while (d0 != 0.0D && d2 != 0.0D && this.p.a(this, this.D.c(d0, -1.0D, d2)).isEmpty()) {
                    if (d0 < d9 && d0 >= -d9) {
                        d0 = 0.0D;
                    } else if (d0 > 0.0D) {
                        d0 -= d9;
                    } else {
                        d0 += d9;
                    }

                    if (d2 < d9 && d2 >= -d9) {
                        d2 = 0.0D;
                    } else if (d2 > 0.0D) {
                        d2 -= d9;
                    } else {
                        d2 += d9;
                    }

                    d6 = d0;
                    d8 = d2;
                }
            }

            List list = this.p.a(this, this.D.a(d0, d1, d2));

            OAxisAlignedBB oaxisalignedbb1;

            for (Iterator iterator = list.iterator(); iterator.hasNext(); d1 = oaxisalignedbb1.b(this.D, d1)) {
                oaxisalignedbb1 = (OAxisAlignedBB) iterator.next();
            }

            this.D.d(0.0D, d1, 0.0D);
            if (!this.K && d7 != d1) {
                d2 = 0.0D;
                d1 = 0.0D;
                d0 = 0.0D;
            }

            boolean flag1 = this.E || d7 != d1 && d7 < 0.0D;

            OAxisAlignedBB oaxisalignedbb2;
            Iterator iterator1;

            for (iterator1 = list.iterator(); iterator1.hasNext(); d0 = oaxisalignedbb2.a(this.D, d0)) {
                oaxisalignedbb2 = (OAxisAlignedBB) iterator1.next();
            }

            this.D.d(d0, 0.0D, 0.0D);
            if (!this.K && d6 != d0) {
                d2 = 0.0D;
                d1 = 0.0D;
                d0 = 0.0D;
            }

            for (iterator1 = list.iterator(); iterator1.hasNext(); d2 = oaxisalignedbb2.c(this.D, d2)) {
                oaxisalignedbb2 = (OAxisAlignedBB) iterator1.next();
            }

            this.D.d(0.0D, 0.0D, d2);
            if (!this.K && d8 != d2) {
                d2 = 0.0D;
                d1 = 0.0D;
                d0 = 0.0D;
            }

            double d10;
            double d11;
            double d12;

            if (this.X > 0.0F && flag1 && (flag || this.W < 0.05F) && (d6 != d0 || d8 != d2)) {
                d10 = d0;
                d11 = d1;
                d12 = d2;
                d0 = d6;
                d1 = (double) this.X;
                d2 = d8;
                OAxisAlignedBB oaxisalignedbb3 = this.D.c();

                this.D.c(oaxisalignedbb);
                list = this.p.a(this, this.D.a(d6, d1, d8));

                Iterator iterator2;
                OAxisAlignedBB oaxisalignedbb4;

                for (iterator2 = list.iterator(); iterator2.hasNext(); d1 = oaxisalignedbb4.b(this.D, d1)) {
                    oaxisalignedbb4 = (OAxisAlignedBB) iterator2.next();
                }

                this.D.d(0.0D, d1, 0.0D);
                if (!this.K && d7 != d1) {
                    d2 = 0.0D;
                    d1 = 0.0D;
                    d0 = 0.0D;
                }

                for (iterator2 = list.iterator(); iterator2.hasNext(); d0 = oaxisalignedbb4.a(this.D, d0)) {
                    oaxisalignedbb4 = (OAxisAlignedBB) iterator2.next();
                }

                this.D.d(d0, 0.0D, 0.0D);
                if (!this.K && d6 != d0) {
                    d2 = 0.0D;
                    d1 = 0.0D;
                    d0 = 0.0D;
                }

                for (iterator2 = list.iterator(); iterator2.hasNext(); d2 = oaxisalignedbb4.c(this.D, d2)) {
                    oaxisalignedbb4 = (OAxisAlignedBB) iterator2.next();
                }

                this.D.d(0.0D, 0.0D, d2);
                if (!this.K && d8 != d2) {
                    d2 = 0.0D;
                    d1 = 0.0D;
                    d0 = 0.0D;
                }

                if (!this.K && d7 != d1) {
                    d2 = 0.0D;
                    d1 = 0.0D;
                    d0 = 0.0D;
                } else {
                    d1 = (double) (-this.X);

                    for (iterator2 = list.iterator(); iterator2.hasNext(); d1 = oaxisalignedbb4.b(this.D, d1)) {
                        oaxisalignedbb4 = (OAxisAlignedBB) iterator2.next();
                    }

                    this.D.d(0.0D, d1, 0.0D);
                }

                if (d10 * d10 + d12 * d12 >= d0 * d0 + d2 * d2) {
                    d0 = d10;
                    d1 = d11;
                    d2 = d12;
                    this.D.c(oaxisalignedbb3);
                } else {
                    double d13 = this.D.b - (double) ((int) this.D.b);

                    if (d13 > 0.0D) {
                        this.W = (float) ((double) this.W + d13 + 0.01D);
                    }
                }
            }

            this.p.E.b();
            this.p.E.a("rest");
            this.t = (this.D.a + this.D.d) / 2.0D;
            this.u = this.D.b + (double) this.M - (double) this.W;
            this.v = (this.D.c + this.D.f) / 2.0D;
            this.F = d6 != d0 || d8 != d2;
            this.G = d7 != d1;
            this.E = d7 != d1 && d7 < 0.0D;
            this.H = this.F || this.G;
            this.a(d1, this.E);
            if (d6 != d0) {
                this.w = 0.0D;
            }

            if (d7 != d1) {
                this.x = 0.0D;
            }

            if (d8 != d2) {
                this.y = 0.0D;
            }

            d10 = this.t - d3;
            d11 = this.u - d4;
            d12 = this.v - d5;
            if (this.f_() && !flag && this.o == null) {
                int i = OMathHelper.c(this.t);
                int j = OMathHelper.c(this.u - 0.20000000298023224D - (double) this.M);
                int k = OMathHelper.c(this.v);
                int l = this.p.a(i, j, k);

                if (l == 0 && this.p.a(i, j - 1, k) == OBlock.bc.cm) {
                    l = this.p.a(i, j - 1, k);
                }

                if (l != OBlock.aI.cm) {
                    d11 = 0.0D;
                }

                this.Q = (float) ((double) this.Q + (double) OMathHelper.a(d10 * d10 + d12 * d12) * 0.6D);
                this.R = (float) ((double) this.R + (double) OMathHelper.a(d10 * d10 + d11 * d11 + d12 * d12) * 0.6D);
                if (this.R > (float) this.c && l > 0) {
                    this.c = (int) this.R + 1;
                    if (this.H()) {
                        float f = OMathHelper.a(this.w * this.w * 0.20000000298023224D + this.x * this.x + this.y * this.y * 0.20000000298023224D) * 0.35F;

                        if (f > 1.0F) {
                            f = 1.0F;
                        }

                        this.p.a(this, "liquid.swim", f, 1.0F + (this.aa.nextFloat() - this.aa.nextFloat()) * 0.4F);
                    }

                    this.a(i, j, k, l);
                    OBlock.p[l].b(this.p, i, j, k, this);
                }
            }

            this.D();
            boolean flag2 = this.G();

            if (this.p.e(this.D.e(0.001D, 0.001D, 0.001D))) {
                this.d(1);
                if (!flag2) {
                    ++this.d;
                    if (this.d == 0) {
                        this.c(8);
                    }
                }
            } else if (this.d <= 0) {
                this.d = -this.ac;
            }

            if (flag2 && this.d > 0) {
                this.p.a(this, "random.fizz", 0.7F, 1.6F + (this.aa.nextFloat() - this.aa.nextFloat()) * 0.4F);
                this.d = -this.ac;
            }

            this.p.E.b();
        }
    }

    protected void D() {
        int i = OMathHelper.c(this.D.a + 0.001D);
        int j = OMathHelper.c(this.D.b + 0.001D);
        int k = OMathHelper.c(this.D.c + 0.001D);
        int l = OMathHelper.c(this.D.d - 0.001D);
        int i1 = OMathHelper.c(this.D.e - 0.001D);
        int j1 = OMathHelper.c(this.D.f - 0.001D);

        if (this.p.d(i, j, k, l, i1, j1)) {
            for (int k1 = i; k1 <= l; ++k1) {
                for (int l1 = j; l1 <= i1; ++l1) {
                    for (int i2 = k; i2 <= j1; ++i2) {
                        int j2 = this.p.a(k1, l1, i2);

                        if (j2 > 0) {
                            OBlock.p[j2].a(this.p, k1, l1, i2, this);
                        }
                    }
                }
            }
        }
    }

    protected void a(int i, int j, int k, int l) {
        OStepSound ostepsound = OBlock.p[l].cz;

        if (this.p.a(i, j + 1, k) == OBlock.aV.cm) {
            ostepsound = OBlock.aV.cz;
            this.p.a(this, ostepsound.e(), ostepsound.c() * 0.15F, ostepsound.d());
        } else if (!OBlock.p[l].cB.d()) {
            this.p.a(this, ostepsound.e(), ostepsound.c() * 0.15F, ostepsound.d());
        }

    }

    protected boolean f_() {
        return true;
    }

    protected void a(double d0, boolean flag) {
        if (flag) {
            if (this.S > 0.0F) {
                this.a(this.S);
                this.S = 0.0F;
            }
        } else if (d0 < 0.0D) {
            this.S = (float) ((double) this.S - d0);
        }

    }

    public OAxisAlignedBB E() {
        return null;
    }

    protected void d(int i) {
        if (!this.af) {
            // CanaryMod Damage Hook: Fire
            if (!(Boolean) manager.callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.FIRE, null, entity, i)) {
                this.a(ODamageSource.a, i);
            }
        }
    }

    public final boolean F() {
        return this.af;
    }

    protected void a(float f) {
        if (this.n != null) {
            this.n.a(f);
        }

    }

    public boolean G() {
        return this.ad || this.p.B(OMathHelper.c(this.t), OMathHelper.c(this.u), OMathHelper.c(this.v));
    }

    public boolean H() {
        return this.ad;
    }

    public boolean I() {
        return this.p.a(this.D.b(0.0D, -0.4000000059604645D, 0.0D).e(0.001D, 0.001D, 0.001D), OMaterial.h, this);
    }

    public boolean a(OMaterial omaterial) {
        double d0 = this.u + (double) this.e();
        int i = OMathHelper.c(this.t);
        int j = OMathHelper.d((float) OMathHelper.c(d0));
        int k = OMathHelper.c(this.v);
        int l = this.p.a(i, j, k);

        if (l != 0 && OBlock.p[l].cB == omaterial) {
            float f = OBlockFluid.d(this.p.g(i, j, k)) - 0.11111111F;
            float f1 = (float) (j + 1) - f;

            return d0 < (double) f1;
        } else {
            return false;
        }
    }

    public float e() {
        return 0.0F;
    }

    public boolean J() {
        return this.p.a(this.D.b(-0.10000000149011612D, -0.4000000059604645D, -0.10000000149011612D), OMaterial.i);
    }

    public void a(float f, float f1, float f2) {
        float f3 = f * f + f1 * f1;

        if (f3 >= 1.0E-4F) {
            f3 = OMathHelper.c(f3);
            if (f3 < 1.0F) {
                f3 = 1.0F;
            }

            f3 = f2 / f3;
            f *= f3;
            f1 *= f3;
            float f4 = OMathHelper.a(this.z * 3.1415927F / 180.0F);
            float f5 = OMathHelper.b(this.z * 3.1415927F / 180.0F);

            this.w += (double) (f * f5 - f1 * f4);
            this.y += (double) (f1 * f5 + f * f4);
        }
    }

    public float c(float f) {
        int i = OMathHelper.c(this.t);
        int j = OMathHelper.c(this.v);

        if (this.p.e(i, 0, j)) {
            double d0 = (this.D.e - this.D.b) * 0.66D;
            int k = OMathHelper.c(this.u - (double) this.M + d0);

            return this.p.o(i, k, j);
        } else {
            return 0.0F;
        }
    }

    public void a(OWorld oworld) {
        this.p = oworld;
    }

    public void a(double d0, double d1, double d2, float f, float f1) {
        this.q = this.t = d0;
        this.r = this.u = d1;
        this.s = this.v = d2;
        this.B = this.z = f;
        this.C = this.A = f1;
        this.W = 0.0F;
        double d3 = (double) (this.B - f);

        if (d3 < -180.0D) {
            this.B += 360.0F;
        }

        if (d3 >= 180.0D) {
            this.B -= 360.0F;
        }

        this.b(this.t, this.u, this.v);
        this.b(f, f1);
    }

    public void b(double d0, double d1, double d2, float f, float f1) {
        this.T = this.q = this.t = d0;
        this.U = this.r = this.u = d1 + (double) this.M;
        this.V = this.s = this.v = d2;
        this.z = f;
        this.A = f1;
        this.b(this.t, this.u, this.v);
    }

    public float d(OEntity oentity) {
        float f = (float) (this.t - oentity.t);
        float f1 = (float) (this.u - oentity.u);
        float f2 = (float) (this.v - oentity.v);

        return OMathHelper.c(f * f + f1 * f1 + f2 * f2);
    }

    public double e(double d0, double d1, double d2) {
        double d3 = this.t - d0;
        double d4 = this.u - d1;
        double d5 = this.v - d2;

        return d3 * d3 + d4 * d4 + d5 * d5;
    }

    public double f(double d0, double d1, double d2) {
        double d3 = this.t - d0;
        double d4 = this.u - d1;
        double d5 = this.v - d2;

        return (double) OMathHelper.a(d3 * d3 + d4 * d4 + d5 * d5);
    }

    public double e(OEntity oentity) {
        double d0 = this.t - oentity.t;
        double d1 = this.u - oentity.u;
        double d2 = this.v - oentity.v;

        return d0 * d0 + d1 * d1 + d2 * d2;
    }

    public void b_(OEntityPlayer oentityplayer) {}

    public void f(OEntity oentity) {
        if (oentity.n != this && oentity.o != this) {
            double d0 = oentity.t - this.t;
            double d1 = oentity.v - this.v;
            double d2 = OMathHelper.a(d0, d1);

            if (d2 >= 0.009999999776482582D) {
                d2 = (double) OMathHelper.a(d2);
                d0 /= d2;
                d1 /= d2;
                double d3 = 1.0D / d2;

                if (d3 > 1.0D) {
                    d3 = 1.0D;
                }

                d0 *= d3;
                d1 *= d3;
                d0 *= 0.05000000074505806D;
                d1 *= 0.05000000074505806D;
                d0 *= (double) (1.0F - this.Z);
                d1 *= (double) (1.0F - this.Z);
                this.g(-d0, 0.0D, -d1);
                oentity.g(d0, 0.0D, d1);
            }

        }
    }

    public void g(double d0, double d1, double d2) {
        this.w += d0;
        this.x += d1;
        this.y += d2;
        this.am = true;
    }

    protected void K() {
        this.I = true;
    }

    public boolean a(ODamageSource odamagesource, int i) {
        this.K();
        return false;
    }

    public boolean L() {
        return false;
    }

    public boolean M() {
        return false;
    }

    public void c(OEntity oentity, int i) {}

    public boolean c(ONBTTagCompound onbttagcompound) {
        String s = this.Q();

        if (!this.L && s != null) {
            onbttagcompound.a("id", s);
            this.d(onbttagcompound);
            return true;
        } else {
            return false;
        }
    }

    public void d(ONBTTagCompound onbttagcompound) {
        onbttagcompound.a("Pos", (ONBTBase) this.a(new double[] { this.t, this.u + (double) this.W, this.v}));
        onbttagcompound.a("Motion", (ONBTBase) this.a(new double[] { this.w, this.x, this.y}));
        onbttagcompound.a("Rotation", (ONBTBase) this.a(new float[] { this.z, this.A}));
        onbttagcompound.a("FallDistance", this.S);
        onbttagcompound.a("Fire", (short) this.d);
        onbttagcompound.a("Air", (short) this.al());
        onbttagcompound.a("OnGround", this.E);
        onbttagcompound.a("Dimension", this.ap);
        this.b(onbttagcompound);
    }

    public void e(ONBTTagCompound onbttagcompound) {
        ONBTTagList onbttaglist = onbttagcompound.m("Pos");
        ONBTTagList onbttaglist1 = onbttagcompound.m("Motion");
        ONBTTagList onbttaglist2 = onbttagcompound.m("Rotation");

        this.w = ((ONBTTagDouble) onbttaglist1.b(0)).a;
        this.x = ((ONBTTagDouble) onbttaglist1.b(1)).a;
        this.y = ((ONBTTagDouble) onbttaglist1.b(2)).a;
        if (Math.abs(this.w) > 10.0D) {
            this.w = 0.0D;
        }

        if (Math.abs(this.x) > 10.0D) {
            this.x = 0.0D;
        }

        if (Math.abs(this.y) > 10.0D) {
            this.y = 0.0D;
        }

        this.q = this.T = this.t = ((ONBTTagDouble) onbttaglist.b(0)).a;
        this.r = this.U = this.u = ((ONBTTagDouble) onbttaglist.b(1)).a;
        this.s = this.V = this.v = ((ONBTTagDouble) onbttaglist.b(2)).a;
        this.B = this.z = ((ONBTTagFloat) onbttaglist2.b(0)).a;
        this.C = this.A = ((ONBTTagFloat) onbttaglist2.b(1)).a;
        this.S = onbttagcompound.g("FallDistance");
        this.d = onbttagcompound.d("Fire");
        this.f(onbttagcompound.d("Air"));
        this.E = onbttagcompound.n("OnGround");
        this.ap = onbttagcompound.e("Dimension");
        this.b(this.t, this.u, this.v);
        this.b(this.z, this.A);
        this.a(onbttagcompound);
    }

    protected final String Q() {
        return OEntityList.b(this);
    }

    protected abstract void a(ONBTTagCompound onbttagcompound);

    protected abstract void b(ONBTTagCompound onbttagcompound);

    protected ONBTTagList a(double... adouble) {
        ONBTTagList onbttaglist = new ONBTTagList();
        double[] adouble1 = adouble;
        int i = adouble.length;

        for (int j = 0; j < i; ++j) {
            double d0 = adouble1[j];

            onbttaglist.a((ONBTBase) (new ONBTTagDouble((String) null, d0)));
        }

        return onbttaglist;
    }

    protected ONBTTagList a(float... afloat) {
        ONBTTagList onbttaglist = new ONBTTagList();
        float[] afloat1 = afloat;
        int i = afloat.length;

        for (int j = 0; j < i; ++j) {
            float f = afloat1[j];

            onbttaglist.a((ONBTBase) (new ONBTTagFloat((String) null, f)));
        }

        return onbttaglist;
    }

    public OEntityItem b(int i, int j) {
        return this.a(i, j, 0.0F);
    }

    public OEntityItem a(int i, int j, float f) {
        return this.a(new OItemStack(i, j, 0), f);
    }

    public OEntityItem a(OItemStack oitemstack, float f) {
        OEntityItem oentityitem = new OEntityItem(this.p, this.t, this.u + (double) f, this.v, oitemstack);

        oentityitem.c = 10;
        this.p.d((OEntity) oentityitem);
        return oentityitem;
    }

    public boolean S() {
        return !this.L;
    }

    public boolean T() {
        for (int i = 0; i < 8; ++i) {
            float f = ((float) ((i >> 0) % 2) - 0.5F) * this.N * 0.8F;
            float f1 = ((float) ((i >> 1) % 2) - 0.5F) * 0.1F;
            float f2 = ((float) ((i >> 2) % 2) - 0.5F) * this.N * 0.8F;
            int j = OMathHelper.c(this.t + (double) f);
            int k = OMathHelper.c(this.u + (double) this.e() + (double) f1);
            int l = OMathHelper.c(this.v + (double) f2);

            if (this.p.s(j, k, l)) {
                return true;
            }
        }

        return false;
    }

    public boolean c(OEntityPlayer oentityplayer) {
        return false;
    }

    public OAxisAlignedBB g(OEntity oentity) {
        return null;
    }

    public void U() {
        if (this.o.L) {
            this.o = null;
        } else {
            this.w = 0.0D;
            this.x = 0.0D;
            this.y = 0.0D;
            this.j_();
            if (this.o != null) {
                this.o.V();
                this.g += (double) (this.o.z - this.o.B);

                for (this.f += (double) (this.o.A - this.o.C); this.g >= 180.0D; this.g -= 360.0D) {
                    ;
                }

                while (this.g < -180.0D) {
                    this.g += 360.0D;
                }

                while (this.f >= 180.0D) {
                    this.f -= 360.0D;
                }

                while (this.f < -180.0D) {
                    this.f += 360.0D;
                }

                double d0 = this.g * 0.5D;
                double d1 = this.f * 0.5D;
                float f = 10.0F;

                if (d0 > (double) f) {
                    d0 = (double) f;
                }

                if (d0 < (double) (-f)) {
                    d0 = (double) (-f);
                }

                if (d1 > (double) f) {
                    d1 = (double) f;
                }

                if (d1 < (double) (-f)) {
                    d1 = (double) (-f);
                }

                this.g -= d0;
                this.f -= d1;
                this.z = (float) ((double) this.z + d0);
                this.A = (float) ((double) this.A + d1);
            }
        }
    }

    public void V() {
        if (!(this.n instanceof OEntityPlayer) || !((OEntityPlayer) this.n).bS()) {
            this.n.T = this.T;
            this.n.U = this.U + this.X() + this.n.W();
            this.n.V = this.V;
        }

        this.n.b(this.t, this.u + this.X() + this.n.W(), this.v);
    }

    public double W() {
        return (double) this.M;
    }

    public double X() {
        return (double) this.O * 0.75D;
    }

    public void a(OEntity oentity) {
        this.f = 0.0D;
        this.g = 0.0D;
        if (oentity == null) {
            if (this.o != null) {
                this.b(this.o.t, this.o.D.b + (double) this.o.O, this.o.v, this.z, this.A);
                this.o.n = null;
            }

            this.o = null;
        } else if (this.o == oentity) {
            this.h(oentity);
            this.o.n = null;
            this.o = null;
        } else {
            if (this.o != null) {
                this.o.n = null;
            }

            if (oentity.n != null) {
                oentity.n.o = null;
            }

            this.o = oentity;
            oentity.n = this;
        }
    }

    public void h(OEntity oentity) {
        double d0 = oentity.t;
        double d1 = oentity.D.b + (double) oentity.O;
        double d2 = oentity.v;

        for (double d3 = -1.5D; d3 < 2.0D; ++d3) {
            for (double d4 = -1.5D; d4 < 2.0D; ++d4) {
                if (d3 != 0.0D || d4 != 0.0D) {
                    int i = (int) (this.t + d3);
                    int j = (int) (this.v + d4);
                    OAxisAlignedBB oaxisalignedbb = this.D.c(d3, 1.0D, d4);

                    if (this.p.a(oaxisalignedbb).isEmpty()) {
                        if (this.p.t(i, (int) this.u, j)) {
                            this.b(this.t + d3, this.u + 1.0D, this.v + d4, this.z, this.A);
                            return;
                        }

                        if (this.p.t(i, (int) this.u - 1, j) || this.p.f(i, (int) this.u - 1, j) == OMaterial.h) {
                            d0 = this.t + d3;
                            d1 = this.u + 1.0D;
                            d2 = this.v + d4;
                        }
                    }
                }
            }
        }

        this.b(d0, d1, d2, this.z, this.A);
    }

    public float Y() {
        return 0.1F;
    }

    public OVec3 Z() {
        return null;
    }

    public void aa() {
        if (this.an > 0) {
            this.an = this.ab();
        } else {
            double d0 = this.q - this.t;
            double d1 = this.s - this.v;

            if (!this.p.J && !this.ao) {
                this.aq = ODirection.a(d0, d1);
    }

            this.ao = true;
    }
    }

    public int ab() {
        return 500;
    }

    public OItemStack[] ae() {
        return null;
    }

    public void b(int i, OItemStack oitemstack) {}

    public boolean af() {
        return this.d > 0 || this.e(0);
    }

    public boolean ag() {
        return this.o != null || this.e(2);
    }

    public boolean ah() {
        return this.e(1);
    }

    public void a(boolean flag) {
        this.a(1, flag);
    }

    public boolean ai() {
        return this.e(3);
    }

    public void b(boolean flag) {
        this.a(3, flag);
    }

    public boolean aj() {
        return this.e(5);
    }

    public void c(boolean flag) {
        this.a(5, flag);
    }

    public void d(boolean flag) {
        this.a(4, flag);
    }

    protected boolean e(int i) {
        return (this.ag.a(0) & 1 << i) != 0;
    }

    protected void a(int i, boolean flag) {
        byte b0 = this.ag.a(0);

        if (flag) {
            this.ag.b(0, Byte.valueOf((byte) (b0 | 1 << i)));
        } else {
            this.ag.b(0, Byte.valueOf((byte) (b0 & ~(1 << i))));
        }

    }

    public int al() {
        return this.ag.b(1);
    }

    public void f(int i) {
        this.ag.b(1, Short.valueOf((short) i));
    }

    public void a(OEntityLightningBolt oentitylightningbolt) {
        // CanaryMod Damage Hook: Lightning
        if ((Boolean) manager.callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.LIGHTNING, null, entity, 5)) {
            return;
        }
        this.d(5);
        ++this.d;
        if (this.d == 0) {
            this.c(8);
        }

    }

    public void a(OEntityLiving oentityliving) {}

    protected boolean i(double d0, double d1, double d2) {
        int i = OMathHelper.c(d0);
        int j = OMathHelper.c(d1);
        int k = OMathHelper.c(d2);
        double d3 = d0 - (double) i;
        double d4 = d1 - (double) j;
        double d5 = d2 - (double) k;

        if (this.p.s(i, j, k)) {
            boolean flag = !this.p.s(i - 1, j, k);
            boolean flag1 = !this.p.s(i + 1, j, k);
            boolean flag2 = !this.p.s(i, j - 1, k);
            boolean flag3 = !this.p.s(i, j + 1, k);
            boolean flag4 = !this.p.s(i, j, k - 1);
            boolean flag5 = !this.p.s(i, j, k + 1);
            byte b0 = -1;
            double d6 = 9999.0D;

            if (flag && d3 < d6) {
                d6 = d3;
                b0 = 0;
            }

            if (flag1 && 1.0D - d3 < d6) {
                d6 = 1.0D - d3;
                b0 = 1;
            }

            if (flag2 && d4 < d6) {
                d6 = d4;
                b0 = 2;
            }

            if (flag3 && 1.0D - d4 < d6) {
                d6 = 1.0D - d4;
                b0 = 3;
            }

            if (flag4 && d5 < d6) {
                d6 = d5;
                b0 = 4;
            }

            if (flag5 && 1.0D - d5 < d6) {
                d6 = 1.0D - d5;
                b0 = 5;
            }

            float f = this.aa.nextFloat() * 0.2F + 0.1F;

            if (b0 == 0) {
                this.w = (double) (-f);
            }

            if (b0 == 1) {
                this.w = (double) f;
            }

            if (b0 == 2) {
                this.x = (double) (-f);
            }

            if (b0 == 3) {
                this.x = (double) f;
            }

            if (b0 == 4) {
                this.y = (double) (-f);
            }

            if (b0 == 5) {
                this.y = (double) f;
            }

            return true;
        } else {
            return false;
        }
    }

    public void am() {
        this.J = true;
        this.S = 0.0F;
    }

    public String an() {
        String s = OEntityList.b(this);

        if (s == null) {
            s = "generic";
        }

        return OStatCollector.a("entity." + s + ".name");
    }

    public OEntity[] ao() {
        return null;
    }

    public boolean i(OEntity oentity) {
        return this == oentity;
    }

    public float ap() {
        return 0.0F;
    }

    public boolean aq() {
        return true;
    }

    public String toString() {
        return String.format("%s[\'%s\'/%d, l=\'%s\', x=%.2f, y=%.2f, z=%.2f]", new Object[] { this.getClass().getSimpleName(), this.an(), Integer.valueOf(this.k), this.p == null ? "~NULL~" : this.p.J().k(), Double.valueOf(this.t), Double.valueOf(this.u), Double.valueOf(this.v)});
    }

    public void j(OEntity oentity) {
        this.b(oentity.t, oentity.u, oentity.v, oentity.z, oentity.A);
    }

    public void a(OEntity oentity, boolean flag) {
        ONBTTagCompound onbttagcompound = new ONBTTagCompound();

        oentity.d(onbttagcompound);
        this.e(onbttagcompound);
        this.an = oentity.an;
        this.aq = oentity.aq;
    }

    public void b(int i) {
        if (!this.p.J && !this.L) {
            OMinecraftServer ominecraftserver = OMinecraftServer.D();
            int j = this.ap;
            OWorldServer oworldserver = ominecraftserver.a(j);
            OWorldServer oworldserver1 = ominecraftserver.a(i);

            this.ap = i;
            this.p.e(this);
            this.L = false;
            ominecraftserver.ad().a(this, j, oworldserver, oworldserver1);
            OEntity oentity = OEntityList.a(OEntityList.b(this), oworldserver1);

            if (oentity != null) {
                oentity.a(this, true);
                oworldserver1.d(oentity);
            }

            this.L = true;
            oworldserver.i();
            oworldserver1.i();
        }
    }

    public float a(OExplosion oexplosion, OBlock oblock, int i, int j, int k) {
        return oblock.a(this);
    }

    public int as() {
        return 3;
    }

    public int at() {
        return this.aq;
    }

    public boolean au() {
        return false;
    }
}
