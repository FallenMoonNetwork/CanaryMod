public class OContainerFurnace extends OContainer
{
    private OTileEntityFurnace a;
    private int f = 0;
    private int g = 0;
    private int h = 0;
    
    public OContainerFurnace(OInventoryPlayer paramOInventoryPlayer, OTileEntityFurnace paramOTileEntityFurnace)
    {
        this.a = paramOTileEntityFurnace;
        
        a(new OSlot(paramOTileEntityFurnace, 0, 56, 17));
        a(new OSlot(paramOTileEntityFurnace, 1, 56, 53));
        a(new OSlotFurnace(paramOInventoryPlayer.d, paramOTileEntityFurnace, 2, 116, 35));
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                a(new OSlot(paramOInventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        for (int i = 0; i < 9; i++)
            a(new OSlot(paramOInventoryPlayer, i, 8 + i * 18, 142));
    }
    
    public void a(OICrafting paramOICrafting)
    {
        super.a(paramOICrafting);
        paramOICrafting.a(this, 0, this.a.c);
        paramOICrafting.a(this, 1, this.a.a);
        paramOICrafting.a(this, 2, this.a.b);
    }
    
    public void b()
    {
        super.b();
        
        for (int i = 0; i < this.e.size(); i++) {
            OICrafting localOICrafting = (OICrafting)this.e.get(i);
            if (this.f != this.a.c) {
                localOICrafting.a(this, 0, this.a.c);
            }
            if (this.g != this.a.a) {
                localOICrafting.a(this, 1, this.a.a);
            }
            if (this.h != this.a.b) {
                localOICrafting.a(this, 2, this.a.b);
            }
        }
        
        this.f = this.a.c;
        this.g = this.a.a;
        this.h = this.a.b;
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
            
            if (paramInt == 2) {
                if (!a(localOItemStack2, 3, 39, true)) {
                    return null;
                }
                localOSlot.a(localOItemStack2, localOItemStack1);
            } else if ((paramInt == 1) || (paramInt == 0)) {
                if (!a(localOItemStack2, 3, 39, false))
                    return null;
            }
            else if (OFurnaceRecipes.a().b(localOItemStack2.b().cj) != null) {
                if (!a(localOItemStack2, 0, 1, false))
                    return null;
            }
            else if (OTileEntityFurnace.b(localOItemStack2)) {
                if (!a(localOItemStack2, 1, 2, false))
                    return null;
            }
            else if ((paramInt >= 3) && (paramInt < 30)) {
                if (!a(localOItemStack2, 30, 39, false))
                    return null;
            }
            else if ((paramInt >= 30) && (paramInt < 39) && 
                (!a(localOItemStack2, 3, 30, false))) {
                return null;
            }
            
            if (localOItemStack2.a == 0)
                localOSlot.c(null);
            else {
                localOSlot.e();
            }
            if (localOItemStack2.a == localOItemStack1.a) {
                return null;
            }
            localOSlot.a(paramOEntityPlayer, localOItemStack2);
        }
        
        return localOItemStack1;
    }
    
    // CanaryMod
    public OTileEntityFurnace getTileEntity() {
        return this.a;
    }
    
    @Override
    public Furnace getInventory() {
        if(super.getInventory() instanceof Furnace) {
            Inventory inventory = super.getInventory();
            if(!inventory.hasOContainer())
                inventory.setOContainer(this);
            return (Furnace)super.getInventory();
        }
        
        Furnace inv = new Furnace(this, this.a);
        setInventory(inv);
        return inv;
    }
}