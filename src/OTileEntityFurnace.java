import java.util.Arrays;

public class OTileEntityFurnace extends OTileEntity implements OISidedInventory, Container<OItemStack> {

    private static final int[] d = new int[] { 0};
    private static final int[] e = new int[] { 2, 1};
    private static final int[] f = new int[] { 1};
    private OItemStack[] g = new OItemStack[3];
    public int a = 0;
    public int b = 0;
    public int c = 0;
    private String h;

    private final Furnace furnace = new Furnace(this); // CanaryMod

    public OTileEntityFurnace() {}

    public int j_() {
        return this.g.length;
    }

    public OItemStack a(int i) {
        return this.g[i];
    }

    public OItemStack a(int i, int j) {
        if (this.g[i] != null) {
            OItemStack oitemstack;

            if (this.g[i].a <= j) {
                oitemstack = this.g[i];
                this.g[i] = null;
                return oitemstack;
            } else {
                oitemstack = this.g[i].a(j);
                if (this.g[i].a == 0) {
                    this.g[i] = null;
                }

                return oitemstack;
            }
        } else {
            return null;
        }
    }

    public OItemStack b(int i) {
        if (this.g[i] != null) {
            OItemStack oitemstack = this.g[i];

            this.g[i] = null;
            return oitemstack;
        } else {
            return null;
        }
    }

    public void a(int i, OItemStack oitemstack) {
        this.g[i] = oitemstack;
        if (oitemstack != null && oitemstack.a > this.d()) {
            oitemstack.a = this.d();
        }

    }

    public String b() {
        return this.c() ? this.h : "container.furnace";
    }

    public boolean c() {
        return this.h != null && this.h.length() > 0;
    }

    public void a(String s) {
        this.h = s;
    }

    public void a(ONBTTagCompound onbttagcompound) {
        super.a(onbttagcompound);
        ONBTTagList onbttaglist = onbttagcompound.m("Items");

        this.g = new OItemStack[this.j_()];

        for (int i = 0; i < onbttaglist.c(); ++i) {
            ONBTTagCompound onbttagcompound1 = (ONBTTagCompound) onbttaglist.b(i);
            byte b0 = onbttagcompound1.c("Slot");

            if (b0 >= 0 && b0 < this.g.length) {
                this.g[b0] = OItemStack.a(onbttagcompound1);
            }
        }

        this.a = onbttagcompound.d("BurnTime");
        this.c = onbttagcompound.d("CookTime");
        this.b = a(this.g[1]);
        if (onbttagcompound.b("CustomName")) {
            this.h = onbttagcompound.i("CustomName");
        }
    }

    public void b(ONBTTagCompound onbttagcompound) {
        super.b(onbttagcompound);
        onbttagcompound.a("BurnTime", (short) this.a);
        onbttagcompound.a("CookTime", (short) this.c);
        ONBTTagList onbttaglist = new ONBTTagList();

        for (int i = 0; i < this.g.length; ++i) {
            if (this.g[i] != null) {
                ONBTTagCompound onbttagcompound1 = new ONBTTagCompound();

                onbttagcompound1.a("Slot", (byte) i);
                this.g[i].b(onbttagcompound1);
                onbttaglist.a((ONBTBase) onbttagcompound1);
            }
        }

        onbttagcompound.a("Items", (ONBTBase) onbttaglist);
        if (this.c()) {
            onbttagcompound.a("CustomName", this.h);
        }
    }

    public int d() {
        return 64;
    }

    public boolean j() {
        return this.a > 0;
    }

    public void h() {
        boolean flag = this.a > 0;
        boolean flag1 = false;

        if (this.a > 0) {
            --this.a;
        }

        if (!this.k.I) {
            if (this.a == 0 && this.u()) {
                this.b = this.a = a(this.g[1]);
                if (this.a > 0) {
                    flag1 = true;
                    if (this.g[1] != null) {
                        --this.g[1].a;
                        if (this.g[1].a == 0) {
                            OItem oitem = this.g[1].b().s();

                            this.g[1] = oitem != null ? new OItemStack(oitem) : null;
                        }
                    }
                }
            }

            if (this.j() && this.u()) {
                ++this.c;
                if (this.c == 200) {
                    this.c = 0;
                    this.l();
                    flag1 = true;
                }
            } else {
                this.c = 0;
            }

            if (flag != this.a > 0) {
                flag1 = true;
                OBlockFurnace.a(this.a > 0, this.k, this.l, this.m, this.n);
            }
        }

        if (flag1) {
            this.k_();
        }

    }

    private boolean u() {
        if (this.g[0] == null) {
            return false;
        } else {
            OItemStack oitemstack = OFurnaceRecipes.a().b(this.g[0].b().cp);

            return oitemstack == null ? false : (this.g[2] == null ? true : (!this.g[2].a(oitemstack) ? false : (this.g[2].a < this.d() && this.g[2].a < this.g[2].e() ? true : this.g[2].a < oitemstack.e())));
        }
    }

    public void l() {
        if (this.u()) {
            OItemStack oitemstack = OFurnaceRecipes.a().b(this.g[0].b().cp);

            if (this.g[2] == null) {
                this.g[2] = oitemstack.m();
            } else if (this.g[2].c == oitemstack.c) {
                ++this.g[2].a;
            }

            --this.g[0].a;
            if (this.g[0].a <= 0) {
                this.g[0] = null;
            }

        }
    }

    public static int a(OItemStack oitemstack) {
        if (oitemstack == null) {
            return 0;
        } else {
            int i = oitemstack.b().cp;
            OItem oitem = oitemstack.b();

            if (i < 256 && OBlock.r[i] != null) {
                OBlock oblock = OBlock.r[i];

                if (oblock == OBlock.bS) {
                    return 150;
                }

                if (oblock.cO == OMaterial.d) {
                    return 300;
                }
            }

            return oitem instanceof OItemTool && ((OItemTool) oitem).g().equals("WOOD") ? 200 : (oitem instanceof OItemSword && ((OItemSword) oitem).h().equals("WOOD") ? 200 : (oitem instanceof OItemHoe && ((OItemHoe) oitem).g().equals("WOOD") ? 200 : (i == OItem.E.cp ? 100 : (i == OItem.n.cp ? 1600 : (i == OItem.az.cp ? 20000 : (i == OBlock.C.cz ? 100 : (i == OItem.bp.cp ? 2400 : 0)))))));
        }
    }

    public static boolean b(OItemStack oitemstack) {
        return a(oitemstack) > 0;
    }

    public boolean a(OEntityPlayer oentityplayer) {
        return this.k.r(this.l, this.m, this.n) != this ? false : oentityplayer.e((double) this.l + 0.5D, (double) this.m + 0.5D, (double) this.n + 0.5D) <= 64.0D;
    }

    public void f() {}

    public void g() {}

    public boolean b(int i, OItemStack oitemstack) {
        return i == 2 ? false : (i == 1 ? b(oitemstack) : true);
    }

    public int[] c(int i) {
        return i == 0 ? e : (i == 1 ? d : f);
    }

    public boolean a(int i, OItemStack oitemstack, int j) {
        return this.b(i, oitemstack);
    }

    public boolean b(int i, OItemStack oitemstack, int j) {
        return j != 0 || i != 1 || oitemstack.c == OItem.ax.cp;
    }

    @Override
    public OItemStack[] getContents() {
        return Arrays.copyOf(this.g, this.getContentsSize());
    }

    @Override
    public void setContents(OItemStack[] aoitemstack) {
        this.g = Arrays.copyOf(aoitemstack, this.getContentsSize());
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
    public Furnace getComplexBlock() {
        return furnace;
    }

}
