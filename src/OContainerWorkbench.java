public class OContainerWorkbench extends OContainer {

    public OInventoryCrafting e = new OInventoryCrafting(this, 3, 3);
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
        this.a((OSlot) (new OSlotCrafting(oinventoryplayer.d, this.e, this.f, 0, 124, 35)));

        int l;
        int i1;

        for (l = 0; l < 3; ++l) {
            for (i1 = 0; i1 < 3; ++i1) {
                this.a(new OSlot(this.e, i1 + l * 3, 30 + i1 * 18, 17 + l * 18));
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

        this.a((OIInventory) this.e);
    }

    // Canarymod - send custom recipes result to client
    public void a(OIInventory oiinventory) {
        OItemStack craftresult = OCraftingManager.a().a(this.e, this.g);
        this.f.a(0, craftresult);
        
        if (this.d.isEmpty()) {
            return;
        } //
        OEntityPlayerMP player = (OEntityPlayerMP) this.d.get(0); 

        player.a.b(new OPacket103SetSlot(this.c, 0, craftresult));
    }

    public void a(OEntityPlayer oentityplayer) {
        super.a(oentityplayer);
        if (!this.g.J) {
            for (int i = 0; i < 9; ++i) {
                OItemStack oitemstack = this.e.a_(i);

                if (oitemstack != null) {
                    oentityplayer.c(oitemstack);
                }
            }
        }
    }

    public boolean c(OEntityPlayer oentityplayer) {
        return this.g.a(this.h, this.i, this.j) != OBlock.aB.cm ? false : oentityplayer.e((double) this.h + 0.5D, (double) this.i + 0.5D, (double) this.j + 0.5D) <= 64.0D;
    }

    public OItemStack b(OEntityPlayer oentityplayer, int i) {
        OItemStack oitemstack = null;
        OSlot oslot = (OSlot) this.b.get(i);

        if (oslot != null && oslot.d()) {
            OItemStack oitemstack1 = oslot.c();

            oitemstack = oitemstack1.l();
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
}
