public class OEntityGhast extends OEntityFlying implements OIMob {

    public int h;
    public double i;
    public double j;
    public double bn;
    private OEntity bq;
    private int br;
    public int bo;
    public int bp;
    private int bs = 1;

    public OEntityGhast(OWorld oworld) {
        super(oworld);
        this.a(4.0F, 4.0F);
        this.ag = true;
        this.b = 5;
    }

    public boolean a(ODamageSource odamagesource, float f) {
        if (this.ar()) {
            return false;
        } else if ("fireball".equals(odamagesource.n()) && odamagesource.i() instanceof OEntityPlayer) {
            super.a(odamagesource, 1000.0F);
            ((OEntityPlayer) odamagesource.i()).a((OStatBase) OAchievementList.y);
            return true;
        } else {
            return super.a(odamagesource, f);
        }
    }

    protected void a() {
        super.a();
        this.ah.a(16, Byte.valueOf((byte) 0));
    }

    protected void az() {
        super.az();
        this.a(OSharedMonsterAttributes.a).a(10.0D);
    }

    protected void bl() {
        if (!this.q.I && this.q.r == 0) {
            this.x();
        }

        this.u();
        this.bo = this.bp;
        double d0 = this.i - this.u;
        double d1 = this.j - this.v;
        double d2 = this.bn - this.w;
        double d3 = d0 * d0 + d1 * d1 + d2 * d2;

        if (d3 < 1.0D || d3 > 3600.0D) {
            this.i = this.u + (double) ((this.ab.nextFloat() * 2.0F - 1.0F) * 16.0F);
            this.j = this.v + (double) ((this.ab.nextFloat() * 2.0F - 1.0F) * 16.0F);
            this.bn = this.w + (double) ((this.ab.nextFloat() * 2.0F - 1.0F) * 16.0F);
        }

        if (this.h-- <= 0) {
            this.h += this.ab.nextInt(5) + 2;
            d3 = (double) OMathHelper.a(d3);
            if (this.a(this.i, this.j, this.bn, d3)) {
                this.x += d0 / d3 * 0.1D;
                this.y += d1 / d3 * 0.1D;
                this.z += d2 / d3 * 0.1D;
            } else {
                this.i = this.u;
                this.j = this.v;
                this.bn = this.w;
            }
        }

        if (this.bq != null && this.bq.M) {
            this.bq = null;
        }

        if (this.bq == null || this.br-- <= 0) {
            OEntityPlayer oentityplayer = this.q.b(this, 100.0D);

            // CanaryMod: MOB_TARGET Hook for ghasts.
            if (oentityplayer != null && !(Boolean) etc.getLoader().callHook(PluginLoader.Hook.MOB_TARGET, oentityplayer.getEntity(), this.getEntity())) {
                this.bq = oentityplayer;
            }
            if (this.bq != null) {
                this.br = 20;
            }
        }

        double d4 = 64.0D;

        if (this.bq != null && this.bq.e((OEntity) this) < d4 * d4) {
            double d5 = this.bq.u - this.u;
            double d6 = this.bq.E.b + (double) (this.bq.P / 2.0F) - (this.v + (double) (this.P / 2.0F));
            double d7 = this.bq.w - this.w;

            this.aN = this.A = -((float) Math.atan2(d5, d7)) * 180.0F / 3.1415927F;
            if (this.o(this.bq)) {
                if (this.bp == 10) {
                    this.q.a((OEntityPlayer) null, 1007, (int) this.u, (int) this.v, (int) this.w, 0);
                }

                ++this.bp;
                if (this.bp == 20) {
                    this.q.a((OEntityPlayer) null, 1008, (int) this.u, (int) this.v, (int) this.w, 0);
                    OEntityLargeFireball oentitylargefireball = new OEntityLargeFireball(this.q, this, d5, d6, d7);

                    oentitylargefireball.e = this.bs;
                    double d8 = 4.0D;
                    OVec3 ovec3 = this.j(1.0F);

                    oentitylargefireball.u = this.u + ovec3.c * d8;
                    oentitylargefireball.v = this.v + (double) (this.P / 2.0F) + 0.5D;
                    oentitylargefireball.w = this.w + ovec3.e * d8;
                    this.q.d((OEntity) oentitylargefireball);
                    this.bp = -40;
                }
            } else if (this.bp > 0) {
                --this.bp;
            }
        } else {
            this.aN = this.A = -((float) Math.atan2(this.x, this.z)) * 180.0F / 3.1415927F;
            if (this.bp > 0) {
                --this.bp;
            }
        }

        if (!this.q.I) {
            byte b0 = this.ah.a(16);
            byte b1 = (byte) (this.bp > 10 ? 1 : 0);

            if (b0 != b1) {
                this.ah.b(16, Byte.valueOf(b1));
            }
        }
    }

    private boolean a(double d0, double d1, double d2, double d3) {
        double d4 = (this.i - this.u) / d3;
        double d5 = (this.j - this.v) / d3;
        double d6 = (this.bn - this.w) / d3;
        OAxisAlignedBB oaxisalignedbb = this.E.c();

        for (int i = 1; (double) i < d3; ++i) {
            oaxisalignedbb.d(d4, d5, d6);
            if (!this.q.a((OEntity) this, oaxisalignedbb).isEmpty()) {
                return false;
            }
        }

        return true;
    }

    protected String r() {
        return "mob.ghast.moan";
    }

    protected String aO() {
        return "mob.ghast.scream";
    }

    protected String aP() {
        return "mob.ghast.death";
    }

    protected int s() {
        return OItem.O.cv;
    }

    protected void b(boolean flag, int i) {
        int j = this.ab.nextInt(2) + this.ab.nextInt(1 + i);

        int k;

        for (k = 0; k < j; ++k) {
            this.b(OItem.br.cv, 1);
        }

        j = this.ab.nextInt(3) + this.ab.nextInt(1 + i);

        for (k = 0; k < j; ++k) {
            this.b(OItem.O.cv, 1);
        }
    }

    protected float ba() {
        return 10.0F;
    }

    public boolean bs() {
        return this.ab.nextInt(20) == 0 && super.bs() && this.q.r > 0;
    }

    public int bv() {
        return 1;
    }

    public void b(ONBTTagCompound onbttagcompound) {
        super.b(onbttagcompound);
        onbttagcompound.a("ExplosionPower", this.bs);
    }

    public void a(ONBTTagCompound onbttagcompound) {
        super.a(onbttagcompound);
        if (onbttagcompound.b("ExplosionPower")) {
            this.bs = onbttagcompound.e("ExplosionPower");
        }
    }

    public void setTarget(OEntity oentity) {
        this.bq = oentity;
    }

    public OEntity getTarget() {
        return this.bq;
    }
}
