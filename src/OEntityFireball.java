import java.util.List;

public abstract class OEntityFireball extends OEntity {

    protected int e = -1; // CanaryMod: private to protected
    private int f = -1;
    private int g = -1;
    private int h = 0;
    private boolean i = false;
    public OEntityLiving a;
    private int j;
    private int au = 0;
    public double b;
    public double c;
    public double d;

    public OEntityFireball(OWorld oworld) {
        super(oworld);
        this.a(1.0F, 1.0F);
    }

    protected void a() {}

    public OEntityFireball(OWorld oworld, double d0, double d1, double d2, double d3, double d4, double d5) {
        super(oworld);
        this.a(1.0F, 1.0F);
        this.b(d0, d1, d2, this.A, this.B);
        this.b(d0, d1, d2);
        double d6 = (double) OMathHelper.a(d3 * d3 + d4 * d4 + d5 * d5);

        this.b = d3 / d6 * 0.1D;
        this.c = d4 / d6 * 0.1D;
        this.d = d5 / d6 * 0.1D;
    }

    public OEntityFireball(OWorld oworld, OEntityLiving oentityliving, double d0, double d1, double d2) {
        super(oworld);
        this.a = oentityliving;
        this.a(1.0F, 1.0F);
        this.b(oentityliving.u, oentityliving.v, oentityliving.w, oentityliving.A, oentityliving.B);
        this.b(this.u, this.v, this.w);
        this.N = 0.0F;
        this.x = this.y = this.z = 0.0D;
        d0 += this.ab.nextGaussian() * 0.4D;
        d1 += this.ab.nextGaussian() * 0.4D;
        d2 += this.ab.nextGaussian() * 0.4D;
        double d3 = (double) OMathHelper.a(d0 * d0 + d1 * d1 + d2 * d2);

        this.b = d0 / d3 * 0.1D;
        this.c = d1 / d3 * 0.1D;
        this.d = d2 / d3 * 0.1D;
    }

    public void l_() {
        if (!this.q.I && (this.a != null && this.a.M || !this.q.f((int) this.u, (int) this.v, (int) this.w))) {
            this.w();
        } else {
            super.l_();
            this.d(1);
            if (this.i) {
                int i = this.q.a(this.e, this.f, this.g);

                if (i == this.h) {
                    ++this.j;
                    if (this.j == 600) {
                        this.w();
                    }

                    return;
                }

                this.i = false;
                this.x *= (double) (this.ab.nextFloat() * 0.2F);
                this.y *= (double) (this.ab.nextFloat() * 0.2F);
                this.z *= (double) (this.ab.nextFloat() * 0.2F);
                this.j = 0;
                this.au = 0;
            } else {
                ++this.au;
            }

            OVec3 ovec3 = this.q.T().a(this.u, this.v, this.w);
            OVec3 ovec31 = this.q.T().a(this.u + this.x, this.v + this.y, this.w + this.z);
            OMovingObjectPosition omovingobjectposition = this.q.a(ovec3, ovec31);

            ovec3 = this.q.T().a(this.u, this.v, this.w);
            ovec31 = this.q.T().a(this.u + this.x, this.v + this.y, this.w + this.z);
            if (omovingobjectposition != null) {
                ovec31 = this.q.T().a(omovingobjectposition.f.c, omovingobjectposition.f.d, omovingobjectposition.f.e);
            }

            OEntity oentity = null;
            List list = this.q.b((OEntity) this, this.E.a(this.x, this.y, this.z).b(1.0D, 1.0D, 1.0D));
            double d0 = 0.0D;

            for (int j = 0; j < list.size(); ++j) {
                OEntity oentity1 = (OEntity) list.get(j);

                if (oentity1.K() && (!oentity1.i(this.a) || this.au >= 25)) {
                    float f = 0.3F;
                    OAxisAlignedBB oaxisalignedbb = oentity1.E.b((double) f, (double) f, (double) f);
                    OMovingObjectPosition omovingobjectposition1 = oaxisalignedbb.a(ovec3, ovec31);

                    if (omovingobjectposition1 != null) {
                        double d1 = ovec3.d(omovingobjectposition1.f);

                        if (d1 < d0 || d0 == 0.0D) {
                            oentity = oentity1;
                            d0 = d1;
                        }
                    }
                }
            }

            if (oentity != null) {
                omovingobjectposition = new OMovingObjectPosition(oentity);
            }

            if (omovingobjectposition != null) {
                this.a(omovingobjectposition);
            }

            this.u += this.x;
            this.v += this.y;
            this.w += this.z;
            float f1 = OMathHelper.a(this.x * this.x + this.z * this.z);

            this.A = (float) (Math.atan2(this.z, this.x) * 180.0D / 3.1415927410125732D) + 90.0F;

            for (this.B = (float) (Math.atan2((double) f1, this.y) * 180.0D / 3.1415927410125732D) - 90.0F; this.B - this.D < -180.0F; this.D -= 360.0F) {
                ;
            }

            while (this.B - this.D >= 180.0F) {
                this.D += 360.0F;
            }

            while (this.A - this.C < -180.0F) {
                this.C -= 360.0F;
            }

            while (this.A - this.C >= 180.0F) {
                this.C += 360.0F;
            }

            this.B = this.D + (this.B - this.D) * 0.2F;
            this.A = this.C + (this.A - this.C) * 0.2F;
            float f2 = this.c();

            if (this.G()) {
                for (int k = 0; k < 4; ++k) {
                    float f3 = 0.25F;

                    this.q.a("bubble", this.u - this.x * (double) f3, this.v - this.y * (double) f3, this.w - this.z * (double) f3, this.x, this.y, this.z);
                }

                f2 = 0.8F;
            }

            this.x += this.b;
            this.y += this.c;
            this.z += this.d;
            this.x *= (double) f2;
            this.y *= (double) f2;
            this.z *= (double) f2;
            this.q.a("smoke", this.u, this.v + 0.5D, this.w, 0.0D, 0.0D, 0.0D);
            this.b(this.u, this.v, this.w);
        }
    }

    protected float c() {
        return 0.95F;
    }

    protected abstract void a(OMovingObjectPosition omovingobjectposition);

    public void b(ONBTTagCompound onbttagcompound) {
        onbttagcompound.a("xTile", (short) this.e);
        onbttagcompound.a("yTile", (short) this.f);
        onbttagcompound.a("zTile", (short) this.g);
        onbttagcompound.a("inTile", (byte) this.h);
        onbttagcompound.a("inGround", (byte) (this.i ? 1 : 0));
        onbttagcompound.a("direction", (ONBTBase) this.a(new double[] { this.x, this.y, this.z}));
    }

    public void a(ONBTTagCompound onbttagcompound) {
        this.e = onbttagcompound.d("xTile");
        this.f = onbttagcompound.d("yTile");
        this.g = onbttagcompound.d("zTile");
        this.h = onbttagcompound.c("inTile") & 255;
        this.i = onbttagcompound.c("inGround") == 1;
        if (onbttagcompound.b("direction")) {
            ONBTTagList onbttaglist = onbttagcompound.m("direction");

            this.x = ((ONBTTagDouble) onbttaglist.b(0)).a;
            this.y = ((ONBTTagDouble) onbttaglist.b(1)).a;
            this.z = ((ONBTTagDouble) onbttaglist.b(2)).a;
        } else {
            this.w();
        }
    }

    public boolean K() {
        return true;
    }

    public float X() {
        return 1.0F;
    }

    public boolean a(ODamageSource odamagesource, int i) {
        if (this.aq()) {
            return false;
        } else {
            this.J();
            if (odamagesource.i() != null) {
                OVec3 ovec3 = odamagesource.i().Y();

                if (ovec3 != null) {
                    this.x = ovec3.c;
                    this.y = ovec3.d;
                    this.z = ovec3.e;
                    this.b = this.x * 0.1D;
                    this.c = this.y * 0.1D;
                    this.d = this.z * 0.1D;
                }

                if (odamagesource.i() instanceof OEntityLiving) {
                    this.a = (OEntityLiving) odamagesource.i();
                }

                return true;
            } else {
                return false;
            }
        }
    }

    public float c(float f) {
        return 1.0F;
    }
}
