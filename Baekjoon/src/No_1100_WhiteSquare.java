import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class No_1100_WhiteSquare {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[][] map = new char[8][8];
        int horseCountOnWhiteSquare = 0;

        for (int i = 0; i < 8; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < 8; j++) {
                if (map[i][j] == 'F' & (i + j) % 2 == 0) {
                    ++horseCountOnWhiteSquare;
                }
            }
        }

        System.out.println(horseCountOnWhiteSquare);

        br.close();
    }
}
