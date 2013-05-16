import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

public class OTileEntity {

    private static Map a = new HashMap();
    private static Map b = new HashMap();
    protected OWorld k;
    public int l;
    public int m;
    public int n;
    protected boolean o;
    public int p = -1;
    public OBlock q;
    NBTTagCompound metadata = new NBTTagCompound("Canary"); // Canary metadata

    public OTileEntity() {}

    private static void a(Class oclass, String s) {
        if (a.containsKey(s)) {
            throw new IllegalArgumentException("Duplicate id: " + s);
        } else {
            a.put(s, oclass);
            b.put(oclass, s);
        }
    }

    public OWorld az() {
        return this.k;
    }

    public void b(OWorld oworld) {
        this.k = oworld;
    }

    public boolean o() {
        return this.k != null;
    }

    public void a(ONBTTagCompound onbttagcompound) {
        this.l = onbttagcompound.e("x");
        this.m = onbttagcompound.e("y");
        this.n = onbttagcompound.e("z");
        if(onbttagcompound.b("Canary")) {
            this.metadata = new NBTTagCompound(onbttagcompound.l("Canary"));
        }
    }

    public void b(ONBTTagCompound onbttagcompound) {
        String s = (String) b.get(this.getClass());

        if (s == null) {
            throw new RuntimeException(this.getClass() + " is missing a mapping! This is a bug!");
        } else {
            onbttagcompound.a("id", s);
            onbttagcompound.a("x", this.l);
            onbttagcompound.a("y", this.m);
            onbttagcompound.a("z", this.n);
            onbttagcompound.a("Canary", metadata.getBaseTag());
        }
    }

    public void h() {}

    public static OTileEntity c(ONBTTagCompound onbttagcompound) {
        OTileEntity otileentity = null;

        try {
            Class oclass = (Class) a.get(onbttagcompound.i("id"));

            if (oclass != null) {
                otileentity = (OTileEntity) oclass.newInstance();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        if (otileentity != null) {
            otileentity.a(onbttagcompound);
        } else {
            OMinecraftServer.D().al().b("Skipping TileEntity with id " + onbttagcompound.i("id"));
        }

        return otileentity;
    }

    public int p() {
        if (this.p == -1) {
            this.p = this.k.h(this.l, this.m, this.n);
        }

        return this.p;
    }

    public void k_() {
        if (this.k != null) {
            this.p = this.k.h(this.l, this.m, this.n);
            this.k.b(this.l, this.m, this.n, this);
            if (this.q() != null) {
                this.k.m(this.l, this.m, this.n, this.q().cz);
            }
        }
    }

    public OBlock q() {
        if (this.q == null) {
            this.q = OBlock.r[this.k.a(this.l, this.m, this.n)];
        }

        return this.q;
    }

    public OPacket m() {
        return null;
    }

    public boolean r() {
        return this.o;
    }

    public void w_() {
        this.o = true;
    }

    public void s() {
        this.o = false;
    }

    public boolean b(int i, int j) {
        return false;
    }

    public void i() {
        this.q = null;
        this.p = -1;
    }

    public void a(OCrashReportCategory ocrashreportcategory) {
        ocrashreportcategory.a("Name", (Callable) (new OCallableTileEntityName(this)));
        OCrashReportCategory.a(ocrashreportcategory, this.l, this.m, this.n, this.q().cz, this.p());
        ocrashreportcategory.a("Actual block type", (Callable) (new OCallableTileEntityID(this)));
        ocrashreportcategory.a("Actual block data value", (Callable) (new OCallableTileEntityData(this)));
    }

    static Map t() {
        return b;
    }

    /**
     * Returns the CanaryMod abstraction of this <tt>TileEntity</tt>.
     * @return the <tt>ComplexBlock</tt> of this <tt>TileEntity</tt>
     */
    public ComplexBlock getComplexBlock() {
        return null;
    }

    static {
        a(OTileEntityFurnace.class, "Furnace");
        a(OTileEntityChest.class, "Chest");
        a(OTileEntityEnderChest.class, "EnderChest");
        a(OTileEntityRecordPlayer.class, "RecordPlayer");
        a(OTileEntityDispenser.class, "Trap");
        a(OTileEntityDropper.class, "Dropper");
        a(OTileEntitySign.class, "Sign");
        a(OTileEntityMobSpawner.class, "MobSpawner");
        a(OTileEntityNote.class, "Music");
        a(OTileEntityPiston.class, "Piston");
        a(OTileEntityBrewingStand.class, "Cauldron");
        a(OTileEntityEnchantmentTable.class, "EnchantTable");
        a(OTileEntityEndPortal.class, "Airportal");
        a(OTileEntityCommandBlock.class, "Control");
        a(OTileEntityBeacon.class, "Beacon");
        a(OTileEntitySkull.class, "Skull");
        a(OTileEntityDaylightDetector.class, "DLDetector");
        a(OTileEntityHopper.class, "Hopper");
        a(OTileEntityComparator.class, "Comparator");
    }
}
