import java.util.List;
import java.util.Random;


public class OBlockEndPortal extends OBlockContainer {

    public static boolean a = false;

    protected OBlockEndPortal(int i, OMaterial omaterial) {
        super(i, 0, omaterial);
        this.a(1.0F);
    }

    public OTileEntity a(OWorld oworld) {
        return new OTileEntityEndPortal();
    }

    public void a(OIBlockAccess oiblockaccess, int i, int j, int k) {
        float f = 0.0625F;

        this.a(0.0F, 0.0F, 0.0F, 1.0F, f, 1.0F);
    }

    public void a(OWorld oworld, int i, int j, int k, OAxisAlignedBB oaxisalignedbb, List list, OEntity oentity) {}

    public boolean c() {
        return false;
    }

    public boolean b() {
        return false;
    }

    public int a(Random random) {
        return 0;
    }

    public void a(OWorld oworld, int i, int j, int k, OEntity oentity) {
        if (oentity.o == null && oentity.n == null && !oworld.J) {
            // CanaryMod: Check if end is enabled
            if (etc.getInstance().isEndEnabled()) {
                oentity.b(1);
            }
        }
    }

    public int d() {
        return -1;
    }

    public void g(OWorld oworld, int i, int j, int k) {
        if (!a) {
            if (oworld.v.h != 0) {
                oworld.e(i, j, k, 0);
            }
        }
    }
}
