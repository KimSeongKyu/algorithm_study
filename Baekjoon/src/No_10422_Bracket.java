import java.io.*;

public class No_10422_Bracket {

    private final static int MAX_LENGTH = 2501;
    private final static long MOD = 1_000_000_007L;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        long[] numberOfValidBrackets = new long[MAX_LENGTH];
        numberOfValidBrackets[0] = 1L;
        numberOfValidBrackets[1] = 1L;

        for (int i = 2; i < MAX_LENGTH; i++) {
            for (int j = 0; j < i; j++) {
                numberOfValidBrackets[i] += numberOfValidBrackets[i-1-j] * numberOfValidBrackets[j];
                numberOfValidBrackets[i] %= MOD;
            }
        }

        int testCases = Integer.valueOf(br.readLine());

        for (int no = 1; no <= testCases; no++) {
            int bracketLength = Integer.valueOf(br.readLine());

            if (bracketLength % 2 == 1) {
                bw.write(String.valueOf(0));
                bw.newLine();
                continue;
            }

            bw.write(String.valueOf(numberOfValidBrackets[bracketLength / 2]));
            bw.newLine();
        }

        br.close();
        bw.flush();
        bw.close();
    }
}
