import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class No_5397_KeyLogger {

    private final static char LEFT_ARROW = '<';
    private final static char RIGHT_ARROW = '>';
    private final static char DELETE = '-';

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCases = Integer.valueOf(br.readLine());

        for(int no = 1; no <= testCases; no++) {
            Stack<Character> leftPasswords = new Stack<>();
            Stack<Character> rightPasswords = new Stack<>();
            char[] pressedKeys = br.readLine().toCharArray();
            int pressedKeyCount = pressedKeys.length;

            for(int i = 0; i < pressedKeyCount; i++) {
                char key = pressedKeys[i];

                if(key == LEFT_ARROW) {
                    if(!leftPasswords.isEmpty()) {
                        rightPasswords.push(leftPasswords.pop());
                    }
                } else if(key == RIGHT_ARROW) {
                    if(!rightPasswords.isEmpty()) {
                        leftPasswords.push(rightPasswords.pop());
                    }
                } else if(key == DELETE) {
                    if(!leftPasswords.isEmpty()) {
                        leftPasswords.pop();
                    }
                } else {
                    leftPasswords.push(key);
                }
            }

            StringBuilder password = new StringBuilder();

            int leftPasswordLength = leftPasswords.size();
            for(int i = 0; i < leftPasswordLength; i++) {
                password.append(leftPasswords.pop());
            }

            password = password.reverse();

            int rightPasswordLength = rightPasswords.size();
            for(int i = 0; i < rightPasswordLength; i++) {
                password.append(rightPasswords.pop());
            }

            System.out.println(password);
        }
    }
}
