package net.bassmann.adventofcode.year2015.day19;

import java.util.List;
import java.util.stream.Collectors;
import net.bassmann.adventofcode.common.AbstractDay;

/**
 * Day 19: Medicine for Rudolph.
 *
 * <p>Rudolph the Red-Nosed Reindeer is sick! His nose isn't shining very brightly, and he needs
 * medicine.
 *
 * <p>Red-Nosed Reindeer biology isn't similar to regular reindeer biology; Rudolph is going to need
 * custom-made medicine. Unfortunately, Red-Nosed Reindeer chemistry isn't similar to regular
 * reindeer chemistry, either.
 *
 * <p>The North Pole is equipped with a Red-Nosed Reindeer nuclear fusion/fission plant, capable of
 * constructing any Red-Nosed Reindeer molecule you need. It works by starting with some input
 * molecule and then doing a series of replacements, one per step, until it has the right molecule.
 *
 * <p>However, the machine has to be calibrated before it can be used. Calibration involves
 * determining the number of molecules that can be generated in one step from a given starting
 * point.
 *
 * <p>For example, imagine a simpler machine that supports only the following replacements:
 *
 * <pre>
 * H => HO
 * H => OH
 * O => HH
 * </pre>
 *
 * Given the replacements above and starting with HOH, the following molecules could be generated:
 *
 * <ul>
 *   <li>HOOH (via H => HO on the first H).
 *   <li>HOHO (via H => HO on the second H).
 *   <li>OHOH (via H => OH on the first H).
 *   <li>HOOH (via H => OH on the second H).
 *   <li>HHHH (via O => HH).
 * </ul>
 *
 * So, in the example above, there are 4 distinct molecules (not five, because HOOH appears twice)
 * after one replacement from HOH. Santa's favorite molecule, HOHOHO, can become 7 distinct
 * molecules (over nine replacements: six from H, and three from O).
 *
 * <p>The machine replaces without regard for the surrounding characters. For example, given the
 * string H2O, the transition H => OO would result in OO2O.
 *
 * <p>Your puzzle input describes all of the possible replacements and, at the bottom, the medicine
 * molecule for which you need to calibrate the machine.
 *
 * <h2>Part One</h2>
 *
 * How many distinct molecules can be created after all the different ways you can do one
 * replacement on the medicine molecule?
 *
 * <h2>Part Two</h2>
 *
 * Now that the machine is calibrated, you're ready to begin molecule fabrication.
 *
 * <p>Molecule fabrication always begins with just a single electron, e, and applying replacements
 * one at a time, just like the ones during calibration.
 *
 * <p>For example, suppose you have the following replacements:
 *
 * <pre>
 * e => H
 * e => O
 * H => HO
 * H => OH
 * O => HH
 * </pre>
 *
 * If you'd like to make HOH, you start with e, and then make the following replacements:
 *
 * <ul>
 *   <li>e => O to get O
 *   <li>O => HH to get HH
 *   <li>H => OH (on the second H) to get HOH
 * </ul>
 *
 * So, you could make HOH after 3 steps. Santa's favorite molecule, HOHOHO, can be made in 6 steps.
 *
 * <p>How long will it take to make the medicine? Given the available replacements and the medicine
 * molecule in your puzzle input, what is the fewest number of steps to go from e to the medicine
 * molecule?
 */
public class Year2015Day19 extends AbstractDay {

  public Year2015Day19() {
    super(2015, 19);
  }

  @Override
  public String solvePart1() {
    MoleculeMachine m = new MoleculeMachine(getReplacementRules(), getMolecule());
    int count = m.countOneReplacements2();
    return Integer.toString(count);
  }

  @Override
  public String solvePart2() {
    long solution = MoleculeMachine.getAskalskisFormula(getMolecule());
    return Long.toString(solution);
  }

  private List<ReplacementRule> getReplacementRules() {
    return getRiddleInput()
        .lines()
        .filter(s -> s.contains("=>"))
        .map(ReplacementRule::fromString)
        .collect(Collectors.toList());
  }

  private String getMolecule() {
    List<String> input = getRiddleInput().asList();
    return input.get(input.size() - 1);
  }
}
