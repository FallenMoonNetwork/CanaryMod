/**
 * Class for the passing of arguments in the onAnvilUse hook.
 *
 * @author gregthegeek
 *
 */
public class HookParametersAnvilUse extends HookParameters {
    public Item slotOne = null;
    public Item slotTwo = null;
    public Item result = null;
    public String toolName = null;
    public int xpLevel = 0;
    private final Block block;

    public HookParametersAnvilUse(Anvil anvil, Block block) {
        this.slotOne = anvil.getItemFromSlot(0);
        this.slotTwo = anvil.getItemFromSlot(1);
        this.result = anvil.getResult();
        this.toolName = anvil.getToolName();
        this.xpLevel = anvil.getXPCost();
        this.block = block;
    }

    public HookParametersAnvilUse(Item slotOne, Item slotTwo, Item result, String toolName, int xpLevel, Block block) {
        this.slotOne = slotOne;
        this.slotTwo = slotTwo;
        this.result = result;
        this.toolName = toolName;
        this.xpLevel = xpLevel;
        this.block = block;
    }

    public Block getBlock() {
        return this.block;
    }
}
