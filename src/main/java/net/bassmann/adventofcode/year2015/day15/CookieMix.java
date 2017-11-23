package net.bassmann.adventofcode.year2015.day15;

import static java.lang.Math.max;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

class CookieMix {

  private static final int TOTAL_SPOONS = 100;

  private final Map<String, Ingredient> ingredients;

  CookieMix(List<Ingredient> ingredients) {
    this.ingredients =
        ingredients.stream().collect(Collectors.toMap(Ingredient::getName, Function.identity()));
  }

  int getNumIngredients() {
    return ingredients.size();
  }

  /**
   * Calculates the total score for a given recipe.
   *
   * @param calories calories limit, to find the recipe for a given amount of calories. Ignored, if
   *     it is zero.
   */
  int getTotalScore(Map<String, Integer> recipe, int calories) {
    int totalCapacity = 0;
    int totalDurability = 0;
    int totalFlavor = 0;
    int totalTexture = 0;
    int totalCalories = 0;
    for (Map.Entry<String, Integer> e : recipe.entrySet()) {
      final Ingredient ingredient = ingredients.get(e.getKey());
      final int amount = e.getValue();
      totalCapacity += ingredient.getCapacity() * amount;
      totalDurability += ingredient.getDurability() * amount;
      totalFlavor += ingredient.getFlavor() * amount;
      totalTexture += ingredient.getTexture() * amount;
      totalCalories += ingredient.getCalories() * amount;
    }
    if (calories > 0 && calories != totalCalories) {
      return 0;
    }
    return max(0, totalCapacity)
        * max(0, totalDurability)
        * max(0, totalFlavor)
        * max(0, totalTexture);
  }

  /**
   * This finds the best total score, but is hardcoded to the ingredients in my input file. I'm not
   * that happy with that, but currently I don't see any other way to iteratively generate all
   * possible recipes.
   *
   * <p>Also this approach is quite brute force, since all combinations of ingredients are tried.
   * Maybe there is a smarter way to solve this?
   *
   * @param calories calories limit, to find the best recipe for a given amount of calories.
   *     Ignored, if it is zero.
   */
  int findBestTotalScore(int calories) {
    int max = 0;
    for (int sugar = 0; sugar <= TOTAL_SPOONS; sugar++) {
      for (int sprinkles = 0; sugar + sprinkles <= TOTAL_SPOONS; sprinkles++) {
        for (int candy = 0; sugar + sprinkles + candy <= TOTAL_SPOONS; candy++) {
          final int chocolate = TOTAL_SPOONS - (sugar + sprinkles + candy);

          final Map<String, Integer> recipe =
              Map.of(
                  "Sugar", sugar, "Sprinkles", sprinkles, "Candy", candy, "Chocolate", chocolate);
          int totalScore = getTotalScore(recipe, calories);
          max = Math.max(max, totalScore);
        }
      }
    }
    return max;
  }
}
