import java.util.List;

public abstract class OEntityAnimal extends OEntityAgeable implements OIAnimals {

    private int bp;
    private int bq;

    public OEntityAnimal(OWorld oworld) {
        super(oworld);
    }

    protected void bj() {
        if (this.b() != 0) {
            this.bp = 0;
        }

        super.bj();
    }

    public void c() {
        super.c();
        if (this.b() != 0) {
            this.bp = 0;
        }

        if (this.bp > 0) {
            --this.bp;
            String s = "heart";

            if (this.bp % 10 == 0) {
                double d0 = this.ab.nextGaussian() * 0.02D;
                double d1 = this.ab.nextGaussian() * 0.02D;
                double d2 = this.ab.nextGaussian() * 0.02D;

                this.q.a(s, this.u + (double) (this.ab.nextFloat() * this.O * 2.0F) - (double) this.O, this.v + 0.5D + (double) (this.ab.nextFloat() * this.P), this.w + (double) (this.ab.nextFloat() * this.O * 2.0F) - (double) this.O, d0, d1, d2);
            }
        } else {
            this.bq = 0;
        }
    }

    protected void a(OEntity oentity, float f) {
        if (oentity instanceof OEntityPlayer) {
            if (f < 3.0F) {
                double d0 = oentity.u - this.u;
                double d1 = oentity.w - this.w;

                this.A = (float) (Math.atan2(d1, d0) * 180.0D / 3.1415927410125732D) - 90.0F;
                this.bn = true;
            }

            OEntityPlayer oentityplayer = (OEntityPlayer) oentity;

            if (oentityplayer.bx() == null || !this.c(oentityplayer.bx())) {
                this.j = null;
            }
        } else if (oentity instanceof OEntityAnimal) {
            OEntityAnimal oentityanimal = (OEntityAnimal) oentity;

            if (this.b() > 0 && oentityanimal.b() < 0) {
                if ((double) f < 2.5D) {
                    this.bn = true;
                }
            } else if (this.bp > 0 && oentityanimal.bp > 0) {
                if (oentityanimal.j == null && !(Boolean) manager.callHook(PluginLoader.Hook.MOB_TARGET, this.getEntity(), oentityanimal.getEntity())) {
                    oentityanimal.j = this;
                }

                if (oentityanimal.j == this && (double) f < 3.5D) {
                    ++oentityanimal.bp;
                    ++this.bp;
                    ++this.bq;
                    if (this.bq % 4 == 0) {
                        this.q.a("heart", this.u + (double) (this.ab.nextFloat() * this.O * 2.0F) - (double) this.O, this.v + 0.5D + (double) (this.ab.nextFloat() * this.P), this.w + (double) (this.ab.nextFloat() * this.O * 2.0F) - (double) this.O, 0.0D, 0.0D, 0.0D);
                    }

                    if (this.bq == 60) {
                        this.b((OEntityAnimal) oentity);
                    }
                } else {
                    this.bq = 0;
                }
            } else {
                this.bq = 0;
                this.j = null;
            }
        }
    }

    private void b(OEntityAnimal oentityanimal) {
        OEntityAgeable oentityageable = this.a((OEntityAgeable) oentityanimal);

        if (oentityageable != null) {
            this.c(6000);
            oentityanimal.c(6000);
            this.bp = 0;
            this.bq = 0;
            this.j = null;
            oentityanimal.j = null;
            oentityanimal.bq = 0;
            oentityanimal.bp = 0;
            oentityageable.c(-24000);
            oentityageable.b(this.u, this.v, this.w, this.A, this.B);

            for (int i = 0; i < 7; ++i) {
                double d0 = this.ab.nextGaussian() * 0.02D;
                double d1 = this.ab.nextGaussian() * 0.02D;
                double d2 = this.ab.nextGaussian() * 0.02D;

                this.q.a("heart", this.u + (double) (this.ab.nextFloat() * this.O * 2.0F) - (double) this.O, this.v + 0.5D + (double) (this.ab.nextFloat() * this.P), this.w + (double) (this.ab.nextFloat() * this.O * 2.0F) - (double) this.O, d0, d1, d2);
            }

            this.q.d((OEntity) oentityageable);
        }
    }

    public boolean a(ODamageSource odamagesource, float f) {
        if (this.aq()) {
            return false;
        } else {
            this.bo = 60;
            if (!this.be()) {
                OAttributeInstance oattributeinstance = this.a(OSharedMonsterAttributes.d);

                if (oattributeinstance.a(h) == null) {
                    oattributeinstance.a(i);
                }
            }

            this.j = null;
            this.bp = 0;
            return super.a(odamagesource, f);
        }
    }

    public float a(int i, int j, int k) {
        return this.q.a(i, j - 1, k) == OBlock.z.cF ? 10.0F : this.q.q(i, j, k) - 0.5F;
    }

    public void b(ONBTTagCompound onbttagcompound) {
        super.b(onbttagcompound);
        onbttagcompound.a("InLove", this.bp);
    }

    public void a(ONBTTagCompound onbttagcompound) {
        super.a(onbttagcompound);
        this.bp = onbttagcompound.e("InLove");
    }

    protected OEntity bL() {
        if (this.bo > 0) {
            return null;
        } else {
            float f = 8.0F;
            List list;
            int i;
            OEntityAnimal oentityanimal;

            if (this.bp > 0) {
                list = this.q.a(this.getClass(), this.E.b((double) f, (double) f, (double) f));

                for (i = 0; i < list.size(); ++i) {
                    oentityanimal = (OEntityAnimal) list.get(i);
                    if (oentityanimal != this && oentityanimal.bp > 0) {
                        return oentityanimal;
                    }
                }
            } else if (this.b() == 0) {
                list = this.q.a(OEntityPlayer.class, this.E.b((double) f, (double) f, (double) f));

                for (i = 0; i < list.size(); ++i) {
                    OEntityPlayer oentityplayer = (OEntityPlayer) list.get(i);

                    if (oentityplayer.bx() != null && this.c(oentityplayer.bx())) {
                        return oentityplayer;
                    }
                }
            } else if (this.b() > 0) {
                list = this.q.a(this.getClass(), this.E.b((double) f, (double) f, (double) f));

                for (i = 0; i < list.size(); ++i) {
                    oentityanimal = (OEntityAnimal) list.get(i);
                    if (oentityanimal != this && oentityanimal.b() < 0) {
                        return oentityanimal;
                    }
                }
            }

            return null;
        }
    }

    public boolean bs() {
        int i = OMathHelper.c(this.u);
        int j = OMathHelper.c(this.E.b);
        int k = OMathHelper.c(this.w);

        return this.q.a(i, j - 1, k) == OBlock.z.cF && this.q.m(i, j, k) > 8 && super.bs();
    }

    public int o() {
        return 120;
    }

    protected boolean t() {
        return false;
    }

    protected int e(OEntityPlayer oentityplayer) {
        return 1 + this.q.s.nextInt(3);
    }

    public boolean c(OItemStack oitemstack) {
        return oitemstack.d == OItem.V.cv;
    }

    public boolean a(OEntityPlayer oentityplayer) {
        OItemStack oitemstack = oentityplayer.bn.h();

        if (oitemstack != null && this.c(oitemstack) && this.b() == 0 && this.bp <= 0) {
            if (!oentityplayer.bG.d) {
                --oitemstack.b;
                if (oitemstack.b <= 0) {
                    oentityplayer.bn.a(oentityplayer.bn.c, (OItemStack) null);
                }
            }

            this.bX();
            return true;
        } else {
            return super.a(oentityplayer);
        }
    }

    public void bX() {
        this.bp = 600;
        this.j = null;
        this.q.a((OEntity) this, (byte) 18);
    }

    public boolean bY() {
        return this.bp > 0;
    }

    public void bZ() {
        this.bp = 0;
    }

    public boolean a(OEntityAnimal oentityanimal) {
        return oentityanimal == this ? false : (oentityanimal.getClass() != this.getClass() ? false : this.bY() && oentityanimal.bY());
    }
}
