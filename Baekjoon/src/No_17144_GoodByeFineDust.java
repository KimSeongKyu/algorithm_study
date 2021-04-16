import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class No_17144_GoodByeFineDust {
    static class FineDust {
        int row;
        int column;
        int amount;

        FineDust(int row, int column, int amount) {
            this.row = row;
            this.column = column;
            this.amount = amount;
        }
    }

    static class AirCleaner {
        int row;
        int column;

        AirCleaner(int row, int column) {
            this.row = row;
            this.column = column;
        }
    }

    private static Queue<FineDust> fineDustQueue;
    private static List<AirCleaner> airCleaners;
    private static int[][] room;
    private static int[] dr = {-1, 1, 0, 0};
    private static int[] dc = {0, 0, -1, 1};

    private static int r;
    private static int c;
    private static int time;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        time = Integer.parseInt(st.nextToken());

        fineDustQueue = new LinkedList<>();
        airCleaners = new ArrayList<>();
        room = new int[r][c];

        for(int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < c; j++) {
                room[i][j] = Integer.parseInt(st.nextToken());
                if(room[i][j] >= 5) fineDustQueue.offer(new FineDust(i, j, room[i][j]));
                else if(room[i][j] == -1) airCleaners.add(new AirCleaner(i, j));
            }
        }

        for(int t = 1; t <= time; t++) {
            spreadFineDust();
            airClean();
            if(t < time) checkFineDust();
        }

        int fineDustAmount = 2;
        for(int[] row : room) fineDustAmount += Arrays.stream(row).sum();

        System.out.println(fineDustAmount);
    }

    private static void airClean() {
        AirCleaner topAirCleaner = airCleaners.get(0);
        cleanDown(topAirCleaner.row-1, 0, 0);
        cleanLeft(0);
        cleanUp(0, topAirCleaner.row, c-1);
        cleanRight(topAirCleaner.row);

        AirCleaner bottomAirCleaner = airCleaners.get(1);
        cleanUp(bottomAirCleaner.row+1, r-1, 0);
        cleanLeft(r-1);
        cleanDown(r-1, bottomAirCleaner.row, c-1);
        cleanRight(bottomAirCleaner.row);
    }

    private static void cleanUp(int startRow, int endRow, int column) {
        for(int i = startRow; i < endRow; i++) {
            room[i][column] = room[i+1][column];
        }
    }

    private static void cleanDown(int startRow, int endRow, int column) {
        for(int i = startRow; i > endRow ; i--) {
            room[i][column] = room[i-1][column];
        }
    }

    private static void cleanLeft(int row) {
        for(int i = 0; i < c-1; i++) {
            room[row][i] = room[row][i+1];
        }
    }

    private static void cleanRight(int row) {
        for(int i = c-1; i > 1; i--) {
            room[row][i] = room[row][i-1];
        }
        room[row][1] = 0;
    }

    private static void spreadFineDust() {
        while(!fineDustQueue.isEmpty()) {
            FineDust fineDust = fineDustQueue.poll();
            int spreadAmount = fineDust.amount/5;

            for(int i = 0; i < 4; i++) {
                int nr = fineDust.row + dr[i];
                int nc = fineDust.column + dc[i];

                if(nr >= 0 && nr < r && nc >= 0 && nc < c && room[nr][nc] != -1) {
                    room[nr][nc] += spreadAmount;
                    room[fineDust.row][fineDust.column] -= spreadAmount;
                }
            }
        }
    }

    private static void checkFineDust() {
        for(int i = 0; i < r; i++) {
            for(int j = 0; j < c; j++) {
                if(room[i][j] >= 5) fineDustQueue.offer(new FineDust(i, j, room[i][j]));
            }
        }
    }
}
