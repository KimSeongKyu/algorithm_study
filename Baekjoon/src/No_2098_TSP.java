import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class No_2098_TSP {
    private static int n;
    private static int cost[][];
    private static int map[][];
    private static int INFINITE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        map = new int[n][n];
        cost = new int[n][(1 << n)-1];
        INFINITE = n * 1_000_000;

        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
            Arrays.fill(cost[i], INFINITE);
        }

        int minCost = tsp(0, 1);
        System.out.println(minCost);
        br.close();
    }

    private static int tsp(int current, int visited) {
        if(visited == (1 << n) - 1) {
            if(map[current][0] == 0) return INFINITE;
            return map[current][0];
        }

        if(cost[current][visited] != INFINITE) return cost[current][visited];

        for(int i = 0; i < n; i++) {
            if(map[current][i] == 0 || (visited & (1 << i)) != 0) continue;

            int next = visited | (1 << i);
            cost[current][visited] = Math.min(cost[current][visited], tsp(i, next)+map[current][i]);
        }

        return cost[current][visited];
    }
}
