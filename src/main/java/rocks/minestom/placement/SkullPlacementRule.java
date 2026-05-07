package rocks.minestom.placement;

/**
 * Original code taken and modified from <a href="https://kloon.io">kloon</a>.
 * Original code licensed under MIT.
 */

import org.jetbrains.annotations.NotNull;

import net.kyori.adventure.nbt.BinaryTag;
import net.kyori.adventure.nbt.CompoundBinaryTag;
import net.minestom.server.codec.Transcoder;
import net.minestom.server.component.DataComponents;
import net.minestom.server.instance.block.Block;
import net.minestom.server.instance.block.BlockFace;
import net.minestom.server.instance.block.rule.BlockPlacementRule;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;
import net.minestom.server.network.player.ResolvableProfile;
import rocks.minestom.placement.handlers.SkullBlock;
import rocks.minestom.placement.nbt.NBT;
import rocks.minestom.placement.properties.BannerRotProp;
import rocks.minestom.placement.properties.enums.FacingXZ;

public final class SkullPlacementRule extends BlockPlacementRule {
    public SkullPlacementRule(@NotNull Block block) {
        super(block);
    }

    @Override
    public Block blockPlace(@NotNull PlacementState placementState) {
        BlockFace face = placementState.blockFace();
        if (face == null) {
            return placementState.block();
        }

        float yaw = placementState.playerPosition().yaw();

        Block block;
        if (face == BlockFace.TOP || face == BlockFace.BOTTOM) {
            int segment = BannerRotProp.convertToSegment(yaw);
            block = SkullBlock.GROUND_ROTATION.get(segment).on(placementState);
        } else {
            FacingXZ facing = FacingXZ.fromBlockFace(face);
            Block wallBlock = SkullBlock.BLOCK_TO_WALL.get(placementState.block().defaultState());
            block = SkullBlock.WALL_FACING.get(facing).on(wallBlock);
        }

        ItemStack itemStack = placementState.usedItemStack();
        if (itemStack != null && itemStack.material() == Material.PLAYER_HEAD && block.id() == Block.PLAYER_HEAD.id()) {
            ResolvableProfile profile = itemStack.get(DataComponents.PROFILE);
            if (profile != null) {
                BinaryTag profileNbt = ResolvableProfile.CODEC.encode(Transcoder.NBT, profile).orElseThrow();
                CompoundBinaryTag nbt = NBT.compound(c -> {
                    c.put("profile", profileNbt);
                });
                block = block.withNbt(nbt);
            }
        }

        return block;
    }
}
