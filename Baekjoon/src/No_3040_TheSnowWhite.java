import java.util.Arrays;
import java.util.Scanner;

public class No_3040_TheSnowWhite {
    private static int[] dwarfs;
    private static int[] selectedDwarfs;
    private static int[] result;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        dwarfs = new int[9];
        selectedDwarfs = new int[7];

        for(int i = 0; i < 9; i++)
            dwarfs[i] = sc.nextInt();

        combination(0, 0);

        for(int dwarf : result) System.out.println(dwarf);
    }

    private static void combination(int step, int start) {
        if(step == 7) {
            if(Arrays.stream(selectedDwarfs).sum() == 100) {
                result = Arrays.copyOf(selectedDwarfs, 7);
            }
            return;
        }

        for(int i = start; i < 9; i++) {
            selectedDwarfs[step] = dwarfs[i];
            combination(step+1, i+1);
        }
    }
}
