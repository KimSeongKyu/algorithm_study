import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class No_1520_Downhill {

    private final static int[] DX = {0, 0, -1, 1};
    private final static int[] DY = {-1, 1, 0, 0};

    private static int[][] map;
    private static boolean[][] hasPath;
    private static int availablePathCount = 0;
    private static int row, column;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        row = Integer.valueOf(st.nextToken());
        column = Integer.valueOf(st.nextToken());

        map = new int[row][column];
        for (int i = 0; i < row; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < column; j++) {
                map[i][j] = Integer.valueOf(st.nextToken());
            }
        }

        boolean[][] isVisited = new boolean[row][column];
        hasPath = new boolean[row][column];

        goDownhill(isVisited, 0, 0);

        System.out.println(availablePathCount);
    }

    private static boolean goDownhill(boolean[][] isVisited, int x, int y) {
        if ((x == column - 1 && y == row - 1) || hasPath[y][x]) {
            availablePathCount++;
            return true;
        }

        isVisited[y][x] = true;
        for (int k = 0; k < 4; k++) {
            int nx = x + DX[k];
            int ny = y + DY[k];

            if(0 <= nx && nx < column && 0 <= ny && ny < row && !isVisited[ny][nx] && map[ny][nx] < map[y][x]) {
                hasPath[ny][nx] = goDownhill(isVisited, nx, ny);
            }
        }

        isVisited[y][x] = false;
        return false;
    }
}
