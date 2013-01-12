public class OContainerBeacon extends OContainer
{
    private OTileEntityBeacon a;
    private final OSlotBeacon f;
    private int g;
    private int h;
    private int i;
    
    public OContainerBeacon(OInventoryPlayer paramOInventoryPlayer, OTileEntityBeacon paramOTileEntityBeacon)
    {
        this.a = paramOTileEntityBeacon;
        
        a(this.f = new OSlotBeacon(this, paramOTileEntityBeacon, 0, 136, 110));
        
        int j = 36;
        int k = 137;
        
        for (int m = 0; m < 3; m++) {
            for (int n = 0; n < 9; n++) {
                a(new OSlot(paramOInventoryPlayer, n + m * 9 + 9, j + n * 18, k + m * 18));
            }
        }
        for (int m = 0; m < 9; m++) {
            a(new OSlot(paramOInventoryPlayer, m, j + m * 18, 58 + k));
        }
        
        this.g = paramOTileEntityBeacon.k();
        this.h = paramOTileEntityBeacon.i();
        this.i = paramOTileEntityBeacon.j();
    }
    
    public void a(OICrafting paramOICrafting)
    {
        super.a(paramOICrafting);
        
        paramOICrafting.a(this, 0, this.g);
        paramOICrafting.a(this, 1, this.h);
        paramOICrafting.a(this, 2, this.i);
    }
    
    public void b()
    {
        super.b();
    }
    
    public OTileEntityBeacon d()
    {
        return this.a;
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
            
            if (paramInt == 0) {
                if (!a(localOItemStack2, 1, 37, true)) {
                    return null;
                }
                localOSlot.a(localOItemStack2, localOItemStack1);
            } else if ((!this.f.d()) && (this.f.a(localOItemStack2)) && (localOItemStack2.a == 1)) {
                if (!a(localOItemStack2, 0, 1, false))
                    return null;
            }
            else if ((paramInt >= 1) && (paramInt < 28)) {
                if (!a(localOItemStack2, 28, 37, false))
                    return null;
            }
            else if ((paramInt >= 28) && (paramInt < 37)) {
                if (!a(localOItemStack2, 1, 28, false)) {
                    return null;
            }
            }
            else if (!a(localOItemStack2, 1, 37, false)) {
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
    public Beacon getInventory() {
        if(super.getInventory() instanceof Beacon)
            return (Beacon)super.getInventory();
        
        Beacon inv = new Beacon(this, this.a);
        setInventory(inv);
        return inv;
    }
}