import java.util.Random;

public class OBlockStationary extends OBlockFluid {

    protected OBlockStationary(int i, OMaterial omaterial) {
        super(i, omaterial);
        this.b(false);
        if (omaterial == OMaterial.i) {
            this.b(true);
        }
    }

    public boolean b(OIBlockAccess oiblockaccess, int i, int j, int k) {
        return this.cO != OMaterial.i;
    }

    public void a(OWorld oworld, int i, int j, int k, int l) {
        super.a(oworld, i, j, k, l);
        if (oworld.a(i, j, k) == this.cz) {
            this.k(oworld, i, j, k);
        }
    }

    private void k(OWorld oworld, int i, int j, int k) {
        int l = oworld.h(i, j, k);

        oworld.f(i, j, k, this.cz - 1, l, 2);
        oworld.a(i, j, k, this.cz - 1, this.a(oworld));
    }

    public void a(OWorld oworld, int i, int j, int k, Random random) {
        if (this.cO == OMaterial.i) {
            int l = random.nextInt(3);

            // CanaryMod: prevent lava from putting something on fire.
            World world = oworld.world;
            Block block = new Block(world, world.getBlockIdAt(i, j, k), i, j, k);

            block.setStatus(1);
            if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.IGNITE, block, null)) {
                return;
            }

            int i1;
            int j1;

            for (i1 = 0; i1 < l; ++i1) {
                i += random.nextInt(3) - 1;
                ++j;
                k += random.nextInt(3) - 1;
                j1 = oworld.a(i, j, k);
                if (j1 == 0) {
                    if (this.m(oworld, i - 1, j, k) || this.m(oworld, i + 1, j, k) || this.m(oworld, i, j, k - 1) || this.m(oworld, i, j, k + 1) || this.m(oworld, i, j - 1, k) || this.m(oworld, i, j + 1, k)) {
                        oworld.c(i, j, k, OBlock.av.cz);
                        return;
                    }
                } else if (OBlock.r[j1].cO.c()) {
                    return;
                }
            }

            if (l == 0) {
                i1 = i;
                j1 = k;

                for (int k1 = 0; k1 < 3; ++k1) {
                    i = i1 + random.nextInt(3) - 1;
                    k = j1 + random.nextInt(3) - 1;
                    if (oworld.c(i, j + 1, k) && this.m(oworld, i, j, k)) {
                        oworld.c(i, j + 1, k, OBlock.av.cz);
                    }
                }
            }
        }
    }

    private boolean m(OWorld oworld, int i, int j, int k) {
        return oworld.g(i, j, k).h();
    }
}
