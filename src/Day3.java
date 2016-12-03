import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Kamil on 03.12.2016.
 */
public class Day3 {

    private Day3() throws IOException{
        List<String> listOfTriangles = Files.lines(Paths.get("AdventOfCode/describtion/day3/input.txt")).collect(Collectors.toList());
        System.out.println("   By row: " + howManyPossibleTrianglesByRow(listOfTriangles));
        System.out.println("By column: " + howManyPossibleTrianglesByColumn(listOfTriangles));
    }

    public static void main (String[] args) throws IOException{
        new Day3();
    }

    private int howManyPossibleTrianglesByRow(List<String> input){
        int numberOfPossibleTriangles = 0;

        for (String s : input){
            String[] sidesStrings = s.trim().split(" +");
            int[] sidesInt = new int[3];

            for (int i = 0; i < sidesStrings.length && i < 3; i++)
                sidesInt[i] = Integer.parseInt(sidesStrings[i]);

            if(new Triangle().isPossible(sidesInt))
                numberOfPossibleTriangles++;
        }

        return numberOfPossibleTriangles;
    }

    private int howManyPossibleTrianglesByColumn(List<String> input){
        int numberOfPossibleTriangles = 0;

        for (int row = 0; row < input.size(); row+=3){
            String[][] sidesOf3Triangles = new String[3][3];
            int[] sidesInt = new int[3];

            for(int rowInside = 0; rowInside < 3; rowInside++){
                for(int col = 0; col < 3; col++){
                    sidesOf3Triangles[rowInside][col] = input.get(rowInside+row).trim().split(" +")[col];
                }
            }

            for (int colmn = 0; colmn < 3; colmn++){
                for (int rown = 0; rown < 3; rown++)
                    sidesInt[rown] = Integer.parseInt(sidesOf3Triangles[rown][colmn]);

                if(new Triangle().isPossible(sidesInt))
                    numberOfPossibleTriangles++;
            }
        }

        return numberOfPossibleTriangles;
    }
}

class Triangle {
    public boolean isPossible(int[] sidesTable){
        Arrays.sort(sidesTable);
        return (sidesTable[0] + sidesTable[1]) > sidesTable[2];
    }
}
