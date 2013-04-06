import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


/**
 * Item.java - Item stuff.
 *
 * @author James
 */
public class Item implements Cloneable, Metadatable {

    /**
     * Type - Used to identify items
     */
    public enum Type {
        Air(0), //
        Stone(1), //
        Grass(2), //
        Dirt(3), //
        Cobblestone(4), //
        Wood(5), //
        Sapling(6), //
        Bedrock(7), //
        Water(8), //
        StationaryWater(9), //
        Lava(10), //
        StationaryLava(11), //
        Sand(12), //
        Gravel(13), //
        GoldOre(14), //
        IronOre(15), //
        CoalOre(16), //
        Log(17), //
        Leaves(18), //
        Sponge(19), //
        Glass(20), //
        LapisLazuliOre(21), //
        LapisLazuliBlock(22), //
        Dispenser(23), //
        SandStone(24), //
        NoteBlock(25), //
        BedBlock(26), //
        PoweredRails(27), //
        DetectorRails(28), //
        StickyPiston(29), //
        Web(30), //
        TallGrass(31), //
        DeadShrub(32), //
        Piston(33), //
        PistonExtended(34), //
        Cloth(35), //
        PistonBlockFiller(36), //
        YellowFlower(37), //
        RedRose(38), //
        BrownMushroom(39), //
        RedMushroom(40), //
        GoldBlock(41), //
        IronBlock(42), //
        DoubleStep(43), //
        Step(44), //
        Brick(45), //
        TNT(46), //
        BookShelf(47), //
        MossyCobblestone(48), //
        Obsidian(49), //
        Torch(50), //
        Fire(51), //
        MobSpawner(52), //
        WoodStairs(53), //
        Chest(54), //
        RedstoneWire(55), //
        DiamondOre(56), //
        DiamondBlock(57), //
        Workbench(58), //
        Crops(59), //
        Soil(60), //
        Furnace(61), //
        BurningFurnace(62), //
        SignPost(63), //
        WoodenDoor(64), //
        Ladder(65), //
        Rails(66), //
        CobblestoneStairs(67), //
        WallSign(68), //
        Lever(69), //
        StonePlate(70), //
        IronDoorBlock(71), //
        WoodPlate(72), //
        RedstoneOre(73), //
        GlowingRedstoneOre(74), //
        RedstoneTorchOff(75), //
        RedstoneTorchOn(76), //
        StoneButton(77), //
        Snow(78), //
        Ice(79), //
        SnowBlock(80), //
        Cactus(81), //
        Clay(82), //
        ReedBlock(83), //
        Jukebox(84), //
        Fence(85), //
        Pumpkin(86), //
        Netherstone(87), //
        SlowSand(88), //
        LightStone(89), //
        Portal(90), //
        JackOLantern(91), //
        CakeBlock(92), //
        RedstoneRepeaterOff(93), //
        RedstoneRepeaterOn(94), //
        LockedChest(95), //
        Trapdoor(96), //
        SilverBlock(97), //
        StoneBrick(98), //
        HugeBrownMushroom(99), //
        HugeRedMushroom(100), //
        IronBars(101), //
        GlassPane(102), //
        MelonBlock(103), //
        PumpkinStem(104), //
        MelonStem(105), //
        Vine(106), //
        FenceGate(107), //
        BrickStair(108), //
        StonebrickStair(109), //
        Mycelium(110), //
        LilyPad(111), //
        NetherBrick(112), //
        NetherBrickFence(113), //
        NetherBrickStair(114), //
        NetherWartBlock(115), //
        EnchantmentTable(116), //
        BrewingStandBlock(117), //
        CauldronBlock(118), //
        EndPortal(119), //
        EndPortalFrame(120), //
        EndStone(121), //
        EnderDragonEgg(122), //
        RedstoneLampOff(123), //
        RedstoneLampOn(124), //
        WoodDoubleStep(125), //
        WoodStep(126), //
        CocoaPlant(127), //
        SandstoneStairs(128), //
        EmeraldOre(129), //
        EnderChest(130), //
        TripwireHook(131), //
        Tripwire(132), //
        EmeraldBlock(133), //
        SpruceWoodStairs(134), //
        BirchWoodStairs(135), //
        JungleWoodStairs(136), //
        CommandBlock(137), //
        Beacon(138), //
        CobblestoneWall(139), //
        FlowerPotBlock(140), //
        CarrotBlock(141), //
        PotatoBlock(142), //
        WoodenButton(143), //
        SkullBlock(144), //
        Anvil(145), //
        TrappedChest(146),//
        WeightedPressurePlateLight(147),//
        WeightedPressurePlateHeavy(148),//
        DaylightSensor(151),//
        RedstoneBlock(152),//
        NetherQuartzOre(153),//
        Hopper(154),//
        QuartzBlock(155),//
        QuartzStairs(156),//
        ActivatorRail(157),//
        Dropper(158),//
        IronSpade(256), //
        IronPickaxe(257), //
        IronAxe(258), //
        FlintAndSteel(259), //
        Apple(260), //
        Bow(261), //
        Arrow(262), //
        Coal(263), //
        Diamond(264), //
        IronIngot(265), //
        GoldIngot(266), //
        IronSword(267), //
        WoodSword(268), //
        WoodSpade(269), //
        WoodPickaxe(270), //
        WoodAxe(271), //
        StoneSword(272), //
        StoneSpade(273), //
        StonePickaxe(274), //
        StoneAxe(275), //
        DiamondSword(276), //
        DiamondSpade(277), //
        DiamondPickaxe(278), //
        DiamondAxe(279), //
        Stick(280), //
        Bowl(281), //
        MushroomSoup(282), //
        GoldSword(283), //
        GoldSpade(284), //
        GoldPickaxe(285), //
        GoldAxe(286), //
        String(287), //
        Feather(288), //
        Gunpowder(289), //
        WoodHoe(290), //
        StoneHoe(291), //
        IronHoe(292), //
        DiamondHoe(293), //
        GoldHoe(294), //
        Seeds(295), //
        Wheat(296), //
        Bread(297), //
        LeatherHelmet(298), //
        LeatherChestplate(299), //
        LeatherLeggings(300), //
        LeatherBoots(301), //
        ChainmailHelmet(302), //
        ChainmailChestplate(303), //
        ChainmailLeggings(304), //
        ChainmailBoots(305), //
        IronHelmet(306), //
        IronChestplate(307), //
        IronLeggings(308), //
        IronBoots(309), //
        DiamondHelmet(310), //
        DiamondChestplate(311), //
        DiamondLeggings(312), //
        DiamondBoots(313), //
        GoldHelmet(314), //
        GoldChestplate(315), //
        GoldLeggings(316), //
        GoldBoots(317), //
        Flint(318), //
        Pork(319), //
        GrilledPork(320), //
        Painting(321), //
        GoldenApple(322), //
        Sign(323), //
        WoodDoor(324), //
        Bucket(325), //
        WaterBucket(326), //
        LavaBucket(327), //
        Minecart(328), //
        Saddle(329), //
        IronDoor(330), //
        RedStone(331), //
        SnowBall(332), //
        Boat(333), //
        Leather(334), //
        MilkBucket(335), //
        ClayBrick(336), //
        ClayBall(337), //
        Reed(338), //
        Paper(339), //
        Book(340), //
        SlimeBall(341), //
        StorageMinecart(342), //
        PoweredMinecart(343), //
        Egg(344), //
        Compass(345), //
        FishingRod(346), //
        Watch(347), //
        LightstoneDust(348), //
        RawFish(349), //
        CookedFish(350), //
        InkSack(351), //
        Bone(352), //
        Sugar(353), //
        Cake(354), //
        Bed(355), //
        RedstoneRepeater(356), //
        Cookie(357), //
        Map(358), //
        Shears(359), //
        MelonSlice(360), //
        PumpkinSeeds(361), //
        MelonSeeds(362), //
        RawBeef(363), //
        Steak(364), //
        RawChicken(365), //
        CookedChicken(366), //
        RottenFlesh(367), //
        EnderPearl(368), //
        BlazeRod(369), //
        GhastTear(370), //
        GoldNugget(371), //
        NetherWart(372), //
        Potion(373), //
        GlassBottle(374),//
        SpiderEye(375), //
        FermentedSpiderEye(376), //
        BlazePowder(377), //
        MagmaCream(378), //
        BrewingStand(379), //
        Cauldron(380), //
        EyeofEnder(381), //
        GlisteringMelon(382), //
        SpawnEgg(383), //
        BottleOEnchanting(384), //
        FireCharge(385), //
        BookAndQuill(386), //
        WrittenBook(387), //
        Emerald(388), //
        ItemFrame(389), //
        FlowerPot(390), //
        Carrot(391), //
        Potato(392), //
        BakedPotato(393), //
        PoisonousPotato(394), //
        EmptyMap(395), //
        GoldenCarrot(396), //
        Skull(397), //
        CarrotOnAStick(398), //
        NetherStar(399), //
        PumpkinPie(400), //
        FireworkRocket(401), //
        FireworkStar(402), //
        EnchantedBook(403), //
        RedstoneComparator(404),//
        NetherBricks(405),//
        NetherQuartz(406),//
        MinecartTNT(407),//
        MinecartHopper(408),//
        GoldRecord(2256), //
        GreenRecord(2257), //
        BlocksRecord(2258), //
        ChirpRecord(2259), //
        FarRecord(2260), //
        MallRecord(2261), //
        MellohiRecord(2262), //
        StalRecord(2263), //
        StradRecord(2264), //
        WardRecord(2265), //
        ElevenRecord(2266), //
        WaitRecord(2267);

        private int id;
        private static Map<Integer, Type> map;

        private Type(int id) {
            this.id = id;
            add(id, this);
        }

        private static void add(int type, Type name) {
            if (map == null) {
                map = new HashMap<Integer, Type>();
            }

            map.put(type, name);
        }

        public int getId() {
            return id;
        }

        public static Type fromId(final int id) {
            return map.get(id);
        }

    }

    private int itemId = 1, amount = 1, slot = -1, damage = 0;
    private OItemStack itemStack = null;
    public Type itemType = Type.fromId(itemId);

    /**
     * Create an item with an id of 1 and amount of 1.
     */
    public Item() {}

    /**
     * Clone an existing <tt>Item</tt>
     * @param toClone the <tt>Item</tt> to clone
     */
    public Item(Item toClone) {
        this(toClone.itemStack.m());
    }

    /**
     * Create a new item.
     *
     * @param itemType
     *            type of item.
     */
    public Item(Type itemType) {
        this(itemType, 1);
    }

    public Item(Type itemType, int amount) {
        this(itemType.getId(), amount);
    }

    public Item(Type itemType, int amount, int slot) {
        this(itemType.getId(), amount, slot);
    }

    public Item(Type itemType, int amount, int slot, int damage) {
        this(itemType.getId(), amount, slot, damage);
    }

    /**
     * Creates an item with specified id and amount
     *
     * @param itemId
     * @param amount
     */
    public Item(int itemId, int amount) {
        this.itemId = itemId;
        this.amount = amount;
        damage = 0;
        itemType = Type.fromId(itemId);
        itemStack = new OItemStack(itemId, amount, damage);
    }

    /**
     * Creates an item with specified id, amount and slot
     *
     * @param itemId
     * @param amount
     * @param slot
     */
    public Item(int itemId, int amount, int slot) {
        this.itemId = itemId;
        this.amount = amount;
        this.slot = slot;
        itemType = Type.fromId(itemId);
        itemStack = new OItemStack(itemId, amount, damage);
    }

    /**
     * Creates an item with specified id, amount and slot
     *
     * @param itemId
     * @param amount
     * @param slot
     * @param damage
     */
    public Item(int itemId, int amount, int slot, int damage) {
        this.itemId = itemId;
        this.amount = amount;
        this.slot = slot;
        this.damage = damage;
        itemType = Type.fromId(itemId);
        itemStack = new OItemStack(itemId, amount, damage);
    }

    /**
     * Creates an item from the actual item class
     *
     * @param itemStack
     */
    public Item(OItemStack itemStack) {
        itemId = itemStack.c;
        amount = itemStack.a;
        damage = itemStack.j();
        itemType = Type.fromId(itemId);
        this.itemStack = itemStack;
    }

    /**
     * Creates an item from the actual item class at the given slot
     *
     * @param itemStack
     * @param slot
     */
    public Item(OItemStack itemStack, int slot) {
        this(itemStack);
        this.slot = slot;
    }

    /**
     * Updates the native item stack with this wrapper's current values.
     */
    public void update() {
        if (this.itemStack != null) {
            this.itemStack.c = itemId;
            this.itemStack.a = amount;
            this.itemStack.b(damage);
        }
    }

    /**
     * Returns the item id
     *
     * @return item id
     */
    public int getItemId() {
        return itemId;
    }

    /**
     * Sets item id to specified id
     *
     * @param itemId
     */
    public void setItemId(int itemId) {
        this.itemId = itemId;
        itemType = Type.fromId(itemId);
        update();
    }

    /**
     * Returns the amount
     *
     * @return amount
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Sets the amount
     *
     * @param amount
     */
    public void setAmount(int amount) {
        this.amount = amount;
        update();
    }

    /**
     * Returns the max amount (stack size)
     *
     * @return amount
     */
    public int getMaxAmount() {
        return this.itemStack.e();
    }

    /**
     * Sets the max amount (stack size)
     *
     * @param amount
     */
    public void setMaxAmount(int amount) {
        this.itemStack.b().e(amount);
    }

    /**
     * Returns true if specified item id is a valid item id.
     *
     * @param itemId
     * @return
     */
    public static boolean isValidItem(int itemId) {
        if (itemId < OItem.f.length) {
            return OItem.f[itemId] != null;
        }
        return false;
    }

    /**
     * Returns this item's current slot. -1 if no slot is specified
     *
     * @return slot
     */
    public int getSlot() {
        return slot;
    }

    /**
     * Sets this item's slot
     *
     * @param slot
     */
    public void setSlot(int slot) {
        this.slot = slot;
    }

    /**
     * Returns this item's current damage. 0 if no damage is specified
     *
     * @return damage
     */
    public int getDamage() {
        return damage;
    }

    /**
     * Sets this item's damage
     *
     * @param damage
     */
    public void setDamage(int damage) {
        this.damage = damage;
        update();
    }

    /**
     * Returns a String value representing this object
     *
     * @return String representation of this object
     */
    @Override
    public String toString() {
        return String.format("Item[id=%d, amount=%d, slot=%d, damage=%d]", itemId, amount, slot, damage);
    }

    /**
     * Tests the given object to see if it equals this object
     *
     * @param obj
     *            the object to test
     * @return true if the two objects match
     */
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof OItemStack) {
            return OItemStack.b(itemStack, (OItemStack) obj);
        } else if(obj instanceof Item) {
            return OItemStack.b(itemStack, ((Item) obj).getBaseItem());
        }
        return false;
    }

    /**
     * Like equals() but doesn't check slot or amount
     *
     * @param item The item to check equality with
     * @return
     */
    public boolean equalsIgnoreSlotAndAmount(Item item) {
        return item != null && item.getItemId() == getItemId() && item.getDamage() == getDamage() && Arrays.equals(item.getEnchantments(), getEnchantments()) && (getDataTag() == null ? item.getDataTag() == null : getDataTag().equals(item.getDataTag()));
    }

    /**
     * Like equals() but doesn't check slot
     *
     * @param item The item to check equality with
     * @return
     */
    public boolean equalsIgnoreSlot(Item item) {
        return equalsIgnoreSlotAndAmount(item) && item.getAmount() == getAmount();
    }

    /**
     * Returns a semi-unique hashcode for this object
     *
     * @return hashcode
     */
    @Override
    public int hashCode() {
        int hash = 7;

        hash = 97 * hash + itemId;
        hash = 97 * hash + amount;
        hash = 97 * hash + slot;
        return hash;
    }

    /**
     * Returns this item type
     *
     * @return the item type
     */
    public Type getType() {
        return itemType;
    }

    /**
     * Set the item type
     *
     * @param itemType
     *            the item type
     */
    public void setType(Type itemType) {
        this.itemType = itemType;
        itemId = itemType.getId();
        update();
    }

    public boolean isCloth() {
        return itemType == Type.Cloth;
    }

    public Cloth.Color getColor() {
        if (!isCloth()) {
            return null;
        } else {
            return Cloth.Color.getColor(damage);
        }
    }

    public OItemStack getBaseItem() {
        if (this.itemStack == null) {
            return new OItemStack(itemId, amount, damage);
        } else {
            return this.itemStack;
        }
    }

    /**
     * Sets an enchantment on the item
     *
     * @param id
     * @param level
     */
    public void addEnchantment(int id, int level) {
        Enchantment enchantment = new Enchantment(Enchantment.Type.fromId(id), level);
        if (enchantment.getEnchantment() != null && itemStack != null) {
            itemStack.a(enchantment.getEnchantment(), enchantment.getLevel());
        }
    }

    /**
     * Sets an enchantment on the item
     *
     * @param type
     *            the enchantment type
     * @param level
     */
    public void addEnchantment(Enchantment.Type type, int level) {
        Enchantment enchantment = new Enchantment(type, level);
        if (enchantment.getEnchantment() != null && itemStack != null) {
            itemStack.a(enchantment.getEnchantment(), enchantment.getLevel());
        }
    }

    /**
     * Sets an enchantment on the item
     *
     * @param enchantment
     */
    public void addEnchantment(Enchantment enchantment) {
        if (enchantment.getEnchantment() != null && itemStack != null) {
            itemStack.a(enchantment.getEnchantment(), enchantment.getLevel());
        }
    }

    /**
     * Removes item enchantments
     */
    public void removeEnchantments() {
        if (itemStack != null) {
            itemStack.d(null);
        }
    }

    /**
     * Gets a list of enchantments
     *
     * @return Enchantment[]
     */
    public Enchantment[] getEnchantments() {
        Enchantment[] enchantments = null;
        if (itemStack != null && itemStack.x()) {
            int size = itemStack.r().c();
            enchantments = new Enchantment[size];
            NBTTagList nbtTagList = new NBTTagList(itemStack.r());
            for (int i = 0; i < size; i++) {
                NBTTagCompound tag = (NBTTagCompound) nbtTagList.get(i);
                enchantments[i] = new Enchantment(Enchantment.Type.fromId(tag.getShort("id")), tag.getShort("lvl"));
            }
        }
        return enchantments;
    }

    /**
     * Gets the items enchantment at specified index
     *
     * @param index
     * @return enchantment
     */
    public Enchantment getEnchantment(int index) {
        if (itemStack != null && itemStack.x()) {
            int size = itemStack.r().c();
            if (index >= size) {
                index = 0;
            }
            NBTTagCompound tag = (NBTTagCompound) new NBTTagList(itemStack.r()).get(index);
            return new Enchantment(Enchantment.Type.fromId(tag.getShort("id")), tag.getShort("lvl"));
        }
        return null;
    }

    /**
     * Gets the items first enchantment
     *
     * @return enchantment
     */
    public Enchantment getEnchantment() {
        return getEnchantment(0);
    }

    /**
     * Gets the visible name of this item.
     * Names can be set using an anvil or {@link #setName(java.lang.String)}.
     * @return The item name
     */
    public String getName() {
        return itemStack.s();
    }

    /**
     * Sets the visible name of this item.
     * Equivalent to renaming this item using an anvil.
     * @param name The item's new name
     */
    public void setName(String name) {
        itemStack.c(name);
    }

    /**
     * Returns the potion effects associated with this item.
     * Returns null if item is not a potion or has no potion effects.
     *
     * @return
     */
    public PotionEffect[] getPotionEffects() {
        if(getType() != Item.Type.Potion) {
            return null;
        }

        OItemStack base = getBaseItem();

        if(!base.p()) {
            return null;
        }

        NBTTagCompound tag = new NBTTagCompound(base.q());
        if(!tag.hasTag("CustomPotionEffects")) {
            return null;
        }

        NBTTagList potionEffects = tag.getNBTTagList("CustomPotionEffects");
        PotionEffect[] rt = new PotionEffect[potionEffects.size()];
        if(rt.length <= 0) {return null;}
        for(int i=0; i<rt.length; i++) {
            rt[i] = new PotionEffect(OPotionEffect.b((ONBTTagCompound) potionEffects.get(i).getBaseTag()));
        }

        return rt;
    }

    /**
     * Adds a potion effect to this item. Only works on potions.
     *
     * @param effect The potion effect to add
     */
    public void addPotionEffect(PotionEffect effect) {
        addPotionEffects(new PotionEffect[] {effect});
    }

    /**
     * Adds an array of potion effects to this item. Only works on potions.
     *
     * @param effects The potion effects to add
     */
    public void addPotionEffects(PotionEffect[] effects) {
        if(getType() != Item.Type.Potion) {
            return;
        }

        OItemStack base = getBaseItem();

        NBTTagList potionEffects = null;
        if(base.p()) {
            NBTTagCompound tag = new NBTTagCompound(base.q());
            if(tag.hasTag("CustomPotionEffects")) {
                potionEffects = tag.getNBTTagList("CustomPotionEffects");
            } else {
                potionEffects = new NBTTagList();
                tag.add("CustomPotionEffects", potionEffects);
            }
        } else {
            potionEffects = new NBTTagList();
            NBTTagCompound tag = new NBTTagCompound();
            tag.add("CustomPotionEffects", potionEffects);
            base.d(tag.getBaseTag());
        }

        NBTTagCompound[] e = new NBTTagCompound[effects.length];
        for(int i = 0; i < e.length; i++) {
            e[i] = new NBTTagCompound();
            effects[i].potionEffect.a(e[i].getBaseTag());
            potionEffects.add(e[i]);
        }
    }

    /**
     * Create a completely independent clone of this <tt>Item</tt>.
     * The <tt>OItemStack</tt> (if it is set) is cloned using its own (native)
     * method.
     *
     * @return a clone of this <tt>Item</tt> instance
     */
    @Override
    public Item clone() {
        try {
            Item clone = (Item) super.clone();
            clone.itemStack = itemStack == null ? null : itemStack.m();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new InternalError(); // We are Cloneable!?
        }
    }

    /**
     * Returns the text that shows up under this item's name in the player's inventory.
     * Returns null if the lore is not set.
     *
     * @return The lore, each string in the array is a new line
     */
    public String[] getLore() { // WWOL: I don't think we need this now with the new NBT API do we?
        NBTTagCompound tag = getDataTag();
        if(tag == null) {return null;}
        if(!tag.hasTag("display")) {return null;}
        NBTTagCompound display = tag.getNBTTagCompound("display");
        if(!display.hasTag("Lore")) {return null;}
        NBTTagList lore = display.getNBTTagList("Lore");
        String[] rt = new String[lore.size()];
        for(int i=0; i<rt.length; i++) {
            rt[i] = lore.get(i).toPlainString();
        }
        return rt;
    }

    /**
     * Sets the text that shows up under the item's name in the player's inventory
     *
     * @param lore The lore to set, each line should be in a separate string in the array
     */
    public void setLore(String... lore) {
        NBTTagCompound tag = getDataTag();
        if(tag == null) {
            tag = new NBTTagCompound("tag");
            setDataTag(tag);
        }
        if(!tag.hasTag("display")) {
            tag.add("display", new NBTTagCompound());
        }
        NBTTagList list = new NBTTagList();
        for(String line : lore) {
            list.add(new NBTTagString("", line));
        }
        tag.getNBTTagCompound("display").add("Lore", list);
    }

    /**
     * Returns the tag containing data for this item.
     * Null if this item has no data tag.
     *
     * @return
     */
    public NBTTagCompound getDataTag() {
        return itemStack.p() ? new NBTTagCompound(itemStack.q()) : null;
    }

    /**
     * Sets the tag containing data for this item.
     * Should be named 'tag'.
     * Setting this to null removes name and lore data.
     *
     * @param tag the data tag
     */
    public void setDataTag(NBTTagCompound tag) {
        itemStack.d = tag == null ? null : tag.getBaseTag();
    }

    @Override
    public NBTTagCompound getMetaTag() {
        NBTTagCompound dataTag = getDataTag();
        if(!dataTag.hasTag("Canary")) {
            dataTag.add("Canary", new NBTTagCompound("Canary"));
        }
        return dataTag.getNBTTagCompound("Canary");
    }

    /**
     * Writes this item's data to an NBTTagCompound.
     *
     * @param tag The tag to write to
     * @return NBTTagCompound
     */
    public NBTTagCompound writeToTag(NBTTagCompound tag) {
        return new NBTTagCompound(itemStack.b(tag.getBaseTag()));
    }

    /**
     * Sets this item's data to that in an NBTTagCompound.
     *
     * @param tag The tag to read from
     */
    public void readFromTag(NBTTagCompound tag) {
        itemStack.c(tag.getBaseTag());
    }
}
