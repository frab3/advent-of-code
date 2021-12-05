package net.bassmann.adventofcode.year2021.day02;

public class Submarine {

  long position;
  long depth;
  long aim;

  Submarine() {
    position = 0;
    depth = 0;
    aim = 0;
  }

  void parseCommand(String command) {
    String[] c = command.split(" ");
    switch (c[0]) {
      case "forward":
        position += Integer.parseInt(c[1]);
        break;
      case "up":
        depth -= Integer.parseInt(c[1]);
        if (depth < 0) depth = 0;
        break;
      case "down":
        depth += Integer.parseInt(c[1]);
        break;
      default:
        throw new IllegalArgumentException();
    }
  }

  void parseCommand2(String command) {
    String[] c = command.split(" ");
    switch (c[0]) {
      case "forward":
        long x = Long.parseLong(c[1]);
        position += x;
        depth += aim * x;
        break;
      case "up":
        aim -= Integer.parseInt(c[1]);
        break;
      case "down":
        aim += Integer.parseInt(c[1]);
        break;
      default:
        throw new IllegalArgumentException();
    }
  }
}
