import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Day05 {

    Day05()  throws NoSuchAlgorithmException, UnsupportedEncodingException{
        String input = "ugkcyxxp";
        long time = System.currentTimeMillis();
        //getPassword(input);
        //getPasswordWithPositions(input);
        getTwoPasswords(input);
        time = System.currentTimeMillis() - time;
        System.out.print("\n");
        System.out.println(time/1000.0 + " s");
    }

    private void getTwoPasswords(String input) throws NoSuchAlgorithmException, UnsupportedEncodingException {

        MessageDigest md = MessageDigest.getInstance("MD5");

        int number = 0, where = 0;
        Character[] firstOutputTable = {'*','*','*','*','*','*','*','*'};
        Character[] secondOutputTable = {'*','*','*','*','*','*','*','*'};
        System.out.print(tableToString(firstOutputTable) + " - " + tableToString(secondOutputTable));

        while (!isNotNull(secondOutputTable)) {
            String nextHash;
            byte[] bytes = (input + Integer.toString(number++)).getBytes();
            nextHash =  String.format("%1$032X", new BigInteger(1, md.digest(bytes)));
            if (nextHash.substring(0,5).equals("00000")){
                char position = nextHash.charAt(5);

                if (where < 8)
                    firstOutputTable[where++] = nextHash.charAt(5);

                if (position >= '0'
                        && position <'8'
                        && secondOutputTable[Integer.parseInt(Character.toString(nextHash.charAt(5)))] == '*'){

                    secondOutputTable[Integer.parseInt(Character.toString(nextHash.charAt(5)))] = nextHash.charAt(6);
                }
                System.out.print("\r" + tableToString(firstOutputTable) + " - " + tableToString(secondOutputTable));
            }
        }
    }

    private String getPassword(String input) throws NoSuchAlgorithmException, UnsupportedEncodingException{

        MessageDigest md = MessageDigest.getInstance("MD5");

        int number = 0;
        String output = "";

        Character[] outputTable = {'*','*','*','*','*','*','*','*'};
        System.out.print(tableToString(outputTable));
        int where = 0;

        while (output.length() < input.length()) {
            String nextHash;
            byte[] bytes = (input + Integer.toString(number++)).getBytes();
            nextHash = String.format("%1$032X", new BigInteger(1, md.digest(bytes)));
            if (nextHash.substring(0, 5).equals("00000")) {
                output += nextHash.charAt(5);
                outputTable[where++] = nextHash.charAt(5);
                System.out.print("\r" + tableToString(outputTable));
            }
        }

        System.out.print("\n");

        return output;
    }

    private String getPasswordWithPositions(String input) throws NoSuchAlgorithmException, UnsupportedEncodingException{

        MessageDigest md = MessageDigest.getInstance("MD5");

        int number = 0;
        String output = "";
        Character[] outputTable = {'*','*','*','*','*','*','*','*'};
        System.out.print(tableToString(outputTable));

        while (!isNotNull(outputTable)) {
            String nextHash;
            byte[] bytes = (input + Integer.toString(number++)).getBytes();
            nextHash =  String.format("%02X", new BigInteger(1, md.digest(bytes)));
            if (nextHash.substring(0,5).equals("00000")){
                char position = nextHash.charAt(5);
                if (position >= '0'
                    && position <'8'
                    && outputTable[Integer.parseInt(Character.toString(nextHash.charAt(5)))] == '*'){

                    outputTable[Integer.parseInt(Character.toString(nextHash.charAt(5)))] = nextHash.charAt(6);
                    System.out.print("\r" + tableToString(outputTable));
                }
            }
        }

        System.out.print("\n");

        for (char c : outputTable)
            output += c;

        return output;
    }

    private boolean isNotNull(Character[] input){
        for (Character c : input)
            if (c == '*') return false;
        return true;
    }

    private String tableToString(Character[] inputTable){
        String outputString = "";
        for (Character c : inputTable)
            outputString += c;
        return outputString;
    }
}
