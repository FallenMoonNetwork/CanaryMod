<<<<<<<
import java.io.IOException;
|||||||
=======
import java.io.Serializable;
>>>>>>>
import java.net.InetAddress;
import java.net.Socket;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;
import javax.crypto.SecretKey;

public class ONetLoginHandler extends ONetHandler {

    private byte[] d;
    public static Logger a = Logger.getLogger("Minecraft");
    private static Random e = new Random();
    public OTcpConnection b;
    public boolean c = false;
    private OMinecraftServer f;
    private int g = 0;
    private String h = null;
    private volatile boolean i = false;
    private String j = "";
    private SecretKey k = null;
    
    private String worldname; // CanaryMod: store worldname given by plugins

    public ONetLoginHandler(OMinecraftServer ominecraftserver, Socket socket, String s) {
        this.f = ominecraftserver;
        this.b = new OTcpConnection(socket, s, this, ominecraftserver.F().getPrivate());
        this.b.e = 0;
    }

    public void c() {
        if (this.i) {
            this.d();
        }

        if (this.g++ == 600) {
            this.a("Took too long to log in");
        } else {
            this.b.b();
        }

    }

    public void a(String s) {
        try {
            a.info("Disconnecting " + this.e() + ": " + s);
            this.b.a((OPacket) (new OPacket255KickDisconnect(s)));
            this.b.d();
            this.c = true;
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }

    public void a(OPacket2ClientProtocol opacket2clientprotocol) {
        this.h = opacket2clientprotocol.f();
        if (!this.h.equals(OStringUtils.a(this.h))) {
            this.a("Invalid username!");
        } else {
            PublicKey publickey = this.f.F().getPublic();

            if (opacket2clientprotocol.d() != 47) {
                if (opacket2clientprotocol.d() > 47) {
                    this.a("Outdated server!");
                } else {
                    this.a("Outdated client!");
                }
            } else {
                this.j = this.f.U() ? Long.toString(e.nextLong(), 16) : "-";
                this.d = new byte[4];
                e.nextBytes(this.d);
                this.b.a((OPacket) (new OPacket253ServerAuthData(this.j, publickey, this.d)));
            }
        }
    }

    public void a(OPacket252SharedKey opacket252sharedkey) {
        PrivateKey privatekey = this.f.F().getPrivate();

        this.k = opacket252sharedkey.a(privatekey);
        if (!Arrays.equals(this.d, opacket252sharedkey.b(privatekey))) {
            this.a("Invalid client reply");
        }

        this.b.a((OPacket) (new OPacket252SharedKey()));
    }

    public void a(OPacket205ClientCommand opacket205clientcommand) {
        if (opacket205clientcommand.a == 0) {
            if (this.f.U()) {
                (new OThreadLoginVerifier(this)).start();
            } else {
                this.i = true;
            }

        }
    }

    public void a(OPacket1Login opacket1login) {}

    public void d() {
        String s = this.f.ad().a(this.b.c(), this.h);

        if (s != null) {
            this.a(s);
        } else {
            OEntityPlayerMP oentityplayermp = this.f.ad().a(this.h);

            if (oentityplayermp != null) {
                this.f.ad().a((OINetworkManager) this.b, oentityplayermp);
            }
        }

        this.c = true;
    }

    public void a(String s, Object[] aobject) {
        a.info(this.e() + " lost connection");
        this.c = true;
    }

    public void a(OPacket254ServerPing opacket254serverping) {
        if (this.b.g() == null) {
            return;
        } // CanaryMod - Fix if we don't have a socket, don't do anything
        try {
            OServerConfigurationManager oserverconfigurationmanager = this.f.ad();
            String s = null;

            if (opacket254serverping.a == 1) {
                List list = Arrays.asList(new Serializable[] { Integer.valueOf(1), Integer.valueOf(47), this.f.x(), this.f.aa(), Integer.valueOf(oserverconfigurationmanager.k()), Integer.valueOf(oserverconfigurationmanager.l())});

                Object object;

                for (Iterator iterator = list.iterator(); iterator.hasNext(); s = s + object.toString().replaceAll(", "")) {
                    object = iterator.next();
                    if (s == null) {
                        s = "\u00A7";
                    } else {
                        s = s + ";
                    }
                }
            } else {
                s = this.f.aa() + "\u00A7" + oserverconfigurationmanager.k() + "\u00A7" + oserverconfigurationmanager.l();
            }

            InetAddress inetaddress = null;

            if (this.b.g() != null) {
                inetaddress = this.b.g().getInetAddress();
            }
            this.b.a((OPacket) (new OPacket255KickDisconnect(s)));
<<<<<<<
            // CanaryMod: swapped lines below. The network connection should be terminated AFTER removing the socket from the connection list.
            if (inetaddress != null && this.f.ac() instanceof ODedicatedServerListenThread) {
                ((ODedicatedServerListenThread) this.f.ac()).a(inetaddress);
|||||||
            this.b.d();
            if (inetaddress != null && this.f.ac() instanceof ODedicatedServerListenThread) {
                ((ODedicatedServerListenThread) this.f.ac()).a(inetaddress);
=======
            this.b.d();
            if (inetaddress != null && this.f.ae() instanceof ODedicatedServerListenThread) {
                ((ODedicatedServerListenThread) this.f.ae()).a(inetaddress);
>>>>>>>
            }
            this.b.d();
            
            this.c = true;
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }

    public void a(OPacket opacket) {
        this.a("Protocol error");
    }

    public String e() {
        return this.h != null ? this.h + " [" + this.b.c().toString() + "]" : this.b.c().toString();
    }

    public boolean a() {
        return true;
    }

    static String a(ONetLoginHandler onetloginhandler) {
        return onetloginhandler.j;
    }

    static OMinecraftServer b(ONetLoginHandler onetloginhandler) {
        return onetloginhandler.f;
    }

    static SecretKey c(ONetLoginHandler onetloginhandler) {
        return onetloginhandler.k;
    }

    static String d(ONetLoginHandler onetloginhandler) {
        return onetloginhandler.h;
    }

    static boolean a(ONetLoginHandler onetloginhandler, boolean flag) {
        return onetloginhandler.i = flag;
    }

}
