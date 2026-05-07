package rocks.minestom.placement;

/**
 * Original code taken and modified from <a href="https://kloon.io">kloon</a>.
 * Original code licensed under MIT.
 */

import rocks.minestom.placement.utils.BlockFamily;
import rocks.minestom.placement.handlers.WaterBlock;
import rocks.minestom.placement.handlers.signs.StandingSignBlock;
import rocks.minestom.placement.properties.BannerRotProp;
import rocks.minestom.placement.properties.BlockProp;
import rocks.minestom.placement.properties.enums.FacingXZ;
import rocks.minestom.placement.utils.PlacementUtils;
import net.minestom.server.instance.block.Block;
import net.minestom.server.instance.block.BlockFace;
import net.minestom.server.instance.block.rule.BlockPlacementRule;
import org.jetbrains.annotations.NotNull;

public final class SignPlacementRule extends BlockPlacementRule {
    public SignPlacementRule(@NotNull Block block) {
        super(block);
    }

    @Override
    public Block blockPlace(@NotNull PlacementState placementState) {
        Block block = PlacementUtils.waterlogged(placementState);
        boolean inWater = WaterBlock.WATERLOGGED.is(block);

        BlockFace face = placementState.blockFace();

        if (face == BlockFace.BOTTOM) {
            return null;
        }

        if (face == BlockFace.TOP) {
            int segment = BannerRotProp.convertToSegment(placementState.playerPosition().yaw() + 180);
            return StandingSignBlock.ROTATION.get(segment).on(block);
        }

        FacingXZ facing = FacingXZ.fromBlockFace(face);

        BlockFamily family = BlockFamily.getFamily(block);
        Block wallBlock = family.wallSign();

        wallBlock = WaterBlock.WATERLOGGED.get(inWater).on(wallBlock);
        wallBlock = BlockProp.FACING_XZ.get(facing).on(wallBlock);

        return wallBlock;
    }
}
