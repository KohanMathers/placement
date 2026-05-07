package rocks.minestom.placement;

/**
 * Original code taken and modified from <a href="https://kloon.io">kloon</a>.
 * Original code licensed under MIT.
 */

import rocks.minestom.placement.handlers.TorchBlock;
import rocks.minestom.placement.properties.BlockProp;
import rocks.minestom.placement.properties.enums.FacingXZ;
import net.minestom.server.instance.block.Block;
import net.minestom.server.instance.block.BlockFace;
import net.minestom.server.instance.block.rule.BlockPlacementRule;
import org.jetbrains.annotations.NotNull;

public final class TorchPlacementRule extends BlockPlacementRule {
    public TorchPlacementRule(@NotNull Block block) {
        super(block);
    }

    @Override
    public Block blockPlace(@NotNull PlacementState placementState) {
        BlockFace face = placementState.blockFace();
        if (face == BlockFace.BOTTOM) {
            return null;
        }
        if (face == BlockFace.TOP) {
            return block;
        }

        FacingXZ facing = FacingXZ.fromBlockFace(face);
        Block wallTorch = TorchBlock.TORCH_TO_WALL.get(block.defaultState());

        return BlockProp.FACING_XZ.get(facing).on(wallTorch);
    }
}
