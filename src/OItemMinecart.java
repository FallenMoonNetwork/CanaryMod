public class OItemMinecart extends OItem {

    private static final OIBehaviorDispenseItem b = new OBehaviorDispenseMinecart();
    public int a;

    public OItemMinecart(int i, int j) {
        super(i);
        this.cq = 1;
        this.a = j;
        this.a(OCreativeTabs.e);
        OBlockDispenser.a.a(this, b);
    }

    public boolean a(OItemStack oitemstack, OEntityPlayer oentityplayer, OWorld oworld, int i, int j, int k, int l, float f, float f1, float f2) {
        int i1 = oworld.a(i, j, k);

        if (OBlockRailBase.d_(i1)) {
            if (!oworld.I) {
                OEntityMinecart oentityminecart = OEntityMinecart.a(oworld, (double) ((float) i + 0.5F), (double) ((float) j + 0.5F), (double) ((float) k + 0.5F), this.a);

                if (oitemstack.t()) {
                    oentityminecart.a(oitemstack.s());
                }

                // CanaryMod: placing of a mine cart
                Block block = this.getBlockInfo(oworld, i, j, k, l);
                Player player = ((OEntityPlayerMP) oentityplayer).getPlayer();

                if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, player, block, block, ((OEntityPlayerMP) oentityplayer).getPlayer().getItemStackInHand())) {
                    return false;
                }
                oworld.d((OEntity) oentityminecart);

            }

            --oitemstack.a;
            return true;
        } else {
            return false;
        }
    }
}
