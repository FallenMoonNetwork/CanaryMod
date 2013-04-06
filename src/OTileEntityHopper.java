import java.util.Arrays;
import java.util.List;

public class OTileEntityHopper extends OTileEntity implements OHopper, Container<OItemStack> {

    private OItemStack[] a = new OItemStack[5];
    private String b;
    int c = -1; // CanaryMod: private -> package-private

    private final HopperBlock hopper = new HopperBlock(this); // CanaryMod: reference to wrapper

    public OTileEntityHopper() {}

    public void a(ONBTTagCompound onbttagcompound) {
        super.a(onbttagcompound);
        ONBTTagList onbttaglist = onbttagcompound.m("Items");

        this.a = new OItemStack[this.j_()];
        if (onbttagcompound.b("CustomName")) {
            this.b = onbttagcompound.i("CustomName");
        }

        this.c = onbttagcompound.e("TransferCooldown");

        for (int i = 0; i < onbttaglist.c(); ++i) {
            ONBTTagCompound onbttagcompound1 = (ONBTTagCompound) onbttaglist.b(i);
            byte b0 = onbttagcompound1.c("Slot");

            if (b0 >= 0 && b0 < this.a.length) {
                this.a[b0] = OItemStack.a(onbttagcompound1);
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
        onbttagcompound.a("TransferCooldown", this.c);
        if (this.c()) {
            onbttagcompound.a("CustomName", this.b);
        }
    }

    public void k_() {
        super.k_();
    }

    public int j_() {
        return this.a.length;
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
                return oitemstack;
            } else {
                oitemstack = this.a[i].a(j);
                if (this.a[i].a == 0) {
                    this.a[i] = null;
                }

                return oitemstack;
            }
        } else {
            return null;
        }
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

    public void a(int i, OItemStack oitemstack) {
        this.a[i] = oitemstack;
        if (oitemstack != null && oitemstack.a > this.d()) {
            oitemstack.a = this.d();
        }
    }

    public String b() {
        return this.c() ? this.b : "container.hopper";
    }

    public boolean c() {
        return this.b != null && this.b.length() > 0;
    }

    public void a(String s) {
        this.b = s;
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

    public void h() {
        if (this.k != null && !this.k.I) {
            --this.c;
            if (!this.l()) {
                this.c(0);
                this.j();
            }
        }
    }

    public boolean j() {
        if (this.k != null && !this.k.I) {
            if (!this.l() && OBlockHopper.d(this.p())) {
                boolean flag = this.u() | a((OHopper) this);

                if (flag) {
                    this.c(8);
                    this.k_();
                    return true;
                }
            }

            return false;
        } else {
            return false;
        }
    }

    private boolean u() {
        OIInventory oiinventory = this.v();

        if (oiinventory == null) {
            return false;
        } else {
            for (int i = 0; i < this.j_(); ++i) {
                if (this.a(i) != null) {
                    OItemStack oitemstack = this.a(i).m();
                    // CanaryMod: Hopper Transfer hook
                    if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.HOPPER_TRANSFER, hopper, new Item(oitemstack), false)) {
                        return false;
                    } //
                    OItemStack oitemstack1 = a(oiinventory, this.a(i, 1), OFacing.a[OBlockHopper.c(this.p())]);

                    if (oitemstack1 == null || oitemstack1.a == 0) {
                        oiinventory.k_();
                        return true;
                    }

                    this.a(i, oitemstack);
                }
            }

            return false;
        }
    }

    public static boolean a(OHopper ohopper) {
        OIInventory oiinventory = b(ohopper);

        if (oiinventory != null) {
            byte b0 = 0;

            if (oiinventory instanceof OISidedInventory && b0 > -1) {
                OISidedInventory oisidedinventory = (OISidedInventory) oiinventory;
                int[] aint = oisidedinventory.c(b0);

                for (int i = 0; i < aint.length; ++i) {
                    if (a(ohopper, oiinventory, aint[i], b0)) {
                        return true;
                    }
                }
            } else {
                int j = oiinventory.j_();

                for (int k = 0; k < j; ++k) {
                    if (a(ohopper, oiinventory, k, b0)) {
                        return true;
                    }
                }
            }
        } else {
            OEntityItem oentityitem = a(ohopper.az(), ohopper.aA(), ohopper.aB() + 1.0D, ohopper.aC());

            if (oentityitem != null) {
                return a((OIInventory) ohopper, oentityitem);
            }
        }

        return false;
    }

    private static boolean a(OHopper ohopper, OIInventory oiinventory, int i, int j) {
        OItemStack oitemstack = oiinventory.a(i);

        if (oitemstack != null && b(oiinventory, oitemstack, i, j)) {
            OItemStack oitemstack1 = oitemstack.m();
            // CanaryMod: Hopper Transfer hook.
            Hopper hopper = null;
            if (ohopper instanceof OTileEntityHopper) {
                hopper = ((OTileEntityHopper) ohopper).hopper;
            } else if (ohopper instanceof OEntityMinecartHopper) {
                hopper = ((OEntityMinecartHopper) ohopper).hopper;
            }
            if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.HOPPER_TRANSFER, hopper, new Item(oitemstack), true)) {
                return false;
            } //
            OItemStack oitemstack2 = a(ohopper, oiinventory.a(i, 1), -1);

            if (oitemstack2 == null || oitemstack2.a == 0) {
                oiinventory.k_();
                return true;
            }

            oiinventory.a(i, oitemstack1);
        }

        return false;
    }

    public static boolean a(OIInventory oiinventory, OEntityItem oentityitem) {
        boolean flag = false;

        if (oentityitem == null) {
            return false;
        } else {
            OItemStack oitemstack = oentityitem.d().m();
            OItemStack oitemstack1 = a(oiinventory, oitemstack, -1);

            if (oitemstack1 != null && oitemstack1.a != 0) {
                oentityitem.a(oitemstack1);
            } else {
                flag = true;
                oentityitem.w();
            }

            return flag;
        }
    }

    public static OItemStack a(OIInventory oiinventory, OItemStack oitemstack, int i) {
        if (oiinventory instanceof OISidedInventory && i > -1) {
            OISidedInventory oisidedinventory = (OISidedInventory) oiinventory;
            int[] aint = oisidedinventory.c(i);

            for (int j = 0; j < aint.length && oitemstack != null && oitemstack.a > 0; ++j) {
                oitemstack = c(oiinventory, oitemstack, aint[j], i);
            }
        } else {
            int k = oiinventory.j_();

            for (int l = 0; l < k && oitemstack != null && oitemstack.a > 0; ++l) {
                oitemstack = c(oiinventory, oitemstack, l, i);
            }
        }

        if (oitemstack != null && oitemstack.a == 0) {
            oitemstack = null;
        }

        return oitemstack;
    }

    private static boolean a(OIInventory oiinventory, OItemStack oitemstack, int i, int j) {
        return !oiinventory.b(i, oitemstack) ? false : !(oiinventory instanceof OISidedInventory) || ((OISidedInventory) oiinventory).a(i, oitemstack, j);
    }

    private static boolean b(OIInventory oiinventory, OItemStack oitemstack, int i, int j) {
        return !(oiinventory instanceof OISidedInventory) || ((OISidedInventory) oiinventory).b(i, oitemstack, j);
    }

    private static OItemStack c(OIInventory oiinventory, OItemStack oitemstack, int i, int j) {
        OItemStack oitemstack1 = oiinventory.a(i);

        if (a(oiinventory, oitemstack, i, j)) {
            boolean flag = false;

            if (oitemstack1 == null) {
                oiinventory.a(i, oitemstack);
                oitemstack = null;
                flag = true;
            } else if (a(oitemstack1, oitemstack)) {
                int k = oitemstack.e() - oitemstack1.a;
                int l = Math.min(oitemstack.a, k);

                oitemstack.a -= l;
                oitemstack1.a += l;
                flag = l > 0;
            }

            if (flag) {
                if (oiinventory instanceof OTileEntityHopper) {
                    ((OTileEntityHopper) oiinventory).c(8);
                }

                oiinventory.k_();
            }
        }

        return oitemstack;
    }

    private OIInventory v() {
        int i = OBlockHopper.c(this.p());

        return b(this.az(), (double) (this.l + OFacing.b[i]), (double) (this.m + OFacing.c[i]), (double) (this.n + OFacing.d[i]));
    }

    public static OIInventory b(OHopper ohopper) {
        return b(ohopper.az(), ohopper.aA(), ohopper.aB() + 1.0D, ohopper.aC());
    }

    public static OEntityItem a(OWorld oworld, double d0, double d1, double d2) {
        List list = oworld.a(OEntityItem.class, OAxisAlignedBB.a().a(d0, d1, d2, d0 + 1.0D, d1 + 1.0D, d2 + 1.0D), OIEntitySelector.a);

        return list.size() > 0 ? (OEntityItem) list.get(0) : null;
    }

    public static OIInventory b(OWorld oworld, double d0, double d1, double d2) {
        OIInventory oiinventory = null;
        int i = OMathHelper.c(d0);
        int j = OMathHelper.c(d1);
        int k = OMathHelper.c(d2);
        OTileEntity otileentity = oworld.r(i, j, k);

        if (otileentity != null && otileentity instanceof OIInventory) {
            oiinventory = (OIInventory) otileentity;
            if (oiinventory instanceof OTileEntityChest) {
                int l = oworld.a(i, j, k);
                OBlock oblock = OBlock.r[l];

                if (oblock instanceof OBlockChest) {
                    oiinventory = ((OBlockChest) oblock).g_(oworld, i, j, k);
                }
            }
        }

        if (oiinventory == null) {
            List list = oworld.a((OEntity) null, OAxisAlignedBB.a().a(d0, d1, d2, d0 + 1.0D, d1 + 1.0D, d2 + 1.0D), OIEntitySelector.b);

            if (list != null && list.size() > 0) {
                oiinventory = (OIInventory) list.get(oworld.s.nextInt(list.size()));
            }
        }

        return oiinventory;
    }

    private static boolean a(OItemStack oitemstack, OItemStack oitemstack1) {
        return oitemstack.c != oitemstack1.c ? false : (oitemstack.k() != oitemstack1.k() ? false : (oitemstack.a > oitemstack.e() ? false : OItemStack.a(oitemstack, oitemstack1)));
    }

    public double aA() {
        return (double) this.l;
    }

    public double aB() {
        return (double) this.m;
    }

    public double aC() {
        return (double) this.n;
    }

    public void c(int i) {
        this.c = i;
    }

    public boolean l() {
        return this.c > 0;
    }

    @Override
    public OItemStack[] getContents() {
        return Arrays.copyOf(this.a, this.getContentsSize());
    }

    @Override
    public void setContents(OItemStack[] values) {
        this.a = Arrays.copyOf(values, this.getContentsSize());
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
        return this.b();
    }

    @Override
    public void setName(String s) {
        this.a(s);
    }

    public OIInventory getInputInventory(){
        return b(this);
    }

    public OIInventory getOutputInventory(){
        return this.v();
    }

    @Override
    public HopperBlock getComplexBlock() {
        return hopper;
    }
}
