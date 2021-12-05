package net.bassmann.kodekalender.year2020.day03;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Year2020Day03 {

  List<String> getLinesFromFile(String filename) throws IOException, URISyntaxException {
    URL url = getClass().getClassLoader().getResource("kodekalender/2020/" + filename);
    if (url == null) {
      return List.of();
    }
    return Files.readAllLines(Paths.get(url.toURI()));
  }

  public static void main(String[] args) throws IOException, URISyntaxException {
    var today = new Year2020Day03();
    List<String> matrix = today.getLinesFromFile("matrix.txt");
    System.out.println(matrix.size());
    List<String> words = today.getLinesFromFile("wordlist.txt");

    List<String> allLines = new ArrayList<>(5998);
    allLines.addAll(matrix);

    // generate all Columns
    for (int col = 0; col < 1000; col++) {
      StringBuilder column = new StringBuilder();
      for (int row = 0; row < 1000; row++) {
        column.append(matrix.get(row).charAt(col));
      }
      allLines.add(column.toString());
    }

    // generate forward diagonals
    // rows 999 -> 0
    for (int row = 999; row >= 0; row--) {
      int r = row;
      int c = 0;
      StringBuilder diag = new StringBuilder();
      while (r < 1000 && c < 1000) {
        diag.append(matrix.get(r).charAt(c));
        r++;
        c++;
      }
      allLines.add(diag.toString());
    }
    // cols 1 -> 999
    for (int col = 1; col < 1000; col++) {
      int r = 0;
      int c = col;
      StringBuilder diag = new StringBuilder();
      while (r < 1000 && c < 1000) {
        diag.append(matrix.get(r).charAt(c));
        r++;
        c++;
      }
      allLines.add(diag.toString());
    }

    // generate backwards diagonals
    // cols 0 -> 999
    for (int col = 0; col < 1000; col++) {
      int r = 0;
      int c = col;
      StringBuilder diag = new StringBuilder();
      while (r < 1000 && c > 0) {
        diag.append(matrix.get(r).charAt(c));
        r++;
        c--;
      }
      allLines.add(diag.toString());
    }
    // rows 1 -> 999 -- but backwards...
    for (int row = 1; row < 1000; row++) {
      int r = row;
      int c = 999;
      StringBuilder diag = new StringBuilder();
      while (r < 1000 && c > 0) {
        diag.append(matrix.get(r).charAt(c));
        r++;
        c--;
      }
      allLines.add(diag.toString());
    }

    System.out.println(allLines.size());

    List<String> missingWords = new ArrayList<>();
    for (String word : words) {
      String reverse = new StringBuilder(word).reverse().toString();
      if (allLines.stream().noneMatch(l -> l.contains(word) || l.contains(reverse))) {
        missingWords.add(word);
      }
    }
    System.out.println(missingWords.stream().sorted().collect(Collectors.joining(",")));
  }
}
