public class PlayerInventory extends ItemArray<OInventoryPlayer> {
    private final OEntityPlayer user;

    // These first two constructors are to maintain backward compatibility
    public PlayerInventory(Player player) {
        this((HumanEntity) player);
    }

    public PlayerInventory(OContainer oContainer, Player player) {
        this(oContainer, (HumanEntity) player);
    }

    public PlayerInventory(HumanEntity human) {
        this(null, human);
    }
    public PlayerInventory(OContainer oContainer, HumanEntity human) {
        // SRG super(oContainer, human.getEntity().field_71071_by);
        super(oContainer, human.getEntity().bn);
        user = human.getEntity();
    }

    /**
     * Give an item to this inventory.
     * The amount that does not fit into the inventory is dropped.
     * This method takes enchantments into account.
     * @param itemId The id of the item to give.
     * @param amount The amount of the item to give.
     * @see #insertItem(Item)
     */
    public void giveItem(int itemId, int amount) {
        Item toGive = new Item(itemId, amount);
        if (!this.insertItem(toGive)) {
            // Not all was given, drop rest (insertItem updates amount).
            this.getPlayer().giveItemDrop(toGive);
        }
    }

    @Override
    public void update() {
        // SRG user.func_70071_h_();
        user.l_();
    }

    /**
     * Returns a String value representing this PlayerInventory
     *
     * @return String representation of this PlayerInventory
     */
    @Override
    public String toString() {
        return String.format("PlayerInventory[user=%s]", this.getHuman());
    }

    public HumanEntity getHuman() {
        return user.getEntity();
    }

    /**
     * Returns the owner of this PlayerInventory
     *
     * @return Player
     */
    public Player getPlayer() {
        return getHuman() instanceof Player ? (Player) getHuman() : null;
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
     * Returns Item held in player's mouse cursor. Ex: When moving items within an inventory.
     *
     * @return Item
     */
    public Item getCursorItem() {
        // SRG OItemStack itemstack = container.func_70445_o();
        OItemStack itemstack = container.o();
        return itemstack == null ? null : new Item(itemstack);
    }

    public void setCursorItem(Item item) {
        // SRG container.func_70437_b(item.getBaseItem());
        container.b(item.getBaseItem());
    }

    /**
     * Returns the highlighted slot on the quickbar (0-8).
     * @return
     */
    public int getSelectedSlot() {
        // SRG return container.field_70461_c;
        return container.c;
    }
}
