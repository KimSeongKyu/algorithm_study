import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class No_15685_DragonCurve {

    private final static int[] DX = {1, 0, -1, 0};
    private final static int[] DY = {0, -1, 0, 1};

    private static boolean[][] map = new boolean[101][101];
    private static int squareCountInDragonCurve = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        int dragonCurveCount = Integer.valueOf(input.readLine());
        for (int i = 0; i < dragonCurveCount; i++) {
            StringTokenizer st = new StringTokenizer(input.readLine());
            int x = Integer.valueOf(st.nextToken());
            int y = Integer.valueOf(st.nextToken());
            int direction = Integer.valueOf(st.nextToken());
            int generation = Integer.valueOf(st.nextToken());

            dragonCurve(x, y, direction, generation);
        }

        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (map[i][j] && map[i + 1][j] && map[i][j + 1] && map[i + 1][j + 1]) {
                    ++squareCountInDragonCurve;
                }
            }
        }

        System.out.println(squareCountInDragonCurve);
        input.close();
    }

    private static void dragonCurve(int x, int y, int startDirection, int generation) {
        List<Integer> directions = new ArrayList<>();
        directions.add(startDirection);

        for (int i = 1; i <= generation; i++) {
            int beforeGenerationLineCount = directions.size();
            for (int j = beforeGenerationLineCount - 1; j >= 0; j--) {
                directions.add((directions.get(j) + 1) % 4);
            }
        }

        map[y][x] = true;
        for (int direction : directions) {
            x += DX[direction];
            y += DY[direction];
            map[y][x] = true;
        }
    }
}
