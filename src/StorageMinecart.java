/**
 * StorageMinecart - So we can access what's in them.
 *
 * @author James
 * @deprecated The minecart system has had an overhaul. Use an appropriate
 * subclass of {@link Minecart}.
 */
@Deprecated
public class StorageMinecart implements Inventory {

    private OEntityMinecart container;
    private ItemArray<OEntityMinecartContainer> ia;

    /**
     * Creates an interface for storage of powered and storage carts.
     *
     * @param cart
     */
    public StorageMinecart(OEntityMinecart cart) {
        this(null, cart);
    }

    public StorageMinecart(OContainer oContainer, OEntityMinecart cart) {
        if (!(cart instanceof OEntityMinecartContainer || cart instanceof OEntityMinecartFurnace)) {
            throw new IllegalArgumentException("Cart must be a container or furnace cart");
        }

        this.container = cart;
        if (cart instanceof OEntityMinecartContainer) {
            this.ia = new ItemArray<OEntityMinecartContainer>(oContainer, (OEntityMinecartContainer) cart) {

                @Override
                public void update() {
                    this.container.k_();
                }

                @Override
                public void setName(String name) {
                    this.container.a(name);
                }

                @Override
                public String getName() {
                    return this.container.b();
                }
            };
        }
    }

    @Override
    public void update() {
    }

    @Override
    public String getName() {
        return this.ia == null ? "" : this.ia.getName();
    }

    @Override
    public void setName(String name) {
        if (this.ia != null) {
            this.ia.setName(name);
        }
    }

    /**
     * Returns the amount of fuel this minecart has.
     * Only useful if this is a powered minecart.
     *
     * @return int
     */
    public int getFuel() {
        return this.ia == null ? ((OEntityMinecartFurnace) container).c : 0;
    }

    /**
     * Returns the amount of fuel this minecart has.
     * Only useful if this is a powered minecart.
     *
     * @param fuel
     */
    public void setFuel(int fuel) {
        if (this.ia == null) {
            ((OEntityMinecartFurnace) container).c = fuel;
        }
    }

    @Override
    public void clearContents() {
        if (this.ia != null) {
            this.ia.clearContents();
        }
    }

    @Override
    public void addItem(Item item) {
        if (this.ia != null) {
            this.ia.addItem(item);
        }
    }

    @Override
    public Item getItemFromSlot(int slot) {
        return this.ia == null ? null : this.ia.getItemFromSlot(slot);
    }

    @Override
    public Item getItemFromId(Item.Type type) {
        return this.ia == null ? null : this.ia.getItemFromId(type);
    }

    @Override
    public Item getItemFromId(int id) {
        return this.ia == null ? null : this.ia.getItemFromId(id);
    }

    @Override
    public Item getItemFromId(Item.Type type, int maxAmount) {
        return this.ia == null ? null : this.ia.getItemFromId(type, maxAmount);
    }

    @Override
    public Item getItemFromId(int id, int maxAmount) {
        return this.ia == null ? null : this.ia.getItemFromId(id, maxAmount);
    }

    @Override
    public Item getItemFromId(int id, int maxAmount, int dmg) {
        return this.ia == null ? null : this.ia.getItemFromId(id, maxAmount, dmg);
    }

    @Override
    public int getEmptySlot() {
        return this.ia == null ? -1 : this.ia.getEmptySlot();
    }

    @Override
    public void removeItem(int slot) {
        if (this.ia != null) {
            this.ia.removeItem(slot);
        }
    }

    @Override
    public void setSlot(Item item, int slot) {
        if (this.ia != null) {
            this.ia.setSlot(item, slot);
        }
    }

    @Override
    public void setSlot(Item.Type type, int amount, int slot) {
        if (this.ia != null) {
            this.ia.setSlot(type, amount, slot);
        }
    }

    @Override
    public void setSlot(int itemId, int amount, int slot) {
        if (this.ia != null) {
            this.ia.setSlot(itemId, amount, slot);
        }
    }

    @Override
    public void setSlot(int itemId, int amount, int damage, int slot) {
        if (this.ia != null) {
            this.ia.setSlot(itemId, amount, damage, slot);
        }
    }

    @Override
    public void removeItem(Item item) {
        if (this.ia != null) {
            this.ia.removeItem(item);
        }
    }

    @Override
    public void removeItem(Item.Type type, int amount) {
        if (this.ia != null) {
            this.ia.removeItem(type, amount);
        }
    }

    @Override
    public void removeItem(int id, int amount) {
        if (this.ia != null) {
            this.ia.removeItem(id, amount);
        }
    }

    @Override
    public void removeItemOverStacks(Item item) {
        if (this.ia != null) {
            this.ia.removeItemOverStacks(item);
        }
    }

    @Override
    public boolean hasItem(Item item) {
        return this.ia == null ? false : this.ia.hasItem(item);
    }

    @Override
    public boolean hasItem(Item.Type type) {
        return this.ia == null ? false : this.ia.hasItem(type);
    }

    @Override
    public boolean hasItem(int itemId) {
        return this.ia == null ? false : this.ia.hasItem(itemId);
    }

    @Override
    public boolean hasItem(Item.Type type, int minimum) {
        return this.ia == null ? false : this.ia.hasItem(type, minimum);
    }

    @Override
    public boolean hasItem(int itemId, int minimum) {
        return this.ia == null ? false : this.ia.hasItem(itemId, minimum);
    }

    @Override
    public boolean hasItem(int itemId, int minimum, int maximum) {
        return this.ia == null ? false : this.ia.hasItem(itemId, minimum, maximum);
    }

    @Override
    public Item[] getContents() {
        return this.ia == null ? new Item[0] : this.ia.getContents();
    }

    @Override
    public void setContents(Item[] contents) {
        if (this.ia != null) {
            this.ia.setContents(contents);
        }
    }

    @Override
    public int getContentsSize() {
        return this.ia == null ? 0 : this.ia.getContentsSize();
    }

    @Override
    public boolean insertItem(Item item) {
        return this.ia == null ? false : this.ia.insertItem(item);
    }

    @Override
    public boolean hasOContainer() {
        return this.ia == null ? false : this.ia.hasOContainer();
    }

    @Override
    public OContainer getOContainer() {
        return this.ia == null ? null : this.ia.getOContainer();
    }

    @Override
    public void setOContainer(OContainer oContainer) {
        if (this.ia != null) {
            this.ia.setOContainer(oContainer);
        }
    }

    @Override
    public boolean updateChangedSlots() {
        return this.ia == null ? false : this.ia.updateChangedSlots();
    }

    @Override
    public boolean updateSlot(int index) {
        return this.ia == null ? false : this.ia.updateSlot(index);
    }
}
