public class OEntitySlime extends OEntityLiving implements OIMob {

    private static final float[] e = new float[] { 1.0F, 0.75F, 0.5F, 0.25F, 0.0F, 0.25F, 0.5F, 0.75F};
    public float b;
    public float c;
    public float d;
    private int f = 0;

    public OEntitySlime(OWorld oworld) {
        super(oworld);
        this.aH = "/mob/slime.png";
        int i = 1 << this.ab.nextInt(3);

        this.N = 0.0F;
        this.f = this.ab.nextInt(20) + 10;
        this.a(i);
    }

    protected void a() {
        super.a();
        this.ah.a(16, new Byte((byte) 1));
    }

    protected void a(int i) {
        this.ah.b(16, new Byte((byte) i));
        this.a(0.6F * (float) i, 0.6F * (float) i);
        this.b(this.u, this.v, this.w);
        this.b(this.aW());
        this.be = i;
    }

    public int aW() {
        int i = this.p();
        return this.maxHealth == 0 ? (i * i) : this.maxHealth;
    }

    public int p() {
        return this.ah.a(16);
    }

    public void b(ONBTTagCompound onbttagcompound) {
        super.b(onbttagcompound);
        onbttagcompound.a("Size", this.p() - 1);
    }

    public void a(ONBTTagCompound onbttagcompound) {
        super.a(onbttagcompound);
        this.a(onbttagcompound.e("Size") + 1);
    }

    protected String h() {
        return "slime";
    }

    protected String n() {
        return "mob.slime." + (this.p() > 1 ? "big" : "small");
    }

    public void l_() {
        if (!this.q.I && this.q.r == 0 && this.p() > 0) {
            this.M = true;
        }

        this.c += (this.b - this.c) * 0.5F;
        this.d = this.c;
        boolean flag = this.F;

        super.l_();
        int i;

        if (this.F && !flag) {
            i = this.p();

            for (int j = 0; j < i * 8; ++j) {
                float f = this.ab.nextFloat() * 3.1415927F * 2.0F;
                float f1 = this.ab.nextFloat() * 0.5F + 0.5F;
                float f2 = OMathHelper.a(f) * (float) i * 0.5F * f1;
                float f3 = OMathHelper.b(f) * (float) i * 0.5F * f1;

                this.q.a(this.h(), this.u + (double) f2, this.E.b, this.w + (double) f3, 0.0D, 0.0D, 0.0D);
            }

            if (this.o()) {
                this.a(this.n(), this.ba(), ((this.ab.nextFloat() - this.ab.nextFloat()) * 0.2F + 1.0F) / 0.8F);
            }

            this.b = -0.5F;
        } else if (!this.F && flag) {
            this.b = 1.0F;
        }

        this.k();
        if (this.q.I) {
            i = this.p();
            this.a(0.6F * (float) i, 0.6F * (float) i);
        }
    }

    protected void bq() {
        this.bn();
        OEntityPlayer oentityplayer = this.q.b(this, 16.0D);

        if (oentityplayer != null && !(Boolean) etc.getLoader().callHook(PluginLoader.Hook.MOB_TARGET, oentityplayer.getEntity(), this.getEntity())) { // CanaryMod - MOB_TARGET
            this.a(oentityplayer, 10.0F, 20.0F);
        }

        if (this.F && this.f-- <= 0) {
            this.f = this.j();
            if (oentityplayer != null) {
                this.f /= 3;
            }

            this.bG = true;
            if (this.q()) {
                this.a(this.n(), this.ba(), ((this.ab.nextFloat() - this.ab.nextFloat()) * 0.2F + 1.0F) * 0.8F);
            }

            this.bD = 1.0F - this.ab.nextFloat() * 2.0F;
            this.bE = (float) (1 * this.p());
        } else {
            this.bG = false;
            if (this.F) {
                this.bD = this.bE = 0.0F;
            }
        }
    }

    protected void k() {
        this.b *= 0.6F;
    }

    protected int j() {
        return this.ab.nextInt(20) + 10;
    }

    protected OEntitySlime i() {
        return new OEntitySlime(this.q);
    }

    public void w() {
        int i = this.p();

        if (!this.q.I && i > 1 && this.aX() <= 0) {
            int j = 2 + this.ab.nextInt(3);

            for (int k = 0; k < j; ++k) {
                float f = ((float) (k % 2) - 0.5F) * (float) i / 4.0F;
                float f1 = ((float) (k / 2) - 0.5F) * (float) i / 4.0F;
                OEntitySlime oentityslime = this.i();

                oentityslime.a(i / 2);
                oentityslime.b(this.u + (double) f, this.v + 0.5D, this.w + (double) f1, this.ab.nextFloat() * 360.0F, 0.0F);
                this.q.d((OEntity) oentityslime);
            }
        }

        super.w();
    }

    public void b_(OEntityPlayer oentityplayer) {
        if (this.l()) {
            int i = this.p();

            if (this.n(oentityplayer) && this.e(oentityplayer) < 0.6D * (double) i * 0.6D * (double) i && oentityplayer.a(ODamageSource.a((OEntityLiving) this), this.m())) {
                this.a("mob.attack", 1.0F, (this.ab.nextFloat() - this.ab.nextFloat()) * 0.2F + 1.0F);
            }
        }
    }

    protected boolean l() {
        return this.p() > 1;
    }

    protected int m() {
        return this.p();
    }

    protected String bc() {
        return "mob.slime." + (this.p() > 1 ? "big" : "small");
    }

    protected String bd() {
        return "mob.slime." + (this.p() > 1 ? "big" : "small");
    }

    protected int be() {
        return this.p() == 1 ? OItem.aN.cp : 0;
    }

    public boolean bv() {
        OChunk ochunk = this.q.d(OMathHelper.c(this.u), OMathHelper.c(this.w));

        if (this.q.L().u() == OWorldType.c && this.ab.nextInt(4) != 1) {
            return false;
        } else {
            if (this.p() == 1 || this.q.r > 0) {
                OBiomeGenBase obiomegenbase = this.q.a(OMathHelper.c(this.u), OMathHelper.c(this.w));

                if (obiomegenbase == OBiomeGenBase.h && this.v > 50.0D && this.v < 70.0D && this.ab.nextFloat() < 0.5F && this.ab.nextFloat() < e[this.q.v()] && this.q.n(OMathHelper.c(this.u), OMathHelper.c(this.v), OMathHelper.c(this.w)) <= this.ab.nextInt(8)) {
                    return super.bv();
                }

                if (this.ab.nextInt(10) == 0 && ochunk.a(987234911L).nextInt(10) == 0 && this.v < 40.0D) {
                    return super.bv();
                }
            }

            return false;
        }
    }

    protected float ba() {
        return 0.4F * (float) this.p();
    }

    public int bs() {
        return 0;
    }

    protected boolean q() {
        return this.p() > 0;
    }

    protected boolean o() {
        return this.p() > 2;
    }
}
