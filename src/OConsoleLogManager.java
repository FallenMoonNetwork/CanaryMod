
import java.io.File;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;


public class OConsoleLogManager {

    public static Logger a = Logger.getLogger("Minecraft");

    public static void a() {
        OConsoleLogFormatter oconsolelogformatter = new OConsoleLogFormatter();

        a.setUseParentHandlers(false);
        ConsoleHandler consolehandler = new ConsoleHandler();

        consolehandler.setFormatter(oconsolelogformatter);
        a.addHandler(consolehandler);

        try {
            FileHandler filehandler = new FileHandler("server.log", true);

            filehandler.setFormatter(oconsolelogformatter);
            a.addHandler(filehandler);
        } catch (Exception exception) {
            a.log(Level.WARNING, "Failed to log to server.log", exception);
        }

        // CanaryMod: Keep the serveroutput logs.
        File log = new File("logs");

        try {
            if (!log.exists()) {
                log.mkdir();
            }
            FileHandler localFileHandler = new FileHandler("logs/server_" + ((int) (System.currentTimeMillis() / 1000L)) + ".log");

            localFileHandler.setFormatter(oconsolelogformatter);
            a.addHandler(localFileHandler);
        } catch (Exception exception1) {
            a.log(Level.WARNING, "Failed to log to server log", exception1);
        }

    }

}
