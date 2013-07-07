public class OCommandServerSaveOn extends OCommandBase {

    public OCommandServerSaveOn() {}

    public String c() {
        return "save-on";
    }

    public int a() {
        return 4;
    }

    public String c(OICommandSender oicommandsender) {
        return "commands.save-on.usage";
    }

    public void b(OICommandSender oicommandsender, String[] astring) {
        OMinecraftServer ominecraftserver = OMinecraftServer.F();
        boolean flag = false;

        for (OWorldServer[] level : ominecraftserver.worlds.values()) { // CanaryMod: multiworld
            for (int i = 0; i < level.length; ++i) {
                if (level[i] != null) {
                    OWorldServer oworldserver = level[i];

                    if (oworldserver.c) {
                        oworldserver.c = false;
                        flag = true;
                    }
                }
            }
        }

        if (flag) {
            a(oicommandsender, "commands.save.enabled", new Object[0]);
        } else {
            throw new OCommandException("commands.save-on.alreadyOn", new Object[0]);
        }
    }
}
