import java.util.List;

public abstract class OEntityThrowable extends OEntity implements OIProjectile {

    private int c = -1;
    private int d = -1;
    private int e = -1;
    private int f = 0;
    protected boolean a = false;
    public int b = 0;
    private OEntityLiving g;
    private String h = null;
    private int i;
    private int j = 0;

    public OEntityThrowable(OWorld oworld) {
        super(oworld);
        this.a(0.25F, 0.25F);
    }

    protected void a() {}

    public OEntityThrowable(OWorld oworld, OEntityLiving oentityliving) {
        super(oworld);
        this.g = oentityliving;
        this.a(0.25F, 0.25F);
        this.b(oentityliving.u, oentityliving.v + (double) oentityliving.e(), oentityliving.w, oentityliving.A, oentityliving.B);
        this.u -= (double) (OMathHelper.b(this.A / 180.0F * 3.1415927F) * 0.16F);
        this.v -= 0.10000000149011612D;
        this.w -= (double) (OMathHelper.a(this.A / 180.0F * 3.1415927F) * 0.16F);
        this.b(this.u, this.v, this.w);
        this.N = 0.0F;
        float f = 0.4F;

        this.x = (double) (-OMathHelper.a(this.A / 180.0F * 3.1415927F) * OMathHelper.b(this.B / 180.0F * 3.1415927F) * f);
        this.z = (double) (OMathHelper.b(this.A / 180.0F * 3.1415927F) * OMathHelper.b(this.B / 180.0F * 3.1415927F) * f);
        this.y = (double) (-OMathHelper.a((this.B + this.d()) / 180.0F * 3.1415927F) * f);
        this.c(this.x, this.y, this.z, this.c(), 1.0F);
    }

    public OEntityThrowable(OWorld oworld, double d0, double d1, double d2) {
        super(oworld);
        this.i = 0;
        this.a(0.25F, 0.25F);
        this.b(d0, d1, d2);
        this.N = 0.0F;
    }

    protected float c() {
        return 1.5F;
    }

    protected float d() {
        return 0.0F;
    }

    public void c(double d0, double d1, double d2, float f, float f1) {
        float f2 = OMathHelper.a(d0 * d0 + d1 * d1 + d2 * d2);

        d0 /= (double) f2;
        d1 /= (double) f2;
        d2 /= (double) f2;
        d0 += this.ab.nextGaussian() * 0.007499999832361937D * (double) f1;
        d1 += this.ab.nextGaussian() * 0.007499999832361937D * (double) f1;
        d2 += this.ab.nextGaussian() * 0.007499999832361937D * (double) f1;
        d0 *= (double) f;
        d1 *= (double) f;
        d2 *= (double) f;
        this.x = d0;
        this.y = d1;
        this.z = d2;
        float f3 = OMathHelper.a(d0 * d0 + d2 * d2);

        this.C = this.A = (float) (Math.atan2(d0, d2) * 180.0D / 3.1415927410125732D);
        this.D = this.B = (float) (Math.atan2(d1, (double) f3) * 180.0D / 3.1415927410125732D);
        this.i = 0;
    }

    public void l_() {
        this.U = this.u;
        this.V = this.v;
        this.W = this.w;
        super.l_();
        if (this.b > 0) {
            --this.b;
        }

        if (this.a) {
            int i = this.q.a(this.c, this.d, this.e);

            if (i == this.f) {
                ++this.i;
                if (this.i == 1200) {
                    this.w();
                }

                return;
            }

            this.a = false;
            this.x *= (double) (this.ab.nextFloat() * 0.2F);
            this.y *= (double) (this.ab.nextFloat() * 0.2F);
            this.z *= (double) (this.ab.nextFloat() * 0.2F);
            this.i = 0;
            this.j = 0;
        } else {
            ++this.j;
        }

        OVec3 ovec3 = this.q.T().a(this.u, this.v, this.w);
        OVec3 ovec31 = this.q.T().a(this.u + this.x, this.v + this.y, this.w + this.z);
        OMovingObjectPosition omovingobjectposition = this.q.a(ovec3, ovec31);

        ovec3 = this.q.T().a(this.u, this.v, this.w);
        ovec31 = this.q.T().a(this.u + this.x, this.v + this.y, this.w + this.z);
        if (omovingobjectposition != null) {
            ovec31 = this.q.T().a(omovingobjectposition.f.c, omovingobjectposition.f.d, omovingobjectposition.f.e);
        }

        if (!this.q.I) {
            OEntity oentity = null;
            List list = this.q.b((OEntity) this, this.E.a(this.x, this.y, this.z).b(1.0D, 1.0D, 1.0D));
            double d0 = 0.0D;
            OEntityLiving oentityliving = this.h();

            for (int j = 0; j < list.size(); ++j) {
                OEntity oentity1 = (OEntity) list.get(j);

                if (oentity1.K() && (oentity1 != oentityliving || this.j >= 5)) {
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
        }

        if (omovingobjectposition != null) {
            if (omovingobjectposition.a == OEnumMovingObjectType.a && this.q.a(omovingobjectposition.b, omovingobjectposition.c, omovingobjectposition.d) == OBlock.bi.cz) {
                this.Z();
            } else {
                this.a(omovingobjectposition);
            }
        }

        this.u += this.x;
        this.v += this.y;
        this.w += this.z;
        float f1 = OMathHelper.a(this.x * this.x + this.z * this.z);

        this.A = (float) (Math.atan2(this.x, this.z) * 180.0D / 3.1415927410125732D);

        for (this.B = (float) (Math.atan2(this.y, (double) f1) * 180.0D / 3.1415927410125732D); this.B - this.D < -180.0F; this.D -= 360.0F) {
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
        float f2 = 0.99F;
        float f3 = this.g();

        if (this.G()) {
            for (int k = 0; k < 4; ++k) {
                float f4 = 0.25F;

                this.q.a("bubble", this.u - this.x * (double) f4, this.v - this.y * (double) f4, this.w - this.z * (double) f4, this.x, this.y, this.z);
            }

            f2 = 0.8F;
        }

        this.x *= (double) f2;
        this.y *= (double) f2;
        this.z *= (double) f2;
        this.y -= (double) f3;
        this.b(this.u, this.v, this.w);
    }

    protected float g() {
        return 0.03F;
    }

    protected abstract void a(OMovingObjectPosition omovingobjectposition);

    public void b(ONBTTagCompound onbttagcompound) {
        onbttagcompound.a("xTile", (short) this.c);
        onbttagcompound.a("yTile", (short) this.d);
        onbttagcompound.a("zTile", (short) this.e);
        onbttagcompound.a("inTile", (byte) this.f);
        onbttagcompound.a("shake", (byte) this.b);
        onbttagcompound.a("inGround", (byte) (this.a ? 1 : 0));
        if ((this.h == null || this.h.length() == 0) && this.g != null && this.g instanceof OEntityPlayer) {
            this.h = this.g.am();
        }

        onbttagcompound.a("ownerName", this.h == null ? "" : this.h);
    }

    public void a(ONBTTagCompound onbttagcompound) {
        this.c = onbttagcompound.d("xTile");
        this.d = onbttagcompound.d("yTile");
        this.e = onbttagcompound.d("zTile");
        this.f = onbttagcompound.c("inTile") & 255;
        this.b = onbttagcompound.c("shake") & 255;
        this.a = onbttagcompound.c("inGround") == 1;
        this.h = onbttagcompound.i("ownerName");
        if (this.h != null && this.h.length() == 0) {
            this.h = null;
        }
    }

    public OEntityLiving h() {
        if (this.g == null && this.h != null && this.h.length() > 0) {
            this.g = this.q.a(this.h);
        }

        return this.g;
    }

    public void setShooter(OEntityLiving shooter) { //CanaryMod: method for setting the shooter
        this.g = shooter;
        if (shooter instanceof OEntityPlayer) {
            this.h = ((OEntityPlayer) shooter).getEntity().getName();
        }
    }
}
