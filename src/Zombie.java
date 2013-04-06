/**
 * Interface for zombies
 *
 * @author gregthegeek
 *
 */
public class Zombie extends Mob {

    /**
     * Creates a zombie wrapper
     *
     * @param oentity The entity to wrap
     */
    public Zombie(OEntityZombie oentity) {
        super(oentity);
    }

    /**
     * Creates a new zombie
     *
     * @param world The world in which to create it
     */
    public Zombie(World world) {
        super("Zombie", world);
    }

    /**
     * Creates a new zombie
     *
     * @param location The location at which to create it
     */
    public Zombie(Location location) {
        super("Zombie", location);
    }

    /**
     * Returns whether or not this zombie is a baby
     *
     * @return
     */
    public boolean isBaby() {
        return getEntity().h_();
    }

    /**
     * Sets whether or not this zombie is a baby
     *
     * @param isBaby
     */
    public void setBaby(boolean isBaby) {
        getEntity().f(isBaby);
    }

    /**
     * Returns whether or not this zombie is a zombie villager
     *
     * @return
     */
    public boolean isVillager() {
        return getEntity().m();
    }

    /**
     * Sets whether or not this zombie is a zombie villager
     *
     * @param isVillager
     */
    public void setVillager(boolean isVillager) {
        getEntity().g(isVillager);
    }

    @Override
    public OEntityZombie getEntity() {
        return (OEntityZombie) entity;
    }
}
