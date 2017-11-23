package net.bassmann.adventofcode.year2015.day15;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class IngredientTest {

  @Test
  void fromString() {
    Ingredient i =
        Ingredient.fromString(
            "Butterscotch: capacity -1, durability -2, flavor 6, texture 3, calories 8");
    assertEquals("Butterscotch", i.getName());
    assertEquals(-1, i.getCapacity());
    assertEquals(-2, i.getDurability());
    assertEquals(6, i.getFlavor());
    assertEquals(3, i.getTexture());
    assertEquals(8, i.getCalories());
  }
}
