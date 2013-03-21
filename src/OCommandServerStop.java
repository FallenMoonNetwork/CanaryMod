public class OCommandServerStop extends OCommandBase{
    
	public String c(){
        return "stop";
	}

    public int a(){
    	return 4;
    }

    public void b(OICommandSender oicommandsender, String[] astring){
    	a(oicommandsender, "commands.stop.start", new Object[0]);

    	// CanaryMod: add extra arguments as custom Stop message
    	OMinecraftServer.D().stopServer(astring.length > 0 ? etc.combineSplit(0, astring, " ") : null);
    }
}