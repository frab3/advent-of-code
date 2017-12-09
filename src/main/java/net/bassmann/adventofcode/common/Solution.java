package net.bassmann.adventofcode.common;

/**
 * Sometimes both parts can be solved within one function, so I need a POJO to return both values.
 */
public class Solution {

  private final String partOne;
  private final String partTwo;

  public Solution(String partOne, String partTwo) {
    this.partOne = partOne;
    this.partTwo = partTwo;
  }

  public Solution(int partOne, int partTwo) {
    this(Integer.toString(partOne), Integer.toString(partTwo));
  }

  public String getPartOne() {
    return partOne;
  }

  public String getPartTwo() {
    return partTwo;
  }
}
