import java.util.Arrays;

/**
 * Interface for the ONBTTagList class
 *
 * @author gregthegeek
 *
 */
public class NBTTagList extends NBTBase {
    /**
     * Wraps an ONBTTagList
     *
     * @param baseTag the tag to wrap
     */
    public NBTTagList(ONBTTagList baseTag) {
        super(baseTag);
    }

    /**
     * Creates a new NBTTagList
     */
    public NBTTagList() {
        this(new ONBTTagList());
    }

    /**
     * Creates a new NBTTagList
     *
     * @param name the name of the tag
     */
    public NBTTagList(String name) {
        this(new ONBTTagList(name));
    }

    @Override
    public ONBTTagList getBaseTag() {
        return (ONBTTagList) super.getBaseTag();
    }

    /**
     * Returns the size of this list
     *
     * @return
     */
    public int size() {
        return this.getBaseTag().c();
    }

    /**
     * Adds a tag to this list
     *
     * @param tag
     */
    public void add(NBTBase tag) {
        this.getBaseTag().a(tag.getBaseTag());
    }

    /**
     * Gets the tag at the specified index
     *
     * @param index
     * @return
     */
    public NBTBase get(int index) {
        return NBTBase.wrap(getBaseTag().b(index));
    }

    /**
     * Returns an array of the values in this NBTTagList.
     *
     * @return
     */
    public NBTBase[] getValues() {
        NBTBase[] vals = new NBTBase[size()];
        for(int i=0; i<vals.length; i++) {
            vals[i] = get(i);
        }
        return vals;
    }

    @Override
    public String toString() {
        return String.format("NBTTag[type=%s, name=%s, value=%s]", getTagName(getType()), getName(), Arrays.toString(getValues()));
    }

    /**
     * Removes the NBTTag at the specified index.
     *
     * @param index The index of the tag to remove.
     */
    public void remove(int index) {
        getBaseTag().a.remove(index);
    }
}
