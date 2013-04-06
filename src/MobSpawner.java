

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
        this.spawner.a(tag.getBaseTag());
    }

    /**
     * Writes the data for this spawner to an NBTTagCompound
     *
     * @param tag the tag to write to
     */
    @Override
    public void writeToTag(NBTTagCompound tag) {
        this.spawner.b(tag.getBaseTag());
    }

    @Override
    public NBTTagCompound getMetaTag() {
        return this.spawner.metadata;
    }

    @Override
    public void update() {
        super.update();
        this.spawner.k_();
    }
}
