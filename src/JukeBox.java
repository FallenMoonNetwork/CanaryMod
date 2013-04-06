/**
 * JukeBox/RecordPlayer interface
 *
 * @author Drathus42
 */
public class JukeBox implements ComplexBlock {
    OTileEntityRecordPlayer jukebox;

    public JukeBox(OTileEntityRecordPlayer jukebox) {
        this.jukebox = jukebox;
    }

    @Override
    public int getX() {
        return jukebox.l;
    }

    @Override
    public int getY() {
        return jukebox.m;
    }

    @Override
    public int getZ() {
        return jukebox.n;
    }

    @Override
    public void update() {
        jukebox.k_();
    }

    @Override
    public Block getBlock() {
        return getWorld().getBlockAt(getX(), getY(), getZ());
    }

    @Override
    public World getWorld() {
        return jukebox.k.world;
    }

    /**
     * Check if a record is present in the JukeBox
     * @return true if record present, false if no record present
     */
    public boolean hasRecord() {
        return (jukebox.p == 1 ? true : false);
    }

    /**
     * Get the ID of the record in the JukeBox (if any)
     * @return Item ID number of record or -1 if no record present
     */
    public int getDiscID() {
        return (hasRecord() ? jukebox.a().j() : -1);
    }

    /**
     * Get the item currently in the jukebox (if any)
     * @return The record <tt>Item</tt> or null if no record present
     */
    public Item getDisc() {
        return hasRecord() ? new Item(jukebox.a()) : null;
    }

    @Override
    public NBTTagCompound getMetaTag() {
        return jukebox.metadata;
    }

    @Override
    public void writeToTag(NBTTagCompound tag) {
        jukebox.b(tag.getBaseTag());
    }

    @Override
    public void readFromTag(NBTTagCompound tag) {
        jukebox.a(tag.getBaseTag());
    }
}
