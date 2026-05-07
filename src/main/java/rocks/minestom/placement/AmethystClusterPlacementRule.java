package rocks.minestom.placement;

/**
 * Original code taken and modified from <a href="https://kloon.io">kloon</a>.
 * Original code licensed under MIT.
 */

import rocks.minestom.placement.utils.PlacementUtils;
import rocks.minestom.placement.properties.BlockProp;
import rocks.minestom.placement.properties.enums.FacingXYZ;
import net.minestom.server.instance.block.Block;
import net.minestom.server.instance.block.rule.BlockPlacementRule;
import org.jetbrains.annotations.NotNull;

public final class AmethystClusterPlacementRule extends BlockPlacementRule {
    public AmethystClusterPlacementRule(@NotNull Block block) {
        super(block);
    }

    @Override
    public Block blockPlace(@NotNull PlacementState placementState) {
        Block block = PlacementUtils.waterlogged(placementState);
        FacingXYZ facing = FacingXYZ.fromBlockFace(placementState.blockFace());
        block = BlockProp.FACING_XYZ.get(facing).on(block);
        return block;
    }
}
