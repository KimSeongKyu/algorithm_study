import java.io.*;
import java.util.*;

public class No_2667_NumberingEstate {

    private static final int[] dx = {0, 0, -1, 1};
    private static final int[] dy = {-1, 1, 0, 0};

    private static Queue<int[]> houseQueue;
    private static boolean[][] isVisited;
    private static int[][] map;
    private static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());

        map = new int[n][n];
        isVisited = new boolean[n][n];

        for(int i = 0; i < n; i++) {
            String row = br.readLine();
            for(int j = 0; j < n; j++) {
                map[i][j] = row.charAt(j)-48;
            }
        }

        List<Integer> houseCountList = new ArrayList<>();
        houseQueue = new LinkedList<>();
        while(findHouse()) {
            int houseCount = 1;

            while(!houseQueue.isEmpty()) {
                int[] house = houseQueue.poll();

                for(int i = 0; i < 4; i++) {
                    int nx = house[1] + dx[i];
                    int ny = house[0] + dy[i];

                    if(nx >= 0 && nx < n && ny >= 0 && ny < n &&
                            !isVisited[ny][nx] && map[ny][nx] == 1) {
                        houseQueue.offer(new int[]{ny, nx});
                        isVisited[ny][nx] = true;
                        ++houseCount;
                    }
                }
            }

            houseCountList.add(houseCount);
        }
        Collections.sort(houseCountList);

        bw.write(String.valueOf(houseCountList.size()));
        bw.newLine();
        for(int houseCount : houseCountList) {
            bw.write(String.valueOf(houseCount));
            bw.newLine();
        }

        br.close();
        bw.flush();
        bw.close();
    }

    private static boolean findHouse() {
        for(int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 1 && !isVisited[i][j]) {
                    houseQueue.offer(new int[]{i, j});
                    isVisited[i][j] = true;
                    return true;
                }
            }
        }
        return false;
    }
}
