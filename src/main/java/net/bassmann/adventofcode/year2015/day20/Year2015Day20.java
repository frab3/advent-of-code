package net.bassmann.adventofcode.year2015.day20;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import net.bassmann.adventofcode.common.AbstractDay;

/**
 * Day 20: Infinite Elves and Infinite Houses.
 *
 * <p>To keep the Elves busy, Santa has them deliver some presents by hand, door-to-door. He sends
 * them down a street with infinite houses numbered sequentially: 1, 2, 3, 4, 5, and so on.
 *
 * <p>Each Elf is assigned a number, too, and delivers presents to houses based on that number:
 *
 * <ul>
 *   <li>The first Elf (number 1) delivers presents to every house: 1, 2, 3, 4, 5, ....
 *   <li>The second Elf (number 2) delivers presents to every second house: 2, 4, 6, 8, 10, ....
 *   <li>Elf number 3 delivers presents to every third house: 3, 6, 9, 12, 15, ....
 * </ul>
 *
 * There are infinitely many Elves, numbered starting with 1. Each Elf delivers presents equal to
 * ten times his or her number at each house.
 *
 * <p>So, the first nine houses on the street end up like this:
 *
 * <pre>
 * House 1 got 10 presents.
 * House 2 got 30 presents.
 * House 3 got 40 presents.
 * House 4 got 70 presents.
 * House 5 got 60 presents.
 * House 6 got 120 presents.
 * House 7 got 80 presents.
 * House 8 got 150 presents.
 * House 9 got 130 presents.
 * </pre>
 *
 * The first house gets 10 presents: it is visited only by Elf 1, which delivers 1 * 10 = 10
 * presents. The fourth house gets 70 presents, because it is visited by Elves 1, 2, and 4, for a
 * total of 10 + 20 + 40 = 70 presents.
 *
 * <h2>Part One</h2>
 *
 * What is the lowest house number of the house to get at least as many presents as the number in
 * your puzzle input?
 *
 * <h2>Part Two</h2>
 *
 * The Elves decide they don't want to visit an infinite number of houses. Instead, each Elf will
 * stop after delivering presents to 50 houses. To make up for it, they decide to deliver presents
 * equal to eleven times their number at each house.
 *
 * <p>With these changes, what is the new lowest house number of the house to get at least as many
 * presents as the number in your puzzle input?
 */
public class Year2015Day20 extends AbstractDay {

  public Year2015Day20() {
    super(2015, 20);
  }

  @Override
  public String solvePart1() {
    int input = Integer.parseInt(getRiddleInput().firstLine());
    int house = findLowestHouseNumber(input);
    return Integer.toString(house);
  }

  @Override
  public String solvePart2() {
    int input = Integer.parseInt(getRiddleInput().firstLine());
    int house = findLowestHouseNumberWithLimitiedElves(input);
    return Integer.toString(house);
  }

  static int findLowestHouseNumber(int presentsToDeliver) {
    int limit = presentsToDeliver / 10;
    int currentHouse = 0;
    int sum;
    do {
      sum = divisors(++currentHouse).sum();
    } while (sum < limit);
    return currentHouse;
  }

  static int findLowestHouseNumberWithLimitiedElves(int presentsToDeliver) {
    List<Integer> elvCount = new ArrayList<>();
    int realLimit = presentsToDeliver / 11;
    int currentHouse = 0;
    int sum;
    do {
      elvCount.add(0);
      sum = 0;
      int[] f = divisors(++currentHouse).toArray();
      for (int i : f) {
        if (elvCount.get(i - 1) < 50) {
          sum += i;
          elvCount.set(i - 1, elvCount.get(i - 1) + 1);
        }
      }

    } while (sum < realLimit);
    return currentHouse;
  }

  /**
   * Sigma function is the sum of all divisors of a given number.
   */
  static int sigma(int number) {
    return divisors(number).sum();
  }

  /**
   * Generates an unordered IntStream of all the divisors of a given number.
   */
  static IntStream divisors(int number) {
    int squareRoot = (int) Math.sqrt(number) + 1;
    return IntStream.range(1, squareRoot)
        .filter(i -> number % i == 0)
        .flatMap(i -> i * i != number ? IntStream.of(i, number / i) : IntStream.of(i));
  }
}
