import java.util.Arrays;
import java.util.Random;

public class OTileEntityDispenser extends OTileEntity implements OIInventory, Container<OItemStack> {

    private OItemStack[] b = new OItemStack[9];
    private Random c = new Random();
    protected String a;

    private final Dispenser dispenser = new Dispenser(this); // CanaryMod

    public OTileEntityDispenser() {}

    public int j_() {
        return 9;
    }

    public OItemStack a(int i) {
        return this.b[i];
    }

    public OItemStack a(int i, int j) {
        if (this.b[i] != null) {
            OItemStack oitemstack;

            if (this.b[i].a <= j) {
                oitemstack = this.b[i];
                this.b[i] = null;
                this.k_();
                return oitemstack;
            } else {
                oitemstack = this.b[i].a(j);
                if (this.b[i].a == 0) {
                    this.b[i] = null;
                }

                this.k_();
                return oitemstack;
            }
        } else {
            return null;
        }
    }

    public OItemStack b(int i) {
        if (this.b[i] != null) {
            OItemStack oitemstack = this.b[i];

            this.b[i] = null;
            return oitemstack;
        } else {
            return null;
        }
    }

    public int j() {
        int i = -1;
        int j = 1;

        for (int k = 0; k < this.b.length; ++k) {
            if (this.b[k] != null && this.c.nextInt(j++) == 0) {
                i = k;
            }
        }

        return i;
    }

    public void a(int i, OItemStack oitemstack) {
        this.b[i] = oitemstack;
        if (oitemstack != null && oitemstack.a > this.d()) {
            oitemstack.a = this.d();
        }

        this.k_();
    }

    public int a(OItemStack oitemstack) {
        for (int i = 0; i < this.b.length; ++i) {
            if (this.b[i] == null || this.b[i].c == 0) {
                this.a(i, oitemstack);
                return i;
            }
        }

        return -1;
    }

    public String b() {
        return this.c() ? this.a : "container.dispenser";
    }

    public void a(String s) {
        this.a = s;
    }

    public boolean c() {
        return this.a != null;
    }

    public void a(ONBTTagCompound onbttagcompound) {
        super.a(onbttagcompound);
        ONBTTagList onbttaglist = onbttagcompound.m("Items");

        this.b = new OItemStack[this.j_()];

        for (int i = 0; i < onbttaglist.c(); ++i) {
            ONBTTagCompound onbttagcompound1 = (ONBTTagCompound) onbttaglist.b(i);
            int j = onbttagcompound1.c("Slot") & 255;

            if (j >= 0 && j < this.b.length) {
                this.b[j] = OItemStack.a(onbttagcompound1);
            }
        }

        if (onbttagcompound.b("CustomName")) {
            this.a = onbttagcompound.i("CustomName");
        }
    }

    public void b(ONBTTagCompound onbttagcompound) {
        super.b(onbttagcompound);
        ONBTTagList onbttaglist = new ONBTTagList();

        for (int i = 0; i < this.b.length; ++i) {
            if (this.b[i] != null) {
                ONBTTagCompound onbttagcompound1 = new ONBTTagCompound();

                onbttagcompound1.a("Slot", (byte) i);
                this.b[i].b(onbttagcompound1);
                onbttaglist.a((ONBTBase) onbttagcompound1);
            }
        }

        onbttagcompound.a("Items", (ONBTBase) onbttaglist);
        if (this.c()) {
            onbttagcompound.a("CustomName", this.a);
        }
    }

    public int d() {
        return 64;
    }

    public boolean a(OEntityPlayer oentityplayer) {
        return this.k.r(this.l, this.m, this.n) != this ? false : oentityplayer.e((double) this.l + 0.5D, (double) this.m + 0.5D, (double) this.n + 0.5D) <= 64.0D;
    }

    public void f() {}

    public void g() {}

    public boolean b(int i, OItemStack oitemstack) {
        return true;
    }

    @Override
    public OItemStack[] getContents() {
        return Arrays.copyOf(this.b, this.getContentsSize());
    }

    @Override
    public void setContents(OItemStack[] aoitemstack) {
        this.b = Arrays.copyOf(aoitemstack, this.getContentsSize());
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
        return this.j_();
    }

    @Override
    public String getName() {
        return this.b();
    }

    @Override
    public void setName(String s) {
        this.a(s);
    }

    @Override
    public Dispenser getComplexBlock() {
        return dispenser;
    }

}
