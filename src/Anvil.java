/**
 * An interface class to anvils.
 * @author Willem Mulder (14mRh4X0r)
 */
public class Anvil extends InventoryCrafting<OContainerRepairINNER1> {
    private final OContainerRepair anvil;

    public Anvil(OContainerRepair container, OIInventory result, int resultStartIndex) {
        // SRG super(container, (OContainerRepairINNER1) OContainerRepair.func_82851_a(container), result, resultStartIndex);
        super(container, (OContainerRepairINNER1) OContainerRepair.a(container), result, resultStartIndex);
        this.anvil = container;
    }

    @Override
    public void update() {
        // SRG anvil.func_75130_a((OIInventory) anvil.getPlayer());
        anvil.a((OIInventory) anvil.getPlayer());
    }

    public String getToolName() {
        return anvil.getToolName();
    }

    public void setToolName(String name) {
        // SRG anvil.func_82850_a(name);
        anvil.a(name);
    }

    public Item getResult() {
        OItemStack stack = anvil.getCraftResult().getContentsAt(0xCAFEBABE);
        return stack == null ? null : new Item(stack);
    }

    public void setResult(Item item) {
        anvil.getCraftResult().setContentsAt(0xCAFEBABE, item.getBaseItem());
        // Update client
        // SRG ((OEntityPlayerMP) anvil.getPlayer()).field_71135_a.func_72567_b((OPacket) new OPacket103SetSlot(anvil.field_75152_c, 2, item.getBaseItem()));
        ((OEntityPlayerMP) anvil.getPlayer()).a.b((OPacket) new OPacket103SetSlot(anvil.d, 2, item.getBaseItem()));
    }

    /**
     * Returns the cost of the repair/rename in XP levels.
     *
     * @return
     */
    public int getXPCost() {
        // SRG return anvil.field_82854_e;
        return anvil.a;
    }

    /**
     * Sets the cost of the repair/rename in XP levels.
     *
     * @param level The XP level the repair/rename should cost.
     */
    public void setXPCost(int level) {
        // SRG anvil.field_82854_e = level;
        anvil.a = level;
    }
}
