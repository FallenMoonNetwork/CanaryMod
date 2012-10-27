import java.util.Iterator;
import java.util.List;
import java.util.Random;


public class OBlockPressurePlate extends OBlock {

    private OEnumMobType a;

    protected OBlockPressurePlate(int i, int j, OEnumMobType oenummobtype, OMaterial omaterial) {
        super(i, j, omaterial);
        this.a = oenummobtype;
        this.a(OCreativeTabs.d);
        this.b(true);
        float f = 0.0625F;

        this.a(f, 0.0F, f, 1.0F - f, 0.03125F, 1.0F - f);
    }

    public int r_() {
        return 20;
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

    public boolean c(OIBlockAccess oiblockaccess, int i, int j, int k) {
        return true;
    }

    public boolean b(OWorld oworld, int i, int j, int k) {
        return oworld.t(i, j - 1, k) || OBlockFence.c(oworld.a(i, j - 1, k));
    }

    public void a(OWorld oworld, int i, int j, int k, int l) {
        boolean flag = false;

        if (!oworld.t(i, j - 1, k) && !OBlockFence.c(oworld.a(i, j - 1, k))) {
            flag = true;
        }

        if (flag) {
            this.c(oworld, i, j, k, oworld.g(i, j, k), 0);
            oworld.e(i, j, k, 0);
        }

    }

    public void b(OWorld oworld, int i, int j, int k, Random random) {
        if (!oworld.J) {
            if (oworld.g(i, j, k) != 0) {
                this.l(oworld, i, j, k);
            }
        }
    }

    public void a(OWorld oworld, int i, int j, int k, OEntity oentity) {
        if (!oworld.J) {
            if (oworld.g(i, j, k) != 1) {
                this.l(oworld, i, j, k);
            }
        }
    }

    private void l(OWorld oworld, int i, int j, int k) {
        boolean flag = oworld.g(i, j, k) == 1;
        boolean flag1 = false;
        float f = 0.125F;
        List list = null;

        if (this.a == OEnumMobType.a) {
            list = oworld.b((OEntity) null, OAxisAlignedBB.a().a((double) ((float) i + f), (double) j, (double) ((float) k + f), (double) ((float) (i + 1) - f), (double) j + 0.25D, (double) ((float) (k + 1) - f)));
        }

        if (this.a == OEnumMobType.b) {
            list = oworld.a(OEntityLiving.class, OAxisAlignedBB.a().a((double) ((float) i + f), (double) j, (double) ((float) k + f), (double) ((float) (i + 1) - f), (double) j + 0.25D, (double) ((float) (k + 1) - f)));
        }

        if (this.a == OEnumMobType.c) {
            list = oworld.a(OEntityPlayer.class, OAxisAlignedBB.a().a((double) ((float) i + f), (double) j, (double) ((float) k + f), (double) ((float) (i + 1) - f), (double) j + 0.25D, (double) ((float) (k + 1) - f)));
        }

        if (!list.isEmpty()) {
            Iterator iterator = list.iterator();

            while (iterator.hasNext()) {
                OEntity oentity = (OEntity) iterator.next();

                if (!oentity.au()) {
            flag1 = true;
                    break;
                }
            }
        }

        // CanaryMod: Allow pressure plate interaction to power redstone
        if (flag1 != flag) {
            flag1 = (Integer) etc.getLoader().callHook(PluginLoader.Hook.REDSTONE_CHANGE, new Block(oworld.world, this.cm, i, j, k), flag ? 1 : 0, flag1 ? 1 : 0) > 0;
        }

        if (flag1 && !flag) {
            oworld.c(i, j, k, 1);
            oworld.h(i, j, k, this.cm);
            oworld.h(i, j - 1, k, this.cm);
            oworld.e(i, j, k, i, j, k);
            oworld.a((double) i + 0.5D, (double) j + 0.1D, (double) k + 0.5D, "random.click", 0.3F, 0.6F);
        }

        if (!flag1 && flag) {
            oworld.c(i, j, k, 0);
            oworld.h(i, j, k, this.cm);
            oworld.h(i, j - 1, k, this.cm);
            oworld.e(i, j, k, i, j, k);
            oworld.a((double) i + 0.5D, (double) j + 0.1D, (double) k + 0.5D, "random.click", 0.3F, 0.5F);
        }

        if (flag1) {
            oworld.a(i, j, k, this.cm, this.r_());
        }

    }

    public void a(OWorld oworld, int i, int j, int k, int l, int i1) {
        if (i1 > 0) {
            oworld.h(i, j, k, this.cm);
            oworld.h(i, j - 1, k, this.cm);
        }

        super.a(oworld, i, j, k, l, i1);
    }

    public void a(OIBlockAccess oiblockaccess, int i, int j, int k) {
        boolean flag = oiblockaccess.g(i, j, k) == 1;
        float f = 0.0625F;

        if (flag) {
            this.a(f, 0.0F, f, 1.0F - f, 0.03125F, 1.0F - f);
        } else {
            this.a(f, 0.0F, f, 1.0F - f, 0.0625F, 1.0F - f);
        }

    }

    public boolean b(OIBlockAccess oiblockaccess, int i, int j, int k, int l) {
        return oiblockaccess.g(i, j, k) > 0;
    }

    public boolean c(OIBlockAccess oiblockaccess, int i, int j, int k, int l) {
        return oiblockaccess.g(i, j, k) == 0 ? false : l == 1;
    }

    public boolean i() {
        return true;
    }

    public void f() {
        float f = 0.5F;
        float f1 = 0.125F;
        float f2 = 0.5F;

        this.a(0.5F - f, 0.5F - f1, 0.5F - f2, 0.5F + f, 0.5F + f1, 0.5F + f2);
    }

    public int q_() {
        return 1;
    }
}
