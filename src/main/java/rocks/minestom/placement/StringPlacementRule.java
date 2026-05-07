package rocks.minestom.placement;

/**
 * Original code taken and modified from <a href="https://kloon.io">kloon</a>.
 * Original code licensed under MIT.
 */

import rocks.minestom.placement.handlers.StringBlock;
import rocks.minestom.placement.properties.BooleanProp;
import net.minestom.server.coordinate.Point;
import net.minestom.server.instance.Instance;
import net.minestom.server.instance.block.Block;
import net.minestom.server.instance.block.rule.BlockPlacementRule;
import net.minestom.server.utils.Direction;
import org.jetbrains.annotations.NotNull;

public final class StringPlacementRule extends BlockPlacementRule {
    public StringPlacementRule(@NotNull Block block) {
        super(block);
    }

    @Override
    public Block blockPlace(@NotNull PlacementState placementState) {
        Block block = placementState.block();
        Point blockPos = placementState.placePosition();
        for (Direction direction : Direction.HORIZONTAL) {
            Point relPos = blockPos.add(direction.vec());
            Block relBlock = placementState.instance().getBlock(relPos);
            if (block.id() == relBlock.id()) {
                BooleanProp prop = StringBlock.fromDirection(direction);
                block = prop.get(true).on(block);

                if (placementState.instance() instanceof Instance instance) {
                    relBlock = StringBlock.fromDirection(direction.opposite()).get(true).on(relBlock);
                    instance.setBlock(relPos, relBlock, false);
                }
            }
        }
        return block;
    }
}
