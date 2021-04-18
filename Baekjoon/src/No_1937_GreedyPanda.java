import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class No_1937_GreedyPanda {

    private static final int[] dx = {0, 0, -1, 1};
    private static final int[] dy = {-1, 1, 0, 0};

    private static boolean[][] isVisited;
    private static int[][] amountOfBambooInForest;
    private static int[][] maxDayToLive;
    private static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        isVisited = new boolean[n][n];
        amountOfBambooInForest = new int[n][n];
        maxDayToLive = new int[n][n];

        for(int i = 0 ; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                amountOfBambooInForest[i][j] = Integer.parseInt(st.nextToken());
                maxDayToLive[i][j] = 1;
            }
        }

        for(int y = 0; y < n; y++) {
            for(int x = 0; x < n; x++) {
                int amountOfBamboo = amountOfBambooInForest[y][x];
                isVisited[y][x] = true;

                for(int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];

                    if(nx >= 0 && nx < n && ny >= 0 && ny < n
                            && amountOfBambooInForest[ny][nx] > amountOfBamboo) {
                        if(!isVisited[ny][nx]) {
                            findAvailableRegion(ny, nx);
                        }
                        maxDayToLive[y][x] = Math.max(maxDayToLive[y][x], maxDayToLive[ny][nx] + 1);
                    }
                }
            }
        }

        int maxDay = 0;
        for(int[] row : maxDayToLive) {
            maxDay = Math.max(maxDay, Arrays.stream(row).max().getAsInt());
        }

        System.out.println(maxDay);
    }

    private static int findAvailableRegion(int y, int x) {
        int amountOfBamboo = amountOfBambooInForest[y][x];
        isVisited[y][x] = true;

        for(int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx >= 0 && nx < n && ny >= 0 && ny < n
                    && amountOfBambooInForest[ny][nx] > amountOfBamboo) {
                if(isVisited[ny][nx]) maxDayToLive[y][x] = Math.max(maxDayToLive[y][x], maxDayToLive[ny][nx] + 1);
                else maxDayToLive[y][x] = Math.max(maxDayToLive[y][x], findAvailableRegion(ny, nx) + 1);
            }
        }

        return maxDayToLive[y][x];
    }
}
