/**
 * JukeBox/RecordPlayer interface
 *
 * @author Drathus42
 */
public class JukeBox extends ComplexBlockBase<OTileEntityRecordPlayer> {

    public JukeBox(OTileEntityRecordPlayer jukebox) {
        super(jukebox);
    }

    /**
     * Check if a record is present in the JukeBox
     * @return true if record present, false if no record present
     */
    public boolean hasRecord() {
        // SRG return tileEntity.func_96097_a() != null;
        return tileEntity.a() != null;
    }

    /**
     * Get the ID of the record in the JukeBox (if any)
     * @return Item ID number of record or -1 if no record present
     */
    public int getDiscID() {
        return hasRecord() ? getDisc().getItemId() : -1;
    }

    /**
     * Get the item currently in the tileEntity (if any)
     * @return The record <tt>Item</tt> or null if no record present
     */
    public Item getDisc() {
        // SRG return hasRecord() ? new Item(tileEntity.func_96097_a()) : null;
        return hasRecord() ? new Item(tileEntity.a()) : null;
    }

    /**
     * Sets the item currently in the tileEntity.
     * @param item The {@link Item} to be in the tileEntity, or <tt>null</yy> to
     * remove any item.
     */
    public void setDisc(Item item) {
        // SRG tileEntity.func_96098_a(item == null ? null : item.getBaseItem());
        tileEntity.a(item == null ? null : item.getBaseItem());
    }
}
