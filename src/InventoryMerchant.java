public class InventoryMerchant extends ItemArray<OInventoryMerchant> {

    public InventoryMerchant(OContainer oContainer, OInventoryMerchant container) {
        super(oContainer, container);
    }

    @Override
    public void update() {
        // SRG container.func_70296_d();
        container.e();
    }

    @Override
    public String getName() {
        return container.getName();
    }

    @Override
    public void setName(String value) {
        container.setName(value);
    }

}
