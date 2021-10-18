import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class No_10804_ReverseCard {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int[] cards = new int[21];
        for (int i = 1; i <= 20; i++) {
            cards[i] = i;
        }

        for (int i = 0; i < 10; i++) {
            StringTokenizer st = new StringTokenizer(input.readLine());
            int fromIndex = Integer.valueOf(st.nextToken());
            int toIndex = Integer.valueOf(st.nextToken()) + 1;
            int[] temp = Arrays.copyOfRange(cards, fromIndex, toIndex);

            for (int j = 0; j < temp.length; j++) {
                cards[toIndex - 1 - j] = temp[j];
            }
        }

        StringBuilder output = new StringBuilder();
        for (int i = 1; i <= 20; i++) {
            output.append(cards[i] + " ");
        }
        output.setLength(output.length() - 1);
        System.out.println(output.toString());
    }
}
