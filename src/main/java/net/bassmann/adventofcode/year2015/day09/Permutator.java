package net.bassmann.adventofcode.year2015.day09;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * A Steinhaus–Johnson–Trotter like permutation generator; can be used to generate all n!
 * permutations of a list from 0 to n-1.
 */
class Permutator implements Iterator<List<Integer>> {

  enum Direction {
    LEFT,
    RIGHT
  }

  private final int size;

  /** The permutator keeps track of how many permutations are left. */
  private int permutationsLeft;

  /** The actual order of values, simple mapping of position -> value. */
  private final int[] values;
  /** The positions of the values, a mapping of value -> pos in output. */
  private final int[] positions;
  /** The direction of each value, a mapping of value -> direction. */
  private final Direction[] directions;

  Permutator(int size) {
    this.size = size;
    permutationsLeft = -1;
    values = new int[size];
    positions = new int[size];
    directions = new Direction[size];
    IntStream.range(0, size).forEach(this::initPosition);
  }

  private void initPosition(int i) {
    values[i] = i;
    positions[i] = i;
    directions[i] = Direction.LEFT;
  }

  @Override
  public boolean hasNext() {
    return permutationsLeft != 0;
  }

  @Override
  public List<Integer> next() {
    if (!hasNext()) {
      throw new NoSuchElementException();
    }

    if (isFirstCallToNext()) {
      permutationsLeft = factorial(size) - 1;
      return createOutputList();
    }

    permutateOnce();
    permutationsLeft--;
    return createOutputList();
  }

  private boolean isFirstCallToNext() {
    return permutationsLeft == -1;
  }

  private void permutateOnce() {
    final int k = findLargestMobileValue();
    swapWithAdjacent(k);
    reverseDirectionOfAllLargerThan(k);
  }

  int findLargestMobileValue() {
    for (int value = size - 1; value >= 0; value--) {
      if (isMobile(value)) {
        return value;
      }
    }
    throw new NoSuchElementException();
  }

  /**
   * A directed integer is said to be mobile if it is greater than its immediate neighbor in the
   * direction it is looking at.
   *
   * @param value of the directed integer, which is checked if it is mobile.
   * @return {@code true} if the directed integer is mobile.
   */
  boolean isMobile(int value) {
    final int adjacentPos = getLookingAtPos(value);
    return adjacentPos >= 0 && adjacentPos < size && value > values[adjacentPos];
  }

  private void swapWithAdjacent(int value) {
    final Direction d = directions[value];
    final int adjacent = values[getLookingAtPos(value)];

    if (d == Direction.LEFT) {
      positions[value]--;
      positions[adjacent]++;
    } else {
      positions[value]++;
      positions[adjacent]--;
    }

    values[positions[value]] = value;
    values[positions[adjacent]] = adjacent;
  }

  private void reverseDirectionOfAllLargerThan(int value) {
    for (int i = value + 1; i < size; i++) {
      directions[i] = directions[i] == Direction.LEFT ? Direction.RIGHT : Direction.LEFT;
    }
  }

  /** Returns the the position of the value, the given value is looking at. */
  private int getLookingAtPos(int value) {
    final int pos = positions[value];
    final Direction d = directions[value];
    return d == Direction.LEFT ? pos - 1 : pos + 1;
  }

  private List<Integer> createOutputList() {
    return IntStream.of(values).boxed().collect(Collectors.toList());
  }

  /** We need to calculate n! to know how many permutations there will be. */
  static int factorial(int n) {
    return IntStream.rangeClosed(1, n).reduce(1, (i1, i2) -> i1 * i2);
  }
}
