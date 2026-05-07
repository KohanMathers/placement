package rocks.minestom.placement.properties;

/**
 * Original code taken and modified from <a href="https://kloon.io">kloon</a>.
 * Original code licensed under MIT.
 */

import rocks.minestom.placement.properties.enums.FacingXYZ;
import rocks.minestom.placement.properties.enums.FacingXZ;
import net.minestom.server.instance.block.Block;
import net.minestom.server.instance.block.rule.BlockPlacementRule;

public record BlockProp(
        String key,
        String value
) {
    public Block on(Block block) {
        return block.withProperty(key(), value());
    }

    public Block on(BlockPlacementRule.PlacementState state) {
        return on(state.block());
    }

    public static final EnumProp<FacingXYZ> FACING_XYZ = new EnumProp<>("facing", FacingXYZ.class);
    public static final EnumProp<FacingXZ> FACING_XZ = new EnumProp<>("facing", FacingXZ.class);
}
