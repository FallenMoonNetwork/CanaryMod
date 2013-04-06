import java.util.Random;

public class OBlockLeaves extends OBlockLeavesBase {

    public static final String[] a = new String[] { "oak", "spruce", "birch", "jungle"};
    public static final String[][] b = new String[][] { { "leaves", "leaves_spruce", "leaves", "leaves_jungle"}, { "leaves_opaque", "leaves_spruce_opaque", "leaves_opaque", "leaves_jungle_opaque"}};
    private OIcon[][] cR = new OIcon[2][];
    int[] c;

    protected OBlockLeaves(int i) {
        super(i, OMaterial.j, false);
        this.b(true);
        this.a(OCreativeTabs.c);
    }

    public void a(OWorld oworld, int i, int j, int k, int l, int i1) {
        byte b0 = 1;
        int j1 = b0 + 1;

        if (oworld.e(i - j1, j - j1, k - j1, i + j1, j + j1, k + j1)) {
            for (int k1 = -b0; k1 <= b0; ++k1) {
                for (int l1 = -b0; l1 <= b0; ++l1) {
                    for (int i2 = -b0; i2 <= b0; ++i2) {
                        int j2 = oworld.a(i + k1, j + l1, k + i2);

                        if (j2 == OBlock.O.cz) {
                            int k2 = oworld.h(i + k1, j + l1, k + i2);

                            oworld.b(i + k1, j + l1, k + i2, k2 | 8, 4);
                        }
                    }
                }
            }
        }
    }

    public void a(OWorld oworld, int i, int j, int k, Random random) {
        if (!oworld.I) {
            int l = oworld.h(i, j, k);

            if ((l & 8) != 0 && (l & 4) == 0) {
                byte b0 = 4;
                int i1 = b0 + 1;
                byte b1 = 32;
                int j1 = b1 * b1;
                int k1 = b1 / 2;

                if (this.c == null) {
                    this.c = new int[b1 * b1 * b1];
                }

                int l1;

                if (oworld.e(i - i1, j - i1, k - i1, i + i1, j + i1, k + i1)) {
                    int i2;
                    int j2;
                    int k2;

                    for (l1 = -b0; l1 <= b0; ++l1) {
                        for (i2 = -b0; i2 <= b0; ++i2) {
                            for (j2 = -b0; j2 <= b0; ++j2) {
                                k2 = oworld.a(i + l1, j + i2, k + j2);
                                if (k2 == OBlock.N.cz) {
                                    this.c[(l1 + k1) * j1 + (i2 + k1) * b1 + j2 + k1] = 0;
                                } else if (k2 == OBlock.O.cz) {
                                    this.c[(l1 + k1) * j1 + (i2 + k1) * b1 + j2 + k1] = -2;
                                } else {
                                    this.c[(l1 + k1) * j1 + (i2 + k1) * b1 + j2 + k1] = -1;
                                }
                            }
                        }
                    }

                    for (l1 = 1; l1 <= 4; ++l1) {
                        for (i2 = -b0; i2 <= b0; ++i2) {
                            for (j2 = -b0; j2 <= b0; ++j2) {
                                for (k2 = -b0; k2 <= b0; ++k2) {
                                    if (this.c[(i2 + k1) * j1 + (j2 + k1) * b1 + k2 + k1] == l1 - 1) {
                                        if (this.c[(i2 + k1 - 1) * j1 + (j2 + k1) * b1 + k2 + k1] == -2) {
                                            this.c[(i2 + k1 - 1) * j1 + (j2 + k1) * b1 + k2 + k1] = l1;
                                        }

                                        if (this.c[(i2 + k1 + 1) * j1 + (j2 + k1) * b1 + k2 + k1] == -2) {
                                            this.c[(i2 + k1 + 1) * j1 + (j2 + k1) * b1 + k2 + k1] = l1;
                                        }

                                        if (this.c[(i2 + k1) * j1 + (j2 + k1 - 1) * b1 + k2 + k1] == -2) {
                                            this.c[(i2 + k1) * j1 + (j2 + k1 - 1) * b1 + k2 + k1] = l1;
                                        }

                                        if (this.c[(i2 + k1) * j1 + (j2 + k1 + 1) * b1 + k2 + k1] == -2) {
                                            this.c[(i2 + k1) * j1 + (j2 + k1 + 1) * b1 + k2 + k1] = l1;
                                        }

                                        if (this.c[(i2 + k1) * j1 + (j2 + k1) * b1 + (k2 + k1 - 1)] == -2) {
                                            this.c[(i2 + k1) * j1 + (j2 + k1) * b1 + (k2 + k1 - 1)] = l1;
                                        }

                                        if (this.c[(i2 + k1) * j1 + (j2 + k1) * b1 + k2 + k1 + 1] == -2) {
                                            this.c[(i2 + k1) * j1 + (j2 + k1) * b1 + k2 + k1 + 1] = l1;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                l1 = this.c[k1 * j1 + k1 * b1 + k1];
                if (l1 >= 0) {
                    oworld.b(i, j, k, l & -9, 4);
                } else {
                    this.k(oworld, i, j, k);
                }
            }
        }
    }

    private void k(OWorld oworld, int i, int j, int k) {
        // CanaryMod: stop leaves from decaying
        World world = oworld.world;
        Block block = new Block(world, world.getBlockIdAt(i, j, k), i, j, k);

        if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.LEAF_DECAY, block)) {
            this.c(oworld, i, j, k, oworld.h(i, j, k), 0);
            oworld.i(i, j, k);
        }
    }

    public int a(Random random) {
        return random.nextInt(20) == 0 ? 1 : 0;
    }

    public int a(int i, Random random, int j) {
        return OBlock.C.cz;
    }

    public void a(OWorld oworld, int i, int j, int k, int l, float f, int i1) {
        if (!oworld.I) {
            int j1 = 20;

            if ((l & 3) == 3) {
                j1 = 40;
            }

            if (i1 > 0) {
                j1 -= 2 << i1;
                if (j1 < 10) {
                    j1 = 10;
                }
            }

            if (oworld.s.nextInt(j1) == 0) {
                int k1 = this.a(l, oworld.s, i1);

                this.b(oworld, i, j, k, new OItemStack(k1, 1, this.a(l)));
            }

            j1 = 200;
            if (i1 > 0) {
                j1 -= 10 << i1;
                if (j1 < 40) {
                    j1 = 40;
                }
            }

            if ((l & 3) == 0 && oworld.s.nextInt(j1) == 0) {
                this.b(oworld, i, j, k, new OItemStack(OItem.k, 1, 0));
            }
        }
    }

    public void a(OWorld oworld, OEntityPlayer oentityplayer, int i, int j, int k, int l) {
        if (!oworld.I && oentityplayer.cb() != null && oentityplayer.cb().c == OItem.bf.cp) {
            oentityplayer.a(OStatList.C[this.cz], 1);
            this.b(oworld, i, j, k, new OItemStack(OBlock.O.cz, 1, l & 3));
        } else {
            super.a(oworld, oentityplayer, i, j, k, l);
        }
    }

    public int a(int i) {
        return i & 3;
    }

    public boolean c() {
        return !this.d;
    }

    protected OItemStack c_(int i) {
        return new OItemStack(this.cz, 1, i & 3);
    }
}
