import java.io.File;
import java.net.SocketAddress;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public abstract class OServerConfigurationManager {

    private static final SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd \'at\' HH:mm:ss z");
    private final OMinecraftServer e;
    public final List a = new ArrayList();
    private final OBanList f = new OBanList(new File("banned-players.txt"));
    private final OBanList g = new OBanList(new File("banned-ips.txt"));
    private Set h = new HashSet();
    private Set i = new HashSet();
    // private OIPlayerFileData j; // CanaryMod: multiworld
    private boolean k;
    protected int b;
    protected int c;
    private OEnumGameType l;
    private boolean m;
    private int n = 0;

    private Map<String, OIPlayerFileData> saveHandlers = new HashMap<String, OIPlayerFileData>(1);
    private Map<String, String> playerWorld = new HashMap<String, String>(1);

    public OServerConfigurationManager(OMinecraftServer ominecraftserver) {
        // CanaryMod: initialize
        etc.setServer(ominecraftserver);
        etc.getInstance().loadData();
        ominecraftserver.al().a("Note: your current classpath is: " + System.getProperty("java.class.path", "*UNKNOWN*"));
        if (!etc.getInstance().getTainted()) {
            ominecraftserver.al().a((etc.getInstance().isCrow() ? "CanaryMod Crow" : "CanaryMod") + " Build " + etc.getInstance().getVersionStr());
        } else {
            ominecraftserver.al().a("Tainted Build Information: " + etc.getInstance().getVersionStr());
        }

        this.e = ominecraftserver;
        this.f.a(false);
        this.g.a(false);
        this.b = 8;
    }

    public void a(OINetworkManager oinetworkmanager, OEntityPlayerMP oentityplayermp) {
        ONBTTagCompound onbttagcompound = this.a(oentityplayermp);
        // CanaryMod: custom world. We don't need the playerWorld entry after this.
        oentityplayermp.a((OWorld) this.e.getWorld(this.playerWorld.remove(oentityplayermp.bS), oentityplayermp.ar));
        oentityplayermp.c.a((OWorldServer) oentityplayermp.q);
        String s = "local";

        if (oinetworkmanager.c() != null) {
            s = oinetworkmanager.c().toString();
        }

        this.e.al().a(oentityplayermp.bS + "[" + s + "] logged in with entity id " + oentityplayermp.k + " at (" + oentityplayermp.u + ", " + oentityplayermp.v + ", " + oentityplayermp.w + ")");
        OWorldServer oworldserver = oentityplayermp.o(); // CanaryMod: get from entity itself.
        OChunkCoordinates ochunkcoordinates = oworldserver.I();

        this.a(oentityplayermp, (OEntityPlayerMP) null, oworldserver);
        ONetServerHandler onetserverhandler = new ONetServerHandler(this.e, oinetworkmanager, oentityplayermp);

        onetserverhandler.b(new OPacket1Login(oentityplayermp.k, oworldserver.L().u(), oentityplayermp.c.b(), oworldserver.L().t(), oworldserver.t.h, oworldserver.r, oworldserver.P(), this.l()));
        onetserverhandler.b(new OPacket6SpawnPosition(ochunkcoordinates.a, ochunkcoordinates.b, ochunkcoordinates.c));
        onetserverhandler.b(new OPacket202PlayerAbilities(oentityplayermp.ce));
        onetserverhandler.b(new OPacket16BlockItemSwitch(oentityplayermp.bK.c));
        this.a((OServerScoreboard) oworldserver.V(), oentityplayermp);
        this.b(oentityplayermp, oworldserver);

        // CanaryMod - onPlayerConnect Hook
        HookParametersConnect hookResult = new HookParametersConnect(String.format(Colors.Yellow + "%s joined the game.", oentityplayermp.bS), true);

        hookResult = (HookParametersConnect) etc.getLoader().callHook(PluginLoader.Hook.PLAYER_CONNECT, oentityplayermp.getPlayer(), hookResult);
        if (!hookResult.isHidden()) {
            this.a((OPacket) (new OPacket3Chat(hookResult.getJoinMessage())));
        }

        // CanaryMod - Check if player is listed as muted, and mute him
        if (etc.getDataSource().isPlayerOnMuteList(oentityplayermp.getPlayer().getName())) {
            oentityplayermp.getPlayer().toggleMute();
        }
        // CanaryMod END

        this.c(oentityplayermp);
        onetserverhandler.a(oentityplayermp.u, oentityplayermp.v, oentityplayermp.w, oentityplayermp.A, oentityplayermp.B);
        this.e.ae().a(onetserverhandler);
        onetserverhandler.b(new OPacket4UpdateTime(oworldserver.G(), oworldserver.H()));
        if (this.e.Q().length() > 0) {
            oentityplayermp.a(this.e.Q(), this.e.S());
        }

        // CanaryMod: apply potion effects?
        if (hookResult.applyPotionsEffects()) {
            Iterator iterator = oentityplayermp.bC().iterator();

            while (iterator.hasNext()) {
                OPotionEffect opotioneffect = (OPotionEffect) iterator.next();

                onetserverhandler.b(new OPacket41EntityEffect(oentityplayermp.k, opotioneffect));
            }
        }

        // CanaryMod: Handle login (send MOTD, send packet and set mode, and call hook)
        etc.getInstance().getMotd(oentityplayermp.getPlayer());
        etc.getLoader().callHook(PluginLoader.Hook.LOGIN, oentityplayermp.getPlayer());

        oentityplayermp.d_();
        if (onbttagcompound != null && onbttagcompound.b("Riding")) {
            OEntity oentity = OEntityList.a(onbttagcompound.l("Riding"), oworldserver);

            if (oentity != null) {
                oentity.p = true;
                oworldserver.d(oentity);
                oentityplayermp.a(oentity);
                oentity.p = false;
            }
        }
    }

    protected void a(OServerScoreboard oserverscoreboard, OEntityPlayerMP oentityplayermp) {
        HashSet hashset = new HashSet();
        Iterator iterator = oserverscoreboard.g().iterator();

        while (iterator.hasNext()) {
            OScorePlayerTeam oscoreplayerteam = (OScorePlayerTeam) iterator.next();

            oentityplayermp.a.b(new OPacket209SetPlayerTeam(oscoreplayerteam, 0));
        }

        for (int i = 0; i < 3; ++i) {
            OScoreObjective oscoreobjective = oserverscoreboard.a(i);

            if (oscoreobjective != null && !hashset.contains(oscoreobjective)) {
                List list = oserverscoreboard.d(oscoreobjective);
                Iterator iterator1 = list.iterator();

                while (iterator1.hasNext()) {
                    OPacket opacket = (OPacket) iterator1.next();

                    oentityplayermp.a.b(opacket);
                }

                hashset.add(oscoreobjective);
            }
        }
    }

    public void a(OWorldServer[] aoworldserver) {
        this.saveHandlers.put(aoworldserver[0].name, aoworldserver[0].K().e());
    }

    public void a(OEntityPlayerMP oentityplayermp, OWorldServer oworldserver) {
        OWorldServer oworldserver1 = oentityplayermp.o();

        if (oworldserver != null) {
            oworldserver.r().c(oentityplayermp);
        }

        oworldserver1.r().a(oentityplayermp);
        oworldserver1.b.c((int) oentityplayermp.u >> 4, (int) oentityplayermp.w >> 4);
    }

    public int a() {
        return OPlayerManager.a(this.o());
    }

    public ONBTTagCompound a(OEntityPlayerMP oentityplayermp) {
        ONBTTagCompound onbttagcompound = etc.getServer().getDefaultWorld().getWorld().L().i();
        ONBTTagCompound onbttagcompound1;

        if (oentityplayermp.c_().equals(this.e.H()) && onbttagcompound != null) {
            oentityplayermp.f(onbttagcompound);
            onbttagcompound1 = onbttagcompound;
            System.out.println("loading single player");
        } else {
            onbttagcompound1 = this.saveHandlers.get(oentityplayermp.getPlayer().getWorld().getName()).b(oentityplayermp);
        }

        return onbttagcompound1;
    }

    protected void b(OEntityPlayerMP oentityplayermp) {
        this.saveHandlers.get(oentityplayermp.getPlayer().getWorld().getName()).a(oentityplayermp);
    }

    public void c(OEntityPlayerMP oentityplayermp) {
        // CanaryMod: Playername with color and Prefix
        PlayerlistEntry entry = oentityplayermp.getPlayer().getPlayerlistEntry(true);

        this.a((OPacket) (new OPacket201PlayerInfo(entry.getName(), entry.isShow(), 1000)));
        this.a.add(oentityplayermp);
        OWorldServer oworldserver = this.e.getWorld(oentityplayermp.q.name, oentityplayermp.ar);

        oworldserver.d(oentityplayermp);
        this.a(oentityplayermp, (OWorldServer) null);

        for (int i = 0; i < this.a.size(); ++i) {
            OEntityPlayerMP oentityplayermp1 = (OEntityPlayerMP) this.a.get(i);

            entry = oentityplayermp1.getPlayer().getPlayerlistEntry(true);
            oentityplayermp.a.b(new OPacket201PlayerInfo(entry.getName(), entry.isShow(), entry.getPing()));
        }
    }

    public void d(OEntityPlayerMP oentityplayermp) {
        oentityplayermp.o().r().d(oentityplayermp);
    }

    public void e(OEntityPlayerMP oentityplayermp) {
        this.b(oentityplayermp);
        OWorldServer oworldserver = oentityplayermp.o();

        if (oentityplayermp.o != null) {
            oworldserver.e(oentityplayermp.o);
            System.out.println("removing player mount");
        }

        oworldserver.e(oentityplayermp);
        oworldserver.r().c(oentityplayermp);
        this.a.remove(oentityplayermp);

        // CanaryMod: Player color and Prefix
        if (etc.getInstance().isPlayerList_enabled()) {
            PlayerlistEntry entry = oentityplayermp.getPlayer().getPlayerlistEntry(false);

            this.a(new OPacket201PlayerInfo(entry.getName(), entry.isShow(), entry.getPing()));
        }
    }

    public String a(SocketAddress socketaddress, String s) {
        if (!etc.getLoader().isLoaded()) {
            return "The server is not finished loading yet!";
        }

        // Move up IP
        String s2 = socketaddress.toString();

        s2 = s2.substring(s2.indexOf("/") + 1);
        s2 = s2.substring(0, s2.indexOf(":"));


        HookParametersLogincheck hook = (HookParametersLogincheck) etc.getLoader().callHook(PluginLoader.Hook.LOGINCHECK, new HookParametersLogincheck(etc.getServer().getDefaultWorld().getName(), s, s2));

        if (hook.getKickReason() != null) {
            return hook.getKickReason();
        }

        // CanaryMod: Store for later usage.
        this.playerWorld.put(hook.getPlayerName(), hook.getWorldName());

        Player player = etc.getDataSource().getPlayer(s);

        if (etc.getDataSource().isOnBanList(s, s2)) {
            Ban ban = etc.getDataSource().getBan(s, s2);

            String msg = etc.getInstance().getDefaultBanMessage() + "\nReason: " + ban.getReason();
            if (ban.getTimestamp() != -1) {
                msg += "\nYour ban will be removed on " + d.format(new Date(ban.getTimestamp() * 1000));
            }

            return msg;
        } else if (this.f.a(s)) {
            OBanEntry obanentry = (OBanEntry) this.f.c().get(s);
            String s1 = etc.getInstance().getDefaultBanMessage() + "\nReason: " + obanentry.f();

            if (obanentry.d() != null) {
                s1 = s1 + "\nYour ban will be removed on " + d.format(obanentry.d());
            }

            return s1;
        } else if (etc.getInstance().isWhitelistEnabled() && !(etc.getDataSource().isUserOnWhitelist(s) || player.isAdmin())) {
            return etc.getInstance().getWhitelistMessage();
        } else {
            if (this.g.a(s2)) {
                OBanEntry obanentry1 = (OBanEntry) this.g.c().get(s2);
                String s3 = "Your IP address is banned from this server!\nReason: " + obanentry1.f();

                if (obanentry1.d() != null) {
                    s3 = s3 + "\nYour ban will be removed on " + d.format(obanentry1.d());
                }

                return s3;
            } else if (this.a.size() >= this.b && !etc.getDataSource().isUserOnReserveList(s)) {
                return "The server is full!";
            } else {
                if (player.getIps() != null && !player.getIps()[0].equals("")) {
                    boolean ipallowed = false;

                    if (player.getIps() != null) {
                        for (String allowedip : player.getIps()) {
                            if (s2.equals(allowedip)) {
                                ipallowed = true;
                                break;
                            }
                        }
                    } else {
                        ipallowed = true;
                    }
                    if (!ipallowed) {
                        return "You are not allowed to log in from your current location";
                    }
                }
            }
        }
        return null;
    }

    public OEntityPlayerMP a(String s) {
        ArrayList arraylist = new ArrayList();

        OEntityPlayerMP oentityplayermp;

        for (int i = 0; i < this.a.size(); ++i) {
            oentityplayermp = (OEntityPlayerMP) this.a.get(i);
            if (oentityplayermp.bS.equalsIgnoreCase(s)) {
                arraylist.add(oentityplayermp);
            }
        }

        Iterator iterator = arraylist.iterator();

        while (iterator.hasNext()) {
            oentityplayermp = (OEntityPlayerMP) iterator.next();
            oentityplayermp.a.c("You logged in from another location");
        }

        // CanaryMod: make sure the world is loaded into memory.
        OWorldServer world = etc.getServer().loadWorld(this.playerWorld.get(s))[0].getWorld();

        Object object;

        if (this.e.M()) {
            object = new ODemoWorldManager(world);
        } else {
            object = new OItemInWorldManager(world);
        }

        return new OEntityPlayerMP(this.e, world, s, (OItemInWorldManager) object);
    }

    public OEntityPlayerMP a(OEntityPlayerMP oentityplayermp, int i, boolean flag) {
        return this.a(oentityplayermp, i, flag, null);
    }

    // CanaryMod: alias to set location when spawning
    public OEntityPlayerMP a(OEntityPlayerMP oentityplayermp, int i, boolean flag, Location location) {
        oentityplayermp.o().p().a(oentityplayermp);
        oentityplayermp.o().p().b(oentityplayermp);
        oentityplayermp.o().r().c(oentityplayermp);
        this.a.remove(oentityplayermp);
        this.e.getWorld(oentityplayermp.getPlayer().getWorld().getName(), oentityplayermp.ar).f(oentityplayermp);
        OChunkCoordinates ochunkcoordinates = oentityplayermp.ci();
        boolean flag1 = oentityplayermp.cj();

        oentityplayermp.ar = i;
        OItemInWorldManager oiteminworldmanager;
        OWorldServer oworldserver = location != null ? location.getWorld().getWorld() : this.e.getWorld(oentityplayermp.q.name, oentityplayermp.ar);

        if (this.e.M()) {
            oiteminworldmanager = new ODemoWorldManager(oworldserver);
        } else {
            oiteminworldmanager = new OItemInWorldManager(oworldserver);
        }

        OEntityPlayerMP oentityplayermp1 = new OEntityPlayerMP(this.e, oworldserver, oentityplayermp.bS, oiteminworldmanager);
        oentityplayermp.getPlayer().moveTo(oentityplayermp1.getPlayer());

        oentityplayermp1.a = oentityplayermp.a;
        oentityplayermp1.a(oentityplayermp, flag);
        oentityplayermp1.k = oentityplayermp.k;

        this.a(oentityplayermp1, oentityplayermp, oworldserver);
        OChunkCoordinates ochunkcoordinates1;

        if (ochunkcoordinates != null) {
            ochunkcoordinates1 = OEntityPlayer.a(oworldserver, ochunkcoordinates, flag1);
            if (ochunkcoordinates1 != null) {
                oentityplayermp1.b((double) ((float) ochunkcoordinates1.a + 0.5F), (double) ((float) ochunkcoordinates1.b + 0.1F), (double) ((float) ochunkcoordinates1.c + 0.5F), 0.0F, 0.0F);
                oentityplayermp1.a(ochunkcoordinates, flag1);
            } else {
                oentityplayermp1.a.b(new OPacket70GameEvent(0, 0));
            }
        }

        // CanaryMod set player location and angle if a spawn location is defined
        if (location != null) {
            oentityplayermp1.a.c = oentityplayermp1; // Set ONetServerHandler.user
            oentityplayermp1.b(location.x, location.y, location.z, location.rotX, location.rotY);
        }

        oworldserver.b.c((int) oentityplayermp1.u >> 4, (int) oentityplayermp1.w >> 4);

        while (!oworldserver.a(oentityplayermp1, oentityplayermp1.E).isEmpty()) {
            oentityplayermp1.b(oentityplayermp1.u, oentityplayermp1.v + 1.0D, oentityplayermp1.w);
        }

        // Force chunk cache reload on the client
        oentityplayermp1.a.b(new OPacket9Respawn(oentityplayermp1.ar >= 0 ? -1 : 0, (byte) oentityplayermp1.q.r, oentityplayermp1.q.L().u(), oentityplayermp1.q.P(), oentityplayermp1.c.b()));
        oentityplayermp1.a.b(new OPacket9Respawn(oentityplayermp1.ar, (byte) oentityplayermp1.q.r, oentityplayermp1.q.L().u(), oentityplayermp1.q.P(), oentityplayermp1.c.b()));
        //oentityplayermp1.a(oworldserver);
        //oentityplayermp1.M = false;

        ochunkcoordinates1 = oworldserver.I();
        oentityplayermp1.a.a(oentityplayermp1.u, oentityplayermp1.v, oentityplayermp1.w, oentityplayermp1.A, oentityplayermp1.B);
        oentityplayermp1.a.b(new OPacket6SpawnPosition(ochunkcoordinates1.a, ochunkcoordinates1.b, ochunkcoordinates1.c));
        oentityplayermp1.a.b(new OPacket43Experience(oentityplayermp1.ch, oentityplayermp1.cg, oentityplayermp1.cf));
        this.b(oentityplayermp1, oworldserver);
        oworldserver.r().a(oentityplayermp1);
        oworldserver.d(oentityplayermp1);
        this.a.add(oentityplayermp1);
        oentityplayermp1.d_();
        oentityplayermp1.b(oentityplayermp1.aX());
        etc.getLoader().callHook(PluginLoader.Hook.PLAYER_RESPAWN, oentityplayermp1.getPlayer(), location);
        return oentityplayermp1;
    }

    // CanaryMod: add flag to allow overriding portal creation
    public void a(OEntityPlayerMP oentityplayermp, int i) {
        this.sendPlayerToOtherDimension(oentityplayermp, i, true);
    }

    public void sendPlayerToOtherDimension(OEntityPlayerMP oentityplayermp, int i, boolean flag) {
        int j = oentityplayermp.ar;
        OWorldServer oworldserver = this.e.getWorld(oentityplayermp.q.name, oentityplayermp.ar);

        oentityplayermp.ar = i;
        OWorldServer oworldserver1 = this.e.getWorld(oentityplayermp.q.name, oentityplayermp.ar);

        oentityplayermp.a.b(new OPacket9Respawn(oentityplayermp.ar, (byte) oentityplayermp.q.r, oworldserver1.L().u(), oworldserver1.P(), oentityplayermp.c.b()));
        oworldserver.f(oentityplayermp);
        oentityplayermp.M = false;
        this.a(oentityplayermp, j, oworldserver, oworldserver1, flag);
        this.a(oentityplayermp, oworldserver);
        oentityplayermp.a.a(oentityplayermp.u, oentityplayermp.v, oentityplayermp.w, oentityplayermp.A, oentityplayermp.B);
        oentityplayermp.c.a(oworldserver1);
        this.b(oentityplayermp, oworldserver1);
        this.f(oentityplayermp);
        Iterator iterator = oentityplayermp.bC().iterator();

        while (iterator.hasNext()) {
            OPotionEffect opotioneffect = (OPotionEffect) iterator.next();

            oentityplayermp.a.b(new OPacket41EntityEffect(oentityplayermp.k, opotioneffect));
        }
    }

    public void a(OEntity oentity, int i, OWorldServer oworldserver, OWorldServer oworldserver1) {
        this.a(oentity, i, oworldserver, oworldserver1, true);
    }

    public void a(OEntity oentity, int i, OWorldServer oworldserver, OWorldServer oworldserver1, boolean flag) {
        double d0 = oentity.u;
        double d1 = oentity.w;
        double d2 = 8.0D;
        double d3 = oentity.u;
        double d4 = oentity.v;
        double d5 = oentity.w;
        float f = oentity.A;

        oworldserver.C.a("moving");
        if (oentity.ar == -1) {
            d0 /= d2;
            d1 /= d2;
            oentity.b(d0, oentity.v, d1, oentity.A, oentity.B);
            if (oentity.R()) {
                oworldserver.a(oentity, false);
            }
        } else if (oentity.ar == 0) {
            d0 *= d2;
            d1 *= d2;
            oentity.b(d0, oentity.v, d1, oentity.A, oentity.B);
            if (oentity.R()) {
                oworldserver.a(oentity, false);
            }
        } else {
            OChunkCoordinates ochunkcoordinates;

            if (i == 1) {
                ochunkcoordinates = oworldserver1.I();
            } else {
                ochunkcoordinates = oworldserver1.l();
            }

            d0 = (double) ochunkcoordinates.a;
            oentity.v = (double) ochunkcoordinates.b;
            d1 = (double) ochunkcoordinates.c;
            oentity.b(d0, oentity.v, d1, 90.0F, 0.0F);
            if (oentity.R()) {
                oworldserver.a(oentity, false);
            }
        }

        oworldserver.C.b();
        if (i != 1) {
            oworldserver.C.a("placing");
            d0 = (double) OMathHelper.a((int) d0, -29999872, 29999872);
            d1 = (double) OMathHelper.a((int) d1, -29999872, 29999872);
            if (oentity.R()) {
                oworldserver1.d(oentity);
                oentity.b(d0, oentity.v, d1, oentity.A, oentity.B);
                oworldserver1.a(oentity, false);
                if (flag) {
                    oworldserver1.s().a(oentity, d3, d4, d5, f);
                } //
            }

            oworldserver.C.b();
        }

        oentity.a((OWorld) oworldserver1);
    }

    public void b() {
        if (++this.n > etc.getInstance().getPlayerList_ticks()) {
            this.n = 0;
        }

        if (etc.getInstance().isPlayerList_autoupdate() && this.n < this.a.size()) {
            OEntityPlayerMP oentityplayermp = (OEntityPlayerMP) this.a.get(this.n);
            PlayerlistEntry ple = oentityplayermp.getPlayer().getPlayerlistEntry(true);

            this.a((OPacket) (new OPacket201PlayerInfo(ple.getName(), ple.isShow(), ple.getPing())));
        }
    }

    public void a(OPacket opacket) {
        for (int i = 0; i < this.a.size(); ++i) {
            ((OEntityPlayerMP) this.a.get(i)).a.b(opacket);
        }
    }

    public void sendPacketToDimension(OPacket opacket, String world, int i) {
        for (int j = 0; j < this.a.size(); ++j) {
            OEntityPlayerMP oentityplayermp = (OEntityPlayerMP) this.a.get(j);

            if (world.equals(oentityplayermp.q.name) && oentityplayermp.ar == i) {
                // TODO check: CanaryMod re-route time updates to world-specific entity trackers
                oentityplayermp.a.b(opacket);
            }
        }
    }

    @Deprecated
    public void a(OPacket opacket, int i) {
        throw new UnsupportedOperationException("OServerConfigurationManager"
                + ".a(OPacket, int) has been replaced by OServerConfiguration"
                + "Manager.sendPacketToDimension(OPacket, String, int).");
    }

    public String c() {
        String s = "";

        for (int i = 0; i < this.a.size(); ++i) {
            if (i > 0) {
                s = s + ", ";
            }

            s = s + ((OEntityPlayerMP) this.a.get(i)).bS;
        }

        return s;
    }

    public String[] d() {
        String[] astring = new String[this.a.size()];

        for (int i = 0; i < this.a.size(); ++i) {
            astring[i] = ((OEntityPlayerMP) this.a.get(i)).bS;
        }

        return astring;
    }

    public OBanList e() {
        return this.f;
    }

    public OBanList f() {
        return this.g;
    }

    public void b(String s) {
        this.h.add(s.toLowerCase());
    }

    public void c(String s) {
        this.h.remove(s.toLowerCase());
    }

    public boolean d(String s) {
        s = s.trim().toLowerCase();
        return !this.k || this.h.contains(s) || this.i.contains(s);
    }

    public boolean e(String s) {
        return this.h.contains(s.trim().toLowerCase()) || this.e.I() && etc.getServer().getDefaultWorld().getWorld().L().v() && this.e.H().equalsIgnoreCase(s) || this.m;
    }

    public OEntityPlayerMP f(String s) {
        Iterator iterator = this.a.iterator();

        OEntityPlayerMP oentityplayermp;

        do {
            if (!iterator.hasNext()) {
                return null;
            }

            oentityplayermp = (OEntityPlayerMP) iterator.next();
        } while (!oentityplayermp.bS.equalsIgnoreCase(s));

        return oentityplayermp;
    }

    public List a(OChunkCoordinates ochunkcoordinates, int i, int j, int k, int l, int i1, int j1, Map map, String s, String s1) {
        if (this.a.isEmpty()) {
            return null;
        } else {
            Object object = new ArrayList();
            boolean flag = k < 0;
            int k1 = i * i;
            int l1 = j * j;

            k = OMathHelper.a(k);

            for (int i2 = 0; i2 < this.a.size(); ++i2) {
                OEntityPlayerMP oentityplayermp = (OEntityPlayerMP) this.a.get(i2);
                boolean flag1;

                if (s != null) {
                    flag1 = s.startsWith("!");
                    if (flag1) {
                        s = s.substring(1);
                    }

                    if (flag1 == s.equalsIgnoreCase(oentityplayermp.am())) {
                        continue;
                    }
                }

                if (s1 != null) {
                    flag1 = s1.startsWith("!");
                    if (flag1) {
                        s1 = s1.substring(1);
                    }

                    OScorePlayerTeam oscoreplayerteam = oentityplayermp.cq();
                    String s2 = oscoreplayerteam == null ? "" : oscoreplayerteam.b();

                    if (flag1 == s1.equalsIgnoreCase(s2)) {
                        continue;
                    }
                }

                if (ochunkcoordinates != null && (i > 0 || j > 0)) {
                    float f = ochunkcoordinates.e(oentityplayermp.b());

                    if (i > 0 && f < (float) k1 || j > 0 && f > (float) l1) {
                        continue;
                    }
                }

                if (this.a((OEntityPlayer) oentityplayermp, map) && (l == OEnumGameType.a.a() || l == oentityplayermp.c.b().a()) && (i1 <= 0 || oentityplayermp.cf >= i1) && oentityplayermp.cf <= j1) {
                    ((List) object).add(oentityplayermp);
                }
            }

            if (ochunkcoordinates != null) {
                Collections.sort((List) object, new OPlayerPositionComparator(ochunkcoordinates));
            }

            if (flag) {
                Collections.reverse((List) object);
            }

            if (k > 0) {
                object = ((List) object).subList(0, Math.min(k, ((List) object).size()));
            }

            return (List) object;
        }
    }

    private boolean a(OEntityPlayer oentityplayer, Map map) {
        if (map != null && map.size() != 0) {
            Iterator iterator = map.entrySet().iterator();

            Entry entry;
            boolean flag;
            int i;

            do {
                if (!iterator.hasNext()) {
                    return true;
                }

                entry = (Entry) iterator.next();
                String s = (String) entry.getKey();

                flag = false;
                if (s.endsWith("_min") && s.length() > 4) {
                    flag = true;
                    s = s.substring(0, s.length() - 4);
                }

                OScoreboard oscoreboard = oentityplayer.cp();
                OScoreObjective oscoreobjective = oscoreboard.b(s);

                if (oscoreobjective == null) {
                    return false;
                }

                OScore oscore = oentityplayer.cp().a(oentityplayer.am(), oscoreobjective);

                i = oscore.c();
                if (i < ((Integer) entry.getValue()).intValue() && flag) {
                    return false;
                }
            } while (i <= ((Integer) entry.getValue()).intValue() || flag);

            return false;
        } else {
            return true;
        }
    }
    // CanaryMod: change signature to include world name
    public void a(double d0, double d1, double d2, double d3, int i, OPacket opacket, String worldName) {
        this.a((OEntityPlayer) null, d0, d1, d2, d3, i, opacket, worldName);
    }

    // CanaryMod: change signature to include world name
    public void a(OEntityPlayer oentityplayer, double d0, double d1, double d2, double d3, int i, OPacket opacket, String worldName) {
        for (int j = 0; j < this.a.size(); ++j) {
            OEntityPlayerMP oentityplayermp = (OEntityPlayerMP) this.a.get(j);

            if (oentityplayermp != oentityplayer && oentityplayermp.ar == i && oentityplayermp.q.name.equals(worldName)) {
                double d4 = d0 - oentityplayermp.u;
                double d5 = d1 - oentityplayermp.v;
                double d6 = d2 - oentityplayermp.w;

                if (d4 * d4 + d5 * d5 + d6 * d6 < d3 * d3) {
                    oentityplayermp.a.b(opacket);
                }
            }
        }
    }

    public void g() {
        for (int i = 0; i < this.a.size(); ++i) {
            this.b((OEntityPlayerMP) this.a.get(i));
        }
    }

    public void h(String s) {
        this.i.add(s);
    }

    public void i(String s) {
        this.i.remove(s);
    }

    public Set h() {
        return this.i;
    }

    public Set i() {
        return this.h;
    }

    public void j() {}

    public void b(OEntityPlayerMP oentityplayermp, OWorldServer oworldserver) {
        oentityplayermp.a.b(new OPacket4UpdateTime(oworldserver.G(), oworldserver.H()));
        if (oworldserver.O()) {
            oentityplayermp.a.b(new OPacket70GameEvent(1, 0));
        }
    }

    public void f(OEntityPlayerMP oentityplayermp) {
        oentityplayermp.a(oentityplayermp.bL);
        oentityplayermp.l();
        oentityplayermp.a.b(new OPacket16BlockItemSwitch(oentityplayermp.bK.c));
    }

    public int k() {
        return this.a.size();
    }

    public int l() {
        return this.b;
    }

    public String[] m() {
        return etc.getServer().getDefaultWorld().getWorld().K().e().f();
    }

    public boolean n() {
        return this.k;
    }

    public void a(boolean flag) {
        this.k = flag;
    }

    public List j(String s) {
        ArrayList arraylist = new ArrayList();
        Iterator iterator = this.a.iterator();

        while (iterator.hasNext()) {
            OEntityPlayerMP oentityplayermp = (OEntityPlayerMP) iterator.next();

            if (oentityplayermp.p().equals(s)) {
                arraylist.add(oentityplayermp);
            }
        }

        return arraylist;
    }

    public int o() {
        return this.c;
    }

    public OMinecraftServer p() {
        return this.e;
    }

    public ONBTTagCompound q() {
        return null;
    }

    private void a(OEntityPlayerMP oentityplayermp, OEntityPlayerMP oentityplayermp1, OWorld oworld) {
        if (oentityplayermp1 != null) {
            oentityplayermp.c.a(oentityplayermp1.c.b());
        } else if (this.l != null) {
            oentityplayermp.c.a(this.l);
        }

        oentityplayermp.c.b(oworld.L().r());
    }

    // CanaryMod: change signature to include String stopMsg for custom stop messages.
    public void r(String stopMsg) {
        while (!this.a.isEmpty()) {
            ((OEntityPlayerMP) this.a.get(0)).a.c(stopMsg != null ? stopMsg : "Server closed");
        }
    }

    public void k(String s) {
        // TODO check: move showDeathMessages here?
        this.e.f(s);
        this.a((OPacket) (new OPacket3Chat(s)));
    }

    /**
     * Returns the list of bans
     *
     * @return bans
     */
    public String getBans() {
        List<String> list = new ArrayList<String>(this.f.c().keySet());

        java.util.Collections.sort(list);
        StringBuilder builder = new StringBuilder();
        int len = 0;

        for (String name : list) {
            if (len > 0) {
                builder.append(", ");
            }
            builder.append(name);
            len++;
        }
        return builder.toString();
    }

    /**
     * Returns the list of IP bans
     *
     * @return ip bans
     */
    public String getIpBans() {
        List<String> list = new ArrayList<String>(this.g.c().keySet());

        java.util.Collections.sort(list);
        StringBuilder builder = new StringBuilder();
        int len = 0;

        for (String name : list) {
            if (len > 0) {
                builder.append(", ");
            }
            builder.append(name);
            len++;
        }
        return builder.toString();
    }

    /**
     * Returns player ban status
     * @param name player name
     * @return true if player is banned from server
     */
    public boolean isBanned(String name) {
        return this.f.a(name);
    }
}
