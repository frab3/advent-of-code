package net.bassmann.adventofcode.year2015.day04;

import java.util.stream.IntStream;
import net.bassmann.adventofcode.common.AbstractDay;

/**
 * Day 4: The Ideal Stocking Stuffer.
 *
 * <p>Santa needs help mining some AdventCoins (very similar to bitcoins) to use as gifts for all
 * the economically forward-thinking little girls and boys.
 *
 * <p>To do this, he needs to find MD5 hashes which, in hexadecimal, start with at least five
 * zeroes. The input to the MD5 hash is some secret key (your puzzle input, given below) followed by
 * a number in decimal. To mine AdventCoins, you must find Santa the lowest positive number (no
 * leading zeroes: 1, 2, 3, ...) that produces such a hash.
 *
 * <h2>Part Two</h2>
 *
 * Now find one that starts with six zeroes.
 */
public class Year2015Day04 extends AbstractDay {

  private final CoinGenerator coinGenerator = new CoinGenerator(getRiddleInput().firstLine());

  public Year2015Day04() {
    super(2015, 4);
  }

  @Override
  public String solvePart1() {
    int coinInput =
        IntStream.iterate(1, i -> i + 1).filter(coinGenerator::isAdventCoin).findFirst().orElse(-1);
    return Integer.toString(coinInput);
  }

  @Override
  public String solvePart2() {
    int coinInput =
        IntStream.iterate(254575, i -> i + 1)
            .filter(coinGenerator::isImprovedAdventCoin)
            .findFirst()
            .orElse(-1);
    return Integer.toString(coinInput);
  }
}
