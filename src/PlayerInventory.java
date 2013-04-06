public class PlayerInventory extends ItemArray<OInventoryPlayer> {
    private final OEntityPlayerMP user;

    public PlayerInventory(Player player) {
        this(null, player);
    }
    public PlayerInventory(OContainer oContainer, Player player) {
        super(oContainer, player.getUser().bK);
        user = player.getUser();
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
        user.l_();
    }

    /**
     * Returns a String value representing this PlayerInventory
     *
     * @return String representation of this PlayerInventory
     */
    @Override
    public String toString() {
        return String.format("PlayerInventory[user=%s]", user.getPlayer());
    }

    /**
     * Returns the owner of this PlayerInventory
     *
     * @return Player
     */
    public Player getPlayer() {
        return user.getPlayer();
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
        OItemStack itemstack = container.o();
        return itemstack == null ? null : new Item(itemstack);
    }

    public void setCursorItem(Item item) {
        container.b(item.getBaseItem());
    }
}
