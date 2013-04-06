public class OItemReed extends OItem {

    private int a;

    public OItemReed(int i, OBlock oblock) {
        super(i);
        this.a = oblock.cz;
    }

    public boolean a(OItemStack oitemstack, OEntityPlayer oentityplayer, OWorld oworld, int i, int j, int k, int l, float f, float f1, float f2) {
        // CanaryMod: Store blockClicked
        Block blockClicked = this.getBlockInfo(oworld, i, j, k, l);
        int i1 = oworld.a(i, j, k);

        if (i1 == OBlock.aW.cz && (oworld.h(i, j, k) & 7) < 1) {
            l = 1;
        } else if (i1 != OBlock.by.cz && i1 != OBlock.ab.cz && i1 != OBlock.ac.cz) {
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
        }

        if (!oentityplayer.a(i, j, k, l, oitemstack)) {
            return false;
        } else if (oitemstack.a == 0) {
            return false;
        } else {
            if (oworld.a(this.a, i, j, k, false, l, (OEntity) null, oitemstack)) {
                // CanaryMod: Reed placement
                Block blockPlaced = new Block(oworld.world, this.a, i, j, k);

                Player player = ((OEntityPlayerMP) oentityplayer).getPlayer();

                if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, player, blockPlaced, blockClicked, ((OEntityPlayerMP) oentityplayer).getPlayer().getItemStackInHand())) {
                    return false;
                }

                OBlock oblock = OBlock.r[this.a];
                int j1 = oblock.a(oworld, i, j, k, l, f, f1, f2, 0);

                if (oworld.f(i, j, k, this.a, j1, 3)) {
                    if (oworld.a(i, j, k) == this.a) {
                        OBlock.r[this.a].a(oworld, i, j, k, (OEntityLiving) oentityplayer, oitemstack);
                        OBlock.r[this.a].k(oworld, i, j, k, j1);
                    }

                    oworld.a((double) ((float) i + 0.5F), (double) ((float) j + 0.5F), (double) ((float) k + 0.5F), oblock.cM.b(), (oblock.cM.c() + 1.0F) / 2.0F, oblock.cM.d() * 0.8F);
                    --oitemstack.a;
                }
            }

            return true;
        }
    }
}
