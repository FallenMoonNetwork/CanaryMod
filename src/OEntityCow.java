
public class OEntityCow extends OEntityAnimal {

    public OEntityCow(OWorld oworld) {
        super(oworld);
        this.aF = "/mob/cow.png";
        this.a(0.9F, 1.3F);
        this.ay().a(true);
        this.bn.a(0, new OEntityAISwimming(this));
        this.bn.a(1, new OEntityAIPanic(this, 0.38F));
        this.bn.a(2, new OEntityAIMate(this, 0.2F));
        this.bn.a(3, new OEntityAITempt(this, 0.25F, OItem.T.cf, false));
        this.bn.a(4, new OEntityAIFollowParent(this, 0.25F));
        this.bn.a(5, new OEntityAIWander(this, 0.2F));
        this.bn.a(6, new OEntityAIWatchClosest(this, OEntityPlayer.class, 6.0F));
        this.bn.a(7, new OEntityAILookIdle(this));
    }

    public boolean bb() {
        return true;
    }

    public int aS() {
        return 10;
    }

    protected String aW() {
        return "mob.cow.say";
    }

    protected String aX() {
        return "mob.cow.hurt";
    }

    protected String aY() {
        return "mob.cow.hurt";
    }

    protected void a(int i, int j, int k, int l) {
        this.p.a(this, "mob.cow.step", 0.15F, 1.0F);
    }

    protected float aV() {
        return 0.4F;
    }

    protected int aZ() {
        return OItem.aF.cf;
    }

    protected void a(boolean flag, int i) {
        int j = this.aa.nextInt(3) + this.aa.nextInt(1 + i);

        int k;

        for (k = 0; k < j; ++k) {
            this.b(OItem.aF.cf, 1);
        }

        j = this.aa.nextInt(3) + 1 + this.aa.nextInt(1 + i);

        for (k = 0; k < j; ++k) {
            if (this.af()) {
                this.b(OItem.bj.cf, 1);
            } else {
                this.b(OItem.bi.cf, 1);
            }
        }

    }

    public boolean c(OEntityPlayer oentityplayer) {
        OItemStack oitemstack = oentityplayer.bK.g();

        if (oitemstack != null && oitemstack.c == OItem.aw.cf && !(Boolean) etc.getLoader().callHook(PluginLoader.Hook.COW_MILK, ((OEntityPlayerMP) oentityplayer).getPlayer(), new Mob(this))) {

            if (--oitemstack.a <= 0) {
                oentityplayer.bK.a(oentityplayer.bK.c, new OItemStack(OItem.aG));
            } else if (!oentityplayer.bK.a(new OItemStack(OItem.aG))) {
                oentityplayer.c(new OItemStack(OItem.aG.cf, 1, 0));
            }

            return true;
        } else {
            return super.c(oentityplayer);
        }
    }

    public OEntityAnimal a(OEntityAnimal oentityanimal) {
        return new OEntityCow(this.p);
    }
}
