/**
 * Interface for the OEntityEgg class
 *
 * @author gregthegeek
 *
 */
public class Egg extends Projectile {

    /**
     * Wraps an OEntityEgg
     *
     * @param base the OEntityEgg to wrap
     */
    public Egg(OEntityEgg base) {
        super(base);
    }

    /**
     * Creates a new OEntityEgg
     *
     * @param world the world to create it in
     */
    public Egg(World world) {
        this(new OEntityEgg(world.getWorld()));
    }

    /**
     * Creates a new OEntityEgg
     *
     * @param world the world to create it in
     * @param shooter the shooter of the egg
     */
    public Egg(World world, LivingEntity shooter) {
        this(new OEntityEgg(world.getWorld(), shooter.getEntity()));
    }

    /**
     * Creates a new OEntityEgg
     *
     * @param world the world in which to create it
     * @param x the x coordinate at which to create it
     * @param y the y coordinate at which to create it
     * @param z the z coordinate at which to create it
     */
    public Egg(World world, double x, double y, double z) {
        this(new OEntityEgg(world.getWorld(), x, y, z));
    }

    /**
     * Creates a new OEntityEgg
     *
     * @param location the location at which to create it
     */
    public Egg(Location location) {
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
    public OEntityEgg getEntity() {
        return (OEntityEgg) super.getEntity();
    }
}
