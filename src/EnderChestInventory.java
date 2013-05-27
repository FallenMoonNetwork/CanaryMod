/**
 * Interface for accessing the inventories of ender chests
 *
 * @author gregthegeek
 *
 */
public class EnderChestInventory extends ItemArray<OInventoryEnderChest> {
    private final HumanEntity owner;

    // These first two constructors are to maintain backward compatibility
    public EnderChestInventory(OInventoryEnderChest container, Player owner) {
        this(container, (HumanEntity) owner);
    }

    public EnderChestInventory(OContainer oContainer, OInventoryEnderChest container, Player owner) {
        this(oContainer, container, (HumanEntity) owner);
    }

    public EnderChestInventory(OInventoryEnderChest container, HumanEntity owner) {
        this(null, container, owner);
    }

    public EnderChestInventory(OContainer oContainer, OInventoryEnderChest container, HumanEntity owner) {
        super(oContainer, container);
        this.owner = owner;
    }

    /**
     * Returns an NBTTagList with data about the contents of this inventory.
     *
     * @return
     */
    public NBTTagList writeToTag() {
        return new NBTTagList(container.h());
    }

    /**
     * Sets this inventory's data to equal the contents of an NBTTagList.
     *
     * @param tag the tag to read data from
     */
    public void readFromTag(NBTTagList tag) {
        container.a(tag.getBaseTag());
    }

    @Override
    public void update() {
        owner.getEntity().l_();
    }

    @Override
    public String getName() {
        return container.getName();
    }

    @Override
    public void setName(String value) {
        container.setName(value);
    }

    /**
     * Returns the player that this ender chest inventory belongs to.
     *
     * @return
     */
    public Player getPlayer() {
        return getHuman() instanceof Player ? (Player) owner : null;
    }

    /**
     * Returns the {@link HumanEntity} that this ender chest inventory belongs
     * to.
     * @return the {@link HumanEntity} that this ender chest inventory belongs
     * to
     */
    public HumanEntity getHuman() {
        return owner;
    }
}
