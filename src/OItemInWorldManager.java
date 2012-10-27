import java.util.ArrayList;


public class OItemInWorldManager {

    public OWorld a;
    public OEntityPlayerMP b;
    private OEnumGameType c;
    private boolean d;
    private int e;
    private int f;
    private int g;
    private int h;
    private int i;
    private boolean j;
    private int k;
    private int l;
    private int m;
    private int n;
    private int o;

    public OItemInWorldManager(OWorld oworld) {
        this.c = OEnumGameType.a;
        this.o = -1;
        this.a = oworld;
    }

    public void a(OEnumGameType oenumgametype) {
        this.c = oenumgametype;
        oenumgametype.a(this.b.cf);
        this.b.o();
    }

    public OEnumGameType b() {
        return this.c;
    }

    public boolean d() {
        return this.c.d();
    }

    public void b(OEnumGameType oenumgametype) {
        if (this.c == OEnumGameType.a) {
            this.c = oenumgametype;
        }

        this.a(this.c);
    }

    public void a() {
        this.i = (int) (System.currentTimeMillis() / 50); // CanaryMod - block lag fix
        int i;
        float f;
        int j;

        if (this.j) {
            i = this.i - this.n;
            int k = this.a.a(this.k, this.l, this.m);

            if (k == 0) {
                this.j = false;
            } else {
                OBlock oblock = OBlock.p[k];

                f = oblock.a(this.b, this.b.p, this.k, this.l, this.m) * (float) (i + 1);
                j = (int) (f * 10.0F);
                if (j != this.o) {
                    this.a.g(this.b.k, this.k, this.l, this.m, j);
                    this.o = j;
                }

                if (f >= 1.0F) {
                    this.j = false;
                    this.b(this.k, this.l, this.m);
                }
            }
        } else if (this.d) {
            i = this.a.a(this.f, this.g, this.h);
            OBlock oblock1 = OBlock.p[i];

            if (oblock1 == null) {
                this.a.g(this.b.k, this.f, this.g, this.h, -1);
                this.o = -1;
                this.d = false;
            } else {
                int l = this.i - this.e;

                f = oblock1.a(this.b, this.b.p, this.f, this.g, this.h) * (float) (l + 1);
                j = (int) (f * 10.0F);
                if (j != this.o) {
                    this.a.g(this.b.k, this.f, this.g, this.h, j);
                    this.o = j;
                }
            }
        }
    }

    public void a(int i, int j, int k, int l) {
        if (!this.c.c() || this.b.f(i, j, k)) {
            if (this.d()) {
                if (!this.a.a((OEntityPlayer) null, i, j, k, l)) {
                    this.b(i, j, k);
                }
            } else {
                this.a.a(this.b, i, j, k, l);
                this.e = this.i;
                float f = 1.0F;
                int i1 = this.a.a(i, j, k);

                if (i1 > 0) {
                    OBlock.p[i1].a(this.a, i, j, k, (OEntityPlayer) this.b);
                    f = OBlock.p[i1].a(this.b, this.b.p, i, j, k);
                }

                if (i1 > 0 && f >= 1.0F) {
                    this.b(i, j, k);
                } else {
                    this.d = true;
                    this.f = i;
                    this.g = j;
                    this.h = k;
                    int j1 = (int) (f * 10.0F);

                    this.a.g(this.b.k, i, j, k, j1);
                    this.o = j1;
                }
            }
        }
    }

    public void a(int i, int j, int k) {
        if (i == this.f && j == this.g && k == this.h) {
            int l = this.i - this.e;
            int i1 = this.a.a(i, j, k);

            if (i1 != 0) {
                OBlock oblock = OBlock.p[i1];
                float f = oblock.a(this.b, this.b.p, i, j, k) * (float) (l + 1);

                if (f >= 0.7F) {
                    this.d = false;
                    this.a.g(this.b.k, i, j, k, -1);
                    this.b(i, j, k);
                } else if (!this.j) {
                    this.d = false;
                    this.j = true;
                    this.k = i;
                    this.l = j;
                    this.m = k;
                    this.n = this.e;
                }
            }
        }
    }

    public void c(int i, int j, int k) {
        this.d = false;
        this.a.g(this.b.k, this.f, this.g, this.h, -1);
    }

    private boolean d(int i, int j, int k) {
        OBlock oblock = OBlock.p[this.a.a(i, j, k)];
        int l = this.a.g(i, j, k);
        
        if (oblock != null) {
            oblock.a(this.a, i, j, k, l, this.b);
        }

        boolean flag = this.a.e(i, j, k, 0);

        if (oblock != null && flag) {
            oblock.c(this.a, i, j, k, l);
        }

        return flag;
    }
    
    public boolean b(int i, int j, int k) {
        if (this.c.c() && !this.b.f(i, j, k)) {
            return false;
        } else {
            // CanaryMod start - portal destroy
            Block block = this.b.getPlayer().getWorld().getBlockAt(i, j, k); //

            if (block.getType() == Block.Type.Obsidian.getType()) {
                boolean removeAll = true;
                ArrayList<Player> updatedPlayers = new ArrayList<Player>();

                for (Player player : etc.getServer().getPlayerList()) {
                    if (player.getWorld().equals(block.getWorld())) {
                        updatedPlayers.add(player);
                    }
                }
                int[][] blockOffsets = new int[][] { new int[] { 0, 1, 0}, new int[] { 1, 0, 0}, new int[] { -1, 0, 0}, new int[] { 0, 0, 1}, new int[] { 0, 0, -1}, new int[] { 0, -1, 0}};

                for (int i1 = 0; i1 < blockOffsets.length; i1 += 1) {
                    Block[][] blocks = getPortalBlocks(block.getWorld(), i + blockOffsets[i1][0], j + blockOffsets[i1][1], k + blockOffsets[i1][2]);

                    if (blocks != null) {
                        // CanaryMod hook onPortalDestroy
                        if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.PORTAL_DESTROY, (Object) blocks)) {
                            removeAll = false;
                        } else {
                            for (int j1 = 0; j1 < 3; j1 += 1) {
                                for (int k1 = 0; k1 < 2; k1 += 1) {
                                    // getChunkFromBlockCoords
                                    block.getWorld().getChunk(blocks[j1][k1]).getChunk().a(blocks[j1][k1].getX() & 15, blocks[j1][k1].getY(), blocks[j1][k1].getZ() & 15, 0, 0, false);
                                    for (Player player : updatedPlayers) {
                                        player.getUser().a.b(new OPacket53BlockChange(blocks[j1][k1].getX(), blocks[j1][k1].getY(), blocks[j1][k1].getZ(), block.getWorld().getWorld()));
                                    }
                                }
                            }
                        }
                    }
                }
                if (removeAll == false) {
                    return true;
                }
            }
            // CanaryMod hook - onBlockBreak
            if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.BLOCK_BROKEN, this.b.getPlayer(), block)) {
                return true;
            }
            // CanaryMod end

            int l = this.a.a(i, j, k);
            int i1 = this.a.g(i, j, k);

            this.a.a(this.b, 2001, i, j, k, l + (this.a.g(i, j, k) << 12));
            boolean flag = this.d(i, j, k);

            if (this.d()) {
                this.b.a.b(new OPacket53BlockChange(i, j, k, this.a));
            } else {
                OItemStack oitemstack = this.b.bP();
                boolean flag1 = this.b.b(OBlock.p[l]);

                if (oitemstack != null) {
                    oitemstack.a(this.a, l, i, j, k, this.b);
                    if (oitemstack.a == 0) {
                        this.b.bQ();
                    }
                }

                if (flag && flag1) {
                    OBlock.p[l].a(this.a, this.b, i, j, k, i1);
                }
            }

            return flag;
        }
    }

    public boolean a(OEntityPlayer oentityplayer, OWorld oworld, OItemStack oitemstack) {
        int i = oitemstack.a;
        int j = oitemstack.j();
        OItemStack oitemstack1 = oitemstack.a(oworld, oentityplayer);

        if (oitemstack1 == oitemstack && (oitemstack1 == null || oitemstack1.a == i && oitemstack1.m() <= 0 && oitemstack1.j() == j)) {
            return false;
        } else {
            oentityplayer.bK.a[oentityplayer.bK.c] = oitemstack1;
            if (this.d()) {
                oitemstack1.a = i;
                if (oitemstack1.f()) {
                oitemstack1.b(j);
            }
            }

            if (oitemstack1.a == 0) {
                oentityplayer.bK.a[oentityplayer.bK.c] = null;
            }

            if (!oentityplayer.bI()) {
                ((OEntityPlayerMP) oentityplayer).a(oentityplayer.bL);
            }

            return true;
        }
    }
    
    /**
     * Called when a player right-click air with an item in hand. We intercept
     * it.
     * 
     * @param player
     * @param world
     * @param item
     * @param blockToPlace
     * @param blockClicked
     * @return
     */
    public boolean itemUsed(OEntityPlayer oentityplayer, OWorld oworld, OItemStack oitemstack, Block block, Block block1) {
        // CanaryMod: only call this hook if we're not using buckets/signs
        if (oitemstack != null) {
            if (oitemstack.a > 0 && oitemstack.c != Item.Type.Sign.getId() && oitemstack.c != Item.Type.Bucket.getId() && oitemstack.c != Item.Type.WaterBucket.getId() && oitemstack.c != Item.Type.LavaBucket.getId() && oitemstack.c != Item.Type.MilkBucket.getId()) {
                if (oentityplayer instanceof OEntityPlayerMP && (Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, ((OEntityPlayerMP) oentityplayer).getPlayer(), block, block1, new Item(oitemstack))) {
                    return false;
                }
            }
        }
        return this.a(oentityplayer, oworld, oitemstack);
    }

    public boolean a(OEntityPlayer oentityplayer, OWorld oworld, OItemStack oitemstack, int i, int j, int k, int l, float f, float f1, float f2) {
        int i1 = oworld.a(i, j, k);

        if (i1 > 0 && OBlock.p[i1].a(oworld, i, j, k, oentityplayer, l, f, f1, f2)) {
            return true;
        } else if (oitemstack == null) {
            return false;
        } else if (this.d()) {
            int j1 = oitemstack.j();
            int k1 = oitemstack.a;
            boolean flag = oitemstack.a(oentityplayer, oworld, i, j, k, l, f, f1, f2);

            oitemstack.b(j1);
            oitemstack.a = k1;
            return flag;
        } else {
            return oitemstack.a(oentityplayer, oworld, i, j, k, l, f, f1, f2);
        }
    }

    public void a(OWorldServer oworldserver) {
        this.a = oworldserver;
    }
    
    // CanaryMod start - getPortalBlocks
    private Block[][] getPortalBlocks(World world, int i, int j, int k) {
        int portalId = Block.Type.Portal.getType();

        if (world.getBlockIdAt(i, j, k) == portalId) {
            // These will be equal 1 if the portal is defined on their axis
            // and 0 if not.
            int portalXOffset = (world.getBlockIdAt(i - 1, j, k) == portalId || world.getBlockIdAt(i + 1, j, k) == portalId) ? 1 : 0;
            int portalZOffset = (world.getBlockIdAt(i, j, k - 1) == portalId || world.getBlockIdAt(i, j, k + 1) == portalId) ? 1 : 0;

            // If the portal is either x aligned or z aligned but not both
            // (has neighbor portal in x or z plane but not both)
            if (portalXOffset != portalZOffset) {
                // Get the edge of the portal.
                int portalX = i - ((world.getBlockIdAt(i - 1, j, k) == portalId) ? 1 : 0);
                int portalZ = k - ((world.getBlockIdAt(i, j, k - 1) == portalId) ? 1 : 0);
                int portalY = j;

                while (world.getBlockIdAt(portalX, ++portalY, portalZ) == portalId) {
                    ;
                }
                portalY -= 1;
                // Scan the portal and see if its still all there (2x3
                // formation)
                boolean completePortal = true;
                Block[][] portalBlocks = new Block[3][2];

                for (int i1 = 0; i1 < 3 && completePortal == true; i1 += 1) {
                    for (int j1 = 0; j1 < 2 && completePortal == true; j1 += 1) {
                        portalBlocks[i1][j1] = world.getBlockAt(portalX + j1 * portalXOffset, portalY - i1, portalZ + j1 * portalZOffset);
                        if (portalBlocks[i1][j1].getType() != portalId) {
                            completePortal = false;
                        }
                    }
                }
                if (completePortal == true) {
                    return portalBlocks;
                }
            }
        }
        return null;
    }
    // CanaryMod end
}
