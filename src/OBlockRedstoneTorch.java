import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class OBlockRedstoneTorch extends OBlockTorch {

    private boolean a = false;
    private static Map b = new HashMap();

    private boolean a(OWorld oworld, int i, int j, int k, boolean flag) {
        if (!b.containsKey(oworld)) {
            b.put(oworld, new ArrayList());
        }

        List list = (List) b.get(oworld);

        if (flag) {
            list.add(new ORedstoneUpdateInfo(i, j, k, oworld.G()));
        }

        int l = 0;

        for (int i1 = 0; i1 < list.size(); ++i1) {
            ORedstoneUpdateInfo oredstoneupdateinfo = (ORedstoneUpdateInfo) list.get(i1);

            if (oredstoneupdateinfo.a == i && oredstoneupdateinfo.b == j && oredstoneupdateinfo.c == k) {
                ++l;
                if (l >= 8) {
                    return true;
                }
            }
        }

        return false;
    }

    protected OBlockRedstoneTorch(int i, boolean flag) {
        super(i);
        this.a = flag;
        this.b(true);
        this.a((OCreativeTabs) null);
    }

    public int a(OWorld oworld) {
        return 2;
    }

    public void a(OWorld oworld, int i, int j, int k) {
        if (oworld.h(i, j, k) == 0) {
            super.a(oworld, i, j, k);
        }

        if (this.a) {
            oworld.f(i, j - 1, k, this.cz);
            oworld.f(i, j + 1, k, this.cz);
            oworld.f(i - 1, j, k, this.cz);
            oworld.f(i + 1, j, k, this.cz);
            oworld.f(i, j, k - 1, this.cz);
            oworld.f(i, j, k + 1, this.cz);
        }
    }

    public void a(OWorld oworld, int i, int j, int k, int l, int i1) {
        if (this.a) {
            oworld.f(i, j - 1, k, this.cz);
            oworld.f(i, j + 1, k, this.cz);
            oworld.f(i - 1, j, k, this.cz);
            oworld.f(i + 1, j, k, this.cz);
            oworld.f(i, j, k - 1, this.cz);
            oworld.f(i, j, k + 1, this.cz);
        }
    }

    public int b(OIBlockAccess oiblockaccess, int i, int j, int k, int l) {
        if (!this.a) {
            return 0;
        } else {
            int i1 = oiblockaccess.h(i, j, k);

            return i1 == 5 && l == 1 ? 0 : (i1 == 3 && l == 3 ? 0 : (i1 == 4 && l == 2 ? 0 : (i1 == 1 && l == 5 ? 0 : (i1 == 2 && l == 4 ? 0 : 15))));
        }
    }

    private boolean m(OWorld oworld, int i, int j, int k) {
        int l = oworld.h(i, j, k);

        return l == 5 && oworld.k(i, j - 1, k, 0) ? true : (l == 3 && oworld.k(i, j, k - 1, 2) ? true : (l == 4 && oworld.k(i, j, k + 1, 3) ? true : (l == 1 && oworld.k(i - 1, j, k, 4) ? true : l == 2 && oworld.k(i + 1, j, k, 5))));
    }

    public void a(OWorld oworld, int i, int j, int k, Random random) {
        boolean flag = this.m(oworld, i, j, k);
        List list = (List) b.get(oworld);

        while (list != null && !list.isEmpty() && oworld.G() - ((ORedstoneUpdateInfo) list.get(0)).d > 60L) {
            list.remove(0);
        }

        if (this.a) {
            if (flag) {
                // CanaryMod: Allow redstone torches to provide power
                if ((Integer) etc.getLoader().callHook(PluginLoader.Hook.REDSTONE_CHANGE, oworld.world.getBlockAt(i, j, k), 1, 0) == 0) {
                    oworld.f(i, j, k, OBlock.aT.cz, oworld.h(i, j, k), 3);
                    if (this.a(oworld, i, j, k, true)) {
                    oworld.a((double) ((float) i + 0.5F), (double) ((float) j + 0.5F), (double) ((float) k + 0.5F), "random.fizz", 0.5F, 2.6F + (oworld.s.nextFloat() - oworld.s.nextFloat()) * 0.8F);

                        for (int l = 0; l < 5; ++l) {
                            double d0 = (double) i + random.nextDouble() * 0.6D + 0.2D;
                            double d1 = (double) j + random.nextDouble() * 0.6D + 0.2D;
                            double d2 = (double) k + random.nextDouble() * 0.6D + 0.2D;

                            oworld.a("smoke", d0, d1, d2, 0.0D, 0.0D, 0.0D);
                        }
                    }
                } //
            }
        } else if (!flag && !this.a(oworld, i, j, k, false)) {
            oworld.f(i, j, k, OBlock.aU.cz, oworld.h(i, j, k), 3);
        }
    }

    public void a(OWorld oworld, int i, int j, int k, int l) {
        if (!this.d(oworld, i, j, k, l)) {
            boolean flag = this.m(oworld, i, j, k);

            if (this.a && flag || !this.a && !flag) {
                oworld.a(i, j, k, this.cz, this.a(oworld));
            }
        }
    }

    public int c(OIBlockAccess oiblockaccess, int i, int j, int k, int l) {
        return l == 0 ? this.b(oiblockaccess, i, j, k, l) : 0;
    }

    public int a(int i, Random random, int j) {
        return OBlock.aU.cz;
    }

    public boolean f() {
        return true;
    }

    public boolean i(int i) {
        return i == OBlock.aT.cz || i == OBlock.aU.cz;
    }
}
