/**
 * Interface for the class ONBTTagDouble
 *
 * @author gregthegeek
 *
 */
public class NBTTagDouble extends NBTBase {
    /**
     * Creates a wrapper around an ONBTTagDouble
     *
     * @param baseTag
     */
    public NBTTagDouble(ONBTTagDouble baseTag) {
        super(baseTag);
    }

    /**
     * Creates a new NBTTagDouble
     *
     * @param name the name of the new tag
     */
    public NBTTagDouble(String name) {
        this(new ONBTTagDouble(name));
    }

    /**
     * Creates a new NBTTagDouble
     *
     * @param name the name of the new tag
     * @param value the value of the new tag
     */
    public NBTTagDouble(String name, double value) {
        this(new ONBTTagDouble(name, value));
    }

    @Override
    public ONBTTagDouble getBaseTag() {
        return (ONBTTagDouble) super.getBaseTag();
    }

    /**
     * Returns the value of this NBTTagDouble
     *
     * @return
     */
    public double getValue() {
        return this.getBaseTag().a;
    }

    /**
     * Sets the value of this NBTTagDouble
     *
     * @param value the value to set
     */
    public void setValue(double value) {
        this.getBaseTag().a = value;
    }

    @Override
    public String toString() {
        return String.format("NBTTag[type=%s, name=%s, value=%f]", getTagName(getType()), getName(), getValue());
    }
}
