package net.bassmann.adventofcode.year2017.day23;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Processor {

  private final List<String> instructions;

  private final Map<String, Long> register = new HashMap<>();
  private boolean waiting = false;
  private int pos = 0;

  private long mulCount = 0;

  Processor(List<String> instructions) {
    this.instructions = instructions;
  }

  long getMulCount() {
    return mulCount;
  }

  //  abstract void snd(String x);

//  abstract int rcv(String x);

  void step() {
    if (pos < 0 || pos >= instructions.size()) {
      waiting = true;
      return;
    }
    String[] inst = instructions.get(pos).split(" ");
    switch (inst[0]) {
//      case "snd":
//        snd(inst[1]);
//        break;
      case "set":
        register.put(inst[1], get(inst[2]));
        break;
      case "add":
        register.put(inst[1], get(inst[1]) + get(inst[2]));
        break;
      case "mul":
        // count this!
        register.put(inst[1], get(inst[1]) * get(inst[2]));
        mulCount++;
        break;
      case "mod":
        register.put(inst[1], get(inst[1]) % get(inst[2]));
        break;

      case "sub":
        register.put(inst[1], get(inst[1]) - get(inst[2]));
        break;
//      case "rcv":
//        pos += rcv(inst[1]);
//        return;
      case "jgz":
        pos += jump(inst[1], inst[2]);
        return;
      case "jnz":
        //        jnz X Y jumps with an offset of the value of Y, but only if the value of X is not
        // zero. (An offset of 2 skips the next instruction, an offset of -1 jumps to the previous
        // instruction, and so on.)
        pos += jumpNZ(inst[1], inst[2]);
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

  private int jumpNZ(String x, String y) {
    long xVal = get(x);
    long yVal = get(y);
    return xVal != 0 ? (int) yVal : 1;
  }
}
