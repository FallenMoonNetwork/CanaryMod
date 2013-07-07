public class OCommandServerStop extends OCommandBase {

    public OCommandServerStop() {}

    public String c() {
        return "stop";
    }

    public int a() {
        return 4;
    }

    public String c(OICommandSender oicommandsender) {
        return "commands.stop.usage";
    }

    public void b(OICommandSender oicommandsender, String[] astring) {
        a(oicommandsender, "commands.stop.start", new Object[0]);
    	OMinecraftServer.F().stopServer(astring.length > 0 ? etc.combineSplit(0, astring, " ") : null); // CanaryMod: add extra arguments as custom Stop message
    }
}
