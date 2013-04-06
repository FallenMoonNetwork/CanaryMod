import java.util.HashMap;
import java.util.Map;

public class OItemRecord extends OItem {

    private static final Map b = new HashMap();
    public final String a;

    protected OItemRecord(int i, String s) {
        super(i);
        this.a = s;
        this.cq = 1;
        this.a(OCreativeTabs.f);
        b.put(s, this);
    }

    public boolean a(OItemStack oitemstack, OEntityPlayer oentityplayer, OWorld oworld, int i, int j, int k, int l, float f, float f1, float f2) {
        if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, ((OEntityPlayerMP) oentityplayer).getPlayer(), null, this.getBlockInfo(oworld, i, j, k, l), ((OEntityPlayerMP) oentityplayer).getPlayer().getItemStackInHand())) {
            return true;
        }

        if (oworld.a(i, j, k) == OBlock.bc.cz && oworld.h(i, j, k) == 0) {
            if (oworld.I) {
                return true;
            } else {
                ((OBlockJukeBox) OBlock.bc).a(oworld, i, j, k, oitemstack);
                oworld.a((OEntityPlayer) null, 1005, i, j, k, this.cp);
                --oitemstack.a;
                return true;
            }
        } else {
            return false;
        }
    }
}
