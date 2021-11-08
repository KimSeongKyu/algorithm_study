import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class No_16637_AddBracket {

    private static int max;
    private static List<Character> operations;
    private static List<Integer> numbers;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        int length = Integer.parseInt(input.readLine());
        String expression = input.readLine();

        operations = new ArrayList<>();
        numbers = new ArrayList<>();

        for (int i = 0; i < length; i++) {
            char letter = expression.charAt(i);
            if (letter == '+' || letter == '-' || letter == '*') {
                operations.add(letter);
                continue;
            }
            numbers.add(Character.getNumericValue(letter));
        }

        max = 0;
        DFS(numbers.get(0), 0);

        System.out.println(max);
        input.close();
    }

    private static int calculate(char operation, int left, int right) {
        int result = 0;
        switch (operation) {
            case '+':
                result = left + right;
            case '-':
                result = left - right;
            case '*':
                result = left * right;
        }
        return result;
    }

    public static void DFS(int result, int opIdx) {
        if (opIdx >= operations.size()) {
            max = Math.max(max, result);
            return;
        }

        int res1 = calculate(operations.get(opIdx), result, numbers.get(opIdx + 1));
        DFS(res1, opIdx + 1);

        if (opIdx + 1 < operations.size()) {
            int res2 = calculate(operations.get(opIdx + 1), numbers.get(opIdx + 1), numbers.get(opIdx + 2));
            DFS(calculate(operations.get(opIdx), result, res2), opIdx + 2);
        }
    }
}
