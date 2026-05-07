package rocks.minestom.placement;

/**
 * Original code taken and modified from <a href="https://kloon.io">kloon</a>.
 * Original code licensed under MIT.
 */

import rocks.minestom.placement.handlers.SnowLayerBlock;
import net.minestom.server.instance.block.Block;
import net.minestom.server.instance.block.BlockFace;
import net.minestom.server.instance.block.rule.BlockPlacementRule;
import org.jetbrains.annotations.NotNull;

public final class SnowLayerPlacementRule extends BlockPlacementRule {
    public SnowLayerPlacementRule(@NotNull Block block) {
        super(block);
    }

    @Override
    public Block blockPlace(@NotNull PlacementState placementState) {
        Block existing = placementState.instance().getBlock(placementState.placePosition());
        if (existing.id() == block.id()) {
            int layer = SnowLayerBlock.LAYERS.get(existing) + 1;
            return SnowLayerBlock.LAYERS.get(layer).on(existing);
        }

        return placementState.block();
    }

    @Override
    public boolean isSelfReplaceable(@NotNull Replacement replacement) {
        Block block = replacement.block();
        int layer = SnowLayerBlock.LAYERS.getOrZero(block);
        if (layer <= 1) {
            return true;
        }

        return replacement.blockFace() == BlockFace.TOP && layer < 8;
    }
}
