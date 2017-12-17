package net.bassmann.adventofcode.year2017.day16;

import static java.lang.Integer.parseInt;

class SpinDanceMove implements DanceMove {

  private final int distance;

  SpinDanceMove(String danceMove) {
    this.distance = parseInt(danceMove.substring(1));
  }

  @Override
  public void doMove(char[] programs) {
    rotate(programs, distance);
  }

  /**
   * Simple algorithm to rotate an array. First reverse the two parts that should swap individually,
   * then reverse the whole array.
   *
   * @param programs the array to rotate.
   * @param distance number of steps the elements in the array are rotated forward.
   */
  private static void rotate(char[] programs, int distance) {
    int border = programs.length - distance;
    reverse(programs, 0, border - 1);
    reverse(programs, border, programs.length - 1);
    reverse(programs, 0, programs.length - 1);
  }

  /**
   * Reverse a part of an array.
   *
   * @param programs the array with the part to reverse.
   * @param start first element in the array to reverse.
   * @param end last element (inclusive) in the array to reverse.
   */
  private static void reverse(char[] programs, int start, int end) {
    char temp;
    while (start < end) {
      temp = programs[start];
      programs[start] = programs[end];
      programs[end] = temp;
      start++;
      end--;
    }
  }
}
