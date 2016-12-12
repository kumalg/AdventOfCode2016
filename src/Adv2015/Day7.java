package Adv2015;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Day7 {
    public static void main(String[] args) throws IOException {
        new Day7();
    }

    Day7() throws IOException {
        List<String> inputInstructions = Files.lines(Paths.get("AdventOfCode/inputs/Adv2015/day7.txt")).collect(Collectors.toList());
        List<Integer> listOfAcceptedInstructions = new ArrayList<>();
        Map<String, Integer> mapOfWires = new TreeMap<>();
        int j = 0;

        while (listOfAcceptedInstructions.size() < inputInstructions.size()) {
            for (int i = 0; i < inputInstructions.size(); i++) {
                if (!listOfAcceptedInstructions.contains(i)) {
                    List<String> instruction = Arrays.asList(inputInstructions.get(i).split(" "));
                    int value = 0;
                    String wire = instruction.get(instruction.size() - 1);

                    if (instruction.contains("NOT")) {
                        if (mapOfWires.containsKey(instruction.get(1)))
                            value = ~mapOfWires.get(instruction.get(1));
                        else continue;
                    } else if (instruction.contains("AND")) {
                        if (Character.isDigit(instruction.get(0).charAt(0)) && mapOfWires.containsKey(instruction.get(2)))
                            value = Integer.parseInt(instruction.get(0)) & mapOfWires.get(instruction.get(2));
                        else if (mapOfWires.containsKey(instruction.get(0)) && mapOfWires.containsKey(instruction.get(2)))
                            value = mapOfWires.get(instruction.get(0)) & mapOfWires.get(instruction.get(2));
                        else continue;
                    } else if (instruction.contains("OR")) {
                        if (Character.isDigit(instruction.get(0).charAt(0)) && mapOfWires.containsKey(instruction.get(2)))
                            value = Integer.parseInt(instruction.get(0)) | mapOfWires.get(instruction.get(2));
                        else if (mapOfWires.containsKey(instruction.get(0)) && mapOfWires.containsKey(instruction.get(2)))
                            value = mapOfWires.get(instruction.get(0)) | mapOfWires.get(instruction.get(2));
                        else continue;
                    } else if (instruction.contains("LSHIFT")) {
                        if (mapOfWires.containsKey(instruction.get(0)))
                            value = mapOfWires.get(instruction.get(0)) << Integer.parseInt(instruction.get(2));
                        else continue;
                    } else if (instruction.contains("RSHIFT")) {
                        if (mapOfWires.containsKey(instruction.get(0)))
                            value = mapOfWires.get(instruction.get(0)) >> Integer.parseInt(instruction.get(2));
                        else continue;
                    } else {
                        if (Character.isDigit(instruction.get(0).charAt(0)))
                            value = Integer.parseInt(instruction.get(0));
                        else if (mapOfWires.containsKey(instruction.get(0)))
                            value = mapOfWires.get(instruction.get(0));
                        else continue;
                    }
                    listOfAcceptedInstructions.add(i);
                    mapOfWires.put(wire, value);
                }
            }
        }

        System.out.println("a: " + mapOfWires.get("a"));
    }
}