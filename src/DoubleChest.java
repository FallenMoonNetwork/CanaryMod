public class DoubleChest extends ItemArray<OInventoryLargeChest> implements ComplexBlock, Inventory {
    private final Block block;
    private String      name = "Large Chest";

    public DoubleChest(OInventoryLargeChest chest) {
        this(null, chest);
    }
    public DoubleChest(OContainer oContainer, OInventoryLargeChest chest) {
        super(oContainer, chest);
        block = chest.getChestBlock();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String value) {
        name = value;
    }

    @Override
    public int getX() {
        return block.getX();
    }

    @Override
    public int getY() {
        return block.getY();
    }

    @Override
    public int getZ() {
        return block.getZ();
    }

    @Override
    public void update() {
        container.d();
    }

    @Override
    public Block getBlock() {
        return block;
    }

    @Override
    public World getWorld() {
        return block.getWorld();
    }

    @Override
    public NBTTagCompound getMetaTag() {
        return getWorld().getOnlyComplexBlock(block).getMetaTag();
    }

    @Override
    public void writeToTag(NBTTagCompound tag) {
        getWorld().getOnlyComplexBlock(block).writeToTag(tag);
    }

    @Override
    public void readFromTag(NBTTagCompound tag) {
        getWorld().getOnlyComplexBlock(block).readFromTag(tag);
    }
}
