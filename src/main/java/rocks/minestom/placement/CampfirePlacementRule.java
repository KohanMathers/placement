package rocks.minestom.placement;

/**
 * Original code taken and modified from <a href="https://kloon.io">kloon</a>.
 * Original code licensed under MIT.
 */

import org.jetbrains.annotations.NotNull;

import net.minestom.server.instance.block.Block;
import net.minestom.server.instance.block.BlockFace;
import net.minestom.server.instance.block.rule.BlockPlacementRule;
import rocks.minestom.placement.handlers.CampfireBlock;
import rocks.minestom.placement.handlers.FacingXZBlock;
import rocks.minestom.placement.handlers.WaterBlock;
import rocks.minestom.placement.properties.enums.FacingXZ;
import rocks.minestom.placement.utils.PlacementUtils;

public final class CampfirePlacementRule extends BlockPlacementRule {
    public CampfirePlacementRule(@NotNull Block block) {
        super(block);
    }

    @Override
    public Block blockPlace(@NotNull PlacementState placementState) {
        Block block = PlacementUtils.waterlogged(placementState);
        boolean inWater = WaterBlock.WATERLOGGED.is(block);

        boolean lit = !inWater;

        Block blockBelow = placementState.instance().getBlock(placementState.placePosition().relative(BlockFace.BOTTOM));
        boolean signal = CampfireBlock.isSmokeSource(blockBelow);

        FacingXZ facing = FacingXZ.fromLook(placementState.playerPosition()).opposite();

        block = CampfireBlock.LIT.get(lit).on(block);
        block = CampfireBlock.SIGNAL_FIRE.get(signal).on(block);
        block = FacingXZBlock.FACING_XZ.get(facing).on(block);

        return block;
    }
}
