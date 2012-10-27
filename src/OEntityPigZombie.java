import java.util.Iterator;
import java.util.List;


public class OEntityPigZombie extends OEntityZombie {

    private int d = 0;
    private int e = 0;

    public OEntityPigZombie(OWorld oworld) {
        super(oworld);
        this.aF = "/mob/pigzombie.png";
        this.bI = 0.5F;
        this.af = true;
    }

    protected boolean bb() {
        return false;
    }

    public void j_() {
        this.bI = this.a_ != null ? 0.95F : 0.5F;
        if (this.e > 0 && --this.e == 0) {
            this.p.a(this, "mob.zombiepig.zpigangry", this.aV() * 2.0F, ((this.aa.nextFloat() - this.aa.nextFloat()) * 0.2F + 1.0F) * 1.8F);
        }

        super.j_();
    }

    public boolean bp() {
        return this.p.t > 0 && this.p.b(this.D) && this.p.a((OEntity) this, this.D).isEmpty() && !this.p.d(this.D);
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
        OEntity oentity = odamagesource.g();

        if (oentity instanceof OEntityPlayer) {
            if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.MOB_TARGET, (Player) oentity.entity.getPlayer(), this.entity)) { // CanaryMod: MOB_TARGET

                List list = this.p.b((OEntity) this, this.D.b(32.0D, 32.0D, 32.0D));
                Iterator iterator = list.iterator();

                while (iterator.hasNext()) {
                    OEntity oentity1 = (OEntity) iterator.next();

                    if (oentity1 instanceof OEntityPigZombie) {
                        OEntityPigZombie oentitypigzombie = (OEntityPigZombie) oentity1;

                    oentitypigzombie.o(oentity);
                    }
                }
                this.o(oentity);
            }

        }

        return super.a(odamagesource, i);
    }

    private void o(OEntity oentity) {
        this.a_ = oentity;
        this.d = 400 + this.aa.nextInt(400);
        this.e = this.aa.nextInt(40);
    }

    protected String aW() {
        return "mob.zombiepig.zpig";
    }

    protected String aX() {
        return "mob.zombiepig.zpighurt";
    }

    protected String aY() {
        return "mob.zombiepig.zpigdeath";
    }

    protected void a(boolean flag, int i) {
        int j = this.aa.nextInt(2 + i);

        int k;

        for (k = 0; k < j; ++k) {
            this.b(OItem.bm.cf, 1);
        }

        j = this.aa.nextInt(2 + i);

        for (k = 0; k < j; ++k) {
            this.b(OItem.bq.cf, 1);
        }
    }

    public boolean c(OEntityPlayer oentityplayer) {
        return false;
    }

    protected void l(int i) {
        this.b(OItem.p.cf, 1);
    }

    protected int aZ() {
        return OItem.bm.cf;
    }

    protected void bB() {
        this.b(0, new OItemStack(OItem.G));
            }

    public void bD() {
        super.bD();
        this.g(false);
        }

    public int c(OEntity oentity) {
        OItemStack oitemstack = this.bA();
        int i = 5;

        if (oitemstack != null) {
            i += oitemstack.a((OEntity) this);
        }
        
        return i;
    }

}
