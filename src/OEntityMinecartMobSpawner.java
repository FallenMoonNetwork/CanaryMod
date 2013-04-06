public class OEntityMinecartMobSpawner extends OEntityMinecart {

    final OMobSpawnerBaseLogic a = new OEntityMinecartMobSpawnerLogic(this); // CanaryMod: private -> package-private

    private MobSpawnerMinecart cart = new MobSpawnerMinecart(this); // CanaryMod: reference to wrapper

    public OEntityMinecartMobSpawner(OWorld oworld) {
        super(oworld);
    }

    public OEntityMinecartMobSpawner(OWorld oworld, double d0, double d1, double d2) {
        super(oworld, d0, d1, d2);
    }

    public int l() {
        return 4;
    }

    public OBlock n() {
        return OBlock.aw;
    }

    protected void a(ONBTTagCompound onbttagcompound) {
        super.a(onbttagcompound);
        this.a.a(onbttagcompound);
    }

    protected void b(ONBTTagCompound onbttagcompound) {
        super.b(onbttagcompound);
        this.a.b(onbttagcompound);
    }

    public void l_() {
        super.l_();
        this.a.g();
    }

    @Override
    public MobSpawnerMinecart getEntity() {
        return cart;
    }
}
