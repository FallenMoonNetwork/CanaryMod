public class OCommandServerSaveOff extends OCommandBase {

    public OCommandServerSaveOff() {}

    public String c() {
        return "save-off";
    }

    public int a() {
        return 4;
    }

    public String c(OICommandSender oicommandsender) {
        return "commands.save-off.usage";
    }

    public void b(OICommandSender oicommandsender, String[] astring) {
        OMinecraftServer ominecraftserver = OMinecraftServer.F();
        boolean flag = false;

        for (OWorldServer[] level : ominecraftserver.worlds.values()) { // CanaryMod: multiworld
            for (int i = 0; i < level.length; ++i) {
                if (level[i] != null) {
                    OWorldServer oworldserver = level[i];
                    if (!oworldserver.c) {
                        oworldserver.c = true;
                        flag = true;
                    }
                }
            }
        }

        if (flag) {
            a(oicommandsender, "commands.save.disabled", new Object[0]);
        } else {
            throw new OCommandException("commands.save-off.alreadyOff", new Object[0]);
        }
    }
}
