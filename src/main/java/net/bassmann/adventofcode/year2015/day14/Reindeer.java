package net.bassmann.adventofcode.year2015.day14;

class Reindeer {

  private final String name;
  private final int speed;
  private final int flyTime;
  private final int restTime;

  Reindeer(String name, int speed, int flyTime, int restTime) {
    this.name = name;
    this.speed = speed;
    this.flyTime = flyTime;
    this.restTime = restTime;
  }

  String getName() {
    return name;
  }

  int getDistanceAfterSeconds(int seconds) {
    final int totalTime = flyTime + restTime;
    int dist = speed * flyTime * (seconds / totalTime);
    final int rest = seconds % totalTime;
    if (rest > flyTime) {
      dist += speed * flyTime;
    } else {
      dist += speed * rest;
    }
    return dist;
  }

  static Reindeer fromString(String input) {
    String[] split = input.split(" ");
    return new Reindeer(
        split[0],
        Integer.parseInt(split[3]),
        Integer.parseInt(split[6]),
        Integer.parseInt(split[13]));
  }
}
