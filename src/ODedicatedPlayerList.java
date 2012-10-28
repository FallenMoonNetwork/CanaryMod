import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Iterator;

public class ODedicatedPlayerList extends OServerConfigurationManager {

    private File e;
    private File f;

    public ODedicatedPlayerList(ODedicatedServer odedicatedserver) {
        super(odedicatedserver);
        this.e = odedicatedserver.e("ops.txt");
        // this.f = odedicatedserver.e("white-list.txt"); // CanaryMod: disable Notchian whitelist
        this.d = odedicatedserver.a("view-distance", 10);
        this.c = odedicatedserver.a("max-players", 20);
        this.a(odedicatedserver.a("white-list", false));
        if (!odedicatedserver.I()) {
            this.e().a(true);
            this.f().a(true);
        }

        this.e().e();
        this.e().f();
        this.f().e();
        this.f().f();
        this.t();
        //this.v();
        this.u();
        //this.w();
    }

    public void a(boolean flag) {
        super.a(flag);
        this.s().a("white-list", Boolean.valueOf(flag));
        this.s().a();
    }

    public void b(String s) {
        super.b(s);
        this.u();
    }

    public void c(String s) {
        super.c(s);
        this.u();
    }

    public void i(String s) {
        super.i(s);
        this.w();
    }

    public void h(String s) {
        super.h(s);
        this.w();
    }

    public void j() {
        this.v();
    }

    private void t() {
        try {
            this.i().clear();
            BufferedReader bufferedreader = new BufferedReader(new FileReader(this.e));
            String s = "";

            while ((s = bufferedreader.readLine()) != null) {
                this.i().add(s.trim().toLowerCase());
            }

            bufferedreader.close();
        } catch (Exception exception) {
            a.warning("Failed to load operators list: " + exception);
        }
    }

    private void u() {
        try {
            PrintWriter printwriter = new PrintWriter(new FileWriter(this.e, false));
            Iterator iterator = this.i().iterator();

            while (iterator.hasNext()) {
                String s = (String) iterator.next();

                printwriter.println(s);
            }

            printwriter.close();
        } catch (Exception exception) {
            a.warning("Failed to save operators list: " + exception);
        }
    }

    private void v() {
        try {
            this.h().clear();
            BufferedReader bufferedreader = new BufferedReader(new FileReader(this.f));
            String s = "";

            while ((s = bufferedreader.readLine()) != null) {
                this.h().add(s.trim().toLowerCase());
            }

            bufferedreader.close();
        } catch (Exception exception) {
            a.warning("Failed to load white-list: " + exception);
        }
    }

    private void w() {
        try {
            PrintWriter printwriter = new PrintWriter(new FileWriter(this.f, false));
            Iterator iterator = this.h().iterator();

            while (iterator.hasNext()) {
                String s = (String) iterator.next();

                printwriter.println(s);
            }

            printwriter.close();
        } catch (Exception exception) {
            a.warning("Failed to save white-list: " + exception);
        }
    }

    public boolean d(String s) {
        s = s.trim().toLowerCase();
        return !this.n() || this.e(s) || this.h().contains(s);
    }

    public ODedicatedServer s() {
        return (ODedicatedServer) super.p();
    }

    public OMinecraftServer p() {
        return this.s();
    }
}
