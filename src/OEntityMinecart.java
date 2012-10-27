import java.util.List;

public class OEntityMinecart extends OEntity implements OIInventory, Container<OItemStack> {

    private OItemStack[] d;
    private int e;
    private boolean f;
    public int a;
    public double b;
    public double c;
    private final OIUpdatePlayerListBox g;
    private boolean h;
    private static final int[][][] i = new int[][][] { { { 0, 0, -1}, { 0, 0, 1}}, { { -1, 0, 0}, { 1, 0, 0}}, { { -1, -1, 0}, { 1, 0, 0}}, { { -1, 0, 0}, { 1, -1, 0}}, { { 0, 0, -1}, { 0, -1, 1}}, { { 0, -1, -1}, { 0, 0, 1}}, { { 0, 0, 1}, { 1, 0, 0}}, { { 0, 0, 1}, { -1, 0, 0}}, { { 0, 0, -1}, { -1, 0, 0}}, { { 0, 0, -1}, { 1, 0, 0}}};
    private int j;
    private double as;
    private double at;
    private double au;
    private double av;
    private double aw;

    // CanaryMod start
    private String name = "container.minecart";
    Minecart cart = new Minecart(this);
    // CanaryMod end

    public OEntityMinecart(OWorld oworld) {
        super(oworld);
        this.d = new OItemStack[36];
        this.e = 0;
        this.f = false;
        this.h = true;
        this.m = true;
        this.a(0.98F, 0.7F);
        this.M = this.O / 2.0F;
        this.g = oworld != null ? oworld.a(this) : null;
    }

    protected boolean f_() {
        return false;
    }

    protected void a() {
        this.ag.a(16, new Byte((byte) 0));
        this.ag.a(17, new Integer(0));
        this.ag.a(18, new Integer(1));
        this.ag.a(19, new Integer(0));
    }

    public OAxisAlignedBB g(OEntity oentity) {
        return oentity.D;
    }

    public OAxisAlignedBB E() {
        return null;
    }

    public boolean M() {
        return true;
    }

    public OEntityMinecart(OWorld oworld, double d0, double d1, double d2, int i) {
        this(oworld);
        this.b(d0, d1 + (double) this.M, d2);
        this.w = 0.0D;
        this.x = 0.0D;
        this.y = 0.0D;
        this.q = d0;
        this.r = d1;
        this.s = d2;
        this.a = i;
        // CanaryMod: Creation of the cart
        manager.callHook(PluginLoader.Hook.VEHICLE_CREATE, cart);
    }

    public double X() {
        return (double) this.O * 0.0D - 0.30000001192092896D;
    }

    public boolean a(ODamageSource odamagesource, int i) {
        // CanaryMod: Attack of the cart
        BaseEntity entity = null;

        if (odamagesource != null && odamagesource.f() != null) {
            entity = new BaseEntity(odamagesource.f());
        }
        if ((Boolean) manager.callHook(PluginLoader.Hook.VEHICLE_DAMAGE, cart, entity, i)) {
            return true;
        }

        if (!this.p.J && !this.L) {

            this.i(-this.k());
            this.h(10);
            this.K();
            this.g(this.i() + i * 10);
            if (odamagesource.g() instanceof OEntityPlayer && ((OEntityPlayer) odamagesource.g()).cf.d) {
                this.g(100);
            }

            if (this.i() > 40) {
                if (this.n != null) {
                    this.n.a((OEntity) this);
                }

                this.x();
                this.a(OItem.az.cf, 1, 0.0F);
                if (this.a == 1) {
                    OEntityMinecart oentityminecart = this;

                    for (int j = 0; j < oentityminecart.k_(); ++j) {
                        OItemStack oitemstack = oentityminecart.a(j);

                        if (oitemstack != null) {
                            float f = this.aa.nextFloat() * 0.8F + 0.1F;
                            float f1 = this.aa.nextFloat() * 0.8F + 0.1F;
                            float f2 = this.aa.nextFloat() * 0.8F + 0.1F;

                            while (oitemstack.a > 0) {
                                int k = this.aa.nextInt(21) + 10;

                                if (k > oitemstack.a) {
                                    k = oitemstack.a;
                                }

                                oitemstack.a -= k;
                                OEntityItem oentityitem = new OEntityItem(this.p, this.t + (double) f, this.u + (double) f1, this.v + (double) f2, new OItemStack(oitemstack.c, k, oitemstack.j()));
                                float f3 = 0.05F;

                                oentityitem.w = (double) ((float) this.aa.nextGaussian() * f3);
                                oentityitem.x = (double) ((float) this.aa.nextGaussian() * f3 + 0.2F);
                                oentityitem.y = (double) ((float) this.aa.nextGaussian() * f3);
                                this.p.d((OEntity) oentityitem);
                            }
                        }
                    }

                    this.a(OBlock.ax.cm, 1, 0.0F);
                } else if (this.a == 2) {
                    this.a(OBlock.aE.cm, 1, 0.0F);
                }
            }

            return true;
        } else {
            return true;
        }
    }

    public boolean L() {
        return !this.L;
    }

    public void x() {
        if (this.h) {
            // CanaryMod: Destruction of the cart
            manager.callHook(PluginLoader.Hook.VEHICLE_DESTROYED, cart);
            for (int i = 0; i < this.k_(); ++i) {
            OItemStack oitemstack = this.a(i);

            if (oitemstack != null) {
                    float f = this.aa.nextFloat() * 0.8F + 0.1F;
                    float f1 = this.aa.nextFloat() * 0.8F + 0.1F;
                    float f2 = this.aa.nextFloat() * 0.8F + 0.1F;

                while (oitemstack.a > 0) {
                        int j = this.aa.nextInt(21) + 10;

                    if (j > oitemstack.a) {
                        j = oitemstack.a;
                    }

                    oitemstack.a -= j;
                    OEntityItem oentityitem = new OEntityItem(this.p, this.t + (double) f, this.u + (double) f1, this.v + (double) f2, new OItemStack(oitemstack.c, j, oitemstack.j()));

                    if (oitemstack.o()) {
                        oentityitem.a.d((ONBTTagCompound) oitemstack.p().b());
                    }

                    float f3 = 0.05F;

                        oentityitem.w = (double) ((float) this.aa.nextGaussian() * f3);
                        oentityitem.x = (double) ((float) this.aa.nextGaussian() * f3 + 0.2F);
                        oentityitem.y = (double) ((float) this.aa.nextGaussian() * f3);
                    this.p.d((OEntity) oentityitem);
                }
            }
        }
        }

        super.x();
        if (this.g != null) {
            this.g.a();
        }
    }

    public void b(int i) {
        this.h = false;
        super.b(i);
    }

    public void j_() {
        // CanaryMod: Update of the cart
        manager.callHook(PluginLoader.Hook.VEHICLE_UPDATE, cart);

        if (this.g != null) {
            this.g.a();
        }

        if (this.j() > 0) {
            this.h(this.j() - 1);
        }

        double prevX = this.q;
        double prevY = this.r;
        double prevZ = this.s;

        if (this.i() > 0) {
            this.g(this.i() - 1);
        }

        if (this.u < -64.0D) {
            this.C();
        }

        if (this.h() && this.aa.nextInt(4) == 0) {
            this.p.a("largesmoke", this.t, this.u + 0.8D, this.v, 0.0D, 0.0D, 0.0D);
        }

        if (this.p.J) {
            if (this.j > 0) {
                double d0 = this.t + (this.as - this.t) / (double) this.j;
                double d1 = this.u + (this.at - this.u) / (double) this.j;
                double d2 = this.v + (this.au - this.v) / (double) this.j;
                double d3 = OMathHelper.g(this.av - (double) this.z);

                this.z = (float) ((double) this.z + d3 / (double) this.j);
                this.A = (float) ((double) this.A + (this.aw - (double) this.A) / (double) this.j);
                --this.j;
                this.b(d0, d1, d2);
                this.b(this.z, this.A);
            } else {
                this.b(this.t, this.u, this.v);
                this.b(this.z, this.A);
            }
        } else {
            this.q = this.t;
            this.r = this.u;
            this.s = this.v;
            this.x -= 0.03999999910593033D;
            int i = OMathHelper.c(this.t);
            int j = OMathHelper.c(this.u);
            int k = OMathHelper.c(this.v);

            // CanaryMod: Change of the cart
            if ((int) i != (int) prevX || (int) j != (int) prevY || (int) k != (int) prevZ) {
                manager.callHook(PluginLoader.Hook.VEHICLE_POSITIONCHANGE, cart, i, j, k);
            }

            if (OBlockRail.e_(this.p, i, j - 1, k)) {
                --j;
            }

            double d4 = 0.4D;
            double d5 = 0.0078125D;
            int l = this.p.a(i, j, k);

            if (OBlockRail.d(l)) {
                OVec3 ovec3 = this.a(this.t, this.u, this.v);
                int i1 = this.p.g(i, j, k);

                this.u = (double) j;
                boolean flag = false;
                boolean flag1 = false;

                if (l == OBlock.W.cm) {
                    flag = (i1 & 8) != 0;
                    flag1 = !flag;
                }

                if (((OBlockRail) OBlock.p[l]).p()) {
                    i1 &= 7;
                }

                if (i1 >= 2 && i1 <= 5) {
                    this.u = (double) (j + 1);
                }

                if (i1 == 2) {
                    this.w -= d5;
                }

                if (i1 == 3) {
                    this.w += d5;
                }

                if (i1 == 4) {
                    this.y += d5;
                }

                if (i1 == 5) {
                    this.y -= d5;
                }

                int[][] aint = this.i[i1];
                double d6 = (double) (aint[1][0] - aint[0][0]);
                double d7 = (double) (aint[1][2] - aint[0][2]);
                double d8 = Math.sqrt(d6 * d6 + d7 * d7);
                double d9 = this.w * d6 + this.y * d7;

                if (d9 < 0.0D) {
                    d6 = -d6;
                    d7 = -d7;
                }

                double d10 = Math.sqrt(this.w * this.w + this.y * this.y);

                this.w = d10 * d6 / d8;
                this.y = d10 * d7 / d8;
                double d11;
                double d12;

                if (this.n != null) {
                    d12 = this.n.w * this.n.w + this.n.y * this.n.y;
                    d11 = this.w * this.w + this.y * this.y;
                    if (d12 > 1.0E-4D && d11 < 0.01D) {
                        this.w += this.n.w * 0.1D;
                        this.y += this.n.y * 0.1D;
                        flag1 = false;
                    }
                }

                if (flag1) {
                    d12 = Math.sqrt(this.w * this.w + this.y * this.y);
                    if (d12 < 0.03D) {
                        this.w *= 0.0D;
                        this.x *= 0.0D;
                        this.y *= 0.0D;
                    } else {
                        this.w *= 0.5D;
                        this.x *= 0.0D;
                        this.y *= 0.5D;
                    }
                }

                d12 = 0.0D;
                d11 = (double) i + 0.5D + (double) aint[0][0] * 0.5D;
                double d13 = (double) k + 0.5D + (double) aint[0][2] * 0.5D;
                double d14 = (double) i + 0.5D + (double) aint[1][0] * 0.5D;
                double d15 = (double) k + 0.5D + (double) aint[1][2] * 0.5D;

                d6 = d14 - d11;
                d7 = d15 - d13;
                double d16;
                double d17;

                if (d6 == 0.0D) {
                    this.t = (double) i + 0.5D;
                    d12 = this.v - (double) k;
                } else if (d7 == 0.0D) {
                    this.v = (double) k + 0.5D;
                    d12 = this.t - (double) i;
                } else {
                    d16 = this.t - d11;
                    d17 = this.v - d13;
                    d12 = (d16 * d6 + d17 * d7) * 2.0D;
                }

                this.t = d11 + d6 * d12;
                this.v = d13 + d7 * d12;
                this.b(this.t, this.u + (double) this.M, this.v);
                d16 = this.w;
                d17 = this.y;
                if (this.n != null) {
                    d16 *= 0.75D;
                    d17 *= 0.75D;
                }

                if (d16 < -d4) {
                    d16 = -d4;
                }

                if (d16 > d4) {
                    d16 = d4;
                }

                if (d17 < -d4) {
                    d17 = -d4;
                }

                if (d17 > d4) {
                    d17 = d4;
                }

                this.d(d16, 0.0D, d17);
                if (aint[0][1] != 0 && OMathHelper.c(this.t) - i == aint[0][0] && OMathHelper.c(this.v) - k == aint[0][2]) {
                    this.b(this.t, this.u + (double) aint[0][1], this.v);
                } else if (aint[1][1] != 0 && OMathHelper.c(this.t) - i == aint[1][0] && OMathHelper.c(this.v) - k == aint[1][2]) {
                    this.b(this.t, this.u + (double) aint[1][1], this.v);
                }

                if (this.n != null) {
                    this.w *= 0.996999979019165D;
                    this.x *= 0.0D;
                    this.y *= 0.996999979019165D;
                } else {
                    if (this.a == 2) {
                        double d18 = this.b * this.b + this.c * this.c;

                        if (d18 > 1.0E-4D) {
                            d18 = (double) OMathHelper.a(d18);
                            this.b /= d18;
                            this.c /= d18;
                            double d19 = 0.04D;

                            this.w *= 0.800000011920929D;
                            this.x *= 0.0D;
                            this.y *= 0.800000011920929D;
                            this.w += this.b * d19;
                            this.y += this.c * d19;
                        } else {
                            this.w *= 0.8999999761581421D;
                            this.x *= 0.0D;
                            this.y *= 0.8999999761581421D;
                        }
                    }

                    this.w *= 0.9599999785423279D;
                    this.x *= 0.0D;
                    this.y *= 0.9599999785423279D;
                }

                OVec3 ovec31 = this.a(this.t, this.u, this.v);

                if (ovec31 != null && ovec3 != null) {
                    double d20 = (ovec3.d - ovec31.d) * 0.05D;

                    d10 = Math.sqrt(this.w * this.w + this.y * this.y);
                    if (d10 > 0.0D) {
                        this.w = this.w / d10 * (d10 + d20);
                        this.y = this.y / d10 * (d10 + d20);
                    }

                    this.b(this.t, ovec31.d, this.v);
                }

                int j1 = OMathHelper.c(this.t);
                int k1 = OMathHelper.c(this.v);

                if (j1 != i || k1 != k) {
                    d10 = Math.sqrt(this.w * this.w + this.y * this.y);
                    this.w = d10 * (double) (j1 - i);
                    this.y = d10 * (double) (k1 - k);
                }

                double d21;

                if (this.a == 2) {
                    d21 = this.b * this.b + this.c * this.c;
                    if (d21 > 1.0E-4D && this.w * this.w + this.y * this.y > 0.001D) {
                        d21 = (double) OMathHelper.a(d21);
                        this.b /= d21;
                        this.c /= d21;
                        if (this.b * this.w + this.c * this.y < 0.0D) {
                            this.b = 0.0D;
                            this.c = 0.0D;
                        } else {
                            this.b = this.w;
                            this.c = this.y;
                        }
                    }
                }

                if (flag) {
                    d21 = Math.sqrt(this.w * this.w + this.y * this.y);
                    if (d21 > 0.01D) {
                        double d22 = 0.06D;

                        this.w += this.w / d21 * d22;
                        this.y += this.y / d21 * d22;
                    } else if (i1 == 1) {
                        if (this.p.s(i - 1, j, k)) {
                            this.w = 0.02D;
                        } else if (this.p.s(i + 1, j, k)) {
                            this.w = -0.02D;
                        }
                    } else if (i1 == 0) {
                        if (this.p.s(i, j, k - 1)) {
                            this.y = 0.02D;
                        } else if (this.p.s(i, j, k + 1)) {
                            this.y = -0.02D;
                        }
                    }
                }
            } else {
                if (this.w < -d4) {
                    this.w = -d4;
                }

                if (this.w > d4) {
                    this.w = d4;
                }

                if (this.y < -d4) {
                    this.y = -d4;
                }

                if (this.y > d4) {
                    this.y = d4;
                }

                if (this.E) {
                    this.w *= 0.5D;
                    this.x *= 0.5D;
                    this.y *= 0.5D;
                }

                this.d(this.w, this.x, this.y);
                if (!this.E) {
                    this.w *= 0.949999988079071D;
                    this.x *= 0.949999988079071D;
                    this.y *= 0.949999988079071D;
                }
            }

            this.D();
            this.A = 0.0F;
            double d23 = this.q - this.t;
            double d24 = this.s - this.v;

            if (d23 * d23 + d24 * d24 > 0.001D) {
                this.z = (float) (Math.atan2(d24, d23) * 180.0D / 3.141592653589793D);
                if (this.f) {
                    this.z += 180.0F;
                }
            }

            double d25 = (double) OMathHelper.g(this.z - this.B);

            if (d25 < -170.0D || d25 >= 170.0D) {
                this.z += 180.0F;
                this.f = !this.f;
            }

            this.b(this.z, this.A);
            List list = this.p.b((OEntity) this, this.D.b(0.20000000298023224D, 0.0D, 0.20000000298023224D));

            if (list != null && !list.isEmpty()) {
                for (int l1 = 0; l1 < list.size(); ++l1) {
                    OEntity oentity = (OEntity) list.get(l1);

                    if (oentity != this.n && oentity.M() && oentity instanceof OEntityMinecart) {
                        oentity.f(this);
                    }
                }
            }

            if (this.n != null && this.n.L) {
                if (this.n.o == this) {
                    this.n.o = null;
                }

                this.n = null;
            }

            if (this.e > 0) {
                --this.e;
            }

            if (this.e <= 0) {
                this.b = this.c = 0.0D;
            }

            this.e(this.e > 0);
        }
    }

    // CanaryMod: Store last position, avoids Hook spaming
    private int lastX = 0;
    private int lastY = 0;
    private int lastZ = 0;

    public OVec3 a(double d0, double d1, double d2) {
        int i = OMathHelper.c(d0);
        int j = OMathHelper.c(d1);
        int k = OMathHelper.c(d2);

        // CanaryMod: Change of the cart
        if ((int) i != (int) lastX || (int) j != (int) lastY || (int) k != (int) lastZ) {
            manager.callHook(PluginLoader.Hook.VEHICLE_POSITIONCHANGE, cart, i, j, k);
            lastX = i;
            lastY = j;
            lastZ = k;
        }

        if (OBlockRail.e_(this.p, i, j - 1, k)) {
            --j;
        }

        int l = this.p.a(i, j, k);

        if (OBlockRail.d(l)) {
            int i1 = this.p.g(i, j, k);

            d1 = (double) j;
            if (((OBlockRail) OBlock.p[l]).p()) {
                i1 &= 7;
            }

            if (i1 >= 2 && i1 <= 5) {
                d1 = (double) (j + 1);
            }

            int[][] aint = this.i[i1];
            double d3 = 0.0D;
            double d4 = (double) i + 0.5D + (double) aint[0][0] * 0.5D;
            double d5 = (double) j + 0.5D + (double) aint[0][1] * 0.5D;
            double d6 = (double) k + 0.5D + (double) aint[0][2] * 0.5D;
            double d7 = (double) i + 0.5D + (double) aint[1][0] * 0.5D;
            double d8 = (double) j + 0.5D + (double) aint[1][1] * 0.5D;
            double d9 = (double) k + 0.5D + (double) aint[1][2] * 0.5D;
            double d10 = d7 - d4;
            double d11 = (d8 - d5) * 2.0D;
            double d12 = d9 - d6;

            if (d10 == 0.0D) {
                d0 = (double) i + 0.5D;
                d3 = d2 - (double) k;
            } else if (d12 == 0.0D) {
                d2 = (double) k + 0.5D;
                d3 = d0 - (double) i;
            } else {
                double d13 = d0 - d4;
                double d14 = d2 - d6;

                d3 = (d13 * d10 + d14 * d12) * 2.0D;
            }

            d0 = d4 + d10 * d3;
            d1 = d5 + d11 * d3;
            d2 = d6 + d12 * d3;
            if (d11 < 0.0D) {
                ++d1;
            }

            if (d11 > 0.0D) {
                d1 += 0.5D;
            }

            return this.p.R().a(d0, d1, d2);
        } else {
            return null;
        }
    }

    protected void b(ONBTTagCompound onbttagcompound) {
        onbttagcompound.a("Type", this.a);
        if (this.a == 2) {
            onbttagcompound.a("PushX", this.b);
            onbttagcompound.a("PushZ", this.c);
            onbttagcompound.a("Fuel", (short) this.e);
        } else if (this.a == 1) {
            ONBTTagList onbttaglist = new ONBTTagList();

            for (int i = 0; i < this.d.length; ++i) {
                if (this.d[i] != null) {
                    ONBTTagCompound onbttagcompound1 = new ONBTTagCompound();

                    onbttagcompound1.a("Slot", (byte) i);
                    this.d[i].b(onbttagcompound1);
                    onbttaglist.a((ONBTBase) onbttagcompound1);
                }
            }

            onbttagcompound.a("Items", (ONBTBase) onbttaglist);
        }
    }

    protected void a(ONBTTagCompound onbttagcompound) {
        this.a = onbttagcompound.e("Type");
        if (this.a == 2) {
            this.b = onbttagcompound.h("PushX");
            this.c = onbttagcompound.h("PushZ");
            this.e = onbttagcompound.d("Fuel");
        } else if (this.a == 1) {
            ONBTTagList onbttaglist = onbttagcompound.m("Items");

            this.d = new OItemStack[this.k_()];

            for (int i = 0; i < onbttaglist.c(); ++i) {
                ONBTTagCompound onbttagcompound1 = (ONBTTagCompound) onbttaglist.b(i);
                int j = onbttagcompound1.c("Slot") & 255;

                if (j >= 0 && j < this.d.length) {
                    this.d[j] = OItemStack.a(onbttagcompound1);
                }
            }
        }
    }

    public void f(OEntity oentity) {
        if (!this.p.J) {
            if (oentity != this.n) {
                // CanaryMod: Collision of a cart
                if ((Boolean) manager.callHook(PluginLoader.Hook.VEHICLE_COLLISION, cart, oentity.entity)) {
                    return;
                }

                if (oentity instanceof OEntityLiving && !(oentity instanceof OEntityPlayer) && !(oentity instanceof OEntityIronGolem) && this.a == 0 && this.w * this.w + this.y * this.y > 0.01D && this.n == null && oentity.o == null) {
                    oentity.a((OEntity) this);
                }

                double d0 = oentity.t - this.t;
                double d1 = oentity.v - this.v;
                double d2 = d0 * d0 + d1 * d1;

                if (d2 >= 9.999999747378752E-5D) {
                    d2 = (double) OMathHelper.a(d2);
                    d0 /= d2;
                    d1 /= d2;
                    double d3 = 1.0D / d2;

                    if (d3 > 1.0D) {
                        d3 = 1.0D;
                    }

                    d0 *= d3;
                    d1 *= d3;
                    d0 *= 0.10000000149011612D;
                    d1 *= 0.10000000149011612D;
                    d0 *= (double) (1.0F - this.Z);
                    d1 *= (double) (1.0F - this.Z);
                    d0 *= 0.5D;
                    d1 *= 0.5D;
                    if (oentity instanceof OEntityMinecart) {
                        double d4 = oentity.t - this.t;
                        double d5 = oentity.v - this.v;
                        OVec3 ovec3 = this.p.R().a(d4, 0.0D, d5).a();
                        OVec3 ovec31 = this.p.R().a((double) OMathHelper.b(this.z * 3.1415927F / 180.0F), 0.0D, (double) OMathHelper.a(this.z * 3.1415927F / 180.0F)).a();
                        double d6 = Math.abs(ovec3.b(ovec31));

                        if (d6 < 0.800000011920929D) {
                            return;
                        }

                        double d7 = oentity.w + this.w;
                        double d8 = oentity.y + this.y;

                        if (((OEntityMinecart) oentity).a == 2 && this.a != 2) {
                            this.w *= 0.20000000298023224D;
                            this.y *= 0.20000000298023224D;
                            this.g(oentity.w - d0, 0.0D, oentity.y - d1);
                            oentity.w *= 0.949999988079071D;
                            oentity.y *= 0.949999988079071D;
                        } else if (((OEntityMinecart) oentity).a != 2 && this.a == 2) {
                            oentity.w *= 0.20000000298023224D;
                            oentity.y *= 0.20000000298023224D;
                            oentity.g(this.w + d0, 0.0D, this.y + d1);
                            this.w *= 0.949999988079071D;
                            this.y *= 0.949999988079071D;
                        } else {
                            d7 /= 2.0D;
                            d8 /= 2.0D;
                            this.w *= 0.20000000298023224D;
                            this.y *= 0.20000000298023224D;
                            this.g(d7 - d0, 0.0D, d8 - d1);
                            oentity.w *= 0.20000000298023224D;
                            oentity.y *= 0.20000000298023224D;
                            oentity.g(d7 + d0, 0.0D, d8 + d1);
                        }
                    } else {
                        this.g(-d0, 0.0D, -d1);
                        oentity.g(d0 / 4.0D, 0.0D, d1 / 4.0D);
                    }
                }
            }
        }
    }

    public int k_() {
        return 27;
    }

    public OItemStack a(int i) {
        return this.d[i];
    }

    public OItemStack a(int i, int j) {
        if (this.d[i] != null) {
            OItemStack oitemstack;

            if (this.d[i].a <= j) {
                oitemstack = this.d[i];
                this.d[i] = null;
                return oitemstack;
            } else {
                oitemstack = this.d[i].a(j);
                if (this.d[i].a == 0) {
                    this.d[i] = null;
                }

                return oitemstack;
            }
        } else {
            return null;
        }
    }

    public OItemStack a_(int i) {
        if (this.d[i] != null) {
            OItemStack oitemstack = this.d[i];

            this.d[i] = null;
            return oitemstack;
        } else {
            return null;
        }
    }

    public void a(int i, OItemStack oitemstack) {
        this.d[i] = oitemstack;
        if (oitemstack != null && oitemstack.a > this.c()) {
            oitemstack.a = this.c();
        }
    }

    public String b() {
        return this.name;
    }

    public int c() {
        return 64;
    }

    public void d() {}

    public boolean c(OEntityPlayer oentityplayer) {
        // CanaryMod: Entering the cart
        manager.callHook(PluginLoader.Hook.VEHICLE_ENTERED, cart, oentityplayer.entity);

        if (this.a == 0) {
            if (this.n != null && this.n instanceof OEntityPlayer && this.n != oentityplayer) {
                return true;
            }

            if (!this.p.J) {
                oentityplayer.a((OEntity) this);
            }
        } else if (this.a == 1) {
            if (!this.p.J) {
                oentityplayer.a((OIInventory) this);
            }
        } else if (this.a == 2) {
            OItemStack oitemstack = oentityplayer.bK.g();

            if (oitemstack != null && oitemstack.c == OItem.m.cf) {
                if (--oitemstack.a == 0) {
                    oentityplayer.bK.a(oentityplayer.bK.c, (OItemStack) null);
                }

                this.e += 3600;
            }

            this.b = this.t - oentityplayer.t;
            this.c = this.v - oentityplayer.v;
        }

        return true;
    }

    public boolean a(OEntityPlayer oentityplayer) {
        return this.L ? false : oentityplayer.e(this) <= 64.0D;
    }

    protected boolean h() {
        return (this.ag.a(16) & 1) != 0;
    }

    protected void e(boolean flag) {
        if (flag) {
            this.ag.b(16, Byte.valueOf((byte) (this.ag.a(16) | 1)));
        } else {
            this.ag.b(16, Byte.valueOf((byte) (this.ag.a(16) & -2)));
        }
    }

    public void l_() {}

    public void f() {}

    public void g(int i) {
        this.ag.b(19, Integer.valueOf(i));
    }

    public int i() {
        return this.ag.c(19);
    }

    public void h(int i) {
        this.ag.b(17, Integer.valueOf(i));
    }

    public int j() {
        return this.ag.c(17);
    }

    public void i(int i) {
        this.ag.b(18, Integer.valueOf(i));
    }

    public int k() {
        return this.ag.c(18);
    }

    @Override
    public OItemStack[] getContents() {
        return this.d;
    } //

    @Override
    public void setContents(OItemStack[] aoitemstack) {
        this.d = aoitemstack;
    } //

    @Override
    public OItemStack getContentsAt(int i) {
        return this.a(i);
    } //

    @Override
    public void setContentsAt(int i, OItemStack oitemstack) {
        this.a(i, oitemstack);
    } //

    @Override
    public int getContentsSize() {
        return this.k_();
    } //

    @Override
    public String getName() {
        return this.name;
    } //

    @Override
    public void setName(String s) {
        this.name = s;
    } //

}
