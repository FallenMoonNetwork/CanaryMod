public class OItemSlab extends OItemBlock {

    private final boolean a;
    private final OBlockHalfSlab b;
    private final OBlockHalfSlab c;

    public OItemSlab(int i, OBlockHalfSlab oblockhalfslab, OBlockHalfSlab oblockhalfslab1, boolean flag) {
        super(i);
        this.b = oblockhalfslab;
        this.c = oblockhalfslab1;
        this.a = flag;
        this.e(0);
        this.a(true);
    }

    public int a(int i) {
        return i;
    }

    public String d(OItemStack oitemstack) {
        return this.b.c(oitemstack.k());
    }

    public boolean a(OItemStack oitemstack, OEntityPlayer oentityplayer, OWorld oworld, int i, int j, int k, int l, float f, float f1, float f2) {
        // CanaryMod: We don't want ITEM_USE to be called twice.
        if (!this.a && (Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, ((OEntityPlayerMP) oentityplayer).getPlayer(), null, this.getBlockInfo(oworld, i, j, k, l), ((OEntityPlayerMP) oentityplayer).getPlayer().getItemStackInHand())) {
            return true;
        }

        if (this.a) {
            return super.a(oitemstack, oentityplayer, oworld, i, j, k, l, f, f1, f2);
        } else if (oitemstack.a == 0) {
            return false;
        } else if (!oentityplayer.a(i, j, k, l, oitemstack)) {
            return false;
        } else {
            int i1 = oworld.a(i, j, k);
            int j1 = oworld.h(i, j, k);
            int k1 = j1 & 7;
            boolean flag = (j1 & 8) != 0;

            if ((l == 1 && !flag || l == 0 && flag) && i1 == this.b.cz && k1 == oitemstack.k()) {
                if (oworld.b(this.c.b(oworld, i, j, k)) && oworld.f(i, j, k, this.c.cz, k1, 3)) {
                    oworld.a((double) ((float) i + 0.5F), (double) ((float) j + 0.5F), (double) ((float) k + 0.5F), this.c.cM.b(), (this.c.cM.c() + 1.0F) / 2.0F, this.c.cM.d() * 0.8F);
                    --oitemstack.a;
                }

                return true;
            } else {
                return this.a(oitemstack, oentityplayer, oworld, i, j, k, l) ? true : super.a(oitemstack, oentityplayer, oworld, i, j, k, l, f, f1, f2);
            }
        }
    }

    private boolean a(OItemStack oitemstack, OEntityPlayer oentityplayer, OWorld oworld, int i, int j, int k, int l) {
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

        int i1 = oworld.a(i, j, k);
        int j1 = oworld.h(i, j, k);
        int k1 = j1 & 7;

        if (i1 == this.b.cz && k1 == oitemstack.k()) {
            if (oworld.b(this.c.b(oworld, i, j, k)) && oworld.f(i, j, k, this.c.cz, k1, 3)) {
                oworld.a((double) ((float) i + 0.5F), (double) ((float) j + 0.5F), (double) ((float) k + 0.5F), this.c.cM.b(), (this.c.cM.c() + 1.0F) / 2.0F, this.c.cM.d() * 0.8F);
                --oitemstack.a;
            }

            return true;
        } else {
            return false;
        }
    }
}
