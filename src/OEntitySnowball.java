public class OEntitySnowball extends OEntityThrowable {

    private Snowball entity = new Snowball(this);

    public OEntitySnowball(OWorld oworld) {
        super(oworld);
    }

    public OEntitySnowball(OWorld oworld, OEntityLivingBase oentitylivingbase) {
        super(oworld, oentitylivingbase);
    }

    public OEntitySnowball(OWorld oworld, double d0, double d1, double d2) {
        super(oworld, d0, d1, d2);
    }

    protected void a(OMovingObjectPosition omovingobjectposition) {
        if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.PROJECTILE_HIT, new Snowball(this), omovingobjectposition.g == null ? null : omovingobjectposition.g.getEntity())) {
            if (omovingobjectposition.g != null) {
                byte b0 = 0;

                if (omovingobjectposition.g instanceof OEntityBlaze) {
                    b0 = 3;
                }

            omovingobjectposition.g.a(ODamageSource.a((OEntity) this, this.h()), (float) b0);
            }

            for (int i = 0; i < 8; ++i) {
                this.q.a("snowballpoof", this.u, this.v, this.w, 0.0D, 0.0D, 0.0D);
            }

            if (!this.q.I) {
                this.w();
            }
        }
    }

    // CanaryMod start: add getEntity()
    @Override
    public Snowball getEntity() {
        return entity;
    } // CanaryMod end
}
