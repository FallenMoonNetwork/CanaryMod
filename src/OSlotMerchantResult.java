public class OSlotMerchantResult extends OSlot {

    private final OInventoryMerchant a;
    private OEntityPlayer b;
    private int c;
    private final OIMerchant d;

    public OSlotMerchantResult(OEntityPlayer oentityplayer, OIMerchant oimerchant, OInventoryMerchant oinventorymerchant, int i, int j, int k) {
        super(oinventorymerchant, i, j, k);
        this.b = oentityplayer;
        this.d = oimerchant;
        this.a = oinventorymerchant;
    }

    public boolean a(OItemStack oitemstack) {
        return false;
    }

    public OItemStack a(int i) {
        if (this.e()) {
            this.c += Math.min(i, this.d().b);
        }

        return super.a(i);
    }

    protected void a(OItemStack oitemstack, int i) {
        this.c += i;
        this.b(oitemstack);
    }

    protected void b(OItemStack oitemstack) {
        oitemstack.a(this.b.q, this.b, this.c);
        this.c = 0;
    }

    public void a(OEntityPlayer oentityplayer, OItemStack oitemstack) { // CanaryMod: redirect to our version
        this.a(oentityplayer, oitemstack, false);
    }

    public boolean a(OEntityPlayer oentityplayer, OItemStack oitemstack, boolean heldShift) { // CanaryMod: add heldShift parameter
        OMerchantRecipe omerchantrecipe = this.a.i();
        if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.VILLAGER_TRADE, ((OEntityPlayerMP) oentityplayer).getPlayer(),
                    ((OEntityVillager) this.d).getEntity(), omerchantrecipe.getVillagerTrade())) {
            if (heldShift) {
                ((OEntityPlayerMP) oentityplayer).getPlayer().getInventory().removeItemOverStacks(new Item(omerchantrecipe.d()));
            } else {
                oentityplayer.bn.b((OItemStack) null);
            }
            return true;
        }
        this.b(oitemstack);

        if (omerchantrecipe != null) {
            OItemStack oitemstack1 = this.a.a(0);
            OItemStack oitemstack2 = this.a.a(1);

            if (this.a(omerchantrecipe, oitemstack1, oitemstack2) || this.a(omerchantrecipe, oitemstack2, oitemstack1)) {
                this.d.a(omerchantrecipe);
                if (oitemstack1 != null && oitemstack1.b <= 0) {
                    oitemstack1 = null;
                }

                if (oitemstack2 != null && oitemstack2.b <= 0) {
                    oitemstack2 = null;
                }

                this.a.a(0, oitemstack1);
                this.a.a(1, oitemstack2);
            }
        }
        return false;
    }

    private boolean a(OMerchantRecipe omerchantrecipe, OItemStack oitemstack, OItemStack oitemstack1) {
        OItemStack oitemstack2 = omerchantrecipe.a();
        OItemStack oitemstack3 = omerchantrecipe.b();

        if (oitemstack != null && oitemstack.d == oitemstack2.d) {
            if (oitemstack3 != null && oitemstack1 != null && oitemstack3.d == oitemstack1.d) {
                oitemstack.b -= oitemstack2.b;
                oitemstack1.b -= oitemstack3.b;
                return true;
            }

            if (oitemstack3 == null && oitemstack1 == null) {
                oitemstack.b -= oitemstack2.b;
                return true;
            }
        }

        return false;
    }
}
