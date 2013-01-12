public class OContainerPlayer extends OContainer {

    public OInventoryCrafting a = new OInventoryCrafting(this, 2, 2);
    public OIInventory f = new OInventoryCraftResult();
    public boolean g = false;
    private final OEntityPlayer h;

    public OContainerPlayer(OInventoryPlayer oinventoryplayer, boolean flag, OEntityPlayer oentityplayer) {
        this.g = flag;
        this.h = oentityplayer;
        this.a((OSlot) (new OSlotCrafting(oinventoryplayer.d, this.a, this.f, 0, 144, 36)));

        int i;
        int j;

        for (i = 0; i < 2; ++i) {
            for (j = 0; j < 2; ++j) {
                this.a(new OSlot(this.a, j + i * 2, 88 + j * 18, 26 + i * 18));
            }
        }

        for (i = 0; i < 4; ++i) {
            this.a((OSlot) (new OSlotArmor(this, oinventoryplayer, oinventoryplayer.k_() - 1 - i, 8, 8 + i * 18, i)));
        }

        for (i = 0; i < 3; ++i) {
            for (j = 0; j < 9; ++j) {
                this.a(new OSlot(oinventoryplayer, j + (i + 1) * 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (i = 0; i < 9; ++i) {
            this.a(new OSlot(oinventoryplayer, i, 8 + i * 18, 142));
        }

        this.a((OIInventory) this.a);
    }

    public void a(OIInventory oiinventory) {
        // Canarymod - send custom recipes result to client
        OItemStack craftresult = OCraftingManager.a().a(this.a, this.h.p);
        this.f.a(0, craftresult);

        if (this.e.size() < 1) {
            return;
        } //
        OEntityPlayerMP player = (OEntityPlayerMP) this.e.get(0);

        player.a.b(new OPacket103SetSlot(player.bK.d, 0, craftresult));

        this.f.a(0, craftresult);
    }

    public void b(OEntityPlayer oentityplayer) {
        super.b(oentityplayer);

        for (int i = 0; i < 4; ++i) {
            OItemStack oitemstack = this.a.a_(i);

            if (oitemstack != null) {
                oentityplayer.c(oitemstack);
            }
        }

        this.f.a(0, (OItemStack) null);
    }

    public boolean a(OEntityPlayer oentityplayer) {
        return true;
    }

    public OItemStack b(OEntityPlayer oentityplayer, int i) {
        OItemStack oitemstack = null;
        OSlot oslot = (OSlot) this.c.get(i);

        if (oslot != null && oslot.d()) {
            OItemStack oitemstack1 = oslot.c();

            oitemstack = oitemstack1.l();
            if (i == 0) {
                if (!this.a(oitemstack1, 9, 45, true)) {
                    return null;
                }

                oslot.a(oitemstack1, oitemstack);
            } else if (i >= 1 && i < 5) {
                if (!this.a(oitemstack1, 9, 45, false)) {
                    return null;
                }
            } else if (i >= 5 && i < 9) {
                if (!this.a(oitemstack1, 9, 45, false)) {
                    return null;
                }
            } else if (oitemstack.b() instanceof OItemArmor && !((OSlot) this.c.get(5 + ((OItemArmor) oitemstack.b()).a)).d()) {
                int j = 5 + ((OItemArmor) oitemstack.b()).a;

                if (!this.a(oitemstack1, j, j + 1, false)) {
                    return null;
                }
            } else if (i >= 9 && i < 36) {
                if (!this.a(oitemstack1, 36, 45, false)) {
                    return null;
                }
            } else if (i >= 36 && i < 45) {
                if (!this.a(oitemstack1, 9, 36, false)) {
                    return null;
                }
            } else if (!this.a(oitemstack1, 9, 45, false)) {
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
    @Override
    public InventoryCrafting getInventory() {
        if(super.getInventory() instanceof InventoryCrafting)
            return (InventoryCrafting)super.getInventory();
        
        InventoryCrafting inv = new InventoryCrafting(this, this.a, this.f);
        setInventory(inv);
        return inv;
    }
}
