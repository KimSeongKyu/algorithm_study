/*
문제
2차원 평면 위의 점 N개가 주어진다. 좌표를 x좌표가 증가하는 순으로,
x좌표가 같으면 y좌표가 증가하는 순서로 정렬한 다음 출력하는 프로그램을 작성하시오.

입력
첫째 줄에 점의 개수 N (1 ≤ N ≤ 100,000)이 주어진다.
둘째 줄부터 N개의 줄에는 i번점의 위치 xi와 yi가 주어진다.
(-100,000 ≤ xi, yi ≤ 100,000) 좌표는 항상 정수이고, 위치가 같은 두 점은 없다.
 */

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class No_11650_CoordinateSort {
    private static int[][] coordinates;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb;

        int n = Integer.parseInt(st.nextToken());

        coordinates = new int[n][2];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            coordinates[i][0] = Integer.parseInt(st.nextToken());   // x좌표
            coordinates[i][1] = Integer.parseInt(st.nextToken());   // y좌표
        }

        Arrays.sort(coordinates, (o1, o2) -> {
            if(o1[0] == o2[0]) return o1[1]-o2[1];  // x좌표가 같으면 y좌표를 기준으로 정렬
            return o1[0]-o2[0]; // x좌표를 기준으로 정렬
        });

        for(int i  = 0; i < n; i++) {
            sb = new StringBuilder();
            for(int j = 0; j < 2; j++)
                sb.append(coordinates[i][j] + " ");
            sb.setLength(sb.length()-1);
            bw.write(sb.toString());
            bw.newLine();
        }

        bw.flush();
        bw.close();
    }
}
