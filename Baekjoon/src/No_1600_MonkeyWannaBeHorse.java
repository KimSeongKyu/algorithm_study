import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class No_1600_MonkeyWannaBeHorse {

    static class Node {
        int x, y, moveCount, jumpCount;

        public Node(int x, int y, int moveCount, int jumpCount) {
            this.x = x;
            this.y = y;
            this.moveCount = moveCount;
            this.jumpCount = jumpCount;
        }
    }

    private static int H, W, K;
    private static int[][] map;
    private static boolean[][][] isVisited;

    private static int[] dx = {0, 0, -1, 1};
    private static int[] dy = {-1, 1, 0, 0};
    private static int[] horseDx = {-2, -1, 1, 2, -2, -1, 1, 2};
    private static int[] horseDy = {-1, -2, -2, -1, 1, 2, 2, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new int[H][W];
        isVisited = new boolean[K+1][H][W];

        for(int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < W; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bfs();
        br.close();
    }

    private static void bfs() {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(0, 0, 0, 0));

        while(!queue.isEmpty()) {
            Node node = queue.poll();

            // 목적지에 도달했을 때
            if(node.x == W-1 && node.y == H-1) {
                System.out.println(node.moveCount);
                return;
            }

            // 원숭이처럼 움직이기
            for(int i = 0; i < 4; i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];

                if(nx >= 0 && nx < W && ny >= 0 && ny < H) {
                    if(!isVisited[node.jumpCount][ny][nx] && map[ny][nx] == 0) {
                        isVisited[node.jumpCount][ny][nx] = true;
                        queue.offer(new Node(nx, ny, node.moveCount+1, node.jumpCount));
                    }
                }
            }

            // 말처럼 움직일 수 있는 칸을 원숭이처럼 움직여서 도달할 수 있으면
            // 원숭이처럼 움직이는게 유리하기 때문에
            // 원숭이처럼 움직인 후 말처럼 움직이는 경우 계산

            // 말처럼 움직일 수 있는 경우
            if(node.jumpCount < K) {
                // 말처럼 움직이기
                for(int i = 0; i < 8; i++) {
                    int nx = node.x + horseDx[i];
                    int ny = node.y + horseDy[i];

                    if(nx >= 0 && nx < W && ny >= 0 && ny < H) {
                        if(!isVisited[node.jumpCount+1][ny][nx] && map[ny][nx] == 0) {
                            isVisited[node.jumpCount+1][ny][nx] = true;
                            queue.offer(new Node(nx, ny, node.moveCount+1, node.jumpCount+1));
                        }
                    }
                }
            }
        }

        // 목적지에 도달하지 못했을 때
        System.out.println(-1);
    }
}
