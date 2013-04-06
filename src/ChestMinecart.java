public class ChestMinecart extends ContainerMinecart {

    ChestMinecart(OEntityMinecartChest o) {
        super(o);
    }

    /**
     * Create a new Storage Minecart with the given position.
     * Call {@link #spawn()} to spawn it in the world.
     *
     * @param world The world for the new minecart
     * @param x The x coordinate for the new minecart
     * @param y The y coordinate for the new minecart
     * @param z The z coordinate for the new minecart
     */
    public ChestMinecart(World world, double x, double y, double z) {
        this(new OEntityMinecartChest(world.getWorld(), x, y, z));
    }

    @Override
    public OEntityMinecartChest getEntity() {
        return (OEntityMinecartChest) super.getEntity();
    }
}
