package net.bassmann.adventofcode.year2021.day06;

import static java.lang.System.arraycopy;

import java.util.Arrays;

public class LanternFishSimulator {

  long[] fishBuckets = new long[9];

  LanternFishSimulator(int[] initialValue) {
    putFishiesInBuckets(initialValue);
  }

  private void putFishiesInBuckets(int[] fishes) {
    Arrays.stream(fishes).forEach(fish -> fishBuckets[fish]++);
  }

  void doDay() {
    long newFish = fishBuckets[0];
    arraycopy(fishBuckets, 1, fishBuckets, 0, fishBuckets.length - 1);
    fishBuckets[6] += newFish;
    fishBuckets[8] = newFish;
  }

  long size() {
    return Arrays.stream(fishBuckets).sum();
  }
}
