
import java.util.ArrayList;
import java.util.List;

<<<<<<< HEAD
public class HumanEntity extends LivingEntity {
=======
public class HumanEntity extends LivingEntityBase {
>>>>>>> 73d9f76... Update to 1.6.1, less MD5/Versioning.
    protected PlayerInventory inventory;

    /**
     * Constructor
     */
    public HumanEntity() {}

    /**
     * Constructor
     *
     * @param human
     */
    public HumanEntity(OEntityPlayer human) {
        super(human);
        inventory = new PlayerInventory(this);
    }

    /**
     * Returns the entity we're wrapping.
     *
     * @return
     */
    @Override
    public OEntityPlayer getEntity() {
        return entity == null ? null : (OEntityPlayer) entity;
    }

    /**
     * Returns the name
     *
     * @return
     */
    @Override
    public String getName() {
        OEntityPlayer oep = getEntity();
<<<<<<< HEAD
        return oep == null ? null : oep.bS;
=======
        return oep == null ? null : oep.bu;
>>>>>>> 73d9f76... Update to 1.6.1, less MD5/Versioning.
    }

    /**
     * Returns the name displayed above this player's head.
     *
     * @return String
     */
    public String getDisplayName() {
        OEntityPlayer oep = getEntity();
        return oep == null ? null : oep.getDisplayName();
    }

    /**
     * Sets the name displayed above this player's head.
     *
     * @param name The name displayed. Any non-color modification will affect skin.
     */
    public void setDisplayName(String name) {
        if (getEntity() != null) {
            getEntity().setDisplayName(name);
        }
    }

    /**
     * Returns whether this player can receive damage.
     * @return the disableDamage state
     */
    public boolean isDamageDisabled() {
        OEntityPlayer oep = getEntity();
<<<<<<< HEAD
        return oep == null ? null : oep.ce.a;
=======
        return oep == null ? null : oep.bG.a;
>>>>>>> 73d9f76... Update to 1.6.1, less MD5/Versioning.
    }

    /**
     * Sets whether this player can receive damage.
     * @param disabled the new value.
     * @see #updateCapabilities()
     */
    public void setDamageDisabled(boolean disabled) {
        if (getEntity() != null) {
<<<<<<< HEAD
            getEntity().ce.a = disabled;
=======
            getEntity().bG.a = disabled;
>>>>>>> 73d9f76... Update to 1.6.1, less MD5/Versioning.
        }
    }

    /**
     * Returns whether the player is flying.
     * @return the flying state
     */
    public boolean isFlying() {
        OEntityPlayer oep = getEntity();
<<<<<<< HEAD
        return oep == null ? null : oep.ce.b;
=======
        return oep == null ? null : oep.bG.b;
>>>>>>> 73d9f76... Update to 1.6.1, less MD5/Versioning.
    }

    /**
     * Sets whether the player is flying.
     * @param flying the flying state.
     * @see #updateCapabilities()
     */
    public void setFlying(boolean flying) {
        if (getEntity() != null) {
<<<<<<< HEAD
            getEntity().ce.b = flying;
=======
            getEntity().bG.b = flying;
>>>>>>> 73d9f76... Update to 1.6.1, less MD5/Versioning.
        }
    }

    /**
     * Returns whether this player is allowed to fly.
     * @return the canFly state
     */
    public boolean canFly() {
        OEntityPlayer oep = getEntity();
<<<<<<< HEAD
        return oep == null ? null : oep.ce.c;
=======
        return oep == null ? null : oep.bG.c;
>>>>>>> 73d9f76... Update to 1.6.1, less MD5/Versioning.
    }

    /**
     * Sets whether this player is allowed to fly.
     * @param allow <tt>true</tt> to allow flying, <tt>false</tt> to deny it.
     * @see #updateCapabilities()
     */
    public void setCanFly(boolean allow) {
        if (getEntity() != null) {
<<<<<<< HEAD
            getEntity().ce.c = allow;
=======
            getEntity().bG.c = allow;
>>>>>>> 73d9f76... Update to 1.6.1, less MD5/Versioning.
        }
    }

    /**
     * Returns whether the player has a creative perks.
     * When set, enables stuff like items not depleting on use, buckets not
     * emptying, anvils and enchantments always working, etc.
     * @return whether player has a creative inventory.
     */
    public boolean hasCreativePerks() {
        OEntityPlayer oep = getEntity();
<<<<<<< HEAD
        return oep == null ? null : oep.ce.d;
=======
        return oep == null ? null : oep.bG.d;
>>>>>>> 73d9f76... Update to 1.6.1, less MD5/Versioning.
    }

    /**
     * Sets whether the player has a creative inventory.
     * When set, enables stuff like items not depleting on use, buckets not
     * emptying, anvils and enchantments always working, etc.
     * @param creativePerks the new state
     * @see #updateCapabilities()
     */
    public void setCreativePerks(boolean creativePerks) {
        if (getEntity() != null) {
<<<<<<< HEAD
            getEntity().ce.d = creativePerks;
=======
            getEntity().bG.d = creativePerks;
>>>>>>> 73d9f76... Update to 1.6.1, less MD5/Versioning.
        }
    }

    /**
     * Returns whether the player has build restrictions like in Adventure.
     * @return whether the player has build restrictions.
     */
    public boolean hasAdventureRestrictions() {
        OEntityPlayer oep = getEntity();
<<<<<<< HEAD
        return oep == null ? null : !oep.ce.e;
=======
        return oep == null ? null : !oep.bG.e;
>>>>>>> 73d9f76... Update to 1.6.1, less MD5/Versioning.
    }

    /**
     * Sets whether the player has build restrictions like in Adventure.
     * @param restrict The new value for the flag.
     * @see #updateCapabilities()
     */
    public void setAdventureRestrictions(boolean restrict) {
        if (getEntity() != null) {
<<<<<<< HEAD
            getEntity().ce.e = !restrict;
=======
            getEntity().bG.e = !restrict;
>>>>>>> 73d9f76... Update to 1.6.1, less MD5/Versioning.
        }
    }

    /**
     * Returns the current flying speed of the player.
     * Default seems to be <tt>0.05</tt>.
     * @return The current flying speed
     */
    public float getFlyingSpeed() {
        OEntityPlayer oep = getEntity();
<<<<<<< HEAD
        return oep == null ? null : oep.ce.a();
=======
        return oep == null ? null : oep.bG.a();
>>>>>>> 73d9f76... Update to 1.6.1, less MD5/Versioning.
    }

    /**
     * Returns the current walking speed of the player.
     * Default seems to be <tt>0.1</tt>.
     * @return The current walking speed
     */
    public float getWalkingSpeed() {
        OEntityPlayer oep = getEntity();
<<<<<<< HEAD
        return oep == null ? null : oep.ce.b();
=======
        return oep == null ? null : oep.bG.b();
>>>>>>> 73d9f76... Update to 1.6.1, less MD5/Versioning.
    }

    /**
     * Updates the human's capabilities to the client.
     * The client won't be affected unless you call this.
     */
    public void updateCapabilities() {
        if (getEntity() != null) {
            getEntity().n();
        }
    }

    /**
     * Whether or not the player is in a bed.
     * @return Sleeping or not.
     */
    public boolean isSleeping() {
        OEntityPlayer oep = getEntity();
<<<<<<< HEAD
        return oep == null ? null : oep.ca;
=======
        return oep == null ? null : oep.bC;
>>>>>>> 73d9f76... Update to 1.6.1, less MD5/Versioning.
    }

    /**
     * Add experience points to total for this Player.
     * The amount will be capped at 2<sup>31</sup> - 1.
     *
     * @param amount the amount of experience points to add.
     */
    public void addXP(int amount) {
<<<<<<< HEAD
        this.getEntity().w(amount);
=======
        this.getEntity().s(amount);
>>>>>>> 73d9f76... Update to 1.6.1, less MD5/Versioning.
        this.updateXP();
    }

    /**
     * Drop the player inventory
     */
    public void dropInventory() {
        Item[] items = inventory.getContents();
        for (Item i : items) {
            if (i == null) {
                continue;
            }
            getWorld().dropItem(getLocation(), i);
        }
        inventory.clearContents();
    }

    /**
     * Drop the player inventory at the specified Location
     * @param location the location to drop the inventory at
     */
    public void dropInventory(Location location) {
        Item[] items = inventory.getContents();
        for (Item i : items) {
            if (i == null) {
                continue;
            }
            getWorld().dropItem(location, i);
        }
        inventory.clearContents();
    }

    /**
     * Drop the player inventory at the specified coordinate
     * @param x
     * @param y
     * @param z
     */
    public void dropInventory(double x, double y, double z) {
        Item[] items = inventory.getContents();
        for (Item i : items) {
            if (i == null) {
                continue;
            }
            getWorld().dropItem(x, y, z, i);
        }
        inventory.clearContents();
    }

    /**
     * Drop item form specified slot
     * @param slot
     */
    public void dropItemFromSlot(int slot) {
        Item i = inventory.getItemFromSlot(slot);
        if (i != null) {
            getWorld().dropItem(getLocation(), i);
            inventory.removeItem(slot);
        }
    }

    /**
     * Drop item form specified slot at the specified Location
     * @param slot
     * @param location
     */
    public void dropItemFromSlot(int slot, Location location) {
        Item i = inventory.getItemFromSlot(slot);
        if (i != null) {
            getWorld().dropItem(location, i);
            inventory.removeItem(slot);
        }
    }

    /**
     * Drop item form specified slot at the specified coordinate
     * @param slot
     * @param x
     * @param y
     * @param z
     */
    public void dropItemFromSlot(int slot, double x, double y, double z) {
        Item i = inventory.getItemFromSlot(slot);
        if (i != null) {
            getWorld().dropItem(x, y, z, i);
            inventory.removeItem(slot);
        }
    }

    /**
     * Get Players food ExhaustionLevel
     * @return
     */
    public float getFoodExhaustionLevel() {
<<<<<<< HEAD
        return getEntity().bN.c;
=======
        return getEntity().bq.c;
>>>>>>> 73d9f76... Update to 1.6.1, less MD5/Versioning.
    }

    /**
     * Get player Food Level
     *
     * @return player food level
     */
    public int getFoodLevel() {
<<<<<<< HEAD
        return getEntity().bN.a;
=======
        return getEntity().bq.a;
>>>>>>> 73d9f76... Update to 1.6.1, less MD5/Versioning.
    }

    /**
     * Get Players food saturationLevel
     * @return
     */
    public float getFoodSaturationLevel() {
<<<<<<< HEAD
        return getEntity().bN.b;
=======
        return getEntity().bq.b;
>>>>>>> 73d9f76... Update to 1.6.1, less MD5/Versioning.
    }

    /**
     * Returns this player's inventory.
     *
     * @return inventory
     */
    public Inventory getInventory() {
        return inventory;
    }

    /**
     * Gets the item the cursor currently has.
     * @return The {@link Item} the cursor currently has.
     */
    public Item getInventoryCursorItem() {
        return inventory.getCursorItem();
    }

    /**
     * Returns item id in player's hand
     *
     * @return item id
     */
    public int getItemInHand() {
<<<<<<< HEAD
        if (this.getEntity().bK.h() != null) {
            return this.getEntity().bK.h().c;
=======
        if (this.getEntity().bn.h() != null) {
            return this.getEntity().bn.h().c;
>>>>>>> 73d9f76... Update to 1.6.1, less MD5/Versioning.
        }
        return -1;
    }

    /**
     * Returns the item stack in the player's hand.
     *
     * @return Item
     */
<<<<<<< HEAD
    @Override
    public Item getItemStackInHand() {
        OItemStack result = getEntity().bK.h();
        if (result != null) {
            return new Item(result, getEntity().bK.c);
=======
    public Item getItemStackInHand() {
        OItemStack result = getEntity().bn.h();
        if (result != null) {
            return new Item(result, getEntity().bn.c);
>>>>>>> 73d9f76... Update to 1.6.1, less MD5/Versioning.
        }
        return null;
    }

    /**
     * Get experience level for this Player.
     *
     * @return
     */
    public int getLevel() {
<<<<<<< HEAD
        return getEntity().cf;
=======
        return getEntity().bH;
>>>>>>> 73d9f76... Update to 1.6.1, less MD5/Versioning.
    }

    /**
     * Returns a player's respawn location
     *
     * @return spawn location
     */
    public Location getRespawnLocation() {
        Location spawn = etc.getServer().getDefaultWorld().getSpawnLocation();
<<<<<<< HEAD
        OChunkCoordinates loc = getEntity().ck();
=======
        OChunkCoordinates loc = getEntity().bA();
>>>>>>> 73d9f76... Update to 1.6.1, less MD5/Versioning.
        if (loc != null) {
            spawn = new Location(etc.getServer().getDefaultWorld(), loc.a, loc.b, loc.c);
        }
        return spawn;
    }

    /**
     * Returns the score for this Player.
     * @return the score for this Player.
     */
    public int getScore() {
<<<<<<< HEAD
        return getEntity().cb();
=======
        return getEntity().br();
>>>>>>> 73d9f76... Update to 1.6.1, less MD5/Versioning.
    }

    /**
     * Get total experience amount for this Player.
     *
     * @return
     */
    public int getXP() {
<<<<<<< HEAD
        return getEntity().cg;
=======
        return getEntity().bI;
>>>>>>> 73d9f76... Update to 1.6.1, less MD5/Versioning.
    }

    /**
     * Gives an item to the player.
     *
     * @param item
     * @see PlayerInventory#insertItem(Item)
     */
    public void giveItem(Item item) {
        inventory.insertItem(item);
        inventory.update();
    }

    /**
     * Gives an item to the player
     *
     * @param itemId
     * @param amount
     */
    public void giveItem(int itemId, int amount) {
        inventory.giveItem(itemId, amount);
        inventory.update();
    }

    /**
     * Gives the player this item by dropping it in front of them<br>
     * NOTE:  using this method calls Hook PluginLoader.Hook.ITEM_DROP
     *
     * @param item
     * @see PluginLoader.Hook.ITEM_DROP
     * @see PluginListener.onItemDrop(Player player, ItemEntity item)
     */
    public void giveItemDrop(Item item) {
        giveItemDrop(item.getItemId(), item.getAmount(), item.getDamage());
    }

    /**
     * Gives the player this item by dropping it in front of them<br>
     * NOTE:  using this method calls Hook PluginLoader.Hook.ITEM_DROP
     *
     * @param itemId
     * @param amount
     * @see PluginLoader.Hook.ITEM_DROP
     * @see PluginListener.onItemDrop(Player player, ItemEntity item)
     */
    public void giveItemDrop(int itemId, int amount) {
        giveItemDrop(itemId, amount, 0);
    }

    /**
     * Gives the player this item by dropping it in front of them<br>
     * NOTE:  using this method calls Hook PluginLoader.Hook.ITEM_DROP
     *
     * @param itemId
     * @param amount
     * @param damage
     * @see PluginLoader.Hook.ITEM_DROP
     * @see PluginListener.onItemDrop(Player player, ItemEntity item)
     */
    public void giveItemDrop(int itemId, int amount, int damage) {
        OEntityPlayer player = getEntity();
        if (amount == -1) {
            player.c(new OItemStack(itemId, 255, damage));
        } else {
            int temp = amount;
            do {
                if (temp - 64 >= 64) {
                    player.c(new OItemStack(itemId, 64, damage));
                } else {
                    player.c(new OItemStack(itemId, temp, damage));
                }
                temp -= 64;
            } while (temp > 0);
        }
    }

    public boolean hasItem(Item item) {
        Item i = inventory.getItemFromId(item.getItemId());
        if (i == null) {
            return false;
        }
        return true;
    }

    /**
     * Remove experience points from total for this Player.
     * The amount will be capped at 0.
     *
     * @param amount the amount of experience points to remove.
     */
    public void removeXP(int amount) {
        this.getEntity().removeXP(amount);
        this.updateXP();
    }

    /**
     * Set player food exhaustion level
     *
     * @param foodExhaustionLevel
     */
    public void setFoodExhaustionLevel(float foodExhaustionLevel) {
<<<<<<< HEAD
        getEntity().bN.c = Math.min(foodExhaustionLevel, 40.0F);
=======
        getEntity().bq.c = Math.min(foodExhaustionLevel, 40.0F);
>>>>>>> 73d9f76... Update to 1.6.1, less MD5/Versioning.
        updateLevels();
    }

    /**
     * Set Player food level
     *
     * @param foodLevel
     *         new food level, between 1 and 20
     */
    public void setFoodLevel(int foodLevel) {
<<<<<<< HEAD
        getEntity().bN.a = Math.min(foodLevel, 20);
=======
        getEntity().bq.a = Math.min(foodLevel, 20);
>>>>>>> 73d9f76... Update to 1.6.1, less MD5/Versioning.
        updateLevels();
    }

    /**
     * Set player food saturation level
     *
     * @param foodSaturationLevel
     */
    public void setFoodSaturationLevel(float foodSaturationLevel) {
<<<<<<< HEAD
        getEntity().bN.b = Math.min(foodSaturationLevel, getFoodLevel());
=======
        getEntity().bq.b = Math.min(foodSaturationLevel, getFoodLevel());
>>>>>>> 73d9f76... Update to 1.6.1, less MD5/Versioning.
        updateLevels();
    }

    /**
     * Sets the item the cursor should have.
     */
    public void setInventoryCursorItem(Item item) {
        inventory.setCursorItem(item);
    }

    /**
     * Sets a player's respawn location
     *
     * @param location
     */
    public void setRespawnLocation(Location location) {
        OChunkCoordinates loc = new OChunkCoordinates((int) Math.floor(location.x), (int) Math.floor(location.y), (int) Math.floor(location.z));
        getEntity().a(loc, true);
    }

    /**
     * Sets a player's respawn location
     *
     * @param x
     * @param y
     * @param z
     */
    public void setRespawnLocation(int x, int y, int z) {
        OChunkCoordinates loc = new OChunkCoordinates(x, y, z);
        getEntity().a(loc, true);
    }

    /**
     * Set total experience points for this Player.
     * Calls {@link #removeXP(int)} or {@link #addXP(int)} based on
     * <tt>amount</tt> and the current XP.
     *
     * @param amount the new amount of experience points.
     */
    public void setXP(int amount) {
        if (amount < this.getXP()) {
            this.removeXP(this.getXP() - amount);
        } else {
            this.addXP(amount - this.getXP());
        }
    }

    /**
     * Updates the inventory on the client.
     */
    public void updateInventory() {
        // Do nothing by default
    }

    /**
     * Send update food and health to client.
     */
    public void updateLevels() {
        // Do nothing by default
    }

    /**
     * Send player the updated experience packet.
     *
     */
    public void updateXP() {
        // Do nothing by default.
    }

    public void setInventory() {
        if (inventory == null) {
            inventory = new PlayerInventory(this);
        }
    }

    /**
     * Returns this player's {@link EnderChestInventory} for modification.
     *
     * @return this player's {@link EnderChestInventory} for modification
     */
    public EnderChestInventory getEnderChest() {
<<<<<<< HEAD
        return new EnderChestInventory(getEntity().cp(), this);
=======
        return new EnderChestInventory(getEntity().bF(), this);
>>>>>>> 73d9f76... Update to 1.6.1, less MD5/Versioning.
    }
}
