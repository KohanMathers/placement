package rocks.minestom.placement.handlers;

/**
 * Original code taken and modified from <a href="https://kloon.io">kloon</a>.
 * Original code licensed under MIT.
 */

import com.google.common.collect.Sets;
import rocks.minestom.placement.properties.BooleanProp;
import net.minestom.server.instance.block.Block;

import java.util.Set;

public class LanternBlock {
    public static final BooleanProp HANGING = new BooleanProp("hanging");
    public static final BooleanProp WATERLOGGED = WaterBlock.WATERLOGGED;

    public static final Set<Block> BLOCKS = Sets.newHashSet(Block.LANTERN, Block.SOUL_LANTERN);
}
