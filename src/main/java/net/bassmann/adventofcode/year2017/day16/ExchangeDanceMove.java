package net.bassmann.adventofcode.year2017.day16;

import static java.lang.Integer.parseInt;

class ExchangeDanceMove implements DanceMove {

  private final int posA;
  private final int posB;

  ExchangeDanceMove(String danceMove) {
    final int posOfSlash = danceMove.lastIndexOf('/');
    this.posA = parseInt(danceMove.substring(1, posOfSlash));
    this.posB = parseInt(danceMove.substring(posOfSlash + 1));
  }

  @Override
  public void doMove(char[] programs) {
    char temp = programs[posA];
    programs[posA] = programs[posB];
    programs[posB] = temp;
  }
}
