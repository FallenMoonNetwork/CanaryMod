/**
 * Interface for the OTileEntitySkull class
 *
 * @author gregthegeek
 *
 */
public class Skull extends ComplexBlockBase<OTileEntitySkull> implements ComplexBlock {

    public Skull(OTileEntitySkull skullBase) {
        super(skullBase);
    }

    /**
     * Returns the name of the skin this skull shows, if any
     *
     * @return
     */
    public String getPlayerName() {
        // SRG return tileEntity.func_82120_c();
        return tileEntity.c();
    }

    /**
     * Sets the type of skull this is
     *
     * @param type the type of mob this belongs to (0-4)
     * @param playerName determines the skin of this head if it's a player head
     */
    public void setType(int type, String playerName) {
        // SRG tileEntity.func_82118_a(type, playerName);
        tileEntity.a(type, playerName);
    }

    /**
     * Sets the orientation of this skull (0-15?)
     *
     * @param rot
     */
    public void setOrientation(int rot) {
        // SRG tileEntity.func_82116_a(rot);
        tileEntity.a(rot);
    }

    public OTileEntitySkull getBaseSkull() {
        return tileEntity;
    }
}
