package rocks.minestom.placement;

/**
 * Original code taken and modified from <a href="https://kloon.io">kloon</a>.
 * Original code licensed under MIT.
 */

import rocks.minestom.placement.handlers.FenceBlock;
import rocks.minestom.placement.handlers.PaneBlock;
import rocks.minestom.placement.handlers.WallBlock;
import rocks.minestom.placement.utils.PlacementUtils;
import net.minestom.server.coordinate.Point;
import net.minestom.server.instance.block.Block;
import net.minestom.server.instance.block.rule.BlockPlacementRule;
import net.minestom.server.utils.Direction;
import org.jetbrains.annotations.NotNull;

public final class PanePlacementRule extends BlockPlacementRule {
    public PanePlacementRule(@NotNull Block block) {
        super(block);
    }

    @Override
    public @NotNull Block blockUpdate(@NotNull UpdateState updateState) {
        return getDirectionalState(updateState.instance(), updateState.currentBlock(), updateState.blockPosition());
    }

    @Override
    public Block blockPlace(@NotNull PlacementState placementState) {
        Block block = PlacementUtils.waterlogged(placementState);
        return getDirectionalState(placementState.instance(), block, placementState.placePosition());
    }

    public Block getDirectionalState(Block.Getter instance, Block block, Point blockPos) {
        for (Direction direction : Direction.HORIZONTAL) {
            Block relBlock = instance.getBlock(blockPos.add(direction.vec())).defaultState();
            boolean canConnect = PaneBlock.BLOCKS.contains(relBlock)
                                 || WallBlock.BLOCKS.contains(relBlock)
                                 || FenceBlock.canAttach(relBlock);
            block = PaneBlock.fromDirection(direction).get(canConnect).on(block);
        }
        return block;
    }
}
