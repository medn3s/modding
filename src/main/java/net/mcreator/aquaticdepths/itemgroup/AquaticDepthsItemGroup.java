
package net.mcreator.aquaticdepths.itemgroup;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;

import net.mcreator.aquaticdepths.AquaticDepthsModElements;

@AquaticDepthsModElements.ModElement.Tag
public class AquaticDepthsItemGroup extends AquaticDepthsModElements.ModElement {
	public AquaticDepthsItemGroup(AquaticDepthsModElements instance) {
		super(instance, 2);
	}

	@Override
	public void initElements() {
		tab = new ItemGroup("tabaquatic_depths") {
			@OnlyIn(Dist.CLIENT)
			@Override
			public ItemStack createIcon() {
				return new ItemStack(Items.BLUE_DYE);
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return false;
			}
		};
	}

	public static ItemGroup tab;
}
