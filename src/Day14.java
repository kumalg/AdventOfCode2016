import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class Day14 {
    Day14() throws NoSuchAlgorithmException, UnsupportedEncodingException{
        String input = "qzyelonm";
        System.out.println("64th key is (not working properly): " + find64thIndex(input));
    }

    private String find64thIndex(String input) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        Set<Integer> acceptedIndexesWithTriples = new TreeSet<>();
        List<Integer> acceptedList = new ArrayList<>();
        Map<Integer, Character> setOfIndexesWithTriples = new TreeMap<>();
        Map<Integer, Character> setOfIndexesWithFives = new TreeMap<>();
        int index = 0;

        MessageDigest md = MessageDigest.getInstance("MD5");

        while (acceptedList.size() < 74){
            String nextHash;
            byte[] bytes = (input + Integer.toString(index)).getBytes();
            nextHash = String.format("%1$032X", new BigInteger(1, md.digest(bytes))).toLowerCase();
//            for (int x = 0; x < 2016; x++)
//                nextHash = String.format("%1$032X", new BigInteger(1, md.digest(nextHash.getBytes()))).toLowerCase();

            if (containsRepeating(nextHash,3) != null && containsRepeating(nextHash,5) == null){
                setOfIndexesWithTriples.put(index, containsRepeating(nextHash,3));
            }
            else if (containsRepeating(nextHash,5) != null){
                setOfIndexesWithFives.put(index, containsRepeating(nextHash,5));
                for (int ix = index-1000; ix <= index; ix++){
                    if ( containsRepeating(nextHash,5) == setOfIndexesWithTriples.get(ix)
                            && !acceptedList.contains(ix)){
                        acceptedIndexesWithTriples.add(ix);
                        acceptedList.add(ix);
                    }
                }
            }

            System.out.print("\rActual hash: " + index + " | Found keys: " + acceptedList.size());
            index++;
        }

        for (int i : acceptedIndexesWithTriples)
            System.out.println(i);

//        for(int i : acceptedList)
//            System.out.println(i);


        return acceptedList.get(63).toString();
    }

    private Character containsRepeating(String nextHash, int howMany){
        char previousChar = nextHash.charAt(0);
        int howManyTimes = 1;
        for(int i = 1; i < nextHash.length(); i++){
            if (nextHash.charAt(i) == previousChar)
                howManyTimes++;
            else if (howMany > howManyTimes){
                howManyTimes = 1;
                previousChar = nextHash.charAt(i);
            }
        }

        return howManyTimes >= howMany ? previousChar : null;
    }
}
