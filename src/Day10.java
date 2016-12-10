import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Day10 {
    public static void main(String[] args) throws IOException {
        new Day10();
    }

    private Day10() throws IOException{
        List<String> inputInstructions = Files.lines(Paths.get("AdventOfCode/inputs/day10.txt")).collect(Collectors.toList());

        Map<Integer, Bot> botList = new TreeMap<>();
        Map<Integer, Integer> outputList = new TreeMap<>();

        Set<Integer> allBots = new TreeSet<>();
        Set<Integer> acceptedBots = new TreeSet<>();

        for (String s : inputInstructions.stream().filter(i -> i.startsWith("value")).collect(Collectors.toList())){
            String[] instruction = s.split("\\s+");

            int number = Integer.parseInt(instruction[5]);

            if (!botList.containsKey(number))
                botList.put(number, new Bot(Integer.parseInt(instruction[1])));

            else {
                Bot bot = botList.get(number);
                bot.addValue(Integer.parseInt(instruction[1]));

                botList.put(number, bot);
            }
        }

        do {
            for (String s : inputInstructions.stream().filter(i -> i.startsWith("bot")).collect(Collectors.toList())){
                String[] instruction = s.split("\\s+");
                int botFromNumber = Integer.parseInt(instruction[1]);
                int number;

                if (botList.containsKey(botFromNumber) && botList.get(botFromNumber).values.size() > 1){
                    number = Integer.parseInt(instruction[6]);

                    if (instruction[5].equals("bot")){
                        if (!botList.containsKey(number))
                            botList.put(number , new Bot(botList.get(botFromNumber).getLowValue()));
                        else
                            botList.get(number).addValue(botList.get(botFromNumber).getLowValue());
                    }
                    else
                        outputList.put(number , botList.get(botFromNumber).getLowValue());

                    number = Integer.parseInt(instruction[11]);

                    if (instruction[10].equals("bot")){
                        if (!botList.containsKey(number))
                            botList.put(number , new Bot(botList.get(botFromNumber).getHighValue()));
                        else
                            botList.get(number).addValue(botList.get(botFromNumber).getHighValue());
                    }
                    else
                        outputList.put(number, botList.get(botFromNumber).getHighValue());

                    acceptedBots.add(botFromNumber);
                }
                allBots.add(botFromNumber);
            }
        }
        while (allBots.size() > acceptedBots.size());

        int botNumber = botList
                .entrySet()
                .stream()
                .filter(i -> i.getValue().getLowValue() == 17 && i.getValue().getHighValue() == 61)
                .findFirst()
                .get()
                .getKey();

        System.out.println("Part I:  " + botNumber);
        System.out.println("Part II: " + outputList.get(0)*outputList.get(1)*outputList.get(2));
    }

    private class Bot{
        private Set<Integer> values = new TreeSet<>();

        Bot(int value){
            this.values.add(value);
        }

        public void addValue(int value){
            values.add(value);
        }

        public int getLowValue(){
            return Collections.min(values);
        }

        public int getHighValue(){
            return Collections.max(values);
        }
    }
}
