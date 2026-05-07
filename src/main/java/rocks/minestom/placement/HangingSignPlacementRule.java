package rocks.minestom.placement;

/**
 * Original code taken and modified from <a href="https://kloon.io">kloon</a>.
 * Original code licensed under MIT.
 */

import rocks.minestom.placement.utils.BlockFamily;
import rocks.minestom.placement.handlers.WaterBlock;
import rocks.minestom.placement.handlers.signs.CeilingHangingSignBlock;
import rocks.minestom.placement.properties.BannerRotProp;
import rocks.minestom.placement.properties.BlockProp;
import rocks.minestom.placement.properties.enums.FacingXZ;
import rocks.minestom.placement.utils.PlacementUtils;
import rocks.minestom.placement.utils.DirectionUtils;
import net.minestom.server.coordinate.Point;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.coordinate.Vec;
import net.minestom.server.instance.block.Block;
import net.minestom.server.instance.block.BlockFace;
import net.minestom.server.instance.block.rule.BlockPlacementRule;
import net.minestom.server.utils.Direction;
import org.jetbrains.annotations.NotNull;

public final class HangingSignPlacementRule extends BlockPlacementRule {
    public HangingSignPlacementRule(@NotNull Block block) {
        super(block);
    }

    @Override
    public Block blockPlace(@NotNull PlacementState placementState) {
        Block block = PlacementUtils.waterlogged(placementState);
        boolean inWater = WaterBlock.WATERLOGGED.is(block);

        BlockFace face = placementState.blockFace();

        if (face == BlockFace.TOP) {
            return null;
        }

        if (face == BlockFace.BOTTOM) {
            boolean attached;
            int segment;
            if (placementState.isPlayerShifting()) {
                attached = true;
                segment = BannerRotProp.convertToSegment(placementState.playerPosition().yaw() + 180);
            } else {
                attached = false;
                Direction direction = FacingXZ.fromLook(placementState.playerPosition()).toDirection().opposite();
                segment = BannerRotProp.convertToSegment(direction);
            }
            block = CeilingHangingSignBlock.ATTACHED.get(attached).on(block);
            return CeilingHangingSignBlock.ROTATION.get(segment).on(block);
        }

        Vec normal = face.toDirection().vec();
        Vec cross = normal.cross(new Vec(0, 1, 0));

        Point blockCenter = placementState.placePosition().add(0.5, 0.5, 0.5);
        Pos playerPos = placementState.playerPosition().withY(blockCenter.y());
        Vec toPlayer = playerPos.sub(blockCenter).asVec().normalize();
        double dot = cross.dot(toPlayer);

        FacingXZ facing = FacingXZ.fromBlockFace(DirectionUtils.clockwise(face));
        if (dot < 0) {
            facing = facing.opposite();
        }

        BlockFamily family = BlockFamily.getFamily(block);
        Block wallBlock = family.wallHangingSign();

        wallBlock = WaterBlock.WATERLOGGED.get(inWater).on(wallBlock);
        wallBlock = BlockProp.FACING_XZ.get(facing).on(wallBlock);

        return wallBlock;
    }
}
