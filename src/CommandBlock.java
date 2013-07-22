/**
 * Interface for the OTileEntityCommandBlock class
 *
 * @author gregthegeek
 *
 */
public class CommandBlock extends ComplexBlockBase<OTileEntityCommandBlock> implements MessageReceiver {

    public CommandBlock(OTileEntityCommandBlock base) {
        super(base);
    }

    @Override
    public void update() {
        // SRG tileEntity.func_70296_d();
        tileEntity.e();
    }

    /**
     * Sets this command block's command
     *
     * @param command the command to execute when this block is activated
     */
    public void setCommand(String command) {
        // SRG tileEntity.func_82352_b(command);
        tileEntity.a(command);
    }

    /**
     * Returns this command block's command
     *
     * @return
     */
    public String getCommand() {
        return tileEntity.getCommand();
    }

    /**
     * Run this command block's command
     */
    public void runCommand() {
        // SRG tileEntity.func_82351_a(getWorld().getWorld());
        tileEntity.a(getWorld().getWorld());
    }

    /**
     * Sets the text that appears before a command block's command in chat.
     * Default is '@'
     *
     * @param prefix
     */
    public void setPrefix(String prefix) {
        // SRG tileEntity.func_96104_c(prefix);
        tileEntity.b(prefix);
    }

    /**
     * Returns the text that appears before a command block's command in chat
     * Default is '@'
     *
     * @return
     */
    public String getPrefix() {
        // SRG return tileEntity.func_70005_c_();
        return tileEntity.c_();
    }

    @Override
    public String getName() {
        return getPrefix();
    }

    @Override
    public void notify(String message) {
        // SRG tileEntity.func_70006_a(OChatMessageComponent.func_111066_d(message));
        tileEntity.a(OChatMessageComponent.d(message));
    }
}
