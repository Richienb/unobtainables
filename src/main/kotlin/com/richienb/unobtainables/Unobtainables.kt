package com.richienb.unobtainables

import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.client.color.item.ItemColorProvider
import net.minecraft.entity.EntityType
import net.minecraft.entity.vehicle.AbstractMinecartEntity
import net.minecraft.item.*
import net.minecraft.state.property.Properties
import net.minecraft.util.Identifier
import net.minecraft.util.math.Direction
import net.minecraft.util.registry.Registry
import java.util.function.Consumer
import java.util.function.Supplier

@Suppress("UNUSED")
object Unobtainables: ModInitializer {
	private val GIANT_SPAWN_EGG = SpawnEggItem(
		EntityType.GIANT,
		0x00afaf,
		0x799c65,
		Item.Settings().group(ItemGroup.MISC)
	)

	private val ILLUSIONER_SPAWN_EGG = SpawnEggItem(
		EntityType.ILLUSIONER,
		0x959b9b,
		0x1e1c1a,
		Item.Settings().group(ItemGroup.MISC)
	)

	// TODO: Use built-in texture
	private val END_PORTAL = BlockItem(
		Blocks.END_PORTAL,
		Item.Settings()
	)

	// TODO: Use built-in texture
	private val END_GATEWAY = BlockItem(
		Blocks.END_GATEWAY,
		Item.Settings()
	)

	private val FIRE = BlockItem(
		Blocks.FIRE,
		Item.Settings()
	)

	private val SOUL_FIRE = BlockItem(
		Blocks.SOUL_FIRE,
		Item.Settings()
	)

	private val WATER = BlockItem(
		Blocks.WATER,
		Item.Settings()
	)

	private val LAVA = BlockItem(
		Blocks.LAVA,
		Item.Settings()
	)

	private object NetherPortal: BlockItem(
		Blocks.NETHER_PORTAL,
		Settings()
	) {
		override fun getPlacementState(context: ItemPlacementContext?): BlockState? {
			val state = super.getPlacementState(context)

			if (context?.player?.horizontalFacing?.axis == Direction.Axis.X) {
				return state?.with(Properties.HORIZONTAL_AXIS, Direction.Axis.Z)
			}

			return state
		}
	}

	// TODO: Allow the spawned mob to be set
	private val SPAWNER_MINECART = MinecartItem(
		AbstractMinecartEntity.Type.SPAWNER,
		Item.Settings().group(ItemGroup.TRANSPORTATION)
	)

	override fun onInitialize() {
		Registry.register(
			Registry.ITEM,
			Identifier("unobtainables", "giant_spawn_egg"),
			GIANT_SPAWN_EGG
		)
		Registry.register(
			Registry.ITEM,
			Identifier("unobtainables", "illusioner_spawn_egg"),
			ILLUSIONER_SPAWN_EGG
		)
		Registry.register(
			Registry.ITEM,
			Identifier("unobtainables", "spawner_minecart"),
			SPAWNER_MINECART
		)
		Registry.register(
			Registry.ITEM,
			Identifier("unobtainables", "nether_portal"),
			NetherPortal
		)
		Registry.register(
			Registry.ITEM,
			Identifier("unobtainables", "end_portal"),
			END_PORTAL
		)
		Registry.register(
			Registry.ITEM,
			Identifier("unobtainables", "end_gateway"),
			END_GATEWAY
		)
		Registry.register(
			Registry.ITEM,
			Identifier("unobtainables", "fire"),
			FIRE
		)
		Registry.register(
			Registry.ITEM,
			Identifier("unobtainables", "soul_fire"),
			SOUL_FIRE
		)
		Registry.register(
			Registry.ITEM,
			Identifier("unobtainables", "water"),
			WATER
		)
		Registry.register(
			Registry.ITEM,
			Identifier("unobtainables", "lava"),
			LAVA
		)
		ColorProviderRegistry.ITEM.register(
			ItemColorProvider { _, _ -> 0x3f77e5 },
			WATER
		)

		FabricItemGroupBuilder.create(Identifier("unobtainables", "unobtainable"))
			.icon(Supplier { ItemStack(Items.BARRIER) })
			.appendItems(Consumer {
				it.add(ItemStack(Items.DEBUG_STICK))
				it.add(ItemStack(Items.BARRIER))
				it.add(ItemStack(Items.COMMAND_BLOCK))
				it.add(ItemStack(Items.REPEATING_COMMAND_BLOCK))
				it.add(ItemStack(Items.CHAIN_COMMAND_BLOCK))
				// TODO: Allow the command to be set
				it.add(ItemStack(Items.COMMAND_BLOCK_MINECART))
				it.add(ItemStack(Items.STRUCTURE_BLOCK))
				it.add(ItemStack(Items.STRUCTURE_VOID))
				it.add(ItemStack(Items.JIGSAW))
				it.add(ItemStack(Items.SPAWNER))
				it.add(ItemStack(SPAWNER_MINECART))
				it.add(ItemStack(NetherPortal))
				it.add(ItemStack(END_PORTAL))
				it.add(ItemStack(END_GATEWAY))
				it.add(ItemStack(GIANT_SPAWN_EGG))
				it.add(ItemStack(ILLUSIONER_SPAWN_EGG))
				it.add(ItemStack(FIRE))
				it.add(ItemStack(SOUL_FIRE))
				it.add(ItemStack(WATER))
				it.add(ItemStack(LAVA))
				it.add(ItemStack(Items.POTION))
				// TODO: Allow knowledge to be customised
				it.add(ItemStack(Items.KNOWLEDGE_BOOK))
			})
			.build()
	}
}
