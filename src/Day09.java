import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Day09 {

    Day09() throws IOException {
        List<String> input = Files.lines(Paths.get("AdventOfCode/inputs/day09.txt")).collect(Collectors.toList());

        System.out.println(decompressedValue(input.get(0)).length());
    }

    private String decompressedValue(String input) {
        List<MarkerWithCharSequence> separateValues = splitToMarkersWithCharSequences(input);
        String decompressedString = "";


        for (MarkerWithCharSequence marker : separateValues)
            decompressedString += marker.huje();

        return decompressedString;
    }

    private List<MarkerWithCharSequence> splitToMarkersWithCharSequences(String input) {
        List<String> allElements = new ArrayList<>(Arrays.asList(input.split("\\)\\(|\\)|\\(")));
        List<SeparateElementDay9> allElements2 = new ArrayList<>();

        allElements.remove("");

        for (int index = 0; index < allElements.size(); index++)
            allElements2.add(new SeparateElementDay9(allElements.get(index), index, allElements.get(index).contains("x")));

//        for (SeparateElementDay9 s : allElements2)
//            System.out.println(s.getIndex() + "   " + s.getValue() + " " + s.getIsMarker());

        List<MarkerWithCharSequence> markerWithCharSequences = new ArrayList<>();

        for (SeparateElementDay9 s : allElements2.stream().filter(i -> !i.getIsMarker()).collect(Collectors.toList())) {
            int searchedIndex = s.getIndex() - 1;
            int howManyMarkers = 0;

            while (searchedIndex >= 0 && allElements2.get(searchedIndex).getIsMarker()) {
                howManyMarkers++;
                searchedIndex--;
            }

            if (howManyMarkers == 0)
                markerWithCharSequences.add(new MarkerWithCharSequence("", s.getValue(), false, false));

            else {
                for (int howmany = 1; howmany < howManyMarkers; howmany++) {
                    markerWithCharSequences.add(new MarkerWithCharSequence(allElements2.get(s.getIndex() - 1).getValue(), s.getValue(), false, true));
                }
                markerWithCharSequences.add(new MarkerWithCharSequence(allElements2.get(s.getIndex() - 1).getValue(), s.getValue(), true, false));
            }
        }

        return markerWithCharSequences;
    }

    class MarkerWithCharSequence {
        private int howManyChars = 0;
        private int howManyTimes = 0;
        private boolean isLast = false;
        // private boolean isRepeated = false;
        private String charSequence = "";

        MarkerWithCharSequence(String marker, String charSequence, boolean isLast, boolean isRepeated) {
            if (!marker.equals("")) {
                this.howManyChars = Integer.parseInt(marker.split("[x]")[0]);
                this.howManyTimes = Integer.parseInt(marker.split("[x]")[1]);
            }
            this.isLast = isLast;
            //   this.isRepeated = isRepeated;

            if (!isRepeated)
                this.charSequence = charSequence;
            else
                this.charSequence = charSequence.substring(0, howManyChars);
        }

        public String getEncryptedCharSequence() {
            String output = "";

            if (howManyTimes != 0) {
                for (int i = 1; i < howManyTimes; i++)
                    output += charSequence.substring(0, howManyChars);
                output += charSequence;
            } else {
                output = charSequence;
            }

//        if (howManyTimes != 0 && !isLast){
//            for (int i = 0; i < howManyTimes; i++)
//                output += charSequence.substring(0,howManyChars);
//        }
//        else if (isLast){
//            output += charSequence.substring(howManyChars);
//        }
//        else
//            output = charSequence;
//
//        return output;
//    }
            return output;
        }

        public String huje() {
            if (howManyChars != 0 && howManyTimes != 0) {
                return "(" + howManyChars + "x" + howManyTimes + ")" + charSequence;
            } else
                return charSequence;
        }
    }

    class SeparateElementDay9 {
        private String value;
        private int index;
        private boolean isMarker;

        SeparateElementDay9(String value, int index, boolean isMarker) {
            this.value = value;
            this.index = index;
            this.isMarker = isMarker;
        }

        public String getValue() {
            return value;
        }

        public int getIndex() {
            return index;
        }

        public boolean getIsMarker() {
            return isMarker;
        }
    }
}