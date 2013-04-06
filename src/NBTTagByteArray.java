import java.util.Arrays;

/**
 * Interface for the ONBTTagByteArray class
 *
 * @author gregthegeek
 *
 */
public class NBTTagByteArray extends NBTBase {
    /**
     * Creates a wrapper around an ONBTTagByteArray
     *
     * @param baseTag
     */
    public NBTTagByteArray(ONBTTagByteArray baseTag) {
        super(baseTag);
    }

    /**
     * Creates a new NBTTagByteArray
     *
     * @param name the name of the tag
     */
    public NBTTagByteArray(String name) {
        this(new ONBTTagByteArray(name));
    }

    /**
     * Creates a new NBTTagByteArray
     *
     * @param name the name of the tag
     * @param value the value of the tag
     */
    public NBTTagByteArray(String name, byte[] value) {
        this(new ONBTTagByteArray(name, value));
    }

    /**
     * Returns the value of this tag
     *
     * @return
     */
    public byte[] getValue() {
        return this.getBaseTag().a;
    }

    /**
     * Sets the value of this tag
     *
     * @param value the new value
     */
    public void setValue(byte[] value) {
        this.getBaseTag().a = value;
    }

    @Override
    public ONBTTagByteArray getBaseTag() {
        return (ONBTTagByteArray) super.getBaseTag();
    }

    @Override
    public String toString() {
        return String.format("NBTTag[type=%s, name=%s, value=%s]", getTagName(getType()), getName(), Arrays.toString(getValue()));
    }
}
