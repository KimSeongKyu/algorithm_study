import java.util.Scanner;

public class No_2961_DeliciousFood {
    private static int[] sour;			// 신맛
    private static int[] bitterness;	// 쓴맛
    private static int n;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        bitterness = new int[n];
        sour = new int[n];

        for(int i = 0; i < n; i++) {
            sour[i] = sc.nextInt();
            bitterness[i] = sc.nextInt();
        }

        int min = cook(1 << n);
        System.out.println(min);
    }

    private static int cook(int bitMask) {
        int sourMult, bitterSum;
        int min = Integer.MAX_VALUE;

        for(int flag = 1; flag < bitMask; flag++) {
            sourMult = 1;
            bitterSum = 0;

            for(int i = 0; i < n; i++) {
                if((flag & (1 << i)) != 0) {
                    sourMult *= sour[i];
                    bitterSum += bitterness[i];
                }
            }

            min = Math.min(Math.abs(sourMult-bitterSum), min);
        }

        return min;
    }

}
