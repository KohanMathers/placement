package rocks.minestom.placement.handlers;

/**
 * Original code taken and modified from <a href="https://kloon.io">kloon</a>.
 * Original code licensed under MIT.
 */

import com.google.common.collect.Sets;
import rocks.minestom.placement.properties.BooleanProp;
import rocks.minestom.placement.properties.IntProp;
import net.minestom.server.instance.block.Block;

import java.util.Set;

public class CandleBlock {
    public static final BooleanProp LIT = new BooleanProp("lit");
    public static final IntProp CANDLES = new IntProp("candles", 1, 5);

    public static final Set<Block> BLOCKS = Sets.newHashSet(
            Block.CANDLE,
            Block.WHITE_CANDLE,
            Block.LIGHT_GRAY_CANDLE,
            Block.GRAY_CANDLE,
            Block.BLACK_CANDLE,
            Block.BROWN_CANDLE,
            Block.RED_CANDLE,
            Block.ORANGE_CANDLE,
            Block.YELLOW_CANDLE,
            Block.LIME_CANDLE,
            Block.GREEN_CANDLE,
            Block.CYAN_CANDLE,
            Block.LIGHT_BLUE_CANDLE,
            Block.BLUE_CANDLE,
            Block.PURPLE_CANDLE,
            Block.MAGENTA_CANDLE,
            Block.PINK_CANDLE
    );
}
