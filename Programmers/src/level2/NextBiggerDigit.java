package level2;

public class NextBiggerDigit {
    public int solution(int n) {
        String number = Integer.toBinaryString(n);
        int ones = counts(number, '1');
        do {
            number = Integer.toBinaryString(++n);
        } while (ones != counts(number, '1'));

        return toInt(number);
    }

    public int toInt(String s) {
        int number = 0;
        int bit = 1;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '1') number += bit;
            bit *= 2;
        }
        return number;
    }

    public int counts(String s, char c) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == c) count++;
        }
        return count;
    }
}