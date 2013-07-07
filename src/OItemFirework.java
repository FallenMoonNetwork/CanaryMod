public class OItemFirework extends OItem {

    public OItemFirework(int i) {
        super(i);
    }

    public boolean a(OItemStack oitemstack, OEntityPlayer oentityplayer, OWorld oworld, int i, int j, int k, int l, float f, float f1, float f2) {
        if (!oworld.I) {
            // CanaryMod: Adding null check to not spawn null firework
            if (oitemstack.e == null) {
                return true;
            }
            // CanaryMod: End

            OEntityFireworkRocket oentityfireworkrocket = new OEntityFireworkRocket(oworld, (double) ((float) i + f), (double) ((float) j + f1), (double) ((float) k + f2), oitemstack);
            oworld.d((OEntity) oentityfireworkrocket);
            if (!oentityplayer.bG.d) {
                --oitemstack.b;
            }

            return true;
        } else {
            return false;
        }
    }
}
