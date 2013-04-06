import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;

public class ONetServerHandler extends ONetHandler {

    public final OINetworkManager a;
    private final OMinecraftServer d;
    public boolean b = false;
    public OEntityPlayerMP c;
    private int e;
    private int f;
    private boolean g;
    private int h;
    private long i;
    private static Random j = new Random();
    private long k;
    // private int l = 0; // CanaryMod - disable native chat spam protection
    private int m = 0;
    private double n;
    private double o;
    private double p;
    private boolean q = true;
    private OIntHashMap r = new OIntHashMap();

    public ONetServerHandler(OMinecraftServer ominecraftserver, OINetworkManager oinetworkmanager, OEntityPlayerMP oentityplayermp) {
        this.d = ominecraftserver;
        this.a = oinetworkmanager;
        oinetworkmanager.a((ONetHandler) this);
        this.c = oentityplayermp;
        oentityplayermp.a = this;
    }

    public void d() {
        this.g = false;
        ++this.e;
        this.d.a.a("packetflow");
        this.a.b();
        this.d.a.c("keepAlive");
        if ((long) this.e - this.k > 20L) {
            this.k = (long) this.e;
            this.i = System.nanoTime() / 1000000L;
            this.h = j.nextInt();
            this.b(new OPacket0KeepAlive(this.h));
        }

        /* CanaryMod - disable native chat spam protection
        if (this.l > 0) {
            --this.l;
        }
         */

        if (this.m > 0) {
            --this.m;
        }

        this.d.a.c("playerTick");
        this.d.a.b();
    }

    public void c(String s) {
        if (!this.b) {
            this.c.k();
            this.b(new OPacket255KickDisconnect(s));
            this.a.d();
            // CanaryMod - onPlayerDisconnect Hook
            HookParametersDisconnect hookResult = new HookParametersDisconnect(String.format(Colors.Yellow + "%s left the game.", this.c.bS), s);

            hookResult = (HookParametersDisconnect) etc.getLoader().callHook(PluginLoader.Hook.PLAYER_DISCONNECT, this.c.getPlayer(), hookResult);
            if (!hookResult.isHidden()) {
                this.d.ad().a((OPacket) (new OPacket3Chat(hookResult.getLeaveMessage())));
            }

            this.d.ad().e(this.c);
            this.b = true;
        }
    }

    public void a(OPacket10Flying opacket10flying) {
        OWorldServer oworldserver = this.d.getWorld(this.c.q.name, this.c.ar);

        this.g = true;
        if (!this.c.j) {
            double d0;

            if (!this.q) {
                d0 = opacket10flying.b - this.o;
                if (opacket10flying.a == this.n && d0 * d0 < 0.01D && opacket10flying.c == this.p) {
                    this.q = true;
                }
            }

            // CanaryMod: Notice player movement
            Player player = this.getPlayer();

            if (etc.floor(this.n) != etc.floor(player.getX()) || etc.floor(this.o) != etc.floor(player.getY()) || etc.floor(this.p) != etc.floor(player.getZ())) {
                Location from = new Location(player.getWorld(), etc.floor(this.n), etc.floor(this.o), etc.floor(this.p));

                Location to = player.getLocation();

                OEntity.manager.callHook(PluginLoader.Hook.PLAYER_MOVE, player, from, to);
            }

            if (this.q) {
                double d1;
                double d2;
                double d3;
                double d4;

                if (this.c.o != null) {
                    float f = this.c.A;
                    float f1 = this.c.B;

                    this.c.o.U();
                    d1 = this.c.u;
                    d2 = this.c.v;
                    d3 = this.c.w;
                    double d5 = 0.0D;

                    d4 = 0.0D;
                    if (opacket10flying.i) {
                        f = opacket10flying.e;
                        f1 = opacket10flying.f;
                    }

                    if (opacket10flying.h && opacket10flying.b == -999.0D && opacket10flying.d == -999.0D) {
                        if (Math.abs(opacket10flying.a) > 1.0D || Math.abs(opacket10flying.c) > 1.0D) {
                            System.err.println(this.c.bS + " was caught trying to crash the server with an invalid position.");
                            this.c("Nope!");
                            return;
                        }

                        d5 = opacket10flying.a;
                        d4 = opacket10flying.c;
                    }

                    this.c.F = opacket10flying.g;
                    this.c.g();
                    this.c.d(d5, 0.0D, d4);
                    this.c.a(d1, d2, d3, f, f1);
                    this.c.x = d5;
                    this.c.z = d4;
                    if (this.c.o != null) {
                        oworldserver.b(this.c.o, true);
                    }

                    if (this.c.o != null) {
                        this.c.o.U();
                    }

                    this.d.ad().d(this.c);
                    this.n = this.c.u;
                    this.o = this.c.v;
                    this.p = this.c.w;
                    oworldserver.g(this.c);
                    return;
                }

                if (this.c.bz()) {
                    this.c.g();
                    this.c.a(this.n, this.o, this.p, this.c.A, this.c.B);
                    oworldserver.g(this.c);
                    return;
                }

                d0 = this.c.v;
                this.n = this.c.u;
                this.o = this.c.v;
                this.p = this.c.w;
                d1 = this.c.u;
                d2 = this.c.v;
                d3 = this.c.w;
                float f2 = this.c.A;
                float f3 = this.c.B;

                if (opacket10flying.h && opacket10flying.b == -999.0D && opacket10flying.d == -999.0D) {
                    opacket10flying.h = false;
                }

                if (opacket10flying.h) {
                    d1 = opacket10flying.a;
                    d2 = opacket10flying.b;
                    d3 = opacket10flying.c;
                    d4 = opacket10flying.d - opacket10flying.b;
                    if (!this.c.bz() && (d4 > 1.65D || d4 < 0.1D)) {
                        this.c("Illegal stance");
                        this.d.al().b(this.c.bS + " had an illegal stance: " + d4);
                        return;
                    }

                    if (Math.abs(opacket10flying.a) > 3.2E7D || Math.abs(opacket10flying.c) > 3.2E7D) {
                        this.c("Illegal position");
                        return;
                    }
                }

                if (opacket10flying.i) {
                    f2 = opacket10flying.e;
                    f3 = opacket10flying.f;
                }

                this.c.g();
                this.c.X = 0.0F;
                this.c.a(this.n, this.o, this.p, f2, f3);
                if (!this.q) {
                    return;
                }

                d4 = d1 - this.c.u;
                double d6 = d2 - this.c.v;
                double d7 = d3 - this.c.w;
                double d8 = Math.min(Math.abs(d4), Math.abs(this.c.x));
                double d9 = Math.min(Math.abs(d6), Math.abs(this.c.y));
                double d10 = Math.min(Math.abs(d7), Math.abs(this.c.z));
                double d11 = d8 * d8 + d9 * d9 + d10 * d10;

                if (d11 > 100.0D && (!this.d.I() || !this.d.H().equals(this.c.bS))) {
                    this.d.al().b(this.c.bS + " moved too quickly! " + d4 + "," + d6 + "," + d7 + " (" + d8 + ", " + d9 + ", " + d10 + ")");
                    this.a(this.n, this.o, this.p, this.c.A, this.c.B);
                    return;
                }

                float f4 = 0.0625F;
                boolean flag = oworldserver.a(this.c, this.c.E.c().e((double) f4, (double) f4, (double) f4)).isEmpty();

                if (this.c.F && !opacket10flying.g && d6 > 0.0D) {
                    this.c.j(0.2F);
                }

                this.c.d(d4, d6, d7);
                this.c.F = opacket10flying.g;
                this.c.j(d4, d6, d7);
                double d12 = d6;

                d4 = d1 - this.c.u;
                d6 = d2 - this.c.v;
                if (d6 > -0.5D || d6 < 0.5D) {
                    d6 = 0.0D;
                }

                d7 = d3 - this.c.w;
                d11 = d4 * d4 + d6 * d6 + d7 * d7;
                boolean flag1 = false;

                if (d11 > 0.0625D && !this.c.bz() && !this.c.c.d()) {
                    flag1 = true;
                    this.d.al().b(this.c.bS + " moved wrongly!");
                }

                this.c.a(d1, d2, d3, f2, f3);
                boolean flag2 = oworldserver.a(this.c, this.c.E.c().e((double) f4, (double) f4, (double) f4)).isEmpty();

                if (flag && (flag1 || !flag2) && !this.c.bz()) {
                    this.a(this.n, this.o, this.p, f2, f3);
                    return;
                }

                OAxisAlignedBB oaxisalignedbb = this.c.E.c().b((double) f4, (double) f4, (double) f4).a(0.0D, -0.55D, 0.0D);

                // CanaryMod: check on flying capability instead of mode
                if (!this.d.Y() && !this.c.ce.c && !oworldserver.c(oaxisalignedbb)) {
                    if (d12 >= -0.03125D) {
                        ++this.f;
                        if (this.f > 80) {
                            this.d.al().b(this.c.bS + " was kicked for floating too long!");
                            this.c("Flying is not enabled on this server");
                            return;
                        }
                    }
                } else {
                    this.f = 0;
                }

                this.c.F = opacket10flying.g;
                this.d.ad().d(this.c);
                this.c.b(this.c.v - d0, opacket10flying.g);
            }
        }
    }

    public void a(double d0, double d1, double d2, float f, float f1) {
        // CanaryMod: Teleportation hook
        Location to = new Location();

        to.x = d0;
        to.y = d1;
        to.z = d2;
        to.rotX = f;
        to.rotY = f1;
        to.dimension = this.c.ar;
        to.world = this.c.q.name;
        Player player = this.getPlayer();

        if ((Boolean) OEntity.manager.callHook(PluginLoader.Hook.TELEPORT, player, player.getLocation(), to)) {
            return;
        }

        this.q = false;
        this.n = d0;
        this.o = d1;
        this.p = d2;
        this.c.a(d0, d1, d2, f, f1);
        this.c.a.b(new OPacket13PlayerLookMove(d0, d1 + 1.6200000047683716D, d1, d2, f, f1, false));
    }

    // CanaryMod: Store x/y/z
    int x, y, z, type;

    public void a(OPacket14BlockDig opacket14blockdig) {
        OWorldServer oworldserver = this.d.getWorld(this.c.q.name, this.c.ar);

        if (opacket14blockdig.e == 4) {
            this.c.a(false);
        } else if (opacket14blockdig.e == 3) {
            this.c.a(true);
        } else if (opacket14blockdig.e == 5) {
            this.c.bX();
        } else {
            boolean flag = false;

            if (opacket14blockdig.e == 0) {
                flag = true;
            }

            if (opacket14blockdig.e == 1) {
                flag = true;
            }

            if (opacket14blockdig.e == 2) {
                flag = true;
            }

            int i = opacket14blockdig.a;
            int j = opacket14blockdig.b;
            int k = opacket14blockdig.c;

            if (flag) {
                double d0 = this.c.u - ((double) i + 0.5D);
                double d1 = this.c.v - ((double) j + 0.5D) + 1.5D;
                double d2 = this.c.w - ((double) k + 0.5D);
                double d3 = d0 * d0 + d1 * d1 + d2 * d2;

                if (d3 > 36.0D) {
                    return;
                }

                if (j >= this.d.ab()) {
                    return;
                }
            }

            // CanaryMod: the player
            Player player = this.getPlayer();

            if (opacket14blockdig.e == 0) {
                // CanaryMod: Start digging
                // No buildrights
                if (!player.canBuild()) {
                    return;
                }

                if (!this.d.a(oworldserver, i, j, k, this.c) || player.isAdmin()) {
                    // CanaryMod: Dig hooks
                    Block block = oworldserver.world.getBlockAt(i, j, k);

                    block.setStatus(0); // Started digging
                    x = block.getX();
                    y = block.getY();
                    z = block.getZ();
                    type = block.getType();
                    if (!(Boolean) OEntity.manager.callHook(PluginLoader.Hook.BLOCK_DESTROYED, player, block)) {
                        this.c.c.a(i, j, k, opacket14blockdig.d);
                    } else {
                        this.c.a.b(new OPacket53BlockChange(i, j, k, oworldserver));
                    }
                } else {
                    this.c.a.b(new OPacket53BlockChange(i, j, k, oworldserver));
                }
            } else if (opacket14blockdig.e == 2) {
                // CanaryMod: Break block
                Block block = oworldserver.world.getBlockAt(i, j, k);

                block.setStatus(2); // Block broken
                OEntity.manager.callHook(PluginLoader.Hook.BLOCK_DESTROYED, player, block);

                this.c.c.a(i, j, k);
                if (oworldserver.a(i, j, k) != 0) {
                    this.c.a.b(new OPacket53BlockChange(i, j, k, oworldserver));
                }
            } else if (opacket14blockdig.e == 1) {
                // CanaryMod: Stop digging
                Block block = oworldserver.world.getBlockAt(i, j, k);

                block.setStatus(1); // Stopped digging
                OEntity.manager.callHook(PluginLoader.Hook.BLOCK_DESTROYED, player, block);

                this.c.c.c(i, j, k);
                if (oworldserver.a(i, j, k) != 0) {
                    this.c.a.b(new OPacket53BlockChange(i, j, k, oworldserver));
                }
            }
        }
    }

    // CanaryMod: Store the blocks between blockPlaced packets
    Block lastRightClicked;

    public void a(OPacket15Place opacket15place) {
        OWorldServer oworldserver = this.d.getWorld(this.c.q.name, this.c.ar);
        OItemStack oitemstack = this.c.bK.h();

        // CanaryMod: Store block data to call hooks
        // CanaryMod START
        Block blockClicked;
        Block blockPlaced = null;

        boolean flag = false;
        int i = opacket15place.d();
        int j = opacket15place.f();
        int k = opacket15place.g();
        int l = opacket15place.h();

        if (opacket15place.h() == 255) {
            // ITEM_USE -- if we have a lastRightClicked then it could be a
            // usable location
            blockClicked = this.lastRightClicked;
            this.lastRightClicked = null;
        } else {
            // RIGHTCLICK or BLOCK_PLACE .. or nothing
            blockClicked = oworldserver.world.getBlockAt(i, j, k);
            blockClicked.setFaceClicked(Block.Face.fromId(opacket15place.h()));

            this.lastRightClicked = blockClicked;
        }

        // If we clicked on something then we also have a location to place the
        // block
        if (blockClicked != null && oitemstack != null) {
            blockPlaced = blockClicked.getFace(Block.Face.fromId(opacket15place.h()));
            if (blockPlaced != null) {
                blockPlaced.setType(oitemstack.c);
            }
        }

        // CanaryMod: END

        if (opacket15place.h() == 255) {
            // CanaryMod: call our version with extra blockClicked/blockPlaced
            if (blockPlaced != null) {
                // Set the type of block to what it currently is
                blockPlaced.setType(oworldserver.world.getBlockIdAt(blockPlaced.getX(), blockPlaced.getY(), blockPlaced.getZ()));
            }

            if (oitemstack == null) {
                return;
            }

            this.c.c.itemUsed(this.c, oworldserver, oitemstack, blockPlaced, blockClicked);
        } else if (opacket15place.f() >= this.d.ab() - 1 && (opacket15place.h() == 1 || opacket15place.f() >= this.d.ab())) {
            this.c.a.b(new OPacket3Chat("" + OEnumChatFormatting.h + "Height limit for building is " + this.d.ab()));
            flag = true;
        } else {
            // CanaryMod: call BLOCK_RIGHTCLICKED
            Item item = (oitemstack != null) ? new Item(oitemstack) : new Item(Item.Type.Air);
            Player player = this.getPlayer();
            boolean cancelled = (Boolean) OEntity.manager.callHook(PluginLoader.Hook.BLOCK_RIGHTCLICKED, player, blockClicked, item);

            // CanaryMod: call original BLOCK_CREATED
            OEntity.manager.callHook(PluginLoader.Hook.BLOCK_CREATED, player, blockPlaced, blockClicked, item.getItemId());
            // CanaryMod: If we were building inside spawn, bail! (unless ops/admin)

            if (this.q && this.c.e((double) i + 0.5D, (double) j + 0.5D, (double) k + 0.5D) < 64.0D && (!this.d.a(oworldserver, i, j, k, this.c) || player.isAdmin()) && player.canBuild() && !cancelled) {
                this.c.c.a(this.c, oworldserver, oitemstack, i, j, k, l, opacket15place.j(), opacket15place.k(), opacket15place.l());
            } else {
                // CanaryMod: No point sending the client to update the blocks, you weren't allowed to place!
                this.c.a.b(new OPacket53BlockChange(i, j, k, oworldserver));
                return;
            }

            flag = true;
        }

        if (flag) {
            this.c.a.b(new OPacket53BlockChange(i, j, k, oworldserver));
            if (l == 0) {
                --j;
            }

            if (l == 1) {
                ++j;
            }

            if (l == 2) {
                --k;
            }

            if (l == 3) {
                ++k;
            }

            if (l == 4) {
                --i;
            }

            if (l == 5) {
                ++i;
            }

            this.c.a.b(new OPacket53BlockChange(i, j, k, oworldserver));
        }

        oitemstack = this.c.bK.h();
        if (oitemstack != null && oitemstack.a == 0) {
            this.c.bK.a[this.c.bK.c] = null;
            oitemstack = null;
        }

        if (oitemstack == null || oitemstack.n() == 0) {
            this.c.h = true;
            this.c.bK.a[this.c.bK.c] = OItemStack.b(this.c.bK.a[this.c.bK.c]);
            OSlot oslot = this.c.bM.a((OIInventory) this.c.bK, this.c.bK.c);

            this.c.bM.b();
            this.c.h = false;
            if (!OItemStack.b(this.c.bK.h(), opacket15place.i())) {
                this.b(new OPacket103SetSlot(this.c.bM.d, oslot.g, this.c.bK.h()));
            }
        }
    }

    public void a(String s, Object[] aobject) {
        // CanaryMod: disconnect!
        OEntity.manager.callHook(PluginLoader.Hook.DISCONNECT, this.getPlayer());
        this.d.al().a(this.c.bS + " lost connection: " + s);

        // CanaryMod - onPlayerDisconnect Hook
        HookParametersDisconnect hookResult = new HookParametersDisconnect(String.format(Colors.Yellow + "%s left the game.", this.c.ax()), s);

        hookResult = (HookParametersDisconnect) etc.getLoader().callHook(PluginLoader.Hook.PLAYER_DISCONNECT, this.getPlayer(), hookResult);
        if (!hookResult.isHidden()) {
            this.d.ad().a((OPacket) (new OPacket3Chat(hookResult.getLeaveMessage())));
        }
        this.d.ad().e(this.c);
        this.b = true;
        if (this.d.I() && this.c.bS.equals(this.d.H())) {
            this.d.al().a("Stopping singleplayer server as player logged out");
            this.d.n();
        }
    }

    public void a(OPacket opacket) {
        this.d.al().b(this.getClass() + " wasn\'t prepared to deal with a " + opacket.getClass());
        this.c("Protocol error, unexpected packet");
    }

    public void b(OPacket opacket) {
        if (opacket instanceof OPacket3Chat) {
            OPacket3Chat opacket3chat = (OPacket3Chat) opacket;
            int i = this.c.t();

            if (i == 2) {
                return;
            }

            if (i == 1 && !opacket3chat.d()) {
                return;
            }
        }

        try {
            this.a.a(opacket);
        } catch (Throwable throwable) {
            OCrashReport ocrashreport = OCrashReport.a(throwable, "Sending packet");
            OCrashReportCategory ocrashreportcategory = ocrashreport.a("Packet being sent");

            ocrashreportcategory.a("Packet ID", (Callable) (new OCallablePacketID(this, opacket)));
            ocrashreportcategory.a("Packet class", (Callable) (new OCallablePacketClass(this, opacket)));
            throw new OReportedException(ocrashreport);
        }
    }

    public void a(OPacket16BlockItemSwitch opacket16blockitemswitch) {
        if (opacket16blockitemswitch.a >= 0 && opacket16blockitemswitch.a < OInventoryPlayer.i()) {
            this.c.bK.c = opacket16blockitemswitch.a;
        } else {
            this.d.al().b(this.c.bS + " tried to set an invalid carried item");
        }
    }

    public void a(OPacket3Chat opacket3chat) {
        /* CanaryMod: leave code for diff visibility
        if (this.c.t() == 2) {
            this.b(new OPacket3Chat("Cannot send chat message."));
        } else {
            String s = opacket3chat.b;

            if (s.length() > 100) {
                this.c("Chat message too long");
            } else {
                s = s.trim();

                for (int i = 0; i < s.length(); ++i) {
                    if (!OChatAllowedCharacters.a(s.charAt(i))) {
                        this.c("Illegal characters in chat");
                        return;
                    }
                }

                if (s.startsWith("/")) {
                    this.d(s);
                } else {
                    if (this.c.t() == 1) {
                        this.b(new OPacket3Chat("Cannot send chat message."));
                        return;
                    }

                    s = "<" + this.c.ax() + "> " + s;
                    this.d.al().a(s);
                    this.d.ad().a((OPacket) (new OPacket3Chat(s, false)));
                }

                this.l += 20;
                if (this.l > 200 && !this.d.ad().e(this.c.bS)) {
                    this.c("disconnect.spam");
                }
            }
        }
        */
        // CanaryMod: redirect chathandling to player class
        this.getPlayer().chat(opacket3chat.b);
    }

    /* CanaryMod: Handled by PlayerCommands class
    private void d(String s) {
        this.d.E().a(this.c, s);
    }
    */

    public void a(OPacket18Animation opacket18animation) {
        if (opacket18animation.b == 1) {
            // CanaryMod: Swing the arm!
            OEntity.manager.callHook(PluginLoader.Hook.ARM_SWING, getPlayer());
            this.c.bK();
        }
    }

    public void a(OPacket19EntityAction opacket19entityaction) {
        if (opacket19entityaction.b == 1) {
            this.c.b(true);
        } else if (opacket19entityaction.b == 2) {
            this.c.b(false);
        } else if (opacket19entityaction.b == 4) {
            this.c.c(true);
        } else if (opacket19entityaction.b == 5) {
            this.c.c(false);
        } else if (opacket19entityaction.b == 3) {
            this.c.a(false, true, true);
            this.q = false;
        }
    }

    public void a(OPacket255KickDisconnect opacket255kickdisconnect) {
        this.a.a("disconnect.quitting", new Object[0]);
    }

    public int e() {
        return this.a.e();
    }

    public void a(OPacket7UseEntity opacket7useentity) {
        OWorldServer oworldserver = this.d.getWorld(this.c.q.name, this.c.ar);
        OEntity oentity = oworldserver.a(opacket7useentity.b);

        if (oentity != null) {
            boolean flag = this.c.n(oentity);
            double d0 = 36.0D;

            if (!flag) {
                d0 = 9.0D;
            }

            if (this.c.e(oentity) < d0) {
                if (opacket7useentity.c == 0) {
                    this.c.p(oentity);
                } else if (opacket7useentity.c == 1) {
                    this.c.q(oentity);
                }
            }
        }
    }

    public void a(OPacket205ClientCommand opacket205clientcommand) {
        if (opacket205clientcommand.a == 1) {
            if (this.c.j) {
                this.c = this.d.ad().a(this.c, 0, true);
            } else if (this.c.o().L().t()) {
                if (this.d.I() && this.c.bS.equals(this.d.H())) {
                    this.c.a.c("You have died. Game over, man, it\'s game over!");
                    this.d.P();
                } else {
                    OBanEntry obanentry = new OBanEntry(this.c.bS);

                    obanentry.b("Death in Hardcore");
                    this.d.ad().e().a(obanentry);
                    this.c.a.c("You have died. Game over, man, it\'s game over!");
                }
            } else {
                if (this.c.aX() > 0) {
                    return;
                }

                this.c = this.d.ad().a(this.c, 0, false);
            }
        }
    }

    public boolean b() {
        return true;
    }

    public void a(OPacket9Respawn opacket9respawn) {}

    public void a(OPacket101CloseWindow opacket101closewindow) {
        this.c.j();
    }

    public void a(OPacket102WindowClick opacket102windowclick) {
        if (this.c.bM.d == opacket102windowclick.a && this.c.bM.c(this.c)) {

            // CanaryMod: onSlotClick
            HookParametersSlotClick hookParametersSlotClick = new HookParametersSlotClick(this.c.bM, opacket102windowclick.b, opacket102windowclick.c, opacket102windowclick.f, this.c);
            hookParametersSlotClick = (HookParametersSlotClick)etc.getLoader().callHook(PluginLoader.Hook.SLOT_CLICK, new Object[]{hookParametersSlotClick});
            if(hookParametersSlotClick.allowClick != HookParametersSlotClick.AllowClick.ALLOW) {
                if(hookParametersSlotClick.allowClick == HookParametersSlotClick.AllowClick.CANCEL) {
                    //update client to let it know the slot wasn't changed
                    if(opacket102windowclick.f == 0) {
                        this.c.bM.updateSlot(opacket102windowclick.b);
                        this.c.updateSlot(-1, -1, this.c.bK.o());
                    } else {
                        ArrayList arraylist = new ArrayList();

                        for (int i = 0; i < this.c.bM.c.size(); ++i) {
                            arraylist.add(((OSlot) this.c.bM.c.get(i)).c());
                        }

                        this.c.a(this.c.bM, arraylist);
                    }
                }
                return;
            }
            // end CanaryMod

            OItemStack oitemstack = this.c.bM.a(opacket102windowclick.b, opacket102windowclick.c, opacket102windowclick.f, this.c);

            if (OItemStack.b(opacket102windowclick.e, oitemstack)) {
                this.c.a.b(new OPacket106Transaction(opacket102windowclick.a, opacket102windowclick.d, true));
                this.c.h = true;
                this.c.bM.b();
                this.c.i();
                this.c.h = false;
            } else {
                this.r.a(this.c.bM.d, Short.valueOf(opacket102windowclick.d));
                this.c.a.b(new OPacket106Transaction(opacket102windowclick.a, opacket102windowclick.d, false));
                this.c.bM.a(this.c, false);
                ArrayList arraylist = new ArrayList();

                for (int i = 0; i < this.c.bM.c.size(); ++i) {
                    arraylist.add(((OSlot) this.c.bM.c.get(i)).c());
                }

                this.c.a(this.c.bM, (List) arraylist);
            }
        }
    }

    public void a(OPacket108EnchantItem opacket108enchantitem) {
        if (this.c.bM.d == opacket108enchantitem.a && this.c.bM.c(this.c)) {
            this.c.bM.a((OEntityPlayer) this.c, opacket108enchantitem.b);
            this.c.bM.b();
        }
    }

    public void a(OPacket107CreativeSetSlot opacket107creativesetslot) {
        if (this.c.c.d()) {
            boolean flag = opacket107creativesetslot.a < 0;
            OItemStack oitemstack = opacket107creativesetslot.b;
            boolean flag1 = opacket107creativesetslot.a >= 1 && opacket107creativesetslot.a < 36 + OInventoryPlayer.i();
            boolean flag2 = oitemstack == null || oitemstack.c < OItem.f.length && oitemstack.c >= 0 && OItem.f[oitemstack.c] != null;
            boolean flag3 = oitemstack == null || oitemstack.k() >= 0 && oitemstack.k() >= 0 && oitemstack.a <= 64 && oitemstack.a > 0;

            if (flag1 && flag2 && flag3) {
                if (oitemstack == null) {
                    this.c.bL.a(opacket107creativesetslot.a, (OItemStack) null);
                } else {
                    this.c.bL.a(opacket107creativesetslot.a, oitemstack);
                }

                this.c.bL.a(this.c, true);
            } else if (flag && flag2 && flag3 && this.m < 200) {
                this.m += 20;
                OEntityItem oentityitem = this.c.c(oitemstack);

                if (oentityitem != null) {
                    oentityitem.c();
                }
            }
        }
    }

    public void a(OPacket106Transaction opacket106transaction) {
        Short oshort = (Short) this.r.a(this.c.bM.d);

        if (oshort != null && opacket106transaction.b == oshort.shortValue() && this.c.bM.d == opacket106transaction.a && !this.c.bM.c(this.c)) {
            this.c.bM.a(this.c, true);
        }
    }

    public void a(OPacket130UpdateSign opacket130updatesign) {
        OWorldServer oworldserver = this.d.getWorld(this.c.q.name, this.c.ar);

        if (oworldserver.f(opacket130updatesign.a, opacket130updatesign.b, opacket130updatesign.c)) {
            OTileEntity otileentity = oworldserver.r(opacket130updatesign.a, opacket130updatesign.b, opacket130updatesign.c);

            if (otileentity instanceof OTileEntitySign) {
                OTileEntitySign otileentitysign = (OTileEntitySign) otileentity;

                if (!otileentitysign.a()) {
                    this.d.g("Player " + this.c.bS + " just tried to change non-editable sign");
                    return;
                }
            }

            int i;
            int j;

            for (j = 0; j < 4; ++j) {
                boolean flag = true;

                /* CanaryMod: Remove the char limit, for plugins.
                if (opacket130updatesign.d[j].length() > 15) {
                    flag = false;
                } else {
                */
                for (i = 0; i < opacket130updatesign.d[j].length(); ++i) {
                    if (OChatAllowedCharacters.a.indexOf(opacket130updatesign.d[j].charAt(i)) < 0) {
                        flag = false;
                    }
                }
                //}

                if (!flag) {
                    opacket130updatesign.d[j] = "!?";
                }
            }

            if (otileentity instanceof OTileEntitySign) {
                j = opacket130updatesign.a;
                int k = opacket130updatesign.b;

                i = opacket130updatesign.c;
                OTileEntitySign otileentitysign1 = (OTileEntitySign) otileentity;

                // CanaryMod: Copy the old line text
                String[] old = Arrays.copyOf(otileentitysign1.a, otileentitysign1.a.length);

                System.arraycopy(opacket130updatesign.d, 0, otileentitysign1.a, 0, 4);

                // CanaryMod: Check if we can change it
                Sign sign = new Sign(otileentitysign1);

                if ((Boolean) OEntity.manager.callHook(PluginLoader.Hook.SIGN_CHANGE, this.getPlayer(), sign)) {
                    otileentitysign1.a = Arrays.copyOf(old, old.length);
                }

                otileentitysign1.k_();
                oworldserver.j(j, k, i);
            }
        }
    }

    public void a(OPacket0KeepAlive opacket0keepalive) {
        if (opacket0keepalive.a == this.h) {
            int i = (int) (System.nanoTime() / 1000000L - this.i);

            this.c.i = (this.c.i * 3 + i) / 4;
        }
    }

    public boolean a() {
        return true;
    }

    public void a(OPacket202PlayerAbilities opacket202playerabilities) {
        this.c.ce.b = opacket202playerabilities.f() && this.c.ce.c;
    }

    public void a(OPacket203AutoComplete opacket203autocomplete) {
        /* CanaryMod: moved logic to Player
        StringBuilder stringbuilder = new StringBuilder();

        String s;

        for (Iterator iterator = this.d.a((OICommandSender) this.c, opacket203autocomplete.d()).iterator(); iterator.hasNext(); stringbuilder.append(s)) {
            s = (String) iterator.next();
            if (stringbuilder.length() > 0) {
                stringbuilder.append("\u0000");
            }
        }

        this.d.a.b(new OPacket203AutoComplete(stringbuilder.toString()));
        */
        String result = this.getPlayer().autoComplete(opacket203autocomplete.d());

        this.c.a.b(new OPacket203AutoComplete(result));
    }

    public void a(OPacket204ClientInfo opacket204clientinfo) {
        this.c.a(opacket204clientinfo);
    }

    public void a(OPacket250CustomPayload opacket250custompayload) {
        DataInputStream datainputstream;
        OItemStack oitemstack;
        OItemStack oitemstack1;

        if ("MC|BEdit".equals(opacket250custompayload.a)) {
            try {
                datainputstream = new DataInputStream(new ByteArrayInputStream(opacket250custompayload.c));
                oitemstack = OPacket.c(datainputstream);
                if (!OItemWritableBook.a(oitemstack.q())) {
                    throw new IOException("Invalid book tag!");
                }

                oitemstack1 = this.c.bK.h();
                if (oitemstack != null && oitemstack.c == OItem.bG.cp && oitemstack.c == oitemstack1.c) {
                    oitemstack1.a("pages", (ONBTBase) oitemstack.q().m("pages"));
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        } else if ("MC|BSign".equals(opacket250custompayload.a)) {
            try {
                datainputstream = new DataInputStream(new ByteArrayInputStream(opacket250custompayload.c));
                oitemstack = OPacket.c(datainputstream);
                if (!OItemEditableBook.a(oitemstack.q())) {
                    throw new IOException("Invalid book tag!");
                }

                oitemstack1 = this.c.bK.h();
                if (oitemstack != null && oitemstack.c == OItem.bH.cp && oitemstack1.c == OItem.bG.cp) {
                    oitemstack1.a("author", (ONBTBase) (new ONBTTagString("author", this.c.bS)));
                    oitemstack1.a("title", (ONBTBase) (new ONBTTagString("title", oitemstack.q().i("title"))));
                    oitemstack1.a("pages", (ONBTBase) oitemstack.q().m("pages"));
                    oitemstack1.c = OItem.bH.cp;
                }
            } catch (Exception exception1) {
                exception1.printStackTrace();
            }
        } else {
            int i;

            if ("MC|TrSel".equals(opacket250custompayload.a)) {
                try {
                    datainputstream = new DataInputStream(new ByteArrayInputStream(opacket250custompayload.c));
                    i = datainputstream.readInt();
                    OContainer ocontainer = this.c.bM;

                    if (ocontainer instanceof OContainerMerchant) {
                        ((OContainerMerchant) ocontainer).e(i);
                    }
                } catch (Exception exception2) {
                    exception2.printStackTrace();
                }
            } else {
                int j;

                if ("MC|AdvCdm".equals(opacket250custompayload.a)) {
                    if (!this.d.Z()) {
                        this.c.a(this.c.a("advMode.notEnabled", new Object[0]));
                    } else if (this.c.a(2, "") && this.c.ce.d) {
                        try {
                            datainputstream = new DataInputStream(new ByteArrayInputStream(opacket250custompayload.c));
                            i = datainputstream.readInt();
                            j = datainputstream.readInt();
                            int k = datainputstream.readInt();
                            String s = OPacket.a(datainputstream, 256);
                            OTileEntity otileentity = this.c.q.r(i, j, k);

                            if (otileentity != null && otileentity instanceof OTileEntityCommandBlock) {
                                ((OTileEntityCommandBlock) otileentity).b(s);
                                this.c.q.j(i, j, k);
                                this.c.a("Command set: " + s);
                            }
                        } catch (Exception exception3) {
                            exception3.printStackTrace();
                        }
                    } else {
                        this.c.a(this.c.a("advMode.notAllowed", new Object[0]));
                    }
                } else if ("MC|Beacon".equals(opacket250custompayload.a)) {
                    if (this.c.bM instanceof OContainerBeacon) {
                        try {
                            datainputstream = new DataInputStream(new ByteArrayInputStream(opacket250custompayload.c));
                            i = datainputstream.readInt();
                            j = datainputstream.readInt();
                            OContainerBeacon ocontainerbeacon = (OContainerBeacon) this.c.bM;
                            OSlot oslot = ocontainerbeacon.a(0);

                            if (oslot.d()) {
                                oslot.a(1);
                                OTileEntityBeacon otileentitybeacon = ocontainerbeacon.e();

                                otileentitybeacon.d(i);
                                otileentitybeacon.e(j);
                                otileentitybeacon.k_();
                            }
                        } catch (Exception exception4) {
                            exception4.printStackTrace();
                        }
                    }
                } else if ("MC|ItemName".equals(opacket250custompayload.a) && this.c.bM instanceof OContainerRepair) {
                    OContainerRepair ocontainerrepair = (OContainerRepair) this.c.bM;

                    if (opacket250custompayload.c != null && opacket250custompayload.c.length >= 1) {
                        String s1 = OChatAllowedCharacters.a(new String(opacket250custompayload.c));

                        if (s1.length() <= 30) {
                            ocontainerrepair.a(s1);
                        }
                    } else {
                        ocontainerrepair.a("");
                    }
                }
            }
        }
    }

    /**
     * Returns the item in player's hand
     *
     * @return item
     */
    public int getItemInHand() {
        if (this.c.bK.h() != null) {
            return this.c.bK.h().c;
        }
        return -1;
    }

    /**
     * Returns the player
     *
     * @return player
     */
    public Player getPlayer() {
        return this.c.getPlayer();
    }

    /**
     * Override player entity
     * @param player
     */
    public void setPlayer(OEntityPlayerMP oentityplayermp) {
        this.c = oentityplayermp;
    }

    /**
     * Override player entity
     * @param player
     */
    public void setPlayer(Player player) {
        this.c = player.getEntity();
    }

    /**
     * Sends a message to the player
     *
     * @param msg
     */
    public void msg(String msg) {
        if (msg.length() >= 119) {
            String cutMsg = msg.substring(0, 118);
            int finalCut = cutMsg.lastIndexOf(" ");
            String subCut = cutMsg.substring(0, finalCut);
            String newMsg = msg.substring(finalCut);

            b(new OPacket3Chat(subCut));
            msg(newMsg);
        } else {
            b(new OPacket3Chat(msg));
        }
    }

}
