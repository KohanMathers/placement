package rocks.minestom.placement;

import java.util.ArrayList;

import org.jetbrains.annotations.NotNull;

import net.kyori.adventure.nbt.CompoundBinaryTag;
import net.minestom.server.MinecraftServer;
import net.minestom.server.coordinate.Point;
import net.minestom.server.event.player.PlayerBlockBreakEvent;
import net.minestom.server.instance.Instance;
import net.minestom.server.instance.block.Block;
import net.minestom.server.instance.block.rule.BlockPlacementRule;
import rocks.minestom.placement.handlers.ChestBlock;
import rocks.minestom.placement.nbt.NBT;
import rocks.minestom.placement.properties.enums.ChestType;
import rocks.minestom.placement.properties.enums.FacingXZ;
import rocks.minestom.placement.utils.PlacementUtils;

/**
 * Original code taken and modified from <a href="https://kloon.io">kloon</a>.
 * Original code licensed under MIT.
 */

public final class ChestPlacementRule extends BlockPlacementRule {
    public ChestPlacementRule(@NotNull Block block) {
        super(block);
    }

    static {
        MinecraftServer.getGlobalEventHandler().addListener(PlayerBlockBreakEvent.class, event -> {
            Block block = event.getBlock();
            Block blockType = block.defaultState();
            if (!ChestBlock.BLOCKS.contains(blockType)) {
                return;
            }

            FacingXZ facing = ChestBlock.FACING_XZ.get(block);
            Point blockPos = event.getBlockPosition();
            Instance instance = event.getInstance();

            Point attachedPos = null;

            Point leftPos = blockPos.relative(facing.rotateClockwise().toBlockFace());
            Point rightPos = blockPos.relative(facing.rotateCounterClockwise().toBlockFace());
            if (isAttached(instance, block, facing, leftPos)) {
                attachedPos = leftPos;
            } else if (isAttached(instance, block, facing, rightPos)) {
                attachedPos = rightPos;
            }

            if (attachedPos != null) {
                Block attachedBlock = instance.getBlock(attachedPos);
                attachedBlock = ChestBlock.TYPE.get(ChestType.SINGLE).on(attachedBlock);
                instance.setBlock(attachedPos, attachedBlock, false);
            }
        });
    }

    @Override
    public Block blockPlace(@NotNull PlacementState placementState) {
        Block block = PlacementUtils.waterlogged(placementState);

        ChestType chestType = ChestType.SINGLE;
        Point blockPos = placementState.placePosition();
        FacingXZ facing = FacingXZ.fromLook(placementState.playerPosition()).opposite();
        boolean sneaking = placementState.isPlayerShifting();

        Point attachedPos = null;
        if (!sneaking) {
            Point leftPos = blockPos.relative(facing.rotateClockwise().toBlockFace());
            Point rightPos = blockPos.relative(facing.rotateCounterClockwise().toBlockFace());
            if (canAttach(placementState.instance(), block, facing, leftPos)) {
                attachedPos = leftPos;
                chestType = ChestType.LEFT;
            } else if (canAttach(placementState.instance(), block, facing, rightPos)) {
                attachedPos = rightPos;
                chestType = ChestType.RIGHT;
            }
        }

        block = ChestBlock.FACING_XZ.get(facing).on(block);
        block = ChestBlock.TYPE.get(chestType).on(block);

        if (attachedPos != null && placementState.instance() instanceof Instance instance) {
            Block attachedBlock = instance.getBlock(attachedPos);
            attachedBlock = ChestBlock.TYPE.get(chestType.opposite()).on(attachedBlock);
            instance.setBlock(attachedPos, attachedBlock, false);
        }

        CompoundBinaryTag nbt = NBT.compound(c -> {
            c.putCompoundList("Items", new ArrayList<>());
        });

        return block.withNbt(nbt);
    }

    private static boolean canAttach(Block.Getter instance, Block chestBlock, FacingXZ facing, Point blockPos) {
        Block checking = instance.getBlock(blockPos);

        if (!isSameChestId(chestBlock, checking)) {
            return false;
        }

        ChestType type = ChestBlock.TYPE.get(checking);
        if (type != ChestType.SINGLE) {
            return false;
        }

        return facing == ChestBlock.FACING_XZ.get(checking);
    }

    private static boolean isAttached(Instance instance, Block chestBlock, FacingXZ facing, Point blockPos) {
        Block checking = instance.getBlock(blockPos);

        if (!isSameChestId(chestBlock, checking)) {
            return false;
        }

        FacingXZ checkingFace = ChestBlock.FACING_XZ.get(checking);
        if (checkingFace != facing) {
            return false;
        }

        ChestType chestType = ChestBlock.TYPE.get(chestBlock);
        ChestType checkingType = ChestBlock.TYPE.get(checking);
        return checkingType != ChestType.SINGLE && chestType == checkingType.opposite();
    }

    private static boolean isSameChestId(Block a, Block b) {
        Block aType = a.defaultState();
        Block bType = b.defaultState();
        return aType == bType;
    }
}
