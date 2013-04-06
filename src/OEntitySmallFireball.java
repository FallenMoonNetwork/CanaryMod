public class OEntitySmallFireball extends OEntityFireball {

    public OEntitySmallFireball(OWorld oworld) {
        super(oworld);
        this.a(0.3125F, 0.3125F);
    }

    public OEntitySmallFireball(OWorld oworld, OEntityLiving oentityliving, double d0, double d1, double d2) {
        super(oworld, oentityliving, d0, d1, d2);
        this.a(0.3125F, 0.3125F);
    }

    public OEntitySmallFireball(OWorld oworld, double d0, double d1, double d2, double d3, double d4, double d5) {
        super(oworld, d0, d1, d2, d3, d4, d5);
        this.a(0.3125F, 0.3125F);
    }

    protected void a(OMovingObjectPosition omovingobjectposition) {
        if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.PROJECTILE_HIT, new Projectile(this), omovingobjectposition.g == null ? null : omovingobjectposition.g.getEntity()) && !this.q.I) {
            if (omovingobjectposition.g != null) {
                if (!omovingobjectposition.g.E() && omovingobjectposition.g.a(ODamageSource.a((OEntityFireball) this, this.a), 5)) {
                    omovingobjectposition.g.d(5);
                }
            } else {
                int i = omovingobjectposition.b;
                int j = omovingobjectposition.c;
                int k = omovingobjectposition.d;

                switch (omovingobjectposition.e) {
                    case 0:
                        --j;
                        break;

                    case 1:
                        ++j;
                        break;

                    case 2:
                        --k;
                        break;

                    case 3:
                        ++k;
                        break;

                    case 4:
                        --i;
                        break;

                    case 5:
                        ++i;
                }

                if (this.q.c(i, j, k)) {
                    this.q.c(i, j, k, OBlock.av.cz);
                }
            }

            this.w();
        }
    }

    public boolean K() {
        return false;
    }

    public boolean a(ODamageSource odamagesource, int i) {
        return false;
    }
}
