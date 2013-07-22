/**
 * Generic superclass for Chests and Furnaces, as they are really similar.
 *
 * @author lightweight
 *
 * @param <C>
 *            The type of container we wish to wrap.
 */
public abstract class BaseContainerBlock<C extends OTileEntity & OIInventory & Container<OItemStack>> extends ItemArray<C> {
    private final String name;

    /**
     * Create a BaseContainerBlock to act as a wrapper for a given container.
     *
     * @param block
     *            The in-world block to 'envelop'.
     * @param reference
     *            Shows in toString().
     */
    public BaseContainerBlock(C block, String reference) {
        this(null, block, reference);
    }
    public BaseContainerBlock(OContainer oContainer, C block, String reference) {
        super(oContainer, block);
        this.name = reference;
    }

    public int getX() {
        // SRG return this.container.field_70329_l;
        return this.container.l;
    }

    public int getY() {
        // SRG return this.container.field_70330_m;
        return this.container.m;
    }

    public int getZ() {
        // SRG return this.container.field_70327_n;
        return this.container.n;
    }

    public World getWorld() {
        // SRG return this.container.field_70331_k.world;
        return this.container.k.world;
    }

    public Block getBlock() {
        return this.getWorld().getBlockAt(this.getX(), this.getY(), this.getZ());
    }

    @Override
    public void update() {
        // SRG this.container.func_70316_g();
        this.container.h();
        // SRG this.container.func_70296_d();
        this.container.e();
    }

    @Override
    public String getName() {
        return this.container.getName();
    }

    @Override
    public void setName(String value) {
        this.container.setName(value);
    }

    /**
     * Tests the given object to see if it equals this object
     *
     * @param obj
     *            the object to test
     * @return true if the two objects match
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        // Supress warning since we've already returned if class is wrong.
        @SuppressWarnings("unchecked")
        final BaseContainerBlock<C> other = (BaseContainerBlock<C>) obj;

        if (this.getX() != other.getX()) {
            return false;
        }
        if (this.getY() != other.getY()) {
            return false;
        }
        if (this.getZ() != other.getZ()) {
            return false;
        }
        if (!this.getWorld().equals(other.getWorld())) {
            return false;
        }
        return true;
    }

    /**
     * Returns a semi-unique hashcode for this object
     *
     * @return hashcode
     */
    @Override
    public int hashCode() {
        int hash = 7;

        hash = 97 * hash + this.getX();
        hash = 97 * hash + this.getY();
        hash = 97 * hash + this.getZ();
        hash = 97 * hash + this.getWorld().hashCode();
        return hash;
    }

    @Override
    public String toString() {
        return String.format(this.name + " [x=%d, y=%d, z=%d, world=%s, dimension=%d]", this.getX(), this.getY(), this.getZ(), this.getWorld().getName(), this.getWorld().getType().getId());
    }

    /**
     * Sets this block's data to that found in an NBTTagCompound
     *
     * @param tag the tag to read from
     */
    public void readFromTag(NBTTagCompound tag) {
        // SRG container.func_70307_a(tag.getBaseTag());
        container.a(tag.getBaseTag());
    }

    /**
     * Writes this block's data to an NBTagCompound
     *
     * @param tag the tag to write to
     */
    public void writeToTag(NBTTagCompound tag) {
        // SRG container.func_70310_b(tag.getBaseTag());
        container.b(tag.getBaseTag());
    }

    public NBTTagCompound getMetaTag() {
        return container.metadata;
    }
}
