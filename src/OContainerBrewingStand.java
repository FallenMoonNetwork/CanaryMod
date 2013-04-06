public class OContainerBrewingStand extends OContainer {

    private OTileEntityBrewingStand a;
    private final OSlot f;
    private int g = 0;

    public OContainerBrewingStand(OInventoryPlayer oinventoryplayer, OTileEntityBrewingStand otileentitybrewingstand) {
        this.a = otileentitybrewingstand;
        this.a((OSlot) (new OSlotBrewingStandPotion(oinventoryplayer.d, otileentitybrewingstand, 0, 56, 46)));
        this.a((OSlot) (new OSlotBrewingStandPotion(oinventoryplayer.d, otileentitybrewingstand, 1, 79, 53)));
        this.a((OSlot) (new OSlotBrewingStandPotion(oinventoryplayer.d, otileentitybrewingstand, 2, 102, 46)));
        this.f = this.a((OSlot) (new OSlotBrewingStandIngredient(this, otileentitybrewingstand, 3, 79, 17)));

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
        oicrafting.a(this, 0, this.a.x_());
    }

    @Override
    public void b() {
        super.b();

        for (int i = 0; i < this.e.size(); ++i) {
            OICrafting oicrafting = (OICrafting) this.e.get(i);

            if (this.g != this.a.x_()) {
                oicrafting.a(this, 0, this.a.x_());
            }
        }

        this.g = this.a.x_();
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
            if ((i < 0 || i > 2) && i != 3) {
                if (!this.f.d() && this.f.a(oitemstack1)) {
                    if (!this.a(oitemstack1, 3, 4, false)) {
                        return null;
                    }
                } else if (OSlotBrewingStandPotion.a_(oitemstack)) {
                    if (!this.a(oitemstack1, 0, 3, false)) {
                        return null;
                    }
                } else if (i >= 4 && i < 31) {
                    if (!this.a(oitemstack1, 31, 40, false)) {
                        return null;
                    }
                } else if (i >= 31 && i < 40) {
                    if (!this.a(oitemstack1, 4, 31, false)) {
                        return null;
                    }
                } else if (!this.a(oitemstack1, 4, 40, false)) {
                    return null;
                }
            } else {
                if (!this.a(oitemstack1, 4, 40, true)) {
                    return null;
                }

                oslot.a(oitemstack1, oitemstack);
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
    @Override
    public BrewingStand getInventory() {
        if (super.getInventory() instanceof BrewingStand) {
            Inventory inventory = super.getInventory();

            if (!inventory.hasOContainer()) {
                inventory.setOContainer(this);
            }

            return (BrewingStand) super.getInventory();
        }

        BrewingStand inv = new BrewingStand(this, this.a);
        setInventory(inv);
        return inv;
    }
}
