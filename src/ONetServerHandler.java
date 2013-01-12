import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;
import java.util.logging.Logger;

public class ONetServerHandler extends ONetHandler {

    public static Logger a = Logger.getLogger("Minecraft");
    public OINetworkManager b;
    public boolean c = false;
    private OMinecraftServer e;
    public OEntityPlayerMP d;
    private int f;
    private int g;
    private boolean h;
    private int i;
    private long j;
    private static Random k = new Random();
    private long l;
    // private int m = 0; CanaryMod - disable native spam protection
    private int n = 0;
    private double o;
    private double p;
    private double q;
    private boolean r = true;
    private OIntHashMap s = new OIntHashMap();

    public ONetServerHandler(OMinecraftServer ominecraftserver, OINetworkManager oinetworkmanager, OEntityPlayerMP oentityplayermp) {
        this.e = ominecraftserver;
        this.b = oinetworkmanager;
        oinetworkmanager.a((ONetHandler) this);
        this.d = oentityplayermp;
        oentityplayermp.a = this;
    }

    public void d() {
        this.h = false;
        ++this.f;
        this.e.b.a("packetflow");
        this.b.b();
        this.e.b.c("keepAlive");
        if ((long) this.f - this.l > 20L) {
            this.l = (long) this.f;
            this.j = System.nanoTime() / 1000000L;
            this.i = k.nextInt();
            this.b(new OPacket0KeepAlive(this.i));
        }

        /* CanaryMod - disable native spam protection
        if (this.m > 0) {
            --this.m;
        }
         */

        if (this.n > 0) {
            --this.n;
        }

        this.e.b.c("playerTick");
        this.e.b.b();
    }

    public void c(String s) {
        if (!this.c) {
            this.d.l();
            this.b(new OPacket255KickDisconnect(s));
            this.b.d();
            // CanaryMod - onPlayerDisconnect Hook
            HookParametersDisconnect hookResult = new HookParametersDisconnect(String.format(Colors.Yellow + "%s left the game.", this.d.bR), s); // XXX

            hookResult = (HookParametersDisconnect) etc.getLoader().callHook(PluginLoader.Hook.PLAYER_DISCONNECT, this.d.getPlayer(), hookResult);
            if (!hookResult.isHidden()) {
                this.e.ad().a((OPacket) (new OPacket3Chat(hookResult.getLeaveMessage())));
            }

            this.e.ad().e(this.d);
            this.c = true;
        }
    }

    public void a(OPacket10Flying opacket10flying) {
        OWorldServer oworldserver = this.e.getWorld(this.d.p.name, this.d.aq);

        this.h = true;
        if (!this.d.j) {
            double d0;

            if (!this.r) {
                d0 = opacket10flying.b - this.p;
                if (opacket10flying.a == this.o && d0 * d0 < 0.01D && opacket10flying.c == this.q) {
                    this.r = true;
                }
            }

            // CanaryMod: Notice player movement
            Player player = this.getPlayer();

            if (etc.floor(this.o) != etc.floor(player.getX()) || etc.floor(this.p) != etc.floor(player.getY()) || etc.floor(this.q) != etc.floor(player.getZ())) {
                Location from = new Location(player.getWorld(), etc.floor(this.o), etc.floor(this.p), etc.floor(this.q));

                Location to = player.getLocation();

                OEntity.manager.callHook(PluginLoader.Hook.PLAYER_MOVE, player, from, to);
            }

            if (this.r) {
                double d1;
                double d2;
                double d3;
                double d4;

                if (this.d.o != null) {
                    float f = this.d.z;
                    float f1 = this.d.A;

                    this.d.o.V();
                    d1 = this.d.t;
                    d2 = this.d.u;
                    d3 = this.d.v;
                    double d5 = 0.0D;

                    d4 = 0.0D;
                    if (opacket10flying.i) {
                        f = opacket10flying.e;
                        f1 = opacket10flying.f;
                    }

                    if (opacket10flying.h && opacket10flying.b == -999.0D && opacket10flying.d == -999.0D) {
                        if (Math.abs(opacket10flying.a) > 1.0D || Math.abs(opacket10flying.c) > 1.0D) {
                            System.err.println(this.d.bR + " was caught trying to crash the server with an invalid position.");
                            this.c("Nope!");
                            return;
                        }

                        d5 = opacket10flying.a;
                        d4 = opacket10flying.c;
                    }

                    this.d.E = opacket10flying.g;
                    this.d.g();
                    this.d.d(d5, 0.0D, d4);
                    this.d.a(d1, d2, d3, f, f1);
                    this.d.w = d5;
                    this.d.y = d4;
                    if (this.d.o != null) {
                        oworldserver.b(this.d.o, true);
                    }

                    if (this.d.o != null) {
                        this.d.o.V();
                    }

                    this.e.ad().d(this.d);
                    this.o = this.d.t;
                    this.p = this.d.u;
                    this.q = this.d.v;
                    oworldserver.g(this.d);
                    return;
                }

                if (this.d.bw()) {
                    this.d.g();
                    this.d.a(this.o, this.p, this.q, this.d.z, this.d.A);
                    oworldserver.g(this.d);
                    return;
                }

                d0 = this.d.u;
                this.o = this.d.t;
                this.p = this.d.u;
                this.q = this.d.v;
                d1 = this.d.t;
                d2 = this.d.u;
                d3 = this.d.v;
                float f2 = this.d.z;
                float f3 = this.d.A;

                if (opacket10flying.h && opacket10flying.b == -999.0D && opacket10flying.d == -999.0D) {
                    opacket10flying.h = false;
                }

                if (opacket10flying.h) {
                    d1 = opacket10flying.a;
                    d2 = opacket10flying.b;
                    d3 = opacket10flying.c;
                    d4 = opacket10flying.d - opacket10flying.b;
                    if (!this.d.bw() && (d4 > 1.65D || d4 < 0.1D)) {
                        this.c("Illegal stance");
                        a.warning(this.d.bR + " had an illegal stance: " + d4);
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

                this.d.g();
                this.d.W = 0.0F;
                this.d.a(this.o, this.p, this.q, f2, f3);
                if (!this.r) {
                    return;
                }

                d4 = d1 - this.d.t;
                double d6 = d2 - this.d.u;
                double d7 = d3 - this.d.v;
                double d8 = Math.min(Math.abs(d4), Math.abs(this.d.w));
                double d9 = Math.min(Math.abs(d6), Math.abs(this.d.x));
                double d10 = Math.min(Math.abs(d7), Math.abs(this.d.y));
                double d11 = d8 * d8 + d9 * d9 + d10 * d10;

                if (d11 > 100.0D && (!this.e.I() || !this.e.H().equals(this.d.bR))) {
                    a.warning(this.d.bR + " moved too quickly! " + d4 + "," + d6 + "," + d7 + " (" + d8 + ", " + d9 + ", " + d10 + ")");
                    this.a(this.o, this.p, this.q, this.d.z, this.d.A);
                    return;
                }

                float f4 = 0.0625F;
                boolean flag = oworldserver.a(this.d, this.d.D.c().e((double) f4, (double) f4, (double) f4)).isEmpty();

                if (this.d.E && !opacket10flying.g && d6 > 0.0D) {
                    this.d.j(0.2F);
                }

                this.d.d(d4, d6, d7);
                this.d.E = opacket10flying.g;
                this.d.j(d4, d6, d7);
                double d12 = d6;

                d4 = d1 - this.d.t;
                d6 = d2 - this.d.u;
                if (d6 > -0.5D || d6 < 0.5D) {
                    d6 = 0.0D;
                }

                d7 = d3 - this.d.v;
                d11 = d4 * d4 + d6 * d6 + d7 * d7;
                boolean flag1 = false;

                if (d11 > 0.0625D && !this.d.bw() && !this.d.c.d()) {
                    flag1 = true;
                    a.warning(this.d.bR + " moved wrongly!");
                }

                this.d.a(d1, d2, d3, f2, f3);
                boolean flag2 = oworldserver.a(this.d, this.d.D.c().e((double) f4, (double) f4, (double) f4)).isEmpty();

                if (flag && (flag1 || !flag2) && !this.d.bw()) {
                    this.a(this.o, this.p, this.q, f2, f3);
                    return;
                }

                OAxisAlignedBB oaxisalignedbb = this.d.D.c().b((double) f4, (double) f4, (double) f4).a(0.0D, -0.55D, 0.0D);

                if (!this.e.Y() && !this.d.c.d() && !oworldserver.c(oaxisalignedbb)) {
                    if (d12 >= -0.03125D) {
                        ++this.g;
                        if (this.g > 80) {
                            a.warning(this.d.bR + " was kicked for floating too long!");
                            this.c("Flying is not enabled on this server");
                            return;
                        }
                    }
                } else {
                    this.g = 0;
                }

                this.d.E = opacket10flying.g;
                this.e.ad().d(this.d);
                this.d.b(this.d.u - d0, opacket10flying.g);
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
        to.dimension = this.d.ap;
        to.world = this.d.p.name;
        Player player = this.getPlayer();

        if ((Boolean) OEntity.manager.callHook(PluginLoader.Hook.TELEPORT, player, player.getLocation(), to)) {
            return;
        }

        this.r = false;
        this.o = d0;
        this.p = d1;
        this.q = d2;
        this.d.a(d0, d1, d2, f, f1);
        this.d.a.b(new OPacket13PlayerLookMove(d0, d1 + 1.6200000047683716D, d1, d2, f, f1, false));
    }

    // CanaryMod: Store x/y/z
    int x, y, z, type;

    public void a(OPacket14BlockDig opacket14blockdig) {
        OWorldServer oworldserver = this.e.getWorld(this.d.p.name, this.d.aq);

        if (opacket14blockdig.e == 4) {
            this.d.f(false);
        } else if (opacket14blockdig.e == 3) {
            this.d.f(true);
        } else if (opacket14blockdig.e == 5) {
            this.d.bO();
        } else {
            int i = this.e.ak();
            // CanaryMod: We allow admins and ops to dig!
            boolean flag = oworldserver.u.h != 0 || this.e.ad().i().isEmpty() || this.e.ad().e(this.d.bR) || i <= 0 || this.e.I() || this.getPlayer().isAdmin();
            boolean flag1 = false;

            if (opacket14blockdig.e == 0) {
                flag1 = true;
            }

            if (opacket14blockdig.e == 1) {
                flag1 = true;
            }

            if (opacket14blockdig.e == 2) {
                flag1 = true;
            }

            int j = opacket14blockdig.a;
            int k = opacket14blockdig.b;
            int l = opacket14blockdig.c;

            if (flag1) {
                double d0 = this.d.t - ((double) j + 0.5D);
                double d1 = this.d.u - ((double) k + 0.5D) + 1.5D;
                double d2 = this.d.v - ((double) l + 0.5D);
                double d3 = d0 * d0 + d1 * d1 + d2 * d2;

                if (d3 > 36.0D) {
                    return;
                }

                if (k >= this.e.ab()) {
                    return;
                }
            }

            OChunkCoordinates ochunkcoordinates = oworldserver.H();
            int i1 = OMathHelper.a(j - ochunkcoordinates.a);
            int j1 = OMathHelper.a(l - ochunkcoordinates.c);

            if (i1 > j1) {
                j1 = i1;
            }

            // CanaryMod: the player
            Player player = this.getPlayer();

            if (opacket14blockdig.e == 0) {
                // CanaryMod: Start digging
                // No buildrights
                if (!player.canBuild()) {
                    return;
                }

                if (j1 <= i && !flag) {
                    this.d.a.b(new OPacket53BlockChange(j, k, l, oworldserver));
                } else {
                    // CanaryMod: Dig hooks
                    Block block = oworldserver.world.getBlockAt(j, k, l);

                    block.setStatus(0); // Started digging
                    x = block.getX();
                    y = block.getY();
                    z = block.getZ();
                    type = block.getType();
                    if (!(Boolean) OEntity.manager.callHook(PluginLoader.Hook.BLOCK_DESTROYED, player, block)) {
                        this.d.c.a(j, k, l, opacket14blockdig.d);
                    } else {
                        this.d.a.b(new OPacket53BlockChange(j, k, l, oworldserver));
                    }
                }
            } else if (opacket14blockdig.e == 2) {
                // CanaryMod: Break block
                Block block = oworldserver.world.getBlockAt(j, k, l);

                block.setStatus(2); // Block broken
                OEntity.manager.callHook(PluginLoader.Hook.BLOCK_DESTROYED, player, block);

                this.d.c.a(j, k, l);
                if (oworldserver.a(j, k, l) != 0) {
                    this.d.a.b(new OPacket53BlockChange(j, k, l, oworldserver));
                }
            } else if (opacket14blockdig.e == 1) {
                // CanaryMod: Stop digging
                Block block = oworldserver.world.getBlockAt(j, k, l);

                block.setStatus(1); // Stopped digging
                OEntity.manager.callHook(PluginLoader.Hook.BLOCK_DESTROYED, player, block);

                this.d.c.c(j, k, l);
                if (oworldserver.a(j, k, l) != 0) {
                    this.d.a.b(new OPacket53BlockChange(j, k, l, oworldserver));
                }
            }
        }
    }

    // CanaryMod: Store the blocks between blockPlaced packets
    Block lastRightClicked;

    public void a(OPacket15Place opacket15place) {
        OWorldServer oworldserver = this.e.getWorld(this.d.p.name, this.d.aq);
        OItemStack oitemstack = this.d.bJ.g();

        // CanaryMod: Store block data to call hooks
        // CanaryMod START
        Block blockClicked;
        Block blockPlaced = null;

        boolean flag = false;
        int i = opacket15place.d();
        int j = opacket15place.f();
        int k = opacket15place.g();
        int l = opacket15place.h();
        int i1 = this.e.ak();
        // We allow admins and ops to build!
        boolean flag1 = oworldserver.u.h != 0 || this.e.ad().i().isEmpty() || this.e.ad().e(this.d.bR) || i1 <= 0 || this.e.I() || this.getPlayer().isAdmin();

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

            this.d.c.itemUsed(this.d, oworldserver, oitemstack, blockPlaced, blockClicked);
        } else if (opacket15place.f() >= this.e.ab() - 1 && (opacket15place.h() == 1 || opacket15place.f() >= this.e.ab())) {
            this.d.a.b(new OPacket3Chat("\u00A77Height limit for building is " + this.e.ab()));
            flag = true;
        } else {
            OChunkCoordinates ochunkcoordinates = oworldserver.H();
            // CanaryMod : Fix stupid buggy spawn protection. (2 times)
            int j1 = OMathHelper.a((l == 4 ? i - 1 : (l == 5 ? (i + 1) : i)) - ochunkcoordinates.a);
            int k1 = OMathHelper.a((l == 2 ? k - 1 : (l == 3 ? (k + 1) : k)) - ochunkcoordinates.c);

            if (j1 > k1) {
                k1 = j1;
            }

            // CanaryMod: call BLOCK_RIGHTCLICKED
            Item item = (oitemstack != null) ? new Item(oitemstack) : new Item(Item.Type.Air);
            Player player = this.getPlayer();
            boolean cancelled = (Boolean) OEntity.manager.callHook(PluginLoader.Hook.BLOCK_RIGHTCLICKED, player, blockClicked, item);

            // CanaryMod: call original BLOCK_CREATED
            OEntity.manager.callHook(PluginLoader.Hook.BLOCK_CREATED, player, blockPlaced, blockClicked, item.getItemId());
            // CanaryMod: If we were building inside spawn, bail! (unless ops/admin)

            if (this.r && this.d.e((double) i + 0.5D, (double) j + 0.5D, (double) k + 0.5D) < 64.0D && (k1 > i1 || flag1) && player.canBuild() && !cancelled) {
                this.d.c.a(this.d, oworldserver, oitemstack, i, j, k, l, opacket15place.j(), opacket15place.l(), opacket15place.m());
            } else {
                // CanaryMod: No point sending the client to update the blocks, you weren't allowed to place!
                this.d.a.b(new OPacket53BlockChange(i, j, k, oworldserver));
                return;
            }

            flag = true;
        }

        if (flag) {
            this.d.a.b(new OPacket53BlockChange(i, j, k, oworldserver));
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

            this.d.a.b(new OPacket53BlockChange(i, j, k, oworldserver));
        }

        oitemstack = this.d.bJ.g();
        if (oitemstack != null && oitemstack.a == 0) {
            this.d.bJ.a[this.d.bJ.c] = null;
            oitemstack = null;
        }

        if (oitemstack == null || oitemstack.m() == 0) {
            this.d.h = true;
            this.d.bJ.a[this.d.bJ.c] = OItemStack.b(this.d.bJ.a[this.d.bJ.c]);
            OSlot oslot = this.d.bL.a((OIInventory) this.d.bJ, this.d.bJ.c);

            this.d.bL.b();
            this.d.h = false;
            if (!OItemStack.b(this.d.bJ.g(), opacket15place.i())) {
                this.b(new OPacket103SetSlot(this.d.bL.d, oslot.g, this.d.bJ.g()));
            }
        }
    }

    public void a(String s, Object[] aobject) {
        // CanaryMod: disconnect!
        OEntity.manager.callHook(PluginLoader.Hook.DISCONNECT, this.getPlayer());
        a.info(this.d.bR + " lost connection: " + s);

        // CanaryMod - onPlayerDisconnect Hook
        HookParametersDisconnect hookResult = new HookParametersDisconnect(String.format(Colors.Yellow + "%s left the game.", this.d.bR), s);

        hookResult = (HookParametersDisconnect) etc.getLoader().callHook(PluginLoader.Hook.PLAYER_DISCONNECT, this.getPlayer(), hookResult); // XXX
        if (!hookResult.isHidden()) {
            this.e.ad().a((OPacket) (new OPacket3Chat(hookResult.getLeaveMessage())));
        }
        this.e.ad().e(this.d);
        this.c = true;
        if (this.e.I() && this.d.bR.equals(this.e.H())) {
            a.info("Stopping singleplayer server as player logged out");
            this.e.n();
        }
    }

    public void a(OPacket opacket) {
        a.warning(this.getClass() + " wasn\'t prepared to deal with a " + opacket.getClass());
        this.c("Protocol error, unexpected packet");
    }

    public void b(OPacket opacket) {
        if (opacket instanceof OPacket3Chat) {
            OPacket3Chat opacket3chat = (OPacket3Chat) opacket;
            int i = this.d.u();

            if (i == 2) {
                return;
            }

            if (i == 1 && !opacket3chat.d()) {
                return;
            }
        }

        this.b.a(opacket);
    }

    public void a(OPacket16BlockItemSwitch opacket16blockitemswitch) {
        if (opacket16blockitemswitch.a >= 0 && opacket16blockitemswitch.a < OInventoryPlayer.h()) {
            this.d.bJ.c = opacket16blockitemswitch.a;
        } else {
            a.warning(this.d.bR + " tried to set an invalid carried item");
        }
    }

    public void a(OPacket3Chat opacket3chat) {
        /* CanaryMod: leave code for diff visibility
        if (this.d.u() == 2) {
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
                    if (this.d.u() == 1) {
                        this.b(new OPacket3Chat("Cannot send chat message."));
                        return;
                    }

                    s = "<" + this.d.bR + "> " + s;
                    a.info(s);
                    this.e.ad().a((OPacket) (new OPacket3Chat(s, false)));
                }

                this.m += 20;
                if (this.m > 200 && !this.e.ad().e(this.d.bR)) {
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
        this.e.E().a(this.d, s);
    }
    */

    public void a(OPacket18Animation opacket18animation) {
        if (opacket18animation.b == 1) {
            // CanaryMod: Swing the arm!
            OEntity.manager.callHook(PluginLoader.Hook.ARM_SWING, getPlayer());
            this.d.bH();
        }
    }

    public void a(OPacket19EntityAction opacket19entityaction) {
        if (opacket19entityaction.b == 1) {
            this.d.a(true);
        } else if (opacket19entityaction.b == 2) {
            this.d.a(false);
        } else if (opacket19entityaction.b == 4) {
            this.d.b(true);
        } else if (opacket19entityaction.b == 5) {
            this.d.b(false);
        } else if (opacket19entityaction.b == 3) {
            this.d.a(false, true, true);
            this.r = false;
        }
    }

    public void a(OPacket255KickDisconnect opacket255kickdisconnect) {
        this.b.a("disconnect.quitting", new Object[0]);
    }

    public int e() {
        return this.b.e();
    }

    public void a(OPacket7UseEntity opacket7useentity) {
        OWorldServer oworldserver = this.e.getWorld(this.d.p.name, this.d.aq);
        OEntity oentity = oworldserver.a(opacket7useentity.b);

        if (oentity != null) {
            boolean flag = this.d.n(oentity);
            double d0 = 36.0D;

            if (!flag) {
                d0 = 9.0D;
            }

            if (this.d.e(oentity) < d0) {
                if (opacket7useentity.c == 0) {
                    this.d.p(oentity);
                } else if (opacket7useentity.c == 1) {
                    this.d.q(oentity);
                }
            }
        }
    }

    public void a(OPacket205ClientCommand opacket205clientcommand) {
        if (opacket205clientcommand.a == 1) {
            // CanaryMod: onPlayerRespawn TODO todo xxx I may have broken this --somners
        	OChunkCoordinates spawnLoc = this.d.bZ();

            if (spawnLoc == null) {
                spawnLoc = this.e.getWorld(this.d.p.name, 0).H();
            }

            // CanaryMod: check if your bed location exists before trying to respawn you there upon death
            Location respawnLocation = new Location(this.e.getWorld(this.d.p.name, 0).world, spawnLoc.a, spawnLoc.b, spawnLoc.c);


            if (this.d.j) {
                this.d = this.e.ad().a(this.d, respawnLocation.dimension, true, respawnLocation);
            } else if (this.d.p().K().t()) {
                if (this.e.I() && this.d.bR.equals(this.e.H())) {
                    this.d.a.c("You have died. Game over, man, it\'s game over!");
                    this.e.P();
                } else {
                    OBanEntry obanentry = new OBanEntry(this.d.bR);

                    obanentry.b("Death in Hardcore");
                    this.e.ad().e().a(obanentry);
                    this.d.a.c("You have died. Game over, man, it\'s game over!");
                }
            } else {
                if (this.d.aU() > 0) {
                    return;
                }

                this.d = this.e.ad().a(this.d, respawnLocation.dimension, false, respawnLocation);
            }
        }
    }

    public boolean b() {
        return true;
    }

    public void a(OPacket9Respawn opacket9respawn) {}

    public void a(OPacket101CloseWindow opacket101closewindow) {
        this.d.k();
    }

    public void a(OPacket102WindowClick opacket102windowclick) {
        if (this.d.bL.d == opacket102windowclick.a && this.d.bL.c(this.d)) {
            
            // CanaryMod: onSlotClick
            HookParametersSlotClick hookParametersSlotClick = new HookParametersSlotClick(this.d.bL, opacket102windowclick.b, opacket102windowclick.c, opacket102windowclick.f, this.d);
            hookParametersSlotClick = (HookParametersSlotClick)etc.getLoader().callHook(PluginLoader.Hook.SLOT_CLICK, new Object[]{hookParametersSlotClick});
            if(hookParametersSlotClick.allowClick != HookParametersSlotClick.AllowClick.ALLOW) {
                if(hookParametersSlotClick.allowClick == HookParametersSlotClick.AllowClick.CANCEL) {
                    //update client to let it know the slot wasn't changed
                    if(opacket102windowclick.f == 0) {
                        this.d.bL.updateSlot(opacket102windowclick.b);
                        this.d.updateSlot(-1, -1, this.d.bJ.n());
                    } else {
                        ArrayList arraylist = new ArrayList();

                        for (int i = 0; i < this.d.bL.c.size(); ++i) {
                            arraylist.add(((OSlot) this.d.bL.c.get(i)).c());
                        }

                        this.d.a(this.d.bL, arraylist);
                    }
                }
                return;
            }
            // end CanaryMod
            
            OItemStack oitemstack = this.d.bL.a(opacket102windowclick.b, opacket102windowclick.c, opacket102windowclick.f, this.d);

            if (OItemStack.b(opacket102windowclick.e, oitemstack)) {
                this.d.a.b(new OPacket106Transaction(opacket102windowclick.a, opacket102windowclick.d, true));
                this.d.h = true;
                this.d.bL.b();
                this.d.j();
                this.d.h = false;
            } else {
                this.s.a(this.d.bL.d, Short.valueOf(opacket102windowclick.d));
                this.d.a.b(new OPacket106Transaction(opacket102windowclick.a, opacket102windowclick.d, false));
                this.d.bL.a(this.d, false);
                ArrayList arraylist = new ArrayList();

                for (int i = 0; i < this.d.bL.c.size(); ++i) {
                    arraylist.add(((OSlot) this.d.bL.c.get(i)).c());
                }

                this.d.a(this.d.bL, arraylist);
            }
        }
    }

    public void a(OPacket108EnchantItem opacket108enchantitem) {
        if (this.d.bL.d == opacket108enchantitem.a && this.d.bL.c(this.d)) {
            this.d.bL.a((OEntityPlayer) this.d, opacket108enchantitem.b);
            this.d.bL.b();
        }
    }

    public void a(OPacket107CreativeSetSlot opacket107creativesetslot) {
        if (this.d.c.d()) {
            boolean flag = opacket107creativesetslot.a < 0;
            OItemStack oitemstack = opacket107creativesetslot.b;
            boolean flag1 = opacket107creativesetslot.a >= 1 && opacket107creativesetslot.a < 36 + OInventoryPlayer.h();
            boolean flag2 = oitemstack == null || oitemstack.c < OItem.e.length && oitemstack.c >= 0 && OItem.e[oitemstack.c] != null;
            boolean flag3 = oitemstack == null || oitemstack.j() >= 0 && oitemstack.j() >= 0 && oitemstack.a <= 64 && oitemstack.a > 0;

            if (flag1 && flag2 && flag3) {
                if (oitemstack == null) {
                    this.d.bK.a(opacket107creativesetslot.a, (OItemStack) null);
                } else {
                    this.d.bK.a(opacket107creativesetslot.a, oitemstack);
                }

                this.d.bK.a(this.d, true);
            } else if (flag && flag2 && flag3 && this.n < 200) {
                this.n += 20;
                OEntityItem oentityitem = this.d.c(oitemstack);

                if (oentityitem != null) {
                    oentityitem.c();
                }
            }
        }
    }

    public void a(OPacket106Transaction opacket106transaction) {
        Short oshort = (Short) this.s.a(this.d.bL.d);

        if (oshort != null && opacket106transaction.b == oshort.shortValue() && this.d.bL.d == opacket106transaction.a && !this.d.bL.c(this.d)) {
            this.d.bL.a(this.d, true);
        }
    }

    public void a(OPacket130UpdateSign opacket130updatesign) {
        OWorldServer oworldserver = this.e.getWorld(this.d.p.name, this.d.aq);

        if (oworldserver.f(opacket130updatesign.a, opacket130updatesign.b, opacket130updatesign.c)) {
            OTileEntity otileentity = oworldserver.q(opacket130updatesign.a, opacket130updatesign.b, opacket130updatesign.c);

            if (otileentity instanceof OTileEntitySign) {
                OTileEntitySign otileentitysign = (OTileEntitySign) otileentity;

                if (!otileentitysign.a()) {
                    this.e.g("Player " + this.d.bR + " just tried to change non-editable sign");
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

                otileentitysign1.d();
                oworldserver.i(j, k, i);
            }
        }
    }

    public void a(OPacket0KeepAlive opacket0keepalive) {
        if (opacket0keepalive.a == this.i) {
            int i = (int) (System.nanoTime() / 1000000L - this.j);

            this.d.i = (this.d.i * 3 + i) / 4;
        }
    }

    public boolean a() {
        return true;
    }

    public void a(OPacket202PlayerAbilities opacket202playerabilities) {
        this.d.cd.b = opacket202playerabilities.f() && this.d.cd.c;
    }

    public void a(OPacket203AutoComplete opacket203autocomplete) {
        /* CanaryMod: moved logic to Player
        StringBuilder stringbuilder = new StringBuilder();

        String s;

        for (Iterator iterator = this.e.a((OICommandSender) this.d, opacket203autocomplete.d()).iterator(); iterator.hasNext(); stringbuilder.append(s)) {
            s = (String) iterator.next();
            if (stringbuilder.length() > 0) {
                stringbuilder.append(");
            }
        }

        this.d.a.b(new OPacket203AutoComplete(stringbuilder.toString()));
        */
        String result = this.getPlayer().autoComplete(opacket203autocomplete.d());

        this.d.a.b(new OPacket203AutoComplete(result));
    }

    public void a(OPacket204ClientInfo opacket204clientinfo) {
        this.d.a(opacket204clientinfo);
    }

    public void a(OPacket250CustomPayload opacket250custompayload) {
        DataInputStream datainputstream;
        OItemStack oitemstack;
        OItemStack oitemstack1;

        if ("MC|BEdit".equals(opacket250custompayload.a)) {
            try {
                datainputstream = new DataInputStream(new ByteArrayInputStream(opacket250custompayload.c));
                oitemstack = OPacket.c(datainputstream);
                if (!OItemWritableBook.a(oitemstack.p())) {
                    throw new IOException("Invalid book tag!");
                }

                oitemstack1 = this.d.bJ.g();
                if (oitemstack != null && oitemstack.c == OItem.bF.cj && oitemstack.c == oitemstack1.c) {
                    oitemstack1.a("pages", (ONBTBase) oitemstack.p().m("pages"));
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        } else if ("MC|BSign".equals(opacket250custompayload.a)) {
            try {
                datainputstream = new DataInputStream(new ByteArrayInputStream(opacket250custompayload.c));
                oitemstack = OPacket.c(datainputstream);
                if (!OItemEditableBook.a(oitemstack.p())) {
                    throw new IOException("Invalid book tag!");
                }

                oitemstack1 = this.d.bJ.g();
                if (oitemstack != null && oitemstack.c == OItem.bG.cj && oitemstack1.c == OItem.bF.cj) {
                    oitemstack1.a("author", (ONBTBase) (new ONBTTagString("author", this.d.bR)));
                    oitemstack1.a("title", (ONBTBase) (new ONBTTagString("title", oitemstack.p().i("title"))));
                    oitemstack1.a("pages", (ONBTBase) oitemstack.p().m("pages"));
                    oitemstack1.c = OItem.bG.cj;
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
                    OContainer ocontainer = this.d.bL;

                    if (ocontainer instanceof OContainerMerchant) {
                        ((OContainerMerchant) ocontainer).b(i);
                    }
                } catch (Exception exception2) {
                    exception2.printStackTrace();
                }
            } else {
                int j;

                if ("MC|AdvCdm".equals(opacket250custompayload.a)) {
                    if (!this.e.Z()) {
                        this.d.a(this.d.a("advMode.notEnabled", new Object[0]));
                    } else if (this.d.a(2, "") && this.d.cd.d) {
                        try {
                            datainputstream = new DataInputStream(new ByteArrayInputStream(opacket250custompayload.c));
                            i = datainputstream.readInt();
                            j = datainputstream.readInt();
                            int k = datainputstream.readInt();
                            String s = OPacket.a(datainputstream, 256);
                            OTileEntity otileentity = this.d.p.q(i, j, k);

                            if (otileentity != null && otileentity instanceof OTileEntityCommandBlock) {
                                ((OTileEntityCommandBlock) otileentity).b(s);
                                this.d.p.i(i, j, k);
                                this.d.a("Command set: " + s);
                            }
                        } catch (Exception exception3) {
                            exception3.printStackTrace();
                        }
                    } else {
                        this.d.a(this.d.a("advMode.notAllowed", new Object[0]));
                    }
                } else if ("MC|Beacon".equals(opacket250custompayload.a)) {
                    if (this.d.bL instanceof OContainerBeacon) {
                        try {
                            datainputstream = new DataInputStream(new ByteArrayInputStream(opacket250custompayload.c));
                            i = datainputstream.readInt();
                            j = datainputstream.readInt();
                            OContainerBeacon ocontainerbeacon = (OContainerBeacon) this.d.bL;
                            OSlot oslot = ocontainerbeacon.a(0);

                            if (oslot.d()) {
                                oslot.a(1);
                                OTileEntityBeacon otileentitybeacon = ocontainerbeacon.d();

                                otileentitybeacon.d(i);
                                otileentitybeacon.e(j);
                                otileentitybeacon.d();
                            }
                        } catch (Exception exception4) {
                            exception4.printStackTrace();
                        }
                    }
                } else if ("MC|ItemName".equals(opacket250custompayload.a) && this.d.bL instanceof OContainerRepair) {
                    OContainerRepair ocontainerrepair = (OContainerRepair) this.d.bL;

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
        if (this.d.bJ.g() != null) {
            return this.d.bJ.g().c;
        }
        return -1;
    }

    /**
     * Returns the player
     *
     * @return player
     */
    public Player getPlayer() {
        return this.d.getPlayer();
    }

    /**
     * Override player entity
     * @param player
     */
    public void setPlayer(OEntityPlayerMP oentityplayermp) {
        this.d = oentityplayermp;
    }

    /**
     * Override player entity
     * @param player
     */
    public void setPlayer(Player player) {
        this.d = player.getEntity();
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
            String newMsg = msg.substring(finalCut + 1);

            b(new OPacket3Chat(subCut));
            msg(newMsg);
        } else {
            b(new OPacket3Chat(msg));
        }
    }

}
