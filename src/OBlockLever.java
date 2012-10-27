
public class OBlockLever extends OBlock {

    protected OBlockLever(int i, int j) {
        super(i, j, OMaterial.q);
        this.a(OCreativeTabs.d);
    }

    public OAxisAlignedBB e(OWorld oworld, int i, int j, int k) {
        return null;
    }

    public boolean c() {
        return false;
    }

    public boolean b() {
        return false;
    }

    public int d() {
        return 12;
    }

    public boolean b_(OWorld oworld, int i, int j, int k, int l) {
        return l == 0 && oworld.s(i, j + 1, k) ? true : (l == 1 && oworld.t(i, j - 1, k) ? true : (l == 2 && oworld.s(i, j, k + 1) ? true : (l == 3 && oworld.s(i, j, k - 1) ? true : (l == 4 && oworld.s(i + 1, j, k) ? true : l == 5 && oworld.s(i - 1, j, k)))));
    }

    public boolean b(OWorld oworld, int i, int j, int k) {
        return oworld.s(i - 1, j, k) ? true : (oworld.s(i + 1, j, k) ? true : (oworld.s(i, j, k - 1) ? true : (oworld.s(i, j, k + 1) ? true : (oworld.t(i, j - 1, k) ? true : oworld.s(i, j + 1, k)))));
    }

    public void a(OWorld oworld, int i, int j, int k, int l, float f, float f1, float f2) {
        int i1 = oworld.g(i, j, k);
        int j1 = i1 & 8;

        i1 &= 7;
        i1 = -1;
        if (l == 0 && oworld.s(i, j + 1, k)) {
            i1 = oworld.u.nextBoolean() ? 0 : 7;
        }

        if (l == 1 && oworld.t(i, j - 1, k)) {
            i1 = 5 + oworld.u.nextInt(2);
        }

        if (l == 2 && oworld.s(i, j, k + 1)) {
            i1 = 4;
        }

        if (l == 3 && oworld.s(i, j, k - 1)) {
            i1 = 3;
        }

        if (l == 4 && oworld.s(i + 1, j, k)) {
            i1 = 2;
        }

        if (l == 5 && oworld.s(i - 1, j, k)) {
            i1 = 1;
        }

        if (i1 == -1) {
            this.c(oworld, i, j, k, oworld.g(i, j, k), 0);
            oworld.e(i, j, k, 0);
        } else {
            oworld.c(i, j, k, i1 + j1);
        }
    }

    public static int d(int i) {
        switch (i) {
        case 0:
            return 0;

        case 1:
            return 5;

        case 2:
            return 4;

        case 3:
            return 3;

        case 4:
            return 2;

        case 5:
            return 1;

        default:
            return -1;
        }
    }

    public void a(OWorld oworld, int i, int j, int k, int l) {
        if (this.l(oworld, i, j, k)) {
            int i1 = oworld.g(i, j, k) & 7;
            boolean flag = false;

            if (!oworld.s(i - 1, j, k) && i1 == 1) {
                flag = true;
            }

            if (!oworld.s(i + 1, j, k) && i1 == 2) {
                flag = true;
            }

            if (!oworld.s(i, j, k - 1) && i1 == 3) {
                flag = true;
            }

            if (!oworld.s(i, j, k + 1) && i1 == 4) {
                flag = true;
            }

            if (!oworld.t(i, j - 1, k) && i1 == 5) {
                flag = true;
            }

            if (!oworld.t(i, j - 1, k) && i1 == 6) {
                flag = true;
            }

            if (!oworld.s(i, j + 1, k) && i1 == 0) {
                flag = true;
            }

            if (!oworld.s(i, j + 1, k) && i1 == 7) {
                flag = true;
            }

            if (flag) {
                this.c(oworld, i, j, k, oworld.g(i, j, k), 0);
                oworld.e(i, j, k, 0);
            }
        }

    }

    private boolean l(OWorld oworld, int i, int j, int k) {
        if (!this.b(oworld, i, j, k)) {
            this.c(oworld, i, j, k, oworld.g(i, j, k), 0);
            oworld.e(i, j, k, 0);
            return false;
        } else {
            return true;
        }
    }

    public void a(OIBlockAccess oiblockaccess, int i, int j, int k) {
        int l = oiblockaccess.g(i, j, k) & 7;
        float f = 0.1875F;

        if (l == 1) {
            this.a(0.0F, 0.2F, 0.5F - f, f * 2.0F, 0.8F, 0.5F + f);
        } else if (l == 2) {
            this.a(1.0F - f * 2.0F, 0.2F, 0.5F - f, 1.0F, 0.8F, 0.5F + f);
        } else if (l == 3) {
            this.a(0.5F - f, 0.2F, 0.0F, 0.5F + f, 0.8F, f * 2.0F);
        } else if (l == 4) {
            this.a(0.5F - f, 0.2F, 1.0F - f * 2.0F, 0.5F + f, 0.8F, 1.0F);
        } else if (l != 5 && l != 6) {
            if (l == 0 || l == 7) {
                f = 0.25F;
                this.a(0.5F - f, 0.4F, 0.5F - f, 0.5F + f, 1.0F, 0.5F + f);
            }
        } else {
            f = 0.25F;
            this.a(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.6F, 0.5F + f);
        }

    }

    public void a(OWorld oworld, int i, int j, int k, OEntityPlayer oentityplayer) {}

    public boolean a(OWorld oworld, int i, int j, int k, OEntityPlayer oentityplayer, int l, float f, float f1, float f2) {
        if (oworld.J) {
            return true;
        } else {
            int i1 = oworld.g(i, j, k);
            int j1 = i1 & 7;
            int k1 = 8 - (i1 & 8);
            // CanaryMod: Allow the lever to change the current first 3 bits are for postion 4th bit is for power. (on / off)
            int old = (k1 != 8) ? 1 : 0;
            int current = (k1 == 8) ? 1 : 0;

            current = ((Integer) etc.getLoader().callHook(PluginLoader.Hook.REDSTONE_CHANGE, new Block(oworld.world, this.cm, i, j, k), old, current)).intValue();
            k1 = (current > 0) ? 8 : 0;

            oworld.c(i, j, k, j1 + k1);
            oworld.e(i, j, k, i, j, k);
            oworld.a((double) i + 0.5D, (double) j + 0.5D, (double) k + 0.5D, "random.click", 0.3F, k1 > 0 ? 0.6F : 0.5F);
            oworld.h(i, j, k, this.cm);
            if (j1 == 1) {
                oworld.h(i - 1, j, k, this.cm);
            } else if (j1 == 2) {
                oworld.h(i + 1, j, k, this.cm);
            } else if (j1 == 3) {
                oworld.h(i, j, k - 1, this.cm);
            } else if (j1 == 4) {
                oworld.h(i, j, k + 1, this.cm);
            } else if (j1 != 5 && j1 != 6) {
                if (j1 == 0 || j1 == 7) {
                    oworld.h(i, j + 1, k, this.cm);
                }
            } else {
                oworld.h(i, j - 1, k, this.cm);
            }

            return true;
        }
    }

    public void a(OWorld oworld, int i, int j, int k, int l, int i1) {
        if ((i1 & 8) > 0) {
            oworld.h(i, j, k, this.cm);
            int j1 = i1 & 7;

            if (j1 == 1) {
                oworld.h(i - 1, j, k, this.cm);
            } else if (j1 == 2) {
                oworld.h(i + 1, j, k, this.cm);
            } else if (j1 == 3) {
                oworld.h(i, j, k - 1, this.cm);
            } else if (j1 == 4) {
                oworld.h(i, j, k + 1, this.cm);
            } else if (j1 != 5 && j1 != 6) {
                if (j1 == 0 || j1 == 7) {
                    oworld.h(i, j + 1, k, this.cm);
                }
            } else {
                oworld.h(i, j - 1, k, this.cm);
            }
        }

        super.a(oworld, i, j, k, l, i1);
    }

    public boolean b(OIBlockAccess oiblockaccess, int i, int j, int k, int l) {
        return (oiblockaccess.g(i, j, k) & 8) > 0;
    }

    public boolean c(OIBlockAccess oiblockaccess, int i, int j, int k, int l) {
        int i1 = oiblockaccess.g(i, j, k);

        if ((i1 & 8) == 0) {
            return false;
        } else {
            int j1 = i1 & 7;

            return j1 == 0 && l == 0 ? true : (j1 == 7 && l == 0 ? true : (j1 == 6 && l == 1 ? true : (j1 == 5 && l == 1 ? true : (j1 == 4 && l == 2 ? true : (j1 == 3 && l == 3 ? true : (j1 == 2 && l == 4 ? true : j1 == 1 && l == 5))))));
        }
    }

    public boolean i() {
        return true;
    }
}
