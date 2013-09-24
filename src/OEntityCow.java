public class OEntityCow extends OEntityAnimal {

    public OEntityCow(OWorld oworld) {
        super(oworld);
        this.a(0.9F, 1.3F);
        this.k().a(true);
        this.c.a(0, new OEntityAISwimming(this));
        this.c.a(1, new OEntityAIPanic(this, 2.0D));
        this.c.a(2, new OEntityAIMate(this, 1.0D));
        this.c.a(3, new OEntityAITempt(this, 1.25D, OItem.V.cv, false));
        this.c.a(4, new OEntityAIFollowParent(this, 1.25D));
        this.c.a(5, new OEntityAIWander(this, 1.0D));
        this.c.a(6, new OEntityAIWatchClosest(this, OEntityPlayer.class, 6.0F));
        this.c.a(7, new OEntityAILookIdle(this));
    }

    public boolean bf() {
        return true;
    }

    protected void az() {
        super.az();
        this.a(OSharedMonsterAttributes.a).a(10.0D);
        this.a(OSharedMonsterAttributes.d).a(0.20000000298023224D);
    }

    protected String r() {
        return "mob.cow.say";
    }

    protected String aO() {
        return "mob.cow.hurt";
    }

    protected String aP() {
        return "mob.cow.hurt";
    }

    protected void a(int i, int j, int k, int l) {
        this.a("mob.cow.step", 0.15F, 1.0F);
    }

    protected float ba() {
        return 0.4F;
    }

    protected int s() {
        return OItem.aH.cv;
    }

    protected void b(boolean flag, int i) {
        int j = this.ab.nextInt(3) + this.ab.nextInt(1 + i);

        int k;

        for (k = 0; k < j; ++k) {
            this.b(OItem.aH.cv, 1);
        }

        j = this.ab.nextInt(3) + 1 + this.ab.nextInt(1 + i);

        for (k = 0; k < j; ++k) {
            if (this.af()) {
                this.b(OItem.bl.cv, 1);
            } else {
                this.b(OItem.bk.cv, 1);
            }
        }
    }

    public boolean a(OEntityPlayer oentityplayer) {
        OItemStack oitemstack = oentityplayer.bn.h();

        if (oitemstack != null && oitemstack.d == OItem.ay.cv && !oentityplayer.bG.d && !(Boolean) etc.getLoader().callHook(PluginLoader.Hook.COW_MILK, ((OEntityPlayerMP) oentityplayer).getPlayer(), this.getEntity())) {
            if (oitemstack.b-- == 1) {
                oentityplayer.bn.a(oentityplayer.bn.c, new OItemStack(OItem.aI));
            } else if (!oentityplayer.bn.a(new OItemStack(OItem.aI))) {
                oentityplayer.b(new OItemStack(OItem.aI.cv, 1, 0));
            }

            return true;
        } else {
            return super.a(oentityplayer);
        }
    }

    public OEntityCow b(OEntityAgeable oentityageable) {
        return new OEntityCow(this.q);
    }

    public OEntityAgeable a(OEntityAgeable oentityageable) {
        return this.b(oentityageable);
    }
}
