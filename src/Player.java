import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Player.java - Interface so mods don't have to update often.
 *
 * @author James
 */
public class Player extends HumanEntity implements MessageReceiver {

    private static final Logger log = Logger.getLogger("Minecraft-Server");
    private int id = -1;
    private String prefix = "";
    private String[] commands = new String[] { "" };
    private ArrayList<String> groups = new ArrayList<String>();
    private String[] ips = new String[] { "" };
    private int restrictions = 0;
    private boolean muted = false;
    private static Map<Player, List<String>> onlyOneUseKitsGlobal = new DefaultedMap(new HashMap<Player, List<String>>()) {
        @Override
        protected List<String> getDefaultValue() {
            return new ArrayList<String>();
        }
    };
    private static Map<Player, Map<Kit, Long>> cooldownKitsGlobal = new DefaultedMap(new HashMap<Player, Map<Kit, Long>>()) {
        @Override
        protected Map<Kit, Long> getDefaultValue() {
            return new HashMap<Kit, Long>();
        }
    };
    private List<String> onlyOneUseKits;
    private Map<Kit, Long> cooldownKits;
    private static Pattern badChatPattern = Pattern.compile("[\u00a7\u2302\u00D7\u00AA\u00BA\u00AE\u00AC\u00BD\u00BC\u00A1\u00AB\u00BB]");
    private String offlineName = ""; // Allows modify command to work on offline players
    private long lastMessage;
    private int spamTicker;

    /**
     * Creates an empty player. Add the player by calling {@link #setUser(OEntityPlayerMP)}
     */
    public Player() {}

    public Player(OEntityPlayerMP player) {
        setUser(player);
    }

    /**
     * Returns the entity we're wrapping.
     *
     * @return
     */
    @Override
    public OEntityPlayerMP getEntity() {
        return (OEntityPlayerMP) entity;
    }

    /**
     * Returns if the player is still connected
     *
     * @return
     */
    public boolean isConnected() {
        // SRG return !getEntity().field_71135_a.field_72576_c;
        return !getEntity().a.b;
    }

    /**
     * Kicks player with the specified reason
     *
     * @param reason
     */
    public void kick(String reason) {
        // SRG getEntity().field_71135_a.func_72565_c(reason);
        getEntity().a.c(reason);
    }

    /**
     * Sends player a notification
     *
     * @param message
     */
    @Override
    public void notify(String message) {
        if (message.length() > 0) {
            sendMessage(Colors.Rose + message);
        }
    }

    /**
     * Sends a message to the player
     *
     * @param message
     */
    public void sendMessage(String message) {
        if (!etc.getInstance().isColorForced() && this.wantsColorDisabled()) {
            message = Colors.strip(message);
        }
        // SRG getEntity().field_71135_a.msg(message);
        getEntity().a.msg(message);
    }

    /**
     * Makes player send message.
     *
     * @param message
     */
    public void chat(String message) {
        if (message.length() > 100) {
            kick("Chat message too long");
            return;
        }
        message = message.trim();
        Matcher m = badChatPattern.matcher(message);
        String out = message;
        if (m.find() && !this.canIgnoreRestrictions()) {
            out = message.replaceAll(m.group(), "");
        }
        message = out;

        PluginLoader.HookResult protectFromSpam = etc.getInstance().getProtectFromSpam();

        if (protectFromSpam == PluginLoader.HookResult.ALLOW_ACTION
                && this.checkSpam())
            this.kick("Spamming.");

        if (message.startsWith("/")) {
            command(message);
        } else {
            if (isMuted()) {
                sendMessage(Colors.Rose + "You are currently muted.");
                return;
            }

            if (protectFromSpam == PluginLoader.HookResult.DEFAULT_ACTION
                    && this.checkSpam())
                this.kick("Spamming.");

            List<Player> receivers = etc.getServer().getPlayerList();
            StringBuilder chatPrefix = new StringBuilder("<").append(getColor()).append(getName()).append(Colors.White).append(">");
            StringBuilder sbMessage = new StringBuilder(message);
            HookParametersChat parametersChat = (HookParametersChat) etc.getLoader().callHook(PluginLoader.Hook.CHAT, new HookParametersChat(this, chatPrefix, sbMessage, receivers));

            if ((parametersChat.isCanceled())) {
                return;
            }

            receivers = parametersChat.getReceivers();
            chatPrefix = parametersChat.getPrefix();
            sbMessage = parametersChat.getMessage();

            String chat = chatPrefix.toString() + " " + sbMessage.toString();

            log.log(Level.INFO, "<{0}> {1}", new Object[]{getName(), sbMessage.toString()});

            //etc.getServer().messageAll(chat);
            for (Player player : receivers) {
                if (player.getChatPreference() == 0 || etc.getInstance().isChatForced()) {
                    if (chatPrefix.length() + message.length() >= 119) {
                        player.sendMessage(chatPrefix.toString());
                        player.sendMessage(sbMessage.toString());
                    } else {
                        player.sendMessage(chat);
                    }
                }
            }
        }
    }

    /**
     * Makes player use command.
     *
     * @param command
     *
     */
    public void command(String command) {
        try {
            if (etc.getInstance().isLogging()) {
                log.log(Level.INFO, "Command used by {0}: {1}", new Object[]{getName(), command});
            }

            String[] split = command.split(" ");

            if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.COMMAND, this, split)) {
                return;
            } // No need to go on, commands were parsed.
            String cmd = split[0].toLowerCase();
            if (!canUseCommand(cmd) && !cmd.startsWith("/#")) {
                if (etc.getInstance().showUnknownCommand()) {
                    sendMessage(Colors.Rose + "Unknown command.");
                }
                return;
            }
            if (command.startsWith("/#") && this.isOp()) {
                String str = command.substring(2);

                log.log(Level.INFO, "{0} issued server command: {1}", new Object[]{getName(), str});
                etc.getServer().useConsoleCommand(str);
                return;
            }

            // Remove '/' before checking.
            if (!ServerConsoleCommands.parseServerConsoleCommand(this, cmd.substring(1), split) && !PlayerCommands.parsePlayerCommand(this, cmd.substring(1), split)) {
                log.log(Level.INFO, "{0} tried command {1}", new Object[]{getName(), command});
                if (etc.getInstance().showUnknownCommand()) {
                    sendMessage(Colors.Rose + "Unknown command");
                }
            }

        } catch (Throwable ex) { // Might as well try and catch big exceptions
            // before the server crashes from a stack
            // overflow or something
            log.log(Level.SEVERE, "Exception in command handler (Report this on github unless you did something dumb like enter letters as numbers):", ex);
            if (isAdmin()) {
                sendMessage(Colors.Rose + "Exception occured. Check the server for more info.");
            }
        }
    }

    /**
     * Returns true if this player can use the specified command
     *
     * @param command
     * @return
     */
    public boolean canUseCommand(String command) {
        PluginLoader.HookResult res = (PluginLoader.HookResult) etc.getLoader().callHook(PluginLoader.Hook.COMMAND_CHECK, this, command);

        if (res == PluginLoader.HookResult.DEFAULT_ACTION) {
            return canUseCommandByDefault(command);
        }// If someone wants to use
        // false instead, this can be
        // done with low priority
        // plugin.
        if (res == PluginLoader.HookResult.ALLOW_ACTION) {
            return true;
        }
        return false;
    }

    /**
     * Returns true if this player can use the specified command This method ignores permission management plugins and shouldn't be used by other plugins.
     *
     * @param command
     * @return
     */

    public boolean canUseCommandByDefault(String command) {
        for (String str : commands) {
            if (str.equalsIgnoreCase(command)) {
                return true;
            }
        }

        for (String str : groups) {
            Group g = etc.getDataSource().getGroup(str);

            if (g != null) {
                if (recursiveUseCommand(g, command)) {
                    return true;
                }
            }
        }

        if (hasNoGroups()) {
            Group def = etc.getInstance().getDefaultGroup();

            if (def != null) {
                if (recursiveUseCommand(def, command)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean recursiveUseCommand(Group g, String command) {
        for (String str : g.Commands) {
            if (str.equalsIgnoreCase(command) || str.equals("*")) {
                return true;
            }
        }

        if (g.InheritedGroups != null) {
            for (String str : g.InheritedGroups) {
                Group g2 = etc.getDataSource().getGroup(str);

                if (g2 != null) {
                    if (recursiveUseCommand(g2, command)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Checks to see if this player is in the specified group
     *
     * @param group
     * @return
     */
    public boolean isInGroup(String group) {
        if (group != null) {
            if (etc.getInstance().getDefaultGroup() != null) {
                if (group.equalsIgnoreCase(etc.getInstance().getDefaultGroup().Name)) {
                    return true;
                }
            }
        }
        for (String str : groups) {
            if (recursiveUserInGroup(etc.getDataSource().getGroup(str), group)) {
                return true;
            }
        }
        return false;
    }

    private boolean recursiveUserInGroup(Group g, String group) {
        if (g == null || group == null) {
            return false;
        }

        if (g.Name.equalsIgnoreCase(group)) {
            return true;
        }

        if (g.InheritedGroups != null) {
            for (String str : g.InheritedGroups) {
                if (g.Name.equalsIgnoreCase(str)) {
                    return true;
                }

                Group g2 = etc.getDataSource().getGroup(str);

                if (g2 != null) {
                    if (recursiveUserInGroup(g2, group)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Returns true if this player has control over the other player
     *
     * @param player
     * @return true if player has control
     */
    public boolean hasControlOver(Player player) {
        boolean isInGroup = false;

        if (player.hasNoGroups()) {
            return true;
        }
        for (String str : player.getGroups()) {
            if (isInGroup(str)) {
                isInGroup = true;
            } else {
                continue;
            }
            break;
        }

        return isInGroup;
    }

    /**
     * Returns the IP of this player
     *
     * @return
     */
    public String getIP() {
        // SRG String ip = getEntity().field_71135_a.field_72575_b.func_74430_c().toString();
        String ip = getEntity().a.a.c().toString();
        return ip.substring(1,ip.lastIndexOf(":"));
    }

    /**
     * Returns true if this player is an admin.
     *
     * @return
     */
    public boolean isAdmin() {
        return restrictions >= 2;
    }

    /**
     * Returns true if this player is an admin.
     *
     * @return
     * @deprecated Use {@link #isAdmin()} instead.
     */
    @Deprecated
    public boolean getAdmin() {
        return isAdmin();
    }

    /**
     * Sets whether or not this player is an administrator
     *
     * @param admin
     */
    public void setAdmin(boolean admin) {
        if(admin) {
            restrictions = 2;
        } else if(restrictions >= 2) {
            restrictions = 1;
        }
    }

    /**
     * Returns false if this player can not modify terrain, edit chests, etc.
     *
     * @return
     */
    public boolean canBuild() {
        return restrictions >= 0;
    }

    /**
     * Don't use this, use canBuild()
     *
     * @return
     */
    @Deprecated
    public boolean canModifyWorld() {
        return canBuild();
    }

    /**
     * Sets whether or not this player can modify the dimension terrain
     *
     * @param canModifyWorld
     */
    public void setCanModifyWorld(boolean canModifyWorld) {
        if(canModifyWorld) {
            if(restrictions < 0) {
                restrictions = 0;
            }
        } else {
            restrictions = -1;
        }
    }

    /**
     * Set allowed commands
     *
     * @return
     */
    public String[] getCommands() {
        return commands;
    }

    /**
     * Sets this player's allowed commands
     *
     * @param commands
     */
    public void setCommands(String[] commands) {
        this.commands = commands;
    }

    /**
     * Returns this player's groups
     *
     * @return
     */
    public String[] getGroups() {
        String[] strGroups = new String[groups.size()];

        groups.toArray(strGroups);
        return strGroups;
    }

    /**
     * Sets this player's groups
     *
     * @param groups
     */
    public void setGroups(String[] groups) {
        this.groups.clear();
        for (String s : groups) {
            if (s.length() > 0) {
                this.groups.add(s);
            }
        }
    }

    /**
     * Adds the player to the specified group
     *
     * @param group
     *            group to add player to
     */
    public void addGroup(String group) {
        groups.add(group);
    }

    /**
     * Removes specified group from list of groups
     *
     * @param group
     *            group to remove
     */
    public void removeGroup(String group) {
        groups.remove(group);
    }

    /**
     * Returns the sql ID.
     *
     * @return
     */
    public int getSqlId() {
        return id;
    }

    /**
     * Sets the sql ID. Don't touch this.
     *
     * @param id
     */
    public void setSqlId(int id) {
        this.id = id;
    }

    /**
     * If the user can ignore restrictions this will return true. Things like item amounts and such are unlimited, etc.
     *
     * @return
     */
    public boolean canIgnoreRestrictions() {
        return restrictions >= 1;
    }

    /**
     * Don't use. Use canIgnoreRestrictions()
     *
     * @return
     */
    @Deprecated
    public boolean ignoreRestrictions() {
        return canIgnoreRestrictions();
    }

    /**
     * Sets ignore restrictions
     *
     * @param ignoreRestrictions
     */
    public void setIgnoreRestrictions(boolean ignoreRestrictions) {
        if(ignoreRestrictions) {
            if(restrictions < 1) {
                restrictions = 1;
            }
        } else if(restrictions > 0) {
            restrictions = 0;
        }
    }

    /**
     * Returns allowed IPs
     *
     * @return
     */
    public String[] getIps() {
        return ips;
    }

    /**
     * Sets allowed IPs
     *
     * @param ips
     */
    public void setIps(String[] ips) {
        this.ips = ips;
    }

    /**
     * Returns the correct color/prefix for this player
     *
     * @return
     */
    public String getColor() {
        if (prefix != null) {
            if (!prefix.equals("")) {
                return Colors.Marker + prefix;
            }
        }
        if (groups.size() > 0) {
            Group group = etc.getDataSource().getGroup(groups.get(0));

            if (group != null) {
                return Colors.Marker + group.Prefix;
            }
        }
        Group def = etc.getInstance().getDefaultGroup();

        return def != null ? Colors.Marker + def.Prefix : "";
    }

    /**
     * Returns the prefix. NOTE: Don't use this, use getColor() instead.
     *
     * @return
     */
    public String getPrefix() {
        return prefix;
    }

    /**
     * Sets the prefix.
     *
     * @param prefix
     */
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    /**
     * Get players name.
     * @return The offline name
     */
    public String getOfflineName() {
        if (getEntity() != null) {
            return getName();
        } else {
            return offlineName;
        }
    }

    /**
     * Sets offline player name.
     * @param name The offline name
     */
    public void setOfflineName(String name) {
        offlineName = name;
    }

    /**
     * Gets the full name prefix+name.
     * @return The name including prefix
     */
    public String getFullName() {
        return this.getColor() + this.getName();
    }

    /**
     * Gets the name that shows on PlayerList (tab).
     * @param show Whether to show the player
     * @return PlayerListEntry The entry to display.
     */
    public PlayerlistEntry getPlayerlistEntry(boolean show) {
        String name;

        if (etc.getInstance().isPlayerList_colors()) {
            name = this.getFullName();
        } else {
            name = this.getName();
        }
        PlayerlistEntry entry = new PlayerlistEntry(name, this.getPing(), show);

        return (PlayerlistEntry) etc.getLoader().callHook(PluginLoader.Hook.GET_PLAYERLISTENTRY, this, entry);
    }

    /**
     * Gets player ping (as shown in playerlist).
     * @return The ping
     */
    public int getPing() {
        // SRG return this.getEntity().field_71138_i;
        return this.getEntity().i;
    }

    /**
     * Gets the actual user class.
     *
     * @return
     */
    public OEntityPlayerMP getUser() {
        return this.getEntity();
    }

    /**
     * Sets the user. Don't use this.
     *
     * @param player
     */
    protected void setUser(OEntityPlayerMP player) {
        entity = player;
        inventory = new PlayerInventory(this);
        onlyOneUseKits = onlyOneUseKitsGlobal.get(this);
        cooldownKits = cooldownKitsGlobal.get(this);
    }

    @Override
    public void teleportTo(double x, double y, double z, float rotation, float pitch) {
        // If player is in vehicle - eject them before they are teleported.
        if (this.getRidingEntity() != null) {
            this.dismount();
        }

        // SRG this.getEntity().field_71135_a.func_72569_a(x, y, z, rotation, pitch);
        this.getEntity().a.a(x, y, z, rotation, pitch);
    }

    @Override
    public void teleportTo(BaseEntity entity) {
        this.teleportTo(entity.getLocation());
    }

    @Override
    public void teleportTo(Location loc) {
        if (!loc.getWorld().equals(this.getWorld())) {
            this.switchWorlds(loc.getWorld());
        }

        super.teleportTo(loc);
    }

    /**
     * Returns true if the player is muted.
     *
     * @return Whether the player is muted.
     */
    public boolean isMuted() {
        return muted;
    }

    /**
     * Toggles mute.
     *
     * @return Whether the player is now muted.
     */
    public boolean toggleMute() {
        muted = !muted;
        return muted;
    }

    /**
     * Checks to see if this player is in any groups.
     *
     * @return true if this player isn't in any group.
     */
    public boolean hasNoGroups() {
        if (groups.isEmpty()) {
            return true;
        }
        if (groups.size() == 1) {
            return groups.get(0).equals("");
        }
        return false;
    }

    /**
     * Returns whether or not this entity is blocking with/using the item in
     * their hand. (e.g. sword, bow, food)
     * @return true if blocking
     */
    // TODO pull up
    public boolean isBlocking() {
        // SRG return getEntity().func_71039_bw();
        return getEntity().bq();
    }

    /**
     * Returns the one-use only kits
     *
     * @return List of kit names
     */
    public List<String> getOnlyOneUseKits() {
        return onlyOneUseKits;
    }

    /**
     * Add a cooldown kit to the cooldown map.
     * @param kit The <tt>Kit</tt> to add to the map
     * @param delay The delay after which this kit can be used again
     */
    public void addCooldownKit(Kit kit, int delay) {
        this.cooldownKits.put(kit, System.currentTimeMillis() + delay * 50);
    }

    /**
     * Remove a cooldown kit from the cooldown map.
     * This makes it available to the player for use again immediately.
     * @param kit The <tt>Kit</tt> to remove.
     */
    public void removeCooldownKit(Kit kit) {
        this.cooldownKits.remove(kit);
    }

    /**
     * Check whether a player can use the cooldown kit.
     * If the cooldown has expired, this method also removes the kit it from
     * the cooldown map.
     * @param kit The <tt>Kit</tt> to check for.
     * @return <tt>false</tt> if the kit's cooldown period is in effect,
     * <tt>true</tt> otherwise.
     */
    public boolean canUseCooldownKit(Kit kit) {
        if (!this.cooldownKits.containsKey(kit)) {
            return true;
        }

        boolean allow = this.cooldownKits.get(kit) < System.currentTimeMillis();
        if (allow) {
            this.removeCooldownKit(kit);
        }
        return allow;
    }

    /**
     * Switch to the specified dimension at the according position.
     * @param world The world to switch to
     */
    public void switchWorlds(World world) {
        ODedicatedServer mcServer = (ODedicatedServer) etc.getMCServer();
        OEntityPlayerMP ent = this.getEntity();

        // Nether is not allowed, so shush
        // SRG if (world.getType() == World.Dimension.NETHER && !mcServer.func_71332_a("allow-nether", true)) {
        if (world.getType() == World.Dimension.NETHER && !mcServer.a("allow-nether", true)) {
            return;
        }
        // The End is not allowed, so shush
        // SRG if (world.getType() == World.Dimension.END && !mcServer.func_71332_a("allow-end", true)) {
        if (world.getType() == World.Dimension.END && !mcServer.a("allow-end", true)) {
            return;
        }
        // Dismount first or get buggy
        if (this.getRidingEntity() != null) {
            this.dismount();
        }

        // Collect world switch achievement ?
        if (world.getType() != World.Dimension.NORMAL) {
            // SRG ent.func_71029_a((OStatBase) (world.getType() == World.Dimension.END ? OAchievementList.field_76002_B : OAchievementList.field_76029_x));
            ent.a((OStatBase) (world.getType() == World.Dimension.END ? OAchievementList.B : OAchievementList.x));
        }

        if (!world.getName().equals(this.getWorld().getName())
                // End -> Normal: respawn
                || world.getType() == World.Dimension.NORMAL
                && this.getWorld().getType() == World.Dimension.END) {
            Location loc = this.getLocation();
            loc.setWorld(world); // teleport to new world

            // SRG mcServer.func_71203_ab().func_72368_a(ent, loc.dimension, true, loc); // Respawn with location
            mcServer.af().a(ent, loc.dimension, true, loc); // Respawn with location
        } else {
            // SRG mcServer.func_71203_ab().sendPlayerToOtherDimension(ent, world.getType().getId(), false);
            mcServer.af().sendPlayerToOtherDimension(ent, world.getType().getId(), false);
            this.updateXP();
        }
    }

    @Override
    public String toString() {
        return String.format("Player[name=%s]", getName());
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Player)) {
            return false;
        }
        final Player other = (Player) obj;

        return getName() == null ? other.getName() == null : getName().equals(other.getName());
    }

    @Override
    public int hashCode() {
        int hash = 7;

        hash = 89 * hash + (this.getName() != null ? this.getName().hashCode() : 0);
        return hash;
    }

    /**
     * Set creative mode for this Player
     *
     * @param i
     */
    public void setCreativeMode(int i) {
        // SRG OEnumGameType gameType = OEnumGameType.func_77146_a(i);
        OEnumGameType gameType = OEnumGameType.a(i);
        // SRG if (getEntity().field_71134_c.func_73081_b() != gameType) {
        if (getEntity().c.b() != gameType) {
            // SRG getEntity().field_71134_c.func_73076_a(gameType);
            getEntity().c.a(gameType);
            // SRG getEntity().field_71135_a.func_72567_b((OPacket) (new OPacket70GameEvent(3, gameType.field_77154_e)));
            getEntity().a.b((OPacket) (new OPacket70GameEvent(3, gameType.e)));
        }
    }

    /**
     * Get creative mode for this Player
     *
     * @return i
     */
    public int getCreativeMode() {
        // SRG return this.getEntity().field_71134_c.func_73081_b().field_77154_e;
        return this.getEntity().c.b().e;
    }

    /**
     * check if the player is in adventure mode
     * @return true = it is in this mode<br>false = it is not in this mode
     */
    public boolean isAdventureMode(){
        return getCreativeMode() == 2;
    }

    /**
     * Check if the player is in survival mode
     * @return true = it is in this mode<br>false = it is not in this mode
     */
    public boolean isSurvivalMode(){
        return getCreativeMode() == 0;
    }

    /**
     * Check if the player is in creative mode
     * @return true = it is in this mode<br>false = it is not in this mode
     */
    public boolean isCreativeMode(){
        return getCreativeMode() == 1;
    }

    /**
     * Check to see if this Player is in creative mode
     *
     * @return <tt>true</tt> if the given Player is in creative mode,
     *          <tt>null</tt> otherwise.
     * @deprecated Depend on {@link #getCreativeMode()} instead
     */
    @Deprecated
    public boolean getMode() {
        if (this.getCreativeMode() == 1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Refresh this Player's mode.
     * @deprecated This method has a confusing name.
     * Use a combination of {@link #setCreativeMode(int)} and
     * {@link World#getGameMode()} instead.
     */
    @Deprecated
    public void refreshCreativeMode() {
        this.setCreativeMode(this.getWorld().getGameMode());
    }

    /**
     * Returns whether falling is disabled.
     * @return the disableFalling state
     * @deprecated Misleading name. See {@link #canFly()}
     */
    @Deprecated
    public boolean isFallingDisabled() {
        return canFly();
    }

    /**
     * Sets whether falling is disabled.
     * @param disabled the new value
     * @deprecated Misleading name. See {@link #setCanFly(boolean)}
     */
    @Deprecated
    public void setFallingDisabled(boolean disabled) {
        setCanFly(disabled);
    }

    /**
     * Returns whether buckets are always full.
     * When set, every bucket that the player holds stays full after emptying.
     * @return whether buckets are always full.
     * @deprecated Misleading name. See {@link #hasCreativePerks()}
     */
    public boolean isBucketAlwaysFull() {
        return hasCreativePerks();
    }

    /**
     * Sets whether buckets are always full.
     * When set, every bucket that the player holds stays full after emptying.
     * @param alwaysFull the new state
     * @deprecated Misleading name. See {@link #setCreativePerks(boolean)}
     */
    public void setBucketAlwaysFull(boolean alwaysFull) {
        setCreativePerks(alwaysFull);
    }

    /**
     * Returns whether this player is an op.
     *
     * @return {@code true} if the player is op.
     */
    public boolean isOp() {
        return isOp(getName());
    }

    /**
     * Static method to determine whether a player is op.
     * @param playerName The name of the player to check.
     * @return {@code true} if the player is op.
     */
    public static boolean isOp(String playerName) {
        // SRG return etc.getMCServer().func_71203_ab().func_72376_i().contains(playerName);
        return etc.getMCServer().af().i().contains(playerName);
    }

    private boolean checkSpam() {
        long diff = System.currentTimeMillis() - this.lastMessage;

        if (this.spamTicker >= diff / 50)
            this.spamTicker = 0;
        else
            this.spamTicker -= diff / 50;

        this.spamTicker += 20;
        if (this.spamTicker > 200) {
            this.kick("Spamming.");
        }

        this.lastMessage = System.currentTimeMillis();

        return false;
    }

    /**
     * Returns a <tt>String</tt> containing possible completions for
     * <tt>currentText</tt>.
     * @param currentText The text to be autocompleted.
     * @return A null-char-separated String of options.
     */
    public String autoComplete(String currentText) {
        List<String> options = new ArrayList<String>();

        if (currentText.length() == 0 || currentText.charAt(0) != '/' && currentText.indexOf(' ') == -1) {
            // Start of line, add a colon to completed names for convenience
            for (Player p : etc.getServer().getPlayerList()) {
                if (p.getName().toLowerCase().startsWith(currentText.toLowerCase())) {
                    options.add(p.getName() + ":"); // Note: no space here, because client splits on space.
                }
            }
        } else if (currentText.charAt(0) == '/') {
            if (currentText.indexOf(' ') > 0) {
                String commandName = currentText.split("\\s+")[0];
                if (this.canUseCommand(commandName)) {
                    // Remove leading '/'
                    commandName = commandName.substring(1);
                    BaseCommand command = ServerConsoleCommands.getInstance().getCommand(commandName);
                    if (command == null) { // Not a server command? Try player commands.
                        command = PlayerCommands.getInstance().getCommand(commandName);
                    }
                    // Call command's autoComplete method
                    List<String> commandOptions = command == null
                            ? etc.autoCompleteNames(currentText.substring(currentText.lastIndexOf(' ') + 1))
                            : command.autoComplete(this, currentText);
                    if (commandOptions != null) {
                        options.addAll(commandOptions);
                    }
                }
            } else {
                // Access ServerConsoleCommands and PlayerCommands to initialize
                ServerConsoleCommands.getInstance();
                PlayerCommands.getInstance();
                // Generate a list with possible commands.
                for (String command : etc.getInstance().getCommands().keySet()) {
                    if (command.startsWith(currentText) && this.canUseCommand(command)) {
                        options.add(command);
                    }
                }
            }
        } else {
            String[] splitText = currentText.split("\\s+");
            String toComplete = splitText[splitText.length - 1];

            options = etc.autoCompleteNames(toComplete);
        }

        return etc.combineSplit(0, options.toArray(new String[options.size()]), "\u0000");
    }

    /**
     * Don't use. Use setCanModifyWorld(), setIgnoreRestrictions(), and setAdmin()
     *
     * @param restrictions
     */
    protected void setRestrictions(int restrictions) {
        this.restrictions = restrictions;
    }

    /**
     * Don't use. Use canModifyWorld(), canIgnoreRestrictions(), and isAdmin()
     *
     * @return
     */
    protected int getRestrictions() {
        return this.restrictions;
    }

    void moveTo(Player p) {
        this.entity = p.entity;
        p.cooldownKits = this.cooldownKits;
        p.onlyOneUseKits = this.onlyOneUseKits;
        p.muted = this.muted;
        p.lastMessage = this.lastMessage;
        p.spamTicker = this.spamTicker;
    }

    /**
     * This method plays a sound for just this player, and no one else can hear it.
     * @param location the location to play the sound at
     * @param sound the sound to play
     * @param volume the volume to play the sound at
     * @param pitch the pitch to play the sound at
     * @see Sound
     * @see Location
     */
    public void playSound(Location location, Sound sound, float volume, float pitch){
        playSound(location.x, location.y, location.z, sound, volume, pitch);
    }

    /**
     * This method plays a sound for just this player, and no one else can hear it.
     * @param x x coordinate to play the sound at
     * @param y y coordinate to play the sound at
     * @param z z coordinate to play the sound at
     * @param sound the sound to play
     * @param volume the volume to play the sound at
     * @param pitch the pitch to play the sound at
     * @see Sound
     * @see Location
     */
    public void playSound(double x, double y, double z, Sound sound, float volume, float pitch){
        getEntity().a.b(new OPacket62LevelSound(sound.getSoundString(), x, y, z, volume, pitch));
    }

    /**
     * Returns this player's set view distance.
     * @return the view distance
     */
    public int getViewDistance() {
        return this.getEntity().getViewDistance();
    }

    /**
     * Returns the chat preference for this player.
     * This number denotes whether the player wants command output only (1),
     * or has disabled messages overall (2)
     * @return chat preferences
     * @see #wantsCommandsOnly()
     * @see #hasChatDisabled()
     */
    public int getChatPreference() {
        // SRG return this.getEntity().func_71126_v();
        return this.getEntity().t();
    }

    /**
     * Returns whether this player wants output from commands only.
     * @return Whether the player set "Chat: Commands Only"
     */
    public boolean wantsCommandsOnly() {
        return this.getChatPreference() == 1;
    }

    /**
     * Returns whether this player has chat disabled.
     * @return Whether the player set "Chat: Hide"
     */
    public boolean hasChatDisabled() {
        return this.getChatPreference() == 2;
    }

    /**
     * Returns the state of the client's "Colors" setting.
     * @return whether this player wants colors disabled.
     */
    public boolean wantsColorDisabled() {
        return !this.getEntity().getColorEnabled();
    }

    @Override
    public void setInventoryCursorItem(Item item) {
        super.setInventoryCursorItem(item);
        // Update client
        // SRG getEntity().field_71135_a.func_72567_b((OPacket) new OPacket103SetSlot(-1, -1, item.getBaseItem()));
        getEntity().a.b((OPacket) new OPacket103SetSlot(-1, -1, item.getBaseItem()));
    }

    @Override
    @SuppressWarnings("unchecked")
    public void updateInventory() {
        // SRG OContainer container = getEntity().field_71069_bz;
        OContainer container = getEntity().bo;
        ArrayList<OItemStack> list = new ArrayList<OItemStack>();
        // SRG for (OSlot slot : (List<OSlot>) container.field_75151_b) {
        for (OSlot slot : (List<OSlot>) container.c) {
            // SRG list.add(slot.func_75211_c());
            list.add(slot.d());
        }
        // SRG getEntity().func_71110_a(container, list);
        getEntity().a(container, list);
    }

    @Override
    public void updateLevels() {
        // SRG getEntity().field_71135_a.func_72567_b((OPacket) new OPacket8UpdateHealth(this.getHealthFloat(), getFoodLevel(), getFoodSaturationLevel()));
        getEntity().a.b((OPacket) new OPacket8UpdateHealth(this.getHealthFloat(), getFoodLevel(), getFoodSaturationLevel()));
    }

    @Override
    public void updateXP() {
        // SRG getEntity().field_71135_a.func_72567_b((OPacket) new OPacket43Experience(getEntity().field_71106_cc, getEntity().field_71068_ca, getEntity().field_71067_cb));
        getEntity().a.b((OPacket) new OPacket43Experience(getEntity().bJ, getEntity().bH, getEntity().bI));
    }
}
