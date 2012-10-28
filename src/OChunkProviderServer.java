import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


public class OChunkProviderServer implements OIChunkProvider {

    private Set b = new HashSet();
    private OChunk c;
    private OIChunkProvider d;
    private OIChunkLoader e;
    public boolean a = true;
    private OLongHashMap f = new OLongHashMap();
    private List g = new ArrayList();
    private OWorldServer h;
    // CanaryMod: load status
    boolean loadedpreload = false;

    public OChunkProviderServer(OWorldServer oworldserver, OIChunkLoader oichunkloader, OIChunkProvider oichunkprovider) {
        super();
        this.c = new OEmptyChunk(oworldserver, 0, 0);
        this.h = oworldserver;
        this.e = oichunkloader;
        this.d = oichunkprovider;
    }

    public boolean a(int i, int j) {
        return this.f.b(OChunkCoordIntPair.a(i, j));
    }

    public void b(int i, int j) {
        if (this.h.v.e()) {
            OChunkCoordinates ochunkcoordinates = this.h.G();
            int k = i * 16 + 8 - ochunkcoordinates.a;
            int l = j * 16 + 8 - ochunkcoordinates.c;
            short short1 = 128;

            if (k < -short1 || k > short1 || l < -short1 || l > short1) {
                this.b.add(Long.valueOf(OChunkCoordIntPair.a(i, j)));
            }
        } else {
            this.b.add(Long.valueOf(OChunkCoordIntPair.a(i, j)));
        }

    }

    public void a() {
        Iterator iterator = this.g.iterator();

        while (iterator.hasNext()) {
            OChunk ochunk = (OChunk) iterator.next();

            this.b(ochunk.g, ochunk.h);
        }

    }

    public OChunk c(int i, int j) {
        long k = OChunkCoordIntPair.a(i, j);

        this.b.remove(Long.valueOf(k));
        OChunk ochunk = (OChunk) this.f.a(k);
        
        if (ochunk == null) {
            // CanaryMod: load preload plugins once!
            if (!loadedpreload) {
                etc.getLoader().loadPreloadPlugins();
                loadedpreload = true;
            }
            ochunk = this.f(i, j);
            if (ochunk == null) {
                // Canary onChunkCreate hook
                byte[] blocks = (byte[]) etc.getLoader().callHook(PluginLoader.Hook.CHUNK_CREATE, i, j, h.world);

                if (blocks != null) {
                    ochunk = Chunk.getNewChunk(h, blocks, i, j).chunk;
                    ochunk.k = true; // is populated = true
                    ochunk.b(); // lightning update
                } else if (this.d == null) {
                    ochunk = this.c;
                } else {
                    ochunk = this.d.d(i, j);
                }

                etc.getLoader().callHook(PluginLoader.Hook.CHUNK_CREATED, ochunk.chunk);
            }

            this.f.a(k, ochunk);
            this.g.add(ochunk);
            if (ochunk != null) {
                ochunk.c();
                // Canary onChunkLoaded hook
                etc.getLoader().callHook(PluginLoader.Hook.CHUNK_LOADED, ochunk.chunk);
                if (!ochunk.k && this.a(i + 1, j + 1) && this.a(i, j + 1) && this.a(i + 1, j)) {
                    this.a(this, i, j);
                }
            }// To prevent NullPointerExceptions
            if (this.a(i - 1, j) && !this.d(i - 1, j).k && this.a(i - 1, j + 1) && this.a(i, j + 1) && this.a(i - 1, j)) {
                this.a(this, i - 1, j);
            }

            ochunk.a(this, this, i, j);
        }

        return ochunk;
    }

    public OChunk d(int i, int j) {
        OChunk ochunk = (OChunk) this.f.a(OChunkCoordIntPair.a(i, j));

        return ochunk == null ? (!this.h.A && !this.a ? this.c : this.c(i, j)) : ochunk;
    }

    private OChunk f(int i, int j) {
        if (this.e == null) {
            return null;
        } else {
            try {
                OChunk ochunk = this.e.a(this.h, i, j);

                if (ochunk != null) {
                    ochunk.n = this.h.E();
                    if (this.d != null) {
                        this.d.e(i, j);
                    }
                }

                return ochunk;
            } catch (Exception exception) {
                exception.printStackTrace();
                return null;
            }
        }
    }

    private void a(OChunk ochunk) {
        if (this.e != null) {
            try {
                this.e.b(this.h, ochunk);
            } catch (Exception exception) {
                exception.printStackTrace();
            }

        }
    }

    private void b(OChunk ochunk) {
        if (this.e != null) {
            ochunk.n = this.h.D(); //gregthegeek: should this line and the one below it be in a try-catch?
            this.e.a(this.h, ochunk);
        }
    }

    public void a(OIChunkProvider oichunkprovider, int i, int j) {
        OChunk ochunk = this.d(i, j);

        if (!ochunk.k) {
            ochunk.k = true;
            if (this.d != null) {
                this.d.a(oichunkprovider, i, j);
                ochunk.e();
            }
        }

    }

    public boolean a(boolean flag, OIProgressUpdate oiprogressupdate) {
        int i = 0;
        Iterator iterator = this.g.iterator();

        while (iterator.hasNext()) {
            OChunk ochunk = (OChunk) iterator.next();

            if (flag) {
                this.a(ochunk);
            }

            if (ochunk.a(flag)) {
                this.b(ochunk);
                ochunk.l = false;
                ++i;
                if (i == 24 && !flag) {
                    return false;
                }
            }
        }

        if (flag) {
            if (this.e == null) {
                return true;
            }

            this.e.b();
        }

        return true;
    }

    public boolean b() {
        if (!this.h.c) {
            for (int i = 0; i < 100; ++i) {
                if (!this.b.isEmpty()) {
                    Long olong = (Long) this.b.iterator().next();
                    OChunk ochunk = (OChunk) this.f.a(olong.longValue());

                    // Canary onChunkUnload hook
                    etc.getLoader().callHook(PluginLoader.Hook.CHUNK_UNLOAD, ochunk.chunk);
                    ochunk.d();
                    this.b(ochunk);
                    this.a(ochunk);
                    this.b.remove(olong);
                    this.f.d(olong.longValue());
                    this.g.remove(ochunk);
                }
            }

            if (this.e != null) {
                this.e.a();
            }
        }

        return this.d.b();
    }
	
    public OChunk regenerateChunk(int i, int j) {
        Long chunkCoordIntPair = OChunkCoordIntPair.a(i, j);
        
        // Unloading the chunk
        OChunk unloadedChunk = (OChunk) f.a(chunkCoordIntPair.longValue());

        if (unloadedChunk != null) {
            unloadedChunk.e(); // setChunkModified
            b(unloadedChunk); // saveChunkData
            a(unloadedChunk); // saveChunkExtraData
            b.remove(chunkCoordIntPair); // unloadQueue
            f.d(chunkCoordIntPair.longValue()); // id2ChunkMap.remove
            g.remove(unloadedChunk); // chunkList
        }
        
        // Generating the new chunk
        OChunk newChunk = d.d(i, j);

        f.a(chunkCoordIntPair, newChunk);
        g.add(newChunk);
        if (newChunk != null) {
            newChunk.c(); // onChunkLoad
            newChunk.d(); // onChunkUnload
        }
        newChunk.a(this, this, i, j); //populateChunk
        
        // Save the new chunk, overriding the old one
        a(newChunk);
        b(newChunk);
        newChunk.k = false;
        if (e != null) {
            e.b(); // saveExtraData
        }
        
        return newChunk;
    }

    public boolean c() {
        return !this.h.c;
    }

    public String d() {
        return "ServerChunkCache: " + this.f.a() + " Drop: " + this.b.size();
    }

    public List a(OEnumCreatureType oenumcreaturetype, int i, int j, int k) {
        return this.d.a(oenumcreaturetype, i, j, k);
    }

    public OChunkPosition a(OWorld oworld, String s, int i, int j, int k) {
        return this.d.a(oworld, s, i, j, k);
    }

    public int e() {
        return this.f.a();
    }

    public void e(int i, int j) {}
}
