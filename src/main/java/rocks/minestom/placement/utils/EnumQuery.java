package rocks.minestom.placement.utils;

/**
 * Original code taken and modified from <a href="https://kloon.io">kloon</a>.
 * Original code licensed under MIT.
 */

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public final class EnumQuery<K, E> {
    private final Map<K, E> map;

    public EnumQuery(E[] values, Function<E, K> keyExtractor) {
        this.map = HashMap.newHashMap(values.length);
        for (E value : values) {
            map.put(keyExtractor.apply(value), value);
        }
    }

    public E get(K key) {
        return map.get(key);
    }
}
