import java.util.ArrayList;
import java.util.Collection;

/**
 * Interface for the ONBTTagCompound class
 *
 * @author gregthegeek
 *
 */
public class NBTTagCompound extends NBTBase {

    /**
     * Creates a wrapper around an ONBTTagCompound
     *
     * @param baseTag
     */
    public NBTTagCompound(ONBTTagCompound baseTag) {
        super(baseTag);
    }

    /**
     * Creates a new NBTTagCompound
     */
    public NBTTagCompound() {
        this(new ONBTTagCompound());
    }

    /**
     * Creates a new NBTTagCompound
     *
     * @param name the name of the tag
     */
    public NBTTagCompound(String name) {
        this(new ONBTTagCompound(name));
    }

    @Override
    public ONBTTagCompound getBaseTag() {
        return (ONBTTagCompound) super.getBaseTag();
    }

    /**
     * Returns the tags in this CompoundTag.
     *
     * @return
     */
    public Collection<NBTBase> getValues() {
        // SRG Collection<ONBTBase> base = this.getBaseTag().func_74758_c();
        Collection<ONBTBase> base = this.getBaseTag().c();
        Collection<NBTBase> rt = new ArrayList<NBTBase>(base.size());
        for(ONBTBase o : base) {
            rt.add(NBTBase.wrap(o));
        }
        return rt;
    }

    /**
     * Returns a tag within this CompoundTag
     *
     * @param name the name of the tag to return
     * @return
     */
    public NBTBase getValue(String name) {
        // SRG return new NBTBase(this.getBaseTag().func_74781_a(name));
        return new NBTBase(this.getBaseTag().a(name));
    }

    /**
     * Returns whether or not this CompoundTag has a tag in it
     *
     * @param name the name of the tag to check
     * @return
     */
    public boolean hasTag(String name) {
        // SRG return this.getBaseTag().func_74764_b(name);
        return this.getBaseTag().b(name);
    }

    /**
     * Adds an NBTTag
     *
     * @param name the name of the tag
     * @param tag the tag to add
     */
    public void add(String name, NBTBase tag) {
        // SRG this.getBaseTag().func_74782_a(name, tag.getBaseTag());
        this.getBaseTag().a(name, tag.getBaseTag());
    }

    /**
     * Adds a new NBTTagByte
     *
     * @param name the name of the NBTByteTag
     * @param b the value of the NBTByteTag
     */
    public void add(String name, byte b) {
        // SRG this.getBaseTag().func_74774_a(name, b);
        this.getBaseTag().a(name, b);
    }

    /**
     * Adds a new NBTTagShort
     *
     * @param name the name of the NBTTagShort
     * @param s the value of the NBTTagShort
     */
    public void add(String name, short s) {
        // SRG this.getBaseTag().func_74777_a(name, s);
        this.getBaseTag().a(name, s);
    }

    /**
     * Adds a new NBTTagInt
     *
     * @param name the name of the new NBTTagInt
     * @param i the value of the NBTTagInt
     */
    public void add(String name, int i) {
        // SRG this.getBaseTag().func_74768_a(name, i);
        this.getBaseTag().a(name, i);
    }

    /**
     * Adds a new NBTTagLong
     *
     * @param name the name of the new NBTTagLong
     * @param l the value of the new NBTTagLong
     */
    public void add(String name, long l) {
        // SRG this.getBaseTag().func_74772_a(name, l);
        this.getBaseTag().a(name, l);
    }

    /**
     * Adds a new NBTTagFloat
     *
     * @param name the name of the new NBTTagFloat
     * @param f the value of the new NBTTagFloat
     */
    public void add(String name, float f) {
        // SRG this.getBaseTag().func_74776_a(name, f);
        this.getBaseTag().a(name, f);
    }

    /**
     * Adds a new NBTTagDouble
     *
     * @param name the name of the new NBTTagDouble
     * @param d the value of the new NBTTagDouble
     */
    public void add(String name, double d) {
        // SRG this.getBaseTag().func_74780_a(name, d);
        this.getBaseTag().a(name, d);
    }

    /**
     * Adds a new NBTTagString
     *
     * @param name the name of the new NBTTagString
     * @param s the value of the new NBTTagString
     */
    public void add(String name, String s) {
        // SRG this.getBaseTag().func_74778_a(name, s);
        this.getBaseTag().a(name, s);
    }

    /**
     * Adds a new NBTTagByteArray
     *
     * @param name the name of the new NBTTagByteArray
     * @param b the value of the new NBTTagByteArray
     */
    public void add(String name, byte[] b) {
        // SRG this.getBaseTag().func_74773_a(name, b);
        this.getBaseTag().a(name, b);
    }

    /**
     * Adds a new NBTTagIntArray
     *
     * @param name the name of the new NBTTagIntArray
     * @param i the value of the new NBTTagIntArray
     */
    public void add(String name, int[] i) {
        // SRG this.getBaseTag().func_74783_a(name, i);
        this.getBaseTag().a(name, i);
    }

    /**
     * Adds a new NBTTagInt with a value of either 0 or 1
     *
     * @param name the name of the new NBTTagInt
     * @param b determines the value of the new NBTTagInt (true=1, false=0)
     */
    public void add(String name, boolean b) {
        // SRG this.getBaseTag().func_74757_a(name, b);
        this.getBaseTag().a(name, b);
    }

    /**
     * Returns the byte value of a tag
     *
     * @param tagName the name of the tag to get the value of
     * @return
     */
    public byte getByte(String tagName) {
        // SRG return this.getBaseTag().func_74771_c(tagName);
        return this.getBaseTag().c(tagName);
    }

    /**
     * Returns the short value of a tag
     *
     * @param tagName the name of the tag to get the value of
     * @return
     */
    public short getShort(String tagName) {
        // SRG return this.getBaseTag().func_74765_d(tagName);
        return this.getBaseTag().d(tagName);
    }

    /**
     * Returns the int value of a tag
     *
     * @param tagName the name of the tag to get the value of
     * @return
     */
    public int getInt(String tagName) {
        // SRG return this.getBaseTag().func_74762_e(tagName);
        return this.getBaseTag().e(tagName);
    }

    /**
     * Returns the long value of a tag
     *
     * @param tagName the name of the tag to get the value of
     * @return
     */
    public long getLong(String tagName) {
        // SRG return this.getBaseTag().func_74763_f(tagName);
        return this.getBaseTag().f(tagName);
    }

    /**
     * Returns the float value of a tag
     *
     * @param tagName the name of the tag to get the value of
     * @return
     */
    public float getFloat(String tagName) {
        // SRG return this.getBaseTag().func_74760_g(tagName);
        return this.getBaseTag().g(tagName);
    }

    /**
     * Returns the double value of a tag
     *
     * @param tagName the name of the tag to get the value of
     * @return
     */
    public double getDouble(String tagName) {
        // SRG return this.getBaseTag().func_74769_h(tagName);
        return this.getBaseTag().h(tagName);
    }

    /**
     * Returns the String value of a tag
     *
     * @param tagName the name of the tag to get the value of
     * @return
     */
    public String getString(String tagName) {
        // SRG return this.getBaseTag().func_74779_i(tagName);
        return this.getBaseTag().i(tagName);
    }

    /**
     * Returns the byte array value of a tag
     *
     * @param tagName the name of the tag to get the value of
     * @return
     */
    public byte[] getByteArray(String tagName) {
        // SRG return this.getBaseTag().func_74770_j(tagName);
        return this.getBaseTag().j(tagName);
    }

    /**
     * Returns the int array value of a tag
     *
     * @param tagName the name of the tag to get the value of
     * @return
     */
    public int[] getIntArray(String tagName) {
        // SRG return this.getBaseTag().func_74759_k(tagName);
        return this.getBaseTag().k(tagName);
    }

    /**
     * Returns an NBTTagCompound within this NBTTagCompound
     *
     * @param tagName the name of the tag to get the value of
     * @return
     */
    public NBTTagCompound getNBTTagCompound(String tagName) {
        // SRG return new NBTTagCompound(this.getBaseTag().func_74775_l(tagName));
        return new NBTTagCompound(this.getBaseTag().l(tagName));
    }

    public NBTTagList getNBTTagList(String tagName) {
        // SRG return new NBTTagList(this.getBaseTag().func_74761_m(tagName));
        return new NBTTagList(this.getBaseTag().m(tagName));
    }

    /**
     * Returns whether or not the tag specified is 0 (0=false, !0=true)
     *
     * @param tagName
     * @return
     */
    public boolean getBoolean(String tagName) {
        // SRG return this.getBaseTag().func_74767_n(tagName);
        return this.getBaseTag().n(tagName);
    }

    /**
     * Removes a tag from this NBTTagCompound
     *
     * @param name the name of the tag to remove
     */
    public void removeTag(String name) {
        // SRG this.getBaseTag().func_82580_o(name);
        this.getBaseTag().o(name);
    }

    /**
     * Returns whether or not this NBTTagCompound has anything in it
     *
     * @return
     */
    public boolean isEmpty() {
        // SRG return this.getBaseTag().func_82582_d();
        return this.getBaseTag().d();
    }

    @Override
    public String toString() {
        return String.format("NBTTag[type=%s, name=%s, value=%s]", getTagName(getType()), getName(), getValues());
    }
}
