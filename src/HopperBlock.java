/**
 * Wrapper class for hoppers.
 * @author Somners
 */
public class HopperBlock extends BaseContainerBlock<OTileEntityHopper> implements ComplexBlock, Hopper {

    private final OTileEntityHopper hopper;

    HopperBlock(OTileEntityHopper hopper) {
        this(null, hopper);
    }

    public HopperBlock(OContainer oContainer, OTileEntityHopper hopper) {
        super(oContainer, hopper, "Hopper");
        this.hopper = hopper;
    }

    /**
     * Gets the Inventory inputting to the hopper.
     * @return the Inventory or null if none.
     */
    public Inventory getInputContainer() {
        return this.getBaseContainerBlock(hopper.getInputInventory());
    }

    /**
     * Gets the Inventory the hopper outputs to.
     * @return the Inventory or null if none.
     */
    public Inventory getOutputContainer() {
        return this.getBaseContainerBlock(hopper.getOutputInventory());
    }

    /**
     * Check if this hopper is connected to any Container either input or output.
     * @return true - it is connected<br>false - it is not connected
     */
    public boolean isConnected() {
        return (this.isInputConnected() && this.isOutputConnected());
    }

    /**
     * Check if the block this hopper inputs from is a Container.
     * @return true - it is connected<br>false - it is not connected
     */
    public boolean isInputConnected() {
        return this.getInputContainer() != null;
    }

    /**
     * Check if the block this hopper outputs to is a Container.
     * @return true - it is connected<br>false - it is not connected
     */
    public boolean isOutputConnected() {
        return this.getOutputContainer() != null;
    }

    /**
     * Gets the Inventory from the inventory instance.
     * @param oiinventory OIInventory instance to get Inventory wrapper for.
     * @return The inventory or null if none.
     */
    private Inventory getBaseContainerBlock(OIInventory oiinventory){
        if (oiinventory instanceof OTileEntity) {
            return (Inventory) ((OTileEntity) oiinventory).getComplexBlock();
        } else if (oiinventory instanceof OInventoryLargeChest) {
            return new DoubleChest((OInventoryLargeChest) oiinventory);
        }
        else {
            return null;
        }
    }

    @Override
    public double getPosX() {
        return this.getX();
    }

    @Override
    public double getPosY() {
        return this.getY();
    }

    @Override
    public double getPosZ() {
        return this.getZ();
    }

    @Override
    public int getTranferCooldown() {
        return hopper.c;
    }

    @Override
    public void setTransferCooldown(int cooldown) {
        hopper.c = cooldown;
    }
}
