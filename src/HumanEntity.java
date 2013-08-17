public class HumanEntity extends LivingEntityBase {
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
        // SRG return oep == null ? null : oep.field_71092_bJ;
        return oep == null ? null : oep.bu;
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
        // SRG return oep == null ? null : oep.field_71075_bZ.field_75102_a;
        return oep == null ? null : oep.bG.a;
    }

    /**
     * Sets whether this player can receive damage.
     * @param disabled the new value.
     * @see #updateCapabilities()
     */
    public void setDamageDisabled(boolean disabled) {
        if (getEntity() != null) {
            // SRG getEntity().field_71075_bZ.field_75102_a = disabled;
            getEntity().bG.a = disabled;
        }
    }

    /**
     * Returns whether the player is flying.
     * @return the flying state
     */
    public boolean isFlying() {
        OEntityPlayer oep = getEntity();
        // SRG return oep == null ? null : oep.field_71075_bZ.field_75100_b;
        return oep == null ? null : oep.bG.b;
    }

    /**
     * Sets whether the player is flying.
     * @param flying the flying state.
     * @see #updateCapabilities()
     */
    public void setFlying(boolean flying) {
        if (getEntity() != null) {
            // SRG getEntity().field_71075_bZ.field_75100_b = flying;
            getEntity().bG.b = flying;
        }
    }

    /**
     * Returns whether this player is allowed to fly.
     * @return the canFly state
     */
    public boolean canFly() {
        OEntityPlayer oep = getEntity();
        // SRG return oep == null ? null : oep.field_71075_bZ.field_75101_c;
        return oep == null ? null : oep.bG.c;
    }

    /**
     * Sets whether this player is allowed to fly.
     * @param allow <tt>true</tt> to allow flying, <tt>false</tt> to deny it.
     * @see #updateCapabilities()
     */
    public void setCanFly(boolean allow) {
        if (getEntity() != null) {
            // SRG getEntity().field_71075_bZ.field_75101_c = allow;
            getEntity().bG.c = allow;
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
        // SRG return oep == null ? null : oep.field_71075_bZ.field_75098_d;
        return oep == null ? null : oep.bG.d;
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
            // SRG getEntity().field_71075_bZ.field_75098_d = creativePerks;
            getEntity().bG.d = creativePerks;
        }
    }

    /**
     * Returns whether the player has build restrictions like in Adventure.
     * @return whether the player has build restrictions.
     */
    public boolean hasAdventureRestrictions() {
        OEntityPlayer oep = getEntity();
        // SRG return oep == null ? null : !oep.field_71075_bZ.field_75099_e;
        return oep == null ? null : !oep.bG.e;
    }

    /**
     * Sets whether the player has build restrictions like in Adventure.
     * @param restrict The new value for the flag.
     * @see #updateCapabilities()
     */
    public void setAdventureRestrictions(boolean restrict) {
        if (getEntity() != null) {
            // SRG getEntity().field_71075_bZ.field_75099_e = !restrict;
            getEntity().bG.e = !restrict;
        }
    }

    /**
     * Returns the current flying speed of the player.
     * Default seems to be <tt>0.05</tt>.
     * @return The current flying speed
     */
    public float getFlyingSpeed() {
        OEntityPlayer oep = getEntity();
        // SRG return oep == null ? null : oep.field_71075_bZ.func_75093_a();
        return oep == null ? null : oep.bG.a();
    }

    /**
     * Returns the current walking speed of the player.
     * Default seems to be <tt>0.1</tt>.
     * @return The current walking speed
     */
    public float getWalkingSpeed() {
        OEntityPlayer oep = getEntity();
        // SRG return oep == null ? null : oep.field_71075_bZ.func_75094_b();
        return oep == null ? null : oep.bG.b();
    }

    /**
     * Updates the human's capabilities to the client.
     * The client won't be affected unless you call this.
     */
    public void updateCapabilities() {
        if (getEntity() != null) {
            // SRG getEntity().func_71016_p();
            getEntity().o();
        }
    }

    /**
     * Whether or not the player is in a bed.
     * @return Sleeping or not.
     */
    public boolean isSleeping() {
        OEntityPlayer oep = getEntity();
        // SRG return oep == null ? null : oep.field_71083_bS;
        return oep == null ? null : oep.bC;
    }

    /**
     * Add experience points to total for this Player.
     * The amount will be capped at 2<sup>31</sup> - 1.
     *
     * @param amount the amount of experience points to add.
     */
    public void addXP(int amount) {
        // SRG this.getEntity().func_71023_q(amount);
        this.getEntity().s(amount);
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
        // SRG return getEntity().field_71100_bB.field_75126_c;
        return getEntity().bq.c;
    }

    /**
     * Get player Food Level
     *
     * @return player food level
     */
    public int getFoodLevel() {
        // SRG return getEntity().field_71100_bB.field_75127_a;
        return getEntity().bq.a;
    }

    /**
     * Get Players food saturationLevel
     * @return
     */
    public float getFoodSaturationLevel() {
        // SRG return getEntity().field_71100_bB.field_75125_b;
        return getEntity().bq.b;
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
        Item inHand = this.getItemStackInHand();
        return inHand == null ? 0 : inHand.getItemId();
    }

    /**
     * Returns the item stack in the player's hand.
     *
     * @return Item
     */
    public Item getItemStackInHand() {
        int slot = inventory.getSelectedSlot();
        return slot >= 0 && slot < 9 ? inventory.getItemFromSlot(slot) : null;
    }

    /**
     * Returns a player's respawn location
     *
     * @return spawn location
     */
    public Location getRespawnLocation() {
        Location spawn = etc.getServer().getDefaultWorld().getSpawnLocation();
        // SRG OChunkCoordinates loc = getEntity().func_70997_bJ();
        OChunkCoordinates loc = getEntity().bE();
        if (loc != null) {
            // SRG spawn = new Location(etc.getServer().getDefaultWorld(), loc.field_71574_a, loc.field_71572_b, loc.field_71573_c);
            spawn = new Location(etc.getServer().getDefaultWorld(), loc.a, loc.b, loc.c);
        }
        return spawn;
    }

    /**
     * Returns the score for this Player.
     * @return the score for this Player.
     */
    public int getScore() {
        // SRG return getEntity().func_71037_bA();
        return getEntity().bv();
    }

    /**
     * Get experience level for this Player.
     *
     * @return
     */
    public int getLevel() {
        // SRG return getEntity().field_71068_ca;
        return getEntity().bH;
    }

    /**
     * Get total experience amount for this Player.
     *
     * @return
     */
    public int getXP() {
        // SRG return getEntity().field_71067_cb;
        return getEntity().bI;
    }

    /**
     * Returns the "progress" of the XP bar, in percents.
     * @return the "progress" of the XP bar, in percents.s
     */
    public float getXPBarProgress() {
        // SRG return getEntity().field_71106_cc;
        return getEntity().bJ;
    }

    /**
     * Gets the amount of XP needed to fill the bar.
     * @return the amount of XP needed to fill the bar
     */
    public int getXPBarMax() {
        // SRG return getEntity().func_71050_bK();
        return getEntity().bG();
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
     * Gives the player this item by dropping it in front of them.<br>
     * <strong>NOTE:</strong> using this method calls the
     * PluginLoader.Hook.ITEM_DROP hook.
     *
     * @param toDrop
     * @see PluginLoader.Hook.ITEM_DROP
     * @see PluginListener.onItemDrop(Player player, ItemEntity item)
     */
    public void giveItemDrop(Item toDrop) {
        Item item = toDrop.clone();
        OEntityPlayer player = getEntity();
        if (item.getAmount() == -1) {
            item.setAmount(255);
            // SRG player.func_71021_b(item.getBaseItem());
            player.b(item.getBaseItem());
        } else {
            while (item.getAmount() > 0) {
                if (item.getAmount() - item.getMaxAmount() >= item.getMaxAmount()) {
                    // SRG player.func_71021_b(item.split(item.getMaxAmount()).getBaseItem());
                    player.b(item.split(item.getMaxAmount()).getBaseItem());
                } else {
                    // SRG player.func_71021_b(item.getBaseItem());
                    player.b(item.getBaseItem());
                    break;
                }
            }
        }
    }

    /**
     * Gives the player this item by dropping it in front of them.<br>
     * <strong>NOTE:</strong> using this method calls the
     * PluginLoader.Hook.ITEM_DROP hook.
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
     * Gives the player this item by dropping it in front of them.<br>
     * <strong>NOTE:</strong> using this method calls the
     * PluginLoader.Hook.ITEM_DROP hook.
     *
     * @param itemId
     * @param amount
     * @param damage
     * @see PluginLoader.Hook.ITEM_DROP
     * @see PluginListener.onItemDrop(Player player, ItemEntity item)
     */
    public void giveItemDrop(int itemId, int amount, int damage) {
        this.giveItemDrop(new Item(itemId, amount, -1, damage));
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
        // SRG getEntity().field_71100_bB.field_75126_c = Math.min(foodExhaustionLevel, 40.0F);
        getEntity().bq.c = Math.min(foodExhaustionLevel, 40.0F);
        updateLevels();
    }

    /**
     * Set Player food level
     *
     * @param foodLevel
     *         new food level, between 1 and 20
     */
    public void setFoodLevel(int foodLevel) {
        // SRG getEntity().field_71100_bB.field_75127_a = Math.min(foodLevel, 20);
        getEntity().bq.a = Math.min(foodLevel, 20);
        updateLevels();
    }

    /**
     * Set player food saturation level
     *
     * @param foodSaturationLevel
     */
    public void setFoodSaturationLevel(float foodSaturationLevel) {
        // SRG getEntity().field_71100_bB.field_75125_b = Math.min(foodSaturationLevel, getFoodLevel());
        getEntity().bq.b = Math.min(foodSaturationLevel, getFoodLevel());
        updateLevels();
    }

    /**
     * Sets the item the cursor should have.
     */
    public void setInventoryCursorItem(Item item) {
        inventory.setCursorItem(item);
    }

    /**
     * Sets a player's respawn location. Only x, y and z apply, world will be
     * the default world.
     *
     * @param location The {@link Location} containing the x, y and z
     * coordinates for the respawn location
     */
    public void setRespawnLocation(Location location) {
        this.setRespawnLocation(etc.floor(location.x), etc.floor(location.y), etc.floor(location.z));
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
        // SRG getEntity().func_71063_a(loc, true);
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
        // SRG return new EnderChestInventory(getEntity().func_71005_bN(), this);
        return new EnderChestInventory(getEntity().bJ(), this);
    }
}
