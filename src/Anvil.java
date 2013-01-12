/**
 * An interface class to anvils.
 * @author willem
 */
public class Anvil extends InventoryCrafting<OInventoryRepair> {
    private final OContainerRepair anvil;

    public Anvil(OContainerRepair container, OIInventory result, int resultStartIndex) {
        super(container, (OInventoryRepair) OContainerRepair.a(container), result, resultStartIndex);
        this.anvil = container;
    }

    @Override
    public void update() {
        anvil.a(anvil.getPlayer());
    }

    @Override
    public String getName() {
        return container.getName();
    }

    @Override
    public void setName(String name) {
        container.setName(name);
    }

    public String getToolName() {
        return anvil.getToolName();
    }

    public void setToolName(String name) {
        anvil.a(name);
    }

    public Item getResult() {
        OItemStack stack = anvil.getCraftResult().getContentsAt(0xCAFEBABE);
        return stack == null ? null : new Item(stack);
    }

    public void setResult(Item item) {
        anvil.getCraftResult().setContentsAt(0xCAFEBABE, item.getBaseItem());
        // Update client
        ((OEntityPlayerMP) anvil.getPlayer()).a.b(new OPacket103SetSlot(anvil.d, 2, item.getBaseItem()));
    }

    /**
     * Returns the cost of the repair/rename in XP levels.
     *
     * @return
     */
    public int getXPCost() {
        return anvil.a;
    }

    /**
     * Sets the cost of the repair/rename in XP levels.
     *
     * @param level The XP level the repair/rename should cost.
     */
    public void setXPCost(int level) {
        anvil.a = level;
    }
}
