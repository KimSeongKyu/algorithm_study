import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class No_1987_Alphabet {

    private static char[][] map;
    private static boolean[] isSelected;
    private static int[] dx = {0, 0, -1, 1};
    private static int[] dy = {-1, 1, 0, 0};
    private static int max = 0;
    private static int R, C;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][];
        isSelected = new boolean[26];

        for(int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
        }

        findPath(0, 0, 1);
        System.out.println(max);
    }

    private static void findPath(int x, int y, int step) {
        int alphabet = map[y][x]-65;
        isSelected[alphabet] = true;

        int nx, ny;
        for(int i = 0; i < 4; i++) {
            nx = x + dx[i];
            ny = y + dy[i];
            if(isPromise(nx, ny)) {
                findPath(nx, ny, step+1);
            }
        }

        max = Math.max(step, max);
        isSelected[alphabet] = false;
        return;
    }

    private static boolean isPromise(int x, int y) {

        if(x >= 0 && x < C && y >= 0 && y < R) {
            int alphabet = map[y][x]-65;
            if(!isSelected[alphabet]) return true;
        }

        return false;
    }
}
