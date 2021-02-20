/*
문제
여는 괄호와 닫는 괄호만으로 이루어진 문자열이 주어진다.
여기서 안정적인 문자열을 만들기 위한 최소 연산의 수를 구하려고 한다.
안정적인 문자열의 정의란 다음과 같다.

빈 문자열은 안정적이다.
S가 안정적이라면, {S}도 안정적인 문자열이다.
S와 T가 안정적이라면, ST(두 문자열의 연결)도 안정적이다.
{}, {}{}, {{}{}}는 안정적인 문자열이지만, }{, {{}{, {}{는 안정적인 문자열이 아니다.

문자열에 행할 수 있는 연산은 여는 괄호를 닫는 괄호로 바꾸거나, 닫는 괄호를 여는 괄호로 바꾸는 것 2가지이다.

입력
입력은 여러 개의 데이터 세트로 이루어져 있다. 각 데이터 세트는 한 줄로 이루어져 있다.
줄에는 여는 괄호와 닫는 괄호만으로 이루어진 문자열이 주어진다. 문자열의 길이가 2000을 넘는 경우는 없고, 항상 길이는 짝수이다.

입력의 마지막 줄은 '-'가 한 개 이상 주어진다.
 */

import java.io.*;
import java.util.Stack;

public class No_4889_StableString {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Stack<Character> stack = new Stack<>();

        int no = 1, count;
        String str;
        Character bracket, unpair1, unpair2;
        while(!(str = br.readLine()).contains("-")) {
            count = 0;
            stack.clear();

            for(int i = 0; i < str.length(); i++) {
                bracket = str.charAt(i);
                if(!stack.isEmpty() && stack.peek() == '{' && bracket == '}') stack.pop();
                else stack.push(bracket);
            }

            while(!stack.isEmpty()) {
                unpair1 = stack.pop();
                unpair2 = stack.pop();

                if(unpair1 == unpair2) count++;
                else count+=2;
            }

            bw.write(no++ + ". " + count);
            bw.newLine();
        }

        br.close();
        bw.flush();
        bw.close();
    }
}
