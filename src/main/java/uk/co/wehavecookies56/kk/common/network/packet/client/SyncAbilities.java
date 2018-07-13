package uk.co.wehavecookies56.kk.common.network.packet.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import uk.co.wehavecookies56.kk.api.abilities.Ability;
import uk.co.wehavecookies56.kk.api.recipes.RecipeRegistry;
import uk.co.wehavecookies56.kk.common.capability.AbilitiesCapability;
import uk.co.wehavecookies56.kk.common.capability.ModCapabilities;
import uk.co.wehavecookies56.kk.common.capability.SynthesisRecipeCapability;
import uk.co.wehavecookies56.kk.common.capability.TutorialsCapability;
import uk.co.wehavecookies56.kk.common.lib.Reference;
import uk.co.wehavecookies56.kk.common.network.packet.AbstractMessage;

public class SyncAbilities extends AbstractMessage.AbstractClientMessage<SyncAbilities> {

    private List<Ability> abilities;

    public SyncAbilities() {}

    public SyncAbilities(AbilitiesCapability.IAbilities abilities) {
        this.abilities = abilities.getUnlockedAbilities();
       // System.out.println(tutorials);

    }

    @Override
    protected void read(PacketBuffer buffer) throws IOException {
    	abilities = new ArrayList<Ability>();
        while(buffer.isReadable()){
			Ability ability = GameRegistry.findRegistry(Ability.class).getValue(new ResourceLocation(Reference.MODID+":"+buffer.readString(100)));
        	abilities.add(ability);
        }
    	//System.out.println(tutorials);

    }

    @Override
    protected void write(PacketBuffer buffer) throws IOException {
        for (int i = 0; i < abilities.size(); i++){
            buffer.writeString(abilities.get(i).getName());
        }
       // System.out.println(tutorials);
    }

    @Override
    public void process(EntityPlayer player, Side side) {
        final AbilitiesCapability.IAbilities ABILITIES = player.getCapability(ModCapabilities.ABILITIES, null);
        for (int i = 0; i < abilities.size(); i++) {
        	ABILITIES.unlockAbility(abilities.get(i), true);
        }
    }


}
