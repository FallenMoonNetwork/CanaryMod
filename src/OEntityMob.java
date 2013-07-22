public abstract class OEntityMob extends OEntityCreature implements OIMob {

    // CanaryMod start
    @SuppressWarnings("FieldNameHidesFieldInSuperclass")
    protected Mob entity = new Mob(this);
    // CanaryMod end

    public OEntityMob(OWorld oworld) {
        super(oworld);
        this.b = 5;
    }

    public void c() {
        this.aV();
        float f = this.d(1.0F);

        if (f > 0.5F) {
            this.aV += 2;
        }

        super.c();
    }

    public void l_() {
        super.l_();
        if (!this.q.I && this.q.r == 0) {
            this.w();
        }
    }

    protected OEntity bL() {
        OEntityPlayer oentityplayer = this.q.b(this, 16.0D);

        return oentityplayer != null && this.o(oentityplayer) ? oentityplayer : null;
    }

    public boolean a(ODamageSource odamagesource, float f) {
        if (this.aq()) {
            return false;
        } else if (super.a(odamagesource, f)) {
            OEntity oentity = odamagesource.i();

            if (this.n != oentity && this.o != oentity) {
                if (oentity != this) {
                    // CanaryMod start - MOB_TARGET hook
                    if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.MOB_TARGET, oentity.getEntity(), this.getEntity())) {
                        this.j = oentity;
                    } // CanaryMod end
                }

                return true;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public boolean m(OEntity oentity) {
        float f = (float) this.a(OSharedMonsterAttributes.e).e();
        int i = 0;

        if (oentity instanceof OEntityLivingBase) {
            f += OEnchantmentHelper.a((OEntityLivingBase) this, (OEntityLivingBase) oentity);
            i += OEnchantmentHelper.b(this, (OEntityLivingBase) oentity);
        }

        boolean flag = oentity.a(ODamageSource.a((OEntityLivingBase) this), f);

        if (flag) {
            if (i > 0) {
                oentity.g((double) (-OMathHelper.a(this.A * 3.1415927F / 180.0F) * (float) i * 0.5F), 0.1D, (double) (OMathHelper.b(this.A * 3.1415927F / 180.0F) * (float) i * 0.5F));
                this.x *= 0.6D;
                this.z *= 0.6D;
            }

            int j = OEnchantmentHelper.a((OEntityLivingBase) this);

            if (j > 0) {
                oentity.d(j * 4);
            }

            if (oentity instanceof OEntityLivingBase) {
                OEnchantmentThorns.a(this, (OEntityLivingBase) oentity, this.ab);
            }
        }

        return flag;
    }

    protected void a(OEntity oentity, float f) {
        if (this.aC <= 0 && f < 2.0F && oentity.E.e > this.E.b && oentity.E.b < this.E.e) {
            this.aC = 20;
            this.m(oentity);
        }
    }

    public float a(int i, int j, int k) {
        return 0.5F - this.q.q(i, j, k);
    }

    protected boolean i_() {
        int i = OMathHelper.c(this.u);
        int j = OMathHelper.c(this.E.b);
        int k = OMathHelper.c(this.w);

        if (this.q.b(OEnumSkyBlock.a, i, j, k) > this.ab.nextInt(32)) {
            return false;
        } else {
            int l = this.q.n(i, j, k);

            if (this.q.P()) {
                int i1 = this.q.j;

                this.q.j = 10;
                l = this.q.n(i, j, k);
                this.q.j = i1;
            }

            return l <= this.ab.nextInt(8);
        }
    }

    public boolean bs() {
        return this.q.r > 0 && this.i_() && super.bs();
    }

    protected void ay() {
        super.ay();
        this.aW().b(OSharedMonsterAttributes.e);
    }

    @Override
    public Mob getEntity() {
        return entity;
    } //
}
