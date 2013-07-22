/**
 * Class used for interfacing with tileEntity blocks.
 * @author 14mRh4X0r
 */
public class NoteBlock extends ComplexBlockBase<OTileEntityNote> {

    public NoteBlock(OTileEntityNote note) {
        super(note);
    }

    /**
     * Returns the current pitch of the tileEntity block.
     * @return current pitch
     */
    public byte getNote() {
        // SRG return tileEntity.field_70416_a;
        return tileEntity.a;
    }

    /**
     * Sets the pitch of the tileEntity block to a given value.
     * @param tileEntity The new pitch
     */
    public void setNote(byte tileEntity) {
        // SRG this.tileEntity.field_70416_a = tileEntity;
        this.tileEntity.a = tileEntity;
    }
}
