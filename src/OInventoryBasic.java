import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class OInventoryBasic implements OIInventory, Container<OItemStack> {

    private String a;
    private int b;
    protected OItemStack[] c; //CanaryMod: private -> protected
    private List d;
    private boolean e;

    public OInventoryBasic(String s, boolean flag, int i) {
        this.a = s;
        this.e = flag;
        this.b = i;
        this.c = new OItemStack[i];
    }

    public void a(OIInvBasic oiinvbasic) {
        if (this.d == null) {
            this.d = new ArrayList();
        }

        this.d.add(oiinvbasic);
    }

    public void b(OIInvBasic oiinvbasic) {
        this.d.remove(oiinvbasic);
    }

    public OItemStack a(int i) {
        return this.c[i];
    }

    public OItemStack a(int i, int j) {
        if (this.c[i] != null) {
            OItemStack oitemstack;

            if (this.c[i].b <= j) {
                oitemstack = this.c[i];
                this.c[i] = null;
                this.e();
                return oitemstack;
            } else {
                oitemstack = this.c[i].a(j);
                if (this.c[i].b == 0) {
                    this.c[i] = null;
                }

                this.e();
                return oitemstack;
            }
        } else {
            return null;
        }
    }

    public OItemStack a_(int i) {
        if (this.c[i] != null) {
            OItemStack oitemstack = this.c[i];

            this.c[i] = null;
            return oitemstack;
        } else {
            return null;
        }
    }

    public void a(int i, OItemStack oitemstack) {
        this.c[i] = oitemstack;
        if (oitemstack != null && oitemstack.b > this.d()) {
            oitemstack.b = this.d();
        }

        this.e();
    }

    public int j_() {
        return this.b;
    }

    public String b() {
        return this.a;
    }

    public boolean c() {
        return this.e;
    }

    public void a(String s) {
        this.e = true;
        this.a = s;
    }

    public int d() {
        return 64;
    }

    public void e() {
        if (this.d != null) {
            for (int i = 0; i < this.d.size(); ++i) {
                ((OIInvBasic) this.d.get(i)).a(this);
            }
        }
    }

    public boolean a(OEntityPlayer oentityplayer) {
        return true;
    }

    public void k_() {}

    public void g() {}

    public boolean b(int i, OItemStack oitemstack) {
        return true;
    }


    @Override
    public OItemStack[] getContents() {
        return Arrays.copyOf(this.c, getContentsSize());
    }

    @Override
    public void setContents(OItemStack[] aoitemstack) {
        this.c = Arrays.copyOf(aoitemstack, getContentsSize());
    }

    @Override
    public OItemStack getContentsAt(int i) {
        return this.a(i);
    }

    @Override
    public void setContentsAt(int i, OItemStack oitemstack) {
        this.a(i, oitemstack);
    }

    @Override
    public int getContentsSize() {
        return this.j_();
    }

    @Override
    public String getName() {
        return this.a;
    }

    @Override
    public void setName(String s) {
        this.a = s;
    }
}
