public class OBlockLever extends OBlock {

    protected OBlockLever(int i) {
        super(i, OMaterial.q);
        this.a(OCreativeTabs.d);
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

    public int d() {
        return 12;
    }

    public boolean c(OWorld oworld, int i, int j, int k, int l) {
        return l == 0 && oworld.u(i, j + 1, k) ? true : (l == 1 && oworld.w(i, j - 1, k) ? true : (l == 2 && oworld.u(i, j, k + 1) ? true : (l == 3 && oworld.u(i, j, k - 1) ? true : (l == 4 && oworld.u(i + 1, j, k) ? true : l == 5 && oworld.u(i - 1, j, k)))));
    }

    public boolean c(OWorld oworld, int i, int j, int k) {
        return oworld.u(i - 1, j, k) ? true : (oworld.u(i + 1, j, k) ? true : (oworld.u(i, j, k - 1) ? true : (oworld.u(i, j, k + 1) ? true : (oworld.w(i, j - 1, k) ? true : oworld.u(i, j + 1, k)))));
    }

    public int a(OWorld oworld, int i, int j, int k, int l, float f, float f1, float f2, int i1) {
        int j1 = i1 & 8;
        int k1 = i1 & 7;
        byte b0 = -1;

        if (l == 0 && oworld.u(i, j + 1, k)) {
            b0 = 0;
        }

        if (l == 1 && oworld.w(i, j - 1, k)) {
            b0 = 5;
        }

        if (l == 2 && oworld.u(i, j, k + 1)) {
            b0 = 4;
        }

        if (l == 3 && oworld.u(i, j, k - 1)) {
            b0 = 3;
        }

        if (l == 4 && oworld.u(i + 1, j, k)) {
            b0 = 2;
        }

        if (l == 5 && oworld.u(i - 1, j, k)) {
            b0 = 1;
        }

        return b0 + j1;
    }

    public void a(OWorld oworld, int i, int j, int k, OEntityLiving oentityliving, OItemStack oitemstack) {
        int l = oworld.h(i, j, k);
        int i1 = l & 7;
        int j1 = l & 8;

        if (i1 == d(1)) {
            if ((OMathHelper.c((double) (oentityliving.A * 4.0F / 360.0F) + 0.5D) & 1) == 0) {
                oworld.b(i, j, k, 5 | j1, 2);
            } else {
                oworld.b(i, j, k, 6 | j1, 2);
            }
        } else if (i1 == d(0)) {
            if ((OMathHelper.c((double) (oentityliving.A * 4.0F / 360.0F) + 0.5D) & 1) == 0) {
                oworld.b(i, j, k, 7 | j1, 2);
            } else {
                oworld.b(i, j, k, 0 | j1, 2);
            }
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
        if (this.k(oworld, i, j, k)) {
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

            if (!oworld.w(i, j - 1, k) && i1 == 5) {
                flag = true;
            }

            if (!oworld.w(i, j - 1, k) && i1 == 6) {
                flag = true;
            }

            if (!oworld.u(i, j + 1, k) && i1 == 0) {
                flag = true;
            }

            if (!oworld.u(i, j + 1, k) && i1 == 7) {
                flag = true;
            }

            if (flag) {
                this.c(oworld, i, j, k, oworld.h(i, j, k), 0);
                oworld.i(i, j, k);
            }
        }
    }

    private boolean k(OWorld oworld, int i, int j, int k) {
        if (!this.c(oworld, i, j, k)) {
            this.c(oworld, i, j, k, oworld.h(i, j, k), 0);
            oworld.i(i, j, k);
            return false;
        } else {
            return true;
        }
    }

    public void a(OIBlockAccess oiblockaccess, int i, int j, int k) {
        int l = oiblockaccess.h(i, j, k) & 7;
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

    public boolean a(OWorld oworld, int i, int j, int k, OEntityPlayer oentityplayer, int l, float f, float f1, float f2) {
        if (oworld.I) {
            return true;
        } else {
            int i1 = oworld.h(i, j, k);
            int j1 = i1 & 7;
            int k1 = 8 - (i1 & 8);
            // CanaryMod: Allow the lever to change the current first 3 bits are for postion 4th bit is for power. (on / off)
            int old = (k1 != 8) ? 1 : 0;
            int current = (k1 == 8) ? 1 : 0;

            current = ((Integer) etc.getLoader().callHook(PluginLoader.Hook.REDSTONE_CHANGE, new Block(oworld.world, this.cz, i, j, k), old, current)).intValue();
            k1 = (current > 0) ? 8 : 0;

            oworld.b(i, j, k, j1 + k1, 3);
            oworld.a((double) i + 0.5D, (double) j + 0.5D, (double) k + 0.5D, "random.click", 0.3F, k1 > 0 ? 0.6F : 0.5F);
            oworld.f(i, j, k, this.cz);
            if (j1 == 1) {
                oworld.f(i - 1, j, k, this.cz);
            } else if (j1 == 2) {
                oworld.f(i + 1, j, k, this.cz);
            } else if (j1 == 3) {
                oworld.f(i, j, k - 1, this.cz);
            } else if (j1 == 4) {
                oworld.f(i, j, k + 1, this.cz);
            } else if (j1 != 5 && j1 != 6) {
                if (j1 == 0 || j1 == 7) {
                    oworld.f(i, j + 1, k, this.cz);
                }
            } else {
                oworld.f(i, j - 1, k, this.cz);
            }

            return true;
        }
    }

    public void a(OWorld oworld, int i, int j, int k, int l, int i1) {
        if ((i1 & 8) > 0) {
            oworld.f(i, j, k, this.cz);
            int j1 = i1 & 7;

            if (j1 == 1) {
                oworld.f(i - 1, j, k, this.cz);
            } else if (j1 == 2) {
                oworld.f(i + 1, j, k, this.cz);
            } else if (j1 == 3) {
                oworld.f(i, j, k - 1, this.cz);
            } else if (j1 == 4) {
                oworld.f(i, j, k + 1, this.cz);
            } else if (j1 != 5 && j1 != 6) {
                if (j1 == 0 || j1 == 7) {
                    oworld.f(i, j + 1, k, this.cz);
                }
            } else {
                oworld.f(i, j - 1, k, this.cz);
            }
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

            return j1 == 0 && l == 0 ? 15 : (j1 == 7 && l == 0 ? 15 : (j1 == 6 && l == 1 ? 15 : (j1 == 5 && l == 1 ? 15 : (j1 == 4 && l == 2 ? 15 : (j1 == 3 && l == 3 ? 15 : (j1 == 2 && l == 4 ? 15 : (j1 == 1 && l == 5 ? 15 : 0)))))));
        }
    }

    public boolean f() {
        return true;
    }
}
