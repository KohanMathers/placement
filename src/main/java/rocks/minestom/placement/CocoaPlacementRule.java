package rocks.minestom.placement;

/**
 * Original code taken and modified from <a href="https://kloon.io">kloon</a>.
 * Original code licensed under MIT.
 */

import rocks.minestom.placement.handlers.CocoaBlock;
import rocks.minestom.placement.properties.enums.FacingXZ;
import net.minestom.server.instance.block.Block;
import net.minestom.server.instance.block.BlockFace;
import net.minestom.server.instance.block.rule.BlockPlacementRule;
import org.jetbrains.annotations.NotNull;

public final class CocoaPlacementRule extends BlockPlacementRule {
    public CocoaPlacementRule(@NotNull Block block) {
        super(block);
    }

    @Override
    public Block blockPlace(@NotNull PlacementState placementState) {
        BlockFace clickedFace = placementState.blockFace();
        if (clickedFace == BlockFace.TOP || clickedFace == BlockFace.BOTTOM) {
            return null;
        }

        FacingXZ facing = FacingXZ.fromBlockFace(clickedFace.getOppositeFace());
        return CocoaBlock.FACING.get(facing).on(placementState.block());
    }
}
