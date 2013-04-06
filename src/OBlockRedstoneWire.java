import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class OBlockRedstoneWire extends OBlock {

    private boolean a = true;
    private Set b = new HashSet();

    public OBlockRedstoneWire(int i) {
        super(i, OMaterial.q);
        this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.0625F, 1.0F);
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
        return 5;
    }

    public boolean c(OWorld oworld, int i, int j, int k) {
        return oworld.w(i, j - 1, k) || oworld.a(i, j - 1, k) == OBlock.bh.cz;
    }

    private void k(OWorld oworld, int i, int j, int k) {
        this.a(oworld, i, j, k, i, j, k);
        ArrayList arraylist = new ArrayList(this.b);

        this.b.clear();

        for (int l = 0; l < arraylist.size(); ++l) {
            OChunkPosition ochunkposition = (OChunkPosition) arraylist.get(l);

            oworld.f(ochunkposition.a, ochunkposition.b, ochunkposition.c, this.cz);
        }
    }

    private void a(OWorld oworld, int i, int j, int k, int l, int i1, int j1) {
        int k1 = oworld.h(i, j, k);
        byte b0 = 0;
        int l1 = this.d(oworld, l, i1, j1, b0);

        this.a = false;
        int i2 = oworld.D(i, j, k);

        this.a = true;
        if (i2 > 0 && i2 > l1 - 1) {
            l1 = i2;
        }

        int j2 = 0;

        for (int k2 = 0; k2 < 4; ++k2) {
            int l2 = i;
            int i3 = k;

            if (k2 == 0) {
                l2 = i - 1;
            }

            if (k2 == 1) {
                ++l2;
            }

            if (k2 == 2) {
                i3 = k - 1;
            }

            if (k2 == 3) {
                ++i3;
            }

            if (l2 != l || i3 != j1) {
                j2 = this.d(oworld, l2, j, i3, j2);
            }

            if (oworld.u(l2, j, i3) && !oworld.u(i, j + 1, k)) {
                if ((l2 != l || i3 != j1) && j >= i1) {
                    j2 = this.d(oworld, l2, j + 1, i3, j2);
                }
            } else if (!oworld.u(l2, j, i3) && (l2 != l || i3 != j1) && j <= i1) {
                j2 = this.d(oworld, l2, j - 1, i3, j2);
            }
        }

        if (j2 > l1) {
            l1 = j2 - 1;
        } else if (l1 > 0) {
            --l1;
        } else {
            l1 = 0;
        }

        if (i2 > l1 - 1) {
            l1 = i2;
        }

        // CanaryMod start: Allow redstone wire current changes
        if (k1 != l1) {
            l1 = (Integer) etc.getLoader().callHook(PluginLoader.Hook.REDSTONE_CHANGE, oworld.world.getBlockAt(i, j, k), k1, l1);
        } // CanaryMod end

        if (k1 != l1) {
            oworld.b(i, j, k, l1, 2);
            this.b.add(new OChunkPosition(i, j, k));
            this.b.add(new OChunkPosition(i - 1, j, k));
            this.b.add(new OChunkPosition(i + 1, j, k));
            this.b.add(new OChunkPosition(i, j - 1, k));
            this.b.add(new OChunkPosition(i, j + 1, k));
            this.b.add(new OChunkPosition(i, j, k - 1));
            this.b.add(new OChunkPosition(i, j, k + 1));
        }
    }

    private void m(OWorld oworld, int i, int j, int k) {
        if (oworld.a(i, j, k) == this.cz) {
            oworld.f(i, j, k, this.cz);
            oworld.f(i - 1, j, k, this.cz);
            oworld.f(i + 1, j, k, this.cz);
            oworld.f(i, j, k - 1, this.cz);
            oworld.f(i, j, k + 1, this.cz);
            oworld.f(i, j - 1, k, this.cz);
            oworld.f(i, j + 1, k, this.cz);
        }
    }

    public void a(OWorld oworld, int i, int j, int k) {
        super.a(oworld, i, j, k);
        if (!oworld.I) {
            this.k(oworld, i, j, k);
            oworld.f(i, j + 1, k, this.cz);
            oworld.f(i, j - 1, k, this.cz);
            this.m(oworld, i - 1, j, k);
            this.m(oworld, i + 1, j, k);
            this.m(oworld, i, j, k - 1);
            this.m(oworld, i, j, k + 1);
            if (oworld.u(i - 1, j, k)) {
                this.m(oworld, i - 1, j + 1, k);
            } else {
                this.m(oworld, i - 1, j - 1, k);
            }

            if (oworld.u(i + 1, j, k)) {
                this.m(oworld, i + 1, j + 1, k);
            } else {
                this.m(oworld, i + 1, j - 1, k);
            }

            if (oworld.u(i, j, k - 1)) {
                this.m(oworld, i, j + 1, k - 1);
            } else {
                this.m(oworld, i, j - 1, k - 1);
            }

            if (oworld.u(i, j, k + 1)) {
                this.m(oworld, i, j + 1, k + 1);
            } else {
                this.m(oworld, i, j - 1, k + 1);
            }
        }
    }

    public void a(OWorld oworld, int i, int j, int k, int l, int i1) {
        super.a(oworld, i, j, k, l, i1);
        if (!oworld.I) {
            oworld.f(i, j + 1, k, this.cz);
            oworld.f(i, j - 1, k, this.cz);
            oworld.f(i + 1, j, k, this.cz);
            oworld.f(i - 1, j, k, this.cz);
            oworld.f(i, j, k + 1, this.cz);
            oworld.f(i, j, k - 1, this.cz);
            this.k(oworld, i, j, k);
            this.m(oworld, i - 1, j, k);
            this.m(oworld, i + 1, j, k);
            this.m(oworld, i, j, k - 1);
            this.m(oworld, i, j, k + 1);
            if (oworld.u(i - 1, j, k)) {
                this.m(oworld, i - 1, j + 1, k);
            } else {
                this.m(oworld, i - 1, j - 1, k);
            }

            if (oworld.u(i + 1, j, k)) {
                this.m(oworld, i + 1, j + 1, k);
            } else {
                this.m(oworld, i + 1, j - 1, k);
            }

            if (oworld.u(i, j, k - 1)) {
                this.m(oworld, i, j + 1, k - 1);
            } else {
                this.m(oworld, i, j - 1, k - 1);
            }

            if (oworld.u(i, j, k + 1)) {
                this.m(oworld, i, j + 1, k + 1);
            } else {
                this.m(oworld, i, j - 1, k + 1);
            }
        }
    }

    private int d(OWorld oworld, int i, int j, int k, int l) {
        if (oworld.a(i, j, k) != this.cz) {
            return l;
        } else {
            int i1 = oworld.h(i, j, k);

            return i1 > l ? i1 : l;
        }
    }

    public void a(OWorld oworld, int i, int j, int k, int l) {
        if (!oworld.I) {
            boolean flag = this.c(oworld, i, j, k);

            if (flag) {
                this.k(oworld, i, j, k);
            } else {
                this.c(oworld, i, j, k, 0, 0);
                oworld.i(i, j, k);
            }

            super.a(oworld, i, j, k, l);
        }
    }

    public int a(int i, Random random, int j) {
        return OItem.aD.cp;
    }

    public int c(OIBlockAccess oiblockaccess, int i, int j, int k, int l) {
        return !this.a ? 0 : this.b(oiblockaccess, i, j, k, l);
    }

    public int b(OIBlockAccess oiblockaccess, int i, int j, int k, int l) {
        if (!this.a) {
            return 0;
        } else {
            int i1 = oiblockaccess.h(i, j, k);

            if (i1 == 0) {
                return 0;
            } else if (l == 1) {
                return i1;
            } else {
                boolean flag = g(oiblockaccess, i - 1, j, k, 1) || !oiblockaccess.u(i - 1, j, k) && g(oiblockaccess, i - 1, j - 1, k, -1);
                boolean flag1 = g(oiblockaccess, i + 1, j, k, 3) || !oiblockaccess.u(i + 1, j, k) && g(oiblockaccess, i + 1, j - 1, k, -1);
                boolean flag2 = g(oiblockaccess, i, j, k - 1, 2) || !oiblockaccess.u(i, j, k - 1) && g(oiblockaccess, i, j - 1, k - 1, -1);
                boolean flag3 = g(oiblockaccess, i, j, k + 1, 0) || !oiblockaccess.u(i, j, k + 1) && g(oiblockaccess, i, j - 1, k + 1, -1);

                if (!oiblockaccess.u(i, j + 1, k)) {
                    if (oiblockaccess.u(i - 1, j, k) && g(oiblockaccess, i - 1, j + 1, k, -1)) {
                        flag = true;
                    }

                    if (oiblockaccess.u(i + 1, j, k) && g(oiblockaccess, i + 1, j + 1, k, -1)) {
                        flag1 = true;
                    }

                    if (oiblockaccess.u(i, j, k - 1) && g(oiblockaccess, i, j + 1, k - 1, -1)) {
                        flag2 = true;
                    }

                    if (oiblockaccess.u(i, j, k + 1) && g(oiblockaccess, i, j + 1, k + 1, -1)) {
                        flag3 = true;
                    }
                }

                return !flag2 && !flag1 && !flag && !flag3 && l >= 2 && l <= 5 ? i1 : (l == 2 && flag2 && !flag && !flag1 ? i1 : (l == 3 && flag3 && !flag && !flag1 ? i1 : (l == 4 && flag && !flag2 && !flag3 ? i1 : (l == 5 && flag1 && !flag2 && !flag3 ? i1 : 0))));
            }
        }
    }

    public boolean f() {
        return this.a;
    }

    public static boolean f(OIBlockAccess oiblockaccess, int i, int j, int k, int l) {
        int i1 = oiblockaccess.a(i, j, k);

        if (i1 == OBlock.az.cz) {
            return true;
        } else if (i1 == 0) {
            return false;
        } else if (!OBlock.bl.g(i1)) {
            return OBlock.r[i1].f() && l != -1;
        } else {
            int j1 = oiblockaccess.h(i, j, k);

            return l == (j1 & 3) || l == ODirection.f[j1 & 3];
        }
    }

    public static boolean g(OIBlockAccess oiblockaccess, int i, int j, int k, int l) {
        if (f(oiblockaccess, i, j, k, l)) {
            return true;
        } else {
            int i1 = oiblockaccess.a(i, j, k);

            if (i1 == OBlock.bm.cz) {
                int j1 = oiblockaccess.h(i, j, k);

                return l == (j1 & 3);
            } else {
                return false;
            }
        }
    }
}
