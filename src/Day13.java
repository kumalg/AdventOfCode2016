import java.util.Arrays;

public class Day13 {
    Day13() {
        int input = 1350;
        boolean[][] table = new boolean[60][60];

        for (int row = 0; row < table.length; row++) {
            for (int col = 0; col < table[row].length; col++) {
                table[row][col] = find0Or1(col, row, input);
                if ((row == 39 && col == 31) || (row == 1 && col == 1))
                    System.out.print('X');
                else if (table[row][col])
                    System.out.print('#');
                else
                    System.out.print('.');
            }
            System.out.print("\n");
        }
    }

    private boolean find0Or1(int x, int y, int input){
        int output = x*x + 3*x + 2*x*y + y + y*y + input;
        return Integer.bitCount(output) % 2 != 0;
    }
}
