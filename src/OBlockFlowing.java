import java.util.Random;


public class OBlockFlowing extends OBlockFluid {

    int a = 0;
    boolean[] b = new boolean[4];
    int[] c = new int[4];

    protected OBlockFlowing(int i, OMaterial omaterial) {
        super(i, omaterial);
    }

    private void l(OWorld oworld, int i, int j, int k) {
        int l = oworld.g(i, j, k);

        oworld.c(i, j, k, this.cm + 1, l);
        oworld.e(i, j, k, i, j, k);
    }

    public boolean c(OIBlockAccess oiblockaccess, int i, int j, int k) {
        return this.cB != OMaterial.i;
    }

    public void b(OWorld oworld, int i, int j, int k, Random random) {
        // CanaryMod: Store originating block
        World world = oworld.world;
        Block blockFrom = new Block(world, this.cm, i, j, k);

       int l = this.f_(oworld, i, j, k);
        byte b0 = 1;

        if (this.cB == OMaterial.i && !oworld.v.e) {
            b0 = 2;
        }

        boolean flag = true;
        int i1;

        if (l > 0) {
            byte b1 = -100;

            this.a = 0;
            int j1 = this.d(oworld, i - 1, j, k, b1);

            j1 = this.d(oworld, i + 1, j, k, j1);
            j1 = this.d(oworld, i, j, k - 1, j1);
            j1 = this.d(oworld, i, j, k + 1, j1);
            i1 = j1 + b0;
            if (i1 >= 8 || j1 < 0) {
                i1 = -1;
            }

            if (this.f_(oworld, i, j + 1, k) >= 0) {
                int k1 = this.f_(oworld, i, j + 1, k);

                if (k1 >= 8) {
                    i1 = k1;
                } else {
                    i1 = k1 + 8;
                }
            }

            if (this.a >= 2 && this.cB == OMaterial.h) {
                if (oworld.f(i, j - 1, k).a()) {
                    i1 = 0;
                } else if (oworld.f(i, j - 1, k) == this.cB && oworld.g(i, j, k) == 0) {
                    i1 = 0;
                }
            }

            if (this.cB == OMaterial.i && l < 8 && i1 < 8 && i1 > l && random.nextInt(4) != 0) {
                i1 = l;
                flag = false;
            }

            if (i1 == l) {
                if (flag) {
                    this.l(oworld, i, j, k);
                }
            } else {
                l = i1;
                if (i1 < 0) {
                    oworld.e(i, j, k, 0);
                } else {
                    oworld.c(i, j, k, i1);
                    oworld.a(i, j, k, this.cm, this.r_());
                    oworld.h(i, j, k, this.cm);
                }
            }
        } else {
            this.l(oworld, i, j, k);
        }

        if (this.p(oworld, i, j - 1, k)) {
            if (this.cB == OMaterial.i && oworld.f(i, j - 1, k) == OMaterial.h) {
                oworld.e(i, j - 1, k, OBlock.w.cm);
                this.j(oworld, i, j - 1, k);
                return;
            }
            // CanaryMod: downwards flow.
            Block blockTo = new Block(world, 0, i, j - 1, k);

            if (!((Boolean) etc.getLoader().callHook(PluginLoader.Hook.FLOW, blockFrom, blockTo))) {
                if (l >= 8) {
                this.h(oworld, i, j - 1, k, l);
                } else {
                    this.h(oworld, i, j - 1, k, l + 8);
                }
            }
        } else if (l >= 0 && (l == 0 || this.o(oworld, i, j - 1, k))) {
            boolean[] aboolean = this.n(oworld, i, j, k);

            i1 = l + b0;
            if (l >= 8) {
                i1 = 1;
            }

            if (i1 >= 8) {
                return;
            }

            // CanaryMod: sidewards flow.
            if (aboolean[0]) {
                Block blockTo = new Block(world, 0, i - 1, j, k);

                if (!((Boolean) etc.getLoader().callHook(PluginLoader.Hook.FLOW, blockFrom, blockTo))) {
                    this.h(oworld, i - 1, j, k, i1);
                }
            }

            if (aboolean[1]) {
                Block blockTo = new Block(world, 0, i + 1, j, k);

                if (!((Boolean) etc.getLoader().callHook(PluginLoader.Hook.FLOW, blockFrom, blockTo))) {
                    this.h(oworld, i + 1, j, k, i1);
                }
            }

            if (aboolean[2]) {
                Block blockTo = new Block(world, 0, i, j, k - 1);

                if (!((Boolean) etc.getLoader().callHook(PluginLoader.Hook.FLOW, blockFrom, blockTo))) {
                    this.h(oworld, i, j, k - 1, i1);
                }
            }

            if (aboolean[3]) {
                Block blockTo = new Block(world, 0, i, j, k + 1);

                if (!((Boolean) etc.getLoader().callHook(PluginLoader.Hook.FLOW, blockFrom, blockTo))) {
                this.h(oworld, i, j, k + 1, i1);
                }
            }
        }
    }

    private void h(OWorld oworld, int i, int j, int k, int l) {
        if (this.p(oworld, i, j, k)) {
            int i1 = oworld.a(i, j, k);

            if (i1 > 0) {
                if (this.cB == OMaterial.i) {
                    this.j(oworld, i, j, k);
                } else {
                    OBlock.p[i1].c(oworld, i, j, k, oworld.g(i, j, k), 0);
                }
            }

            oworld.d(i, j, k, this.cm, l);
        }

    }

    private int d(OWorld oworld, int i, int j, int k, int l, int i1) {
        int j1 = 1000;

        for (int k1 = 0; k1 < 4; ++k1) {
            if ((k1 != 0 || i1 != 1) && (k1 != 1 || i1 != 0) && (k1 != 2 || i1 != 3) && (k1 != 3 || i1 != 2)) {
                int l1 = i;
                int i2 = k;

                if (k1 == 0) {
                    l1 = i - 1;
                }

                if (k1 == 1) {
                    ++l1;
                }

                if (k1 == 2) {
                    i2 = k - 1;
                }

                if (k1 == 3) {
                    ++i2;
                }

                if (!this.o(oworld, l1, j, i2) && (oworld.f(l1, j, i2) != this.cB || oworld.g(l1, j, i2) != 0)) {
                    if (!this.o(oworld, l1, j - 1, i2)) {
                        return l;
                    }

                    if (l < 4) {
                        int j2 = this.d(oworld, l1, j, i2, l + 1, k1);

                        if (j2 < j1) {
                            j1 = j2;
                        }
                    }
                }
            }
        }

        return j1;
    }

    private boolean[] n(OWorld oworld, int i, int j, int k) {
        int l;
        int i1;

        for (l = 0; l < 4; ++l) {
            this.c[l] = 1000;
            i1 = i;
            int j1 = k;

            if (l == 0) {
                i1 = i - 1;
            }

            if (l == 1) {
                ++i1;
            }

            if (l == 2) {
                j1 = k - 1;
            }

            if (l == 3) {
                ++j1;
            }

            if (!this.o(oworld, i1, j, j1) && (oworld.f(i1, j, j1) != this.cB || oworld.g(i1, j, j1) != 0)) {
                if (this.o(oworld, i1, j - 1, j1)) {
                    this.c[l] = this.d(oworld, i1, j, j1, 1, l);
                } else {
                    this.c[l] = 0;
                }
            }
        }

        l = this.c[0];

        for (i1 = 1; i1 < 4; ++i1) {
            if (this.c[i1] < l) {
                l = this.c[i1];
            }
        }

        for (i1 = 0; i1 < 4; ++i1) {
            this.b[i1] = this.c[i1] == l;
        }

        return this.b;
    }

    private boolean o(OWorld oworld, int i, int j, int k) {
        int l = oworld.a(i, j, k);

        if (l != OBlock.aH.cm && l != OBlock.aO.cm && l != OBlock.aG.cm && l != OBlock.aI.cm && l != OBlock.ba.cm) {
            if (l == 0) {
                return false;
            } else {
                OMaterial omaterial = OBlock.p[l].cB;

                return omaterial == OMaterial.C ? true : omaterial.c();
            }
        } else {
            return true;
        }
    }

    protected int d(OWorld oworld, int i, int j, int k, int l) {
        int i1 = this.f_(oworld, i, j, k);

        if (i1 < 0) {
            return l;
        } else {
            if (i1 == 0) {
                ++this.a;
            }

            if (i1 >= 8) {
                i1 = 0;
            }

            return l >= 0 && i1 >= l ? l : i1;
        }
    }

    private boolean p(OWorld oworld, int i, int j, int k) {
        // CanaryMod: See if this liquid can destroy this block.
        Block block = new Block(oworld.world, oworld.world.getBlockIdAt(i, j, k), i, j, k);
        PluginLoader.HookResult ret = (PluginLoader.HookResult) etc.getLoader().callHook(PluginLoader.Hook.LIQUID_DESTROY, this.ca, block);

        if (ret == PluginLoader.HookResult.PREVENT_ACTION) {
            return false;
        }
        if (ret == PluginLoader.HookResult.ALLOW_ACTION) {
            return true;
        }
        OMaterial omaterial = oworld.f(i, j, k);

        return omaterial == this.cB ? false : (omaterial == OMaterial.i ? false : !this.o(oworld, i, j, k));
    }

    public void g(OWorld oworld, int i, int j, int k) {
        super.g(oworld, i, j, k);
        if (oworld.a(i, j, k) == this.cm) {
            oworld.a(i, j, k, this.cm, this.r_());
        }

    }

    public boolean l() {
        return false;
    }
}
