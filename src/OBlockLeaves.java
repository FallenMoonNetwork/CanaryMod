import java.util.Random;


public class OBlockLeaves extends OBlockLeavesBase {

    private int cD;
    public static final String[] a = new String[] { "oak", "spruce", "birch", "jungle"};
    int[] b;

    protected OBlockLeaves(int i, int j) {
        super(i, j, OMaterial.j, false);
        this.cD = j;
        this.b(true);
        this.a(OCreativeTabs.c);
    }

    public void a(OWorld oworld, int i, int j, int k, int l, int i1) {
        byte b0 = 1;
        int j1 = b0 + 1;

        if (oworld.d(i - j1, j - j1, k - j1, i + j1, j + j1, k + j1)) {
            for (int k1 = -b0; k1 <= b0; ++k1) {
                for (int l1 = -b0; l1 <= b0; ++l1) {
                    for (int i2 = -b0; i2 <= b0; ++i2) {
                        int j2 = oworld.a(i + k1, j + l1, k + i2);

                        if (j2 == OBlock.N.cm) {
                            int k2 = oworld.g(i + k1, j + l1, k + i2);

                            oworld.d(i + k1, j + l1, k + i2, k2 | 8);
                        }
                    }
                }
            }
        }

    }

    public void b(OWorld oworld, int i, int j, int k, Random random) {
        if (!oworld.J) {
            int l = oworld.g(i, j, k);

            if ((l & 8) != 0 && (l & 4) == 0) {
                byte b0 = 4;
                int i1 = b0 + 1;
                byte b1 = 32;
                int j1 = b1 * b1;
                int k1 = b1 / 2;

                if (this.b == null) {
                    this.b = new int[b1 * b1 * b1];
                }

                int l1;

                if (oworld.d(i - i1, j - i1, k - i1, i + i1, j + i1, k + i1)) {
                    int i2;
                    int j2;
                    int k2;

                    for (l1 = -b0; l1 <= b0; ++l1) {
                        for (i2 = -b0; i2 <= b0; ++i2) {
                            for (j2 = -b0; j2 <= b0; ++j2) {
                                k2 = oworld.a(i + l1, j + i2, k + j2);
                                if (k2 == OBlock.M.cm) {
                                    this.b[(l1 + k1) * j1 + (i2 + k1) * b1 + j2 + k1] = 0;
                                } else if (k2 == OBlock.N.cm) {
                                    this.b[(l1 + k1) * j1 + (i2 + k1) * b1 + j2 + k1] = -2;
                                } else {
                                    this.b[(l1 + k1) * j1 + (i2 + k1) * b1 + j2 + k1] = -1;
                                }
                            }
                        }
                    }

                    for (l1 = 1; l1 <= 4; ++l1) {
                        for (i2 = -b0; i2 <= b0; ++i2) {
                            for (j2 = -b0; j2 <= b0; ++j2) {
                                for (k2 = -b0; k2 <= b0; ++k2) {
                                    if (this.b[(i2 + k1) * j1 + (j2 + k1) * b1 + k2 + k1] == l1 - 1) {
                                        if (this.b[(i2 + k1 - 1) * j1 + (j2 + k1) * b1 + k2 + k1] == -2) {
                                            this.b[(i2 + k1 - 1) * j1 + (j2 + k1) * b1 + k2 + k1] = l1;
                                        }

                                        if (this.b[(i2 + k1 + 1) * j1 + (j2 + k1) * b1 + k2 + k1] == -2) {
                                            this.b[(i2 + k1 + 1) * j1 + (j2 + k1) * b1 + k2 + k1] = l1;
                                        }

                                        if (this.b[(i2 + k1) * j1 + (j2 + k1 - 1) * b1 + k2 + k1] == -2) {
                                            this.b[(i2 + k1) * j1 + (j2 + k1 - 1) * b1 + k2 + k1] = l1;
                                        }

                                        if (this.b[(i2 + k1) * j1 + (j2 + k1 + 1) * b1 + k2 + k1] == -2) {
                                            this.b[(i2 + k1) * j1 + (j2 + k1 + 1) * b1 + k2 + k1] = l1;
                                        }

                                        if (this.b[(i2 + k1) * j1 + (j2 + k1) * b1 + (k2 + k1 - 1)] == -2) {
                                            this.b[(i2 + k1) * j1 + (j2 + k1) * b1 + (k2 + k1 - 1)] = l1;
                                        }

                                        if (this.b[(i2 + k1) * j1 + (j2 + k1) * b1 + k2 + k1 + 1] == -2) {
                                            this.b[(i2 + k1) * j1 + (j2 + k1) * b1 + k2 + k1 + 1] = l1;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                l1 = this.b[k1 * j1 + k1 * b1 + k1];
                if (l1 >= 0) {
                    oworld.d(i, j, k, l & -9);
                } else {
                    this.l(oworld, i, j, k);
                }
            }

        }
    }

    private void l(OWorld oworld, int i, int j, int k) {
        // CanaryMod: stop leaves from decaying
        World world = oworld.world;
        Block block = new Block(world, world.getBlockIdAt(i, j, k), i, j, k);

        if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.LEAF_DECAY, block)) {
            this.c(oworld, i, j, k, oworld.g(i, j, k), 0);
            oworld.e(i, j, k, 0);
        }
    }

    public int a(Random random) {
        return random.nextInt(20) == 0 ? 1 : 0;
    }

    public int a(int i, Random random, int j) {
        return OBlock.B.cm;
    }

    public void a(OWorld oworld, int i, int j, int k, int l, float f, int i1) {
        if (!oworld.J) {
            byte b0 = 20;

            if ((l & 3) == 3) {
                b0 = 40;
            }

            if (oworld.u.nextInt(b0) == 0) {
                int j1 = this.a(l, oworld.u, i1);

                this.a(oworld, i, j, k, new OItemStack(j1, 1, this.b(l)));
            }

            if ((l & 3) == 0 && oworld.u.nextInt(200) == 0) {
                this.a(oworld, i, j, k, new OItemStack(OItem.j, 1, 0));
            }
        }

    }

    public void a(OWorld oworld, OEntityPlayer oentityplayer, int i, int j, int k, int l) {
        if (!oworld.J && oentityplayer.bP() != null && oentityplayer.bP().c == OItem.be.cf) {
            oentityplayer.a(OStatList.C[this.cm], 1);
            this.a(oworld, i, j, k, new OItemStack(OBlock.N.cm, 1, l & 3));
        } else {
            super.a(oworld, oentityplayer, i, j, k, l);
        }

    }

    public int b(int i) {
        return i & 3;
    }

    public boolean c() {
        return !this.c;
    }

    public int a(int i, int j) {
        return (j & 3) == 1 ? this.cl + 80 : ((j & 3) == 3 ? this.cl + 144 : this.cl);
    }
}
