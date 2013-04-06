public class OInventoryCrafting implements OIInventory, Container<OItemStack>
{
    private OItemStack[] a;
    private int b;
    private OContainer c;

    // CanaryMod
    private String name = "container.crafting";

    public OInventoryCrafting(OContainer paramOContainer, int paramInt1, int paramInt2)
    {
        int i = paramInt1 * paramInt2;
        this.a = new OItemStack[i];
        this.c = paramOContainer;
        this.b = paramInt1;
    }

    public int j_() {
        return this.a.length;
    }

    public OItemStack a(int i) {
        return i >= this.j_() ? null : this.a[i];
    }

    public OItemStack b(int i, int j) {
        if (i >= 0 && i < this.b) {
            int k = i + j * this.b;

            return this.a(k);
        } else {
            return null;
        }
    }

    public String b() {
        return name; // CanaryMod
    }

    public boolean c() {
        return false;
    }

    public OItemStack b(int i) {
        if (this.a[i] != null) {
            OItemStack oitemstack = this.a[i];

            this.a[i] = null;
            return oitemstack;
        } else {
            return null;
        }
    }

    public OItemStack a(int i, int j) {
        if (this.a[i] != null) {
            OItemStack oitemstack;

            if (this.a[i].a <= j) {
                oitemstack = this.a[i];
                this.a[i] = null;
                this.c.a((OIInventory) this);
                return oitemstack;
            } else {
                oitemstack = this.a[i].a(j);
                if (this.a[i].a == 0) {
                    this.a[i] = null;
                }

                this.c.a((OIInventory) this);
                return oitemstack;
            }
        } else {
            return null;
        }
    }

    public void a(int i, OItemStack oitemstack) {
        this.a[i] = oitemstack;
        this.c.a((OIInventory) this);
    }

    public int d() {
        return 64;
    }

    public void k_() {}

    public boolean a(OEntityPlayer oentityplayer) {
        return true;
    }

    public void f() {}

    public void g() {}

    public boolean b(int i, OItemStack oitemstack) {
        return true;
    }

    // CanaryMod
    @Override
    public OItemStack[] getContents() {
        return this.a;
    }

    @Override
    public void setContents(OItemStack[] values) {
        this.a = values;
    }

    @Override
    public OItemStack getContentsAt(int index) {
        return this.a(index);
    }

    @Override
    public void setContentsAt(int index, OItemStack value) {
        this.a(index, value);
    }

    @Override
    public int getContentsSize() {
        return this.j_();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String value) {
        name = value;
    }
}