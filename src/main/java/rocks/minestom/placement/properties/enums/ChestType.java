package rocks.minestom.placement.properties.enums;

/**
 * Original code taken and modified from <a href="https://kloon.io">kloon</a>.
 * Original code licensed under MIT.
 */

public enum ChestType {
    SINGLE,
    LEFT,
    RIGHT,
    ;

    public ChestType opposite() {
        return switch (this) {
            case SINGLE -> SINGLE;
            case LEFT -> RIGHT;
            case RIGHT -> LEFT;
        };
    }
}
