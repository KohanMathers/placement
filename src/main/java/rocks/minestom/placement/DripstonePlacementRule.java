package rocks.minestom.placement;

/**
 * Original code taken and modified from <a href="https://kloon.io">kloon</a>.
 * Original code licensed under MIT.
 */

import rocks.minestom.placement.handlers.DripstoneBlock;
import rocks.minestom.placement.utils.PlacementUtils;
import net.minestom.server.coordinate.Point;
import net.minestom.server.instance.block.Block;
import net.minestom.server.instance.block.BlockFace;
import net.minestom.server.instance.block.rule.BlockPlacementRule;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class DripstonePlacementRule extends BlockPlacementRule {
    public DripstonePlacementRule(@NotNull Block block) {
        super(block);
    }

    @Override
    public @NotNull Block blockUpdate(@NotNull UpdateState updateState) {
        Block block = updateState.currentBlock();

        DripstoneBlock.Verticality verticality = DripstoneBlock.VERTICALITY.get(block);

        DripstoneBlock.Thickness thickness = DripstoneBlock.computeThickness(updateState.instance(), updateState.blockPosition(), verticality.direction(), true);

        return DripstoneBlock.THICKNESS.get(thickness).on(block);
    }

    @Override
    public Block blockPlace(@NotNull PlacementState placementState) {
        BlockFace clickedFace = placementState.blockFace();

        DripstoneBlock.Verticality verticality = getTipDirection(placementState.instance(), placementState.placePosition(), clickedFace);
        if (verticality == null) {
            return null;
        }

        boolean notSneaking = !placementState.isPlayerShifting();
        DripstoneBlock.Thickness thickness = DripstoneBlock.computeThickness(placementState.instance(), placementState.placePosition(), verticality.direction(), notSneaking);

        Block block = PlacementUtils.waterlogged(placementState);
        block = DripstoneBlock.THICKNESS.get(thickness).on(block);
        block = DripstoneBlock.VERTICALITY.get(verticality).on(block);

        return block;
    }

    @Nullable
    private DripstoneBlock.Verticality getTipDirection(Block.Getter instance, Point blockPos, BlockFace clickedFace) {
        if (clickedFace == BlockFace.TOP) {
            Block below = instance.getBlock(blockPos.relative(BlockFace.BOTTOM));
            return below.isSolid() || DripstoneBlock.isDripstoneWithVerticality(below, DripstoneBlock.Verticality.UP) ? DripstoneBlock.Verticality.UP : null;
        } else if (clickedFace == BlockFace.BOTTOM) {
            Block above = instance.getBlock(blockPos.relative(BlockFace.TOP));
            return above.isSolid() || DripstoneBlock.isDripstoneWithVerticality(above, DripstoneBlock.Verticality.DOWN) ? DripstoneBlock.Verticality.DOWN : null;
        }
        return null;
    }
}
