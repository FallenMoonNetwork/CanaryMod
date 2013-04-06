public class OItemHoe extends OItem {

    protected OEnumToolMaterial a;

    public OItemHoe(int i, OEnumToolMaterial oenumtoolmaterial) {
        super(i);
        this.a = oenumtoolmaterial;
        this.cq = 1;
        this.e(oenumtoolmaterial.a());
        this.a(OCreativeTabs.i);
    }

    public boolean a(OItemStack oitemstack, OEntityPlayer oentityplayer, OWorld oworld, int i, int j, int k, int l, float f, float f1, float f2) {
        if (!oentityplayer.a(i, j, k, l, oitemstack)) {
            return false;
        } else {
            int i1 = oworld.a(i, j, k);
            int j1 = oworld.a(i, j + 1, k);

            if ((l == 0 || j1 != 0 || i1 != OBlock.y.cz) && i1 != OBlock.z.cz) {
                return false;
            } else {
                // CanaryMod: Hoes
                Block blockClicked = this.getBlockInfo(oworld, i, j, k, l);
                Block blockPlaced = oworld.world.getBlockAt(i, j+i, k);

                // Call the hook
                if (oentityplayer instanceof OEntityPlayerMP) {
                    Player player = ((OEntityPlayerMP) oentityplayer).getPlayer();

                    if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, player, blockPlaced, blockClicked, ((OEntityPlayerMP) oentityplayer).getPlayer().getItemStackInHand())) {
                        return false;
                    }
                }

                OBlock oblock = OBlock.aE;

                oworld.a((double) ((float) i + 0.5F), (double) ((float) j + 0.5F), (double) ((float) k + 0.5F), oblock.cM.e(), (oblock.cM.c() + 1.0F) / 2.0F, oblock.cM.d() * 0.8F);
                if (oworld.I) {
                    return true;
                } else {
                    oworld.c(i, j, k, oblock.cz);
                    oitemstack.a(1, (OEntityLiving) oentityplayer);
                    return true;
                }
            }
        }
    }

    public String g() {
        return this.a.toString();
    }
}
