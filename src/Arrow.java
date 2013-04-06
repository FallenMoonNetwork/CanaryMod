/**
 * Interface for the OEntityArrow class
 *
 * @author gregthegeek
 *
 */
public class Arrow extends Projectile {

    /**
     * Wraps an OEntityArrow
     *
     * @param base the entity to wrap
     */
    public Arrow(OEntityArrow base) {
        super(base);
    }

    /**
     * Creates a new Arrow
     *
     * @param world the world to create it in
     */
    public Arrow(World world) {
        this(new OEntityArrow(world.getWorld()));
    }

    /**
     * Creates a new Arrow
     *
     * @param location the location to create it at
     */
    public Arrow(Location location) {
        this(location.getWorld(), location.x, location.y, location.z);
    }

    /**
     * Creates a new Arrow
     *
     * @param world the world to create it in
     * @param x the x coordinate to create it at
     * @param y the y coordinate to create it at
     * @param z the z coordinate to create it at
     */
    public Arrow(World world, double x, double y, double z) {
        this(new OEntityArrow(world.getWorld(), x, y, z));
    }

    /**
     * Creates a new Arrow
     *
     * @param world the world to create it in
     * @param shooter the shooter of the arrow
     * @param target the target of the arrow
     * @param power the greater the power, the faster the shot. 0=no forward motion.
     * @param inaccuracy the lower the inaccuracy, the more accurate the shot. 0=perfect.
     */
    public Arrow(World world, LivingEntity shooter, LivingEntity target, float power, float inaccuracy) {
        this(new OEntityArrow(world.getWorld(), shooter.getEntity(), target.getEntity(), power, inaccuracy));
    }

    /**
     * Set the shooter of this arrow
     *
     * @param shooter the entity that shot
     */
    public void setShooter(BaseEntity shooter) {
        getEntity().c = shooter.getEntity();
    }

    @Override
    public boolean setShooter(LivingEntity shooter) {
        setShooter(shooter);
        return true;
    }

    @Override
    public BaseEntity getShooter() {
        return getEntity().c == null ? null : getEntity().c.getEntity();
    }

    /**
     * Returns whether or not players can pick up this arrow
     *
     * @return
     */
    public boolean canPickup() {
        return getEntity().a == 1;
    }

    /**
     * Sets whether or not players can pick up this arrow
     *
     * @param canPickup
     */
    public void setCanPickup(boolean canPickup) {
        getEntity().a = canPickup ? 1 : 0;
    }

    @Override
    public OEntityArrow getEntity() {
        return (OEntityArrow) super.getEntity();
    }
}
