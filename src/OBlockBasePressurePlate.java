import java.util.Random;

public abstract class OBlockBasePressurePlate extends OBlock {

    private String a;

    protected OBlockBasePressurePlate(int i, String s, OMaterial omaterial) {
        super(i, omaterial);
        this.a = s;
        this.a(OCreativeTabs.d);
        this.b(true);
        this.b_(this.d(15));
    }

    public void a(OIBlockAccess oiblockaccess, int i, int j, int k) {
        this.b_(oiblockaccess.h(i, j, k));
    }

    protected void b_(int i) {
        boolean flag = this.c(i) > 0;
        float f = 0.0625F;

        if (flag) {
            this.a(f, 0.0F, f, 1.0F - f, 0.03125F, 1.0F - f);
        } else {
            this.a(f, 0.0F, f, 1.0F - f, 0.0625F, 1.0F - f);
        }
    }

    public int a(OWorld oworld) {
        return 20;
    }

    public OAxisAlignedBB b(OWorld oworld, int i, int j, int k) {
        return null;
    }

    public boolean c() {
        return false;
    }

    public boolean b() {
        return false;
    }

    public boolean b(OIBlockAccess oiblockaccess, int i, int j, int k) {
        return true;
    }

    public boolean c(OWorld oworld, int i, int j, int k) {
        return oworld.w(i, j - 1, k) || OBlockFence.l_(oworld.a(i, j - 1, k));
    }

    public void a(OWorld oworld, int i, int j, int k, int l) {
        boolean flag = false;

        if (!oworld.w(i, j - 1, k) && !OBlockFence.l_(oworld.a(i, j - 1, k))) {
            flag = true;
        }

        if (flag) {
            this.c(oworld, i, j, k, oworld.h(i, j, k), 0);
            oworld.i(i, j, k);
        }
    }

    public void a(OWorld oworld, int i, int j, int k, Random random) {
        if (!oworld.I) {
            int l = this.c(oworld.h(i, j, k));

            if (l > 0) {
                this.b(oworld, i, j, k, l);
            }
        }
    }

    public void a(OWorld oworld, int i, int j, int k, OEntity oentity) {
        if (!oworld.I) {
            int l = this.c(oworld.h(i, j, k));

            if (l == 0) {
                this.b(oworld, i, j, k, l);
            }
        }
    }

    protected void b(OWorld oworld, int i, int j, int k, int l) {
        int i1 = this.e(oworld, i, j, k);

        // CanaryMod: Allow pressure plate interaction to power redstone
        if (l != i1) {
            i1 = this.d((Integer) etc.getLoader().callHook(PluginLoader.Hook.REDSTONE_CHANGE, new Block(oworld.world, this.cz, i, j, k), l, i1));
        }

        boolean flag = l > 0;
        boolean flag1 = i1 > 0;

        if (l != i1) {
            oworld.b(i, j, k, this.d(i1), 2);
            this.b_(oworld, i, j, k);
            oworld.g(i, j, k, i, j, k);
        }

        if (!flag1 && flag) {
            oworld.a((double) i + 0.5D, (double) j + 0.1D, (double) k + 0.5D, "random.click", 0.3F, 0.5F);
        } else if (flag1 && !flag) {
            oworld.a((double) i + 0.5D, (double) j + 0.1D, (double) k + 0.5D, "random.click", 0.3F, 0.6F);
        }

        if (flag1) {
            oworld.a(i, j, k, this.cz, this.a(oworld));
        }
    }

    protected OAxisAlignedBB a(int i, int j, int k) {
        float f = 0.125F;

        return OAxisAlignedBB.a().a((double) ((float) i + f), (double) j, (double) ((float) k + f), (double) ((float) (i + 1) - f), (double) j + 0.25D, (double) ((float) (k + 1) - f));
    }

    public void a(OWorld oworld, int i, int j, int k, int l, int i1) {
        if (this.c(i1) > 0) {
            this.b_(oworld, i, j, k);
        }

        super.a(oworld, i, j, k, l, i1);
    }

    protected void b_(OWorld oworld, int i, int j, int k) {
        oworld.f(i, j, k, this.cz);
        oworld.f(i, j - 1, k, this.cz);
    }

    public int b(OIBlockAccess oiblockaccess, int i, int j, int k, int l) {
        return this.c(oiblockaccess.h(i, j, k));
    }

    public int c(OIBlockAccess oiblockaccess, int i, int j, int k, int l) {
        return l == 1 ? this.c(oiblockaccess.h(i, j, k)) : 0;
    }

    public boolean f() {
        return true;
    }

    public void g() {
        float f = 0.5F;
        float f1 = 0.125F;
        float f2 = 0.5F;

        this.a(0.5F - f, 0.5F - f1, 0.5F - f2, 0.5F + f, 0.5F + f1, 0.5F + f2);
    }

    public int h() {
        return 1;
    }

    protected abstract int e(OWorld oworld, int i, int j, int k);

    protected abstract int c(int i);

    protected abstract int d(int i);
}
