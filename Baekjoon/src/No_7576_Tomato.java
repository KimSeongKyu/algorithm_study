import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class No_7576_Tomato {
    private static final int RIPED = 1;
    private static final int UNRIPED = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] dc = {-1, 1, 0, 0};
        int[] dr = {0, 0, -1, 1};

        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        int[][] tomato = new int[n][m];
        Queue<int[]> ripedTomatoes = new LinkedList<>();

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                tomato[i][j] = Integer.parseInt(st.nextToken());
                if(tomato[i][j] == RIPED) ripedTomatoes.offer(new int[] {i, j, 0});
            }
        }

        int day = 0;
        while(!ripedTomatoes.isEmpty()) {
            int[] ripedTomato = ripedTomatoes.poll();
            int today = ripedTomato[2];

            for(int i = 0; i < 4; i++) {
                int nr = ripedTomato[0]+dr[i];
                int nc = ripedTomato[1]+dc[i];

                if(nr >= 0 && nr < n && nc >= 0 && nc < m) {
                    if(tomato[nr][nc] == 0) {
                        tomato[nr][nc] = 1;
                        ripedTomatoes.offer(new int[] {nr, nc, today+1});
                    }
                }
            }

            day = today;
        }

        checkAvailable: for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(tomato[i][j] == UNRIPED) {
                    day = -1;
                    break checkAvailable;
                }
            }
        }

        System.out.println(day);
    }
}