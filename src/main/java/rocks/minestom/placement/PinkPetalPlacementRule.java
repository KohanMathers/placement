package rocks.minestom.placement;

/**
 * Original code taken and modified from <a href="https://kloon.io">kloon</a>.
 * Original code licensed under MIT.
 */

import rocks.minestom.placement.handlers.PinkPetalBlock;
import rocks.minestom.placement.properties.enums.FacingXZ;
import net.minestom.server.instance.block.Block;
import net.minestom.server.instance.block.rule.BlockPlacementRule;
import org.jetbrains.annotations.NotNull;

public final class PinkPetalPlacementRule extends BlockPlacementRule {
    public PinkPetalPlacementRule(@NotNull Block block) {
        super(block);
    }

    @Override
    public Block blockPlace(@NotNull PlacementState placementState) {
        Block block = placementState.block();

        Block existing = placementState.instance().getBlock(placementState.placePosition());
        int flowers = PinkPetalBlock.FLOWER_AMOUNT.getOrZero(existing) + 1;

        FacingXZ facing = FacingXZ.fromLook(placementState.playerPosition()).opposite();

        block = PinkPetalBlock.FLOWER_AMOUNT.get(flowers).on(block);
        block = PinkPetalBlock.FACING.get(facing).on(block);

        return block;
    }

    @Override
    public boolean isSelfReplaceable(@NotNull Replacement replacement) {
        Block block = replacement.block();
        Block blockType = block.defaultState();
        if (blockType == this.block) {
            int flowers = PinkPetalBlock.FLOWER_AMOUNT.get(block);
            return flowers < 4;
        }
        return super.isSelfReplaceable(replacement);
    }
}
