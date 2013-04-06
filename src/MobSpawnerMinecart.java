
public class MobSpawnerMinecart extends Minecart {

    MobSpawnerMinecart(OEntityMinecartMobSpawner o) {
        super(o);
    }

    /**
     * Create a new Spawner Minecart with the given position.
     * Call {@link #spawn()} to spawn it in the world,
     * use this cart's {@link MobSpawnerLogic} to set options for the spawner.
     *
     * @param world The world for the new minecart
     * @param x The x coordinate for the new minecart
     * @param y The y coordinate for the new minecart
     * @param z The z coordinate for the new minecart
     * @see #getLogic()
     */
    public MobSpawnerMinecart(World world, double x, double y, double z) {
        this(new OEntityMinecartMobSpawner(world.getWorld(), x, y, z));
    }

    /**
     * Get the {@link MobSpawnerLogic} for this <tt>MobSpawnerMinecart</tt>.
     * @return This <tt>MobSpawnerMinecart</tt>'s {@link MobSpawnerLogic}
     */
    public MobSpawnerLogic getLogic() {
        return this.getEntity().a.logic;
    }

    @Override
    public OEntityMinecartMobSpawner getEntity() {
        return (OEntityMinecartMobSpawner) super.getEntity();
    }
}
