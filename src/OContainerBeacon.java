public class OContainerBeacon extends OContainer {

    private OTileEntityBeacon a;
    private final OSlotBeacon f;
    private int g;
    private int h;
    private int i;

    public OContainerBeacon(OInventoryPlayer oinventoryplayer, OTileEntityBeacon otileentitybeacon) {
        this.a = otileentitybeacon;
        this.a((OSlot) (this.f = new OSlotBeacon(this, otileentitybeacon, 0, 136, 110)));
        byte b0 = 36;
        short short1 = 137;

        int i;

        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.a(new OSlot(oinventoryplayer, j + i * 9 + 9, b0 + j * 18, short1 + i * 18));
            }
        }

        for (i = 0; i < 9; ++i) {
            this.a(new OSlot(oinventoryplayer, i, b0 + i * 18, 58 + short1));
        }

        this.g = otileentitybeacon.l();
        this.h = otileentitybeacon.j();
        this.i = otileentitybeacon.k();
    }

    public void a(OICrafting oicrafting) {
        super.a(oicrafting);
        oicrafting.a(this, 0, this.g);
        oicrafting.a(this, 1, this.h);
        oicrafting.a(this, 2, this.i);
    }

    public void b() {
        super.b();
    }

    public OTileEntityBeacon e() {
        return this.a;
    }

    public boolean a(OEntityPlayer oentityplayer) {
        return this.a.a(oentityplayer);
    }

    public OItemStack b(OEntityPlayer oentityplayer, int i) {
        OItemStack oitemstack = null;
        OSlot oslot = (OSlot) this.c.get(i);

        if (oslot != null && oslot.d()) {
            OItemStack oitemstack1 = oslot.c();

            oitemstack = oitemstack1.m();
            if (i == 0) {
                if (!this.a(oitemstack1, 1, 37, true)) {
                    return null;
                }

                oslot.a(oitemstack1, oitemstack);
            } else if (!this.f.d() && this.f.a(oitemstack1) && oitemstack1.a == 1) {
                if (!this.a(oitemstack1, 0, 1, false)) {
                    return null;
                }
            } else if (i >= 1 && i < 28) {
                if (!this.a(oitemstack1, 28, 37, false)) {
                    return null;
                }
            } else if (i >= 28 && i < 37) {
                if (!this.a(oitemstack1, 1, 28, false)) {
                    return null;
                }
            } else if (!this.a(oitemstack1, 1, 37, false)) {
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

    @Override
    public Beacon getInventory() {
        if (super.getInventory() instanceof Beacon) {
            return (Beacon) super.getInventory();
        }

        Beacon inv = new Beacon(this, this.a);
        super.setInventory(inv);
        return inv;
    }
}
