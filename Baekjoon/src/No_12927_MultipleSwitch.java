import java.util.Scanner;

public class No_12927_MultipleSwitch {
    private static char[] lights;
    private static int count;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.next();

        if(!input.contains("Y")) System.out.println(0);
        else if(!input.contains("N")) System.out.println(1);
        else {
            lights = new char[input.length()+1];
            for(int i = 1; i < lights.length; i++)
                lights[i] = input.charAt(i-1);

            char condition = (lights[1] == 'Y') ? 'N' : 'Y';
            toggle(condition);

            if(lights.toString().contains("Y")) System.out.println(-1);
            else {
                if(lights[1] == 'Y') System.out.println(count+1);
                else System.out.println(count);
            }
        }
    }
    private static void toggle(char condition) {
        for(int i = 2; i < lights.length; i++) {
            if(lights[i] == condition) {
                ++count;
                for(int j = i; j < lights.length; j+=i){
                    lights[j] = (lights[j] == 'Y') ? 'N' : 'Y';
                }
            }
        }
    }
}
