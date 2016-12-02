import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Kamil on 02.12.2016.
 */
public class day2 {

    private ArrayList<String> numsString = new ArrayList<>();
    private Number1to9 num1to9 = new Number1to9('5');
    private Number1toD num1toD = new Number1toD('5');
    private ArrayList<Character> pin1to9 = new ArrayList<>();
    private ArrayList<Character> pin1toD = new ArrayList<>();

    private day2() throws IOException{

        BufferedReader in = new BufferedReader(new FileReader("AdventOfCode/describtion/day2/input.txt"));

        String line;
        while((line = in.readLine()) != null) {
            numsString.add(line);
        }
        in.close();

        for (int i = 0; i < numsString.size(); i++){
            for (char c : numsString.get(i).toCharArray()){
                num1to9.move(c);
                num1toD.move(c);
            }
            pin1to9.add(i,num1to9.getActualChar());
            pin1toD.add(i,num1toD.getActualChar());
            num1to9 = new Number1to9(pin1to9.get(i));
            num1toD = new Number1toD(pin1toD.get(i));
        }

        for (char char1to9 : pin1to9){
            System.out.print(char1to9);
        }

        System.out.print("\n");

        for (char char1toD : pin1toD){
            System.out.print(char1toD);
        }
    }

    public static void main(String[] args) throws IOException{
        new day2();
    }

}

abstract class Number {
    protected char actualNum;

    public char getActualChar(){
        return actualNum;
    }
}

class Number1toD extends Number{

    Number1toD(char startNum){
        actualNum = startNum;
    }

    public void move(char where){
        switch (actualNum){
            case '1':{
                switch (where){
                    case 'D':
                        actualNum = '3';
                        break;
                }
                break;
            }

            case '2':{
                switch (where){
                    case 'D':
                        actualNum = '6';
                        break;
                    case 'R':
                        actualNum = '3';
                        break;
                }
                break;
            }

            case '3':{
                switch (where){
                    case 'U':
                        actualNum = '1';
                        break;
                    case 'D':
                        actualNum = '7';
                        break;
                    case 'L':
                        actualNum = '2';
                        break;
                    case 'R':
                        actualNum = '4';
                        break;
                }
                break;
            }

            case '4':{
                switch (where){
                    case 'D':
                        actualNum = '8';
                        break;
                    case 'L':
                        actualNum = '3';
                        break;
                }
                break;
            }

            case '5':{
                switch (where){
                    case 'R':
                        actualNum = '6';
                        break;
                }
                break;
            }

            case '6':{
                switch (where){
                    case 'U':
                        actualNum = '2';
                        break;
                    case 'D':
                        actualNum = 'A';
                        break;
                    case 'L':
                        actualNum = '5';
                        break;
                    case 'R':
                        actualNum = '7';
                        break;
                }
                break;
            }

            case '7':{
                switch (where){
                    case 'U':
                        actualNum = '3';
                        break;
                    case 'D':
                        actualNum = 'B';
                        break;
                    case 'L':
                        actualNum = '6';
                        break;
                    case 'R':
                        actualNum = '8';
                        break;
                }
                break;
            }

            case '8':{
                switch (where){
                    case 'U':
                        actualNum = '4';
                        break;
                    case 'D':
                        actualNum = 'C';
                        break;
                    case 'L':
                        actualNum = '7';
                        break;
                    case 'R':
                        actualNum = '9';
                        break;
                }
                break;
            }

            case '9':{
                switch (where){
                    case 'L':
                        actualNum = '8';
                        break;
                }
                break;
            }

            case 'A':{
                switch (where){
                    case 'U':
                        actualNum = '6';
                        break;
                    case 'R':
                        actualNum = 'B';
                        break;
                }
                break;
            }

            case 'B':{
                switch (where){
                    case 'U':
                        actualNum = '7';
                        break;
                    case 'D':
                        actualNum = 'D';
                        break;
                    case 'L':
                        actualNum = 'A';
                        break;
                    case 'R':
                        actualNum = 'C';
                        break;
                }
                break;
            }

            case 'C':{
                switch (where){
                    case 'U':
                        actualNum = '8';
                        break;
                    case 'L':
                        actualNum = 'B';
                        break;
                }
                break;
            }

            case 'D':{
                switch (where){
                    case 'U':
                        actualNum = 'B';
                        break;
                }
                break;
            }

        }
    }
}

class Number1to9 extends Number{

    Number1to9(char startNum){
        actualNum = startNum;
    }

    public void move(char where){
        switch (actualNum){
            case '1':{
                switch (where){
                    case 'D':
                        actualNum = '4';
                        break;
                    case 'R':
                        actualNum = '2';
                        break;
                }
                break;
            }

            case '2':{
                switch (where){
                    case 'D':
                        actualNum = '5';
                        break;
                    case 'L':
                        actualNum = '1';
                        break;
                    case 'R':
                        actualNum = '3';
                        break;
                }
                break;
            }

            case '3':{
                switch (where){
                    case 'D':
                        actualNum = '6';
                        break;
                    case 'L':
                        actualNum = '2';
                        break;
                }
                break;
            }

            case '4':{
                switch (where){
                    case 'U':
                        actualNum = '1';
                        break;
                    case 'D':
                        actualNum = '7';
                        break;
                    case 'R':
                        actualNum = '5';
                        break;
                }
                break;
            }

            case '5':{
                switch (where){
                    case 'U':
                        actualNum = '2';
                        break;
                    case 'D':
                        actualNum = '8';
                        break;
                    case 'L':
                        actualNum = '4';
                        break;
                    case 'R':
                        actualNum = '6';
                        break;
                }
                break;
            }

            case '6':{
                switch (where){
                    case 'U':
                        actualNum = '3';
                        break;
                    case 'D':
                        actualNum = '9';
                        break;
                    case 'L':
                        actualNum = '5';
                        break;
                }
                break;
            }

            case '7':{
                switch (where){
                    case 'U':
                        actualNum = '4';
                        break;
                    case 'R':
                        actualNum = '8';
                        break;
                }
                break;
            }

            case '8':{
                switch (where){
                    case 'U':
                        actualNum = '5';
                        break;
                    case 'L':
                        actualNum = '7';
                        break;
                    case 'R':
                        actualNum = '9';
                        break;
                }
                break;
            }

            case '9':{
                switch (where){
                    case 'U':
                        actualNum = '6';
                        break;
                    case 'L':
                        actualNum = '8';
                        break;
                }
                break;
            }

        }
    }
}
