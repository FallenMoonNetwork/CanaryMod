public class Workbench extends ItemArray<OInventoryCraftResult> {
    private final OContainerWorkbench workbench;

    public Workbench(OContainerWorkbench block) {
        // SRG super(block, (OInventoryCraftResult) block.field_75160_f);
        super(block, (OInventoryCraftResult) block.f);
        workbench = block;
    }

    @Override
    public void update() {
        // SRG workbench.func_75142_b();
        workbench.b();
    }

    @Deprecated
    public void update(Player player) {
        this.update(); // We have an update method now!
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
