import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class No_1493_FillBoxes {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long[] box = new long[3];
        for(int i = 0; i < 3; i++) box[i] = Long.parseLong(st.nextToken());
        long boxDiv1Count = box[0]*box[1]*box[2];
        long totalPieces = 0;

        int n = Integer.parseInt(br.readLine());
        long[][] cubes = new long[n][2];
        long[] piecePerLength = new long[n];

        for(int i = n-1; i >= 0; i--) {
            st = new StringTokenizer(br.readLine());
            cubes[i][0] = (long)Math.pow(2, Integer.parseInt(st.nextToken()));
            cubes[i][1] = Long.parseLong(st.nextToken());

            piecePerLength[i] = (box[0]/cubes[i][0]) * (box[1]/cubes[i][0]) * (box[2]/cubes[i][0]);
        }

        long length, cubeDiv1Count;
        for(int i = 0; i < n; i++) {
            length = cubes[i][0];   // 큐브 한 변의 길이
            if(length > box[0] || length > box[1] || length > box[2]) continue; // 큐브가 박스보다 크면 더 작은 큐브를 선택

            // 해당 큐브로 박스를 전부 채우기 위해 필요한 개수가 큐브의 개수보다 많을 경우 주어진 큐브 개수만 사용
            if(piecePerLength[i] > cubes[i][1]) piecePerLength[i] = cubes[i][1];
            totalPieces += piecePerLength[i];

            cubeDiv1Count = (long)Math.pow(length, 3);   // 큐브를 1x1x1로 나눴을 때의 개수

            // 박스를 1x1x1로 나눴을 때의 개수 -= 큐브를 1x1x1로 나눈 개수 * 사용한 큐브의 개수
            boxDiv1Count -= cubeDiv1Count * piecePerLength[i];

            if(boxDiv1Count == 0) break;    // 큐브로 박스를 완전히 채웠으면 종료

            // 현재 사용한 큐브가 차지한 공간만큼 더 작은 큐브가 차지할 수 있는 공간 감소
            for(int j = i+1; j < n; j++) piecePerLength[j] -= (piecePerLength[i] * Math.pow(8, j-i));
        }

        if(boxDiv1Count > 0) totalPieces = -1;
        System.out.println(totalPieces);

        br.close();
    }
}
