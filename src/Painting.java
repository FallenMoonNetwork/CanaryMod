import java.util.HashMap;


/**
 * Interface for paintings
 *
 * @author gregthegeek
 *
 */
public class Painting extends HangingEntity {
    public enum Type {
        /**
         * A kebab with three green chili peppers
         */
        KEBAB(OEnumArt.a),
        /**
         * Free-look perspective of the map de_aztec from the video game Counter Strike
         */
        AZTEC(OEnumArt.b),
        /**
         * A man wearing a fez in a desert-type land standing next to a house and a bush
         */
        ALBAN(OEnumArt.c),
        /**
         * Free-look perspective of the map de_aztec from the video game Counter Strike
         */
        AZTEC2(OEnumArt.d),
        /**
         * Painting of the Counter Strike map de_dust2
         */
        BOMB(OEnumArt.e),
        /**
         * Still life painting of two plants in pots
         */
        PLANT(OEnumArt.f),
        /**
         * Painting of a view of some wastelands
         */
        WASTELAND(OEnumArt.g),
        /**
         * Some men and women skinny-dipping in a pool over a cube of sorts
         */
        POOL(OEnumArt.h),
        /**
         * Two hikers with pointy beards seemingly greeting each other
         */
        COURBET(OEnumArt.i),
        /**
         * Painting of a view of mountains and a lake, with a small photo of a mountan and a plant with red dots on the window ledge
         */
        SEA(OEnumArt.j),
        /**
         * Painting of a view of mountains at sunset
         */
        SUNSET(OEnumArt.k),
        /**
         * Painting of a view of mountains and a lake, with a small photo of a mountain and a creeper looking at the viewer through a window
         */
        CREEBET(OEnumArt.l),
        /**
         * Painting of a man with a walking stick, traversing rocky plains
         */
        WANDERER(OEnumArt.m),
        /**
         * A small picture of King Graham, the player character in the King's Quest series
         */
        GRAHAM(OEnumArt.n),
        /**
         * A hand holding a match, causing pixelated fire on a white cubic glass fireplace
         */
        MATCH(OEnumArt.o),
        /**
         * Painting of a statue bust surrounded by pixelated fire
         */
        BUST(OEnumArt.p),
        /**
         * Painting of scenery from Space Quest I, with the character Graham from King's Quest
         */
        STAGE(OEnumArt.q),
        /**
         * Painting of an angel praying into what appears to be a void with pixelated fire below
         */
        VOID(OEnumArt.r),
        /**
         * Painting of a skeleton at night with red flowers in the foreground
         */
        SKULL_AND_ROSES(OEnumArt.s),
        /**
         * Painting depicting the creation of the wither
         */
        WITHER(OEnumArt.t),
        /**
         * Two pixelated men poised to fight
         */
        FIGHTERS(OEnumArt.u),
        /**
         * A painting of the main character of the classic Atari game International Karate fighting a large hand
         */
        POINTER(OEnumArt.v),
        /**
         * Painting of a girl that is pointing to a pig on a canvas
         */
        PIG_SCENE(OEnumArt.w),
        /**
         * A skull on pixelated fire, in the background there is a moon in a clear night sky
         */
        BURNING_SKULL(OEnumArt.x),
        /**
         * A painting of the "Mean Midget" from Grim Fandago
         */
        SKELETON(OEnumArt.y),
        /**
         * A paper-looking screenshot of level 100 from the original Donkey Kong arcade game
         */
        DONKEY_KONG(OEnumArt.z);

        private static HashMap<OEnumArt, Type> map;
        private final OEnumArt base;

        private Type(OEnumArt base) {
            this.base = base;
            add(base, this);
        }

        /**
         * Returns the OEnumArt that this wraps
         *
         * @return
         */
        public OEnumArt getBase() {
            return this.base;
        }

        /**
         * Returns the width of this painting in pixels
         *
         * @return
         */
        public int getWidth() {
            return this.base.C;
        }

        /**
         * Returns the height of this painting in pixels
         *
         * @return
         */
        public int getHeight() {
            return this.base.D;
        }

        @SuppressWarnings("MapReplaceableByEnumMap") // Doesn't work on the .class
        private static void add(OEnumArt base, Type type) {
            if(map == null) {
                map = new HashMap<OEnumArt, Type>();
            }
            map.put(base, type);
        }

        /**
         * Get the Canary type for an <tt>OEnumArt</tt>.
         * @param base The <tt>OEnumArt</tt> to get the Canary type for.
         * @return The Canary <tt>Type</tt> for <tt>base</tt>
         */
        public static Type fromBase(OEnumArt base) {
            return map.get(base);
        }
    }

    /**
     * Creates a new painting wrapper
     *
     * @param entity The entity to wrap
     */
    public Painting(OEntityPainting entity) {
        super(entity);
    }

    /**
     * Creates a new painting
     *
     * @param world The world in which to create it
     */
    public Painting(World world) {
        super(new OEntityPainting(world.getWorld()));
    }

    /**
     * Creates a new painting
     *
     * @param world The world in which to create it
     * @param x The x coordinate at which to create it
     * @param y The y coordinate at which to create it
     * @param z The z coordinate at which to create it
     */
    public Painting(World world, int x, int y, int z) {
        this(world, x, y, z, Position.NORTH_FACE);
    }

    /**
     * Creates a new painting
     *
     * @param world The world in which to create it
     * @param x The x coordinate at which to create it
     * @param y The y coordinate at which to create it
     * @param z The z coordinate at which to create it
     * @param position The position in which to create it
     */
    public Painting(World world, int x, int y, int z, Position position) {
        this(new OEntityPainting(world.getWorld(), x, y, z, position.ordinal()));
    }

    @Override
    public OEntityPainting getEntity() {
        return (OEntityPainting) entity;
    }

    /**
     * Returns the type of painting this is
     *
     * @return
     */
    public Type getType() {
        return Type.fromBase(getEntity().e);
    }

    /**
     * Sets the type of painting this is
     *
     * @param type
     */
    public void setType(Type type) {
        getEntity().e = type.getBase();
    }

    /**
     * Returns the width of the painting in pixels
     *
     * @return
     */
    public int getWidth() {
        return getEntity().d();
    }

    /**
     * Returns the height of the painting in pixels
     *
     * @return
     */
    public int getHeight() {
        return getEntity().g();
    }
}
