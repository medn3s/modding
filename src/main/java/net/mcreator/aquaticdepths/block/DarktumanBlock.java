
package net.mcreator.aquaticdepths.block;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.IBlockReader;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.loot.LootContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.BlockItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.Minecraft;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.SoundType;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.Blocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Block;

import net.mcreator.aquaticdepths.itemgroup.AquaticDepthsItemGroup;
import net.mcreator.aquaticdepths.AquaticDepthsModElements;

import java.util.List;
import java.util.Collections;

@AquaticDepthsModElements.ModElement.Tag
public class DarktumanBlock extends AquaticDepthsModElements.ModElement {
	@ObjectHolder("aquatic_depths:darktuman")
	public static final Block block = null;

	public DarktumanBlock(AquaticDepthsModElements instance) {
		super(instance, 5);
		FMLJavaModLoadingContext.get().getModEventBus().register(new BlockColorRegisterHandler());
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new CustomBlock());
		elements.items
				.add(() -> new BlockItem(block, new Item.Properties().group(AquaticDepthsItemGroup.tab)).setRegistryName(block.getRegistryName()));
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void clientLoad(FMLClientSetupEvent event) {
		RenderTypeLookup.setRenderLayer(block, RenderType.getCutoutMipped());
	}

	private static class BlockColorRegisterHandler {
		@OnlyIn(Dist.CLIENT)
		@SubscribeEvent
		public void blockColorLoad(ColorHandlerEvent.Block event) {
			event.getBlockColors().register((bs, world, pos, index) -> {
				return world != null && pos != null ? Minecraft.getInstance().world.getBiome(pos).getSkyColor() : 8562943;
			}, block);
		}
	}

	public static class CustomBlock extends SlabBlock {
		public CustomBlock() {
			super(Block.Properties.create(Material.ROCK, MaterialColor.AIR).sound(SoundType.GROUND).hardnessAndResistance(-1, 3600000)
					.setLightLevel(s -> 0).doesNotBlockMovement().slipperiness(0.1f).speedFactor(0f).jumpFactor(0f).notSolid()
					.setOpaque((bs, br, bp) -> false));
			setRegistryName("darktuman");
		}

		@Override
		public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player) {
			return new ItemStack(Blocks.VOID_AIR);
		}

		@Override
		public MaterialColor getMaterialColor() {
			return MaterialColor.AIR;
		}

		@Override
		public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
			List<ItemStack> dropsOriginal = super.getDrops(state, builder);
			if (!dropsOriginal.isEmpty())
				return dropsOriginal;
			return Collections.singletonList(new ItemStack(this, 0));
		}
	}
}
