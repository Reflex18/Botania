/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * 
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * 
 * File Created @ [Mar 29, 2015, 10:16:29 PM (GMT)]
 */
package vazkii.botania.common.item.relic;

import java.util.List;
import java.util.UUID;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.util.ChunkCoordinates;
import net.minecraftforge.oredict.RecipeSorter;
import net.minecraftforge.oredict.RecipeSorter.Category;
import vazkii.botania.api.item.IWireframeCoordinateListProvider;
import vazkii.botania.common.achievement.ICraftAchievement;
import vazkii.botania.common.achievement.ModAchievements;
import vazkii.botania.common.crafting.recipe.AesirRingRecipe;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.lib.LibItemNames;
import baubles.api.BaubleType;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import cpw.mods.fml.common.registry.GameRegistry;

public class ItemAesirRing extends ItemRelicBauble implements IWireframeCoordinateListProvider, ICraftAchievement {

	Multimap<String, AttributeModifier> attributes = HashMultimap.create();

	public ItemAesirRing() {
		super(LibItemNames.AESIR_RING);
		fillModifiers(attributes);
		GameRegistry.addRecipe(new AesirRingRecipe());
		RecipeSorter.register("botania:aesirRing", AesirRingRecipe.class, Category.SHAPELESS, "");
	}

	@Override
	public void onValidPlayerWornTick(ItemStack stack, EntityPlayer player) {
		((ItemOdinRing) ModItems.odinRing).onValidPlayerWornTick(stack, player);
	}

	@Override
	public BaubleType getBaubleType(ItemStack arg0) {
		return BaubleType.RING;
	}

	@Override
	public List<ChunkCoordinates> getWireframesToDraw(EntityPlayer player, ItemStack stack) {
		return ((IWireframeCoordinateListProvider) ModItems.lokiRing).getWireframesToDraw(player, stack);
	}

	@Override
	public void onEquippedOrLoadedIntoWorld(ItemStack stack, EntityLivingBase player) {
		player.getAttributeMap().applyAttributeModifiers(attributes);
	}

	@Override
	public void onUnequipped(ItemStack stack, EntityLivingBase player) {
		player.getAttributeMap().removeAttributeModifiers(attributes);
	}

	void fillModifiers(Multimap<String, AttributeModifier> attributes) {
		attributes.put(SharedMonsterAttributes.maxHealth.getAttributeUnlocalizedName(), new AttributeModifier(new UUID(2756708 /** Random number **/, 43873), "Bauble modifier", 20, 0));
	}

	@Override
	public Achievement getAchievementOnCraft(ItemStack stack, EntityPlayer player, IInventory matrix) {
		return ModAchievements.relicAesirRing;
	}

}
