package net.bassmann.adventofcode.year2015.day16;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import net.bassmann.adventofcode.common.AbstractDay;

/**
 * Day 16: Aunt Sue.
 *
 * <p>Your Aunt Sue has given you a wonderful gift, and you'd like to send her a thank you card.
 * However, there's a small problem: she signed it "From, Aunt Sue".
 *
 * <p>You have 500 Aunts named "Sue".
 *
 * <p>So, to avoid sending the card to the wrong person, you need to figure out which Aunt Sue
 * (which you conveniently number 1 to 500, for sanity) gave you the gift. You open the present and,
 * as luck would have it, good ol' Aunt Sue got you a My First Crime Scene Analysis Machine! Just
 * what you wanted. Or needed, as the case may be.
 *
 * <p>The My First Crime Scene Analysis Machine (MFCSAM for short) can detect a few specific
 * compounds in a given sample, as well as how many distinct kinds of those compounds there are.
 * According to the instructions, these are what the MFCSAM can detect:
 *
 * <ul>
 *   <li>children, by human DNA age analysis.
 *   <li>cats. It doesn't differentiate individual breeds.
 *   <li>Several seemingly random breeds of dog: samoyeds, pomeranians, akitas, and vizslas.
 *   <li>goldfish. No other kinds of fish.
 *   <li>trees, all in one group.
 *   <li>cars, presumably by exhaust or gasoline or something.
 *   <li>perfumes, which is handy, since many of your Aunts Sue wear a few kinds.
 * </ul>
 *
 * In fact, many of your Aunts Sue have many of these. You put the wrapping from the gift into the
 * MFCSAM. It beeps inquisitively at you a few times and then prints out a message on ticker tape:
 *
 * <ul>
 *   <li>children: 3
 *   <li>cats: 7
 *   <li>samoyeds: 2
 *   <li>pomeranians: 3
 *   <li>akitas: 0
 *   <li>vizslas: 0
 *   <li>goldfish: 5
 *   <li>trees: 3
 *   <li>cars: 2
 *   <li>perfumes: 1
 * </ul>
 *
 * You make a list of the things you can remember about each Aunt Sue. Things missing from your list
 * aren't zero - you simply don't remember the value.
 *
 * <h2>Part One</h2>
 *
 * What is the number of the Sue that got you the gift?
 *
 * <h2>Part Two</h2>
 *
 * As you're about to send the thank you note, something in the MFCSAM's instructions catches your
 * eye. Apparently, it has an outdated retroencabulator, and so the output from the machine isn't
 * exact values - some of them indicate ranges.
 *
 * <p>In particular, the cats and trees readings indicates that there are greater than that many
 * (due to the unpredictable nuclear decay of cat dander and tree pollen), while the pomeranians and
 * goldfish readings indicate that there are fewer than that many (due to the modial interaction of
 * magnetoreluctance).
 *
 * <p>What is the number of the real Aunt Sue?
 */
public class Year2015Day16 extends AbstractDay {

  private List<AuntSue> aunties;

  public Year2015Day16() {
    super(2015, 16);
  }

  @Override
  public String solvePart1() {
    final Map<String, Integer> scan = getScanResult();
    AuntSue coolSue = getAunties().stream().filter(a -> a.matchesScan(scan)).findAny().get();
    return Integer.toString(coolSue.getNumber());
  }

  @Override
  public String solvePart2() {
    final Map<String, Integer> scan = getScanResult();
    AuntSue coolSue = getAunties().stream().filter(a -> a.matchesScanRanges(scan)).findAny().get();
    return Integer.toString(coolSue.getNumber());
  }

  private List<AuntSue> getAunties() {
    if (aunties == null) {
      aunties = getRiddleInput().lines().map(AuntSue::fromString).collect(Collectors.toList());
    }
    return aunties;
  }

  private Map<String, Integer> getScanResult() {
    return Map.of(
        "children", 3,
        "cats", 7,
        "samoyeds", 2,
        "pomeranians", 3,
        "akitas", 0,
        "vizslas", 0,
        "goldfish", 5,
        "trees", 3,
        "cars", 2,
        "perfumes", 1);
  }
}
