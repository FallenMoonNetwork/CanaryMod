import java.util.List;
import java.util.UUID;

public class OEntityPigZombie extends OEntityZombie {

    private static final UUID bq = UUID.fromString("49455A49-7EC5-45BA-B886-3B90B23A1718");
    private static final OAttributeModifier br = (new OAttributeModifier(bq, "Attacking speed boost", 0.45D, 0)).a(false);
    private int bs;
    private int bt;
    private OEntity bu;

    public OEntityPigZombie(OWorld oworld) {
        super(oworld);
        this.ag = true;
    }

    protected void ay() {
        super.ay();
        this.a(bp).a(0.0D);
        this.a(OSharedMonsterAttributes.d).a(0.5D);
        this.a(OSharedMonsterAttributes.e).a(5.0D);
    }

    protected boolean be() {
        return false;
    }

    public void l_() {
        if (this.bu != this.j && !this.q.I) {
            OAttributeInstance oattributeinstance = this.a(OSharedMonsterAttributes.d);

            oattributeinstance.b(br);
            if (this.j != null) {
                oattributeinstance.a(br);
            }
        }

        this.bu = this.j;
        if (this.bt > 0 && --this.bt == 0) {
            this.a("mob.zombiepig.zpigangry", this.aZ() * 2.0F, ((this.ab.nextFloat() - this.ab.nextFloat()) * 0.2F + 1.0F) * 1.8F);
        }

        super.l_();
    }

    public boolean bs() {
        return this.q.r > 0 && this.q.b(this.E) && this.q.a((OEntity) this, this.E).isEmpty() && !this.q.d(this.E);
    }

    public void b(ONBTTagCompound onbttagcompound) {
        super.b(onbttagcompound);
        onbttagcompound.a("Anger", (short) this.bs);
    }

    public void a(ONBTTagCompound onbttagcompound) {
        super.a(onbttagcompound);
        this.bs = onbttagcompound.d("Anger");
    }

    protected OEntity bL() {
        return this.bs == 0 ? null : super.bL();
    }

    public boolean a(ODamageSource odamagesource, float f) {
        if (this.aq()) {
            return false;
        } else {
            OEntity oentity = odamagesource.i();

            if (oentity instanceof OEntityPlayer) {
                if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.MOB_TARGET, oentity.getEntity(), this.getEntity())) { // CanaryMod: MOB_TARGET
                    List list = this.q.b((OEntity) this, this.E.b(32.0D, 32.0D, 32.0D));

                    for (int i = 0; i < list.size(); ++i) {
                        OEntity oentity1 = (OEntity) list.get(i);

                        if (oentity1 instanceof OEntityPigZombie) {
                            OEntityPigZombie oentitypigzombie = (OEntityPigZombie) oentity1;

                            oentitypigzombie.c(oentity);
                        }
                    }

                    this.c(oentity);
                } //
            }

            return super.a(odamagesource, f);
        }
    }

    private void c(OEntity oentity) {
        this.j = oentity;
        this.bs = 400 + this.ab.nextInt(400);
        this.bt = this.ab.nextInt(40);
    }

    protected String r() {
        return "mob.zombiepig.zpig";
    }

    protected String aN() {
        return "mob.zombiepig.zpighurt";
    }

    protected String aO() {
        return "mob.zombiepig.zpigdeath";
    }

    protected void b(boolean flag, int i) {
        int j = this.ab.nextInt(2 + i);

        int k;

        for (k = 0; k < j; ++k) {
            this.b(OItem.bo.cv, 1);
        }

        j = this.ab.nextInt(2 + i);

        for (k = 0; k < j; ++k) {
            this.b(OItem.bs.cv, 1);
        }
    }

    public boolean a(OEntityPlayer oentityplayer) {
        return false;
    }

    protected void l(int i) {
        this.b(OItem.r.cv, 1);
    }

    protected int s() {
        return OItem.bo.cv;
    }

    protected void bw() {
        this.c(0, new OItemStack(OItem.I));
    }

    public OEntityLivingData a(OEntityLivingData oentitylivingdata) {
        super.a(oentitylivingdata);
        this.i(false);
        return oentitylivingdata;
    }
}
