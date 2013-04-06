import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Scanner;
import java.util.zip.CheckedInputStream;
import java.util.zip.CRC32;


public class Main {

    public static final long   minecraft_server = 0x68e18cf4L;

    public static final long[] minecraft_servero = {0xb87fd468L, 0x8eafb753L};

    public static final long   mysql = 0xb2e59524L;
    public static final long   jarjar = 0x4e724d4dL;
    public static final long   rules = 0xd809724dL;

    public static final Logger log = Logger.getLogger("Minecraft");

    private static boolean     newIsCrow = false;

    public static void main(String[] args) throws IOException {

        if (!fileExists("minecraft_servero.jar")) {
            if (!fileExists("jarjar.jar") || !fileExists("rules.rules")) {
                log("-----------------------------");
                log("jarjar.jar and/or rules.rules are missing!");
                log("-----------------------------");
                System.exit(0);
            }
            checkCRC32("jarjar.jar", jarjar);
            checkCRC32("rules.rules", rules);

            if (!fileExists("minecraft_server.jar")) {
                log("Missing minecraft_servero.jar, Downloading minecraft_server.jar...");
                downloadFile("https://s3.amazonaws.com/MinecraftDownload/launcher/minecraft_server.jar", "minecraft_server.jar");
                checkCRC32("minecraft_server.jar", minecraft_server);

                log("Finished downloading minecraft_server.jar, start converting minecraft_server.jar to minecraft_servero.jar...");
            } else {
                checkCRC32("minecraft_server.jar", minecraft_server);
                log("Missing minecraft_servero.jar, start converting minecraft_server.jar to minecraft_servero.jar...");
            }

            try {
                com.tonicsystems.jarjar.Main.main(new String[] { "process", "rules.rules", "minecraft_server.jar", "minecraft_servero.jar" });
            } catch (Throwable t) {
                log.log(Level.SEVERE, null, t);
            }
            checkCRC32("minecraft_servero.jar", minecraft_servero);

            log("Finished converting minecraft_server.jar, Starting minecraft server...");
            dynamicLoadJar("minecraft_servero.jar");
            Motd.makeMotd();
        } else {
            checkCRC32("minecraft_servero.jar", minecraft_servero);
        }

        if (etc.getInstance().getDataSourceType().equalsIgnoreCase("mysql")) {
            checkCRC32("mysql-connector-java-bin.jar", mysql);
        }

        if (checkForUpdate()) {
            log("===============");
            log("New " + (newIsCrow ? "Canary" : "Crow") + " version found.");
            log("You can download it at http://www.canarymod.net/download");
            log("Be sure to replace version.txt!");
            log("===============");
        }
        // derp.

        // This mod works with GUI now :D
        try {
            OMinecraftServer.main(args);
        } catch (Throwable t) {
            log.log(Level.SEVERE, null, t);
        }
        new DeadLockDetector();
    }

    public static boolean fileExists(String filename) {
        return new File(filename).exists();
    }

    public static void checkCRC32(String fileName, long crc) throws IOException {
        if (etc.getInstance().getTainted()) {
            return;
        }

        long checksum = getCRC32(fileName);

        if (checksum != crc) {
            log("-----------------------------");
            log(fileName + " does not match checksum! Checksum found: " + Long.toHexString(checksum) + ", required checksum: " + Long.toHexString(crc) + ".");
            log("This means some of your files are either corrupted, outdated or too new. (minecraft got updated?)");
            log("If you still want to run the server, delete version.txt to run the server in tainted mode.");
            log("-----------------------------");
            System.exit(0);
        }
    }

    public static void checkCRC32(String fileName, long[] crcs) throws IOException {
        if (etc.getInstance().getTainted()) {
            return;
        }

        long checksum = 0;
        boolean match = false;
        for (long crc : crcs) {
            checksum = getCRC32(fileName);
            match = checksum == crc;
            if (match) {
                break;
            }
        }

        if (!match) {
            // Generate nice string
            StringBuilder req = new StringBuilder(Long.toHexString(crcs[0]));
            for (int i = 1; i < crcs.length; i++) {
                if (i + 1 == crcs.length) {
                    req.append(" or ");
                } else {
                    req.append(", ");
                }

                req.append(Long.toHexString(crcs[i]));
            }

            log("-----------------------------");
            log(fileName + " does not match checksum! Checksum found: " + Long.toHexString(checksum) + ", required checksum: " + req + ".");
            log("This means some of your files are either corrupted, outdated or too new. (minecraft got updated?)");
            log("If you still want to run the server, delete version.txt to run the server in tainted mode.");
            log("-----------------------------");
            System.exit(0);
        }
    }

    public static long getCRC32(String fileName) throws IOException {

        FileInputStream stream = new FileInputStream(fileName);
        CheckedInputStream cis = new CheckedInputStream(stream, new CRC32());
        byte[] buf = new byte[128];

        while (cis.read(buf) >= 0) {}

        long rt = cis.getChecksum().getValue();

        stream.close();
        cis.close();

        return rt;
    }

    public static void printCRC() throws IOException {
        log("Minecraft_server CRC32: \t" + getCRC32("minecraft_server.jar"));
        log("Minecraft_servero CRC32: \t" + getCRC32("minecraft_servero.jar"));
        log("Jarjar CRC32: \t\t\t" + getCRC32("jarjar.jar"));
        log("Rules CRC32: \t\t\t" + getCRC32("rules.rules"));
        log("Mysql CRC32: \t\t\t" + getCRC32("mysql-connector-java-bin.jar"));
    }

    public static void downloadFile(String website, String fileLocation) throws IOException {
        URL url = new URL(website);
        ReadableByteChannel rbc = Channels.newChannel(url.openStream());
        FileOutputStream fos = new FileOutputStream(fileLocation);

        fos.getChannel().transferFrom(rbc, 0, 1 << 24);
    }

    public static void log(String str) {
        System.out.println(str);
    }

    public static void dynamicLoadJar(String fileName) throws IOException {
        URLClassLoader sysloader = (URLClassLoader) ClassLoader.getSystemClassLoader();
        Class<?> sysclass = URLClassLoader.class;

        try {
            Method method = sysclass.getDeclaredMethod("addURL", new Class[] { URL.class });

            method.setAccessible(true);
            method.invoke(sysloader, new Object[] { (new File(fileName)).toURI().toURL() });
        } catch (Throwable t) {
            t.printStackTrace();
            throw new IOException("Error, could not add URL to system classloader");
        }
    }

    public static boolean checkForUpdate() {
        etc e = etc.getInstance();
        if (e.getTainted()) {
            log("=== Not checking for updates, tainted version detected (check "
                    + "version.txt) ===");
            return false;
        }
        boolean crow = e.isCrow();
        String version = e.getVersionStr();
        try {
            URLConnection canaryURL = new URL(
                    "http://72.9.154.217/dl/version.txt").openConnection();
            URLConnection crowURL = new URL(
                    "http://72.9.154.217/dl/crow/version.txt").openConnection();
            canaryURL.setConnectTimeout(500);
            canaryURL.setReadTimeout(500);
            crowURL.setConnectTimeout(500);
            crowURL.setReadTimeout(500);
            if (canaryURL.getLastModified() > crowURL.getLastModified()) {
                return crow || !version.equals(
                            new Scanner(canaryURL.getInputStream()).nextLine());
            } else {
                newIsCrow = true;
                return !(crow && version.equals(
                            new Scanner(crowURL.getInputStream()).nextLine()));
            }
        } catch (Exception ex) {
            log("=== Checking for updates failed: " + ex.getMessage() + " ===");
            return false;
        }
    }
}
