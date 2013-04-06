import java.util.Random;

public class OBlockFire extends OBlock {

    private int[] a = new int[256];
    private int[] b = new int[256];

    protected OBlockFire(int i) {
        super(i, OMaterial.o);
        this.b(true);
    }

    public void s_() {
        this.a(OBlock.B.cz, 5, 20);
        this.a(OBlock.bR.cz, 5, 20);
        this.a(OBlock.bS.cz, 5, 20);
        this.a(OBlock.bd.cz, 5, 20);
        this.a(OBlock.ax.cz, 5, 20);
        this.a(OBlock.cb.cz, 5, 20);
        this.a(OBlock.ca.cz, 5, 20);
        this.a(OBlock.cc.cz, 5, 20);
        this.a(OBlock.N.cz, 5, 5);
        this.a(OBlock.O.cz, 30, 60);
        this.a(OBlock.ar.cz, 30, 20);
        this.a(OBlock.aq.cz, 15, 100);
        this.a(OBlock.ab.cz, 60, 100);
        this.a(OBlock.af.cz, 30, 60);
        this.a(OBlock.by.cz, 15, 100);
    }

    private void a(int i, int j, int k) {
        this.a[i] = j;
        this.b[i] = k;
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
        return 3;
    }

    public int a(Random random) {
        return 0;
    }

    public int a(OWorld oworld) {
        return 30;
    }

    public void a(OWorld oworld, int i, int j, int k, Random random) {
        if (oworld.M().b("doFireTick")) {
            boolean flag = oworld.a(i, j - 1, k) == OBlock.bf.cz;

            if (oworld.t instanceof OWorldProviderEnd && oworld.a(i, j - 1, k) == OBlock.D.cz) {
                flag = true;
            }

            if (!this.c(oworld, i, j, k)) {
                oworld.i(i, j, k);
            }

            if (!flag && oworld.O() && (oworld.F(i, j, k) || oworld.F(i - 1, j, k) || oworld.F(i + 1, j, k) || oworld.F(i, j, k - 1) || oworld.F(i, j, k + 1))) {
                oworld.i(i, j, k);
            } else {
                int l = oworld.h(i, j, k);

                if (l < 15) {
                    oworld.b(i, j, k, l + random.nextInt(3) / 2, 4);
                }

                oworld.a(i, j, k, this.cz, this.a(oworld) + random.nextInt(10));
                if (!flag && !this.k(oworld, i, j, k)) {
                    if (!oworld.w(i, j - 1, k) || l > 3) {
                        oworld.i(i, j, k);
                    }
                } else if (!flag && !this.d(oworld, i, j - 1, k) && l == 15 && random.nextInt(4) == 0) {
                    oworld.i(i, j, k);
                } else {
                    boolean flag1 = oworld.G(i, j, k);
                    byte b0 = 0;

                    if (flag1) {
                        b0 = -50;
                    }

                    this.a(oworld, i + 1, j, k, 300 + b0, random, l);
                    this.a(oworld, i - 1, j, k, 300 + b0, random, l);
                    this.a(oworld, i, j - 1, k, 250 + b0, random, l);
                    this.a(oworld, i, j + 1, k, 250 + b0, random, l);
                    this.a(oworld, i, j, k - 1, 300 + b0, random, l);
                    this.a(oworld, i, j, k + 1, 300 + b0, random, l);

                    for (int i1 = i - 1; i1 <= i + 1; ++i1) {
                        for (int j1 = k - 1; j1 <= k + 1; ++j1) {
                            for (int k1 = j - 1; k1 <= j + 4; ++k1) {
                                if (i1 != i || k1 != j || j1 != k) {
                                    int l1 = 100;

                                    if (k1 > j + 1) {
                                        l1 += (k1 - (j + 1)) * 100;
                                    }

                                    int i2 = this.m(oworld, i1, k1, j1);

                                    if (i2 > 0) {
                                        int j2 = (i2 + 40 + oworld.r * 7) / (l + 30);

                                        if (flag1) {
                                            j2 /= 2;
                                        }

                                        if (j2 > 0 && random.nextInt(l1) <= j2 && (!oworld.O() || !oworld.F(i1, k1, j1)) && !oworld.F(i1 - 1, k1, k) && !oworld.F(i1 + 1, k1, j1) && !oworld.F(i1, k1, j1 - 1) && !oworld.F(i1, k1, j1 + 1)) {
                                            int k2 = l + random.nextInt(5) / 4;

                                            if (k2 > 15) {
                                                k2 = 15;
                                            }

                                            // CanaryMod: dynamic spreading of fire.
                                            // avg call amount per placed block of fire ~ 4
                                            Block block = new Block(oworld.world, oworld.world.getBlockIdAt(i1, k1, j1), i1, k1, j1);

                                            block.setStatus(3);
                                            if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.IGNITE, block, null)) {
                                                oworld.f(i1, k1, j1, this.cz, k2, 3);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public boolean l() {
        return false;
    }

    private void a(OWorld oworld, int i, int j, int k, int l, Random random, int i1) {
        int j1 = this.b[oworld.a(i, j, k)];

        if (random.nextInt(l) < j1) {
            boolean flag = oworld.a(i, j, k) == OBlock.aq.cz;

            if (random.nextInt(i1 + 10) < 5 && !oworld.F(i, j, k)) {
                int k1 = i1 + random.nextInt(5) / 4;

                if (k1 > 15) {
                    k1 = 15;
                }

                // CanaryMod: VERY SLOW dynamic spreading of fire.
                Block block = new Block(oworld.world, oworld.world.getBlockIdAt(i, j, k), i, j, k);

                block.setStatus(3);
                if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.IGNITE, block, null)) {
                    oworld.f(i, j, k, this.cz, k1, 3);
                }
            } else {
                // CanaryMod: fire destroying a block.
                Block block = new Block(oworld.world, oworld.a(i, j, k), i, j, k);
                block.setStatus(4);
                if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.IGNITE, block, null)) {
                    oworld.i(i, j, k);
                }
            }

            if (flag) {
                OBlock.aq.g(oworld, i, j, k, 1);
            }
        }
    }

    private boolean k(OWorld oworld, int i, int j, int k) {
        return this.d((OIBlockAccess) oworld, i + 1, j, k) ? true : (this.d((OIBlockAccess) oworld, i - 1, j, k) ? true : (this.d((OIBlockAccess) oworld, i, j - 1, k) ? true : (this.d((OIBlockAccess) oworld, i, j + 1, k) ? true : (this.d((OIBlockAccess) oworld, i, j, k - 1) ? true : this.d((OIBlockAccess) oworld, i, j, k + 1)))));
    }

    private int m(OWorld oworld, int i, int j, int k) {
        byte b0 = 0;

        if (!oworld.c(i, j, k)) {
            return 0;
        } else {
            int l = this.d(oworld, i + 1, j, k, b0);

            l = this.d(oworld, i - 1, j, k, l);
            l = this.d(oworld, i, j - 1, k, l);
            l = this.d(oworld, i, j + 1, k, l);
            l = this.d(oworld, i, j, k - 1, l);
            l = this.d(oworld, i, j, k + 1, l);
            return l;
        }
    }

    public boolean m() {
        return false;
    }

    public boolean d(OIBlockAccess oiblockaccess, int i, int j, int k) {
        return this.a[oiblockaccess.a(i, j, k)] > 0;
    }

    public int d(OWorld oworld, int i, int j, int k, int l) {
        int i1 = this.a[oworld.a(i, j, k)];

        return i1 > l ? i1 : l;
    }

    public boolean c(OWorld oworld, int i, int j, int k) {
        return oworld.w(i, j - 1, k) || this.k(oworld, i, j, k);
    }

    public void a(OWorld oworld, int i, int j, int k, int l) {
        if (!oworld.w(i, j - 1, k) && !this.k(oworld, i, j, k)) {
            oworld.i(i, j, k);
        }
    }

    public void a(OWorld oworld, int i, int j, int k) {
        if (oworld.t.h > 0 || oworld.a(i, j - 1, k) != OBlock.at.cz || !OBlock.bi.n_(oworld, i, j, k)) {
            if (!oworld.w(i, j - 1, k) && !this.k(oworld, i, j, k)) {
                oworld.i(i, j, k);
            } else {
                oworld.a(i, j, k, this.cz, this.a(oworld) + oworld.s.nextInt(10));
            }
        }
    }
}
