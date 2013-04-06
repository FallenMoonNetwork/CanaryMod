/**
 * Interface for the ONBTTagFloat class
 *
 * @author gregthegeek
 *
 */
public class NBTTagFloat extends NBTBase {
    /**
     * Creates a wrapper around an ONBTTagFloat
     *
     * @param baseTag the tag to wrap
     */
    public NBTTagFloat(ONBTTagFloat baseTag) {
        super(baseTag);
    }

    /**
     * Creates a new NBTTagFloat
     *
     * @param name the name of the tag
     */
    public NBTTagFloat(String name) {
        this(new ONBTTagFloat(name));
    }

    /**
     * Creates a new NBTTagFloat
     *
     * @param name the name of the tag
     * @param value the value of the tag
     */
    public NBTTagFloat(String name, float value) {
        this(new ONBTTagFloat(name, value));
    }

    @Override
    public ONBTTagFloat getBaseTag() {
        return (ONBTTagFloat) super.getBaseTag();
    }

    /**
     * Returns the value of this tag
     *
     * @return
     */
    public float getValue() {
        return this.getBaseTag().a;
    }

    /**
     * Sets the value of this tag
     *
     * @param value the new value
     */
    public void setValue(float value) {
        this.getBaseTag().a = value;
    }

    @Override
    public String toString() {
        return String.format("NBTTag[type=%s, name=%s, value=%f]", getTagName(getType()), getName(), getValue());
    }
}
