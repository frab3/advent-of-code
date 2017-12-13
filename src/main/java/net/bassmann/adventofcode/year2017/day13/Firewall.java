package net.bassmann.adventofcode.year2017.day13;

import static java.util.stream.Collectors.toList;

import java.util.Comparator;
import java.util.List;

class Firewall {

  private final List<Scanner> scanners;

  Firewall(List<String> input) {
    scanners =
        input
            .stream()
            .map(s -> s.split(": "))
            .map(s -> new Scanner(s[0], s[1]))
            .sorted(Comparator.comparing(Scanner::getRange))
            .collect(toList());
  }

  int getSeverity(int delay) {
    return scanners.stream().filter(s -> s.isAtTop(delay)).mapToInt(Scanner::getSeverity).sum();
  }

  boolean getCaught(int delay) {
    return scanners.stream().anyMatch(s -> s.isAtTop(delay));
  }
}
