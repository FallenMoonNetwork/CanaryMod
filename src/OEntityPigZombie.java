import java.util.List;

public class OEntityPigZombie extends OEntityZombie {

    private int d = 0;
    private int e = 0;

    public OEntityPigZombie(OWorld oworld) {
        super(oworld);
        this.aH = "/mob/pigzombie.png";
        this.bI = 0.5F;
        this.ag = true;
    }

    protected boolean bh() {
        return false;
    }

    public void l_() {
        this.bI = this.a_ != null ? 0.95F : 0.5F;
        if (this.e > 0 && --this.e == 0) {
            this.a("mob.zombiepig.zpigangry", this.ba() * 2.0F, ((this.ab.nextFloat() - this.ab.nextFloat()) * 0.2F + 1.0F) * 1.8F);
        }

        super.l_();
    }

    public boolean bv() {
        return this.q.r > 0 && this.q.b(this.E) && this.q.a((OEntity) this, this.E).isEmpty() && !this.q.d(this.E);
    }

    public void b(ONBTTagCompound onbttagcompound) {
        super.b(onbttagcompound);
        onbttagcompound.a("Anger", (short) this.d);
    }

    public void a(ONBTTagCompound onbttagcompound) {
        super.a(onbttagcompound);
        this.d = onbttagcompound.d("Anger");
    }

    protected OEntity j() {
        return this.d == 0 ? null : super.j();
    }

    public boolean a(ODamageSource odamagesource, int i) {
        if (this.aq()) {
            return false;
        } else {
            OEntity oentity = odamagesource.i();

            if (oentity instanceof OEntityPlayer) {
                if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.MOB_TARGET, oentity.getEntity(), this.getEntity())) { // CanaryMod: MOB_TARGET
                    List list = this.q.b((OEntity) this, this.E.b(32.0D, 32.0D, 32.0D));

                    for (int j = 0; j < list.size(); ++j) {
                        OEntity oentity1 = (OEntity) list.get(j);

                        if (oentity1 instanceof OEntityPigZombie) {
                            OEntityPigZombie oentitypigzombie = (OEntityPigZombie) oentity1;

                            oentitypigzombie.p(oentity);
                        }
                    }

                    this.p(oentity);
                } //
            }

            return super.a(odamagesource, i);
        }
    }

    private void p(OEntity oentity) {
        this.a_ = oentity;
        this.d = 400 + this.ab.nextInt(400);
        this.e = this.ab.nextInt(40);
    }

    protected String bb() {
        return "mob.zombiepig.zpig";
    }

    protected String bc() {
        return "mob.zombiepig.zpighurt";
    }

    protected String bd() {
        return "mob.zombiepig.zpigdeath";
    }

    protected void a(boolean flag, int i) {
        int j = this.ab.nextInt(2 + i);

        int k;

        for (k = 0; k < j; ++k) {
            this.b(OItem.bn.cp, 1);
        }

        j = this.ab.nextInt(2 + i);

        for (k = 0; k < j; ++k) {
            this.b(OItem.br.cp, 1);
        }
    }

    public boolean a_(OEntityPlayer oentityplayer) {
        return false;
    }

    protected void l(int i) {
        this.b(OItem.q.cp, 1);
    }

    protected int be() {
        return OItem.bn.cp;
    }

    protected void bH() {
        this.c(0, new OItemStack(OItem.H));
    }

    public void bJ() {
        super.bJ();
        this.i(false);
    }

    public int c(OEntity oentity) {
        OItemStack oitemstack = this.bG();
        int i = 5;

        if (oitemstack != null) {
            i += oitemstack.a((OEntity) this);
        }

        return i;
    }
}
