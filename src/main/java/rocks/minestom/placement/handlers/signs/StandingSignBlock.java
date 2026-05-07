package rocks.minestom.placement.handlers.signs;

/**
 * Original code taken and modified from <a href="https://kloon.io">kloon</a>.
 * Original code licensed under MIT.
 */

import rocks.minestom.placement.handlers.WaterBlock;
import rocks.minestom.placement.properties.BooleanProp;
import rocks.minestom.placement.properties.IntProp;

public class StandingSignBlock {
    public static final IntProp ROTATION = new IntProp("rotation", 16);
    public static final BooleanProp WATERLOGGED = WaterBlock.WATERLOGGED;
}
