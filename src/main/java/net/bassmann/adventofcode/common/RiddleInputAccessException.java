package net.bassmann.adventofcode.common;

class RiddleInputAccessException extends RuntimeException {

  RiddleInputAccessException(Throwable cause) {
    super("Cannot access RiddleInput", cause);
  }
}
