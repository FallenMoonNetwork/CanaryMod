
public class OItemMonsterPlacer extends OItem {

    public OItemMonsterPlacer(int i) {
        super(i);
        this.a(true);
        this.a(OCreativeTabs.f);
    }

    public String j(OItemStack oitemstack) {
        String s = ("" + OStatCollector.a(this.a() + ".name")).trim();
        String s1 = OEntityList.a(oitemstack.j());

        if (s1 != null) {
            s = s + " " + OStatCollector.a("entity." + s1 + ".name");
        }

        return s;
    }

    public boolean a(OItemStack oitemstack, OEntityPlayer oentityplayer, OWorld oworld, int i, int j, int k, int l, float f, float f1, float f2) {
        // CanaryMod: deny hackish eggs, call onItemUse
        if (oworld.J || oitemstack.j() < 50 || oitemstack.j() >= 200 || (Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, ((OEntityPlayerMP) oentityplayer).getPlayer(), this.getBlockInfo(oworld, i, j, k, l), null, new Item(oitemstack))) {
            return true;
        } else {
            int i1 = oworld.a(i, j, k);

            i += OFacing.b[l];
            j += OFacing.c[l];
            k += OFacing.d[l];
            double d0 = 0.0D;

            if (l == 1 && i1 == OBlock.bc.cm || i1 == OBlock.bE.cm) {
                d0 = 0.5D;
            }

            if (a(oworld, oitemstack.j(), (double) i + 0.5D, (double) j + d0, (double) k + 0.5D) != null && !oentityplayer.cf.d) {
                --oitemstack.a;
            }

            return true;
        }
    }

    public static OEntity a(OWorld oworld, int i, double d0, double d1, double d2) {
        if (!OEntityList.a.containsKey(Integer.valueOf(i))) {
            return null;
        } else {
            OEntity oentity = null;

            for (int j = 0; j < 1; ++j) {
                oentity = OEntityList.a(i, oworld);
            if (oentity != null) {
                    oentity.b(d0, d1, d2, oworld.u.nextFloat() * 360.0F, 0.0F);
                    ((OEntityLiving) oentity).bD();
                oworld.d(oentity);
                    ((OEntityLiving) oentity).aN();
                }
            }

            return oentity;
        }
    }
}
