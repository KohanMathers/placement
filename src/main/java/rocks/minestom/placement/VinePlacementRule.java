package rocks.minestom.placement;

/**
 * Original code taken and modified from <a href="https://kloon.io">kloon</a>.
 * Original code licensed under MIT.
 */

import org.jetbrains.annotations.NotNull;

import net.minestom.server.coordinate.Point;
import net.minestom.server.instance.block.Block;
import net.minestom.server.instance.block.BlockFace;
import net.minestom.server.instance.block.rule.BlockPlacementRule;
import rocks.minestom.placement.handlers.VineBlock;
import rocks.minestom.placement.properties.BooleanProp;
import rocks.minestom.placement.utils.DirectionUtils;

public final class VinePlacementRule extends BlockPlacementRule {
    public VinePlacementRule(@NotNull Block block) {
        super(block);
    }

    @Override
    public Block blockPlace(@NotNull PlacementState placementState) {
        Block existing = placementState.instance().getBlock(placementState.placePosition());
        boolean isVine = existing.id() == this.block.id();
        Block block = isVine ? existing : this.block;

        BlockFace face = placementState.blockFace();
        if (face == BlockFace.BOTTOM || face == null) {
            return block;
        }
        face = face.getOppositeFace();

        Point relPos = placementState.placePosition().relative(face);
        Block relBlock = placementState.instance().getBlock(relPos);
        if (relBlock.isSolid()) {
            BooleanProp prop = VineBlock.fromDirection(DirectionUtils.fromBlockFace(face));
            block = prop.get(true).on(block);
        } else if (VineBlock.allSidesAreOff(block)) {
            return null;
        }

        return block;
    }

    @Override
    public boolean isSelfReplaceable(@NotNull Replacement replacement) {
        return true;
    }
}
