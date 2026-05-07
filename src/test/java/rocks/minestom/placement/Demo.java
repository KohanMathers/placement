import net.kyori.adventure.key.Key;
import net.minestom.server.Auth;
import net.minestom.server.MinecraftServer;
import net.minestom.server.entity.GameMode;
import net.minestom.server.event.player.AsyncPlayerConfigurationEvent;
import net.minestom.server.event.player.PlayerSpawnEvent;
import net.minestom.server.instance.InstanceContainer;
import net.minestom.server.instance.block.Block;
import rocks.minestom.placement.utils.Utility;
import rocks.minestom.placement.*;

private static InstanceContainer createInstance() {
    var instance = MinecraftServer.getInstanceManager().createInstanceContainer();
    instance.setGenerator(unit -> unit.modifier().fillHeight(-64, 0, Block.STONE));
    instance.setTime(6000);
    instance.setTimeRate(0);
    return instance;
}

private static void registerPlacementRules() {
    Utility.registerPlacementRules(
            AxisPlacementRule::new,
            Block.CREAKING_HEART,
            Block.HAY_BLOCK,
            Block.IRON_CHAIN,
            Block.DEEPSLATE,
            Block.INFESTED_DEEPSLATE,
            Block.MUDDY_MANGROVE_ROOTS,
            Block.BAMBOO_BLOCK,
            Block.STRIPPED_BAMBOO_BLOCK,
            Block.BASALT,
            Block.POLISHED_BASALT,
            Block.QUARTZ_PILLAR,
            Block.PURPUR_PILLAR,
            Block.BONE_BLOCK,
            Block.OCHRE_FROGLIGHT,
            Block.VERDANT_FROGLIGHT,
            Block.PEARLESCENT_FROGLIGHT);

    Utility.registerPlacementRules(AxisPlacementRule::new, Key.key("minecraft:logs"));
    Utility.registerPlacementRules(StairPlacementRule::new, StairPlacementRule.KEY);
    Utility.registerPlacementRules(SlabPlacementRule::new, SlabPlacementRule.KEY);
    Utility.registerPlacementRules(FencePlacementRule::new, FencePlacementRule.KEY);
    Utility.registerPlacementRules(FenceGatePlacementRule::new, FenceGatePlacementRule.KEY);
    Utility.registerPlacementRules(WallPlacementRule::new, WallPlacementRule.KEY);
    Utility.registerPlacementRules(PanePlacementRule::new,
            Block.GLASS_PANE,
            Block.IRON_BARS,
            Block.WHITE_STAINED_GLASS_PANE,
            Block.ORANGE_STAINED_GLASS_PANE,
            Block.MAGENTA_STAINED_GLASS_PANE,
            Block.LIGHT_BLUE_STAINED_GLASS_PANE,
            Block.YELLOW_STAINED_GLASS_PANE,
            Block.LIME_STAINED_GLASS_PANE,
            Block.PINK_STAINED_GLASS_PANE,
            Block.GRAY_STAINED_GLASS_PANE,
            Block.LIGHT_GRAY_STAINED_GLASS_PANE,
            Block.CYAN_STAINED_GLASS_PANE,
            Block.PURPLE_STAINED_GLASS_PANE,
            Block.BLUE_STAINED_GLASS_PANE,
            Block.BROWN_STAINED_GLASS_PANE,
            Block.GREEN_STAINED_GLASS_PANE,
            Block.RED_STAINED_GLASS_PANE,
            Block.BLACK_STAINED_GLASS_PANE);

    Utility.registerPlacementRules(DoorPlacementRule::new, DoorPlacementRule.KEY);
    Utility.registerPlacementRules(BedPlacementRule::new, BedPlacementRule.KEY);
    Utility.registerPlacementRules(ButtonPlacementRule::new, ButtonPlacementRule.KEY);
    Utility.registerPlacementRules(TrapdoorPlacementRule::new, TrapdoorPlacementRule.KEY);
    Utility.registerPlacementRules(SignPlacementRule::new, Key.key("minecraft:standing_signs"));
    Utility.registerPlacementRules(WallSignPlacementRule::new, WallSignPlacementRule.KEY);
    Utility.registerPlacementRules(HangingSignPlacementRule::new, CeilingHangingSignPlacementRule.KEY);
    Utility.registerPlacementRules(WallHangingSignPlacementRule::new, WallHangingSignPlacementRule.KEY);
    Utility.registerPlacementRules(BannerPlacementRule::new, BannerPlacementRule.KEY);
    Utility.registerPlacementRules(HorizontalFacingPlacementRule::new, Block.FURNACE, Block.BLAST_FURNACE, Block.SMOKER, Block.STONECUTTER);
    Utility.registerPlacementRules(ChestPlacementRule::new, Block.CHEST, Block.TRAPPED_CHEST);
    Utility.registerPlacementRules(PlantPlacementRule::new, PlantPlacementRule.KEY);
    Utility.registerPlacementRules(PlantPlacementRule::new, Key.key("minecraft:saplings"));
    Utility.registerPlacementRules(CropPlacementRule::new, CropPlacementRule.KEY);
    Utility.registerPlacementRules(TallPlantPlacementRule::new,
            Block.SUNFLOWER,
            Block.LILAC,
            Block.PEONY,
            Block.ROSE_BUSH,
            Block.TALL_GRASS,
            Block.LARGE_FERN,
            Block.TALL_SEAGRASS,
            Block.PITCHER_PLANT);

    Utility.registerPlacementRules(MushroomPlacementRule::new, Block.BROWN_MUSHROOM, Block.RED_MUSHROOM);
    Utility.registerPlacementRules(SugarCanePlacementRule::new, Block.SUGAR_CANE);
    Utility.registerPlacementRules(CactusPlacementRule::new, Block.CACTUS);
    Utility.registerPlacementRules(CactusFlowerPlacementRule::new, Block.CACTUS_FLOWER);
    Utility.registerPlacementRules(RailPlacementRule::new, RailPlacementRule.KEY);

    Utility.registerPlacementRules(AmethystClusterPlacementRule::new,
            Block.AMETHYST_CLUSTER,
            Block.LARGE_AMETHYST_BUD,
            Block.MEDIUM_AMETHYST_BUD,
            Block.SMALL_AMETHYST_BUD);
    Utility.registerPlacementRules(AnvilPlacementRule::new,
            Block.ANVIL,
            Block.CHIPPED_ANVIL,
            Block.DAMAGED_ANVIL);
    Utility.registerPlacementRules(BellPlacementRule::new, Block.BELL);
    Utility.registerPlacementRules(CampfirePlacementRule::new, Block.CAMPFIRE, Block.SOUL_CAMPFIRE);
    Utility.registerPlacementRules(CandlePlacementRule::new,
            Block.CANDLE,
            Block.WHITE_CANDLE,
            Block.LIGHT_GRAY_CANDLE,
            Block.GRAY_CANDLE,
            Block.BLACK_CANDLE,
            Block.BROWN_CANDLE,
            Block.RED_CANDLE,
            Block.ORANGE_CANDLE,
            Block.YELLOW_CANDLE,
            Block.LIME_CANDLE,
            Block.GREEN_CANDLE,
            Block.CYAN_CANDLE,
            Block.LIGHT_BLUE_CANDLE,
            Block.BLUE_CANDLE,
            Block.PURPLE_CANDLE,
            Block.MAGENTA_CANDLE,
            Block.PINK_CANDLE);
    Utility.registerPlacementRules(ChorusPlantPlacementRule::new, Block.CHORUS_PLANT);
    Utility.registerPlacementRules(CocoaPlacementRule::new, Block.COCOA);
    Utility.registerPlacementRules(CoralFanPlacementRule::new,
            Block.TUBE_CORAL_FAN,
            Block.BRAIN_CORAL_FAN,
            Block.BUBBLE_CORAL_FAN,
            Block.FIRE_CORAL_FAN,
            Block.HORN_CORAL_FAN,
            Block.DEAD_TUBE_CORAL_FAN,
            Block.DEAD_BRAIN_CORAL_FAN,
            Block.DEAD_BUBBLE_CORAL_FAN,
            Block.DEAD_FIRE_CORAL_FAN,
            Block.DEAD_HORN_CORAL_FAN);
    Utility.registerPlacementRules(CrafterPlacementRule::new, Block.CRAFTER);
    Utility.registerPlacementRules(DecoratedPotPlacementRule::new, Block.DECORATED_POT);
    Utility.registerPlacementRules(DripstonePlacementRule::new, Block.POINTED_DRIPSTONE);
    Utility.registerPlacementRules(HopperPlacementRule::new, Block.HOPPER);
    Utility.registerPlacementRules(HugeMushroomPlacementRule::new,
            Block.BROWN_MUSHROOM_BLOCK,
            Block.RED_MUSHROOM_BLOCK,
            Block.MUSHROOM_STEM);
    Utility.registerPlacementRules(LanternPlacementRule::new, Block.LANTERN, Block.SOUL_LANTERN);
    Utility.registerPlacementRules(block -> new MultifacePlacementRule(block).waterlogged(),
            Block.SCULK_VEIN,
            Block.GLOW_LICHEN);
    Utility.registerPlacementRules(PinkPetalPlacementRule::new, Block.PINK_PETALS);
    Utility.registerPlacementRules(RedstoneWirePlacementRule::new, Block.REDSTONE_WIRE);
    Utility.registerPlacementRules(SkullPlacementRule::new,
            Block.SKELETON_SKULL,
            Block.ZOMBIE_HEAD,
            Block.CREEPER_HEAD,
            Block.PIGLIN_HEAD,
            Block.WITHER_SKELETON_SKULL,
            Block.DRAGON_HEAD,
            Block.PLAYER_HEAD);
    Utility.registerPlacementRules(SnowLayerPlacementRule::new, Block.SNOW);
    Utility.registerPlacementRules(StringPlacementRule::new, Block.TRIPWIRE);
    Utility.registerPlacementRules(TorchPlacementRule::new,
            Block.TORCH,
            Block.SOUL_TORCH,
            Block.REDSTONE_TORCH);
    Utility.registerPlacementRules(VinePlacementRule::new, Block.VINE);
}

void main() {
    var server = MinecraftServer.init(new Auth.Online());
    var instance = createInstance();

    MinecraftServer.getGlobalEventHandler()
            .addListener(AsyncPlayerConfigurationEvent.class, event -> event.setSpawningInstance(instance))
            .addListener(PlayerSpawnEvent.class, event -> event.getPlayer().setGameMode(GameMode.CREATIVE));

    registerPlacementRules();
    server.start("0.0.0.0", 25565);
}
