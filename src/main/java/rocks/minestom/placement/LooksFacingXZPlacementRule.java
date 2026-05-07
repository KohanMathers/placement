package rocks.minestom.placement;

/**
 * Original code taken and modified from <a href="https://kloon.io">kloon</a>.
 * Original code licensed under MIT.
 */

import rocks.minestom.placement.handlers.FacingXZBlock;
import rocks.minestom.placement.properties.enums.FacingXZ;
import rocks.minestom.placement.utils.PlacementUtils;
import net.minestom.server.instance.block.Block;
import net.minestom.server.instance.block.rule.BlockPlacementRule;
import net.minestom.server.utils.Direction;
import net.minestom.server.utils.MathUtils;
import org.jetbrains.annotations.NotNull;

// opposite by default, actually (because most blocks are)
public final class LooksFacingXZPlacementRule extends BlockPlacementRule {
    private boolean waterlogged = false;
    private boolean opposite = false;

    public LooksFacingXZPlacementRule(@NotNull Block block) {
        super(block);
    }

    public LooksFacingXZPlacementRule waterlogged() {
        this.waterlogged = true;
        return this;
    }

    public LooksFacingXZPlacementRule opposite() {
        this.opposite = true;
        return this;
    }

    @Override
    public Block blockPlace(@NotNull PlacementState placementState) {
        float yaw = placementState.playerPosition().yaw();
        Direction dir = MathUtils.getHorizontalDirection(yaw).opposite();
        if (opposite) {
            dir = dir.opposite();
        }
        FacingXZ facing = FacingXZ.fromDirection(dir);

        Block block = placementState.block();
        if (waterlogged) {
            block = PlacementUtils.waterlogged(placementState);
        }
        block = FacingXZBlock.FACING_XZ.get(facing).on(block);
        return block;
    }
}
