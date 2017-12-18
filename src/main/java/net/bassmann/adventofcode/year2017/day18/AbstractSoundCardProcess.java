package net.bassmann.adventofcode.year2017.day18;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Sound card implementations only differ in what happens on {@code snd} and {@code rcv} commands,
 * so each implementation must provide the functionality for that.
 */
abstract class AbstractSoundCardProcess {

  private final List<String> instructions;

  private final Map<String, Long> register = new HashMap<>();
  private boolean waiting = false;
  private int pos = 0;

  AbstractSoundCardProcess(List<String> instructions) {
    this.instructions = instructions;
  }

  abstract void snd(String x);

  abstract int rcv(String x);

  void step() {
    if (pos < 0 || pos >= instructions.size()) {
      waiting = true;
      return;
    }
    String[] inst = instructions.get(pos).split(" ");
    switch (inst[0]) {
      case "snd":
        snd(inst[1]);
        break;
      case "set":
        register.put(inst[1], get(inst[2]));
        break;
      case "add":
        register.put(inst[1], get(inst[1]) + get(inst[2]));
        break;
      case "mul":
        register.put(inst[1], get(inst[1]) * get(inst[2]));
        break;
      case "mod":
        register.put(inst[1], get(inst[1]) % get(inst[2]));
        break;
      case "rcv":
        pos += rcv(inst[1]);
        return;
      case "jgz":
        pos += jump(inst[1], inst[2]);
        return;
      default:
        throw new IllegalArgumentException();
    }
    pos += 1;
  }

  long get(String r) {
    if (Character.isLetter(r.charAt(0))) {
      return register.computeIfAbsent(r, i -> 0L);
    } else {
      return Long.parseLong(r);
    }
  }

  void set(String r, long val) {
    register.put(r, val);
  }

  boolean isWaiting() {
    return waiting;
  }

  void setWaiting(boolean waiting) {
    this.waiting = waiting;
  }

  private int jump(String x, String y) {
    long xVal = get(x);
    long yVal = get(y);
    return xVal > 0 ? (int) yVal : 1;
  }
}
