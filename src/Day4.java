import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Day4 {
    public static void main (String[] args) throws IOException{
        List<String> listOfRooms = Files.lines(Paths.get("AdventOfCode/description/day4/input.txt")).collect(Collectors.toList());

        int allID = 0;

        for(String roomString : listOfRooms){
            Room room = new Room(roomString);
            if (new Room(roomString).isRealRoom())
                allID += room.getSectorID();
        }

        System.out.print(allID);
    }
}

class Char {
    private char aChar;
    private int number;

    Char(char aChar, int number){
        this.aChar = aChar;
        this.number = number;
    }

    public char getAChar(){ return aChar; }

    public int getNumber(){ return number; }
}

class Room {
    private String encryptedName;
    private int sectorID;
    private String checkSum;

    Room (String encryptedRoom){
        int lastDash = encryptedRoom.lastIndexOf('-');
        int firstBracket;

        encryptedName = encryptedRoom.substring(0,lastDash).replace("-","");

        String iDAndCheckSum = encryptedRoom.substring(lastDash+1).replace("]","");
        firstBracket = iDAndCheckSum.indexOf('[');

        sectorID = Integer.parseInt(iDAndCheckSum.substring(0,firstBracket));
        checkSum = iDAndCheckSum.substring(firstBracket+1);
    }

    public boolean isRealRoom(Room this){
        List<Char> yco = new ArrayList<>();
        Map<Character, Integer> map = getCharCounts(this.getEncryptedName());

        String realCheckSum = "";

        for (char c : map.keySet()){
            yco.add(new Char(c, map.get(c)));
        }

        Collections.sort(yco, (Char o1, Char o2) -> new Integer(o2.getNumber()).compareTo(o1.getNumber()));

        for (int i = 0; i<5;i++)
            realCheckSum += yco.get(i).getAChar();

        return realCheckSum.equals(this.getCheckSum());
    }

    private static Map<Character, Integer> getCharCounts(String encryptedName) {
        Map<Character, Integer> words = new TreeMap<>();

        for (char c : encryptedName.toCharArray())
            words.put(c,words.getOrDefault(c,0)+1);

        return words;
    }

    private String getEncryptedName(){ return encryptedName; }

    public int getSectorID(){ return sectorID; }

    private String getCheckSum(){ return checkSum; }
}


