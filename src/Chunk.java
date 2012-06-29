

/**
 * Chunk.java - Interface to chunks, especially for generating chunks.
 * @author phi
 */
public class Chunk {
    public final OChunk chunk;

    /**
     * Instantiated this wrapper around OChunk
     * @param chunk the OChunk to wrap
     */
    public Chunk(OChunk chunk) {
        this.chunk = chunk;
    }

    /**
     * Generates a new chunk for the specified world. This does not register the generated chunk at the world.
     * @param world
     * @param x
     * @param z
     * @return new chunk
     */
    public static Chunk getNewChunk(OWorld world, int x, int z) {
        return getNewChunk(world, new byte[32768], x, z);
    }

    /**
     * Generates a new chunk for the specified world. This does not register the generated chunk at the world.
     * @param world
     * @param blocks
     * @param x
     * @param z
     * @return new chunk
     */
    public static Chunk getNewChunk(OWorld world, byte[] blocks, int x, int z) {
        return new OChunk(world, blocks, x, z).chunk;
    }
    
    /**
     * Regenerates the world according to the world seed.
     * @param world
     * @param x
     * @param z
     * @return new chunk
     */
    public static Chunk regenerateChunk(OWorld world, int x, int z) {
        return ((OWorldServer)world).G.regenerateChunk(x, z).chunk;
    }

    /**
     * Returns whether this chunk is loaded
     * @return true if chunk is loaded
     */
    public boolean isLoaded() {
        return chunk.d;
    }

    /**
     * Returns this chunks's world
     * @return world
     */
    public World getWorld() {
        return chunk.e.world;
    }

    /**
     * Gets the x location of the chunk.
     * @return x
     */
    public int getX() {
        return chunk.g;
    }

    /**
     * Gets the z location of the chunk.
     * @return z
     */
    public int getZ() {
        return chunk.h;
    }

    /**
     * Sets the block type at the specified location
     * @param x
     * @param y
     * @param z
     * @param id block type
     * @return true if successful
     */
    public boolean setBlockIdAt(int x, int y, int z, int id) {
        if (isLoaded()) {
            // handles notification
            return getWorld().setBlockAt(id, x | (getX() << 4), y, x | (getZ() << 4));
        } else {
            return chunk.a(x, y, z, id);
        }
    }

    /**
     * Returns the block type at the specified location
     * @param x
     * @param y
     * @param z
     * @return block type
     */
    public int getBlockIdAt(int x, int y, int z) {
        return chunk.a(x, y, z);
    }

    /**
     * Sets the block data at the specified location
     * @param x
     * @param y
     * @param z
     * @param data block data
     */
    public void setBlockDataAt(int x, int y, int z, int data) {
        if (isLoaded()) {
            // handles notification
            getWorld().setBlockData(x | (getX() << 4), y, x | (getZ() << 4), data);
        } else {
            chunk.b(x, y, z, data);
        }
    }

    /**
     * Returns the block data at the specified location
     * @param x
     * @param y
     * @param z
     * @return block data
     */
    public int getBlockDataAt(int x, int y, int z) {
        return chunk.b(x, y, z);
    }

    /**
     * Gets the chunk's biome data byte array
     * 
     * @return biomedata
     */
    public byte[] getBiomeData() {
        return chunk.l();
    }

    /**
     * Sets the chunk's biome data (needs to be byte[256])
     * 
     * @param biomedata
     */
    public void setBiomeData(byte[] biomedata) {
        if (biomedata.length != 256) return;
        chunk.a(biomedata);
    }

    /**
     * resends chunk data to clients
     */
    public void update() {
        etc.getMCServer().h.a(new OPacket51MapChunk(chunk, true, 0));
    }

    /**
     * gets the wrapped chunk
     * 
     * @return chunk
     */
    public OChunk getChunk() {
        return chunk;
    }
}
