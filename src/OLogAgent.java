import java.io.File;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OLogAgent implements OILogAgent {

    private final Logger a;
    private final String b;
    private final String c;
    private final String d;

    public OLogAgent(String s, String s1, String s2) {
        this.a = Logger.getLogger(s);
        this.c = s;
        this.d = s1;
        this.b = s2;
        this.b();
    }

    private void b() {
        this.a.setUseParentHandlers(false);
        Handler[] ahandler = this.a.getHandlers();
        int i = ahandler.length;

        for (int j = 0; j < i; ++j) {
            Handler handler = ahandler[j];

            this.a.removeHandler(handler);
        }

        OLogFormatter ologformatter = new OLogFormatter(this, (OLogAgentINNER1) null);
        ConsoleHandler consolehandler = new ConsoleHandler();

        consolehandler.setFormatter(ologformatter);
        this.a.addHandler(consolehandler);

        try {
            FileHandler filehandler = new FileHandler(this.b, true);

            filehandler.setFormatter(ologformatter);
            this.a.addHandler(filehandler);
        } catch (Exception exception) {
            this.a.log(Level.WARNING, "Failed to log " + this.c + " to " + this.b, exception);
        }

        // CanaryMod start - keep logs in separate folder as well

        File log = new File("logs");
        String filePath = "<uninitialized>";
        try {
            if (!log.exists()) {
                log.mkdir();
            }

            String temp = new File(this.b).getName();
            int dotIndex = temp.lastIndexOf('.');
            String fileName = temp.substring(0, dotIndex);
            String fileExt  = temp.substring(dotIndex);
            filePath = new File(log, fileName + "_"
                            + ((int) (System.currentTimeMillis() / 1000L))
                            + fileExt).getAbsolutePath();
            FileHandler filehandler = new FileHandler(filePath, true);

            filehandler.setFormatter(ologformatter);
            this.a.addHandler(filehandler);
        } catch (Exception exception) {
            this.a.log(Level.WARNING, "Failed to log " + this.c + " to " + filePath, exception);
        } // CanaryMod end
    }

    public Logger a() {
        return this.a;
    }

    public void a(String s) {
        this.a.log(Level.INFO, s);
    }

    public void b(String s) {
        this.a.log(Level.WARNING, s);
    }

    public void b(String s, Object... aobject) {
        this.a.log(Level.WARNING, s, aobject);
    }

    public void b(String s, Throwable throwable) {
        this.a.log(Level.WARNING, s, throwable);
    }

    public void c(String s) {
        this.a.log(Level.SEVERE, s);
    }

    public void c(String s, Throwable throwable) {
        this.a.log(Level.SEVERE, s, throwable);
    }

    static String a(OLogAgent ologagent) {
        return ologagent.d;
    }
}
