package net.bassmann.adventofcode.year2015.day14;

import java.util.List;
import java.util.stream.Collectors;
import net.bassmann.adventofcode.common.AbstractDay;

/**
 * Day 14: Reindeer Olympics
 *
 * <p>This year is the Reindeer Olympics! Reindeer can fly at high speeds, but must rest
 * occasionally to recover their energy. Santa would like to know which of his reindeer is fastest,
 * and so he has them race.
 *
 * <p>Reindeer can only either be flying (always at their top speed) or resting (not moving at all),
 * and always spend whole seconds in either state.
 *
 * <p>For example, suppose you have the following Reindeer:
 *
 * <ul>
 *   <li>Comet can fly 14 km/s for 10 seconds, but then must rest for 127 seconds.
 *   <li>Dancer can fly 16 km/s for 11 seconds, but then must rest for 162 seconds.
 * </ul>
 *
 * After one second, Comet has gone 14 km, while Dancer has gone 16 km. After ten seconds, Comet has
 * gone 140 km, while Dancer has gone 160 km. On the eleventh second, Comet begins resting (staying
 * at 140 km), and Dancer continues on for a total distance of 176 km. On the 12th second, both
 * reindeer are resting. They continue to rest until the 138th second, when Comet flies for another
 * ten seconds. On the 174th second, Dancer flies for another 11 seconds.
 *
 * <p>In this example, after the 1000th second, both reindeer are resting, and Comet is in the lead
 * at 1120 km (poor Dancer has only gotten 1056 km by that point). So, in this situation, Comet
 * would win (if the race ended at 1000 seconds).
 *
 * <h2>Part One</h2>
 *
 * Given the descriptions of each reindeer (in your puzzle input), after exactly 2503 seconds, what
 * distance has the winning reindeer traveled?
 *
 * <h2>Part Two</h2>
 *
 * Seeing how reindeer move in bursts, Santa decides he's not pleased with the old scoring system.
 *
 * <p>Instead, at the end of each second, he awards one point to the reindeer currently in the lead.
 * (If there are multiple reindeer tied for the lead, they each get one point.) He keeps the
 * traditional 2503 second time limit, of course, as doing otherwise would be entirely ridiculous.
 *
 * <p>Given the example reindeer from above, after the first second, Dancer is in the lead and gets
 * one point. He stays in the lead until several seconds into Comet's second burst: after the 140th
 * second, Comet pulls into the lead and gets his first point. Of course, since Dancer had been in
 * the lead for the 139 seconds before that, he has accumulated 139 points by the 140th second.
 *
 * <p>After the 1000th second, Dancer has accumulated 689 points, while poor Comet, our old
 * champion, only has 312. So, with the new scoring system, Dancer would win (if the race ended at
 * 1000 seconds).
 *
 * <p>Again given the descriptions of each reindeer (in your puzzle input), after exactly 2503
 * seconds, how many points does the winning reindeer have?
 */
public class Year2015Day14 extends AbstractDay {

  private List<Reindeer> reindeerList = null;

  public Year2015Day14() {
    super(2015, 14);
  }

  @Override
  public String solvePart1() {
    int winningDistance = getWinningDistance(2503);
    return Integer.toString(winningDistance);
  }

  @Override
  public String solvePart2() {
    ReindeerRace race = new ReindeerRace(getReindeerList());
    int maxPoints = race.getMaxPointsAfter(2503);
    return Integer.toString(maxPoints);
  }

  private List<Reindeer> getReindeerList() {
    if (reindeerList == null) {
      reindeerList =
          getRiddleInput().lines().map(Reindeer::fromString).collect(Collectors.toList());
    }
    return reindeerList;
  }

  private int getWinningDistance(int seconds) {
    return getReindeerList()
        .stream()
        .mapToInt(r -> r.getDistanceAfterSeconds(seconds))
        .max()
        .orElse(-1);
  }
}
