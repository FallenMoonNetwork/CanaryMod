import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.Callable;

public abstract class OEntity {

    private static int b;
    public int k;
    public double l;
    public boolean m;
    public OEntity n;
    public OEntity o;
    public boolean p;
    public OWorld q;
    public double r;
    public double s;
    public double t;
    public double u;
    public double v;
    public double w;
    public double x;
    public double y;
    public double z;
    public float A;
    public float B;
    public float C;
    public float D;
    public final OAxisAlignedBB E;
    public boolean F;
    public boolean G;
    public boolean H;
    public boolean I;
    public boolean J;
    protected boolean K;
    public boolean L;
    public boolean M; //CanaryMod: isdead variable
    public float N;
    public float O;
    public float P;
    public float Q;
    public float R;
    public float S;
    public float T;
    private int c;
    public double U;
    public double V;
    public double W;
    public float X;
    public float Y;
    public boolean Z;
    public float aa;
    protected Random ab;
    public int ac;
    public int ad;
    protected int d;// CanaryMod: private -> protected
    protected boolean ae;
    public int af;
    private boolean e;
    protected boolean ag;
    protected ODataWatcher ah;
    private double f;
    private double g;
    public boolean ai;
    public int aj;
    public int ak;
    public int al;
    public boolean am;
    public boolean an;
    public int ao;
    protected boolean ap;
    protected int aq;
    public int ar;
    protected int as;
    protected boolean h; // CanaryMod: private -> protected
    private UUID i;
    public OEnumEntitySize at;

    // CanaryMod Start
    @Deprecated
    BaseEntity entity = new BaseEntity(this);
    public static PluginLoader manager = etc.getLoader();
    NBTTagCompound metadata;
    MobSpawnerLogic spawner;
    // CanaryMod end

    public OEntity(OWorld oworld) {
        this.k = b++;
        this.l = 1.0D;
        this.E = OAxisAlignedBB.a(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
        this.L = true;
        this.O = 0.6F;
        this.P = 1.8F;
        this.c = 1;
        this.ab = new Random();
        this.ad = 1;
        this.e = true;
        this.ah = new ODataWatcher();
        this.i = UUID.randomUUID();
        this.at = OEnumEntitySize.b;
        this.q = oworld;
        this.b(0.0D, 0.0D, 0.0D);
        if (oworld != null) {
            this.ar = oworld.t.i;
        }

        this.ah.a(0, Byte.valueOf((byte) 0));
        this.ah.a(1, Short.valueOf((short) 300));
        metadata = new NBTTagCompound("Canary"); // CanaryMod
        this.a();
    }

    protected abstract void a();

    public ODataWatcher v() {
        return this.ah;
    }

    public boolean equals(Object object) {
        return object instanceof OEntity ? ((OEntity) object).k == this.k : false;
    }

    public int hashCode() {
        return this.k;
    }

    public void x() {
        // CanaryMod: ENTITY_DESTROYED hook
        etc.getLoader().callHook(PluginLoader.Hook.ENTITY_DESTROYED, this.getEntity());
        // CanaryMod: end
        this.M = true;
    }

    protected void a(float f, float f1) {
        float f2;

        if (f != this.O || f1 != this.P) {
            f2 = this.O;
            this.O = f;
            this.P = f1;
            this.E.d = this.E.a + (double) this.O;
            this.E.f = this.E.c + (double) this.O;
            this.E.e = this.E.b + (double) this.P;
            if (this.O > f2 && !this.e && !this.q.I) {
                this.d((double) (f2 - this.O), 0.0D, (double) (f2 - this.O));
            }
        }

        f2 = f % 2.0F;
        if ((double) f2 < 0.375D) {
            this.at = OEnumEntitySize.a;
        } else if ((double) f2 < 0.75D) {
            this.at = OEnumEntitySize.b;
        } else if ((double) f2 < 1.0D) {
            this.at = OEnumEntitySize.c;
        } else if ((double) f2 < 1.375D) {
            this.at = OEnumEntitySize.d;
        } else if ((double) f2 < 1.75D) {
            this.at = OEnumEntitySize.e;
        } else {
            this.at = OEnumEntitySize.f;
        }
    }

    protected void b(float f, float f1) {
        this.A = f % 360.0F;
        this.B = f1 % 360.0F;
    }

    public void b(double d0, double d1, double d2) {
        this.u = d0;
        this.v = d1;
        this.w = d2;
        float f = this.O / 2.0F;
        float f1 = this.P;

        this.E.b(d0 - (double) f, d1 - (double) this.N + (double) this.X, d2 - (double) f, d0 + (double) f, d1 - (double) this.N + (double) this.X + (double) f1, d2 + (double) f);
    }

    public void l_() {
        this.y();
    }

    public void y() {
        this.q.C.a("entityBaseTick");
        if (this.o != null && this.o.M) {
            this.o = null;
        }

        this.Q = this.R;
        this.r = this.u;
        this.s = this.v;
        this.t = this.w;
        this.D = this.B;
        this.C = this.A;
        int i;

        if (!this.q.I && this.q instanceof OWorldServer) {
            this.q.C.a("portal");
            OMinecraftServer ominecraftserver = ((OWorldServer) this.q).p();

            i = this.z();
            if (this.ap) {
                if (ominecraftserver.u()) {
                    if (this.o == null && this.aq++ >= i) {
                        this.aq = i;
                        this.ao = this.ac();
                        byte b0;

                        if (this.q.t.i == -1) {
                            b0 = 0;
                        } else {
                            b0 = -1;
                        }

                        this.b(b0);
                    }

                    this.ap = false;
                }
            } else {
                if (this.aq > 0) {
                    this.aq -= 4;
                }

                if (this.aq < 0) {
                    this.aq = 0;
                }
            }

            if (this.ao > 0) {
                --this.ao;
            }

            this.q.C.b();
        }

        if (this.ai() && !this.H()) {
            int j = OMathHelper.c(this.u);

            i = OMathHelper.c(this.v - 0.20000000298023224D - (double) this.N);
            int k = OMathHelper.c(this.w);
            int l = this.q.a(j, i, k);

            if (l > 0) {
                this.q.a("tilecrack_" + l + "_" + this.q.h(j, i, k), this.u + ((double) this.ab.nextFloat() - 0.5D) * (double) this.O, this.E.b + 0.1D, this.w + ((double) this.ab.nextFloat() - 0.5D) * (double) this.O, -this.x * 4.0D, 1.5D, -this.z * 4.0D);
            }
        }

        this.I();
        if (this.q.I) {
            this.d = 0;
        } else if (this.d > 0) {
            if (this.ag) {
                this.d -= 4;
                if (this.d < 0) {
                    this.d = 0;
                }
            } else {
                if (this.d % 20 == 0) {
                    // CanaryMod Damage hook: Periodic burn damage
                    HookParametersDamage ev = (HookParametersDamage) etc.getLoader().callHook(PluginLoader.Hook.DAMAGE, new HookParametersDamage(null, this.getEntity(), DamageType.FIRE_TICK.getDamageSource(), 1.0F));
                    if (!ev.isCanceled()) {
                        this.a(ev.getDamageSource().getDamageSource(), ev.getDamageAmountFloat());
                    }
                }

                --this.d;
            }
        }

        if (this.J()) {
            this.A();
            this.T *= 0.5F;
        }

        if (this.v < -64.0D) {
            this.C();
        }

        if (!this.q.I) {
            this.a(0, this.d > 0);
        }

        this.e = false;
        this.q.C.b();
    }

    public int z() {
        return 0;
    }

    protected void A() {
        if (!this.ag) {
            // CanaryMod Damage hook: Lava
            HookParametersDamage ev = null;
            if (this instanceof OEntityLiving) {
                ev = (HookParametersDamage) etc.getLoader().callHook(PluginLoader.Hook.DAMAGE, new HookParametersDamage(null, this.getEntity(), DamageType.LAVA.getDamageSource(), 4.0F));
                if (ev.isCanceled()) {
                    return;
                }
            }

            if (ev == null) {
                //No hook has been fired, continue the standard procedure
                this.a(ODamageSource.c, 4.0F);
                this.d(15);
            } else {
                //Hook was fired, apply its data
                this.a(ev.getDamageSource().getDamageSource(), ev.getDamageAmountFloat());
                this.d(15);
            }
        }
    }

    public void d(int i) {
        int j = i * 20;

        j = OEnchantmentProtection.a(this, j);
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
        OAxisAlignedBB oaxisalignedbb = this.E.c(d0, d1, d2);
        List list = this.q.a(this, oaxisalignedbb);

        return !list.isEmpty() ? false : !this.q.d(oaxisalignedbb);
    }

    public void d(double d0, double d1, double d2) {
        if (this.Z) {
            this.E.d(d0, d1, d2);
            this.u = (this.E.a + this.E.d) / 2.0D;
            this.v = this.E.b + (double) this.N - (double) this.X;
            this.w = (this.E.c + this.E.f) / 2.0D;
        } else {
            this.q.C.a("move");
            this.X *= 0.4F;
            double d3 = this.u;
            double d4 = this.v;
            double d5 = this.w;

            if (this.K) {
                this.K = false;
                d0 *= 0.25D;
                d1 *= 0.05000000074505806D;
                d2 *= 0.25D;
                this.x = 0.0D;
                this.y = 0.0D;
                this.z = 0.0D;
            }

            double d6 = d0;
            double d7 = d1;
            double d8 = d2;
            OAxisAlignedBB oaxisalignedbb = this.E.c();
            boolean flag = this.F && this.ah() && this instanceof OEntityPlayer;

            if (flag) {
                double d9;

                for (d9 = 0.05D; d0 != 0.0D && this.q.a(this, this.E.c(d0, -1.0D, 0.0D)).isEmpty(); d6 = d0) {
                    if (d0 < d9 && d0 >= -d9) {
                        d0 = 0.0D;
                    } else if (d0 > 0.0D) {
                        d0 -= d9;
                    } else {
                        d0 += d9;
                    }
                }

                for (; d2 != 0.0D && this.q.a(this, this.E.c(0.0D, -1.0D, d2)).isEmpty(); d8 = d2) {
                    if (d2 < d9 && d2 >= -d9) {
                        d2 = 0.0D;
                    } else if (d2 > 0.0D) {
                        d2 -= d9;
                    } else {
                        d2 += d9;
                    }
                }

                while (d0 != 0.0D && d2 != 0.0D && this.q.a(this, this.E.c(d0, -1.0D, d2)).isEmpty()) {
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

            List list = this.q.a(this, this.E.a(d0, d1, d2));

            for (int i = 0; i < list.size(); ++i) {
                d1 = ((OAxisAlignedBB) list.get(i)).b(this.E, d1);
            }

            this.E.d(0.0D, d1, 0.0D);
            if (!this.L && d7 != d1) {
                d2 = 0.0D;
                d1 = 0.0D;
                d0 = 0.0D;
            }

            boolean flag1 = this.F || d7 != d1 && d7 < 0.0D;

            int j;

            for (j = 0; j < list.size(); ++j) {
                d0 = ((OAxisAlignedBB) list.get(j)).a(this.E, d0);
            }

            this.E.d(d0, 0.0D, 0.0D);
            if (!this.L && d6 != d0) {
                d2 = 0.0D;
                d1 = 0.0D;
                d0 = 0.0D;
            }

            for (j = 0; j < list.size(); ++j) {
                d2 = ((OAxisAlignedBB) list.get(j)).c(this.E, d2);
            }

            this.E.d(0.0D, 0.0D, d2);
            if (!this.L && d8 != d2) {
                d2 = 0.0D;
                d1 = 0.0D;
                d0 = 0.0D;
            }

            double d10;
            double d11;
            double d12;
            int k;

            if (this.Y > 0.0F && flag1 && (flag || this.X < 0.05F) && (d6 != d0 || d8 != d2)) {
                d10 = d0;
                d11 = d1;
                d12 = d2;
                d0 = d6;
                d1 = (double) this.Y;
                d2 = d8;
                OAxisAlignedBB oaxisalignedbb1 = this.E.c();

                this.E.d(oaxisalignedbb);
                list = this.q.a(this, this.E.a(d6, d1, d8));

                for (k = 0; k < list.size(); ++k) {
                    d1 = ((OAxisAlignedBB) list.get(k)).b(this.E, d1);
                }

                this.E.d(0.0D, d1, 0.0D);
                if (!this.L && d7 != d1) {
                    d2 = 0.0D;
                    d1 = 0.0D;
                    d0 = 0.0D;
                }

                for (k = 0; k < list.size(); ++k) {
                    d0 = ((OAxisAlignedBB) list.get(k)).a(this.E, d0);
                }

                this.E.d(d0, 0.0D, 0.0D);
                if (!this.L && d6 != d0) {
                    d2 = 0.0D;
                    d1 = 0.0D;
                    d0 = 0.0D;
                }

                for (k = 0; k < list.size(); ++k) {
                    d2 = ((OAxisAlignedBB) list.get(k)).c(this.E, d2);
                }

                this.E.d(0.0D, 0.0D, d2);
                if (!this.L && d8 != d2) {
                    d2 = 0.0D;
                    d1 = 0.0D;
                    d0 = 0.0D;
                }

                if (!this.L && d7 != d1) {
                    d2 = 0.0D;
                    d1 = 0.0D;
                    d0 = 0.0D;
                } else {
                    d1 = (double) (-this.Y);

                    for (k = 0; k < list.size(); ++k) {
                        d1 = ((OAxisAlignedBB) list.get(k)).b(this.E, d1);
                    }

                    this.E.d(0.0D, d1, 0.0D);
                }

                if (d10 * d10 + d12 * d12 >= d0 * d0 + d2 * d2) {
                    d0 = d10;
                    d1 = d11;
                    d2 = d12;
                    this.E.d(oaxisalignedbb1);
                }
            }

            this.q.C.b();
            this.q.C.a("rest");
            this.u = (this.E.a + this.E.d) / 2.0D;
            this.v = this.E.b + (double) this.N - (double) this.X;
            this.w = (this.E.c + this.E.f) / 2.0D;
            this.G = d6 != d0 || d8 != d2;
            this.H = d7 != d1;
            this.F = d7 != d1 && d7 < 0.0D;
            this.I = this.G || this.H;
            this.a(d1, this.F);
            if (d6 != d0) {
                this.x = 0.0D;
            }

            if (d7 != d1) {
                this.y = 0.0D;
            }

            if (d8 != d2) {
                this.z = 0.0D;
            }

            d10 = this.u - d3;
            d11 = this.v - d4;
            d12 = this.w - d5;
            if (this.e_() && !flag && this.o == null) {
                int l = OMathHelper.c(this.u);

                k = OMathHelper.c(this.v - 0.20000000298023224D - (double) this.N);
                int i1 = OMathHelper.c(this.w);
                int j1 = this.q.a(l, k, i1);

                if (j1 == 0) {
                    int k1 = this.q.e(l, k - 1, i1);

                    if (k1 == 11 || k1 == 32 || k1 == 21) {
                        j1 = this.q.a(l, k - 1, i1);
                    }
                }

                if (j1 != OBlock.aK.cF) {
                    d11 = 0.0D;
                }

                this.R = (float) ((double) this.R + (double) OMathHelper.a(d10 * d10 + d12 * d12) * 0.6D);
                this.S = (float) ((double) this.S + (double) OMathHelper.a(d10 * d10 + d11 * d11 + d12 * d12) * 0.6D);
                if (this.S > (float) this.c && j1 > 0) {
                    this.c = (int) this.S + 1;
                    if (this.H()) {
                        float f = OMathHelper.a(this.x * this.x * 0.20000000298023224D + this.y * this.y + this.z * this.z * 0.20000000298023224D) * 0.35F;

                        if (f > 1.0F) {
                            f = 1.0F;
                        }

                        this.a("liquid.swim", f, 1.0F + (this.ab.nextFloat() - this.ab.nextFloat()) * 0.4F);
                    }

                    this.a(l, k, i1, j1);
                    OBlock.s[j1].b(this.q, l, k, i1, this);
                }
            }

            try {
                this.D();
            } catch (Throwable throwable) {
                OCrashReport ocrashreport = OCrashReport.a(throwable, "Checking entity tile collision");
                OCrashReportCategory ocrashreportcategory = ocrashreport.a("Entity being checked for collision");

                this.a(ocrashreportcategory);
                throw new OReportedException(ocrashreport);
            }

            boolean flag2 = this.G();

            if (this.q.e(this.E.e(0.001D, 0.001D, 0.001D))) {
                this.e(1);
                if (!flag2) {
                    ++this.d;
                    if (this.d == 0) {
                        this.d(8);
                    }
                }
            } else if (this.d <= 0) {
                this.d = -this.ad;
            }

            if (flag2 && this.d > 0) {
                this.a("random.fizz", 0.7F, 1.6F + (this.ab.nextFloat() - this.ab.nextFloat()) * 0.4F);
                this.d = -this.ad;
            }

            this.q.C.b();
        }
    }

    protected void D() {
        int i = OMathHelper.c(this.E.a + 0.001D);
        int j = OMathHelper.c(this.E.b + 0.001D);
        int k = OMathHelper.c(this.E.c + 0.001D);
        int l = OMathHelper.c(this.E.d - 0.001D);
        int i1 = OMathHelper.c(this.E.e - 0.001D);
        int j1 = OMathHelper.c(this.E.f - 0.001D);

        if (this.q.e(i, j, k, l, i1, j1)) {
            for (int k1 = i; k1 <= l; ++k1) {
                for (int l1 = j; l1 <= i1; ++l1) {
                    for (int i2 = k; i2 <= j1; ++i2) {
                        int j2 = this.q.a(k1, l1, i2);

                        if (j2 > 0) {
                            try {
                                OBlock.s[j2].a(this.q, k1, l1, i2, this);
                            } catch (Throwable throwable) {
                                OCrashReport ocrashreport = OCrashReport.a(throwable, "Colliding entity with tile");
                                OCrashReportCategory ocrashreportcategory = ocrashreport.a("Tile being collided with");

                                OCrashReportCategory.a(ocrashreportcategory, k1, l1, i2, j2, this.q.h(k1, l1, i2));
                                throw new OReportedException(ocrashreport);
                            }
                        }
                    }
                }
            }
        }
    }

    protected void a(int i, int j, int k, int l) {
        OStepSound ostepsound = OBlock.s[l].cS;

        if (this.q.a(i, j + 1, k) == OBlock.aX.cF) {
            ostepsound = OBlock.aX.cS;
            this.a(ostepsound.e(), ostepsound.c() * 0.15F, ostepsound.d());
        } else if (!OBlock.s[l].cU.d()) {
            this.a(ostepsound.e(), ostepsound.c() * 0.15F, ostepsound.d());
        }
    }

    public void a(String s, float f, float f1) {
        this.q.a(this, s, f, f1);
    }

    protected boolean e_() {
        return true;
    }

    protected void a(double d0, boolean flag) {
        if (flag) {
            if (this.T > 0.0F) {
                this.b(this.T);
                this.T = 0.0F;
            }
        } else if (d0 < 0.0D) {
            this.T = (float) ((double) this.T - d0);
        }
    }

    public OAxisAlignedBB E() {
        return null;
    }

    protected void e(int i) {
        if (!this.ag) {
            // CanaryMod Damage Hook: Fire
            HookParametersDamage ev = (HookParametersDamage) etc.getLoader().callHook(PluginLoader.Hook.DAMAGE, new HookParametersDamage(null, this.getEntity(), DamageType.FIRE.getDamageSource(), (float) i));
            if (!ev.isCanceled()) {
                this.a(ev.getDamageSource().getDamageSource(), ev.getDamageAmountFloat());
            }//
        }
    }

    public final boolean F() {
        return this.ag;
    }

    protected void b(float f) {
        if (this.n != null) {
            this.n.b(f);
        }
    }

    public boolean G() {
        return this.ae || this.q.F(OMathHelper.c(this.u), OMathHelper.c(this.v), OMathHelper.c(this.w)) || this.q.F(OMathHelper.c(this.u), OMathHelper.c(this.v + (double) this.P), OMathHelper.c(this.w));
    }

    public boolean H() {
        return this.ae;
    }

    public boolean I() {
        if (this.q.a(this.E.b(0.0D, -0.4000000059604645D, 0.0D).e(0.001D, 0.001D, 0.001D), OMaterial.h, this)) {
            if (!this.ae && !this.e) {
                float f = OMathHelper.a(this.x * this.x * 0.20000000298023224D + this.y * this.y + this.z * this.z * 0.20000000298023224D) * 0.2F;

                if (f > 1.0F) {
                    f = 1.0F;
                }

                this.a("liquid.splash", f, 1.0F + (this.ab.nextFloat() - this.ab.nextFloat()) * 0.4F);
                float f1 = (float) OMathHelper.c(this.E.b);

                int i;
                float f2;
                float f3;

                for (i = 0; (float) i < 1.0F + this.O * 20.0F; ++i) {
                    f2 = (this.ab.nextFloat() * 2.0F - 1.0F) * this.O;
                    f3 = (this.ab.nextFloat() * 2.0F - 1.0F) * this.O;
                    this.q.a("bubble", this.u + (double) f2, (double) (f1 + 1.0F), this.w + (double) f3, this.x, this.y - (double) (this.ab.nextFloat() * 0.2F), this.z);
                }

                for (i = 0; (float) i < 1.0F + this.O * 20.0F; ++i) {
                    f2 = (this.ab.nextFloat() * 2.0F - 1.0F) * this.O;
                    f3 = (this.ab.nextFloat() * 2.0F - 1.0F) * this.O;
                    this.q.a("splash", this.u + (double) f2, (double) (f1 + 1.0F), this.w + (double) f3, this.x, this.y, this.z);
                }
            }

            this.T = 0.0F;
            this.ae = true;
            this.d = 0;
        } else {
            this.ae = false;
        }

        return this.ae;
    }

    public boolean a(OMaterial omaterial) {
        double d0 = this.v + (double) this.f();
        int i = OMathHelper.c(this.u);
        int j = OMathHelper.d((float) OMathHelper.c(d0));
        int k = OMathHelper.c(this.w);
        int l = this.q.a(i, j, k);

        if (l != 0 && OBlock.s[l].cU == omaterial) {
            float f = OBlockFluid.d(this.q.h(i, j, k)) - 0.11111111F;
            float f1 = (float) (j + 1) - f;

            return d0 < (double) f1;
        } else {
            return false;
        }
    }

    public float f() {
        return 0.0F;
    }

    public boolean J() {
        return this.q.a(this.E.b(-0.10000000149011612D, -0.4000000059604645D, -0.10000000149011612D), OMaterial.i);
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
            float f4 = OMathHelper.a(this.A * 3.1415927F / 180.0F);
            float f5 = OMathHelper.b(this.A * 3.1415927F / 180.0F);

            this.x += (double) (f * f5 - f1 * f4);
            this.z += (double) (f1 * f5 + f * f4);
        }
    }

    public float d(float f) {
        int i = OMathHelper.c(this.u);
        int j = OMathHelper.c(this.w);

        if (this.q.f(i, 0, j)) {
            double d0 = (this.E.e - this.E.b) * 0.66D;
            int k = OMathHelper.c(this.v - (double) this.N + d0);

            return this.q.q(i, k, j);
        } else {
            return 0.0F;
        }
    }

    public void a(OWorld oworld) {
        this.q = oworld;
    }

    public void a(double d0, double d1, double d2, float f, float f1) {
        this.r = this.u = d0;
        this.s = this.v = d1;
        this.t = this.w = d2;
        this.C = this.A = f;
        this.D = this.B = f1;
        this.X = 0.0F;
        double d3 = (double) (this.C - f);

        if (d3 < -180.0D) {
            this.C += 360.0F;
        }

        if (d3 >= 180.0D) {
            this.C -= 360.0F;
        }

        this.b(this.u, this.v, this.w);
        this.b(f, f1);
    }

    public void b(double d0, double d1, double d2, float f, float f1) {
        this.U = this.r = this.u = d0;
        this.V = this.s = this.v = d1 + (double) this.N;
        this.W = this.t = this.w = d2;
        this.A = f;
        this.B = f1;
        this.b(this.u, this.v, this.w);
    }

    public float d(OEntity oentity) {
        float f = (float) (this.u - oentity.u);
        float f1 = (float) (this.v - oentity.v);
        float f2 = (float) (this.w - oentity.w);

        return OMathHelper.c(f * f + f1 * f1 + f2 * f2);
    }

    public double e(double d0, double d1, double d2) {
        double d3 = this.u - d0;
        double d4 = this.v - d1;
        double d5 = this.w - d2;

        return d3 * d3 + d4 * d4 + d5 * d5;
    }

    public double f(double d0, double d1, double d2) {
        double d3 = this.u - d0;
        double d4 = this.v - d1;
        double d5 = this.w - d2;

        return (double) OMathHelper.a(d3 * d3 + d4 * d4 + d5 * d5);
    }

    public double e(OEntity oentity) {
        double d0 = this.u - oentity.u;
        double d1 = this.v - oentity.v;
        double d2 = this.w - oentity.w;

        return d0 * d0 + d1 * d1 + d2 * d2;
    }

    public void b_(OEntityPlayer oentityplayer) {}

    public void f(OEntity oentity) {
        if (oentity.n != this && oentity.o != this) {
            double d0 = oentity.u - this.u;
            double d1 = oentity.w - this.w;
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
                d0 *= (double) (1.0F - this.aa);
                d1 *= (double) (1.0F - this.aa);
                this.g(-d0, 0.0D, -d1);
                oentity.g(d0, 0.0D, d1);
            }
        }
    }

    public void g(double d0, double d1, double d2) {
        this.x += d0;
        this.y += d1;
        this.z += d2;
        this.an = true;
    }

    protected void K() {
        this.J = true;
    }

    public boolean a(ODamageSource odamagesource, float f) {
        if (this.ar()) {
            return false;
        } else {
            this.K();
            return false;
        }
    }

    public boolean L() {
        return false;
    }

    public boolean M() {
        return false;
    }

    public void b(OEntity oentity, int i) {}

    public boolean c(ONBTTagCompound onbttagcompound) {
        String s = this.Q();

        if (!this.M && s != null) {
            onbttagcompound.a("id", s);
            this.e(onbttagcompound);
            return true;
        } else {
            return false;
        }
    }

    public boolean d(ONBTTagCompound onbttagcompound) {
        String s = this.Q();

        if (!this.M && s != null && this.n == null) {
            onbttagcompound.a("id", s);
            this.e(onbttagcompound);
            return true;
        } else {
            return false;
        }
    }

    public void e(ONBTTagCompound onbttagcompound) {
        try {
            onbttagcompound.a("Pos", (ONBTBase) this.a(new double[] { this.u, this.v + (double) this.X, this.w}));
            onbttagcompound.a("Motion", (ONBTBase) this.a(new double[] { this.x, this.y, this.z}));
            onbttagcompound.a("Rotation", (ONBTBase) this.a(new float[] { this.A, this.B}));
            onbttagcompound.a("FallDistance", this.T);
            onbttagcompound.a("Fire", (short) this.d);
            onbttagcompound.a("Air", (short) this.al());
            onbttagcompound.a("OnGround", this.F);
            onbttagcompound.a("Dimension", this.ar);
            onbttagcompound.a("Invulnerable", this.h);
            onbttagcompound.a("PortalCooldown", this.ao);
            // CanaryMod: allow the saving of persistent metadata
            if (metadata != null) {
                onbttagcompound.a("Canary", metadata.getBaseTag());
            } // CanaryMod end
            onbttagcompound.a("UUIDMost", this.i.getMostSignificantBits());
            onbttagcompound.a("UUIDLeast", this.i.getLeastSignificantBits());

            this.b(onbttagcompound);
            if (this.o != null) {
                ONBTTagCompound onbttagcompound1 = new ONBTTagCompound("Riding");

                if (this.o.c(onbttagcompound1)) {
                    onbttagcompound.a("Riding", (ONBTBase) onbttagcompound1);
                }
            }
        } catch (Throwable throwable) {
            OCrashReport ocrashreport = OCrashReport.a(throwable, "Saving entity NBT");
            OCrashReportCategory ocrashreportcategory = ocrashreport.a("Entity being saved");

            this.a(ocrashreportcategory);
            throw new OReportedException(ocrashreport);
        }
    }

    public void f(ONBTTagCompound onbttagcompound) {
        try {
            ONBTTagList onbttaglist = onbttagcompound.m("Pos");
            ONBTTagList onbttaglist1 = onbttagcompound.m("Motion");
            ONBTTagList onbttaglist2 = onbttagcompound.m("Rotation");

            this.x = ((ONBTTagDouble) onbttaglist1.b(0)).a;
            this.y = ((ONBTTagDouble) onbttaglist1.b(1)).a;
            this.z = ((ONBTTagDouble) onbttaglist1.b(2)).a;
            if (Math.abs(this.x) > 10.0D) {
                this.x = 0.0D;
            }

            if (Math.abs(this.y) > 10.0D) {
                this.y = 0.0D;
            }

            if (Math.abs(this.z) > 10.0D) {
                this.z = 0.0D;
            }

            this.r = this.U = this.u = ((ONBTTagDouble) onbttaglist.b(0)).a;
            this.s = this.V = this.v = ((ONBTTagDouble) onbttaglist.b(1)).a;
            this.t = this.W = this.w = ((ONBTTagDouble) onbttaglist.b(2)).a;
            this.C = this.A = ((ONBTTagFloat) onbttaglist2.b(0)).a;
            this.D = this.B = ((ONBTTagFloat) onbttaglist2.b(1)).a;
            this.T = onbttagcompound.g("FallDistance");
            this.d = onbttagcompound.d("Fire");
            this.g(onbttagcompound.d("Air"));
            this.F = onbttagcompound.n("OnGround");
            this.ar = onbttagcompound.e("Dimension");
            this.h = onbttagcompound.n("Invulnerable");
            this.ao = onbttagcompound.e("PortalCooldown");
            if (onbttagcompound.b("UUIDMost") && onbttagcompound.b("UUIDLeast")) {
                this.i = new UUID(onbttagcompound.f("UUIDMost"), onbttagcompound.f("UUIDLeast"));
            }

            this.b(this.u, this.v, this.w);
            this.b(this.A, this.B);
            this.metadata = onbttagcompound.b("Canary") ? new NBTTagCompound(onbttagcompound.l("Canary")) : new NBTTagCompound("Canary"); //CanaryMod: allow the saving of persistent metadata
            this.a(onbttagcompound);
            if (this.P()) {
                this.b(this.u, this.v, this.w);
            }
        } catch (Throwable throwable) {
            OCrashReport ocrashreport = OCrashReport.a(throwable, "Loading entity NBT");
            OCrashReportCategory ocrashreportcategory = ocrashreport.a("Entity being loaded");

            this.a(ocrashreportcategory);
            throw new OReportedException(ocrashreport);
        }
    }

    protected boolean P() {
        return true;
    }

    protected final String Q() {
        return OEntityList.b(this);
    }

    protected abstract void a(ONBTTagCompound onbttagcompound);

    protected abstract void b(ONBTTagCompound onbttagcompound);

    public void R() {}

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
        if (oitemstack.b == 0) {
            return null;
        } else {
            OEntityItem oentityitem = new OEntityItem(this.q, this.u, this.v + (double) f, this.w, oitemstack);

            oentityitem.b = 10;
            this.q.d((OEntity) oentityitem);
            return oentityitem;
        }
    }

    public boolean T() {
        return !this.M;
    }

    public boolean U() {
        for (int i = 0; i < 8; ++i) {
            float f = ((float) ((i >> 0) % 2) - 0.5F) * this.O * 0.8F;
            float f1 = ((float) ((i >> 1) % 2) - 0.5F) * 0.1F;
            float f2 = ((float) ((i >> 2) % 2) - 0.5F) * this.O * 0.8F;
            int j = OMathHelper.c(this.u + (double) f);
            int k = OMathHelper.c(this.v + (double) this.f() + (double) f1);
            int l = OMathHelper.c(this.w + (double) f2);

            if (this.q.u(j, k, l)) {
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

    public void V() {
        if (this.o.M) {
            this.o = null;
        } else {
            this.x = 0.0D;
            this.y = 0.0D;
            this.z = 0.0D;
            this.l_();
            if (this.o != null) {
                this.o.W();
                this.g += (double) (this.o.A - this.o.C);

                for (this.f += (double) (this.o.B - this.o.D); this.g >= 180.0D; this.g -= 360.0D) {
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
            }
        }
    }

    public void W() {
        if (this.n != null) {
            this.n.b(this.u, this.v + this.Y() + this.n.X(), this.w);
        }
    }

    public double X() {
        return (double) this.N;
    }

    public double Y() {
        return (double) this.P * 0.75D;
    }

    public void a(OEntity oentity) {
        this.f = 0.0D;
        this.g = 0.0D;
        if (oentity == null) {
            if (this.o != null) {
                this.b(this.o.u, this.o.E.b + (double) this.o.P, this.o.w, this.A, this.B);
                this.o.n = null;
            }

            this.o = null;
        } else {
            if (this.o != null) {
                this.o.n = null;
            }

            this.o = oentity;
            oentity.n = this;
        }
    }

    public float Z() {
        return 0.1F;
    }

    public OVec3 aa() {
        return null;
    }

    public void ab() {
        if (this.ao > 0) {
            this.ao = this.ac();
        } else {
            double d0 = this.r - this.u;
            double d1 = this.t - this.w;

            if (!this.q.I && !this.ap) {
                this.as = ODirection.a(d0, d1);
            }

            this.ap = true;
        }
    }

    public int ac() {
        return 900;
    }

    public OItemStack[] ae() {
        return null;
    }

    public void c(int i, OItemStack oitemstack) {}

    public boolean af() {
        return !this.ag && (this.d > 0 || this.f(0));
    }

    public boolean ag() {
        return this.o != null;
    }

    public boolean ah() {
        return this.f(1);
    }

    public void b(boolean flag) {
        this.a(1, flag);
    }

    public boolean ai() {
        return this.f(3);
    }

    public void c(boolean flag) {
        this.a(3, flag);
    }

    public boolean aj() {
        return this.f(5);
    }

    public void d(boolean flag) {
        this.a(5, flag);
    }

    public void e(boolean flag) {
        this.a(4, flag);
    }

    protected boolean f(int i) {
        return (this.ah.a(0) & 1 << i) != 0;
    }

    protected void a(int i, boolean flag) {
        byte b0 = this.ah.a(0);

        if (flag) {
            this.ah.b(0, Byte.valueOf((byte) (b0 | 1 << i)));
        } else {
            this.ah.b(0, Byte.valueOf((byte) (b0 & ~(1 << i))));
        }
    }

    public int al() {
        return this.ah.b(1);
    }

    public void g(int i) {
        this.ah.b(1, Short.valueOf((short) i));
    }

    public void a(OEntityLightningBolt oentitylightningbolt) {
        //CanaryMod Damage Hook: Lightning
        @SuppressWarnings("deprecation")
        HookParametersDamage ev = (HookParametersDamage) etc.getLoader().callHook(PluginLoader.Hook.DAMAGE, new HookParametersDamage(null, this.getEntity(), DamageType.LIGHTNING.getDamageSource(), 5));
        if (ev.isCanceled()) {
            return;
        }
        //TODO: Apply a changed DamageSource here?
        this.e(ev.getDamageAmount());
        ++this.d;
        if (this.d == 0) {
            this.d(8);
        }
    }

    public void a(OEntityLivingBase oentitylivingbase) {}

    protected boolean i(double d0, double d1, double d2) {
        int i = OMathHelper.c(d0);
        int j = OMathHelper.c(d1);
        int k = OMathHelper.c(d2);
        double d3 = d0 - (double) i;
        double d4 = d1 - (double) j;
        double d5 = d2 - (double) k;
        List list = this.q.a(this.E);

        if (list.isEmpty() && !this.q.v(i, j, k)) {
            return false;
        } else {
            boolean flag = !this.q.v(i - 1, j, k);
            boolean flag1 = !this.q.v(i + 1, j, k);
            boolean flag2 = !this.q.v(i, j - 1, k);
            boolean flag3 = !this.q.v(i, j + 1, k);
            boolean flag4 = !this.q.v(i, j, k - 1);
            boolean flag5 = !this.q.v(i, j, k + 1);
            byte b0 = 3;
            double d6 = 9999.0D;

            if (flag && d3 < d6) {
                d6 = d3;
                b0 = 0;
            }

            if (flag1 && 1.0D - d3 < d6) {
                d6 = 1.0D - d3;
                b0 = 1;
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

            float f = this.ab.nextFloat() * 0.2F + 0.1F;

            if (b0 == 0) {
                this.x = (double) (-f);
            }

            if (b0 == 1) {
                this.x = (double) f;
            }

            if (b0 == 2) {
                this.y = (double) (-f);
            }

            if (b0 == 3) {
                this.y = (double) f;
            }

            if (b0 == 4) {
                this.z = (double) (-f);
            }

            if (b0 == 5) {
                this.z = (double) f;
            }

            return true;
        }
    }

    public void am() {
        this.K = true;
        this.T = 0.0F;
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

    public boolean h(OEntity oentity) {
        return this == oentity;
    }

    public float ap() {
        return 0.0F;
    }

    public boolean aq() {
        return true;
    }

    public boolean i(OEntity oentity) {
        return false;
    }

    public String toString() {
        return String.format("%s[\'%s\'/%d, l=\'%s\', x=%.2f, y=%.2f, z=%.2f]", new Object[] { this.getClass().getSimpleName(), this.an(), Integer.valueOf(this.k), this.q == null ? "~NULL~" : this.q.N().k(), Double.valueOf(this.u), Double.valueOf(this.v), Double.valueOf(this.w)});
    }

    public boolean ar() {
        return this.h;
    }

    public void j(OEntity oentity) {
        this.b(oentity.u, oentity.v, oentity.w, oentity.A, oentity.B);
    }

    public void a(OEntity oentity, boolean flag) {
        ONBTTagCompound onbttagcompound = new ONBTTagCompound();

        oentity.e(onbttagcompound);
        this.f(onbttagcompound);
        this.ao = oentity.ao;
        this.as = oentity.as;
    }

    public void b(int i) {
        if (!this.q.I && !this.M) {
            this.q.C.a("changeDimension");
            OMinecraftServer ominecraftserver = OMinecraftServer.F();
            int j = this.ar;
            OWorldServer oworldserver = ominecraftserver.getWorld(q.name, j);
            OWorldServer oworldserver1 = ominecraftserver.getWorld(q.name, i);

            this.ar = i;
            if (j == 1 && i == 1) {
                oworldserver1 = ominecraftserver.getWorld(q.name, 0);
                this.ar = 0;
            }

            this.q.e(this);
            this.M = false;
            this.q.C.a("reposition");
            ominecraftserver.af().a(this, j, oworldserver, oworldserver1);
            this.q.C.c("reloading");
            OEntity oentity = OEntityList.a(OEntityList.b(this), oworldserver1);

            if (oentity != null) {
                oentity.a(this, true);
                if (j == 1 && i == 1) {
                    OChunkCoordinates ochunkcoordinates = oworldserver1.K();

                    ochunkcoordinates.b = this.q.i(ochunkcoordinates.a, ochunkcoordinates.c);
                    oentity.b((double) ochunkcoordinates.a, (double) ochunkcoordinates.b, (double) ochunkcoordinates.c, oentity.A, oentity.B);
                }

                oworldserver1.d(oentity);
            }

            this.M = true;
            this.q.C.b();
            oworldserver.i();
            oworldserver1.i();
            this.q.C.b();
        }
    }

    public float a(OExplosion oexplosion, OWorld oworld, int i, int j, int k, OBlock oblock) {
        return oblock.a(this);
    }

    public boolean a(OExplosion oexplosion, OWorld oworld, int i, int j, int k, int l, float f) {
        return true;
    }

    public int as() {
        return 3;
    }

    public int at() {
        return this.as;
    }

    public boolean au() {
        return false;
    }

    public void a(OCrashReportCategory ocrashreportcategory) {
        ocrashreportcategory.a("Entity Type", (Callable) (new OCallableEntityType(this)));
        ocrashreportcategory.a("Entity ID", Integer.valueOf(this.k));
        ocrashreportcategory.a("Entity Name", (Callable) (new OCallableEntityName(this)));
        ocrashreportcategory.a("Entity\'s Exact location", String.format("%.2f, %.2f, %.2f", new Object[] { Double.valueOf(this.u), Double.valueOf(this.v), Double.valueOf(this.w)}));
        ocrashreportcategory.a("Entity\'s Block location", OCrashReportCategory.a(OMathHelper.c(this.u), OMathHelper.c(this.v), OMathHelper.c(this.w)));
        ocrashreportcategory.a("Entity\'s Momentum", String.format("%.2f, %.2f, %.2f", new Object[] { Double.valueOf(this.x), Double.valueOf(this.y), Double.valueOf(this.z)}));
    }

    public UUID aw() {
        return this.i;
    }

    public boolean ax() {
        return true;
    }

    public String ay() {
        return this.an();
    }

    // CanaryMod start: add getEntity
    @SuppressWarnings("deprecation")
    public BaseEntity getEntity() {
        return entity;
    } // CanaryMod end
}
