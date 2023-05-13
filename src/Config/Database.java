package Config;

import java.util.ArrayList;

// This class serves as a simple database and stores the raw string data that the game needed
public class Database {
    private final ArrayList<int[][]> mapList = new ArrayList<>();

    // 0: INACCESSIBLE SPACE
    // 1: Plain SPACE
    // 2: Bush SPACE
    // 3: Cave SPACE
    // 4: Koulou SPACE
    // 8: Hero Nexus SPACE
    // 9: Monster Spawner SPACE
    private final int[][] map1 = new int[][]{
            new int[] {9, 9, 0, 9, 9, 0, 9, 9},
            new int[] {1, 1, 0, 3, 1, 0, 1, 2},
            new int[] {4, 1, 0, 1, 4, 0, 1, 1},
            new int[] {1, 1, 0, 1, 3, 0, 1, 4},
            new int[] {3, 1, 0, 4, 1, 0, 2, 1},
            new int[] {2, 4, 0, 1, 1, 0, 1, 1},
            new int[] {2, 3, 0, 2, 2, 0, 4, 1},
            new int[] {8, 8, 0, 8, 8, 0, 8, 8}
    };

    private final int[][] map2 = new int[][]{
            new int[] {9, 9, 0, 9, 9, 0, 9, 9},
            new int[] {1, 2, 0, 3, 4, 0, 1, 3},
            new int[] {4, 1, 0, 2, 1, 0, 3, 1},
            new int[] {1, 3, 0, 4, 1, 0, 1, 1},
            new int[] {2, 1, 0, 1, 2, 0, 2, 4},
            new int[] {1, 4, 0, 3, 1, 0, 4, 3},
            new int[] {3, 1, 0, 1, 1, 0, 2, 1},
            new int[] {8, 8, 0, 8, 8, 0, 8, 8}
    };

    private final int[][] map3 = new int[][]{
            new int[] {9, 9, 0, 9, 9, 0, 9, 9},
            new int[] {1, 1, 0, 1, 2, 0, 1, 4},
            new int[] {3, 4, 0, 4, 1, 0, 1, 1},
            new int[] {1, 1, 0, 3, 3, 0, 1, 4},
            new int[] {4, 2, 0, 1, 1, 0, 1, 1},
            new int[] {2, 3, 0, 4, 1, 0, 1, 3},
            new int[] {1, 1, 0, 2, 1, 0, 3, 1},
            new int[] {8, 8, 0, 8, 8, 0, 8, 8}
    };

    private final int[][] map4 = new int[][]{
            new int[] {9, 9, 0, 9, 9, 0, 9, 9},
            new int[] {1, 3, 0, 1, 1, 0, 1, 1},
            new int[] {1, 1, 0, 4, 1, 0, 3, 4},
            new int[] {3, 4, 0, 1, 2, 0, 2, 1},
            new int[] {1, 1, 0, 3, 1, 0, 3, 4},
            new int[] {4, 2, 0, 1, 4, 0, 1, 2},
            new int[] {1, 1, 0, 1, 3, 0, 1, 1},
            new int[] {8, 8, 0, 8, 8, 0, 8, 8}
    };

    private final int[][] map5 = new int[][]{
            new int[] {9, 9, 0, 9, 9, 0, 9, 9},
            new int[] {3, 1, 0, 4, 1, 0, 1, 4},
            new int[] {1, 4, 0, 1, 2, 0, 3, 1},
            new int[] {1, 1, 0, 3, 1, 0, 1, 2},
            new int[] {4, 2, 0, 2, 1, 0, 4, 1},
            new int[] {1, 3, 0, 1, 4, 0, 3, 1},
            new int[] {2, 1, 0, 1, 3, 0, 2, 1},
            new int[] {8, 8, 0, 8, 8, 0, 8, 8}
    };

    private final String[] warriorData = new String[] {
            "Gaerdal_Ironhand    100     700     500     600     1354    7",
            "Sehanine_Monnbow    600     700     800     500     2500    8",
            "Muamman_Duathall    300     900     500     750     2546    6",
            "Flandal_Steelskin   200     750     650     700     2500    7",
            "Undefeated_Yoj      400     800     400     700     2500    7",
            "Eunoia_Cyn          400     700     800     600     2500    6"
    };

    private final String[] sorcererData = new String[] {
            "Rillifane_Rallathil     1300    750     450     500     2500    9",
            "Segojan_Earthcaller     900     800     500     650     2500    5",
            "Reign_Havoc             800     800     800     800     2500    8",
            "Reverie_Ashels          900     800     700     400     2500    7",
            "Kalabar                 800     850     400     600     2500    6",
            "Skye_Soar               1000    700     400     500     2500    5"
    };

    private final String[] paladinData = new String[] {
            "Parzival             300     750     650     700     2500    7",
            "Sehanine_Moonbow     300     750     700     700     2500    7",
            "Skoraeus_Stonebones  250     650     600     350     2500    4",
            "Garl_Glittergold     100     600     500     400     2500    5",
            "Amaryllis_Astra      500     500     500     500     2500    5",
            "Caliber_Heist        400     400     400     400     2500    8"
    };

    private final String[] weaponData = new String[] {
            "Sword           500     1    800    1",
            "Bow             300     2    500    2",
            "Scythe          1000    6    1100   2",
            "Axe             550     5    850    1",
            "TSwords     	 1400    8    1600   2",
            "Dagger          200     1    250    1"
    };

    private final String[] armorData = new String[] {
            "Platinum_Shield       150   1   200",
            "Breastplate           350   3   600",
            "Full_Body_Armor       1000  8   1100",
            "Wizard_Shield         1200  10  1500",
            "Guardian_Angel        1000  10  1000"
    };

    private final String[] potionData = new String[] {
            "Healing_Potion  250     1   100     Health",
            "Strength_Potion 200     1   75      Strength",
            "Magic_Potion    350     2   100     Mana",
            "Luck_Elixir     500     4   65      Agility",
            "Mermaid_Tears   850     5   100     Health/Mana/Strength/Agility",
            "Ambrosia        1000    8   150     All"
    };

    private final String[] fireSpellData = new String[] {
            "Flame_Tornado   700     4   850     300",
            "Breath_of_Fire  350     1   450     100",
            "Heat_Wave       450     2   600     150",
            "Lava_Comet      800     7   1000    550",
            "Hell_Storm      600     3   950     600"
    };

    private final String[] iceSpellData = new String[] {
            "Snow_Cannon     500     2   650     250",
            "Ice_Blade       250     1   450     100",
            "Frost_Blizzard  750     5   850     350",
            "Arctic_Storm    700     6   800     300"
    };

    private final String[] lightningSpellData = new String[] {
            "Lightning_Dagger      400        1       500     150",
            "Thunder_Blast         750        4       950     400",
            "Electric_Arrows       550        5       650     200",
            "Spark_Needles         500        2       600     200"
    };

    private final String[] dragonData = new String[] {
            "Desghidorrah	 3       300       400     35",
            "Chrysophylax     2       200       500     20",
            "BunsenBurner	 4       400       500     45",
            "Natsunomeryu 	 1       100       200     10",
            "TheScaleless	 7       700       600     75",
            "Kas-Ethelinh     5       600       500     60",
            "Alexstraszan	 10      1000      9000    55",
            "Phaarthurnax	 6       600       700     60",
            "D-Maleficent	 9       900       950     85",
            "TheWeatherbe 	 8       800       900     80",
            "Igneel           6       600       400     60",
            "BlueEyesWhite    9       900       600     75"
    };

    private final String[] exoskeletonData = new String[] {
            "Cyrrollalee     7       700        800     75",
            "Brandobaris     3       350        450     30",
            "BigBad-Wolf     1       150        250     15",
            "WickedWitch     2       250        350     25",
            "Aasterinian     4       400        500     45",
            "Chronepsish     6       650        750     60",
            "Kiaransalee     8       850        950     85",
            "St-Shargaas     5       550        650     55",
            "Merrshaullk     10      1000       900     55",
            "St-Yeenoghu     9       950        850     90",
            "DocOck          6       600        600     55",
            "Exodia          10      1000       1000    50"
    };

    private final String[] spiritData = new String[] {
            "Andrealphus     2       600       500     40",
            "Blinky          1       450       350     35",
            "Andromalius     3       550       450     25",
            "Chiang-shih     4       700       600     40",
            "FallenAngel     5       800       700     50",
            "Ereshkigall     6       950       450     35",
            "Melchiresas     7       350       150     75",
            "Jormunngand     8       600       900     20",
            "Rakkshasass     9       550       600     35",
            "Taltecuhtli     10      300       200     50",
            "Casper          1       100       100     50"
    };

    public Database() {
        mapList.add(map1);
        mapList.add(map2);
        mapList.add(map3);
        mapList.add(map4);
        mapList.add(map5);
    }

    public ArrayList<int[][]> getMapList() {
        return mapList;
    }

    public String[] getWarriorData() {
        return warriorData;
    }

    public String[] getSorcererData() {
        return sorcererData;
    }

    public String[] getPaladinData() {
        return paladinData;
    }

    public String[] getWeaponData() {
        return weaponData;
    }

    public String[] getArmorData() {
        return armorData;
    }

    public String[] getPotionData() {
        return potionData;
    }

    public String[] getFireSpellData() {
        return fireSpellData;
    }

    public String[] getIceSpellData() {
        return iceSpellData;
    }

    public String[] getLightningSpellData() {
        return lightningSpellData;
    }

    public String[] getDragonData() {
        return dragonData;
    }

    public String[] getExoskeletonData() {
        return exoskeletonData;
    }

    public String[] getSpiritData() {
        return spiritData;
    }
}
