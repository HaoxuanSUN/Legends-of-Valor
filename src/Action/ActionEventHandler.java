package Action;

import Battle.BattleHandler;
import Config.CalculateHandler;
import Config.InputHandler;
import Config.Printer;
import Inventory.Inventory;
import Item.Item;
import Item.ItemType;
import Market.*;
import Team.HeroTeam;
import Team.MonsterTeam;
import World.World;
import World.SpaceType;
import Character.Hero;

// This class is used to handle user input actions and triggers
// corresponding event handler. It takes care of the key inputs
// from world, inventory and market interface.
public class ActionEventHandler {
    private PositionChangeHandler positionChangeHandler;

    private final World world;

    private final HeroTeam heroTeam;

    private final MonsterTeam monsterTeam;

    private final Market market;

    private final BattleHandler battleHandler;

    public ActionEventHandler(World world, HeroTeam heroTeam, MonsterTeam monsterTeam,
                              BattleHandler battleHandler, Market market) {
        this.world = world;
        this.heroTeam = heroTeam;
        this.monsterTeam = monsterTeam;
        this.battleHandler = battleHandler;
        this.market = market;

        positionChangeHandler = new PositionChangeHandler<Hero>(world, heroTeam, monsterTeam);
    }

    // Handles event from user action input at world interface
    public boolean handleAction(ActionType action, Hero hero) {

        switch(action) {
            case MOVE_UP:
            case MOVE_DOWN:
            case MOVE_LEFT:
            case MOVE_RIGHT:
                MoveDirection dir = MoveDirection.UP;
                if (action == ActionType.MOVE_DOWN) {
                    dir = MoveDirection.DOWN;
                }
                else if (action == ActionType.MOVE_LEFT) {
                    dir = MoveDirection.LEFT;
                }
                else if (action == ActionType.MOVE_RIGHT){
                    dir = MoveDirection.RIGHT;
                }

                String errorMsg = positionChangeHandler.makeMove(dir, hero);
                if (errorMsg != null) {
                    Printer.printMoveError(errorMsg);
                }
                else {
                    hero.handleBuff(world.getSpace(hero.getPosition()).getBuff());
                    return true;
                }

                break;
            case TELEPORT:
                errorMsg = positionChangeHandler.makeTeleport(InputHandler.getTeleportPos(), hero);
                if (errorMsg != null) {
                    Printer.printTeleportError(errorMsg);
                }
                else {
                    return true;
                }
                break;
            case RECALL:
                if (!positionChangeHandler.makeRecall(hero)) {
                    Printer.printRecallError();
                }
                else {
                    return true;
                }
                break;
            case WEAPON:
                if (hero.handleWeaponEquip()) return true;
                break;
            case ARMOR:
                if (hero.handleArmorEquip()) return true;
                break;
            case POTION:
                if (hero.handlePotionUse()) return true;
                break;
            case ATTACK:
                if (battleHandler.detectBattle(hero)) return true;
                break;
            case HERO_INFO:
                Printer.printHeroInfo(hero);
                break;
            case ALL_INFO:
                Printer.printAllInfo(heroTeam, monsterTeam);
                break;
            case MAP:
                Printer.printWorld(world, heroTeam, monsterTeam, hero);
                break;
            case RULE:
                Printer.printRule();
                break;
            case INVENTORY:
                Printer.printHeroInventory(hero);
                break;
            case MARKET:
                handleMarketAction(hero);
                break;
            case HELP:
                Printer.printAction();
                break;
            case SKIP:
                return true;
            case QUIT:
                Printer.printThankYou();
                System.exit(0);
            default:
                System.out.println("! Invalid input, please try again!");
        }
        return false;
    }


    // Handles event from user action input at market interface
    private void handleMarketAction(Hero hero) {
        if (world.getSpaceType(hero.getPosition()) != SpaceType.HERO_NEXUS) {
            Printer.printNotAtMarketError();
            return;
        }
        Printer.printLoading("Entering Market");
        market.printStore();
        Printer.printMarketAction();
        Printer.printWelcomeMarket();
        Printer.printMarketLevel(market.getMarketLevel());

        outerloop:
        while (true) {
            Printer.printHeroGold(hero.getName(), hero.getGold());
            MarketActionType action = InputHandler.getMarketAction();
            switch(action) {
                case BUY:
                    handlePurchase(hero);
                    break;
                case SELL:
                    handleSell(hero);
                    break;
                case STORE:
                    market.printStore();
                    break;
                case CLOSE:
                    Printer.printLoading("Exiting Market");
                    break outerloop;
                case MAP:
                    Printer.printWorld(world, heroTeam, monsterTeam, hero);
                    break;
                case INVENTORY:
                    Printer.printHeroInventory(hero);
                    break;
                case ALL_INFO:
                    Printer.printAllInfo(heroTeam, monsterTeam);
                    break;
                case HERO_INFO:
                    Printer.printHeroInfo(hero);
                    break;
                case HELP:
                    Printer.printMarketAction();
                    break;
                case QUIT:
                    Printer.printThankYou();
                    System.exit(0);
            }
        }
    }

    // Helper function to handle purchase when received purchase action
    private void handlePurchase(Hero hero) {
        Printer.printItemType();
        int chosenItemType = InputHandler.getIndexNumber("buy", "an item type", 4);

        switch(chosenItemType) {
            case 0: // Weapon
                if (market.getWeaponsForSale().size() == 0) {
                    Printer.printNoItemInMarketError("weapon");
                    break;
                }

                Printer.printWeaponList(market.getWeaponsForSale());
                Printer.printHeroLevel(hero.getName(), hero.getLevel());
                Printer.printGoldLeft(hero.getName(), hero.getGold());

                int chosenItemIndex = InputHandler.getIndexNumber("buy", "a weapon", market.getWeaponsForSale().size());
                Item purchasedWeapon = market.handlePurchase(ItemType.WEAPON, chosenItemIndex);

                if (hero.getLevel() < purchasedWeapon.getLevel()) {
                    Printer.printNoEnoughLevel(hero.getName());
                }
                else if (hero.buyItem(purchasedWeapon)) {
                    Printer.printPurchaseItem(hero.getName(), "weapon", purchasedWeapon.getName(), purchasedWeapon.getPrice());
                }
                else {
                    market.addItem(purchasedWeapon);
                    Printer.printNoEnoughGold(hero.getName());
                }
                break;
            case 1: // Armor
                if (market.getArmorsForSale().size() == 0) {
                    Printer.printNoItemInMarketError("armor");
                    break;
                }

                Printer.printArmorList(market.getArmorsForSale());
                Printer.printHeroLevel(hero.getName(), hero.getLevel());
                Printer.printGoldLeft(hero.getName(), hero.getGold());

                chosenItemIndex = InputHandler.getIndexNumber("buy", "an armor", market.getArmorsForSale().size());
                Item purchasedArmor = market.handlePurchase(ItemType.ARMOR, chosenItemIndex);

                if (hero.getLevel() < purchasedArmor.getLevel()) {
                    Printer.printNoEnoughLevel(hero.getName());
                }
                else if (hero.buyItem(purchasedArmor)) {
                    Printer.printPurchaseItem(hero.getName(), "armor", purchasedArmor.getName(), purchasedArmor.getPrice());
                }
                else {
                    market.addItem(purchasedArmor);
                    Printer.printNoEnoughGold(hero.getName());
                }
                break;
            case 2: // Spell
                if (market.getSpellsForSale().size() == 0) {
                    Printer.printNoItemInMarketError("spell");
                    break;
                }

                Printer.printSpellList(market.getSpellsForSale());
                Printer.printHeroLevel(hero.getName(), hero.getLevel());
                Printer.printGoldLeft(hero.getName(), hero.getGold());

                chosenItemIndex = InputHandler.getIndexNumber("buy", "a spell", market.getSpellsForSale().size());
                Item purchasedSpell = market.handlePurchase(ItemType.SPELL, chosenItemIndex);

                if (hero.getLevel() < purchasedSpell.getLevel()) {
                    Printer.printNoEnoughLevel(hero.getName());
                }
                else if (hero.buyItem(purchasedSpell)) {
                    Printer.printPurchaseItem(hero.getName(), "spell", purchasedSpell.getName(), purchasedSpell.getPrice());
                }
                else {
                    market.addItem(purchasedSpell);
                    Printer.printNoEnoughGold(hero.getName());
                }
                break;
            case 3: // Potion
                if (market.getPotionsForSale().size() == 0) {
                    Printer.printNoItemInMarketError("potion");
                    break;
                }

                Printer.printPotionList(market.getPotionsForSale());
                Printer.printHeroLevel(hero.getName(), hero.getLevel());
                Printer.printGoldLeft(hero.getName(), hero.getGold());

                chosenItemIndex = InputHandler.getIndexNumber("buy", "a potion", market.getPotionsForSale().size());
                Item purchasedPotion = market.handlePurchase(ItemType.POTION, chosenItemIndex);

                if (hero.getLevel() < purchasedPotion.getLevel()) {
                    Printer.printNoEnoughLevel(hero.getName());
                }
                else if (hero.getGold() < purchasedPotion.getPrice()) {
                    Printer.printNoEnoughGold(hero.getName());
                }
                else {
                        int purchaseNum = InputHandler.getNumber(hero.getGold() / purchasedPotion.getPrice());
                        for (int i = 0; i < purchaseNum; i++) {
                            hero.buyItem(purchasedPotion);
                        }
                        Printer.printPurchaseItemWithNum(hero.getName(), "potion",
                                purchasedPotion.getName(), purchasedPotion.getPrice(), purchaseNum);
                }
                break;
        }
    }

    // Helper function to handle sell when received sell action
    private void handleSell(Hero hero) {
        Inventory chosenHeroInventory = hero.getInventory();

        if (chosenHeroInventory.getSize() == 0) {
            Printer.printNothingToSell(hero.getName(), "inventory");
            return;
        }

        Printer.printHeroInventory(hero);

        Printer.printItemType();
        int chosenItemType = InputHandler.getIndexNumber("sell", "an item type", 4);

        switch (chosenItemType) {
            case 0: // Weapon
                int size = chosenHeroInventory.getWeapons().size();
                if (size == 0) {
                    Printer.printNothingToSell(hero.getName(), "weapon");
                }
                else {
                    Printer.printWeaponList(chosenHeroInventory.getWeapons());
                    Printer.printSellPriceHint();

                    int chosenItemIndex = InputHandler.getIndexNumber("sell", "a weapon", size);
                    Item soldItem = hero.sellItem(ItemType.WEAPON, chosenItemIndex);
                    int soldAmount = CalculateHandler.calItemSellPrice(soldItem.getPrice());
                    market.addItem(soldItem);
                    Printer.printSellItem(hero.getName(), "a weapon", soldAmount);
                }
                break;
            case 1: // Armor
                size = chosenHeroInventory.getArmors().size();
                if (size == 0) {
                    Printer.printNothingToSell(hero.getName(), "armor");
                }
                else {
                    Printer.printArmorList(chosenHeroInventory.getArmors());
                    Printer.printSellPriceHint();

                    int chosenItemIndex = InputHandler.getIndexNumber("sell", "an armor", size);
                    Item soldItem = hero.sellItem(ItemType.ARMOR, chosenItemIndex);
                    int soldAmount = CalculateHandler.calItemSellPrice(soldItem.getPrice());
                    market.addItem(soldItem);
                    Printer.printSellItem(hero.getName(), "an armor", soldAmount);
                }
                break;
            case 2: // Spell
                size = chosenHeroInventory.getSpells().size();
                if (size == 0) {
                    Printer.printNothingToSell(hero.getName(), "spell");
                }
                else {
                    Printer.printSpellList(chosenHeroInventory.getSpells());
                    Printer.printSellPriceHint();

                    int chosenItemIndex = InputHandler.getIndexNumber("sell", "a spell", size);
                    Item soldItem = hero.sellItem(ItemType.SPELL, chosenItemIndex);
                    if (soldItem == null) {
                        Printer.printUsedSpellError();
                    }
                    else {
                        int soldAmount = CalculateHandler.calItemSellPrice(soldItem.getPrice());
                        market.addItem(soldItem);
                        Printer.printSellItem(hero.getName(), "a spell", soldAmount);
                    }
                }
                break;
            case 3: // Potion
                size = chosenHeroInventory.getPotions().size();
                if (size == 0) {
                    Printer.printNothingToSell(hero.getName(), "potion");
                }
                else {
                    Printer.printPotionList(chosenHeroInventory.getPotions());
                    Printer.printSellPriceHint();

                    int chosenItemIndex = InputHandler.getIndexNumber("sell", "a potion", size);
                    Item soldItem = hero.sellItem(ItemType.POTION, chosenItemIndex);
                    int soldAmount = CalculateHandler.calItemSellPrice(soldItem.getPrice());
                    market.addItem(soldItem);
                    Printer.printSellItem(hero.getName(), "a potion", soldAmount);
                }
                break;
        }
    }

}
