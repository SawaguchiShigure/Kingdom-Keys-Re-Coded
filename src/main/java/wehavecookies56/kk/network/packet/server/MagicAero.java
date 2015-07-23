package wehavecookies56.kk.network.packet.server;

import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import wehavecookies56.kk.entities.magic.EntityAero;
import wehavecookies56.kk.network.packet.AbstractMessage.AbstractServerMessage;

public class MagicAero extends AbstractServerMessage<MagicAero> {

	public MagicAero() {}

	@Override
	protected void read(PacketBuffer buffer) throws IOException {

	}

	@Override
	protected void write(PacketBuffer buffer) throws IOException {

	}

	@Override
	public void process(EntityPlayer player, Side side) {
		World world = player.worldObj;
		world.spawnEntityInWorld(new EntityAero(world, player, player.posX, player.posY, player.posZ));
		//MagicAttack.currMagic = MagicAttack.currMagic - MagicAttack.fireCost;
	}

}