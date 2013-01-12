/**
 * An interface class to Enchantment Tables.
 * @author willem
 */
public class EnchantmentTable extends ItemArray<OInventoryBasic> {
    private final OContainerEnchantment enchantTable;

    public EnchantmentTable(OContainerEnchantment block) {
        super(block, (OInventoryBasic) block.a);
        enchantTable = block;
    }

    @Override
    public void update() {
        enchantTable.b();
    }

    public void update(Player player) {
        this.update(); // Apparently we have an update method now
    }

    @Override
    public String getName() {
        return container.getName();
    }

    @Override
    public void setName(String value) {
        container.setName(value);
    }

    /**
     * Enchants the item and subtracts the XP from the player.
     * @param player The {@link Player} to subract XP from
     * @param slot The slot the player would've clicked. Range: 0-2
     * @return false when <tt>player</tt> doesn't have enough XP.
     * @see #getEnchantLevels() to see which slot has which level.
     * @see Player#getXP() For checking XP levels.
     */
    public boolean enchantItem(Player player, int slot) {
        return enchantTable.a(player.getUser(), slot);
    }

    /**
     * Gets the levels as displayed in the GUI.
     * The upper slot has index 0.
     * @return an int[3] containing the levels of the slots
     */
    public int[] getEnchantLevels() {
        return enchantTable.g;
    }

}
