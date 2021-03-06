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
	
	@Config.Comment("Set Organization Weapon Strength and Magic values")
	@Config.LangKey("kk.balance.orgweapons")
	public static OrgWeapons orgweapons = new OrgWeapons();

    @Config.Comment("Set Other Weapon's Strength and Magic values")
    @Config.LangKey("kk.balance.other")
    public static Other other = new Other();

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
				sanction = new double[] { 7, 4 },
				overlord = new double[] { 7, 5 },
				veneration = new double[] { 8, 5 },
				autocracy = new double[] { 8, 6 },
				conquest = new double[] { 8, 7 },
				terminus = new double[] { 9, 7 },
				judment = new double[] { 10, 7 },
				discipline = new double[] { 10, 8 },
				aristocracy = new double[] { 10, 9 },
				superiority = new double[] { 11, 9 },
				aggression = new double[] { 12, 9 },
				fury = new double[] { 12, 10 },
				despair = new double[] { 13, 10 },
				triumph = new double[] { 13, 11 },
				ruination = new double[] { 13, 12 },
				domination = new double[] { 14, 12 },
				annihilation = new double[] { 14, 13 },
				tyrant = new double[] { 15, 13 },
				magnificence = new double[] { 16, 13 },
				infinity = new double[] { 16, 14 },
				interdiction = new double[] { 17, 14 },
				roundfan = new double[] { 6, 4 },
				absolute = new double[] { 6, 4 },

				//Xigbar
				standalone = new double[] { 4, 9 },
				killerbee = new double[] { 5, 9 },
				stingray = new double[] { 5, 10 },
				counterweight = new double[] { 6, 10 },
				precision = new double[] { 6, 11 },
				dualhead = new double[] { 6, 12 },
				bahamut = new double[] { 7, 12 },
				gullwing = new double[] { 8, 12 },
				blueframe = new double[] { 8, 13 },
				starshell = new double[] { 8, 14 },
				sunrise = new double[] { 9, 14 },
				ignition = new double[] { 10, 14 },
				armstrong = new double[] { 10, 15 },
				hardboiledheat = new double[] { 11, 15 },
				diabloeye = new double[] { 11, 16 },
				doubletap = new double[] { 11, 17 },
				stardust = new double[] { 12, 17 },
				energymuzzle = new double[] { 12, 18 },
				crimeandpunishment = new double[] { 13, 18 },
				cupidsarrow = new double[] { 14, 18 },
				finalweapon = new double[] { 14, 19 },
				sharpshooter = new double[] { 15, 19 },
				dryer = new double[] { 4, 9 },
				trumpet = new double[] { 4, 9 },

				//Xaldin
				zephyr = new double[] { 9, 4 },
				moonglade = new double[] { 10, 4 },
				aer = new double[] { 10, 5 },
				nescience = new double[] { 11, 5 },
				brume = new double[] { 11, 6 },
				asura = new double[] { 11, 7 },
				crux = new double[] { 12, 7 },
				paladin = new double[] { 13, 7 },
				fellking = new double[] { 13, 8 },
				nightcloud = new double[] { 13, 9 },
				shimmer = new double[] { 14, 9 },
				vortex = new double[] { 15, 9 },
				scission = new double[] { 15, 10 },
				heavenfall = new double[] { 16, 10 },
				aether = new double[] { 16, 11 },
				mazzaroth = new double[] { 16, 12 },
				hegemon = new double[] { 17, 12 },
				foxfire = new double[] { 17, 13 },
				yaksha = new double[] { 18, 13 },
				cynosura = new double[] { 19, 13 },
				dragonreign = new double[] { 19, 14 },
				lindworm = new double[] { 20, 14 },
				broom = new double[] { 9, 4 },
				wyvern = new double[] { 9, 4 },

				//Vexen
				testerzero = new double[] { 5, 10 },
				productone = new double[] { 6, 10 },
				deepfreeze = new double[] { 6, 11 },
				cryoliteshield = new double[] { 7, 11 },
				falsetheory = new double[] { 7, 12 },
				glacier = new double[] { 7, 13 },
				absolutezero = new double[] { 8, 13 },
                gunz = new double[] { 9, 13 },
                mindel = new double[] { 9, 14 },
                snowslide = new double[] { 9, 15 },
                iceberg = new double[] { 10, 15 },
                inquisition = new double[] { 11, 15 },
                scrutiny = new double[] { 11, 16 },
                empiricism = new double[] { 12, 16 },
                edification = new double[] { 12, 17 },
                contrivance = new double[] { 12, 18 },
                wurm = new double[] { 13, 18 },
                subzero = new double[] { 13, 19 },
                coldblood = new double[] { 14, 19 },
                diamondshield = new double[] { 15, 19 },
                aegis = new double[] { 15, 20 },
                frozenpride = new double[] { 16, 20 },
				potlid = new double[] { 5, 10 },
				snowman = new double[] { 5, 10 },

				//Lexaeus
				reticence = new double[] { 10, 2 },
				goliath = new double[] { 11, 2 },
				copperred = new double[] { 11, 3 },
				daybreak = new double[] { 12, 3 },
				colossus = new double[] { 12, 4 },
				ursamajor = new double[] { 12, 5 },
				megacosm = new double[] { 13, 5 },
				terrene = new double[] { 14, 5 },
				fuligin = new double[] { 14, 6 },
				hardwinter = new double[] { 14, 7 },
				firefly = new double[] { 15, 7 },
				harbinger = new double[] { 16, 7 },
				redwood = new double[] { 16, 8 },
				sequoia = new double[] { 17, 8 },
				ironblack = new double[] { 17, 9 },
				earthshine = new double[] { 17, 10 },
				octiron = new double[] { 18, 10 },
				hyperion = new double[] { 18, 11 },
				clarity = new double[] { 19, 11 },
				onethousandandonenights = new double[] { 20, 11 },
				cardinalvirtue = new double[] { 20, 12 },
				skysplitter = new double[] { 21, 12 },
				bleepbloopbop = new double[] { 10, 2 },
				monolith = new double[] { 10, 2 },


				//Zexion
				blackprimer = new double[] { 5, 10 },
				whitetome= new double[] { 6, 10 },
				illicitresearch = new double[] { 6, 11 },
				buriedsecrets = new double[] { 7, 11 },
				arcanecompendium = new double[] { 7, 12 },
				dissentersnotes = new double[] { 7, 13 },
				nefariouscodex = new double[] { 8, 13 },
				mysticalbum = new double[] { 9, 13 },
				cursedmanual = new double[] { 9, 14 },
				tabootext = new double[] { 9, 15 },
				eldritchesoterica = new double[] { 10, 15 },
				freakishbestiary = new double[] { 11, 15 },
				madmansvita = new double[] { 11, 16 },
				untitledwritings = new double[] { 12, 16 },
				abandoneddogma = new double[] { 12, 17 },
				atlasofomens = new double[] { 12, 18 },
				revoltingscrapbook = new double[] { 13, 18 },
				lostheterodoxy = new double[] { 13, 19 },
				otherworldlytales = new double[] { 14, 19 },
				indescribablelore = new double[] { 15, 19 },
				radicaltreatise = new double[] { 15, 20 },
				bookofretribution = new double[] { 16, 20 },
				midnightsnack = new double[] { 5, 10 },
				deardiary = new double[] { 5, 10 },
				
				//Saix
				newmoon = new double[] { 7, 6 },
				werewolf = new double[] { 8, 6 },
				artemis = new double[] { 8, 7 },
				luminary = new double[] { 9, 7 },
				selene = new double[] { 9, 8 },
				moonrise = new double[] { 9, 9 },
				astrologia = new double[] { 10, 9 },
				crater = new double[] { 11, 9 },
				lunarphase = new double[] { 11, 10 },
				crescent = new double[] { 11, 11 },
				gibbous = new double[] { 12, 11 },
				berseker = new double[] { 13, 11 },
				twilight = new double[] { 13, 12 },
				queenofthenight = new double[] { 14, 12 },
				balsamicmoon = new double[] { 14, 13 },
				orbit = new double[] { 14, 14 },
				lightyear = new double[] { 15, 14 },
				kingofthenight = new double[] { 15, 15 },
				moonset = new double[] { 16, 15 },
				horoscope = new double[] { 17, 15 },
				dichotomy = new double[] { 17, 16 },
				lunatic = new double[] { 18, 16 },
				justdesserts = new double[] { 7, 6 },
				bunnymoon = new double[] { 7, 6 },       

				//Axel
				ashes = new double[] { 5, 8 },
				doldrums = new double[] { 6, 8 },
				delayedaction = new double[] { 6, 9 },
				divebombers = new double[] { 7, 9 },
				combustion = new double[] { 7, 10 },
				moulinrouge = new double[] { 7, 11 },
				blazeofglory = new double[] { 8, 11 },
				prometheus = new double[] { 9, 11 },
				ifrit = new double[] { 9, 12 },
				magmaocean = new double[] { 9, 13 },
				volcanics = new double[] { 10, 13 },
				inferno = new double[] { 11, 13 },
				sizzlingedge = new double[] { 11, 14 },
				corona = new double[] { 12, 14 },
				ferriswheel = new double[] { 12, 15 },
				burnout = new double[] { 12, 16 },
				omegatrinity = new double[] { 13, 16 },
				outbreak = new double[] { 13, 17 },
				doubleedge = new double[] { 14, 17 },
				wildfire = new double[] { 15, 17 },
				prominence = new double[] { 15, 18 },
				eternalflames = new double[] { 16, 18 },
				pizzacut = new double[] { 5, 8 },
				conformers = new double[] { 5, 8 },
    

				//Demyx
				basicmodel = new double[] { 5, 8 },
				tuneup = new double[] { 6, 8 },
				quartet = new double[] { 6, 9 },
				quintet = new double[] { 7, 9 },
				overture = new double[] { 7, 10 },
				oldhand = new double[] { 7, 11 },
				dacapo = new double[] { 8, 11 },
				powerchord = new double[] { 9, 11 },
				fermata = new double[] { 9, 12 },
				interlude = new double[] { 9, 13 },
				serenade = new double[] { 10, 13 },
				songbird = new double[] { 11, 13 },
				risetofame = new double[] { 11, 14 },
				rockstar = new double[] { 12, 14 },
				eightfinger = new double[] { 12, 15 },
				concierto = new double[] { 12, 16 },
				harmonics = new double[] { 13, 16 },
				millionbucks = new double[] { 13, 17 },
				fortissimo = new double[] { 14, 17 },
				uptoeleven = new double[] { 15, 17 },
				sanctuary = new double[] { 15, 18 },
				arpeggio = new double[] { 16, 18 },
				princeofawesome = new double[] { 5, 8 },
				afterschool = new double[] { 5, 8 },


				//Luxord
				thefool = new double[] { 6, 7 },
				themagician = new double[] { 7, 7 },
				thestar = new double[] { 7, 8 },
				themoon = new double[] { 8, 8 },
				justice = new double[] { 8, 9 },
				thehierophant = new double[] { 8, 10 },
				theworld = new double[] { 9, 10 },
				temperance = new double[] { 10, 10 },
				thehighpriestess = new double[] { 10, 11 },
				thetower = new double[] { 10, 12 },
				thehangedman = new double[] { 11, 12 },
				death = new double[] { 12, 12 },
				thehermit = new double[] { 12, 13 },
				strength = new double[] { 13, 13 },
				thelovers = new double[] { 13, 14 },
				thechariot = new double[] { 13, 15 },
				thesun = new double[] { 14, 15 },
				thedevil = new double[] { 14, 16 },
				theempress = new double[] { 15, 16 },
				theemperor = new double[] { 16, 16 },
				thejoker = new double[] { 16, 17 },
				fairgame = new double[] { 17, 17 },
				finestfantasy13 = new double[] { 6, 7 },
				highrollerssecret = new double[] { 6, 7 },        

				//Marluxia
				fickleerica = new double[] { 8, 5 },
				jiltedanemone = new double[] { 9, 5 },
				proudamaryllis = new double[] { 9, 6 },
				madsafflower = new double[] { 10, 6 },
				poormelissa = new double[] { 10, 7 },
				tragicallium = new double[] { 10, 8 },
				mournfulcineria = new double[] { 11, 8 },
				pseudosilene = new double[] { 12, 8 },
				faithlessdigitalis = new double[] { 12, 9 },
				grimmuscari = new double[] { 12, 10 },
				docilevallota = new double[] { 13, 10 },
				quietbelladonna = new double[] { 14, 10 },
				partingipheion = new double[] { 14, 11 },
				loftygerbera = new double[] { 15, 11 },
				gallantachillea = new double[] { 15, 12 },
				noblepeony = new double[] { 15, 13 },
				fearsomeanise = new double[] { 16, 13 },
				vindictivethistle = new double[] { 16, 14 },
				fairhelianthus = new double[] { 17, 14 },
				solemnmagnolia = new double[] { 18, 14 },
				hallowedlotus = new double[] { 18, 15 },
				gracefuldahlia = new double[] { 19, 15 },
				stirringladle = new double[] { 8, 5 },
				daintybellflowers = new double[] { 8, 5 },

				//Larxene
				trancheuse = new double[] { 8, 6 },
				orage = new double[] { 9, 6 },
				tourbillon = new double[] { 9, 7 },
				tempete = new double[] { 10, 7 },
				carmin = new double[] { 10, 8 },
				meteore = new double[] { 10, 9 },
				etoile = new double[] { 11, 9 },
				irregulier = new double[] { 12, 9 },
				dissonance = new double[] { 12, 10 },
				eruption = new double[] { 12, 11 },
				soleilcouchant = new double[] { 13, 11 },
				indigo = new double[] { 14, 11 },
				vague = new double[] { 14, 12 },
				deluge = new double[] { 15, 12 },
				rafale = new double[] { 15, 13 },
				typhon = new double[] { 15, 14 },
				extirpeur = new double[] { 16, 14 },
				croixdusud = new double[] { 16, 15 },
				lumineuse = new double[] { 17, 15 },
				clairdelune = new double[] { 18, 15 },
				voldenuit = new double[] { 18, 16 },
				foudre = new double[] { 19, 16 },
				demoiselle = new double[] { 8, 6 },
				ampoule = new double[] { 8, 6 };

	}

	public static class Other {
        @Config.RequiresMcRestart
        public int[] dreamStaff = new int[] { 0, 1 };

        @Config.RequiresMcRestart
        public int[] dreamSword = new int[] { 1, 0 };

        @Config.RequiresMcRestart
        public double[] dreamShield = new double[] { 0, 0 };

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
