import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class No_1080_Matrix {
    private static char[][] beforeMatrix;
    private static char[][] afterMatrix;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        beforeMatrix = new char[n][m];
        afterMatrix = new char[n][m];

        for(int i = 0; i < n; i++) beforeMatrix[i] = br.readLine().toCharArray();
        for(int i = 0; i < n; i++) afterMatrix[i] = br.readLine().toCharArray();

        int reverseCount = 0;
        for(int i = 0; i < n-2; i++) {
            for(int j = 0; j < m-2; j++) {
                if(beforeMatrix[i][j] != afterMatrix[i][j]) {
                    reverse(i, j);
                    ++reverseCount;
                }
            }
        }

        Loop: for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(beforeMatrix[i][j] != afterMatrix[i][j]) {
                    reverseCount = -1;
                    break Loop;
                }
            }
        }

        System.out.println(reverseCount);
    }


    private static void reverse(int i, int j) {
        for(int y = i; y < i+3; y++) {
            for(int x = j; x < j+3; x++) {
                beforeMatrix[y][x] = (beforeMatrix[y][x] == '0') ? '1' : '0';
            }
        }
    }
}
