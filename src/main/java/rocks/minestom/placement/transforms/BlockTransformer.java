package rocks.minestom.placement.transforms;

/**
 * Original code taken and modified from <a href="https://kloon.io">kloon</a>.
 * Original code licensed under MIT.
 */

import net.minestom.server.instance.block.Block;

public interface BlockTransformer {
    Block rotate(Block block, RotationTransform rotation);

    Block flip(Block block, FlipTransform flip);
}
