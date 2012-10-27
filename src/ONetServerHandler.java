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
    private OMinecraftServer d;
    private OEntityPlayerMP e;
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
        this.d = ominecraftserver;
        this.b = oinetworkmanager;
        oinetworkmanager.a((ONetHandler) this);
        this.e = oentityplayermp;
        oentityplayermp.a = this;
    }

    public void d() {
        this.h = false;
        ++this.f;
        this.d.b.a("packetflow");
        this.b.b();
        this.d.b.c("keepAlive");
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

        this.d.b.c("playerTick");
        if (!this.h && !this.e.j) {
            this.e.g();
            if (this.e.o == null) {
                this.e.b(this.o, this.p, this.q, this.e.z, this.e.A);
            }
        }

        this.d.b.b();
    }

    public void c(String s) {
        if (!this.c) {
            this.e.l();
            this.b(new OPacket255KickDisconnect(s));
            this.b.d();
<<<<<<<
            // CanaryMod - onPlayerDisconnect Hook
            HookParametersDisconnect hookResult = new HookParametersDisconnect(String.format(Colors.Yellow + "%s left the game.", this.e.bJ), s); // XXX

            hookResult = (HookParametersDisconnect) etc.getLoader().callHook(PluginLoader.Hook.PLAYER_DISCONNECT, this.e.getPlayer(), hookResult);
            if (!hookResult.isHidden()) {
                this.d.ab().a((OPacket) (new OPacket3Chat(hookResult.getLeaveMessage())));
            }

            this.d.ab().e(this.e);
|||||||
            this.d.ab().a((OPacket) (new OPacket3Chat("\u00A7e" + this.e.bJ + " left the game.")));
            this.d.ab().e(this.e);
=======
            this.d.ad().a((OPacket) (new OPacket3Chat("\u00A7e" + this.e.bT + " left the game.")));
            this.d.ad().e(this.e);
>>>>>>>
            this.c = true;
        }
    }

    public void a(OPacket10Flying opacket10flying) {
<<<<<<<
        OWorldServer oworldserver = this.d.getWorld(this.e.p.name, this.e.bK);
|||||||
        OWorldServer oworldserver = this.d.a(this.e.bK);
=======
        OWorldServer oworldserver = this.d.a(this.e.ap);
>>>>>>>

        this.h = true;
        if (!this.e.j) {
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

                if (this.e.o != null) {
                    float f = this.e.z;
                    float f1 = this.e.A;

                    this.e.o.V();
                    d1 = this.e.t;
                    d2 = this.e.u;
                    d3 = this.e.v;
                    double d5 = 0.0D;

                    d4 = 0.0D;
                    if (opacket10flying.i) {
                        f = opacket10flying.e;
                        f1 = opacket10flying.f;
                    }

                    if (opacket10flying.h && opacket10flying.b == -999.0D && opacket10flying.d == -999.0D) {
                        if (Math.abs(opacket10flying.a) > 1.0D || Math.abs(opacket10flying.c) > 1.0D) {
                            System.err.println(this.e.bT + " was caught trying to crash the server with an invalid position.");
                            this.c("Nope!");
                            return;
                        }

                        d5 = opacket10flying.a;
                        d4 = opacket10flying.c;
                    }

                    this.e.E = opacket10flying.g;
                    this.e.g();
                    this.e.d(d5, 0.0D, d4);
                    this.e.a(d1, d2, d3, f, f1);
                    this.e.w = d5;
                    this.e.y = d4;
                    if (this.e.o != null) {
                        oworldserver.b(this.e.o, true);
                    }

                    if (this.e.o != null) {
                        this.e.o.V();
                    }

                    this.d.ad().d(this.e);
                    this.o = this.e.t;
                    this.p = this.e.u;
                    this.q = this.e.v;
                    oworldserver.g(this.e);
                    return;
                }

                if (this.e.bt()) {
                    this.e.g();
                    this.e.a(this.o, this.p, this.q, this.e.z, this.e.A);
                    oworldserver.g(this.e);
                    return;
                }

                d0 = this.e.u;
                this.o = this.e.t;
                this.p = this.e.u;
                this.q = this.e.v;
                d1 = this.e.t;
                d2 = this.e.u;
                d3 = this.e.v;
                float f2 = this.e.z;
                float f3 = this.e.A;

                if (opacket10flying.h && opacket10flying.b == -999.0D && opacket10flying.d == -999.0D) {
                    opacket10flying.h = false;
                }

                if (opacket10flying.h) {
                    d1 = opacket10flying.a;
                    d2 = opacket10flying.b;
                    d3 = opacket10flying.c;
                    d4 = opacket10flying.d - opacket10flying.b;
                    if (!this.e.bt() && (d4 > 1.65D || d4 < 0.1D)) {
                        this.c("Illegal stance");
                        a.warning(this.e.bT + " had an illegal stance: " + d4);
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

                this.e.g();
                this.e.W = 0.0F;
                this.e.a(this.o, this.p, this.q, f2, f3);
                if (!this.r) {
                    return;
                }

                d4 = d1 - this.e.t;
                double d6 = d2 - this.e.u;
                double d7 = d3 - this.e.v;
                double d8 = Math.min(Math.abs(d4), Math.abs(this.e.w));
                double d9 = Math.min(Math.abs(d6), Math.abs(this.e.x));
                double d10 = Math.min(Math.abs(d7), Math.abs(this.e.y));
                double d11 = d8 * d8 + d9 * d9 + d10 * d10;

                if (d11 > 100.0D && (!this.d.I() || !this.d.H().equals(this.e.bT))) {
                    a.warning(this.e.bT + " moved too quickly! " + d4 + "," + d6 + "," + d7 + " (" + d8 + ", " + d9 + ", " + d10 + ")");
                    this.a(this.o, this.p, this.q, this.e.z, this.e.A);
                    return;
                }

                float f4 = 0.0625F;
                boolean flag = oworldserver.a(this.e, this.e.D.c().e((double) f4, (double) f4, (double) f4)).isEmpty();

                if (this.e.E && !opacket10flying.g && d6 > 0.0D) {
                    this.e.j(0.2F);
                }

                this.e.d(d4, d6, d7);
                this.e.E = opacket10flying.g;
                this.e.j(d4, d6, d7);
                double d12 = d6;

                d4 = d1 - this.e.t;
                d6 = d2 - this.e.u;
                if (d6 > -0.5D || d6 < 0.5D) {
                    d6 = 0.0D;
                }

                d7 = d3 - this.e.v;
                d11 = d4 * d4 + d6 * d6 + d7 * d7;
                boolean flag1 = false;

                if (d11 > 0.0625D && !this.e.bt() && !this.e.c.d()) {
                    flag1 = true;
                    a.warning(this.e.bT + " moved wrongly!");
                }

                this.e.a(d1, d2, d3, f2, f3);
                boolean flag2 = oworldserver.a(this.e, this.e.D.c().e((double) f4, (double) f4, (double) f4)).isEmpty();

                if (flag && (flag1 || !flag2) && !this.e.bt()) {
                    this.a(this.o, this.p, this.q, f2, f3);
                    return;
                }

                OAxisAlignedBB oaxisalignedbb = this.e.D.c().b((double) f4, (double) f4, (double) f4).a(0.0D, -0.55D, 0.0D);

                if (!this.d.Y() && !this.e.c.d() && !oworldserver.c(oaxisalignedbb)) {
                    if (d12 >= -0.03125D) {
                        ++this.g;
                        if (this.g > 80) {
                            a.warning(this.e.bT + " was kicked for floating too long!");
                            this.c("Flying is not enabled on this server");
                            return;
                        }
                    }
                } else {
                    this.g = 0;
                }

                this.e.E = opacket10flying.g;
                this.d.ad().d(this.e);
                this.e.b(this.e.u - d0, opacket10flying.g);
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
        to.dimension = this.e.bK;
        to.world = this.e.p.name;
        Player player = this.getPlayer();

        if ((Boolean) OEntity.manager.callHook(PluginLoader.Hook.TELEPORT, player, player.getLocation(), to)) {
            return;
        }

        this.r = false;
        this.o = d0;
        this.p = d1;
        this.q = d2;
        this.e.a(d0, d1, d2, f, f1);
        this.e.a.b(new OPacket13PlayerLookMove(d0, d1 + 1.6200000047683716D, d1, d2, f, f1, false));
    }

    // CanaryMod: Store x/y/z
    int x, y, z, type;

    public void a(OPacket14BlockDig opacket14blockdig) {
<<<<<<<
        OWorldServer oworldserver = this.d.getWorld(this.e.p.name, this.e.bK);
|||||||
        OWorldServer oworldserver = this.d.a(this.e.bK);
=======
        OWorldServer oworldserver = this.d.a(this.e.ap);
>>>>>>>

        if (opacket14blockdig.e == 4) {
            this.e.bN();
        } else if (opacket14blockdig.e == 5) {
            this.e.bK();
        } else {
<<<<<<<
            // CanaryMod: We allow admins and ops to dig!
            boolean flag = oworldserver.c = oworldserver.w.g != 0 || this.d.ab().e(this.e.bJ) || this.d.H() || this.getPlayer().isAdmin();
|||||||
            boolean flag = oworldserver.c = oworldserver.w.g != 0 || this.d.ab().e(this.e.bJ) || this.d.H();
=======
            boolean flag = oworldserver.v.h != 0 || this.d.ad().i().isEmpty() || this.d.ad().e(this.e.bT) || this.d.I();
>>>>>>>
            boolean flag1 = false;

            if (opacket14blockdig.e == 0) {
                flag1 = true;
            }

            if (opacket14blockdig.e == 2) {
                flag1 = true;
            }

            int i = opacket14blockdig.a;
            int j = opacket14blockdig.b;
            int k = opacket14blockdig.c;

            if (flag1) {
                double d0 = this.e.t - ((double) i + 0.5D);
                double d1 = this.e.u - ((double) j + 0.5D) + 1.5D;
                double d2 = this.e.v - ((double) k + 0.5D);
                double d3 = d0 * d0 + d1 * d1 + d2 * d2;

                if (d3 > 36.0D) {
                    return;
                }

                if (j >= this.d.ab()) {
                    return;
                }
            }

            OChunkCoordinates ochunkcoordinates = oworldserver.G();
            int l = OMathHelper.a(i - ochunkcoordinates.a);
            int i1 = OMathHelper.a(k - ochunkcoordinates.c);

            if (l > i1) {
                i1 = l;
            }

            // CanaryMod: the player
            Player player = this.getPlayer();

            if (opacket14blockdig.e == 0) {
                // CanaryMod: Start digging
                // No buildrights
                if (!player.canBuild()) {
                    return;
                }
                // CanaryMod: Custom spawn prot size
                if (i1 <= etc.getInstance().getSpawnProtectionSize() && !flag) {
                    this.e.a.b(new OPacket53BlockChange(i, j, k, oworldserver));
                } else {
                    // CanaryMod: Dig hooks
                    Block block = oworldserver.world.getBlockAt(i, j, k);

                    block.setStatus(0); // Started digging
                    x = block.getX();
                    y = block.getY();
                    z = block.getZ();
                    type = block.getType();
                    if (!(Boolean) OEntity.manager.callHook(PluginLoader.Hook.BLOCK_DESTROYED, player, block)) {
                        this.e.c.a(i, j, k, opacket14blockdig.d);
                    } else {
                        this.e.a.b(new OPacket53BlockChange(i, j, k, oworldserver));
                    }
                }
            } else if (opacket14blockdig.e == 2) {
                // CanaryMod: Break block
                Block block = oworldserver.world.getBlockAt(i, j, k);

                block.setStatus(2); // Block broken
                OEntity.manager.callHook(PluginLoader.Hook.BLOCK_DESTROYED, player, block);

                this.e.c.a(i, j, k);
                if (oworldserver.a(i, j, k) != 0) {
                    this.e.a.b(new OPacket53BlockChange(i, j, k, oworldserver));
                }
            } else if (opacket14blockdig.e == 1) {
                // CanaryMod: Stop digging
                Block block = oworldserver.world.getBlockAt(i, j, k);

                block.setStatus(1); // Stopped digging
                OEntity.manager.callHook(PluginLoader.Hook.BLOCK_DESTROYED, player, block);

                this.e.c.c(i, j, k);
                if (oworldserver.a(i, j, k) != 0) {
                    this.e.a.b(new OPacket53BlockChange(i, j, k, oworldserver));
                }
            } else if (opacket14blockdig.e == 3) {
                // CanaryMod: Send block update
                Block block = new Block(oworldserver.world, type, x, y, z);

                block.setStatus(3); // Send update for block
                OEntity.manager.callHook(PluginLoader.Hook.BLOCK_DESTROYED, player, block);

                double d4 = this.e.t - ((double) i + 0.5D);
                double d5 = this.e.u - ((double) j + 0.5D);
                double d6 = this.e.v - ((double) k + 0.5D);
                double d7 = d4 * d4 + d5 * d5 + d6 * d6;

                if (d7 < 256.0D) {
                    this.e.a.b(new OPacket53BlockChange(i, j, k, oworldserver));
                }
            }

            oworldserver.c = false;
        }
    }

    // CanaryMod: Store the blocks between blockPlaced packets
    Block lastRightClicked;

    public void a(OPacket15Place opacket15place) {
        OWorldServer oworldserver = this.d.getWorld(this.e.p.name, this.e.bK);
        OItemStack oitemstack = this.e.by.g();

        // CanaryMod: Store block data to call hooks
        // CanaryMod START
        Block blockClicked;
        Block blockPlaced = null;


        boolean flag = false;
        int i = opacket15place.d();
        int j = opacket15place.f();
        int k = opacket15place.g();
        int l = opacket15place.h();
        // We allow admins and ops to build!
        boolean flag1 = oworldserver.c = oworldserver.w.g != 0 || this.d.ab().e(this.e.bJ) || this.d.H() || this.getPlayer().isAdmin();

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

            this.e.c.itemUsed(this.e, oworldserver, oitemstack, blockPlaced, blockClicked);
        } else if (opacket15place.f() >= this.d.Z() - 1 && (opacket15place.h() == 1 || opacket15place.f() >= this.d.Z())) {
            this.e.a.b(new OPacket3Chat("\u00A77Height limit for building is " + this.d.Z()));
            flag = true;
        } else {
            OChunkCoordinates ochunkcoordinates = oworldserver.E();
            // CanaryMod : Fix stupid buggy spawn protection. (2 times)
            int i1 = OMathHelper.a((l == 4 ? i - 1 : (l == 5 ? (i + 1) : i)) - ochunkcoordinates.a);
            int j1 = OMathHelper.a((l == 2 ? k - 1 : (l == 3 ? (k + 1) : k)) - ochunkcoordinates.c);

            if (i1 > j1) {
                j1 = i1;
            }

            // CanaryMod: call BLOCK_RIGHTCLICKED
            Item item = (oitemstack != null) ? new Item(oitemstack) : new Item(Item.Type.Air);
            Player player = this.getPlayer();
            boolean cancelled = (Boolean) OEntity.manager.callHook(PluginLoader.Hook.BLOCK_RIGHTCLICKED, player, blockClicked, item);

            // CanaryMod: call original BLOCK_CREATED
            OEntity.manager.callHook(PluginLoader.Hook.BLOCK_CREATED, player, blockPlaced, blockClicked, item.getItemId());
            // CanaryMod: If we were building inside spawn, bail! (unless ops/admin)

            if (this.r && this.e.e((double) i + 0.5D, (double) j + 0.5D, (double) k + 0.5D) < 64.0D && (j1 > etc.getInstance().getSpawnProtectionSize() || flag1) && player.canBuild() && !cancelled) {
                this.e.c.a(this.e, oworldserver, oitemstack, i, j, k, l, opacket15place.j(), opacket15place.l(), opacket15place.m());
            } else {
                // CanaryMod: No point sending the client to update the blocks, you weren't allowed to place!
                this.e.a.b(new OPacket53BlockChange(i, j, k, oworldserver));
                oworldserver.c = false; // Same as last statement of this method!
                return;
            }

            flag = true;
        }

        if (flag) {
            this.e.a.b(new OPacket53BlockChange(i, j, k, oworldserver));
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

<<<<<<<
|||||||
            if (opacket14blockdig.e == 0) {
                if (i1 <= 16 && !flag) {
=======
            if (opacket14blockdig.e == 0) {
                if (i1 <= this.d.ak() && !flag) {
>>>>>>>
            this.e.a.b(new OPacket53BlockChange(i, j, k, oworldserver));
        }

        oitemstack = this.e.by.g();
        if (oitemstack != null && oitemstack.a == 0) {
            this.e.by.a[this.e.by.c] = null;
            oitemstack = null;
        }
<<<<<<<

        if (oitemstack == null || oitemstack.m() == 0) {
            this.e.h = true;
            this.e.by.a[this.e.by.c] = OItemStack.b(this.e.by.a[this.e.by.c]);
            OSlot oslot = this.e.bA.a((OIInventory) this.e.by, this.e.by.c);

            this.e.bA.b();
            this.e.h = false;
            if (!OItemStack.b(this.e.by.g(), opacket15place.i())) {
                this.b(new OPacket103SetSlot(this.e.bA.c, oslot.d, this.e.by.g()));
|||||||

            oworldserver.c = false;
=======
>>>>>>>
            }
        }

<<<<<<<
        oworldserver.c = false;
    }
|||||||
    public void a(OPacket15Place opacket15place) {
        OWorldServer oworldserver = this.d.a(this.e.bK);
        OItemStack oitemstack = this.e.by.g();
        boolean flag = false;
        int i = opacket15place.d();
        int j = opacket15place.f();
        int k = opacket15place.g();
        int l = opacket15place.h();
        boolean flag1 = oworldserver.c = oworldserver.w.g != 0 || this.d.ab().e(this.e.bJ) || this.d.H();
=======
    public void a(OPacket15Place opacket15place) {
        OWorldServer oworldserver = this.d.a(this.e.ap);
        OItemStack oitemstack = this.e.bK.g();
        boolean flag = false;
        int i = opacket15place.d();
        int j = opacket15place.f();
        int k = opacket15place.g();
        int l = opacket15place.h();
        boolean flag1 = oworldserver.v.h != 0 || this.d.ad().i().isEmpty() || this.d.ad().e(this.e.bT) || this.d.I();
>>>>>>>

    public void a(String s, Object[] aobject) {
        // CanaryMod: disconnect!
        OEntity.manager.callHook(PluginLoader.Hook.DISCONNECT, this.getPlayer());
        a.info(this.e.bJ + " lost connection: " + s);

<<<<<<<
        // CanaryMod - onPlayerDisconnect Hook
        HookParametersDisconnect hookResult = new HookParametersDisconnect(String.format(Colors.Yellow + "%s left the server.", this.e.bJ), s);
|||||||
            this.e.c.a(this.e, oworldserver, oitemstack);
        } else if (opacket15place.f() >= this.d.Z() - 1 && (opacket15place.h() == 1 || opacket15place.f() >= this.d.Z())) {
            this.e.a.b(new OPacket3Chat("\u00A77Height limit for building is " + this.d.Z()));
            flag = true;
        } else {
            OChunkCoordinates ochunkcoordinates = oworldserver.E();
            int i1 = OMathHelper.a(i - ochunkcoordinates.a);
            int j1 = OMathHelper.a(k - ochunkcoordinates.c);
=======
            this.e.c.a(this.e, oworldserver, oitemstack);
        } else if (opacket15place.f() >= this.d.ab() - 1 && (opacket15place.h() == 1 || opacket15place.f() >= this.d.ab())) {
            this.e.a.b(new OPacket3Chat("\u00A77Height limit for building is " + this.d.ab()));
            flag = true;
        } else {
            OChunkCoordinates ochunkcoordinates = oworldserver.G();
            int i1 = OMathHelper.a(i - ochunkcoordinates.a);
            int j1 = OMathHelper.a(k - ochunkcoordinates.c);
>>>>>>>

<<<<<<<
        hookResult = (HookParametersDisconnect) etc.getLoader().callHook(PluginLoader.Hook.PLAYER_DISCONNECT, this.e.getPlayer(), hookResult); // XXX
        if (!hookResult.isHidden()) {
            this.d.ab().a((OPacket) (new OPacket3Chat(hookResult.getLeaveMessage())));
|||||||
                j1 = i1;
            }

            if (this.r && this.e.e((double) i + 0.5D, (double) j + 0.5D, (double) k + 0.5D) < 64.0D && (j1 > 16 || flag1)) {
                this.e.c.a(this.e, oworldserver, oitemstack, i, j, k, l, opacket15place.j(), opacket15place.l(), opacket15place.m());
=======
                j1 = i1;
            }

            if (this.r && this.e.e((double) i + 0.5D, (double) j + 0.5D, (double) k + 0.5D) < 64.0D && (j1 > this.d.ak() || flag1)) {
                this.e.c.a(this.e, oworldserver, oitemstack, i, j, k, l, opacket15place.j(), opacket15place.l(), opacket15place.m());
>>>>>>>
        }
        this.d.ab().e(this.e);
        this.c = true;
        if (this.d.H() && this.e.bJ.equals(this.d.G())) {
            a.info("Stopping singleplayer server as player logged out");
            this.d.m();
        }
    }

<<<<<<<
    public void a(OPacket opacket) {
        a.warning(this.getClass() + " wasn\'t prepared to deal with a " + opacket.getClass());
        this.c("Protocol error, unexpected packet");
|||||||
        oitemstack = this.e.by.g();
        if (oitemstack != null && oitemstack.a == 0) {
            this.e.by.a[this.e.by.c] = null;
            oitemstack = null;
=======
        oitemstack = this.e.bK.g();
        if (oitemstack != null && oitemstack.a == 0) {
            this.e.bK.a[this.e.bK.c] = null;
            oitemstack = null;
>>>>>>>
    }

<<<<<<<
    public void b(OPacket opacket) {
        if (opacket instanceof OPacket3Chat) {
            OPacket3Chat opacket3chat = (OPacket3Chat) opacket;
            int i = this.e.v();
|||||||
        if (oitemstack == null || oitemstack.m() == 0) {
            this.e.h = true;
            this.e.by.a[this.e.by.c] = OItemStack.b(this.e.by.a[this.e.by.c]);
            OSlot oslot = this.e.bA.a((OIInventory) this.e.by, this.e.by.c);
=======
        if (oitemstack == null || oitemstack.m() == 0) {
            this.e.h = true;
            this.e.bK.a[this.e.bK.c] = OItemStack.b(this.e.bK.a[this.e.bK.c]);
            OSlot oslot = this.e.bM.a((OIInventory) this.e.bK, this.e.bK.c);
>>>>>>>

<<<<<<<
            if (i == 2) {
                return;
            }

            if (i == 1 && !opacket3chat.d()) {
                return;
|||||||
            this.e.bA.b();
            this.e.h = false;
            if (!OItemStack.b(this.e.by.g(), opacket15place.i())) {
                this.b(new OPacket103SetSlot(this.e.bA.c, oslot.d, this.e.by.g()));
=======
            this.e.bM.b();
            this.e.h = false;
            if (!OItemStack.b(this.e.bK.g(), opacket15place.i())) {
                this.b(new OPacket103SetSlot(this.e.bM.c, oslot.g, this.e.bK.g()));
>>>>>>>
            }
        }
<<<<<<<

        this.b.a(opacket);
|||||||

        oworldserver.c = false;
=======
>>>>>>>
    }

<<<<<<<
    public void a(OPacket16BlockItemSwitch opacket16blockitemswitch) {
        if (opacket16blockitemswitch.a >= 0 && opacket16blockitemswitch.a < OInventoryPlayer.h()) {
            this.e.by.c = opacket16blockitemswitch.a;
        } else {
            a.warning(this.e.bJ + " tried to set an invalid carried item");
        }
|||||||
    public void a(String s, Object[] aobject) {
        a.info(this.e.bJ + " lost connection: " + s);
        this.d.ab().a((OPacket) (new OPacket3Chat("\u00A7e" + this.e.bJ + " left the game.")));
        this.d.ab().e(this.e);
        this.c = true;
        if (this.d.H() && this.e.bJ.equals(this.d.G())) {
            a.info("Stopping singleplayer server as player logged out");
            this.d.m();
=======
    public void a(String s, Object[] aobject) {
        a.info(this.e.bT + " lost connection: " + s);
        this.d.ad().a((OPacket) (new OPacket3Chat("\u00A7e" + this.e.bT + " left the game.")));
        this.d.ad().e(this.e);
        this.c = true;
        if (this.d.I() && this.e.bT.equals(this.d.H())) {
            a.info("Stopping singleplayer server as player logged out");
            this.d.n();
>>>>>>>
    }

    public void a(OPacket3Chat opacket3chat) {
        String s = opacket3chat.b;

        // CanaryMod - disable native spam protection

        // CanaryMod: redirect chathandling to player class
        getPlayer().chat(s);
    }

<<<<<<<
    // Handled by PlayerCommands class
    private void d(String s) {
        //this.d.D().a(this.e, s);
|||||||

    public void a(OPacket16BlockItemSwitch opacket16blockitemswitch) {
        if (opacket16blockitemswitch.a >= 0 && opacket16blockitemswitch.a < OInventoryPlayer.h()) {
            this.e.by.c = opacket16blockitemswitch.a;
        } else {
            a.warning(this.e.bJ + " tried to set an invalid carried item");
        }
=======

    public void a(OPacket16BlockItemSwitch opacket16blockitemswitch) {
        if (opacket16blockitemswitch.a >= 0 && opacket16blockitemswitch.a < OInventoryPlayer.h()) {
            this.e.bK.c = opacket16blockitemswitch.a;
        } else {
            a.warning(this.e.bT + " tried to set an invalid carried item");
        }
>>>>>>>
    }

<<<<<<<
    public void a(OPacket18Animation opacket18animation) {
        if (opacket18animation.b == 1) {
            // CanaryMod: Swing the arm!
            if (!this.e.bH) { // Only call hook once per actual swing
                OEntity.manager.callHook(PluginLoader.Hook.ARM_SWING, getPlayer());
            }
|||||||
    public void a(OPacket3Chat opacket3chat) {
        if (this.e.v() == 2) {
            this.b(new OPacket3Chat("Cannot send chat message."));
        } else {
            String s = opacket3chat.b;
=======
    public void a(OPacket3Chat opacket3chat) {
        if (this.e.u() == 2) {
            this.b(new OPacket3Chat("Cannot send chat message."));
        } else {
            String s = opacket3chat.b;
>>>>>>>
            this.e.i();
        }
    }

    public void a(OPacket19EntityAction opacket19entityaction) {
        if (opacket19entityaction.b == 1) {
            this.e.a(true);
        } else if (opacket19entityaction.b == 2) {
            this.e.a(false);
        } else if (opacket19entityaction.b == 4) {
            this.e.b(true);
        } else if (opacket19entityaction.b == 5) {
            this.e.b(false);
        } else if (opacket19entityaction.b == 3) {
            this.e.a(false, true, true);
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
        OWorldServer oworldserver = this.d.getWorld(this.e.p.name, this.e.bK);
        OEntity oentity = oworldserver.a(opacket7useentity.b);

        if (oentity != null) {
            boolean flag = this.e.l(oentity);
            double d0 = 36.0D;

            if (!flag) {
                d0 = 9.0D;
            }

            if (this.e.e(oentity) < d0) {
                if (opacket7useentity.c == 0) {
                    this.e.m(oentity);
                } else if (opacket7useentity.c == 1) {
                    this.e.n(oentity);
                }
            }
        }
    }

    public void a(OPacket205ClientCommand opacket205clientcommand) {
        if (opacket205clientcommand.a == 1) {
            // CanaryMod: onPlayerRespawn
            OChunkCoordinates defaultSpawnCoords = this.e.bJ();

            if (defaultSpawnCoords == null) {
                defaultSpawnCoords = this.e.p.E();
            }

            // CanaryMod: check if your bed location exists before trying to respawn you there upon death
            OChunkCoordinates loc = this.e.bJ();
            Location respawnLocation = loc == null ? this.e.p.world.getSpawnLocation() :  new Location(e.p.world, loc.a, loc.b, loc.c);


            if (this.e.j) {
                etc.getLoader().callHook(PluginLoader.Hook.PLAYER_RESPAWN, this.getPlayer(), respawnLocation);
                this.e = this.d.ab().a(this.e, respawnLocation.dimension, true, respawnLocation);
            } else if (this.e.q().H().s()) {
                if (this.d.H() && this.e.bJ.equals(this.d.G())) {
                    this.e.a.c("You have died. Game over, man, it\'s game over!");
                    this.d.O();
                } else {
                    OBanEntry obanentry = new OBanEntry(this.e.bJ);

                    obanentry.b("Death in Hardcore");
                    this.d.ab().e().a(obanentry);
                    this.e.a.c("You have died. Game over, man, it\'s game over!");
                }
            } else {
<<<<<<<
                if (this.e.aN() > 0) {
|||||||
                    if (this.e.v() == 1) {
                        this.b(new OPacket3Chat("Cannot send chat message."));
=======
                    if (this.e.u() == 1) {
                        this.b(new OPacket3Chat("Cannot send chat message."));
>>>>>>>
                    return;
                }

<<<<<<<
                etc.getLoader().callHook(PluginLoader.Hook.PLAYER_RESPAWN, this.getPlayer(), respawnLocation);
                this.e = this.d.ab().a(this.e, respawnLocation.dimension, false, respawnLocation);
|||||||
                    s = "<" + this.e.bJ + "> " + s;
                    a.info(s);
                    this.d.ab().a((OPacket) (new OPacket3Chat(s, false)));
=======
                    s = "<" + this.e.bT + "> " + s;
                    a.info(s);
                    this.d.ad().a((OPacket) (new OPacket3Chat(s, false)));
>>>>>>>
            }
<<<<<<<
|||||||

                this.m += 20;
                if (this.m > 200 && !this.d.ab().e(this.e.bJ)) {
                    this.c("disconnect.spam");
=======

                this.m += 20;
                if (this.m > 200 && !this.d.ad().e(this.e.bT)) {
                    this.c("disconnect.spam");
>>>>>>>
        }
    }

<<<<<<<
    public boolean b() {
        return true;
|||||||
    private void d(String s) {
        this.d.D().a(this.e, s);
=======
    private void d(String s) {
        this.d.E().a(this.e, s);
>>>>>>>
    }

<<<<<<<
    public void a(OPacket9Respawn opacket9respawn) {}
|||||||
    public void a(OPacket18Animation opacket18animation) {
        if (opacket18animation.b == 1) {
            this.e.i();
        }
    }
=======
    public void a(OPacket18Animation opacket18animation) {
        if (opacket18animation.b == 1) {
            this.e.bE();
        }
    }
>>>>>>>

    public void a(OPacket101CloseWindow opacket101closewindow) {
        this.e.l();
    }

<<<<<<<
    public void a(OPacket102WindowClick opacket102windowclick) {
        if (this.e.bA.c == opacket102windowclick.a && this.e.bA.b(this.e)) {
            OItemStack oitemstack = this.e.bA.a(opacket102windowclick.b, opacket102windowclick.c, opacket102windowclick.f, this.e);
|||||||
    public void a(OPacket7UseEntity opacket7useentity) {
        OWorldServer oworldserver = this.d.a(this.e.bK);
        OEntity oentity = oworldserver.a(opacket7useentity.b);

        if (oentity != null) {
            boolean flag = this.e.l(oentity);
            double d0 = 36.0D;
=======
    public void a(OPacket7UseEntity opacket7useentity) {
        OWorldServer oworldserver = this.d.a(this.e.ap);
        OEntity oentity = oworldserver.a(opacket7useentity.b);

        if (oentity != null) {
            boolean flag = this.e.m(oentity);
            double d0 = 36.0D;
>>>>>>>

<<<<<<<
            if (OItemStack.b(opacket102windowclick.e, oitemstack)) {
                this.e.a.b(new OPacket106Transaction(opacket102windowclick.a, opacket102windowclick.d, true));
                this.e.h = true;
                this.e.bA.b();
                this.e.k();
                this.e.h = false;
            } else {
                this.s.a(this.e.bA.c, Short.valueOf(opacket102windowclick.d));
|||||||

            if (this.e.e(oentity) < d0) {
                if (opacket7useentity.c == 0) {
                    this.e.m(oentity);
                } else if (opacket7useentity.c == 1) {
                    this.e.n(oentity);
                }
            }
        }
=======

            if (this.e.e(oentity) < d0) {
                if (opacket7useentity.c == 0) {
                    this.e.o(oentity);
                } else if (opacket7useentity.c == 1) {
                    this.e.p(oentity);
                }
            }
        }
>>>>>>>
<<<<<<<
                this.e.a.b(new OPacket106Transaction(opacket102windowclick.a, opacket102windowclick.d, false));
                this.e.bA.a(this.e, false);
                ArrayList arraylist = new ArrayList();
|||||||
    public void a(OPacket205ClientCommand opacket205clientcommand) {
        if (opacket205clientcommand.a == 1) {
            if (this.e.j) {
                this.e = this.d.ab().a(this.e, 0, true);
            } else if (this.e.q().H().s()) {
                if (this.d.H() && this.e.bJ.equals(this.d.G())) {
                    this.e.a.c("You have died. Game over, man, it\'s game over!");
                    this.d.O();
                } else {
                    OBanEntry obanentry = new OBanEntry(this.e.bJ);
=======
    public void a(OPacket205ClientCommand opacket205clientcommand) {
        if (opacket205clientcommand.a == 1) {
            if (this.e.j) {
                this.e = this.d.ad().a(this.e, 0, true);
            } else if (this.e.p().J().t()) {
                if (this.d.I() && this.e.bT.equals(this.d.H())) {
                    this.e.a.c("You have died. Game over, man, it\'s game over!");
                    this.d.P();
                } else {
                    OBanEntry obanentry = new OBanEntry(this.e.bT);
>>>>>>>

<<<<<<<
                for (int i = 0; i < this.e.bA.b.size(); ++i) {
                    arraylist.add(((OSlot) this.e.bA.b.get(i)).c());
|||||||
                    obanentry.b("Death in Hardcore");
                    this.d.ab().e().a(obanentry);
                    this.e.a.c("You have died. Game over, man, it\'s game over!");
                }
            } else {
                if (this.e.aN() > 0) {
                    return;
=======
                    obanentry.b("Death in Hardcore");
                    this.d.ad().e().a(obanentry);
                    this.e.a.c("You have died. Game over, man, it\'s game over!");
                }
            } else {
                if (this.e.aT() > 0) {
                    return;
>>>>>>>
                }

<<<<<<<
                this.e.a(this.e.bA, arraylist);
|||||||
                this.e = this.d.ab().a(this.e, 0, false);
=======
                this.e = this.d.ad().a(this.e, 0, false);
>>>>>>>
            }
        }
    }
<<<<<<<
|||||||
    public void a(OPacket9Respawn opacket9respawn) {}

    public void a(OPacket101CloseWindow opacket101closewindow) {
        this.e.l();
    }

    public void a(OPacket102WindowClick opacket102windowclick) {
        if (this.e.bA.c == opacket102windowclick.a && this.e.bA.b(this.e)) {
            OItemStack oitemstack = this.e.bA.a(opacket102windowclick.b, opacket102windowclick.c, opacket102windowclick.f, this.e);

            if (OItemStack.b(opacket102windowclick.e, oitemstack)) {
                this.e.a.b(new OPacket106Transaction(opacket102windowclick.a, opacket102windowclick.d, true));
                this.e.h = true;
                this.e.bA.b();
                this.e.k();
                this.e.h = false;
            } else {
                this.s.a(this.e.bA.c, Short.valueOf(opacket102windowclick.d));
                this.e.a.b(new OPacket106Transaction(opacket102windowclick.a, opacket102windowclick.d, false));
                this.e.bA.a(this.e, false);
                ArrayList arraylist = new ArrayList();

                for (int i = 0; i < this.e.bA.b.size(); ++i) {
                    arraylist.add(((OSlot) this.e.bA.b.get(i)).c());
                }
=======
    public void a(OPacket9Respawn opacket9respawn) {}

    public void a(OPacket101CloseWindow opacket101closewindow) {
        this.e.k();
    }

    public void a(OPacket102WindowClick opacket102windowclick) {
        if (this.e.bM.c == opacket102windowclick.a && this.e.bM.b(this.e)) {
            OItemStack oitemstack = this.e.bM.a(opacket102windowclick.b, opacket102windowclick.c, opacket102windowclick.f, this.e);

            if (OItemStack.b(opacket102windowclick.e, oitemstack)) {
                this.e.a.b(new OPacket106Transaction(opacket102windowclick.a, opacket102windowclick.d, true));
                this.e.h = true;
                this.e.bM.b();
                this.e.j();
                this.e.h = false;
            } else {
                this.s.a(this.e.bM.c, Short.valueOf(opacket102windowclick.d));
                this.e.a.b(new OPacket106Transaction(opacket102windowclick.a, opacket102windowclick.d, false));
                this.e.bM.a(this.e, false);
                ArrayList arraylist = new ArrayList();

                for (int i = 0; i < this.e.bM.b.size(); ++i) {
                    arraylist.add(((OSlot) this.e.bM.b.get(i)).c());
                }
>>>>>>>

<<<<<<<
|||||||
                this.e.a(this.e.bA, arraylist);
            }
        }
    }

=======
                this.e.a(this.e.bM, arraylist);
            }
        }
    }

>>>>>>>
    public void a(OPacket108EnchantItem opacket108enchantitem) {
<<<<<<<
        if (this.e.bA.c == opacket108enchantitem.a && this.e.bA.b(this.e)) {
            this.e.bA.a((OEntityPlayer) this.e, opacket108enchantitem.b);
            this.e.bA.b();
        }
|||||||
        if (this.e.bA.c == opacket108enchantitem.a && this.e.bA.b(this.e)) {
            this.e.bA.a((OEntityPlayer) this.e, opacket108enchantitem.b);
            this.e.bA.b();
=======
        if (this.e.bM.c == opacket108enchantitem.a && this.e.bM.b(this.e)) {
            this.e.bM.a((OEntityPlayer) this.e, opacket108enchantitem.b);
            this.e.bM.b();
>>>>>>>
    }

    public void a(OPacket107CreativeSetSlot opacket107creativesetslot) {
        if (this.e.c.d()) {
            boolean flag = opacket107creativesetslot.a < 0;
            OItemStack oitemstack = opacket107creativesetslot.b;
            boolean flag1 = opacket107creativesetslot.a >= 1 && opacket107creativesetslot.a < 36 + OInventoryPlayer.h();
            boolean flag2 = oitemstack == null || oitemstack.c < OItem.e.length && oitemstack.c >= 0 && OItem.e[oitemstack.c] != null;
            boolean flag3 = oitemstack == null || oitemstack.j() >= 0 && oitemstack.j() >= 0 && oitemstack.a <= 64 && oitemstack.a > 0;

            if (flag1 && flag2 && flag3) {
                if (oitemstack == null) {
                    this.e.bz.a(opacket107creativesetslot.a, (OItemStack) null);
                } else {
                    this.e.bz.a(opacket107creativesetslot.a, oitemstack);
                }

<<<<<<<
                this.e.bz.a(this.e, true);
|||||||

            if (flag1 && flag2 && flag3) {
                if (oitemstack == null) {
                    this.e.bz.a(opacket107creativesetslot.a, (OItemStack) null);
                } else {
                    this.e.bz.a(opacket107creativesetslot.a, oitemstack);
                }

                this.e.bz.a(this.e, true);
=======

            if (flag1 && flag2 && flag3) {
                if (oitemstack == null) {
                    this.e.bL.a(opacket107creativesetslot.a, (OItemStack) null);
                } else {
                    this.e.bL.a(opacket107creativesetslot.a, oitemstack);
                }

                this.e.bL.a(this.e, true);
>>>>>>>
            } else if (flag && flag2 && flag3 && this.n < 200) {
                this.n += 20;
                OEntityItem oentityitem = this.e.c(oitemstack);

                if (oentityitem != null) {
                    oentityitem.c();
                }
            }
        }
    }

    public void a(OPacket106Transaction opacket106transaction) {
        Short oshort = (Short) this.s.a(this.e.bM.c);

        if (oshort != null && opacket106transaction.b == oshort.shortValue() && this.e.bM.c == opacket106transaction.a && !this.e.bM.b(this.e)) {
            this.e.bM.a(this.e, true);
        }
    }

    public void a(OPacket130UpdateSign opacket130updatesign) {
<<<<<<<
        OWorldServer oworldserver = this.d.getWorld(this.e.p.name, this.e.bK);
|||||||
        OWorldServer oworldserver = this.d.a(this.e.bK);
=======
        OWorldServer oworldserver = this.d.a(this.e.ap);
>>>>>>>

        if (oworldserver.e(opacket130updatesign.a, opacket130updatesign.b, opacket130updatesign.c)) {
            OTileEntity otileentity = oworldserver.p(opacket130updatesign.a, opacket130updatesign.b, opacket130updatesign.c);

            if (otileentity instanceof OTileEntitySign) {
                OTileEntitySign otileentitysign = (OTileEntitySign) otileentity;

                if (!otileentitysign.a()) {
                    this.d.g("Player " + this.e.bT + " just tried to change non-editable sign");
                    return;
                }
            }

            int i;
            int j;

            for (j = 0; j < 4; ++j) {
                boolean flag = true;

                // CanaryMod: Remove the char limit, for plugins.
                // if (opacket130updatesign.d[j].length() > 15) {
                // flag = false;
                // } else {
                for (i = 0; i < opacket130updatesign.d[j].length(); ++i) {
                    if (OChatAllowedCharacters.a.indexOf(opacket130updatesign.d[j].charAt(i)) < 0) {
                        flag = false;
                    }
                }
                // }

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
                oworldserver.h(j, k, i);
            }
        }
    }

    public void a(OPacket0KeepAlive opacket0keepalive) {
        if (opacket0keepalive.a == this.i) {
            int i = (int) (System.nanoTime() / 1000000L - this.j);

            this.e.i = (this.e.i * 3 + i) / 4;
        }
    }

    public boolean a() {
        return true;
    }

    public void a(OPacket202PlayerAbilities opacket202playerabilities) {
        this.e.cf.b = opacket202playerabilities.f() && this.e.cf.c;
    }

    public void a(OPacket203AutoComplete opacket203autocomplete) {
        String result = this.getPlayer().autoComplete(opacket203autocomplete.d());

        this.e.a.b(new OPacket203AutoComplete(result));
    }

    public void a(OPacket204ClientInfo opacket204clientinfo) {
        this.e.a(opacket204clientinfo);
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

                oitemstack1 = this.e.bK.g();
                if (oitemstack != null && oitemstack.c == OItem.bF.cf && oitemstack.c == oitemstack1.c) {
                    oitemstack1.d(oitemstack.p());
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

                oitemstack1 = this.e.bK.g();
                if (oitemstack != null && oitemstack.c == OItem.bG.cf && oitemstack1.c == OItem.bF.cf) {
                    oitemstack1.d(oitemstack.p());
                    oitemstack1.c = OItem.bG.cf;
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
                    OContainer ocontainer = this.e.bM;

                if (ocontainer instanceof OContainerMerchant) {
                        ((OContainerMerchant) ocontainer).b(i);
                }
            } catch (Exception exception2) {
                exception2.printStackTrace();
            }
            } else {
                int j;

                if ("MC|AdvCdm".equals(opacket250custompayload.a)) {
                    if (!this.d.Z()) {
                        this.e.a(this.e.a("advMode.notEnabled", new Object[0]));
                    } else if (this.e.a(2, "") && this.e.cf.d) {
                        try {
                            datainputstream = new DataInputStream(new ByteArrayInputStream(opacket250custompayload.c));
                            i = datainputstream.readInt();
                            j = datainputstream.readInt();
                            int k = datainputstream.readInt();
                            String s = OPacket.a(datainputstream, 256);
                            OTileEntity otileentity = this.e.p.p(i, j, k);

                            if (otileentity != null && otileentity instanceof OTileEntityCommandBlock) {
                                ((OTileEntityCommandBlock) otileentity).b(s);
                                this.e.p.h(i, j, k);
                                this.e.a("Command set: " + s);
                            }
                        } catch (Exception exception3) {
                            exception3.printStackTrace();
                        }
                    } else {
                        this.e.a(this.e.a("advMode.notAllowed", new Object[0]));
                    }
                } else if ("MC|Beacon".equals(opacket250custompayload.a)) {
                    if (this.e.bM instanceof OContainerBeacon) {
                        try {
                            datainputstream = new DataInputStream(new ByteArrayInputStream(opacket250custompayload.c));
                            i = datainputstream.readInt();
                            j = datainputstream.readInt();
                            OContainerBeacon ocontainerbeacon = (OContainerBeacon) this.e.bM;
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
                } else if ("MC|ItemName".equals(opacket250custompayload.a) && this.e.bM instanceof OContainerRepair) {
                    OContainerRepair ocontainerrepair = (OContainerRepair) this.e.bM;

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
        if (this.e.by.g() != null) {
            return this.e.by.g().c;
        }
        return -1;
    }

    /**
     * Returns the player
     *
     * @return player
     */
    public Player getPlayer() {
        return this.e.getPlayer();
    }

    /**
     * Override player entity
     * @param player
     */
    public void setPlayer(OEntityPlayerMP oentityplayermp) {
        this.e = oentityplayermp;
    }

    /**
     * Override player entity
     * @param player
     */
    public void setPlayer(Player player) {
        this.e = player.getEntity();
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
