package net.bassmann.adventofcode.year2015.day11;

class PasswordIncrementor {

  private PasswordIncrementor() {}

  static String increment(String password) {
    int index = findIndexToIncrement(password);
    return increment(password, index);
  }

  static String increment(String password, int index) {
    StringBuilder s = new StringBuilder();
    if (index > -1) {
      s.append(password.substring(0, index));
      s.append((char) (password.charAt(index) + 1));
    } else {
      s.append('a');
    }
    for (int j = index + 1; j < password.length(); j++) {
      s.append('a');
    }
    return s.toString();
  }

  static int findIndexToIncrement(String password) {
    for (int i = password.length() - 1; i >= 0; i--) {
      if (password.charAt(i) != 'z') {
        return i;
      }
    }
    return -1;
  }
}
