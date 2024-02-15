
package net.mcreator.aquaticdepths.block;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.state.properties.SlabType;
import net.minecraft.loot.LootContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.BlockItem;
import net.minecraft.block.material.Material;
import net.minecraft.block.SoundType;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Block;

import net.mcreator.aquaticdepths.itemgroup.AquaticDepthsItemGroup;
import net.mcreator.aquaticdepths.AquaticDepthsModElements;

import java.util.List;
import java.util.Collections;

@AquaticDepthsModElements.ModElement.Tag
public class StoneBrickSlabBlock extends AquaticDepthsModElements.ModElement {
	@ObjectHolder("aquatic_depths:stone_brick_slab")
	public static final Block block = null;

	public StoneBrickSlabBlock(AquaticDepthsModElements instance) {
		super(instance, 9);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new CustomBlock());
		elements.items
				.add(() -> new BlockItem(block, new Item.Properties().group(AquaticDepthsItemGroup.tab)).setRegistryName(block.getRegistryName()));
	}

	public static class CustomBlock extends SlabBlock {
		public CustomBlock() {
			super(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(9999f, 999999999f).setLightLevel(s -> 0));
			setRegistryName("stone_brick_slab");
		}

		@Override
		public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
			List<ItemStack> dropsOriginal = super.getDrops(state, builder);
			if (!dropsOriginal.isEmpty())
				return dropsOriginal;
			return Collections.singletonList(new ItemStack(this, state.get(TYPE) == SlabType.DOUBLE ? 2 : 1));
		}
	}
}
