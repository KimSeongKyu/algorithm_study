package level2;

import java.util.Arrays;

public class PhoneNumberList {
    public boolean solution(String[] phone_book) {
        boolean answer = true;
        Arrays.sort(phone_book);

        Loop:
        for (int i = 0; i < phone_book.length; i++) {
            String number = phone_book[i];
            for (int j = i + 1; j < phone_book.length; j++) {
                if (phone_book[j].length() < number.length()) continue;
                String preNumber = phone_book[j].substring(0, number.length());
                if (preNumber.equals(number)) {
                    answer = false;
                    break Loop;
                }
            }
        }
        return answer;
    }
}