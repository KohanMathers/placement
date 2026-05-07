package rocks.minestom.placement;

/**
 * Original code taken and modified from <a href="https://kloon.io">kloon</a>.
 * Original code licensed under MIT.
 */

import rocks.minestom.placement.handlers.PipeBlock;
import net.minestom.server.coordinate.Point;
import net.minestom.server.instance.Instance;
import net.minestom.server.instance.block.Block;
import net.minestom.server.instance.block.BlockFace;
import net.minestom.server.instance.block.rule.BlockPlacementRule;
import org.jetbrains.annotations.NotNull;

public final class ChorusPlantPlacementRule extends BlockPlacementRule {
    public ChorusPlantPlacementRule(@NotNull Block block) {
        super(block);
    }

    @Override
    public Block blockPlace(@NotNull PlacementState placementState) {
        Block block = placementState.block();
        Point blockPos = placementState.placePosition();

        boolean anyConnected = false;
        for (BlockFace face : BlockFace.values()) {
            Point relPos = blockPos.relative(face);
            Block relBlock = placementState.instance().getBlock(relPos);
            if (block.compare(relBlock)) {
                anyConnected = true;
                block = PipeBlock.getProperty(face).get(true).on(block);

                if (placementState.instance() instanceof Instance instance) {
                    relBlock = PipeBlock.getProperty(face.getOppositeFace()).get(true).on(relBlock);
                    instance.setBlock(relPos, relBlock, false);
                }
            }
        }

        if (!anyConnected) {
            return PipeBlock.getProperty(placementState.blockFace().getOppositeFace()).get(true).on(block);
        }

        return block;
    }
}
