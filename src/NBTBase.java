/**
 * Interface for the ONBTBase class
 *
 * @author gregthegeek
 *
 */
public class NBTBase {
    private final ONBTBase baseTag;

    /**
     * Creates a wrapper around an ONBTBase.
     *
     * @param baseTag the ONBTBase tag to wrap
     */
    public NBTBase(ONBTBase baseTag) {
        this.baseTag = baseTag;
    }

    /**
     * Returns the name of this tag.
     *
     * @return String
     */
    public String getName() {
        return getBaseTag().e();
    }

    /**
     * Sets the name of this tag.
     *
     * @param name the new name for the tag
     */
    public void setName(String name) {
        getBaseTag().p(name);
    }

    /**
     * Returns the ONBTBase this wraps.
     *
     * @return ONBTBase
     */
    public ONBTBase getBaseTag() {
        return this.baseTag;
    }

    @Override
    public boolean equals(Object object) {
        if(object instanceof NBTBase) {
            return getBaseTag().equals(((NBTBase) object).getBaseTag());
        } else if(object instanceof ONBTBase) {
            return getBaseTag().equals((ONBTBase) object);
        }
        return false;
    }

    /**
     * Returns the type of tag this is.
     *
     * @return byte
     */
    public byte getType() {
        return getBaseTag().a();
    }

    @Override
    public String toString() {
        return String.format("NBTTag[type=%s]", getTagName(getType()));
    }

    /**
     * Returns the name of the type of tag this is.
     *
     * @param type
     * @return String
     */
    public static String getTagName(byte type) {
        return ONBTBase.a(type);
    }

    /**
     * Wraps an NBTTag in its proper wrapper.
     *
     * @param tag The tag to wrap
     * @return NBTBase
     */
    public static NBTBase wrap(ONBTBase tag) {
        switch(tag.a()) {
        case 1:
            return new NBTTagByte((ONBTTagByte) tag);
        case 2:
            return new NBTTagShort((ONBTTagShort) tag);
        case 3:
            return new NBTTagInt((ONBTTagInt) tag);
        case 4:
            return new NBTTagLong((ONBTTagLong) tag);
        case 5:
            return new NBTTagFloat((ONBTTagFloat) tag);
        case 6:
            return new NBTTagDouble((ONBTTagDouble) tag);
        case 7:
            return new NBTTagByteArray((ONBTTagByteArray) tag);
        case 8:
            return new NBTTagString((ONBTTagString) tag);
        case 9:
            return new NBTTagList((ONBTTagList) tag);
        case 10:
            return new NBTTagCompound((ONBTTagCompound) tag);
        case 11:
            return new NBTTagIntArray((ONBTTagIntArray) tag);
        }
        return new NBTBase(tag);
    }

    public String toPlainString() {
        return getName();
    }
}
