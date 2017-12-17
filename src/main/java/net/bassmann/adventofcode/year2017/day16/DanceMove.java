package net.bassmann.adventofcode.year2017.day16;

interface DanceMove {

  void doMove(char[] programs);

  static DanceMove fromString(String danceMove) {
    switch (danceMove.charAt(0)) {
      case 's':
        return new SpinDanceMove(danceMove);
      case 'x':
        return new ExchangeDanceMove(danceMove);
      case 'p':
        return new PartnerDanceMove(danceMove);
      default:
        throw new IllegalArgumentException("Not a valid dance move.");
    }
  }
}
