public class OItemSkull extends OItem {

    private static final String[] b = new String[] { "skeleton", "wither", "zombie", "char", "creeper"};
    public static final String[] a = new String[] { "skull_skeleton", "skull_wither", "skull_zombie", "skull_char", "skull_creeper"};

    public OItemSkull(int i) {
        super(i);
        this.a(OCreativeTabs.c);
        this.e(0);
        this.a(true);
    }

    public boolean a(OItemStack oitemstack, OEntityPlayer oentityplayer, OWorld oworld, int i, int j, int k, int l, float f, float f1, float f2) {
        if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, ((OEntityPlayerMP) oentityplayer).getPlayer(), null, getBlockInfo(oworld, i, j, k, l), new Item(oitemstack))) {
            return true;
        }

        if (l == 0) {
            return false;
        } else if (!oworld.g(i, j, k).a()) {
            return false;
        } else {
            if (l == 1) {
                ++j;
            }

            if (l == 2) {
                --k;
            }

            if (l == 3) {
                ++k;
            }

            if (l == 4) {
                --i;
            }

            if (l == 5) {
                ++i;
            }

            if (!oentityplayer.a(i, j, k, l, oitemstack)) {
                return false;
            } else if (!OBlock.ck.c(oworld, i, j, k)) {
                return false;
            } else {
                oworld.f(i, j, k, OBlock.ck.cz, l, 2);
                int i1 = 0;

                if (l == 1) {
                    i1 = OMathHelper.c((double) (oentityplayer.A * 16.0F / 360.0F) + 0.5D) & 15;
                }

                OTileEntity otileentity = oworld.r(i, j, k);

                if (otileentity != null && otileentity instanceof OTileEntitySkull) {
                    String s = "";

                    if (oitemstack.p() && oitemstack.q().b("SkullOwner")) {
                        s = oitemstack.q().i("SkullOwner");
                    }

                    ((OTileEntitySkull) otileentity).a(oitemstack.k(), s);
                    ((OTileEntitySkull) otileentity).a(i1);
                    ((OBlockSkull) OBlock.ck).a(oworld, i, j, k, (OTileEntitySkull) otileentity);
                }

                --oitemstack.a;
                return true;
            }
        }
    }

    public int a(int i) {
        return i;
    }

    public String d(OItemStack oitemstack) {
        int i = oitemstack.k();

        if (i < 0 || i >= b.length) {
            i = 0;
        }

        return super.a() + "." + b[i];
    }

    public String l(OItemStack oitemstack) {
        return oitemstack.k() == 3 && oitemstack.p() && oitemstack.q().b("SkullOwner") ? OStatCollector.a("item.skull.player.name", new Object[] { oitemstack.q().i("SkullOwner")}) : super.l(oitemstack);
    }
}
