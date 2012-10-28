import java.util.HashMap;
import java.util.Map;


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

    public OTileEntity() {
        super();
    }

    private static void a(Class oclass, String s) {
        if (a.containsKey(s)) {
            throw new IllegalArgumentException("Duplicate id: " + s);
        } else {
            a.put(s, oclass);
            b.put(oclass, s);
        }
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
        }
    }

    public void g() {}

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
            System.out.println("Skipping TileEntity with id " + onbttagcompound.i("id"));
        }

        return otileentity;
    }

    public int p() {
        if (this.p == -1) {
            this.p = this.k.g(this.l, this.m, this.n);
        }

        return this.p;
    }

    public void d() {
        if (this.k != null) {
            this.p = this.k.g(this.l, this.m, this.n);
            this.k.b(this.l, this.m, this.n, this);
        }

    }

    public OBlock q() {
        if (this.q == null) {
            this.q = OBlock.p[this.k.a(this.l, this.m, this.n)];
        }

        return this.q;
    }

    public OPacket l() {
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

    public void b(int i, int j) {}

    public void h() {
        this.q = null;
        this.p = -1;
    }

    static {
        a(OTileEntityFurnace.class, "Furnace");
        a(OTileEntityChest.class, "Chest");
        a(OTileEntityEnderChest.class, "EnderChest");
        a(OTileEntityRecordPlayer.class, "RecordPlayer");
        a(OTileEntityDispenser.class, "Trap");
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
    }
}
