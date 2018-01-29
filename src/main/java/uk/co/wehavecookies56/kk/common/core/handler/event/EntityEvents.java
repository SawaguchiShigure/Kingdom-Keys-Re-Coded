package uk.co.wehavecookies56.kk.common.core.handler.event;

import java.util.*;

import com.mojang.authlib.GameProfile;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerContainerEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.items.ItemStackHandler;
import uk.co.wehavecookies56.kk.api.driveforms.DriveFormRegistry;
import uk.co.wehavecookies56.kk.api.materials.MaterialRegistry;
import uk.co.wehavecookies56.kk.api.recipes.FreeDevRecipeRegistry;
import uk.co.wehavecookies56.kk.api.recipes.RecipeRegistry;
import uk.co.wehavecookies56.kk.common.KingdomKeys;
import uk.co.wehavecookies56.kk.common.capability.DriveStateCapability;
import uk.co.wehavecookies56.kk.common.capability.FirstTimeJoinCapability;
import uk.co.wehavecookies56.kk.common.capability.MagicStateCapability;
import uk.co.wehavecookies56.kk.common.capability.ModCapabilities;
import uk.co.wehavecookies56.kk.common.capability.MunnyCapability;
import uk.co.wehavecookies56.kk.common.capability.OrganizationXIIICapability;
import uk.co.wehavecookies56.kk.common.capability.OrganizationXIIICapability.IOrganizationXIII;
import uk.co.wehavecookies56.kk.common.capability.PlayerStatsCapability;
import uk.co.wehavecookies56.kk.common.capability.PlayerStatsCapability.IPlayerStats;
import uk.co.wehavecookies56.kk.common.capability.SummonKeybladeCapability;
import uk.co.wehavecookies56.kk.common.capability.SynthesisMaterialCapability;
import uk.co.wehavecookies56.kk.common.capability.SynthesisRecipeCapability;
import uk.co.wehavecookies56.kk.common.container.inventory.InventoryKeychain;
import uk.co.wehavecookies56.kk.common.core.handler.MainConfig;
import uk.co.wehavecookies56.kk.common.core.helper.EntityHelper.MobType;
import uk.co.wehavecookies56.kk.common.entity.magic.DamageCalculation;
import uk.co.wehavecookies56.kk.common.entity.magic.EntityThunder;
import uk.co.wehavecookies56.kk.common.entity.mobs.*;
import uk.co.wehavecookies56.kk.common.item.ModItems;
import uk.co.wehavecookies56.kk.common.item.base.ItemKeyblade;
import uk.co.wehavecookies56.kk.common.item.base.ItemOrgWeapon;
import uk.co.wehavecookies56.kk.common.item.base.ItemRealKeyblade;
import uk.co.wehavecookies56.kk.common.item.org.IOrgWeapon;
import uk.co.wehavecookies56.kk.common.lib.EntityDropEntry;
import uk.co.wehavecookies56.kk.common.lib.Strings;
import uk.co.wehavecookies56.kk.common.network.packet.PacketDispatcher;
import uk.co.wehavecookies56.kk.common.network.packet.client.OpenOrgGUI;
import uk.co.wehavecookies56.kk.common.network.packet.client.SyncDriveData;
import uk.co.wehavecookies56.kk.common.network.packet.client.SyncDriveInventory;
import uk.co.wehavecookies56.kk.common.network.packet.client.SyncHudData;
import uk.co.wehavecookies56.kk.common.network.packet.client.SyncItemsInventory;
import uk.co.wehavecookies56.kk.common.network.packet.client.SyncKeybladeData;
import uk.co.wehavecookies56.kk.common.network.packet.client.SyncLevelData;
import uk.co.wehavecookies56.kk.common.network.packet.client.SyncMagicData;
import uk.co.wehavecookies56.kk.common.network.packet.client.SyncMagicInventory;
import uk.co.wehavecookies56.kk.common.network.packet.client.SyncOrgXIIIData;
import uk.co.wehavecookies56.kk.common.network.packet.server.DeSummonKeyblade;
import uk.co.wehavecookies56.kk.common.network.packet.server.DeSummonOrgWeapon;
import uk.co.wehavecookies56.kk.common.util.Utils;
import uk.co.wehavecookies56.kk.common.world.WorldSavedDataKingdomKeys;
import uk.co.wehavecookies56.kk.common.world.dimension.DimensionTeleporter;
import uk.co.wehavecookies56.kk.common.world.dimension.ModDimensions;
import uk.co.wehavecookies56.kk.common.world.dimension.TeleporterDiveToTheHeart;
import uk.co.wehavecookies56.kk.common.world.dimension.TeleporterOverworld;

/**
 * Created by Toby on 19/07/2016.
 */
public class EntityEvents {

    @SubscribeEvent
    public void PlayerClone (PlayerEvent.Clone event) {
        FirstTimeJoinCapability.IFirstTimeJoin ftjBefore = event.getOriginal().getCapability(ModCapabilities.FIRST_TIME_JOIN, null);
        FirstTimeJoinCapability.IFirstTimeJoin ftjAfter = event.getEntityPlayer().getCapability(ModCapabilities.FIRST_TIME_JOIN, null);
        ftjAfter.setFirstTimeJoin(ftjBefore.getFirstTimeJoin());
        ftjAfter.setPosX(ftjBefore.getPosX());
        ftjAfter.setPosY(ftjBefore.getPosY());
        ftjAfter.setPosZ(ftjBefore.getPosZ());
        MunnyCapability.IMunny munnyBefore = event.getOriginal().getCapability(ModCapabilities.MUNNY, null);
        MunnyCapability.IMunny munnyAfter = event.getEntityPlayer().getCapability(ModCapabilities.MUNNY, null);
        munnyAfter.setMunny(munnyBefore.getMunny());
        DriveStateCapability.IDriveState dsBefore = event.getOriginal().getCapability(ModCapabilities.DRIVE_STATE, null);
        DriveStateCapability.IDriveState dsAfter = event.getEntityPlayer().getCapability(ModCapabilities.DRIVE_STATE, null);
        dsAfter.setActiveDriveName(dsBefore.getActiveDriveName());
        dsAfter.setAntiPoints(dsBefore.getAntiPoints());
        dsAfter.setDriveExp(Strings.Form_Valor, dsBefore.getDriveExp(Strings.Form_Valor));
        dsAfter.setDriveExp(Strings.Form_Wisdom, dsBefore.getDriveExp(Strings.Form_Wisdom));
        dsAfter.setDriveExp(Strings.Form_Limit, dsBefore.getDriveExp(Strings.Form_Limit));
        dsAfter.setDriveExp(Strings.Form_Master, dsBefore.getDriveExp(Strings.Form_Master));
        dsAfter.setDriveExp(Strings.Form_Final, dsBefore.getDriveExp(Strings.Form_Final));
        dsAfter.setDriveLevel(Strings.Form_Valor, dsBefore.getDriveLevel(Strings.Form_Valor));
        dsAfter.setDriveLevel(Strings.Form_Wisdom, dsBefore.getDriveLevel(Strings.Form_Wisdom));
        dsAfter.setDriveLevel(Strings.Form_Limit, dsBefore.getDriveLevel(Strings.Form_Limit));
        dsAfter.setDriveLevel(Strings.Form_Master, dsBefore.getDriveLevel(Strings.Form_Master));
        dsAfter.setDriveLevel(Strings.Form_Final, dsBefore.getDriveLevel(Strings.Form_Final));
        dsAfter.setDriveGaugeLevel(dsBefore.getDriveGaugeLevel());
        dsAfter.setInDrive(dsBefore.getInDrive());
        dsAfter.setDP(dsBefore.getDP());
        dsAfter.setFP(dsBefore.getFP());

        for (int i = 0; i < dsBefore.getInventoryDriveForms().getSlots(); i++) {
            dsAfter.getInventoryDriveForms().setStackInSlot(i, dsBefore.getInventoryDriveForms().getStackInSlot(i));
        }
        MagicStateCapability.IMagicState magicBefore = event.getOriginal().getCapability(ModCapabilities.MAGIC_STATE, null);
        MagicStateCapability.IMagicState magicAfter = event.getEntityPlayer().getCapability(ModCapabilities.MAGIC_STATE, null);
        magicAfter.setKH1Fire(magicBefore.getKH1Fire());
        magicAfter.setMagicLevel(Strings.Spell_Fire, magicBefore.getMagicLevel(Strings.Spell_Fire));
        magicAfter.setMagicLevel(Strings.Spell_Blizzard, magicBefore.getMagicLevel(Strings.Spell_Blizzard));
        magicAfter.setMagicLevel(Strings.Spell_Thunder, magicBefore.getMagicLevel(Strings.Spell_Thunder));
        magicAfter.setMagicLevel(Strings.Spell_Cure, magicBefore.getMagicLevel(Strings.Spell_Cure));
        magicAfter.setMagicLevel(Strings.Spell_Stop, magicBefore.getMagicLevel(Strings.Spell_Stop));
        magicAfter.setMagicLevel(Strings.Spell_Aero, magicBefore.getMagicLevel(Strings.Spell_Aero));
        for (int i = 0; i < magicBefore.getInventorySpells().getSlots(); i++) {
            magicAfter.getInventorySpells().setStackInSlot(i, magicBefore.getInventorySpells().getStackInSlot(i));
        }
        SummonKeybladeCapability.ISummonKeyblade skBefore = event.getOriginal().getCapability(ModCapabilities.SUMMON_KEYBLADE, null);
        SummonKeybladeCapability.ISummonKeyblade skAfter = event.getEntityPlayer().getCapability(ModCapabilities.SUMMON_KEYBLADE, null);
        skAfter.setIsKeybladeSummoned(EnumHand.MAIN_HAND, skBefore.getIsKeybladeSummoned(EnumHand.MAIN_HAND));
        skAfter.setIsKeybladeSummoned(EnumHand.OFF_HAND, skBefore.getIsKeybladeSummoned(EnumHand.OFF_HAND));
        skAfter.setActiveSlot(skBefore.activeSlot());
        for (int i = 0; i < skBefore.getInventoryKeychain().getSlots(); i++) {
            skAfter.getInventoryKeychain().setStackInSlot(i, skBefore.getInventoryKeychain().getStackInSlot(i));
        }
        PlayerStatsCapability.IPlayerStats statsBefore = event.getOriginal().getCapability(ModCapabilities.PLAYER_STATS, null);
        PlayerStatsCapability.IPlayerStats statsAfter = event.getEntityPlayer().getCapability(ModCapabilities.PLAYER_STATS, null);
        statsAfter.setDefense(statsBefore.getDefense());
        statsAfter.setExperience(statsBefore.getExperience());
        statsAfter.setHP(statsBefore.getHP());
        statsAfter.setLevel(statsBefore.getLevel());
        statsAfter.setMagic(statsBefore.getMagic());
        statsAfter.setMaxMP(statsBefore.getMaxMP());
        statsAfter.setMP(statsBefore.getMP());
        statsAfter.setRecharge(statsBefore.getRecharge());
        statsAfter.setStrength(statsBefore.getStrength());
        statsAfter.setEnderDragonDefeated(statsBefore.enderDragonDefeated());
        for (int i = 0; i < statsBefore.getInventoryPotionsMenu().getSlots(); i++) {
            statsAfter.getInventoryPotionsMenu().setStackInSlot(i, statsBefore.getInventoryPotionsMenu().getStackInSlot(i));
        }
        SynthesisRecipeCapability.ISynthesisRecipe recipesBefore = event.getOriginal().getCapability(ModCapabilities.SYNTHESIS_RECIPES, null);
        SynthesisRecipeCapability.ISynthesisRecipe recipesAfter = event.getEntityPlayer().getCapability(ModCapabilities.SYNTHESIS_RECIPES, null);
        for (int i = 0; i < recipesBefore.getKnownRecipes().size(); i++) {
            recipesAfter.learnRecipe(RecipeRegistry.get(recipesBefore.getKnownRecipes().get(i)));
        }
        SynthesisMaterialCapability.ISynthesisMaterial materialsBefore = event.getOriginal().getCapability(ModCapabilities.SYNTHESIS_MATERIALS, null);
        SynthesisMaterialCapability.ISynthesisMaterial materialsAfter = event.getEntityPlayer().getCapability(ModCapabilities.SYNTHESIS_MATERIALS, null);
        Iterator<Map.Entry<String, Integer>> it = materialsBefore.getKnownMaterialsMap().entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Integer> pair = (Map.Entry<String, Integer>) it.next();
            materialsAfter.setMaterial(MaterialRegistry.get(pair.getKey().toString()), pair.getValue());
        }
        OrganizationXIIICapability.IOrganizationXIII orgBefore = event.getOriginal().getCapability(ModCapabilities.ORGANIZATION_XIII, null);
        OrganizationXIIICapability.IOrganizationXIII orgAfter = event.getEntityPlayer().getCapability(ModCapabilities.ORGANIZATION_XIII, null);
        orgAfter.setMember(orgBefore.getMember());
        orgAfter.setCurrentWeapon(orgBefore.currentWeapon());
        orgAfter.setWeaponSummoned(EnumHand.MAIN_HAND, orgBefore.summonedWeapon(EnumHand.MAIN_HAND));
        orgAfter.setWeaponSummoned(EnumHand.OFF_HAND, orgBefore.summonedWeapon(EnumHand.OFF_HAND));
        orgAfter.setPortalX(orgBefore.getPortalX());
        orgAfter.setPortalY(orgBefore.getPortalY());
        orgAfter.setPortalZ(orgBefore.getPortalZ());
        if (event.isWasDeath()) {
            orgAfter.setMember(Utils.OrgMember.NONE);
            orgAfter.setWeaponSummoned(EnumHand.MAIN_HAND, false);
            orgAfter.setWeaponSummoned(EnumHand.OFF_HAND, false);
            orgAfter.setUnlockedWeapons(new ArrayList<Item>());
            orgAfter.setUnlockPoints((int)(statsAfter.getLevel() / 5));
            dsAfter.setActiveDriveName("none");
            dsAfter.setInDrive(false);
            dsAfter.setDP(0);
            dsAfter.setFP(0);
        }
    }

    @SubscribeEvent
    public void playerRespawn(net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent event) {
        if (event.isEndConquered()) {
            BlockPos spawn = new BlockPos(192, 5, 161);
            new DimensionTeleporter(event.player.world.getMinecraftServer().getServer().getWorld(Utils.getDimensionIDAndBlockPos(Strings.TraverseTown).id), Strings.TraverseTown, spawn).teleport((EntityPlayer) event.player);
        }
    }

    public void dropRecipe(LivingDropsEvent event) {
        int recipeRand = Utils.randomWithRange(1, 100);
        if(event.getSource().getTrueSource() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.getSource().getTrueSource();
            ItemStack itemstack = player.inventory.getCurrentItem();
            if (ItemStack.areItemStacksEqual(player.getHeldItemMainhand(), ItemStack.EMPTY)) {
                if (!ItemStack.areItemStacksEqual(player.getHeldItemOffhand(), ItemStack.EMPTY)) {
                    itemstack = player.getHeldItemOffhand();
                }
            }

            switch(getEnchantment(itemstack, 21)) {
                case 1:
                    recipeRand = Utils.randomWithRange(1, 98);
                    break;
                case 2:
                    recipeRand = Utils.randomWithRange(1, 96);
                    break;
                case 3:
                    recipeRand = Utils.randomWithRange(1, 94);
                    break;
            }

            if(recipeRand <= 1) {
                event.getEntityLiving().entityDropItem(new ItemStack(ModItems.Recipe), 1);
            }
        }
    }


    public int getEnchantment(ItemStack stack, int id) {
        for (int i = 0; i < stack.getEnchantmentTagList().tagCount(); i++) {
            if (stack.getEnchantmentTagList().getCompoundTagAt(i).getShort("id") == id) {
                return stack.getEnchantmentTagList().getCompoundTagAt(i).getShort("lvl");
            }
        }
        return 0;
    }

    public static boolean isBoss = false;

    public static boolean isHostiles = false;

    @SubscribeEvent
    public void potentialSpawns(WorldEvent.PotentialSpawns event) {
        if (event.getType() == KingdomKeys.HEARTLESS) {
            if (!WorldSavedDataKingdomKeys.get(DimensionManager.getWorld(DimensionType.OVERWORLD.getId())).spawnHeartless) {
                event.setCanceled(true);
            } else if (event.getWorld().getLight(event.getPos()) > 7 && event.getWorld().isDaytime()) {
                event.setCanceled(true);
            }
        }
    }

    @SubscribeEvent
    public void OnEntityJoinWorld (EntityJoinWorldEvent event) {
        
        if (!event.getEntity().world.isRemote && event.getEntity() instanceof EntityPlayer) {
            FreeDevRecipeRegistry.learnFreeDevRecipe(event.getEntity().getCapability(ModCapabilities.SYNTHESIS_RECIPES, null).getFreeDevRecipes(), (EntityPlayer) event.getEntity(), ModItems.DriveRecovery.getUnlocalizedName());
            FreeDevRecipeRegistry.learnFreeDevRecipe(event.getEntity().getCapability(ModCapabilities.SYNTHESIS_RECIPES, null).getFreeDevRecipes(), (EntityPlayer) event.getEntity(), ModItems.HighDriveRecovery.getUnlocalizedName());
            FreeDevRecipeRegistry.learnFreeDevRecipe(event.getEntity().getCapability(ModCapabilities.SYNTHESIS_RECIPES, null).getFreeDevRecipes(), (EntityPlayer) event.getEntity(), ModItems.MagicBoost.getUnlocalizedName());
            FreeDevRecipeRegistry.learnFreeDevRecipe(event.getEntity().getCapability(ModCapabilities.SYNTHESIS_RECIPES, null).getFreeDevRecipes(), (EntityPlayer) event.getEntity(), ModItems.PowerBoost.getUnlocalizedName());
            FreeDevRecipeRegistry.learnFreeDevRecipe(event.getEntity().getCapability(ModCapabilities.SYNTHESIS_RECIPES, null).getFreeDevRecipes(), (EntityPlayer) event.getEntity(), ModItems.DefenseBoost.getUnlocalizedName());
            FreeDevRecipeRegistry.learnFreeDevRecipe(event.getEntity().getCapability(ModCapabilities.SYNTHESIS_RECIPES, null).getFreeDevRecipes(), (EntityPlayer) event.getEntity(), ModItems.Elixir.getUnlocalizedName());
            FreeDevRecipeRegistry.learnFreeDevRecipe(event.getEntity().getCapability(ModCapabilities.SYNTHESIS_RECIPES, null).getFreeDevRecipes(), (EntityPlayer) event.getEntity(), ModItems.Megalixir.getUnlocalizedName());
            FreeDevRecipeRegistry.learnFreeDevRecipe(event.getEntity().getCapability(ModCapabilities.SYNTHESIS_RECIPES, null).getFreeDevRecipes(), (EntityPlayer) event.getEntity(), ModItems.Ether.getUnlocalizedName());
            FreeDevRecipeRegistry.learnFreeDevRecipe(event.getEntity().getCapability(ModCapabilities.SYNTHESIS_RECIPES, null).getFreeDevRecipes(), (EntityPlayer) event.getEntity(), ModItems.MegaEther.getUnlocalizedName());
            FreeDevRecipeRegistry.learnFreeDevRecipe(event.getEntity().getCapability(ModCapabilities.SYNTHESIS_RECIPES, null).getFreeDevRecipes(), (EntityPlayer) event.getEntity(), ModItems.Potion.getUnlocalizedName());
            FreeDevRecipeRegistry.learnFreeDevRecipe(event.getEntity().getCapability(ModCapabilities.SYNTHESIS_RECIPES, null).getFreeDevRecipes(), (EntityPlayer) event.getEntity(), ModItems.HiPotion.getUnlocalizedName());
            FreeDevRecipeRegistry.learnFreeDevRecipe(event.getEntity().getCapability(ModCapabilities.SYNTHESIS_RECIPES, null).getFreeDevRecipes(), (EntityPlayer) event.getEntity(), ModItems.MegaPotion.getUnlocalizedName());
            FreeDevRecipeRegistry.learnFreeDevRecipe(event.getEntity().getCapability(ModCapabilities.SYNTHESIS_RECIPES, null).getFreeDevRecipes(), (EntityPlayer) event.getEntity(), ModItems.Panacaea.getUnlocalizedName());
            FreeDevRecipeRegistry.learnFreeDevRecipe(event.getEntity().getCapability(ModCapabilities.SYNTHESIS_RECIPES, null).getFreeDevRecipes(), (EntityPlayer) event.getEntity(), Strings.SM_MythrilShard);
            FreeDevRecipeRegistry.learnFreeDevRecipe(event.getEntity().getCapability(ModCapabilities.SYNTHESIS_RECIPES, null).getFreeDevRecipes(), (EntityPlayer) event.getEntity(), Strings.SM_MythrilStone);
            FreeDevRecipeRegistry.learnFreeDevRecipe(event.getEntity().getCapability(ModCapabilities.SYNTHESIS_RECIPES, null).getFreeDevRecipes(), (EntityPlayer) event.getEntity(), Strings.SM_MythrilGem);
            FreeDevRecipeRegistry.learnFreeDevRecipe(event.getEntity().getCapability(ModCapabilities.SYNTHESIS_RECIPES, null).getFreeDevRecipes(), (EntityPlayer) event.getEntity(), Strings.SM_MythrilCrystal);
            FreeDevRecipeRegistry.learnFreeDevRecipe(event.getEntity().getCapability(ModCapabilities.SYNTHESIS_RECIPES, null).getFreeDevRecipes(), (EntityPlayer) event.getEntity(), Strings.SM_ManifestIllusion);
            FreeDevRecipeRegistry.learnFreeDevRecipe(event.getEntity().getCapability(ModCapabilities.SYNTHESIS_RECIPES, null).getFreeDevRecipes(), (EntityPlayer) event.getEntity(), Strings.SM_LostIllusion);

            if (event.getEntity().getCapability(ModCapabilities.SUMMON_KEYBLADE, null).getInventoryKeychain().getSlots() != InventoryKeychain.INV_SIZE) {
                ItemStackHandler oldInv = event.getEntity().getCapability(ModCapabilities.SUMMON_KEYBLADE, null).getInventoryKeychain();
                event.getEntity().getCapability(ModCapabilities.SUMMON_KEYBLADE, null).setInventory(new ItemStackHandler(InventoryKeychain.INV_SIZE));
                ItemStackHandler newInv = event.getEntity().getCapability(ModCapabilities.SUMMON_KEYBLADE, null).getInventoryKeychain();
                for (int i = 0; i < oldInv.getSlots(); i++) {
                    newInv.setStackInSlot(i, oldInv.getStackInSlot(i));
                }
            }

            PacketDispatcher.sendTo(new SyncHudData(event.getEntity().getCapability(ModCapabilities.PLAYER_STATS, null)), (EntityPlayerMP) event.getEntity());
            PacketDispatcher.sendTo(new SyncMagicInventory(event.getEntity().getCapability(ModCapabilities.MAGIC_STATE, null)), (EntityPlayerMP) event.getEntity());
            PacketDispatcher.sendTo(new SyncItemsInventory(event.getEntity().getCapability(ModCapabilities.PLAYER_STATS, null)), (EntityPlayerMP) event.getEntity());
            PacketDispatcher.sendTo(new SyncDriveInventory(event.getEntity().getCapability(ModCapabilities.DRIVE_STATE, null)), (EntityPlayerMP) event.getEntity());
            PacketDispatcher.sendTo(new SyncDriveData(event.getEntity().getCapability(ModCapabilities.DRIVE_STATE, null)), (EntityPlayerMP) event.getEntity());
            PacketDispatcher.sendTo(new SyncMagicData(event.getEntity().getCapability(ModCapabilities.MAGIC_STATE, null), event.getEntity().getCapability(ModCapabilities.PLAYER_STATS, null)), (EntityPlayerMP) event.getEntity());
            PacketDispatcher.sendTo(new SyncKeybladeData(event.getEntity().getCapability(ModCapabilities.SUMMON_KEYBLADE, null)), (EntityPlayerMP) event.getEntity());
            PacketDispatcher.sendTo(new SyncLevelData(event.getEntity().getCapability(ModCapabilities.PLAYER_STATS, null)), (EntityPlayerMP) event.getEntity());
            PacketDispatcher.sendTo(new SyncOrgXIIIData(event.getEntity().getCapability(ModCapabilities.ORGANIZATION_XIII, null)), (EntityPlayerMP) event.getEntity());
            FirstTimeJoinCapability.IFirstTimeJoin FTJ = event.getEntity().getCapability(ModCapabilities.FIRST_TIME_JOIN, null);
            if (!FTJ.getFirstTimeJoin()) {
                ((EntityPlayer) event.getEntity()).inventory.addItemStackToInventory(new ItemStack(ModItems.WoodenKeyblade));
                FTJ.setFirstTimeJoin(true);
                FTJ.setPosX(((EntityPlayer) event.getEntity()).getPosition().getX());
                FTJ.setPosY(((EntityPlayer) event.getEntity()).getPosition().getY());
                FTJ.setPosZ(((EntityPlayer) event.getEntity()).getPosition().getZ());
                if (((EntityPlayer) event.getEntity()).dimension != ModDimensions.diveToTheHeartID && MainConfig.worldgen.EnableStationOfAwakening)
                    if (!event.getWorld().isRemote)
                        new TeleporterDiveToTheHeart(event.getWorld().getMinecraftServer().getServer().getWorld(ModDimensions.diveToTheHeartID)).teleport(((EntityPlayer) event.getEntity()));
            }

            ((EntityPlayer) event.getEntity()).getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(event.getEntity().getCapability(ModCapabilities.PLAYER_STATS, null).getHP());

            if (((EntityPlayer) event.getEntity()).getCapability(ModCapabilities.DRIVE_STATE, null).getDriveGaugeLevel() < 3) {
                ((EntityPlayer) event.getEntity()).getCapability(ModCapabilities.DRIVE_STATE, null).setDriveGaugeLevel(3);
            }

            try {
                if (event.getEntity() instanceof EntityPlayerMP){
                    EntityPlayerMP player = (EntityPlayerMP) event.getEntity();
                    GameProfile profileWehavecookies56 = player.mcServer.getPlayerProfileCache().getGameProfileForUsername("Wehavecookies56");
                    UUID uuidWehavecookies56 = profileWehavecookies56.getId();
                    final MunnyCapability.IMunny munny = ((EntityPlayer) event.getEntity()).getCapability(ModCapabilities.MUNNY, null);
                    if (event.getEntity().getUniqueID() == uuidWehavecookies56) {
                        munny.setMunny(munny.getMunny() + 10000);
                    }
                    GameProfile profileAbelatox = player.mcServer.getPlayerProfileCache().getGameProfileForUsername("Abelatox");
                    UUID uuidAbelatox = profileAbelatox.getId();
                    if (event.getEntity().getUniqueID() == uuidAbelatox) {
                        munny.setMunny(munny.getMunny() + 10000);
                    }
                }
            } catch (Exception e) {

            }
        }
    }

    @SubscribeEvent
    public void onLivingDeathEvent (LivingDeathEvent event) {
        if (!event.getEntity().world.isRemote && event.getEntity() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.getEntity();
            SummonKeybladeCapability.ISummonKeyblade SUMMON = player.getCapability(ModCapabilities.SUMMON_KEYBLADE, null);
            IOrganizationXIII ORG = player.getCapability(ModCapabilities.ORGANIZATION_XIII, null);
            if (SUMMON.getIsKeybladeSummoned(EnumHand.MAIN_HAND)) {
                if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT) {
                    PacketDispatcher.sendToServer(new DeSummonKeyblade());
                    PacketDispatcher.sendTo(new SyncKeybladeData(SUMMON), (EntityPlayerMP) player);
                }else{
                    SUMMON.setIsKeybladeSummoned(EnumHand.MAIN_HAND, false);
                    if (event.getEntity().world.getGameRules().getBoolean("keepInventory")) {
                        for (int i = 0; i < player.inventory.getSizeInventory(); i++) {
                            if (!ItemStack.areItemStacksEqual(player.inventory.getStackInSlot(i), ItemStack.EMPTY)) {
                                if (player.inventory.getStackInSlot(i).getItem() instanceof ItemRealKeyblade) {
                                    player.inventory.setInventorySlotContents(i, ItemStack.EMPTY);
                                }
                            }
                        }
                    }
                }
            }else if (ORG.summonedWeapon(EnumHand.MAIN_HAND)) {
                if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT) {
                    PacketDispatcher.sendToServer(new DeSummonOrgWeapon());
                    PacketDispatcher.sendTo(new SyncOrgXIIIData(ORG), (EntityPlayerMP) player);
                }else{
                    SUMMON.setIsKeybladeSummoned(EnumHand.MAIN_HAND, false);
                    if (event.getEntity().world.getGameRules().getBoolean("keepInventory")) {
                        for (int i = 0; i < player.inventory.getSizeInventory(); i++) {
                            if (!ItemStack.areItemStacksEqual(player.inventory.getStackInSlot(i), ItemStack.EMPTY)) {
                                if (player.inventory.getStackInSlot(i).getItem() instanceof IOrgWeapon) {
                                    player.inventory.setInventorySlotContents(i, ItemStack.EMPTY);
                                }
                            }
                        }
                    }
                }
            }
        }

        if (event.getEntity() instanceof EntityDragon) {
            WorldSavedDataKingdomKeys.get(DimensionManager.getWorld(DimensionType.OVERWORLD.getId())).setSpawnHeartless(true);
        }

        if (!event.getEntity().world.isRemote && event.getEntity() instanceof EntityMob) if (event.getSource().getTrueSource() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.getSource().getTrueSource();

            EntityMob mob = (EntityMob) event.getEntity();

            player.getCapability(ModCapabilities.PLAYER_STATS, null).addExperience(player,(int) (mob.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).getAttributeValue() / 2));
            if (!player.getCapability(ModCapabilities.PLAYER_STATS, null).enderDragonDefeated()) {
                player.getCapability(ModCapabilities.DRIVE_STATE, null).setDriveGaugeLevel(player.getCapability(ModCapabilities.DRIVE_STATE, null).getDriveGaugeLevel()+1);
                player.getCapability(ModCapabilities.DRIVE_STATE, null).setDP(player.getCapability(ModCapabilities.DRIVE_STATE, null).getMaxDP());
                PacketDispatcher.sendTo(new SyncDriveData(player.getCapability(ModCapabilities.DRIVE_STATE, null)), (EntityPlayerMP) player);

                TextComponentTranslation driMessage = new TextComponentTranslation(Strings.Chat_DriveBoost, new TextComponentTranslation(""+player.getCapability(ModCapabilities.DRIVE_STATE, null).getDriveGaugeLevel()));
                driMessage.getStyle().setColor(TextFormatting.GREEN);
                player.sendMessage(driMessage);
                player.getCapability(ModCapabilities.PLAYER_STATS, null).setEnderDragonDefeated(true);
            }

            if(event.getEntity() instanceof EntityDragon) {
                player.getCapability(ModCapabilities.PLAYER_STATS, null).addExperience(player,2000);
            }
            if(event.getEntity() instanceof EntityWither) {
                player.getCapability(ModCapabilities.PLAYER_STATS, null).addExperience(player,1500);
            }
            PacketDispatcher.sendTo(new SyncLevelData(player.getCapability(ModCapabilities.PLAYER_STATS, null)), (EntityPlayerMP) player);
        }
    }

    public static List<EntityDropEntry> entityDrops = new ArrayList<>();
    static {
        //Blazing
        entityDrops.add(new EntityDropEntry(EntityRedNocturne.class, ModItems.blazingShard.createStack(), 20));
        entityDrops.add(new EntityDropEntry(EntityBlaze.class, ModItems.blazingStone.createStack(), 20));
        entityDrops.add(new EntityDropEntry(EntityMagmaCube.class, ModItems.blazingGem.createStack(), 20));
        entityDrops.add(new EntityDropEntry(EntityCrimsonJazz.class, ModItems.blazingCrystal.createStack(), 20));

        //Frost
        entityDrops.add(new EntityDropEntry(EntityPolarBear.class, ModItems.frostShard.createStack(), 20));
        entityDrops.add(new EntityDropEntry(EntitySnowman.class, ModItems.frostStone.createStack(), 20));
        entityDrops.add(new EntityDropEntry(EntityBlueRhapsody.class, ModItems.frostGem.createStack(), 20));
        entityDrops.add(new EntityDropEntry(EntityElderGuardian.class, ModItems.frostCrystal.createStack(), 66));

        //Lightning
        entityDrops.add(new EntityDropEntry(EntityYellowOpera.class, ModItems.lightningShard.createStack(), 20));
        entityDrops.add(new EntityDropEntry(EntityGuardian.class, ModItems.lightningStone.createStack(), 20));
        entityDrops.add(new EntityDropEntry(EntityIllusionIllager.class, ModItems.lightningGem.createStack(), 20));
        entityDrops.add(new EntityDropEntry(EntityEmeraldBlues.class, ModItems.lightningCrystal.createStack(), 20));

        //Lucid
        entityDrops.add(new EntityDropEntry(EntityZombie.class, ModItems.lucidShard.createStack(), 20));
        entityDrops.add(new EntityDropEntry(EntitySkeleton.class, ModItems.lucidStone.createStack(), 20));
        entityDrops.add(new EntityDropEntry(EntitySlime.class, ModItems.lucidGem.createStack(), 20));
        entityDrops.add(new EntityDropEntry(EntityGigaShadow.class, ModItems.lucidCrystal.createStack(), 20));

        //Power
        entityDrops.add(new EntityDropEntry(EntityCreeper.class, ModItems.powerShard.createStack(), 20));
        entityDrops.add(new EntityDropEntry(EntitySilverRock.class, ModItems.powerStone.createStack(), 20));
        entityDrops.add(new EntityDropEntry(EntityGhast.class, ModItems.powerGem.createStack(), 20));
        entityDrops.add(new EntityDropEntry(EntityWither.class, ModItems.powerCrystal.createStack(), 66));

        //Dark
        entityDrops.add(new EntityDropEntry(EntityEndermite.class, ModItems.darkShard.createStack(), 20));
        entityDrops.add(new EntityDropEntry(EntityShadow.class, ModItems.darkStone.createStack(), 20));
        entityDrops.add(new EntityDropEntry(EntityEnderman.class, ModItems.darkGem.createStack(), 20));
        entityDrops.add(new EntityDropEntry(EntityDragon.class, ModItems.darkCrystal.createStack(), 66));

        //Dense
        entityDrops.add(new EntityDropEntry(EntitySilverfish.class, ModItems.denseShard.createStack(), 20));
        entityDrops.add(new EntityDropEntry(EntitySpider.class, ModItems.denseStone.createStack(), 20));
        entityDrops.add(new EntityDropEntry(EntityVex.class, ModItems.denseGem.createStack(), 20));
        entityDrops.add(new EntityDropEntry(EntityEvoker.class, ModItems.denseCrystal.createStack(), 20));

        //Twilight
        entityDrops.add(new EntityDropEntry(EntitySilverfish.class, ModItems.twilightShard.createStack(), 20));
        entityDrops.add(new EntityDropEntry(EntityHusk.class, ModItems.twilightStone.createStack(), 20));
        entityDrops.add(new EntityDropEntry(EntityStray.class, ModItems.twilightGem.createStack(), 20));
        entityDrops.add(new EntityDropEntry(EntityDragon.class, ModItems.twilightCrystal.createStack(), 66));

        //Mythril
        entityDrops.add(new EntityDropEntry(EntityShulker.class, ModItems.mythrilShard.createStack(), 20));
        entityDrops.add(new EntityDropEntry(EntityShulker.class, ModItems.mythrilStone.createStack(), 20));
        entityDrops.add(new EntityDropEntry(EntityShulker.class, ModItems.mythrilGem.createStack(), 20));
        entityDrops.add(new EntityDropEntry(EntityShulker.class, ModItems.mythrilCrystal.createStack(), 20));

        //Stormy
        entityDrops.add(new EntityDropEntry(EntityParrot.class, ModItems.stormyShard.createStack(), 20));
        entityDrops.add(new EntityDropEntry(EntityZombieVillager.class, ModItems.stormyStone.createStack(), 20));
        entityDrops.add(new EntityDropEntry(EntityCreeper.class, ModItems.stormyGem.createStack(), 20));
        entityDrops.add(new EntityDropEntry(EntityWither.class, ModItems.stormyCrystal.createStack(), 66));

        //Remembrance
        entityDrops.add(new EntityDropEntry(EntityHorse.class, ModItems.remembranceShard.createStack(), 20));
        entityDrops.add(new EntityDropEntry(EntityCaveSpider.class, ModItems.remembranceStone.createStack(), 20));
        entityDrops.add(new EntityDropEntry(EntityIronGolem.class, ModItems.remembranceGem.createStack(), 20));
        entityDrops.add(new EntityDropEntry(EntityEnderman.class, ModItems.remembranceCrystal.createStack(), 20));

        //Bright
        entityDrops.add(new EntityDropEntry(EntityLlama.class, ModItems.brightShard.createStack(), 20));
        entityDrops.add(new EntityDropEntry(EntityCaveSpider.class, ModItems.brightStone.createStack(), 20));
        entityDrops.add(new EntityDropEntry(EntityGreenRequiem.class, ModItems.brightGem.createStack(), 20));
        entityDrops.add(new EntityDropEntry(EntityVindicator.class, ModItems.brightCrystal.createStack(), 20));

        //Energy
        entityDrops.add(new EntityDropEntry(EntityCreeper.class, ModItems.energyShard.createStack(), 20));
        entityDrops.add(new EntityDropEntry(EntityGuardian.class, ModItems.energyStone.createStack(), 20));
        entityDrops.add(new EntityDropEntry(EntityWitch.class, ModItems.energyGem.createStack(), 20));
        entityDrops.add(new EntityDropEntry(EntityElderGuardian.class, ModItems.energyCrystal.createStack(), 66));

        //Serenity
        entityDrops.add(new EntityDropEntry(EntityChicken.class, ModItems.serenityShard.createStack(), 20));
        entityDrops.add(new EntityDropEntry(EntityPig.class, ModItems.serenityStone.createStack(), 20));
        entityDrops.add(new EntityDropEntry(EntitySheep.class, ModItems.serenityGem.createStack(), 20));
        entityDrops.add(new EntityDropEntry(EntityCow.class, ModItems.serenityCrystal.createStack(), 20));

        //Orichalcum
        entityDrops.add(new EntityDropEntry(EntityWitherSkeleton.class, ModItems.orichalcum.createStack(), 20));
        entityDrops.add(new EntityDropEntry(EntityIronGolem.class, ModItems.orichalcum.createStack(), 20));
        entityDrops.add(new EntityDropEntry(EntityDragon.class, ModItems.orichalcumPlus.createStack(), 66));
        entityDrops.add(new EntityDropEntry(EntityElderGuardian.class, ModItems.orichalcumPlus.createStack(), 66));
        entityDrops.add(new EntityDropEntry(EntityWither.class, ModItems.orichalcumPlus.createStack(), 66));

        //Spells
        entityDrops.add(new EntityDropEntry(EntityMagmaCube.class, new ItemStack(ModItems.LevelUpMagicFire), 12));
        entityDrops.add(new EntityDropEntry(EntityElderGuardian.class, new ItemStack(ModItems.LevelUpMagicBlizzard), 50));
        entityDrops.add(new EntityDropEntry(EntityIllusionIllager.class, new ItemStack(ModItems.LevelUpMagicThunder), 20));
        entityDrops.add(new EntityDropEntry(EntityDragon.class, new ItemStack(ModItems.LevelUpMagicCure), 25));
        entityDrops.add(new EntityDropEntry(EntityWither.class, new ItemStack(ModItems.LevelUpMagicCure), 25));
        entityDrops.add(new EntityDropEntry(EntityBat.class, new ItemStack(ModItems.LevelUpMagicAero), 5));
        entityDrops.add(new EntityDropEntry(EntityGhast.class, new ItemStack(ModItems.LevelUpMagicAero), 20));

    }

    public void addEntityDrop(LivingDropsEvent event, EntityDropEntry drop) {
        if (!MainConfig.entities.disableDrops) {
            if (drop.dropConditions(event)) {
                event.getEntity().entityDropItem(drop.getItem(), 1);
            }
        }
    }

    @SubscribeEvent
    public void onLivingDrops (LivingDropsEvent event) {
        for (int i = 0; i < entityDrops.size(); i++) {
            if (entityDrops.get(i).getDroppedFrom().isInstance(event.getEntity())) {
                addEntityDrop(event, entityDrops.get(i));
            }
        }
        // Remove any summoned keyblades or org weapons on death
        if (event.getEntity() instanceof EntityPlayer) {
            for (int i = 0; i < event.getDrops().size(); i++) {
                if (event.getDrops().get(i).getItem().getItem() instanceof ItemKeyblade && (event.getDrops().get(i).getItem().getItem() != ModItems.WoodenKeyblade && event.getDrops().get(i).getItem().getItem() != ModItems.WoodenStick)) {
                    event.getDrops().remove(i);

                    event.getEntity().getCapability(ModCapabilities.SUMMON_KEYBLADE, null).setIsKeybladeSummoned(EnumHand.MAIN_HAND, false);
                    i = 0;
                }
                if (event.getDrops().get(i).getItem().getItem() == event.getEntity().getCapability(ModCapabilities.ORGANIZATION_XIII, null).currentWeapon()) {
                    event.getDrops().remove(i);

                    event.getEntity().getCapability(ModCapabilities.ORGANIZATION_XIII, null).setWeaponSummoned(((EntityPlayer) event.getEntity()).getActiveHand(), false);
                    i = 0;
                }
            }
        }
        if (!MainConfig.entities.disableDrops) {
            if (event.getSource().getTrueSource() instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer) event.getSource().getTrueSource();
                if (!ItemStack.areItemStacksEqual(player.getHeldItem(player.getActiveHand()), ItemStack.EMPTY)) {
                    if (player.getHeldItem(player.getActiveHand()).getItem() instanceof ItemKeyblade) {
                        if (event.getEntity() instanceof EntityMob)
                            if (Arrays.asList(MainConfig.entities.dropsList).contains("recipe"))
                                dropRecipe(event);
                    }
                }
                if (!ItemStack.areItemStacksEqual(player.getHeldItem(EnumHand.OFF_HAND), ItemStack.EMPTY)) {
                    if (player.getHeldItem(player.getActiveHand()).getItem() instanceof ItemKeyblade) {
                        if (event.getEntity() instanceof EntityMob)
                            if (Arrays.asList(MainConfig.entities.dropsList).contains("recipe"))
                                dropRecipe(event);
                    }
                }

                if (!ItemStack.areItemStacksEqual(player.getHeldItem(player.getActiveHand()), ItemStack.EMPTY)) {
                    if (player.getHeldItem(player.getActiveHand()).getItem() instanceof ItemRealKeyblade) {
                        if (event.getEntity() instanceof EntityAnimal) {
                            if (Arrays.asList(MainConfig.entities.dropsList).contains("heart"))
                                event.getEntityLiving().entityDropItem(new ItemStack(ModItems.Heart), 2);
                        } else if (event.getEntity() instanceof EntityMob) {
                            if (Arrays.asList(MainConfig.entities.dropsList).contains("darkheart"))
                                event.getEntityLiving().entityDropItem(new ItemStack(ModItems.DarkHeart), 2);
                            if (Arrays.asList(MainConfig.entities.dropsList).contains("spellorb") && event.getEntity() instanceof EntityWitch) {
                                int rand;
                                rand = Utils.randomWithRange(1, 30);
                                if (rand == 1)
                                    event.getEntityLiving().entityDropItem(new ItemStack(ModItems.LevelUpMagicFire), 1);
                                else if (rand == 5)
                                    event.getEntityLiving().entityDropItem(new ItemStack(ModItems.LevelUpMagicBlizzard), 1);
                                else if (rand == 9)
                                    event.getEntityLiving().entityDropItem(new ItemStack(ModItems.LevelUpMagicThunder), 1);
                                else if (rand == 13)
                                    event.getEntityLiving().entityDropItem(new ItemStack(ModItems.LevelUpMagicCure), 1);
                                else if (rand == 17) {
                                    // event.getEntityLiving().entityDropItem(new ItemStack(ModItems.LevelUpMagicGravity), 1);
                                } else if (rand == 21)
                                    event.getEntityLiving().entityDropItem(new ItemStack(ModItems.LevelUpMagicAero), 1);
                                else if (rand == 25)
                                    event.getEntityLiving().entityDropItem(new ItemStack(ModItems.LevelUpMagicStop), 1);
                            }
                        } else if (event.getEntity() instanceof EntityAgeable)
                            if (Arrays.asList(MainConfig.entities.dropsList).contains("pureheart"))
                                event.getEntityLiving().entityDropItem(new ItemStack(ModItems.PureHeart), 2);
                        if (event.getEntity() instanceof EntityDragon || event.getEntity() instanceof EntityWither) {
                            if (Arrays.asList(MainConfig.entities.dropsList).contains("kingdomhearts"))
                                event.getEntityLiving().entityDropItem(new ItemStack(ModItems.KingdomHearts), 1);
                            if (Arrays.asList(MainConfig.entities.dropsList).contains("recipe"))
                                event.getEntityLiving().entityDropItem(new ItemStack(ModItems.Recipe), 1);
                        }
                    }
                }

                if (!ItemStack.areItemStacksEqual(player.getHeldItem(EnumHand.OFF_HAND), ItemStack.EMPTY)) {
                    if (player.getHeldItem(EnumHand.OFF_HAND).getItem() instanceof ItemRealKeyblade) {
                        if (event.getEntity() instanceof EntityAnimal)
                            if (Arrays.asList(MainConfig.entities.dropsList).contains("heart"))
                                event.getEntityLiving().entityDropItem(new ItemStack(ModItems.Heart), 2);
                        else if (event.getEntity() instanceof EntityMob) {
                            if (Arrays.asList(MainConfig.entities.dropsList).contains("darkheart"))
                                event.getEntityLiving().entityDropItem(new ItemStack(ModItems.DarkHeart), 2);
                            if (Arrays.asList(MainConfig.entities.dropsList).contains("spellorb") && event.getEntity() instanceof EntityWitch) {
                                int rand;
                                rand = Utils.randomWithRange(1, 30);
                                if (rand == 1)
                                    event.getEntityLiving().entityDropItem(new ItemStack(ModItems.LevelUpMagicFire), 1);
                                else if (rand == 5)
                                    event.getEntityLiving().entityDropItem(new ItemStack(ModItems.LevelUpMagicBlizzard), 1);
                                else if (rand == 9)
                                    event.getEntityLiving().entityDropItem(new ItemStack(ModItems.LevelUpMagicThunder), 1);
                                else if (rand == 13)
                                    event.getEntityLiving().entityDropItem(new ItemStack(ModItems.LevelUpMagicCure), 1);
                                else if (rand == 17) {
                                    // event.getEntityLiving().entityDropItem(new ItemStack(ModItems.LevelUpMagicGravity), 1);
                                } else if (rand == 21)
                                    event.getEntityLiving().entityDropItem(new ItemStack(ModItems.LevelUpMagicAero), 1);
                                else if (rand == 25)
                                    event.getEntityLiving().entityDropItem(new ItemStack(ModItems.LevelUpMagicStop), 1);
                            }
                        } else if (event.getEntity() instanceof EntityAgeable)
                            if (Arrays.asList(MainConfig.entities.dropsList).contains("pureheart"))
                                event.getEntityLiving().entityDropItem(new ItemStack(ModItems.PureHeart), 2);
                        if (event.getEntity() instanceof EntityDragon || event.getEntity() instanceof EntityWither) {
                            if(Arrays.asList(MainConfig.entities.dropsList).contains("kingdomhearts"))
                                event.getEntityLiving().entityDropItem(new ItemStack(ModItems.KingdomHearts), 1);
                            if(Arrays.asList(MainConfig.entities.dropsList).contains("recipe"))
                                event.getEntityLiving().entityDropItem(new ItemStack(ModItems.Recipe), 1);
                        }
                    }
                }


                ItemStack munny = new ItemStack(ModItems.Munny, 1);
                munny.setTagCompound(new NBTTagCompound());
                ItemStack driveOrb = new ItemStack(ModItems.DriveOrb, 1);
                driveOrb.setTagCompound(new NBTTagCompound());
                ItemStack magicOrb = new ItemStack(ModItems.MagicOrb, 1);
                magicOrb.setTagCompound(new NBTTagCompound());
                ItemStack HPOrb = new ItemStack(ModItems.HpOrb, 1);

                if (event.getEntity() instanceof EntityAnimal) {
                    munny.getTagCompound().setInteger("amount", Utils.randomWithRange(1, 20));
                    if (Arrays.asList(MainConfig.entities.dropsList).contains("munny"))
                        event.getEntityLiving().entityDropItem(munny, 1);
                    driveOrb.getTagCompound().setInteger("amount", 1);
                    if (Arrays.asList(MainConfig.entities.dropsList).contains("dporb"))
                        event.getEntityLiving().entityDropItem(driveOrb, 1);
                    magicOrb.getTagCompound().setInteger("amount", Utils.randomWithRange(2, 8));
                    if (Arrays.asList(MainConfig.entities.dropsList).contains("mporb"))
                        event.getEntityLiving().entityDropItem(magicOrb, 1);
                    if (Arrays.asList(MainConfig.entities.dropsList).contains("hporb"))
                        event.getEntityLiving().entityDropItem(HPOrb, 1);
                } else if (event.getEntity() instanceof EntityMob) {
                    munny.getTagCompound().setInteger("amount", Utils.randomWithRange(5, 50));
                    if (Arrays.asList(MainConfig.entities.dropsList).contains("munny"))
                        event.getEntityLiving().entityDropItem(munny, 1);
                    driveOrb.getTagCompound().setInteger("amount", 5);
                    if (Arrays.asList(MainConfig.entities.dropsList).contains("dporb"))
                        event.getEntityLiving().entityDropItem(driveOrb, 1);
                    magicOrb.getTagCompound().setInteger("amount", Utils.randomWithRange(5, 15));
                    if (Arrays.asList(MainConfig.entities.dropsList).contains("mporb"))
                        event.getEntityLiving().entityDropItem(magicOrb, 1);
                    if (Arrays.asList(MainConfig.entities.dropsList).contains("hporb"))
                        event.getEntityLiving().entityDropItem(HPOrb, 1);

                } else if (event.getEntity() instanceof EntityAgeable) {
                    munny.getTagCompound().setInteger("amount", Utils.randomWithRange(50, 100));
                    if (Arrays.asList(MainConfig.entities.dropsList).contains("munny"))
                        event.getEntityLiving().entityDropItem(munny, 1);
                    driveOrb.getTagCompound().setInteger("amount", 5);
                    if (Arrays.asList(MainConfig.entities.dropsList).contains("dporb"))
                        event.getEntityLiving().entityDropItem(driveOrb, 1);
                    magicOrb.getTagCompound().setInteger("amount", Utils.randomWithRange(10, 25));
                    if (Arrays.asList(MainConfig.entities.dropsList).contains("mporb"))
                        event.getEntityLiving().entityDropItem(magicOrb, 1);
                    if (Arrays.asList(MainConfig.entities.dropsList).contains("hporb"))
                        event.getEntityLiving().entityDropItem(HPOrb, 1);
                } else if (event.getEntity() instanceof EntityDragon || event.getEntity() instanceof EntityWither) {
                    munny.getTagCompound().setInteger("amount", Utils.randomWithRange(500, 1000));
                    if (Arrays.asList(MainConfig.entities.dropsList).contains("munny"))
                        event.getEntityLiving().entityDropItem(munny, 1);
                    driveOrb.getTagCompound().setInteger("amount", Utils.randomWithRange(200, 250));
                    if (Arrays.asList(MainConfig.entities.dropsList).contains("dporb"))
                        event.getEntityLiving().entityDropItem(driveOrb, 1);
                    magicOrb.getTagCompound().setInteger("amount", Utils.randomWithRange(100, 140));
                    if (Arrays.asList(MainConfig.entities.dropsList).contains("mporb"))
                        event.getEntityLiving().entityDropItem(magicOrb, 1);
                    if (Arrays.asList(MainConfig.entities.dropsList).contains("hporb"))
                        event.getEntityLiving().entityDropItem(HPOrb, 1);
                }
            }
        }
    }

    @SubscribeEvent
    public void onPlayerTick (TickEvent.PlayerTickEvent event) {
        EntityPlayer player = event.player;
        if (player.getCapability(ModCapabilities.ORGANIZATION_XIII, null).getMember() == Utils.OrgMember.NONE) {
            if (!ItemStack.areItemStacksEqual(player.inventory.armorInventory.get(0), ItemStack.EMPTY) && player.inventory.armorInventory.get(1) != ItemStack.EMPTY && player.inventory.armorInventory.get(2) != ItemStack.EMPTY && player.inventory.armorInventory.get(3) != ItemStack.EMPTY) {
            	boolean isWearingOrgArmor = player.inventory.armorInventory.get(0).getItem() == ModItems.OrganizationRobe_Boots && player.inventory.armorInventory.get(1).getItem() == ModItems.OrganizationRobe_Leggings && player.inventory.armorInventory.get(2).getItem() == ModItems.OrganizationRobe_Chestplate && player.inventory.armorInventory.get(3).getItem() == ModItems.OrganizationRobe_Helmet;
            	boolean isWearingXemnasArmor = player.inventory.armorInventory.get(0).getItem() == ModItems.Xemnas_Boots && player.inventory.armorInventory.get(1).getItem() == ModItems.Xemnas_Leggings && player.inventory.armorInventory.get(2).getItem() == ModItems.Xemnas_Chestplate && player.inventory.armorInventory.get(3).getItem() == ModItems.Xemnas_Helmet;
                if (isWearingOrgArmor || isWearingXemnasArmor) {
                    if (!player.world.isRemote) {

                        if (!player.getCapability(ModCapabilities.ORGANIZATION_XIII, null).getOpenedGUI()) {
                            player.getCapability(ModCapabilities.ORGANIZATION_XIII, null).setOpenedGUI(true);
                            PacketDispatcher.sendTo(new OpenOrgGUI(), (EntityPlayerMP) player);
                        }
                    }
                }
            } else {
                if (player.getCapability(ModCapabilities.ORGANIZATION_XIII, null).getMember() == Utils.OrgMember.NONE)
                    player.getCapability(ModCapabilities.ORGANIZATION_XIII, null).setOpenedGUI(false);
            }
        }
        
        //Choices
        IPlayerStats STATS = player.getCapability(ModCapabilities.PLAYER_STATS, null);
        if (!event.player.world.isRemote) {
            if (player.dimension == ModDimensions.diveToTheHeartID) {
                if (player.getPosition().getX() == -13 && player.getPosition().getZ() == -1 && player.getPosition().getY() == 66) {
                    if (!STATS.getChoice1().equals(Strings.Choice_Shield)) {
                        STATS.setChoice1(Strings.Choice_Shield);
                        TextComponentTranslation shield = new TextComponentTranslation("Shield");
                        shield.getStyle().setColor(TextFormatting.YELLOW);
                        player.sendMessage(shield);
                    }
                } else if (player.getPosition().getX() == 11 && player.getPosition().getZ() == -1 && player.getPosition().getY() == 66) {
                    if (!STATS.getChoice1().equals(Strings.Choice_Staff)) {
                        STATS.setChoice1(Strings.Choice_Staff);
                        TextComponentTranslation staff = new TextComponentTranslation("Staff");
                        staff.getStyle().setColor(TextFormatting.YELLOW);
                        player.sendMessage(staff);
                    }
                } else if (player.getPosition().getX() == -1 && player.getPosition().getZ() == -13 && player.getPosition().getY() == 66) {
                    if (!STATS.getChoice1().equals(Strings.Choice_Sword)) {
                        STATS.setChoice1(Strings.Choice_Sword);
                        TextComponentTranslation sword = new TextComponentTranslation("Sword");
                        sword.getStyle().setColor(TextFormatting.YELLOW);
                        player.sendMessage(sword);
                    }
                } else if (player.getPosition().getX() == -1 && player.getPosition().getZ() == +10 && player.getPosition().getY() == 65) {
                    if (((EntityPlayer) player).dimension == ModDimensions.diveToTheHeartID) {
                        if (!STATS.getChoice1().equals("") && !STATS.getChoice1().equals("door")) {
                            //if (!player.world.isRemote) {
                            switch (STATS.getChoice1()) {
                                case Strings.Choice_Shield:
                                    STATS.addDefense(2);
                                    break;
                                case Strings.Choice_Staff:
                                    STATS.addMagic(2);
                                    break;
                                case Strings.Choice_Sword:
                                    STATS.addStrength(2);
                                    break;
                            }
                            PacketDispatcher.sendTo(new SyncLevelData(STATS), (EntityPlayerMP) player);
                            new TeleporterOverworld(event.player.world.getMinecraftServer().getServer().getWorld(0)).teleport((player), player.world);
                            //}
                        } else {
                            if (!STATS.getChoice1().equals("door")) {
                                TextComponentTranslation needChoice = new TextComponentTranslation("You must make a choice");
                                needChoice.getStyle().setColor(TextFormatting.RED);
                                player.sendMessage(needChoice);
                                STATS.setChoice1("door");
                            }
                        }
                    }
                }
            } else if (player.dimension == ModDimensions.traverseTownID) {
                if (player.getPosition().getX() == 193 && player.getPosition().getZ() == 161 && player.getPosition().getY() == 6) {
                    new TeleporterOverworld(event.player.world.getMinecraftServer().getServer().getWorld(0)).teleport((player), player.world);
                }
            }
        }
        //PacketDispatcher.sendTo(new SyncLevelData(event.player.getCapability(ModCapabilities.PLAYER_STATS, null)), (EntityPlayerMP)event.player);


        DriveStateCapability.IDriveState DS = event.player.getCapability(ModCapabilities.DRIVE_STATE, null);
        if (!DS.getInDrive())
            if (STATS.getMP() <= 0 || STATS.getRecharge()) {
                STATS.setRecharge(true);
                if (STATS.getMP() != STATS.getMaxMP()) {
                    STATS.addMP(0.1);
                    if (STATS.getMP() > STATS.getMaxMP())
                        STATS.setMP(STATS.getMaxMP());

                } else {
                    STATS.setMP(STATS.getMaxMP());
                    STATS.setRecharge(false);
                    if(event.side.isServer()) {
                        PacketDispatcher.sendTo(new SyncMagicData(event.player.getCapability(ModCapabilities.MAGIC_STATE, null), event.player.getCapability(ModCapabilities.PLAYER_STATS, null)), (EntityPlayerMP)event.player);
                    }
                }
            }
        if (!DS.getActiveDriveName().equals("none") && DriveFormRegistry.isDriveFormRegistered(DS.getActiveDriveName())) {
            DriveFormRegistry.get(DS.getActiveDriveName()).update(event.player);
        }
        List<Entity> entities = event.player.world.getEntitiesWithinAABBExcludingEntity(event.player, event.player.getEntityBoundingBox().grow(16.0D, 10.0D, 16.0D).offset(-8.0D, -5.0D, -8.0D));
        List<Entity> bossEntities = event.player.world.getEntitiesWithinAABBExcludingEntity(event.player, event.player.getEntityBoundingBox().grow(150.0D, 100.0D, 150.0D).offset(-75.0D, -50.0D, -75.0D));
        if (!bossEntities.isEmpty()) {
            for (int i = 0; i < bossEntities.size(); i++) {
                if (bossEntities.get(i) instanceof EntityDragon || bossEntities.get(i) instanceof EntityWither) {
                    isBoss = true;
                    break;
                } else {
                    isBoss = false;
                }
            }
        } else {
            isBoss = false;
        }
        if (!entities.isEmpty()) {
            for (int i = 0; i < entities.size(); i++) {
                if (entities.get(i) instanceof EntityMob) {
                    isHostiles = true;
                    break;
                } else {
                    isHostiles = false;
                }
            }
        } else {
            isHostiles = false;
        }

    }
   /* @SubscribeEvent
    public void onLivingUpdate (LivingEvent.LivingUpdateEvent event) {
        if (event.getEntityLiving() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.getEntityLiving();

            if(player.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue() == 0 && player.isAirBorne){
                player.motionX=0;
                player.motionY=0;
                player.motionZ=0;
            }
        }
    }*/

    @SubscribeEvent
    public void onHurt (LivingHurtEvent event) {
        if (event.getEntityLiving() instanceof EntityPlayer) {
            PlayerStatsCapability.IPlayerStats STATS = event.getEntity().getCapability(ModCapabilities.PLAYER_STATS, null);
            EntityPlayer player = (EntityPlayer) event.getEntityLiving();
            if (event.getAmount() - STATS.getDefense() <= 0)
                event.setAmount(1);
            else
                event.setAmount((float)( event.getAmount() - (STATS.getDefense()*0.25)));
            if (event.getSource().getDamageType().equals("lightningBolt"))
                if (EntityThunder.summonLightning)
                    event.setCanceled(true);
        }
        if (event.getSource().getTrueSource() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.getSource().getTrueSource();
            player.getCapability(ModCapabilities.DRIVE_STATE, null).addDP(1);
            PacketDispatcher.sendTo(new SyncDriveData(player.getCapability(ModCapabilities.DRIVE_STATE, null)), (EntityPlayerMP) player);
        }
        if(event.getEntityLiving() instanceof IKHMob) {
            EntityPlayer player = null;
            IKHMob khMob = (IKHMob) event.getEntityLiving();
            if (event.getSource().getImmediateSource() instanceof EntityPlayer) {
                player = (EntityPlayer) event.getSource().getImmediateSource();
            }
            if(player != null) {
                if(khMob.getType() == MobType.HEARTLESS_EMBLEM || khMob.getType() == MobType.HEARTLESS_PUREBLOOD || khMob.getType() == MobType.NOBODY) {
                    /*if(ItemStack.areItemStacksEqual(player.getHeldItem(player.getActiveHand()), ItemStack.EMPTY) && ) {//Main empty but offhand is damagable
                        event.setCanceled(true);
                    } else {*/
                		//If the player has a real weapon in any slot
                        if(!(player.getHeldItemMainhand().getItem() instanceof ItemKeyblade || player.getHeldItemMainhand().getItem() instanceof IOrgWeapon ||
                        	 player.getHeldItemOffhand().getItem() instanceof ItemKeyblade || player.getHeldItemOffhand().getItem() instanceof IOrgWeapon)) {
                            event.setCanceled(true);
                        }
                    //}
                }
            }
        }
        if (event.getSource().getImmediateSource() instanceof EntityPlayer && !event.getSource().damageType.equals(EnumHand.OFF_HAND.name())) {
            EntityPlayer player = (EntityPlayer) event.getSource().getImmediateSource();
            if(event.getSource().getDamageType().equals("thorns")) return;

            if(!ItemStack.areItemStacksEqual(player.getHeldItem(player.getActiveHand()), ItemStack.EMPTY)) {
                if(player.getHeldItem(player.getActiveHand()).getItem() instanceof ItemKeyblade || player.getHeldItem(player.getActiveHand()).getItem() instanceof IOrgWeapon) {
                    event.setAmount(event.getAmount()-4 + DamageCalculation.getStrengthDamage(player));
                }
            }
        }
    }

    @SubscribeEvent
    public void onFall (LivingFallEvent event) {
        if (event.getEntityLiving() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.getEntityLiving();
            DriveStateCapability.IDriveState DS = player.getCapability(ModCapabilities.DRIVE_STATE, null);
            if (DS.getInDrive())
                event.setDistance(0);
            
//            if(player.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue() == 0){
//                player.motionY=-0.001;
//            }
        }
    }

    @SubscribeEvent
    public void container(PlayerContainerEvent.Open event) {
        if (!event.getEntityPlayer().getCapability(ModCapabilities.DRIVE_STATE, null).getActiveDriveName().equals("none")) {
            event.getEntityPlayer().closeScreen();
        }
    }

    @SubscribeEvent
    public void interactWithEntity(PlayerInteractEvent.EntityInteract event) {
        if (!event.getEntityPlayer().getCapability(ModCapabilities.DRIVE_STATE, null).getActiveDriveName().equals("none")) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void interactWithBlock(PlayerInteractEvent.RightClickBlock event) {
        if (!event.getEntityPlayer().getCapability(ModCapabilities.DRIVE_STATE, null).getActiveDriveName().equals("none") || (event.getEntityPlayer().dimension == ModDimensions.diveToTheHeartID && !event.getEntityPlayer().capabilities.isCreativeMode)) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void onJump (LivingJumpEvent event) {
        if (event.getEntityLiving() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.getEntityLiving();
            if(player.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue() == 0){
                player.motionY=-0.0001;
            }
        }
    }

}
