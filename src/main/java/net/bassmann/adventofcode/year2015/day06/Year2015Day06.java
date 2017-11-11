package net.bassmann.adventofcode.year2015.day06;

import net.bassmann.adventofcode.common.AbstractDay;

/**
 * Day 6: Probably a Fire Hazard.
 *
 * <p>Because your neighbors keep defeating you in the holiday house decorating contest year after
 * year, you've decided to deploy one million lights in a 1000x1000 grid.
 *
 * <p>Furthermore, because you've been especially nice this year, Santa has mailed you instructions
 * on how to display the ideal lighting configuration.
 *
 * <p>Lights in your grid are numbered from 0 to 999 in each direction; the lights at each corner
 * are at 0,0, 0,999, 999,999, and 999,0. The instructions include whether to turn on, turn off, or
 * toggle various inclusive ranges given as coordinate pairs. Each coordinate pair represents
 * opposite corners of a rectangle, inclusive; a coordinate pair like 0,0 through 2,2 therefore
 * refers to 9 lights in a 3x3 square. The lights all start turned off.
 *
 * <p>To defeat your neighbors this year, all you have to do is set up your lights by doing the
 * instructions Santa sent you in order.
 *
 * <h2>Part One</h2>
 *
 * After following the instructions, how many lights are lit?
 *
 * <h2>Part Two</h2>
 *
 * You just finish implementing your winning light pattern when you realize you mistranslated
 * Santa's message from Ancient Nordic Elvish.
 *
 * <p>The light grid you bought actually has individual brightness controls; each light can have a
 * brightness of zero or more. The lights all start at zero.
 *
 * <p>The phrase turn on actually means that you should increase the brightness of those lights by
 * 1.
 *
 * <p>The phrase turn off actually means that you should decrease the brightness of those lights by
 * 1, to a minimum of zero.
 *
 * <p>The phrase toggle actually means that you should increase the brightness of those lights by 2.
 *
 * <p>What is the total brightness of all lights combined after following Santa's instructions?
 */
public class Year2015Day06 extends AbstractDay {

  public Year2015Day06() {
    super(2015, 6);
  }

  @Override
  public String solvePart1() {
    LightGrid grid = new SimpleLightGrid(1000);
    getRiddleInput().lines().forEachOrdered(grid::executeCommand);
    int countOn = grid.count();
    return Integer.toString(countOn);
  }

  @Override
  public String solvePart2() {
    LightGrid grid = new BrightnessLightGrid(1000);
    getRiddleInput().lines().forEachOrdered(grid::executeCommand);
    int countOn = grid.count();
    return Integer.toString(countOn);
  }
}
