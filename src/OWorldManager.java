import java.util.Iterator;

public class OWorldManager implements OIWorldAccess {

    private OMinecraftServer a;
    public OWorldServer b; // CanaryMod private -> public

    public OWorldManager(OMinecraftServer ominecraftserver, OWorldServer oworldserver) {
        this.a = ominecraftserver;
        this.b = oworldserver;
    }

    public void a(String s, double d0, double d1, double d2, double d3, double d4, double d5) {}

    public void a(OEntity oentity) {
        this.b.p().a(oentity);
    }

    public void b(OEntity oentity) {
        this.b.p().b(oentity);
    }

    public void a(String s, double d0, double d1, double d2, float f, float f1) {
        this.a.ad().a(d0, d1, d2, f > 1.0F ? (double) (16.0F * f) : 16.0D, this.b.t.h, new OPacket62LevelSound(s, d0, d1, d2, f, f1), this.b.name); // CanaryMod: pass world name
    }

    public void a(OEntityPlayer oentityplayer, String s, double d0, double d1, double d2, float f, float f1) {
        this.a.ad().a(oentityplayer, d0, d1, d2, f > 1.0F ? (double) (16.0F * f) : 16.0D, this.b.t.h, new OPacket62LevelSound(s, d0, d1, d2, f, f1), this.b.name); // CanaryMod: pass world name
    }

    public void a(int i, int j, int k, int l, int i1, int j1) {}

    public void a(int i, int j, int k) {
        this.b.r().a(i, j, k);
    }

    public void b(int i, int j, int k) {}

    public void a(String s, int i, int j, int k) {}

    public void a(OEntityPlayer oentityplayer, int i, int j, int k, int l, int i1) {
        this.a.ad().a(oentityplayer, (double) j, (double) k, (double) l, 64.0D, this.b.t.h, new OPacket61DoorChange(i, j, k, l, i1, false), this.b.name); // CanaryMod: pass world name
    }

    public void a(int i, int j, int k, int l, int i1) {
        this.a.ad().a((OPacket) (new OPacket61DoorChange(i, j, k, l, i1, true)));
    }

    public void b(int i, int j, int k, int l, int i1) {
        Iterator iterator = this.a.ad().a.iterator();

        while (iterator.hasNext()) {
            OEntityPlayerMP oentityplayermp = (OEntityPlayerMP) iterator.next();

            if (oentityplayermp != null && oentityplayermp.q == this.b && oentityplayermp.k != i) {
                double d0 = (double) j - oentityplayermp.u;
                double d1 = (double) k - oentityplayermp.v;
                double d2 = (double) l - oentityplayermp.w;

                if (d0 * d0 + d1 * d1 + d2 * d2 < 1024.0D) {
                    oentityplayermp.a.b(new OPacket55BlockDestroy(i, j, k, l, i1));
                }
            }
        }
    }
}
