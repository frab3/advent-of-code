package net.bassmann.adventofcode.year2015.day12;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Second part of the riddle. The RedSum class first transforms the input into a list, which
 * contains just the important parts. The numbers, the red attributes, and the object delimiters.
 *
 * <p>It then uses a stack to parse the object structure. Afterwards the objects just return the sum
 * of all their children, but return zero if they also contain a red attribute.
 */
class RedSum {

  private final List<String> list;

  RedSum(String input) {
    list = new ArrayList<>();
    Matcher m = Pattern.compile("-?\\d+|\\{|}|:\"red\"").matcher(input);
    while (m.find()) {
      list.add(m.group());
    }
  }

  int parseAndSum() {
    final Deque<MyJsonObject> stack = new ArrayDeque<>();

    MyJsonObject current = new MyJsonObject();
    for (String s : list) {
      switch (s) {
        case "{":
          stack.push(current);
          current = new MyJsonObject();
          break;
        case "}":
          stack.peek().addChild(current);
          current = stack.pop();
          break;
        default:
          current.addValue(s);
      }
    }

    return current.sum();
  }

  static class MyJsonObject {
    private final List<String> values = new ArrayList<>();
    private final List<MyJsonObject> children = new ArrayList<>();

    void addValue(String value) {
      values.add(value);
    }

    void addChild(MyJsonObject child) {
      children.add(child);
    }

    /**
     * Returns the sum of all values and children of this object. If any values ar red, then the sum
     * is automatically zero.
     */
    int sum() {
      int sum = 0;
      for (String v : values) {
        if (":\"red\"".equals(v)) {
          return 0;
        }
        sum += Integer.parseInt(v);
      }
      for (MyJsonObject o : children) {
        sum += o.sum();
      }
      return sum;
    }
  }
}
