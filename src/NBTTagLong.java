/**
 * Interface for the ONBTTagLong class
 *
 * @author gregthegeek
 *
 */
public class NBTTagLong extends NBTBase {
    /**
     * Wraps an ONBTTagLong
     *
     * @param tagBase the tag to wrap
     */
    public NBTTagLong(ONBTTagLong tagBase) {
        super(tagBase);
    }

    /**
     * Creates a new NBTTagLong
     *
     * @param name the name of the tag
     */
    public NBTTagLong(String name) {
        this(new ONBTTagLong(name));
    }

    /**
     * Creates a new NBTTagLong
     *
     * @param name the name of the tag
     * @param value the value of the tag
     */
    public NBTTagLong(String name, long value) {
        this(new ONBTTagLong(name, value));
    }

    @Override
    public ONBTTagLong getBaseTag() {
        return (ONBTTagLong) super.getBaseTag();
    }

    /**
     * Returns the value of this tag
     *
     * @return
     */
    public long getValue() {
        return this.getBaseTag().a;
    }

    /**
     * Sets the value of this tag
     *
     * @param value the new value
     */
    public void setValue(long value) {
        this.getBaseTag().a = value;
    }

    @Override
    public String toString() {
        return String.format("NBTTag[type=%s, name=%s, value=%d]", getTagName(getType()), getName(), getValue());
    }
}
