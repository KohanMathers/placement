package rocks.minestom.placement.handlers;

/**
 * Original code taken and modified from <a href="https://kloon.io">kloon</a>.
 * Original code licensed under MIT.
 */

import rocks.minestom.placement.utils.BlockFamily;
import rocks.minestom.placement.properties.BooleanProp;
import rocks.minestom.placement.properties.EnumProp;
import rocks.minestom.placement.properties.enums.WallHeight;
import net.minestom.server.instance.block.Block;
import net.minestom.server.utils.Direction;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class WallBlock {
    public static final EnumProp<WallHeight> EAST = new EnumProp<>("east", WallHeight.class);
    public static final EnumProp<WallHeight> NORTH = new EnumProp<>("north", WallHeight.class);
    public static final EnumProp<WallHeight> SOUTH = new EnumProp<>("south", WallHeight.class);
    public static final EnumProp<WallHeight> WEST = new EnumProp<>("west", WallHeight.class);
    public static final BooleanProp UP = new BooleanProp("up");
    public static final BooleanProp WATERLOGGED = WaterBlock.WATERLOGGED;

    public static final Set<Block> BLOCKS = BlockFamily.getBlocksOfVariant(BlockFamily.Variant.WALL);
    public static final List<Direction> DIRECTIONALS = Arrays.asList(Direction.NORTH, Direction.SOUTH, Direction.WEST, Direction.EAST);

    public static EnumProp<WallHeight> getDirectional(Direction direction) {
        return switch(direction) {
            case NORTH -> NORTH;
            case SOUTH -> SOUTH;
            case WEST -> WEST;
            case EAST -> EAST;
            default -> throw new IllegalStateException("Unsupported direction " + direction);
        };
    }

    public static WallHeight getDirectional(Direction direction, Block block) {
        EnumProp<WallHeight> prop = getDirectional(direction);
        return prop.get(block);
    }
}
