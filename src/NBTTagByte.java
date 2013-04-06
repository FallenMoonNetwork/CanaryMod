/**
 * Interface for the ONBTTagByte class
 *
 * @author gregthegeek
 *
 */
public class NBTTagByte extends NBTBase {

    /**
     * Creates a wrapper around an ONBTTagByte
     *
     * @param baseTag
     */
    public NBTTagByte(ONBTTagByte baseTag) {
        super(baseTag);
    }

    /**
     * Creates a new NBTTagByte
     *
     * @param name the name of the new tag
     */
    public NBTTagByte(String name) {
        this(new ONBTTagByte(name));
    }

    /**
     * Creates a new NBTTagByte
     *
     * @param name the name of the new tag
     * @param value the value of the new tag
     */
    public NBTTagByte(String name, byte value) {
        this(new ONBTTagByte(name, value));
    }

    /**
     * Returns the byte value of this tag
     *
     * @return
     */
    public byte getValue() {
        return getBaseTag().a;
    }

    /**
     * Sets the byte value of this tag
     *
     * @param value
     */
    public void setValue(byte value) {
        getBaseTag().a = value;
    }

    @Override
    public ONBTTagByte getBaseTag() {
        return (ONBTTagByte) super.getBaseTag();
    }

    @Override
    public String toString() {
        return String.format("NBTTag[type=%s, name=%s, value=%d]", getTagName(getType()), getName(), getValue());
    }
}
