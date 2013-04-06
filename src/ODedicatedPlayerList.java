import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Iterator;

public class ODedicatedPlayerList extends OServerConfigurationManager {

    private File d;
    private File e;

    public ODedicatedPlayerList(ODedicatedServer odedicatedserver) {
        super(odedicatedserver);
        this.d = odedicatedserver.e("ops.txt");
        // this.e = odedicatedserver.e("white-list.txt"); // CanaryMod: disable Notchian whitelist
        this.c = odedicatedserver.a("view-distance", 10);
        this.b = odedicatedserver.a("max-players", 20);
        // this.a(odedicatedserver.a("white-list", false)); // CanaryMod: disable Notchian whitelist
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
        /* if (!this.e.exists()) {
            this.w();
        }*/
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
            BufferedReader bufferedreader = new BufferedReader(new FileReader(this.d));
            String s = "";

            while ((s = bufferedreader.readLine()) != null) {
                this.i().add(s.trim().toLowerCase());
            }

            bufferedreader.close();
        } catch (Exception exception) {
            this.s().al().b("Failed to load operators list: " + exception);
        }
    }

    private void u() {
        try {
            PrintWriter printwriter = new PrintWriter(new FileWriter(this.d, false));
            Iterator iterator = this.i().iterator();

            while (iterator.hasNext()) {
                String s = (String) iterator.next();

                printwriter.println(s);
            }

            printwriter.close();
        } catch (Exception exception) {
            this.s().al().b("Failed to save operators list: " + exception);
        }
    }

    private void v() {
        try {
            this.h().clear();
            BufferedReader bufferedreader = new BufferedReader(new FileReader(this.e));
            String s = "";

            while ((s = bufferedreader.readLine()) != null) {
                this.h().add(s.trim().toLowerCase());
            }

            bufferedreader.close();
        } catch (Exception exception) {
            this.s().al().b("Failed to load white-list: " + exception);
        }
    }

    private void w() {
        try {
            PrintWriter printwriter = new PrintWriter(new FileWriter(this.e, false));
            Iterator iterator = this.h().iterator();

            while (iterator.hasNext()) {
                String s = (String) iterator.next();

                printwriter.println(s);
            }

            printwriter.close();
        } catch (Exception exception) {
            this.s().al().b("Failed to save white-list: " + exception);
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
