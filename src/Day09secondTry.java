import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Kamil on 09.12.2016.
 */
public class Day09secondTry {
    public static void main(String[] args) throws IOException {
        new Day09secondTry();
    }

    private Day09secondTry() throws IOException{
        List<String> input = Files.lines(Paths.get("AdventOfCode/inputs/day9.txt")).collect(Collectors.toList());
//        decompressedValue("ADVENT");
//        decompressedValue("A(1x5)BC");
//        decompressedValue("(3x3)XYZ");
//        decompressedValue("A(2x2)BCD(2x2)EFG");
//        decompressedValue("(6x1)(1x3)A");
//        decompressedValue("X(8x2)(3x3)ABCY");

        decompressedValue(input.get(0));
        decompressedValue2(input.get(0));
    }

    private void decompressedValue(String input){
        String output = splitToMarkersWithCharSequences(input);

      //  System.out.println(output);
        System.out.println(output.length());
    }

    private String splitToMarkersWithCharSequences(String input){
        List<String> allElements = new ArrayList<>(Arrays.asList(input.split("\\)\\(|\\)|\\(")));
        List<SeparateElementDay9> allElements2 = new ArrayList<>();

        allElements.remove("");

        for (int index = 0; index < allElements.size(); index++)
            allElements2.add(new SeparateElementDay9(allElements.get(index), index, allElements.get(index).contains("x")));

        String output = "";

        for (int elementIndex = 0; elementIndex < allElements2.size(); elementIndex++){
            if (!allElements2.get(elementIndex).getIsMarker())
                output += allElements2.get(elementIndex).getValue();

            else {
                String changeTo = "";
                int howManyChars = Integer.parseInt(allElements2.get(elementIndex).getValue().split("[x]")[0]);
                int howManyTimes = Integer.parseInt(allElements2.get(elementIndex).getValue().split("[x]")[1]);

                if (!allElements2.get(elementIndex+1).getIsMarker())
                    for (int dupa = 1; dupa < howManyTimes; dupa++)
                        changeTo += allElements2.get(elementIndex+1).getValue().substring(0,howManyChars);

                else {
                    int index = elementIndex;
                    while (allElements2.size() > ++index && allElements2.get(index).getIsMarker()){

                        int i = elementIndex + 1;

                        while (changeTo.length() < howManyChars){
                            if(allElements2.size() > i){
                                elementIndex++;
                                if (allElements2.get(i).getIsMarker())
                                    changeTo += "(" + allElements2.get(i).getValue() + ")";
                                else
                                    changeTo += allElements2.get(i).getValue();
                                i++;
                            }
                        }

                        changeTo = changeTo.substring(0,howManyChars);

                        for (int times = 1; times < howManyTimes; times++)
                            changeTo += changeTo;

                        changeTo = changeTo.substring(0,howManyChars*howManyTimes);

                        if(changeTo.length()-1 > changeTo.lastIndexOf(')'))
                            changeTo = changeTo.substring(0,changeTo.lastIndexOf(')')+1);

                    }
                    elementIndex--;
                }
                output += changeTo;
            }
        }

        return output;
    }

    private void decompressedValue2(String input){
        System.out.println(splitToMarkersWithCharSequences2(input));
//        System.out.print(output.length());

//        while (output.contains("x")){
//            output = splitToMarkersWithCharSequences(output);
//            System.out.print("\r" + output.length());
//        }
    }

    private int splitToMarkersWithCharSequences2(String input){
        String output = input;

        do{
            int indexStart = output.indexOf("(");
            int indexEnd = output.indexOf(")") + 1;
            String bracket = output.substring(indexStart, indexEnd);
            int howManyChars = Integer.parseInt(bracket.substring(1,bracket.indexOf("x")));
            int howManyTimes = Integer.parseInt(bracket.substring(bracket.indexOf("x")+1,bracket.length()-1));

            String sequenceToCopy = output.substring(indexEnd,indexEnd+howManyChars);
            String repeatedSequenceToCopy = sequenceToCopy;

            for (int i = 2; i < howManyTimes; i++)
                repeatedSequenceToCopy += sequenceToCopy;

            String tro = output.substring(0,indexStart) + repeatedSequenceToCopy + output.substring(indexEnd);
            System.out.print("\r" + tro.length());
            output = tro;
        }
        while (output.contains("x"));

        return output.length();
    }

    class SeparateElementDay9 {
        private String value;
        private int index;
        private boolean isMarker;

        SeparateElementDay9(String value, int index, boolean isMarker){
            this.value = value;
            this.index = index;
            this.isMarker = isMarker;
        }

        public String getValue(){
            return value;
        }

        public int getIndex(){
            return index;
        }

        public void setValue(String value){
            this.value = value;
        }

        public boolean getIsMarker(){
            return isMarker;
        }
    }

}