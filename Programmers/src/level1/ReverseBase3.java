package level1;

public class ReverseBase3 {
    public int solution(int n) {
        int answer = 0;
        String base3 = "";

        while (n != 0) {
            base3 += String.valueOf(n % 3);
            n /= 3;
        }

        for (int i = 0; i < base3.length(); i++) {
            answer += (Math.pow(3, i) * (base3.charAt(base3.length() - 1 - i) - 48));
        }

        return answer;
    }
}