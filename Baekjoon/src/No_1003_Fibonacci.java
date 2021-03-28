import java.io.*;

public class No_1003_Fibonacci {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[][] fibonacci = new int[41][2];
        fibonacci[0][0] = 1;
        fibonacci[0][1] = 0;
        fibonacci[1][0] = 0;
        fibonacci[1][1] = 1;
        for(int i = 2; i <= 40; i++) {
            fibonacci[i][0] = fibonacci[i-1][0] + fibonacci[i-2][0];
            fibonacci[i][1] = fibonacci[i-1][1] + fibonacci[i-2][1];
        }

        int testCases = Integer.parseInt(br.readLine());
        for(int no = 1; no <= testCases; no++) {
            int n = Integer.parseInt(br.readLine());
            bw.write(fibonacci[n][0] + " " + fibonacci[n][1]);
            bw.newLine();
        }
        
        br.close();
        bw.flush();
        bw.close();
    }
}
