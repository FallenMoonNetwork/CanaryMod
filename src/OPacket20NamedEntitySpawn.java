import java.io.DataInputStream;
import java.io.DataOutputStream;
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
        OItemStack oitemstack = oentityplayer.bK.h();

        this.h = oitemstack == null ? 0 : oitemstack.c;
        this.i = oentityplayer.u();
    }

    public void a(DataInputStream datainputstream) throws IOException {
        this.a = datainputstream.readInt();
        this.b = a(datainputstream, 16);
        this.c = datainputstream.readInt();
        this.d = datainputstream.readInt();
        this.e = datainputstream.readInt();
        this.f = datainputstream.readByte();
        this.g = datainputstream.readByte();
        this.h = datainputstream.readShort();
        this.j = ODataWatcher.a(datainputstream);
    }

    public void a(DataOutputStream dataoutputstream) throws IOException {
        dataoutputstream.writeInt(this.a);
        a(this.b, dataoutputstream);
        dataoutputstream.writeInt(this.c);
        dataoutputstream.writeInt(this.d);
        dataoutputstream.writeInt(this.e);
        dataoutputstream.writeByte(this.f);
        dataoutputstream.writeByte(this.g);
        dataoutputstream.writeShort(this.h);
        this.i.a(dataoutputstream);
    }

    public void a(ONetHandler onethandler) {
        onethandler.a(this);
    }

    public int a() {
        return 28;
    }
}
