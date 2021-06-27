import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class No_10974_EveryPermutations {

    private static int n;
    private static int[] numbers;
    private static boolean[] isVisited;

    private static BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder permutation = new StringBuilder();

    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);

        n = input.nextInt();
        numbers = new int[n];
        isVisited = new boolean[n];

        makePermutations(0);

        output.flush();
        output.close();
    }

    private static void makePermutations(int step) throws IOException {
        if(step == n) {
            permutation.setLength(0);
            for(int i = 0; i < n; i++) {
                permutation.append(numbers[i] + " ");
            }
            permutation.setLength(permutation.length()-1);
            output.write(permutation.toString());
            output.newLine();
            return;
        }

        for(int i = 0; i < n; i++) {
            if(isVisited[i]) {
                continue;
            }

            numbers[step] = i+1;
            isVisited[i] = true;
            makePermutations(step+1);
            isVisited[i] = false;
        }
    }
}
