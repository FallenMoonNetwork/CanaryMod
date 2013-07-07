public class OTileEntityCommandBlock extends OTileEntity implements OICommandSender {

    private int a;
    private String b = "";
    private String c = "@";

    private final CommandBlock block = new CommandBlock(this); // CanaryMod

    public OTileEntityCommandBlock() {}

    public void a(String s) {
        this.b = s;
        this.e();
    }

    public int a(OWorld oworld) {
        if (oworld.I) {
            return 0;
        } else {
            OMinecraftServer ominecraftserver = OMinecraftServer.F();

            if (ominecraftserver != null && ominecraftserver.ab()) {
                OICommandManager oicommandmanager = ominecraftserver.G();

                if(!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.COMMAND_BLOCK_COMMAND, this.getComplexBlock(), this.b.split(" "))) {
                    return oicommandmanager.a(this, this.b);
                }
            }
            return 0;
        }
    }

    public String c_() {
        return this.c;
    }

    public void b(String s) {
        this.c = s;
    }

    public void a(OChatMessageComponent ochatmessagecomponent) {}

    public boolean a(int i, String s) {
        return i <= 2;
    }

    public void b(ONBTTagCompound onbttagcompound) {
        super.b(onbttagcompound);
        onbttagcompound.a("Command", this.b);
        onbttagcompound.a("SuccessCount", this.a);
        onbttagcompound.a("CustomName", this.c);
    }

    public void a(ONBTTagCompound onbttagcompound) {
        super.a(onbttagcompound);
        this.b = onbttagcompound.i("Command");
        this.a = onbttagcompound.e("SuccessCount");
        if (onbttagcompound.b("CustomName")) {
            this.c = onbttagcompound.i("CustomName");
        }
    }

    public OChunkCoordinates b() {
        return new OChunkCoordinates(this.l, this.m, this.n);
    }

    public OWorld f_() {
        return this.ay();
    }

    public OPacket m() {
        ONBTTagCompound onbttagcompound = new ONBTTagCompound();

        this.b(onbttagcompound);
        return new OPacket132TileEntityData(this.l, this.m, this.n, 2, onbttagcompound);
    }

    public int f() {
        return this.a;
    }

    public void a(int i) {
        this.a = i;
    }

    public String getCommand() { // CanaryMod: allows us to access the command stored
        return this.b;
    }

    @Override
    public CommandBlock getComplexBlock() {
        return block;
    }
}
