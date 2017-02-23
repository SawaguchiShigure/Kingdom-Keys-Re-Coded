package uk.co.wehavecookies56.kk.common.network.packet.server;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import uk.co.wehavecookies56.kk.common.achievement.ModAchievements;
import uk.co.wehavecookies56.kk.common.core.helper.AchievementHelper;
import uk.co.wehavecookies56.kk.common.lib.Lists;
import uk.co.wehavecookies56.kk.common.lib.Strings;
import uk.co.wehavecookies56.kk.common.network.packet.AbstractMessage;
import uk.co.wehavecookies56.kk.common.util.Utils;
import java.io.IOException;

/**
 * Created by NStel on 2/19/2017.
 */
public class AbsoluteKnowledgePacket extends AbstractMessage.AbstractServerMessage<AbsoluteKnowledgePacket> {
    public AbsoluteKnowledgePacket () {}

    @Override
    protected void read (PacketBuffer buffer) throws IOException {

    }

    @Override
    protected void write (PacketBuffer buffer) throws IOException {

    }

    @Override
    public void process (EntityPlayer player, Side side) {
        player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
        int rand = Utils.randomWithRange(0, Lists.Lexaeus.size() - 1);
        player.inventory.addItemStackToInventory(new ItemStack(Lists.Lexaeus.get(rand)));
        TextComponentTranslation manifestMessage = new TextComponentTranslation(Strings.Chat_Manifest, new TextComponentTranslation(Lists.Lexaeus.get(rand).getUnlocalizedName() + ".name"));
        manifestMessage.getStyle().setColor(TextFormatting.YELLOW);
        player.sendMessage(manifestMessage);
        AchievementHelper.addAchievement(player, ModAchievements.getOrgWeapon);
    }
}