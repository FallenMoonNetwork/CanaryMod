public class TNTMinecart extends Minecart {

    TNTMinecart(OEntityMinecartTNT o) {
        super(o);
    }

    /**
     * Create a new Minecart with the given position.
     * Call {@link #spawn()} to spawn it in the world.
     *
     * @param world The world for the new minecart
     * @param x The x coordinate for the new minecart
     * @param y The y coordinate for the new minecart
     * @param z The z coordinate for the new minecart
     */
    public TNTMinecart(World world, double x, double y, double z) {
        this(new OEntityMinecartTNT(world.getWorld(), x, y, z));
    }

    @Override
    public OEntityMinecartTNT getEntity() {
        return (OEntityMinecartTNT) super.getEntity();
    }

    /**
     * Activates the TNT as if the cart passed over an activator rail.
     */
    public void activate() {
        this.getEntity().d();
    }

    /**
     * Immediately explodes the minecart.
     * <tt>power</tt> is normally calculated by squaring the cart's speed.
     * @param power The power at which the cart should explode, squared
     */
    public void explode(double power) {
        this.getEntity().c(power);
    }

    /**
     * Get the fuse time for this minecart's TNT.
     * Starts at 80 by default, is -1 if not yet activated.
     * @return The fuse time in ticks for the TNT if lit, -1 otherwise.
     */
    public int getFuseTime() {
        return this.getEntity().a;
    }

    /**
     * Set the fuse time for this minecart's TNT.
     * Starts at 80 by default. Setting this to anything greater than -1
     * activates the fuse at the set time, setting it to -1 extinguishes it.
     * @param time The fuse time in ticks.
     */
    public void setFuseTime(int time) {
        this.getEntity().a = time;
    }

    /**
     * Checks whether the fuse is lit.
     * Equivalent to <tt>{@link #getFuseTime()} > -1</tt>.
     * @return <tt>true</tt> if the fuse is lit, <tt>false</tt> otherwise.
     */
    public boolean isFuseLit() {
        return this.getEntity().ay();
    }
}
