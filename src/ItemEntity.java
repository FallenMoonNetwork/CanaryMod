
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
        getEntity().a = age;
    }

    public int getAge() {
        return getEntity().a;
    }

    public void setDelayBeforeCanPickUp(int time) {
        getEntity().b = time;
    }

    public int getDelayBeforeCanPickUp() {
        return getEntity().b;
    }

    public Item getItem() {
        return new Item(getEntity().d());
    }

    @Override
    public World getWorld() {
        return getEntity().q.world;
    }

}
