package net.bassmann.adventofcode.year2015.day05;

/** Similar to StringChecker, but now we look back two chars. */
class ImprovedStringChecker {

  boolean isNice(String input) {
    if (input.length() < 3) {
      return false;
    }

    boolean containsRepeatingPair = false;
    boolean containsBrace = false;

    final char[] charArray = input.toCharArray();
    // Init the look-back chars
    char a = charArray[0];
    char b = charArray[1];
    for (int i = 2, end = charArray.length; i < end; i++) {
      final char c = charArray[i];

      if (!containsRepeatingPair) {
        final String pair = String.valueOf(a) + String.valueOf(b);
        if (input.indexOf(pair, i) != -1) {
          containsRepeatingPair = true;
        }
      }

      if (!containsBrace && isBrace(a, c)) {
        containsBrace = true;
      }

      // As soon as both conditions are true, we can return true
      if (containsRepeatingPair && containsBrace) {
        return true;
      }

      a = b;
      b = c;
    }

    return false;
  }

  private boolean isBrace(char a, char c) {
    return a == c;
  }
}
