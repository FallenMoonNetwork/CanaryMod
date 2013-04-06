import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;

public class OChunk {

    public static boolean a;
    private OExtendedBlockStorage[] r;
    private byte[] s;
    public int[] b;
    public boolean[] c;
    public boolean d;
    public OWorld e;
    public int[] f;
    public final int g;
    public final int h;
    private boolean t;
    public Map i;
    public List[] j;
    public boolean k;
    public boolean l;
    public boolean m;
    public long n;
    public boolean o;
    public int p;
    private int u;
    boolean q;
    // CanaryMod
    public final Chunk chunk = new Chunk(this);

    public OChunk(OWorld oworld, int i, int j) {
        this.r = new OExtendedBlockStorage[16];
        this.s = new byte[256];
        this.b = new int[256];
        this.c = new boolean[256];
        this.t = false;
        this.i = new HashMap();
        this.k = false;
        this.l = false;
        this.m = false;
        this.n = 0L;
        this.o = false;
        this.p = 0;
        this.u = 4096;
        this.q = false;
        this.j = new List[16];
        this.e = oworld;
        this.g = i;
        this.h = j;
        this.f = new int[256];

        for (int k = 0; k < this.j.length; ++k) {
            this.j[k] = new ArrayList();
        }

        Arrays.fill(this.b, -999);
        Arrays.fill(this.s, (byte) -1);
    }

    public OChunk(OWorld oworld, byte[] abyte, int i, int j) {
        this(oworld, i, j);
        int k = abyte.length / 256;

        for (int l = 0; l < 16; ++l) {
            for (int i1 = 0; i1 < 16; ++i1) {
                for (int j1 = 0; j1 < k; ++j1) {
                    byte b0 = abyte[l << 11 | i1 << 7 | j1];

                    if (b0 != 0) {
                        int k1 = j1 >> 4;

                        if (this.r[k1] == null) {
                            this.r[k1] = new OExtendedBlockStorage(k1 << 4, !oworld.t.f);
                        }

                        this.r[k1].a(l, j1 & 15, i1, b0);
                    }
                }
            }
        }
    }

    public boolean a(int i, int j) {
        return i == this.g && j == this.h;
    }

    public int b(int i, int j) {
        return this.f[j << 4 | i];
    }

    public int h() {
        for (int i = this.r.length - 1; i >= 0; --i) {
            if (this.r[i] != null) {
                return this.r[i].d();
            }
        }

        return 0;
    }

    public OExtendedBlockStorage[] i() {
        return this.r;
    }

    public void b() {
        int i = this.h();

        this.p = Integer.MAX_VALUE;

        int j;
        int k;

        for (j = 0; j < 16; ++j) {
            k = 0;

            while (k < 16) {
                this.b[j + (k << 4)] = -999;
                int l = i + 16 - 1;

                while (true) {
                    if (l > 0) {
                        if (this.b(j, l - 1, k) == 0) {
                            --l;
                            continue;
                        }

                        this.f[k << 4 | j] = l;
                        if (l < this.p) {
                            this.p = l;
                        }
                    }

                    if (!this.e.t.f) {
                        l = 15;
                        int i1 = i + 16 - 1;

                        do {
                            l -= this.b(j, i1, k);
                            if (l > 0) {
                                OExtendedBlockStorage oextendedblockstorage = this.r[i1 >> 4];

                                if (oextendedblockstorage != null) {
                                    oextendedblockstorage.c(j, i1 & 15, k, l);
                                    this.e.p((this.g << 4) + j, i1, (this.h << 4) + k);
                                }
                            }

                            --i1;
                        } while (i1 > 0 && l > 0);
                    }

                    ++k;
                    break;
                }
            }
        }

        this.l = true;

        for (j = 0; j < 16; ++j) {
            for (k = 0; k < 16; ++k) {
                this.e(j, k);
            }
        }
    }

    private void e(int i, int j) {
        this.c[i + j * 16] = true;
        this.t = true;
    }

    private void q() {
        this.e.C.a("recheckGaps");
        if (this.e.b(this.g * 16 + 8, 0, this.h * 16 + 8, 16)) {
            for (int i = 0; i < 16; ++i) {
                for (int j = 0; j < 16; ++j) {
                    if (this.c[i + j * 16]) {
                        this.c[i + j * 16] = false;
                        int k = this.b(i, j);
                        int l = this.g * 16 + i;
                        int i1 = this.h * 16 + j;
                        int j1 = this.e.g(l - 1, i1);
                        int k1 = this.e.g(l + 1, i1);
                        int l1 = this.e.g(l, i1 - 1);
                        int i2 = this.e.g(l, i1 + 1);

                        if (k1 < j1) {
                            j1 = k1;
                        }

                        if (l1 < j1) {
                            j1 = l1;
                        }

                        if (i2 < j1) {
                            j1 = i2;
                        }

                        this.g(l, i1, j1);
                        this.g(l - 1, i1, k);
                        this.g(l + 1, i1, k);
                        this.g(l, i1 - 1, k);
                        this.g(l, i1 + 1, k);
                    }
                }
            }

            this.t = false;
        }

        this.e.C.b();
    }

    private void g(int i, int j, int k) {
        int l = this.e.f(i, j);

        if (l > k) {
            this.d(i, j, k, l + 1);
        } else if (l < k) {
            this.d(i, j, l, k + 1);
        }
    }

    private void d(int i, int j, int k, int l) {
        if (l > k && this.e.b(i, 0, j, 16)) {
            for (int i1 = k; i1 < l; ++i1) {
                this.e.c(OEnumSkyBlock.a, i, i1, j);
            }

            this.l = true;
        }
    }

    private void h(int i, int j, int k) {
        int l = this.f[k << 4 | i] & 255;
        int i1 = l;

        if (j > l) {
            i1 = j;
        }

        while (i1 > 0 && this.b(i, i1 - 1, k) == 0) {
            --i1;
        }

        if (i1 != l) {
            this.e.e(i + this.g * 16, k + this.h * 16, i1, l);
            this.f[k << 4 | i] = i1;
            int j1 = this.g * 16 + i;
            int k1 = this.h * 16 + k;
            int l1;
            int i2;

            if (!this.e.t.f) {
                OExtendedBlockStorage oextendedblockstorage;

                if (i1 < l) {
                    for (l1 = i1; l1 < l; ++l1) {
                        oextendedblockstorage = this.r[l1 >> 4];
                        if (oextendedblockstorage != null) {
                            oextendedblockstorage.c(i, l1 & 15, k, 15);
                            this.e.p((this.g << 4) + i, l1, (this.h << 4) + k);
                        }
                    }
                } else {
                    for (l1 = l; l1 < i1; ++l1) {
                        // CanaryMod start: Catch corrupt index info
                        if (l1 >> 4 < 0 || l1 >> 4 >= 16) {
                            this.e.W().b("Invalid chunk info array index: {0}", l1 >> 4);
                            this.e.W().b("l: {0}, i1: {1}", l, i1);
                            this.e.W().b("Chunk location: {0}, {1}", j1, k1);
                            l1 = 0;
                        } // CanaryMod end
                        oextendedblockstorage = this.r[l1 >> 4];
                        if (oextendedblockstorage != null) {
                            oextendedblockstorage.c(i, l1 & 15, k, 0);
                            this.e.p((this.g << 4) + i, l1, (this.h << 4) + k);
                        }
                    }
                }

                l1 = 15;

                while (i1 > 0 && l1 > 0) {
                    --i1;
                    i2 = this.b(i, i1, k);
                    if (i2 == 0) {
                        i2 = 1;
                    }

                    l1 -= i2;
                    if (l1 < 0) {
                        l1 = 0;
                    }

                    OExtendedBlockStorage oextendedblockstorage1 = this.r[i1 >> 4];

                    if (oextendedblockstorage1 != null) {
                        oextendedblockstorage1.c(i, i1 & 15, k, l1);
                    }
                }
            }

            l1 = this.f[k << 4 | i];
            i2 = l;
            int j2 = l1;

            if (l1 < l) {
                i2 = l1;
                j2 = l;
            }

            if (l1 < this.p) {
                this.p = l1;
            }

            if (!this.e.t.f) {
                this.d(j1 - 1, k1, i2, j2);
                this.d(j1 + 1, k1, i2, j2);
                this.d(j1, k1 - 1, i2, j2);
                this.d(j1, k1 + 1, i2, j2);
                this.d(j1, k1, i2, j2);
            }

            this.l = true;
        }
    }

    public int b(int i, int j, int k) {
        return OBlock.t[this.a(i, j, k)];
    }

    public int a(int i, int j, int k) {
        if (j >> 4 >= this.r.length) {
            return 0;
        } else {
            OExtendedBlockStorage oextendedblockstorage = this.r[j >> 4];

            return oextendedblockstorage != null ? oextendedblockstorage.a(i, j & 15, k) : 0;
        }
    }

    public int c(int i, int j, int k) {
        if (j >> 4 >= this.r.length) {
            return 0;
        } else {
            OExtendedBlockStorage oextendedblockstorage = this.r[j >> 4];

            return oextendedblockstorage != null ? oextendedblockstorage.b(i, j & 15, k) : 0;
        }
    }

    public boolean a(int i, int j, int k, int l, int i1) {
        // CanaryMod: Call our own version
        return a(i, j, k, l, i1, true);
    }

    public boolean a(int i, int j, int k, int l, int i1, boolean checkPortal) {
        int j1 = k << 4 | i;

        if (j >= this.b[j1] - 1) {
            this.b[j1] = -999;
        }

        int k1 = this.f[j1];
        int l1 = this.a(i, j, k);
        int i2 = this.c(i, j, k);

        if (l1 == l && i2 == i1) {
            return false;
        } else {

            int portalPointX = this.g * 16 + i;
            int portalPointZ = this.h * 16 + k;

            if (checkPortal == true) {
                int portalPointY = j;
                // CanaryMod check if removed block is portal block
                int portalId = Block.Type.Portal.getType();

                if (chunk.getWorld().getBlockIdAt(portalPointX, portalPointY, portalPointZ) == portalId) {
                    // These will be equal 1 if the portal is defined on their axis and 0 if not.
                    int portalXOffset = (chunk.getWorld().getBlockIdAt(portalPointX - 1, portalPointY, portalPointZ) == portalId || chunk.getWorld().getBlockIdAt(portalPointX + 1, portalPointY, portalPointZ) == portalId) ? 1 : 0;
                    int portalZOffset = (chunk.getWorld().getBlockIdAt(portalPointX, portalPointY, portalPointZ - 1) == portalId || chunk.getWorld().getBlockIdAt(portalPointX, portalPointY, portalPointZ + 1) == portalId) ? 1 : 0;

                    // If the portal is either x aligned or z aligned but not both (has neighbor portal in x or z plane but not both)
                    if (portalXOffset != portalZOffset) {
                        // Get the edge of the portal.
                        int portalX = portalPointX - ((chunk.getWorld().getBlockIdAt(portalPointX - 1, portalPointY, portalPointZ) == portalId) ? 1 : 0);
                        int portalZ = portalPointZ - ((chunk.getWorld().getBlockIdAt(portalPointX, portalPointY, portalPointZ - 1) == portalId) ? 1 : 0);
                        int portalY = j;

                        while (chunk.getWorld().getBlockIdAt(portalX, ++portalY, portalZ) == portalId) {
                            ;
                        }
                        portalY -= 1;
                        // Scan the portal and see if its still all there (2x3 formation)
                        boolean completePortal = true;
                        Block[][] portalBlocks = new Block[3][2];

                        for (int i3 = 0; i3 < 3 && completePortal == true; i3 += 1) {
                            for (int j3 = 0; j3 < 2 && completePortal == true; j3 += 1) {
                                portalBlocks[i3][j3] = chunk.getWorld().getBlockAt(portalX + j3 * portalXOffset, portalY - i3, portalZ + j3 * portalZOffset);
                                if (portalBlocks[i3][j3].getType() != portalId) {
                                    completePortal = false;
                                }
                            }
                        }
                        if (completePortal == true) {
                            // CanaryMod hook onPortalDestroy
                            if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.PORTAL_DESTROY, (Object) portalBlocks)) {
                                // Hook returned true = don't destroy the portal.
                                // in that case we need to reconstruct the portal's frame to make the portal valid.
                                // Problem is we don't want to reconstruct it right away because more blocks might be deleted (for example on explosion).
                                // In order to avoid spamming the hook for each destroyed block, I'm queuing the reconstruction of the portal instead.
                                etc.getServer().addToServerQueue(new PortalReconstructJob(chunk.getWorld(), portalX, portalY, portalZ, (portalXOffset == 1)));
                            }
                        }
                    }
                }
            }

            OExtendedBlockStorage oextendedblockstorage = this.r[j >> 4];
            boolean flag = false;

            if (oextendedblockstorage == null) {
                if (l == 0) {
                    return false;
                }

                oextendedblockstorage = this.r[j >> 4] = new OExtendedBlockStorage(j >> 4 << 4, !this.e.t.f);
                flag = j >= k1;
            }

            int j2 = this.g * 16 + i;
            int k2 = this.h * 16 + k;

            if (l1 != 0 && !this.e.I) {
                OBlock.r[l1].l(this.e, j2, j, k2, i2);
            }

            oextendedblockstorage.a(i, j & 15, k, l);
            if (l1 != 0) {
                if (!this.e.I) {
                    OBlock.r[l1].a(this.e, j2, j, k2, l1, i2);
                } else if (OBlock.r[l1] instanceof OITileEntityProvider && l1 != l) {
                    this.e.s(j2, j, k2);
                }
            }

            if (oextendedblockstorage.a(i, j & 15, k) != l) {
                return false;
            } else {
                oextendedblockstorage.b(i, j & 15, k, i1);
                if (flag) {
                    this.b();
                } else {
                    if (OBlock.t[l & 4095] > 0) {
                        if (j >= k1) {
                            this.h(i, j + 1, k);
                        }
                    } else if (j == k1 - 1) {
                        this.h(i, j, k);
                    }

                    this.e(i, k);
                }

                OTileEntity otileentity;

                if (l != 0) {
                    if (!this.e.I) {
                        OBlock.r[l].a(this.e, j2, j, k2);
                    }

                    if (OBlock.r[l] instanceof OITileEntityProvider) {
                        otileentity = this.e(i, j, k);
                        if (otileentity == null) {
                            otileentity = ((OITileEntityProvider) OBlock.r[l]).b(this.e);
                            this.e.a(j2, j, k2, otileentity);
                        }

                        if (otileentity != null) {
                            otileentity.i();
                        }
                    }
                } else if (l1 > 0 && OBlock.r[l1] instanceof OITileEntityProvider) {
                    otileentity = this.e(i, j, k);
                    if (otileentity != null) {
                        otileentity.i();
                    }
                }

                this.l = true;
                return true;
            }
        }
    }

    public boolean b(int i, int j, int k, int l) {
        // CanaryMod: call our own version
        return b(i, j, k, l, true);
    }

    public boolean b(int i, int j, int k, int l, boolean checkPortal) {
        OExtendedBlockStorage oextendedblockstorage = this.r[j >> 4];

        if (oextendedblockstorage == null) {
            return false;
        } else {
            if (checkPortal == true) {
                int portalPointX = this.g * 16 + i;
                int portalPointY = j;
                int portalPointZ = this.h * 16 + k;
                // CanaryMod check if removed block is portal block
                int portalId = Block.Type.Portal.getType();

                if (chunk.getWorld().getBlockIdAt(portalPointX, portalPointY, portalPointZ) == portalId) {
                    // These will be equal 1 if the portal is defined on theirn axis and 0 if not.
                    int portalXOffset = (chunk.getWorld().getBlockIdAt(portalPointX - 1, portalPointY, portalPointZ) == portalId || chunk.getWorld().getBlockIdAt(portalPointX + 1, portalPointY, portalPointZ) == portalId) ? 1 : 0;
                    int portalZOffset = (chunk.getWorld().getBlockIdAt(portalPointX, portalPointY, portalPointZ - 1) == portalId || chunk.getWorld().getBlockIdAt(portalPointX, portalPointY, portalPointZ + 1) == portalId) ? 1 : 0;

                    // If the portal is either x aligned or z aligned but not both (has neighbor portal in x or z plane but not both)
                    if (portalXOffset != portalZOffset) {
                        // Get the edge of the portal.
                        int portalX = portalPointX - ((chunk.getWorld().getBlockIdAt(portalPointX - 1, portalPointY, portalPointZ) == portalId) ? 1 : 0);
                        int portalZ = portalPointZ - ((chunk.getWorld().getBlockIdAt(portalPointX, portalPointY, portalPointZ - 1) == portalId) ? 1 : 0);
                        int portalY = j;

                        while (chunk.getWorld().getBlockIdAt(portalX, ++portalY, portalZ) == portalId) {
                            ;
                        }
                        portalY -= 1;
                        // Scan the portal and see if its still all there (2x3 formation)
                        boolean completePortal = true;
                        Block[][] portalBlocks = new Block[3][2];

                        for (int i2 = 0; i2 < 3 && completePortal == true; i2 += 1) {
                            for (int j2 = 0; j2 < 2 && completePortal == true; j2 += 1) {
                                portalBlocks[i2][j2] = chunk.getWorld().getBlockAt(portalX + j2 * portalXOffset, portalY - i2, portalZ + j2 * portalZOffset);
                                if (portalBlocks[i2][j2].getType() != portalId) {
                                    completePortal = false;
                                }
                            }
                        }
                        if (completePortal == true) {
                            // CanaryMod hook onPortalDestroy
                            if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.PORTAL_DESTROY, (Object) portalBlocks)) {
                                // Hook returned true = don't destroy the portal.
                                // in that case we need to reconstruct the portal's frame to make the portal valid.
                                // Problem is we don't want to reconstruct it right away because more blocks might be deleted (for example on explosion).
                                // In order to avoid spamming the hook for each destroyed block, I'm queuing the reconstruction of the portal instead.
                                etc.getServer().addToServerQueue(new PortalReconstructJob(chunk.getWorld(), portalX, portalY, portalZ, (portalXOffset == 1)));
                            }
                        }
                    }
                }
            }

            int i1 = oextendedblockstorage.b(i, j & 15, k);

            if (i1 == l) {
                return false;
            } else {
                this.l = true;
                oextendedblockstorage.b(i, j & 15, k, l);
                int j1 = oextendedblockstorage.a(i, j & 15, k);

                if (j1 > 0 && OBlock.r[j1] instanceof OITileEntityProvider) {
                    OTileEntity otileentity = this.e(i, j, k);

                    if (otileentity != null) {
                        otileentity.i();
                        otileentity.p = l;
                    }
                }

                return true;
            }
        }
    }

    public int a(OEnumSkyBlock oenumskyblock, int i, int j, int k) {
        OExtendedBlockStorage oextendedblockstorage = this.r[j >> 4];

        return oextendedblockstorage == null ? (this.d(i, j, k) ? oenumskyblock.c : 0) : (oenumskyblock == OEnumSkyBlock.a ? (this.e.t.f ? 0 : oextendedblockstorage.c(i, j & 15, k)) : (oenumskyblock == OEnumSkyBlock.b ? oextendedblockstorage.d(i, j & 15, k) : oenumskyblock.c));
    }

    public void a(OEnumSkyBlock oenumskyblock, int i, int j, int k, int l) {
        OExtendedBlockStorage oextendedblockstorage = this.r[j >> 4];

        if (oextendedblockstorage == null) {
            oextendedblockstorage = this.r[j >> 4] = new OExtendedBlockStorage(j >> 4 << 4, !this.e.t.f);
            this.b();
        }

        this.l = true;
        if (oenumskyblock == OEnumSkyBlock.a) {
            if (!this.e.t.f) {
                oextendedblockstorage.c(i, j & 15, k, l);
            }
        } else if (oenumskyblock == OEnumSkyBlock.b) {
            oextendedblockstorage.d(i, j & 15, k, l);
        }
    }

    public int c(int i, int j, int k, int l) {
        OExtendedBlockStorage oextendedblockstorage = this.r[j >> 4];

        if (oextendedblockstorage == null) {
            return !this.e.t.f && l < OEnumSkyBlock.a.c ? OEnumSkyBlock.a.c - l : 0;
        } else {
            int i1 = this.e.t.f ? 0 : oextendedblockstorage.c(i, j & 15, k);

            if (i1 > 0) {
                a = true;
            }

            i1 -= l;
            int j1 = oextendedblockstorage.d(i, j & 15, k);

            if (j1 > i1) {
                i1 = j1;
            }

            return i1;
        }
    }

    public void a(OEntity oentity) {
        this.m = true;
        int i = OMathHelper.c(oentity.u / 16.0D);
        int j = OMathHelper.c(oentity.w / 16.0D);

        if (i != this.g || j != this.h) {
            this.e.W().a().log(Level.SEVERE,
                    "Entity not in this chunk! Chunk: {0}, {1} Entity: {2}",
                    new Object[] {this.g, this.h, oentity});
            this.e.W().c("Destroying entity to prevent trouble", new RuntimeException());
            oentity.w();
            return;
        }

        int k = OMathHelper.c(oentity.v / 16.0D);

        if (k < 0) {
            k = 0;
        }

        if (k >= this.j.length) {
            k = this.j.length - 1;
        }

        oentity.ai = true;
        oentity.aj = this.g;
        oentity.ak = k;
        oentity.al = this.h;
        this.j[k].add(oentity);
    }

    public void b(OEntity oentity) {
        this.a(oentity, oentity.ak);
    }

    public void a(OEntity oentity, int i) {
        if (i < 0) {
            i = 0;
        }

        if (i >= this.j.length) {
            i = this.j.length - 1;
        }

        this.j[i].remove(oentity);
    }

    public boolean d(int i, int j, int k) {
        return j >= this.f[k << 4 | i];
    }

    public OTileEntity e(int i, int j, int k) {
        OChunkPosition ochunkposition = new OChunkPosition(i, j, k);
        OTileEntity otileentity = (OTileEntity) this.i.get(ochunkposition);

        if (otileentity == null) {
            int l = this.a(i, j, k);

            if (l <= 0 || !OBlock.r[l].t()) {
                return null;
            }

            if (otileentity == null) {
                otileentity = ((OITileEntityProvider) OBlock.r[l]).b(this.e);
                this.e.a(this.g * 16 + i, j, this.h * 16 + k, otileentity);
            }

            otileentity = (OTileEntity) this.i.get(ochunkposition);
        }

        if (otileentity != null && otileentity.r()) {
            this.i.remove(ochunkposition);
            return null;
        } else {
            return otileentity;
        }
    }

    public void a(OTileEntity otileentity) {
        int i = otileentity.l - this.g * 16;
        int j = otileentity.m;
        int k = otileentity.n - this.h * 16;

        this.a(i, j, k, otileentity);
        if (this.d) {
            this.e.g.add(otileentity);
        }
    }

    public void a(int i, int j, int k, OTileEntity otileentity) {
        OChunkPosition ochunkposition = new OChunkPosition(i, j, k);

        otileentity.b(this.e);
        otileentity.l = this.g * 16 + i;
        otileentity.m = j;
        otileentity.n = this.h * 16 + k;
        if (this.a(i, j, k) != 0 && OBlock.r[this.a(i, j, k)] instanceof OITileEntityProvider) {
            if (this.i.containsKey(ochunkposition)) {
                ((OTileEntity) this.i.get(ochunkposition)).w_();
            }

            otileentity.s();
            this.i.put(ochunkposition, otileentity);
        }
    }

    public void f(int i, int j, int k) {
        OChunkPosition ochunkposition = new OChunkPosition(i, j, k);

        if (this.d) {
            OTileEntity otileentity = (OTileEntity) this.i.remove(ochunkposition);

            if (otileentity != null) {
                otileentity.w_();
            }
        }
    }

    public void c() {
        this.d = true;
        this.e.a(this.i.values());

        for (int i = 0; i < this.j.length; ++i) {
            this.e.a(this.j[i]);
        }
    }

    public void d() {
        this.d = false;
        Iterator iterator = this.i.values().iterator();

        while (iterator.hasNext()) {
            OTileEntity otileentity = (OTileEntity) iterator.next();

            this.e.a(otileentity);
        }

        for (int i = 0; i < this.j.length; ++i) {
            this.e.b(this.j[i]);
        }
    }

    public void e() {
        this.l = true;
    }

    public void a(OEntity oentity, OAxisAlignedBB oaxisalignedbb, List list, OIEntitySelector oientityselector) {
        int i = OMathHelper.c((oaxisalignedbb.b - 2.0D) / 16.0D);
        int j = OMathHelper.c((oaxisalignedbb.e + 2.0D) / 16.0D);

        if (i < 0) {
            i = 0;
            j = Math.max(i, j);
        }

        if (j >= this.j.length) {
            j = this.j.length - 1;
            i = Math.min(i, j);
        }

        for (int k = i; k <= j; ++k) {
            List list1 = this.j[k];

            for (int l = 0; l < list1.size(); ++l) {
                OEntity oentity1 = (OEntity) list1.get(l);

                if (oentity1 != oentity && oentity1.E.a(oaxisalignedbb) && (oientityselector == null || oientityselector.a(oentity1))) {
                    list.add(oentity1);
                    OEntity[] aoentity = oentity1.an();

                    if (aoentity != null) {
                        for (int i1 = 0; i1 < aoentity.length; ++i1) {
                            oentity1 = aoentity[i1];
                            if (oentity1 != oentity && oentity1.E.a(oaxisalignedbb) && (oientityselector == null || oientityselector.a(oentity1))) {
                                list.add(oentity1);
                            }
                        }
                    }
                }
            }
        }
    }

    public void a(Class oclass, OAxisAlignedBB oaxisalignedbb, List list, OIEntitySelector oientityselector) {
        int i = OMathHelper.c((oaxisalignedbb.b - 2.0D) / 16.0D);
        int j = OMathHelper.c((oaxisalignedbb.e + 2.0D) / 16.0D);

        if (i < 0) {
            i = 0;
        } else if (i >= this.j.length) {
            i = this.j.length - 1;
        }

        if (j >= this.j.length) {
            j = this.j.length - 1;
        } else if (j < 0) {
            j = 0;
        }

        for (int k = i; k <= j; ++k) {
            List list1 = this.j[k];

            for (int l = 0; l < list1.size(); ++l) {
                OEntity oentity = (OEntity) list1.get(l);

                if (oclass.isAssignableFrom(oentity.getClass()) && oentity.E.a(oaxisalignedbb) && (oientityselector == null || oientityselector.a(oentity))) {
                    list.add(oentity);
                }
            }
        }
    }

    public boolean a(boolean flag) {
        if (flag) {
            if (this.m && this.e.G() != this.n || this.l) {
                return true;
            }
        } else if (this.m && this.e.G() >= this.n + 600L) {
            return true;
        }

        return this.l;
    }

    public Random a(long i) {
        return new Random(this.e.F() + (long) (this.g * this.g * 4987142) + (long) (this.g * 5947611) + (long) (this.h * this.h) * 4392871L + (long) (this.h * 389711) ^ i);
    }

    public boolean g() {
        return false;
    }

    public void a(OIChunkProvider oichunkprovider, OIChunkProvider oichunkprovider1, int i, int j) {
        if (!this.k && oichunkprovider.a(i + 1, j + 1) && oichunkprovider.a(i, j + 1) && oichunkprovider.a(i + 1, j)) {
            oichunkprovider.a(oichunkprovider1, i, j);
        }

        if (oichunkprovider.a(i - 1, j) && !oichunkprovider.d(i - 1, j).k && oichunkprovider.a(i - 1, j + 1) && oichunkprovider.a(i, j + 1) && oichunkprovider.a(i - 1, j + 1)) {
            oichunkprovider.a(oichunkprovider1, i - 1, j);
        }

        if (oichunkprovider.a(i, j - 1) && !oichunkprovider.d(i, j - 1).k && oichunkprovider.a(i + 1, j - 1) && oichunkprovider.a(i + 1, j - 1) && oichunkprovider.a(i + 1, j)) {
            oichunkprovider.a(oichunkprovider1, i, j - 1);
        }

        if (oichunkprovider.a(i - 1, j - 1) && !oichunkprovider.d(i - 1, j - 1).k && oichunkprovider.a(i, j - 1) && oichunkprovider.a(i - 1, j)) {
            oichunkprovider.a(oichunkprovider1, i - 1, j - 1);
        }
    }

    public int d(int i, int j) {
        int k = i | j << 4;
        int l = this.b[k];

        if (l == -999) {
            int i1 = this.h() + 15;

            l = -1;

            while (i1 > 0 && l == -1) {
                int j1 = this.a(i, i1, j);
                OMaterial omaterial = j1 == 0 ? OMaterial.a : OBlock.r[j1].cO;

                if (!omaterial.c() && !omaterial.d()) {
                    --i1;
                } else {
                    l = i1 + 1;
                }
            }

            this.b[k] = l;
        }

        return l;
    }

    public void k() {
        if (this.t && !this.e.t.f) {
            this.q();
        }
    }

    public OChunkCoordIntPair l() {
        return new OChunkCoordIntPair(this.g, this.h);
    }

    public boolean c(int i, int j) {
        if (i < 0) {
            i = 0;
        }

        if (j >= 256) {
            j = 255;
        }

        for (int k = i; k <= j; k += 16) {
            OExtendedBlockStorage oextendedblockstorage = this.r[k >> 4];

            if (oextendedblockstorage != null && !oextendedblockstorage.a()) {
                return false;
            }
        }

        return true;
    }

    public void a(OExtendedBlockStorage[] aoextendedblockstorage) {
        this.r = aoextendedblockstorage;
    }

    public OBiomeGenBase a(int i, int j, OWorldChunkManager oworldchunkmanager) {
        int k = this.s[j << 4 | i] & 255;

        if (k == 255) {
            OBiomeGenBase obiomegenbase = oworldchunkmanager.a((this.g << 4) + i, (this.h << 4) + j);

            k = obiomegenbase.N;
            this.s[j << 4 | i] = (byte) (k & 255);
        }

        return OBiomeGenBase.a[k] == null ? OBiomeGenBase.c : OBiomeGenBase.a[k];
    }

    public byte[] m() {
        return this.s;
    }

    public void a(byte[] abyte) {
        this.s = abyte;
    }

    public void n() {
        this.u = 0;
    }

    public void o() {
        for (int i = 0; i < 8; ++i) {
            if (this.u >= 4096) {
                return;
            }

            int j = this.u % 16;
            int k = this.u / 16 % 16;
            int l = this.u / 256;

            ++this.u;
            int i1 = (this.g << 4) + k;
            int j1 = (this.h << 4) + l;

            for (int k1 = 0; k1 < 16; ++k1) {
                int l1 = (j << 4) + k1;

                if (this.r[j] == null && (k1 == 0 || k1 == 15 || k == 0 || k == 15 || l == 0 || l == 15) || this.r[j] != null && this.r[j].a(k, k1, l) == 0) {
                    if (OBlock.v[this.e.a(i1, l1 - 1, j1)] > 0) {
                        this.e.A(i1, l1 - 1, j1);
                    }

                    if (OBlock.v[this.e.a(i1, l1 + 1, j1)] > 0) {
                        this.e.A(i1, l1 + 1, j1);
                    }

                    if (OBlock.v[this.e.a(i1 - 1, l1, j1)] > 0) {
                        this.e.A(i1 - 1, l1, j1);
                    }

                    if (OBlock.v[this.e.a(i1 + 1, l1, j1)] > 0) {
                        this.e.A(i1 + 1, l1, j1);
                    }

                    if (OBlock.v[this.e.a(i1, l1, j1 - 1)] > 0) {
                        this.e.A(i1, l1, j1 - 1);
                    }

                    if (OBlock.v[this.e.a(i1, l1, j1 + 1)] > 0) {
                        this.e.A(i1, l1, j1 + 1);
                    }

                    this.e.A(i1, l1, j1);
                }
            }
        }
    }
}
