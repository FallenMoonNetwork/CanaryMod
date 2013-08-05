public class OEntityEgg extends OEntityThrowable {

    private Egg entity = new Egg(this); // CanaryMod: reference wrapper

    public OEntityEgg(OWorld oworld) {
        super(oworld);
    }

    public OEntityEgg(OWorld oworld, OEntityLivingBase oentitylivingbase) {
        super(oworld, oentitylivingbase);
    }

    public OEntityEgg(OWorld oworld, double d0, double d1, double d2) {
        super(oworld, d0, d1, d2);
    }

    protected void a(OMovingObjectPosition omovingobjectposition) {
        if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.PROJECTILE_HIT, this.getEntity(), omovingobjectposition.g == null ? null : omovingobjectposition.g.getEntity())) {
            if (omovingobjectposition.g != null) {
                omovingobjectposition.g.a(ODamageSource.a((OEntity) this, this.h()), 0.0F);
            }

            if (!this.q.I && this.ab.nextInt(8) == 0) {
                byte b0 = 1;

                if (this.ab.nextInt(32) == 0) {
                    b0 = 4;
                }

                for (int i = 0; i < b0; ++i) {
                    OEntityChicken oentitychicken = new OEntityChicken(this.q);

                oentitychicken.c(-24000);
                    oentitychicken.b(this.u, this.v, this.w, this.A, 0.0F);
                    this.q.d((OEntity) oentitychicken);
                }
            }

            for (int j = 0; j < 8; ++j) {
                this.q.a("snowballpoof", this.u, this.v, this.w, 0.0D, 0.0D, 0.0D);
            }

            if (!this.q.I) {
                this.w();
            }
        }
    }

    // CanaryMod start: add getEntity()
    @Override
    public Egg getEntity() {
        return entity;
    } // CanaryMod end
}
