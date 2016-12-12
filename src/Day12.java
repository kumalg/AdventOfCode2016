import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Day12 {
    Day12() throws IOException {
        List<String> inputInstructions = Files.lines(Paths.get("AdventOfCode/inputs/day12.txt")).collect(Collectors.toList());
        Map<String, Integer> mapOfValues = new TreeMap<>();
        for (int i = 0; i < inputInstructions.size(); i++){
            String[] instruction = inputInstructions.get(i).split(" ");
            switch (instruction[0]) {
                case "cpy" :
                    if(Character.isDigit(instruction[1].charAt(0)))
                        mapOfValues.put(instruction[2], Integer.parseInt(instruction[1]));
                    else if (mapOfValues.containsKey(instruction[1]))
                        mapOfValues.put(instruction[2], mapOfValues.get(instruction[1]));
                    break;
                case "inc" :
                    mapOfValues.put(instruction[1], mapOfValues.get(instruction[1]) + 1);
                    break;
                case "dec" :
                    mapOfValues.put(instruction[1], mapOfValues.get(instruction[1]) - 1);
                    break;
                case "jnz" :
                    int whichNumberToCheck;
                    int howMany = Integer.parseInt(instruction[2]);
                    if(Character.isDigit(instruction[1].charAt(0)))
                        whichNumberToCheck = Integer.parseInt(instruction[1]);
                    else if (mapOfValues.containsKey(instruction[1]))
                        whichNumberToCheck = mapOfValues.get(instruction[1]);
                    else break;

                    if (whichNumberToCheck != 0)
                        i += howMany - 1;
                    break;
            }
        }
        for (String s : mapOfValues.keySet())
            System.out.println(s + ": " + mapOfValues.get(s));
    }
}
