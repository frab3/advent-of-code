package net.bassmann.adventofcode.year2015.day18;

import net.bassmann.adventofcode.common.AbstractDay;

/**
 * Day 18: Like a GIF For Your Yard.
 *
 * <p>After the million lights incident, the fire code has gotten stricter: now, at most ten
 * thousand lights are allowed. You arrange them in a 100x100 grid.
 *
 * <p>Never one to let you down, Santa again mails you instructions on the ideal lighting
 * configuration. With so few lights, he says, you'll have to resort to animation.
 *
 * <p>Start by setting your lights to the included initial configuration (your puzzle input). A #
 * means "on", and a . means "off".
 *
 * <p>Then, animate your grid in steps, where each step decides the next configuration based on the
 * current one. Each light's next state (either on or off) depends on its current state and the
 * current states of the eight lights adjacent to it (including diagonals). Lights on the edge of
 * the grid might have fewer than eight neighbors; the missing ones always count as "off".
 *
 * <p>For example, in a simplified 6x6 grid, the light marked A has the neighbors numbered 1 through
 * 8, and the light marked B, which is on an edge, only has the neighbors marked 1 through 5:
 *
 * <pre>
 * 1B5...
 * 234...
 * ......
 * ..123.
 * ..8A4.
 * ..765.
 * </pre>
 *
 * The state a light should have next is based on its current state (on or off) plus the number of
 * neighbors that are on:
 *
 * <ul>
 *   <li>A light which is on stays on when 2 or 3 neighbors are on, and turns off otherwise.
 *   <li>A light which is off turns on if exactly 3 neighbors are on, and stays off otherwise.
 * </ul>
 *
 * All of the lights update simultaneously; they all consider the same current state before moving
 * to the next.
 *
 * <p>Here's a few steps from an example configuration of another 6x6 grid:
 *
 * <pre>
 * Initial state:
 * .#.#.#
 * ...##.
 * #....#
 * ..#...
 * #.#..#
 * ####..
 *
 * After 1 step:
 * ..##..
 * ..##.#
 * ...##.
 * ......
 * #.....
 * #.##..
 *
 * After 2 steps:
 * ..###.
 * ......
 * ..###.
 * ......
 * .#....
 * .#....
 *
 * After 3 steps:
 * ...#..
 * ......
 * ...#..
 * ..##..
 * ......
 * ......
 *
 * After 4 steps:
 * ......
 * ......
 * ..##..
 * ..##..
 * ......
 * ......
 * </pre>
 *
 * After 4 steps, this example has four lights on.
 *
 * <h2>Part One</h2>
 *
 * In your grid of 100x100 lights, given your initial configuration, how many lights are on after
 * 100 steps?
 */
public class Year2015Day18 extends AbstractDay {

  public Year2015Day18() {
    super(2015, 18);
  }

  @Override
  public String solvePart1() {
    return null;
  }

  @Override
  public String solvePart2() {
    return null;
  }
}
