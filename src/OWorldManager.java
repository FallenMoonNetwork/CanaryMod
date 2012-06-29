
public class OWorldManager implements OIWorldAccess {

    private OMinecraftServer a;
    public OWorldServer b; //CanaryMod private -> public

    public OWorldManager(OMinecraftServer var1, OWorldServer var2) {
        super();
        this.a = var1;
        this.b = var2;
    }

    public void a(String var1, double var2, double var4, double var6, double var8, double var10, double var12) {}

    public void a(OEntity var1) {
        if(this.b.name.equals(var1.bi.name)) {
            this.b.getEntityTracker().trackEntity(var1);
        }
    }

    public void b(OEntity var1) {
        if(this.b.name.equals(var1.bi.name)) {
            this.b.getEntityTracker().untrackEntity(var1);
        }
    }

    public void a(String var1, double var2, double var4, double var6, float var8, float var9) {}

    public void a(int var1, int var2, int var3, int var4, int var5, int var6) {}

    public void a(int var1, int var2, int var3) {
        this.a.h.markBlockNeedsUpdate(var1, var2, var3, this.b.t.g, this.b.name);
    }

    public void b(int var1, int var2, int var3) {}

    public void a(String var1, int var2, int var3, int var4) {}

    public void a(int var1, int var2, int var3, OTileEntity var4) {
        this.a.h.a(var1, var2, var3, var4);
    }

    public void a(OEntityPlayer var1, int var2, int var3, int var4, int var5, int var6) {
        //canatyMod: Fix for block particle spawning cross-worlds
        this.a.h.a(var1, (double) var3, (double) var4, (double) var5, 64.0D, this.b.t.g, new OPacket61DoorChange(var2, var3, var4, var5, var6), b.name);
    }
}
