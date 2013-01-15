import java.util.List;

public class OEntityBoat extends OEntity {

    private boolean a;
    private double b;
    private int c;
    private double d;
    private double e;
    private double f;
    private double g;
    private double h;

    // CanaryMod Start
    Boat boat = new Boat(this);
    // CanaryMod end

    public OEntityBoat(OWorld oworld) {
        super(oworld);
        this.a = true;
        this.b = 0.07D;
        this.m = true;
        this.a(1.5F, 0.6F);
        this.M = this.O / 2.0F;
    }

    protected boolean f_() {
        return false;
    }

    protected void a() {
        this.ag.a(17, new Integer(0));
        this.ag.a(18, new Integer(1));
        this.ag.a(19, new Integer(0));
    }

    public OAxisAlignedBB g(OEntity oentity) {
        return oentity.D;
    }

    public OAxisAlignedBB E() {
        return this.D;
    }

    public boolean M() {
        return true;
    }

    public OEntityBoat(OWorld oworld, double d0, double d1, double d2) {
        this(oworld);
        this.b(d0, d1 + (double) this.M, d2);
        this.w = 0.0D;
        this.x = 0.0D;
        this.y = 0.0D;
        this.q = d0;
        this.r = d1;
        this.s = d2;

        // CanaryMod: Creation of the boat
        manager.callHook(PluginLoader.Hook.VEHICLE_CREATE, boat);
    }

    public double X() {
        return (double) this.O * 0.0D - 0.30000001192092896D;
    }

    public boolean a(ODamageSource odamagesource, int i) {
        // CanaryMod: Attack of the boat
        BaseEntity entity = null;

        if (odamagesource != null && odamagesource.g() != null) {
            entity = new BaseEntity(odamagesource.g());
        }
        if ((Boolean) manager.callHook(PluginLoader.Hook.VEHICLE_DAMAGE, boat, entity, i)) {
            return true;
        }

        if (this.ar()) {
            return false;
        } else if (!this.p.I && !this.L) {
            this.h(-this.h());
            this.g(10);
            this.a(this.d() + i * 10);
            this.K();
            if (odamagesource.g() instanceof OEntityPlayer && ((OEntityPlayer) odamagesource.g()).cd.d) {
                this.a(100);
            }

            if (this.d() > 40) {
                if (this.n != null) {
                    this.n.a((OEntity) this);
                }

                this.a(OItem.aE.cj, 1, 0.0F);
                this.x();
            }

            return true;
        } else {
            return true;
        }
    }

    public boolean L() {
        return !this.L;
    }

    public void j_() {
        super.j_();
        // CanaryMod: Update of the boat
        manager.callHook(PluginLoader.Hook.VEHICLE_UPDATE, boat);

        double prevX = this.q;
        double prevY = this.r;
        double prevZ = this.s;

        if (this.g() > 0) {
            this.g(this.g() - 1);
        }

        if (this.d() > 0) {
            this.a(this.d() - 1);
        }

        this.q = this.t;
        this.r = this.u;
        this.s = this.v;
        byte b0 = 5;
        double d0 = 0.0D;

        for (int i = 0; i < b0; ++i) {
            double d1 = this.D.b + (this.D.e - this.D.b) * (double) (i + 0) / (double) b0 - 0.125D;
            double d2 = this.D.b + (this.D.e - this.D.b) * (double) (i + 1) / (double) b0 - 0.125D;
            OAxisAlignedBB oaxisalignedbb = OAxisAlignedBB.a().a(this.D.a, d1, this.D.c, this.D.d, d2, this.D.f);

            if (this.p.b(oaxisalignedbb, OMaterial.h)) {
                d0 += 1.0D / (double) b0;
            }
        }

        double d3 = Math.sqrt(this.w * this.w + this.y * this.y);
        double d4;
        double d5;

        if (d3 > 0.26249999999999996D) {
            d4 = Math.cos((double) this.z * 3.141592653589793D / 180.0D);
            d5 = Math.sin((double) this.z * 3.141592653589793D / 180.0D);

            for (int j = 0; (double) j < 1.0D + d3 * 60.0D; ++j) {
                double d6 = (double) (this.aa.nextFloat() * 2.0F - 1.0F);
                double d7 = (double) (this.aa.nextInt(2) * 2 - 1) * 0.7D;
                double d8;
                double d9;

                if (this.aa.nextBoolean()) {
                    d8 = this.t - d4 * d6 * 0.8D + d5 * d7;
                    d9 = this.v - d5 * d6 * 0.8D - d4 * d7;
                    this.p.a("splash", d8, this.u - 0.125D, d9, this.w, this.x, this.y);
                } else {
                    d8 = this.t + d4 + d5 * d6 * 0.7D;
                    d9 = this.v + d5 - d4 * d6 * 0.7D;
                    this.p.a("splash", d8, this.u - 0.125D, d9, this.w, this.x, this.y);
                }
            }
        }

        double d10;
        double d11;

        if (this.p.I && this.a) {
            if (this.c > 0) {
                d4 = this.t + (this.d - this.t) / (double) this.c;
                d5 = this.u + (this.e - this.u) / (double) this.c;
                d10 = this.v + (this.f - this.v) / (double) this.c;
                d11 = OMathHelper.g(this.g - (double) this.z);
                this.z = (float) ((double) this.z + d11 / (double) this.c);
                this.A = (float) ((double) this.A + (this.h - (double) this.A) / (double) this.c);
                --this.c;
                this.b(d4, d5, d10);
                this.b(this.z, this.A);
            } else {
                d4 = this.t + this.w;
                d5 = this.u + this.x;
                d10 = this.v + this.y;
                this.b(d4, d5, d10);
                if (this.E) {
                    this.w *= 0.5D;
                    this.x *= 0.5D;
                    this.y *= 0.5D;
                }

                this.w *= 0.9900000095367432D;
                this.x *= 0.949999988079071D;
                this.y *= 0.9900000095367432D;
            }
        } else {
            if (d0 < 1.0D) {
                d4 = d0 * 2.0D - 1.0D;
                this.x += 0.03999999910593033D * d4;
            } else {
                if (this.x < 0.0D) {
                    this.x /= 2.0D;
                }

                this.x += 0.007000000216066837D;
            }

            if (this.n != null) {
                this.w += this.n.w * this.b;
                this.y += this.n.y * this.b;
            }

            d4 = Math.sqrt(this.w * this.w + this.y * this.y);
            if (d4 > 0.35D) {
                d5 = 0.35D / d4;
                this.w *= d5;
                this.y *= d5;
                d4 = 0.35D;
            }

            if (d4 > d3 && this.b < 0.35D) {
                this.b += (0.35D - this.b) / 35.0D;
                if (this.b > 0.35D) {
                    this.b = 0.35D;
                }
            } else {
                this.b -= (this.b - 0.07D) / 35.0D;
                if (this.b < 0.07D) {
                    this.b = 0.07D;
                }
            }

            if (this.E) {
                this.w *= 0.5D;
                this.x *= 0.5D;
                this.y *= 0.5D;
            }

            this.d(this.w, this.x, this.y);
            if (this.F && d3 > 0.2D) {
                if (!this.p.I) {
                    this.x();

                    int k;

                    for (k = 0; k < 3; ++k) {
                        this.a(OBlock.A.cm, 1, 0.0F);
                    }

                    for (k = 0; k < 2; ++k) {
                        this.a(OItem.D.cj, 1, 0.0F);
                    }
                }
            } else {
                this.w *= 0.9900000095367432D;
                this.x *= 0.949999988079071D;
                this.y *= 0.9900000095367432D;
            }

            this.A = 0.0F;
            d5 = (double) this.z;
            d10 = this.q - this.t;
            d11 = this.s - this.v;
            if (d10 * d10 + d11 * d11 > 0.001D) {
                d5 = (double) ((float) (Math.atan2(d11, d10) * 180.0D / 3.141592653589793D));
            }

            double d12 = OMathHelper.g(d5 - (double) this.z);

            if (d12 > 20.0D) {
                d12 = 20.0D;
            }

            if (d12 < -20.0D) {
                d12 = -20.0D;
            }

            this.z = (float) ((double) this.z + d12);
            this.b(this.z, this.A);
            if (!this.p.I) {
                // CanaryMod: Change of the boat
                if ((int) this.t != (int) prevX || (int) this.u != (int) prevY || (int) this.v != (int) prevZ) {
                    manager.callHook(PluginLoader.Hook.VEHICLE_POSITIONCHANGE, boat, (int) this.q, (int) this.r, (int) this.s);
                }

                List list = this.p.b((OEntity) this, this.D.b(0.20000000298023224D, 0.0D, 0.20000000298023224D));
                int l;

                if (list != null && !list.isEmpty()) {
                    for (l = 0; l < list.size(); ++l) {
                        OEntity oentity = (OEntity) list.get(l);

                        if (oentity != this.n && oentity.M() && oentity instanceof OEntityBoat) {
                            oentity.f(this);
                        }
                    }
                }

                for (l = 0; l < 4; ++l) {
                    int i1 = OMathHelper.c(this.t + ((double) (l % 2) - 0.5D) * 0.8D);
                    int j1 = OMathHelper.c(this.v + ((double) (l / 2) - 0.5D) * 0.8D);

                    for (int k1 = 0; k1 < 2; ++k1) {
                        int l1 = OMathHelper.c(this.u) + k1;
                        int i2 = this.p.a(i1, l1, j1);
                        int j2 = this.p.h(i1, l1, j1);

                        if (i2 == OBlock.aV.cm) {
                            this.p.e(i1, l1, j1, 0);
                        } else if (i2 == OBlock.bC.cm) {
                            OBlock.bC.a(this.p, i1, l1, j1, j2, 0.3F, 0);
                            this.p.e(i1, l1, j1, 0);
                        }
                    }
                }

                if (this.n != null && this.n.L) {
                    this.n = null;
                }
            }
        }
    }

    public void V() {
        if (this.n != null) {
            double d0 = Math.cos((double) this.z * 3.141592653589793D / 180.0D) * 0.4D;
            double d1 = Math.sin((double) this.z * 3.141592653589793D / 180.0D) * 0.4D;

            this.n.b(this.t + d0, this.u + this.X() + this.n.W(), this.v + d1);
        }
    }

    protected void b(ONBTTagCompound onbttagcompound) {}

    protected void a(ONBTTagCompound onbttagcompound) {}

    public boolean a(OEntityPlayer oentityplayer) {
        // CanaryMod: Entering the boat
        manager.callHook(PluginLoader.Hook.VEHICLE_ENTERED, boat, oentityplayer.entity);

        if (this.n != null && this.n instanceof OEntityPlayer && this.n != oentityplayer) {
            return true;
        } else {
            if (!this.p.I) {
                oentityplayer.a((OEntity) this);
            }

            return true;
        }
    }

    public void a(int i) {
        this.ag.b(19, Integer.valueOf(i));
    }

    public int d() {
        return this.ag.c(19);
    }

    public void g(int i) {
        this.ag.b(17, Integer.valueOf(i));
    }

    public int g() {
        return this.ag.c(17);
    }

    public void h(int i) {
        this.ag.b(18, Integer.valueOf(i));
    }

    public int h() {
        return this.ag.c(18);
    }

    @Override
    public Boat getEntity() {
        return boat;
    } //
}
