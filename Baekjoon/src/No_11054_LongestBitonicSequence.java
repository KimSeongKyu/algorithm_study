import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class No_11054_LongestBitonicSequence {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int lengthOfSequence = Integer.valueOf(br.readLine());
        int[] sequence = new int[lengthOfSequence];
        BitonicSequenceLength[] maxLengths = new BitonicSequenceLength[lengthOfSequence];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < lengthOfSequence; i++) {
            sequence[i] = Integer.valueOf(st.nextToken());
            maxLengths[i] = new BitonicSequenceLength();
        }

        for (int i = 1; i < lengthOfSequence; i++) {
            for (int j = 0; j < i; j++) {
                if (sequence[j] < sequence[i]) {
                    maxLengths[i].fromLeft = Math.max(maxLengths[i].fromLeft, maxLengths[j].fromLeft + 1);
                }
            }
        }

        for (int i = lengthOfSequence - 2; i >= 0; i--) {
            for (int j = lengthOfSequence - 1; j > i; j--) {
                if (sequence[j] < sequence[i]) {
                    maxLengths[i].fromRight = Math.max(maxLengths[i].fromRight, maxLengths[j].fromRight + 1);
                }
            }
        }

        int maxLength = Arrays.stream(maxLengths)
                .mapToInt(length -> length.fromLeft + length.fromRight)
                .max()
                .getAsInt();

        System.out.println(maxLength - 1);
    }

    static class BitonicSequenceLength {
        int fromLeft = 1;
        int fromRight = 1;
    }
}
