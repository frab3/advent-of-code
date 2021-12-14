package net.bassmann.adventofcode.year2021.day14;

import java.util.Arrays;

public class StringLinkedList {

  private Element first;
  private Element last;

  private final long[] count = new long[26];

  public void add(String value) {
    Element e = new Element(value);
    if (isEmpty()) {
      first = e;
    } else {
      last.insertAfter(e);
    }
    last = e;
    inc(value);
  }

  boolean isEmpty() {
    return first == null;
  }

  public Element getFirst() {
    return first;
  }

  public Element getLast() {
    return last;
  }

  public void insertBefore(Element existing, String value) {
    var e = new Element(value);
    if (existing == first) {
      first = e;
    }
    existing.insertBefore(e);
    inc(value);
  }

  private void inc(String value) {
    count[value.charAt(0) - 'A']++;
  }


  public long min() {
    return Arrays.stream(count).filter(i -> i > 0).min().orElseThrow();
  }

  public long max() {
    return Arrays.stream(count).filter(i -> i > 0).max().orElseThrow();
  }

  @Override
  public String toString() {
    var b = new StringBuilder();
    var e = first;
    while (e != null) {
      b.append(e.getValue());
      e = e.next;
    }
    return b.toString();
  }

  public static class Element {
    private final String value;
    private Element previous = null;
    private Element next = null;

    public Element(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }

    boolean hasNext() {
      return next != null;
    }

    boolean isFirst() {
      return previous == null;
    }

    boolean isLast() {
      return next == null;
    }

    Element next() {
      return next;
    }

    private Element insertBefore(Element e) {
      if (!isFirst()) {
        previous.next = e;
        e.previous = previous;
      }
      e.next = this;
      previous = e;
      return e;
    }

    private Element insertAfter(Element e) {
      if (!isLast()) {
        next.previous = e;
        e.next = next;
      }
      e.previous = this;
      next = e;
      return e;
    }
  }
}
