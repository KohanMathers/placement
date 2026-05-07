package rocks.minestom.placement;

/**
 * Original code taken and modified from <a href="https://kloon.io">kloon</a>.
 * Original code licensed under MIT.
 */

import rocks.minestom.placement.handlers.HugeMushroomBlock;
import net.minestom.server.coordinate.Point;
import net.minestom.server.instance.block.Block;
import net.minestom.server.instance.block.rule.BlockPlacementRule;
import net.minestom.server.utils.Direction;
import org.jetbrains.annotations.NotNull;

public final class HugeMushroomPlacementRule extends BlockPlacementRule {
    public HugeMushroomPlacementRule(@NotNull Block block) {
        super(block);
    }

    @Override
    public @NotNull Block blockUpdate(@NotNull UpdateState updateState) {
        return getState(updateState.instance(), updateState.currentBlock(), updateState.blockPosition());
    }

    @Override
    public Block blockPlace(@NotNull PlacementState placementState) {
        return getState(placementState.instance(), placementState.block(), placementState.placePosition());
    }

    private Block getState(Block.Getter instance, Block block, Point blockPos) {
        for (Direction direction : Direction.values()) {
            Block relBlock = instance.getBlock(blockPos.add(direction.vec()));
            if (block.id() == relBlock.id()) {
                block = HugeMushroomBlock.getFromDirection(direction).get(false).on(block);
            }
        }

        return block;
    }
}
