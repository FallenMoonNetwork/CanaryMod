import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;


public class OBlockRedstoneWire extends OBlock {

    private boolean a = true;
    private Set b = new HashSet();

    public OBlockRedstoneWire(int i, int j) {
        super(i, j, OMaterial.q);
        this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.0625F, 1.0F);
    }

    public int a(int i, int j) {
        return this.cl;
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
        return 5;
    }

    public boolean b(OWorld oworld, int i, int j, int k) {
        return oworld.t(i, j - 1, k) || oworld.a(i, j - 1, k) == OBlock.bg.cm;
    }

    private void l(OWorld oworld, int i, int j, int k) {
        this.a(oworld, i, j, k, i, j, k);
        ArrayList arraylist = new ArrayList(this.b);

        this.b.clear();
        Iterator iterator = arraylist.iterator();

        while (iterator.hasNext()) {
            OChunkPosition ochunkposition = (OChunkPosition) iterator.next();

            oworld.h(ochunkposition.a, ochunkposition.b, ochunkposition.c, this.cm);
        }

    }

    private void a(OWorld oworld, int i, int j, int k, int l, int i1, int j1) {
        int k1 = oworld.g(i, j, k);
        int l1 = 0;

        this.a = false;
        boolean flag = oworld.z(i, j, k);

        this.a = true;
        int i2;
        int j2;
        int k2;

        if (flag) {
            l1 = 15;
        } else {
            for (i2 = 0; i2 < 4; ++i2) {
                j2 = i;
                k2 = k;
                if (i2 == 0) {
                    j2 = i - 1;
                }

                if (i2 == 1) {
                    ++j2;
                }

                if (i2 == 2) {
                    k2 = k - 1;
                }

                if (i2 == 3) {
                    ++k2;
                }

                if (j2 != l || j != i1 || k2 != j1) {
                    l1 = this.d(oworld, j2, j, k2, l1);
                }

                if (oworld.s(j2, j, k2) && !oworld.s(i, j + 1, k)) {
                    if (j2 != l || j + 1 != i1 || k2 != j1) {
                        l1 = this.d(oworld, j2, j + 1, k2, l1);
                    }
                } else if (!oworld.s(j2, j, k2) && (j2 != l || j - 1 != i1 || k2 != j1)) {
                    l1 = this.d(oworld, j2, j - 1, k2, l1);
                }
            }

            if (l1 > 0) {
                --l1;
            } else {
                l1 = 0;
            }
        }
		
        // CanaryMod: Allow redstone wire current changes
        if (k1 != l1) {
            l1 = (Integer) etc.getLoader().callHook(PluginLoader.Hook.REDSTONE_CHANGE, new Block(oworld.world, this.ca, i, j, k), k1, l1);
        }

        if (k1 != l1) {
            oworld.s = true;
            oworld.c(i, j, k, l1);
            oworld.e(i, j, k, i, j, k);
            oworld.s = false;

            for (i2 = 0; i2 < 4; ++i2) {
                j2 = i;
                k2 = k;
                int l2 = j - 1;

                if (i2 == 0) {
                    j2 = i - 1;
                }

                if (i2 == 1) {
                    ++j2;
                }

                if (i2 == 2) {
                    k2 = k - 1;
                }

                if (i2 == 3) {
                    ++k2;
                }

                if (oworld.s(j2, j, k2)) {
                    l2 += 2;
                }

                boolean flag1 = false;
                int i3 = this.d(oworld, j2, j, k2, -1);

                l1 = oworld.g(i, j, k);
                if (l1 > 0) {
                    --l1;
                }

                if (i3 >= 0 && i3 != l1) {
                    this.a(oworld, j2, j, k2, i, j, k);
                }

                i3 = this.d(oworld, j2, l2, k2, -1);
                l1 = oworld.g(i, j, k);
                if (l1 > 0) {
                    --l1;
                }

                if (i3 >= 0 && i3 != l1) {
                    this.a(oworld, j2, l2, k2, i, j, k);
                }
            }

            if (k1 < l1 || l1 == 0) {
                this.b.add(new OChunkPosition(i, j, k));
                this.b.add(new OChunkPosition(i - 1, j, k));
                this.b.add(new OChunkPosition(i + 1, j, k));
                this.b.add(new OChunkPosition(i, j - 1, k));
                this.b.add(new OChunkPosition(i, j + 1, k));
                this.b.add(new OChunkPosition(i, j, k - 1));
                this.b.add(new OChunkPosition(i, j, k + 1));
            }
        }

    }

    private void n(OWorld oworld, int i, int j, int k) {
        if (oworld.a(i, j, k) == this.cm) {
            oworld.h(i, j, k, this.cm);
            oworld.h(i - 1, j, k, this.cm);
            oworld.h(i + 1, j, k, this.cm);
            oworld.h(i, j, k - 1, this.cm);
            oworld.h(i, j, k + 1, this.cm);
            oworld.h(i, j - 1, k, this.cm);
            oworld.h(i, j + 1, k, this.cm);
        }
    }

    public void g(OWorld oworld, int i, int j, int k) {
        super.g(oworld, i, j, k);
        if (!oworld.J) {
            this.l(oworld, i, j, k);
            oworld.h(i, j + 1, k, this.cm);
            oworld.h(i, j - 1, k, this.cm);
            this.n(oworld, i - 1, j, k);
            this.n(oworld, i + 1, j, k);
            this.n(oworld, i, j, k - 1);
            this.n(oworld, i, j, k + 1);
            if (oworld.s(i - 1, j, k)) {
                this.n(oworld, i - 1, j + 1, k);
            } else {
                this.n(oworld, i - 1, j - 1, k);
            }

            if (oworld.s(i + 1, j, k)) {
                this.n(oworld, i + 1, j + 1, k);
            } else {
                this.n(oworld, i + 1, j - 1, k);
            }

            if (oworld.s(i, j, k - 1)) {
                this.n(oworld, i, j + 1, k - 1);
            } else {
                this.n(oworld, i, j - 1, k - 1);
            }

            if (oworld.s(i, j, k + 1)) {
                this.n(oworld, i, j + 1, k + 1);
            } else {
                this.n(oworld, i, j - 1, k + 1);
            }

        }
    }

    public void a(OWorld oworld, int i, int j, int k, int l, int i1) {
        super.a(oworld, i, j, k, l, i1);
        if (!oworld.J) {
            oworld.h(i, j + 1, k, this.cm);
            oworld.h(i, j - 1, k, this.cm);
            oworld.h(i + 1, j, k, this.cm);
            oworld.h(i - 1, j, k, this.cm);
            oworld.h(i, j, k + 1, this.cm);
            oworld.h(i, j, k - 1, this.cm);
            this.l(oworld, i, j, k);
            this.n(oworld, i - 1, j, k);
            this.n(oworld, i + 1, j, k);
            this.n(oworld, i, j, k - 1);
            this.n(oworld, i, j, k + 1);
            if (oworld.s(i - 1, j, k)) {
                this.n(oworld, i - 1, j + 1, k);
            } else {
                this.n(oworld, i - 1, j - 1, k);
            }

            if (oworld.s(i + 1, j, k)) {
                this.n(oworld, i + 1, j + 1, k);
            } else {
                this.n(oworld, i + 1, j - 1, k);
            }

            if (oworld.s(i, j, k - 1)) {
                this.n(oworld, i, j + 1, k - 1);
            } else {
                this.n(oworld, i, j - 1, k - 1);
            }

            if (oworld.s(i, j, k + 1)) {
                this.n(oworld, i, j + 1, k + 1);
            } else {
                this.n(oworld, i, j - 1, k + 1);
            }

        }
    }

    private int d(OWorld oworld, int i, int j, int k, int l) {
        if (oworld.a(i, j, k) != this.cm) {
            return l;
        } else {
            int i1 = oworld.g(i, j, k);

            return i1 > l ? i1 : l;
        }
    }

    public void a(OWorld oworld, int i, int j, int k, int l) {
        if (!oworld.J) {
            int i1 = oworld.g(i, j, k);
            boolean flag = this.b(oworld, i, j, k);

            if (flag) {
                this.l(oworld, i, j, k);
            } else {
                this.c(oworld, i, j, k, i1, 0);
                oworld.e(i, j, k, 0);
            }

            super.a(oworld, i, j, k, l);
        }
    }

    public int a(int i, Random random, int j) {
        return OItem.aC.cf;
    }

    public boolean c(OIBlockAccess oiblockaccess, int i, int j, int k, int l) {
        return !this.a ? false : this.b(oiblockaccess, i, j, k, l);
    }

    public boolean b(OIBlockAccess oiblockaccess, int i, int j, int k, int l) {
        if (!this.a) {
            return false;
        } else if (oiblockaccess.g(i, j, k) == 0) {
            return false;
        } else if (l == 1) {
            return true;
        } else {
            boolean flag = g(oiblockaccess, i - 1, j, k, 1) || !oiblockaccess.s(i - 1, j, k) && g(oiblockaccess, i - 1, j - 1, k, -1);
            boolean flag1 = g(oiblockaccess, i + 1, j, k, 3) || !oiblockaccess.s(i + 1, j, k) && g(oiblockaccess, i + 1, j - 1, k, -1);
            boolean flag2 = g(oiblockaccess, i, j, k - 1, 2) || !oiblockaccess.s(i, j, k - 1) && g(oiblockaccess, i, j - 1, k - 1, -1);
            boolean flag3 = g(oiblockaccess, i, j, k + 1, 0) || !oiblockaccess.s(i, j, k + 1) && g(oiblockaccess, i, j - 1, k + 1, -1);

            if (!oiblockaccess.s(i, j + 1, k)) {
                if (oiblockaccess.s(i - 1, j, k) && g(oiblockaccess, i - 1, j + 1, k, -1)) {
                    flag = true;
                }

                if (oiblockaccess.s(i + 1, j, k) && g(oiblockaccess, i + 1, j + 1, k, -1)) {
                    flag1 = true;
                }

                if (oiblockaccess.s(i, j, k - 1) && g(oiblockaccess, i, j + 1, k - 1, -1)) {
                    flag2 = true;
                }

                if (oiblockaccess.s(i, j, k + 1) && g(oiblockaccess, i, j + 1, k + 1, -1)) {
                    flag3 = true;
                }
            }

            return !flag2 && !flag1 && !flag && !flag3 && l >= 2 && l <= 5 ? true : (l == 2 && flag2 && !flag && !flag1 ? true : (l == 3 && flag3 && !flag && !flag1 ? true : (l == 4 && flag && !flag2 && !flag3 ? true : l == 5 && flag1 && !flag2 && !flag3)));
        }
    }

    public boolean i() {
        return this.a;
    }

    public static boolean f(OIBlockAccess oiblockaccess, int i, int j, int k, int l) {
        int i1 = oiblockaccess.a(i, j, k);

        if (i1 == OBlock.ay.cm) {
            return true;
        } else if (i1 == 0) {
            return false;
        } else if (i1 != OBlock.bk.cm && i1 != OBlock.bl.cm) {
            return OBlock.p[i1].i() && l != -1;
        } else {
            int j1 = oiblockaccess.g(i, j, k);

            return l == (j1 & 3) || l == ODirection.f[j1 & 3];
        }
    }

    public static boolean g(OIBlockAccess oiblockaccess, int i, int j, int k, int l) {
        if (f(oiblockaccess, i, j, k, l)) {
            return true;
        } else {
            int i1 = oiblockaccess.a(i, j, k);

            if (i1 == OBlock.bl.cm) {
                int j1 = oiblockaccess.g(i, j, k);

                return l == (j1 & 3);
            } else {
                return false;
            }
        }
    }
}
