/**
 * Interface for the ONBTTagShort class
 *
 * @author gregthegeek
 *
 */
public class NBTTagShort extends NBTBase {
    /**
     * Wraps an ONBTTagShort
     *
     * @param baseTag the tag to wrap
     */
    public NBTTagShort(ONBTTagShort baseTag) {
        super(baseTag);
    }

    /**
     * Creates a new NBTTagShort
     *
     * @param name the name of the tag
     */
    public NBTTagShort(String name) {
        this(new ONBTTagShort(name));
    }

    /**
     * Creates a new NBTTagShort
     *
     * @param name the name of the tag
     * @param value the value of the tag
     */
    public NBTTagShort(String name, short value) {
        this(new ONBTTagShort(name, value));
    }

    @Override
    public ONBTTagShort getBaseTag() {
        return (ONBTTagShort) super.getBaseTag();
    }

    /**
     * Sets the value of this tag
     *
     * @return
     */
    public short getValue() {
        return this.getBaseTag().a;
    }

    /**
     * Sets the value of this tag
     *
     * @param value the new value
     */
    public void setValue(short value) {
        this.getBaseTag().a = value;
    }

    @Override
    public String toString() {
        return String.format("NBTTag[type=%s, name=%s, value=%d]", getTagName(getType()), getName(), getValue());
    }
}
