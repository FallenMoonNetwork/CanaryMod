public class OContainerDispenser extends OContainer {

    private OTileEntityDispenser a;

    public OContainerDispenser(OIInventory oiinventory, OTileEntityDispenser otileentitydispenser) {
        this.a = otileentitydispenser;

        int i;
        int j;

        for (i = 0; i < 3; ++i) {
            for (j = 0; j < 3; ++j) {
                this.a(new OSlot(otileentitydispenser, j + i * 3, 62 + j * 18, 17 + i * 18));
            }
        }

        for (i = 0; i < 3; ++i) {
            for (j = 0; j < 9; ++j) {
                this.a(new OSlot(oiinventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (i = 0; i < 9; ++i) {
            this.a(new OSlot(oiinventory, i, 8 + i * 18, 142));
        }
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
            if (i < 9) {
                if (!this.a(oitemstack1, 9, 45, true)) {
                    return null;
                }
            } else if (!this.a(oitemstack1, 0, 9, false)) {
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
    public OTileEntityDispenser getTileEntity() {
        return this.a;
    }

    @Override
    public Dispenser getInventory() {
        if (super.getInventory() instanceof Dispenser) {
            Inventory inventory = super.getInventory();
            if (!inventory.hasOContainer()) {
                inventory.setOContainer(this);
            }
            return (Dispenser) super.getInventory();
        }

        Dispenser inv = new Dispenser(this, this.a);
        setInventory(inv);
        return inv;
    }
}
