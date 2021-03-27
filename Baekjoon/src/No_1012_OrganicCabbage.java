import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class No_1012_OrganicCabbage {

    static class Cabbage {
        int x, y;
        Cabbage(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCases = Integer.parseInt(br.readLine());
        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};

        for(int no = 1; no <= testCases; no++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            int[][] farm = new int[N][M];
            boolean[][] isPlanted = new boolean[N][M];
            Cabbage[] cabbages = new Cabbage[K];
            Queue<Cabbage> adjCabbages = new LinkedList<>();

            for(int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                farm[y][x] = 1;
                cabbages[i] = new Cabbage(x, y);
            }

            int wormCount = 0;

            for(int i = 0; i < K; i++) {
                Cabbage cabbage = cabbages[i];
                if (isPlanted[cabbage.y][cabbage.x]) continue;

                adjCabbages.offer(cabbage);
                isPlanted[cabbage.y][cabbage.x] = true;

                while (!adjCabbages.isEmpty()) {
                    cabbage = adjCabbages.poll();

                    int x = cabbage.x;
                    int y = cabbage.y;
                    isPlanted[y][x] = true;

                    for (int j = 0; j < 4; j++) {
                        int nx = x + dx[j];
                        int ny = y + dy[j];

                        if (nx >= 0 && nx < M && ny >= 0 && ny < N &&
                                !isPlanted[ny][nx] && farm[ny][nx] == 1) {
                            adjCabbages.offer(new Cabbage(nx, ny));
                            isPlanted[ny][nx] = true;
                        }
                    }
                }

                ++wormCount;
            }

            System.out.println(wormCount);
        }
    }
}
