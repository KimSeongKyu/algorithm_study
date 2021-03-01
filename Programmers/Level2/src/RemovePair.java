import java.util.Stack;

public class RemovePair {
    class Solution {
        public int solution(String s) {
            int answer = 0;
            char before = '1';                          // 임의의 문자를 before로 지정
            Stack<Character> stack = new Stack<>();

            Loop: for(int i = 0; i < s.length(); i++) {
                stack.push(s.charAt(i));                // stack에 문자 추가
                while(stack.peek() == before) {         // 문자가 짝을 이루는 경우
                    stack.pop();                        // 두 문자 제거
                    stack.pop();
                    if(stack.isEmpty()) {               // stack이 빈 경우 before를 다시 임의의 문자로 지정
                        before = '1';
                        continue Loop;
                    }
                    else break;
                }
                before = stack.peek();                  // 새로 짝을 찾을 문자를 before로 지정
            }
            if(stack.isEmpty()) answer = 1;
            return answer;
        }
    }
}
