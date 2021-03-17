import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class No_4779_CantorSet {
    private static char[] dashes;
    private static int n;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while(sc.hasNextInt()) {
            n = sc.nextInt();
            dashes = new char[(int)Math.pow(3, n)];
            for(int i = 0; i < Math.pow(3, n); i++) dashes[i]='-';

            int midStart = (int)Math.pow(3, n-1);
            divide(n, midStart);
            bw.write(String.valueOf(dashes));
            bw.newLine();
        }

        sc.close();
        bw.flush();
        bw.close();
    }

    private static void divide(int step, int mid) {
        if(step == 0) return;

        int range = (int)Math.pow(3, step-1);
        int nextRange = (int)Math.pow(3, step-2);
        int left = mid-range;
        int right = mid+range;

        for(int i = mid; i < mid+range; i++) dashes[i] = ' ';
        divide(step-1, left+nextRange);
        divide(step-1, right+nextRange);
    }
}
