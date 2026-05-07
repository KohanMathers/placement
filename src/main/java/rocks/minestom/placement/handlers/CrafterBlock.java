package rocks.minestom.placement.handlers;

/**
 * Original code taken and modified from <a href="https://kloon.io">kloon</a>.
 * Original code licensed under MIT.
 */

import rocks.minestom.placement.properties.BooleanProp;
import rocks.minestom.placement.properties.EnumProp;
import rocks.minestom.placement.properties.enums.FrontAndTop;

public class CrafterBlock {
    public static final EnumProp<FrontAndTop> ORIENTATION = new EnumProp<>("orientation", FrontAndTop.class);
    public static final BooleanProp CRAFTING = new BooleanProp("crafting");
    public static final BooleanProp TRIGGERED = new BooleanProp("triggered");
}
