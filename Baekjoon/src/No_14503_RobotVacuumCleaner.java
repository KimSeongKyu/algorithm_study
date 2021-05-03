import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class No_14503_RobotVacuumCleaner {

    private static final int CLEANED = -1;
    private static final int NOTCLEANED = 0;
    private static final int WALL = 1;

    private static final int[] dx = {0, 1, 0, -1};
    private static final int[] dy = {-1, 0, 1, 0};

    static class Robot {
        int x, y;
        int direction;

        public Robot(int x, int y, int direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
        }
    }

    private static int[][] map;
    private static int cleanCount = 0;
    private static Robot robot;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        map = new int[n][m];

        st = new StringTokenizer(br.readLine());
        int robotY = Integer.parseInt(st.nextToken());
        int robotX = Integer.parseInt(st.nextToken());
        int robotDirection = Integer.parseInt(st.nextToken());
        robot = new Robot(robotX, robotY, robotDirection);

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while(true) {
            clean(robot.x, robot.y);
            if(!isAvailableToClean()) {
                break;
            }
        }
        System.out.println(cleanCount);
    }

    private static boolean isAvailableToClean() {
        while(true) {
            int direction = robot.direction;
            for (int i = 0; i < 4; i++) {
                direction = turnLeft(direction);
                if (isNotCleaned(direction)) {
                    move(direction);
                    robot.direction = direction;
                    return true;
                }
            }
            direction = turnBack(direction);
            if (isAvailableToMove(direction)) {
                move(direction);
            } else {
                return false;
            }
        }
    }

    private static boolean isAvailableToMove(int direction) {
        if(map[robot.y+dy[direction]][robot.x+dx[direction]] != WALL) {
            return true;
        }
        return false;
    }

    private static void move(int direction) {
        robot.x += dx[direction];
        robot.y += dy[direction];
    }

    private static int turnLeft(int direction) {
        return direction-1 >= 0 ? direction-1 : 3;
    }

    private static int turnBack(int direction) {
        return direction-2 >= 0 ? direction-2 : 4-Math.abs(direction-2);
    }

    private static boolean isNotCleaned(int direction) {
        if(map[robot.y+dy[direction]][robot.x+dx[direction]] == NOTCLEANED) {
            return true;
        }
        return false;
    }

    private static void clean(int x, int y) {
        if(map[y][x] == NOTCLEANED) {
            map[y][x] = CLEANED;
            ++cleanCount;
        }
    }
}
