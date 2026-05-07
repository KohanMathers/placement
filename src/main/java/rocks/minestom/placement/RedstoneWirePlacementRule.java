package rocks.minestom.placement;

/**
 * Original code taken and modified from <a href="https://kloon.io">kloon</a>.
 * Original code licensed under MIT.
 */

import rocks.minestom.placement.handlers.RedstoneWireBlock;
import net.minestom.server.instance.Instance;
import net.minestom.server.instance.block.Block;
import net.minestom.server.instance.block.rule.BlockPlacementRule;
import org.jetbrains.annotations.NotNull;

public final class RedstoneWirePlacementRule extends BlockPlacementRule {
    public RedstoneWirePlacementRule(@NotNull Block block) {
        super(block);
    }

    @Override
    public @NotNull Block blockUpdate(@NotNull UpdateState updateState) {
        return RedstoneWireBlock.getConnectionState(updateState.instance(), updateState.currentBlock(), updateState.blockPosition());
    }

    @Override
    public Block blockPlace(@NotNull PlacementState placementState) {
        Block block = RedstoneWireBlock.getConnectionState(placementState.instance(), placementState.block(), placementState.placePosition());
        if (placementState.instance() instanceof Instance instance) {
            RedstoneWireBlock.updateNeighbors(instance, block, placementState.placePosition());
        }
        return block;
    }

    @Override
    public boolean isSelfReplaceable(@NotNull Replacement replacement) {
        Block block = replacement.block();
        return RedstoneWireBlock.isDot(block) || RedstoneWireBlock.isCross(block);
    }
}
