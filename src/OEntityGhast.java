
public class OEntityGhast extends OEntityFlying implements OIMob {

    public int b = 0;
    public double c;
    public double d;
    public double e;
    private OEntity h = null;
    private int i = 0;
    public int f = 0;
    public int g = 0;

    public OEntityGhast(OWorld oworld) {
        super(oworld);
        this.aF = "/mob/ghast.png";
        this.a(4.0F, 4.0F);
        this.af = true;
        this.bc = 5;
    }

    public boolean a(ODamageSource odamagesource, int i) {
        if ("fireball".equals(odamagesource.l()) && odamagesource.g() instanceof OEntityPlayer) {
            super.a(odamagesource, 1000);
            ((OEntityPlayer) odamagesource.g()).a((OStatBase) OAchievementList.y);
            return true;
        } else {
            return super.a(odamagesource, i);
        }
    }

    protected void a() {
        super.a();
        this.ag.a(16, Byte.valueOf((byte) 0));
    }

    public int aS() {
        return 10;
    }

    public void j_() {
        super.j_();
        byte b0 = this.ag.a(16);

        this.aF = b0 == 1 ? "/mob/ghast_fire.png" : "/mob/ghast.png";
    }

    protected void bk() {
        if (!this.p.J && this.p.t == 0) {
            this.x();
        }

        this.bh();
        this.f = this.g;
        double d0 = this.c - this.t;
        double d1 = this.d - this.u;
        double d2 = this.e - this.v;
        double d3 = d0 * d0 + d1 * d1 + d2 * d2;

        if (d3 < 1.0D || d3 > 3600.0D) {
            this.c = this.t + (double) ((this.aa.nextFloat() * 2.0F - 1.0F) * 16.0F);
            this.d = this.u + (double) ((this.aa.nextFloat() * 2.0F - 1.0F) * 16.0F);
            this.e = this.v + (double) ((this.aa.nextFloat() * 2.0F - 1.0F) * 16.0F);
        }

        if (this.b-- <= 0) {
            this.b += this.aa.nextInt(5) + 2;
            d3 = (double) OMathHelper.a(d3);
            if (this.a(this.c, this.d, this.e, d3)) {
                this.w += d0 / d3 * 0.1D;
                this.x += d1 / d3 * 0.1D;
                this.y += d2 / d3 * 0.1D;
            } else {
                this.c = this.t;
                this.d = this.u;
                this.e = this.v;
            }
        }

        if (this.h != null && this.h.L) {
            this.h = null;
        }

        if (this.h == null || this.i-- <= 0) {
            OEntityPlayer oentityplayer = this.p.b(this, 100.0D);

            // CanaryMod: MOB_TARGET Hook for ghasts.
            if (oentityplayer != null && !(Boolean) etc.getLoader().callHook(PluginLoader.Hook.MOB_TARGET, oentityplayer.entity.getPlayer(), this.entity)) {
                this.h = oentityplayer;
            }
            if (this.h != null) {
                this.i = 20;
            }
        }

        double d4 = 64.0D;

        if (this.h != null && this.h.e((OEntity) this) < d4 * d4) {
            double d5 = this.h.t - this.t;
            double d6 = this.h.D.b + (double) (this.h.O / 2.0F) - (this.u + (double) (this.O / 2.0F));
            double d7 = this.h.v - this.v;

            this.aw = this.z = -((float) Math.atan2(d5, d7)) * 180.0F / 3.1415927F;
            if (this.m(this.h)) {
                if (this.g == 10) {
                    this.p.a((OEntityPlayer) null, 1007, (int) this.t, (int) this.u, (int) this.v, 0);
                }

                ++this.g;
                if (this.g == 20) {
                    this.p.a((OEntityPlayer) null, 1008, (int) this.t, (int) this.u, (int) this.v, 0);
                    OEntityLargeFireball oentitylargefireball = new OEntityLargeFireball(this.p, this, d5, d6, d7);
                    double d8 = 4.0D;
                    OVec3 ovec3 = this.i(1.0F);

                    oentitylargefireball.t = this.t + ovec3.c * d8;
                    oentitylargefireball.u = this.u + (double) (this.O / 2.0F) + 0.5D;
                    oentitylargefireball.v = this.v + ovec3.e * d8;
                    this.p.d((OEntity) oentitylargefireball);
                    this.g = -40;
                }
            } else if (this.g > 0) {
                --this.g;
            }
        } else {
            this.aw = this.z = -((float) Math.atan2(this.w, this.y)) * 180.0F / 3.1415927F;
            if (this.g > 0) {
                --this.g;
            }
        }

        if (!this.p.J) {
            byte b0 = this.ag.a(16);
            byte b1 = (byte) (this.g > 10 ? 1 : 0);

            if (b0 != b1) {
                this.ag.b(16, Byte.valueOf(b1));
            }
        }

    }

    private boolean a(double d0, double d1, double d2, double d3) {
        double d4 = (this.c - this.t) / d3;
        double d5 = (this.d - this.u) / d3;
        double d6 = (this.e - this.v) / d3;
        OAxisAlignedBB oaxisalignedbb = this.D.c();

        for (int i = 1; (double) i < d3; ++i) {
            oaxisalignedbb.d(d4, d5, d6);
            if (!this.p.a((OEntity) this, oaxisalignedbb).isEmpty()) {
                return false;
            }
        }

        return true;
    }

    protected String aW() {
        return "mob.ghast.moan";
    }

    protected String aX() {
        return "mob.ghast.scream";
    }

    protected String aY() {
        return "mob.ghast.death";
    }

    protected int aZ() {
        return OItem.M.cf;
    }

    protected void a(boolean flag, int i) {
        int j = this.aa.nextInt(2) + this.aa.nextInt(1 + i);

        int k;

        for (k = 0; k < j; ++k) {
            this.b(OItem.bp.cf, 1);
        }

        j = this.aa.nextInt(3) + this.aa.nextInt(1 + i);

        for (k = 0; k < j; ++k) {
            this.b(OItem.M.cf, 1);
        }

    }

    protected float aV() {
        return 10.0F;
    }

    public boolean bp() {
        return this.aa.nextInt(20) == 0 && super.bp() && this.p.t > 0;
    }

    public int bs() {
        return 1;
    }

    public void setTarget(OEntity oentity) {
        this.h = oentity;
    }

    public OEntity getTarget() {
        return this.h;
    }
}
