import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day8 {
    Day8() throws IOException{
        List<String> input = Files.lines(Paths.get("AdventOfCode/inputs/day8.txt")).collect(Collectors.toList());
        outputTable(input);
    }

    private void outputTable(List<String> instructions){
        char[][] screen = new char[6][50];

        for (char[] row : screen)
            Arrays.fill(row, '.');

        for (String in : instructions){
            String[] inSplited = in.split("\\s+");
            switch (inSplited[0]){
                case "rotate" :
                    int howMany = Integer.parseInt(inSplited[4]);
                    int rowOrColNum = Integer.parseInt(inSplited[2].substring(2));
                    switch (inSplited[1]) {
                        case "row":
                            String rowString = String.copyValueOf(screen[rowOrColNum]);

                            rowString = rowString.substring(50-howMany) + rowString.substring(0,50-howMany);
                            screen[rowOrColNum] = rowString.toCharArray();
                            break;
                        case "column":

                            String colString = "";
                            for (char[] chars : screen)
                                colString += chars[rowOrColNum];

                            colString = colString.substring(6-howMany) + colString.substring(0,6-howMany);

                            for (int row = 0; row < 6; row++)
                                screen[row][rowOrColNum] = colString.charAt(row);

                            break;
                    }
                    break;
                case "rect" :
                    for (int row = 0; row < Integer.parseInt(inSplited[1].split("x")[1]); row++)
                        for (int col = 0; col < Integer.parseInt(inSplited[1].split("x")[0]); col++)
                            screen[row][col] = '#';
                    break;
            }
        }

        for (char[] s : screen){
            for (char s1 : s)
                System.out.print(s1);
            System.out.print("\n");
        }
    }
}

