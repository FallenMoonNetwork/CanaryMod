public class OItemHoe extends OItem {

    protected OEnumToolMaterial a;

    public OItemHoe(int i, OEnumToolMaterial oenumtoolmaterial) {
        super(i);
        this.a = oenumtoolmaterial;
        this.cw = 1;
        this.e(oenumtoolmaterial.a());
        this.a(OCreativeTabs.i);
    }

    public boolean a(OItemStack oitemstack, OEntityPlayer oentityplayer, OWorld oworld, int i, int j, int k, int l, float f, float f1, float f2) {
        if (!oentityplayer.a(i, j, k, l, oitemstack)) {
            return false;
        } else {
            int i1 = oworld.a(i, j, k);
            int j1 = oworld.a(i, j + 1, k);

            if (l != 0 && j1 == 0 && (i1 == OBlock.z.cF || i1 == OBlock.A.cF)) {
                // CanaryMod: Hoes
                Block blockClicked = this.getBlockInfo(oworld, i, j, k, l);
                Block blockPlaced = blockClicked.getFace(blockClicked.getFaceClicked());

                // Call the hook
                if (oentityplayer instanceof OEntityPlayerMP) {
                    Player player = ((OEntityPlayerMP) oentityplayer).getPlayer();

                    if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, player, blockPlaced, blockClicked, ((OEntityPlayerMP) oentityplayer).getPlayer().getItemStackInHand())) {
                        return false;
                    }
                }

                OBlock oblock = OBlock.aF;

                oworld.a((double) ((float) i + 0.5F), (double) ((float) j + 0.5F), (double) ((float) k + 0.5F), oblock.cS.e(), (oblock.cS.c() + 1.0F) / 2.0F, oblock.cS.d() * 0.8F);
                if (oworld.I) {
                    return true;
                } else {
                    oworld.c(i, j, k, oblock.cF);
                    oitemstack.a(1, (OEntityLivingBase) oentityplayer);
                    return true;
                }
            } else {
                return false;
            }
        }
    }

    public String g() {
        return this.a.toString();
    }
}
