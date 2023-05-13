# CS611-HW4
## Legends of Valor
---------------------------------------------------------------------------

Haoxuan Sun
hs4379@bu.edu
U58198360

## Files
---------------------------------------------------------------------------
- Action: package includes classes related to action handle
    ActionEventHandler.java: This class is used to handle user input actions and triggers
                             corresponding event handler. It takes care of the key inputs
                             from world, inventory and market interface
    ActionType.java: Enum class to show available action types at world interface
    MoveDirection.java: Enum class to indicate direction to move
    PositionChangeHandler.java: This class handles the movement action for team to move in world,
                             it implements MakeMove/Teleport/Recall interface

- Battle: package includes classes related to battle
    AttackType.java: Enum class to show attack types by hero
    BattleAction.java: Enum class to show available action types at battle interface
    BattleHandler.java: This is an abstract class that make use of java generics,
                        it contains abstract methods of a battle,
			     and it implements attack interface
    MonstersAndHeroesBattle.java: This class extends BattleHandler class and implements
                                  abstract methods, it is used to control a battle flow
                                  of Monsters and Heroes

- Character: package includes classes related to character in game
    Buff.java: this is a class tracking the type of buff each hero have.
    CharacterType.java: Enum class to show character types
    GameCharacter.java: This is an abstract class which has basic fields and methods of a game character.
    Hero.java: This is a sub class extended from GameCharacter, it is a basic hero unit in game
    HeroRole.java: Enum class to show different roles of hero
    Level.java: This is a class takes care of level and exp point, Hero has Level
    Monster.java: This is a sub class extended from GameCharacter, it is a basic monster unit in game
    MonsterType.java: Enum class to show different kinds of monster

- Config: package includes classes related to game data and calculation
    CalculateHandler.java: This class handles any calculation needed during the game process
    Database.java: This class serves as a simple database and stores the raw string data that the game needed
    InputHandler.java: This class handles any input action needed from user during the game process
    MonstersAndHeroesRule.java: This class implements Rule interface and help game to check win/lose conditions
    Printer.java: This class handles terminal output of the game
    Stats.java: This class read data from Database and parse them to the data for other classes to read

- Controller: package includes classes related to overall game flow control
    GameController.java: This class has the abstract method to control the overall game flow
    GameLobby.java: This class implements the function for select the game
    MonstersAndHeroes.java: This class extends GameController class and implements the abstract methods

- Interface: package includes all interface
    Attack.java: Interface includes a method that handles attack, which makes use of java generics
    MakeMove.java: Interface includes a method that handles move action
    Recall.java: Interface includes a method that handles recall action
    Rule.java Interface helps to check win/lose conditions
    Teleport.java: Interface includes a method that handles teleport action

- Inventory: package includes classes related to inventory
    Inventory.java: This class take cares of everything related to inventory, Hero has Inventory
    InventoryActionType.java: Enum class show available action type in inventory interface

- Item: package includes classes related to item
    Armor.java: This class extends item class, and represents an armor item in game
    Item.java: This is an abstract class, and is a general item with basic fields and methods
    ItemType.java: Enum class to show different item types
    Potion.java: This class extends item class, and represents a potion item in game
    Spell.java: This class extends item class, and represents a spell item in game
    SpellType.java: Enum class to show types of spell item
    Weapon.java: This class extends item class, and represents a weapon item in game

- Market: package includes classes related to market
    Market.java: This class handles market actions in game
    MarketActionType.java: Enum to show available action in market interface

- Team: package includes classes related to team
    HeroTeam.java: This class extends Team and maintains a list of hero objects
    MonsterTeam.java: This class extends Team and maintains a list of monster objects
    Team.java: This is an abstract class includes basic fields and method of a team

- World: package includes classes related to map of world
    Board.java: This class is an abstract class contains the basic fields and methods that
                consist of a game board
    BuffType.java: Enum to show available buff types of a space in board/world
    Lane.java: This class contains the basic fields and methods for a lane
    LaneType.java: Enum to show lane types in board/world
    MonsterSpawner.java: This class handles monster spawner
    Position.java: This class contains the basic fields and methods for position
    Space.java: This class represents a single space in board/world
    SpaceType.java: Enum class show the space type of space object
    Symbol.java: Enum class show what the symbol of each space type in world looks like
    World.java: This class extends general Board class and represents a basic world of RPG game


## Notes
---------------------------------------------------------------------------
1. 
<Files to be parsed should be stored in ConfigFiles, for parser class to read class>

2.
All basic functionalities described in pdf have been implemented in the game.
Here are some possible bonus points:
World of play
    - We make the game randomly generated the world by pre-storing many different maps in database
    and let object randomly pick one, to make game map look randomly generated.
Market
    - We make the spell multi-use, when it reaches the max use limit, it will then be destroyed.
    - We let the market adjust its product's level scaled by the highest level of hero in team.
    - We allow player to choose how many potion they can buy to avoid redundant operations.

3.
General structure:
   - Our project emphasizes scalability by using multiple enum classes that makes future development easier,
   such as adding more action for each user interfaces (world, battle, etc.). Also, make each class as less coupled
   as possible, by dividing the functionality into as many classes as possible.
   - Moreover, we created many basic abstract class such as Board, Team, etc. to make those class ready to use
   for other game development.
   - Finally, we separate the calculation and data from the logic part so that if anyone wants to change the stats
   or the formula of the game, they can easily change in config file without entering the logic part and find them.



## How to compile and run
---------------------------------------------------------------------------
1. Navigate to the directory "LegendsOfValor" after unzipping the files
2. Run the following instructions:
if java files in folders run:
javac -d out src/Action/*.java src/Battle/*.java src/Character/*.java src/Config/*.java src/Controller/*.java src/Interface/*.java src/Inventory/*.java src/Item/*.java src/Market/*.java src/Team/*.java src/World/*.java src/*.java

if java files not in folders run:
javac -d out src/*.java

java -cp out Main






## Input/Output Example
---------------------------------------------------------------------------
WELCOME!
- Please select a game to play:
---------- Games ----------
|   1. Legends of Valor    |
---------------------------

- Choose game to play.
> Please input the valid game number:
1

- Welcome to Legends of Valor!
- This is a game in a world full of spells, monsters, and heroes!
- You will be asked to form a team of heroes to fight against the monsters.
- Hope you enjoy the game~
- GLHF!

> Press 'Enter' to show rules.

====================================================== Game Initialize ======================================================

> For TOP Hero, which role do you want he/she to be?
    1. Warrior:  strength++  agility++
    2. Sorcerer: dexterity++ agility++
    3. Paladin:  strength++  dexterity++
> Please input the valid role number (1/2/3): 
1

> For MID Hero, which role do you want he/she to be?
    1. Warrior:  strength++  agility++
    2. Sorcerer: dexterity++ agility++
    3. Paladin:  strength++  dexterity++
> Please input the valid role number (1/2/3): 
2

> For BOT Hero, which role do you want he/she to be?
    1. Warrior:  strength++  agility++
    2. Sorcerer: dexterity++ agility++
    3. Paladin:  strength++  dexterity++
> Please input the valid role number (1/2/3): 
1

- Initializing Hero Teams...

- Team has been created!
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Team Status ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
No.	HP	Mana	Strength	Agility Dexterity	Gold	Lv	EXP	Role	  Alive	Name
1	100	100	700		500	600		1354	1	7	WARRIOR +		Gaerdal Ironhand
2	100	800	850		400	600		2500	1	6	SORCERER+		Kalabar
3	100	400	800		400	700		2500	1	7	WARRIOR +		Undefeated Yoj
('+' : not faint; '-' : faint)
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

- Initializing Monsters...

- First wave of monster has been created!

- Generating Random Map...

- World has been created!

  TOP      MID      BOT
|  | M|  |  | M|  |  | M|1
|  |  |  |  |  |  |  |  |2
|  |  |  |  |  |  |  |  |3
|  |  |  |  |  |  |  |  |4 R
|  |  |  |  |  |  |  |  |5 O
|  |  |  |  |  |  |  |  |6 W
|  |  |  |  |  |  |  |  |7
|H |  |  |H |  |  |H |  |8
 1  2  3  4  5  6  7  8
	   COLUMN

H: Your heroes location
M: Monsters location
  | Inaccessible space
  | Plain space
  | Hero Nexus (Market)
  | Monster Nexus
  | Bush space      Dexterity++
  | Cave space      Agility++
  | Koulou space    Strength++

> Press 'Enter' to start the game.

====================================================== Game Start ======================================================
                                    ============= Input Options ============
                                   ｜--- W/w: move up one unit ---          ｜
                                   ｜--- A/a: move left one unit ---        ｜
                                   ｜--- S/s: move down one unit ---        ｜
                                   ｜--- D/d: move right one unit ---       ｜
                                   ｜--- T/t: teleport to a space ---       ｜
                                   ｜--- R/r: recall to nexus ---           ｜
                                   ｜--- weapon: change weapon ---          ｜
                                   ｜--- armor: change armor ---            ｜
                                   ｜--- potion: use a potion---            ｜
                                   ｜--- attack: perform an attack ---      ｜
                                   ｜--- * I/i: show hero & monster info ---｜
                                   ｜--- * H/h: show current hero info ---  ｜
                                   ｜--- * M/m: enter market ---            ｜
                                   ｜--- * map: open world map ---          ｜
                                   ｜--- * bag: show inventory ---          ｜
                                   ｜--- * rule: open rule book ---         ｜
                                   ｜--- * help: action list ---            ｜
                                   ｜--- Q/q/quit: quit game ---            ｜
                                    ========================================
                                    action with * does not consume the turn

> Press 'Enter' to start round 1.

====================================================== Round 1 ======================================================

  TOP      MID      BOT
|  | M|  |  | M|  |  | M|1
|  |  |  |  |  |  |  |  |2
|  |  |  |  |  |  |  |  |3
|  |  |  |  |  |  |  |  |4 R
|  |  |  |  |  |  |  |  |5 O
|  |  |  |  |  |  |  |  |6 W
|  |  |  |  |  |  |  |  |7
|H |  |  |H |  |  |H |  |8
 1  2  3  4  5  6  7  8
	   COLUMN


>>>>>>>>> Hero Gaerdal Ironhand's Turn <<<<<<<<<
- Hero Gaerdal Ironhand is currently at Top lane. 
- Row 8
- Col 1
- You are currently at a market space, you can enter the market by M/m.
> (World) Please enter the desired action ("help" for action options): 
m
- Entering Market...

>>>>>>>>>>>>>>>>>>>>>>>>> MARKET <<<<<<<<<<<<<<<<<<<<<<<<<
-------------------- Weapon --------------------
No.	Lv	Damage	Price	Hands	Name
1	1	800	500	1	Sword
2	1	250	200	1	Dagger
------------------------------------------------
----------------------------- Armor ------------------------------
No.	Lv	Reduction	Price	Name
1	1	200		150	Platinum_Shield
------------------------------------------------------------------
-------------------------------- Potion ----------------------------------
No.	Lv	Increase	Price	Name		Attribute_Affected
1	1	100		250	Healing_Potion	Health
2	1	75		200	Strength_Potion	Strength
--------------------------------------------------------------------------
--------------------------------------- Spell ---------------------------------------
No.	Lv	Damage	Mana	Price	Used	Max_Use	Type		Name
1	1	450	100	250	0	3	ICE		Ice_Blade
2	1	450	100	350	0	3	FIRE		Breath_of_Fire
3	1	500	150	400	0	3	LIGHTNING	Lightning_Dagger
-------------------------------------------------------------------------------------
========= Market Options =========
｜--- B/b: buy item ---          ｜
｜--- S/s: sell item ---         ｜
｜--- V/v: view products ---     ｜
｜--- I/i: show all info ---     ｜
｜--- H/h: show hero info ---    ｜
｜--- bag: show inventory ---    ｜
｜--- map: open world map ---    ｜
｜--- close: close market ---    ｜
｜--- help: action list ---      ｜
｜--- Q/q/quit: quit game ---    ｜
==================================

- Welcome to market!

- Current market level: 1

- Hero Gaerdal Ironhand has 1354 golds.
> (Market) Please enter the desired action ("help" for market options): 
b
--- Item Types ---
|   1. Weapon    |
|   2. Armor     |
|   3. Spell     |
|   4. Potion    |
------------------

- Choose an item type to buy.
> Please input the valid an item type number: 
1

-------------------- Weapon --------------------
No.	Lv	Damage	Price	Hands	Name
1	1	800	500	1	Sword
2	1	250	200	1	Dagger
------------------------------------------------
- Hero Gaerdal Ironhand is level 1.
- Hero Gaerdal Ironhand has 1354 golds.

- Choose a weapon to buy.
> Please input the valid a weapon number: 
1

LOG: Hero Gaerdal Ironhand purchased weapon Sword using 500 golds!

- Hero Gaerdal Ironhand has 854 golds.
> (Market) Please enter the desired action ("help" for market options): 
close
- Exiting Market...

- You are currently at a market space, you can enter the market by M/m.
> (World) Please enter the desired action ("help" for action options): 
Map
  TOP      MID      BOT
|  | M|  |  | M|  |  | M|1
|  |  |  |  |  |  |  |  |2
|  |  |  |  |  |  |  |  |3
|  |  |  |  |  |  |  |  |4 R
|  |  |  |  |  |  |  |  |5 O
|  |  |  |  |  |  |  |  |6 W
|  |  |  |  |  |  |  |  |7
|H |  |  |H |  |  |H |  |8
 1  2  3  4  5  6  7  8
	   COLUMN

- You are currently at a market space, you can enter the market by M/m.
> (World) Please enter the desired action ("help" for action options): 
weapon
-------------------- Weapon --------------------
No.	Lv	Damage	Price	Hands	Name
1	1	800	500	1	Sword
------------------------------------------------
- Choose a weapon to equip.
> Please input the valid a weapon number: 
1

- Weapon Sword is successfully equipped!

  TOP      MID      BOT
|  | M|  |  | M|  |  | M|1
|  |  |  |  |  |  |  |  |2
|  |  |  |  |  |  |  |  |3
|  |  |  |  |  |  |  |  |4 R
|  |  |  |  |  |  |  |  |5 O
|  |  |  |  |  |  |  |  |6 W
|  |  |  |  |  |  |  |  |7
|H |  |  |H |  |  |H |  |8
 1  2  3  4  5  6  7  8
	   COLUMN

>>>>>>>>> Hero Kalabar's Turn <<<<<<<<<
- Hero Kalabar is currently at Mid lane. 
- Row 8
- Col 4
- You are currently at a market space, you can enter the market by M/m.
> (World) Please enter the desired action ("help" for action options): 
W
  TOP      MID      BOT
|  | M|  |  | M|  |  | M|1
|  |  |  |  |  |  |  |  |2
|  |  |  |  |  |  |  |  |3
|  |  |  |  |  |  |  |  |4 R
|  |  |  |  |  |  |  |  |5 O
|  |  |  |  |  |  |  |  |6 W
|  |  |  |H |  |  |  |  |7
|H |  |  |  |  |  |H |  |8
 1  2  3  4  5  6  7  8
	   COLUMN

>>>>>>>>> Hero Undefeated Yoj's Turn <<<<<<<<<
- Hero Undefeated Yoj is currently at Top lane. 
- Row 8
- Col 7
- You are currently at a market space, you can enter the market by M/m.
> (World) Please enter the desired action ("help" for action options): 
t
- Choose row to teleport.
> Please input the valid row number: 
8

- Choose col to teleport.
> Please input the valid col number: 
4

>>>>>>>>> Monster Casper's Turn <<<<<<<<<
- No hero in range to attack or hero in range is fainted.

LOG: Monster Casper moves down by one unit.

>>>>>>>>> Monster Blinky's Turn <<<<<<<<<
- No hero in range to attack or hero in range is fainted.

LOG: Monster Blinky moves down by one unit.

>>>>>>>>> Monster Blinky's Turn <<<<<<<<<
- No hero in range to attack or hero in range is fainted.

LOG: Monster Blinky moves down by one unit.

  TOP      MID      BOT
|  |  |  |  |  |  |  |  |1
|  | M|  |  | M|  |  | M|2
|  |  |  |  |  |  |  |  |3
|  |  |  |  |  |  |  |  |4 R
|  |  |  |  |  |  |  |  |5 O
|  |  |  |  |  |  |  |  |6 W
|  |  |  |H |  |  |  |  |7
|H |  |  |H |  |  |  |  |8
 1  2  3  4  5  6  7  8
	   COLUMN

====================================================== Round 1 End ======================================================
> Press 'Enter' to start round 2.

====================================================== Round 2 ======================================================
  TOP      MID      BOT
|  |  |  |  |  |  |  |  |1
|  | M|  |  | M|  |  | M|2
|  |  |  |  |  |  |  |  |3
|  |  |  |  |  |  |  |  |4 R
|  |  |  |  |  |  |  |  |5 O
|  |  |  |  |  |  |  |  |6 W
|  |  |  |H |  |  |  |  |7
|H |  |  |H |  |  |  |  |8
 1  2  3  4  5  6  7  8
	   COLUMN



........ 3 turns later......




====================================================== Round 5 ======================================================
  TOP      MID      BOT
|  |  |  |  |  |  |  |  |1
|  |  |  |  |  |  |  |  |2
|  |  |  |  |  |  |  |  |3
|  |  |  |  | M|  |  |  |4 R
|  | M|  |H |H |  |  | M|5 O
|  |H |  |  |  |  |  |  |6 W
|  |  |  |  |  |  |  |  |7
|  |  |  |  |  |  |  |  |8
 1  2  3  4  5  6  7  8
	   COLUMN

>>>>>>>>> Hero Gaerdal Ironhand's Turn <<<<<<<<<
- Hero Gaerdal Ironhand is currently at Top lane. 
- Row 6
- Col 2
- You are currently at a koulou space, strength has increased by 10%.
> (World) Please enter the desired action ("help" for action options): 
attack
LOG: Monster Casper has dodged attack!

  TOP      MID      BOT
|  |  |  |  |  |  |  |  |1
|  |  |  |  |  |  |  |  |2
|  |  |  |  |  |  |  |  |3
|  |  |  |  | M|  |  |  |4 R
|  | M|  |H |H |  |  | M|5 O
|  |H |  |  |  |  |  |  |6 W
|  |  |  |  |  |  |  |  |7
|  |  |  |  |  |  |  |  |8
 1  2  3  4  5  6  7  8
	   COLUMN

>>>>>>>>> Hero Kalabar's Turn <<<<<<<<<
- Hero Kalabar is currently at Mid lane. 
- Row 5
- Col 5
> (World) Please enter the desired action ("help" for action options): 
i
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Team Status ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
No.	HP	Mana	Strength	Agility	Dexterity	Gold	Lv	EXP	Role	Alive	Name
1	100	100	770		500	600		854	1	7	WARRIOR	+	Gaerdal Ironhand
2	100	800	850		400	600		2500	1	6	SORCERER	+	Kalabar
3	100	400	800		400	700		2500	1	7	WARRIOR	+	Undefeated Yoj
('+' : not faint; '-' : faint)
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Monster Status ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
No.	HP	Damage	Defense	Dodge	Lv	Row	Col	Type	Name
1	100	100	100	50%	1	5	2	SPIRIT		Casper
2	100	450	350	35%	1	4	5	SPIRIT		Blinky
3	100	450	350	35%	1	5	8	SPIRIT		Blinky
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

> (World) Please enter the desired action ("help" for action options): 
attack
LOG: Monster Blinky has dodged attack!

  TOP      MID      BOT
|  |  |  |  |  |  |  |  |1
|  |  |  |  |  |  |  |  |2
|  |  |  |  |  |  |  |  |3
|  |  |  |  | M|  |  |  |4 R
|  | M|  |H |H |  |  | M|5 O
|  |H |  |  |  |  |  |  |6 W
|  |  |  |  |  |  |  |  |7
|  |  |  |  |  |  |  |  |8
 1  2  3  4  5  6  7  8
	   COLUMN

>>>>>>>>> Hero Undefeated Yoj's Turn <<<<<<<<<
- Hero Undefeated Yoj is currently at Mid lane. 
- Row 5
- Col 4
> (World) Please enter the desired action ("help" for action options): 
attack
LOG: Hero Undefeated Yoj attacked monster Blinky for 5 damage by weapon!

>>>>>>>>> Monster Casper's Turn <<<<<<<<<
LOG: Monster Casper attacked hero Gaerdal Ironhand for 100 damage!

>>>>>>>>> Monster Blinky's Turn <<<<<<<<<
LOG: Monster Blinky attacked hero Kalabar for 450 damage!

>>>>>>>>> Monster Blinky's Turn <<<<<<<<<
- No hero in range to attack or hero in range is fainted.

LOG: Monster Blinky moves down by one unit.

  TOP      MID      BOT
|  |  |  |  |  |  |  |  |1
|  |  |  |  |  |  |  |  |2
|  |  |  |  |  |  |  |  |3
|  |  |  |  | M|  |  |  |4 R
|  | M|  |H |H |  |  |  |5 O
|  |H |  |  |  |  |  | M|6 W
|  |  |  |  |  |  |  |  |7
|  |  |  |  |  |  |  |  |8
 1  2  3  4  5  6  7  8
	   COLUMN

- Hero Gaerdal Ironhand has revived at nexus, and restores full MP & HP!

- Hero Kalabar has revived at nexus, and restores full MP & HP!

====================================================== Round 5 End ======================================================
> Press 'Enter' to start round 6.

====================================================== Round 6 ======================================================
  TOP      MID      BOT
|  |  |  |  |  |  |  |  |1
|  |  |  |  |  |  |  |  |2
|  |  |  |  |  |  |  |  |3
|  |  |  |  | M|  |  |  |4 R
|  | M|  |H |  |  |  |  |5 O
|  |  |  |  |  |  |  | M|6 W
|  |  |  |  |  |  |  |  |7
|H |  |  |H |  |  |  |  |8
 1  2  3  4  5  6  7  8
	   COLUMN



........ many turns later......




====================================================== Round 15 ======================================================
  TOP      MID      BOT
|  |  |  |  |  |  |  |  |1
|  |  |  |  |  |  |  |  |2
|  |  |  |  |  |  |  |  |3
|  |  |  |  |  |  |  |  |4 R
|  |  |  |  |  |  |  |  |5 O
|  | M|  |  | M|  |  |  |6 W
|  | M|  |  | M|  |  | M|7
|H |  |  |H |  |  |H |  |8
 1  2  3  4  5  6  7  8
	   COLUMN

>>>>>>>>> Hero Gaerdal Ironhand's Turn <<<<<<<<<
- Hero Gaerdal Ironhand is currently at Top lane. 
- Row 8
- Col 1
- You are currently at a market space, you can enter the market by M/m.
> (World) Please enter the desired action ("help" for action options): 
w
  TOP      MID      BOT
|  |  |  |  |  |  |  |  |1
|  |  |  |  |  |  |  |  |2
|  |  |  |  |  |  |  |  |3
|  |  |  |  |  |  |  |  |4 R
|  |  |  |  |  |  |  |  |5 O
|  | M|  |  | M|  |  |  |6 W
|H | M|  |  | M|  |  | M|7
|  |  |  |H |  |  |H |  |8
 1  2  3  4  5  6  7  8
	   COLUMN

>>>>>>>>> Hero Kalabar's Turn <<<<<<<<<
- Hero Kalabar is currently at Mid lane. 
- Row 8
- Col 4
- You are currently at a market space, you can enter the market by M/m.
> (World) Please enter the desired action ("help" for action options): 
w
  TOP      MID      BOT
|  |  |  |  |  |  |  |  |1
|  |  |  |  |  |  |  |  |2
|  |  |  |  |  |  |  |  |3
|  |  |  |  |  |  |  |  |4 R
|  |  |  |  |  |  |  |  |5 O
|  | M|  |  | M|  |  |  |6 W
|H | M|  |H | M|  |  | M|7
|  |  |  |  |  |  |H |  |8
 1  2  3  4  5  6  7  8
	   COLUMN

>>>>>>>>> Hero Undefeated Yoj's Turn <<<<<<<<<
- Hero Undefeated Yoj is currently at Top lane. 
- Row 8
- Col 7
- You are currently at a market space, you can enter the market by M/m.
> (World) Please enter the desired action ("help" for action options): 
w
>>>>>>>>> Monster Casper's Turn <<<<<<<<<
LOG: Monster Casper attacked hero Gaerdal Ironhand for 100 damage!

>>>>>>>>> Monster Blinky's Turn <<<<<<<<<
LOG: Monster Blinky attacked hero Kalabar for 450 damage!

>>>>>>>>> Monster Blinky's Turn <<<<<<<<<
LOG: Monster Blinky attacked hero Undefeated Yoj for 450 damage!

>>>>>>>>> Monster Blinky's Turn <<<<<<<<<
- No hero in range to attack or hero in range is fainted.

LOG: Monster Blinky moves down by one unit.

>>>>>>>>> Monster BigBad-Wolf's Turn <<<<<<<<<
- No hero in range to attack or hero in range is fainted.

LOG: Monster BigBad-Wolf moves down by one unit.

[1;91m- Monster has reached your nexus, you lose!!![0m
- Thank you for playing the game!
- See you next time!


