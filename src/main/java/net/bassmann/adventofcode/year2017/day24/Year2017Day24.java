package net.bassmann.adventofcode.year2017.day24;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import net.bassmann.adventofcode.common.AbstractDay;

public class Year2017Day24 extends AbstractDay {

  public Year2017Day24() {
    super(2017, 24);
  }

  @Override
  public String solvePart1() {
    int l = solve1(getRiddleInput().asList());
    return Integer.toString(l);
  }

  @Override
  public String solvePart2() {
    int l = solve2(getRiddleInput().asList());
    return Integer.toString(l);
  }

  static int solve1(List<String> input) {
    List<Piece> pieces = input.stream().map(Piece::new).collect(Collectors.toList());

    //    List<List<Piece>> allBridges;
    //
    //    int lastPort = 0;
    //    int maxStrength = 0;
    //    List<Piece> currentBridge = new ArrayList<>();
    //
    //    List<Piece> nextPieces =
    //        pieces.stream().filter(p -> p.hasPort(lastPort)).collect(Collectors.toList());
    //
    //    for (Piece p : nextPieces) {
    //      if (nextPieces.isEmpty()) {
    //        int strength = currentBridge.stream().mapToInt(Piece::getStrength).sum();
    //        if (strength > maxStrength) {
    //          maxStrength = strength;
    //        }
    //      } else {
    //
    //      }
    //    }

    return buildBridge(new ArrayList<>(), 0, pieces);
  }

  static int buildBridge(List<Piece> currentBridge, int lastPort, List<Piece> pieces) {
    List<Piece> nextPieces =
        pieces
            .stream()
            .filter(p -> !currentBridge.contains(p))
            .filter(p -> p.hasPort(lastPort))
            .collect(Collectors.toList());

    if (nextPieces.isEmpty()) {
      return currentBridge.stream().mapToInt(Piece::getStrength).sum();
    } else {

      int max = 0;
      for (Piece p : nextPieces) {
        currentBridge.add(p);
        max = Math.max(buildBridge(currentBridge, p.getOtherPort(lastPort), pieces), max);
        currentBridge.remove(p);
      }
      return max;
    }
  }

  static int solve2(List<String> input) {
    List<Piece> pieces = input.stream().map(Piece::new).collect(Collectors.toList());
    return buildBridge2(new ArrayList<>(), 0, pieces).getStrength();
  }

  static Properties buildBridge2(List<Piece> currentBridge, int lastPort, List<Piece> pieces) {
    List<Piece> nextPieces =
        pieces
            .stream()
            .filter(p -> !currentBridge.contains(p))
            .filter(p -> p.hasPort(lastPort))
            .collect(Collectors.toList());

    if (nextPieces.isEmpty()) {
      return new Properties(
          currentBridge.size(), currentBridge.stream().mapToInt(Piece::getStrength).sum());
    } else {

      Properties bestProp = new Properties(0, 0);
      for (Piece p : nextPieces) {
        currentBridge.add(p);
        Properties prop =
            buildBridge2(currentBridge, p.getOtherPort(lastPort), pieces);
        if (prop.length > bestProp.length) {
          bestProp = prop;
        } else if (prop.length == bestProp.length && prop.strength > bestProp.strength) {
          bestProp = prop;
        }
        currentBridge.remove(p);
      }
      return bestProp;
    }
  }

  public static void main(String[] args) {
    List<String> example = List.of("0/2", "2/2", "2/3", "3/4", "3/5", "0/1", "10/1", "9/10");
    System.out.println(solve2(example));
  }

  static class Piece {

    private final String key;
    private final int port1;
    private final int port2;
    private final int strength;

    Piece(String s) {
      key = s;
      String[] split = s.split("/");
      port1 = Integer.parseInt(split[0]);
      port2 = Integer.parseInt(split[1]);
      strength = port1 + port2;
    }

    String getKey() {
      return key;
    }

    boolean hasPort(int port) {
      return port1 == port || port2 == port;
    }

    int getOtherPort(int port) {
      if (port == port2) {
        return port1;
      }
      if (port == port1) {
        return port2;
      }
      throw new IllegalArgumentException("Unkown Port");
    }

    int getStrength() {
      return strength;
    }
  }

  static class Properties {

    private final int length;
    private final int strength;

    Properties(int length, int strength) {
      this.length = length;
      this.strength = strength;
    }

    int getLength() {
      return length;
    }

    int getStrength() {
      return strength;
    }
  }
}
