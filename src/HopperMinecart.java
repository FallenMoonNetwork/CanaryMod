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
        // SRG return this.getEntity().field_98044_b;
        return this.getEntity().b;
    }

    @Override
    public void setTransferCooldown(int cooldown) {
        // SRG this.getEntity().field_98044_b = cooldown;
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
        // SRG return this.getEntity().func_96111_ay();
        return this.getEntity().u();
    }

    /**
     * Set the blocked state of this hopper minecart.
     * Blockages normally happen with activator rails.
     * @param blocked <tt>true</tt> if this hopper minecart should be blocked,
     * <tt>false</tt> otherwise.
     */
    public void setBlocked(boolean blocked) {
        // SRG this.getEntity().func_96110_f(blocked);
        this.getEntity().f(blocked);
    }
}
