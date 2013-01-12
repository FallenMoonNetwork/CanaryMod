import java.util.Arrays;

/**
 * ItemArray.java - Interface to OItemStack[] so I don't have to copy+paste this a bunch
 * of times
 *
 * @param <C> The container of the items
 * @author James
 */
public abstract class ItemArray<C extends Container<OItemStack>> implements Inventory {
    protected C container;
    private OContainer oContainer;

    public ItemArray(C container) {
        this(null, container);
    }
    
    public ItemArray(OContainer oContainer, C container) {
        this.container = container;
        this.oContainer = oContainer;
    }

    @Override
    public int getContentsSize() {
        return container.getContentsSize();
    }

    @Override
    public void addItem(Item item) {
        if (item == null) {
            return;
        }

        int slot = item.getSlot();
        int size = getContentsSize();

        if (slot < size && slot >= 0) {
            if (item.getAmount() <= 0) {
                removeItem(slot);
            } else if (Item.isValidItem(item.getItemId())) {
                setSlot(item, slot);
            }
        } else if (slot == -1) {
            int newSlot = getEmptySlot();

            if (newSlot != -1) {
                setSlot(item, newSlot);
                item.setSlot(newSlot);
            }
        }
    }

    @Override
    public Item getItemFromSlot(int slot) {
        int size = getContentsSize();

        if (slot < size && slot >= 0) {
            OItemStack result = container.getContentsAt(slot);

            if (result != null) {
                return new Item(result, slot);
            }
        }

        return null;
    }

    @Override
    public Item getItemFromId(Item.Type type) {
        return getItemFromId(type.getId());
    }

    @Override
    public Item getItemFromId(int id) {
        Item[] items = getContents();

        for (Item item : items) {
            if ((item != null) && (item.getItemId() == id)) {
                return item;
            }
        }

        return null;
    }

    @Override
    public Item getItemFromId(Item.Type type, int maxAmount) {
        return getItemFromId(type.getId());
    }

    @Override
    public Item getItemFromId(int id, int maxAmount) {
        Item[] items = getContents();

        for (Item item : items) {
            if ((item != null) && (item.getItemId() == id) && (item.getAmount() <= maxAmount)) {
                return item;
            }
        }

        return null;
    }

    @Override
    public int getEmptySlot() {
        int size = getContentsSize();

        for (int i = 0; size > i; i++) {
            if (container.getContentsAt(i) != null) {
                continue;
            }
            return i;
        }

        return -1;
    }

    @Override
    public void removeItem(int slot) {
        int size = getContentsSize();

        if (slot < size && slot >= 0) {
            container.setContentsAt(slot, null);
        }
    }

    @Override
    public void setSlot(Item item, int slot) {
        int size = getContentsSize();

        if (slot < size && slot >= 0) {
            container.setContentsAt(slot, item == null ? null : item.getBaseItem());
        }
    }

    @Override
    public void setSlot(Item.Type type, int amount, int slot) {
        setSlot(type.getId(), amount, slot);
    }

    @Override
    public void setSlot(int itemId, int amount, int slot) {
        setSlot(itemId, amount, 0, slot);
    }

    @Override
    public void setSlot(int itemId, int amount, int damage, int slot) {
        int size = getContentsSize();

        if (slot < size && slot >= 0) {
            container.setContentsAt(slot, new OItemStack(itemId, (amount > 64 ? (amount == 255 ? -1 : 64) : amount), damage));
        }
    }

    @Override
    public void removeItem(Item item) {
        Item[] items = getContents();

        for (Item i : items) {
            if(i != null && i.equalsIgnoreSlot(item)) {
                removeItem(i.getSlot());
                break;
            }
        }
    }

    @Override
    public void removeItem(Item.Type type, int amount) {
        removeItem(type.getId(), amount);
    }

    @Override
    public void removeItem(int id, int amount) {
        Item[] items = getContents();
        int remaining = amount;

        for (Item item : items) {
            if ((item != null) && (item.getItemId() == id)) {
                if (item.getAmount() == remaining) {
                    removeItem(item.getSlot());
                    return;
                } else if (item.getAmount() > remaining) {
                    setSlot(id, item.getAmount() - remaining, item.getSlot());
                    return;
                } else {
                    removeItem(item.getSlot());
                    remaining -= item.getAmount();
                }
            }
        }
    }

    @Override
    public void removeItemOverStacks(Item item) {
        Item[] items = getContents();
        int remaining = item.getAmount();

        for(Item i : items) {
            if(item.equalsIgnoreSlotAndAmount(i)) {
                if(i.getAmount() == remaining) {
                    removeItem(i.getSlot());
                    return;
                } else if(i.getAmount() > remaining) {
                    setSlot(i.getItemId(), i.getAmount() - remaining, i.getSlot());
                    return;
                } else {
                    removeItem(i.getSlot());
                    remaining -= i.getAmount();
                }
            }
        }
    }

    @Override
    public boolean hasItem(Item item) {
        Item[] items = getContents();

        for(Item i : items) {
            if(i != null && i.equalsIgnoreSlot(item)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean hasItem(Item.Type type) {
        return hasItem(type, 1);
    }

    @Override
    public boolean hasItem(int itemId) {
        return hasItem(itemId, 1);
    }

    @Override
    public boolean hasItem(Item.Type type, int minimum) {
        Item[] items = getContents();

        for (Item item : items) {
            if ((item != null) && (item.getType() == type) && (item.getAmount() >= minimum)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean hasItem(int itemId, int minimum) {
        Item[] items = getContents();

        for (Item item : items) {
            if ((item != null) && (item.getItemId() == itemId) && (item.getAmount() >= minimum)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean hasItem(int itemId, int minimum, int maximum) {
        Item[] items = getContents();

        for (Item item : items) {
            if ((item != null) && (item.getItemId() == itemId) && (item.getAmount() >= minimum) && (item.getAmount() <= maximum)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public Item[] getContents() {
        int arraySize = getContentsSize();
        Item[] rt = new Item[arraySize];

        for (int i = 0; i < arraySize; i++) {
            rt[i] = getItemFromSlot(i);
        }

        return rt;
    }

    @Override
    public void setContents(Item[] contents) {
        int arraySize = getContentsSize();

        for (int i = 0; i < arraySize; i++) {
            if (contents[i] == null) {
                removeItem(i);
            } else {
                setSlot(contents[i], i);
            }
        }
    }

    @Override
    public void clearContents() {
        int size = getContentsSize();

        for (int i = 0; size > i; i++) {
            container.setContentsAt(i, null);
        }
    }

    @Override
    public boolean insertItem(Item item) {
        int amount = item.getAmount();
        Item itemExisting;
        int maxAmount = item.getMaxAmount();

        while (amount > 0) {
            // Get an existing item with at least 1 spot free
            itemExisting = this.getItemFromId(item.getItemId(), maxAmount-1);

            // Add the items to the existing stack of items
            if (itemExisting != null &&
                    (itemExisting.getEnchantment() == null || etc.getInstance().allowEnchantableItemStacking)) {
                // Add as much items as possible to the stack
                int k = Math.min(maxAmount - itemExisting.getAmount(), item.getAmount());
                this.setSlot(item.getItemId(), itemExisting.getAmount() + k, itemExisting.getSlot());
                amount -= k;
                continue;
            }
            // We still have slots, but no stack, create a new stack.
            if (this.getEmptySlot() != -1) {
                this.addItem(new Item(item.getItemId(), amount));
                amount = 0;
                continue;
            }

            // No free slots, no incomplete stacks: full
            // make sure the stored items are removed
            item.setAmount(amount);
            return false;
        }

        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ItemArray) {
            return Arrays.equals(getContents(), ((ItemArray) obj).getContents());
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("ItemArray[size=%d, contents=%s]", getContentsSize(), Arrays.toString(getContents()));
    }
    
    @Override
    public boolean hasOContainer() {
        return oContainer != null;
    }
    
    @Override
    public OContainer getOContainer() {
        return oContainer;
    }
    
    @Override
    public void setOContainer(OContainer oContainer) {
        this.oContainer  = oContainer;
    }
    
    @Override
    public boolean updateChangedSlots() {
        if(oContainer == null)
            return false;
        oContainer.updateChangedSlots();
        return true;
    }
    
    @Override
    public boolean updateSlot(int index) {
        if(oContainer == null)
            return false;
        oContainer.updateSlot(index);
        return true;
    }
}
