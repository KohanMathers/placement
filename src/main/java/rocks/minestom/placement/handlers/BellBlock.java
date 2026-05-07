package rocks.minestom.placement.handlers;

/**
 * Original code taken and modified from <a href="https://kloon.io">kloon</a>.
 * Original code licensed under MIT.
 */

import rocks.minestom.placement.properties.BlockProp;
import rocks.minestom.placement.properties.BooleanProp;
import rocks.minestom.placement.properties.EnumProp;
import rocks.minestom.placement.properties.enums.FacingXZ;

public class BellBlock {
    public static final EnumProp<FacingXZ> FACING = BlockProp.FACING_XZ;
    public static final EnumProp<Attachment> ATTACHMENT = new EnumProp<>("attachment", Attachment.class);
    public static final BooleanProp POWERED = new BooleanProp("powered");

    public enum Attachment {
        CEILING,
        DOUBLE_WALL,
        FLOOR,
        SINGLE_WALL
    }
}
