
public class OEntitySlime extends OEntityLiving implements OIMob {

    public float a;
    public float b;
    public float c;
    private int d = 0;

    // CanaryMod start
    protected LivingEntity entity = new LivingEntity(this);
    // CanaryMod end

    public OEntitySlime(OWorld var1) {
        super(var1);
        this.ae = "/mob/slime.png";
        int var2 = 1 << this.bS.nextInt(3);
        this.bF = 0.0F;
        this.d = this.bS.nextInt(20) + 10;
        this.c(var2);
    }

    protected void b() {
        super.b();
        this.bY.a(16, new Byte((byte) 1));
    }

    public void c(int var1) {
        this.bY.b(16, new Byte((byte) var1));
        this.b(0.6F * (float) var1, 0.6F * (float) var1);
        this.c(this.bm, this.bn, this.bo);
        this.h(this.d());
        this.aA = var1;
    }

    public int d() {
        int var1 = this.L();
        return var1 * var1;
    }

    public int L() {
        return this.bY.a(16);
    }

    public void b(ONBTTagCompound var1) {
        super.b(var1);
        var1.a("Size", this.L() - 1);
    }

    public void a(ONBTTagCompound var1) {
        super.a(var1);
        this.c(var1.f("Size") + 1);
    }

    protected String A() {
        return "slime";
    }

    protected String I() {
        return "mob.slime";
    }

    public void F_() {
        if (!this.bi.F && this.bi.q == 0 && this.L() > 0) {
            this.bE = true;
        }

        this.b += (this.a - this.b) * 0.5F;
        this.c = this.b;
        boolean var1 = this.bx;
        super.F_();
        if (this.bx && !var1) {
            int var2 = this.L();

            for (int var3 = 0; var3 < var2 * 8; ++var3) {
                float var4 = this.bS.nextFloat() * 3.1415927F * 2.0F;
                float var5 = this.bS.nextFloat() * 0.5F + 0.5F;
                float var6 = OMathHelper.a(var4) * (float) var2 * 0.5F * var5;
                float var7 = OMathHelper.b(var4) * (float) var2 * 0.5F * var5;
                this.bi.a(this.A(), this.bm + (double) var6, this.bw.b, this.bo + (double) var7, 0.0D, 0.0D, 0.0D);
            }

            if (this.K()) {
                this.bi.a(this, this.I(), this.p(), ( (this.bS.nextFloat() - this.bS.nextFloat()) * 0.2F + 1.0F) / 0.8F);
            }

            this.a = -0.5F;
        }

        this.F();
    }

    protected void d_() {
        this.aG();
        OEntityPlayer var1 = this.bi.b(this, 16.0D);
        if (var1 != null && !(Boolean) etc.getLoader().callHook(PluginLoader.Hook.MOB_TARGET, (Player) var1.entity.getPlayer(), entity)) { //CanaryMod - MOB_TARGET
            this.a(var1, 10.0F, 20.0F);
        }

        if (this.bx && this.d-- <= 0) {
            this.d = this.E();
            if (var1 != null) {
                this.d /= 3;
            }

            this.aZ = true;
            if (this.M()) {
                this.bi.a(this, this.I(), this.p(), ( (this.bS.nextFloat() - this.bS.nextFloat()) * 0.2F + 1.0F) * 0.8F);
            }

            this.a = 1.0F;
            this.aW = 1.0F - this.bS.nextFloat() * 2.0F;
            this.aX = (float) (1 * this.L());
        } else {
            this.aZ = false;
            if (this.bx) {
                this.aW = this.aX = 0.0F;
            }
        }

    }

    protected void F() {
        this.a *= 0.6F;
    }

    protected int E() {
        return this.bS.nextInt(20) + 10;
    }

    protected OEntitySlime C() {
        return new OEntitySlime(this.bi);
    }

    public void X() {
        int var1 = this.L();
        if (!this.bi.F && var1 > 1 && this.aD() <= 0) {
            int var2 = 2 + this.bS.nextInt(3);

            for (int var3 = 0; var3 < var2; ++var3) {
                float var4 = ((float) (var3 % 2) - 0.5F) * (float) var1 / 4.0F;
                float var5 = ((float) (var3 / 2) - 0.5F) * (float) var1 / 4.0F;
                OEntitySlime var6 = this.C();
                var6.c(var1 / 2);
                var6.c(this.bm + (double) var4, this.bn + 0.5D, this.bo + (double) var5, this.bS.nextFloat() * 360.0F, 0.0F);
                this.bi.b((OEntity) var6);
            }
        }

        super.X();
    }

    public void a_(OEntityPlayer var1) {
        if (this.G()) {
            int var2 = this.L();
            if (this.h(var1) && (double) this.i(var1) < 0.6D * (double) var2 && var1.a(ODamageSource.a((OEntityLiving) this), this.H())) {
                this.bi.a(this, "mob.slimeattack", 1.0F, (this.bS.nextFloat() - this.bS.nextFloat()) * 0.2F + 1.0F);
            }
        }

    }

    protected boolean G() {
        return this.L() > 1;
    }

    protected int H() {
        return this.L();
    }

    protected String j() {
        return "mob.slime";
    }

    protected String k() {
        return "mob.slime";
    }

    protected int f() {
        return this.L() == 1 ? OItem.aL.bP : 0;
    }

    public boolean l() {
        OChunk var1 = this.bi.c(OMathHelper.b(this.bm), OMathHelper.b(this.bo));
        return (this.L() == 1 || this.bi.q > 0) && this.bS.nextInt(10) == 0 && var1.a(987234911L).nextInt(10) == 0 && this.bn < 40.0D ? super.l() : false;
    }

    protected float p() {
        return 0.4F * (float) this.L();
    }

    public int D() {
        return 0;
    }

    protected boolean M() {
        return this.L() > 1;
    }

    protected boolean K() {
        return this.L() > 2;
    }
}
