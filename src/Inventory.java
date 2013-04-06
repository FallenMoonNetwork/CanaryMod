/**
 * Inventory.java - Interface to player inventories
 *
 * @author James
 */
public interface Inventory {

    /**
     * Updates this inventory, sending the new information to clients.
     */
    public void update();

    /**
     * Clears this inventory.
     */
    public void clearContents();

    /**
     * Adds the specified item. If the item doesn't have a slot, it will get the
     * nearest available slot. If amount is equal to 0, it will delete the item
     * if a slot is specified.
     *
     * @param item
     *            item to add
     */
    public void addItem(Item item);

    /**
     * Retrieves from the slot
     *
     * @param slot
     *            slot to get item from
     * @return item
     */
    public Item getItemFromSlot(int slot);

    /**
     * Retrieves from the slot
     *
     * @param type
     * @return item
     */
    public Item getItemFromId(Item.Type type);

    /**
     * Retrieves from the slot
     *
     * @param id
     * @return item
     */
    public Item getItemFromId(int id);

    /**
     * Retrieves from the slot
     *
     * @param type
     * @param maxAmount
     * @return item
     */
    public Item getItemFromId(Item.Type type, int maxAmount);

    /**
     * Retrieves from the slot
     *
     * @param id
     * @param maxAmount
     * @return item
     */
    public Item getItemFromId(int id, int maxAmount);

    /**
     * Retrieves from the slot
     *
     * @param id
     * @param maxAmount
     * @param dmg
     * @return
     */
    public Item getItemFromId(int id, int maxAmount, int dmg);

    /**
     * Gets the nearest empty slot. -1 if there's no empty slots
     *
     * @return nearest empty slot
     */
    public int getEmptySlot();

    /**
     * Removes the item from the slot
     *
     * @param slot
     *            slot to remove item from
     */
    public void removeItem(int slot);

    /**
     * Sets the specified slot with item
     *
     * @param item
     *            item to set
     * @param slot
     *            slot to use
     */
    public void setSlot(Item item, int slot);

    /**
     * Replaces the slot with the specified item.
     *
     * @param type
     *            type of the item to put into the slot.
     * @param amount
     *            amount of the item to put into the slot.
     * @param slot
     *            the id of the slot.
     */
    public void setSlot(Item.Type type, int amount, int slot);

    /**
     * Replaces the slot with the specified item.
     *
     * @param itemId
     *            item id of the item to put into the slot.
     * @param amount
     *            amount of the item to put into the slot.
     * @param slot
     *            the id of the slot.
     */
    public void setSlot(int itemId, int amount, int slot);

    /**
     * Replaces the slot with the specified item.
     *
     * @param itemId
     *            item id of the item to put into the slot.
     * @param amount
     *            amount of the item to put into the slot.
     * @param damage
     *            remaining damage of the item to put into the slot.
     * @param slot
     *            the id of the slot.
     */
    public void setSlot(int itemId, int amount, int damage, int slot);

    /**
     * Removes the item. No slot needed, it will go through the inventory until
     * the exact item specified is removed.
     *
     * @param item
     *            item to remove
     */
    public void removeItem(Item item);

    /**
     * Removes the item. No slot needed, it will go through the inventory until
     * the amount specified is removed.
     *
     * @param type
     *            item to remove
     * @param amount
     *            amount to remove
     */
    public void removeItem(Item.Type type, int amount);

    /**
     * Removes the item. No slot needed, it will go through the inventory until
     * the amount specified is removed.
     *
     * @param id
     *            item to remove
     * @param amount
     *            amount to remove
     */
    public void removeItem(int id, int amount);

    /**
     * Removes items from the inventory that match the given item until the amount in the given item is removed.
     *
     * @param item The item type to remove
     */
    public void removeItemOverStacks(Item item);

    /**
     * Checks to see if this getArray() has an item identical to the one specified.
     *
     * @param item
     * @return
     */
    public boolean hasItem(Item item);

    /**
     * Checks to see if this getArray() has one slot that has the given item
     * type
     *
     * @param type
     * @return
     */
    public boolean hasItem(Item.Type type);

    /**
     * Checks to see if this getArray() has one slot that has the given item id
     *
     * @param itemId
     * @return
     */
    public boolean hasItem(int itemId);

    /**
     * Checks to see if this getArray() has one slot that has the item id and
     * equal or more to the amount.
     *
     * @param type
     *            item to look for
     * @param minimum
     *            amount of items that must be in the stack
     * @return
     */
    public boolean hasItem(Item.Type type, int minimum);

    /**
     * Checks to see if this getArray() has one slot that has the item id and
     * equal or more to the amount.
     *
     * @param itemId
     *            item to look for
     * @param minimum
     *            amount of items that must be in the stack
     * @return
     */
    public boolean hasItem(int itemId, int minimum);

    /**
     * Checks to see if this getArray() has one slot that has the item id and
     * equal or more to the amount.
     *
     * @param itemId
     * @param minimum
     * @param maximum
     * @return
     */
    public boolean hasItem(int itemId, int minimum, int maximum);

    /**
     * Returns the contents of this chest
     *
     * @return contents
     */
    public Item[] getContents();

    /**
     * Sets the contents
     *
     * @param contents
     *            contents to set
     */
    public void setContents(Item[] contents);

    /**
     * Returns the <tt>Inventory</tt>'s size.
     * @return The size of the <tt>Inventory</tt>
     */
    public int getContentsSize();

    /**
     * Returns the name for this <tt>Inventory</tt>.
     * @return This <tt>Inventory</tt>'s name
     */
    public String getName();

    /**
     * Sets the name for this <tt>Inventory</tt>.
     * @param value This <tt>Inventory</tt>'s new name
     */
    public void setName(String value);

    /**
     * Adds the item to the set, appending to stacks
     * or with no or full stack, adds a new stack.
     * Stack sizes correspond with the max of the item
     *
     * @param item
     * @return true if all items are in the inventory,
     *          false when items are left over. item is updated to the leftover-amount.
     */
    public boolean insertItem(Item item);

    /**
     * Checks to see if OContainer is defined, which
     * may be null due to legacy
     *
     * @return
     */
    public boolean hasOContainer();

    /**
     * Basic get for the stored OContainer
     *
     * @return
     */
    public OContainer getOContainer();

    /**
     * Basic set for the stored OContainer
     *
     * @param oContainer
     */
    public void setOContainer(OContainer oContainer);

    /**
     * Attempts to send slot change updates to clients.
     * This has the same goal as update(), but works
     * differently. The separation is to keep the original
     * process, while also adding this new one.
     *
     * @return false if something is preventing it from
     *          attempting to send updates.
     */
    public boolean updateChangedSlots();

    /**
     * Attempts to send slot update to clients.
     *
     * @return false if something is preventing it from
     *          attempting to send updates.
     */
    public boolean updateSlot(int index);
}
