import java.util.Scanner;

public class No_9095_AddOneTwoThree {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] ways = new int[11];
        ways[1] = 1;
        ways[2] = 2;
        ways[3] = 4;
        for(int i = 4; i <= 10; i++)
            ways[i] = ways[i-1]+ways[i-2]+ways[i-3];

        int testCases = sc.nextInt();
        for(int no = 1; no <= testCases; no++) {
            int n = sc.nextInt();
            System.out.println(ways[n]);
        }
    }
}
