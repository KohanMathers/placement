package rocks.minestom.placement.utils;

/**
 * Original code taken and modified from <a href="https://kloon.io">kloon</a>.
 * Original code licensed under MIT.
 */

import java.util.Arrays;
import java.util.Comparator;

import net.minestom.server.coordinate.Vec;
import net.minestom.server.instance.block.BlockFace;
import net.minestom.server.utils.Direction;
import static net.minestom.server.utils.Direction.DOWN;
import static net.minestom.server.utils.Direction.EAST;
import static net.minestom.server.utils.Direction.HORIZONTAL;
import static net.minestom.server.utils.Direction.NORTH;
import static net.minestom.server.utils.Direction.SOUTH;
import static net.minestom.server.utils.Direction.UP;
import static net.minestom.server.utils.Direction.WEST;

public final class DirectionUtils {
    public static Direction clockwise(Direction direction) {
        return switch (direction) {
            case NORTH -> EAST;
            case EAST -> SOUTH;
            case SOUTH -> WEST;
            case WEST -> NORTH;
            default -> throw new IllegalStateException("Cannot clockwise rotate " + direction);
        };
    }

    public static Direction counterwise(Direction direction) {
        return switch (direction) {
            case NORTH -> WEST;
            case EAST -> NORTH;
            case SOUTH -> EAST;
            case WEST -> SOUTH;
            default -> throw new IllegalStateException("Cannot rotate " + direction);
        };
    }

    public static BlockFace clockwise(BlockFace face) {
        return switch (face) {
            case NORTH -> BlockFace.EAST;
            case EAST -> BlockFace.SOUTH;
            case SOUTH -> BlockFace.WEST;
            case WEST -> BlockFace.NORTH;
            default -> throw new IllegalStateException("Cannot clockwise rotate " + face);
        };
    }

    public static BlockFace counterwise(BlockFace face) {
        return switch (face) {
            case NORTH -> BlockFace.WEST;
            case EAST -> BlockFace.NORTH;
            case SOUTH -> BlockFace.EAST;
            case WEST -> BlockFace.SOUTH;
            default -> throw new IllegalStateException("Cannot clockwise rotate " + face);
        };
    }

    public static Axis axis(Direction direction) {
        return switch (direction) {
            case WEST, EAST -> Axis.X;
            case DOWN, UP -> Axis.Y;
            case NORTH, SOUTH -> Axis.Z;
        };
    }

    public static Axis axis(BlockFace face) {
        return axis(fromBlockFace(face));
    }

    public static Direction fromBlockFace(BlockFace face) {
        return switch (face) {
            case BOTTOM -> DOWN;
            case TOP -> UP;
            case NORTH -> NORTH;
            case SOUTH -> SOUTH;
            case WEST -> WEST;
            case EAST -> EAST;
        };
    }

    public static Direction closestXZ(Vec vec) {
        Vec normalized = vec.normalize();
        return Arrays.stream(HORIZONTAL)
                .max(Comparator.comparingDouble(dir -> normalized.dot(dir.vec())))
                .get();
    }

    public static Direction closestXYZ(Vec vec) {
        Vec normalized = vec.normalize();
        return Arrays.stream(Direction.values())
                .max(Comparator.comparingDouble(dir -> normalized.dot(dir.vec())))
                .get();
    }
}
