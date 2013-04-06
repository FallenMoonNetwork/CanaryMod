public class OContainerFurnace extends OContainer {

    private OTileEntityFurnace a;
    private int f = 0;
    private int g = 0;
    private int h = 0;

    public OContainerFurnace(OInventoryPlayer oinventoryplayer, OTileEntityFurnace otileentityfurnace) {
        this.a = otileentityfurnace;
        this.a(new OSlot(otileentityfurnace, 0, 56, 17));
        this.a(new OSlot(otileentityfurnace, 1, 56, 53));
        this.a((OSlot) (new OSlotFurnace(oinventoryplayer.d, otileentityfurnace, 2, 116, 35)));

        int i;

        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.a(new OSlot(oinventoryplayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (i = 0; i < 9; ++i) {
            this.a(new OSlot(oinventoryplayer, i, 8 + i * 18, 142));
        }
    }

    @Override
    public void a(OICrafting oicrafting) {
        super.a(oicrafting);
        oicrafting.a(this, 0, this.a.c);
        oicrafting.a(this, 1, this.a.a);
        oicrafting.a(this, 2, this.a.b);
    }

    @Override
    public void b() {
        super.b();

        for (int i = 0; i < this.e.size(); ++i) {
            OICrafting oicrafting = (OICrafting) this.e.get(i);

            if (this.f != this.a.c) {
                oicrafting.a(this, 0, this.a.c);
            }

            if (this.g != this.a.a) {
                oicrafting.a(this, 1, this.a.a);
            }

            if (this.h != this.a.b) {
                oicrafting.a(this, 2, this.a.b);
            }
        }

        this.f = this.a.c;
        this.g = this.a.a;
        this.h = this.a.b;
    }

    @Override
    public boolean a(OEntityPlayer oentityplayer) {
        return this.a.a(oentityplayer);
    }

    @Override
    public OItemStack b(OEntityPlayer oentityplayer, int i) {
        OItemStack oitemstack = null;
        OSlot oslot = (OSlot) this.c.get(i);

        if (oslot != null && oslot.d()) {
            OItemStack oitemstack1 = oslot.c();

            oitemstack = oitemstack1.m();
            if (i == 2) {
                if (!this.a(oitemstack1, 3, 39, true)) {
                    return null;
                }

                oslot.a(oitemstack1, oitemstack);
            } else if (i != 1 && i != 0) {
                if (OFurnaceRecipes.a().b(oitemstack1.b().cp) != null) {
                    if (!this.a(oitemstack1, 0, 1, false)) {
                        return null;
                    }
                } else if (OTileEntityFurnace.b(oitemstack1)) {
                    if (!this.a(oitemstack1, 1, 2, false)) {
                        return null;
                    }
                } else if (i >= 3 && i < 30) {
                    if (!this.a(oitemstack1, 30, 39, false)) {
                        return null;
                    }
                } else if (i >= 30 && i < 39 && !this.a(oitemstack1, 3, 30, false)) {
                    return null;
                }
            } else if (!this.a(oitemstack1, 3, 39, false)) {
                return null;
            }

            if (oitemstack1.a == 0) {
                oslot.c((OItemStack) null);
            } else {
                oslot.e();
            }

            if (oitemstack1.a == oitemstack.a) {
                return null;
            }

            oslot.a(oentityplayer, oitemstack1);
        }

        return oitemstack;
    }

    // CanaryMod
    public OTileEntityFurnace getTileEntity() {
        return this.a;
    }

    @Override
    public Furnace getInventory() {
        if (super.getInventory() instanceof Furnace) {
            Inventory inventory = super.getInventory();
            if (!inventory.hasOContainer()) {
                inventory.setOContainer(this);
            }
            return (Furnace) super.getInventory();
        }

        Furnace inv = new Furnace(this, this.a);
        setInventory(inv);
        return inv;
    }
}
