public class Workbench extends ItemArray<OInventoryCraftResult> {
    private final OContainerWorkbench workbench;

    public Workbench(OContainerWorkbench block) {
        super(block, (OInventoryCraftResult) block.f);
        workbench = block;
    }

    @Override
    public void update() {
        workbench.b();
    }

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
