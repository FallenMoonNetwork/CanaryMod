/**
 * Interface for the OMerchantRecipe class
 *
 * @author gregthegeek
 *
 */
public class VillagerTrade {
    private final OMerchantRecipe recipe;

    /**
     * Wraps an OMerchantRecipe
     *
     * @param recipeBase the recipe to wrap
     */
    public VillagerTrade(OMerchantRecipe recipe) {
        this.recipe = recipe;
    }

    /**
     * Creates a new villager trade
     *
     * @param buying the item the player will give to the villager
     * @param selling the item the villager will give to the player
     */
    public VillagerTrade(Item buying, Item selling) {
        this(new OMerchantRecipe(buying.getBaseItem(), selling.getBaseItem()));
    }

    /**
     * Creates a new villager trade
     *
     * @param buyingOne the first item the player will give to the villager
     * @param buyingTwo the second item the player will give to the villager
     * @param selling the item the villager will give to the player
     */
    public VillagerTrade(Item buyingOne, Item buyingTwo, Item selling) {
        this(new OMerchantRecipe(buyingOne.getBaseItem(), buyingTwo.getBaseItem(), selling.getBaseItem()));
    }

    /**
     * Creates a new villager trade
     *
     * @param tag the tag to read the trade data from
     */
    public VillagerTrade(NBTTagCompound tag) {
        this(new OMerchantRecipe(tag.getBaseTag()));
    }

    /**
     * Returns the base recipe for this trade.
     *
     * @return
     */
    public OMerchantRecipe getRecipe() {
        return this.recipe;
    }

    /**
     * Returns the first item the player must give to the villager.
     *
     * @return
     */
    public Item getBuyingOne() {
        // SRG return new Item(getRecipe().func_77394_a());
        return new Item(getRecipe().a());
    }

    /**
     * Sets the first item the player must give to the villager.
     *
     * @param item The item
     */
    public void setBuyingOne(Item item) {
        // SRG getRecipe().field_77403_a = item.getBaseItem();
        getRecipe().a = item.getBaseItem();
    }

    /**
     * Returns the second item the player must give to the villager.
     *
     * @return
     */
    public Item getBuyingTwo() {
        // SRG return new Item(getRecipe().func_77396_b());
        return new Item(getRecipe().b());
    }

    /**
     * Sets the second item the player must give to the villager.
     *
     * @param item The item
     */
    public void setBuyingTwo(Item item) {
        // SRG getRecipe().func_77396_b = item.getBaseItem();
        getRecipe().b = item.getBaseItem();
    }

    /**
     * Returns whether or not this trade requires the player to give the villager two items.
     *
     * @return true if the player must give two items, false if the player must give only one
     */
    public boolean requiresTwoItems() {
        // SRG return getRecipe().func_77398_c();
        return getRecipe().c();
    }

    /**
     * Returns the item the player receives from the trade.
     *
     * @return
     */
    public Item getSelling() {
        // SRG return new Item(getRecipe().func_77397_d());
        return new Item(getRecipe().d());
    }

    /**
     * Sets the item the player receives from the trade.
     *
     * @param item
     */
    public void setSelling(Item item) {
        // SRG getRecipe().field_77402_c = item.getBaseItem();
        getRecipe().c = item.getBaseItem();
    }

    /**
     * Increase the number of times this was used by one.
     */
    public void use() {
        // SRG getRecipe().func_77399_f();
        getRecipe().f();
    }

    /**
     * Increases the maximum amount of times this trade can be used.
     * The default max is 7
     *
     * @param increase the amount to increase it buy
     */
    public void increaseMaxUses(int increase) {
        // SRG getRecipe().func_82783_a(increase);
        getRecipe().a(increase);
    }

    /**
     * Returns whether or not this recipe has exceeded its max usages and can no longer be used.
     *
     * @return
     */
    public boolean isUsedUp() {
        // SRG return getRecipe().func_82784_g();
        return getRecipe().g();
    }

    /**
     * Returns the data for this trade in an NBTCompoundTag.
     *
     * @return
     */
    public NBTTagCompound getDataAsTag() {
        // SRG return new NBTTagCompound(getRecipe().func_77395_g());
        return new NBTTagCompound(getRecipe().i());
    }

    /**
     * Reads the data from an NBTTagCompound into this trade
     *
     * @param tag the tag to read the data from
     */
    public void readFromTag(NBTTagCompound tag) {
        // SRG getRecipe().func_77390_a(tag.getBaseTag());
        getRecipe().a(tag.getBaseTag());
    }

    @Override
    public String toString() {
        return String.format("VillagerTrade[buying1=%s, buying2=%s, selling=%s]", getBuyingOne(), getBuyingTwo(), getSelling());
    }
}
