import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class OPlayerManager {

    private final OWorldServer a;
    private final List b = new ArrayList();
    private final OLongHashMap c = new OLongHashMap();
    private final List d = new ArrayList();
    private final int e;
    private final int[][] f = new int[][] { { 1, 0}, { 0, 1}, { -1, 0}, { 0, -1}};
    
    private PlayerManager playerManager; // CanaryMod player manager wrap

    public OPlayerManager(OWorldServer oworldserver, int i) {
        if (i > 15) {
            throw new IllegalArgumentException("Too big view radius!");
        } else if (i < 3) {
            throw new IllegalArgumentException("Too small view radius!");
        } else {
            this.e = i;
            this.a = oworldserver;
        }
        
        this.playerManager = new PlayerManager(this);
    }
    
    public PlayerManager getCanaryPlayerManager() {
        return playerManager;
    }

    public OWorldServer a() {
        return this.a;
    }

    public void b() {
        Iterator iterator = this.d.iterator();

        while (iterator.hasNext()) {
            OPlayerInstance oplayerinstance = (OPlayerInstance) iterator.next();

            oplayerinstance.a();
        }

        this.d.clear();
        if (this.b.isEmpty()) {
            OWorldProvider oworldprovider = this.a.v;

            if (!oworldprovider.e()) {
                this.a.b.a();
            }
        }
    }

    private OPlayerInstance a(int i, int j, boolean flag) {
        long k = (long) i + 2147483647L | (long) j + 2147483647L << 32;
        OPlayerInstance oplayerinstance = (OPlayerInstance) this.c.a(k);

        if (oplayerinstance == null && flag) {
            oplayerinstance = new OPlayerInstance(this, i, j);
            this.c.a(k, oplayerinstance);
        }

        return oplayerinstance;
    }

    public void a(int i, int j, int k) {
        int l = i >> 4;
        int i1 = k >> 4;
        OPlayerInstance oplayerinstance = this.a(l, i1, false);

        if (oplayerinstance != null) {
            oplayerinstance.a(i & 15, j, k & 15);
        }
    }

    public void a(OEntityPlayerMP oentityplayermp) {
        int i = (int) oentityplayermp.t >> 4;
        int j = (int) oentityplayermp.v >> 4;

        oentityplayermp.d = oentityplayermp.t;
        oentityplayermp.e = oentityplayermp.v;

        for (int k = i - this.e; k <= i + this.e; ++k) {
            for (int l = j - this.e; l <= j + this.e; ++l) {
                this.a(k, l, true).a(oentityplayermp);
            }
        }

        this.b.add(oentityplayermp);
        this.b(oentityplayermp);
    }

    public void b(OEntityPlayerMP oentityplayermp) {
        ArrayList arraylist = new ArrayList(oentityplayermp.f);
        int i = 0;
        int j = this.e;
        int k = (int) oentityplayermp.t >> 4;
        int l = (int) oentityplayermp.v >> 4;
        int i1 = 0;
        int j1 = 0;
        OChunkCoordIntPair ochunkcoordintpair = OPlayerInstance.a(this.a(k, l, true));

        oentityplayermp.f.clear();
        if (arraylist.contains(ochunkcoordintpair)) {
            oentityplayermp.f.add(ochunkcoordintpair);
        }

        int k1;

        for (k1 = 1; k1 <= j * 2; ++k1) {
            for (int l1 = 0; l1 < 2; ++l1) {
                int[] aint = this.f[i++ % 4];

                for (int i2 = 0; i2 < k1; ++i2) {
                    i1 += aint[0];
                    j1 += aint[1];
                    ochunkcoordintpair = OPlayerInstance.a(this.a(k + i1, l + j1, true));
                    if (arraylist.contains(ochunkcoordintpair)) {
                        oentityplayermp.f.add(ochunkcoordintpair);
                    }
                }
            }
        }

        i %= 4;

        for (k1 = 0; k1 < j * 2; ++k1) {
            i1 += this.f[i][0];
            j1 += this.f[i][1];
            ochunkcoordintpair = OPlayerInstance.a(this.a(k + i1, l + j1, true));
            if (arraylist.contains(ochunkcoordintpair)) {
                oentityplayermp.f.add(ochunkcoordintpair);
            }
        }
    }

    public void c(OEntityPlayerMP oentityplayermp) {
        int i = (int) oentityplayermp.d >> 4;
        int j = (int) oentityplayermp.e >> 4;

        for (int k = i - this.e; k <= i + this.e; ++k) {
            for (int l = j - this.e; l <= j + this.e; ++l) {
                OPlayerInstance oplayerinstance = this.a(k, l, false);

                if (oplayerinstance != null) {
                    oplayerinstance.b(oentityplayermp);
                }
            }
        }

        this.b.remove(oentityplayermp);
    }

    private boolean a(int i, int j, int k, int l, int i1) {
        int j1 = i - k;
        int k1 = j - l;

        return j1 >= -i1 && j1 <= i1 ? k1 >= -i1 && k1 <= i1 : false;
    }

    public void d(OEntityPlayerMP oentityplayermp) {
        int i = (int) oentityplayermp.t >> 4;
        int j = (int) oentityplayermp.v >> 4;
        double d0 = oentityplayermp.d - oentityplayermp.t;
        double d1 = oentityplayermp.e - oentityplayermp.v;
        double d2 = d0 * d0 + d1 * d1;

        if (d2 >= 64.0D) {
            int k = (int) oentityplayermp.d >> 4;
            int l = (int) oentityplayermp.e >> 4;
            int i1 = this.e;
            int j1 = i - k;
            int k1 = j - l;

            if (j1 != 0 || k1 != 0) {
                // CanaryMod speed up teleporting.
                if (j1 > this.e || j1 < -this.e || k1 > this.e || k1 < -this.e) {
                    this.c(oentityplayermp);
                    this.a(oentityplayermp);
                    return;
                }

                for (int l1 = i - i1; l1 <= i + i1; ++l1) {
                    for (int i2 = j - i1; i2 <= j + i1; ++i2) {
                        if (!this.a(l1, i2, k, l, i1)) {
                            this.a(l1, i2, true).a(oentityplayermp);
                        }

                        if (!this.a(l1 - j1, i2 - k1, i, j, i1)) {
                            OPlayerInstance oplayerinstance = this.a(l1 - j1, i2 - k1, false);

                            if (oplayerinstance != null) {
                                oplayerinstance.b(oentityplayermp);
                            }
                        }
                    }
                }

                this.b(oentityplayermp);
                oentityplayermp.d = oentityplayermp.t;
                oentityplayermp.e = oentityplayermp.v;
            }
        }
    }

    public boolean a(OEntityPlayerMP oentityplayermp, int i, int j) {
        OPlayerInstance oplayerinstance = this.a(i, j, false);

        return oplayerinstance == null ? false : OPlayerInstance.b(oplayerinstance).contains(oentityplayermp) && !oentityplayermp.f.contains(OPlayerInstance.a(oplayerinstance));
    }

    public static int a(int i) {
        return i * 16 - 16;
    }

    static OWorldServer a(OPlayerManager oplayermanager) {
        return oplayermanager.a;
    }

    static OLongHashMap b(OPlayerManager oplayermanager) {
        return oplayermanager.c;
    }

    static List c(OPlayerManager oplayermanager) {
        return oplayermanager.d;
    }
   
    // CanaryMod: bring back old "send packet to chunk" method from alpha
    public void sendPacketToChunk(OPacket opacket, int i, int j, int k) {
        // Get chunk coordinates
        int chunkx = i >> 4;
        int chunkz = k >> 4;
        // Get the chunk
        OPlayerInstance localOPlayerInstance = this.a(chunkx, chunkz, false);

        // if chunk != null, send packet
        if (localOPlayerInstance != null) {
            localOPlayerInstance.a(opacket);
        } // CanaryMod: diff visibility
    } // CanaryMod: diff visibility

}
