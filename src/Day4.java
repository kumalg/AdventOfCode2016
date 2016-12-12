import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Day4 {

    Day4() throws IOException {
        List<String> listOfRooms = Files.lines(Paths.get("AdventOfCode/inputs/day4.txt")).collect(Collectors.toList());

        int allID = 0;

        for (String roomString : listOfRooms) {
            Room room = new Room(roomString);
            if (room.decryptName().equals("northpole object storage"))
                System.out.println("Sector ID of the room where North Pole objects are stored: " + room.getSectorID());
            if (room.isRealRoom())
                allID += room.getSectorID();
        }

        System.out.println("                  Sum of the sector IDs of the real rooms: " + allID);
    }

    private class Char {
        private char aChar;
        private int number;

        Char(char aChar, int number) {
            this.aChar = aChar;
            this.number = number;
        }

        public char getChar() {
            return aChar;
        }

        public int getNumber() {
            return number;
        }
    }

    private class Room {
        private String encryptedName;
        private int sectorID;
        private String checkSum;

        Room(String encryptedRoom) {
            int lastDash = encryptedRoom.lastIndexOf('-');
            int firstBracket;

            encryptedName = encryptedRoom.substring(0, lastDash).replace("-", " ");

            String iDAndCheckSum = encryptedRoom.substring(lastDash + 1).replace("]", "");
            firstBracket = iDAndCheckSum.indexOf('[');

            sectorID = Integer.parseInt(iDAndCheckSum.substring(0, firstBracket));
            checkSum = iDAndCheckSum.substring(firstBracket + 1);
        }

        public String decryptName() {
            String decryptedName = "";
            int howMany = sectorID % 26;

            for (char encryptedChar : encryptedName.toCharArray())
                decryptedName += encryptedChar == ' ' ? ' ' : (char) ((encryptedChar - 'a' + howMany) % 26 + 'a');

            return decryptedName;
        }

        public boolean isRealRoom(Room this) { // this jest niepotrzebny, ale dobrze wiedzieć że działa nawet wywołanie samego .isRealRoom()
            List<Char> listOfLetters = new ArrayList<>();
            Map<Character, Integer> mapOfLetters = getCharCounts(this.getEncryptedName().replace(" ", ""));

            String realCheckSum = "";

            for (char c : mapOfLetters.keySet()) {
                listOfLetters.add(new Char(c, mapOfLetters.get(c)));
            }

            Collections.sort(listOfLetters, (Char o1, Char o2) -> new Integer(o2.getNumber()).compareTo(o1.getNumber()));

            for (int i = 0; i < 5; i++)
                realCheckSum += listOfLetters.get(i).getChar();

            return realCheckSum.equals(this.getCheckSum());
        }

        private Map<Character, Integer> getCharCounts(String encryptedName) {
            Map<Character, Integer> words = new TreeMap<>();

            for (char c : encryptedName.toCharArray())
                words.put(c, words.getOrDefault(c, 0) + 1);

            return words;
        }

        private String getEncryptedName() {
            return encryptedName;
        }

        public int getSectorID() {
            return sectorID;
        }

        private String getCheckSum() {
            return checkSum;
        }
    }
}