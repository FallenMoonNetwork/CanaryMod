
public class OItemSeeds extends OItem {

    private int a;
    private int b;

    public OItemSeeds(int i, int j, int k) {
        super(i);
        this.a = j;
        this.b = k;
        this.a(OCreativeTabs.l);
    }

    public boolean a(OItemStack oitemstack, OEntityPlayer oentityplayer, OWorld oworld, int i, int j, int k, int l, float f, float f1, float f2) {
        if (l != 1) {
            return false;
        } else if (oentityplayer.a(i, j, k, l, oitemstack) && oentityplayer.a(i, j + 1, k, l, oitemstack)) {
            int i1 = oworld.a(i, j, k);

            if (i1 == this.b && oworld.c(i, j + 1, k)) {
                // CanaryMod: Seeds
                Block blockClicked = this.getBlockInfo(oworld, i, j, k, l);
                
                Block blockPlaced = oworld.world.getBlockAt(i, j + 1, k);

                // Call the hook
                Player player = ((OEntityPlayerMP) oentityplayer).getPlayer();

                if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, player, blockPlaced, blockClicked, new Item(oitemstack))) {
                    return false;
                }
                
                oworld.e(i, j + 1, k, this.a);
                --oitemstack.a;
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
