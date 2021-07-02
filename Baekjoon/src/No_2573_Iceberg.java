import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class No_2573_Iceberg {

    private final static int WATER = 0;
    private final static int[] DX = {0, 0, -1, 1};
    private final static int[] DY = {-1, 1, 0, 0};
    private static int[][] map;
    private static int row, column;
    private static Queue<Iceberg> icebergs = new LinkedList<>();

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
                if (map[i][j] != WATER) {
                    icebergs.offer(new Iceberg(j, i, map[i][j]));
                }
            }
        }

        int dividedYear = meltIceberg();
        System.out.println(dividedYear);
        br.close();
    }

    private static int meltIceberg() {
        int year = 0;
        Queue<Iceberg> meltedIcebergs = new LinkedList<>();

        while (!icebergs.isEmpty()) {
            int icebergCount = icebergs.size();

            for (int i = 0; i < icebergCount; i++) {
                Iceberg iceberg = icebergs.poll();

                boolean isDivided = true;
                int nx, ny;
                for (int j = 0; j < 4; j++) {
                    nx = iceberg.x + DX[j];
                    ny = iceberg.y + DY[j];

                    if (0 <= nx && nx < column && 0 <= ny && ny < row) {
                        if (map[ny][nx] == WATER) {
                            if (iceberg.height > 0) {
                                --iceberg.height;
                            }
                        } else {
                            isDivided = false;
                        }
                    }
                }

                if (isDivided) {
                    return year;
                }

                meltedIcebergs.offer(iceberg);
            }

            while (!meltedIcebergs.isEmpty()) {
                Iceberg iceberg = meltedIcebergs.poll();
                map[iceberg.y][iceberg.x] = iceberg.height;
                if (iceberg.height != WATER) {
                    icebergs.offer(iceberg);
                }
            }

            ++year;
        }

        return 0;
    }

    static class Iceberg {
        int x;
        int y;
        int height;

        Iceberg(int x, int y, int height) {
            this.x = x;
            this.y = y;
            this.height = height;
        }
    }
}
