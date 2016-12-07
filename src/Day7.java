import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day7 {

    public static void main(String[] args) throws IOException{
        List<String> input = Files.lines(Paths.get("AdventOfCode/inputs/day7.txt")).collect(Collectors.toList());

        System.out.println("Number of IP's supported TSL: " + numberOfIPsSupportTSL(input));
        System.out.println("Number of IP's supported SSL: " + numberOfIPsSupportSSL(input));
    }

    private static int numberOfIPsSupportTSL(List<String> input){
        int numberOfValidIPs = 0;

        for(String line : input){
            List<String> outsideBracket = Arrays.asList(line.split("\\[([^]]+)\\]+"));
            String[] allWords = line.split("\\[|\\]");
            boolean dupa = true;

            for (String s : allWords)
                if (!outsideBracket.contains(s))
                    if (haveABBA(s)){
                        dupa = false;
                        break;
                    }

            if (dupa)
                if (haveABBAFromList(outsideBracket))
                    numberOfValidIPs++;
        }

        return numberOfValidIPs;
    }


    private static int numberOfIPsSupportSSL(List<String> input){
        int numberOfValidIPs = 0;

        for(String line : input){
            List<String> outsideBracket = Arrays.asList(line.split("\\[([^]]+)\\]+"));
            String[] allWords = line.split("\\[|\\]");
            List<String> outsideABAs = new ArrayList<>();

            for (String outside : outsideBracket)
                outsideABAs.addAll(listOfABAs(outside));

            boolean dupa = false;

            for (String s : allWords)
                if (!outsideBracket.contains(s)){
                    for(String abaInside : listOfABAs(s))
                        if (outsideABAs.contains(invertABA(abaInside))){
                            dupa = true;
                            break;
                        }
                }
                if (dupa) numberOfValidIPs++;
        }

        return numberOfValidIPs;
    }
    
    private static boolean haveABBAFromList(List<String> stringsTable){
        for(String singleString : stringsTable)
            if (haveABBA(singleString)) return true;
        return false;
    }

    private static boolean haveABBA(String string){
        if (string.length() >= 4)
            for (int i = 0; i < string.length() - 3; i++)
                if(isABBA(string.substring(i,i+4))) return true;
        return false;
    }

    private static boolean isABBA(String input) {
        return input.charAt(0) == input.charAt(3)
                && input.charAt(1) == input.charAt(2)
                && input.charAt(0) != input.charAt(1);
    }

    private static List<String> listOfABAs(String string){
        List<String> list = new ArrayList<>();
        if (string.length() >= 3)
            for (int i = 0; i < string.length() - 2; i++)
                if(isABA(string.substring(i,i+3)))
                    list.add(string.substring(i,i+3));
        return list;
    }

    private static boolean isABA(String input) {
        return input.charAt(0) == input.charAt(2)
                && input.charAt(0) != input.charAt(1);
    }

    private static String invertABA(String input){
        return input.charAt(1) + "" + input.charAt(0) + "" + input.charAt(1);
    }
}