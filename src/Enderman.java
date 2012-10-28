public class Enderman extends Mob {

    public Enderman(OEntityEnderman entity) {
        super(entity);
    }

    /**
     * Returns block in endermans's hand
     *
     * @return Block
     */
    public Block getBlockInHand() {
        return new Block(Block.Type.fromId(getEntity().p()), getEntity().p());
    }

    /**
     *  Sets the block the enderman is holding.
     *  @param blockID - the block ID that the enderman should hold.
     *  @param blockData - the data of the block the enderman should hold.
     * @return True if the enderman can hold the block or not.
     */
    public boolean setBlockInHand(int blockID, int blockData) {
        if (OEntityEnderman.canHoldItem(blockID)) {
            getEntity().a(blockID);
            getEntity().b(blockData);
            return true;
        }
        return false;
    }

    /**
     * Sets the block the enderman is holding.
     * @param blockID - the block ID that the enderman should hold.
     * @return True if the enderman can hold the block or not.
     */
    public boolean setBlockInHand(int blockID) {
        return this.setBlockInHand(blockID, 0);
    }

    /**
     *  Sets the block the enderman is holding.
     * @param block - the block the enderman should hold
     * @return True if the enderman can hold the block or not.
     */
    public boolean setBlockInHand(Block block) {
        return this.setBlockInHand(block.getType(), block.getData());
    }

    /**
     * @param blockID - the block ID to check if the enderman can hold
     * @return True if the enderman can hold the block or not.
     */
    public boolean getHoldable(int blockID) {
        return OEntityEnderman.getHoldable(blockID);
    }

    /**
     *  Allows or prevents the enderman from picking up a specific block
     *  @param blockID - the block to allow or prevent the enderman from picking up.
     * @param holdable  Whether the enderman should be able to hold the block or not.
     */
    public void setHoldable(int blockID, boolean holdable) {
        OEntityEnderman.setHoldable(blockID, holdable);
    }

    @Override
    public OEntityEnderman getEntity() {
        return (OEntityEnderman) entity;
    }

}
