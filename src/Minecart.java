import java.util.HashMap;
import java.util.Map;


/**
 * Minecart - Used for manipulating minecarts
 *
 * @author tw1nk
 */
public class Minecart extends BaseVehicle {

    /**
     * Type of minecart
     */
    public enum Type {

        /**
         * Base minecart.
         */
        Minecart(0), //
        /**
         * Storage minecart. Has storage for items.
         */
        StorageCart(1), //
        /**
         * Powered minecart. Has storage for fuel.
         */
        PoweredMinecart(2), //
        /**
         * TNT minecart.
         */
        TNTMinecart(3), //
        /**
         * Mob spawner minecart.
         */
        MobSpawnerCart(4), //
        /**
         * Hopper minecart.
         */
        HopperMinecart(5);

        private final int                       id;
        private static final Map<Integer, Type> lookup = new HashMap<Integer, Type>();

        static {
            for (Type t : Type.values()) {
                lookup.put(t.getType(), t);
            }
        }

        private Type(int id) {
            this.id = id;
        }

        public int getType() {
            return id;
        }

        public static Type fromId(final int type) {
            return lookup.get(type);
        }
    }

    /**
     * Creates an interface for minecart.
     *
     * @param o
     */
    public Minecart(OEntityMinecart o) {
        super(o);
    }

    /**
     * Create a new Minecart at the given position
     *
     * @param x
     * @param y
     * @param z
     * @param type
     *            0=Minecart, 1=StorageCart, 2=PoweredMinecart
     * @deprecated Use {@link #Minecart(World, double, double, double, Minecart.Type)} instead.
     */
    @Deprecated
    public Minecart(double x, double y, double z, Type type) {
        this(etc.getServer().getDefaultWorld(), x, y, z, type);
    }

    /**
     * Create a new Minecart at the given position
     *
     * @param world The world for the new minecart
     * @param x The x coordinate for the new minecart
     * @param y The y coordinate for the new minecart
     * @param z The z coordinate for the new minecart
     * @param type The type for the new minecart
     * @deprecated The minecart system has had an overhaul. Use an appropriate
     * subclass, or the constructor without type for an empty cart.
     *
     */
    @Deprecated
    public Minecart(World world, double x, double y, double z, Type type) {
        this(OEntityMinecart.a(world.getWorld(), x, y, z, type.getType()));
        this.spawn();
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
    public Minecart(World world, double x, double y, double z) {
        this(new OEntityMinecartEmpty(world.getWorld(), x, y, z));
    }

    /**
     * Returns the entity we're wrapping.
     *
     * @return
     */
    @Override
    public OEntityMinecart getEntity() {
        return (OEntityMinecart) entity;
    }

    /**
     * Sets the current amount of damage the minecart has taken.
     * Decreases over time. The cart breaks when this is over 40.
     *
     * @param damage This minecart's new damage value
     */
    public void setDamage(int damage) {
        getEntity().h(damage);
    }

    /**
     * Gets the current amount of damage the minecart has taken.
     * Decreases over time. The cart breaks when this is over 40.
     *
     * @return This minecart's current damage value
     */
    public int getDamage() {
        return getEntity().i();
    }

    /**
     * Returns the type of this minecart.
     *
     * @return type
     */
    public Type getType() {
        return Type.fromId(getEntity().l());
    }

    /**
     * Returns the storage for this minecart. Returns null if minecart is not a
     * storage or powered minecart.
     *
     * @return storage
     * @deprecated The minecart system has had an overhaul. Use an appropriate
     * subclass.
     */
    public StorageMinecart getStorage() {
        if (getType() == Type.StorageCart || getType() == Type.PoweredMinecart) {
            return new StorageMinecart(getEntity());
        }
        return null;
    }
}
