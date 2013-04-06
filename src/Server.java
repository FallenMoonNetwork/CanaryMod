import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Server.java - Interface to server stuff Crow build 3.1.9.
 *
 * As of Canary b8, a lot of methods have been deprecated because they were
 * dimension-related. Please use the appropriate {@link World} functions
 * instead.
 *
 * @author James
 */
public class Server {

    private final ODedicatedServer server;

    /**
     * Creates a server
     *
     * @param server
     */
    public Server(OMinecraftServer server) {
        this.server = (ODedicatedServer) server;
    }

    /**
     * Sends a message to all users
     *
     * @param msg Message text to send
     */
    public void messageAll(String msg) {
        server.ad().a(new OPacket3Chat(msg));
    }

    /**
     * Bans specified player
     *
     * @param player Name of the player to ban
     * @deprecated Use one of the methods in {@link BanSystem} instead.
     */
    @Deprecated
    public void ban(String player) {
        etc.getDataSource().addBan(new Ban(player));
    }

    /**
     * Unbans specified user
     *
     * @param player Player name to unban
     *
     */
    public void unban(String player) {
        server.ad().e().b(player);
        etc.getDataSource().expireBan(new Ban(player));
    }

    /**
     * Uses the specified console command
     *
     * @param command
     * @throws OCommandNotFoundException if the command was not found.
     */
    public void useConsoleCommand(String command) {
        server.a(command, server);
    }

    /**
     * Uses the specified console command
     *
     * @param command command to use
     * @param player player to use command as
     * @throws OCommandNotFoundException if the command was not found.
     */
    public void useConsoleCommand(String command, Player player) {
        server.a(command, player.getEntity());
    }

    /**
     * Returns actual server time (-2^63 to 2^63-1)
     *
     * @deprecated Use {@link World#getTime()} instead.
     * @return time server time
     */
    @Deprecated
    public long getTime() {
        return getDefaultWorld().getTime();
    }

    /**
     * Returns current server time (0-24000)
     *
     * @deprecated use {@link World#getRelativeTime() } instead.
     * @return time server time
     */
    @Deprecated
    public long getRelativeTime() {
        return getDefaultWorld().getRelativeTime();
    }

    /**
     * Sets the actual server time
     *
     * @deprecated Use {@link World#setTime(long)} instead.
     * @param time time (-2^63 to 2^63-1)
     */
    @Deprecated
    public void setTime(long time) {
        getDefaultWorld().setTime(time);
    }

    /**
     * Sets the current server time
     *
     * @deprecated Use {@link World#setRelativeTime(long) } instead.
     * @param time time (0-24000)
     */
    @Deprecated
    public void setRelativeTime(long time) {
        getDefaultWorld().setRelativeTime(time);
    }

    /**
     * Returns the actual Minecraft Server
     *
     * @return
     */
    public ODedicatedServer getMCServer() {
        return server;
    }

    /**
     * Tries to match a character's name.
     * Use this for getting a <tt>Player</tt> from user input.
     *
     * @param name
     * @return
     */
    public Player matchPlayer(String name) {
        Player lastPlayer = null;

        name = name.toLowerCase();

        for (Player player : this.getPlayerList()) {
            String playerName = player.getName().toLowerCase();

            if (playerName.equals(name)) {
                // Perfect match found
                lastPlayer = player;
                break;
            }
            if (playerName.indexOf(name) != -1) {
                // Partial match
                if (lastPlayer != null) {
                    // Found multiple
                    return null;
                }
                lastPlayer = player;
            }
        }

        return lastPlayer;
    }

    /**
     * Returns specified player.
     * For fuzzy matching, use {@link #matchPlayer(String)}.
     *
     * @param name
     * @return
     */
    public Player getPlayer(String name) {
        OEntityPlayerMP user = server.ad().f(name);

        return user == null ? null : user.getPlayer();
    }

    /**
     * Returns the player list.
     *
     * @return list of players
     */
    public List<Player> getPlayerList() {
        List<Player> toRet = new ArrayList<Player>();

        for (OEntityPlayerMP oepmp : (List<OEntityPlayerMP>) server.ad().a) {
            toRet.add(oepmp.getPlayer());
        }
        return toRet;
    }

    /**
     * Returns a string with comma-seperated player names
     *
     * @return list of player names
     */
    public String getPlayerNames() {
        return server.ad().c();
    }

    /**
     * Returns the list of mobs in all open chunks.
     *
     * @deprecated Use {@link World#getMobList() } instead.
     * @return list of mobs
     */
    @Deprecated
    public List<Mob> getMobList() {
        return getDefaultWorld().getMobList();
    }

    /**
     * Returns the list of animals in all open chunks.
     *
     * @deprecated Use {@link World#getAnimalList() } instead.
     * @return list of animals
     */
    @Deprecated
    public List<Mob> getAnimalList() {
        return getDefaultWorld().getAnimalList();
    }

    /**
     * Returns the list of minecarts in all open chunks.
     *
     * @deprecated Use {@link World#getMinecartList() } instead.
     * @return list of minecarts
     */
    @Deprecated
    public List<Minecart> getMinecartList() {
        return getDefaultWorld().getMinecartList();
    }

    /**
     * Returns the list of boats in all open chunks.
     *
     * @deprecated Use {@link World#getBoatList() } instead.
     * @return list of boats
     */
    @Deprecated
    public List<Boat> getBoatList() {
        return getDefaultWorld().getBoatList();
    }

    /**
     * Returns the list of all entities in the server in open chunks.
     *
     * @deprecated Use {@link World#getEntityList() } instead.
     * @return list of entities
     */
    @Deprecated
    public List<BaseEntity> getEntityList() {
        return getDefaultWorld().getEntityList();
    }

    /**
     * Returns the list of all living entities (players, animals, mobs) in open
     * chunks.
     *
     * @deprecated Use {@link World#getLivingEntityList() } instead.
     * @return list of living entities
     */
    @Deprecated
    public List<LivingEntity> getLivingEntityList() {
        return getDefaultWorld().getLivingEntityList();
    }

    /**
     * Returns the list of vehicles in open chunks.
     *
     * @deprecated Use {@link World#getVehicleEntityList() } instead.
     * @return list of vehicles
     */
    @Deprecated
    public List<BaseVehicle> getVehicleEntityList() {
        return getDefaultWorld().getVehicleEntityList();
    }

    /**
     * Get the global spawn location
     *
     * @deprecated Use {@link World#getSpawnLocation() } instead
     * @return Location object for spawn
     */
    @Deprecated
    public Location getSpawnLocation() {
        return getDefaultWorld().getSpawnLocation();
    }

    /**
     * Sets the block
     *
     * @deprecated Use {@link World#setBlock(Block) } instead.
     * @param block
     * @return
     */
    @Deprecated
    public boolean setBlock(Block block) {
        return getDefaultWorld().setBlock(block);
    }

    /**
     * Returns the block at the specified location
     *
     * @deprecated Use {@link World#getBlockAt(int, int, int) } instead.
     * @param x
     * @param y
     * @param z
     * @return block
     */
    @Deprecated
    public Block getBlockAt(int x, int y, int z) {
        return getDefaultWorld().getBlockAt(x, y, z);
    }

    /**
     * Returns the block data at the specified coordinates
     *
     * @deprecated Use {@link World#getBlockData(int, int, int)} instead.
     * @param x x
     * @param y y
     * @param z z
     * @return block data
     */
    @Deprecated
    public int getBlockData(int x, int y, int z) {
        return getDefaultWorld().getBlockData(x, y, z);
    }

    /**
     * Sets the block data at the specified coordinates
     *
     * @deprecated Use {@link World#setBlockData(int, int, int, int) } instead.
     * @param x x
     * @param y y
     * @param z z
     * @param data data
     * @return true if it was successful
     */
    @Deprecated
    public boolean setBlockData(int x, int y, int z, int data) {
        return getDefaultWorld().setBlockData(x, y, z, data);
    }

    /**
     * Sets the block type at the specified location
     *
     * @deprecated Use {@link World#setBlockAt(int, int, int, int) } instead.
     * @param blockType
     * @param x
     * @param y
     * @param z
     * @return true if successful
     */
    @Deprecated
    public boolean setBlockAt(int blockType, int x, int y, int z) {
        return getDefaultWorld().setBlockAt(blockType, x, y, z);
    }

    /**
     * Returns the highest block Y
     *
     * @deprecated Use {@link World#getHighestBlockY(int, int) } instead.
     * @param x
     * @param z
     * @return highest block altitude
     */
    @Deprecated
    public int getHighestBlockY(int x, int z) {
        return getDefaultWorld().getHighestBlockY(x, z);
    }

    /**
     * Returns the block type at the specified location
     *
     * @deprecated Use {@link World#getBlockIdAt(int, int, int) } instead.
     * @param x
     * @param y
     * @param z
     * @return block type
     */
    @Deprecated
    public int getBlockIdAt(int x, int y, int z) {
        return getDefaultWorld().getBlockIdAt(x, y, z);
    }

    /**
     * Returns the complex block at the specified location. Null if there's no
     * complex block there. This will also find complex-blocks spanning multiple
     * spaces, such as double chests.
     *
     * @deprecated Use {@link World#getComplexBlock(Block) } instead.
     * @param block
     * @return complex block
     */
    @Deprecated
    public ComplexBlock getComplexBlock(Block block) {
        return getDefaultWorld().getComplexBlock(block);
    }

    /**
     * Returns the complex block at the specified location. Null if there's no
     * complex block there. This will also find complex-blocks spanning multiple
     * spaces, such as double chests.
     *
     * @deprecated Use {@link World#getComplexBlock(int, int, int) } instead.
     * @param x x
     * @param y y
     * @param z z
     * @return complex block
     */
    @Deprecated
    public ComplexBlock getComplexBlock(int x, int y, int z) {
        return getDefaultWorld().getComplexBlock(x, y, z);
    }

    /**
     * Returns the only complex block at the specified location. Null if there's
     * no complex block there.
     *
     * @deprecated Use {@link World#getOnlyComplexBlock(Block) } instead.
     * @param block
     * @return complex block
     */
    @Deprecated
    public ComplexBlock getOnlyComplexBlock(Block block) {
        return getDefaultWorld().getOnlyComplexBlock(block);
    }

    /**
     * Returns the complex block at the specified location. Null if there's no
     * complex block there.
     *
     * @deprecated Use {@link World#getOnlyComplexBlock(int, int, int)} instead.
     * @param x x
     * @param y y
     * @param z z
     * @return complex block
     */
    @Deprecated
    public ComplexBlock getOnlyComplexBlock(int x, int y, int z) {
        return getDefaultWorld().getOnlyComplexBlock(x, y, z);
    }

    /**
     * Drops an item at the specified location
     *
     * @deprecated Use {@link World#dropItem(Location, int) } instead.
     * @param loc
     * @param itemId
     */
    @Deprecated
    public void dropItem(Location loc, int itemId) {
        getDefaultWorld().dropItem(loc, itemId);
    }

    /**
     * Drops an item at the specified location
     *
     * @deprecated Use {@link World#dropItem(double, double, double, int)}
     * instead.
     * @param x
     * @param y
     * @param z
     * @param itemId
     *
     * @return returns the ItemEntity that was dropped
     */
    @Deprecated
    public ItemEntity dropItem(double x, double y, double z, int itemId) {
        return getDefaultWorld().dropItem(x, y, z, itemId);
    }

    /**
     * Drops an item with desired quantity at the specified location
     *
     * @deprecated Use {@link World#dropItem(Location, int, int) } instead.
     * @param loc
     * @param itemId
     * @param quantity
     *
     * @return returns the ItemEntity that was dropped
     */
    @Deprecated
    public ItemEntity dropItem(Location loc, int itemId, int quantity) {
        return getDefaultWorld().dropItem(loc, itemId, quantity);
    }

    /**
     * Drops an item with desired quantity at the specified location
     *
     * @deprecated Use {@link World#dropItem(double, double, double, int, int) }
     * instead.
     * @param x
     * @param y
     * @param z
     * @param itemId
     * @param quantity
     *
     * @return returns the ItemEntity that was dropped
     */
    @Deprecated
    public ItemEntity dropItem(double x, double y, double z, int itemId, int quantity) {
        return getDefaultWorld().dropItem(x, y, z, itemId, quantity);
    }

    /**
     * Forces the server to update the physics for blocks around the given block
     *
     * @deprecated Use {@link World#updateBlockPhysics(Block) } instead.
     * @param block the block that changed
     */
    @Deprecated
    public void updateBlockPhysics(Block block) {
        getDefaultWorld().updateBlockPhysics(block);
    }

    /**
     * Forces the server to update the physics for blocks around the given block
     *
     * @deprecated Use {@link World#updateBlockPhysics(int, int, int, int) }
     * instead.
     * @param x the X coordinate of the block
     * @param y the Y coordinate of the block
     * @param z the Z coordinate of the block
     * @param data the new data for the block
     */
    @Deprecated
    public void updateBlockPhysics(int x, int y, int z, int data) {
        getDefaultWorld().updateBlockPhysics(x, y, z, data);
    }

    /**
     * Adds a runnable to the Server Queue, so that it will be executed in the
     * Server Thread.
     *
     * @param r - the runnable
     */
    public void addToServerQueue(Runnable r) {
        addToServerQueue(r, 0L);
    }

    /**
     * Executes a runnable in the server thread after a specified delay.
     *
     * @param r - the runnable
     * @param delayMillis - the delay in milliseconds
     */
    public void addToServerQueue(Runnable r, long delayMillis) {
        OEntityTracker.add(r, delayMillis);
    }

    /**
     * Saves all player inventories to file
     */
    public void saveInventories() {
        server.ad().g();
    }

    /**
     * Checks to see whether or not the chunk containing the given block is
     * loaded into memory.
     *
     * @deprecated Use {@link World#isChunkLoaded(Block) } instead.
     * @param block the Block to check
     * @return true if the chunk is loaded
     */
    @Deprecated
    public boolean isChunkLoaded(Block block) {
        return getDefaultWorld().isChunkLoaded(block);
    }

    /**
     * Checks to see whether or not the chunk containing the given block
     * coordinates is loaded into memory.
     *
     * @deprecated Use {@link World#isChunkLoaded(int, int, int) } instead.
     * @param x a block x-coordinate
     * @param y a block y-coordinate
     * @param z a block z-coordinate
     * @return true if the chunk is loaded
     */
    @Deprecated
    public boolean isChunkLoaded(int x, int y, int z) {
        return getDefaultWorld().isChunkLoaded(x, y, z);
    }

    /**
     * Loads the chunk containing the given block. If the chunk does not exist,
     * it will be generated.
     *
     * @deprecated Use {@link World#loadChunk(Block) } instead.
     * @param block the Block to check
     */
    @Deprecated
    public void loadChunk(Block block) {
        getDefaultWorld().loadChunk(block);
    }

    /**
     * Loads the chunk containing the given block coordinates. If the chunk does
     * not exist, it will be generated.
     *
     * @deprecated Use {@link World#loadChunk(int, int, int) } instead.
     * @param x a block x-coordinate
     * @param y a block y-coordinate
     * @param z a block z-coordinate
     */
    @Deprecated
    public void loadChunk(int x, int y, int z) {
        getDefaultWorld().loadChunk(x, y, z);
    }

    /**
     * Loads the chunk containing the given chunk coordinates. If the chunk does
     * not exist, it will be generated.
     *
     * @deprecated Use {@link World#loadChunk(int, int) } instead.
     * @param x a chunk x-coordinate
     * @param z a chunk z-coordinate
     */
    @Deprecated
    public void loadChunk(int x, int z) {
        getDefaultWorld().loadChunk(x, z);
    }

    /**
     * Checks if the provided block is being powered through redstone
     *
     * @deprecated Use {@link World#isBlockPowered(Block) } instead.
     * @param block Block to check
     * @return true if the block is being powered
     */
    @Deprecated
    public boolean isBlockPowered(Block block) {
        return getDefaultWorld().isBlockPowered(block);
    }

    /**
     * Checks if the provided block is being powered through redstone
     *
     * @deprecated Use {@link World#isBlockPowered(int, int, int) } instead.
     * @param x a block x-coordinate
     * @param y a block y-coordinate
     * @param z a block z-coordinate
     * @return true if the block is being powered
     */
    @Deprecated
    public boolean isBlockPowered(int x, int y, int z) {
        return getDefaultWorld().isBlockPowered(x, y, z);
    }

    /**
     * Checks if the provided block is being indirectly powered through redstone
     *
     * @deprecated Use {@link World#isBlockIndirectlyPowered(Block) } instead.
     * @param block Block to check
     * @return true if the block is being indirectly powered
     */
    @Deprecated
    public boolean isBlockIndirectlyPowered(Block block) {
        return getDefaultWorld().isBlockIndirectlyPowered(block);
    }

    /**
     * Checks if the provided block is being indirectly powered through redstone
     *
     * @deprecated Use {@link World#isBlockIndirectlyPowered(int, int, int) }
     * instead.
     * @param x a block x-coordinate
     * @param y a block y-coordinate
     * @param z a block z-coordinate
     * @return true if the block is being indirectly powered
     */
    @Deprecated
    public boolean isBlockIndirectlyPowered(int x, int y, int z) {
        return getDefaultWorld().isBlockIndirectlyPowered(x, y, z);
    }

    /**
     * Set the thunder state
     *
     * @deprecated Use {@link World#setThundering(boolean) } instead;
     * @param thundering whether it should thunder
     */
    @Deprecated
    public void setThundering(boolean thundering) {
        getDefaultWorld().setThundering(thundering);
    }

    /**
     * Set the thunder ticks.
     *
     * @deprecated Use {@link World#setThunderTime(int) } instead.
     * @param ticks ticks of thunder
     */
    @Deprecated
    public void setThunderTime(int ticks) {
        getDefaultWorld().setThunderTime(ticks);
    }

    /**
     * Sets the rain state.
     *
     * @deprecated Use {@link World#setRaining(boolean) } instead.
     * @param raining whether it should rain
     */
    @Deprecated
    public void setRaining(boolean raining) {
        getDefaultWorld().setRaining(raining);
    }

    /**
     * Sets the rain ticks.
     *
     * @deprecated Use {@link World#setRainTime(int) } instead.
     * @param ticks ticks of rain
     */
    @Deprecated
    public void setRainTime(int ticks) {
        getDefaultWorld().setRainTime(ticks);
    }

    /**
     * Returns whether it's thundering
     *
     * @deprecated Use {@link World#isThundering() } instead.
     * @return whether it's thundering
     */
    @Deprecated
    public boolean isThundering() {
        return getDefaultWorld().isThundering();
    }

    /**
     * Returns the number of ticks to go till the end of the thunder
     *
     * @deprecated Use {@link World#getThunderTime() } instead.
     * @return the thunder ticks
     */
    @Deprecated
    public int getThunderTime() {
        return getDefaultWorld().getThunderTime();
    }

    /**
     * Returns whether it's raining
     *
     * @deprecated Use {@link World#isRaining() } instead.
     * @return whether it's raining
     */
    @Deprecated
    public boolean isRaining() {
        return getDefaultWorld().isRaining();
    }

    /**
     * Returns the number of ticks to go till the end of the rain
     *
     * @deprecated Use {@link World#getRainTime() } instead.
     * @return the rain ticks
     */
    @Deprecated
    public int getRainTime() {
        return getDefaultWorld().getRainTime();
    }

    /**
     * Returns the dimension new users spawn in
     *
     * @return the default dimension
     */
    public World getDefaultWorld() {
        OWorld oworld = server.getWorld(server.J(), 0);

        return oworld != null ? oworld.world : null;
    }

    /**
     * Returns the dimension at the given dimension. Due to the way the server
     * works, this returns the Nether's {@link World} if {@code dimension} is
     * -1, the normal dimension's {@link World} if {@code dimension} is 0, and
     * the End's {@link World} otherwise.
     *
     * @param dimension The dimension to return the World for
     * @return {@code dimension}'s World
     * @deprecated Use a combination of {@link #getWorld(java.lang.String)} and
     * {@link World.Dimension#toIndex()} instead:<blockquote><code>
     * // Get the normal dimension from world "test"<br>
     * World testNormal = etc.getServer().getWorld("test")[World.Dimension.NORMAL.toIndex()];
     * </code></blockquote>
     */
    public World getWorld(int dimension) {
        OWorld oworld = server.getWorld(server.J(), dimension);
        return oworld != null ? oworld.world : null;
    }

    /**
     * Adds a recipe to the crafting manager.
     *
     * @param item The item to return
     * @param recipe The recipe to return the item for.<br />
     * The recipe starts with (an array of) 1-3 strings of equal length
     * containing 1-3 characters, describing the recipe. Each character denotes
     * a different item stack. Following the (array of) strings are
     * character-item stack pairs, specifying which character corresponds to
     * which item stack. The item stack half of the pair can be
     * {@link Item.Type}, {@link Block.Type}, or even {@link Item} (the latter
     * is useful for specifying amounts and data values).<br />
     *
     * The following code would add a workbench:
     * <blockquote><code><pre>
     * etc.getServer().addRecipe(new Item(Item.Type.Workbench),
     *                           "##",
     *                           "##",
     *                           '#', Item.Type.Wood);
     * </pre></code></blockquote>
     * And the following code would add a piston:
     * <blockquote><code><pre>
     * etc.getServer().addRecipe(new Item(Item.Type.Piston),
     *                           "WWW",
     *                           "CIC",
     *                           "CRC",
     *                           'W', Item.Type.Wood,
     *                           'C', Item.Type.Cobblestone,
     *                           'I', Item.Type.IronIngot,
     *                           'R', Item.Type.RedStone);
     * </pre></code></blockquote>
     */
    public void addRecipe(Item item, Object... recipe) {
        for (int i = 0; i < recipe.length; i++) {
            if (recipe[i] instanceof Block.Type) {
                recipe[i] = OBlock.r[((Block.Type) recipe[i]).getType()];
            } else if (recipe[i] instanceof Item.Type) {
                recipe[i] = OItem.f[((Item.Type) recipe[i]).getId()];
            } else if (recipe[i] instanceof Item) {
                recipe[i] = ((Item) recipe[i]).getBaseItem();
            }
        }
        OCraftingManager.a().a(item.getBaseItem(), recipe);
    }

    /**
     * Adds a shapeless recipe to the crafting manager.
     * An example of a shapeless recipe is dyeing wool. It doesn't matter where
     * the needed items are, they just need to be there.
     *
     * @param item The item to return
     * @param recipe The recipe to return the item for.<br>
     * This is really just a list of items that need to be in the crafting
     * grid. This code would add the dyeing recipe for red wool:
     * <blockquote><code>
     * etc.getServer().addShapelessRecipe(Cloth.RED.getItem(),
     *         new Item(Item.Type.InkSack, 1, -1, 1), Cloth.WHITE.getItem());
     * </blockquote></code>
     */
    public void addShapelessRecipe(Item item, Object... recipe) {
        for (int i = 0; i < recipe.length; i++) {
            if (recipe[i] instanceof Block.Type) {
                recipe[i] = OBlock.r[((Block.Type) recipe[i]).getType()];
            } else if (recipe[i] instanceof Item.Type) {
                recipe[i] = OItem.f[((Item.Type) recipe[i]).getId()];
            } else if (recipe[i] instanceof Item) {
                recipe[i] = ((Item) recipe[i]).getBaseItem();
            }
        }
        OCraftingManager.a().b(item.getBaseItem(), recipe);
    }

    /**
     * Adds a smelting recipe to the furnace recipes.
     * {@code from} is the item that is put into the furnace, and should have
     * amount 1. {@code to} is the result after smelting.
     *
     * @param from The inserted item
     * @param to The resulting item
     * @throws IllegalArgumentException if the amount of {@code from} doesn't
     * equal 1.
     */
    public void addSmeltingRecipe(Item from, Item to) {
        this.addSmeltingRecipe(from, to, 0F);
    }

    /**
     * Adds a smelting recipe to the furnace recipes.
     * {@code from} is the item that is put into the furnace, and should have
     * amount 1. {@code to} is the result after smelting.
     *
     * @param from The inserted item
     * @param to The resulting item
     * @param xp The amount of XP you get
     * @throws IllegalArgumentException if the amount of {@code from} doesn't
     * equal 1.
     */
    public void addSmeltingRecipe(Item from, Item to, float xp) {
        if (from.getAmount() != 1) {
            throw new IllegalArgumentException("The 'from' amount should be 1");
        }
        OFurnaceRecipes.a().a(from.getItemId(), to.getBaseItem(), xp);
    }

    /**
     * Returns the list with recipes, as kept by the crafting manager.
     *
     * @return a list containing {@code OIRecipe} instances.
     */
    @SuppressWarnings("unchecked")
    public List<OIRecipe> getRecipeList() {
        return OCraftingManager.a().b();
    }

    /**
     * Loads the world with the specified name using defaults and returns it. If
     * the world already is loaded, just return the world.
     *
     * @param name The name of the world to load.
     * @return A {@link World}-array containing the 3 dimensions of the
     * specified <tt>World</tt>.
     * @see World.Dimension#toIndex()
     */
    public World[] loadWorld(String name) {
        return loadWorld(name, World.Type.DEFAULT);
    }

    /**
     * Loads the world with the specified name and type and returns it. If the
     * world already is loaded, just return the world.
     *
     * @param name The name of the world to load.
     * @param type The type of the world to load. If a world with the specified
     * name already exists, this argument is ignored.
     * @return A {@link World}-array containing the 3 dimensions of the
     * specified <tt>World</tt>.
     * @see World.Dimension#toIndex()
     */
    public World[] loadWorld(String name, World.Type type) {
        return loadWorld(name, type, new Random().nextLong());
    }

    /**
     * Loads the world with the specified name, type and seed and returns it. If
     * the world already is loaded, just return the world.
     *
     * @param name The name of the world to load.
     * @param type The type of the world to load. If a world with the specified
     * name already exists, this argument is ignored.
     * @param seed The seed of the world to load. If a world with the specified
     * name already exists, this argument is ignored.
     * @return A {@link World}-array containing the 3 dimensions of the
     * specified <tt>World</tt>.
     * @see World.Dimension#toIndex()
     */
    public World[] loadWorld(String name, World.Type type, long seed) {
        return this.loadWorld(name, type, seed, "");
    }

    /**
     * Loads the world with the specified name, type and seed and returns it. If
     * the world already is loaded, just return the world.
     *
     * @param name The name of the world to load.
     * @param type The type of the world to load. If a world with the specified
     * name already exists, this argument is ignored.
     * @param seed The seed of the world to load. If a world with the specified
     * name already exists, this argument is ignored.
     * @param generatorSettings The generator settings for the world to load. If
     * a world with the specified name already exists, this argument is ignored.
     * @return A {@link World}-array containing the 3 dimensions of the
     * specified <tt>World</tt>.
     * @see World.Dimension#toIndex()
     */
    public World[] loadWorld(String name, World.Type type, long seed, String generatorSettings) {
        if (!server.worlds.containsKey(name)) {
            server.a(name, name, seed, type.getNative(), generatorSettings);
        }

        OWorldServer[] nativeWorlds = server.worlds.get(name);
        return new World[]{nativeWorlds[0].world, nativeWorlds[1].world, nativeWorlds[2].world};
    }

    /**
     * Checks whether the world with the given name is loaded.
     *
     * @param name The name of the world to check for.
     * @return <tt>true</tt> if the world is loaded, <tt>false</tt> otherwise.
     */
    public boolean isWorldLoaded(String name) {
        return server.worlds.containsKey(name);
    }

    /**
     * Gives the world with the specified name.
     *
     * @param name The name of the world to get.
     * @return a {@link World}-array containing the 3 dimensions of the
     * specified world if it exists, <tt>null</tt> otherwise.
     * @see World.Dimension#toIndex()
     */
    public World[] getWorld(String name) {
        return this.isWorldLoaded(name) ? this.loadWorld(name) : null;
    }

    /**
     * Get the configuration manager for the given world
     * @param world
     * @return
     */
    public PlayerManager getPlayerManager(World world) {
        return world.getPlayerManager();
    }

    /**
     * Get the version of Minecarft this CanaryMod is for.
     * @return String of the version in the format major.minor[.build]
     */
    public String getMCVersion() {
        return server.x();
    }

    /**
     * Returns a <tt>Set&lt;String&gt;</tt> with the names of loaded worlds.
     * Actually just a shortcut for <tt>etc.getMCServer().worlds.keySet();</tt>
     *
     * @return a <tt>Set&lt;String&gt;</tt> with the world names.
     */
    public Set<String> getLoadedWorldNames() {
        return server.worlds.keySet();
    }

    /**
     * Returns a <tt>Set&lt;World[]&gt;</tt> with the currently loaded worlds.
     *
     * @return a <tt>Set&lt;World[]&gt;</tt> with the loaded worlds.
     */
    public Set<World[]> getLoadedWorld() {
        Set<World[]> loadedWorlds = new HashSet<World[]>(server.worlds.size());

        for (OWorldServer[] aows : server.worlds.values()) {
            World[] worlds = new World[aows.length];
            for (int i = 0; i < aows.length; i++) {
                worlds[i] = aows[i].world;
            }

            loadedWorlds.add(worlds);
        }

        return loadedWorlds;
    }
}
