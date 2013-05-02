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
            int i;
            OWorldServer oworldserver;
            boolean flag;

            for (OWorldServer[] level: ominecraftserver.worlds.values()) { // CanaryMod: multiworld
                for (i = 0; i < level.length; ++i) {
                    if (level[i] != null) {
                        oworldserver = level[i];
                        flag = oworldserver.c;
                        oworldserver.c = false;
                        oworldserver.a(true, (OIProgressUpdate) null);
                        oworldserver.c = flag;
                    }
                }
            }

            if (astring.length > 0 && "flush".equals(astring[0])) {
                oicommandsender.a(oicommandsender.a("commands.save.flushStart", new Object[0]));

                for (OWorldServer[] level: ominecraftserver.worlds.values()) { // CanaryMod: multiworld
                    for (i = 0; i < level.length; ++i) {
                        if (level[i] != null) {
                            oworldserver = level[i];
                            flag = oworldserver.c;
                            oworldserver.c = false;
                            oworldserver.m();
                            oworldserver.c = flag;
                        }
                    }
                }

                oicommandsender.a(oicommandsender.a("commands.save.flushEnd", new Object[0]));
            }
        } catch (OMinecraftException ominecraftexception) {
            a(oicommandsender, "commands.save.failed", new Object[] { ominecraftexception.getMessage()});
            return;
        }

        a(oicommandsender, "commands.save.success", new Object[0]);
    }
}
