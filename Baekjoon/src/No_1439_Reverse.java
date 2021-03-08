import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class No_1439_Reverse {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] letters = br.readLine().toCharArray();

        char flag = letters[0];
        int zeroCount = 0, oneCount = 0;

        // 연속된 0과 1의 문자열 중 개수가 더 적은 만큼 뒤집기
        for(int end = 1 ; end < letters.length; end++) {
            if(letters[end] != flag) {      // 연속된 문자가 끝난 경우
                if ((flag == '0')) {        // 연속된 문자가 0인 경우
                    flag = '1';
                    ++zeroCount;
                } else {                    // 연속된 문자가 1인 경우
                    flag = '0';
                    ++oneCount;
                }
            }
        }

        // 마지막 연속된 수는 위에서 더해지지 않음
        if(letters[letters.length-1] == '0') ++zeroCount;
        else ++oneCount;

        System.out.println(Math.min(zeroCount, oneCount));
    }
}
