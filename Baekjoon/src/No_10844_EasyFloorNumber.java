import java.util.Scanner;

public class No_10844_EasyFloorNumber {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        long[][] floorNumbers = new long[n+1][10];

        for(int i = 1; i <= 9; i++) {
            floorNumbers[1][i] = 1;
        }

        for(int i = 2; i <= n; i++) {
            for(int j = 0; j <= 9; j++) {
                if(j-1 >= 0) floorNumbers[i][j] += floorNumbers[i-1][j-1];
                if(j+1 <= 9) floorNumbers[i][j] += floorNumbers[i-1][j+1];
                floorNumbers[i][j] %= 1_000_000_000;
            }
        }

        long floorCount = 0;
        for(int i = 0; i <= 9; i++) {
            floorCount += floorNumbers[n][i];
        }

        System.out.println(floorCount % 1_000_000_000);
    }
}
