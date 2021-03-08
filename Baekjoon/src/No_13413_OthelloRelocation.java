import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class No_13413_OthelloRelocation {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Stack<Character> differentChar = new Stack<>();

        char[] initialState, targetState;
        char standardChar;
        int testCases = Integer.parseInt(br.readLine());
        int n, relocateCount;

        for(int no = 1; no <= testCases; no++) {
            // 입력 및 초기화
            n = Integer.parseInt(br.readLine());
            initialState = br.readLine().toCharArray();
            targetState = br.readLine().toCharArray();
            standardChar = '.';
            relocateCount = 0;
            differentChar.clear();

            // 1. 두 말의 위치를 교환하여 재배치하는 경우
            for(int i = 0; i < n; i++) {
                if(initialState[i] != targetState[i]) {                                     // 초기 상태와 목표 상태가 다르면
                    if(differentChar.isEmpty()) standardChar = initialState[i];             // 초기 상태의 처음으로 다른 문자를 기준으로
                    if(initialState[i] == standardChar) differentChar.push(standardChar);   // 같으면 stack에 넣고
                    else {
                        differentChar.pop();                                                // 다르면 stack에서 빼서
                        ++relocateCount;                                                    // 둘을 교환하여 재배치
                    }
                }
            }

            // 2. 말을 뒤집어서 재배치하는 경우
            relocateCount += differentChar.size();                                          // 교환할 수 없는 말은 뒤집어서 재배치

            System.out.println(relocateCount);
        }
    }
}
