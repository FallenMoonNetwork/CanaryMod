public abstract class ContainerMinecart extends Minecart implements Inventory {
    private final ItemArray<OEntityMinecartContainer> ia;

    public ContainerMinecart(OEntityMinecartContainer o) {
        super(o);
        this.ia = new ItemArray<OEntityMinecartContainer>(o) {

            @Override
            public void update() {
                container.k_();
            }

            @Override
            public void setName(String value) {
                container.setName(value);
            }

            @Override
            public String getName() {
                return container.getName();
            }
        };
    }

    @Override
    public void update() {
        ia.update();
    }

    @Override
    public int getContentsSize() {
        return ia.getContentsSize();
    }

    @Override
    public void addItem(Item item) {
        ia.addItem(item);
    }

    @Override
    public Item getItemFromSlot(int slot) {
        return ia.getItemFromSlot(slot);
    }

    @Override
    public Item getItemFromId(Item.Type type) {
        return ia.getItemFromId(type);
    }

    @Override
    public Item getItemFromId(int id) {
        return ia.getItemFromId(id);
    }

    @Override
    public Item getItemFromId(Item.Type type, int maxAmount) {
        return ia.getItemFromId(type, maxAmount);
    }

    @Override
    public Item getItemFromId(int id, int maxAmount) {
        return ia.getItemFromId(id, maxAmount);
    }

    @Override
    public Item getItemFromId(int id, int maxAmount, int dmg) {
        return ia.getItemFromId(id, maxAmount, dmg);
    }

    @Override
    public int getEmptySlot() {
        return ia.getEmptySlot();
    }

    @Override
    public void removeItem(int slot) {
        ia.removeItem(slot);
    }

    @Override
    public void setSlot(Item item, int slot) {
        ia.setSlot(item, slot);
    }

    @Override
    public void setSlot(Item.Type type, int amount, int slot) {
        ia.setSlot(type, amount, slot);
    }

    @Override
    public void setSlot(int itemId, int amount, int slot) {
        ia.setSlot(itemId, amount, slot);
    }

    @Override
    public void setSlot(int itemId, int amount, int damage, int slot) {
        ia.setSlot(itemId, amount, damage, slot);
    }

    @Override
    public void removeItem(Item item) {
        ia.removeItem(item);
    }

    @Override
    public void removeItem(Item.Type type, int amount) {
        ia.removeItem(type, amount);
    }

    @Override
    public void removeItem(int id, int amount) {
        ia.removeItem(id, amount);
    }

    @Override
    public void removeItemOverStacks(Item item) {
        ia.removeItemOverStacks(item);
    }

    @Override
    public boolean hasItem(Item item) {
        return ia.hasItem(item);
    }

    @Override
    public boolean hasItem(Item.Type type) {
        return ia.hasItem(type);
    }

    @Override
    public boolean hasItem(int itemId) {
        return ia.hasItem(itemId);
    }

    @Override
    public String getName() {
        return ia.getName();
    }

    @Override
    public boolean hasItem(Item.Type type, int minimum) {
        return ia.hasItem(type, minimum);
    }

    @Override
    public void setName(String value) {
        ia.setName(value);
    }

    @Override
    public boolean hasItem(int itemId, int minimum) {
        return ia.hasItem(itemId, minimum);
    }

    @Override
    public boolean hasItem(int itemId, int minimum, int maximum) {
        return ia.hasItem(itemId, minimum, maximum);
    }

    @Override
    public Item[] getContents() {
        return ia.getContents();
    }

    @Override
    public void setContents(Item[] contents) {
        ia.setContents(contents);
    }

    @Override
    public void clearContents() {
        ia.clearContents();
    }

    @Override
    public boolean insertItem(Item item) {
        return ia.insertItem(item);
    }

    @Override
    public boolean hasOContainer() {
        return ia.hasOContainer();
    }

    @Override
    public OContainer getOContainer() {
        return ia.getOContainer();
    }

    @Override
    public void setOContainer(OContainer oContainer) {
        ia.setOContainer(oContainer);
    }

    @Override
    public boolean updateChangedSlots() {
        return ia.updateChangedSlots();
    }

    @Override
    public boolean updateSlot(int index) {
        return ia.updateSlot(index);
    }

    @Override
    public OEntityMinecartContainer getEntity() {
        return (OEntityMinecartContainer) super.getEntity();
    }
}
