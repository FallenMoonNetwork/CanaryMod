import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class OPacket3Chat extends OPacket {

    public String a;
    private boolean b;

    public OPacket3Chat() {
        this.b = true;
    }

    public OPacket3Chat(OChatMessageComponent ochatmessagecomponent) {
        this(ochatmessagecomponent.i());
    }

    public OPacket3Chat(OChatMessageComponent ochatmessagecomponent, boolean flag) {
        this(ochatmessagecomponent.i(), flag);
    }

    private OPacket3Chat(String s) {
        this(s, true);
    }

    private OPacket3Chat(String s, boolean flag) {
        this.b = true;
        if (s.length() > 32767) {
            s = s.substring(0, 32767);
        }

        this.a = s;
        this.b = flag;
    }

    public void a(DataInput datainput) throws IOException {
        this.a = a(datainput, 32767);
    }

    public void a(DataOutput dataoutput) throws IOException {
        a(this.a, dataoutput);
    }

    public void a(ONetHandler onethandler) {
        onethandler.a(this);
    }

    public int a() {
        return 2 + this.a.length() * 2;
    }

    public boolean d() {
        return this.b;
    }
}
