import java.util.Random;


public class OBlockSand extends OBlock {

    public static boolean c = false;

    public OBlockSand(int i, int j) {
        super(i, j, OMaterial.p);
        this.a(OCreativeTabs.b);
    }

    public OBlockSand(int i, int j, OMaterial omaterial) {
        super(i, j, omaterial);
    }

    public void g(OWorld oworld, int i, int j, int k) {
        // CanaryMod: Physics
        if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.BLOCK_PHYSICS, new Block(oworld.world, this.ca, i, j, k), true)) {
        oworld.a(i, j, k, this.cm, this.r_());
        }
    }

    public void a(OWorld oworld, int i, int j, int k, int l) {
        // CanaryMod: Physics
        if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.BLOCK_PHYSICS, new Block(oworld.world, this.ca, i, j, k), true)) {
			oworld.a(i, j, k, this.cm, this.r_());
		}
    }

    public void b(OWorld oworld, int i, int j, int k, Random random) {
        if (!oworld.J) {
            this.l(oworld, i, j, k);
        }
    }

    private void l(OWorld oworld, int i, int j, int k) {
        if (a_(oworld, i, j - 1, k) && j >= 0) {
            byte b0 = 32;

            if (!c && oworld.d(i - b0, j - b0, k - b0, i + b0, j + b0, k + b0)) {
                if (!oworld.J) {
                    OEntityFallingSand oentityfallingsand = new OEntityFallingSand(oworld, (double) ((float) i + 0.5F), (double) ((float) j + 0.5F), (double) ((float) k + 0.5F), this.cm, oworld.g(i, j, k));

                    this.a(oentityfallingsand);
                    oworld.d((OEntity) oentityfallingsand);
                }
            } else {
                oworld.e(i, j, k, 0);

                while (a_(oworld, i, j - 1, k) && j > 0) {
                    --j;
                }

                if (j > 0) {
                    oworld.e(i, j, k, this.cm);
                }
            }
        }

    }

    protected void a(OEntityFallingSand oentityfallingsand) {}

    public int r_() {
        return 3;
    }

    public static boolean a_(OWorld oworld, int i, int j, int k) {
        int l = oworld.a(i, j, k);

        if (l == 0) {
            return true;
        } else if (l == OBlock.au.cm) {
            return true;
        } else {
            OMaterial omaterial = OBlock.p[l].cB;

            return omaterial == OMaterial.h ? true : omaterial == OMaterial.i;
        }
    }

    public void a_(OWorld oworld, int i, int j, int k, int l) {}
	
}
