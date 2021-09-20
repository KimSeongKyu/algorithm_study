import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class No_17298_NGE {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int numberCount = Integer.valueOf(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] numbers = new int[numberCount];
        int[] nges = new int[numberCount];
        for (int i = 0; i < numberCount; i++) {
            numbers[i] = Integer.valueOf(st.nextToken());
        }

        int startIndex = numberCount - 1;
        nges[startIndex] = -1;
        Stack<Integer> restNumbers = new Stack<>();
        restNumbers.push(numbers[startIndex]);

        for (int i = startIndex - 1; i >= 0; i--) {
            int number = numbers[i];
            int restNumber = restNumbers.peek();

            if (number < restNumber) {
                nges[i] = restNumber;
            } else {
                do {
                    restNumber = restNumbers.pop();
                } while (!restNumbers.isEmpty() && number >= restNumber);

                if (number < restNumber) {
                    nges[i] = restNumber;
                    restNumbers.push(restNumber);
                } else {
                    nges[i] = -1;
                }
            }
            restNumbers.push(number);
        }

        StringBuilder output = new StringBuilder();
        for (int i = 0; i < numberCount; i++) {
            output.append(nges[i] + " ");
        }
        output.setLength(output.length() - 1);
        System.out.println(output);
    }
}
