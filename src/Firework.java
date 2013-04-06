import java.awt.Color;

/**
 * Interface for Firework rockets.
 *
 * @author gregthegeek
 *
 */
public class Firework extends Projectile {

    /**
     * Wraps an OEntityFireworkRocket.
     *
     * @param entity The entity to wrap.
     */
    public Firework(OEntityFireworkRocket entity) {
        super(entity);
    }

    /**
     * Creates a new firework.
     *
     * @param world The world to create it in.
     */
    public Firework(World world) {
        this(new OEntityFireworkRocket(world.getWorld()));
    }

    /**
     * Creates a new firework.
     *
     * @param world The world to create it in.
     * @param x The x coordinate at which to create it.
     * @param y The z coordinate at which to create it.
     * @param z The z coordinate at which to create it.
     * @param item The item used to create this firework.
     */
    public Firework(World world, double x, double y, double z, Item item) {
        this(new OEntityFireworkRocket(world.getWorld(), x, y, z, item.getBaseItem()));
    }

    /**
     * Creates a new firework.
     *
     * @param location The location at which to create it.
     * @param item The item used to create this firework.
     */
    public Firework(Location location, Item item) {
        this(location.getWorld(), location.x, location.y, location.z, item);
    }

    /**
     * Returns the firework item used to create this firework.
     *
     * @return The firework item.
     */
    public Item getItem() {
        return new Item(getEntity().ah.f(8));
    }

    /**
     * Sets the firework item used to create this firework.
     *
     * @param item The firework item.
     */
    public void setItem(Item item) {
        getEntity().ah.b(8, item.getBaseItem());
    }

    @Override
    public OEntityFireworkRocket getEntity() {
        return (OEntityFireworkRocket) super.getEntity();
    }

    /**
     * Returns the distance of this firework's flight, in blocks.
     *
     * @return The distance in blocks.
     */
    public int getFlightDistance() {
        return getEntity().b;
    }

    /**
     * Sets the distance of this firework's flight, in blocks.
     *
     * @param blocks The number of blocks this firework will fly before exploding.
     */
    public void setFlightDistance(int blocks) {
        getEntity().b = blocks;
    }

    /**
     * Returns the data tag containing information about this firework.
     *
     * @return NBTTagCompound
     */
    public NBTTagCompound getDataTag() {
        return getItem().getDataTag().getNBTTagCompound("Fireworks");
    }

    /**
     * Sets the data tag containing information about this firework.
     *
     * @param tag The NBTTagCompound.
     */
    public void setDataTag(NBTTagCompound tag) {
        getItem().getDataTag().add("Fireworks", tag);
    }

    /**
     * Returns an array of the explosions associated with this firework.
     *
     * @return An array of the explosions.
     */
    public FireworkExplosion[] getExplosions() {
        NBTTagList list = getDataTag().getNBTTagList("Explosions");
        FireworkExplosion[] rt = new FireworkExplosion[list.size()];
        for(int i=0; i<rt.length; i++) {
            NBTTagCompound tag = (NBTTagCompound) list.get(i);
            FireworkExplosion.Shape shape = FireworkExplosion.Shape.values()[tag.getByte("Type")];
            int[] colors = tag.getIntArray("Colors");
            Color[] c = new Color[colors.length];
            for(int q=0; q<c.length; q++) {
                c[i] = new Color(colors[i]);
            }
            boolean twinkle = tag.hasTag("Flicker");
            boolean trail = tag.hasTag("Trail");
            rt[i] = new FireworkExplosion(shape, twinkle, trail, c);
        }
        return rt;
    }

    /**
     * Removes the explosion located at the specified index.
     *
     * @param index The index of the explosion to remove.
     */
    public void removeExplosion(int index) {
        getDataTag().getNBTTagList("Explosions").remove(index);
    }

    /**
     * Adds an explosion to this firework.
     *
     * @param explosion The explosion to add.
     */
    public void addExplosion(FireworkExplosion explosion) {
        getDataTag().getNBTTagList("Explosions").add(explosion.toNBT());
    }

    /**
     * Sets the amount of time before this rocket explodes.
     *
     * @param life Probably ticks.
     */
    public void setRemainingLife(int life) {
        getEntity().a = life;
    }

    /**
     * Returns the amount of time before this rocket explodes.
     *
     * @return int
     */
    public int getRemainingLife() {
        return getEntity().a;
    }

    /**
     * Returns a firework rocket item without any effects.
     *
     * @param power The amount of power the rocket should be created with (usually 1-3).
     * @return Item
     */
    public static Item getBlankFireworkRocket(byte power) {
        NBTTagCompound tag = new NBTTagCompound("Fireworks");
        tag.add("Flight", power);
        tag.add("Explosions", new NBTTagList("Explosions"));
        Item i = new Item(Item.Type.FireworkRocket);
        NBTTagCompound pTag = new NBTTagCompound("tag");
        pTag.add("Fireworks", tag);
        i.setDataTag(pTag);
        return i;
    }
}
