import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Kamil on 02.12.2016.
 */
public class day2 {

    private ArrayList<String> keypad1to9Template = new ArrayList<>(Arrays.asList(
            "123",
            "456",
            "789"
    ));

    private List<String> keypad1toDTemplate = Arrays.asList(
            "  1  ",
            " 234 ",
            "56789",
            " ABC ",
            "  D  "
    );

    private List<String> numsString = new ArrayList<>();

    private String findPin (List<String> keypadTemplate, List<String> input, char startChar) throws Exception{
        String pin = "";
        Number number = new Number(keypadTemplate, startChar);

        for (int i = 0; i < input.size(); i++){
            for (char c : input.get(i).toCharArray())
                number.move(c);

            char actualChar = number.getActualChar();
            pin += actualChar;

            number = new Number(keypadTemplate, actualChar);
        }

        return pin;
    }

    private day2() throws Exception{
        numsString = Files.lines(Paths.get("AdventOfCode/describtion/day2/input.txt")).collect(Collectors.toList());

        System.out.println(findPin(keypad1to9Template, numsString, '5'));
        System.out.println(findPin(keypad1toDTemplate, numsString, '5'));
    }

    public static void main(String[] args) throws Exception{
        new day2();
    }
}

class Number {

    private List<String> keypadTemplate;
    private int xPos = 0, yPos = 0;

    public char getActualChar() throws Exception{
        try {
            return keypadTemplate.get(yPos).charAt(xPos);
        } catch (IndexOutOfBoundsException e){
            throw new Exception("Kłopociki :<");
        }
    }

    Number (List<String> pinTemplate, char startChar) {
        for(int i = 0; i < pinTemplate.size(); i++){
            if (pinTemplate.get(i).contains(Character.toString(startChar))){
                for (int j = 0; j < pinTemplate.get(i).length(); j++){
                    if (pinTemplate.get(i).charAt(j) == startChar) {
                        xPos = j;
                        yPos = i;
                        break;
                    }
                }
            }
        }

        this.keypadTemplate = pinTemplate;
    }

    public void move(char direction){
        int newXPos = xPos, newYPos = yPos;
        char newChar = ' ';

        switch (direction){
            case 'U' : {
                newYPos--;
                break;
            }
            case 'D' : {
                newYPos++;
                break;
            }
            case 'L' : {
                newXPos--;
                break;
            }
            case 'R' : {
                newXPos++;
                break;
            }
        }

        if (newYPos>= 0 && newYPos < keypadTemplate.size()){
            if (newXPos >= 0 && newXPos < keypadTemplate.get(newYPos).length()){
                newChar = keypadTemplate.get(newYPos).charAt(newXPos);
            }
        }

        if (newChar != ' '){
            xPos = newXPos;
            yPos = newYPos;
        }
    }
}