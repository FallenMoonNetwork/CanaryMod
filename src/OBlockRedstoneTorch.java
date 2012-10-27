import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class OBlockRedstoneTorch extends OBlockTorch {

    private boolean a = false;
    private static Map b = new HashMap();

    public int a(int i, int j) {
        return i == 1 ? OBlock.ay.a(i, j) : super.a(i, j);
    }

    private boolean a(OWorld oworld, int i, int j, int k, boolean flag) {
        if (!b.containsKey(oworld)) {
            b.put(oworld, new ArrayList());
        }

        if (flag) {
            ((List) b.get(oworld)).add(new ORedstoneUpdateInfo(i, j, k, oworld.E()));
        }

        int l = 0;
        Iterator iterator = ((List) b.get(oworld)).iterator();

        while (iterator.hasNext()) {
            ORedstoneUpdateInfo oredstoneupdateinfo = (ORedstoneUpdateInfo) iterator.next();

            if (oredstoneupdateinfo.a == i && oredstoneupdateinfo.b == j && oredstoneupdateinfo.c == k) {
                ++l;
                if (l >= 8) {
                    return true;
                }
            }
        }

        return false;
    }

    protected OBlockRedstoneTorch(int i, int j, boolean flag) {
        super(i, j);
        this.a = flag;
        this.b(true);
        this.a((OCreativeTabs) null);
    }

    public int r_() {
        return 2;
    }

    public void g(OWorld oworld, int i, int j, int k) {
        if (oworld.g(i, j, k) == 0) {
            super.g(oworld, i, j, k);
        }

        if (this.a) {
            oworld.h(i, j - 1, k, this.cm);
            oworld.h(i, j + 1, k, this.cm);
            oworld.h(i - 1, j, k, this.cm);
            oworld.h(i + 1, j, k, this.cm);
            oworld.h(i, j, k - 1, this.cm);
            oworld.h(i, j, k + 1, this.cm);
        }
    }

    public void a(OWorld oworld, int i, int j, int k, int l, int i1) {
        if (this.a) {
            oworld.h(i, j - 1, k, this.cm);
            oworld.h(i, j + 1, k, this.cm);
            oworld.h(i - 1, j, k, this.cm);
            oworld.h(i + 1, j, k, this.cm);
            oworld.h(i, j, k - 1, this.cm);
            oworld.h(i, j, k + 1, this.cm);
        }
    }

    public boolean b(OIBlockAccess oiblockaccess, int i, int j, int k, int l) {
        if (!this.a) {
            return false;
        } else {
            int i1 = oiblockaccess.g(i, j, k);

            return i1 == 5 && l == 1 ? false : (i1 == 3 && l == 3 ? false : (i1 == 4 && l == 2 ? false : (i1 == 1 && l == 5 ? false : i1 != 2 || l != 4)));
        }
    }

    private boolean l(OWorld oworld, int i, int j, int k) {
        int l = oworld.g(i, j, k);

        return l == 5 && oworld.l(i, j - 1, k, 0) ? true : (l == 3 && oworld.l(i, j, k - 1, 2) ? true : (l == 4 && oworld.l(i, j, k + 1, 3) ? true : (l == 1 && oworld.l(i - 1, j, k, 4) ? true : l == 2 && oworld.l(i + 1, j, k, 5))));
    }

    public void b(OWorld oworld, int i, int j, int k, Random random) {
        boolean flag = this.l(oworld, i, j, k);
        List list = (List) b.get(oworld);

        while (list != null && !list.isEmpty() && oworld.E() - ((ORedstoneUpdateInfo) list.get(0)).d > 60L) {
            list.remove(0);
        }

        if (this.a) {
            if (flag) {
                oworld.d(i, j, k, OBlock.aS.cm, oworld.g(i, j, k));
                // CanaryMod: Allow redstone torches to provide power
                int current = (Integer) etc.getLoader().callHook(PluginLoader.Hook.REDSTONE_CHANGE, new Block(oworld.world, this.cm, i, j, k), 1, 0);

                if (current == 0) {
                oworld.d(i, j, k, OBlock.aS.cm, oworld.g(i, j, k));
                    if (this.a(oworld, i, j, k, true)) {
                    oworld.a((double) ((float) i + 0.5F), (double) ((float) j + 0.5F), (double) ((float) k + 0.5F), "random.fizz", 0.5F, 2.6F + (oworld.u.nextFloat() - oworld.u.nextFloat()) * 0.8F);

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
            oworld.d(i, j, k, OBlock.aT.cm, oworld.g(i, j, k));
        }
    }

    public void a(OWorld oworld, int i, int j, int k, int l) {
        super.a(oworld, i, j, k, l);
        oworld.a(i, j, k, this.cm, this.r_());
    }

    public boolean c(OIBlockAccess oiblockaccess, int i, int j, int k, int l) {
        return l == 0 ? this.b(oiblockaccess, i, j, k, l) : false;
    }

    public int a(int i, Random random, int j) {
        return OBlock.aT.cm;
    }

    public boolean i() {
        return true;
    }
}
