public class OTileEntityCommandBlock extends OTileEntity implements OICommandSender {

    private int a = 0;
    private String b = "";
    private String c = "@";

    private final CommandBlock block = new CommandBlock(this); // CanaryMod

    public OTileEntityCommandBlock() {}

    public void b(String s) {
        this.b = s;
        this.k_();
    }

    public int a(OWorld oworld) {
        if (oworld.I) {
            return 0;
        } else {
            OMinecraftServer ominecraftserver = OMinecraftServer.D();

            if (ominecraftserver != null && ominecraftserver.Z()) {
                OICommandManager oicommandmanager = ominecraftserver.E();

                if(!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.COMMAND_BLOCK_COMMAND, new CommandBlock(this), this.b.split(" "))) {
                    return oicommandmanager.a(this, this.b);
                }
            }
            return 0;
        }
    }

    public String c_() {
        return this.c;
    }

    public void c(String s) {
        this.c = s;
    }

    public void a(String s) {}

    public boolean a(int i, String s) {
        return i <= 2;
    }

    public String a(String s, Object... aobject) {
        return s;
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

    public OPacket m() {
        ONBTTagCompound onbttagcompound = new ONBTTagCompound();

        this.b(onbttagcompound);
        return new OPacket132TileEntityData(this.l, this.m, this.n, 2, onbttagcompound);
    }

    public int d() {
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
