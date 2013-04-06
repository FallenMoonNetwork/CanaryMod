/**
 * Interface for skeletons
 *
 * @author gregthegeek
 *
 */
public class Skeleton extends Mob {

    /**
     * Creates a skeleton wrapper
     *
     * @param oentity The entity to wrap
     */
    public Skeleton(OEntitySkeleton oentity) {
        super(oentity);
    }

    /**
     * Creates a new skeleton
     *
     * @param world The world to create it in
     */
    public Skeleton(World world) {
        super("Skeleton", world);
    }

    /**
     * Creates a new skeleton
     *
     * @param location The location at which to create it
     */
    public Skeleton(Location location) {
        super("Skeleton", location);
    }

    /**
     * Returns whether or not this is a wither skeleton
     *
     * @return
     */
    public boolean isWither() {
        return getEntity().o() == 1;
    }

    /**
     * Sets whether or not this is a wither skeleton
     *
     * @param isWither
     */
    public void setWither(boolean isWither) {
        getEntity().a(isWither ? 1 : 0);
    }

    @Override
    public OEntitySkeleton getEntity() {
        return (OEntitySkeleton) entity;
    }

    /**
     * Make this skeleton launch an arrow at the living entity
     *
     * @param e the entity to shoot at
     */
    public void shootAt(LivingEntity e) {
        getEntity().d(e.getEntity());
    }
}
