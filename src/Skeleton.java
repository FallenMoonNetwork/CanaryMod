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
        // SRG return getEntity().func_82202_m() == 1;
        return getEntity().bV() == 1;
    }

    /**
     * Sets whether or not this is a wither skeleton
     *
     * @param isWither
     */
    public void setWither(boolean isWither) {
        // SRG getEntity().func_82201_a(isWither ? 1 : 0);
        getEntity().a(isWither ? 1 : 0);
    }

    @Override
    public OEntitySkeleton getEntity() {
        return (OEntitySkeleton) entity;
    }

    /**
     * Make this skeleton launch an arrow at the living entity.
     * This method mimics Minecraft's force calculation.
     *
     * @param ent the entity to shoot at
     */
    public void shootAt(LivingEntity ent) {
        double distanceToEntity = this.getLocation().distanceTo(ent.getLocation());
        float power = Math.min(Math.max(0.1F, (float) distanceToEntity / 15F), 1.0F);
        this.shootAt(ent, power);
    }

    /**
     * Make this skeleton launch an arrow at the entity with specified power.
     * @param ent The entity to shoot at
     * @param power The power to shoot with (0.0-1.0)
     */
    public void shootAt(LivingEntity ent, float power) {
        // SRG getEntity().func_82196_d(ent.getEntity(), power);
        getEntity().a(ent.getEntity(), power);
    }
}
