

/**
 * Mob.java - Interface for mobs
 *
 * @author James
 */
public class Mob extends LivingEntity {

    /**
     * Creates a mob interface
     *
     * @param locallb
     *            name of mob
     */
    public Mob(OEntityLiving locallb) {
        super(locallb);
    }

    /**
     * Creates a mob interface
     *
     * @param mob
     *            name of mob
     * @deprecated Use {@link #Mob(java.lang.String, World)} instead.
     */
    public Mob(String mob) {
        this(mob, etc.getServer().getDefaultWorld());
    }

    /**
     * Creates a mob interface
     * @param mob name of the mob
     * @param world World for the mob
     */
    public Mob(String mob, World world) {
        this((OEntityLiving) OEntityList.a(mob, world.getWorld()));
        this.getEntity().bJ(); // initCreature
    }

    /**
     * Creates a mob interface
     *
     * @param mobName
     *            name of mob
     * @param location
     *            location of mob
     */
    public Mob(String mobName, Location location) {
        this(mobName, location.getWorld());
        teleportTo(location);
    }

    /**
     * Backwards compat.
     */
    public void spawn(Mob rider) {
        this.spawn((LivingEntity) rider);
    }

    /**
     * Returns this mob's name
     *
     * @return name
     */
    @Override
    public String getName() {
        return OEntityList.b(entity);
    }

    /**
     * Returns the current target of the mob
     *
     * @return OEntity
     */
    public OEntity getTarget() {
        if (getEntity() instanceof OEntityGhast) {
            OEntityGhast ghast = (OEntityGhast) getEntity();

            return ghast.getTarget();
        }
        return ((OEntityCreature) getEntity()).a_;
    }

    /**
     * Sets the mobs target
     *
     * @param target the entity to target
     */
    public void setTarget(OEntity target) {
        if (getEntity() instanceof OEntityGhast) {
            OEntityGhast ghast = (OEntityGhast) getEntity();

            ghast.setTarget(target);
            return;
        }
        ((OEntityCreature) getEntity()).a_ = target;
    }

    @Override
    public void setHealth(int health) {
        super.setHealth(health);
        if (health <= 0) {
            dropLoot();
        }
    }

    /**
     * Returns the actual mob
     *
     * @return
     */
    public OEntityLiving getMob() {
        return getEntity();
    }

    /**
     * Checks to see if the mob is a valid mob
     *
     * @param mob
     *            the mob to check
     * @return true of mob is valid
     */
    public static boolean isValid(String mob) {
        if (mob == null) {
            return false;
        }

        OEntity c = OEntityList.a(mob, etc.getServer().getDefaultWorld().getWorld());

        return c instanceof OIMob || c instanceof OIAnimals;
    }

    public boolean isInLove(){
        if (getEntity() instanceof OEntityAnimal){
            return ((OEntityAnimal) getEntity()).r();
        }
        return false;
    }
}
