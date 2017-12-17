package net.bassmann.adventofcode.year2017.day16;

class PartnerDanceMove implements DanceMove {

  private final char partnerA;
  private final char partnerB;

  PartnerDanceMove(String danceMove) {
    final int length = danceMove.length();
    this.partnerA = danceMove.charAt(length - 3);
    this.partnerB = danceMove.charAt(length - 1);
  }

  @Override
  public void doMove(char[] programs) {
    boolean foundA = false;
    boolean foundB = false;
    for (int i = 0; i < programs.length; i++) {
      if (foundA && foundB) {
        break;
      } else if (!foundA && programs[i] == partnerA) {
        programs[i] = partnerB;
        foundA = true;
      } else if (!foundB && programs[i] == partnerB) {
        programs[i] = partnerA;
        foundB = true;
      }
    }
  }
}
