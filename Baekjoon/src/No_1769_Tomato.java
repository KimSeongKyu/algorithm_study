import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class No_1769_Tomato {

    private final static int RIPED_TOMATO = 1;
    private final static int NON_RIPED_TOMATO = 0;
    private final static int EMPTY = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(input.readLine());

        int[] dx = {0, 0, -1, 1, 0, 0};
        int[] dy = {-1, 1, 0, 0, 0, 0};
        int[] dz = {0, 0, 0, 0, -1, 1};

        int horizontal = Integer.valueOf(st.nextToken());
        int vertical = Integer.valueOf(st.nextToken());
        int height = Integer.valueOf(st.nextToken());

        int[][][] box = new int[vertical][horizontal][height];
        boolean[][][] isVisited = new boolean[vertical][horizontal][height];
        boolean isAllRiped = true;

        Queue<Tomato> tomatoes = new LinkedList<>();
        for (int z = 0; z < height; z++) {
            for (int y = 0; y < vertical; y++) {
                st = new StringTokenizer(input.readLine());
                for (int x = 0; x < horizontal; x++) {
                    int status = Integer.valueOf(st.nextToken());
                    box[y][x][z] = status;

                    if (status == NON_RIPED_TOMATO) {
                        isAllRiped = false;
                    } else if (status == RIPED_TOMATO) {
                        tomatoes.offer(new Tomato(x, y, z));
                        isVisited[y][x][z] = true;
                    }
                }
            }
        }

        if (isAllRiped) {
            System.out.println(0);
            return;
        }

        int totalDay = -1;
        while (!tomatoes.isEmpty()) {
            int spreadCount = tomatoes.size();

            for (int i = 0; i < spreadCount; i++) {
                Tomato tomato = tomatoes.poll();

                for (int j = 0; j < 6; j++) {
                    int nx = tomato.x + dx[j];
                    int ny = tomato.y + dy[j];
                    int nz = tomato.z + dz[j];

                    if (0 <= nx && nx < horizontal && 0 <= ny && ny < vertical && 0 <= nz && nz < height &&
                            !isVisited[ny][nx][nz] && box[ny][nx][nz] == NON_RIPED_TOMATO) {
                        isVisited[ny][nx][nz] = true;
                        tomatoes.offer(new Tomato(nx, ny, nz));
                    }
                }
            }

            ++totalDay;
        }

        for(int k = 0; k < height; k++) {
            for(int i = 0; i < vertical; i++) {
                for(int j = 0; j < horizontal; j++) {
                    if(box[i][j][k] != EMPTY && !isVisited[i][j][k]) {
                        System.out.println(-1);
                        return;
                    }
                }
            }
        }

        System.out.println(totalDay);
    }

    static class Tomato {
        int x;
        int y;
        int z;

        Tomato(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }
}
