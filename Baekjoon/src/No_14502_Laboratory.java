import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class No_14502_Laboratory {
    private static final int[] dx = {0, 0, -1, 1};
    private static final int[] dy = {-1, 1, 0, 0};

    private static Queue<Virus> viruses;
    private static int[][] map;
    private static int n, m;
    private static int maxSafeArea = Integer.MIN_VALUE;

    static class Virus {
        int x, y;
        public Virus(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];

        viruses = new LinkedList<>();
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }

        }

        simulate(0, 0);

        System.out.println(maxSafeArea);
    }

    private static void simulate(int step, int start) {
        if(step == 3) {
            spread();
            return;
        }

        for(int i = start; i < n*m; i++) {
            int y = i / m;
            int x = i % m;

            if(map[y][x] == 0) {
                map[y][x] = 1;
                simulate(step+1, i+1);
                map[y][x] = 0;
            }
        }
    }

    private static void spread() {
        boolean[][] isSpread = new boolean[n][m];
        int[][] tempMap = new int[n][];
        for(int i = 0; i < n; i++) tempMap[i] = Arrays.copyOf(map[i], m);

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(map[i][j] == 2) viruses.offer(new Virus(j ,i));
            }
        }

        while(!viruses.isEmpty()) {
            Virus virus = viruses.poll();
            int x = virus.x;
            int y = virus.y;

            for(int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx >= 0 && nx < m && ny >= 0 && ny < n &&
                        !isSpread[ny][nx] && tempMap[ny][nx] == 0) {
                    tempMap[ny][nx] = 2;
                    isSpread[ny][nx] = true;
                    viruses.offer(new Virus(nx, ny));
                }
            }
        }

        maxSafeArea = Math.max(maxSafeArea, checkSafeArea(tempMap));
    }

    private static int checkSafeArea(int[][] tempMap) {
        int safeAreaCount = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(tempMap[i][j] == 0) ++safeAreaCount;
            }
        }
        return safeAreaCount;
    }
}
