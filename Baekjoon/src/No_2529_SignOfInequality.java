import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class No_2529_SignOfInequality {

    private static int n;
    private static String[] inequalities;

    private static boolean[] isVisited = new boolean[10];

    private static List<String> numbersSatisfyingInequality = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(input.readLine());
        inequalities = input.readLine().split(" ");

        makePermutations(0, "");

        System.out.println(numbersSatisfyingInequality.get(numbersSatisfyingInequality.size() - 1));
        System.out.println(numbersSatisfyingInequality.get(0));
    }

    private static void makePermutations(int step, String number) {
        if (step == n + 1) {
            numbersSatisfyingInequality.add(number);
            return;
        }

        for (int i = 0; i <= 9; i++) {
            if (isVisited[i]) {
                continue;
            }

            if (step == 0 || isSatisfyingInEquality(number, step, i)) {
                isVisited[i] = true;
                makePermutations(step + 1, number + i);
                isVisited[i] = false;
            }
        }
    }

    private static boolean isSatisfyingInEquality(String number, int step, int rightOperand) {
        int leftOperand = number.charAt(step - 1) - '0';
        if ((inequalities[step - 1].equals(">") && leftOperand < rightOperand) ||
                (inequalities[step - 1].equals("<") && leftOperand > rightOperand)) {
            return false;
        }
        return true;
    }
}
