public class OContainerMerchant extends OContainer {

    private OIMerchant a;
    private OInventoryMerchant f;
    private final OWorld g;

    public OContainerMerchant(OInventoryPlayer oinventoryplayer, OIMerchant oimerchant, OWorld oworld) {
        this.a = oimerchant;
        this.g = oworld;
        this.f = new OInventoryMerchant(oinventoryplayer.d, oimerchant);
        this.a(new OSlot(this.f, 0, 36, 53));
        this.a(new OSlot(this.f, 1, 62, 53));
        this.a((OSlot) (new OSlotMerchantResult(oinventoryplayer.d, oimerchant, this.f, 2, 120, 53)));

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

    public OInventoryMerchant d() {
        return this.f;
    }

    public void a(OICrafting oicrafting) {
        super.a(oicrafting);
    }

    public void b() {
        super.b();
    }

    public void a(OIInventory oiinventory) {
        this.f.g();
        super.a(oiinventory);
    }

    public void b(int i) {
        this.f.c(i);
    }

    public boolean a(OEntityPlayer oentityplayer) {
        return this.a.m_() == oentityplayer;
    }

    public OItemStack b(OEntityPlayer oentityplayer, int i) {
        OItemStack oitemstack = null;
        OSlot oslot = (OSlot) this.c.get(i);

        if (oslot != null && oslot.d()) {
            OItemStack oitemstack1 = oslot.c();

            oitemstack = oitemstack1.l();
            if (i == 2) {
                if (!this.a(oitemstack1, 3, 39, true)) {
                    return null;
                }

                oslot.a(oitemstack1, oitemstack);
            } else if (i != 0 && i != 1) {
                if (i >= 3 && i < 30) {
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

            //CanaryMod start
            if (oslot instanceof OSlotMerchantResult) {
                if (((OSlotMerchantResult) oslot).a(oentityplayer, oitemstack1, true)) { //tells the method the player held shift
                    oitemstack = null;
                }
            } else {
                oslot.a(oentityplayer, oitemstack1);
            } //CanaryMod end
        }

        return oitemstack;
    }

    public void b(OEntityPlayer oentityplayer) {
        super.b(oentityplayer);
        this.a.b_((OEntityPlayer) null);
        super.b(oentityplayer);
        if (!this.g.I) {
            OItemStack oitemstack = this.f.a_(0);

            if (oitemstack != null) {
                oentityplayer.c(oitemstack);
            }

            oitemstack = this.f.a_(1);
            if (oitemstack != null) {
                oentityplayer.c(oitemstack);
            }
        }
    }
    
    // CanaryMod
    @Override
    public InventoryMerchant getInventory() {
        if(super.getInventory() instanceof InventoryMerchant)
            return (InventoryMerchant)super.getInventory();
        
        InventoryMerchant inv = new InventoryMerchant(this, this.f);
        setInventory(inv);
        return inv;
    }
}
