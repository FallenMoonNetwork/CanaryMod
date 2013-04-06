
public class InventoryMerchant extends ItemArray<OInventoryMerchant> {

    public InventoryMerchant(OContainer oContainer, OInventoryMerchant container) {
        super(oContainer, container);
    }

    @Override
    public void update() {
        container.k_();
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
