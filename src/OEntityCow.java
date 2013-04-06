public class OEntityCow extends OEntityAnimal {

    public OEntityCow(OWorld oworld) {
        super(oworld);
        this.aH = "/mob/cow.png";
        this.a(0.9F, 1.3F);
        this.aC().a(true);
        this.bo.a(0, new OEntityAISwimming(this));
        this.bo.a(1, new OEntityAIPanic(this, 0.38F));
        this.bo.a(2, new OEntityAIMate(this, 0.2F));
        this.bo.a(3, new OEntityAITempt(this, 0.25F, OItem.U.cp, false));
        this.bo.a(4, new OEntityAIFollowParent(this, 0.25F));
        this.bo.a(5, new OEntityAIWander(this, 0.2F));
        this.bo.a(6, new OEntityAIWatchClosest(this, OEntityPlayer.class, 6.0F));
        this.bo.a(7, new OEntityAILookIdle(this));
    }

    public boolean bh() {
        return true;
    }

    public int aW() {
        //CanaryMod: set max health here, but check for uninitialized value.
        return this.maxHealth == 0 ? 10 : this.maxHealth;

    }

    protected String bb() {
        return "mob.cow.say";
    }

    protected String bc() {
        return "mob.cow.hurt";
    }

    protected String bd() {
        return "mob.cow.hurt";
    }

    protected void a(int i, int j, int k, int l) {
        this.a("mob.cow.step", 0.15F, 1.0F);
    }

    protected float ba() {
        return 0.4F;
    }

    protected int be() {
        return OItem.aG.cp;
    }

    protected void a(boolean flag, int i) {
        int j = this.ab.nextInt(3) + this.ab.nextInt(1 + i);

        int k;

        for (k = 0; k < j; ++k) {
            this.b(OItem.aG.cp, 1);
        }

        j = this.ab.nextInt(3) + 1 + this.ab.nextInt(1 + i);

        for (k = 0; k < j; ++k) {
            if (this.ae()) {
                this.b(OItem.bk.cp, 1);
            } else {
                this.b(OItem.bj.cp, 1);
            }
        }
    }

    public boolean a_(OEntityPlayer oentityplayer) {
        OItemStack oitemstack = oentityplayer.bK.h();

        if (oitemstack != null && oitemstack.c == OItem.ax.cp && !(Boolean) etc.getLoader().callHook(PluginLoader.Hook.COW_MILK, ((OEntityPlayerMP) oentityplayer).getPlayer(), new Mob(this))) {
            if (--oitemstack.a <= 0) {
                oentityplayer.bK.a(oentityplayer.bK.c, new OItemStack(OItem.aH));
            } else if (!oentityplayer.bK.a(new OItemStack(OItem.aH))) {
                oentityplayer.c(new OItemStack(OItem.aH.cp, 1, 0));
            }

            return true;
        } else {
            return super.a_(oentityplayer);
        }
    }

    public OEntityCow b(OEntityAgeable oentityageable) {
        return new OEntityCow(this.q);
    }

    public OEntityAgeable a(OEntityAgeable oentityageable) {
        return this.b(oentityageable);
    }
}
