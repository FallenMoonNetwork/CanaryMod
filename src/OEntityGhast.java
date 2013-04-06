public class OEntityGhast extends OEntityFlying implements OIMob {

    public int b = 0;
    public double c;
    public double d;
    public double e;
    private OEntity h = null;
    private int i = 0;
    public int f = 0;
    public int g = 0;
    private int j = 1;

    public OEntityGhast(OWorld oworld) {
        super(oworld);
        this.aH = "/mob/ghast.png";
        this.a(4.0F, 4.0F);
        this.ag = true;
        this.be = 5;
    }

    public boolean a(ODamageSource odamagesource, int i) {
        if (this.aq()) {
            return false;
        } else if ("fireball".equals(odamagesource.n()) && odamagesource.i() instanceof OEntityPlayer) {
            super.a(odamagesource, 1000);
            ((OEntityPlayer) odamagesource.i()).a((OStatBase) OAchievementList.y);
            return true;
        } else {
            return super.a(odamagesource, i);
        }
    }

    protected void a() {
        super.a();
        this.ah.a(16, Byte.valueOf((byte) 0));
    }

    public int aW() {
        //CanaryMod: set max health here, but check for uninitialized value.
        return this.maxHealth == 0 ? 10 : this.maxHealth;
    }

    public void l_() {
        super.l_();
        byte b0 = this.ah.a(16);

        this.aH = b0 == 1 ? "/mob/ghast_fire.png" : "/mob/ghast.png";
    }

    protected void bq() {
        if (!this.q.I && this.q.r == 0) {
            this.w();
        }

        this.bn();
        this.f = this.g;
        double d0 = this.c - this.u;
        double d1 = this.d - this.v;
        double d2 = this.e - this.w;
        double d3 = d0 * d0 + d1 * d1 + d2 * d2;

        if (d3 < 1.0D || d3 > 3600.0D) {
            this.c = this.u + (double) ((this.ab.nextFloat() * 2.0F - 1.0F) * 16.0F);
            this.d = this.v + (double) ((this.ab.nextFloat() * 2.0F - 1.0F) * 16.0F);
            this.e = this.w + (double) ((this.ab.nextFloat() * 2.0F - 1.0F) * 16.0F);
        }

        if (this.b-- <= 0) {
            this.b += this.ab.nextInt(5) + 2;
            d3 = (double) OMathHelper.a(d3);
            if (this.a(this.c, this.d, this.e, d3)) {
                this.x += d0 / d3 * 0.1D;
                this.y += d1 / d3 * 0.1D;
                this.z += d2 / d3 * 0.1D;
            } else {
                this.c = this.u;
                this.d = this.v;
                this.e = this.w;
            }
        }

        if (this.h != null && this.h.M) {
            this.h = null;
        }

        if (this.h == null || this.i-- <= 0) {
            this.h = this.q.b(this, 100.0D);
            if (this.h != null) {
                this.i = 20;
            }
            OEntityPlayer oentityplayer = this.q.b(this, 100.0D);

            // CanaryMod: MOB_TARGET Hook for ghasts.
            if (oentityplayer != null && !(Boolean) etc.getLoader().callHook(PluginLoader.Hook.MOB_TARGET, oentityplayer.getEntity(), this.getEntity())) {
                this.h = oentityplayer;
            }
            if (this.h != null) {
                this.i = 20;
            }
        }

        double d4 = 64.0D;

        if (this.h != null && this.h.e((OEntity) this) < d4 * d4) {
            double d5 = this.h.u - this.u;
            double d6 = this.h.E.b + (double) (this.h.P / 2.0F) - (this.v + (double) (this.P / 2.0F));
            double d7 = this.h.w - this.w;

            this.ay = this.A = -((float) Math.atan2(d5, d7)) * 180.0F / 3.1415927F;
            if (this.n(this.h)) {
                if (this.g == 10) {
                    this.q.a((OEntityPlayer) null, 1007, (int) this.u, (int) this.v, (int) this.w, 0);
                }

                ++this.g;
                if (this.g == 20) {
                    this.q.a((OEntityPlayer) null, 1008, (int) this.u, (int) this.v, (int) this.w, 0);
                    OEntityLargeFireball oentitylargefireball = new OEntityLargeFireball(this.q, this, d5, d6, d7);

                    oentitylargefireball.e = this.j;
                    double d8 = 4.0D;
                    OVec3 ovec3 = this.i(1.0F);

                    oentitylargefireball.u = this.u + ovec3.c * d8;
                    oentitylargefireball.v = this.v + (double) (this.P / 2.0F) + 0.5D;
                    oentitylargefireball.w = this.w + ovec3.e * d8;
                    this.q.d((OEntity) oentitylargefireball);
                    this.g = -40;
                }
            } else if (this.g > 0) {
                --this.g;
            }
        } else {
            this.ay = this.A = -((float) Math.atan2(this.x, this.z)) * 180.0F / 3.1415927F;
            if (this.g > 0) {
                --this.g;
            }
        }

        if (!this.q.I) {
            byte b0 = this.ah.a(16);
            byte b1 = (byte) (this.g > 10 ? 1 : 0);

            if (b0 != b1) {
                this.ah.b(16, Byte.valueOf(b1));
            }
        }
    }

    private boolean a(double d0, double d1, double d2, double d3) {
        double d4 = (this.c - this.u) / d3;
        double d5 = (this.d - this.v) / d3;
        double d6 = (this.e - this.w) / d3;
        OAxisAlignedBB oaxisalignedbb = this.E.c();

        for (int i = 1; (double) i < d3; ++i) {
            oaxisalignedbb.d(d4, d5, d6);
            if (!this.q.a((OEntity) this, oaxisalignedbb).isEmpty()) {
                return false;
            }
        }

        return true;
    }

    protected String bb() {
        return "mob.ghast.moan";
    }

    protected String bc() {
        return "mob.ghast.scream";
    }

    protected String bd() {
        return "mob.ghast.death";
    }

    protected int be() {
        return OItem.N.cp;
    }

    protected void a(boolean flag, int i) {
        int j = this.ab.nextInt(2) + this.ab.nextInt(1 + i);

        int k;

        for (k = 0; k < j; ++k) {
            this.b(OItem.bq.cp, 1);
        }

        j = this.ab.nextInt(3) + this.ab.nextInt(1 + i);

        for (k = 0; k < j; ++k) {
            this.b(OItem.N.cp, 1);
        }
    }

    protected float ba() {
        return 10.0F;
    }

    public boolean bv() {
        return this.ab.nextInt(20) == 0 && super.bv() && this.q.r > 0;
    }

    public int by() {
        return 1;
    }

    public void b(ONBTTagCompound onbttagcompound) {
        super.b(onbttagcompound);
        onbttagcompound.a("ExplosionPower", this.j);
    }

    public void a(ONBTTagCompound onbttagcompound) {
        super.a(onbttagcompound);
        if (onbttagcompound.b("ExplosionPower")) {
            this.j = onbttagcompound.e("ExplosionPower");
        }
    }

    public void setTarget(OEntity oentity) {
        this.h = oentity;
    }

    public OEntity getTarget() {
        return this.h;
    }
}
