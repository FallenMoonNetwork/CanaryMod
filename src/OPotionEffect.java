public class OPotionEffect {

    int a; // CanaryMod: private -> package-private
    int b; // CanaryMod: private -> package-private
    int c; // CanaryMod: private -> package-private
    private boolean d;
    private boolean e;
    public boolean permanent = false;
    public PotionEffect potionEffect = new PotionEffect(this);

    public OPotionEffect(int i, int j) {
        this(i, j, 0);
    }

    public OPotionEffect(int i, int j, int k) {
        this(i, j, k, false);
    }

    public OPotionEffect(int i, int j, int k, boolean flag) {
        this.a = i;
        this.b = j;
        this.c = k;
        this.e = flag;
    }

    public OPotionEffect(OPotionEffect opotioneffect) {
        this.a = opotioneffect.a;
        this.b = opotioneffect.b;
        this.c = opotioneffect.c;
    }

    public void a(OPotionEffect opotioneffect) {
        if (this.a != opotioneffect.a) {
            System.err.println("This method should only be called for matching effects!");
        }

        if (opotioneffect.c > this.c) {
            this.c = opotioneffect.c;
            this.b = opotioneffect.b;
        } else if (opotioneffect.c == this.c && this.b < opotioneffect.b) {
            this.b = opotioneffect.b;
        } else if (!opotioneffect.e && this.e) {
            this.e = opotioneffect.e;
        }
    }

    public int a() {
        return this.a;
    }

    public int b() {
        return this.b;
    }

    public int c() {
        return this.c;
    }

    public boolean d() {
        return this.d;
    }

    public void a(boolean flag) {
        this.d = flag;
    }

    public boolean e() {
        return this.e;
    }

    public boolean a(OEntityLiving oentityliving) {
        if (this.b > 0) {
            if (OPotion.a[this.a].a(this.b, this.c)) {
                this.b(oentityliving);
            }

            this.h();
        }

        return this.b > 0;
    }

    private int h() {
        return this.permanent ? this.b : --this.b; // CanaryMod
    }

    public void b(OEntityLiving oentityliving) {
        if (this.b > 0) {
            OPotion.a[this.a].a(oentityliving, this.c);
        }
    }

    public String f() {
        return OPotion.a[this.a].a();
    }

    public int hashCode() {
        return this.a;
    }

    public String toString() {
        String s = "";

        if (this.c() > 0) {
            s = this.f() + " x " + (this.c() + 1) + ", Duration: " + this.b();
        } else {
            s = this.f() + ", Duration: " + this.b();
        }

        if (this.d) {
            s = s + ", Splash: true";
        }

        return OPotion.a[this.a].i() ? "(" + s + ")" : s;
    }

    public boolean equals(Object object) {
        if (!(object instanceof OPotionEffect)) {
            return false;
        } else {
            OPotionEffect opotioneffect = (OPotionEffect) object;

            return this.a == opotioneffect.a && this.c == opotioneffect.c && this.b == opotioneffect.b && this.d == opotioneffect.d && this.e == opotioneffect.e;
        }
    }

    public ONBTTagCompound a(ONBTTagCompound onbttagcompound) {
        onbttagcompound.a("Id", (byte) this.a());
        onbttagcompound.a("Amplifier", (byte) this.c());
        onbttagcompound.a("Duration", this.b());
        onbttagcompound.a("Ambient", this.e());
        return onbttagcompound;
    }

    public static OPotionEffect b(ONBTTagCompound onbttagcompound) {
        byte b0 = onbttagcompound.c("Id");
        byte b1 = onbttagcompound.c("Amplifier");
        int i = onbttagcompound.e("Duration");
        boolean flag = onbttagcompound.n("Ambient");

        return new OPotionEffect(b0, i, b1, flag);
    }
}
