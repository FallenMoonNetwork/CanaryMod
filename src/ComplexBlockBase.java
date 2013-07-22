/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author willem
 */
public abstract class ComplexBlockBase<T extends OTileEntity> implements ComplexBlock {
    T tileEntity;

    protected ComplexBlockBase(T tileEntity) {
        this.tileEntity = tileEntity;
    }

    @Override
    public int getX() {
        // SRG return this.tileEntity.field_70329_l;
        return this.tileEntity.l;
    }

    @Override
    public int getY() {
        // SRG return this.tileEntity.field_70330_m;
        return this.tileEntity.m;
    }

    @Override
    public int getZ() {
        // SRG return this.tileEntity.field_70327_n;
        return this.tileEntity.n;
    }

    @Override
    public World getWorld() {
        // SRG return this.tileEntity.field_70331_k.world;
        return this.tileEntity.k.world;
    }

    @Override
    public void update() {
        // SRG tileEntity.func_70316_g();
        tileEntity.h();
    }

    @Override
    public Block getBlock() {
        return getWorld().getBlockAt(getX(), getY(), getZ());
    }

    @Override
    public NBTTagCompound getMetaTag() {
        return tileEntity.metadata;
    }

    @Override
    public void readFromTag(NBTTagCompound tag) {
        tileEntity.a(tag.getBaseTag());
    }

    @Override
    public void writeToTag(NBTTagCompound tag) {
        // SRG tileEntity.func_70310_b(tag.getBaseTag());
        tileEntity.b(tag.getBaseTag());
    }

}
