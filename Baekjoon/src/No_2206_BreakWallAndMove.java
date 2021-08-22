import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class No_2206_BreakWallAndMove {

    private static int N, M;
    private static int[][] map;
    private static int[][] breakWallCount;
    private static int[] dx = {0, 0, -1, 1};
    private static int[] dy = {-1, 1, 0, 0};

    static class Node {
        int x, y, distance;
        int breakWallCount;

        public Node(int x, int y, int distance, int breakWallCount) {
            this.x = x;
            this.y = y;
            this.distance = distance;
            this.breakWallCount = breakWallCount;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        breakWallCount = new int[N][M];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(String.valueOf(str.charAt(j)));
                breakWallCount[i][j] = Integer.MAX_VALUE;
            }
        }

        int minDistance = bfs(0, 0);
        System.out.println(minDistance);
        br.close();
    }

    static int bfs(int x, int y) {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(x, y, 1, 0));
        breakWallCount[y][x] = 0;

        while (!q.isEmpty()) {
            Node node = q.poll();

            if (node.x == M - 1 && node.y == N - 1)
                return node.distance;
            else {
                for (int i = 0; i < 4; i++) {
                    int nx = node.x + dx[i];
                    int ny = node.y + dy[i];

                    if (nx >= 0 && nx < M && ny >= 0 && ny < N) {
                        if (breakWallCount[ny][nx] > node.breakWallCount) {
                            if (map[ny][nx] == 0) {
                                q.add(new Node(nx, ny, node.distance + 1, node.breakWallCount));
                                breakWallCount[ny][nx] = node.breakWallCount;
                            } else {
                                if (node.breakWallCount == 0) {
                                    q.add(new Node(nx, ny, node.distance + 1, node.breakWallCount + 1));
                                    breakWallCount[ny][nx] = node.breakWallCount + 1;
                                }
                            }
                        }
                    }
                }
            }
        }

        return -1;
    }
}