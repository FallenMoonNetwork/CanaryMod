import java.util.Arrays;
import java.util.Random;


public class OTileEntityDispenser extends OTileEntity implements OIInventory, Container<OItemStack> {

    private OItemStack[] a = new OItemStack[9];
    private Random b = new Random();
    private String name = "container.dispenser";

    public OTileEntityDispenser() {
        super();
    }

    public int k_() {
        return 9;
    }

    public OItemStack a(int i) {
        return this.a[i];
    }

    public OItemStack a(int i, int j) {
        if (this.a[i] != null) {
            OItemStack oitemstack;

            if (this.a[i].a <= j) {
                oitemstack = this.a[i];
                this.a[i] = null;
                this.d();
                return oitemstack;
            } else {
                oitemstack = this.a[i].a(j);
                if (this.a[i].a == 0) {
                    this.a[i] = null;
                }

                this.d();
                return oitemstack;
            }
        } else {
            return null;
        }
    }

    public OItemStack a_(int i) {
        if (this.a[i] != null) {
            OItemStack oitemstack = this.a[i];

            this.a[i] = null;
            return oitemstack;
        } else {
            return null;
        }
    }

    public int i() {
        int i = -1;
        int j = 1;

        for (int k = 0; k < this.a.length; ++k) {
            if (this.a[k] != null && this.b.nextInt(j++) == 0) {
                i = k;
            }
        }

        return i;
    }

    public void a(int i, OItemStack oitemstack) {
        this.a[i] = oitemstack;
        if (oitemstack != null && oitemstack.a > this.c()) {
            oitemstack.a = this.c();
        }

        this.d();
    }

    public int a(OItemStack oitemstack) {
        for (int i = 0; i < this.a.length; ++i) {
            if (this.a[i] == null || this.a[i].c == 0) {
                this.a[i] = oitemstack;
                return i;
            }
        }

        return -1;
    }

    public String b() {
        return name;
    }

    public void a(ONBTTagCompound onbttagcompound) {
        super.a(onbttagcompound);
        ONBTTagList onbttaglist = onbttagcompound.m("Items");

        this.a = new OItemStack[this.k_()];

        for (int i = 0; i < onbttaglist.c(); ++i) {
            ONBTTagCompound onbttagcompound1 = (ONBTTagCompound) onbttaglist.b(i);
            int j = onbttagcompound1.c("Slot") & 255;

            if (j >= 0 && j < this.a.length) {
                this.a[j] = OItemStack.a(onbttagcompound1);
            }
        }

    }

    public void b(ONBTTagCompound onbttagcompound) {
        super.b(onbttagcompound);
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

    public int c() {
        return 64;
    }

    public boolean a(OEntityPlayer oentityplayer) {
        return this.k.p(this.l, this.m, this.n) != this ? false : oentityplayer.e((double) this.l + 0.5D, (double) this.m + 0.5D, (double) this.n + 0.5D) <= 64.0D;
    }

    public void l_() {}

    public void f() {}

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
        return this.k_();
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
