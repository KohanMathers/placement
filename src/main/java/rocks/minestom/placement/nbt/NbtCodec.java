package rocks.minestom.placement.nbt;

/**
 * Original code taken and modified from <a href="https://kloon.io">kloon</a>.
 * Original code licensed under MIT.
 */

import net.kyori.adventure.nbt.CompoundBinaryTag;
import org.jetbrains.annotations.Nullable;

public interface NbtCodec<T> {
    void encode(T obj, KloonCompoundBuilder c);

    T decode (@Nullable CompoundBinaryTag c);
}
