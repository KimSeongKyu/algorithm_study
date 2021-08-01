import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class No_1261_AlgoSpot {

    static class BrokenWallCountAndPosition implements Comparable<BrokenWallCountAndPosition>{
        int x;
        int y;
        int brokenWallCount;

        BrokenWallCountAndPosition(int x, int y, int brokenWallCount) {
            this.x = x;
            this.y = y;
            this.brokenWallCount = brokenWallCount;
        }

        @Override
        public int compareTo(BrokenWallCountAndPosition other) {
            return brokenWallCount - other.brokenWallCount;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] dx = new int[]{0, 0, -1, 1};
        int[] dy = new int[]{-1, 1, 0, 0};

        int column = Integer.valueOf(st.nextToken());
        int row = Integer.valueOf(st.nextToken());
        int[][] map = new int[row][column];

        for (int i = 0; i < row; i++) {
            char[] status = br.readLine().toCharArray();
            for (int j = 0; j < column; j++) {
                map[i][j] = status[j] - '0';
            }
        }

        PriorityQueue<BrokenWallCountAndPosition> minimumBrokenWallCountAndPositions =
                new PriorityQueue<>(Comparator.comparingInt(brokenWallCountAndPosition -> brokenWallCountAndPosition.brokenWallCount));
        boolean[][] isVisited = new boolean[row][column];

        minimumBrokenWallCountAndPositions.offer(new BrokenWallCountAndPosition(0, 0, 0));
        isVisited[0][0] = true;

        while (!minimumBrokenWallCountAndPositions.isEmpty()) {
            BrokenWallCountAndPosition brokenWallCountAndPosition = minimumBrokenWallCountAndPositions.poll();
            int x = brokenWallCountAndPosition.x;
            int y = brokenWallCountAndPosition.y;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (0 <= nx && nx < column && 0 <= ny && ny < row && !isVisited[ny][nx]) {
                    map[ny][nx] += brokenWallCountAndPosition.brokenWallCount;

                    minimumBrokenWallCountAndPositions.offer(new BrokenWallCountAndPosition(nx, ny, map[ny][nx]));
                    isVisited[ny][nx] = true;
                }
            }
        }

        System.out.println(map[row - 1][column - 1]);
    }
}
