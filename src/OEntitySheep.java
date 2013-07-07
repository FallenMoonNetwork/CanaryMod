import java.util.Random;

public class OEntitySheep extends OEntityAnimal {

    private final OInventoryCrafting bq = new OInventoryCrafting(new OContainerSheep(this), 2, 1);
    public static final float[][] bp = new float[][] { { 1.0F, 1.0F, 1.0F}, { 0.85F, 0.5F, 0.2F}, { 0.7F, 0.3F, 0.85F}, { 0.4F, 0.6F, 0.85F}, { 0.9F, 0.9F, 0.2F}, { 0.5F, 0.8F, 0.1F}, { 0.95F, 0.5F, 0.65F}, { 0.3F, 0.3F, 0.3F}, { 0.6F, 0.6F, 0.6F}, { 0.3F, 0.5F, 0.6F}, { 0.5F, 0.25F, 0.7F}, { 0.2F, 0.3F, 0.7F}, { 0.4F, 0.3F, 0.2F}, { 0.4F, 0.5F, 0.2F}, { 0.6F, 0.2F, 0.2F}, { 0.1F, 0.1F, 0.1F}};
    private int br;
    private OEntityAIEatGrass bs = new OEntityAIEatGrass(this);

    private Sheep sheep = new Sheep(this); // CanaryMod: one sheep per sheep

    public OEntitySheep(OWorld oworld) {
        super(oworld);
        this.a(0.9F, 1.3F);
        this.k().a(true);
        this.c.a(0, new OEntityAISwimming(this));
        this.c.a(1, new OEntityAIPanic(this, 1.25D));
        this.c.a(2, new OEntityAIMate(this, 1.0D));
        this.c.a(3, new OEntityAITempt(this, 1.1D, OItem.V.cv, false));
        this.c.a(4, new OEntityAIFollowParent(this, 1.1D));
        this.c.a(5, this.bs);
        this.c.a(6, new OEntityAIWander(this, 1.0D));
        this.c.a(7, new OEntityAIWatchClosest(this, OEntityPlayer.class, 6.0F));
        this.c.a(8, new OEntityAILookIdle(this));
        this.bq.a(0, new OItemStack(OItem.aY, 1, 0));
        this.bq.a(1, new OItemStack(OItem.aY, 1, 0));
    }

    protected boolean bb() {
        return true;
    }

    protected void be() {
        this.br = this.bs.f();
        super.be();
    }

    public void c() {
        if (this.q.I) {
            this.br = Math.max(0, this.br - 1);
        }

        super.c();
    }

    protected void ax() {
        super.ax();
        this.a(OSharedMonsterAttributes.a).a(8.0D);
        this.a(OSharedMonsterAttributes.d).a(0.23000000417232513D);
    }

    protected void a() {
        super.a();
        this.ah.a(16, new Byte((byte) 0));
    }

    protected void b(boolean flag, int i) {
        if (!this.bQ()) {
            this.a(new OItemStack(OBlock.ag.cF, 1, this.bP()), 0.0F);
        }
    }

    protected int s() {
        return OBlock.ag.cF;
    }

    public boolean a(OEntityPlayer oentityplayer) {
        OItemStack oitemstack = oentityplayer.bn.h();

        if (oitemstack != null && oitemstack.d == OItem.bg.cv && !this.bQ() && !this.g_()) {
            if (!this.q.I) {
                this.j(true);
                int i = 1 + this.ab.nextInt(3);

                for (int j = 0; j < i; ++j) {
                    OEntityItem oentityitem = this.a(new OItemStack(OBlock.ag.cF, 1, this.bP()), 1.0F);

                    oentityitem.y += (double) (this.ab.nextFloat() * 0.05F);
                    oentityitem.x += (double) ((this.ab.nextFloat() - this.ab.nextFloat()) * 0.1F);
                    oentityitem.z += (double) ((this.ab.nextFloat() - this.ab.nextFloat()) * 0.1F);
                }
            }

            oitemstack.a(1, (OEntityLivingBase) oentityplayer);
            this.a("mob.sheep.shear", 1.0F, 1.0F);
        }

        return super.a(oentityplayer);
    }

    public void b(ONBTTagCompound onbttagcompound) {
        super.b(onbttagcompound);
        onbttagcompound.a("Sheared", this.bQ());
        onbttagcompound.a("Color", (byte) this.bP());
    }

    public void a(ONBTTagCompound onbttagcompound) {
        super.a(onbttagcompound);
        this.j(onbttagcompound.n("Sheared"));
        this.p(onbttagcompound.c("Color"));
    }

    protected String r() {
        return "mob.sheep.say";
    }

    protected String aK() {
        return "mob.sheep.say";
    }

    protected String aL() {
        return "mob.sheep.say";
    }

    protected void a(int i, int j, int k, int l) {
        this.a("mob.sheep.step", 0.15F, 1.0F);
    }

    public int bP() {
        return this.ah.a(16) & 15;
    }

    public void p(int i) {
        byte b0 = this.ah.a(16);

        this.ah.b(16, Byte.valueOf((byte) (b0 & 240 | i & 15)));
    }

    public boolean bQ() {
        return (this.ah.a(16) & 16) != 0;
    }

    public void j(boolean flag) {
        byte b0 = this.ah.a(16);

        if (flag) {
            this.ah.b(16, Byte.valueOf((byte) (b0 | 16)));
        } else {
            this.ah.b(16, Byte.valueOf((byte) (b0 & -17)));
        }
    }

    public static int a(Random random) {
        int i = random.nextInt(100);

        return i < 5 ? 15 : (i < 10 ? 7 : (i < 15 ? 8 : (i < 18 ? 12 : (random.nextInt(500) == 0 ? 6 : 0))));
    }

    public OEntitySheep b(OEntityAgeable oentityageable) {
        OEntitySheep oentitysheep = (OEntitySheep) oentityageable;
        OEntitySheep oentitysheep1 = new OEntitySheep(this.q);
        int i = this.a(this, oentitysheep);

        oentitysheep1.p(15 - i);
        return oentitysheep1;
    }

    public void n() {
        this.j(false);
        if (this.g_()) {
            this.a(60);
        }
    }

    public OEntityLivingData a(OEntityLivingData oentitylivingdata) {
        oentitylivingdata = super.a(oentitylivingdata);
        this.p(a(this.q.s));
        return oentitylivingdata;
    }

    private int a(OEntityAnimal oentityanimal, OEntityAnimal oentityanimal1) {
        int i = this.b(oentityanimal);
        int j = this.b(oentityanimal1);

        this.bq.a(0).b(i);
        this.bq.a(1).b(j);
        OItemStack oitemstack = OCraftingManager.a().a(this.bq, ((OEntitySheep) oentityanimal).q);
        int k;

        if (oitemstack != null && oitemstack.b().cv == OItem.aY.cv) {
            k = oitemstack.k();
        } else {
            k = this.q.s.nextBoolean() ? i : j;
        }

        return k;
    }

    private int b(OEntityAnimal oentityanimal) {
        return 15 - ((OEntitySheep) oentityanimal).bP();
    }

    public OEntityAgeable a(OEntityAgeable oentityageable) {
        return this.b(oentityageable);
    }

    @Override
    public Sheep getEntity() {
        return sheep;
    }
}
