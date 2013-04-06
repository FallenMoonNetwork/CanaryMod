import java.io.IOException;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.Socket;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.crypto.SecretKey;

public class ONetLoginHandler extends ONetHandler {

    private static Random c = new Random();
    private byte[] d;
    private final OMinecraftServer e;
    public final OTcpConnection a;
    public boolean b = false;
    private int f = 0;
    private String g = null;
    private volatile boolean h = false;
    private String i = "";
    private boolean j = false;
    private SecretKey k = null;

    public ONetLoginHandler(OMinecraftServer ominecraftserver, Socket socket, String s) throws IOException {
        this.e = ominecraftserver;
        this.a = new OTcpConnection(ominecraftserver.al(), socket, s, this, ominecraftserver.F().getPrivate());
        this.a.e = 0;
    }

    public void c() {
        if (this.h) {
            this.d();
        }

        if (this.f++ == 600) {
            this.a("Took too long to log in");
        } else {
            this.a.b();
        }
    }

    public void a(String s) {
        try {
            this.e.al().a("Disconnecting " + this.e() + ": " + s);
            this.a.a((OPacket) (new OPacket255KickDisconnect(s)));
            this.a.d();
            this.b = true;
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void a(OPacket2ClientProtocol opacket2clientprotocol) {
        this.g = opacket2clientprotocol.f();
        if (!this.g.toLowerCase().matches("[a-z0-9_]+")) {
            this.a("Invalid username!");
        } else {
            PublicKey publickey = this.e.F().getPublic();

            if (opacket2clientprotocol.d() != 60) {
                if (opacket2clientprotocol.d() > 60) {
                    this.a("Outdated server!");
                } else {
                    this.a("Outdated client!");
                }
            } else {
                this.i = this.e.U() ? Long.toString(c.nextLong(), 16) : "-";
                this.d = new byte[4];
                c.nextBytes(this.d);
                this.a.a((OPacket) (new OPacket253ServerAuthData(this.i, publickey, this.d)));
            }
        }
    }

    public void a(OPacket252SharedKey opacket252sharedkey) {
        PrivateKey privatekey = this.e.F().getPrivate();

        this.k = opacket252sharedkey.a(privatekey);
        if (!Arrays.equals(this.d, opacket252sharedkey.b(privatekey))) {
            this.a("Invalid client reply");
        }

        this.a.a((OPacket) (new OPacket252SharedKey()));
    }

    public void a(OPacket205ClientCommand opacket205clientcommand) {
        if (opacket205clientcommand.a == 0) {
            if (this.j) {
                this.a("Duplicate login");
                return;
            }

            this.j = true;
            if (this.e.U()) {
                (new OThreadLoginVerifier(this)).start();
            } else {
                this.h = true;
            }
        }
    }

    public void a(OPacket1Login opacket1login) {}

    public void d() {
        String s = this.e.ad().a(this.a.c(), this.g);

        if (s != null) {
            this.a(s);
        } else {
            OEntityPlayerMP oentityplayermp = this.e.ad().a(this.g);

            if (oentityplayermp != null) {
                this.e.ad().a((OINetworkManager) this.a, oentityplayermp);
            }
        }

        this.b = true;
    }

    public void a(String s, Object[] aobject) {
        this.e.al().a(this.e() + " lost connection");
        this.b = true;
    }

    public void a(OPacket254ServerPing opacket254serverping) {
        // CanaryMod start - Fix if we don't have a socket, don't do anything
        if (this.a.c() == null) {
            return;
        } // CanaryMod end

        try {
            OServerConfigurationManager oserverconfigurationmanager = this.e.ad();
            String s = null;

            if (opacket254serverping.a == 1) {
                List list = Arrays.asList(new Serializable[] { Integer.valueOf(1), Integer.valueOf(60), this.e.x(), this.e.aa(), Integer.valueOf(oserverconfigurationmanager.k()), Integer.valueOf(oserverconfigurationmanager.l())});

                Object object;

                for (Iterator iterator = list.iterator(); iterator.hasNext(); s = s + object.toString().replaceAll("\u0000", "")) {
                    object = iterator.next();
                    if (s == null) {
                        s = "\u00a7";
                    } else {
                        s = s + "\u0000";
                    }
                }
            } else {
                s = this.e.aa() + "\u00a7" + oserverconfigurationmanager.k() + "\u00a7" + oserverconfigurationmanager.l();
            }

            InetAddress inetaddress = null;

            if (this.a.g() != null) {
                inetaddress = this.a.g().getInetAddress();
            }

            this.a.a((OPacket) (new OPacket255KickDisconnect(s)));
            this.a.d();
            if (inetaddress != null && this.e.ae() instanceof ODedicatedServerListenThread) {
                ((ODedicatedServerListenThread) this.e.ae()).a(inetaddress);
            }

            this.b = true;
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void a(OPacket opacket) {
        this.a("Protocol error");
    }

    public String e() {
        return this.g != null ? this.g + " [" + this.a.c().toString() + "]" : this.a.c().toString();
    }

    public boolean a() {
        return true;
    }

    static String a(ONetLoginHandler onetloginhandler) {
        return onetloginhandler.i;
    }

    static OMinecraftServer b(ONetLoginHandler onetloginhandler) {
        return onetloginhandler.e;
    }

    static SecretKey c(ONetLoginHandler onetloginhandler) {
        return onetloginhandler.k;
    }

    static String d(ONetLoginHandler onetloginhandler) {
        return onetloginhandler.g;
    }

    static boolean a(ONetLoginHandler onetloginhandler, boolean flag) {
        return onetloginhandler.h = flag;
    }
}
