import javafx.util.Pair;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Day6 {
    public static void main(String[] args) throws IOException{
        List<String> input = Files.lines(Paths.get("AdventOfCode/description/day6/input.txt")).collect(Collectors.toList());

        System.out.println(correctText(input, (a, b) -> a > b));
        System.out.println(correctText(input, (a, b) -> a < b));
    }

    private static String correctText(List<String> input, IntComparator comparator){
        Map<Character, Integer> allCharactersByColumn = new HashMap<>();
        Pair<Character, Integer> correctChar = null;
        String output = "";

        for (int col = 0; col < input.get(0).length(); col++){
            for (int row = 0; row < input.size(); row++){
                char actualChar = input.get(row).charAt(col);
                allCharactersByColumn.put(actualChar, allCharactersByColumn.getOrDefault(actualChar, 0) + 1);
            }

            for (char c : allCharactersByColumn.keySet()){
                if (correctChar == null
                        || comparator.gt(allCharactersByColumn.get(c), correctChar.getValue()))
                    correctChar = new Pair<>(c, allCharactersByColumn.get(c));
            }

            output += correctChar.getKey();

            correctChar = null;
            allCharactersByColumn.clear();
        }
        return output;
    }
}

interface IntComparator{
    boolean gt(int a, int b);
}