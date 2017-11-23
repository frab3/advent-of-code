package net.bassmann.adventofcode.year2015.day15;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class CookieMixTest {

  private static CookieMix mix;

  @BeforeAll
  static void setupMix() {
    List<Ingredient> ingredients = new ArrayList<>();
    ingredients.add(
        Ingredient.fromString(
            "Butterscotch: capacity -1, durability -2, flavor 6, texture 3, calories 8"));
    ingredients.add(
        Ingredient.fromString(
            "Cinnamon: capacity 2, durability 3, flavor -2, texture -1, calories 3"));
    mix = new CookieMix(ingredients);
  }

  @Test
  void getNumIngredients() {
    assertEquals(2, mix.getNumIngredients());
  }

  @Test
  void getTotalScore() {
    Map<String, Integer> recipe = Map.of("Butterscotch", 44, "Cinnamon", 56);
    int actual = mix.getTotalScore(recipe, 0);
    assertEquals(62842880, actual);
  }
}
