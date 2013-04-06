public class OInventoryMerchant implements OIInventory, Container<OItemStack> {

    private final OIMerchant a;
    private OItemStack[] b = new OItemStack[3];
    private final OEntityPlayer c;
    private OMerchantRecipe d;
    private int e;

    // CanaryMod
    private String name = "mob.villager";

    public OInventoryMerchant(OEntityPlayer oentityplayer, OIMerchant oimerchant) {
        this.c = oentityplayer;
        this.a = oimerchant;
    }

    public int j_() {
        return this.b.length;
    }

    public OItemStack a(int i) {
        return this.b[i];
    }

    public OItemStack a(int i, int j) {
        if (this.b[i] != null) {
            OItemStack oitemstack;

            if (i == 2) {
                oitemstack = this.b[i];
                this.b[i] = null;
                return oitemstack;
            } else if (this.b[i].a <= j) {
                oitemstack = this.b[i];
                this.b[i] = null;
                if (this.d(i)) {
                    this.h();
                }

                return oitemstack;
            } else {
                oitemstack = this.b[i].a(j);
                if (this.b[i].a == 0) {
                    this.b[i] = null;
                }

                if (this.d(i)) {
                    this.h();
                }

                return oitemstack;
            }
        } else {
            return null;
        }
    }

    private boolean d(int i) {
        return i == 0 || i == 1;
    }

    public OItemStack b(int i) {
        if (this.b[i] != null) {
            OItemStack oitemstack = this.b[i];

            this.b[i] = null;
            return oitemstack;
        } else {
            return null;
        }
    }

    public void a(int i, OItemStack oitemstack) {
        this.b[i] = oitemstack;
        if (oitemstack != null && oitemstack.a > this.d()) {
            oitemstack.a = this.d();
        }

        if (this.d(i)) {
            this.h();
        }
    }

    public String b(){
        //Canarymod:
        return name;//
    }

    public boolean c() {
        return false;
    }

    public int d() {
        return 64;
    }

    public boolean a(OEntityPlayer oentityplayer) {
        return this.a.m_() == oentityplayer;
    }

    public void f() {}

    public void g() {}

    public boolean b(int i, OItemStack oitemstack) {
        return true;
    }

    public void k_() {
        this.h();
    }

    public void h() {
        this.d = null;
        OItemStack oitemstack = this.b[0];
        OItemStack oitemstack1 = this.b[1];

        if (oitemstack == null) {
            oitemstack = oitemstack1;
            oitemstack1 = null;
        }

        if (oitemstack == null) {
            this.a(2, (OItemStack) null);
        } else {
            OMerchantRecipeList omerchantrecipelist = this.a.b(this.c);

            if (omerchantrecipelist != null) {
                OMerchantRecipe omerchantrecipe = omerchantrecipelist.a(oitemstack, oitemstack1, this.e);

                if (omerchantrecipe != null && !omerchantrecipe.g()) {
                    this.d = omerchantrecipe;
                    this.a(2, omerchantrecipe.d().m());
                } else if (oitemstack1 != null) {
                    omerchantrecipe = omerchantrecipelist.a(oitemstack1, oitemstack, this.e);
                    if (omerchantrecipe != null && !omerchantrecipe.g()) {
                        this.d = omerchantrecipe;
                        this.a(2, omerchantrecipe.d().m());
                    } else {
                        this.a(2, (OItemStack) null);
                    }
                } else {
                    this.a(2, (OItemStack) null);
                }
            }
        }
    }

    public OMerchantRecipe i() {
        return this.d;
    }

    public void c(int i) {
        this.e = i;
        this.h();
    }

    // CanaryMod
    @Override
    public OItemStack[] getContents() {
        return this.b;
    }

    @Override
    public void setContents(OItemStack[] values) {
        this.b = values;
    }

    @Override
    public OItemStack getContentsAt(int index) {
        return this.a(index);
    }

    @Override
    public void setContentsAt(int index, OItemStack value) {
        this.a(index, value);
    }

    @Override
    public int getContentsSize() {
        return this.j_();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String value) {
        name = value;
    }
}
