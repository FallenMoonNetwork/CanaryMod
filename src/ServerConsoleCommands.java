import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerConsoleCommands extends CommandHandler {

    private static final Logger log = Logger.getLogger("Minecraft-Server");
    private static ServerConsoleCommands instance;
    private final LinkedHashMap<String, BaseCommand> commands = new LinkedHashMap<String, BaseCommand>();

    private ServerConsoleCommands() {
        add("reload", reload);
        add("listplugins", listplugins);
        add("enableplugin", enableplugin);
        add("reloadplugin", reloadplugin);
        add("disableplugin", disableplugin);
        add("modify", modify);
        add("mp", modify);
        add("reservelist", reservelist);
        add("whitelist", whitelist);
        add("version", version);
        add("banlist", banlist);
        add("ban", ban);
        add("banip", banip);
        add("unban", unban);
        add("unbanip", unbanip);
        add("tempban", tempban);
        add("kick", kick);
        add("kickall", kickall);
        add("time", time);
        add("weather", weather);
        add("toggledownfall", weather);
        add("thunder", thunder);
    }

    /**
     * Performs a lookup for a command of the given name and executes it if
     * found. Returns false if command not found.
     *
     * @param command
     * @param caller
     * @param args
     * @return
     */
    public static boolean parseServerConsoleCommand(MessageReceiver caller, String command, String[] args) {
        BaseCommand cmd = instance.getCommand(command);

        if (cmd != null) {
            cmd.parseCommand(caller, args);
            // Inform caller a matching command was found.
            return true;
        }
        return false;
    }

    /**
     * Returns the <tt>ServerConsoleCommands</tt> instance.
     * @return the <tt>ServerConsoleCommands</tt> as used by the server.
     */
    public static ServerConsoleCommands getInstance() {
        return instance;
    }

    public static final BaseCommand reload = new BaseCommand("- Reloads CanaryMod") {

        @Override
        protected void execute(MessageReceiver caller, String[] parameters) {
            etc.getInstance().load();
            etc.getInstance().loadData();
            etc.getDataSource().loadGroups();
            for (Player p : etc.getServer().getPlayerList()) {
                p.getUser().reloadPlayer();
            }
            log.log(Level.INFO, "CanaryMod reloaded by {0}", caller.getName());
            caller.notify("Successfully reloaded config");
        }

        @Override
        public List<String> autoComplete(MessageReceiver caller, String currentText) {
            return null;
        }
    };
    public static final BaseCommand modify = new BaseCommand("<player> <key> <value> - Type /modify for more info", "Overriden onBadSyntax", 3) {

        @Override
        protected void execute(MessageReceiver caller, String[] parameters) {
            if (parameters.length > 2 && parameters[2].contains(":")) {
                for (int i = 3; i < parameters.length; i++) {
                    if (!parameters[i].contains(":")) {
                        onBadSyntax(caller, null);
                        return;
                    }
                }

                Player player = etc.getServer().matchPlayer(parameters[1]);

                if (player == null) {
                    if (!etc.getDataSource().doesPlayerExist(parameters[1])) {
                        caller.notify("Player does not exist.");
                        return;
                    } else {
                        player = etc.getDataSource().getPlayer(parameters[1]);
                        player.setOfflineName(parameters[1]);
                    }
                }

                for (int i = 2; i < parameters.length; i++) {
                    if (parameters[i].split(":").length != 2) {
                        caller.notify("This key:value pair is deformed... " + parameters[i]);
                        return;
                    }
                    String key = parameters[i].split(":")[0];
                    String value = parameters[i].split(":")[1];
                    boolean newUser = false;

                    if (!etc.getDataSource().doesPlayerExist(player.getOfflineName())) {
                        if (!key.equalsIgnoreCase("groups") && !key.equalsIgnoreCase("g")) {
                            caller.notify("When adding a new user, set their group(s) first.");
                            return;
                        }
                        caller.notify("Adding new user.");
                        newUser = true;
                        player.setCanModifyWorld(true);
                    }

                    boolean skip = false;

                    if ((key.equalsIgnoreCase("groups") || key.equalsIgnoreCase("g"))) {
                        for (String groupname : value.split(",")) {
                            if (!etc.getDataSource().doesGroupExist(groupname)) {
                                caller.notify(String.format("Group %s does not exist. Skipping group key.", groupname));
                                skip = true;
                            }
                        }
                    }

                    if (!skip) {
                        updatePlayerValues(player, key, value);
                        saveChanges(player, newUser);
                    }

                    log.log(Level.INFO, "Modifed user {0}. {1} => {2} by {3}", new Object[]{player.getOfflineName(), key, value, caller.getName()});
                }
                caller.notify("Modified user.");
            } else {
                if (parameters.length < 4) {
                    onBadSyntax(caller, null);
                    return;
                }

                Player player = etc.getServer().matchPlayer(parameters[1]);

                if (player == null) {
                    if (!etc.getDataSource().doesPlayerExist(parameters[1])) {
                        caller.notify("Player does not exist.");
                        return;
                    } else {
                        player = etc.getDataSource().getPlayer(parameters[1]);
                        player.setOfflineName(parameters[1]);
                    }
                }

                String key = parameters[2];
                String value = parameters[3];
                boolean newUser = false;

                if (!etc.getDataSource().doesPlayerExist(player.getOfflineName())) {
                    if (!key.equalsIgnoreCase("groups") && !key.equalsIgnoreCase("g")) {
                        caller.notify("When adding a new user, set their group(s) first.");
                        return;
                    }
                    caller.notify("Adding new user.");
                    newUser = true;
                }

                if ((key.equalsIgnoreCase("groups") || key.equalsIgnoreCase("g"))) {
                    for (String groupname : value.split(",")) {
                        if (!etc.getDataSource().doesGroupExist(groupname)) {
                            caller.notify(String.format("Group %s does not exist. %s groups not modified.", groupname, player.getOfflineName()));
                            return;
                        }
                    }
                }

                updatePlayerValues(player, key, value);
                saveChanges(player, newUser);

                caller.notify("Modified user.");
                // Send to server
                // log too,
                // regardless of
                // caller.
                log.log(Level.INFO, "Modifed user {0}. {1} => {2} by {3}", new Object[]{player.getOfflineName(), key, value, caller.getName()});
            }
        }

        private void saveChanges(Player player, boolean newUser) {
            if (newUser) {
                etc.getDataSource().addPlayer(player);
            } else {
                etc.getDataSource().modifyPlayer(player);
            }
        }

        private void updatePlayerValues(Player player, String key, String value) {
            if (key.equalsIgnoreCase("prefix") || key.equalsIgnoreCase("p")) {
                player.setPrefix(value);
            } else if (key.equalsIgnoreCase("commands") || key.equalsIgnoreCase("c")) {
                player.setCommands(value.split(","));
            } else if (key.equalsIgnoreCase("groups") || key.equalsIgnoreCase("g")) {
                player.setGroups(value.split(","));
            } else if (key.equalsIgnoreCase("ignoresrestrictions") || key.equalsIgnoreCase("ir")) {
                player.setIgnoreRestrictions(value.equalsIgnoreCase("true") || value.equals("1"));
            } else if (key.equalsIgnoreCase("admin") || key.equalsIgnoreCase("a")) {
                player.setAdmin(value.equalsIgnoreCase("true") || value.equals("1"));
            } else if (key.equalsIgnoreCase("modworld") || key.equalsIgnoreCase("mw")) {
                player.setCanModifyWorld(value.equalsIgnoreCase("true") || value.equals("1"));
            }
        }

        @Override
        public void onBadSyntax(MessageReceiver caller, String[] params) {
            caller.notify("Usage is: /modify <player> <key> <value>");
            caller.notify("Keys:");
            caller.notify("prefix: only the letter the color represents");
            caller.notify("commands: list seperated by comma");
            caller.notify("groups: list seperated by comma");
            caller.notify("ignoresrestrictions: true or false");
            caller.notify("admin: true or false");
            caller.notify("modworld: true or false");
        }

        private List<String> realReturn(List<String> list, String[] split, String[] realSplit) {
            if (split.length == realSplit.length) {
                return list;
            }

            List<String> toRet = new ArrayList<String>(list.size());
            for (String word : list) {
                toRet.add(split[2] + ":" + word);
            }

            return toRet;
        }

        @Override
        public List<String> autoComplete(MessageReceiver caller, String currentText) {
            String[] split = currentText.split("[ :]", -1);
            String[] realSplit = currentText.split(" ", -1);
            if (split.length == 2) {
                return super.autoComplete(caller, currentText);
            } else if (split.length == 3) {
                return etc.autoComplete(split[2], "prefix", "commands", "groups", "ignoresrestrictions", "admin", "modworld");
            } else if (split.length == 4) {
                if (split[2].equalsIgnoreCase("prefix")) {
                    return realReturn(etc.autoComplete(split[3], "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"), split, realSplit);
                } else if (split[2].equalsIgnoreCase("commands")) {
                    if (split[3].isEmpty()) {
                        return realReturn(Arrays.asList(etc.combineSplit(0, etc.getDataSource().getPlayer(split[1]).getCommands(), ",")), split, realSplit);
                    } else {
                        return null;
                    }
                } else if (split[2].equalsIgnoreCase("groups")) {
                    List<String> filledGroups = Arrays.asList(split[3].split(",", -1));
                    List<String> availGroups = new ArrayList<String>();

                    for (Group g : etc.getDataSource().getGroupList()) {
                        if (!filledGroups.contains(g.Name)) {
                            availGroups.add(g.Name);
                        }
                    }

                    List<String> lastCompleted = etc.autoComplete(filledGroups.remove(filledGroups.size() - 1),
                                                              availGroups.toArray(new String[availGroups.size()]));
                    List<String> completed = new ArrayList<String>(lastCompleted.size());

                    StringBuilder filledJoinedBuilder = new StringBuilder();
                    for (String filled : filledGroups) {
                        filledJoinedBuilder.append(filled).append(",");
                    }

                    for (String option : lastCompleted) {
                        completed.add(new StringBuilder(filledJoinedBuilder).append(option).toString());
                    }

                    return realReturn(completed, split, realSplit);
                } else if (split[2].equalsIgnoreCase("ignoresrestrictions")
                        || split[2].equalsIgnoreCase("admin")
                        || split[2].equalsIgnoreCase("modworld")) {
                    return realReturn(etc.autoComplete(split[3], "true", "false"), split, realSplit);
                }
            }
            return null;
        }
    };
    public final static BaseCommand whitelist = new BaseCommand("<toggle|add|remove> [player]", "whitelist <toggle|add|remove>", 2) {

        @Override
        protected void execute(MessageReceiver caller, String[] parameters) {
            if (parameters[1].equalsIgnoreCase("toggle")) {
                caller.notify((etc.getInstance().toggleWhitelist() ? "Whitelist enabled" : "Whitelist disabled"));
            } else if (parameters.length == 3) {
                if (parameters[1].equalsIgnoreCase("add")) {
                    etc.getDataSource().addToWhitelist(parameters[2]);
                    caller.notify(parameters[2] + " added to whitelist");
                } else if (parameters[1].equalsIgnoreCase("remove")) {
                    etc.getDataSource().removeFromWhitelist(parameters[2]);
                    caller.notify(parameters[2] + " removed from whitelist");
                } else {
                    caller.notify("Invalid operation.");
                }
            } else {
                caller.notify("Invalid operation.");
            }
        }

        @Override
        public List<String> autoComplete(MessageReceiver caller, String currentText) {
            String[] split = currentText.split(" ", -1);
            if (split.length == 2) {
                return etc.autoComplete(split[1], "toggle", "add", "remove");
            } else if (split.length == 3 && split[1].equalsIgnoreCase("remove")) {
                return super.autoComplete(caller, currentText);
            }

            return null;
        }
    };
    public final static BaseCommand reservelist = new BaseCommand("<add|remove> <player>", "reservelist <add|remove> <player>", 3, 3) {

        @Override
        protected void execute(MessageReceiver caller, String[] parameters) {
            if (parameters[1].equalsIgnoreCase("add")) {
                etc.getDataSource().addToReserveList(parameters[2]);
                caller.notify(parameters[2] + " added to reservelist");
            } else if (parameters[1].equalsIgnoreCase("remove")) {
                etc.getDataSource().removeFromReserveList(parameters[2]);
                caller.notify(parameters[2] + " removed from reservelist");
            } else {
                caller.notify("Invalid operation.");
            }
        }

        @Override
        public List<String> autoComplete(MessageReceiver caller, String currentText) {
            String[] split = currentText.split(" ", -1);
            if (split.length == 2) {
                return etc.autoComplete(split[1], "add", "remove");
            } else if (split.length == 3) {
                return super.autoComplete(caller, currentText);
            }

            return null;
        }
    };
    public final static BaseCommand listplugins = new BaseCommand("- Lists all plugins") {

        @Override
        protected void execute(MessageReceiver caller, String[] parameters) {
            caller.notify("Plugins" + Colors.White + ": " + etc.getLoader().getPluginList());
        }

        @Override
        public List<String> autoComplete(MessageReceiver caller, String currentText) {
            return null;
        }
    };
    public final static BaseCommand reloadplugin = new BaseCommand("<plugin> - Reloads plugin", "Correct usage is: /reloadplugin [plugin]", 2) {

        @Override
        protected void execute(MessageReceiver caller, String[] parameters) {
            if (etc.getLoader().reloadPlugin(parameters[1])) {
                caller.notify("Plugin reloaded.");
            } else {
                caller.notify("Unable to reload plugin. Check capitalization and/or server logfile.");
            }
        }

        @Override
        public List<String> autoComplete(MessageReceiver caller, String currentText) {
            String[] pluginList = etc.getLoader().getPluginList().split(",");
            for (int i = 0; i < pluginList.length; i++) {
                pluginList[i] = pluginList[i].substring(0, pluginList[i].length() - 4);
            }

            return etc.autoComplete(currentText.substring(currentText.indexOf(' ') + 1), pluginList);
        }
    };
    public final static BaseCommand enableplugin = new BaseCommand("<plugin> - Enables plugin", "Correct usage is: /enableplugin [plugin]", 2) {

        @Override
        protected void execute(MessageReceiver caller, String[] parameters) {
            if (etc.getLoader().enablePlugin(parameters[1])) {
                caller.notify("Plugin enabled.");
            } else {
                caller.notify("Unable to enable plugin. Check capitalization and/or server logfile.");
            }
        }

        @Override
        public List<String> autoComplete(MessageReceiver caller, String currentText) {
            return null;
        }
    };
    public final static BaseCommand disableplugin = new BaseCommand("<plugin> - Disables plugin", "Correct usage is: /disableplugin [plugin]", 2) {

        @Override
        protected void execute(MessageReceiver caller, String[] parameters) {
            etc.getLoader().disablePlugin(parameters[1]);
            caller.notify("Plugin disabled.");
        }

        @Override
        public List<String> autoComplete(MessageReceiver caller, String currentText) {
            return reloadplugin.autoComplete(caller, currentText);
        }
    };
    public final static BaseCommand version = new BaseCommand("- Displays the server version") {

        @Override
        protected void execute(MessageReceiver caller, String[] parameters) {
            if (!etc.getInstance().getTainted()) {
                if (etc.getInstance().isCrow()) {
                    caller.notify(Colors.Gold + "Crow Test Build " + etc.getInstance().getVersionStr());
                } else {
                    caller.notify(Colors.Gold + "CanaryMod Build " + etc.getInstance().getVersionStr());
                }
            } else {
                caller.notify(Colors.Gold + "Tainted Build Information: " + etc.getInstance().getVersionStr());
            }
        }

        @Override
        public List<String> autoComplete(MessageReceiver caller, String currentText) {
            return null;
        }
    };
    public static final BaseCommand banlist = new BaseCommand("['IPs'] - Gives a list of (IP) bans") {

        @Override
        protected void execute(MessageReceiver caller, String[] split) {
            boolean ips = split.length == 2 && split[1].equalsIgnoreCase("ips");

            if (!ips) {
                // SRG caller.notify(Colors.Blue + "Ban list:" + Colors.White + " " + etc.getMCServer().func_71203_ab().getBans());
                caller.notify(Colors.Blue + "Ban list:" + Colors.White + " " + etc.getMCServer().af().getBans());
            } else {
                // SRG caller.notify(Colors.Blue + "IP Ban list:" + Colors.White + " " + etc.getMCServer().func_71203_ab().getIpBans());
                caller.notify(Colors.Blue + "IP Ban list:" + Colors.White + " " + etc.getMCServer().af().getIpBans());
            }
        }

        @Override
        public List<String> autoComplete(MessageReceiver caller, String currentText) {
            if (currentText.indexOf(' ') != currentText.length() - 1) {
                return Arrays.asList("IPs");
            }

            return null;
        }
    };
    public static final BaseCommand banip = new BaseCommand("<Player> [Reason] - Bans the player's IP", "Correct usage is: /banip [player] <reason> (optional) NOTE: this permabans IPs.", 2) {

        @Override
        protected void execute(MessageReceiver caller, String[] split) {
            Player player = etc.getServer().matchPlayer(split[1]);

            if (player != null) {
                if (caller instanceof Player && !((Player) caller).hasControlOver(player)) {
                    caller.notify("You can't ban that user.");
                    return;
                }

                // adds player to ban list
                if (split.length > 2) {
                    BanSystem.fileIpBan(player, etc.combineSplit(2, split, " "));
                } else {
                    BanSystem.fileIpBan(player);
                }

                etc.getLoader().callHook(PluginLoader.Hook.IPBAN, new Object[]{(caller instanceof Player) ? (Player) caller : null, player, split.length >= 3 ? etc.combineSplit(2, split, " ") : ""});

                log.log(Level.INFO, "IP Banning {0} (IP: {1})", new Object[]{player.getName(), player.getIP()});
                caller.notify("IP Banning " + player.getName() + " (IP: " + player.getIP() + ")");

                if (split.length > 2) {
                    player.kick("IP Banned by " + caller.getName() + ": " + etc.combineSplit(2, split, " "));
                } else {
                    player.kick("IP Banned by " + caller.getName() + ".");
                }
            } else {
                caller.notify("Can't find user " + split[1] + ".");
            }
        }
    };
    public static final BaseCommand ban = new BaseCommand("<Player> [Reason] - Bans the player", "Correct usage is: /ban [player] <reason> (optional)", 2) {

        @Override
        protected void execute(MessageReceiver caller, String[] split) {
            Player player = etc.getServer().matchPlayer(split[1]);

            if (player != null) {
                if (caller instanceof Player && !((Player) caller).hasControlOver(player)) {
                    caller.notify("You can't ban that user.");
                    return;
                }

                // adds player to ban list
                if (split.length > 2) {
                    BanSystem.fileBan(player, etc.combineSplit(2, split, " "));
                } else {
                    BanSystem.fileBan(player);
                }

                etc.getLoader().callHook(PluginLoader.Hook.BAN, new Object[]{(caller instanceof Player) ? (Player) caller : null, player, split.length >= 3 ? etc.combineSplit(2, split, " ") : ""});

                if (split.length > 2) {
                    player.kick("Banned by " + caller.getName() + ": " + etc.combineSplit(2, split, " "));
                } else {
                    player.kick("Banned by " + caller.getName() + ".");
                }
                log.log(Level.INFO, "{0}: banning {1}", new Object[]{caller.getName(), player.getName()});
                caller.notify("Banning " + player.getName());
            } else {
                if (!etc.getDataSource().isOnBanList(split[1], null)) {
                    etc.getDataSource().addBan(new Ban(split[1]));
                    log.log(Level.INFO, "{0}: banning {1}", new Object[]{caller.getName(), split[1]});
                    caller.notify("Banning " + split[1]);
                } else {
                    caller.notify(String.format("%s is already banned from this server", split[1]));
                }
            }
        }
    };
    public static final BaseCommand unban = new BaseCommand("<Player> - Unbans the player or IP", "Correct usage is: /unban [player or ip]", 2, 2) {

        @Override
        protected void execute(MessageReceiver caller, String[] split) {
            etc.getServer().unban(split[1]);
            caller.notify("Unbanned " + split[1]);
        }

        @Override
        public List<String> autoComplete(MessageReceiver caller, String currentText) {
            String[] split = currentText.split(" ", -1);
            if (split.length == 2) {
                List<Ban> banList = etc.getDataSource().getBans();
                List<String> bannedPlayerNames = new ArrayList<String>(banList.size());
                for (Ban b : banList) {
                    if (!b.isIP()) {
                        bannedPlayerNames.add(b.getName());
                    }
                }

                // SRG bannedPlayerNames.addAll(Arrays.asList(etc.getMCServer().func_71203_ab().getBans().split(" ,")));
                bannedPlayerNames.addAll(Arrays.asList(etc.getMCServer().af().getBans().split(" ,")));

                return etc.autoComplete(split[1], bannedPlayerNames.toArray(new String[bannedPlayerNames.size()]));
            }
            return null;
        }
    };
    public static final BaseCommand unbanip = new BaseCommand("<IP> - Unbans the IP", "Correct usage is: /unbanip [ip]", 2, 2) {

        @Override
        protected void execute(MessageReceiver caller, String[] parameters) {
            caller.notify("This command is going to be phased out.");
            caller.notify("For new bans, you must use /unban to unban IPs.");
            etc.getDataSource().expireBan(new Ban(parameters[1]));
            etc.getMCServer().af().f().b(parameters[1]);
            caller.notify("Unbanned " + parameters[1]);
        }

        @Override
        public List<String> autoComplete(MessageReceiver caller, String currentText) {
            String[] split = currentText.split(" ", -1);
            if (split.length == 2) {
                // SRG return etc.autoComplete(split[1], etc.getMCServer().func_71203_ab().getIpBans().split(" ,"));
                return etc.autoComplete(split[1], etc.getMCServer().af().getIpBans().split(" ,"));
            }
            return null;
        }
    };

    public static final BaseCommand tempban = new BaseCommand("['ip'] <player> <time> [reason] - Bans the player for the specified time", "Overridden because multiline", 3) {

        @Override
        protected void execute(MessageReceiver caller, String[] split) {
            boolean byIp = split[1].equalsIgnoreCase("ip");
            if (byIp) {
                split = (split[0] + " " + etc.combineSplit(2, split, " ")).split(" ");
            }

            Player player = etc.getServer().matchPlayer(split[1]);
            try {
                if (player != null) {
                    if (caller instanceof Player && !((Player) caller).hasControlOver(player)) {
                        caller.notify("You can't ban that user.");
                        return;
                    }

                    if (split.length > 3) {
                        BanSystem.fileBan(player, etc.combineSplit(3, split, " "),
                                (int) (matchFutureDate(split[2]) / 1000), byIp);
                    } else {
                        BanSystem.fileBan(player, etc.getInstance().getDefaultBanMessage(),
                                (int) (matchFutureDate(split[2]) / 1000), byIp);
                    }

                    etc.getLoader().callHook(PluginLoader.Hook.BAN, caller instanceof Player ? caller : null, player, split.length >= 3 ? etc.combineSplit(2, split, " ") : "");

                    if (split.length > 3) {
                        player.kick("Banned by " + caller.getName() + ": " + etc.combineSplit(3, split, " "));
                    } else {
                        player.kick("Banned by " + caller.getName() + ".");
                    }
                    log.log(Level.INFO, "{0}: banning {1}", new Object[]{caller.getName(), player.getName()});
                    caller.notify("Banning " + player.getName());
                } else if (byIp) {
                    if (!etc.getDataSource().isOnBanList(null, split[1])) {
                        Ban b = new Ban();
                        b.setIp(split[1]);
                        b.setTimestamp((int) (matchFutureDate(split[2]) / 1000));
                        etc.getDataSource().addBan(b);
                        log.log(Level.INFO, "{0}: banning {1}", new Object[]{caller.getName(), split[1]});
                        caller.notify("Banning " + split[1]);
                    }
                } else {
                    if (!etc.getDataSource().isOnBanList(split[1], null)) {
                        Ban b = new Ban(split[1]);
                        b.setTimestamp((int) (matchFutureDate(split[2]) / 1000));
                        etc.getDataSource().addBan(b);
                        log.log(Level.INFO, "{0}: banning {1}", new Object[]{caller.getName(), split[1]});
                        caller.notify("Banning " + split[1]);
                    } else {
                        caller.notify(String.format("%s is already banned from this server", split[1]));
                    }
                }
            } catch (IllegalArgumentException e) {
                caller.notify("Error while trying to ban: " + e.getMessage());
                this.onBadSyntax(caller, split);
            }
        }

        // Thanks to sk89q's CommandBook for this parser
        public long matchFutureDate(String filter) throws IllegalArgumentException {
            if (filter.equalsIgnoreCase("now")) {
                return System.currentTimeMillis();
            }
            String[] groupings = filter.split("-");
            if (groupings.length == 0) {
                throw new IllegalArgumentException("Invalid date specified");
            }
            Calendar cal = new GregorianCalendar();
            cal.setTimeInMillis(System.currentTimeMillis());
            for (String str : groupings) {
                int type;
                switch (str.charAt(str.length() - 1)) {
                    case 'm':
                        type = Calendar.MINUTE;
                        break;
                    case 'h':
                        type = Calendar.HOUR;
                        break;
                    case 'd':
                        type = Calendar.DATE;
                        break;
                    case 'w':
                        type = Calendar.WEEK_OF_YEAR;
                        break;
                    case 'y':
                        type = Calendar.YEAR;
                        break;
                    default:
                        throw new IllegalArgumentException("Unknown date value specified");
                }
                cal.add(type, Integer.valueOf(str.substring(0, str.length() - 1)));
            }
            return cal.getTimeInMillis();
        }

        @Override
        public void onBadSyntax(MessageReceiver caller, String[] parameters) {
            caller.notify("Correct usage: /tempban ['ip'] <player> <time> [reason] (optional)");
            caller.notify("Time is given as a series of [number][m|h|d|w|y] split by -.");
            caller.notify("The characters m, h, d, w, y stand for minutes, hours, days, weeks");
            caller.notify("  and years, respectively");
        }

        @Override
        public List<String> autoComplete(MessageReceiver caller, String currentText) {
            List<String> split = Arrays.asList(currentText.split(" ", -1));

            // Pop off "ip", if present
            if (split.size() > 1 && split.get(1).equalsIgnoreCase("ip")) {
                split.remove(1);
            }

            // Don't autocomplete time
            if (split.size() == 3) {
                return null;
            }

            return super.autoComplete(caller, currentText);
        }
    };
    public static final BaseCommand kick = new BaseCommand("<Player> [Reason] - Kicks player", "Correct usage is: /kick [player] <reason> (optional)", 2) {

        @Override
        protected void execute(MessageReceiver caller, String[] split) {
            Player player = etc.getServer().matchPlayer(split[1]);

            if (player != null) {
                if ((caller instanceof Player) && !((Player) caller).hasControlOver(player)) {
                    caller.notify("You can't kick that user.");
                    return;
                }

                etc.getLoader().callHook(PluginLoader.Hook.KICK, caller instanceof Player ? (Player) caller : null, player, split.length >= 3 ? etc.combineSplit(2, split, " ") : "");

                if (split.length > 2) {
                    player.kick("Kicked by " + caller.getName() + ": " + etc.combineSplit(2, split, " "));
                } else {
                    player.kick("Kicked by " + caller.getName() + ".");
                }
                log.log(Level.INFO, "Kicking {0}", player.getName());
                caller.notify("Kicking " + player.getName());
            } else {
                caller.notify("Can't find user " + split[1] + ".");
            }
        }
    };
    public static final BaseCommand kickall = new BaseCommand("[Reason] - Kicks all players", "Correct usage is: /kickall <reason> (optional)", 1) {

        @Override
        protected void execute(MessageReceiver caller, String[] split) {
            log.info("Kicking all players.");
            for (Player player : etc.getServer().getPlayerList()) {
                if (player != null && player.isConnected()) {
                    etc.getLoader().callHook(PluginLoader.Hook.KICK, caller instanceof Player ? (Player) caller : null, player, split.length >= 2 ? etc.combineSplit(1, split, " ") : "");

                    if (split.length > 1) {
                        player.kick("Kicked by " + caller.getName() + ": " + etc.combineSplit(1, split, " "));
                    } else {
                        player.kick("Kicked by " + caller.getName() + ".");
                    }
                }
            }
            log.info("Kicked all players.");
        }
    };
    public static final BaseCommand time = new BaseCommand("[world] <time|'day'|'night'|'check'|'raw' rawtime> - Changes or checks the time", "Correct usage is: /time <day|night|check|raw> (rawtime)", 2, 3) {

        @Override
        protected void execute(MessageReceiver caller, String[] args) {
            World world;

            if (args.length > 2 && !args[1].equalsIgnoreCase("raw")) {
                World[] worlda = etc.getServer().getWorld(args[1]);
                if (worlda == null) {
                    caller.notify(String.format("The world %s doesn't exist.", args[1]));
                    return;
                }
                world = worlda[0];
                // remove world from args
                String[] tmp = new String[args.length - 1];
                tmp[0] = args[0];
                System.arraycopy(args, 2, tmp, 1, args.length - 2);
                args = tmp;
            } else if (caller instanceof Player) {
                world = ((Player) caller).getWorld();
            } else if (caller instanceof CommandBlock) {
                world = ((CommandBlock) caller).getWorld();
            } else {
                world = etc.getServer().getDefaultWorld();
            }

            if (args.length == 2) {
                if (args[1].equalsIgnoreCase("day")) {
                    world.setRelativeTime(0);
                } else if (args[1].equalsIgnoreCase("night")) {
                    world.setRelativeTime(13000);
                } else if (args[1].equalsIgnoreCase("check")) {
                    caller.notify("The time is " + world.getRelativeTime() + "! (RAW: " + world.getTime() + ")");
                } else if (args[1].matches("\\d+")) {
                    world.setRelativeTime(Long.parseLong(args[1]));
                } else {
                    this.onBadSyntax(caller, args);
                }
            } else if (args[1].equalsIgnoreCase("raw")) {
                if (args[2].matches("\\d+")) {
                    world.setTime(Long.parseLong(args[2]));
                } else {
                    caller.notify("Please enter numbers, not letters.");
                }
            }
        }

        @Override
        public List<String> autoComplete(MessageReceiver caller, String currentText) {
            List<String> split = Arrays.asList(currentText.split(" "));
            String world = null;

            if (split.size() > 2 && !split.get(1).matches("(?i)day|night|check|raw|\\d+")) {
                world = split.remove(1);
            }

            if (split.size() == 2) {
                List<String> complete = Arrays.asList("day", "night", "check", "raw");

                if (world == null) {
                    complete.addAll(etc.getServer().getLoadedWorldNames());
                }

                return etc.autoComplete(split.get(1), complete.toArray(new String[complete.size()]));
            }

            return null;
        }
    };
    public static final BaseCommand weather = new BaseCommand("[on|off] (optional) - Set weather to the specified value (default: toggle)", "Usage: /weather [on|off]", 1, 2) {

        @Override
        protected void execute(MessageReceiver caller, String[] args) {
            World world;

            if (args.length > 1 && !args[1].matches("on|off") || args.length > 2) {
                World[] worlda = etc.getServer().getWorld(args[1]);
                if (worlda == null) {
                    caller.notify(String.format("The world %s doesn't exist.", args[1]));
                    return;
                }
                world = worlda[0];
                // remove world from args
                String[] tmp = new String[args.length - 1];
                tmp[0] = args[0];
                System.arraycopy(args, 2, tmp, 1, args.length - 2);
                args = tmp;
            } else if (caller instanceof Player) {
                world = ((Player) caller).getWorld();
            } else if (caller instanceof CommandBlock) {
                world = ((CommandBlock) caller).getWorld();
            } else {
                world = etc.getServer().getDefaultWorld();
            }

            if (args.length == 1) {
                world.setRaining(!world.isRaining());
                caller.notify("Weather toggled.");
            } else if (args[1].equalsIgnoreCase("on")) {
                world.setRaining(true);
                caller.notify(Colors.Yellow + "Weather turned on.");
            } else if (args[1].equalsIgnoreCase("off")) {
                world.setRaining(false);
                caller.notify(Colors.Yellow + "Weather turned off.");
            } else {
                onBadSyntax(caller, args);
            }

        }

        @Override
        public List<String> autoComplete(MessageReceiver caller, String currentText) {
            if (currentText.indexOf(' ') != currentText.lastIndexOf(' ')) {
                return null;
            }

            return etc.autoComplete(currentText.substring(currentText.indexOf(' ') + 1), "on", "off");
        }
    };
    public static final BaseCommand thunder = new BaseCommand("[on|off] (optional) - Set thunder to the specified value (default: toggle)", "Usage: /thunder [on|off]", 1, 2) {

        @Override
        protected void execute(MessageReceiver caller, String[] args) {
            World world;

            if (args.length > 1 && !args[1].matches("on|off") || args.length > 2) {
                World[] worlda = etc.getServer().getWorld(args[1]);
                if (worlda == null) {
                    caller.notify(String.format("The world %s doesn't exist.", args[1]));
                    return;
                }
                world = worlda[0];
                // remove world from args
                String[] tmp = new String[args.length - 1];
                tmp[0] = args[0];
                System.arraycopy(args, 2, tmp, 1, args.length - 2);
                args = tmp;
            } else if (caller instanceof Player) {
                world = ((Player) caller).getWorld();
            } else if (caller instanceof CommandBlock) {
                world = ((CommandBlock) caller).getWorld();
            } else {
                world = etc.getServer().getDefaultWorld();
            }

            if (args.length == 1) {
                world.setThundering(!world.isThundering());
                caller.notify("Thunder toggled.");
            } else if (args[1].equalsIgnoreCase("on")) {
                world.setThundering(true);
                caller.notify(Colors.Yellow + "Thunder turned on.");
            } else if (args[1].equalsIgnoreCase("off")) {
                world.setThundering(false);
                caller.notify(Colors.Yellow + "Thunder turned off.");
            } else {
                onBadSyntax(caller, args);
            }
        }

        @Override
        public List<String> autoComplete(MessageReceiver caller, String currentText) {
            return weather.autoComplete(caller, currentText);
        }
    };

    static {
        // CanaryMod: Initialize *after* all the commands
        instance = new ServerConsoleCommands();
    }
}
