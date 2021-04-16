import java.util.Scanner;

public class No_2839_SugarDelivery {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int result = 0;

        while(n > 0) {
            if(n%5 == 0) {
                result += n/5;
                n = 0;
                break;
            }
            n-=3;
            ++result;
        }

        if(n != 0) result = -1;

        System.out.println(result);
    }
}
