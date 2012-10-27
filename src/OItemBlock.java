
public class OItemBlock extends OItem {

    private int a;

    public OItemBlock(int i) {
        super(i);
        this.a = i + 256;
        this.c(OBlock.p[i + 256].a(2));
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

        if (i1 == OBlock.aV.cm) {
            l = 1;
        } else if (i1 != OBlock.bx.cm && i1 != OBlock.aa.cm && i1 != OBlock.ab.cm) {
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
        } else if (j == 255 && OBlock.p[this.a].cB.a()) {
            return false;
        } else if (oworld.a(this.a, i, j, k, false, l, oentityplayer) // CanaryMod: prevent unwanted blocks from getting placed.
                && !(Boolean) etc.getLoader().callHook(PluginLoader.Hook.BLOCK_PLACE, ((OEntityPlayerMP) oentityplayer).getPlayer(), blockPlaced, blockClicked, new Item(oitemstack))) {
            OBlock oblock = OBlock.p[this.a];


            if (oworld.d(i, j, k, this.a, this.a(oitemstack.j()))) {
                if (oworld.a(i, j, k) == this.a) {
                    OBlock.p[this.a].a(oworld, i, j, k, l, f, f1, f2);
                    OBlock.p[this.a].a(oworld, i, j, k, (OEntityLiving) oentityplayer);
                }

                oworld.a((double) ((float) i + 0.5F), (double) ((float) j + 0.5F), (double) ((float) k + 0.5F), oblock.cz.b(), (oblock.cz.c() + 1.0F) / 2.0F, oblock.cz.d() * 0.8F);
                --oitemstack.a;
            }

            return true;
        } else {
            return false;
        }
    }

    public String c_(OItemStack oitemstack) {
        return OBlock.p[this.a].a();
    }

    public String a() {
        return OBlock.p[this.a].a();
    }
}
