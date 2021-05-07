package level2;

import java.util.Arrays;

public class Printer {
    public int solution(int[] priorities, int location) {
        int answer = 0;
        int priority, max;
        int i = 0, n = priorities.length;
        int[] done = new int[n];

        do {
            i %= n;
            priority = priorities[i];
            max = Arrays.stream(priorities).max().getAsInt();
            if (max <= priority) {
                done[i] = -1;
                priorities[i] = -1;
                answer++;
            }
            i++;
        } while (done[location] != -1);

        return answer;
    }
}