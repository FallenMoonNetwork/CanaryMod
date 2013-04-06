import java.util.Random;

public class OEntitySheep extends OEntityAnimal {

    private final OInventoryCrafting e = new OInventoryCrafting(new OContainerSheep(this), 2, 1);
    public static final float[][] d = new float[][] { { 1.0F, 1.0F, 1.0F}, { 0.85F, 0.5F, 0.2F}, { 0.7F, 0.3F, 0.85F}, { 0.4F, 0.6F, 0.85F}, { 0.9F, 0.9F, 0.2F}, { 0.5F, 0.8F, 0.1F}, { 0.95F, 0.5F, 0.65F}, { 0.3F, 0.3F, 0.3F}, { 0.6F, 0.6F, 0.6F}, { 0.3F, 0.5F, 0.6F}, { 0.5F, 0.25F, 0.7F}, { 0.2F, 0.3F, 0.7F}, { 0.4F, 0.3F, 0.2F}, { 0.4F, 0.5F, 0.2F}, { 0.6F, 0.2F, 0.2F}, { 0.1F, 0.1F, 0.1F}};
    private int f;
    private OEntityAIEatGrass g = new OEntityAIEatGrass(this);

    private Sheep sheep = new Sheep(this); // CanaryMod: one sheep per sheep

    public OEntitySheep(OWorld oworld) {
        super(oworld);
        this.aH = "/mob/sheep.png";
        this.a(0.9F, 1.3F);
        float f = 0.23F;

        this.aC().a(true);
        this.bo.a(0, new OEntityAISwimming(this));
        this.bo.a(1, new OEntityAIPanic(this, 0.38F));
        this.bo.a(2, new OEntityAIMate(this, f));
        this.bo.a(3, new OEntityAITempt(this, 0.25F, OItem.U.cp, false));
        this.bo.a(4, new OEntityAIFollowParent(this, 0.25F));
        this.bo.a(5, this.g);
        this.bo.a(6, new OEntityAIWander(this, f));
        this.bo.a(7, new OEntityAIWatchClosest(this, OEntityPlayer.class, 6.0F));
        this.bo.a(8, new OEntityAILookIdle(this));
        this.e.a(0, new OItemStack(OItem.aX, 1, 0));
        this.e.a(1, new OItemStack(OItem.aX, 1, 0));
    }

    protected boolean bh() {
        return true;
    }

    protected void bo() {
        this.f = this.g.f();
        super.bo();
    }

    public void c() {
        if (this.q.I) {
            this.f = Math.max(0, this.f - 1);
        }

        super.c();
    }

    public int aW() {
        return 8;
    }

    protected void a() {
        super.a();
        this.ah.a(16, new Byte((byte) 0));
    }

    protected void a(boolean flag, int i) {
        if (!this.n()) {
            this.a(new OItemStack(OBlock.af.cz, 1, this.m()), 0.0F);
        }
    }

    protected int be() {
        return OBlock.af.cz;
    }

    public boolean a_(OEntityPlayer oentityplayer) {
        OItemStack oitemstack = oentityplayer.bK.h();

        if (oitemstack != null && oitemstack.c == OItem.bf.cp && !this.n() && !this.h_()) {
            if (!this.q.I) {
                this.i(true);
                int i = 1 + this.ab.nextInt(3);

                for (int j = 0; j < i; ++j) {
                    OEntityItem oentityitem = this.a(new OItemStack(OBlock.af.cz, 1, this.m()), 1.0F);

                    oentityitem.y += (double) (this.ab.nextFloat() * 0.05F);
                    oentityitem.x += (double) ((this.ab.nextFloat() - this.ab.nextFloat()) * 0.1F);
                    oentityitem.z += (double) ((this.ab.nextFloat() - this.ab.nextFloat()) * 0.1F);
                }
            }

            oitemstack.a(1, (OEntityLiving) oentityplayer);
            this.a("mob.sheep.shear", 1.0F, 1.0F);
        }

        return super.a_(oentityplayer);
    }

    public void b(ONBTTagCompound onbttagcompound) {
        super.b(onbttagcompound);
        onbttagcompound.a("Sheared", this.n());
        onbttagcompound.a("Color", (byte) this.m());
    }

    public void a(ONBTTagCompound onbttagcompound) {
        super.a(onbttagcompound);
        this.i(onbttagcompound.n("Sheared"));
        this.s(onbttagcompound.c("Color"));
    }

    protected String bb() {
        return "mob.sheep.say";
    }

    protected String bc() {
        return "mob.sheep.say";
    }

    protected String bd() {
        return "mob.sheep.say";
    }

    protected void a(int i, int j, int k, int l) {
        this.a("mob.sheep.step", 0.15F, 1.0F);
    }

    public int m() {
        return this.ah.a(16) & 15;
    }

    public void s(int i) {
        byte b0 = this.ah.a(16);

        this.ah.b(16, Byte.valueOf((byte) (b0 & 240 | i & 15)));
    }

    public boolean n() {
        return (this.ah.a(16) & 16) != 0;
    }

    public void i(boolean flag) {
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

        oentitysheep1.s(15 - i);
        return oentitysheep1;
    }

    public void aK() {
        this.i(false);
        if (this.h_()) {
            int i = this.b() + 1200;

            if (i > 0) {
                i = 0;
            }

            this.a(i);
        }
    }

    public void bJ() {
        this.s(a(this.q.s));
    }

    private int a(OEntityAnimal oentityanimal, OEntityAnimal oentityanimal1) {
        int i = this.b(oentityanimal);
        int j = this.b(oentityanimal1);

        this.e.a(0).b(i);
        this.e.a(1).b(j);
        OItemStack oitemstack = OCraftingManager.a().a(this.e, ((OEntitySheep) oentityanimal).q);
        int k;

        if (oitemstack != null && oitemstack.b().cp == OItem.aX.cp) {
            k = oitemstack.k();
        } else {
            k = this.q.s.nextBoolean() ? i : j;
        }

        return k;
    }

    private int b(OEntityAnimal oentityanimal) {
        return 15 - ((OEntitySheep) oentityanimal).m();
    }

    public OEntityAgeable a(OEntityAgeable oentityageable) {
        return this.b(oentityageable);
    }

    @Override
    public Sheep getEntity() {
        return sheep;
    }
}
