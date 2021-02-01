/*
문제
도현이네 반 학생 N명의 이름과 국어, 영어, 수학 점수가 주어진다.
이때, 다음과 같은 조건으로 학생의 성적을 정렬하는 프로그램을 작성하시오.

국어 점수가 감소하는 순서로
국어 점수가 같으면 영어 점수가 증가하는 순서로
국어 점수와 영어 점수가 같으면 수학 점수가 감소하는 순서로
모든 점수가 같으면 이름이 사전 순으로 증가하는 순서로 (단, 아스키 코드에서 대문자는 소문자보다 작으므로 사전순으로 앞에 온다.)

입력
첫째 줄에 도현이네 반의 학생의 수 N (1 ≤ N ≤ 100,000)이 주어진다.
둘째 줄부터 한 줄에 하나씩 각 학생의 이름, 국어, 영어, 수학 점수가 공백으로 구분해 주어진다.
점수는 1보다 크거나 같고, 100보다 작거나 같은 자연수이다. 이름은 알파벳 대소문자로 이루어진 문자열이고, 길이는 10자리를 넘지 않는다.
 */

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class No_10825_KoreanEnglishMath {
    private static String[][] scores;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        scores = new String[n][4];

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            scores[i][0] = st.nextToken();  // 학생 이름
            scores[i][1] = st.nextToken();  // 국어 점수
            scores[i][2] = st.nextToken();  // 영어 점수
            scores[i][3] = st.nextToken();  // 수학 점수
        }

        Arrays.sort(scores, (o1, o2) -> {
           // 국어점수가 다를 경우 내림차순
           if(!o1[1].equals(o2[1])) return Integer.parseInt(o2[1]) - Integer.parseInt(o1[1]);
           // 국어점수가 같을 경우 영어 점수를 기준으로 오름차순
           else if(!o1[2].equals(o2[2])) return Integer.parseInt(o1[2]) - Integer.parseInt(o2[2]);
           // 국어, 영어 점수가 같을 경우 수학 점수를 기준으로 내림차순
           else if(!o1[3].equals(o2[3])) return Integer.parseInt(o2[3]) - Integer.parseInt(o1[3]);
           // 국어, 영어, 수학 점수가 같을 경우 이름을 사전순으로 오름차순
           else return o1[0].compareTo(o2[0]);
        });

        for(String[] score : scores) {
            bw.write(score[0]);
            bw.newLine();
        }

        bw.flush();
        bw.close();
    }
}
