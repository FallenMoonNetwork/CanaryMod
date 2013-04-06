public class OContainerWorkbench extends OContainer {

    public OInventoryCrafting a = new OInventoryCrafting(this, 3, 3);
    public OIInventory f = new OInventoryCraftResult();
    private OWorld g;
    private int h;
    private int i;
    private int j;

    public OContainerWorkbench(OInventoryPlayer oinventoryplayer, OWorld oworld, int i, int j, int k) {
        this.g = oworld;
        this.h = i;
        this.i = j;
        this.j = k;
        this.a((OSlot) (new OSlotCrafting(oinventoryplayer.d, this.a, this.f, 0, 124, 35)));

        int l;
        int i1;

        for (l = 0; l < 3; ++l) {
            for (i1 = 0; i1 < 3; ++i1) {
                this.a(new OSlot(this.a, i1 + l * 3, 30 + i1 * 18, 17 + l * 18));
            }
        }

        for (l = 0; l < 3; ++l) {
            for (i1 = 0; i1 < 9; ++i1) {
                this.a(new OSlot(oinventoryplayer, i1 + l * 9 + 9, 8 + i1 * 18, 84 + l * 18));
            }
        }

        for (l = 0; l < 9; ++l) {
            this.a(new OSlot(oinventoryplayer, l, 8 + l * 18, 142));
        }

        this.a((OIInventory) this.a);
    }

    public void a(OIInventory oiinventory) {
        // Canarymod - send custom recipes result to client
        OItemStack craftresult = OCraftingManager.a().a(this.a, this.g);
        this.f.a(0, craftresult);

        if (this.e.isEmpty()) {
            return;
        } //
        OEntityPlayerMP player = (OEntityPlayerMP) this.e.get(0);

        player.a.b(new OPacket103SetSlot(this.d, 0, craftresult));
    }

    public void b(OEntityPlayer oentityplayer) {
        super.b(oentityplayer);
        if (!this.g.I) {
            for (int i = 0; i < 9; ++i) {
                OItemStack oitemstack = this.a.b(i);

                if (oitemstack != null) {
                    oentityplayer.c(oitemstack);
                }
            }
        }
    }

    public boolean a(OEntityPlayer oentityplayer) {
        return this.g.a(this.h, this.i, this.j) != OBlock.aC.cz ? false : oentityplayer.e((double) this.h + 0.5D, (double) this.i + 0.5D, (double) this.j + 0.5D) <= 64.0D;
    }

    public OItemStack b(OEntityPlayer oentityplayer, int i) {
        OItemStack oitemstack = null;
        OSlot oslot = (OSlot) this.c.get(i);

        if (oslot != null && oslot.d()) {
            OItemStack oitemstack1 = oslot.c();

            oitemstack = oitemstack1.m();
            if (i == 0) {
                if (!this.a(oitemstack1, 10, 46, true)) {
                    return null;
                }

                oslot.a(oitemstack1, oitemstack);
            } else if (i >= 10 && i < 37) {
                if (!this.a(oitemstack1, 37, 46, false)) {
                    return null;
                }
            } else if (i >= 37 && i < 46) {
                if (!this.a(oitemstack1, 10, 37, false)) {
                    return null;
                }
            } else if (!this.a(oitemstack1, 10, 46, false)) {
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

    public boolean a(OItemStack oitemstack, OSlot oslot) {
        return oslot.f != this.f && super.a(oitemstack, oslot);
    }

    // CanaryMod
    @Override
    public InventoryCrafting<?> getInventory() {
        InventoryCrafting<?> ic;

        if (super.getInventory() instanceof InventoryCrafting) {
            ic = (InventoryCrafting<?>) super.getInventory();
            if (ic.getOContainer() == null) {
                ic.setOContainer(this);
            }
        } else {
            // Not using Workbench because it uses an older form that might not be safe to update
            ic = new InventoryCrafting<OInventoryCrafting>(this, this.a, this.f);
            super.setInventory(ic);
        }

        return ic;
    }
}
