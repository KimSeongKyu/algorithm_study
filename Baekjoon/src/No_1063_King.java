import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class No_1063_King {

    private final static String[] COLUMN = {"A", "B", "C", "D", "E", "F", "G", "H"};
    private final static String[] ROW = {"8", "7", "6", "5", "4", "3", "2", "1"};

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(input.readLine());

        String kingPosition = st.nextToken();
        String stonePosition = st.nextToken();
        int moveCount = Integer.valueOf(st.nextToken());

        Position king = createPosition(kingPosition);
        Position stone = createPosition(stonePosition);

        for (int i = 0; i < moveCount; i++) {
            String command = input.readLine();
            simulate(Command.valueOf(command), king, stone);
        }

        System.out.println(COLUMN[king.x] + ROW[king.y]);
        System.out.println(COLUMN[stone.x] + ROW[stone.y]);
    }

    private static Position createPosition(String position) {
        int column = position.charAt(0) - 'A';
        int row = 7 - (position.charAt(1) - '1');

        return new Position(column, row);
    }

    private static void simulate(Command command, Position king, Position stone) {
        int nextX = king.x + command.x;
        int nextY = king.y + command.y;

        if(isValidPosition(nextX, nextY)) {
            if(isSamePosition(stone, nextX, nextY)) {
                if(!isValidPosition(stone.x + command.x, stone.y + command.y)) {
                    return;
                }
                move(command, stone);
            }
            move(command, king);
        }
    }

    private static void move(Command command, Position position) {
        position.x += command.x;
        position.y += command.y;
    }

    private static boolean isSamePosition(Position stone, int kingX, int kingY) {
        return stone.x == kingX && stone.y == kingY;
    }

    private static boolean isValidPosition(int x, int y) {
        return 0 <= x && x < 8 && 0 <= y && y < 8;
    }

    private enum Command {
        R(1, 0),
        L(-1, 0),
        B(0, 1),
        T(0, -1),
        RT(1, -1),
        LT(-1, -1),
        RB(1, 1),
        LB(-1, 1);

        int x;
        int y;

        Command(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static class Position {
        int x;
        int y;

        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
