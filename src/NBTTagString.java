/**
 * Interface for the ONBTTagString class
 *
 * @author gregthegeek
 *
 */
public class NBTTagString extends NBTBase {
    /**
     * Wraps an ONBTTagString
     *
     * @param baseTag the tag to wrap
     */
    public NBTTagString(ONBTTagString baseTag) {
        super(baseTag);
    }

    /**
     * Creates a new NBTTagString
     *
     * @param name the name of the tag
     */
    public NBTTagString(String name) {
        this(new ONBTTagString(name));
    }

    /**
     * Creates a new NBTTagString
     *
     * @param name the name of the tag
     * @param value the value of the tag
     */
    public NBTTagString(String name, String value) {
        this(new ONBTTagString(name, value));
    }

    @Override
    public ONBTTagString getBaseTag() {
        return (ONBTTagString) super.getBaseTag();
    }

    /**
     * Returns the value of this tag
     *
     * @return
     */
    public String getValue() {
        return this.getBaseTag().a;
    }

    /**
     * Sets the value of this tag
     *
     * @param value the new value
     */
    public void setValue(String value) {
        this.getBaseTag().a = value;
    }

        /**
         * Gets the plain string of this tag.
         * @return the plain string
         */
    @Override
        public String toPlainString(){
            return getValue();
        }

    @Override
    public String toString() {
        return String.format("NBTTag[type=%s, name=%s, value=%s]", getTagName(getType()), getName(), getValue());
    }
}
