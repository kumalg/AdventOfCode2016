import java.util.ArrayList;
import java.util.List;

public class Day10x2015 {
    public static void main(String[] args){
        new Day10x2015();
    }

    private Day10x2015(){
        List<Character> list = new ArrayList<>();

        for(char c : Integer.toString(1113222113).toCharArray())
            list.add(c);

        for (int i = 0 ; i < 50 ; i++)
            list = nextStep(list);

        System.out.print(list.size());
    }

    private List<Character> nextStep(List<Character> previousStep){
        List<Character> returnList = new ArrayList<>();
        List<NumberX> list = new ArrayList<>();

        char actualNumber = previousStep.get(0);
        int howMany = 1;

        for(int i = 1; i < previousStep.size(); i++){
            if (actualNumber == previousStep.get(i)){
                howMany++;
            }
            else{
                list.add(new NumberX(actualNumber, howMany));
                actualNumber = previousStep.get(i);
                howMany = 1;
            }
        }

        list.add(new NumberX(actualNumber, howMany));

        for(NumberX n : list){
            returnList.add(Integer.toString(n.getHowMany()).charAt(0));
            returnList.add(n.getNumber());
        }

        return returnList;
    }
}

class NumberX {
    private char number;
    private int howMany;

    NumberX(char number, int howMany){
        this.number = number;
        this.howMany = howMany;
    }

    public char getNumber(){ return number; }
    public int getHowMany(){ return howMany; }
}
