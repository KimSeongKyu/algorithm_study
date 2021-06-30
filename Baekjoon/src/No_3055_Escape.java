import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class No_3055_Escape {

    private final static int[] DX = {0, 0, -1, 1};
    private final static int[] DY = {-1, 1, 0, 0};

    private final static char EMPTY = '.';
    private final static char WATER = '*';
    private final static char HEDGEHOG = 'S';
    private final static char END = 'D';

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int row = Integer.parseInt(st.nextToken());
        int column = Integer.parseInt(st.nextToken());

        char[][] map = new char[row][column];
        boolean[][] isVisited = new boolean[row][column];

        Queue<Position> positions = new LinkedList<>();

        int[] hedgehogPosition = new int[2];
        for (int i = 0; i < row; i++) {
            String rowMap = br.readLine();
            for (int j = 0; j < column; j++) {
                char state = rowMap.charAt(j);
                if (state == WATER) {
                    positions.offer(new Position(state, j, i));
                    isVisited[i][j] = true;
                } else if (state == HEDGEHOG) {
                    hedgehogPosition = new int[]{j, i};
                }
                map[i][j] = state;
            }
        }

        positions.offer(new Position(HEDGEHOG, hedgehogPosition[0], hedgehogPosition[1]));

        int time = 0;
        while (!positions.isEmpty()) {
            int stepSize = positions.size();

            for (int i = 0; i < stepSize; i++) {
                Position position = positions.poll();

                for (int j = 0; j < 4; j++) {
                    int nx = position.x + DX[j];
                    int ny = position.y + DY[j];

                    if (0 <= nx && nx < column && 0 <= ny && ny < row) {
                        if (!isVisited[ny][nx] && map[ny][nx] == EMPTY) {
                            isVisited[ny][nx] = true;
                            map[ny][nx] = position.state;
                            positions.offer(new Position(position.state, nx, ny));
                        } else if (position.state == HEDGEHOG && map[ny][nx] == END) {
                            br.close();
                            System.out.println(++time);
                            return;
                        }
                    }
                }
            }

            ++time;
        }

        System.out.println("KAKTUS");
        br.close();
    }

    static class Position {
        char state;
        int x;
        int y;

        Position(char state, int x, int y) {
            this.state = state;
            this.x = x;
            this.y = y;
        }
    }
}
