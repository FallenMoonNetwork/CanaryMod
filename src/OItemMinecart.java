
public class OItemMinecart extends OItem {

    public int a;

    public OItemMinecart(int i, int j) {
        super(i);
        this.cg = 1;
        this.a = j;
        this.a(OCreativeTabs.e);
    }

    public boolean a(OItemStack oitemstack, OEntityPlayer oentityplayer, OWorld oworld, int i, int j, int k, int l, float f, float f1, float f2) {
        int i1 = oworld.a(i, j, k);

        if (OBlockRail.d(i1)) {

            if (!oworld.J) {
                // CanaryMod: placing of a mine cart
                Block block = this.getBlockInfo(oworld, i, j, k, l);
                Player player = ((OEntityPlayerMP) oentityplayer).getPlayer();

                if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, player, block, block, new Item(oitemstack))) {
                    return false;
                }
                oworld.d((OEntity) (new OEntityMinecart(oworld, (double) ((float) i + 0.5F), (double) ((float) j + 0.5F), (double) ((float) k + 0.5F), this.a)));
            }

            --oitemstack.a;
            return true;
        } else {
            return false;
        }
    }
}
