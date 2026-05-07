package rocks.minestom.placement.utils;

/**
 * Original code taken and modified from <a href="https://kloon.io">kloon</a>.
 * Original code licensed under MIT.
 */

import org.apache.commons.text.WordUtils;

public final class WordUtilsK {
    private WordUtilsK() {}

    public static <T extends Enum<T>> String enumName(T enumEntry) {
        return WordUtils.capitalize(enumEntry.name().toLowerCase().replace("_", " "));
    }
}
