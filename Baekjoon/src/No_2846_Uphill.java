import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class No_2846_Uphill {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] road = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            road[i] = Integer.parseInt(st.nextToken());
        }

        int uphill = 0;
        int maxUphill = 0;
        for(int i = 0; i < n-1; i++) {
            if(road[i+1] > road[i]) {
                uphill += road[i+1]-road[i];
            } else {
                uphill = 0;
            }
            maxUphill = Math.max(uphill, maxUphill);
        }

        System.out.println(maxUphill);
    }
}
