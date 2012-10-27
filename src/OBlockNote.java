
public class OBlockNote extends OBlockContainer {

    public OBlockNote(int i) {
        super(i, 74, OMaterial.d);
        this.a(OCreativeTabs.d);
    }

    public int a(int i) {
        return this.cl;
    }

    public void a(OWorld oworld, int i, int j, int k, int l) {
        if (l > 0) {
            boolean flag = oworld.z(i, j, k);
            OTileEntityNote otileentitynote = (OTileEntityNote) oworld.p(i, j, k);

            if (otileentitynote != null && otileentitynote.b != flag) {
                if (flag) {
                    otileentitynote.a(oworld, i, j, k);
                }

                otileentitynote.b = flag;
            }
        }

    }

    public boolean a(OWorld oworld, int i, int j, int k, OEntityPlayer oentityplayer, int l, float f, float f1, float f2) {
        if (oworld.J) {
            return true;
        } else {
            OTileEntityNote otileentitynote = (OTileEntityNote) oworld.p(i, j, k);

            if (otileentitynote != null) {
                otileentitynote.a();
                otileentitynote.a(oworld, i, j, k);
            }

            return true;
        }
    }

    public void a(OWorld oworld, int i, int j, int k, OEntityPlayer oentityplayer) {
        if (!oworld.J) {
            OTileEntityNote otileentitynote = (OTileEntityNote) oworld.p(i, j, k);

            if (otileentitynote != null) {
                otileentitynote.a(oworld, i, j, k);
            }

        }
    }

    public OTileEntity a(OWorld oworld) {
        return new OTileEntityNote();
    }

    public void b(OWorld oworld, int i, int j, int k, int l, int i1) {
        float f = (float) Math.pow(2.0D, (double) (i1 - 12) / 12.0D);
        String s = "harp";

        if (l == 1) {
            s = "bd";
        }

        if (l == 2) {
            s = "snare";
        }

        if (l == 3) {
            s = "hat";
        }

        if (l == 4) {
            s = "bassattack";
        }

        oworld.a((double) i + 0.5D, (double) j + 0.5D, (double) k + 0.5D, "note." + s, 3.0F, f);
        oworld.a("note", (double) i + 0.5D, (double) j + 1.2D, (double) k + 0.5D, (double) i1 / 24.0D, 0.0D, 0.0D);
    }
}
