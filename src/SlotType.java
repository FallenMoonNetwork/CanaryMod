/**
 * Class to help define gui slots.
 *
 * @author m4411k4
 *
 */
public class SlotType {
    public static final int OUTSIDE = -999;

    public enum Type {
        DEFAULT,
        UNKNOWN_UPDATE_NEEDED,
        NULL,
        OUTSIDE,
        ARMOR,
        BEACON,
        BREWING_STAND_INGREDIENT,
        BREWING_STAND_POTION,
        CRAFTING,
        ENCHANTMENT,
        FURNACE,
        MERCHANT_RESULT,
        REPAIR,
    };

    public enum SpecificType {
        NULL,
        OUTSIDE,
        ARMOR,
        CONTAINER,
        CRAFT,
        ENCHANT,
        FUEL,
        INVENTORY,
        RESULT,
        PAYMENT,
        POTION,
        QUICKBAR,
        TRADE,
    };

    /**
     * A more reliable slot type. If a new slot type is introduced, it will return that this needs to be updated.
     *
     * @return SlotType
     */
    public static Type getSlotType(OContainer container, int slotIndex) {
        if(container == null)
            return Type.NULL;

        if(slotIndex == OUTSIDE)
            return Type.OUTSIDE;

        OSlot slot = container.a(slotIndex);
        if(slot instanceof OSlotArmor) {
            return Type.ARMOR;
        } else if(slot instanceof OSlotBeacon) {
            return Type.BEACON;
        } else if(slot instanceof OSlotBrewingStandIngredient) {
            return Type.BREWING_STAND_INGREDIENT;
        } else if(slot instanceof OSlotBrewingStandPotion) {
            return Type.BREWING_STAND_POTION;
        } else if(slot instanceof OSlotCrafting) {
            return Type.CRAFTING;
        } else if(slot instanceof OSlotEnchantment) {
            return Type.ENCHANTMENT;
        } else if(slot instanceof OSlotFurnace) {
            return Type.FURNACE;
        } else if(slot instanceof OSlotMerchantResult) {
            return Type.MERCHANT_RESULT;
        } else if(slot instanceof OSlotRepair) {
            return Type.REPAIR;
        } else if(slot.getClass() != OSlot.class) {
            return Type.UNKNOWN_UPDATE_NEEDED;
        } else {
            return Type.DEFAULT;
        }
    }

    /**
     * Attempts to define slots. Minecraft updates can break the definitions if slots are added/removed/modified.
     *
     * @return
     */
    public static SpecificType getSpecificSlotType(OContainer container, int slotIndex) {
        if(container == null)
            return SpecificType.NULL;

        if(slotIndex == OUTSIDE)
            return SpecificType.OUTSIDE;

        Inventory inv = container.getInventory();
        if(inv == null)
            return SpecificType.NULL;

        if(slotIndex < inv.getContentsSize()) {
            if(container instanceof OContainerBeacon) {
                switch(slotIndex) {
                    default:
                        return SpecificType.PAYMENT;
                }
            } else if(container instanceof OContainerBrewingStand) {
                switch(slotIndex) {
                    case 3:
                        return SpecificType.CRAFT;
                    default:
                        return SpecificType.POTION;
                }
            } else if(container instanceof OContainerChest) {
                switch(slotIndex) {
                    default:
                        return SpecificType.CONTAINER;
                }
            } else if(container instanceof OContainerDispenser) {
                switch(slotIndex) {
                    default:
                        return SpecificType.CONTAINER;
                }
            } else if(container instanceof OContainerEnchantment) {
                switch(slotIndex) {
                    default:
                        return SpecificType.ENCHANT;
                }
            } else if(container instanceof OContainerFurnace) {
                switch(slotIndex) {
                    case 0:
                        return SpecificType.CRAFT;
                    case 1:
                        return SpecificType.FUEL;
                    default:
                        return SpecificType.RESULT;
                }
            } else if(container instanceof OContainerMerchant) {
                switch(slotIndex) {
                    case 0:
                    case 1:
                        return SpecificType.TRADE;
                    default:
                        return SpecificType.RESULT;
                }
            } else if(container instanceof OContainerPlayer) {
                if(slotIndex == 0)
                    return SpecificType.RESULT;
                if(slotIndex <= 4)
                    return SpecificType.CRAFT;
            } else if(container instanceof OContainerRepair) {
                switch(slotIndex) {
                    case 0:
                    case 1:
                        return SpecificType.CRAFT;
                    default:
                        return SpecificType.RESULT;
                }
            } else if(container instanceof OContainerWorkbench) {
                if(slotIndex == 0)
                    return SpecificType.RESULT;
                if(slotIndex <= 9)
                    return SpecificType.CRAFT;
            }
        }

        int localSlot = slotIndex - inv.getContentsSize();

        if(container instanceof OContainerPlayer) {
            if(localSlot < 4)
                return SpecificType.ARMOR;

            localSlot -= 4; //remove armor index
        }

        if(localSlot >= 27 && localSlot < 36) {
            return SpecificType.QUICKBAR;
        }

        return SpecificType.INVENTORY;
    }
}
