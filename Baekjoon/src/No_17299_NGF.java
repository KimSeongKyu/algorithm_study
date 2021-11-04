import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class No_17299_NGF {

    private final static int MAX_SEQUENCE_SIZE = 1_000_001;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
        int[] countInSequence = new int[MAX_SEQUENCE_SIZE];

        int sequenceSize = Integer.valueOf(input.readLine());
        int[] sequence = new int[sequenceSize];
        StringTokenizer st = new StringTokenizer(input.readLine());
        for(int i = 0; i < sequenceSize; i++) {
            int number = Integer.valueOf(st.nextToken());
            ++countInSequence[number];
            sequence[i] = number;
        }

        int[] ngfs = new int[sequenceSize];
        Stack<Integer> largerCountThan = new Stack<>();

        for(int i = sequenceSize - 1; i >= 0; i--) {
            while(!largerCountThan.isEmpty()) {
                int largerCountNumber = largerCountThan.peek();
                if(countInSequence[largerCountNumber] > countInSequence[sequence[i]]) {
                    ngfs[i] = largerCountNumber;
                    largerCountThan.push(sequence[i]);
                    break;
                } else {
                    largerCountThan.pop();
                }
            }

            if(largerCountThan.isEmpty()) {
                ngfs[i] = -1;
                largerCountThan.push(sequence[i]);
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int ngf : ngfs) {
            sb.append(ngf + " ");
        }
        output.write(sb.toString());
        output.newLine();

        input.close();
        output.flush();
        output.close();
    }
}
