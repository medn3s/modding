package net.mcreator.aquaticdepths.procedures;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.IWorld;
import net.minecraft.util.DamageSource;
import net.minecraft.potion.Effects;
import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.aquaticdepths.AquaticDepthsModVariables;
import net.mcreator.aquaticdepths.AquaticDepthsMod;

import java.util.Map;
import java.util.Collection;

public class WitherblockEntityWalksOnTheBlockProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				AquaticDepthsMod.LOGGER.warn("Failed to load dependency world for procedure WitherblockEntityWalksOnTheBlock!");
			return;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				AquaticDepthsMod.LOGGER.warn("Failed to load dependency entity for procedure WitherblockEntityWalksOnTheBlock!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		Entity entity = (Entity) dependencies.get("entity");
		if (AquaticDepthsModVariables.MapVariables.get(world).WitherPlayerEffect == false) {
			if (true) {
				AquaticDepthsModVariables.MapVariables.get(world).WitherPlayerEffect = (true);
				AquaticDepthsModVariables.MapVariables.get(world).syncData(world);
				if (entity instanceof LivingEntity) {
					((LivingEntity) entity).attackEntityFrom(new DamageSource("WitheredBricks").setDamageBypassesArmor(), (float) (new Object() {
						int check(Entity _entity) {
							if (_entity instanceof LivingEntity) {
								Collection<EffectInstance> effects = ((LivingEntity) _entity).getActivePotionEffects();
								for (EffectInstance effect : effects) {
									if (effect.getPotion() == Effects.WITHER)
										return effect.getAmplifier();
								}
							}
							return 0;
						}
					}.check(entity)));
				}
				new Object() {
					private int ticks = 0;
					private float waitTicks;
					private IWorld world;

					public void start(IWorld world, int waitTicks) {
						this.waitTicks = waitTicks;
						MinecraftForge.EVENT_BUS.register(this);
						this.world = world;
					}

					@SubscribeEvent
					public void tick(TickEvent.ServerTickEvent event) {
						if (event.phase == TickEvent.Phase.END) {
							this.ticks += 1;
							if (this.ticks >= this.waitTicks)
								run();
						}
					}

					private void run() {
						AquaticDepthsModVariables.MapVariables.get(world).WitherPlayerEffect = (false);
						AquaticDepthsModVariables.MapVariables.get(world).syncData(world);
						MinecraftForge.EVENT_BUS.unregister(this);
					}
				}.start(world, (int) 20);
			}
		}
	}
}
