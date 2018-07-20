package uk.co.wehavecookies56.kk.common.core.command;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.PlayerNotFoundException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import uk.co.wehavecookies56.kk.client.core.helper.GuiHelper;
import uk.co.wehavecookies56.kk.common.capability.ModCapabilities;
import uk.co.wehavecookies56.kk.common.capability.SummonKeybladeCapability.ISummonKeyblade;
import uk.co.wehavecookies56.kk.common.core.helper.TextHelper;
import uk.co.wehavecookies56.kk.common.item.base.ItemKeyblade;
import uk.co.wehavecookies56.kk.common.lib.GuiIDs;
import uk.co.wehavecookies56.kk.common.network.packet.PacketDispatcher;
import uk.co.wehavecookies56.kk.common.network.packet.server.DeSummonKeyblade;
import uk.co.wehavecookies56.kk.common.network.packet.server.RemoveItemInSlotAndGiveEffect;

public class CommandOpenKeyInv implements ICommand {

    private List<String> aliases;

    public CommandOpenKeyInv () {
        this.aliases = new ArrayList<String>();
        this.aliases.add("kkopenkeyinv");
    }

    @Override
    public int compareTo (ICommand arg0) {
        return 0;
    }

    @Override
    public String getName () {
        return "openkeyinv";
    }

    public int getRequiredPermissionLevel () {
        return 2;
    }

    @Override
    public String getUsage (ICommandSender sender) {
        return "/openkeyinv [player]";
    }

    @Override
    public List<String> getAliases () {
        return this.aliases;
    }

    public static boolean isInteger (String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        } catch (NullPointerException e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean isUsernameIndex (String[] args, int index) {
        return true;
    }

    public static EntityPlayerMP getCommandSenderAsPlayer (ICommandSender sender) throws PlayerNotFoundException {
        if (sender instanceof EntityPlayerMP)
            return (EntityPlayerMP) sender;
        else
            throw new PlayerNotFoundException("You must specify which player you wish to perform this action on.", new Object[0]);
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (sender.getCommandSenderEntity() instanceof EntityPlayer) {
            if (args.length > 1) {
                TextHelper.sendFormattedChatMessage("Invalid arguments, usage: " + getUsage(sender), TextFormatting.RED, (EntityPlayer) sender.getCommandSenderEntity());
            }else {
                EntityPlayer player = (EntityPlayer) sender.getCommandSenderEntity();
                if(args.length == 1) {
	                if(getPlayerFromUsername(args[0]) != null) {
	                	player = getPlayerFromUsername(args[0]);
	                }
            	}
            	ISummonKeyblade KEYBLADE = player.getCapability(ModCapabilities.SUMMON_KEYBLADE, null);
            	
                GuiHelper.openInv(GuiIDs.GUI_KEYCHAIN_INV);

              /*  if (!ItemStack.areItemStacksEqual(KEYBLADE.getInventoryKeychain().getStackInSlot(slot), ItemStack.EMPTY)) {
                    PacketDispatcher.sendToServer(new RemoveItemInSlot("keychain", slot));
                    PacketDispatcher.sendToAllAround(new RemoveItemInSlot("keychain", slot), (EntityPlayer) sender.getCommandSenderEntity(), 1);

                    if (sender.getCommandSenderEntity().getCapability(ModCapabilities.SUMMON_KEYBLADE, null).getIsKeybladeSummoned(EnumHand.MAIN_HAND))
                        if (!ItemStack.areItemStacksEqual(player.getHeldItem(EnumHand.MAIN_HAND), ItemStack.EMPTY) && player.getHeldItem(EnumHand.MAIN_HAND).getItem() instanceof ItemKeyblade)
                            //player.getCapability(ModCapabilities.SUMMON_KEYBLADE, null).setIsKeybladeSummoned(EnumHand.MAIN_HAND, true);
                        PacketDispatcher.sendToServer(new DeSummonKeyblade());
                    	if(args.length == 2)
                    		TextHelper.sendFormattedChatMessage(args[1] + "'s keychain has been removed!", TextFormatting.YELLOW, (EntityPlayer) sender.getCommandSenderEntity());
                    	else
                    		TextHelper.sendFormattedChatMessage("Your keychain has been removed!", TextFormatting.YELLOW, (EntityPlayer) sender.getCommandSenderEntity());
                } else
                    TextHelper.sendFormattedChatMessage("The chain slot has no chain!", TextFormatting.RED, (EntityPlayer) sender.getCommandSenderEntity());
                */
            }
        }
    }

    public static EntityPlayer getPlayerFromUsername(String username) {
        if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT)
            return null;

        return FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayerByUsername(username);
    }
    
    public static boolean isNumber(String num) {
    	try {
        	//Parse the given level
            int number = Integer.parseInt(num);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return sender.canUseCommand(getRequiredPermissionLevel(), getName());

    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos pos) {
        return null;
    }
}
