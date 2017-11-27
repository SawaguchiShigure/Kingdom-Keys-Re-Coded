package uk.co.wehavecookies56.kk.common.core.handler;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import uk.co.wehavecookies56.kk.common.lib.Reference;

/**
 * Created by Toby on 16/07/2017.
 */
@Config(modid = Reference.MODID, name = Reference.MODID + "/Balance")
@Config.LangKey("kk.balance.title")
 public class BalanceConfig {
    @Config.Comment("Set Keyblade Strength and Magic values")
    @Config.LangKey("kk.balance.keyblades")
    public static Keyblades keyblades = new Keyblades();
    public static OrgWeapons orgweapons = new OrgWeapons();

    public static class Keyblades {
        // Keyblade configs
        @Config.RequiresMcRestart
        public int[]
                abaddonPlasma = new int[] { 6, 0 },
                abyssalTide = new int[] { 4, 0 },
                allForOne = new int[] { 6, 6 },
                anguisForetellersKeyblade = new int[] { 10, 8 },
                astralBlast = new int[] { 6, 0 },
                aubade = new int[] { 6, 0 },
                bondofFlame = new int[] { 8, 8 },
                brightcrest = new int[] { 5, 10 },
                chaosRipper = new int[] { 18, -4 },
                circleofLife = new int[] { 8, 2 },
                counterpoint = new int[] { 8, 7 },
                crabclaw = new int[] { 6, 3 },
                crownofGuilt = new int[] { 5, 8 },
                darkerthanDark = new int[] { 6, 9 },
                darkgnaw = new int[] { 7, 1 },
                decisivePumpkin = new int[] { 12, 2 },
                destinysEmbrace = new int[] { 4, 3 },
                diamondDust = new int[] { 3, 10 },
                divewing = new int[] { 8, 8 },
                divineRose = new int[] { 11, 0 },
                dreamStaff = new int[] { 0, 1 },
                dreamSword = new int[] { 1, 0 },
                dualDisc = new int[] { 5, 3 },
                earthshaker = new int[] { 4, 0 },
                endofPain = new int[] { 10, 8 },
                endsoftheEarth = new int[] { 8, 4 },
                fairyHarp = new int[] { 6, 4 },
                fairyStars = new int[] { 2, 2 },
                fatalCrest = new int[] { 6, 10 },
                fenrir = new int[] { 14, 2 },
                ferrisGear = new int[] { 5, 4 },
                followtheWind = new int[] { 6, 2 },
                frolicFlame = new int[] { 4, 4 },
                glimpseofDarkness = new int[] { 6, 0 },
                guardianBell = new int[] { 4, 5 },
                guardianSoul = new int[] { 10, 2 },
                gullWing = new int[] { 4, 6 },
                herosCrest = new int[] { 8, 0 },
                hiddenDragon = new int[] { 4, 4 },
                hyperdrive = new int[] { 5, 4 },
                incompletexblade = new int[] { 10, 5 },
                jungleKing = new int[] { 5, 1 },
                keybladeofPeoplesHearts = new int[] { 11, 5 },
                xblade = new int[] { 25, 15 },
                kingdomKey = new int[] { 3, 0 },
                kingdomKeyD = new int[] { 3, 0 },
                knockoutPunch = new int[] { 8, 5 },
                ladyLuck = new int[] { 6, 6 },
                leasKeyblade = new int[] { 8, 6 },
                leopardosForetellersKeyblade = new int[] { 10, 8 },
                leviathan = new int[] { 4, 0 },
                lionheart = new int[] { 9, 2 },
                lostMemory = new int[] { 10, 7 },
                lunarEclipse = new int[] { 7, 8 },
                markofaHero = new int[] { 7, 0 },
                masterXehanortsKeyblade = new int[] { 13, 6 },
                mastersDefender = new int[] { 12, 10 },
                maverickFlare = new int[] { 6, 0 },
                metalChocobo = new int[] { 8, -2 },
                midnightRoar = new int[] { 6, 0 },
                mirageSplit = new int[] { 8, 5 },
                missingAche = new int[] { 3, 0 },
                monochrome = new int[] { 6, 4 },
                mysteriousAbyss = new int[] { 6, 6 },
                nightmaresEnd = new int[] { 9, 3 },
                combinedKeyblade = new int[] { 20, 9 },
                noName = new int[] { 11, 10 },
                oathkeeper = new int[] { 8, 4 },
                oblivion = new int[] { 10, -1 },
                oceansRage = new int[] { 6, 5 },
                olympia = new int[] { 9, 0 },
                omegaWeapon = new int[] { 7, 3 },
                ominousBlight = new int[] { 4, 0 },
                oneWingedAngel = new int[] { 7, -4 },
                painofSolitude = new int[] { 3, 1 },
                photonDebugger = new int[] { 6, 4 },
                pixiePetal = new int[] { 3, 6 },
                pumpkinhead = new int[] { 7, 0 },
                rainfell = new int[] { 2, 2 },
                rejectionofFate = new int[] { 4, 0 },
                royalRadiance = new int[] { 14, 10 },
                rumblingRose = new int[] { 10, 0 },
                signofInnocence = new int[] { 5, 1 },
                silentDirge = new int[] { 5, 2 },
                skullNoise = new int[] { 4, 4 },
                sleepingLion = new int[] { 10, 6 },
                soulEater = new int[] { 8, 0 },
                spellbinder = new int[] { 4, 8 },
                starSeeker = new int[] { 6, 3 },
                starlight = new int[] { 3, 2 },
                stormfall = new int[] { 7, 6 },
                strokeofMidnight = new int[] { 2, 2 },
                sweetDreams = new int[] { 9, 6 },
                sweetMemories = new int[] { 0, 8 },
                sweetstack = new int[] { 9, 6 },
                threeWishes = new int[] { 6, 2 },
                totalEclipse = new int[] { 5, 0 },
                treasureTrove = new int[] { 3, 2 },
                trueLightsFlight = new int[] { 5, 0 },
                twilightBlaze = new int[] { 6, 0 },
                twoBecomeOne = new int[] { 6, 3 },
                ultimaWeaponKH1 = new int[] { 12, 8 },
                ultimaWeaponKH2 = new int[] { 12, 8 },
                ultimaWeaponBBS = new int[] { 12, 8 },
                ultimaWeaponDDD = new int[] { 12, 8 },
                umbrella = new int[] { 2, 0 },
                unbound = new int[] { 14, 7 },
                unicornisForetellersKeyblade = new int[] { 10, 8 },
                ursusForetellersKeyblade = new int[] { 10, 8 },
                victoryLine = new int[] { 5, 1 },
                voidGear = new int[] { 14, 6 },
                vulpeusForetellersKeyblade = new int[] { 10, 8 },
                waytotheDawn = new int[] { 7, 3 },
                waywardWind = new int[] { 3, 1 },
                winnersProof = new int[] { 10, 14 },
                wishingLamp = new int[] { 8, 6 },
                wishingStar = new int[] { 5, 2 },
                woodenKeyblade = new int[] { 1, 0 },
                woodenStick = new int[] { 0, 0 },
                youngXehanortsKeyblade = new int[] { 13, 8 },
                zeroOne = new int[] { 10, 4 }
                ;
    }
    
    public static class OrgWeapons {
        // Organization Weapons configs
        @Config.RequiresMcRestart
        public double[]
        		//Xemnas
                malice = new double[] { 6, 4 },
                sanction = new double[] { 6, 4 },
                overlord = new double[] { 6, 4 },
                veneration = new double[] { 6, 4 },
                autocracy = new double[] { 6, 4 },
                conquest = new double[] { 6, 4 },
                terminus = new double[] { 6, 4 },
                judment = new double[] { 6, 4 },
                discipline = new double[] { 6, 4 },
                aristocracy = new double[] { 6, 4 },
                superiority = new double[] { 6, 4 },
                aggression = new double[] { 6, 4 },
                fury = new double[] { 6, 4 },
                despair = new double[] { 6, 4 },
                triumph = new double[] { 6, 4 },
                ruination = new double[] { 6, 4 },
                domination = new double[] { 6, 4 },
                annihilation = new double[] { 6, 4 },
                tyrant = new double[] { 6, 4 },
                magnificence = new double[] { 6, 4 },
                infinity = new double[] { 6, 4 },
                interdiction = new double[] { 6, 4 },
                roundfan = new double[] { 6, 4 },
                absolute = new double[] { 6, 4 },
                
                //Xigbar
        		standalone = new double[] { 4, 9 },
        		killerbee = new double[] { 4, 9 },
        		stingray = new double[] { 4, 9 },
        		counterweight = new double[] { 4, 9 },
        		precision = new double[] { 4, 9 },
        		dualhead = new double[] { 4, 9 },
        		bahamut = new double[] { 4, 9 },
        		gullwing = new double[] { 4, 9 },
        		blueframe = new double[] { 4, 9 },
        		starshell = new double[] { 4, 9 },
        		sunrise = new double[] { 4, 9 },
        		ignition = new double[] { 4, 9 },
        		armstrong = new double[] { 4, 9 },
        		hardboiledheat = new double[] { 4, 9 },
        		diabloeye = new double[] { 4, 9 },
        		doubletrap = new double[] { 4, 9 },
        		stardust = new double[] { 4, 9 },
        		energymuzzle = new double[] { 4, 9 },
        		crimeandpunishment = new double[] { 4, 9 },
        		cupidsarrow = new double[] { 4, 9 },
        		finalweapon = new double[] { 4, 9 },
        		sharpshooter = new double[] { 4, 9 },
        		dryer = new double[] { 4, 9 },
        		trumpet = new double[] { 4, 9 },
        
                //Xaldin
        		zephyr = new double[] { 9, 4 },
        		moonglade = new double[] { 9, 4 },
        		aer = new double[] { 9, 4 },
        		nescience = new double[] { 9, 4 },
        		brume = new double[] { 9, 4 },
        		asura = new double[] { 9, 4 },
        		crux = new double[] { 9, 4 },
        		paladin = new double[] { 9, 4 },
        		fellking = new double[] { 9, 4 },
        		nightcloud = new double[] { 9, 4 },
        		shimmer = new double[] { 9, 4 },
        		vortex = new double[] { 9, 4 },
        		scission = new double[] { 9, 4 },
        		heavenfall = new double[] { 9, 4 },
        		aether = new double[] { 9, 4 },
        		mazzaroth = new double[] { 9, 4 },
        		hegemon = new double[] { 9, 4 },
        		foxfire = new double[] { 9, 4 },
        		yaksha = new double[] { 9, 4 },
        		cynosura = new double[] { 9, 4 },
        		dragonreign = new double[] { 9, 4 },
        		lindworm = new double[] { 9, 4 },
        		broom = new double[] { 9, 4 },
        		wyvern = new double[] { 9, 4 },
                
                //Vexen
                //TODO ??? Should have some strength and magic, should we implement in ItemOrgShield the strength and magic stats?
                
                //Lexaeus
                reticence = new double[] { 15, 7 },
                goliath = new double[] { 15, 7 },
                copperred = new double[] { 15, 7 },
                daybreak = new double[] { 15, 7 },
                colossus = new double[] { 15, 7 },
                ursamajor = new double[] { 15, 7 },
                megacosm = new double[] { 15, 7 },
                terrene = new double[] { 15, 7 },
                fuligin = new double[] { 15, 7 },
                hardwinter = new double[] { 15, 7 },
                firefly = new double[] { 15, 7 },
                harbinger = new double[] { 15, 7 },
                redwood = new double[] { 15, 7 },
                sequoia = new double[] { 15, 7 },
                ironblack = new double[] { 15, 7 },
                earthshine = new double[] { 15, 7 },
                octiron = new double[] { 15, 7 },
                hyperion = new double[] { 15, 7 },
                clarity = new double[] { 15, 7 },
                onethousandandonenights = new double[] { 15, 7 },
                cardinalvirtue = new double[] { 15, 7 },
                skysplitter = new double[] { 15, 7 },
                bleepbloopbop = new double[] { 15, 7 },
                monolith = new double[] { 15, 7 },
                
                //Zexion
                blackprimer = new double[] { 5, 10 },
                blackprimer = new double[] { 5, 10 },
                blackprimer = new double[] { 5, 10 },        
                blackprimer = new double[] { 5, 10 },        
                blackprimer = new double[] { 5, 10 },        
                blackprimer = new double[] { 5, 10 },        
                blackprimer = new double[] { 5, 10 },        
                blackprimer = new double[] { 5, 10 },        
                blackprimer = new double[] { 5, 10 },        
                blackprimer = new double[] { 5, 10 },        
                blackprimer = new double[] { 5, 10 },        
                blackprimer = new double[] { 5, 10 },        
                blackprimer = new double[] { 5, 10 },        
                blackprimer = new double[] { 5, 10 },        
                blackprimer = new double[] { 5, 10 },        
                blackprimer = new double[] { 5, 10 },        
                blackprimer = new double[] { 5, 10 },        
                blackprimer = new double[] { 5, 10 },        
                blackprimer = new double[] { 5, 10 },        
                blackprimer = new double[] { 5, 10 },        
                blackprimer = new double[] { 5, 10 },        
                blackprimer = new double[] { 5, 10 },        
                blackprimer = new double[] { 5, 10 },        
                blackprimer = new double[] { 5, 10 },        
                
                //Saix
                newmoon = new double[] { 7, 6 },
                newmoon = new double[] { 7, 6 },
                newmoon = new double[] { 7, 6 },       
                newmoon = new double[] { 7, 6 },       
                newmoon = new double[] { 7, 6 },       
                newmoon = new double[] { 7, 6 },       
                newmoon = new double[] { 7, 6 },       
                newmoon = new double[] { 7, 6 },       
                newmoon = new double[] { 7, 6 },       
                newmoon = new double[] { 7, 6 },       
                newmoon = new double[] { 7, 6 },       
                newmoon = new double[] { 7, 6 },       
                newmoon = new double[] { 7, 6 },       
                newmoon = new double[] { 7, 6 },       
                newmoon = new double[] { 7, 6 },       
                newmoon = new double[] { 7, 6 },       
                newmoon = new double[] { 7, 6 },       
                newmoon = new double[] { 7, 6 },       
                newmoon = new double[] { 7, 6 },       
                newmoon = new double[] { 7, 6 },       
                newmoon = new double[] { 7, 6 },       
                newmoon = new double[] { 7, 6 },       
                newmoon = new double[] { 7, 6 },       
                newmoon = new double[] { 7, 6 },       
                
                //Axel
                ashes = new double[] { 5, 8 },
                ashes = new double[] { 5, 8 },
                ashes = new double[] { 5, 8 },        
                ashes = new double[] { 5, 8 },        
                ashes = new double[] { 5, 8 },        
                ashes = new double[] { 5, 8 },        
                ashes = new double[] { 5, 8 },        
                ashes = new double[] { 5, 8 },        
                ashes = new double[] { 5, 8 },        
                ashes = new double[] { 5, 8 },        
                ashes = new double[] { 5, 8 },        
                ashes = new double[] { 5, 8 },        
                ashes = new double[] { 5, 8 },        
                ashes = new double[] { 5, 8 },        
                ashes = new double[] { 5, 8 },        
                ashes = new double[] { 5, 8 },        
                ashes = new double[] { 5, 8 },        
                ashes = new double[] { 5, 8 },        
                ashes = new double[] { 5, 8 },        
                ashes = new double[] { 5, 8 },        
                ashes = new double[] { 5, 8 },        
                ashes = new double[] { 5, 8 },        
                ashes = new double[] { 5, 8 },        
                ashes = new double[] { 5, 8 },        
                
                //Demyx
                basicmodel = new double[] { 5, 8 },
                basicmodel = new double[] { 5, 8 },
                basicmodel = new double[] { 5, 8 },        
                basicmodel = new double[] { 5, 8 },        
                basicmodel = new double[] { 5, 8 },        
                basicmodel = new double[] { 5, 8 },        
                basicmodel = new double[] { 5, 8 },        
                basicmodel = new double[] { 5, 8 },        
                basicmodel = new double[] { 5, 8 },        
                basicmodel = new double[] { 5, 8 },        
                basicmodel = new double[] { 5, 8 },        
                basicmodel = new double[] { 5, 8 },        
                basicmodel = new double[] { 5, 8 },        
                basicmodel = new double[] { 5, 8 },        
                basicmodel = new double[] { 5, 8 },        
                basicmodel = new double[] { 5, 8 },        
                basicmodel = new double[] { 5, 8 },        
                basicmodel = new double[] { 5, 8 },        
                basicmodel = new double[] { 5, 8 },        
                basicmodel = new double[] { 5, 8 },        
                basicmodel = new double[] { 5, 8 },        
                basicmodel = new double[] { 5, 8 },        
                basicmodel = new double[] { 5, 8 },        
                basicmodel = new double[] { 5, 8 },        
                
                //Luxord
                thefool = new double[] { 6, 7 },
                thefool = new double[] { 6, 7 },
                thefool = new double[] { 6, 7 },        
                thefool = new double[] { 6, 7 },        
                thefool = new double[] { 6, 7 },        
                thefool = new double[] { 6, 7 },        
                thefool = new double[] { 6, 7 },        
                thefool = new double[] { 6, 7 },        
                thefool = new double[] { 6, 7 },        
                thefool = new double[] { 6, 7 },        
                thefool = new double[] { 6, 7 },        
                thefool = new double[] { 6, 7 },        
                thefool = new double[] { 6, 7 },        
                thefool = new double[] { 6, 7 },        
                thefool = new double[] { 6, 7 },        
                thefool = new double[] { 6, 7 },        
                thefool = new double[] { 6, 7 },        
                thefool = new double[] { 6, 7 },        
                thefool = new double[] { 6, 7 },        
                thefool = new double[] { 6, 7 },        
                thefool = new double[] { 6, 7 },        
                thefool = new double[] { 6, 7 },        
                thefool = new double[] { 6, 7 },        
                thefool = new double[] { 6, 7 },        
                
                //Marluxia
                fickleerica = new double[] { 8, 5 },
                fickleerica = new double[] { 8, 5 },
                fickleerica = new double[] { 8, 5 },
                fickleerica = new double[] { 8, 5 },
                fickleerica = new double[] { 8, 5 },
                fickleerica = new double[] { 8, 5 },
                fickleerica = new double[] { 8, 5 },
                fickleerica = new double[] { 8, 5 },
                fickleerica = new double[] { 8, 5 },
                fickleerica = new double[] { 8, 5 },
                fickleerica = new double[] { 8, 5 },
                fickleerica = new double[] { 8, 5 },
                fickleerica = new double[] { 8, 5 },
                fickleerica = new double[] { 8, 5 },
                fickleerica = new double[] { 8, 5 },
                fickleerica = new double[] { 8, 5 },
                fickleerica = new double[] { 8, 5 },
                fickleerica = new double[] { 8, 5 },
                fickleerica = new double[] { 8, 5 },
                fickleerica = new double[] { 8, 5 },
                fickleerica = new double[] { 8, 5 },
                fickleerica = new double[] { 8, 5 },
                fickleerica = new double[] { 8, 5 },
                fickleerica = new double[] { 8, 5 };
                
    }

    @Mod.EventBusSubscriber(modid = Reference.MODID)
    private static class Events {

        @SubscribeEvent
        public static void onConfigChanged(ConfigChangedEvent event) {
            if (event.getModID().equals(Reference.MODID)) {
                ConfigManager.sync(Reference.MODID, Config.Type.INSTANCE);
            }
        }

    }

}
