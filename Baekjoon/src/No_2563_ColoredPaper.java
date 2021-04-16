import java.util.Scanner;

public class No_2563_ColoredPaper {
    public static void main(String[] args) {
        boolean[][] onPaper = new boolean[100][100];
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int x, y;
        int area = 0;

        for(int i = 0; i < n; i++) {
            x = sc.nextInt();
            y = sc.nextInt();

            for(int j = x; j < x+10; j++) {
                for(int k = y; k < y+10; k++) {
                    onPaper[j][k] = true;
                }
            }
        }

        for(int i = 0; i < 100; i++) {
            for(int j = 0; j < 100; j++) {
                if(onPaper[i][j]) ++area;
            }
        }

        System.out.println(area);
    }
}
