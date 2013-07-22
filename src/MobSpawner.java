

/**
 * MobSpawner.java - Wrapper for mob spawners.
 *
 * @author James
 */
public class MobSpawner extends MobSpawnerLogic implements ComplexBlock {

    private OTileEntityMobSpawner spawner;

    /**
     * Creates an interface for the spawner.
     *
     * @param spawner
     */
    public MobSpawner(OTileEntityMobSpawner spawner) {
        // SRG super(spawner.func_98049_a());
        super(spawner.a());
        this.spawner = spawner;
    }

    @Override
    public Block getBlock() {
        return this.getWorld().getBlockAt(this.getX(), this.getY(), this.getZ());
    }

    /**
     * Reads the data for this spawner from an NBTTagCompound
     *
     * @param tag the tag to read from
     */
    @Override
    public void readFromTag(NBTTagCompound tag) {
        // SRG this.spawner.func_70307_a(tag.getBaseTag());
        this.spawner.a(tag.getBaseTag());
    }

    /**
     * Writes the data for this spawner to an NBTTagCompound
     *
     * @param tag the tag to write to
     */
    @Override
    public void writeToTag(NBTTagCompound tag) {
        // SRG this.spawner.func_70310_b(tag.getBaseTag());
        this.spawner.b(tag.getBaseTag());
    }

    @Override
    public NBTTagCompound getMetaTag() {
        return this.spawner.metadata;
    }

    @Override
    public void update() {
        super.update();
        // SRG this.spawner.func_70316_g();
        this.spawner.h();
    }
}
