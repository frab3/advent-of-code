package net.bassmann.adventofcode.year2015.day15;

class Ingredient {

  private final String name;
  private final int capacity;
  private final int durability;
  private final int flavor;
  private final int texture;
  private final int calories;

  private Ingredient(
      String name, int capacity, int durability, int flavor, int texture, int calories) {
    this.name = name;
    this.capacity = capacity;
    this.durability = durability;
    this.flavor = flavor;
    this.texture = texture;
    this.calories = calories;
  }

  static Ingredient fromString(String input) {
    final String[] split = input.split(" ");
    return new Ingredient(
        dropLastChar(split[0]),
        Integer.parseInt(dropLastChar(split[2])),
        Integer.parseInt(dropLastChar(split[4])),
        Integer.parseInt(dropLastChar(split[6])),
        Integer.parseInt(dropLastChar(split[8])),
        Integer.parseInt(split[10]));
  }

  private static String dropLastChar(String s) {
    return s.substring(0, s.length() - 1);
  }

  String getName() {
    return name;
  }

  int getCapacity() {
    return capacity;
  }

  int getDurability() {
    return durability;
  }

  int getFlavor() {
    return flavor;
  }

  int getTexture() {
    return texture;
  }

  int getCalories() {
    return calories;
  }

  @Override
  public String toString() {
    return "Ingredient{"
        + "name='"
        + name
        + '\''
        + ", capacity="
        + capacity
        + ", durability="
        + durability
        + ", flavor="
        + flavor
        + ", texture="
        + texture
        + ", calories="
        + calories
        + '}';
  }
}
