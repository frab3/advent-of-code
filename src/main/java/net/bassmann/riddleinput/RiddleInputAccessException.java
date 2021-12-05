package net.bassmann.riddleinput;

public class RiddleInputAccessException extends RuntimeException {

  RiddleInputAccessException(Throwable cause) {
    super("Cannot access RiddleInput", cause);
  }
}
