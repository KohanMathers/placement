package rocks.minestom.placement.handlers;

/**
 * Original code taken and modified from <a href="https://kloon.io">kloon</a>.
 * Original code licensed under MIT.
 */

import rocks.minestom.placement.properties.BlockProp;
import rocks.minestom.placement.properties.EnumProp;
import rocks.minestom.placement.properties.IntProp;
import rocks.minestom.placement.properties.enums.FacingXZ;

public class PinkPetalBlock {
    public static final EnumProp<FacingXZ> FACING = BlockProp.FACING_XZ;
    public static final IntProp FLOWER_AMOUNT = new IntProp("flower_amount", 1, 5);
}
