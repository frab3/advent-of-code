package net.bassmann.adventofcode.year2015.day21;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import net.bassmann.adventofcode.common.AbstractDay;

/**
 * Day 21: RPG Simulator 20XX.
 *
 * <p>Little Henry Case got a new video game for Christmas. It's an RPG, and he's stuck on a boss.
 * He needs to know what equipment to buy at the shop. He hands you the controller.
 *
 * <p>In this game, the player (you) and the enemy (the boss) take turns attacking. The player
 * always goes first. Each attack reduces the opponent's hit points by at least 1. The first
 * character at or below 0 hit points loses.
 *
 * <p>Damage dealt by an attacker each turn is equal to the attacker's damage score minus the
 * defender's armor score. An attacker always does at least 1 damage. So, if the attacker has a
 * damage score of 8, and the defender has an armor score of 3, the defender loses 5 hit points. If
 * the defender had an armor score of 300, the defender would still lose 1 hit point.
 *
 * <p>Your damage score and armor score both start at zero. They can be increased by buying items in
 * exchange for gold. You start with no items and have as much gold as you need. Your total damage
 * or armor is equal to the sum of those stats from all of your items. You have 100 hit points.
 *
 * <p>Here is what the item shop is selling:
 *
 * <pre>
 * Weapons:    Cost  Damage  Armor
 * Dagger        8     4       0
 * Shortsword   10     5       0
 * Warhammer    25     6       0
 * Longsword    40     7       0
 * Greataxe     74     8       0
 *
 * Armor:      Cost  Damage  Armor
 * Leather      13     0       1
 * Chainmail    31     0       2
 * Splintmail   53     0       3
 * Bandedmail   75     0       4
 * Platemail   102     0       5
 *
 * Rings:      Cost  Damage  Armor
 * Damage +1    25     1       0
 * Damage +2    50     2       0
 * Damage +3   100     3       0
 * Defense +1   20     0       1
 * Defense +2   40     0       2
 * Defense +3   80     0       3
 * </pre>
 *
 * You must buy exactly one weapon; no dual-wielding. Armor is optional, but you can't use more than
 * one. You can buy 0-2 rings (at most one for each hand). You must use any items you buy. The shop
 * only has one of each item, so you can't buy, for example, two rings of Damage +3.
 *
 * <p>For example, suppose you have 8 hit points, 5 damage, and 5 armor, and that the boss has 12
 * hit points, 7 damage, and 2 armor:
 *
 * <ul>
 *   <li>The player deals 5-2 = 3 damage; the boss goes down to 9 hit points.
 *   <li>The boss deals 7-5 = 2 damage; the player goes down to 6 hit points.
 *   <li>The player deals 5-2 = 3 damage; the boss goes down to 6 hit points.
 *   <li>The boss deals 7-5 = 2 damage; the player goes down to 4 hit points.
 *   <li>The player deals 5-2 = 3 damage; the boss goes down to 3 hit points.
 *   <li>The boss deals 7-5 = 2 damage; the player goes down to 2 hit points.
 *   <li>The player deals 5-2 = 3 damage; the boss goes down to 0 hit points.
 * </ul>
 *
 * In this scenario, the player wins! (Barely.)
 *
 * <h2>Part One</h2>
 *
 * You have 100 hit points. The boss's actual stats are in your puzzle input. What is the least
 * amount of gold you can spend and still win the fight?
 */
public class Year2015Day21 extends AbstractDay {

  final List<Item> weapons =
      List.of(
          new Item("Dagger", 8, 4, 0),
          new Item("Shortsword", 10, 5, 0),
          new Item("Warhammer", 25, 6, 0),
          new Item("Longsword", 40, 7, 0),
          new Item("Greataxe", 74, 8, 0));

  final List<Item> armor =
      List.of(
          new Item("Leather", 13, 0, 1),
          new Item("Chainmail", 31, 0, 2),
          new Item("Splintmail", 53, 0, 3),
          new Item("Bandedmail", 75, 0, 4),
          new Item("Platemail", 102, 0, 5));

  final List<Item> rings =
      List.of(
          new Item("Damage +1", 25, 1, 0),
          new Item("Damage +2", 50, 2, 0),
          new Item("Damage +3", 100, 3, 0),
          new Item("Defense +1", 20, 0, 1),
          new Item("Defense +2", 40, 0, 2),
          new Item("Defense +3", 80, 0, 3));

  final List<Inventory> allCombinations;

  public Year2015Day21() {
    super(2015, 21);
    allCombinations = generateAllCombinations();
  }

  List<Inventory> generateAllCombinations() {
    List<Inventory> list = new ArrayList<>();
    for (Item weapon : weapons) {
      list.add(new Inventory(weapon, null, null, null));
      for (Item armor : armor) {
        list.add(new Inventory(weapon, armor, null, null));
        for (Item ring1 : rings) {
          list.add(new Inventory(weapon, armor, ring1, null));
          for (Item ring2 : rings) {
            if (ring1 != ring2) {
              list.add(new Inventory(weapon, armor, ring1, ring2));
            }
          }
        }
      }
      for (Item ring1 : rings) {
        list.add(new Inventory(weapon, null, ring1, null));
        for (Item ring2 : rings) {
          if (ring1 != ring2) {
            list.add(new Inventory(weapon, null, ring1, ring2));
          }
        }
      }
    }

    list.sort(Comparator.comparing(Inventory::getCost));

    return list;
  }

  @Override
  public String solvePart1() {
    BossFight bf =
        allCombinations.stream()
            .map(i -> new BossFight(i.getDamage(), i.getArmor(), i.getCost()).fight())
            .filter(b -> b.hasPlayerWon())
            .findFirst()
            .orElse(null);

    return Integer.toString(bf.cost);
  }

  @Override
  public String solvePart2() {
    BossFight bf =
        IntStream.range(1, allCombinations.size())
            .mapToObj(i -> allCombinations.get(allCombinations.size() - i))
            .map(i -> new BossFight(i.getDamage(), i.getArmor(), i.getCost()).fight())
            .filter(b -> !b.hasPlayerWon())
            .findFirst()
            .orElse(null);
    return Integer.toString(bf.cost);
  }

  public static void main(String[] args) {
    var today = new Year2015Day21();
    System.out.println(today.solvePart1());
    System.out.println(today.solvePart2());
  }

  static class BossFight {
    private final int pDamage;
    private final int pArmor;
    private int pHealth = 100;

    private final int bDamage = 8;
    private final int bArmor = 1;
    private int bHealth = 104;

    private boolean isPlayersTurn = true;

    private final int cost;

    BossFight(int pDamage, int pArmor, int cost) {
      this.pDamage = pDamage;
      this.pArmor = pArmor;
      this.cost = cost;
    }

    boolean isFightOver() {
      return pHealth <= 0 || bHealth <= 0;
    }

    boolean hasPlayerWon() {
      return pHealth > 0 && bHealth <= 0;
    }

    boolean hasBossWon() {
      return bHealth > 0 && pHealth <= 0;
    }

    BossFight fight() {
      while (!isFightOver()) {
        if (isPlayersTurn) {
          bHealth -= Math.max(pDamage - bArmor, 1);
        } else {
          pHealth -= Math.max(bDamage - pArmor, 1);
        }
        isPlayersTurn = !isPlayersTurn;
      }
      return this;
    }
  }

  static class Item {
    private String name;
    private int cost;
    private int damage;
    private int armor;

    Item(String name, int cost, int damage, int armor) {
      this.name = name;
      this.cost = cost;
      this.damage = damage;
      this.armor = armor;
    }

    public int getCost() {
      return cost;
    }

    public int getDamage() {
      return damage;
    }

    public int getArmor() {
      return armor;
    }
  }

  static class Inventory {
    private final Item sword;
    private final Item armor;
    private final Item ring1;
    private final Item ring2;

    Inventory(Item sword, Item armor, Item ring1, Item ring2) {
      this.sword = Objects.requireNonNull(sword);
      this.armor = armor;
      this.ring1 = ring1;
      this.ring2 = ring2;
    }

    Inventory withArmor(Item armor) {
      return new Inventory(sword, armor, ring1, ring2);
    }

    Inventory withRing1(Item ring1) {
      return new Inventory(sword, armor, ring1, ring2);
    }

    Inventory withRing2(Item ring2) {
      return new Inventory(sword, armor, ring1, ring2);
    }

    private Stream<Item> stream() {
      return Stream.of(sword, armor, ring1, ring2);
    }

    public int getDamage() {
      return stream().filter(Objects::nonNull).mapToInt(Item::getDamage).sum();
    }

    public int getArmor() {
      return stream().filter(Objects::nonNull).mapToInt(Item::getArmor).sum();
    }

    public int getCost() {
      return stream().filter(Objects::nonNull).mapToInt(Item::getCost).sum();
    }
  }
}
