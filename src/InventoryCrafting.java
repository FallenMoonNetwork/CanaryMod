import java.util.Arrays;


public class InventoryCrafting<C extends Container<OItemStack>> extends ItemArray<C> {
    public OInventoryCraftResult result;
    
    private final int resultStartIndex;
    
    public InventoryCrafting(OContainer oContainer, C container, OIInventory result) {
        this(oContainer, container, result, 0);
    }
    
    public InventoryCrafting(OContainer oContainer, C container, OIInventory result, int resultStartIndex) {
        super(oContainer, container);
        this.result = (OInventoryCraftResult)result;
        this.resultStartIndex = resultStartIndex;
    }
    
    public OItemStack[] getCraftMatrixContents() {
        return container.getContents();
    }
    
    public OItemStack[] getResultContents() {
        return result.getContents();
    }
    
    public int getCraftMatrixSize() {
        return container.getContentsSize();
    }
    
    public int getResultSize() {
        return result.getContentsSize();
    }
    
    @Override
    public Item getItemFromSlot(int slot) {
        OSlot oslot = getOContainer().getSlot(slot);
        if(oslot != null) {
            OItemStack oitem = oslot.c();
            return oitem == null ? null : new Item(oitem);
        }
        
        return null;
    }
    
    @Override
    public int getEmptySlot() {
        int size = getContentsSize();

        for (int i = 0; size > i; i++) {
            OSlot oslot = getOContainer().getSlot(i);
            if (oslot != null && oslot.c() != null) {
                continue;
            }
            return i;
        }

        return -1;
    }

    @Override
    public void removeItem(int slot) {
        setSlot((OItemStack)null, slot);
    }

    @Override
    public void setSlot(Item item, int slot) {
        setSlot(item == null ? null : item.getBaseItem(), slot);
    }
    
    @Override
    public void setSlot(int itemId, int amount, int damage, int slot) {
        OItemStack item = new OItemStack(itemId, (amount > 64 ? (amount == 255 ? -1 : 64) : amount), damage);
        setSlot(item, slot);
    }
    
    private void setSlot(OItemStack item, int slot) {
        //since OSlot "setSlot" uses update methods, need to do it the hard way.
        int size = getResultSize();
        int index = slot;
        if(slot >= resultStartIndex && slot < resultStartIndex + size) {
            index = slot - resultStartIndex;
            result.setContentsAt(index, item);
        }
        
        if(resultStartIndex == 0) {
            index = slot - resultStartIndex - size;
        }
        
        size = getCraftMatrixSize();

        if (index >= 0 && index < size) {
            container.setContentsAt(index, item);
        }
    }
    
    @Override
    public void clearContents() {
        int size = getContentsSize();

        for (int i = 0; size > i; i++) {
            removeItem(i);
        }
    }
    
    @Override
    public int getContentsSize() {
        return getCraftMatrixSize() + getResultSize();
    }
    
    @Override
    public void update() {
        getOContainer().updateChangedSlots();
    }
    
    @Override
    public void setOContainer(OContainer oContainer) {
        if(oContainer == null)
            return;
        super.setOContainer(oContainer);
    }
    
    public int getLocalSlotIndex(int slot) {
        if(slot == SlotType.OUTSIDE)
            return slot;
        
        int size = getResultSize();
        if(slot >= resultStartIndex && slot < resultStartIndex + size) {
            return slot - resultStartIndex;
        }
        
        if(resultStartIndex == 0) {
            return slot - resultStartIndex - size;
        }
        
        return slot;
    }
    
    @Override
    public String getName() {
        return container.getName();
    }
    
    @Override
    public void setName(String value) {
        container.setName(value);
    }

    @Override
    public String toString() {
        return String.format("CraftMatrix[size=%d, contents=%s]", container.getContentsSize(), Arrays.toString(getCraftMatrixContents()))
                + String.format(" Result[size=%d, contents=%s]", getResultSize(), Arrays.toString(getResultContents()))
                ;
    }
}
