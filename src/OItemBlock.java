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
        if (oitemstack.b == 0) {
            return false;
        }
        // CanaryMod: Store blockInfo of the one we clicked
        Block blockClicked = this.getBlockInfo(oworld, i, j, k, l);

        int i1 = oworld.a(i, j, k);

        if (i1 == OBlock.aX.cF && (oworld.h(i, j, k) & 7) < 1) {
            l = 1;
        } else if (i1 != OBlock.bz.cF && i1 != OBlock.ac.cF && i1 != OBlock.ad.cF) {
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

        if (oitemstack.b == 0) {
            return false;
        } else if (!oentityplayer.a(i, j, k, l, oitemstack)) {
            return false;
        } else if (j == 255 && OBlock.s[this.a].cU.a()) {
            return false;
        } else if (oworld.a(this.a, i, j, k, false, l, oentityplayer, oitemstack) // CanaryMod: prevent unwanted blocks from getting placed.
                && !(Boolean) etc.getLoader().callHook(PluginLoader.Hook.BLOCK_PLACE, ((OEntityPlayerMP) oentityplayer).getPlayer(), blockPlaced, blockClicked, new Item(oitemstack))) {
            OBlock oblock = OBlock.s[this.a];
            int j1 = this.a(oitemstack.k());
            int k1 = OBlock.s[this.a].a(oworld, i, j, k, l, f, f1, f2, j1);

            if (oworld.f(i, j, k, this.a, k1, 3)) {
                if (oworld.a(i, j, k) == this.a) {
                    OBlock.s[this.a].a(oworld, i, j, k, (OEntityLivingBase) oentityplayer, oitemstack);
                    OBlock.s[this.a].k(oworld, i, j, k, k1);
                }

                oworld.a((double) ((float) i + 0.5F), (double) ((float) j + 0.5F), (double) ((float) k + 0.5F), oblock.cS.b(), (oblock.cS.c() + 1.0F) / 2.0F, oblock.cS.d() * 0.8F);
                --oitemstack.b;
            }

            return true;
        } else {
            return false;
        }
    }

    public String d(OItemStack oitemstack) {
        return OBlock.s[this.a].a();
    }

    public String a() {
        return OBlock.s[this.a].a();
    }
}
