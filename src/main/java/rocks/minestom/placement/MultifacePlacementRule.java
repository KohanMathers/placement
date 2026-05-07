package rocks.minestom.placement;

/**
 * Original code taken and modified from <a href="https://kloon.io">kloon</a>.
 * Original code licensed under MIT.
 */

import rocks.minestom.placement.handlers.MultifaceBlock;
import rocks.minestom.placement.utils.PlacementUtils;
import net.minestom.server.instance.block.Block;
import net.minestom.server.instance.block.BlockFace;
import net.minestom.server.instance.block.rule.BlockPlacementRule;
import org.jetbrains.annotations.NotNull;

public final class MultifacePlacementRule extends BlockPlacementRule {
    private boolean waterlogged = false;

    public MultifacePlacementRule(@NotNull Block block) {
        super(block);
    }

    public MultifacePlacementRule waterlogged() {
        this.waterlogged = true;
        return this;
    }

    @Override
    public Block blockPlace(@NotNull PlacementState placementState) {
        Block block = placementState.block();
        Block existing = placementState.instance().getBlock(placementState.placePosition());
        if (existing.compare(this.block)) {
            block = existing;
        } else if (waterlogged) {
            block = PlacementUtils.waterlogged(placementState);
        }

        BlockFace clickedFace = placementState.blockFace();
        if (clickedFace == null) {
            return block;
        }

        BlockFace connectedFace = clickedFace.getOppositeFace();
        block = MultifaceBlock.fromBlockFace(connectedFace).get(true).on(block);

        return block;
    }

    @Override
    public boolean isSelfReplaceable(@NotNull Replacement replacement) {
        return true;
    }
}
