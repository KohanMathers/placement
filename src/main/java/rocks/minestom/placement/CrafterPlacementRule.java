package rocks.minestom.placement;

/**
 * Original code taken and modified from <a href="https://kloon.io">kloon</a>.
 * Original code licensed under MIT.
 */

import rocks.minestom.placement.handlers.CrafterBlock;
import rocks.minestom.placement.properties.enums.FrontAndTop;
import rocks.minestom.placement.utils.PlacementUtils;
import net.minestom.server.instance.block.Block;
import net.minestom.server.instance.block.rule.BlockPlacementRule;
import net.minestom.server.utils.Direction;
import org.jetbrains.annotations.NotNull;

public final class CrafterPlacementRule extends BlockPlacementRule {
    public CrafterPlacementRule(@NotNull Block block) {
        super(block);
    }

    @Override
    public Block blockPlace(@NotNull PlacementState placementState) {
        Direction front = PlacementUtils.getLookingDirection(placementState).opposite();
        Direction top = switch (front) {
            case DOWN -> PlacementUtils.getHorizontalDirection(placementState).opposite();
            case UP -> PlacementUtils.getHorizontalDirection(placementState);
            case NORTH, SOUTH, WEST, EAST -> Direction.UP;
        };

        FrontAndTop frontAndTop = FrontAndTop.from(front, top);

        return CrafterBlock.ORIENTATION.get(frontAndTop).on(placementState.block());
    }
}
