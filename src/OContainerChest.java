
public class OContainerChest extends OContainer {

    private OIInventory e;
    private int f;
    // CanaryMod: silenced - Used to determine wether the chest should be opened or closed stealthily
    private boolean silenced;

    public OContainerChest(OIInventory oiinventory, OIInventory oiinventory1, boolean flag) {
        this.e = oiinventory1;
<<<<<<<
        this.f = oiinventory1.i_() / 9;
        this.silenced = flag;
        if (!this.silenced) {
            oiinventory1.k_();
        }
|||||||
        this.f = oiinventory1.i_() / 9;
        oiinventory1.k_();
=======
        this.f = oiinventory1.k_() / 9;
        oiinventory1.l_();
>>>>>>>
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

    public boolean c(OEntityPlayer oentityplayer) {
        return this.e.a(oentityplayer);
    }

    public OItemStack b(OEntityPlayer oentityplayer, int i) {
        OItemStack oitemstack = null;
        OSlot oslot = (OSlot) this.b.get(i);

        if (oslot != null && oslot.d()) {
            OItemStack oitemstack1 = oslot.c();

            oitemstack = oitemstack1.l();
            if (i < this.f * 9) {
                if (!this.a(oitemstack1, this.f * 9, this.b.size(), true)) {
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

    public void a(OEntityPlayer oentityplayer) {
        super.a(oentityplayer);
        if (!this.silenced) {
            this.e.f();
        }
    }
}
