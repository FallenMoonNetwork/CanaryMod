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
    public boolean b;
    private int f;
    private String g;
    private volatile boolean h;
    private String i = "";
    private boolean j;
    private SecretKey k;

    public ONetLoginHandler(OMinecraftServer ominecraftserver, Socket socket, String s) throws IOException {
        this.e = ominecraftserver;
        this.a = new OTcpConnection(ominecraftserver.an(), socket, s, this, ominecraftserver.H().getPrivate());
        this.a.e = 0;
    }

    public void d() {
        if (this.h) {
            this.e();
        }

        if (this.f++ == 600) {
            this.a("Took too long to log in");
        } else {
            this.a.b();
        }
    }

    public void a(String s) {
        try {
            this.e.an().a("Disconnecting " + this.f() + ": " + s);
            this.a.a((OPacket) (new OPacket255KickDisconnect(s)));
            this.a.d();
            this.b = true;
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void a(OPacket2ClientProtocol opacket2clientprotocol) {
        if (this.g != null) {
            this.a("Quit repeating yourself!");
        } else {
            this.g = opacket2clientprotocol.f();
            if (!this.g.toLowerCase().matches("[a-z0-9_-]{2,16}")) {
                this.a("Invalid username!");
            } else {
                PublicKey publickey = this.e.H().getPublic();

                if (opacket2clientprotocol.d() != 78) {
                    if (opacket2clientprotocol.d() > 78) {
                        this.a("Outdated server!");
                    } else {
                        this.a("Outdated client!");
                    }
                } else {
                    this.i = this.e.W() ? Long.toString(c.nextLong(), 16) : "-";
                    this.d = new byte[4];
                    c.nextBytes(this.d);
                    this.a.a((OPacket) (new OPacket253ServerAuthData(this.i, publickey, this.d)));
                }
            }
        }
    }

    public void a(OPacket252SharedKey opacket252sharedkey) {
        PrivateKey privatekey = this.e.H().getPrivate();

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
            if (this.e.W()) {
                (new OThreadLoginVerifier(this)).start();
            } else {
                this.h = true;
            }
        }
    }

    public void a(OPacket1Login opacket1login) {}

    public void e() {
        String s = this.e.af().a(this.a.c(), this.g);

        if (s != null) {
            this.a(s);
        } else {
            OEntityPlayerMP oentityplayermp = this.e.af().a(this.g);

            if (oentityplayermp != null) {
                this.e.af().a((OINetworkManager) this.a, oentityplayermp);
            }
        }

        this.b = true;
    }

    public void a(String s, Object[] aobject) {
        this.e.an().a(this.f() + " lost connection");
        this.b = true;
    }

    public void a(OPacket254ServerPing opacket254serverping) {
        // CanaryMod start - Fix if we don't have a socket, don't do anything
        if (this.a.c() == null) {
            return;
        } // CanaryMod end

        try {
            OServerConfigurationManager oserverconfigurationmanager = this.e.af();
            String s = null;

            if (opacket254serverping.d()) {
                s = this.e.ac() + "\u00a7" + oserverconfigurationmanager.k() + "\u00a7" + oserverconfigurationmanager.l();
            } else {
                List list = Arrays.asList(new Serializable[] { Integer.valueOf(1), Integer.valueOf(78), this.e.z(), this.e.ac(), Integer.valueOf(oserverconfigurationmanager.k()), Integer.valueOf(oserverconfigurationmanager.l())});

                Object object;

                for (Iterator iterator = list.iterator(); iterator.hasNext(); s = s + object.toString().replaceAll("\u0000", "")) {
                    object = iterator.next();
                    if (s == null) {
                        s = "\u00a7";
                    } else {
                        s = s + "\u0000";
                    }
                }
            }

            InetAddress inetaddress = null;

            if (this.a.g() != null) {
                inetaddress = this.a.g().getInetAddress();
            }

            this.a.a((OPacket) (new OPacket255KickDisconnect(s)));
            this.a.d();
            if (inetaddress != null && this.e.ag() instanceof ODedicatedServerListenThread) {
                ((ODedicatedServerListenThread) this.e.ag()).a(inetaddress);
            }

            this.b = true;
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void a(OPacket opacket) {
        this.a("Protocol error");
    }

    public String f() {
        return this.g != null ? this.g + " [" + this.a.c().toString() + "]" : this.a.c().toString();
    }

    public boolean a() {
        return true;
    }

    public boolean c() {
        return this.b;
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
