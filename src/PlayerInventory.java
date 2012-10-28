public class PlayerInventory extends ItemArray<OInventoryPlayer> implements Inventory {
    private final OEntityPlayerMP user;

    public PlayerInventory(Player player) {
        super(player.getUser().bK);
        user = player.getUser();
    }

    // Recoded to prevent enchantable item dupe exploit
    public void giveItem(int itemId, int amount) {
    	int remaining = amount;

    	do {
        	// Do not allow stacking of enchantable items,
        	// this is to prevent enchantment duping.
    		//
    		// Could do with a cleanup into a single function, 
    		// but this works for now.
        	if (!etc.getInstance().allowEnchantableItemStacking &&
        		((itemId >= 256 && itemId <= 258) || 
        		 (itemId >= 267 && itemId <= 279) || 
        		 (itemId >= 283 && itemId <= 286) ||
        		 (itemId >= 298 && itemId <= 317) ||
        		 (itemId == 261))) {
    			int targetSlot = getEmptySlot();
    			
    			if (targetSlot == -1) {
    				// Drop whatever is left
    				user.getPlayer().giveItemDrop(itemId, remaining);
    				remaining = 0;
    			} else {
    				addItem(new Item(itemId, 1, targetSlot));
    				remaining--;
    			}
    		} else {
    			if (hasItem(itemId, 1, 63)) {
    				Item i = getItemFromId(itemId, 63);
    				
    				if (i != null) {
    					int freeSpace = 64 - i.getAmount();
    					int toAdd;
    					if (remaining > freeSpace) {
    						toAdd = freeSpace;
    						remaining -= freeSpace;
    					} else {
    						toAdd = remaining;
    						remaining = 0;
    					}
    					i.setAmount(i.getAmount() + toAdd);
    					addItem(i);
    				}
    			} else {
    				int targetSlot = getEmptySlot();
        			
        			if (targetSlot == -1) {
        				// Drop whatever is left
        				user.getPlayer().giveItemDrop(itemId, remaining);
        				remaining = 0;
        			} else {
        				if (remaining > 64) {
        					addItem(new Item(itemId, 64, targetSlot));
        					remaining -= 64;
        				} else {
        					addItem(new Item(itemId, remaining, targetSlot));
        					remaining = 0;
        				}
        			}
    			}
    		}
    		
    	} while (remaining > 0);
    }

    @Override
    public void update() {
        user.h_();
    }

    /**
     * Returns a String value representing this PlayerInventory
     * 
     * @return String representation of this PlayerInventory
     */
    @Override
    public String toString() {
        return String.format("PlayerInventory[user=%s]", user.getPlayer());
    }

    /**
     * Returns the owner of this PlayerInventory
     * 
     * @return Player
     */
    public Player getPlayer() {
        return user.getPlayer();
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
