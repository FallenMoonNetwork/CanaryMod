/**
 * Interface for the ONBTTagInt class
 *
 * @author gregthegeek
 *
 */
public class NBTTagInt extends NBTBase {
    /**
     * Wraps an ONBTTagInt
     *
     * @param baseTag the tag to wrap
     */
    public NBTTagInt(ONBTTagInt baseTag) {
        super(baseTag);
    }

    /**
     * Creates a new NBTTagInt
     *
     * @param name the name of the tag
     */
    public NBTTagInt(String name) {
        this(new ONBTTagInt(name));
    }

    /**
     * Creates a new NBTTagInt
     *
     * @param name the name of the tag
     * @param value the value of the tag
     */
    public NBTTagInt(String name, int value) {
        this(new ONBTTagInt(name, value));
    }

    @Override
    public ONBTTagInt getBaseTag() {
        return (ONBTTagInt) super.getBaseTag();
    }

    /**
     * Returns the value of this tag
     *
     * @return
     */
    public int getValue() {
        return this.getBaseTag().a;
    }

    /**
     * Sets the value of this tag
     *
     * @param value the new value
     */
    public void setValue(int value) {
        this.getBaseTag().a = value;
    }

    @Override
    public String toString() {
        return String.format("NBTTag[type=%s, name=%s, value=%d]", getTagName(getType()), getName(), getValue());
    }
}
