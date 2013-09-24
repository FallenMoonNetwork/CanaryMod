import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public abstract class OEntityPlayer extends OEntityLivingBase implements OICommandSender {

    public OInventoryPlayer bn = new OInventoryPlayer(this);
    private OInventoryEnderChest a = new OInventoryEnderChest();
    public OContainer bo;
    public OContainer bp;
    protected OFoodStats bq = new OFoodStats(this); // CanaryMod: pass 'this'
    protected int br;
    public float bs;
    public float bt;
    protected final String bu;
    public int bv;
    public double bw;
    public double bx;
    public double by;
    public double bz;
    public double bA;
    public double bB;
    protected boolean bC;
    public OChunkCoordinates bD;
    private int b;
    public float bE;
    public float bF;
    private OChunkCoordinates c;
    private boolean d;
    private OChunkCoordinates e;
    public OPlayerCapabilities bG = new OPlayerCapabilities();
    public int bH;
    public int bI;
    public float bJ;
    private OItemStack f;
    private int g;
    protected float bK = 0.1F;
    protected float bL = 0.02F;
    private int h;
    public OEntityFishHook bM;

    // CanaryMod start
    @SuppressWarnings("FieldNameHidesFieldInSuperclass")
    HumanEntity entity = new HumanEntity(this);
    private String displayName; // CanaryMod: custom display names
    // CanaryMod end

    public OEntityPlayer(OWorld oworld, String s) {
        super(oworld);
        this.bu = s;
        this.bo = new OContainerPlayer(this.bn, !oworld.I, this);
        this.bp = this.bo;
        this.N = 1.62F;
        OChunkCoordinates ochunkcoordinates = oworld.K();

        this.b((double) ochunkcoordinates.a + 0.5D, (double) (ochunkcoordinates.b + 1), (double) ochunkcoordinates.c + 0.5D, 0.0F, 0.0F);
        this.ba = 180.0F;
        this.ad = 20;
    }

    protected void az() {
        super.az();
        this.aX().b(OSharedMonsterAttributes.e).a(1.0D);
    }

    protected void a() {
        super.a();
        this.ah.a(16, Byte.valueOf((byte) 0));
        this.ah.a(17, Float.valueOf(0.0F));
        this.ah.a(18, Integer.valueOf(0));
    }

    public boolean br() {
        return this.f != null;
    }

    public void bt() {
        if (this.f != null) {
            this.f.b(this.q, this, this.g);
        }

        this.bu();
    }

    public void bu() {
        this.f = null;
        this.g = 0;
        if (!this.q.I) {
            this.e(false);
        }
    }

    public boolean bv() {
        return this.br() && OItem.g[this.f.d].c_(this.f) == OEnumAction.d;
    }

    public void l_() {
        if (this.f != null) {
            OItemStack oitemstack = this.bn.h();

            if (oitemstack == this.f) {
                if (this.g <= 25 && this.g % 4 == 0) {
                    this.c(oitemstack, 5);
                }

                if (--this.g == 0 && !this.q.I) {
                    this.n();
                }
            } else {
                this.bu();
            }
        }

        if (this.bv > 0) {
            --this.bv;
        }

        if (this.bh()) {
            ++this.b;
            if (this.b > 100) {
                this.b = 100;
            }

            if (!this.q.I) {
                if (!this.h()) {
                    this.a(true, true, false);
                } else if (this.q.v()) {
                    this.a(false, true, true);
                }
            }
        } else if (this.b > 0) {
            ++this.b;
            if (this.b >= 110) {
                this.b = 0;
            }
        }

        super.l_();
        if (!this.q.I && this.bp != null && !this.bp.a(this)) {
            this.i();
            this.bp = this.bo;
        }

        if (this.af() && this.bG.a) {
            this.B();
        }

        this.bw = this.bz;
        this.bx = this.bA;
        this.by = this.bB;
        double d0 = this.u - this.bz;
        double d1 = this.v - this.bA;
        double d2 = this.w - this.bB;
        double d3 = 10.0D;

        if (d0 > d3) {
            this.bw = this.bz = this.u;
        }

        if (d2 > d3) {
            this.by = this.bB = this.w;
        }

        if (d1 > d3) {
            this.bx = this.bA = this.v;
        }

        if (d0 < -d3) {
            this.bw = this.bz = this.u;
        }

        if (d2 < -d3) {
            this.by = this.bB = this.w;
        }

        if (d1 < -d3) {
            this.bx = this.bA = this.v;
        }

        this.bz += d0 * 0.25D;
        this.bB += d2 * 0.25D;
        this.bA += d1 * 0.25D;
        this.a(OStatList.k, 1);
        if (this.o == null) {
            this.e = null;
        }

        if (!this.q.I) {
            this.bq.a(this);
        }
    }

    public int z() {
        return this.bG.a ? 0 : 80;
    }

    public int ac() {
        return 10;
    }

    public void a(String s, float f, float f1) {
        this.q.a(this, s, f, f1);
    }

    protected void c(OItemStack oitemstack, int i) {
        if (oitemstack.o() == OEnumAction.c) {
            this.a("random.drink", 0.5F, this.q.s.nextFloat() * 0.1F + 0.9F);
        }

        if (oitemstack.o() == OEnumAction.b) {
            for (int j = 0; j < i; ++j) {
                OVec3 ovec3 = this.q.V().a(((double) this.ab.nextFloat() - 0.5D) * 0.1D, Math.random() * 0.1D + 0.1D, 0.0D);

                ovec3.a(-this.B * 3.1415927F / 180.0F);
                ovec3.b(-this.A * 3.1415927F / 180.0F);
                OVec3 ovec31 = this.q.V().a(((double) this.ab.nextFloat() - 0.5D) * 0.3D, (double) (-this.ab.nextFloat()) * 0.6D - 0.3D, 0.6D);

                ovec31.a(-this.B * 3.1415927F / 180.0F);
                ovec31.b(-this.A * 3.1415927F / 180.0F);
                ovec31 = ovec31.c(this.u, this.v + (double) this.f(), this.w);
                this.q.a("iconcrack_" + oitemstack.b().cv, ovec31.c, ovec31.d, ovec31.e, ovec3.c, ovec3.d + 0.05D, ovec3.e);
            }

            this.a("random.eat", 0.5F + 0.5F * (float) this.ab.nextInt(2), (this.ab.nextFloat() - this.ab.nextFloat()) * 0.2F + 1.0F);
        }
    }

    protected void n() {
        if (this.f != null) {
            this.c(this.f, 16);
            int i = this.f.b;
            OItemStack oitemstack = this.f.b(this.q, this);

            if (oitemstack != this.f || oitemstack != null && oitemstack.b != i) {
                this.bn.a[this.bn.c] = oitemstack;
                if (oitemstack.b == 0) {
                    this.bn.a[this.bn.c] = null;
                }
            }

            this.bu();
        }
    }

    protected boolean bc() {
        return this.aN() <= 0.0F || this.bh();
    }

    protected void i() {
        this.bp = this.bo;
    }

    public void a(OEntity oentity) {
        if (this.o != null && oentity == null) {
            if (!this.q.I) {
                this.l(this.o);
            }

            if (this.o != null) {
                this.o.n = null;
            }

            this.o = null;
        } else {
            super.a(oentity);
        }
    }

    public void V() {
        if (!this.q.I && this.ah()) {
            this.a((OEntity) null);
            this.b(false);
        } else {
            double d0 = this.u;
            double d1 = this.v;
            double d2 = this.w;
            float f = this.A;
            float f1 = this.B;

            super.V();
            this.bs = this.bt;
            this.bt = 0.0F;
            this.k(this.u - d0, this.v - d1, this.w - d2);
            if (this.o instanceof OEntityPig) {
                this.B = f1;
                this.A = f;
                this.aN = ((OEntityPig) this.o).aN;
            }
        }
    }

    protected void bl() {
        super.bl();
        this.aW();
    }

    public void c() {
        if (this.br > 0) {
            --this.br;
        }

        //CanaryMod: adjust 'healing over time' independent of monster-spawn=true/false (nice notchup!)
        PluginLoader.HookResult autoHeal = etc.getInstance().autoHeal();

        if (this.q.r == 0 && autoHeal == PluginLoader.HookResult.DEFAULT_ACTION || autoHeal == PluginLoader.HookResult.ALLOW_ACTION){
            if (this.aN() < this.aT() && this.q.O().b("naturalRegeneration") && this.ac % 20 * 12 == 0) {
                this.f(1.0F);
            }
        }

        this.bn.k();
        this.bs = this.bt;
        super.c();
        OAttributeInstance oattributeinstance = this.a(OSharedMonsterAttributes.d);

        if (!this.q.I) {
            oattributeinstance.a((double) this.bG.b());
        }

        this.aR = this.bL;
        if (this.ai()) {
            this.aR = (float) ((double) this.aR + (double) this.bL * 0.3D);
        }

        this.i((float) oattributeinstance.e());
        float f = OMathHelper.a(this.x * this.x + this.z * this.z);
        float f1 = (float) Math.atan(-this.y * 0.20000000298023224D) * 15.0F;

        if (f > 0.1F) {
            f = 0.1F;
        }

        if (!this.F || this.aN() <= 0.0F) {
            f = 0.0F;
        }

        if (this.F || this.aN() <= 0.0F) {
            f1 = 0.0F;
        }

        this.bt += (f - this.bt) * 0.4F;
        this.aK += (f1 - this.aK) * 0.8F;
        if (this.aN() > 0.0F) {
            OAxisAlignedBB oaxisalignedbb = null;

            if (this.o != null && !this.o.M) {
                oaxisalignedbb = this.E.a(this.o.E).b(1.0D, 0.0D, 1.0D);
            } else {
                oaxisalignedbb = this.E.b(1.0D, 0.5D, 1.0D);
            }

            List list = this.q.b((OEntity) this, oaxisalignedbb);

            if (list != null) {
                for (int i = 0; i < list.size(); ++i) {
                    OEntity oentity = (OEntity) list.get(i);

                    if (!oentity.M) {
                        this.r(oentity);
                    }
                }
            }
        }
    }

    private void r(OEntity oentity) {
        oentity.b_(this);
    }

    public int bw() {
        return this.ah.c(18);
    }

    public void c(int i) {
        this.ah.b(18, Integer.valueOf(i));
    }

    public void p(int i) {
        int j = this.bw();

        this.ah.b(18, Integer.valueOf(j + i));
    }

    public void a(ODamageSource odamagesource) {
        super.a(odamagesource);
        this.a(0.2F, 0.2F);
        this.b(this.u, this.v, this.w);
        this.y = 0.10000000149011612D;
        if (this.bu.equals("Notch")) {
            this.a(new OItemStack(OItem.l, 1), true);
        }

        if (!this.q.O().b("keepInventory")) {
            this.bn.m();
        }

        if (odamagesource != null) {
            this.x = (double) (-OMathHelper.b((this.aA + this.A) * 3.1415927F / 180.0F) * 0.1F);
            this.z = (double) (-OMathHelper.a((this.aA + this.A) * 3.1415927F / 180.0F) * 0.1F);
        } else {
            this.x = this.z = 0.0D;
        }

        this.N = 0.1F;
        this.a(OStatList.y, 1);
    }

    public void b(OEntity oentity, int i) {
        this.p(i);
        Collection collection = this.bM().a(OScoreObjectiveCriteria.e);

        if (oentity instanceof OEntityPlayer) {
            this.a(OStatList.A, 1);
            collection.addAll(this.bM().a(OScoreObjectiveCriteria.d));
        } else {
            this.a(OStatList.z, 1);
        }

        Iterator iterator = collection.iterator();

        while (iterator.hasNext()) {
            OScoreObjective oscoreobjective = (OScoreObjective) iterator.next();
            OScore oscore = this.bM().a(this.an(), oscoreobjective);

            oscore.a();
        }
    }

    public OEntityItem a(boolean flag) {
        return this.a(this.bn.a(this.bn.c, flag && this.bn.h() != null ? this.bn.h().b : 1), false);
    }

    public OEntityItem b(OItemStack oitemstack) {
        return this.a(oitemstack, false);
    }

    public OEntityItem a(OItemStack oitemstack, boolean flag) {
        if (oitemstack == null) {
            return null;
        } else if (oitemstack.b == 0) {
            return null;
        } else {
            OEntityItem oentityitem = new OEntityItem(this.q, this.u, this.v - 0.30000001192092896D + (double) this.f(), this.w, oitemstack);

            oentityitem.b = 40;
            float f = 0.1F;
            float f1;

            if (flag) {
                f1 = this.ab.nextFloat() * 0.5F;
                float f2 = this.ab.nextFloat() * 3.1415927F * 2.0F;

                oentityitem.x = (double) (-OMathHelper.a(f2) * f1);
                oentityitem.z = (double) (OMathHelper.b(f2) * f1);
                oentityitem.y = 0.20000000298023224D;
            } else {
                f = 0.3F;
                oentityitem.x = (double) (-OMathHelper.a(this.A / 180.0F * 3.1415927F) * OMathHelper.b(this.B / 180.0F * 3.1415927F) * f);
                oentityitem.z = (double) (OMathHelper.b(this.A / 180.0F * 3.1415927F) * OMathHelper.b(this.B / 180.0F * 3.1415927F) * f);
                oentityitem.y = (double) (-OMathHelper.a(this.B / 180.0F * 3.1415927F) * f + 0.1F);
                f = 0.02F;
                f1 = this.ab.nextFloat() * 3.1415927F * 2.0F;
                f *= this.ab.nextFloat();
                oentityitem.x += Math.cos((double) f1) * (double) f;
                oentityitem.y += (double) ((this.ab.nextFloat() - this.ab.nextFloat()) * 0.1F);
                oentityitem.z += Math.sin((double) f1) * (double) f;
            }

            if (!(Boolean) manager.callHook(PluginLoader.Hook.ITEM_DROP, ((OEntityPlayerMP) this).getPlayer(), oentityitem.getEntity())) {
                Item droppedItem = oentityitem.item.getItem();

                if (droppedItem.getAmount() < 0) {
                    droppedItem.setAmount(1);
                    droppedItem.update();
                }
                this.a(oentityitem);
                this.a(OStatList.v, 1);

                return oentityitem;
            } else { // return the item to the inventory.
                return null;
            }
        }
    }

    protected void a(OEntityItem oentityitem) {
        this.q.d((OEntity) oentityitem);
    }

    public float a(OBlock oblock, boolean flag) {
        float f = this.bn.a(oblock);

        if (f > 1.0F) {
            int i = OEnchantmentHelper.c(this);
            OItemStack oitemstack = this.bn.h();

            if (i > 0 && oitemstack != null) {
                float f1 = (float) (i * i + 1);

                if (!oitemstack.b(oblock) && f <= 1.0F) {
                    f += f1 * 0.08F;
                } else {
                    f += f1;
                }
            }
        }

        if (this.a(OPotion.e)) {
            f *= 1.0F + (float) (this.b(OPotion.e).c() + 1) * 0.2F;
        }

        if (this.a(OPotion.f)) {
            f *= 1.0F - (float) (this.b(OPotion.f).c() + 1) * 0.2F;
        }

        if (this.a(OMaterial.h) && !OEnchantmentHelper.h(this)) {
            f /= 5.0F;
        }

        if (!this.F) {
            f /= 5.0F;
        }

        return f;
    }

    public boolean a(OBlock oblock) {
        return this.bn.b(oblock);
    }

    public void a(ONBTTagCompound onbttagcompound) {
        super.a(onbttagcompound);
        ONBTTagList onbttaglist = onbttagcompound.m("Inventory");

        this.bn.b(onbttaglist);
        this.bn.c = onbttagcompound.e("SelectedItemSlot");
        this.bC = onbttagcompound.n("Sleeping");
        this.b = onbttagcompound.d("SleepTimer");
        this.bJ = onbttagcompound.g("XpP");
        this.bH = onbttagcompound.e("XpLevel");
        this.bI = onbttagcompound.e("XpTotal");
        this.c(onbttagcompound.e("Score"));
        if (this.bC) {
            this.bD = new OChunkCoordinates(OMathHelper.c(this.u), OMathHelper.c(this.v), OMathHelper.c(this.w));
            this.a(true, true, false);
        }

        if (onbttagcompound.b("SpawnX") && onbttagcompound.b("SpawnY") && onbttagcompound.b("SpawnZ")) {
            this.c = new OChunkCoordinates(onbttagcompound.e("SpawnX"), onbttagcompound.e("SpawnY"), onbttagcompound.e("SpawnZ"));
            this.d = onbttagcompound.n("SpawnForced");
        }

        this.bq.a(onbttagcompound);
        this.bG.b(onbttagcompound);
        if (onbttagcompound.b("EnderItems")) {
            ONBTTagList onbttaglist1 = onbttagcompound.m("EnderItems");

            this.a.a(onbttaglist1);
        }
    }

    public void b(ONBTTagCompound onbttagcompound) {
        super.b(onbttagcompound);
        onbttagcompound.a("Inventory", (ONBTBase) this.bn.a(new ONBTTagList()));
        onbttagcompound.a("SelectedItemSlot", this.bn.c);
        onbttagcompound.a("Sleeping", this.bC);
        onbttagcompound.a("SleepTimer", (short) this.b);
        onbttagcompound.a("XpP", this.bJ);
        onbttagcompound.a("XpLevel", this.bH);
        onbttagcompound.a("XpTotal", this.bI);
        onbttagcompound.a("Score", this.bw());
        if (this.c != null) {
            onbttagcompound.a("SpawnX", this.c.a);
            onbttagcompound.a("SpawnY", this.c.b);
            onbttagcompound.a("SpawnZ", this.c.c);
            onbttagcompound.a("SpawnForced", this.d);
        }

        this.bq.b(onbttagcompound);
        this.bG.a(onbttagcompound);
        onbttagcompound.a("EnderItems", (ONBTBase) this.a.h());
    }

    public void a(OIInventory oiinventory) {}

    public void a(OTileEntityHopper otileentityhopper) {}

    public void a(OEntityMinecartHopper oentityminecarthopper) {}

    public void a(OEntityHorse oentityhorse, OIInventory oiinventory) {}

    public void a(int i, int j, int k, String s) {}

    public void c(int i, int j, int k) {}

    public void b(int i, int j, int k) {}

    public float f() {
        return 0.12F;
    }

    protected void d_() {
        this.N = 1.62F;
    }

    public boolean a(ODamageSource odamagesource, float f) {
        if (this.ar()) {
            return false;
        } else if (this.bG.a && !odamagesource.g()) {
            return false;
        } else {
            this.aV = 0;
            if (this.aN() <= 0.0F) {
                return false;
            } else {
                if (this.bh() && !this.q.I) {
                    this.a(true, true, false);
                }

                if (odamagesource.p()) {
                    if (this.q.r == 0) {
                        f = 0.0F;
                    }

                    if (this.q.r == 1) {
                        f = f / 2.0F + 1.0F;
                    }

                    if (this.q.r == 3) {
                        f = f * 3.0F / 2.0F;
                    }
                }

                if (f == 0.0F) {
                    return false;
                } else {
                    OEntity oentity = odamagesource.i();

                    if (oentity instanceof OEntityArrow && ((OEntityArrow) oentity).c != null) {
                        oentity = ((OEntityArrow) oentity).c;
                    }

                    this.a(OStatList.x, Math.round(f * 10.0F));
                    return super.a(odamagesource, f);
                }
            }
        }
    }

    public boolean a(OEntityPlayer oentityplayer) {
        OTeam oteam = this.bo();
        OTeam oteam1 = oentityplayer.bo();

        return oteam == null ? true : (!oteam.a(oteam1) ? true : oteam.g());
    }

    protected void h(float f) {
        this.bn.a(f);
    }

    public int aQ() {
        return this.bn.l();
    }

    public float bx() {
        int i = 0;
        OItemStack[] aoitemstack = this.bn.b;
        int j = aoitemstack.length;

        for (int k = 0; k < j; ++k) {
            OItemStack oitemstack = aoitemstack[k];

            if (oitemstack != null) {
                ++i;
            }
        }

        return (float) i / (float) this.bn.b.length;
    }

    protected void d(ODamageSource odamagesource, float f) {
        if (!this.ar()) {
            if (!odamagesource.e() && this.bv() && f > 0.0F) {
                f = (1.0F + f) * 0.5F;
            }

            f = this.b(odamagesource, f);
            f = this.c(odamagesource, f);
            float f1 = f;

            f = Math.max(f - this.bn(), 0.0F);
            this.m(this.bn() - (f1 - f));
            if (f != 0.0F) {
                this.a(odamagesource.f());
                float f2 = this.aN();

                this.g(this.aN() - f);
                this.aR().a(odamagesource, f2, f);
            }
        }
    }

    public void a(OTileEntityFurnace otileentityfurnace) {}

    public void a(OTileEntityDispenser otileentitydispenser) {}

    public void a(OTileEntity otileentity) {}

    public void a(OTileEntityBrewingStand otileentitybrewingstand) {}

    public void a(OTileEntityBeacon otileentitybeacon) {}

    public void a(OIMerchant oimerchant, String s) {}

    public void c(OItemStack oitemstack) {}

    public boolean p(OEntity oentity) {
        OItemStack oitemstack = this.by();
        OItemStack oitemstack1 = oitemstack != null ? oitemstack.m() : null;

        if (!oentity.c(this)) {
            PluginLoader.HookResult res = (PluginLoader.HookResult) manager.callHook(PluginLoader.Hook.ENTITY_RIGHTCLICKED, ((OEntityPlayerMP) this).getPlayer(), oentity.getEntity(), oitemstack == null ? null : new Item(oitemstack));

            if (res == PluginLoader.HookResult.ALLOW_ACTION) {
                return true;
            } else if (res == PluginLoader.HookResult.DEFAULT_ACTION) {
                // Normally when interact action is not defined on the interacted entity, false is returned, and the item stack is not used.
                // For example sheep can interact by shearing and cows by milking, and the item stack changes from this interaction if it returns true.
                // Players on the other hand won't interact normally, but if we want to update the item stack anyways, we will ALLOW the action.
                if (oitemstack != null && oentity instanceof OEntityLivingBase) {
                    if (this.bG.d) {
                        oitemstack = oitemstack1;
                    }

                    if (oitemstack.a(this, (OEntityLivingBase) oentity)) {
                        if (oitemstack.b <= 0 && !this.bG.d) {
                        this.bz();
                        }

                        return true;
                    }
                }
            }

            return false;
        } else {
            if (oitemstack != null && oitemstack == this.by()) {
                if (oitemstack.b <= 0 && !this.bG.d) {
                    this.bz();
                } else if (oitemstack.b < oitemstack1.b && this.bG.d) {
                    oitemstack.b = oitemstack1.b;
                }
            }

            return true;
        }
    }

    public OItemStack by() {
        return this.bn.h();
    }

    public void bz() {
        this.bn.a(this.bn.c, (OItemStack) null);
    }

    public double X() {
        return (double) (this.N - 0.5F);
    }

    public void q(OEntity oentity) {
        if (oentity.aq()) {
            if (!oentity.i(this)) {
                float f = (float) this.a(OSharedMonsterAttributes.e).e();
                int i = 0;
                float f1 = 0.0F;

                if (oentity instanceof OEntityLivingBase) {
                    f1 = OEnchantmentHelper.a((OEntityLivingBase) this, (OEntityLivingBase) oentity);
                    i += OEnchantmentHelper.b(this, (OEntityLivingBase) oentity);
                }

                if (this.ai()) {
                    ++i;
                }

                if (f > 0.0F || f1 > 0.0F) {
                    boolean flag = this.T > 0.0F && !this.F && !this.e() && !this.H() && !this.a(OPotion.q) && this.o == null && oentity instanceof OEntityLivingBase;

                    if (flag && f > 0.0F) {
                        f *= 1.5F;
                    }

                    f += f1;
                    boolean flag1 = false;
                    int j = OEnchantmentHelper.a((OEntityLivingBase) this);

                    if (oentity instanceof OEntityLivingBase && j > 0 && !oentity.af()) {
                        flag1 = true;
                        oentity.d(1);
                    }

                    boolean flag2 = oentity.a(ODamageSource.a(this), f);

                    if (flag2) {
                        if (i > 0) {
                            oentity.g((double) (-OMathHelper.a(this.A * 3.1415927F / 180.0F) * (float) i * 0.5F), 0.1D, (double) (OMathHelper.b(this.A * 3.1415927F / 180.0F) * (float) i * 0.5F));
                            this.x *= 0.6D;
                            this.z *= 0.6D;
                            this.c(false);
                        }

                        if (flag) {
                            this.b(oentity);
                        }

                        if (f1 > 0.0F) {
                            this.c(oentity);
                        }

                        if (f >= 18.0F) {
                            this.a((OStatBase) OAchievementList.E);
                        }

                        this.k(oentity);
                        if (oentity instanceof OEntityLivingBase) {
                            OEnchantmentThorns.a(this, (OEntityLivingBase) oentity, this.ab);
                        }
                    }

                    OItemStack oitemstack = this.by();
                    Object object = oentity;

                    if (oentity instanceof OEntityDragonPart) {
                        OIEntityMultiPart oientitymultipart = ((OEntityDragonPart) oentity).a;

                        if (oientitymultipart != null && oientitymultipart instanceof OEntityLivingBase) {
                            object = (OEntityLivingBase) oientitymultipart;
                        }
                    }

                    if (oitemstack != null && object instanceof OEntityLivingBase) {
                        oitemstack.a((OEntityLivingBase) object, this);
                        if (oitemstack.b <= 0) {
                            this.bz();
                        }
                    }

                    if (oentity instanceof OEntityLivingBase) {
                        this.a(OStatList.w, Math.round(f * 10.0F));
                        if (j > 0 && flag2) {
                            oentity.d(j * 4);
                        } else if (flag1) {
                            oentity.B();
                        }
                    }

                    this.a(0.3F);
                }
            }
        }
    }

    public void b(OEntity oentity) {}

    public void c(OEntity oentity) {}

    public void x() {
        super.x();
        this.bo.b(this);
        if (this.bp != null) {
            this.bp.b(this);
        }
    }

    public boolean U() {
        return !this.bC && super.U();
    }

    public OEnumStatus a(int i, int j, int k) {
        if (!this.q.I) {
            if (this.bh() || !this.T()) {
                return OEnumStatus.e;
            }

            if (!this.q.t.d()) {
                return OEnumStatus.b;
            }

            if (this.q.v()) {
                return OEnumStatus.c;
            }

            if (Math.abs(this.u - (double) i) > 3.0D || Math.abs(this.v - (double) j) > 2.0D || Math.abs(this.w - (double) k) > 3.0D) {
                return OEnumStatus.d;
            }

            double d0 = 8.0D;
            double d1 = 5.0D;
            List list = this.q.a(OEntityMob.class, OAxisAlignedBB.a().a((double) i - d0, (double) j - d1, (double) k - d0, (double) i + d0, (double) j + d1, (double) k + d0));

            if (!list.isEmpty()) {
                return OEnumStatus.f;
            }
        }

        if (this.ag()) {
            this.a((OEntity) null);
        }

        this.a(0.2F, 0.2F);
        this.N = 0.2F;
        if (this.q.f(i, j, k)) {
            int l = this.q.h(i, j, k);
            int i1 = OBlockBed.j(l);
            float f = 0.5F;
            float f1 = 0.5F;

            switch (i1) {
                case 0:
                    f1 = 0.9F;
                    break;

                case 1:
                    f = 0.1F;
                    break;

                case 2:
                    f1 = 0.1F;
                    break;

                case 3:
                    f = 0.9F;
            }

            this.t(i1);
            this.b((double) ((float) i + f), (double) ((float) j + 0.9375F), (double) ((float) k + f1));
        } else {
            this.b((double) ((float) i + 0.5F), (double) ((float) j + 0.9375F), (double) ((float) k + 0.5F));
        }

        this.bC = true;
        this.b = 0;
        this.bD = new OChunkCoordinates(i, j, k);
        this.x = this.z = this.y = 0.0D;
        if (!this.q.I) {
            this.q.c();
        }

        return OEnumStatus.a;
    }

    private void t(int i) {
        this.bE = 0.0F;
        this.bF = 0.0F;
        switch (i) {
            case 0:
                this.bF = -1.8F;
                break;

            case 1:
                this.bE = 1.8F;
                break;

            case 2:
                this.bF = 1.8F;
                break;

            case 3:
                this.bE = -1.8F;
        }
    }

    public void a(boolean flag, boolean flag1, boolean flag2) {
        this.a(0.6F, 1.8F);
        this.d_();
        OChunkCoordinates ochunkcoordinates = this.bD;
        OChunkCoordinates ochunkcoordinates1 = this.bD;

        if (ochunkcoordinates != null && this.q.a(ochunkcoordinates.a, ochunkcoordinates.b, ochunkcoordinates.c) == OBlock.X.cF) {
            OBlockBed.a(this.q, ochunkcoordinates.a, ochunkcoordinates.b, ochunkcoordinates.c, false);
            ochunkcoordinates1 = OBlockBed.b(this.q, ochunkcoordinates.a, ochunkcoordinates.b, ochunkcoordinates.c, 0);
            if (ochunkcoordinates1 == null) {
                ochunkcoordinates1 = new OChunkCoordinates(ochunkcoordinates.a, ochunkcoordinates.b + 1, ochunkcoordinates.c);
            }

            this.b((double) ((float) ochunkcoordinates1.a + 0.5F), (double) ((float) ochunkcoordinates1.b + this.N + 0.1F), (double) ((float) ochunkcoordinates1.c + 0.5F));
        }

        this.bC = false;
        if (!this.q.I && flag1) {
            this.q.c();
        }

        if (flag) {
            this.b = 0;
        } else {
            this.b = 100;
        }

        if (flag2) {
            this.a(this.bD, false);
        }
    }

    private boolean h() {
        return this.q.a(this.bD.a, this.bD.b, this.bD.c) == OBlock.X.cF;
    }

    public static OChunkCoordinates a(OWorld oworld, OChunkCoordinates ochunkcoordinates, boolean flag) {
        OIChunkProvider oichunkprovider = oworld.L();

        oichunkprovider.c(ochunkcoordinates.a - 3 >> 4, ochunkcoordinates.c - 3 >> 4);
        oichunkprovider.c(ochunkcoordinates.a + 3 >> 4, ochunkcoordinates.c - 3 >> 4);
        oichunkprovider.c(ochunkcoordinates.a - 3 >> 4, ochunkcoordinates.c + 3 >> 4);
        oichunkprovider.c(ochunkcoordinates.a + 3 >> 4, ochunkcoordinates.c + 3 >> 4);
        if (oworld.a(ochunkcoordinates.a, ochunkcoordinates.b, ochunkcoordinates.c) == OBlock.X.cF) {
            OChunkCoordinates ochunkcoordinates1 = OBlockBed.b(oworld, ochunkcoordinates.a, ochunkcoordinates.b, ochunkcoordinates.c, 0);

            return ochunkcoordinates1;
        } else {
            OMaterial omaterial = oworld.g(ochunkcoordinates.a, ochunkcoordinates.b, ochunkcoordinates.c);
            OMaterial omaterial1 = oworld.g(ochunkcoordinates.a, ochunkcoordinates.b + 1, ochunkcoordinates.c);
            boolean flag1 = !omaterial.a() && !omaterial.d();
            boolean flag2 = !omaterial1.a() && !omaterial1.d();

            return flag && flag1 && flag2 ? ochunkcoordinates : null;
        }
    }

    public boolean bh() {
        return this.bC;
    }

    public boolean bD() {
        return this.bC && this.b >= 100;
    }

    protected void b(int i, boolean flag) {
        byte b0 = this.ah.a(16);

        if (flag) {
            this.ah.b(16, Byte.valueOf((byte) (b0 | 1 << i)));
        } else {
            this.ah.b(16, Byte.valueOf((byte) (b0 & ~(1 << i))));
        }
    }

    public void a(String s) {}

    public OChunkCoordinates bF() {
        return this.c;
    }

    public boolean bG() {
        return this.d;
    }

    public void a(OChunkCoordinates ochunkcoordinates, boolean flag) {
        if (ochunkcoordinates != null) {
            this.c = new OChunkCoordinates(ochunkcoordinates);
            this.d = flag;
        } else {
            this.c = null;
            this.d = false;
        }
    }

    public void a(OStatBase ostatbase) {
        this.a(ostatbase, 1);
    }

    public void a(OStatBase ostatbase, int i) {}

    protected void be() {
        super.be();
        this.a(OStatList.u, 1);
        if (this.ai()) {
            this.a(0.8F);
        } else {
            this.a(0.2F);
        }
    }

    public void e(float f, float f1) {
        double d0 = this.u;
        double d1 = this.v;
        double d2 = this.w;

        if (this.bG.b && this.o == null) {
            double d3 = this.y;
            float f2 = this.aR;

            this.aR = this.bG.a();
            super.e(f, f1);
            this.y = d3 * 0.6D;
            this.aR = f2;
        } else {
            super.e(f, f1);
        }

        this.j(this.u - d0, this.v - d1, this.w - d2);
    }

    public float bg() {
        return (float) this.a(OSharedMonsterAttributes.d).e();
    }

    public void j(double d0, double d1, double d2) {
        if (this.o == null) {
            int i;

            if (this.a(OMaterial.h)) {
                i = Math.round(OMathHelper.a(d0 * d0 + d1 * d1 + d2 * d2) * 100.0F);
                if (i > 0) {
                    this.a(OStatList.q, i);
                    this.a(0.015F * (float) i * 0.01F);
                }
            } else if (this.H()) {
                i = Math.round(OMathHelper.a(d0 * d0 + d2 * d2) * 100.0F);
                if (i > 0) {
                    this.a(OStatList.m, i);
                    this.a(0.015F * (float) i * 0.01F);
                }
            } else if (this.e()) {
                if (d1 > 0.0D) {
                    this.a(OStatList.o, (int) Math.round(d1 * 100.0D));
                }
            } else if (this.F) {
                i = Math.round(OMathHelper.a(d0 * d0 + d2 * d2) * 100.0F);
                if (i > 0) {
                    this.a(OStatList.l, i);
                    if (this.ai()) {
                        this.a(0.099999994F * (float) i * 0.01F);
                    } else {
                        this.a(0.01F * (float) i * 0.01F);
                    }
                }
            } else {
                i = Math.round(OMathHelper.a(d0 * d0 + d2 * d2) * 100.0F);
                if (i > 25) {
                    this.a(OStatList.p, i);
                }
            }
        }
    }

    private void k(double d0, double d1, double d2) {
        if (this.o != null) {
            int i = Math.round(OMathHelper.a(d0 * d0 + d1 * d1 + d2 * d2) * 100.0F);

            if (i > 0) {
                if (this.o instanceof OEntityMinecart) {
                    this.a(OStatList.r, i);
                    if (this.e == null) {
                        this.e = new OChunkCoordinates(OMathHelper.c(this.u), OMathHelper.c(this.v), OMathHelper.c(this.w));
                    } else if ((double) this.e.e(OMathHelper.c(this.u), OMathHelper.c(this.v), OMathHelper.c(this.w)) >= 1000000.0D) {
                        this.a((OStatBase) OAchievementList.q, 1);
                    }
                } else if (this.o instanceof OEntityBoat) {
                    this.a(OStatList.s, i);
                } else if (this.o instanceof OEntityPig) {
                    this.a(OStatList.t, i);
                }
            }
        }
    }

    protected void b(float f) {
        if (!this.bG.c) {
            if (f >= 2.0F) {
                this.a(OStatList.n, (int) Math.round((double) f * 100.0D));
            }

            super.b(f);
        }
    }

    public void a(OEntityLivingBase oentitylivingbase) {
        if (oentitylivingbase instanceof OIMob) {
            this.a((OStatBase) OAchievementList.s);
        }
    }

    public void am() {
        if (!this.bG.b) {
            super.am();
        }
    }

    public OItemStack o(int i) {
        return this.bn.f(i);
    }

    public void s(int i) {
        this.p(i);
        int j = Integer.MAX_VALUE - this.bI;

        if (i > j) {
            i = j;
        }

        this.bJ += (float) i / (float) this.bH();

        for (this.bI += i; this.bJ >= 1.0F; this.bJ /= (float) this.bH()) {
            this.bJ = (this.bJ - 1.0F) * (float) this.bH();
            this.a(1);
        }
    }

    // CanaryMod start - custom XP methods
    public void removeXP(int i) {
        if (i > this.bI) { // Don't go below 0
            i = this.bI;
        }
        this.bI -= i;
        this.recalculateXP();
    }

    public void setXP(int i) {
        if (i < this.bI) {
            this.removeXP(this.bI - i);
        } else {
            this.t(i - this.bI);
        }
    }

    public void recalculateXP() {
        this.bJ = this.bI / (float) this.bH();
        this.bH = 0;

        while (this.bJ >= 1.0F) {
            this.bJ = (this.bJ - 1.0F) * this.bH();
            this.bH++;
            this.bJ /= this.bH();
        }

        if (this instanceof OEntityPlayerMP) {
            ((OEntityPlayerMP) this).getEntity().updateLevels();
        }
    } // CanaryMod end - custom XP methods

    public void a(int i) {
        manager.callHook(PluginLoader.Hook.LEVEL_UP, ((OEntityPlayerMP) this).getPlayer());

        this.bH += i;
        if (this.bH < 0) {
            this.bH = 0;
            this.bJ = 0.0F;
            this.bI = 0;
        }

        if (i > 0 && this.bH % 5 == 0 && (float) this.h < (float) this.ac - 100.0F) {
            float f = this.bH > 30 ? 1.0F : (float) this.bH / 30.0F;

            this.q.a((OEntity) this, "random.levelup", f * 0.75F, 1.0F);
            this.h = this.ac;
        }
    }

    public int bH() {
        // CanaryMod: Old experience option
        if (etc.getInstance().isOldExperience()) {
            return 7 + (this.bH * 7 >> 1);
        } // CanaryMod: End

        return this.bH >= 30 ? 62 + (this.bH - 30) * 7 : (this.bH >= 15 ? 17 + (this.bH - 15) * 3 : 17);
    }

    public void a(float f) {
        if (!this.bG.a) {
            if (!this.q.I) {
                this.bq.a(f);
            }
        }
    }

    public OFoodStats bI() {
        return this.bq;
    }

    public boolean g(boolean flag) {
        return (flag || this.bq.c()) && !this.bG.a;
    }

    public boolean bJ() {
        return this.aN() > 0.0F && this.aN() < this.aT();
    }

    public void a(OItemStack oitemstack, int i) {
        if (oitemstack != this.f) {
            this.f = oitemstack;
            this.g = i;
            if (!this.q.I) {
                this.e(true);
            }
        }
    }

    public boolean d(int i, int j, int k) {
        if (this.bG.e) {
            return true;
        } else {
            int l = this.q.a(i, j, k);

            if (l > 0) {
                OBlock oblock = OBlock.s[l];

                if (oblock.cU.q()) {
                    return true;
                }

                if (this.by() != null) {
                    OItemStack oitemstack = this.by();

                    if (oitemstack.b(oblock) || oitemstack.a(oblock) > 1.0F) {
                        return true;
                    }
                }
            }

            return false;
        }
    }

    public boolean a(int i, int j, int k, int l, OItemStack oitemstack) {
        return this.bG.e ? true : (oitemstack != null ? oitemstack.z() : false);
    }

    protected int e(OEntityPlayer oentityplayer) {
        if (this.q.O().b("keepInventory")) {
            return 0;
        } else {
            int i = this.bH * 7;

            return i > 100 ? 100 : i;
        }
    }

    protected boolean aC() {
        return true;
    }

    public String an() {
        return this.bu;
    }

    public void a(OEntityPlayer oentityplayer, boolean flag) {
        if (flag) {
            this.bn.b(oentityplayer.bn);
            this.g(oentityplayer.aN());
            this.bq = oentityplayer.bq;
            this.bH = oentityplayer.bH;
            this.bI = oentityplayer.bI;
            this.bJ = oentityplayer.bJ;
            this.c(oentityplayer.bw());
            this.as = oentityplayer.as;
        } else if (this.q.O().b("keepInventory")) {
            this.bn.b(oentityplayer.bn);
            this.bH = oentityplayer.bH;
            this.bI = oentityplayer.bI;
            this.bJ = oentityplayer.bJ;
            this.c(oentityplayer.bw());
        }

        this.a = oentityplayer.a;
    }

    protected boolean e_() {
        return !this.bG.b;
    }

    public void o() {}

    public void a(OEnumGameType oenumgametype) {}

    public String c_() {
        return this.bu;
    }

    public OWorld f_() {
        return this.q;
    }

    public OInventoryEnderChest bK() {
        return this.a;
    }

    public OItemStack n(int i) {
        return i == 0 ? this.bn.h() : this.bn.b[i - 1];
    }

    public OItemStack aZ() {
        return this.bn.h();
    }

    public void c(int i, OItemStack oitemstack) {
        this.bn.b[i] = oitemstack;
    }

    public OItemStack[] ae() {
        return this.bn.b;
    }

    public boolean ax() {
        return !this.bG.b;
    }

    public OScoreboard bM() {
        return this.q.X();
    }

    public OTeam bo() {
        return this.bM().i(this.bu);
    }

    public String ay() {
        return OScorePlayerTeam.a(this.bo(), this.bu);
    }

    public void m(float f) {
        if (f < 0.0F) {
            f = 0.0F;
        }

        this.v().b(17, Float.valueOf(f));
    }

    public float bn() {
        return this.v().d(17);
    }

    // CanaryMod start
    @Override
    public HumanEntity getEntity() {
        return entity;
    } //

    public String getDisplayName() {
        return displayName == null ? this.bu : displayName;
    } //

    public void setDisplayName(String name) {
        this.displayName = name;
    } // CanaryMod end
}
