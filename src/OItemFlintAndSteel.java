public class OItemFlintAndSteel extends OItem {

    public OItemFlintAndSteel(int i) {
        super(i);
        this.cq = 1;
        this.e(64);
        this.a(OCreativeTabs.i);
    }

    public boolean a(OItemStack oitemstack, OEntityPlayer oentityplayer, OWorld oworld, int i, int j, int k, int l, float f, float f1, float f2) {
        // CanaryMod: Store block data clicked
        Block blockClicked = this.getBlockInfo(oworld, i, j, k, l);

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

        if (!oentityplayer.a(i, j, k, l, oitemstack)) {
            return false;
        } else {
            int i1 = oworld.a(i, j, k);

            if (i1 == 0) {

                // CanaryMod: Hook to control ignites AND ligther use
                Block blockPlaced = new Block(oworld.world, Block.Type.Fire.getType(), i, j, k);
                Player player = ((OEntityPlayerMP) oentityplayer).getPlayer();

                Boolean preventLighter = (Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, player, blockPlaced, blockClicked, ((OEntityPlayerMP) oentityplayer).getPlayer().getItemStackInHand());

                blockPlaced.setStatus(2); // Specifically to mark this ignite as from a lighter
                Boolean preventIgnite = (Boolean) etc.getLoader().callHook(PluginLoader.Hook.IGNITE, blockPlaced, player);

                if (preventIgnite || preventLighter) {
                    return false;
                }

                if (i1 == 0) {
                    oworld.a((double) i + 0.5D, (double) j + 0.5D, (double) k + 0.5D, "fire.ignite", 1.0F, e.nextFloat() * 0.4F + 0.8F);
                    oworld.c(i, j, k, OBlock.av.cz);
                }
            }

            oitemstack.a(1, (OEntityLiving) oentityplayer);
            return true;
        }
    }
}
