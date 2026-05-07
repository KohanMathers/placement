package rocks.minestom.placement;

/**
 * Original code taken and modified from <a href="https://kloon.io">kloon</a>.
 * Original code licensed under MIT.
 */

import com.google.common.collect.ImmutableBiMap;
import rocks.minestom.placement.handlers.WaterBlock;
import rocks.minestom.placement.properties.BlockProp;
import rocks.minestom.placement.properties.enums.FacingXZ;
import rocks.minestom.placement.utils.PlacementUtils;
import net.minestom.server.instance.block.Block;
import net.minestom.server.instance.block.BlockFace;
import net.minestom.server.instance.block.rule.BlockPlacementRule;
import org.jetbrains.annotations.NotNull;

public final class CoralFanPlacementRule extends BlockPlacementRule {
    public CoralFanPlacementRule(@NotNull Block block) {
        super(block);
    }

    public static final ImmutableBiMap<Block, Block> BLOCK_TO_WALL = ImmutableBiMap.<Block, Block>builder()
            .put(Block.TUBE_CORAL_FAN, Block.TUBE_CORAL_WALL_FAN)
            .put(Block.BRAIN_CORAL_FAN, Block.BRAIN_CORAL_WALL_FAN)
            .put(Block.BUBBLE_CORAL_FAN, Block.BUBBLE_CORAL_WALL_FAN)
            .put(Block.FIRE_CORAL_FAN, Block.FIRE_CORAL_WALL_FAN)
            .put(Block.HORN_CORAL_FAN, Block.HORN_CORAL_WALL_FAN)
            .put(Block.DEAD_TUBE_CORAL_FAN, Block.DEAD_TUBE_CORAL_WALL_FAN)
            .put(Block.DEAD_BRAIN_CORAL_FAN, Block.DEAD_BRAIN_CORAL_WALL_FAN)
            .put(Block.DEAD_BUBBLE_CORAL_FAN, Block.DEAD_BUBBLE_CORAL_WALL_FAN)
            .put(Block.DEAD_FIRE_CORAL_FAN, Block.DEAD_FIRE_CORAL_WALL_FAN)
            .put(Block.DEAD_HORN_CORAL_FAN, Block.DEAD_HORN_CORAL_WALL_FAN)
            .build();

    @Override
    public Block blockPlace(@NotNull PlacementState placementState) {
        Block block = PlacementUtils.waterlogged(placementState);
        boolean waterlogged = WaterBlock.WATERLOGGED.is(block);

        BlockFace face = placementState.blockFace();

        if (face == BlockFace.TOP || face == BlockFace.BOTTOM || face == null) {
            return block;
        }

        FacingXZ facing = FacingXZ.fromBlockFace(face);

        block = BLOCK_TO_WALL.get(block.defaultState());
        block = WaterBlock.WATERLOGGED.get(waterlogged).on(block);
        block = BlockProp.FACING_XZ.get(facing).on(block);

        return block;
    }
}
