package level2;

import java.util.PriorityQueue;

public class MoreSpicy {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        int min, secondMin;
        PriorityQueue<Integer> pq = new PriorityQueue();
        for (int i = 0; i < scoville.length; i++)
            pq.offer(scoville[i]);

        while (pq.peek() < K) {
            if (pq.size() == 1) {
                answer = -1;
                break;
            }
            min = pq.poll();
            secondMin = pq.poll();
            pq.offer(min + secondMin * 2);
            answer++;
        }

        return answer;
    }
}