public class OCommandServerSaveAll extends OCommandBase {

    public OCommandServerSaveAll() {}

    public String c() {
        return "save-all";
    }

    public int a() {
        return 4;
    }

    public void b(OICommandSender oicommandsender, String[] astring) {
        OMinecraftServer ominecraftserver = OMinecraftServer.D();

        oicommandsender.a(oicommandsender.a("commands.save.start", new Object[0]));
        if (ominecraftserver.ad() != null) {
            ominecraftserver.ad().g();
        }

        try {
            for (OWorldServer[] level: ominecraftserver.worlds.values()) { // CanaryMod: multiworld
                for (int i = 0; i < level.length; ++i) {
                    if (level[i] != null) {
                        OWorldServer oworldserver = level[i];
                        boolean flag = oworldserver.c;

                        oworldserver.c = false;
                        oworldserver.a(true, (OIProgressUpdate) null);
                        oworldserver.c = flag;
                    }
                }
            }
        } catch (OMinecraftException ominecraftexception) {
            a(oicommandsender, "commands.save.failed", new Object[] { ominecraftexception.getMessage()});
            return;
        }

        a(oicommandsender, "commands.save.success", new Object[0]);
    }
}
