import java.util.Scanner;

public class No_9935_StringBomb {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String targetString = sc.next();
        String bombString = sc.next();
        StringBuilder result = new StringBuilder();

        int targetStringLength = targetString.length();
        int bombStringLength = bombString.length();
        int resultLength = 0;

        for(int i = 0; i < targetStringLength; i++) {
            result.append(targetString.charAt(i));
            ++resultLength;

            if(resultLength >= bombStringLength) {
                boolean isSame = true;

                for(int j = 0; j < bombStringLength; j++) {
                    char characterOfResult = result.charAt(resultLength - bombStringLength + j);
                    char characterOfBombString = bombString.charAt(j);

                    if(characterOfResult != characterOfBombString) {
                        isSame = false;
                        break;
                    }
                }

                if(isSame) {
                    result.delete(resultLength - bombStringLength, resultLength);
                    resultLength -= bombStringLength;
                }
            }
        }

        if(resultLength == 0) {
            System.out.println("FRULA");
        } else {
            System.out.println(result);
        }
    }
}
