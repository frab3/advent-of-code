package net.bassmann.adventofcode.year2015.day13;

import java.util.List;
import net.bassmann.adventofcode.common.AbstractDay;
import net.bassmann.adventofcode.year2015.day09.Permutator;

/**
 * Day 13: Knights of the Dinner Table.
 *
 * <p>In years past, the holiday feast with your family hasn't gone so well. Not everyone gets
 * along! This year, you resolve, will be different. You're going to find the optimal seating
 * arrangement and avoid all those awkward conversations.
 *
 * <p>You start by writing up a list of everyone invited and the amount their happiness would
 * increase or decrease if they were to find themselves sitting next to each other person. You have
 * a circular table that will be just big enough to fit everyone comfortably, and so each person
 * will have exactly two neighbors.
 *
 * <p>For example, suppose you have only four attendees planned, and you calculate their potential
 * happiness as follows:
 *
 * <pre>
 * Alice would gain 54 happiness units by sitting next to Bob.
 * Alice would lose 79 happiness units by sitting next to Carol.
 * Alice would lose 2 happiness units by sitting next to David.
 * Bob would gain 83 happiness units by sitting next to Alice.
 * Bob would lose 7 happiness units by sitting next to Carol.
 * Bob would lose 63 happiness units by sitting next to David.
 * Carol would lose 62 happiness units by sitting next to Alice.
 * Carol would gain 60 happiness units by sitting next to Bob.
 * Carol would gain 55 happiness units by sitting next to David.
 * David would gain 46 happiness units by sitting next to Alice.
 * David would lose 7 happiness units by sitting next to Bob.
 * David would gain 41 happiness units by sitting next to Carol.
 * </pre>
 *
 * Then, if you seat Alice next to David, Alice would lose 2 happiness units (because David talks so
 * much), but David would gain 46 happiness units (because Alice is such a good listener), for a
 * total change of 44.
 *
 * <p>If you continue around the table, you could then seat Bob next to Alice (Bob gains 83, Alice
 * gains 54). Finally, seat Carol, who sits next to Bob (Carol gains 60, Bob loses 7) and David
 * (Carol gains 55, David gains 41). The arrangement looks like this:
 *
 * <pre>
 *      +41 +46
 * +55   David    -2
 * Carol       Alice
 * +60    Bob    +54
 *      -7  +83
 * </pre>
 *
 * After trying every other seating arrangement in this hypothetical scenario, you find that this
 * one is the most optimal, with a total change in happiness of 330.
 *
 * <h2>Part One</h2>
 *
 * What is the total change in happiness for the optimal seating arrangement of the actual guest
 * list?
 */
public class Year2015Day13 extends AbstractDay {

  public Year2015Day13() {
    super(2015, 13);
  }

  @Override
  public String solvePart1() {
    int max = getOptimalHappiness(getRiddleInput().asList());
    return Integer.toString(max);
  }

  @Override
  public String solvePart2() {
    int max = getOptimalHappinessIncludingMe(getRiddleInput().asList());
    return Integer.toString(max);
  }

  static int getOptimalHappiness(List<String> input) {
    DinnerParty party = new DinnerParty();
    input.forEach(party::addGuest);
    Permutator perm = new Permutator(party.size() - 1);

    int max = Integer.MIN_VALUE;
    while (perm.hasNext()) {
      int happiness = party.getHappiness(perm.next());
      max = Math.max(max, happiness);
    }
    return max;
  }

  static int getOptimalHappinessIncludingMe(List<String> input) {
    DinnerParty party = new DinnerParty();
    input.forEach(party::addGuest);
    Permutator perm = new Permutator(party.size());

    int max = Integer.MIN_VALUE;
    while (perm.hasNext()) {
      int happiness = party.getHappinessIncludingMe(perm.next());
      max = Math.max(max, happiness);
    }
    return max;
  }
}
