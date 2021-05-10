import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class No_9084_Coin {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCases = Integer.parseInt(br.readLine());
        for (int no = 1; no <= testCases; no++) {
            int numOfCoinKind = Integer.parseInt(br.readLine());
            int[] coins = new int[numOfCoinKind];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i = 0; i < numOfCoinKind; i++) {
                coins[i] = Integer.parseInt(st.nextToken());
            }

            int targetPrice = Integer.parseInt(br.readLine());
            int[] numOfCases = new int[targetPrice+1];
            numOfCases[0] = 1;
            for(int coin : coins) {
                for(int price = coin; price <= targetPrice; price++) {
                    numOfCases[price] += numOfCases[price - coin];
                }
            }

            System.out.println(numOfCases[targetPrice]);
        }
    }


}
