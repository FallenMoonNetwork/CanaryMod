/**
 * Class for the passing of arguments in the onSlotClick hook.
 *
 * @author m4411k4
 *
 */
public class HookParametersSlotClick {
    public Inventory openInventory;
    public int inventorySlot;
    private OContainer openContainer;
    private int mouseClick;
    private int grabOption;
    private Player player;

    public enum AllowClick {
        ALLOW,
        CANCEL,
        CANCEL_WITHOUT_CLIENT_UPDATE, //For when the plugin wants to handle the updating. Sorry for the long name :(
    };

    public AllowClick allowClick = AllowClick.ALLOW;

    public HookParametersSlotClick(OContainer openContainer, int inventorySlot, int mouseClick, int grabOption, OEntityPlayerMP player) {
        this.openContainer = openContainer;
        this.openInventory = openContainer.getInventory();
        this.inventorySlot = inventorySlot;
        this.mouseClick = mouseClick;
        this.grabOption = grabOption;
        this.player = new Player(player);
    }

    public Player getPlayer() {
        return player;
    }

    public Item getClickedItem() {
        if(openInventory == null || clickedOutside())
            return null;

        OSlot slot = openContainer.a(inventorySlot);
        OItemStack itemstack = slot.c();
        return itemstack == null ? null : new Item(itemstack);
    }

    public void setClickedItem(Item item) {
        if(openInventory == null || clickedOutside())
            return;

        OSlot slot = openContainer.a(inventorySlot);
        slot.c(item == null ? null : item.getBaseItem());
    }

    public Item getCursorItem() {
        if(openInventory == null)
            return null;

        return player.getInventoryCursorItem();
    }

    public void setCursorItem(Item item) {
        if(openInventory == null)
            return;

        player.setInventoryCursorItem(item);
    }

    /**
     * A more reliable slot type. If a new slot type is introduced, it will return that this needs to be updated.
     *
     * @return SlotType.Type
     */
    public SlotType.Type getSlotType() {
        return SlotType.getSlotType(openContainer, inventorySlot);
    }

    /**
     * Attempts to define slots. Minecraft updates can break the definitions if slots are added/removed/modified.
     *
     * @return
     */
    public SlotType.SpecificType getSpecificSlotType() {
        return SlotType.getSpecificSlotType(openContainer, inventorySlot);
    }

    public boolean isLeftClick() {
        return mouseClick == 0;
    }

    public boolean isRightClick() {
        return mouseClick == 1;
    }

    public int mouseClick() {
        return mouseClick;
    }

    public int getGrabOption() {
        return grabOption;
    }

    public boolean isHoldingShift() {
        return grabOption == 1;
    }

    public boolean isHoverSwapUsed() {
        return grabOption == 2;
    }

    public boolean isPickBlockKeyUsed() {
        return grabOption == 3;
    }

    public boolean clickedOutside() {
        return inventorySlot == SlotType.OUTSIDE;
    }
}
