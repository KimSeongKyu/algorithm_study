import java.io.*;
import java.util.StringTokenizer;

public class No_16926_RotateArray1 {
    private static int[][] array;
    private static int last;
    private static int n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        array = new int[n][m];
        int rotateCount = Integer.parseInt(st.nextToken());

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                array[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while((n>1 && m>1) && (rotateCount-- > 0)) rotate(0, 0);


        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                sb.append(array[i][j] + " ");
            }
            sb.setLength(sb.length()-1);
            sb.append("\n");
        }

        bw.write(sb.toString());
        br.close();
        bw.flush();
        bw.close();
    }

    private static void rotate(int i, int j) {

        if(i >= n/2 || j >= m/2) return;

        last = array[i][j];

        for(int x = j; x < m-j-1; x++) array[i][x] = array[i][x+1];	// 좌
        for(int y = i; y < n-i-1; y++) array[y][m-j-1] = array[y+1][m-j-1];	// 상
        for(int x = m-j-1; x > j; x--) array[n-i-1][x] = array[n-i-1][x-1];	// 우
        for(int y = n-i-1; y > i; y--) array[y][j] = array[y-1][j];	// 하

        array[i+1][j] = last;

        rotate(i+1, j+1);
    }
}
