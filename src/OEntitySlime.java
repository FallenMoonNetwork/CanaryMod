public class OEntitySlime extends OEntityLiving implements OIMob {

    public float h;
    public float i;
    public float j;
    private int bn;

    public OEntitySlime(OWorld oworld) {
        super(oworld);
        int i = 1 << this.ab.nextInt(3);

        this.N = 0.0F;
        this.bn = this.ab.nextInt(20) + 10;
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
        this.a(OSharedMonsterAttributes.a).a((double) (i * i));
        this.g(this.aT());
        this.b = i;
    }

    public int bR() {
        return this.ah.a(16);
    }

    public void b(ONBTTagCompound onbttagcompound) {
        super.b(onbttagcompound);
        onbttagcompound.a("Size", this.bR() - 1);
    }

    public void a(ONBTTagCompound onbttagcompound) {
        super.a(onbttagcompound);
        this.a(onbttagcompound.e("Size") + 1);
    }

    protected String bJ() {
        return "slime";
    }

    protected String bP() {
        return "mob.slime." + (this.bR() > 1 ? "big" : "small");
    }

    public void l_() {
        if (!this.q.I && this.q.r == 0 && this.bR() > 0) {
            this.M = true;
        }

        this.i += (this.h - this.i) * 0.5F;
        this.j = this.i;
        boolean flag = this.F;

        super.l_();
        int i;

        if (this.F && !flag) {
            i = this.bR();

            for (int j = 0; j < i * 8; ++j) {
                float f = this.ab.nextFloat() * 3.1415927F * 2.0F;
                float f1 = this.ab.nextFloat() * 0.5F + 0.5F;
                float f2 = OMathHelper.a(f) * (float) i * 0.5F * f1;
                float f3 = OMathHelper.b(f) * (float) i * 0.5F * f1;

                this.q.a(this.bJ(), this.u + (double) f2, this.E.b, this.w + (double) f3, 0.0D, 0.0D, 0.0D);
            }

            if (this.bQ()) {
                this.a(this.bP(), this.ba(), ((this.ab.nextFloat() - this.ab.nextFloat()) * 0.2F + 1.0F) / 0.8F);
            }

            this.h = -0.5F;
        } else if (!this.F && flag) {
            this.h = 1.0F;
        }

        this.bM();
        if (this.q.I) {
            i = this.bR();
            this.a(0.6F * (float) i, 0.6F * (float) i);
        }
    }

    protected void bl() {
        this.u();
        OEntityPlayer oentityplayer = this.q.b(this, 16.0D);

        if (oentityplayer != null && !(Boolean) etc.getLoader().callHook(PluginLoader.Hook.MOB_TARGET, oentityplayer.getEntity(), this.getEntity())) { // CanaryMod - MOB_TARGET
            this.a(oentityplayer, 10.0F, 20.0F);
        }

        if (this.F && this.bn-- <= 0) {
            this.bn = this.bL();
            if (oentityplayer != null) {
                this.bn /= 3;
            }

            this.bd = true;
            if (this.bS()) {
                this.a(this.bP(), this.ba(), ((this.ab.nextFloat() - this.ab.nextFloat()) * 0.2F + 1.0F) * 0.8F);
            }

            this.be = 1.0F - this.ab.nextFloat() * 2.0F;
            this.bf = (float) (1 * this.bR());
        } else {
            this.bd = false;
            if (this.F) {
                this.be = this.bf = 0.0F;
            }
        }
    }

    protected void bM() {
        this.h *= 0.6F;
    }

    protected int bL() {
        return this.ab.nextInt(20) + 10;
    }

    protected OEntitySlime bK() {
        return new OEntitySlime(this.q);
    }

    public void x() {
        int i = this.bR();

        if (!this.q.I && i > 1 && this.aN() <= 0.0F) {
            int j = 2 + this.ab.nextInt(3);

            for (int k = 0; k < j; ++k) {
                float f = ((float) (k % 2) - 0.5F) * (float) i / 4.0F;
                float f1 = ((float) (k / 2) - 0.5F) * (float) i / 4.0F;
                OEntitySlime oentityslime = this.bK();

                oentityslime.a(i / 2);
                oentityslime.b(this.u + (double) f, this.v + 0.5D, this.w + (double) f1, this.ab.nextFloat() * 360.0F, 0.0F);
                this.q.d((OEntity) oentityslime);
            }
        }

        super.x();
    }

    public void b_(OEntityPlayer oentityplayer) {
        if (this.bN()) {
            int i = this.bR();

            if (this.o(oentityplayer) && this.e(oentityplayer) < 0.6D * (double) i * 0.6D * (double) i && oentityplayer.a(ODamageSource.a((OEntityLivingBase) this), (float) this.bO())) {
                this.a("mob.attack", 1.0F, (this.ab.nextFloat() - this.ab.nextFloat()) * 0.2F + 1.0F);
            }
        }
    }

    protected boolean bN() {
        return this.bR() > 1;
    }

    protected int bO() {
        return this.bR();
    }

    protected String aO() {
        return "mob.slime." + (this.bR() > 1 ? "big" : "small");
    }

    protected String aP() {
        return "mob.slime." + (this.bR() > 1 ? "big" : "small");
    }

    protected int s() {
        return this.bR() == 1 ? OItem.aO.cv : 0;
    }

    public boolean bs() {
        OChunk ochunk = this.q.d(OMathHelper.c(this.u), OMathHelper.c(this.w));

        if (this.q.N().u() == OWorldType.c && this.ab.nextInt(4) != 1) {
            return false;
        } else {
            if (this.bR() == 1 || this.q.r > 0) {
                OBiomeGenBase obiomegenbase = this.q.a(OMathHelper.c(this.u), OMathHelper.c(this.w));

                if (obiomegenbase == OBiomeGenBase.h && this.v > 50.0D && this.v < 70.0D && this.ab.nextFloat() < 0.5F && this.ab.nextFloat() < this.q.x() && this.q.n(OMathHelper.c(this.u), OMathHelper.c(this.v), OMathHelper.c(this.w)) <= this.ab.nextInt(8)) {
                    return super.bs();
                }

                if (this.ab.nextInt(10) == 0 && ochunk.a(987234911L).nextInt(10) == 0 && this.v < 40.0D) {
                    return super.bs();
                }
            }

            return false;
        }
    }

    protected float ba() {
        return 0.4F * (float) this.bR();
    }

    public int bp() {
        return 0;
    }

    protected boolean bS() {
        return this.bR() > 0;
    }

    protected boolean bQ() {
        return this.bR() > 2;
    }
}
