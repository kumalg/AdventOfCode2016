import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Day3 {

    Day3() throws IOException{
        List<String> listOfTriangles = Files.lines(Paths.get("AdventOfCode/inputs/day3.txt")).collect(Collectors.toList());
        System.out.println("   By row: " + trianglesByRow(toMatrix(listOfTriangles)));
        System.out.println("By column: " + trianglesByCol(toMatrix(listOfTriangles)));
    }

    private List<List<Integer>> toMatrix(List<String> list){
        List<List<Integer>> newList = new ArrayList<>();

        for(String s : list){
            String[] sidesStrings = s.trim().split("\\s+");
            List<Integer> sidesInt = new ArrayList<>();

            for (int i = 0; i < 3; i++)
                sidesInt.add(Integer.parseInt(sidesStrings[i]));

            newList.add(sidesInt);
        }

        return newList;
    }

    private int trianglesByRow( List<List<Integer>> input){
        int numberOfPossibleTriangles = 0;

        for (List<Integer> sides : input)
            if (isPossible(sides))
                numberOfPossibleTriangles++;

        return numberOfPossibleTriangles;
    }

    private int trianglesByCol( List<List<Integer>> input){
        int numberOfPossibleTriangles = 0;

        for (int row = 0; row < input.size(); row += 3){
            for (int col = 0; col < 3; col++){
                List<Integer> sides = new ArrayList<>();
                for (int rowInside = 0; rowInside < 3; rowInside++)
                    sides.add(input.get(row+rowInside).get(col));

                if (isPossible(sides))
                    numberOfPossibleTriangles++;
            }
        }

        return numberOfPossibleTriangles;
    }

    public boolean isPossible(List<Integer> sidesList){
        Collections.sort(sidesList);
        return (sidesList.get(0) + sidesList.get(1)) > sidesList.get(2);
    }
}