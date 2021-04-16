import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class No_1759_MakePassword {

    private static int L, C;
    private static char[] characters;
    private static char[] password;
    private static BufferedWriter bw;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        characters = new char[C];
        password = new char[L];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < C; i++) characters[i] = st.nextToken().charAt(0);

        Arrays.sort(characters);	// 암호로 사용할 법한 문자 오름차순 정렬
        makePassword(0, 0);			// 조합 찾기

        br.close();
        bw.flush();
        bw.close();
    }

    // 조합 찾기
    private static void makePassword(int step, int start) throws IOException {
        if(step == L) {
            int vowelCount = 0, consonantCount = 0;
            StringBuilder sb = new StringBuilder();

            for(char c : password) {
                if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') ++vowelCount;	// 모음인 경우
                else ++consonantCount;														// 자음인 경우
                sb.append(c);
            }

            if(vowelCount >= 1 && consonantCount >= 2) {	// 모음이 1개 이상, 자음이 2개 이상인 경우 출력
                bw.write(sb.toString());
                bw.newLine();
            }
            return;
        }

        for(int i = start; i < C; i++) {
            password[step] = characters[i];
            makePassword(step+1, i+1);
        }
    }
}
