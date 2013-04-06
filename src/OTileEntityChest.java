import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class OTileEntityChest extends OTileEntity implements OIInventory, Container<OItemStack> {

    private OItemStack[] i = new OItemStack[36];
    public boolean a = false;
    public OTileEntityChest b;
    public OTileEntityChest c;
    public OTileEntityChest d;
    public OTileEntityChest e;
    public float f;
    public float g;
    public int h;
    private int j;
    private int r = -1;
    private String s;

    private final Chest chest = new Chest(this); // CanaryMod

    public OTileEntityChest() {}

    public int j_() {
        return 27;
    }

    public OItemStack a(int i) {
        return this.i[i];
    }

    public OItemStack a(int i, int j) {
        if (this.i[i] != null) {
            OItemStack oitemstack;

            if (this.i[i].a <= j) {
                oitemstack = this.i[i];
                this.i[i] = null;
                this.k_();
                return oitemstack;
            } else {
                oitemstack = this.i[i].a(j);
                if (this.i[i].a == 0) {
                    this.i[i] = null;
                }

                this.k_();
                return oitemstack;
            }
        } else {
            return null;
        }
    }

    public OItemStack b(int i) {
        if (this.i[i] != null) {
            OItemStack oitemstack = this.i[i];

            this.i[i] = null;
            return oitemstack;
        } else {
            return null;
        }
    }

    public void a(int i, OItemStack oitemstack) {
        this.i[i] = oitemstack;
        if (oitemstack != null && oitemstack.a > this.d()) {
            oitemstack.a = this.d();
        }

        this.k_();
    }

    public String b() {
        return this.c() ? this.s : "container.chest";
    }

    public boolean c() {
        return this.s != null && this.s.length() > 0;
    }

    public void a(String s) {
        this.s = s;
    }

    public void a(ONBTTagCompound onbttagcompound) {
        super.a(onbttagcompound);
        ONBTTagList onbttaglist = onbttagcompound.m("Items");

        this.i = new OItemStack[this.j_()];
        if (onbttagcompound.b("CustomName")) {
            this.s = onbttagcompound.i("CustomName");
        }

        for (int i = 0; i < onbttaglist.c(); ++i) {
            ONBTTagCompound onbttagcompound1 = (ONBTTagCompound) onbttaglist.b(i);
            int j = onbttagcompound1.c("Slot") & 255;

            if (j >= 0 && j < this.i.length) {
                this.i[j] = OItemStack.a(onbttagcompound1);
            }
        }
    }

    public void b(ONBTTagCompound onbttagcompound) {
        super.b(onbttagcompound);
        ONBTTagList onbttaglist = new ONBTTagList();

        for (int i = 0; i < this.i.length; ++i) {
            if (this.i[i] != null) {
                ONBTTagCompound onbttagcompound1 = new ONBTTagCompound();

                onbttagcompound1.a("Slot", (byte) i);
                this.i[i].b(onbttagcompound1);
                onbttaglist.a((ONBTBase) onbttagcompound1);
            }
        }

        onbttagcompound.a("Items", (ONBTBase) onbttaglist);
        if (this.c()) {
            onbttagcompound.a("CustomName", this.s);
        }
    }

    public int d() {
        return 64;
    }

    public boolean a(OEntityPlayer oentityplayer) {
        return this.k.r(this.l, this.m, this.n) != this ? false : oentityplayer.e((double) this.l + 0.5D, (double) this.m + 0.5D, (double) this.n + 0.5D) <= 64.0D;
    }

    public void i() {
        super.i();
        this.a = false;
    }

    private void a(OTileEntityChest otileentitychest, int i) {
        if (otileentitychest.r()) {
            this.a = false;
        } else if (this.a) {
            switch (i) {
                case 0:
                    if (this.e != otileentitychest) {
                        this.a = false;
                    }
                    break;

                case 1:
                    if (this.d != otileentitychest) {
                        this.a = false;
                    }
                    break;

                case 2:
                    if (this.b != otileentitychest) {
                        this.a = false;
                    }
                    break;

                case 3:
                    if (this.c != otileentitychest) {
                        this.a = false;
                    }
            }
        }
    }

    public void j() {
        if (!this.a) {
            this.a = true;
            this.b = null;
            this.c = null;
            this.d = null;
            this.e = null;
            if (this.a(this.l - 1, this.m, this.n)) {
                this.d = (OTileEntityChest) this.k.r(this.l - 1, this.m, this.n);
            }

            if (this.a(this.l + 1, this.m, this.n)) {
                this.c = (OTileEntityChest) this.k.r(this.l + 1, this.m, this.n);
            }

            if (this.a(this.l, this.m, this.n - 1)) {
                this.b = (OTileEntityChest) this.k.r(this.l, this.m, this.n - 1);
            }

            if (this.a(this.l, this.m, this.n + 1)) {
                this.e = (OTileEntityChest) this.k.r(this.l, this.m, this.n + 1);
            }

            if (this.b != null) {
                this.b.a(this, 0);
            }

            if (this.e != null) {
                this.e.a(this, 2);
            }

            if (this.c != null) {
                this.c.a(this, 1);
            }

            if (this.d != null) {
                this.d.a(this, 3);
            }
        }
    }

    private boolean a(int i, int j, int k) {
        OBlock oblock = OBlock.r[this.k.a(i, j, k)];

        return oblock != null && oblock instanceof OBlockChest ? ((OBlockChest) oblock).a == this.l() : false;
    }

    public void h() {
        super.h();
        this.j();
        ++this.j;
        float f;

        if (!this.k.I && this.h != 0 && (this.j + this.l + this.m + this.n) % 200 == 0) {
            this.h = 0;
            f = 5.0F;
            List list = this.k.a(OEntityPlayer.class, OAxisAlignedBB.a().a((double) ((float) this.l - f), (double) ((float) this.m - f), (double) ((float) this.n - f), (double) ((float) (this.l + 1) + f), (double) ((float) (this.m + 1) + f), (double) ((float) (this.n + 1) + f)));
            Iterator iterator = list.iterator();

            while (iterator.hasNext()) {
                OEntityPlayer oentityplayer = (OEntityPlayer) iterator.next();

                if (oentityplayer.bM instanceof OContainerChest) {
                    OIInventory oiinventory = ((OContainerChest) oentityplayer.bM).e();

                    if (oiinventory == this || oiinventory instanceof OInventoryLargeChest && ((OInventoryLargeChest) oiinventory).a((OIInventory) this)) {
                        ++this.h;
                    }
                }
            }
        }

        this.g = this.f;
        f = 0.1F;
        double d0;

        if (this.h > 0 && this.f == 0.0F && this.b == null && this.d == null) {
            double d1 = (double) this.l + 0.5D;

            d0 = (double) this.n + 0.5D;
            if (this.e != null) {
                d0 += 0.5D;
            }

            if (this.c != null) {
                d1 += 0.5D;
            }

            this.k.a(d1, (double) this.m + 0.5D, d0, "random.chestopen", 0.5F, this.k.s.nextFloat() * 0.1F + 0.9F);
        }

        if (this.h == 0 && this.f > 0.0F || this.h > 0 && this.f < 1.0F) {
            float f1 = this.f;

            if (this.h > 0) {
                this.f += f;
            } else {
                this.f -= f;
            }

            if (this.f > 1.0F) {
                this.f = 1.0F;
            }

            float f2 = 0.5F;

            if (this.f < f2 && f1 >= f2 && this.b == null && this.d == null) {
                d0 = (double) this.l + 0.5D;
                double d2 = (double) this.n + 0.5D;

                if (this.e != null) {
                    d2 += 0.5D;
                }

                if (this.c != null) {
                    d0 += 0.5D;
                }

                this.k.a(d0, (double) this.m + 0.5D, d2, "random.chestclosed", 0.5F, this.k.s.nextFloat() * 0.1F + 0.9F);
            }

            if (this.f < 0.0F) {
                this.f = 0.0F;
            }
        }
    }

    public boolean b(int i, int j) {
        if (i == 1) {
            this.h = j;
            return true;
        } else {
            return super.b(i, j);
        }
    }

    public void f() {
        if (this.h < 0) {
            this.h = 0;
        }

        ++this.h;
        this.k.d(this.l, this.m, this.n, this.q().cz, 1, this.h);
        this.k.f(this.l, this.m, this.n, this.q().cz);
        this.k.f(this.l, this.m - 1, this.n, this.q().cz);
    }

    public void g() {
        if (this.q() != null && this.q() instanceof OBlockChest) {
            --this.h;
            this.k.d(this.l, this.m, this.n, this.q().cz, 1, this.h);
            this.k.f(this.l, this.m, this.n, this.q().cz);
            this.k.f(this.l, this.m - 1, this.n, this.q().cz);
        }
    }

    public boolean b(int i, OItemStack oitemstack) {
        return true;
    }

    public void w_() {
        super.w_();
        this.i();
        this.j();
    }

    public int l() {
        if (this.r == -1) {
            if (this.k == null || !(this.q() instanceof OBlockChest)) {
                return 0;
            }

            this.r = ((OBlockChest) this.q()).a;
        }

        return this.r;
    }

    @Override
    public OItemStack[] getContents() {
        return Arrays.copyOf(this.i, this.getContentsSize());
    }

    @Override
    public void setContents(OItemStack[] aoitemstack) {
        this.i = Arrays.copyOf(aoitemstack, this.getContentsSize());
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
    public Chest getComplexBlock() {
        return chest;
    }
}
