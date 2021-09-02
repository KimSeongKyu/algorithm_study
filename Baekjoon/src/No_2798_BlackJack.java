import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class No_2798_BlackJack {

    private final static int R = 3;
    private final static int[] combination = new int[3];

    private static int N;
    private static int maxSumOfCardNumbers;
    private static int[] cardNumbers;
    private static int sumOfCardNumbers = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.valueOf(st.nextToken());
        maxSumOfCardNumbers = Integer.valueOf(st.nextToken());

        st = new StringTokenizer(br.readLine());
        cardNumbers = new int[N];
        for (int i = 0; i < N; i++) {
            cardNumbers[i] = Integer.valueOf(st.nextToken());
        }

        findNearestSumToMaxSumOfCardNumbers(0, 0);
        System.out.println(sumOfCardNumbers);
    }

    private static void findNearestSumToMaxSumOfCardNumbers(int step, int start) {
        if (step == R) {
            int sum = Arrays.stream(combination)
                    .reduce(0, Integer::sum);
            if(sum <= maxSumOfCardNumbers) {
                if(sumOfCardNumbers == 0) {
                    sumOfCardNumbers = sum;
                } else if(sumOfCardNumbers < sum) {
                    sumOfCardNumbers = sum;
                }
            }
            return;
        }

        for (int i = start; i < N; i++) {
            combination[step] = cardNumbers[i];
            findNearestSumToMaxSumOfCardNumbers(step + 1, i + 1);
        }
    }
}
