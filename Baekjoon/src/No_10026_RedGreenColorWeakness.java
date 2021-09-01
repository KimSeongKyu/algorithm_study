import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class No_10026_RedGreenColorWeakness {

    private final static int[] DX = {0, 0, -1, 1};
    private final static int[] DY = {-1, 1, 0, 0};
    private static int mapSize;
    private static char[][] map;
    private static boolean[][] isVisited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        mapSize = Integer.valueOf(br.readLine());
        map = new char[mapSize][mapSize];
        isVisited = new boolean[mapSize][mapSize];

        for (int i = 0; i < mapSize; i++) {
            char[] row = br.readLine().toCharArray();
            for (int j = 0; j < mapSize; j++) {
                map[i][j] = row[j];
            }
        }

        int normalGroupCount = 0;
        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                boolean isNewGroup = false;
                if (!isVisited[i][j]) {
                    normalDfs(j, i, map[i][j]);
                    isNewGroup = true;
                }
                if (isNewGroup) {
                    normalGroupCount++;
                }
            }
        }

        isVisited = new boolean[mapSize][mapSize];
        int colorWeaknessGroupCount = 0;
        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                boolean isNewGroup = false;
                if (!isVisited[i][j]) {
                    colorWeaknessDfs(j, i, map[i][j]);
                    isNewGroup = true;
                }
                if (isNewGroup) {
                    colorWeaknessGroupCount++;
                }
            }
        }

        System.out.println(normalGroupCount + " " + colorWeaknessGroupCount);
    }

    private static void colorWeaknessDfs(int x, int y, char beforeColor) {
        if (beforeColor == 'B' && map[y][x] != 'B') {
            return;
        } else if (beforeColor != 'B' && map[y][x] == 'B') {
            return;
        }

        isVisited[y][x] = true;
        for (int i = 0; i < 4; i++) {
            int nx = x + DX[i];
            int ny = y + DY[i];

            if (0 <= nx && nx < mapSize && 0 <= ny && ny < mapSize && !isVisited[ny][nx]) {
                colorWeaknessDfs(nx, ny, map[y][x]);
            }
        }
    }

    private static void normalDfs(int x, int y, char beforeColor) {
        if (map[y][x] != beforeColor) {
            return;
        }

        isVisited[y][x] = true;
        for (int i = 0; i < 4; i++) {
            int nx = x + DX[i];
            int ny = y + DY[i];

            if (0 <= nx && nx < mapSize && 0 <= ny && ny < mapSize && !isVisited[ny][nx]) {
                normalDfs(nx, ny, map[y][x]);
            }
        }
    }
}
