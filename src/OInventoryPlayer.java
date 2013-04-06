import java.util.concurrent.Callable;

public class OInventoryPlayer implements OIInventory, Container<OItemStack> {

    public OItemStack[] a = new OItemStack[36];
    public OItemStack[] b = new OItemStack[4];
    public int c = 0;
    public OEntityPlayer d;
    private OItemStack g;
    public boolean e = false;
    // CanaryMod
    private String name = "container.inventory";

    public OInventoryPlayer(OEntityPlayer oentityplayer) {
        this.d = oentityplayer;
    }

    public OItemStack h() {
        return this.c < 9 && this.c >= 0 ? this.a[this.c] : null;
    }

    public static int i() {
        return 9;
    }

    private int h(int i) {
        for (int j = 0; j < this.a.length; ++j) {
            if (this.a[j] != null && this.a[j].c == i) {
                return j;
            }
        }

        return -1;
    }

    private int d(OItemStack oitemstack) {
        for (int i = 0; i < this.a.length; ++i) {
            if (this.a[i] != null && this.a[i].c == oitemstack.c && this.a[i].f() && this.a[i].a < this.a[i].e() && this.a[i].a < this.d() && (!this.a[i].h() || this.a[i].k() == oitemstack.k()) && OItemStack.a(this.a[i], oitemstack)) {
                return i;
            }
        }

        return -1;
    }

    public int j() {
        for (int i = 0; i < this.a.length; ++i) {
            if (this.a[i] == null) {
                return i;
            }
        }

        return -1;
    }

    public int b(int i, int j) {
        int k = 0;

        int l;
        OItemStack oitemstack;

        for (l = 0; l < this.a.length; ++l) {
            oitemstack = this.a[l];
            if (oitemstack != null && (i <= -1 || oitemstack.c == i) && (j <= -1 || oitemstack.k() == j)) {
                k += oitemstack.a;
                this.a[l] = null;
            }
        }

        for (l = 0; l < this.b.length; ++l) {
            oitemstack = this.b[l];
            if (oitemstack != null && (i <= -1 || oitemstack.c == i) && (j <= -1 || oitemstack.k() == j)) {
                k += oitemstack.a;
                this.b[l] = null;
            }
        }

        return k;
    }

    private int e(OItemStack oitemstack) {
        int i = oitemstack.c;
        int j = oitemstack.a;
        int k;

        if (oitemstack.e() == 1) {
            k = this.j();
            if (k < 0) {
                return j;
            } else {
                if (this.a[k] == null) {
                    this.a[k] = OItemStack.b(oitemstack);
                }

                return 0;
            }
        } else {
            k = this.d(oitemstack);
            if (k < 0) {
                k = this.j();
            }

            if (k < 0) {
                return j;
            } else {
                if (this.a[k] == null) {
                    this.a[k] = new OItemStack(i, 0, oitemstack.k());
                    if (oitemstack.p()) {
                        this.a[k].d((ONBTTagCompound) oitemstack.q().b());
                    }
                }

                int l = j;

                if (j > this.a[k].e() - this.a[k].a) {
                    l = this.a[k].e() - this.a[k].a;
                }

                if (l > this.d() - this.a[k].a) {
                    l = this.d() - this.a[k].a;
                }

                if (l == 0) {
                    return j;
                } else {
                    j -= l;
                    this.a[k].a += l;
                    this.a[k].b = 5;
                    return j;
                }
            }
        }
    }

    public void k() {
        for (int i = 0; i < this.a.length; ++i) {
            if (this.a[i] != null) {
                this.a[i].a(this.d.q, this.d, i, this.c == i);
            }
        }
    }

    public boolean d(int i) {
        int j = this.h(i);

        if (j < 0) {
            return false;
        } else {
            if (--this.a[j].a <= 0) {
                this.a[j] = null;
            }

            return true;
        }
    }

    public boolean e(int i) {
        int j = this.h(i);

        return j >= 0;
    }

    // CanaryMod: Simulate Pickup (Its the same as a(OItemStack) but without
    // altering the inventory
    public boolean canPickup(OEntityItem oentityitem) {
        OItemStack oitemstack = oentityitem.d();
        int i;

        if (oitemstack.h()) {
            i = this.i();
            if (i >= 0) {
                return !(Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_PICK_UP, ((OEntityPlayerMP) this.d).getPlayer(), oentityitem.item);
            } else if (this.d.ce.d) {
                return true;
            } else {
                return false;
            }
        } else {
            int slot = 0;
            int left = oitemstack.a;

            do {
                OItemStack oitemstack1 = this.a[slot];
                int delta = 0;

                if (oitemstack1 == null) {
                    delta = Math.min(64, left);
                } else if (oitemstack1.a < 64 && oitemstack.c == oitemstack1.c && oitemstack.e() == oitemstack1.e()) {
                    delta = Math.min(64 - oitemstack.a, left);
                }
                left -= delta;
                slot++;
            } while (left > 0 && slot < 36);
            if (oitemstack.a - left > 0) {
                return !(Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_PICK_UP, ((OEntityPlayerMP) this.d).getPlayer(), oentityitem.item);
            } else {
                return false;
            }
        }
    }

    public boolean a(OItemStack oitemstack) {
        if (oitemstack == null) {
            return false;
        } else {
            try {
                int i;

                if (oitemstack.i()) {
                    i = this.j();
                    if (i >= 0) {
                        this.a[i] = OItemStack.b(oitemstack);
                        this.a[i].b = 5;
                        oitemstack.a = 0;
                        return true;
                    } else if (this.d.ce.d) {
                        oitemstack.a = 0;
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    do {
                        i = oitemstack.a;
                        oitemstack.a = this.e(oitemstack);
                    } while (oitemstack.a > 0 && oitemstack.a < i);

                    if (oitemstack.a == i && this.d.ce.d) {
                        oitemstack.a = 0;
                        return true;
                    } else {
                        return oitemstack.a < i;
                    }
                }
            } catch (Throwable throwable) {
                OCrashReport ocrashreport = OCrashReport.a(throwable, "Adding item to inventory");
                OCrashReportCategory ocrashreportcategory = ocrashreport.a("Item being added");

                ocrashreportcategory.a("Item ID", Integer.valueOf(oitemstack.c));
                ocrashreportcategory.a("Item data", Integer.valueOf(oitemstack.k()));
                ocrashreportcategory.a("Item name", (Callable) (new OCallableItemName(this, oitemstack)));
                throw new OReportedException(ocrashreport);
            }
        }
    }

    public OItemStack a(int i, int j) {
        OItemStack[] aoitemstack = this.a;

        if (i >= this.a.length) {
            aoitemstack = this.b;
            i -= this.a.length;
        }

        if (aoitemstack[i] != null) {
            OItemStack oitemstack;

            if (aoitemstack[i].a <= j) {
                oitemstack = aoitemstack[i];
                aoitemstack[i] = null;
                return oitemstack;
            } else {
                oitemstack = aoitemstack[i].a(j);
                if (aoitemstack[i].a == 0) {
                    aoitemstack[i] = null;
                }

                return oitemstack;
            }
        } else {
            return null;
        }
    }

    public OItemStack b(int i) {
        OItemStack[] aoitemstack = this.a;

        if (i >= this.a.length) {
            aoitemstack = this.b;
            i -= this.a.length;
        }

        if (aoitemstack[i] != null) {
            OItemStack oitemstack = aoitemstack[i];

            aoitemstack[i] = null;
            return oitemstack;
        } else {
            return null;
        }
    }

    public void a(int i, OItemStack oitemstack) {
        OItemStack[] aoitemstack = this.a;

        if (i >= aoitemstack.length) {
            i -= aoitemstack.length;
            aoitemstack = this.b;
        }

        aoitemstack[i] = oitemstack;
    }

    public float a(OBlock oblock) {
        float f = 1.0F;

        if (this.a[this.c] != null) {
            f *= this.a[this.c].a(oblock);
        }

        return f;
    }

    public ONBTTagList a(ONBTTagList onbttaglist) {
        int i;
        ONBTTagCompound onbttagcompound;

        for (i = 0; i < this.a.length; ++i) {
            if (this.a[i] != null) {
                onbttagcompound = new ONBTTagCompound();
                onbttagcompound.a("Slot", (byte) i);
                this.a[i].b(onbttagcompound);
                onbttaglist.a((ONBTBase) onbttagcompound);
            }
        }

        for (i = 0; i < this.b.length; ++i) {
            if (this.b[i] != null) {
                onbttagcompound = new ONBTTagCompound();
                onbttagcompound.a("Slot", (byte) (i + 100));
                this.b[i].b(onbttagcompound);
                onbttaglist.a((ONBTBase) onbttagcompound);
            }
        }

        return onbttaglist;
    }

    public void b(ONBTTagList onbttaglist) {
        this.a = new OItemStack[36];
        this.b = new OItemStack[4];

        for (int i = 0; i < onbttaglist.c(); ++i) {
            ONBTTagCompound onbttagcompound = (ONBTTagCompound) onbttaglist.b(i);
            int j = onbttagcompound.c("Slot") & 255;
            OItemStack oitemstack = OItemStack.a(onbttagcompound);

            if (oitemstack != null) {
                if (j >= 0 && j < this.a.length) {
                    this.a[j] = oitemstack;
                }

                if (j >= 100 && j < this.b.length + 100) {
                    this.b[j - 100] = oitemstack;
                }
            }
        }
    }

    public int j_() {
        return this.a.length + 4;
    }

    public OItemStack a(int i) {
        OItemStack[] aoitemstack = this.a;

        if (i >= aoitemstack.length) {
            i -= aoitemstack.length;
            aoitemstack = this.b;
        }

        return aoitemstack[i];
    }

    public String b() {
        return this.name;
    }

    public boolean c() {
        return false;
    }

    public int d() {
        return 64;
    }

    public int a(OEntity oentity) {
        OItemStack oitemstack = this.a(this.c);

        return oitemstack != null ? oitemstack.a(oentity) : 1;
    }

    public boolean b(OBlock oblock) {
        if (oblock.cO.l()) {
            return true;
        } else {
            OItemStack oitemstack = this.a(this.c);

            return oitemstack != null ? oitemstack.b(oblock) : false;
        }
    }

    public OItemStack f(int i) {
        return this.b[i];
    }

    public int l() {
        int i = 0;

        for (int j = 0; j < this.b.length; ++j) {
            if (this.b[j] != null && this.b[j].b() instanceof OItemArmor) {
                int k = ((OItemArmor) this.b[j].b()).c;

                i += k;
            }
        }

        return i;
    }

    public void g(int i) {
        i /= 4;
        if (i < 1) {
            i = 1;
        }

        for (int j = 0; j < this.b.length; ++j) {
            if (this.b[j] != null && this.b[j].b() instanceof OItemArmor) {
                this.b[j].a(i, (OEntityLiving) this.d);
                if (this.b[j].a == 0) {
                    this.b[j] = null;
                }
            }
        }
    }

    public void m() {
        int i;

        for (i = 0; i < this.a.length; ++i) {
            if (this.a[i] != null) {
                this.d.a(this.a[i], true);
                this.a[i] = null;
            }
        }

        for (i = 0; i < this.b.length; ++i) {
            if (this.b[i] != null) {
                this.d.a(this.b[i], true);
                this.b[i] = null;
            }
        }
    }

    public void k_() {
        this.e = true;
    }

    public void b(OItemStack oitemstack) {
        this.g = oitemstack;
    }

    public OItemStack o() {
        return this.g;
    }

    public boolean a(OEntityPlayer oentityplayer) {
        return this.d.M ? false : oentityplayer.e(this.d) <= 64.0D;
    }

    public boolean c(OItemStack oitemstack) {
        int i;

        for (i = 0; i < this.b.length; ++i) {
            if (this.b[i] != null && this.b[i].a(oitemstack)) {
                return true;
            }
        }

        for (i = 0; i < this.a.length; ++i) {
            if (this.a[i] != null && this.a[i].a(oitemstack)) {
                return true;
            }
        }

        return false;
    }

    public void f() {}

    public void g() {}

    public boolean b(int i, OItemStack oitemstack) {
        return true;
    }

    public void b(OInventoryPlayer oinventoryplayer) {
        int i;

        for (i = 0; i < this.a.length; ++i) {
            this.a[i] = OItemStack.b(oinventoryplayer.a[i]);
        }

        for (i = 0; i < this.b.length; ++i) {
            this.b[i] = OItemStack.b(oinventoryplayer.b[i]);
        }

        this.c = oinventoryplayer.c;
    }

    @Override
    public OItemStack[] getContents() {
        return this.a;
    }

    @Override
    public void setContents(OItemStack[] aoitemstack) {
        this.a = aoitemstack;
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
        return this.name;
    }

    @Override
    public void setName(String s) {
        this.name = s;
    }
}
