import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Day5 {
    public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException{
        new Day5();
    }

    Day5()  throws NoSuchAlgorithmException, UnsupportedEncodingException{
        System.out.println(getPassword("ugkcyxxp"));
        System.out.println(getPasswordWithPositions("ugkcyxxp"));
    }

    private String getPassword(String input) throws NoSuchAlgorithmException, UnsupportedEncodingException{

        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(input.getBytes());

        int number = 0;
        String output = "";

        while (output.length() < input.length()) {
            String nextHash;
            md.update((input + Integer.toString(++number)).getBytes());
            nextHash = String.format("%1$032X", new BigInteger(1, md.digest()));
            if (nextHash.substring(0, 5).equals("00000")) {
                System.out.print("*");
                output += nextHash.charAt(5);
            }
        }

        System.out.print("\n");

        return output;
    }

    private boolean isNotNull(Character[] input){
        for (Character c : input)
            if (c == null) return false;
        return true;
    }

    private String getPasswordWithPositions(String input) throws NoSuchAlgorithmException, UnsupportedEncodingException{

        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(input.getBytes());

        int number = 0;
        String output = "";
        Character[] output2 = new Character[8];

        while (!isNotNull(output2)) {
            String nextHash;
            md.update((input + Integer.toString(++number)).getBytes());
            nextHash =  String.format("%1$032X", new BigInteger(1, md.digest()));
            if (nextHash.substring(0,5).equals("00000")){
                char position = nextHash.charAt(5);
                if (position >= '0'
                    && position <'8'
                    && output2[Integer.parseInt(Character.toString(nextHash.charAt(5)))] == null){

                    System.out.print("*");
                    output2[Integer.parseInt(Character.toString(nextHash.charAt(5)))] = nextHash.charAt(6);
                }
            }
        }

        System.out.print("\n");

        for (char c : output2)
            output += c;

        return output;
    }
}
