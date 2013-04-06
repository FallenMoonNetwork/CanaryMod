public class OInventoryLargeChest implements OIInventory, Container<OItemStack> {

    private String a;
    private OIInventory b;
    private OIInventory c;

    public OInventoryLargeChest(String s, OIInventory oiinventory, OIInventory oiinventory1) {
        this.a = s;
        if (oiinventory == null) {
            oiinventory = oiinventory1;
        }

        if (oiinventory1 == null) {
            oiinventory1 = oiinventory;
        }

        this.b = oiinventory;
        this.c = oiinventory1;
    }

    public int j_() {
        return this.b.j_() + this.c.j_();
    }

    public boolean a(OIInventory oiinventory) {
        return this.b == oiinventory || this.c == oiinventory;
    }

    public String b() {
        return this.b.c() ? this.b.b() : (this.c.c() ? this.c.b() : this.a);
    }

    public boolean c() {
        return this.b.c() || this.c.c();
    }

    public OItemStack a(int i) {
        return i >= this.b.j_() ? this.c.a(i - this.b.j_()) : this.b.a(i);
    }

    public OItemStack a(int i, int j) {
        return i >= this.b.j_() ? this.c.a(i - this.b.j_(), j) : this.b.a(i, j);
    }

    public OItemStack b(int i) {
        return i >= this.b.j_() ? this.c.b(i - this.b.j_()) : this.b.b(i);
    }

    public void a(int i, OItemStack oitemstack) {
        if (i >= this.b.j_()) {
            this.c.a(i - this.b.j_(), oitemstack);
        } else {
            this.b.a(i, oitemstack);
        }
    }

    public int d() {
        return this.b.d();
    }

    public void k_() {
        this.b.k_();
        this.c.k_();
    }

    public boolean a(OEntityPlayer oentityplayer) {
        return this.b.a(oentityplayer) && this.c.a(oentityplayer);
    }

    public void f() {
        this.b.f();
        this.c.f();
    }

    public void g() {
        this.b.g();
        this.c.g();
    }

    public boolean b(int i, OItemStack oitemstack) {
        return true;
    }

    public OItemStack[] getContents() {
        int size = this.getContentsSize();
        OItemStack[] result = new OItemStack[size];

        for (int i = 0; i < size; i++) {
            result[i] = getContentsAt(i);
        }
        return result;
    }

    public void setContents(OItemStack[] aoitemstack) {
        int size = this.getContentsSize();

        for (int i = 0; i < size; i++) {
            this.setContentsAt(i, aoitemstack[i]);
        }
    }

    public OItemStack getContentsAt(int i) {
        return this.a(i);
    }

    public void setContentsAt(int i, OItemStack oitemstack) {
        this.a(i, oitemstack);
    }

    public int getContentsSize() {
        return this.j_();
    }

    public String getName() {
        return this.a;
    }

    public void setName(String s) {
        this.a = s;
    }

    public Block getChestBlock() {
        if (this.b instanceof OTileEntityChest) {
            OTileEntityChest block = (OTileEntityChest) this.b;

            return block.k.world.getBlockAt(block.l, block.m, block.n);
        }
        if (this.c instanceof OTileEntityChest) {
            OTileEntityChest block = (OTileEntityChest) this.c;

            return block.k.world.getBlockAt(block.l, block.m, block.n);
        }
        return null;
    }
}
