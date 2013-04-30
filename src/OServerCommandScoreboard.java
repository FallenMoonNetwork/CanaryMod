import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class OServerCommandScoreboard extends OCommandBase {

    public OServerCommandScoreboard() {}

    public String c() {
        return "scoreboard";
    }

    public int a() {
        return 2;
    }

    public void b(OICommandSender oicommandsender, String[] astring) {
        if (astring.length >= 1) {
            if (astring[0].equalsIgnoreCase("objectives")) {
                if (astring.length == 1) {
                    throw new OWrongUsageException("commands.scoreboard.objectives.usage", new Object[0]);
                }

                if (astring[1].equalsIgnoreCase("list")) {
                    this.d(oicommandsender);
                } else if (astring[1].equalsIgnoreCase("add")) {
                    if (astring.length < 4) {
                        throw new OWrongUsageException("commands.scoreboard.objectives.add.usage", new Object[0]);
                    }

                    this.b(oicommandsender, astring, 2);
                } else if (astring[1].equalsIgnoreCase("remove")) {
                    if (astring.length != 3) {
                        throw new OWrongUsageException("commands.scoreboard.objectives.remove.usage", new Object[0]);
                    }

                    this.e(oicommandsender, astring[2]);
                } else {
                    if (!astring[1].equalsIgnoreCase("setdisplay")) {
                        throw new OWrongUsageException("commands.scoreboard.objectives.usage", new Object[0]);
                    }

                    if (astring.length != 3 && astring.length != 4) {
                        throw new OWrongUsageException("commands.scoreboard.objectives.setdisplay.usage", new Object[0]);
                    }

                    this.j(oicommandsender, astring, 2);
                }

                return;
            }

            if (astring[0].equalsIgnoreCase("players")) {
                if (astring.length == 1) {
                    throw new OWrongUsageException("commands.scoreboard.players.usage", new Object[0]);
                }

                if (astring[1].equalsIgnoreCase("list")) {
                    if (astring.length > 3) {
                        throw new OWrongUsageException("commands.scoreboard.players.list.usage", new Object[0]);
                    }

                    this.k(oicommandsender, astring, 2);
                } else if (astring[1].equalsIgnoreCase("add")) {
                    if (astring.length != 5) {
                        throw new OWrongUsageException("commands.scoreboard.players.add.usage", new Object[0]);
                    }

                    this.l(oicommandsender, astring, 2);
                } else if (astring[1].equalsIgnoreCase("remove")) {
                    if (astring.length != 5) {
                        throw new OWrongUsageException("commands.scoreboard.players.remove.usage", new Object[0]);
                    }

                    this.l(oicommandsender, astring, 2);
                } else if (astring[1].equalsIgnoreCase("set")) {
                    if (astring.length != 5) {
                        throw new OWrongUsageException("commands.scoreboard.players.set.usage", new Object[0]);
                    }

                    this.l(oicommandsender, astring, 2);
                } else {
                    if (!astring[1].equalsIgnoreCase("reset")) {
                        throw new OWrongUsageException("commands.scoreboard.players.usage", new Object[0]);
                    }

                    if (astring.length != 3) {
                        throw new OWrongUsageException("commands.scoreboard.players.reset.usage", new Object[0]);
                    }

                    this.m(oicommandsender, astring, 2);
                }

                return;
            }

            if (astring[0].equalsIgnoreCase("teams")) {
                if (astring.length == 1) {
                    throw new OWrongUsageException("commands.scoreboard.teams.usage", new Object[0]);
                }

                if (astring[1].equalsIgnoreCase("list")) {
                    if (astring.length > 3) {
                        throw new OWrongUsageException("commands.scoreboard.teams.list.usage", new Object[0]);
                    }

                    this.f(oicommandsender, astring, 2);
                } else if (astring[1].equalsIgnoreCase("add")) {
                    if (astring.length < 3) {
                        throw new OWrongUsageException("commands.scoreboard.teams.add.usage", new Object[0]);
                    }

                    this.c(oicommandsender, astring, 2);
                } else if (astring[1].equalsIgnoreCase("remove")) {
                    if (astring.length != 3) {
                        throw new OWrongUsageException("commands.scoreboard.teams.remove.usage", new Object[0]);
                    }

                    this.e(oicommandsender, astring, 2);
                } else if (astring[1].equalsIgnoreCase("empty")) {
                    if (astring.length != 3) {
                        throw new OWrongUsageException("commands.scoreboard.teams.empty.usage", new Object[0]);
                    }

                    this.i(oicommandsender, astring, 2);
                } else if (astring[1].equalsIgnoreCase("join")) {
                    if (astring.length < 4 && (astring.length != 3 || !(oicommandsender instanceof OEntityPlayer))) {
                        throw new OWrongUsageException("commands.scoreboard.teams.join.usage", new Object[0]);
                    }

                    this.g(oicommandsender, astring, 2);
                } else if (astring[1].equalsIgnoreCase("leave")) {
                    if (astring.length < 3 && !(oicommandsender instanceof OEntityPlayer)) {
                        throw new OWrongUsageException("commands.scoreboard.teams.leave.usage", new Object[0]);
                    }

                    this.h(oicommandsender, astring, 2);
                } else {
                    if (!astring[1].equalsIgnoreCase("option")) {
                        throw new OWrongUsageException("commands.scoreboard.teams.usage", new Object[0]);
                    }

                    if (astring.length != 4 && astring.length != 5) {
                        throw new OWrongUsageException("commands.scoreboard.teams.option.usage", new Object[0]);
                    }

                    this.d(oicommandsender, astring, 2);
                }

                return;
            }
        }

        throw new OWrongUsageException("commands.scoreboard.usage", new Object[0]);
    }

    protected OScoreboard d() {
        return OMinecraftServer.D().a(0).V();
    }

    protected OScoreObjective a(String s, boolean flag) {
        OScoreboard oscoreboard = this.d();
        OScoreObjective oscoreobjective = oscoreboard.b(s);

        if (oscoreobjective == null) {
            throw new OCommandException("commands.scoreboard.objectiveNotFound", new Object[] { s});
        } else if (flag && oscoreobjective.c().b()) {
            throw new OCommandException("commands.scoreboard.objectiveReadOnly", new Object[] { s});
        } else {
            return oscoreobjective;
        }
    }

    protected OScorePlayerTeam a(String s) {
        OScoreboard oscoreboard = this.d();
        OScorePlayerTeam oscoreplayerteam = oscoreboard.e(s);

        if (oscoreplayerteam == null) {
            throw new OCommandException("commands.scoreboard.teamNotFound", new Object[] { s});
        } else {
            return oscoreplayerteam;
        }
    }

    protected void b(OICommandSender oicommandsender, String[] astring, int i) {
        String s = astring[i++];
        String s1 = astring[i++];
        OScoreboard oscoreboard = this.d();
        OScoreObjectiveCriteria oscoreobjectivecriteria = (OScoreObjectiveCriteria) OScoreObjectiveCriteria.a.get(s1);

        if (oscoreobjectivecriteria == null) {
            String[] astring1 = (String[]) OScoreObjectiveCriteria.a.keySet().toArray(new String[0]);

            throw new OWrongUsageException("commands.scoreboard.objectives.add.wrongType", new Object[] { a(astring1)});
        } else if (oscoreboard.b(s) != null) {
            throw new OCommandException("commands.scoreboard.objectives.add.alreadyExists", new Object[] { s});
        } else if (s.length() > 16) {
            throw new OSyntaxErrorException("commands.scoreboard.objectives.add.tooLong", new Object[] { s, Integer.valueOf(16)});
        } else {
            OScoreObjective oscoreobjective = oscoreboard.a(s, oscoreobjectivecriteria);

            if (astring.length > i) {
                String s2 = a(oicommandsender, astring, i);

                if (s2.length() > 32) {
                    throw new OSyntaxErrorException("commands.scoreboard.objectives.add.displayTooLong", new Object[] { s2, Integer.valueOf(32)});
                }

                if (s2.length() > 0) {
                    oscoreobjective.a(s2);
                }
            }

            a(oicommandsender, "commands.scoreboard.objectives.add.success", new Object[] { s});
        }
    }

    protected void c(OICommandSender oicommandsender, String[] astring, int i) {
        String s = astring[i++];
        OScoreboard oscoreboard = this.d();

        if (oscoreboard.e(s) != null) {
            throw new OCommandException("commands.scoreboard.teams.add.alreadyExists", new Object[] { s});
        } else if (s.length() > 16) {
            throw new OSyntaxErrorException("commands.scoreboard.teams.add.tooLong", new Object[] { s, Integer.valueOf(16)});
        } else {
            OScorePlayerTeam oscoreplayerteam = oscoreboard.f(s);

            if (astring.length > i) {
                String s1 = a(oicommandsender, astring, i);

                if (s1.length() > 32) {
                    throw new OSyntaxErrorException("commands.scoreboard.teams.add.displayTooLong", new Object[] { s1, Integer.valueOf(32)});
                }

                if (s1.length() > 0) {
                    oscoreplayerteam.a(s1);
                }
            }

            a(oicommandsender, "commands.scoreboard.teams.add.success", new Object[] { s});
        }
    }

    protected void d(OICommandSender oicommandsender, String[] astring, int i) {
        OScorePlayerTeam oscoreplayerteam = this.a(astring[i++]);
        String s = astring[i++].toLowerCase();

        if (!s.equalsIgnoreCase("color") && !s.equalsIgnoreCase("friendlyfire") && !s.equalsIgnoreCase("seeFriendlyInvisibles")) {
            throw new OWrongUsageException("commands.scoreboard.teams.option.usage", new Object[0]);
        } else if (astring.length == 4) {
            if (s.equalsIgnoreCase("color")) {
                throw new OWrongUsageException("commands.scoreboard.teams.option.noValue", new Object[] { s, a(OEnumChatFormatting.a(true, false))});
            } else if (!s.equalsIgnoreCase("friendlyfire") && !s.equalsIgnoreCase("seeFriendlyInvisibles")) {
                throw new OWrongUsageException("commands.scoreboard.teams.option.usage", new Object[0]);
            } else {
                throw new OWrongUsageException("commands.scoreboard.teams.option.noValue", new Object[] { s, a(Arrays.asList(new String[] { "true", "false"}))});
            }
        } else {
            String s1 = astring[i++];

            if (s.equalsIgnoreCase("color")) {
                OEnumChatFormatting oenumchatformatting = OEnumChatFormatting.b(s1);

                if (s1 == null) {
                    throw new OWrongUsageException("commands.scoreboard.teams.option.noValue", new Object[] { s, a(OEnumChatFormatting.a(true, false))});
                }

                oscoreplayerteam.b(oenumchatformatting.toString());
                oscoreplayerteam.c(OEnumChatFormatting.v.toString());
            } else if (s.equalsIgnoreCase("friendlyfire")) {
                if (!s1.equalsIgnoreCase("true") && !s1.equalsIgnoreCase("false")) {
                    throw new OWrongUsageException("commands.scoreboard.teams.option.noValue", new Object[] { s, a(Arrays.asList(new String[] { "true", "false"}))});
                }

                oscoreplayerteam.a(s1.equalsIgnoreCase("true"));
            } else if (s.equalsIgnoreCase("seeFriendlyInvisibles")) {
                if (!s1.equalsIgnoreCase("true") && !s1.equalsIgnoreCase("false")) {
                    throw new OWrongUsageException("commands.scoreboard.teams.option.noValue", new Object[] { s, a(Arrays.asList(new String[] { "true", "false"}))});
                }

                oscoreplayerteam.b(s1.equalsIgnoreCase("true"));
            }

            a(oicommandsender, "commands.scoreboard.teams.option.success", new Object[] { s, oscoreplayerteam.b(), s1});
        }
    }

    protected void e(OICommandSender oicommandsender, String[] astring, int i) {
        OScoreboard oscoreboard = this.d();
        OScorePlayerTeam oscoreplayerteam = this.a(astring[i++]);

        oscoreboard.d(oscoreplayerteam);
        a(oicommandsender, "commands.scoreboard.teams.remove.success", new Object[] { oscoreplayerteam.b()});
    }

    protected void f(OICommandSender oicommandsender, String[] astring, int i) {
        OScoreboard oscoreboard = this.d();

        if (astring.length > i) {
            OScorePlayerTeam oscoreplayerteam = this.a(astring[i++]);
            Collection collection = oscoreplayerteam.d();

            if (collection.size() <= 0) {
                throw new OCommandException("commands.scoreboard.teams.list.player.empty", new Object[] { oscoreplayerteam.b()});
            }

            oicommandsender.a(OEnumChatFormatting.c + oicommandsender.a("commands.scoreboard.teams.list.player.count", new Object[] { Integer.valueOf(collection.size()), oscoreplayerteam.b()}));
            oicommandsender.a(a(collection.toArray()));
        } else {
            Collection collection1 = oscoreboard.g();

            if (collection1.size() <= 0) {
                throw new OCommandException("commands.scoreboard.teams.list.empty", new Object[0]);
            }

            oicommandsender.a(OEnumChatFormatting.c + oicommandsender.a("commands.scoreboard.teams.list.count", new Object[] { Integer.valueOf(collection1.size())}));
            Iterator iterator = collection1.iterator();

            while (iterator.hasNext()) {
                OScorePlayerTeam oscoreplayerteam1 = (OScorePlayerTeam) iterator.next();

                oicommandsender.a(oicommandsender.a("commands.scoreboard.teams.list.entry", new Object[] { oscoreplayerteam1.b(), oscoreplayerteam1.c(), Integer.valueOf(oscoreplayerteam1.d().size())}));
            }
        }
    }

    protected void g(OICommandSender oicommandsender, String[] astring, int i) {
        OScoreboard oscoreboard = this.d();
        OScorePlayerTeam oscoreplayerteam = oscoreboard.e(astring[i++]);
        HashSet hashset = new HashSet();
        String s;

        if (oicommandsender instanceof OEntityPlayer && i == astring.length) {
            s = c(oicommandsender).am();
            oscoreboard.a(s, oscoreplayerteam);
            hashset.add(s);
        } else {
            while (i < astring.length) {
                s = d(oicommandsender, astring[i++]);
                oscoreboard.a(s, oscoreplayerteam);
                hashset.add(s);
            }
        }

        if (!hashset.isEmpty()) {
            a(oicommandsender, "commands.scoreboard.teams.join.success", new Object[] { Integer.valueOf(hashset.size()), oscoreplayerteam.b(), a(hashset.toArray(new String[0]))});
        }
    }

    protected void h(OICommandSender oicommandsender, String[] astring, int i) {
        OScoreboard oscoreboard = this.d();
        HashSet hashset = new HashSet();
        HashSet hashset1 = new HashSet();
        String s;

        if (oicommandsender instanceof OEntityPlayer && i == astring.length) {
            s = c(oicommandsender).am();
            if (oscoreboard.g(s)) {
                hashset.add(s);
            } else {
                hashset1.add(s);
            }
        } else {
            while (i < astring.length) {
                s = d(oicommandsender, astring[i++]);
                if (oscoreboard.g(s)) {
                    hashset.add(s);
                } else {
                    hashset1.add(s);
                }
            }
        }

        if (!hashset.isEmpty()) {
            a(oicommandsender, "commands.scoreboard.teams.leave.success", new Object[] { Integer.valueOf(hashset.size()), a(hashset.toArray(new String[0]))});
        }

        if (!hashset1.isEmpty()) {
            throw new OCommandException("commands.scoreboard.teams.leave.failure", new Object[] { Integer.valueOf(hashset1.size()), a(hashset1.toArray(new String[0]))});
        }
    }

    protected void i(OICommandSender oicommandsender, String[] astring, int i) {
        OScoreboard oscoreboard = this.d();
        OScorePlayerTeam oscoreplayerteam = this.a(astring[i++]);
        ArrayList arraylist = new ArrayList(oscoreplayerteam.d());

        if (arraylist.isEmpty()) {
            throw new OCommandException("commands.scoreboard.teams.empty.alreadyEmpty", new Object[] { oscoreplayerteam.b()});
        } else {
            Iterator iterator = arraylist.iterator();

            while (iterator.hasNext()) {
                String s = (String) iterator.next();

                oscoreboard.b(s, oscoreplayerteam);
            }

            a(oicommandsender, "commands.scoreboard.teams.empty.success", new Object[] { Integer.valueOf(arraylist.size()), oscoreplayerteam.b()});
        }
    }

    protected void e(OICommandSender oicommandsender, String s) {
        OScoreboard oscoreboard = this.d();
        OScoreObjective oscoreobjective = this.a(s, false);

        oscoreboard.k(oscoreobjective);
        a(oicommandsender, "commands.scoreboard.objectives.remove.success", new Object[] { s});
    }

    protected void d(OICommandSender oicommandsender) {
        OScoreboard oscoreboard = this.d();
        Collection collection = oscoreboard.c();

        if (collection.size() <= 0) {
            throw new OCommandException("commands.scoreboard.objectives.list.empty", new Object[0]);
        } else {
            oicommandsender.a(OEnumChatFormatting.c + oicommandsender.a("commands.scoreboard.objectives.list.count", new Object[] { Integer.valueOf(collection.size())}));
            Iterator iterator = collection.iterator();

            while (iterator.hasNext()) {
                OScoreObjective oscoreobjective = (OScoreObjective) iterator.next();

                oicommandsender.a(oicommandsender.a("commands.scoreboard.objectives.list.entry", new Object[] { oscoreobjective.b(), oscoreobjective.d(), oscoreobjective.c().a()}));
            }
        }
    }

    protected void j(OICommandSender oicommandsender, String[] astring, int i) {
        OScoreboard oscoreboard = this.d();
        String s = astring[i++];
        int j = OScoreboard.j(s);
        OScoreObjective oscoreobjective = null;

        if (astring.length == 4) {
            oscoreobjective = this.a(astring[i++], false);
        }

        if (j < 0) {
            throw new OCommandException("commands.scoreboard.objectives.setdisplay.invalidSlot", new Object[] { s});
        } else {
            oscoreboard.a(j, oscoreobjective);
            if (oscoreobjective != null) {
                a(oicommandsender, "commands.scoreboard.objectives.setdisplay.successSet", new Object[] { OScoreboard.b(j), oscoreobjective.b()});
            } else {
                a(oicommandsender, "commands.scoreboard.objectives.setdisplay.successCleared", new Object[] { OScoreboard.b(j)});
            }
        }
    }

    protected void k(OICommandSender oicommandsender, String[] astring, int i) {
        OScoreboard oscoreboard = this.d();

        if (astring.length > i) {
            String s = d(oicommandsender, astring[i++]);
            Map map = oscoreboard.d(s);

            if (map.size() <= 0) {
                throw new OCommandException("commands.scoreboard.players.list.player.empty", new Object[] { s});
            }

            oicommandsender.a(OEnumChatFormatting.c + oicommandsender.a("commands.scoreboard.players.list.player.count", new Object[] { Integer.valueOf(map.size()), s}));
            Iterator iterator = map.values().iterator();

            while (iterator.hasNext()) {
                OScore oscore = (OScore) iterator.next();

                oicommandsender.a(oicommandsender.a("commands.scoreboard.players.list.player.entry", new Object[] { Integer.valueOf(oscore.c()), oscore.d().d(), oscore.d().b()}));
            }
        } else {
            Collection collection = oscoreboard.d();

            if (collection.size() <= 0) {
                throw new OCommandException("commands.scoreboard.players.list.empty", new Object[0]);
            }

            oicommandsender.a(OEnumChatFormatting.c + oicommandsender.a("commands.scoreboard.players.list.count", new Object[] { Integer.valueOf(collection.size())}));
            oicommandsender.a(a(collection.toArray()));
        }
    }

    protected void l(OICommandSender oicommandsender, String[] astring, int i) {
        String s = astring[i - 1];
        String s1 = d(oicommandsender, astring[i++]);
        OScoreObjective oscoreobjective = this.a(astring[i++], true);
        int j = s.equalsIgnoreCase("set") ? a(oicommandsender, astring[i++]) : a(oicommandsender, astring[i++], 1);
        OScoreboard oscoreboard = this.d();
        OScore oscore = oscoreboard.a(s1, oscoreobjective);

        if (s.equalsIgnoreCase("set")) {
            oscore.c(j);
        } else if (s.equalsIgnoreCase("add")) {
            oscore.a(j);
        } else {
            oscore.b(j);
        }

        a(oicommandsender, "commands.scoreboard.players.set.success", new Object[] { oscoreobjective.b(), s1, Integer.valueOf(oscore.c())});
    }

    protected void m(OICommandSender oicommandsender, String[] astring, int i) {
        OScoreboard oscoreboard = this.d();
        String s = d(oicommandsender, astring[i++]);

        oscoreboard.c(s);
        a(oicommandsender, "commands.scoreboard.players.reset.success", new Object[] { s});
    }

    public List a(OICommandSender oicommandsender, String[] astring) {
        if (astring.length == 1) {
            return a(astring, new String[] { "objectives", "players", "teams"});
        } else {
            if (astring[0].equalsIgnoreCase("objectives")) {
                if (astring.length == 2) {
                    return a(astring, new String[] { "list", "add", "remove", "setdisplay"});
                }

                if (astring[1].equalsIgnoreCase("add")) {
                    if (astring.length == 4) {
                        return a(astring, OScoreObjectiveCriteria.a.keySet());
                    }
                } else if (astring[1].equalsIgnoreCase("remove")) {
                    if (astring.length == 3) {
                        return a(astring, this.a(false));
                    }
                } else if (astring[1].equalsIgnoreCase("setdisplay")) {
                    if (astring.length == 3) {
                        return a(astring, new String[] { "list", "sidebar", "belowName"});
                    }

                    if (astring.length == 4) {
                        return a(astring, this.a(false));
                    }
                }
            } else if (astring[0].equalsIgnoreCase("players")) {
                if (astring.length == 2) {
                    return a(astring, new String[] { "set", "add", "remove", "reset", "list"});
                }

                if (!astring[1].equalsIgnoreCase("set") && !astring[1].equalsIgnoreCase("add") && !astring[1].equalsIgnoreCase("remove")) {
                    if ((astring[1].equalsIgnoreCase("reset") || astring[1].equalsIgnoreCase("list")) && astring.length == 3) {
                        return a(astring, this.d().d());
                    }
                } else {
                    if (astring.length == 3) {
                        return a(astring, OMinecraftServer.D().A());
                    }

                    if (astring.length == 4) {
                        return a(astring, this.a(true));
                    }
                }
            } else if (astring[0].equalsIgnoreCase("teams")) {
                if (astring.length == 2) {
                    return a(astring, new String[] { "add", "remove", "join", "leave", "empty", "list", "option"});
                }

                if (astring[1].equalsIgnoreCase("join")) {
                    if (astring.length == 3) {
                        return a(astring, this.d().f());
                    }

                    if (astring.length >= 4) {
                        return a(astring, OMinecraftServer.D().A());
                    }
                } else {
                    if (astring[1].equalsIgnoreCase("leave")) {
                        return a(astring, OMinecraftServer.D().A());
                    }

                    if (!astring[1].equalsIgnoreCase("empty") && !astring[1].equalsIgnoreCase("list") && !astring[1].equalsIgnoreCase("remove")) {
                        if (astring[1].equalsIgnoreCase("option")) {
                            if (astring.length == 3) {
                                return a(astring, this.d().f());
                            }

                            if (astring.length == 4) {
                                return a(astring, new String[] { "color", "friendlyfire", "seeFriendlyInvisibles"});
                            }

                            if (astring.length == 5) {
                                if (astring[3].equalsIgnoreCase("color")) {
                                    return a(astring, OEnumChatFormatting.a(true, false));
                                }

                                if (astring[3].equalsIgnoreCase("friendlyfire") || astring[3].equalsIgnoreCase("seeFriendlyInvisibles")) {
                                    return a(astring, new String[] { "true", "false"});
                                }
                            }
                        }
                    } else if (astring.length == 3) {
                        return a(astring, this.d().f());
                    }
                }
            }

            return null;
        }
    }

    protected List a(boolean flag) {
        Collection collection = this.d().c();
        ArrayList arraylist = new ArrayList();
        Iterator iterator = collection.iterator();

        while (iterator.hasNext()) {
            OScoreObjective oscoreobjective = (OScoreObjective) iterator.next();

            if (!flag || !oscoreobjective.c().b()) {
                arraylist.add(oscoreobjective.b());
            }
        }

        return arraylist;
    }

    public boolean a(String[] astring, int i) {
        return astring[0].equalsIgnoreCase("players") ? i == 2 : (!astring[0].equalsIgnoreCase("teams") ? false : i == 2 || i == 3);
    }
}
