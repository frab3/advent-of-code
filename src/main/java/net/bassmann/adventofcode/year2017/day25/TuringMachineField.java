package net.bassmann.adventofcode.year2017.day25;

class TuringMachineField {

  private TuringMachineField left;
  private TuringMachineField right;
  private boolean value = false;

  int get() {
    return value ? 1 : 0;
  }

  void set(int value) {
    this.value = value == 1;
  }

  TuringMachineField left() {
    if (left == null) {
      left = new TuringMachineField();
      left.right = this;
    }
    return left;
  }

  TuringMachineField right() {
    if (right == null) {
      right = new TuringMachineField();
      right.left = this;
    }
    return right;
  }

  int checksum() {
    int sum = get();
    TuringMachineField f = left;
    while (f != null) {
      sum += f.get();
      f = f.left;
    }
    f = right;
    while (f != null) {
      sum += f.get();
      f = f.right;
    }
    return sum;
  }
}
