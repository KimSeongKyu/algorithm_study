package level2;

import java.util.Stack;

public class RemovePair {
    public int solution(String s) {
        int answer = 0;
        char before = '1';
        Stack<Character> stack = new Stack<>();

        Loop:
        for (int i = 0; i < s.length(); i++) {
            stack.push(s.charAt(i));
            while (stack.peek() == before) {
                stack.pop();
                stack.pop();
                if (stack.isEmpty()) {
                    before = '1';
                    continue Loop;
                } else break;
            }
            before = stack.peek();
        }
        if (stack.isEmpty()) answer = 1;
        return answer;
    }
}