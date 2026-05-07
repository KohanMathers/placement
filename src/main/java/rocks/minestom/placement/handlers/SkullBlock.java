package rocks.minestom.placement.handlers;

/**
 * Original code taken and modified from <a href="https://kloon.io">kloon</a>.
 * Original code licensed under MIT.
 */

import com.google.common.collect.ImmutableBiMap;
import rocks.minestom.placement.properties.BlockProp;
import rocks.minestom.placement.properties.EnumProp;
import rocks.minestom.placement.properties.IntProp;
import rocks.minestom.placement.properties.enums.FacingXZ;
import net.minestom.server.instance.block.Block;

import java.util.Set;

public class SkullBlock {
    public static final IntProp GROUND_ROTATION = new IntProp("rotation", 16);
    public static final EnumProp<FacingXZ> WALL_FACING = BlockProp.FACING_XZ;

    public static final ImmutableBiMap<Block,Block> BLOCK_TO_WALL = ImmutableBiMap.<Block, Block>builder()
            .put(Block.SKELETON_SKULL, Block.SKELETON_WALL_SKULL)
            .put(Block.ZOMBIE_HEAD, Block.ZOMBIE_WALL_HEAD)
            .put(Block.CREEPER_HEAD, Block.CREEPER_WALL_HEAD)
            .put(Block.PIGLIN_HEAD, Block.PIGLIN_WALL_HEAD)
            .put(Block.WITHER_SKELETON_SKULL, Block.WITHER_SKELETON_WALL_SKULL)
            .put(Block.DRAGON_HEAD, Block.DRAGON_WALL_HEAD)
            .put(Block.PLAYER_HEAD, Block.PLAYER_WALL_HEAD)
            .build();
    public static final Set<Block> BLOCKS = BLOCK_TO_WALL.keySet();
}
