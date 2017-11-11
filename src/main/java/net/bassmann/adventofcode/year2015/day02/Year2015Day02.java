package net.bassmann.adventofcode.year2015.day02;

import static java.util.stream.Collectors.toList;

import java.util.List;
import net.bassmann.adventofcode.common.AbstractDay;

/**
 * Day 2: I Was Told There Would Be No Math.
 *
 * <p>The elves are running low on wrapping paper, and so they need to submit an order for more.
 * They have a list of the dimensions (length l, width w, and height h) of each present, and only
 * want to order exactly as much as they need.
 *
 * <p>Fortunately, every present is a box (a perfect right rectangular prism), which makes
 * calculating the required wrapping paper for each gift a little easier: find the surface area of
 * the box, which is 2*l*w + 2*w*h + 2*h*l. The elves also need a little extra paper for each
 * present: the area of the smallest side.
 *
 * <h2>Part 1</h2>
 *
 * All numbers in the elves' list are in feet. How many total square feet of wrapping paper should
 * they order?
 *
 * <h2>Part 2</h2>
 *
 * The elves are also running low on ribbon. Ribbon is all the same width, so they only have to
 * worry about the length they need to order, which they would again like to be exact.
 *
 * <p>The ribbon required to wrap a present is the shortest distance around its sides, or the
 * smallest perimeter of any one face. Each present also requires a bow made out of ribbon as well;
 * the feet of ribbon required for the perfect bow is equal to the cubic feet of volume of the
 * present. Don't ask how they tie the bow, though; they'll never tell.
 *
 * <p>How many total feet of ribbon should they order?
 */
public class Year2015Day02 extends AbstractDay {

  private List<Package> packages;

  public Year2015Day02() {
    super(2015, 2);
  }

  /** Split the input into package objects. */
  private List<Package> getPackages() {
    if (packages == null) {
      packages = getRiddleInput().lines().map(Package::fromString).collect(toList());
    }
    return packages;
  }

  @Override
  public String solvePart1() {
    int totalPaperNeeded = getTotalPaperNeeded();
    return Integer.toString(totalPaperNeeded);
  }

  @Override
  public String solvePart2() {
    int totalRibbonNeeded = getTotalRibbonNeeded();
    return Integer.toString(totalRibbonNeeded);
  }

  private int getTotalPaperNeeded() {
    return getPackages().stream().mapToInt(Package::getAreaOfPackingPaperNeeded).sum();
  }

  private int getTotalRibbonNeeded() {
    return getPackages().stream().mapToInt(Package::getLengthOfRibbonNeeded).sum();
  }
}
