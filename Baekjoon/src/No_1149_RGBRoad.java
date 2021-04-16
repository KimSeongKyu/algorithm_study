import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class No_1149_RGBRoad {
    private static final int R = 0;
    private static final int G = 1;
    private static final int B = 2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] cost = new int[n][3];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < 3; i++) cost[0][i] = Integer.parseInt(st.nextToken());

        int red, green, blue;
        for(int i = 1; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            red = Integer.parseInt(st.nextToken());
            green = Integer.parseInt(st.nextToken());
            blue = Integer.parseInt(st.nextToken());

            cost[i][R] = red + Math.min(cost[i-1][G], cost[i-1][B]);
            cost[i][G] = green + Math.min(cost[i-1][R], cost[i-1][B]);
            cost[i][B] = blue + Math.min(cost[i-1][R], cost[i-1][G]);
        }

        System.out.println(Arrays.stream(cost[n-1]).min().getAsInt());
    }
}
