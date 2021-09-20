import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class No_17608_Stick {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int stickCount = Integer.valueOf(br.readLine());
        int[] stickHeights = new int[stickCount];

        for (int i = 0; i < stickCount; i++) {
            stickHeights[i] = Integer.valueOf(br.readLine());
        }

        int maxStickHeight = stickHeights[stickCount - 1];
        int startIndex = stickCount - 2;
        int showingStickCount = 1;

        for (int i = startIndex; i >= 0; i--) {
            if (stickHeights[i] > maxStickHeight) {
                ++showingStickCount;
                maxStickHeight = stickHeights[i];
            }
        }

        System.out.println(showingStickCount);
    }
}
