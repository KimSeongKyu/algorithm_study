import java.util.Scanner;

public class No_9372_SangeunTravel {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testCases = sc.nextInt();

        int n, m;
        for(int no = 1; no <= testCases; no++) {
            n = sc.nextInt();
            m = sc.nextInt();
            for(int i = 0; i < m; i++) {
                sc.nextInt();
                sc.nextInt();
            }
            System.out.println(n-1);
        }
    }
}
