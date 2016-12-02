import java.util.ArrayList;

/**
 * Created by Kamil on 02.12.2016.
 */
public class day2 {
    private String input = "LURLLLLLDUULRDDDRLRDDDUDDUULLRLULRURLRRDULUUURDUURLRDRRURUURUDDRDLRRLDDDDLLDURLDUUUDRDDDLULLDDLRLRRRLDLDDDDDLUUUDLUULRDUDLDRRRUDUDDRULURULDRUDLDUUUDLUDURUURRUUDRLDURRULURRURUUDDLRLDDDDRDRLDDLURLRDDLUDRLLRURRURRRURURRLLRLDRDLULLUDLUDRURDLRDUUDDUUDRLUDDLRLUDLLURDRUDDLRURDULLLUDDURULDRLUDLUDLULRRUUDDLDRLLUULDDURLURRRRUUDRUDLLDRUDLRRDUDUUURRULLDLDDRLUURLDUDDRLDRLDULDDURDLUUDRRLDRLLLRRRDLLLLURDLLLUDRUULUULLRLRDLULRLURLURRRDRLLDLDRLLRLULRDDDLUDDLLLRRLLLUURLDRULLDURDLULUDLRLDLUDURLLLURUUUDRRRULRDURLLURRLDLRLDLDRRUUDRDDDDDRDUUDULUL\n" +
            "RRURLURRULLUDUULUUURURULLDLRLRRULRUDUDDLLLRRRRLRUDUUUUDULUDRULDDUDLURLRRLLDLURLRDLDUULRDLLLDLLULLURLLURURULUDLDUDLUULDDLDRLRRUURRRLLRRLRULRRLDLDLRDULDLLDRRULRDRDUDUUUDUUDDRUUUDDLRDULLULDULUUUDDUULRLDLRLUUUUURDLULDLUUUULLLLRRRLDLLDLUDDULRULLRDURDRDRRRDDDLRDDULDLURLDLUDRRLDDDLULLRULDRULRURDURRUDUUULDRLRRUDDLULDLUULULRDRDULLLDULULDUDLDRLLLRLRURUDLUDDDURDUDDDULDRLUDRDRDRLRDDDDRLDRULLURUDRLLUDRLDDDLRLRDLDDUULRUDRLUULRULRLDLRLLULLUDULRLDRURDD\n" +
            "UUUUUURRDLLRUDUDURLRDDDURRRRULRLRUURLLLUULRUDLLRUUDURURUDRDLDLDRDUDUDRLUUDUUUDDURRRDRUDDUURDLRDRLDRRULULLLUDRDLLUULURULRULDRDRRLURULLDURUURDDRDLLDDDDULDULUULLRULRLDURLDDLULRLRRRLLURRLDLLULLDULRULLDLRULDDLUDDDLDDURUUUURDLLRURDURDUUDRULDUULLUUULLULLURLRDRLLRULLLLRRRRULDRULLUURLDRLRRDLDDRLRDURDRRDDDRRUDRLUULLLULRDDLDRRLRUDLRRLDULULRRDDURULLRULDUDRLRUUUULURLRLRDDDUUDDULLULLDDUDRLRDDRDRLDUURLRUULUULDUDDURDDLLLURUULLRDLRRDRDDDUDDRDLRRDDUURDUULUDDDDUUDDLULLDRDDLULLUDLDDURRULDUDRRUURRDLRLLDDRRLUUUDDUUDUDDDDDDDLULURRUULURLLUURUDUDDULURDDLRDDRRULLLDRRDLURURLRRRDDLDUUDR\n" +
            "URLLRULULULULDUULDLLRDUDDRRLRLLLULUDDUDLLLRURLLLLURRLRRDLULRUDDRLRRLLRDLRRULDLULRRRRUUDDRURLRUUDLRRULDDDLRULDURLDURLRLDDULURDDDDULDRLLUDRULRDDLUUUDUDUDDRRUDUURUURLUUULRLULUURURRLRUUULDDLURULRRRRDULUDLDRLLUURRRLLURDLDLLDUDRDRLLUDLDDLRLDLRUDUULDRRLLULDRRULLULURRLDLUUDLUDDRLURDDUDRDUDDDULLDRUDLRDLRDURUULRRDRUUULRUURDURLDUDRDLLRUULUULRDDUDLRDUUUUULDDDDDRRULRURLLRLLUUDLUDDUULDRULDLDUURUDUDLRULULUULLLLRLULUDDDRRLLDRUUDRLDDDRDDURRDDDULURDLDLUDDUULUUURDULDLLULRRUURDDUDRUULDLRLURUDLRDLLLDRLDUURUDUDRLLLDDDULLUDUUULLUUUDLRRRURRRRRDUULLUURRDUU\n" +
            "UDULUUDLDURRUDDUDRDDRRUULRRULULURRDDRUULDRLDUDDRRRRDLRURLLLRLRRLLLULDURRDLLDUDDULDLURLURUURLLLDUURRUUDLLLUDRUDLDDRLRRDLRLDDDULLRUURUUUDRRDLLLRRULDRURLRDLLUDRLLULRDLDDLLRRUDURULRLRLDRUDDLUUDRLDDRUDULLLURLRDLRUUDRRUUDUDRDDRDRDDLRULULURLRULDRURLURLRDRDUUDUDUULDDRLUUURULRDUDRUDRULUDDULLRDDRRUULRLDDLUUUUDUDLLLDULRRLRDDDLULRDUDRLDLURRUUDULUDRURUDDLUUUDDRLRLRLURDLDDRLRURRLLLRDRLRUUDRRRLUDLDLDDDLDULDRLURDURULURUDDDUDUULRLLDRLDDDDRULRDRLUUURD";
    private String[] numsString = input.split("\n");
    private Number num = new Number(5);
    private Number1D num1D = new Number1D('5');
    private ArrayList<Integer> nums = new ArrayList<>();
    private ArrayList<Character> nums1D = new ArrayList<>();

    private day2(){

        for (int i = 0; i < numsString.length; i++){
            for (char c : numsString[i].toCharArray()){
                num.move(c);
                num1D.move(c);
            }
            nums.add(i,num.getActualNum());
            nums1D.add(i,num1D.getActualChar());
            num = new Number(nums.get(i));
            num1D = new Number1D(nums1D.get(i));
        }

        for (int i : nums){
            System.out.print(i);
        }

        System.out.print("\n");

        for (char i1D : nums1D){
            System.out.print(i1D);
        }
    }

    public static void main(String[] args){
        new day2();
    }

}

class Number1D {
    private char actualNum;

    Number1D(char startNum){
        actualNum = startNum;
    }

    public char getActualChar(){
        return actualNum;
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

class Number {
    private int actualNum;

    Number(int startNum){
        actualNum = startNum;
    }

    public int getActualNum(){
        return actualNum;
    }

    public void move(char where){
        switch (actualNum){
            case 1:{
                switch (where){
                    case 'D':
                        actualNum = 4;
                        break;
                    case 'R':
                        actualNum = 2;
                        break;
                }
                break;
            }

            case 2:{
                switch (where){
                    case 'D':
                        actualNum = 5;
                        break;
                    case 'L':
                        actualNum = 1;
                        break;
                    case 'R':
                        actualNum = 3;
                        break;
                }
                break;
            }

            case 3:{
                switch (where){
                    case 'D':
                        actualNum = 6;
                        break;
                    case 'L':
                        actualNum = 2;
                        break;
                }
                break;
            }

            case 4:{
                switch (where){
                    case 'U':
                        actualNum = 1;
                        break;
                    case 'D':
                        actualNum = 7;
                        break;
                    case 'R':
                        actualNum = 5;
                        break;
                }
                break;
            }

            case 5:{
                switch (where){
                    case 'U':
                        actualNum = 2;
                        break;
                    case 'D':
                        actualNum = 8;
                        break;
                    case 'L':
                        actualNum = 4;
                        break;
                    case 'R':
                        actualNum = 6;
                        break;
                }
                break;
            }

            case 6:{
                switch (where){
                    case 'U':
                        actualNum = 3;
                        break;
                    case 'D':
                        actualNum = 9;
                        break;
                    case 'L':
                        actualNum = 5;
                        break;
                }
                break;
            }

            case 7:{
                switch (where){
                    case 'U':
                        actualNum = 4;
                        break;
                    case 'R':
                        actualNum = 8;
                        break;
                }
                break;
            }

            case 8:{
                switch (where){
                    case 'U':
                        actualNum = 5;
                        break;
                    case 'L':
                        actualNum = 7;
                        break;
                    case 'R':
                        actualNum = 9;
                        break;
                }
                break;
            }

            case 9:{
                switch (where){
                    case 'U':
                        actualNum = 6;
                        break;
                    case 'L':
                        actualNum = 8;
                        break;
                }
                break;
            }

        }
    }
}
