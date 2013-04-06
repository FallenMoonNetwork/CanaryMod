public class HopperMinecart extends ContainerMinecart implements Hopper {

    HopperMinecart(OEntityMinecartHopper o) {
        super(o);
    }

    /**
     * Create a new Hopper Minecart with the given position.
     * Call {@link #spawn()} to spawn it in the world.
     *
     * @param world The world for the new minecart
     * @param x The x coordinate for the new minecart
     * @param y The y coordinate for the new minecart
     * @param z The z coordinate for the new minecart
     */
    public HopperMinecart(World world, double x, double y, double z) {
        this(new OEntityMinecartHopper(world.getWorld(), x, y, z));
    }

    @Override
    public double getPosX() {
        return this.getX();
    }

    @Override
    public double getPosY() {
        return this.getY();
    }

    @Override
    public double getPosZ() {
        return this.getZ();
    }

    @Override
    public int getTranferCooldown() {
        return this.getEntity().b;
    }

    @Override
    public void setTransferCooldown(int cooldown) {
        this.getEntity().b = cooldown;
    }

    @Override
    public OEntityMinecartHopper getEntity() {
        return (OEntityMinecartHopper) super.getEntity();
    }

    /**
     * Get the blocked state of this hopper minecart.
     * Blockages normally happen with activator rails.
     * @return <tt>true</tt> if this hopper minecart is blocked, <tt>false</tt>
     * otherwise
     */
    public boolean isBlocked() {
        return this.getEntity().ay();
    }

    /**
     * Set the blocked state of this hopper minecart.
     * Blockages normally happen with activator rails.
     * @param blocked <tt>true</tt> if this hopper minecart should be blocked,
     * <tt>false</tt> otherwise.
     */
    public void setBlocked(boolean blocked) {
        this.getEntity().f(blocked);
    }
}
