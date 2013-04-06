public class OTileEntityMobSpawner extends OTileEntity {

    private final OMobSpawnerBaseLogic a = new OTileEntityMobSpawnerLogic(this);
    private final MobSpawner spawner = new MobSpawner(this); // CanaryMod

    public OTileEntityMobSpawner() {}

    public void a(ONBTTagCompound onbttagcompound) {
        super.a(onbttagcompound);
        this.a.a(onbttagcompound);
    }

    public void b(ONBTTagCompound onbttagcompound) {
        super.b(onbttagcompound);
        this.a.b(onbttagcompound);
    }

    public void h() {
        this.a.g();
        super.h();
    }

    public OPacket m() {
        ONBTTagCompound onbttagcompound = new ONBTTagCompound();

        this.b(onbttagcompound);
        onbttagcompound.o("SpawnPotentials");
        return new OPacket132TileEntityData(this.l, this.m, this.n, 1, onbttagcompound);
    }

    public boolean b(int i, int j) {
        return this.a.b(i) ? true : super.b(i, j);
    }

    public OMobSpawnerBaseLogic a() {
        return this.a;
    }

    @Override
    public MobSpawner getComplexBlock() {
        return spawner;
    }
}
