import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class OAnvilChunkLoader implements OIChunkLoader, OIThreadedFileIO {

    private List a = new ArrayList();
    private Set b = new HashSet();
    private Object c = new Object();
    private final File d;

    public OAnvilChunkLoader(File file1) {
        this.d = file1;
    }

    public OChunk a(OWorld oworld, int i, int j) throws IOException {
        ONBTTagCompound onbttagcompound = null;
        OChunkCoordIntPair ochunkcoordintpair = new OChunkCoordIntPair(i, j);
        Object object = this.c;

        synchronized (this.c) {
            if (this.b.contains(ochunkcoordintpair)) {
                for (int k = 0; k < this.a.size(); ++k) {
                    if (((OAnvilChunkLoaderPending) this.a.get(k)).a.equals(ochunkcoordintpair)) {
                        onbttagcompound = ((OAnvilChunkLoaderPending) this.a.get(k)).b;
                        break;
                    }
                }
            }
        }

        if (onbttagcompound == null) {
            DataInputStream datainputstream = ORegionFileCache.c(this.d, i, j);

            if (datainputstream == null) {
                return null;
            }

            onbttagcompound = OCompressedStreamTools.a((DataInput) datainputstream);
        }

        return this.a(oworld, i, j, onbttagcompound);
    }

    protected OChunk a(OWorld oworld, int i, int j, ONBTTagCompound onbttagcompound) {
        if (!onbttagcompound.b("Level")) {
            oworld.W().c("Chunk file at " + i + "," + j + " is missing level data, skipping");
            return null;
        } else if (!onbttagcompound.l("Level").b("Sections")) {
            oworld.W().c("Chunk file at " + i + "," + j + " is missing block data, skipping");
            return null;
        } else {
            OChunk ochunk = this.a(oworld, onbttagcompound.l("Level"));

            if (!ochunk.a(i, j)) {
                oworld.W().c("Chunk file at " + i + "," + j + " is in the wrong location; relocating. (Expected " + i + ", " + j + ", got " + ochunk.g + ", " + ochunk.h + ")");
                onbttagcompound.a("xPos", i);
                onbttagcompound.a("zPos", j);
                ochunk = this.a(oworld, onbttagcompound.l("Level"));
            }

            return ochunk;
        }
    }

    public void a(OWorld oworld, OChunk ochunk) throws OMinecraftException, IOException {
        oworld.E();

        try {
            ONBTTagCompound onbttagcompound = new ONBTTagCompound();
            ONBTTagCompound onbttagcompound1 = new ONBTTagCompound();

            onbttagcompound.a("Level", (ONBTBase) onbttagcompound1);
            this.a(ochunk, oworld, onbttagcompound1);
            this.a(ochunk.l(), onbttagcompound);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    protected void a(OChunkCoordIntPair ochunkcoordintpair, ONBTTagCompound onbttagcompound) {
        Object object = this.c;

        synchronized (this.c) {
            if (this.b.contains(ochunkcoordintpair)) {
                for (int i = 0; i < this.a.size(); ++i) {
                    if (((OAnvilChunkLoaderPending) this.a.get(i)).a.equals(ochunkcoordintpair)) {
                        this.a.set(i, new OAnvilChunkLoaderPending(ochunkcoordintpair, onbttagcompound));
                        return;
                    }
                }
            }

            this.a.add(new OAnvilChunkLoaderPending(ochunkcoordintpair, onbttagcompound));
            this.b.add(ochunkcoordintpair);
            OThreadedFileIOBase.a.a(this);
        }
    }

    public boolean c() {
        OAnvilChunkLoaderPending oanvilchunkloaderpending = null;
        Object object = this.c;

        synchronized (this.c) {
            if (this.a.isEmpty()) {
                return false;
            }

            oanvilchunkloaderpending = (OAnvilChunkLoaderPending) this.a.remove(0);
            this.b.remove(oanvilchunkloaderpending.a);
        }

        if (oanvilchunkloaderpending != null) {
            try {
                this.a(oanvilchunkloaderpending);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        return true;
    }

    private void a(OAnvilChunkLoaderPending oanvilchunkloaderpending) throws IOException {
        DataOutputStream dataoutputstream = ORegionFileCache.d(this.d, oanvilchunkloaderpending.a.a, oanvilchunkloaderpending.a.b);

        OCompressedStreamTools.a(oanvilchunkloaderpending.b, (DataOutput) dataoutputstream);
        dataoutputstream.close();
    }

    public void b(OWorld oworld, OChunk ochunk) {}

    public void a() {}

    public void b() {}

    private void a(OChunk ochunk, OWorld oworld, ONBTTagCompound onbttagcompound) {
        onbttagcompound.a("xPos", ochunk.g);
        onbttagcompound.a("zPos", ochunk.h);
        onbttagcompound.a("LastUpdate", oworld.G());
        onbttagcompound.a("HeightMap", ochunk.f);
        onbttagcompound.a("TerrainPopulated", ochunk.k);
        OExtendedBlockStorage[] aoextendedblockstorage = ochunk.i();
        ONBTTagList onbttaglist = new ONBTTagList("Sections");
        boolean flag = !oworld.t.f;
        OExtendedBlockStorage[] aoextendedblockstorage1 = aoextendedblockstorage;
        int i = aoextendedblockstorage.length;

        ONBTTagCompound onbttagcompound1;

        for (int j = 0; j < i; ++j) {
            OExtendedBlockStorage oextendedblockstorage = aoextendedblockstorage1[j];

            if (oextendedblockstorage != null) {
                onbttagcompound1 = new ONBTTagCompound();
                onbttagcompound1.a("Y", (byte) (oextendedblockstorage.d() >> 4 & 255));
                onbttagcompound1.a("Blocks", oextendedblockstorage.g());
                if (oextendedblockstorage.i() != null) {
                    onbttagcompound1.a("Add", oextendedblockstorage.i().a);
                }

                onbttagcompound1.a("Data", oextendedblockstorage.j().a);
                onbttagcompound1.a("BlockLight", oextendedblockstorage.k().a);
                if (flag) {
                    onbttagcompound1.a("SkyLight", oextendedblockstorage.l().a);
                } else {
                    onbttagcompound1.a("SkyLight", new byte[oextendedblockstorage.k().a.length]);
                }

                onbttaglist.a((ONBTBase) onbttagcompound1);
            }
        }

        onbttagcompound.a("Sections", (ONBTBase) onbttaglist);
        onbttagcompound.a("Biomes", ochunk.m());
        ochunk.m = false;
        ONBTTagList onbttaglist1 = new ONBTTagList();

        Iterator iterator;

        for (i = 0; i < ochunk.j.length; ++i) {
            iterator = ochunk.j[i].iterator();

            while (iterator.hasNext()) {
                OEntity oentity = (OEntity) iterator.next();

                onbttagcompound1 = new ONBTTagCompound();
                if (oentity.d(onbttagcompound1)) {
                    ochunk.m = true;
                    onbttaglist1.a((ONBTBase) onbttagcompound1);
                }
            }
        }

        onbttagcompound.a("Entities", (ONBTBase) onbttaglist1);
        ONBTTagList onbttaglist2 = new ONBTTagList();

        iterator = ochunk.i.values().iterator();

        while (iterator.hasNext()) {
            OTileEntity otileentity = (OTileEntity) iterator.next();

            onbttagcompound1 = new ONBTTagCompound();
            otileentity.b(onbttagcompound1);
            onbttaglist2.a((ONBTBase) onbttagcompound1);
        }

        onbttagcompound.a("TileEntities", (ONBTBase) onbttaglist2);
        List list = oworld.a(ochunk, false);

        if (list != null) {
            long k = oworld.G();
            ONBTTagList onbttaglist3 = new ONBTTagList();
            Iterator iterator1 = list.iterator();

            while (iterator1.hasNext()) {
                ONextTickListEntry onextticklistentry = (ONextTickListEntry) iterator1.next();
                ONBTTagCompound onbttagcompound2 = new ONBTTagCompound();

                onbttagcompound2.a("i", onextticklistentry.d);
                onbttagcompound2.a("x", onextticklistentry.a);
                onbttagcompound2.a("y", onextticklistentry.b);
                onbttagcompound2.a("z", onextticklistentry.c);
                onbttagcompound2.a("t", (int) (onextticklistentry.e - k));
                onbttagcompound2.a("p", onextticklistentry.f);
                onbttaglist3.a((ONBTBase) onbttagcompound2);
            }

            onbttagcompound.a("TileTicks", (ONBTBase) onbttaglist3);
        }
    }

    private OChunk a(OWorld oworld, ONBTTagCompound onbttagcompound) {
        int i = onbttagcompound.e("xPos");
        int j = onbttagcompound.e("zPos");
        OChunk ochunk = new OChunk(oworld, i, j);

        ochunk.f = onbttagcompound.k("HeightMap");
        ochunk.k = onbttagcompound.n("TerrainPopulated");
        ONBTTagList onbttaglist = onbttagcompound.m("Sections");
        byte b0 = 16;
        OExtendedBlockStorage[] aoextendedblockstorage = new OExtendedBlockStorage[b0];
        boolean flag = !oworld.t.f;

        for (int k = 0; k < onbttaglist.c(); ++k) {
            ONBTTagCompound onbttagcompound1 = (ONBTTagCompound) onbttaglist.b(k);
            byte b1 = onbttagcompound1.c("Y");
            OExtendedBlockStorage oextendedblockstorage = new OExtendedBlockStorage(b1 << 4, flag);

            oextendedblockstorage.a(onbttagcompound1.j("Blocks"));
            if (onbttagcompound1.b("Add")) {
                // CanaryMod: "fix" crashing bug. Nobody uses block IDs over 255 anyway.
                // oextendedblockstorage.a(new ONibbleArray(onbttagcompound1.j("Add"), 4));
            }

            oextendedblockstorage.b(new ONibbleArray(onbttagcompound1.j("Data"), 4));
            oextendedblockstorage.c(new ONibbleArray(onbttagcompound1.j("BlockLight"), 4));
            if (flag) {
                oextendedblockstorage.d(new ONibbleArray(onbttagcompound1.j("SkyLight"), 4));
            }

            oextendedblockstorage.e();
            aoextendedblockstorage[b1] = oextendedblockstorage;
        }

        ochunk.a(aoextendedblockstorage);
        if (onbttagcompound.b("Biomes")) {
            ochunk.a(onbttagcompound.j("Biomes"));
        }

        ONBTTagList onbttaglist1 = onbttagcompound.m("Entities");

        if (onbttaglist1 != null) {
            for (int l = 0; l < onbttaglist1.c(); ++l) {
                ONBTTagCompound onbttagcompound2 = (ONBTTagCompound) onbttaglist1.b(l);
                OEntity oentity = OEntityList.a(onbttagcompound2, oworld);

                ochunk.m = true;
                if (oentity != null) {
                    ochunk.a(oentity);
                    OEntity oentity1 = oentity;

                    for (ONBTTagCompound onbttagcompound3 = onbttagcompound2; onbttagcompound3.b("Riding"); onbttagcompound3 = onbttagcompound3.l("Riding")) {
                        OEntity oentity2 = OEntityList.a(onbttagcompound3.l("Riding"), oworld);

                        if (oentity2 != null) {
                            ochunk.a(oentity2);
                            oentity1.a(oentity2);
                        }

                        oentity1 = oentity2;
                    }
                }
            }
        }

        ONBTTagList onbttaglist2 = onbttagcompound.m("TileEntities");

        if (onbttaglist2 != null) {
            for (int i1 = 0; i1 < onbttaglist2.c(); ++i1) {
                ONBTTagCompound onbttagcompound4 = (ONBTTagCompound) onbttaglist2.b(i1);
                OTileEntity otileentity = OTileEntity.c(onbttagcompound4);

                if (otileentity != null) {
                    ochunk.a(otileentity);
                }
            }
        }

        if (onbttagcompound.b("TileTicks")) {
            ONBTTagList onbttaglist3 = onbttagcompound.m("TileTicks");

            if (onbttaglist3 != null) {
                for (int j1 = 0; j1 < onbttaglist3.c(); ++j1) {
                    ONBTTagCompound onbttagcompound5 = (ONBTTagCompound) onbttaglist3.b(j1);

                    oworld.b(onbttagcompound5.e("x"), onbttagcompound5.e("y"), onbttagcompound5.e("z"), onbttagcompound5.e("i"), onbttagcompound5.e("t"), onbttagcompound5.e("p"));
                }
            }
        }

        return ochunk;
    }
}
