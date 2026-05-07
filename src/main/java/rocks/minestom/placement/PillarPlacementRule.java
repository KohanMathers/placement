package rocks.minestom.placement;

/**
 * Original code taken and modified from <a href="https://kloon.io">kloon</a>.
 * Original code licensed under MIT.
 */

import rocks.minestom.placement.handlers.PillarBlock;
import rocks.minestom.placement.utils.DirectionUtils;
import rocks.minestom.placement.utils.Axis;
import net.minestom.server.instance.block.Block;
import net.minestom.server.instance.block.BlockFace;
import net.minestom.server.instance.block.rule.BlockPlacementRule;
import org.jetbrains.annotations.NotNull;

public final class PillarPlacementRule extends BlockPlacementRule {
    private boolean waterlogged = false;

    public PillarPlacementRule(@NotNull Block block) {
        super(block);
    }

    public PillarPlacementRule waterlogged() {
        this.waterlogged = true;
        return this;
    }

    @Override
    public Block blockPlace(@NotNull PlacementState placementState) {
        BlockFace face = placementState.blockFace();
        if (face == null) {
            return placementState.block();
        }
        Axis axis = DirectionUtils.axis(face);

        return PillarBlock.AXIS.get(axis).on(placementState);
    }
}
