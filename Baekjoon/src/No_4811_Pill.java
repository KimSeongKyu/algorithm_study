import java.io.*;
import java.util.Arrays;

public class No_4811_Pill {
    private static long[][] stringCount = new long[31][31];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        stringCount[0][0] = 1;
        select(30, 0);

        int n;
        while((n = Integer.parseInt(br.readLine())) != 0) {
            bw.write(String.valueOf(stringCount[n][0]));
            bw.newLine();
        }

        br.close();
        bw.flush();
        bw.close();
    }

    private static long select(int W, int H) {
        if(W < 0 || H < 0) return 0;

        if(stringCount[W][H] != 0) return stringCount[W][H];

        return stringCount[W][H] = select(W-1, H+1) + select(W, H-1);
    }
}
