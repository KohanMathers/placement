package rocks.minestom.placement.handlers;

/**
 * Original code taken and modified from <a href="https://kloon.io">kloon</a>.
 * Original code licensed under MIT.
 */

import rocks.minestom.placement.properties.BooleanProp;
import rocks.minestom.placement.properties.EnumProp;
import rocks.minestom.placement.properties.enums.FacingHopper;

public class HopperBlock {
    public static final BooleanProp ENABLED = new BooleanProp("enabled");
    public static final EnumProp<FacingHopper> FACING = new EnumProp<>("facing", FacingHopper.class);
}
