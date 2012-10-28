import java.util.concurrent.Callable;

public class OCallableServerMemoryStats implements Callable {

    final OMinecraftServer a;

    public OCallableServerMemoryStats(OMinecraftServer ominecraftserver) {
        this.a = ominecraftserver;
    }

    public String a() {
        int i = this.a.worlds.get(this.a.J())[0].R().c();
        int j = 56 * i;
        int k = j / 1024 / 1024;
        int l = this.a.worlds.get(this.a.J())[0].R().d();
        int i1 = 56 * l;
        int j1 = i1 / 1024 / 1024;

        return i + " (" + j + " bytes; " + k + " MB) allocated, " + l + " (" + i1 + " bytes; " + j1 + " MB) used";
    }

    public Object call() {
        return this.a();
    }
}
