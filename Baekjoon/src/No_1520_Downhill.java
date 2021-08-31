import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class No_1520_Downhill {

    private final static int[] DX = {0, 0, -1, 1};
    private final static int[] DY = {-1, 1, 0, 0};

    private static int[][] map;
    private static int[][] availablePathCounts;
    private static int row, column;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        row = Integer.valueOf(st.nextToken());
        column = Integer.valueOf(st.nextToken());

        map = new int[row][column];
        availablePathCounts = new int[row][column];

        for (int i = 0; i < row; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < column; j++) {
                map[i][j] = Integer.valueOf(st.nextToken());
                availablePathCounts[i][j] = Integer.MAX_VALUE;
            }
        }

        System.out.println(goDownhill(0, 0));
    }

    private static int goDownhill(int x, int y) {
        if (availablePathCounts[y][x] != Integer.MAX_VALUE) {
            return availablePathCounts[y][x];
        }

        if (x == column - 1 && y == row - 1) {
            return 1;
        }

        availablePathCounts[y][x] = 0;
        for (int k = 0; k < 4; k++) {
            int nx = x + DX[k];
            int ny = y + DY[k];

            if (0 <= nx && nx < column && 0 <= ny && ny < row && map[ny][nx] < map[y][x]) {
                availablePathCounts[y][x] += goDownhill(nx, ny);
            }
        }

        return availablePathCounts[y][x];
    }
}
