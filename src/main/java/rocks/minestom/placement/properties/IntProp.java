package rocks.minestom.placement.properties;

/**
 * Original code taken and modified from <a href="https://kloon.io">kloon</a>.
 * Original code licensed under MIT.
 */

import net.minestom.server.instance.block.Block;

public class IntProp {
    private final String key;
    private final int minIncluded;
    private final int maxExcluded;

    private final BlockProp[] props;

    public IntProp(String key, int maxExcluded) {
        this(key, 0, maxExcluded);
    }

    public IntProp(String key, int minIncluded, int maxExcluded) {
        this.key = key;
        this.minIncluded = minIncluded;
        this.maxExcluded = maxExcluded;

        this.props = new BlockProp[maxExcluded];
        for (int i = minIncluded; i < maxExcluded; ++i) {
            props[i] = new BlockProp(key, String.valueOf(i));
        }
    }

    public int getMinIncluded() {
        return minIncluded;
    }

    public int getMaxExcluded() {
        return maxExcluded;
    }

    public int getMaxIncluded() {
        return maxExcluded - 1;
    }

    public boolean has(Block block) {
        return block.properties().containsKey(key);
    }

    public BlockProp get(int value) {
        value = Math.clamp(value, minIncluded, maxExcluded - 1);
        return props[value];
    }

    public int get(Block block) {
        String property = block.getProperty(key);
        if (property == null) {
            return minIncluded;
        }
        int parsedInt = Integer.parseInt(property);
        return Math.clamp(parsedInt, minIncluded, maxExcluded);
    }

    public int getOrZero(Block block) {
        String property = block.getProperty(key);
        if (property == null) {
            return 0;
        }
        int parsedInt = Integer.parseInt(property);
        return Math.clamp(parsedInt, minIncluded, maxExcluded);
    }
}
