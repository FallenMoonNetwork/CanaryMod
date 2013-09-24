import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.List;

public class OPacket20NamedEntitySpawn extends OPacket {

    public int a;
    public String b;
    public int c;
    public int d;
    public int e;
    public byte f;
    public byte g;
    public int h;
    private ODataWatcher i;
    private List j;

    public OPacket20NamedEntitySpawn() {}

    public OPacket20NamedEntitySpawn(OEntityPlayer oentityplayer) {
        this.a = oentityplayer.k;
        this.b = oentityplayer.getDisplayName(); // CanaryMod: use display name
        this.c = OMathHelper.c(oentityplayer.u * 32.0D);
        this.d = OMathHelper.c(oentityplayer.v * 32.0D);
        this.e = OMathHelper.c(oentityplayer.w * 32.0D);
        this.f = (byte) ((int) (oentityplayer.A * 256.0F / 360.0F));
        this.g = (byte) ((int) (oentityplayer.B * 256.0F / 360.0F));
        OItemStack oitemstack = oentityplayer.bn.h();

        this.h = oitemstack == null ? 0 : oitemstack.d;
        this.i = oentityplayer.v();
    }

    public void a(DataInput datainput) throws IOException {
        this.a = datainput.readInt();
        this.b = a(datainput, 16);
        this.c = datainput.readInt();
        this.d = datainput.readInt();
        this.e = datainput.readInt();
        this.f = datainput.readByte();
        this.g = datainput.readByte();
        this.h = datainput.readShort();
        this.j = ODataWatcher.a(datainput);
    }

    public void a(DataOutput dataoutput) throws IOException {
        dataoutput.writeInt(this.a);
        a(this.b, dataoutput);
        dataoutput.writeInt(this.c);
        dataoutput.writeInt(this.d);
        dataoutput.writeInt(this.e);
        dataoutput.writeByte(this.f);
        dataoutput.writeByte(this.g);
        dataoutput.writeShort(this.h);
        this.i.a(dataoutput);
    }

    public void a(ONetHandler onethandler) {
        onethandler.a(this);
    }

    public int a() {
        return 28;
    }
}
