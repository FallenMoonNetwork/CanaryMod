public abstract class OEntityMob extends OEntityCreature implements OIMob {

    // CanaryMod start
    @SuppressWarnings("FieldNameHidesFieldInSuperclass")
    protected Mob entity = new Mob(this);
    // CanaryMod end

    public OEntityMob(OWorld oworld) {
        super(oworld);
        this.be = 5;
    }

    public void c() {
        this.br();
        float f = this.c(1.0F);

        if (f > 0.5F) {
            this.bC += 2;
        }

        super.c();
    }

    public void l_() {
        super.l_();
        if (!this.q.I && this.q.r == 0) {
            this.w();
        }
    }

    protected OEntity j() {
        OEntityPlayer oentityplayer = this.q.b(this, 16.0D);

        return oentityplayer != null && this.n(oentityplayer) ? oentityplayer : null;
    }

    public boolean a(ODamageSource odamagesource, int i) {
        if (this.aq()) {
            return false;
        } else if (super.a(odamagesource, i)) {
            OEntity oentity = odamagesource.i();

            if (this.n != oentity && this.o != oentity) {
                if (oentity != this) {
                    // CanaryMod start - MOB_TARGET hook
                    if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.MOB_TARGET, oentity.getEntity(), this.getEntity())) {
                        this.a_ = oentity;
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
        int i = this.c(oentity);

        if (this.a(OPotion.g)) {
            i += 3 << this.b(OPotion.g).c();
        }

        if (this.a(OPotion.t)) {
            i -= 2 << this.b(OPotion.t).c();
        }

        int j = 0;

        if (oentity instanceof OEntityLiving) {
            i += OEnchantmentHelper.a((OEntityLiving) this, (OEntityLiving) oentity);
            j += OEnchantmentHelper.b(this, (OEntityLiving) oentity);
        }

        boolean flag = oentity.a(ODamageSource.a((OEntityLiving) this), i);

        if (flag) {
            if (j > 0) {
                oentity.g((double) (-OMathHelper.a(this.A * 3.1415927F / 180.0F) * (float) j * 0.5F), 0.1D, (double) (OMathHelper.b(this.A * 3.1415927F / 180.0F) * (float) j * 0.5F));
                this.x *= 0.6D;
                this.z *= 0.6D;
            }

            int k = OEnchantmentHelper.a((OEntityLiving) this);

            if (k > 0) {
                oentity.d(k * 4);
            }

            if (oentity instanceof OEntityLiving) {
                OEnchantmentThorns.a(this, (OEntityLiving) oentity, this.ab);
            }
        }

        return flag;
    }

    protected void a(OEntity oentity, float f) {
        if (this.ba <= 0 && f < 2.0F && oentity.E.e > this.E.b && oentity.E.b < this.E.e) {
            this.ba = 20;
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

            if (this.q.N()) {
                int i1 = this.q.j;

                this.q.j = 10;
                l = this.q.n(i, j, k);
                this.q.j = i1;
            }

            return l <= this.ab.nextInt(8);
        }
    }

    public boolean bv() {
        return this.i_() && super.bv();
    }

    public int c(OEntity oentity) {
        return 2;
    }

    @Override
    public Mob getEntity() {
        return entity;
    } //
}
