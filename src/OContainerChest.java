public class OContainerChest extends OContainer {

    private OIInventory a;
    private int f;
    // CanaryMod: silenced - Used to determine wether the chest should be opened or closed stealthily
    private boolean silenced;

    public OContainerChest(OIInventory oiinventory, OIInventory oiinventory1, boolean flag) {
        this.a = oiinventory1;
        this.f = oiinventory1.k_() / 9;
        this.silenced = flag;
        if (!this.silenced) {
            oiinventory1.l_();
        }

        int i = (this.f - 4) * 18;

        int j;
        int k;

        for (j = 0; j < this.f; ++j) {
            for (k = 0; k < 9; ++k) {
                this.a(new OSlot(oiinventory1, k + j * 9, 8 + k * 18, 18 + j * 18));
            }
        }

        for (j = 0; j < 3; ++j) {
            for (k = 0; k < 9; ++k) {
                this.a(new OSlot(oiinventory, k + j * 9 + 9, 8 + k * 18, 103 + j * 18 + i));
            }
        }

        for (j = 0; j < 9; ++j) {
            this.a(new OSlot(oiinventory, j, 8 + j * 18, 161 + i));
        }
    }

    public boolean a(OEntityPlayer oentityplayer) {
        return this.a.a_(oentityplayer);
    }

    public OItemStack b(OEntityPlayer oentityplayer, int i) {
        OItemStack oitemstack = null;
        OSlot oslot = (OSlot) this.c.get(i);

        if (oslot != null && oslot.d()) {
            OItemStack oitemstack1 = oslot.c();

            oitemstack = oitemstack1.l();
            if (i < this.f * 9) {
                if (!this.a(oitemstack1, this.f * 9, this.c.size(), true)) {
                    return null;
                }
            } else if (!this.a(oitemstack1, 0, this.f * 9, false)) {
                return null;
            }

            if (oitemstack1.a == 0) {
                oslot.c((OItemStack) null);
            } else {
                oslot.e();
            }
        }

        return oitemstack;
    }

    public void b(OEntityPlayer oentityplayer) {
        super.b(oentityplayer);
        if (!this.silenced) {
            this.a.f();
        }
    }

    public OIInventory d() {
        return this.a;
    }
    
    // CanaryMod
    @Override
    public Inventory getInventory() {
        
        OIInventory inv = this.a;
        
        if(inv instanceof OInventoryLargeChest)
        {
            if(super.getInventory() instanceof DoubleChest) {
                Inventory inventory = super.getInventory();
                if(!inventory.hasOContainer())
                    inventory.setOContainer(this);
                return super.getInventory();
            }
            
            DoubleChest chest = new DoubleChest(this, (OInventoryLargeChest)inv);
            setInventory(chest);
            return chest;
        }
        else if(inv instanceof OInventoryEnderChest)
        {
            if(super.getInventory() instanceof EnderChestInventory) {
                Inventory inventory = super.getInventory();
                if(!inventory.hasOContainer())
                    inventory.setOContainer(this);
                return super.getInventory();
            }
            
            EnderChestInventory chest = new EnderChestInventory(this, (OInventoryEnderChest)inv, null);
            setInventory(chest);
            return chest;
        }
        else if(inv instanceof OTileEntityChest)
        {
            if(super.getInventory() instanceof Chest) {
                Inventory inventory = super.getInventory();
                if(!inventory.hasOContainer())
                    inventory.setOContainer(this);
                return super.getInventory();
            }
            
            Chest chest = new Chest(this, (OTileEntityChest)inv);
            setInventory(chest);
            return chest;
        }
        else if(inv instanceof OEntityMinecart)
        {
            if(super.getInventory() instanceof StorageMinecart) {
                Inventory inventory = super.getInventory();
                if(!inventory.hasOContainer())
                    inventory.setOContainer(this);
                return super.getInventory();
            }
            
            StorageMinecart chest = new StorageMinecart(this, (OEntityMinecart)inv);
            setInventory(chest);
            return chest;
        }
        return null;
    }
}
