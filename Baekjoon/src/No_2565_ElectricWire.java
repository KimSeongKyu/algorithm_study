import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class No_2565_ElectricWire {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] wire = new int[n+1][2];
        int[] wireCount = new int[n+1];

        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            wire[i][0] = Integer.parseInt(st.nextToken());
            wire[i][1] = Integer.parseInt(st.nextToken());
            wireCount[i] = 1;
        }

        Arrays.sort(wire, (o1, o2) -> o1[0]-o2[0]);

        int maxCount = 0;
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j < i; j++) {
                if(wire[j][1] < wire[i][1]) {
                    wireCount[i] = Math.max(wireCount[i], wireCount[j]+1);
                }
            }
            if(maxCount < wireCount[i]) maxCount = wireCount[i];
        }

        System.out.println(n-maxCount);
    }
}
