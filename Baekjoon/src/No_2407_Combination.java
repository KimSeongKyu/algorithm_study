import java.math.BigDecimal;
import java.util.Scanner;

public class No_2407_Combination {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        BigDecimal[][] combination = new BigDecimal[n + 1][m + 1];

        for (int i = 0; i <= n; i++) {
            combination[i][0] = new BigDecimal(1);
        }

        for (int j = 1; j <= m; j++) {
            combination[0][j] = new BigDecimal(0);
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                combination[i][j] = new BigDecimal(0);
                if (j <= i) {
                    combination[i][j] = combination[i - 1][j - 1].add(combination[i - 1][j]);
                }
            }
        }

        System.out.println(combination[n][m]);
    }
}
