import java.util.List;
import java.util.Random;

public abstract class OBlockButton extends OBlock {

    private final boolean a;

    protected OBlockButton(int i, boolean flag) {
        super(i, OMaterial.q);
        this.b(true);
        this.a(OCreativeTabs.d);
        this.a = flag;
    }

    public OAxisAlignedBB b(OWorld oworld, int i, int j, int k) {
        return null;
    }

    public int a(OWorld oworld) {
        return this.a ? 30 : 20;
    }

    public boolean c() {
        return false;
    }

    public boolean b() {
        return false;
    }

    public boolean c(OWorld oworld, int i, int j, int k, int l) {
        return l == 2 && oworld.u(i, j, k + 1) ? true : (l == 3 && oworld.u(i, j, k - 1) ? true : (l == 4 && oworld.u(i + 1, j, k) ? true : l == 5 && oworld.u(i - 1, j, k)));
    }

    public boolean c(OWorld oworld, int i, int j, int k) {
        return oworld.u(i - 1, j, k) ? true : (oworld.u(i + 1, j, k) ? true : (oworld.u(i, j, k - 1) ? true : oworld.u(i, j, k + 1)));
    }

    public int a(OWorld oworld, int i, int j, int k, int l, float f, float f1, float f2, int i1) {
        int j1 = oworld.h(i, j, k);
        int k1 = j1 & 8;

        j1 &= 7;
        if (l == 2 && oworld.u(i, j, k + 1)) {
            j1 = 4;
        } else if (l == 3 && oworld.u(i, j, k - 1)) {
            j1 = 3;
        } else if (l == 4 && oworld.u(i + 1, j, k)) {
            j1 = 2;
        } else if (l == 5 && oworld.u(i - 1, j, k)) {
            j1 = 1;
        } else {
            j1 = this.k(oworld, i, j, k);
        }

        return j1 + k1;
    }

    private int k(OWorld oworld, int i, int j, int k) {
        return oworld.u(i - 1, j, k) ? 1 : (oworld.u(i + 1, j, k) ? 2 : (oworld.u(i, j, k - 1) ? 3 : (oworld.u(i, j, k + 1) ? 4 : 1)));
    }

    public void a(OWorld oworld, int i, int j, int k, int l) {
        if (this.m(oworld, i, j, k)) {
            int i1 = oworld.h(i, j, k) & 7;
            boolean flag = false;

            if (!oworld.u(i - 1, j, k) && i1 == 1) {
                flag = true;
            }

            if (!oworld.u(i + 1, j, k) && i1 == 2) {
                flag = true;
            }

            if (!oworld.u(i, j, k - 1) && i1 == 3) {
                flag = true;
            }

            if (!oworld.u(i, j, k + 1) && i1 == 4) {
                flag = true;
            }

            if (flag) {
                this.c(oworld, i, j, k, oworld.h(i, j, k), 0);
                oworld.i(i, j, k);
            }
        }
    }

    private boolean m(OWorld oworld, int i, int j, int k) {
        if (!this.c(oworld, i, j, k)) {
            this.c(oworld, i, j, k, oworld.h(i, j, k), 0);
            oworld.i(i, j, k);
            return false;
        } else {
            return true;
        }
    }

    public void a(OIBlockAccess oiblockaccess, int i, int j, int k) {
        int l = oiblockaccess.h(i, j, k);

        this.d(l);
    }

    private void d(int i) {
        int j = i & 7;
        boolean flag = (i & 8) > 0;
        float f = 0.375F;
        float f1 = 0.625F;
        float f2 = 0.1875F;
        float f3 = 0.125F;

        if (flag) {
            f3 = 0.0625F;
        }

        if (j == 1) {
            this.a(0.0F, f, 0.5F - f2, f3, f1, 0.5F + f2);
        } else if (j == 2) {
            this.a(1.0F - f3, f, 0.5F - f2, 1.0F, f1, 0.5F + f2);
        } else if (j == 3) {
            this.a(0.5F - f2, f, 0.0F, 0.5F + f2, f1, f3);
        } else if (j == 4) {
            this.a(0.5F - f2, f, 1.0F - f3, 0.5F + f2, f1, 1.0F);
        }
    }

    public void a(OWorld oworld, int i, int j, int k, OEntityPlayer oentityplayer) {}

    public boolean a(OWorld oworld, int i, int j, int k, OEntityPlayer oentityplayer, int l, float f, float f1, float f2) {
        int i1 = oworld.h(i, j, k);
        int j1 = i1 & 7;
        int k1 = 8 - (i1 & 8);

        if (k1 == 0) {
            return true;
        }

        // CanaryMod: Allow button to provide power
        int change = (Integer) etc.getLoader().callHook(PluginLoader.Hook.REDSTONE_CHANGE, oworld.world.getBlockAt(i, j, k), 0, 1);

        if (change == 0) {
            return true;
        } else {
            oworld.b(i, j, k, j1 + k1, 3);
            oworld.g(i, j, k, i, j, k);
            oworld.a((double) i + 0.5D, (double) j + 0.5D, (double) k + 0.5D, "random.click", 0.3F, 0.6F);
            this.d(oworld, i, j, k, j1);
            oworld.a(i, j, k, this.cz, this.a(oworld));
            return true;
        }
    }

    public void a(OWorld oworld, int i, int j, int k, int l, int i1) {
        if ((i1 & 8) > 0) {
            int j1 = i1 & 7;

            this.d(oworld, i, j, k, j1);
        }

        super.a(oworld, i, j, k, l, i1);
    }

    public int b(OIBlockAccess oiblockaccess, int i, int j, int k, int l) {
        return (oiblockaccess.h(i, j, k) & 8) > 0 ? 15 : 0;
    }

    public int c(OIBlockAccess oiblockaccess, int i, int j, int k, int l) {
        int i1 = oiblockaccess.h(i, j, k);

        if ((i1 & 8) == 0) {
            return 0;
        } else {
            int j1 = i1 & 7;

            return j1 == 5 && l == 1 ? 15 : (j1 == 4 && l == 2 ? 15 : (j1 == 3 && l == 3 ? 15 : (j1 == 2 && l == 4 ? 15 : (j1 == 1 && l == 5 ? 15 : 0))));
        }
    }

    public boolean f() {
        return true;
    }

    public void a(OWorld oworld, int i, int j, int k, Random random) {
        if (!oworld.I) {
            int l = oworld.h(i, j, k);

            if ((l & 8) != 0) {

                // CanaryMod: Allow button to provide power
                int change = (Integer) etc.getLoader().callHook(PluginLoader.Hook.REDSTONE_CHANGE, oworld.world.getBlockAt(i, j, k), 1, 0);

                if (change > 0) {
                    return;
                }

                if (this.a) {
                    this.n(oworld, i, j, k);
                } else {
                    oworld.b(i, j, k, l & 7, 3);
                    int i1 = l & 7;

                    this.d(oworld, i, j, k, i1);
                    oworld.a((double) i + 0.5D, (double) j + 0.5D, (double) k + 0.5D, "random.click", 0.3F, 0.5F);
                    oworld.g(i, j, k, i, j, k);
                }
            }
        }
    }

    public void g() {
        float f = 0.1875F;
        float f1 = 0.125F;
        float f2 = 0.125F;

        this.a(0.5F - f, 0.5F - f1, 0.5F - f2, 0.5F + f, 0.5F + f1, 0.5F + f2);
    }

    public void a(OWorld oworld, int i, int j, int k, OEntity oentity) {
        if (!oworld.I) {
            if (this.a) {
                if ((oworld.h(i, j, k) & 8) == 0) {
                    this.n(oworld, i, j, k);
                }
            }
        }
    }

    private void n(OWorld oworld, int i, int j, int k) {
        int l = oworld.h(i, j, k);
        int i1 = l & 7;
        boolean flag = (l & 8) != 0;

        this.d(l);
        List list = oworld.a(OEntityArrow.class, OAxisAlignedBB.a().a((double) i + this.cG, (double) j + this.cH, (double) k + this.cI, (double) i + this.cJ, (double) j + this.cK, (double) k + this.cL));
        boolean flag1 = !list.isEmpty();

        if (flag1 && !flag) {
            oworld.b(i, j, k, i1 | 8, 3);
            this.d(oworld, i, j, k, i1);
            oworld.g(i, j, k, i, j, k);
            oworld.a((double) i + 0.5D, (double) j + 0.5D, (double) k + 0.5D, "random.click", 0.3F, 0.6F);
        }

        if (!flag1 && flag) {
            oworld.b(i, j, k, i1, 3);
            this.d(oworld, i, j, k, i1);
            oworld.g(i, j, k, i, j, k);
            oworld.a((double) i + 0.5D, (double) j + 0.5D, (double) k + 0.5D, "random.click", 0.3F, 0.5F);
        }

        if (flag1) {
            oworld.a(i, j, k, this.cz, this.a(oworld));
        }
    }

    private void d(OWorld oworld, int i, int j, int k, int l) {
        oworld.f(i, j, k, this.cz);
        if (l == 1) {
            oworld.f(i - 1, j, k, this.cz);
        } else if (l == 2) {
            oworld.f(i + 1, j, k, this.cz);
        } else if (l == 3) {
            oworld.f(i, j, k - 1, this.cz);
        } else if (l == 4) {
            oworld.f(i, j, k + 1, this.cz);
        } else {
            oworld.f(i, j - 1, k, this.cz);
        }
    }
}
