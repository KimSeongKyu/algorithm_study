package level2;

public class PresentationOfNum {
    public int solution(int n) {
        int answer = 1;
        for (int i = 1; i <= n / 2; i++) {
            int now = i;
            for (int j = i + 1; j <= n / 2 + 1; j++) {
                now += j;
                if (now == n) {
                    answer++;
                } else if (now > n) break;
            }
        }
        return answer;
    }
}