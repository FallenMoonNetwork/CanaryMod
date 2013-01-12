public class OInventoryMerchant implements OIInventory, Container<OItemStack>
{
    private final OIMerchant a;
    private OItemStack[] b = new OItemStack[3];
    private final OEntityPlayer c;
    private OMerchantRecipe d;
    private int e;
    
    // CanaryMod
    private String name = "mob.villager";
    
    public OInventoryMerchant(OEntityPlayer paramOEntityPlayer, OIMerchant paramOIMerchant)
    {
        this.c = paramOEntityPlayer;
        this.a = paramOIMerchant;
    }
    
    public int k_() {
        return this.b.length;
    }
    
    public OItemStack a(int paramInt) {
        return this.b[paramInt];
    }
    
    public OItemStack a(int paramInt1, int paramInt2) {
        if (this.b[paramInt1] != null) {
            OItemStack localOItemStack;
            if (paramInt1 == 2) {
                localOItemStack = this.b[paramInt1];
                this.b[paramInt1] = null;
                return localOItemStack;
            }
            if (this.b[paramInt1].a <= paramInt2) {
                localOItemStack = this.b[paramInt1];
                this.b[paramInt1] = null;
                if (d(paramInt1)) {
                    g();
                }
                return localOItemStack;
            }
            localOItemStack = this.b[paramInt1].a(paramInt2);
            if (this.b[paramInt1].a == 0) this.b[paramInt1] = null;
            if (d(paramInt1)) {
                g();
            }
            return localOItemStack;
        }
        
        return null;
    }
    
    private boolean d(int paramInt) {
        return (paramInt == 0) || (paramInt == 1);
    }
    
    public OItemStack a_(int paramInt) {
        if (this.b[paramInt] != null) {
            OItemStack localOItemStack = this.b[paramInt];
            this.b[paramInt] = null;
            return localOItemStack;
        }
        return null;
    }
    
    public void a(int paramInt, OItemStack paramOItemStack) {
        this.b[paramInt] = paramOItemStack;
        if ((paramOItemStack != null) && (paramOItemStack.a > c())) paramOItemStack.a = c();
        if (d(paramInt))
            g();
    }
    
    public String b()
    {
        return name;
    }
    
    public int c() {
        return 64;
    }
    
    public boolean a_(OEntityPlayer paramOEntityPlayer) {
        return this.a.m_() == paramOEntityPlayer;
    }
    
    public void l_() {
    }
    
    public void f() {
    }
    
    public void d() {
        g();
    }
    
    public void g() {
        this.d = null;
        
        Object localObject = this.b[0];
        OItemStack localOItemStack = this.b[1];
        
        if (localObject == null) {
            localObject = localOItemStack;
            localOItemStack = null;
        }
        
        if (localObject == null) {
            a(2, null);
        } else {
            OMerchantRecipeList localOMerchantRecipeList = this.a.b(this.c);
            if (localOMerchantRecipeList != null) {
                OMerchantRecipe localOMerchantRecipe = localOMerchantRecipeList.a((OItemStack)localObject, localOItemStack, this.e);
                if ((localOMerchantRecipe != null) && (!localOMerchantRecipe.g())) {
                    this.d = localOMerchantRecipe;
                    a(2, localOMerchantRecipe.d().l());
                } else if (localOItemStack != null)
                {
                    localOMerchantRecipe = localOMerchantRecipeList.a(localOItemStack, (OItemStack)localObject, this.e);
                    if ((localOMerchantRecipe != null) && (!localOMerchantRecipe.g())) {
                        this.d = localOMerchantRecipe;
                        a(2, localOMerchantRecipe.d().l());
                    } else {
                        a(2, null);
                    }
                }
                else {
                    a(2, null);
                }
            }
        }
    }
    
    public OMerchantRecipe h() {
        return this.d;
    }
    
    public void c(int paramInt) {
        this.e = paramInt;
        g();
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
        return this.k_();
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