/**
 * Interface for Creepers
 *
 * @author gregthegeek
 *
 */

public class Creeper extends Mob {

    /**
     * Creates a creeper wrapper
     *
     * @param oentity The base entity to wrap
     */
    public Creeper(OEntityCreeper oentity) {
        super(oentity);
    }

    /**
     * Creates a new creeper
     *
     * @param world The world to create it in
     */
    public Creeper(World world) {
        super("Creeper", world);
    }

    /**
     * Creates a new creeper
     *
     * @param location The location at which to create it
     */
    public Creeper(Location location) {
        super("Creeper", location);
    }

    /**
     * Returns whether or not this creeper is charged
     *
     * @return
     */
    public boolean isCharged() {
        return getEntity().m();
    }

    /**
     * Sets whether or not this creeper is charged
     *
     * @param charged
     */
    public void setCharged(boolean charged) {
        getEntity().ah.b(17, (byte) (charged ? 1 : 0));
    }

    @Override
    public OEntityCreeper getEntity() {
        return (OEntityCreeper) entity;
    }
}
