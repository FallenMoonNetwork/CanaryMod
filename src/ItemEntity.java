public class ItemEntity extends BaseEntity {

    /**
     * Constructor
     */
    public ItemEntity() {}

    /**
     * Constructor
     *
     * @param item
     */
    public ItemEntity(OEntityItem item) {
        super(item);
    }

    /**
     * Returns the entity we're wrapping.
     *
     * @return
     */
    @Override
    public OEntityItem getEntity() {
        return (OEntityItem) entity;
    }

    public void setAge(int age) {
        // SRG getEntity().field_70292_b = age;
        getEntity().a = age;
    }

    public int getAge() {
        // SRG return getEntity().field_70292_b;
        return getEntity().a;
    }

    public void setDelayBeforeCanPickUp(int time) {
        // SRG getEntity().field_70293_c = time;
        getEntity().b = time;
    }

    public int getDelayBeforeCanPickUp() {
        // SRG return getEntity().field_70293_c;
        return getEntity().b;
    }

    public Item getItem() {
        // SRG return new Item(getEntity().func_92059_d());
        return new Item(getEntity().d());
    }

    @Override
    public World getWorld() {
        return super.getWorld(); // Backward compatibility
    }
}
