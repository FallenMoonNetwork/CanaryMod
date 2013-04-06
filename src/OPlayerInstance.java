import java.util.ArrayList;
import java.util.List;

class OPlayerInstance {

    private final List b;
    private final OChunkCoordIntPair c;
    private short[] d;
    private int e;
    private int f;

    final OPlayerManager a;

    public OPlayerInstance(OPlayerManager oplayermanager, int i, int j) {
        this.a = oplayermanager;
        this.b = new ArrayList();
        this.d = new short[64];
        this.e = 0;
        this.c = new OChunkCoordIntPair(i, j);
        oplayermanager.a().b.c(i, j);
    }

    public void a(OEntityPlayerMP oentityplayermp) {
        if (this.b.contains(oentityplayermp)) {
            throw new IllegalStateException("Failed to add player. " + oentityplayermp + " already is in chunk " + this.c.a + ", " + this.c.b);
        } else {
            this.b.add(oentityplayermp);
            oentityplayermp.f.add(this.c);
        }
    }

    public void b(OEntityPlayerMP oentityplayermp) {
        if (this.b.contains(oentityplayermp)) {
            oentityplayermp.a.b(new OPacket51MapChunk(OPlayerManager.a(this.a).e(this.c.a, this.c.b), true, 0));
            this.b.remove(oentityplayermp);
            oentityplayermp.f.remove(this.c);
            if (this.b.isEmpty()) {
                long i = (long) this.c.a + 2147483647L | (long) this.c.b + 2147483647L << 32;

                OPlayerManager.b(this.a).d(i);
                if (this.e > 0) {
                    OPlayerManager.c(this.a).remove(this);
                }

                this.a.a().b.b(this.c.a, this.c.b);
            }
        }
    }

    public void a(int i, int j, int k) {
        if (this.e == 0) {
            OPlayerManager.c(this.a).add(this);
        }

        this.f |= 1 << (j >> 4);
        if (this.e < 64) {
            short short1 = (short) (i << 12 | k << 8 | j);

            for (int l = 0; l < this.e; ++l) {
                if (this.d[l] == short1) {
                    return;
                }
            }

            this.d[this.e++] = short1;
        }
    }

    public void a(OPacket opacket) {
        for (int i = 0; i < this.b.size(); ++i) {
            OEntityPlayerMP oentityplayermp = (OEntityPlayerMP) this.b.get(i);

            if (!oentityplayermp.f.contains(this.c)) {
                oentityplayermp.a.b(opacket);
            }
        }
    }

    public void a() {
        if (this.e != 0) {
            int i;
            int j;
            int k;

            if (this.e == 1) {
                i = this.c.a * 16 + (this.d[0] >> 12 & 15);
                j = this.d[0] & 255;
                k = this.c.b * 16 + (this.d[0] >> 8 & 15);
                this.a((OPacket) (new OPacket53BlockChange(i, j, k, OPlayerManager.a(this.a))));
                if (OPlayerManager.a(this.a).d(i, j, k)) {
                    this.a(OPlayerManager.a(this.a).r(i, j, k));
                }
            } else {
                int l;

                if (this.e == 64) {
                    i = this.c.a * 16;
                    j = this.c.b * 16;
                    this.a((OPacket) (new OPacket51MapChunk(OPlayerManager.a(this.a).e(this.c.a, this.c.b), false, this.f)));

                    for (k = 0; k < 16; ++k) {
                        if ((this.f & 1 << k) != 0) {
                            l = k << 4;
                            List list = OPlayerManager.a(this.a).c(i, l, j, i + 16, l + 16, j + 16);

                            for (int i1 = 0; i1 < list.size(); ++i1) {
                                this.a((OTileEntity) list.get(i1));
                            }
                        }
                    }
                } else {
                    this.a((OPacket) (new OPacket52MultiBlockChange(this.c.a, this.c.b, this.d, this.e, OPlayerManager.a(this.a))));

                    for (i = 0; i < this.e; ++i) {
                        j = this.c.a * 16 + (this.d[i] >> 12 & 15);
                        k = this.d[i] & 255;
                        l = this.c.b * 16 + (this.d[i] >> 8 & 15);
                        if (OPlayerManager.a(this.a).d(j, k, l)) {
                            this.a(OPlayerManager.a(this.a).r(j, k, l));
                        }
                    }
                }
            }

            this.e = 0;
            this.f = 0;
        }
    }

    private void a(OTileEntity otileentity) {
        if (otileentity != null) {
            OPacket opacket = otileentity.m();

            if (opacket != null) {
                this.a(opacket);
            }
        }
    }

    static OChunkCoordIntPair a(OPlayerInstance oplayerinstance) {
        return oplayerinstance.c;
    }

    static List b(OPlayerInstance oplayerinstance) {
        return oplayerinstance.b;
    }
}
