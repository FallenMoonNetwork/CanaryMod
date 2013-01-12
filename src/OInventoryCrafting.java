public class OInventoryCrafting implements OIInventory, Container<OItemStack>
{
    private OItemStack[] a;
    private int b;
    private OContainer c;
    
    // CanaryMod
    private String name = "container.crafting";
    
    public OInventoryCrafting(OContainer paramOContainer, int paramInt1, int paramInt2)
    {
        int i = paramInt1 * paramInt2;
        this.a = new OItemStack[i];
        this.c = paramOContainer;
        this.b = paramInt1;
    }
    
    public int k_() {
        return this.a.length;
    }
    
    public OItemStack a(int paramInt) {
        if (paramInt >= k_()) {
            return null;
        }
        return this.a[paramInt];
    }
    
    public OItemStack b(int paramInt1, int paramInt2) {
        if ((paramInt1 < 0) || (paramInt1 >= this.b)) {
            return null;
        }
        int i = paramInt1 + paramInt2 * this.b;
        return a(i);
    }
    
    public String b() {
        return name; // CanaryMod
    }
    
    public OItemStack a_(int paramInt) {
        if (this.a[paramInt] != null) {
            OItemStack localOItemStack = this.a[paramInt];
            this.a[paramInt] = null;
            return localOItemStack;
        }
        return null;
    }
    
    public OItemStack a(int paramInt1, int paramInt2) {
        if (this.a[paramInt1] != null) {
            OItemStack localOItemStack;
            if (this.a[paramInt1].a <= paramInt2) {
                localOItemStack = this.a[paramInt1];
                this.a[paramInt1] = null;
                this.c.a(this);
                return localOItemStack;
            }
            localOItemStack = this.a[paramInt1].a(paramInt2);
            if (this.a[paramInt1].a == 0) this.a[paramInt1] = null;
            this.c.a(this);
            return localOItemStack;
        }
        
        return null;
    }
    
    public void a(int paramInt, OItemStack paramOItemStack) {
        this.a[paramInt] = paramOItemStack;
        this.c.a(this);
    }
    
    public int c() {
        return 64;
    }
    
    public void d() {
    }
    
    public boolean a_(OEntityPlayer paramOEntityPlayer) {
        return true;
    }
    
    public void l_()
    {
    }
    
    public void f()
    {
    }
    
    // CanaryMod
    @Override
    public OItemStack[] getContents() {
        return this.a;
    }
    
    @Override
    public void setContents(OItemStack[] values) {
        this.a = values;
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