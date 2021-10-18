import java.io.*;
import java.util.StringTokenizer;

public class No_14499_RollingDice {

    private static int row;
    private static int column;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(input.readLine());

        row = Integer.valueOf(st.nextToken());
        column = Integer.valueOf(st.nextToken());
        int diceX = Integer.valueOf(st.nextToken());
        int diceY = Integer.valueOf(st.nextToken());
        int commandCount = Integer.valueOf(st.nextToken());

        int[][] map = new int[row][column];
        Dice dice = new Dice(diceY, diceX);

        for (int i = 0; i < row; i++) {
            st = new StringTokenizer(input.readLine());
            for (int j = 0; j < column; j++) {
                map[i][j] = Integer.valueOf(st.nextToken());
            }
        }

        st = new StringTokenizer(input.readLine());
        for (int i = 0; i < commandCount; i++) {
            int command = Integer.valueOf(st.nextToken());
            boolean isRolled = dice.roll(command);
            if (isRolled) {
                output.write(String.valueOf(dice.top));
                output.newLine();

                int number = map[dice.y][dice.x];
                if (number == 0) {
                    map[dice.y][dice.x] = dice.bottom;
                } else {
                    dice.copyBottom(number);
                    map[dice.y][dice.x] = 0;
                }
            }
        }

        output.flush();
        output.close();
        input.close();
    }

    private static class Dice {
        final static int EAST = 1;
        final static int WEST = 2;
        final static int NORTH = 3;
        final static int SOUTH = 4;

        int top, bottom, left, right, front, back;
        int x, y;

        Dice(int x, int y) {
            this.x = x;
            this.y = y;
        }

        private boolean roll(int command) {
            if (command == EAST && isValidPosition(x + 1, y)) {
                rollEast();
                ++x;
                return true;
            } else if (command == WEST && isValidPosition(x - 1, y)) {
                rollWest();
                --x;
                return true;
            } else if (command == NORTH && isValidPosition(x, y - 1)) {
                rollNorth();
                --y;
                return true;
            } else if (command == SOUTH && isValidPosition(x, y + 1)) {
                rollSouth();
                ++y;
                return true;
            }

            return false;
        }

        private boolean isValidPosition(int x, int y) {
            return 0 <= x && x < column && 0 <= y && y < row;
        }

        private void rollNorth() {
            int temp = top;
            top = front;
            front = bottom;
            bottom = back;
            back = temp;
        }

        private void rollSouth() {
            int temp = top;
            top = back;
            back = bottom;
            bottom = front;
            front = temp;
        }

        private void rollEast() {
            int temp = top;
            top = left;
            left = bottom;
            bottom = right;
            right = temp;
        }

        private void rollWest() {
            int temp = top;
            top = right;
            right = bottom;
            bottom = left;
            left = temp;
        }

        private void copyBottom(int number) {
            bottom = number;
        }
    }
}
