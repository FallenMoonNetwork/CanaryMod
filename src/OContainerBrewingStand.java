public class OContainerBrewingStand extends OContainer
{
    private OTileEntityBrewingStand a;
    private final OSlot f;
    private int g = 0;
    
    public OContainerBrewingStand(OInventoryPlayer paramOInventoryPlayer, OTileEntityBrewingStand paramOTileEntityBrewingStand)
    {
        this.a = paramOTileEntityBrewingStand;
        
        a(new OSlotBrewingStandPotion(paramOInventoryPlayer.d, paramOTileEntityBrewingStand, 0, 56, 46));
        a(new OSlotBrewingStandPotion(paramOInventoryPlayer.d, paramOTileEntityBrewingStand, 1, 79, 53));
        a(new OSlotBrewingStandPotion(paramOInventoryPlayer.d, paramOTileEntityBrewingStand, 2, 102, 46));
        this.f = a(new OSlotBrewingStandIngredient(this, paramOTileEntityBrewingStand, 3, 79, 17));
        
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
        paramOICrafting.a(this, 0, this.a.x_());
    }
    
    public void b()
    {
        super.b();
        
        for (int i = 0; i < this.e.size(); i++) {
            OICrafting localOICrafting = (OICrafting)this.e.get(i);
            if (this.g != this.a.x_()) {
                localOICrafting.a(this, 0, this.a.x_());
            }
        }
        this.g = this.a.x_();
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
            
            if (((paramInt >= 0) && (paramInt <= 2)) || (paramInt == 3)) {
                if (!a(localOItemStack2, 4, 40, true)) {
                    return null;
                }
                localOSlot.a(localOItemStack2, localOItemStack1);
            } else if ((!this.f.d()) && (this.f.a(localOItemStack2))) {
                if (!a(localOItemStack2, 3, 4, false))
                    return null;
            }
            else if (OSlotBrewingStandPotion.a_(localOItemStack1)) {
                if (!a(localOItemStack2, 0, 3, false))
                    return null;
            }
            else if ((paramInt >= 4) && (paramInt < 31)) {
                if (!a(localOItemStack2, 31, 40, false))
                    return null;
            }
            else if ((paramInt >= 31) && (paramInt < 40)) {
                if (!a(localOItemStack2, 4, 31, false)) {
                    return null;
                }
            }
            else if (!a(localOItemStack2, 4, 40, false)) {
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
    @Override
    public BrewingStand getInventory() {
        if(super.getInventory() instanceof BrewingStand) {
            Inventory inventory = super.getInventory();
            if(!inventory.hasOContainer())
                inventory.setOContainer(this);
            return (BrewingStand)super.getInventory();
        }
        
        BrewingStand inv = new BrewingStand(this, this.a);
        setInventory(inv);
        return inv;
    }
}