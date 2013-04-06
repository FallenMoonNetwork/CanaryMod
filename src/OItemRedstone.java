public class OItemRedstone extends OItem {

    public OItemRedstone(int i) {
        super(i);
        this.a(OCreativeTabs.d);
    }

    public boolean a(OItemStack oitemstack, OEntityPlayer oentityplayer, OWorld oworld, int i, int j, int k, int l, float f, float f1, float f2) {

        // CanaryMod: Store block data clicked
        Block blockClicked = this.getBlockInfo(oworld, i, j, k, l);

        if (oworld.a(i, j, k) != OBlock.aW.cz) {
            if (l == 0) {
                --j;
            }

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

            if (!oworld.c(i, j, k)) {
                return false;
            }
        }

        if (!oentityplayer.a(i, j, k, l, oitemstack)) {
            return false;
        } else {
            if (OBlock.az.c(oworld, i, j, k)) {
                // CanaryMod: Redstone dust hook!
                Block blockPlaced = new Block(oworld.world, Block.Type.RedstoneWire.getType(), i, j, k);
                Player player = ((OEntityPlayerMP) oentityplayer).getPlayer();

                if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, player, blockPlaced, blockClicked, ((OEntityPlayerMP) oentityplayer).getPlayer().getItemStackInHand())) {
                    return false;
                }

                --oitemstack.a;
                oworld.c(i, j, k, OBlock.az.cz);
            }

            return true;
        }
    }
}
