import java.util.Arrays;
import java.util.ArrayList;

public class adv1 {

    private String allMoves = "L3, R1, L4, L1, L2, R4, L3, L3, R2, R3, L5, R1, R3, L4, L1, L2, R2, R1, L4, L4, R2, L5, R3, R2, R1, L1, L2, R2, R2, L1, L1, R2, R1, L3, L5, R4, L3, R3, R3, L5, L190, L4, R4, R51, L4, R5, R5, R2, L1, L3, R1, R4, L3, R1, R3, L5, L4, R2, R5, R2, L1, L5, L1, L1, R78, L3, R2, L3, R5, L2, R2, R4, L1, L4, R1, R185, R3, L4, L1, L1, L3, R4, L4, L1, R5, L5, L1, R5, L1, R2, L5, L2, R4, R3, L2, R3, R1, L3, L5, L4, R3, L2, L4, L5, L4, R1, L1, R5, L2, R4, R2, R3, L1, L1, L4, L3, R4, L3, L5, R2, L5, L1, L1, R2, R3, L5, L3, L2, L1, L4, R4, R4, L2, R3, R1, L2, R1, L2, L2, R3, R3, L1, R4, L5, L3, R4, R4, R1, L2, L5, L3, R1, R4, L2, R5, R4, R2, L5, L3, R4, R1, L1, R5, L3, R1, R5, L2, R1, L5, L2, R2, L2, L3, R3, R3, R1";
    private ArrayList<String> allMovesArray = new ArrayList<>();
    private Position position = new Position();

    private adv1(){
        toArrayList(allMoves);

        for (String s : allMovesArray)
            position.go(s);

        System.out.println("Hearthquaqe position: " + position.getPositionString());
        System.out.println("First repeated position: " + position.getFirstRepeatedPositionString());
    }

    private void toArrayList(String allMoves){
        allMovesArray = new ArrayList<>(Arrays.asList(allMoves.split(", ")));
    }

    public static void main(String[] args) {
        new adv1();
    }
}

class Position {
    private ArrayList<Position> listOfAllPositions;
    private Position firstRepeatedPosition = null;
    private int x,y;
    private char direction;

    public Position(){
        x = 0;
        y = 0;
        direction = 'N';

        listOfAllPositions = new ArrayList<>();
        listOfAllPositions.add(new Position(0,0));
    }

    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    public String getPositionString(){
        return "x: " + x + " | y: " + y + " | Blocks: " + (Math.abs(x)+Math.abs(y));
    }

    public String getFirstRepeatedPositionString(){
        return firstRepeatedPosition.getPositionString();
    }

    @Override
    public boolean equals(Object o){
        Position secondPosition = (Position)o;
        return x == secondPosition.x && y == secondPosition.y;
    }

    public void go(String dupa){
        int howManyBlocks = Integer.parseInt(dupa.substring(1,dupa.length()));
        changeDirection(dupa.charAt(0));

        for (int i = 0; i < howManyBlocks ; i++){
            switch (direction){
                case 'N':{
                    y++;
                    break;
                }
                case 'S':{
                    y--;
                    break;
                }
                case 'E':{
                    x++;
                    break;
                }
                case 'W':{
                    x--;
                    break;
                }
            }
            tryAddNewPositionToList(x,y);
        }
    }

    private void tryAddNewPositionToList(int x, int y){
        if (!listOfAllPositions.contains(new Position(x,y)))
            listOfAllPositions.add(new Position(x,y));
        else{
            if (firstRepeatedPosition == null)
                firstRepeatedPosition = new Position(x,y);
        }
    }

    private void changeDirection(char leftOrRight){
        switch (direction){
            case 'N' : {
                    if (leftOrRight == 'R')
                        direction = 'E';
                    else if (leftOrRight == 'L')
                        direction = 'W';
                    break;
                }
            case 'S' : {
                if (leftOrRight == 'R')
                    direction = 'W';
                else if (leftOrRight == 'L')
                    direction = 'E';
                break;
            }
            case 'E' : {
                if (leftOrRight == 'R')
                    direction = 'S';
                else if (leftOrRight == 'L')
                    direction = 'N';
                break;
            }
            case 'W' : {
                if (leftOrRight == 'R')
                    direction = 'N';
                else if (leftOrRight == 'L')
                    direction = 'S';
                break;
            }
        }
    }
}
