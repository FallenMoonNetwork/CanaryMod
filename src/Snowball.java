/**
 * Interface for the OEntitySnowball class
 *
 * @author gregthegeek
 *
 */
public class Snowball extends Projectile {

    /**
     * Wraps an OEntitySnowball
     *
     * @param base the OEntitySnowball to wrap
     */
    public Snowball(OEntitySnowball base) {
        super(base);
    }

    /**
     * Creates a new Snowball
     *
     * @param world the world to create it in
     */
    public Snowball(World world) {
        this(new OEntitySnowball(world.getWorld()));
    }

    /**
     * Creates a new Snowball
     *
     * @param world the world to create it in
     * @param shooter the shooter of the snowball
     */
    public Snowball(World world, LivingEntity shooter) {
        this(new OEntitySnowball(world.getWorld(), shooter.getEntity()));
    }

    /**
     * Creates a new Snowball
     *
     * @param world the world to create it in
     * @param x the x coordinate to create it at
     * @param y the y coordinate to create it at
     * @param z the z coordinate to create it at
     */
    public Snowball(World world, double x, double y, double z) {
        this(new OEntitySnowball(world.getWorld(), x, y, z));
    }

    /**
     * Creates a new Snowball
     *
     * @param location the location at which to create it
     */
    public Snowball(Location location) {
        this(location.getWorld(), location.x, location.y, location.z);
    }

    @Override
    public LivingEntity getShooter() {
        return getEntity().h() == null ? null : getEntity().h().getEntity();
    }

    @Override
    public boolean setShooter(LivingEntity shooter) {
        getEntity().setShooter(shooter.getEntity());
        return true;
    }

    @Override
    public OEntitySnowball getEntity() {
        return (OEntitySnowball) super.getEntity();
    }
}
