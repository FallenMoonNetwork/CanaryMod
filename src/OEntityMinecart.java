import java.util.List;

public abstract class OEntityMinecart extends OEntity {

    private boolean a;
    private final OIUpdatePlayerListBox b;
    private String c;
    private static final int[][][] d = new int[][][] { { { 0, 0, -1}, { 0, 0, 1}}, { { -1, 0, 0}, { 1, 0, 0}}, { { -1, -1, 0}, { 1, 0, 0}}, { { -1, 0, 0}, { 1, -1, 0}}, { { 0, 0, -1}, { 0, -1, 1}}, { { 0, -1, -1}, { 0, 0, 1}}, { { 0, 0, 1}, { 1, 0, 0}}, { { 0, 0, 1}, { -1, 0, 0}}, { { 0, 0, -1}, { -1, 0, 0}}, { { 0, 0, -1}, { 1, 0, 0}}};
    private int e;
    private double f;
    private double g;
    private double h;
    private double i;
    private double j;

    Minecart cart = new Minecart(this); // CanaryMod: Reference to the cart

    public OEntityMinecart(OWorld oworld) {
        super(oworld);
        this.m = true;
        this.a(0.98F, 0.7F);
        this.N = this.P / 2.0F;
        this.b = oworld != null ? oworld.a(this) : null;
    }

    public static OEntityMinecart a(OWorld oworld, double d0, double d1, double d2, int i) {
        switch (i) {
            case 1:
                return new OEntityMinecartChest(oworld, d0, d1, d2);

            case 2:
                return new OEntityMinecartFurnace(oworld, d0, d1, d2);

            case 3:
                return new OEntityMinecartTNT(oworld, d0, d1, d2);

            case 4:
                return new OEntityMinecartMobSpawner(oworld, d0, d1, d2);

            case 5:
                return new OEntityMinecartHopper(oworld, d0, d1, d2);

            default:
                return new OEntityMinecartEmpty(oworld, d0, d1, d2);
        }
    }

    protected boolean e_() {
        return false;
    }

    protected void a() {
        this.ah.a(17, new Integer(0));
        this.ah.a(18, new Integer(1));
        this.ah.a(19, new Float(0.0F));
        this.ah.a(20, new Integer(0));
        this.ah.a(21, new Integer(6));
        this.ah.a(22, Byte.valueOf((byte) 0));
    }

    public OAxisAlignedBB g(OEntity oentity) {
        return oentity.L() ? oentity.E : null;
    }

    public OAxisAlignedBB D() {
        return null;
    }

    public boolean L() {
        return true;
    }

    public OEntityMinecart(OWorld oworld, double d0, double d1, double d2) {
        this(oworld);
        this.b(d0, d1, d2);
        this.x = 0.0D;
        this.y = 0.0D;
        this.z = 0.0D;
        this.r = d0;
        this.s = d1;
        this.t = d2;
        manager.callHook(PluginLoader.Hook.VEHICLE_CREATE, this.getEntity()); // CanaryMod: Creation of the cart
    }

    public double W() {
        return (double) this.P * 0.0D - 0.30000001192092896D;
    }

    public boolean a(ODamageSource odamagesource, float f) {
        // CanaryMod: Attack of the cart
        BaseEntity entity = null;

        if (odamagesource != null && odamagesource.h() != null) {
            entity = odamagesource.h().getEntity();
        }

        if ((Boolean) manager.callHook(PluginLoader.Hook.VEHICLE_DAMAGE, this.getEntity(), entity, f)) {
            return true;
        }

        if (!this.q.I && !this.M) {
            if (this.ap()) {
                return false;
            } else {
                this.h(-this.k());
                this.c(10);
                this.J();
                this.a(this.i() + f * 10.0F);
                boolean flag = odamagesource.i() instanceof OEntityPlayer && ((OEntityPlayer) odamagesource.i()).bG.d;

                if (flag || this.i() > 40.0F) {
                    if (this.n != null) {
                        this.n.a((OEntity) this);
                    }

                    if (flag && !this.c()) {
                        this.w();
                    } else {
                        this.a(odamagesource);
                    }
                }

                return true;
            }
        } else {
            return true;
        }
    }

    public void a(ODamageSource odamagesource) {
        manager.callHook(PluginLoader.Hook.VEHICLE_DESTROYED, this.getEntity()); // CanaryMod: Destruction of the cart
        this.w();
        OItemStack oitemstack = new OItemStack(OItem.aB, 1);

        if (this.c != null) {
            oitemstack.c(this.c);
        }

        this.a(oitemstack, 0.0F);
    }

    public boolean K() {
        return !this.M;
    }

    public void w() {
        super.w();
        if (this.b != null) {
            this.b.a();
        }
    }

    public void l_() {
        // CanaryMod: call update hook
        manager.callHook(PluginLoader.Hook.VEHICLE_UPDATE, this.getEntity());

        if (this.b != null) {
            this.b.a();
        }

        //CanaryMod: lets track the location shall we?
        double prevX = this.r;
        double prevY = this.s;
        double prevZ = this.t;
        //CanaryMod: end

        if (this.j() > 0) {
            this.c(this.j() - 1);
        }

        if (this.i() > 0.0F) {
            this.a(this.i() - 1.0F);
        }

        if (this.v < -64.0D) {
            this.B();
        }

        int i;

        if (!this.q.I && this.q instanceof OWorldServer) {
            this.q.C.a("portal");
            OMinecraftServer ominecraftserver = ((OWorldServer) this.q).p();

            i = this.y();
            if (this.ap) {
                if (ominecraftserver.u()) {
                    if (this.o == null && this.aq++ >= i) {
                        this.aq = i;
                        this.ao = this.aa();
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

        if (this.q.I) {
            if (this.e > 0) {
                double d0 = this.u + (this.f - this.u) / (double) this.e;
                double d1 = this.v + (this.g - this.v) / (double) this.e;
                double d2 = this.w + (this.h - this.w) / (double) this.e;
                double d3 = OMathHelper.g(this.i - (double) this.A);

                this.A = (float) ((double) this.A + d3 / (double) this.e);
                this.B = (float) ((double) this.B + (this.j - (double) this.B) / (double) this.e);
                --this.e;
                this.b(d0, d1, d2);
                this.b(this.A, this.B);
            } else {
                this.b(this.u, this.v, this.w);
                this.b(this.A, this.B);
            }
        } else {
            this.r = this.u;
            this.s = this.v;
            this.t = this.w;
            this.y -= 0.03999999910593033D;
            int j = OMathHelper.c(this.u);

            i = OMathHelper.c(this.v);
            int k = OMathHelper.c(this.w);

            // CanaryMod: Change of the cart
            if ((int) i != (int) prevX || (int) j != (int) prevY || (int) k != (int) prevZ) {
                manager.callHook(PluginLoader.Hook.VEHICLE_POSITIONCHANGE, this.getEntity(), i, j, k);
            }

            if (OBlockRailBase.d_(this.q, j, i - 1, k)) {
                --i;
            }

            double d4 = 0.4D;
            double d5 = 0.0078125D;
            int l = this.q.a(j, i, k);

            if (OBlockRailBase.e_(l)) {
                int i1 = this.q.h(j, i, k);

                this.a(j, i, k, d4, d5, l, i1);
                if (l == OBlock.cy.cF) {
                    // CanaryMod: call MINECART_ACTIVATE hook
                    if (!(Boolean) manager.callHook(PluginLoader.Hook.MINECART_ACTIVATE, this.getEntity(), (i1 & 8) != 0)) {
                        this.a(j, i, k, (i1 & 8) != 0);
                    }
                }
            } else {
                this.b(d4);
            }

            this.C();
            this.B = 0.0F;
            double d6 = this.r - this.u;
            double d7 = this.t - this.w;

            if (d6 * d6 + d7 * d7 > 0.001D) {
                this.A = (float) (Math.atan2(d7, d6) * 180.0D / 3.141592653589793D);
                if (this.a) {
                    this.A += 180.0F;
                }
            }

            double d8 = (double) OMathHelper.g(this.A - this.C);

            if (d8 < -170.0D || d8 >= 170.0D) {
                this.A += 180.0F;
                this.a = !this.a;
            }

            this.b(this.A, this.B);
            List list = this.q.b((OEntity) this, this.E.b(0.20000000298023224D, 0.0D, 0.20000000298023224D));

            if (list != null && !list.isEmpty()) {
                for (int j1 = 0; j1 < list.size(); ++j1) {
                    OEntity oentity = (OEntity) list.get(j1);

                    if (oentity != this.n && oentity.L() && oentity instanceof OEntityMinecart) {
                        oentity.f((OEntity) this);
                    }
                }
            }

            if (this.n != null && this.n.M) {
                if (this.n.o == this) {
                    this.n.o = null;
                }
                this.n = null;
            }
        }
    }

    public void a(int i, int j, int k, boolean flag) {}

    protected void b(double d0) {
        if (this.x < -d0) {
            this.x = -d0;
        }

        if (this.x > d0) {
            this.x = d0;
        }

        if (this.z < -d0) {
            this.z = -d0;
        }

        if (this.z > d0) {
            this.z = d0;
        }

        if (this.F) {
            this.x *= 0.5D;
            this.y *= 0.5D;
            this.z *= 0.5D;
        }

        this.d(this.x, this.y, this.z);
        if (!this.F) {
            this.x *= 0.949999988079071D;
            this.y *= 0.949999988079071D;
            this.z *= 0.949999988079071D;
        }
    }

    protected void a(int i, int j, int k, double d0, double d1, int l, int i1) {
        this.T = 0.0F;
        OVec3 ovec3 = this.a(this.u, this.v, this.w);

        this.v = (double) j;
        boolean flag = false;
        boolean flag1 = false;

        if (l == OBlock.Y.cF) {
            flag = (i1 & 8) != 0;
            flag1 = !flag;
        }

        if (((OBlockRailBase) OBlock.s[l]).e()) {
            i1 &= 7;
        }

        if (i1 >= 2 && i1 <= 5) {
            this.v = (double) (j + 1);
        }

        if (i1 == 2) {
            this.x -= d1;
        }

        if (i1 == 3) {
            this.x += d1;
        }

        if (i1 == 4) {
            this.z += d1;
        }

        if (i1 == 5) {
            this.z -= d1;
        }

        int[][] aint = d[i1];
        double d2 = (double) (aint[1][0] - aint[0][0]);
        double d3 = (double) (aint[1][2] - aint[0][2]);
        double d4 = Math.sqrt(d2 * d2 + d3 * d3);
        double d5 = this.x * d2 + this.z * d3;

        if (d5 < 0.0D) {
            d2 = -d2;
            d3 = -d3;
        }

        double d6 = Math.sqrt(this.x * this.x + this.z * this.z);

        if (d6 > 2.0D) {
            d6 = 2.0D;
        }

        this.x = d6 * d2 / d4;
        this.z = d6 * d3 / d4;
        double d7;
        double d8;
        double d9;
        double d10;

        if (this.n != null && this.n instanceof OEntityLivingBase) {
            d7 = (double) ((OEntityLivingBase) this.n).bf;
            if (d7 > 0.0D) {
                d8 = -Math.sin((double) (this.n.A * 3.1415927F / 180.0F));
                d9 = Math.cos((double) (this.n.A * 3.1415927F / 180.0F));
                d10 = this.x * this.x + this.z * this.z;
                if (d10 < 0.01D) {
                    this.x += d8 * 0.1D;
                    this.z += d9 * 0.1D;
                    flag1 = false;
                }
            }
        }

        if (flag1) {
            d7 = Math.sqrt(this.x * this.x + this.z * this.z);
            if (d7 < 0.03D) {
                this.x *= 0.0D;
                this.y *= 0.0D;
                this.z *= 0.0D;
            } else {
                this.x *= 0.5D;
                this.y *= 0.0D;
                this.z *= 0.5D;
            }
        }

        d7 = 0.0D;
        d8 = (double) i + 0.5D + (double) aint[0][0] * 0.5D;
        d9 = (double) k + 0.5D + (double) aint[0][2] * 0.5D;
        d10 = (double) i + 0.5D + (double) aint[1][0] * 0.5D;
        double d11 = (double) k + 0.5D + (double) aint[1][2] * 0.5D;

        d2 = d10 - d8;
        d3 = d11 - d9;
        double d12;
        double d13;

        if (d2 == 0.0D) {
            this.u = (double) i + 0.5D;
            d7 = this.w - (double) k;
        } else if (d3 == 0.0D) {
            this.w = (double) k + 0.5D;
            d7 = this.u - (double) i;
        } else {
            d12 = this.u - d8;
            d13 = this.w - d9;
            d7 = (d12 * d2 + d13 * d3) * 2.0D;
        }

        this.u = d8 + d2 * d7;
        this.w = d9 + d3 * d7;
        this.b(this.u, this.v + (double) this.N, this.w);
        d12 = this.x;
        d13 = this.z;
        if (this.n != null) {
            d12 *= 0.75D;
            d13 *= 0.75D;
        }

        if (d12 < -d0) {
            d12 = -d0;
        }

        if (d12 > d0) {
            d12 = d0;
        }

        if (d13 < -d0) {
            d13 = -d0;
        }

        if (d13 > d0) {
            d13 = d0;
        }

        this.d(d12, 0.0D, d13);
        if (aint[0][1] != 0 && OMathHelper.c(this.u) - i == aint[0][0] && OMathHelper.c(this.w) - k == aint[0][2]) {
            this.b(this.u, this.v + (double) aint[0][1], this.w);
        } else if (aint[1][1] != 0 && OMathHelper.c(this.u) - i == aint[1][0] && OMathHelper.c(this.w) - k == aint[1][2]) {
            this.b(this.u, this.v + (double) aint[1][1], this.w);
        }

        this.h();
        OVec3 ovec31 = this.a(this.u, this.v, this.w);

        if (ovec31 != null && ovec3 != null) {
            double d14 = (ovec3.d - ovec31.d) * 0.05D;

            d6 = Math.sqrt(this.x * this.x + this.z * this.z);
            if (d6 > 0.0D) {
                this.x = this.x / d6 * (d6 + d14);
                this.z = this.z / d6 * (d6 + d14);
            }

            this.b(this.u, ovec31.d, this.w);
        }

        int j1 = OMathHelper.c(this.u);
        int k1 = OMathHelper.c(this.w);

        if (j1 != i || k1 != k) {
            d6 = Math.sqrt(this.x * this.x + this.z * this.z);
            this.x = d6 * (double) (j1 - i);
            this.z = d6 * (double) (k1 - k);
        }

        if (flag) {
            double d15 = Math.sqrt(this.x * this.x + this.z * this.z);

            if (d15 > 0.01D) {
                double d16 = 0.06D;

                this.x += this.x / d15 * d16;
                this.z += this.z / d15 * d16;
            } else if (i1 == 1) {
                if (this.q.u(i - 1, j, k)) {
                    this.x = 0.02D;
                } else if (this.q.u(i + 1, j, k)) {
                    this.x = -0.02D;
                }
            } else if (i1 == 0) {
                if (this.q.u(i, j, k - 1)) {
                    this.z = 0.02D;
                } else if (this.q.u(i, j, k + 1)) {
                    this.z = -0.02D;
                }
            }
        }
    }

    protected void h() {
        if (this.n != null) {
            this.x *= 0.996999979019165D;
            this.y *= 0.0D;
            this.z *= 0.996999979019165D;
        } else {
            this.x *= 0.9599999785423279D;
            this.y *= 0.0D;
            this.z *= 0.9599999785423279D;
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
            manager.callHook(PluginLoader.Hook.VEHICLE_POSITIONCHANGE, this.getEntity(), i, j, k);
            lastX = i;
            lastY = j;
            lastZ = k;
        }

        if (OBlockRailBase.d_(this.q, i, j - 1, k)) {
            --j;
        }

        int l = this.q.a(i, j, k);

        if (OBlockRailBase.e_(l)) {
            int i1 = this.q.h(i, j, k);

            d1 = (double) j;
            if (((OBlockRailBase) OBlock.s[l]).e()) {
                i1 &= 7;
            }

            if (i1 >= 2 && i1 <= 5) {
                d1 = (double) (j + 1);
            }

            int[][] aint = d[i1];
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

            return this.q.V().a(d0, d1, d2);
        } else {
            return null;
        }
    }

    protected void a(ONBTTagCompound onbttagcompound) {
        if (onbttagcompound.n("CustomDisplayTile")) {
            this.i(onbttagcompound.e("DisplayTile"));
            this.j(onbttagcompound.e("DisplayData"));
            this.k(onbttagcompound.e("DisplayOffset"));
        }

        if (onbttagcompound.b("CustomName") && onbttagcompound.i("CustomName").length() > 0) {
            this.c = onbttagcompound.i("CustomName");
        }
    }

    protected void b(ONBTTagCompound onbttagcompound) {
        if (this.s()) {
            onbttagcompound.a("CustomDisplayTile", true);
            onbttagcompound.a("DisplayTile", this.m() == null ? 0 : this.m().cF);
            onbttagcompound.a("DisplayData", this.o());
            onbttagcompound.a("DisplayOffset", this.q());
        }

        if (this.c != null && this.c.length() > 0) {
            onbttagcompound.a("CustomName", this.c);
        }
    }

    public void f(OEntity oentity) {
        if (!this.q.I) {
            if (oentity != this.n) {
                // CanaryMod: Collision of a cart
                if ((Boolean) manager.callHook(PluginLoader.Hook.VEHICLE_COLLISION, this.getEntity(), oentity.getEntity())) {
                    return;
                }
                if (oentity instanceof OEntityLivingBase && !(oentity instanceof OEntityPlayer) && !(oentity instanceof OEntityIronGolem) && this.l() == 0 && this.x * this.x + this.z * this.z > 0.01D && this.n == null && oentity.o == null) {
                    oentity.a((OEntity) this);
                }

                double d0 = oentity.u - this.u;
                double d1 = oentity.w - this.w;
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
                    d0 *= (double) (1.0F - this.aa);
                    d1 *= (double) (1.0F - this.aa);
                    d0 *= 0.5D;
                    d1 *= 0.5D;
                    if (oentity instanceof OEntityMinecart) {
                        double d4 = oentity.u - this.u;
                        double d5 = oentity.w - this.w;
                        OVec3 ovec3 = this.q.V().a(d4, 0.0D, d5).a();
                        OVec3 ovec31 = this.q.V().a((double) OMathHelper.b(this.A * 3.1415927F / 180.0F), 0.0D, (double) OMathHelper.a(this.A * 3.1415927F / 180.0F)).a();
                        double d6 = Math.abs(ovec3.b(ovec31));

                        if (d6 < 0.800000011920929D) {
                            return;
                        }

                        double d7 = oentity.x + this.x;
                        double d8 = oentity.z + this.z;

                        if (((OEntityMinecart) oentity).l() == 2 && this.l() != 2) {
                            this.x *= 0.20000000298023224D;
                            this.z *= 0.20000000298023224D;
                            this.g(oentity.x - d0, 0.0D, oentity.z - d1);
                            oentity.x *= 0.949999988079071D;
                            oentity.z *= 0.949999988079071D;
                        } else if (((OEntityMinecart) oentity).l() != 2 && this.l() == 2) {
                            oentity.x *= 0.20000000298023224D;
                            oentity.z *= 0.20000000298023224D;
                            oentity.g(this.x + d0, 0.0D, this.z + d1);
                            this.x *= 0.949999988079071D;
                            this.z *= 0.949999988079071D;
                        } else {
                            d7 /= 2.0D;
                            d8 /= 2.0D;
                            this.x *= 0.20000000298023224D;
                            this.z *= 0.20000000298023224D;
                            this.g(d7 - d0, 0.0D, d8 - d1);
                            oentity.x *= 0.20000000298023224D;
                            oentity.z *= 0.20000000298023224D;
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

    public void a(float f) {
        this.ah.b(19, Float.valueOf(f));
    }

    public float i() {
        return this.ah.d(19);
    }

    public void c(int i) {
        this.ah.b(17, Integer.valueOf(i));
    }

    public int j() {
        return this.ah.c(17);
    }

    public void h(int i) {
        this.ah.b(18, Integer.valueOf(i));
    }

    public int k() {
        return this.ah.c(18);
    }

    public abstract int l();

    public OBlock m() {
        if (!this.s()) {
            return this.n();
        } else {
            int i = this.u().c(20) & '\uffff';

            return i > 0 && i < OBlock.s.length ? OBlock.s[i] : null;
        }
    }

    public OBlock n() {
        return null;
    }

    public int o() {
        return !this.s() ? this.p() : this.u().c(20) >> 16;
    }

    public int p() {
        return 0;
    }

    public int q() {
        return !this.s() ? this.r() : this.u().c(21);
    }

    public int r() {
        return 6;
    }

    public void i(int i) {
        this.u().b(20, Integer.valueOf(i & '\uffff' | this.o() << 16));
        this.a(true);
    }

    public void j(int i) {
        OBlock oblock = this.m();
        int j = oblock == null ? 0 : oblock.cF;

        this.u().b(20, Integer.valueOf(j & '\uffff' | i << 16));
        this.a(true);
    }

    public void k(int i) {
        this.u().b(21, Integer.valueOf(i));
        this.a(true);
    }

    public boolean s() {
        return this.u().a(22) == 1;
    }

    public void a(boolean flag) {
        this.u().b(22, Byte.valueOf((byte) (flag ? 1 : 0)));
    }

    public void a(String s) {
        this.c = s;
    }

    public String al() {
        return this.c != null ? this.c : super.al();
    }

    public boolean c() {
        return this.c != null;
    }

    public String t() {
        return this.c;
    }

    @Override
    public Minecart getEntity() {
        return cart;
    }
}
