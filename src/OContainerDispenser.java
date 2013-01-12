public class OContainerDispenser extends OContainer
{
    private OTileEntityDispenser a;
    
    public OContainerDispenser(OIInventory paramOIInventory, OTileEntityDispenser paramOTileEntityDispenser)
    {
        this.a = paramOTileEntityDispenser;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                a(new OSlot(paramOTileEntityDispenser, j + i * 3, 62 + j * 18, 17 + i * 18));
            }
        }
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                a(new OSlot(paramOIInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        for (int i = 0; i < 9; i++)
            a(new OSlot(paramOIInventory, i, 8 + i * 18, 142));
    }
    
    public boolean a(OEntityPlayer paramOEntityPlayer)
    {
        return this.a.a_(paramOEntityPlayer);
    }
    
    public OItemStack b(OEntityPlayer paramOEntityPlayer, int paramInt)
    {
        OItemStack localOItemStack1 = null;
        OSlot localOSlot = (OSlot)this.c.get(paramInt);
        if ((localOSlot != null) && (localOSlot.d())) {
            OItemStack localOItemStack2 = localOSlot.c();
            localOItemStack1 = localOItemStack2.l();
            
            if (paramInt < 9) {
                if (!a(localOItemStack2, 9, 45, true)) {
                    return null;
                }
            }
            else if (!a(localOItemStack2, 0, 9, false)) {
                return null;
            }
            
            if (localOItemStack2.a == 0)
                localOSlot.c(null);
            else {
                localOSlot.e();
            }
            if (localOItemStack2.a == localOItemStack1.a)
            {
                return null;
            }
            localOSlot.a(paramOEntityPlayer, localOItemStack2);
        }
        
        return localOItemStack1;
    }
    
    // CanaryMod
    public OTileEntityDispenser getTileEntity() {
        return this.a;
    }
    
    @Override
    public Dispenser getInventory() {
        if(super.getInventory() instanceof Dispenser) {
            Inventory inventory = super.getInventory();
            if(!inventory.hasOContainer())
                inventory.setOContainer(this);
            return (Dispenser)super.getInventory();
        }
        
        Dispenser inv = new Dispenser(this, this.a);
        setInventory(inv);
        return inv;
    }
}