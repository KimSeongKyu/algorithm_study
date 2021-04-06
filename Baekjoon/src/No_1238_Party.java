import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class No_1238_Party {

    private static final int INFINITE = 100_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        int[][] distances = new int[N+1][N+1];
        for(int i = 1; i <= N; i++) Arrays.fill(distances[i], INFINITE);
        for(int i = 1; i <= N; i++) distances[i][i] = 0;

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());
            distances[from][to] = distance;
        }

        for(int k = 1; k <= N; k++) {
            for(int i = 1; i <= N; i++) {
                for(int j = 1; j <= N; j++) {
                    distances[i][j] = Math.min(distances[i][j], distances[i][k] + distances[k][j]);
                }
            }
        }

        int maxDistance = 0;
        for(int i = 1; i <= N; i++) {
            maxDistance = Math.max(maxDistance, distances[i][X]+distances[X][i]);
        }

        System.out.println(maxDistance);
        br.close();
    }
}
