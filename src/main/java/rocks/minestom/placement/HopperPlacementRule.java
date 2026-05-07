package rocks.minestom.placement;

/**
 * Original code taken and modified from <a href="https://kloon.io">kloon</a>.
 * Original code licensed under MIT.
 */

import rocks.minestom.placement.handlers.HopperBlock;
import rocks.minestom.placement.properties.enums.FacingHopper;
import net.minestom.server.instance.block.Block;
import net.minestom.server.instance.block.BlockFace;
import net.minestom.server.instance.block.rule.BlockPlacementRule;
import org.jetbrains.annotations.NotNull;

public final class HopperPlacementRule extends BlockPlacementRule {
    public HopperPlacementRule(@NotNull Block block) {
        super(block);
    }

    @Override
    public Block blockPlace(@NotNull PlacementState placementState) {
        BlockFace oppositeFace = placementState.blockFace().getOppositeFace();

        FacingHopper facing = FacingHopper.fromBlockFace(oppositeFace);

        Block block = HopperBlock.ENABLED.get(true).on(placementState.block());
        block = HopperBlock.FACING.get(facing).on(block);

        return block;
    }
}
