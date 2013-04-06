import java.util.Arrays;

/**
 * Interface for the ONBTTagIntArray class
 *
 * @author gregthegeek
 *
 */
public class NBTTagIntArray extends NBTBase {
    /**
     * Wraps an ONBTTagIntArray
     *
     * @param baseTag the tag to wrap
     */
    public NBTTagIntArray(ONBTTagIntArray baseTag) {
        super(baseTag);
    }

    /**
     * Creates a new NBTTagIntArray
     *
     * @param name the name of the tag
     */
    public NBTTagIntArray(String name) {
        this(new ONBTTagIntArray(name));
    }

    /**
     * Creates a new NBTTagIntArray
     *
     * @param name the name of the tag
     * @param value the value of the tag
     */
    public NBTTagIntArray(String name, int[] value) {
        this(new ONBTTagIntArray(name, value));
    }

    @Override
    public ONBTTagIntArray getBaseTag() {
        return (ONBTTagIntArray) super.getBaseTag();
    }

    /**
     * Returns the value of this tag
     *
     * @return
     */
    public int[] getValue() {
        return this.getBaseTag().a;
    }

    /**
     * Sets the value of this tag
     *
     * @param value the new value
     */
    public void setValue(int[] value) {
        this.getBaseTag().a = value;
    }

    @Override
    public String toString() {
        return String.format("NBTTag[type=%s, name=%s, value=%s]", getTagName(getType()), getName(), Arrays.toString(getValue()));
    }
}
