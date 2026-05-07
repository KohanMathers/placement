package rocks.minestom.placement;

/**
 * Original code taken and modified from <a href="https://kloon.io">kloon</a>.
 * Original code licensed under MIT.
 */

import rocks.minestom.placement.handlers.CandleBlock;
import rocks.minestom.placement.utils.PlacementUtils;
import net.minestom.server.instance.block.Block;
import net.minestom.server.instance.block.rule.BlockPlacementRule;
import org.jetbrains.annotations.NotNull;

public final class CandlePlacementRule extends BlockPlacementRule {
    public CandlePlacementRule(@NotNull Block block) {
        super(block);
    }

    @Override
    public Block blockPlace(@NotNull PlacementState placementState) {
        Block block = PlacementUtils.waterlogged(placementState);

        Block existing = placementState.instance().getBlock(placementState.placePosition());
        int candles = CandleBlock.CANDLES.getOrZero(existing) + 1;

        return CandleBlock.CANDLES.get(candles).on(block);
    }

    @Override
    public boolean isSelfReplaceable(@NotNull Replacement replacement) {
        Block block = replacement.block();
        Block blockType = block.defaultState();
        if (blockType == this.block) {
            int candles = CandleBlock.CANDLES.get(block);
            return candles < 4;
        }
        return super.isSelfReplaceable(replacement);
    }
}
