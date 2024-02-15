
package net.mcreator.aquaticdepths.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.Rarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.block.BlockState;

import net.mcreator.aquaticdepths.itemgroup.AquaticDepthsItemGroup;
import net.mcreator.aquaticdepths.AquaticDepthsModElements;

@AquaticDepthsModElements.ModElement.Tag
public class AbyssaloreItem extends AquaticDepthsModElements.ModElement {
	@ObjectHolder("aquatic_depths:abyssalore")
	public static final Item block = null;

	public AbyssaloreItem(AquaticDepthsModElements instance) {
		super(instance, 2);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}

	public static class ItemCustom extends Item {
		public ItemCustom() {
			super(new Item.Properties().group(AquaticDepthsItemGroup.tab).maxStackSize(64).rarity(Rarity.UNCOMMON));
			setRegistryName("abyssalore");
		}

		@Override
		public int getItemEnchantability() {
			return 0;
		}

		@Override
		public float getDestroySpeed(ItemStack par1ItemStack, BlockState par2Block) {
			return 1F;
		}
	}
}
