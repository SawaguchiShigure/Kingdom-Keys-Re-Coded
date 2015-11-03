package wehavecookies56.kk.item;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import wehavecookies56.kk.lib.Strings;
import wehavecookies56.kk.network.packet.PacketDispatcher;
import wehavecookies56.kk.network.packet.server.magics.LevelUpMagic;
import wehavecookies56.kk.util.TextHelper;

public class ItemUpgradeCure extends Item
{
	public ItemUpgradeCure(){
		this.setMaxStackSize(1);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player){
		if(world.isRemote){
			PacketDispatcher.sendToServer(new LevelUpMagic("Cure"));
		}
		return stack;
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List tooltip, boolean advanced) {
		tooltip.add(TextHelper.localize(Strings.Gui_CommandMenu_Magic_Cure));
		super.addInformation(stack, playerIn, tooltip, advanced);
	}
}