
public class OEntityEnderPearl extends OEntityThrowable {

    public OEntityEnderPearl(OWorld oworld) {
        super(oworld);
    }

    public OEntityEnderPearl(OWorld oworld, OEntityLiving oentityliving) {
        super(oworld, oentityliving);
    }

    protected void a(OMovingObjectPosition omovingobjectposition) {
        if (omovingobjectposition.g != null) {
            omovingobjectposition.g.a(ODamageSource.a((OEntity) this, this.c), 0);
        }
        // CanaryMod start - Fix enderpearl dupe bug
        Player p = null;

        if (this.c instanceof OEntityPlayerMP) {
            p = new Player((OEntityPlayerMP) this.c);
        }
        if ((p != null) && !(etc.getServer().getPlayerList().contains(p))) {
            this.y(); // kill this entity
            return;
        }
        // CanaryMod end
        
        for (int i = 0; i < 32; ++i) {
            this.p.a("portal", this.t, this.u + this.aa.nextDouble() * 2.0D, this.v, this.aa.nextGaussian(), 0.0D, this.aa.nextGaussian());
        }

        if (!this.p.J) {
            if (this.c != null && this.c instanceof OEntityPlayerMP) {
                OEntityPlayerMP oentityplayermp = (OEntityPlayerMP) this.c;

                if (!oentityplayermp.a.c && oentityplayermp.p == this.p) {
                    this.c.a(this.t, this.u, this.v);
                    this.c.S = 0.0F;
                    this.c.a(ODamageSource.h, 5);
                }
            }

            this.x();
        }

    }
}
