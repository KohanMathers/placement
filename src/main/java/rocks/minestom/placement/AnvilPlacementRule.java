package rocks.minestom.placement;

/**
 * Original code taken and modified from <a href="https://kloon.io">kloon</a>.
 * Original code licensed under MIT.
 */

import rocks.minestom.placement.properties.BlockProp;
import rocks.minestom.placement.properties.enums.FacingXYZ;
import rocks.minestom.placement.utils.DirectionUtils;
import net.minestom.server.instance.block.Block;
import net.minestom.server.instance.block.rule.BlockPlacementRule;
import net.minestom.server.utils.Direction;
import net.minestom.server.utils.MathUtils;
import org.jetbrains.annotations.NotNull;

public final class AnvilPlacementRule extends BlockPlacementRule {
    public AnvilPlacementRule(@NotNull Block block) {
        super(block);
    }

    @Override
    public Block blockPlace(@NotNull PlacementState placementState) {
        Direction direction = MathUtils.getHorizontalDirection(placementState.playerPosition().yaw());
        direction = DirectionUtils.clockwise(direction);
        FacingXYZ facing = FacingXYZ.fromDirection(direction);
        return BlockProp.FACING_XYZ.get(facing).on(placementState);
    }
}
