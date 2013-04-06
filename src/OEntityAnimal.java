import java.util.List;

public abstract class OEntityAnimal extends OEntityAgeable implements OIAnimals {

    private int d;
    private int e = 0;
    private Mob mob = new Mob(this);

    public OEntityAnimal(OWorld oworld) {
        super(oworld);
    }

    protected void bp() {
        if (this.b() != 0) {
            this.d = 0;
        }

        super.bp();
    }

    public void c() {
        super.c();
        if (this.b() != 0) {
            this.d = 0;
        }

        if (this.d > 0) {
            --this.d;
            String s = "heart";

            if (this.d % 10 == 0) {
                double d0 = this.ab.nextGaussian() * 0.02D;
                double d1 = this.ab.nextGaussian() * 0.02D;
                double d2 = this.ab.nextGaussian() * 0.02D;

                this.q.a(s, this.u + (double) (this.ab.nextFloat() * this.O * 2.0F) - (double) this.O, this.v + 0.5D + (double) (this.ab.nextFloat() * this.P), this.w + (double) (this.ab.nextFloat() * this.O * 2.0F) - (double) this.O, d0, d1, d2);
            }
        } else {
            this.e = 0;
        }
    }

    protected void a(OEntity oentity, float f) {
        if (oentity instanceof OEntityPlayer) {
            if (f < 3.0F) {
                double d0 = oentity.u - this.u;
                double d1 = oentity.w - this.w;

                this.A = (float) (Math.atan2(d1, d0) * 180.0D / 3.1415927410125732D) - 90.0F;
                this.b = true;
            }

            OEntityPlayer oentityplayer = (OEntityPlayer) oentity;

            if (oentityplayer.cb() == null || !this.c(oentityplayer.cb())) {
                this.a_ = null;
            }
        } else if (oentity instanceof OEntityAnimal) {
            OEntityAnimal oentityanimal = (OEntityAnimal) oentity;

            if (this.b() > 0 && oentityanimal.b() < 0) {
                if ((double) f < 2.5D) {
                    this.b = true;
                }
            } else if (this.d > 0 && oentityanimal.d > 0) {
                if (oentityanimal.a_ == null && !(Boolean) manager.callHook(PluginLoader.Hook.MOB_TARGET, this.getEntity(), oentityanimal.getEntity())) {
                    oentityanimal.a_ = this;
                }

                if (oentityanimal.a_ == this && (double) f < 3.5D) {
                    ++oentityanimal.d;
                    ++this.d;
                    ++this.e;
                    if (this.e % 4 == 0) {
                        this.q.a("heart", this.u + (double) (this.ab.nextFloat() * this.O * 2.0F) - (double) this.O, this.v + 0.5D + (double) (this.ab.nextFloat() * this.P), this.w + (double) (this.ab.nextFloat() * this.O * 2.0F) - (double) this.O, 0.0D, 0.0D, 0.0D);
                    }

                    if (this.e == 60) {
                        this.b((OEntityAnimal) oentity);
                    }
                } else {
                    this.e = 0;
                }
            } else {
                this.e = 0;
                this.a_ = null;
            }
        }
    }

    private void b(OEntityAnimal oentityanimal) {
        OEntityAgeable oentityageable = this.a((OEntityAgeable) oentityanimal);

        if (oentityageable != null) {
            this.a(6000);
            oentityanimal.a(6000);
            this.d = 0;
            this.e = 0;
            this.a_ = null;
            oentityanimal.a_ = null;
            oentityanimal.e = 0;
            oentityanimal.d = 0;
            oentityageable.a(-24000);
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

    public boolean a(ODamageSource odamagesource, int i) {
        if (this.aq()) {
            return false;
        } else {
            this.c = 60;
            this.a_ = null;
            this.d = 0;
            return super.a(odamagesource, i);
        }
    }

    public float a(int i, int j, int k) {
        return this.q.a(i, j - 1, k) == OBlock.y.cz ? 10.0F : this.q.q(i, j, k) - 0.5F;
    }

    public void b(ONBTTagCompound onbttagcompound) {
        super.b(onbttagcompound);
        onbttagcompound.a("InLove", this.d);
    }

    public void a(ONBTTagCompound onbttagcompound) {
        super.a(onbttagcompound);
        this.d = onbttagcompound.e("InLove");
    }

    protected OEntity j() {
        if (this.c > 0) {
            return null;
        } else {
            float f = 8.0F;
            List list;
            int i;
            OEntityAnimal oentityanimal;

            if (this.d > 0) {
                list = this.q.a(this.getClass(), this.E.b((double) f, (double) f, (double) f));

                for (i = 0; i < list.size(); ++i) {
                    oentityanimal = (OEntityAnimal) list.get(i);
                    if (oentityanimal != this && oentityanimal.d > 0) {
                        return oentityanimal;
                    }
                }
            } else if (this.b() == 0) {
                list = this.q.a(OEntityPlayer.class, this.E.b((double) f, (double) f, (double) f));

                for (i = 0; i < list.size(); ++i) {
                    OEntityPlayer oentityplayer = (OEntityPlayer) list.get(i);

                    if (oentityplayer.cb() != null && this.c(oentityplayer.cb())) {
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

    public boolean bv() {
        int i = OMathHelper.c(this.u);
        int j = OMathHelper.c(this.E.b);
        int k = OMathHelper.c(this.w);

        return this.q.a(i, j - 1, k) == OBlock.y.cz && this.q.m(i, j, k) > 8 && super.bv();
    }

    public int aQ() {
        return 120;
    }

    protected boolean bm() {
        return false;
    }

    protected int d(OEntityPlayer oentityplayer) {
        return 1 + this.q.s.nextInt(3);
    }

    public boolean c(OItemStack oitemstack) {
        return oitemstack.c == OItem.U.cp;
    }

    public boolean a_(OEntityPlayer oentityplayer) {
        OItemStack oitemstack = oentityplayer.bK.h();

        if (oitemstack != null && this.c(oitemstack) && this.b() == 0 && this.d <= 0) {
            if (!oentityplayer.ce.d) {
                --oitemstack.a;
                if (oitemstack.a <= 0) {
                    oentityplayer.bK.a(oentityplayer.bK.c, (OItemStack) null);
                }
            }

            this.d = 600;
            this.a_ = null;

            for (int i = 0; i < 7; ++i) {
                double d0 = this.ab.nextGaussian() * 0.02D;
                double d1 = this.ab.nextGaussian() * 0.02D;
                double d2 = this.ab.nextGaussian() * 0.02D;

                this.q.a("heart", this.u + (double) (this.ab.nextFloat() * this.O * 2.0F) - (double) this.O, this.v + 0.5D + (double) (this.ab.nextFloat() * this.P), this.w + (double) (this.ab.nextFloat() * this.O * 2.0F) - (double) this.O, d0, d1, d2);
            }

            return true;
        } else {
            return super.a_(oentityplayer);
        }
    }

    public boolean r() {
        return this.d > 0;
    }

    public void s() {
        this.d = 0;
    }

    public boolean a(OEntityAnimal oentityanimal) {
        return oentityanimal == this ? false : (oentityanimal.getClass() != this.getClass() ? false : this.r() && oentityanimal.r());
    }

    @Override
    public LivingEntity getEntity() {
        return mob;
    } //
}
