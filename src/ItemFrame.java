
/**
 * Interface for item frames
 *
 * @author gregthegeek
 *
 */
public class ItemFrame extends HangingEntity {

    /**
     * Creates a new ItemFrame wrapper
     *
     * @param entity The item frame entity to wrap
     */
    public ItemFrame(OEntityItemFrame entity) {
        super(entity);
    }

    /**
     * Creates a new ItemFrame
     *
     * @param world The world it create it in
     */
    public ItemFrame(World world) {
        super(new OEntityItemFrame(world.getWorld()));
    }

    /**
     * Creates a new ItemFrame
     *
     * @param world The world to create it in
     * @param x The x coordinate at which to create it
     * @param y The y coordinate at which to create it
     * @param z The z coordinate at which to create it
     */
    public ItemFrame(World world, int x, int y, int z) {
        this(world, x, y, z, Position.NORTH_FACE);
    }

    /**
     * Creates a new ItemFrame
     *
     * @param world The world to create it in
     * @param x The x coordinate at which to create it
     * @param y The y coordinate at which to create it
     * @param z The z coordinate at which to create it
     * @param position The position in which to create it
     */
    public ItemFrame(World world, int x, int y, int z, Position position) {
        this(new OEntityItemFrame(world.getWorld(), x, y, z, position.ordinal()));
    }

    @Override
    public OEntityItemFrame getEntity() {
        return (OEntityItemFrame) entity;
    }

    /**
     * Returns the item visible in the frame
     *
     * @return
     */
    public Item getFramedItem() {
        return new Item(getEntity().i());
    }

    /**
     * Sets the item displayed in the frame
     *
     * @param item The item to be displayed
     */
    public void setFramedItem(Item item) {
        getEntity().a(item.getBaseItem());
    }

    @Override
    public void setPosition(Position position) {
        if(position.ordinal() > 3) {
            throw new IllegalArgumentException("You cannot set item frames to center positions");
        }
        super.setPosition(position);
    }
}
