import java.util.Random;

public class OBlockSand extends OBlock {

    public static boolean c = false;

    public OBlockSand(int i) {
        super(i, OMaterial.p);
        this.a(OCreativeTabs.b);
    }

    public OBlockSand(int i, OMaterial omaterial) {
        super(i, omaterial);
    }

    public void a(OWorld oworld, int i, int j, int k) {
        // CanaryMod: Physics
        if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.BLOCK_PHYSICS, oworld.world.getBlockAt(i, j, k), true)) {
            oworld.a(i, j, k, this.cz, this.a(oworld));
        }
    }

    public void a(OWorld oworld, int i, int j, int k, int l) {
        // CanaryMod: Physics
        if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.BLOCK_PHYSICS, oworld.world.getBlockAt(i, j, k), true)) {
            oworld.a(i, j, k, this.cz, this.a(oworld));
        }
    }

    public void a(OWorld oworld, int i, int j, int k, Random random) {
        if (!oworld.I) {
            this.k(oworld, i, j, k);
        }
    }

    private void k(OWorld oworld, int i, int j, int k) {
        if (a_(oworld, i, j - 1, k) && j >= 0) {
            byte b0 = 32;

            if (!c && oworld.e(i - b0, j - b0, k - b0, i + b0, j + b0, k + b0)) {
                if (!oworld.I) {
                    OEntityFallingSand oentityfallingsand = new OEntityFallingSand(oworld, (double) ((float) i + 0.5F), (double) ((float) j + 0.5F), (double) ((float) k + 0.5F), this.cz, oworld.h(i, j, k));

                    this.a(oentityfallingsand);
                    oworld.d((OEntity) oentityfallingsand);
                }
            } else {
                oworld.i(i, j, k);

                while (a_(oworld, i, j - 1, k) && j > 0) {
                    --j;
                }

                if (j > 0) {
                    oworld.c(i, j, k, this.cz);
                }
            }
        }
    }

    protected void a(OEntityFallingSand oentityfallingsand) {}

    public int a(OWorld oworld) {
        return 2;
    }

    public static boolean a_(OWorld oworld, int i, int j, int k) {
        int l = oworld.a(i, j, k);

        if (l == 0) {
            return true;
        } else if (l == OBlock.av.cz) {
            return true;
        } else {
            OMaterial omaterial = OBlock.r[l].cO;

            return omaterial == OMaterial.h ? true : omaterial == OMaterial.i;
        }
    }

    public void a_(OWorld oworld, int i, int j, int k, int l) {}
}
