import java.util.List;

public class OEntityArrow extends OEntity implements OIProjectile {

    private int d = -1;
    private int e = -1;
    private int f = -1;
    private int g = 0;
    private int h = 0;
    private boolean i = false;
    public int a = 0;
    public int b = 0;
    public OEntity c;
    private int j;
    private int au = 0;
    private double av = 2.0D;
    private int aw;

    public OEntityArrow(OWorld oworld) {
        super(oworld);
        this.l = 10.0D;
        this.a(0.5F, 0.5F);
    }

    public OEntityArrow(OWorld oworld, double d0, double d1, double d2) {
        super(oworld);
        this.l = 10.0D;
        this.a(0.5F, 0.5F);
        this.b(d0, d1, d2);
        this.N = 0.0F;
    }

    public OEntityArrow(OWorld oworld, OEntityLiving oentityliving, OEntityLiving oentityliving1, float f, float f1) {
        super(oworld);
        this.l = 10.0D;
        this.c = oentityliving;
        if (oentityliving instanceof OEntityPlayer) {
            this.a = 1;
        }

        this.v = oentityliving.v + (double) oentityliving.e() - 0.10000000149011612D;
        double d0 = oentityliving1.u - oentityliving.u;
        double d1 = oentityliving1.E.b + (double) (oentityliving1.P / 3.0F) - this.v;
        double d2 = oentityliving1.w - oentityliving.w;
        double d3 = (double) OMathHelper.a(d0 * d0 + d2 * d2);

        if (d3 >= 1.0E-7D) {
            float f2 = (float) (Math.atan2(d2, d0) * 180.0D / 3.1415927410125732D) - 90.0F;
            float f3 = (float) (-(Math.atan2(d1, d3) * 180.0D / 3.1415927410125732D));
            double d4 = d0 / d3;
            double d5 = d2 / d3;

            this.b(oentityliving.u + d4, this.v, oentityliving.w + d5, f2, f3);
            this.N = 0.0F;
            float f4 = (float) d3 * 0.2F;

            this.c(d0, d1 + (double) f4, d2, f, f1);
        }
    }

    public OEntityArrow(OWorld oworld, OEntityLiving oentityliving, float f) {
        super(oworld);
        this.l = 10.0D;
        this.c = oentityliving;
        if (oentityliving instanceof OEntityPlayer) {
            this.a = 1;
        }

        this.a(0.5F, 0.5F);
        this.b(oentityliving.u, oentityliving.v + (double) oentityliving.e(), oentityliving.w, oentityliving.A, oentityliving.B);
        this.u -= (double) (OMathHelper.b(this.A / 180.0F * 3.1415927F) * 0.16F);
        this.v -= 0.10000000149011612D;
        this.w -= (double) (OMathHelper.a(this.A / 180.0F * 3.1415927F) * 0.16F);
        this.b(this.u, this.v, this.w);
        this.N = 0.0F;
        this.x = (double) (-OMathHelper.a(this.A / 180.0F * 3.1415927F) * OMathHelper.b(this.B / 180.0F * 3.1415927F));
        this.z = (double) (OMathHelper.b(this.A / 180.0F * 3.1415927F) * OMathHelper.b(this.B / 180.0F * 3.1415927F));
        this.y = (double) (-OMathHelper.a(this.B / 180.0F * 3.1415927F));
        this.c(this.x, this.y, this.z, f * 1.5F, 1.0F);
    }

    protected void a() {
        this.ah.a(16, Byte.valueOf((byte) 0));
    }

    public void c(double d0, double d1, double d2, float f, float f1) {
        float f2 = OMathHelper.a(d0 * d0 + d1 * d1 + d2 * d2);

        d0 /= (double) f2;
        d1 /= (double) f2;
        d2 /= (double) f2;
        d0 += this.ab.nextGaussian() * (double) (this.ab.nextBoolean() ? -1 : 1) * 0.007499999832361937D * (double) f1;
        d1 += this.ab.nextGaussian() * (double) (this.ab.nextBoolean() ? -1 : 1) * 0.007499999832361937D * (double) f1;
        d2 += this.ab.nextGaussian() * (double) (this.ab.nextBoolean() ? -1 : 1) * 0.007499999832361937D * (double) f1;
        d0 *= (double) f;
        d1 *= (double) f;
        d2 *= (double) f;
        this.x = d0;
        this.y = d1;
        this.z = d2;
        float f3 = OMathHelper.a(d0 * d0 + d2 * d2);

        this.C = this.A = (float) (Math.atan2(d0, d2) * 180.0D / 3.1415927410125732D);
        this.D = this.B = (float) (Math.atan2(d1, (double) f3) * 180.0D / 3.1415927410125732D);
        this.j = 0;
    }

    public void l_() {
        super.l_();
        if (this.D == 0.0F && this.C == 0.0F) {
            float f = OMathHelper.a(this.x * this.x + this.z * this.z);

            this.C = this.A = (float) (Math.atan2(this.x, this.z) * 180.0D / 3.1415927410125732D);
            this.D = this.B = (float) (Math.atan2(this.y, (double) f) * 180.0D / 3.1415927410125732D);
        }

        int i = this.q.a(this.d, this.e, this.f);

        if (i > 0) {
            OBlock.r[i].a((OIBlockAccess) this.q, this.d, this.e, this.f);
            OAxisAlignedBB oaxisalignedbb = OBlock.r[i].b(this.q, this.d, this.e, this.f);

            if (oaxisalignedbb != null && oaxisalignedbb.a(this.q.T().a(this.u, this.v, this.w))) {
                this.i = true;
            }
        }

        if (this.b > 0) {
            --this.b;
        }

        if (this.i) {
            int j = this.q.a(this.d, this.e, this.f);
            int k = this.q.h(this.d, this.e, this.f);

            if (j == this.g && k == this.h) {
                ++this.j;
                if (this.j == 1200) {
                    this.w();
                }
            } else {
                this.i = false;
                this.x *= (double) (this.ab.nextFloat() * 0.2F);
                this.y *= (double) (this.ab.nextFloat() * 0.2F);
                this.z *= (double) (this.ab.nextFloat() * 0.2F);
                this.j = 0;
                this.au = 0;
            }
        } else {
            ++this.au;
            OVec3 ovec3 = this.q.T().a(this.u, this.v, this.w);
            OVec3 ovec31 = this.q.T().a(this.u + this.x, this.v + this.y, this.w + this.z);
            OMovingObjectPosition omovingobjectposition = this.q.a(ovec3, ovec31, false, true);

            ovec3 = this.q.T().a(this.u, this.v, this.w);
            ovec31 = this.q.T().a(this.u + this.x, this.v + this.y, this.w + this.z);
            if (omovingobjectposition != null) {
                ovec31 = this.q.T().a(omovingobjectposition.f.c, omovingobjectposition.f.d, omovingobjectposition.f.e);
            }

            OEntity oentity = null;
            List list = this.q.b((OEntity) this, this.E.a(this.x, this.y, this.z).b(1.0D, 1.0D, 1.0D));
            double d0 = 0.0D;

            int l;
            float f1;

            for (l = 0; l < list.size(); ++l) {
                OEntity oentity1 = (OEntity) list.get(l);

                if (oentity1.K() && (oentity1 != this.c || this.au >= 5)) {
                    f1 = 0.3F;
                    OAxisAlignedBB oaxisalignedbb1 = oentity1.E.b((double) f1, (double) f1, (double) f1);
                    OMovingObjectPosition omovingobjectposition1 = oaxisalignedbb1.a(ovec3, ovec31);

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

            if (omovingobjectposition != null && omovingobjectposition.g != null && omovingobjectposition.g instanceof OEntityPlayer) {
                OEntityPlayer oentityplayer = (OEntityPlayer) omovingobjectposition.g;

                if (oentityplayer.ce.a || this.c instanceof OEntityPlayer && !((OEntityPlayer) this.c).a(oentityplayer)) {
                    omovingobjectposition = null;
                }
            }

            float f2;
            float f3;

            if (omovingobjectposition != null) {

                if (omovingobjectposition.g != null  && (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.PROJECTILE_HIT, new Arrow(this), omovingobjectposition == null || omovingobjectposition.g == null ? null : omovingobjectposition.g.getEntity()))) {
                    f2 = OMathHelper.a(this.x * this.x + this.y * this.y + this.z * this.z);
                    int i1 = OMathHelper.f((double) f2 * this.av);

                        if (this.d()) {
                        i1 += this.ab.nextInt(i1 / 2 + 2);
                        }

                        ODamageSource odamagesource = null;

                        if (this.c == null) {
                            odamagesource = ODamageSource.a(this, this);
                        } else {
                            odamagesource = ODamageSource.a(this, this.c);
                        }

                    if (this.ae() && !(omovingobjectposition.g instanceof OEntityEnderman)) {
                        omovingobjectposition.g.d(5);
                        }

                        if (omovingobjectposition.g.a(odamagesource, i1)) {
                            if (omovingobjectposition.g instanceof OEntityLiving) {
                                OEntityLiving oentityliving = (OEntityLiving) omovingobjectposition.g;

                            if (!this.q.I) {
                                oentityliving.r(oentityliving.bM() + 1);
                                }

                            if (this.aw > 0) {
                                f3 = OMathHelper.a(this.x * this.x + this.z * this.z);
                                    if (f3 > 0.0F) {
                                    omovingobjectposition.g.g(this.x * (double) this.aw * 0.6000000238418579D / (double) f3, 0.1D, this.z * (double) this.aw * 0.6000000238418579D / (double) f3);
                                    }
                                }

                                if (this.c != null) {
                                OEnchantmentThorns.a(this.c, oentityliving, this.ab);
                                }

                                if (this.c != null && omovingobjectposition.g != this.c && omovingobjectposition.g instanceof OEntityPlayer && this.c instanceof OEntityPlayerMP) {
                                    ((OEntityPlayerMP) this.c).a.b(new OPacket70GameEvent(6, 0));
                                }
                            }

                        this.a("random.bowhit", 1.0F, 1.2F / (this.ab.nextFloat() * 0.2F + 0.9F));
                            if (!(omovingobjectposition.g instanceof OEntityEnderman)) {
                            this.w();
                            }
                        } else {
                            this.x *= -0.10000000149011612D;
                            this.y *= -0.10000000149011612D;
                        this.z *= -0.10000000149011612D;
                        this.A += 180.0F;
                        this.C += 180.0F;
                        this.au = 0;
                        }
                } else {
                    this.d = omovingobjectposition.b;
                    this.e = omovingobjectposition.c;
                    this.f = omovingobjectposition.d;
                    this.g = this.q.a(this.d, this.e, this.f);
                    this.h = this.q.h(this.d, this.e, this.f);
                    this.x = (double) ((float) (omovingobjectposition.f.c - this.u));
                    this.y = (double) ((float) (omovingobjectposition.f.d - this.v));
                    this.z = (double) ((float) (omovingobjectposition.f.e - this.w));
                    f2 = OMathHelper.a(this.x * this.x + this.y * this.y + this.z * this.z);
                    this.u -= this.x / (double) f2 * 0.05000000074505806D;
                    this.v -= this.y / (double) f2 * 0.05000000074505806D;
                    this.w -= this.z / (double) f2 * 0.05000000074505806D;
                    this.a("random.bowhit", 1.0F, 1.2F / (this.ab.nextFloat() * 0.2F + 0.9F));
                    this.i = true;
                    this.b = 7;
                    this.a(false);
                    if (this.g != 0) {
                        OBlock.r[this.g].a(this.q, this.d, this.e, this.f, (OEntity) this);
                    }
                }
            }

            if (this.d()) {
                for (l = 0; l < 4; ++l) {
                    this.q.a("crit", this.u + this.x * (double) l / 4.0D, this.v + this.y * (double) l / 4.0D, this.w + this.z * (double) l / 4.0D, -this.x, -this.y + 0.2D, -this.z);
                }
            }

            this.u += this.x;
            this.v += this.y;
            this.w += this.z;
            f2 = OMathHelper.a(this.x * this.x + this.z * this.z);
            this.A = (float) (Math.atan2(this.x, this.z) * 180.0D / 3.1415927410125732D);

            for (this.B = (float) (Math.atan2(this.y, (double) f2) * 180.0D / 3.1415927410125732D); this.B - this.D < -180.0F; this.D -= 360.0F) {
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
            float f4 = 0.99F;

            f1 = 0.05F;
            if (this.G()) {
                for (int j1 = 0; j1 < 4; ++j1) {
                    f3 = 0.25F;
                    this.q.a("bubble", this.u - this.x * (double) f3, this.v - this.y * (double) f3, this.w - this.z * (double) f3, this.x, this.y, this.z);
                }

                f4 = 0.8F;
            }

            this.x *= (double) f4;
            this.y *= (double) f4;
            this.z *= (double) f4;
            this.y -= (double) f1;
            this.b(this.u, this.v, this.w);
            this.C();
        }
    }

    public void b(ONBTTagCompound onbttagcompound) {
        onbttagcompound.a("xTile", (short) this.d);
        onbttagcompound.a("yTile", (short) this.e);
        onbttagcompound.a("zTile", (short) this.f);
        onbttagcompound.a("inTile", (byte) this.g);
        onbttagcompound.a("inData", (byte) this.h);
        onbttagcompound.a("shake", (byte) this.b);
        onbttagcompound.a("inGround", (byte) (this.i ? 1 : 0));
        onbttagcompound.a("pickup", (byte) this.a);
        onbttagcompound.a("damage", this.av);
    }

    public void a(ONBTTagCompound onbttagcompound) {
        this.d = onbttagcompound.d("xTile");
        this.e = onbttagcompound.d("yTile");
        this.f = onbttagcompound.d("zTile");
        this.g = onbttagcompound.c("inTile") & 255;
        this.h = onbttagcompound.c("inData") & 255;
        this.b = onbttagcompound.c("shake") & 255;
        this.i = onbttagcompound.c("inGround") == 1;
        if (onbttagcompound.b("damage")) {
            this.av = onbttagcompound.h("damage");
        }

        if (onbttagcompound.b("pickup")) {
            this.a = onbttagcompound.c("pickup");
        } else if (onbttagcompound.b("player")) {
            this.a = onbttagcompound.n("player") ? 1 : 0;
        }
    }

    public void b_(OEntityPlayer oentityplayer) {
        if (!this.q.I && this.i && this.b <= 0) {
            boolean flag = this.a == 1 || this.a == 2 && oentityplayer.ce.d;

            if (this.a == 1 && !oentityplayer.bK.a(new OItemStack(OItem.m, 1))) {
                flag = false;
            }

            if (flag) {
                this.a("random.pop", 0.2F, ((this.ab.nextFloat() - this.ab.nextFloat()) * 0.7F + 1.0F) * 2.0F);
                oentityplayer.a((OEntity) this, 1);
                this.w();
            }
        }
    }

    protected boolean f_() {
        return false;
    }

    public void b(double d0) {
        this.av = d0;
    }

    public double c() {
        return this.av;
    }

    public void a(int i) {
        this.aw = i;
    }

    public boolean ap() {
        return false;
    }

    public void a(boolean flag) {
        byte b0 = this.ah.a(16);

        if (flag) {
            this.ah.b(16, Byte.valueOf((byte) (b0 | 1)));
        } else {
            this.ah.b(16, Byte.valueOf((byte) (b0 & -2)));
        }
    }

    public boolean d() {
        byte b0 = this.ah.a(16);

        return (b0 & 1) != 0;
    }
}
