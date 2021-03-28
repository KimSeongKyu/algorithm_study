import java.util.Scanner;

public class No_1463_MakeOne {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[] count = (n > 3) ? new int[n+1] : new int[4];
        count[2] = 1;
        count[3] = 1;

        for(int i = 4; i <= n; i++) {
            if(i%6 == 0) {
                count[i] = Math.min(count[i-1]+1, count[i/3]+1);
                count[i] = Math.min(count[i/2]+1, count[i]);
            }
            else if(i%3 == 0) count[i] = Math.min(count[i-1]+1, count[i/3]+1);
            else if(i%2 == 0) count[i] = Math.min(count[i-1]+1, count[i/2]+1);
            else count[i] = count[i-1]+1;
        }

        System.out.println(count[n]);
    }
}
