package rocks.minestom.placement;

/**
 * Original code taken and modified from <a href="https://kloon.io">kloon</a>.
 * Original code licensed under MIT.
 */

import rocks.minestom.placement.handlers.LanternBlock;
import rocks.minestom.placement.utils.PlacementUtils;
import rocks.minestom.placement.utils.DirectionUtils;
import rocks.minestom.placement.utils.Axis;
import net.minestom.server.instance.block.Block;
import net.minestom.server.instance.block.BlockFace;
import net.minestom.server.instance.block.rule.BlockPlacementRule;
import org.jetbrains.annotations.NotNull;

public final class LanternPlacementRule extends BlockPlacementRule {
    public LanternPlacementRule(@NotNull Block block) {
        super(block);
    }

    @Override
    public Block blockPlace(@NotNull PlacementState placementState) {
        BlockFace face = placementState.blockFace();
        if (DirectionUtils.axis(face) != Axis.Y) {
            return null;
        }

        boolean hanging = face == BlockFace.BOTTOM;

        Block block = PlacementUtils.waterlogged(placementState);
        block = LanternBlock.HANGING.get(hanging).on(block);
        return block;
    }
}
