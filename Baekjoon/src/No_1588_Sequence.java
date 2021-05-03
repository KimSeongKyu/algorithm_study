import java.util.Scanner;

public class No_1588_Sequence {
    private static long left, right;
    private static int n;

    private static long[] totalNumberCount = new long[4];
    private static long[][][] numberCountOfHeight;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int number = sc.nextInt();
        left = sc.nextInt();
        right = sc.nextInt();
        n = sc.nextInt();

        numberCountOfHeight = new long[4][n+1][4];
        for(int i = 1; i <= 3; i++) {
            setNumberCount(i);
        }
        makeSequence(number, 0, 0);

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= 3; i++) {
            sb.append(totalNumberCount[i] + " ");
        }
        System.out.println(sb);
    }

    private static void setNumberCount(int number) {
        numberCountOfHeight[number][0][number] = 1;
        for(int i = 1; i <= n; i++) {
            numberCountOfHeight[number][i][1] =
                    numberCountOfHeight[number][i-1][1] +
                    numberCountOfHeight[number][i-1][2]*2;
            numberCountOfHeight[number][i][2] =
                    numberCountOfHeight[number][i-1][1] +
                    numberCountOfHeight[number][i-1][2] +
                    numberCountOfHeight[number][i-1][3]*2;
            numberCountOfHeight[number][i][3] =
                    numberCountOfHeight[number][i-1][1] +
                    numberCountOfHeight[number][i-1][3];
        }
    }

    private static void makeSequence(int number, int height, long position) {
        if(height == n) {
            if(left <= position && position <= right) {
                ++totalNumberCount[number];
            }
            return;
        } 

        long range = (long)Math.pow(3, n-height);
        if(left <= position*range && position*range+range <= right) {
            for(int i = 1; i <= 3; i++) {
                totalNumberCount[i] += numberCountOfHeight[number][n-height][i];
            }
        }
        else if((position*range <= left && left <= position*range+range) ||
                (position*range <= right && right <= position*range+range)){
            switch(number) {
                case 1:
                    makeSequence(1, height+1, position*3);
                    makeSequence(3, height+1, position*3+1);
                    makeSequence(2, height+1, position*3+2);
                    break;
                case 2:
                    makeSequence(2, height+1, position*3);
                    makeSequence(1, height+1, position*3+1);
                    makeSequence(1, height+1, position*3+2);
                    break;
                case 3:
                    makeSequence(2, height+1, position*3);
                    makeSequence(3, height+1, position*3+1);
                    makeSequence(2, height+1, position*3+2);
                    break;
            }
        }
    }
}
