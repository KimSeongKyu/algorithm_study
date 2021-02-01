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
