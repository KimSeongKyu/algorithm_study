import java.io.*;
import java.util.StringTokenizer;

public class No_16918_BomberMan {

    private final static int[] DX = {0, 0, -1, 1};
    private final static int[] DY = {-1, 1, 0, 0};

    private static int[][][] map;
    private static int row;
    private static int column;
    private static int time;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(input.readLine());

        row = Integer.valueOf(st.nextToken());
        column = Integer.valueOf(st.nextToken());
        time = Integer.valueOf(st.nextToken());


        map = new int[row][column][1];
        for(int i = 0; i < row; i++) {
            char[] line = input.readLine().toCharArray();
            for(int j = 0; j < column; j++) {
                if(line[j] == 'O') {
                    map[i][j][0] = 1;
                } else {
                    map[i][j][0] = -1;
                }
            }
        }

        for(int currentTime = 2; currentTime <= time; currentTime++) {
            timeGoes();
            if(currentTime % 2 == 1) {
                bomb();
            }
        }

        for(int i = 0; i < row; i++) {
            for(int j = 0; j < column; j++) {
                if(map[i][j][0] == -1) {
                    output.write(".");
                } else {
                    output.write("O");
                }
            }
            output.newLine();
        }

        input.close();
        output.flush();
        output.close();
    }

    private static void timeGoes() {
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < column; j++) {
                ++map[i][j][0];
            }
        }
    }

    private static void bomb() {
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < column; j++) {
                if(map[i][j][0] == 3) {
                    map[i][j][0] = -1;
                    for(int k = 0; k < 4; k++) {
                        int nx = j + DX[k];
                        int ny = i + DY[k];
                        if(0 <= nx && nx < column && 0 <= ny && ny < row && map[ny][nx][0] != 3) {
                            map[ny][nx][0] = -1;
                        }
                    }
                }
            }
        }
    }
}
