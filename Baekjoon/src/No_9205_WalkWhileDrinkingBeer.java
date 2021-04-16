import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class No_9205_WalkWhileDrinkingBeer {

    static class Location {
        int x, y;
        public Location(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCases = Integer.parseInt(br.readLine());

        for(int no = 1; no <= testCases; no++) {
            int n = Integer.parseInt(br.readLine());
            Location[] location = new Location[n+2];
            boolean[][] isMovable = new boolean[n+2][n+2];

            for(int i = 0; i < n+2; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());	// 현재 위치(집/편의점/패스티벌 장소)의 x좌표
                int y = Integer.parseInt(st.nextToken());	// 현재 위치(집/편의점/패스티벌 장소)의 y좌표
                location[i] = new Location(x, y);
            }

            for(int from = 0; from < n+2; from++) {
                for(int to = 0; to < n+2; to++) {
                    if(from == to) continue;

                    // from부터 to까지 맥주를 다 마시기 전에 도착하는 경우 isMovable[from][to] = true, 못 도착하는 경우 false
                    if(Math.abs(location[from].x-location[to].x) + Math.abs(location[from].y-location[to].y) <= 1000)
                        isMovable[from][to] = true;
                    else isMovable[from][to] = false;
                }
            }

            for(int via = 0; via < n+2; via++) {	// 경로를 거쳐
                for(int from = 0; from < n+2; from++) {	// from부터
                    for(int to = 0; to < n+2; to++) {	// to까지
                        // 직접적으로는 도착할 수 없지만 경로를 거쳐 도착 가능한 경우 isMovable[from][to] = true
                        if(!isMovable[from][to] && isMovable[from][via] && isMovable[via][to])
                            isMovable[from][to] = true;
                    }
                }
            }

            if(isMovable[0][n+1]) System.out.println("happy");	// 패스티벌까지 맥주를 다 안 마시는 경우
            else System.out.println("sad");	// 패스티벌 도착 전에 맥주를 다 마시는 경우
        }
    }
}
