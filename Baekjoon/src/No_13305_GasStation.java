import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class No_13305_GasStation {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        long[] distances = new long[n-1];
        long[] prices = new long[n-1];
        long totalPrice = 0L;

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n-1; i++) {
            distances[i] = Long.parseLong(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n-1; i++) {
            prices[i] = Long.parseLong(st.nextToken());
        }

        long minPrice = prices[0];
        for(int i = 0; i < n-1; i++) {
            if(prices[i] < minPrice) minPrice = prices[i];
            totalPrice += (minPrice*distances[i]);
        }

        System.out.println(totalPrice);
        br.close();
    }
}
