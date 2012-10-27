import java.util.Arrays;
import java.util.List;


public class OTileEntityBrewingStand extends OTileEntity implements OIInventory, Container<OItemStack> {

    private OItemStack[] a = new OItemStack[4];
    private int b;
    private int c;
    private int d;
    
    private String name = "container.brewing"; // CanaryMod

    public OTileEntityBrewingStand() {
        super();
    }

    public String b() {
        return name;
    }

    public int k_() {
        return this.a.length;
    }

    public void g() {
        if (this.b > 0) {
            --this.b;
            if (this.b == 0) {
                this.t();
                this.d();
            } else if (!this.k()) {
                this.b = 0;
                this.d();
            } else if (this.d != this.a[3].c) {
                this.b = 0;
                this.d();
            }
        } else if (this.k()) {
            this.b = 400;
            this.d = this.a[3].c;
        }

        int i = this.i();

        if (i != this.c) {
            this.c = i;
            this.k.c(this.l, this.m, this.n, i);
        }

        super.g();
    }

    public int x_() {
        return this.b;
    }

    private boolean k() {
        if (this.a[3] != null && this.a[3].a > 0) {
            OItemStack oitemstack = this.a[3];

            if (!OItem.e[oitemstack.c].v()) {
                return false;
            } else {
                boolean flag = false;

                for (int i = 0; i < 3; ++i) {
                    if (this.a[i] != null && this.a[i].c == OItem.bs.cf) {
                        int j = this.a[i].j();
                        int k = this.b(j, oitemstack);

                        if (!OItemPotion.g(j) && OItemPotion.g(k)) {
                            flag = true;
                            break;
                        }

                        List list = OItem.bs.f(j);
                        List list1 = OItem.bs.f(k);

                        if ((j <= 0 || list != list1) && (list == null || !list.equals(list1) && list1 != null) && j != k) {
                            flag = true;
                            break;
                        }
                    }
                }

                return flag;
            }
        } else {
            return false;
        }
    }

    private void t() {
        if (this.k()) {
            OItemStack oitemstack = this.a[3];

            for (int i = 0; i < 3; ++i) {
                if (this.a[i] != null && this.a[i].c == OItem.bs.cf) {
                    int j = this.a[i].j();
                    int k = this.b(j, oitemstack);
                    List list = OItem.bs.f(j);
                    List list1 = OItem.bs.f(k);

                    if ((j <= 0 || list != list1) && (list == null || !list.equals(list1) && list1 != null)) {
                        if (j != k) {
                            this.a[i].b(k);
                        }
                    } else if (!OItemPotion.g(j) && OItemPotion.g(k)) {
                        this.a[i].b(k);
                    }
                }
            }

            if (OItem.e[oitemstack.c].s()) {
                this.a[3] = new OItemStack(OItem.e[oitemstack.c].r());
            } else {
                --this.a[3].a;
                if (this.a[3].a <= 0) {
                    this.a[3] = null;
                }
            }

        }
    }

    private int b(int i, OItemStack oitemstack) {
        return oitemstack == null ? i : (OItem.e[oitemstack.c].v() ? OPotionHelper.a(i, OItem.e[oitemstack.c].u()) : i);
    }

    public void a(ONBTTagCompound onbttagcompound) {
        super.a(onbttagcompound);
        ONBTTagList onbttaglist = onbttagcompound.m("Items");

        this.a = new OItemStack[this.k_()];

        for (int i = 0; i < onbttaglist.c(); ++i) {
            ONBTTagCompound onbttagcompound1 = (ONBTTagCompound) onbttaglist.b(i);
            byte b0 = onbttagcompound1.c("Slot");

            if (b0 >= 0 && b0 < this.a.length) {
                this.a[b0] = OItemStack.a(onbttagcompound1);
            }
        }

        this.b = onbttagcompound.d("BrewTime");
    }

    public void b(ONBTTagCompound onbttagcompound) {
        super.b(onbttagcompound);
        onbttagcompound.a("BrewTime", (short) this.b);
        ONBTTagList onbttaglist = new ONBTTagList();

        for (int i = 0; i < this.a.length; ++i) {
            if (this.a[i] != null) {
                ONBTTagCompound onbttagcompound1 = new ONBTTagCompound();

                onbttagcompound1.a("Slot", (byte) i);
                this.a[i].b(onbttagcompound1);
                onbttaglist.a((ONBTBase) onbttagcompound1);
            }
        }

        onbttagcompound.a("Items", (ONBTBase) onbttaglist);
    }

    public OItemStack a(int i) {
        return i >= 0 && i < this.a.length ? this.a[i] : null;
    }

    public OItemStack a(int i, int j) {
        if (i >= 0 && i < this.a.length) {
            OItemStack oitemstack = this.a[i];

            this.a[i] = null;
            return oitemstack;
        } else {
            return null;
        }
    }

    public OItemStack a_(int i) {
        if (i >= 0 && i < this.a.length) {
            OItemStack oitemstack = this.a[i];

            this.a[i] = null;
            return oitemstack;
        } else {
            return null;
        }
    }

    public void a(int i, OItemStack oitemstack) {
        if (i >= 0 && i < this.a.length) {
            this.a[i] = oitemstack;
        }

    }

    public int c() {
        return 1;
    }

    public boolean a(OEntityPlayer oentityplayer) {
        return this.k.p(this.l, this.m, this.n) != this ? false : oentityplayer.e((double) this.l + 0.5D, (double) this.m + 0.5D, (double) this.n + 0.5D) <= 64.0D;
    }

    public void l_() {}

    public void f() {}

    public int i() {
        int i = 0;

        for (int j = 0; j < 3; ++j) {
            if (this.a[j] != null) {
                i |= 1 << j;
            }
        }

        return i;
    }

    @Override
    public OItemStack[] getContents() {
        return Arrays.copyOf(this.a, this.getContentsSize());
    }

    @Override
    public void setContents(OItemStack[] aoitemstack) {
        this.a = Arrays.copyOf(aoitemstack, this.getContentsSize());
    }

    @Override
    public OItemStack getContentsAt(int i) {
        return this.a(i);
    }

    @Override
    public void setContentsAt(int i, OItemStack oitemstack) {
        this.a(i, oitemstack);
    }

    @Override
    public int getContentsSize() {
        return this.i_();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String s) {
        this.name = s;
    }
}
