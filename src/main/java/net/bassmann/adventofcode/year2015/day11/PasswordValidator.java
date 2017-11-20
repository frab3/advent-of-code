package net.bassmann.adventofcode.year2015.day11;

class PasswordValidator {

  private static final char[] FORBIDDEN_CHARS = {'i', 'l', 'o'};

  private PasswordValidator() {}

  static boolean isValid(String password) {
    return !containsForbiddenChar(password)
        && containsStraight(password)
        && containsTwoNonOverlappingPairs(password);
  }

  static boolean containsStraight(String password) {
    final int length = password.length();
    if (length < 3) {
      return false;
    }

    final char[] charArray = password.toCharArray();
    char c1 = charArray[0];
    char c2 = charArray[1];
    for (int i = 2; i < length; i++) {
      final char c3 = charArray[i];
      if (c1 == c2 - 1 && c2 == c3 - 1) {
        return true;
      }
      c1 = c2;
      c2 = c3;
    }
    return false;
  }

  static boolean containsForbiddenChar(String password) {
    return indexOfForbiddenChar(password) > -1;
  }

  static int indexOfForbiddenChar(String password) {
    int index = Integer.MAX_VALUE;
    for (char forbidden : FORBIDDEN_CHARS) {
      final int i = password.indexOf(forbidden);
      if (i > -1) {
        index = Math.min(index, i);
      }
    }
    return index == Integer.MAX_VALUE ? -1 : index;
  }

  static boolean containsTwoNonOverlappingPairs(String password) {
    final char[] charArray = password.toCharArray();
    char firstPair = ' ';
    char p = charArray[0];
    for (int i = 1, length = charArray.length; i < length; i++) {
      char c = charArray[i];
      if (p == c && c != firstPair) {
        if (firstPair != ' ') {
          return true;
        } else {
          firstPair = c;
        }
      }
      p = c;
    }
    return false;
  }
}
