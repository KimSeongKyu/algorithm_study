import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class No_5427_Fire {
    private final static char EMPTY = '.';
    private final static char WALL = '#';
    private final static char START_POSITION = '@';
    private final static char FIRE = '*';

    static class Position {
        int x;
        int y;

        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};

        int testCases = Integer.valueOf(input.readLine());
        TEST_CASE: for(int no = 1; no <= testCases; no++) {
            StringTokenizer st = new StringTokenizer(input.readLine());
            int width = Integer.valueOf(st.nextToken());
            int height = Integer.valueOf(st.nextToken());

            char[][] building = new char[height][width];
            boolean[][] isVisited = new boolean[height][width];
            Queue<Position> sangeunPositions = new LinkedList<>();
            Queue<Position> fires = new LinkedList<>();

            for(int i = 0; i < height; i++) {
                building[i] = input.readLine().toCharArray();
                for(int j = 0; j < width; j++) {
                    if(building[i][j] == START_POSITION) {
                        isVisited[i][j] = true;
                        sangeunPositions.offer(new Position(j, i));
                    } else if(building[i][j] == FIRE) {
                        isVisited[i][j] = true;
                        fires.offer(new Position(j, i));
                    }
                }
            }

            int shortestTime = 1;
            while(!sangeunPositions.isEmpty()) {
                int availableFireMovement = fires.size();
                int availableSangeunMovement = sangeunPositions.size();

                for(int i = 0; i < availableFireMovement; i++) {
                    Position fire = fires.poll();

                    for(int j = 0; j < 4; j++) {
                        int nx = fire.x + dx[j];
                        int ny = fire.y + dy[j];

                        if(0 <= nx && nx < width && 0 <= ny && ny < height && !isVisited[ny][nx] && building[ny][nx] != WALL) {
                            isVisited[ny][nx] = true;
                            fires.offer(new Position(nx, ny));
                        }
                    }
                }

                for(int i = 0; i < availableSangeunMovement; i++) {
                    Position sangeunPosition = sangeunPositions.poll();
                    int x = sangeunPosition.x;
                    int y = sangeunPosition.y;

                    if(x == 0 || x == width - 1 || y == 0 || y == height - 1) {
                        System.out.println(shortestTime);
                        continue TEST_CASE;
                    }

                    for(int j = 0; j < 4; j++) {
                        int nx = x + dx[j];
                        int ny = y + dy[j];

                        if(0 <= nx && nx < width && 0 <= ny && ny < height && !isVisited[ny][nx] && building[ny][nx] == EMPTY) {
                            isVisited[ny][nx] = true;
                            sangeunPositions.offer(new Position(nx, ny));
                        }
                    }
                }

                ++shortestTime;
            }

            System.out.println("IMPOSSIBLE");
        }
    }
}
