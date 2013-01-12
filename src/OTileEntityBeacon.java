import java.util.Iterator;
import java.util.List;

public class OTileEntityBeacon extends OTileEntity
implements OIInventory, Container<OItemStack>
{
    public static final OPotion[][] a = { { OPotion.c, OPotion.e }, { OPotion.m, OPotion.j }, { OPotion.g }, { OPotion.l } };
    private boolean d;
    private int e = -1;
    private int f;
    private int g;
    private OItemStack h;
    
    // CanaryMod
    private String name = "container.beacon";
    private final Beacon beacon = new Beacon(this);
    
    public void g()
    {
        if (this.k.F() % 80L == 0L) {
            v();
            u();
        }
    }
    
    private void u()
    {
        Iterator localIterator;
        OEntityPlayer localOEntityPlayer;
        if ((this.d) && (this.e > 0) && (!this.k.I) && (this.f > 0))
        {
            double d1 = this.e * 8 + 8;
            int i = 0;
            if ((this.e >= 4) && (this.f == this.g)) {
                i = 1;
            }
            
            OAxisAlignedBB localOAxisAlignedBB = OAxisAlignedBB.a().a(this.l, this.m, this.n, this.l + 1, this.m + 1, this.n + 1).b(d1, d1, d1);
            List localList = this.k.a(OEntityPlayer.class, localOAxisAlignedBB);
            for (localIterator = localList.iterator(); localIterator.hasNext(); ) { localOEntityPlayer = (OEntityPlayer)localIterator.next();
                localOEntityPlayer.d(new OPotionEffect(this.f, 180, i, true));
            }
            
            if ((this.e >= 4) && (this.f != this.g) && (this.g > 0))
            for (localIterator = localList.iterator(); localIterator.hasNext(); ) { localOEntityPlayer = (OEntityPlayer)localIterator.next();
                localOEntityPlayer.d(new OPotionEffect(this.g, 180, 0, true));
            }
        }
    }
    
    private void v()
    {
        if (!this.k.k(this.l, this.m + 1, this.n)) {
            this.d = false;
            this.e = 0;
        } else {
            this.d = true;
        
        this.e = 0;
        for (int i = 1; i <= 4; i++)
        {
            int j = this.m - i;
            if (j < 0)
            {
                break;
            }
            int k = 1;
            for (int m = this.l - i; (m <= this.l + i) && (k != 0); m++) {
                for (int n = this.n - i; n <= this.n + i; n++) {
                    int i1 = this.k.a(m, j, n);
                    if ((i1 != OBlock.bY.cm) && (i1 != OBlock.ak.cm) && (i1 != OBlock.aA.cm) && (i1 != OBlock.al.cm)) {
                        k = 0;
                        break;
                    }
                }
            }
            if (k == 0) break;
                this.e = i;
        }
        
        if (this.e == 0)
            this.d = false;
        }
    }
    
    public int i()
    {
        return this.f;
    }
    
    public int j() {
        return this.g;
    }
    
    public int k() {
        return this.e;
    }
    
    public void d(int paramInt)
    {
        this.f = 0;
        
        for (int i = 0; (i < this.e) && (i < 3); i++)
            for (OPotion localOPotion : a[i])
                if (localOPotion.H == paramInt) {
                    this.f = paramInt;
                    return;
                }
    }
    
    public void e(int paramInt)
    {
        this.g = 0;
        
        if (this.e >= 4)
        for (int i = 0; i < 4; i++)
            for (OPotion localOPotion : a[i])
                if (localOPotion.H == paramInt) {
                    this.g = paramInt;
                    return;
                }
    }
    
    public OPacket l()
    {
        ONBTTagCompound localONBTTagCompound = new ONBTTagCompound();
        b(localONBTTagCompound);
        return new OPacket132TileEntityData(this.l, this.m, this.n, 3, localONBTTagCompound);
    }
    
    public void a(ONBTTagCompound paramONBTTagCompound)
    {
        super.a(paramONBTTagCompound);
        
        this.f = paramONBTTagCompound.e("Primary");
        this.g = paramONBTTagCompound.e("Secondary");
        this.e = paramONBTTagCompound.e("Levels");
    }
    
    public void b(ONBTTagCompound paramONBTTagCompound)
    {
        super.b(paramONBTTagCompound);
        
        paramONBTTagCompound.a("Primary", this.f);
        paramONBTTagCompound.a("Secondary", this.g);
        
        paramONBTTagCompound.a("Levels", this.e);
    }
    
    public int k_() {
        return 1;
    }
    
    public OItemStack a(int paramInt) {
        if (paramInt == 0) {
            return this.h;
        }
        return null;
    }
    
    public OItemStack a(int paramInt1, int paramInt2) {
        if ((paramInt1 == 0) && (this.h != null)) {
            if (paramInt2 >= this.h.a) {
                OItemStack localOItemStack = this.h;
                this.h = null;
                return localOItemStack;
            }
            this.h.a -= paramInt2;
            return new OItemStack(this.h.c, paramInt2, this.h.j());
        }
        
        return null;
    }
    
    public OItemStack a_(int paramInt) {
        if ((paramInt == 0) && (this.h != null)) {
            OItemStack localOItemStack = this.h;
            this.h = null;
            return localOItemStack;
        }
        return null;
    }
    
    public void a(int paramInt, OItemStack paramOItemStack) {
        if (paramInt == 0)
        this.h = paramOItemStack;
    }
    
    public String b()
    {
        return name; // CanaryMod
    }
    
    public int c() {
        return 1;
    }
    
    public boolean a_(OEntityPlayer paramOEntityPlayer) {
        if (this.k.q(this.l, this.m, this.n) != this) return false;
        return paramOEntityPlayer.e(this.l + 0.5D, this.m + 0.5D, this.n + 0.5D) <= 64.0D;
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
        return new OItemStack[]{this.h};
    }

    @Override
    public void setContents(OItemStack[] values) {
        if(values == null || values.length < 1)
            this.h = null;
        else
            this.h = values[0];
    }

    @Override
    public OItemStack getContentsAt(int index) {
        if(index != 0)
            return null;
        return this.h;
    }

    @Override
    public void setContentsAt(int index, OItemStack value) {
        if(index != 0)
            return;
        
        this.h = value;
    }

    @Override
    public int getContentsSize() {
        return 1;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String value) {
        name = value;
    }
    
    @Override
    public Beacon getComplexBlock() {
        return beacon;
    }
    
    public int getLevels() {
        return e;
    }
    
    public void setLevels(int levels) {
        this.e = levels;
    }
    
    public int getPrimaryEffect() {
        return f;
    }
    
    public void setPrimaryEffect(int effect) {
        this.d(effect);
    }
    
    public void setPrimaryEffectDirectly(int effect) {
        this.f = effect;
    }
    
    public int getSecondaryEffect() {
        return g;
    }
    
    public void setSecondaryEffect(int effect) {
        this.e(effect);
    }
    
    public void setSecondaryEffectDirectly(int effect) {
        this.g = effect;
    }
}