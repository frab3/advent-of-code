package net.bassmann.adventofcode.year2020.day12;

import java.util.List;
import net.bassmann.adventofcode.common.AbstractDay;

public class Year2020Day12 extends AbstractDay {

  public Year2020Day12() {
    super(2020, 12);
  }

  @Override
  public String solvePart1() {
    Ship ship = new Ship();
    getRiddleInput().lines().forEach(ship::applyCommand);
    return Integer.toString(ship.getManhattanDistance());
  }

  @Override
  public String solvePart2() {
    ShipAndWaypoint ship = new ShipAndWaypoint();
    getRiddleInput().lines().forEach(ship::applyCommand);
    return Integer.toString(ship.getManhattenDistance());
  }

  static class Ship {
    private int hPos = 0;
    private int vPos = 0;
    char heading = 'E';
    Turtle ship = new Turtle();

    Ship() {
      ship.speed(100);
    }

    void applyCommand(String command) {
      char c = command.charAt(0);
      int value = Integer.parseInt(command.substring(1));
      switch (c) {
        case 'N':
          vPos += value;
          ship.setPosition(ship.getX(), ship.getY() + value * 10);
          break;
        case 'S':
          vPos -= value;
          ship.setPosition(ship.getX(), ship.getY() - value * 10);
          break;
        case 'E':
          hPos += value;
          ship.setPosition(ship.getX() + value * 10, ship.getY());
          break;
        case 'W':
          hPos -= value;
          ship.setPosition(ship.getX() - value * 10, ship.getY());
          break;
        case 'L':
          turnLeft(value);
          ship.left(value);
          break;
        case 'R':
          turnLeft(360 - value);
          ship.right(value);
          break;
        case 'F':
          forward(value);
          ship.forward(value*10);
          break;
      }
    }

    private void forward(int value) {
      switch (heading) {
        case 'N':
          vPos += value;
          break;
        case 'S':
          vPos -= value;
          break;
        case 'E':
          hPos += value;
          break;
        case 'W':
          hPos -= value;
          break;
      }
    }

    private void turnLeft(int value) {
      if (value == 0) return;
      switch (heading) {
        case 'N':
          heading = 'W';
          break;
        case 'W':
          heading = 'S';
          break;
        case 'S':
          heading = 'E';
          break;
        case 'E':
          heading = 'N';
          break;
      }
      turnLeft(value - 90);
    }

    int getManhattanDistance() {
      return Math.abs(vPos) + Math.abs(hPos);
    }
  }

  static class ShipAndWaypoint {
    int shipH = 0;
    int shipV = 0;
    int wayH = 10;
    int wayV = 1;

    void applyCommand(String command) {
      char c = command.charAt(0);
      int value = Integer.parseInt(command.substring(1));
      switch (c) {
        case 'N':
          wayV += value;
          break;
        case 'S':
          wayV -= value;
          break;
        case 'E':
          wayH += value;
          break;
        case 'W':
          wayH -= value;
          break;
        case 'L':
          rotateLeft(value);
          break;
        case 'R':
          rotateLeft(360 - value);
          break;
        case 'F':
          shipH += value * wayH;
          shipV += value * wayV;
          break;
      }
    }

    void rotateLeft(int value) {
      if (value == 90) {
        int temp = wayV;
        wayV = wayH;
        wayH = -temp;
      }
      if (value == 180) {
        wayH = -wayH;
        wayV = -wayV;
      }
      if (value == 270) {
        int temp = wayV;
        wayV = -wayH;
        wayH = temp;
      }
    }

    int getManhattenDistance() {
      return Math.abs(shipH) + Math.abs(shipV);
    }
  }

  public static void main(String[] args) {
    var testInput = List.of("F10", "N3", "F7", "R90", "F11");

    Ship myShip = new Ship();
    testInput.forEach(myShip::applyCommand);
    System.out.println(myShip.getManhattanDistance());

    var today = new Year2020Day12();
    System.out.println(today.solvePart1());

    ShipAndWaypoint myShipAndWaypoint = new ShipAndWaypoint();
    testInput.forEach(myShipAndWaypoint::applyCommand);
    System.out.println(myShipAndWaypoint.getManhattenDistance());

    System.out.println(today.solvePart2());

    var ship = new Turtle();
    ship.speed(1000);
    ship.forward(100);
    ship.setPosition(ship.getX(), ship.getY()+30);
    ship.forward(70);
    ship.right(90);
    ship.forward(110);
  }
}
