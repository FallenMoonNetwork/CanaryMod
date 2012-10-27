
public class OItemHangingEntity extends OItem {


    private final Class a;

    public OItemHangingEntity(int i, Class oclass) {
        super(i);
        this.a = oclass;
        this.a(OCreativeTabs.c);
    }

    public boolean a(OItemStack oitemstack, OEntityPlayer oentityplayer, OWorld oworld, int i, int j, int k, int l, float f, float f1, float f2) {
        // CanaryMod: store clicked block data
        Block blockClicked = this.getBlockInfo(oworld, i, j, k, l);

        if (l == 0) {
            return false;
        } else if (l == 1) {
            return false;
        } else {
            int i1 = ODirection.e[l];
            OEntityHanging oentityhanging = this.a(oworld, i, j, k, i1);

            if (!oentityplayer.a(i, j, k, l, oitemstack)) {
                return false;
            } else {

                if (oentityhanging != null && oentityhanging.c()) {
                    if (!oworld.J) {
                        // CanaryMod: Painting place hook
                        if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, ((OEntityPlayerMP) oentityplayer).getPlayer(), null, blockClicked, new Item(oitemstack))) {
                            return false;
                        }
                        oworld.d((OEntity) oentityhanging);

                    }

                    --oitemstack.a;
                }

                return true;
            }
        }
    }

    private OEntityHanging a(OWorld oworld, int i, int j, int k, int l) {
        return (OEntityHanging) (this.a == OEntityPainting.class ? new OEntityPainting(oworld, i, j, k, l) : (this.a == OEntityItemFrame.class ? new OEntityItemFrame(oworld, i, j, k, l) : null));
    }
}
