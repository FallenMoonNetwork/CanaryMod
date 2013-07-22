/**
 * Interface for villagers
 *
 * @author gregthegeek
 *
 */
public class Villager extends Mob {

    /**
     * Represents the profession of the villager
     *
     * @author gregthegeek
     *
     */
    public enum Profession {
        FARMER,
        LIBRARIAN,
        PRIEST,
        BLACKSMITH,
        BUTCHER,
        GENERIC;
    }

    /**
     * Creates a villager wrapper
     *
     * @param oentity The entity to wrap
     */
    public Villager(OEntityVillager oentity) {
        super(oentity);
    }

    /**
     * Creates a new villager
     *
     * @param world The world in which to create it
     */
    public Villager(World world) {
        super("Villager", world);
    }

    /**
     * Creates a new villager
     *
     * @param location The location at which to create it
     */
    public Villager(Location location) {
        super("Villager", location);
    }

    /**
     * Returns the profession of the villager
     *
     * @return
     */
    public Profession getProfession() {
        // SRG return Profession.values()[getEntity().func_70946_n()];
        return Profession.values()[getEntity().bT()];
    }

    /**
     * Sets the profession of the villager
     *
     * @param profession
     */
    public void setProfession(Profession profession) {
        // SRG getEntity().func_70938_b(profession.ordinal());
        getEntity().p(profession.ordinal());
    }

    @Override
    public OEntityVillager getEntity() {
        return (OEntityVillager) entity;
    }

    /**
     * Returns an immutable array of this villager's trades
     *
     * @return
     */
    public VillagerTrade[] getTrades() {
        // SRG OMerchantRecipeList list = getEntity().func_70934_b((OEntityPlayer) null);
        OMerchantRecipeList list = getEntity().b((OEntityPlayer) null);
        
        VillagerTrade[] rt = new VillagerTrade[list.size()];
        for (int i=0; i<rt.length; i++) {
            rt[i] = ((OMerchantRecipe) list.get(i)).getVillagerTrade();
        }
        return rt;
    }

    /**
     * Adds a trade to this villager
     *
     * @param trade
     */
    public void addTrade(VillagerTrade trade) {
        // SRG getEntity().func_70934_b((OEntityPlayer) null).add(trade.getRecipe());
        getEntity().b((OEntityPlayer) null).add(trade.getRecipe());
    }

    /**
     * Removes a trade from this villager
     *
     * @param index the index of the trade to remove
     */
    public void removeTrade(int index) {
        // SRG getEntity().func_70934_b((OEntityPlayer) null).remove(index);
        getEntity().b((OEntityPlayer) null).remove(index);
    }
}
