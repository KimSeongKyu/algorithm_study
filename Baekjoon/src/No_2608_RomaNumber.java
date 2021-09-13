import java.util.Scanner;

public class No_2608_RomaNumber {

    private final static int THOUSAND = 1000;
    private final static int HUNDRED = 100;
    private final static int TEN = 10;
    private final static int ONE = 1;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String leftOperand = sc.next();
        String rightOperand = sc.next();

        int sum = sum(leftOperand);
        sum += sum(rightOperand);

        System.out.println(sum);

        String sumString = "";
        while (sum > 0) {
            if (sum / THOUSAND > 0) {
                sumString = numberToString(sum, THOUSAND, "M", "", "", "");
                sum %= THOUSAND;
            } else if (sum / HUNDRED > 0) {
                sumString += numberToString(sum, HUNDRED, "C", "CD", "D", "CM");
                sum %= HUNDRED;
            } else if (sum / TEN > 0) {
                sumString += numberToString(sum, TEN, "X", "XL", "L", "XC");
                sum %= TEN;
            } else {
                sumString += numberToString(sum, ONE, "I", "IV", "V", "IX");
                sum %= ONE;
            }
        }

        System.out.println(sumString);
    }

    private static String numberToString(int number, int numberOfDigit, String numberAsString,
                                         String smallNumberAsString, String middleNumberAsString, String largeNumberAsString) {
        int digit = number / numberOfDigit;
        String sum = "";

        if (digit == 4) {
            return smallNumberAsString;
        } else if (digit == 5) {
            return middleNumberAsString;
        } else if (digit == 9) {
            return largeNumberAsString;
        } else {
            while (0 < digit && digit < 4 || 5 < digit && digit < 9) {
                sum += numberAsString;
                --digit;
            }
        }
        return sum;
    }

    private static int sum(String operand) {
        int sum = 0;
        int beforeNumber = 0;
        int operandLength = operand.length();

        for (int i = 0; i < operandLength; i++) {
            int number = Number.valueOf(String.valueOf(operand.charAt(i))).value;

            sum += number;
            if (i > 0 && number > beforeNumber && number / beforeNumber <= 10) {
                sum -= beforeNumber * 2;
            }

            beforeNumber = number;
        }
        return sum;
    }

    private enum Number {

        I(1),
        V(5),
        X(10),
        L(50),
        C(100),
        D(500),
        M(1000);

        private int value;

        Number(int value) {
            this.value = value;
        }
    }
}
