package rocks.minestom.placement;

/**
 * Original code taken and modified from <a href="https://kloon.io">kloon</a>.
 * Original code licensed under MIT.
 */

import rocks.minestom.placement.nbt.NBT;
import net.kyori.adventure.nbt.CompoundBinaryTag;
import net.minestom.server.instance.block.Block;
import net.minestom.server.instance.block.rule.BlockPlacementRule;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public final class DecoratedPotPlacementRule extends BlockPlacementRule {
    public DecoratedPotPlacementRule(@NotNull Block block) {
        super(block);
    }

    @Override
    public Block blockPlace(@NotNull PlacementState placementState) {
        Block block = new LooksFacingXZPlacementRule(Block.DECORATED_POT).waterlogged().opposite().blockPlace(placementState);

        CompoundBinaryTag nbt = NBT.compound(c -> {
            c.putCompoundList("minecraft:container", new ArrayList<>());
            c.putStringList("minecraft:pot_decorations", new ArrayList<>());
        });

        return block.withNbt(nbt);
    }
}
