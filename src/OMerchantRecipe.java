public class OMerchantRecipe {

    protected OItemStack a; // CanaryMod: private -> protected
    protected OItemStack b; // CanaryMod: private -> protected
    protected OItemStack c; // CanaryMod: private -> protected
    private int d;
    private int e;

    public OMerchantRecipe(ONBTTagCompound onbttagcompound) {
        this.a(onbttagcompound);
    }

    public OMerchantRecipe(OItemStack oitemstack, OItemStack oitemstack1, OItemStack oitemstack2) {
        this.a = oitemstack;
        this.b = oitemstack1;
        this.c = oitemstack2;
        this.e = 7;
    }

    public OMerchantRecipe(OItemStack oitemstack, OItemStack oitemstack1) {
        this(oitemstack, (OItemStack) null, oitemstack1);
    }

    public OMerchantRecipe(OItemStack oitemstack, OItem oitem) {
        this(oitemstack, new OItemStack(oitem));
    }

    public OItemStack a() {
        return this.a;
    }

    public OItemStack b() {
        return this.b;
    }

    public boolean c() {
        return this.b != null;
    }

    public OItemStack d() {
        return this.c;
    }

    public boolean a(OMerchantRecipe omerchantrecipe) {
        return this.a.c == omerchantrecipe.a.c && this.c.c == omerchantrecipe.c.c ? this.b == null && omerchantrecipe.b == null || this.b != null && omerchantrecipe.b != null && this.b.c == omerchantrecipe.b.c : false;
    }

    public boolean b(OMerchantRecipe omerchantrecipe) {
        return this.a(omerchantrecipe) && (this.a.a < omerchantrecipe.a.a || this.b != null && this.b.a < omerchantrecipe.b.a);
    }

    public void f() {
        ++this.d;
    }

    public void a(int i) {
        this.e += i;
    }

    public boolean g() {
        return this.d >= this.e;
    }

    public void a(ONBTTagCompound onbttagcompound) {
        ONBTTagCompound onbttagcompound1 = onbttagcompound.l("buy");

        this.a = OItemStack.a(onbttagcompound1);
        ONBTTagCompound onbttagcompound2 = onbttagcompound.l("sell");

        this.c = OItemStack.a(onbttagcompound2);
        if (onbttagcompound.b("buyB")) {
            this.b = OItemStack.a(onbttagcompound.l("buyB"));
        }

        if (onbttagcompound.b("uses")) {
            this.d = onbttagcompound.e("uses");
        }

        if (onbttagcompound.b("maxUses")) {
            this.e = onbttagcompound.e("maxUses");
        } else {
            this.e = 7;
        }
    }

    public ONBTTagCompound i() {
        ONBTTagCompound onbttagcompound = new ONBTTagCompound();

        onbttagcompound.a("buy", this.a.b(new ONBTTagCompound("buy")));
        onbttagcompound.a("sell", this.c.b(new ONBTTagCompound("sell")));
        if (this.b != null) {
            onbttagcompound.a("buyB", this.b.b(new ONBTTagCompound("buyB")));
        }

        onbttagcompound.a("uses", this.d);
        onbttagcompound.a("maxUses", this.e);
        return onbttagcompound;
    }
}
