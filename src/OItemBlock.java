public class OItemBlock extends OItem {

    private int a;

    public OItemBlock(int i) {
        super(i);
        this.a = i + 256;
    }

    public int g() {
        return this.a;
    }

    public boolean a(OItemStack oitemstack, OEntityPlayer oentityplayer, OWorld oworld, int i, int j, int k, int l, float f, float f1, float f2) {
        // CanaryMod: Bail if we have nothing of the items in hand
        if (oitemstack.a == 0) {
            return false;
        }
        // CanaryMod: Store blockInfo of the one we clicked
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

        // CanaryMod: Store faceClicked (must be here to have the 'snow' special case).
        blockClicked.setFaceClicked(Block.Face.fromId(l));
        // CanaryMod: And the block we're about to place
        Block blockPlaced = new Block(oworld.world, this.a, i, j, k);

        if (oitemstack.a == 0) {
            return false;
        } else if (!oentityplayer.a(i, j, k, l, oitemstack)) {
            return false;
        } else if (j == 255 && OBlock.r[this.a].cO.a()) {
            return false;
        } else if (oworld.a(this.a, i, j, k, false, l, oentityplayer, oitemstack)// CanaryMod: prevent unwanted blocks from getting placed.
                && !(Boolean) etc.getLoader().callHook(PluginLoader.Hook.BLOCK_PLACE, ((OEntityPlayerMP) oentityplayer).getPlayer(), blockPlaced, blockClicked, new Item(oitemstack))) {
            OBlock oblock = OBlock.r[this.a];
            int j1 = this.a(oitemstack.k());
            int k1 = OBlock.r[this.a].a(oworld, i, j, k, l, f, f1, f2, j1);

            if (oworld.f(i, j, k, this.a, k1, 3)) {
                if (oworld.a(i, j, k) == this.a) {
                    OBlock.r[this.a].a(oworld, i, j, k, (OEntityLiving) oentityplayer, oitemstack);
                    OBlock.r[this.a].k(oworld, i, j, k, k1);
                }

                oworld.a((double) ((float) i + 0.5F), (double) ((float) j + 0.5F), (double) ((float) k + 0.5F), oblock.cM.b(), (oblock.cM.c() + 1.0F) / 2.0F, oblock.cM.d() * 0.8F);
                --oitemstack.a;
            }

            return true;
        } else {
            return false;
        }
    }

    public String d(OItemStack oitemstack) {
        return OBlock.r[this.a].a();
    }

    public String a() {
        return OBlock.r[this.a].a();
    }
}
