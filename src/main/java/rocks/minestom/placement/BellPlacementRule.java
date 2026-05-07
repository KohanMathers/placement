package rocks.minestom.placement;

/**
 * Original code taken and modified from <a href="https://kloon.io">kloon</a>.
 * Original code licensed under MIT.
 */

import rocks.minestom.placement.handlers.BellBlock;
import rocks.minestom.placement.properties.enums.FacingXZ;
import net.minestom.server.coordinate.Point;
import net.minestom.server.instance.block.Block;
import net.minestom.server.instance.block.BlockFace;
import net.minestom.server.instance.block.rule.BlockPlacementRule;
import org.jetbrains.annotations.NotNull;

public final class BellPlacementRule extends BlockPlacementRule {
    public BellPlacementRule(@NotNull Block block) {
        super(block);
    }

    @Override
    public Block blockPlace(@NotNull PlacementState placementState) {
        Block.Getter instance = placementState.instance();
        BlockFace blockFace = placementState.blockFace();
        Point blockPos = placementState.placePosition();

        Block block = placementState.block();

        BellBlock.Attachment attachment;
        FacingXZ facing = FacingXZ.fromLook(placementState.playerPosition());

        if (blockFace == BlockFace.BOTTOM) {
            attachment = BellBlock.Attachment.CEILING;
        } else if (blockFace == BlockFace.TOP) {
            attachment = BellBlock.Attachment.FLOOR;
        } else {
            Block facingBlock = instance.getBlock(blockPos.relative(blockFace));
            Block oppositeBlock = instance.getBlock(blockPos.relative(blockFace.getOppositeFace()));

            if (facingBlock.isSolid() && oppositeBlock.isSolid()) {
                attachment = BellBlock.Attachment.DOUBLE_WALL;
                facing = FacingXZ.fromBlockFace(blockFace).opposite();
            } else if (facingBlock.isSolid() && !oppositeBlock.isSolid()) {
                attachment = BellBlock.Attachment.SINGLE_WALL;
            } else if (oppositeBlock.isSolid() && !facingBlock.isSolid()) {
                attachment = BellBlock.Attachment.SINGLE_WALL;
                facing = FacingXZ.fromBlockFace(blockFace.getOppositeFace());
            } else {
                attachment = BellBlock.Attachment.FLOOR;
            }
        }

        block = BellBlock.FACING.get(facing).on(block);
        block = BellBlock.ATTACHMENT.get(attachment).on(block);

        return block;
    }
}
