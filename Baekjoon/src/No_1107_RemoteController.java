import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class No_1107_RemoteController {

    private final static int START_CHANNEL = 100;
    private final static int MAX = 1_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        int channel = Integer.valueOf(input.readLine());
        int brokenButtonCount = Integer.valueOf(input.readLine());

        boolean[] isBrokenButton = new boolean[10];
        if (brokenButtonCount > 0) {
            StringTokenizer st = new StringTokenizer(input.readLine());
            for (int i = 0; i < brokenButtonCount; i++) {
                int brokenButton = Integer.valueOf(st.nextToken());
                isBrokenButton[brokenButton] = true;
            }
        }

        int minClickCount = Math.abs(channel - START_CHANNEL);
        if (!isBrokenButton[0]) {
            minClickCount = Math.min(minClickCount, channel + 1);
        }

        LOOP:
        for (int currentChannel = 1; currentChannel <= MAX; currentChannel++) {
            int tempChannel = currentChannel;
            while (tempChannel > 0) {
                if (isBrokenButton[tempChannel % 10]) {
                    continue LOOP;
                }
                tempChannel /= 10;
            }

            int currentClickCount = String.valueOf(currentChannel).length() + Math.abs(channel - currentChannel);
            minClickCount = Math.min(currentClickCount, minClickCount);
        }

        System.out.println(minClickCount);
    }
}
