import java.util.PriorityQueue;

public class MoreSpicy {
    class Solution {
        public int solution(int[] scoville, int K) {
            int answer = 0;
            int min, secondMin;
            PriorityQueue<Integer> pq = new PriorityQueue();    // 오름차순 우선순위 큐
            for(int i = 0; i < scoville.length; i++)
                pq.offer(scoville[i]);

            while(pq.peek() < K) {
                if(pq.size() == 1) {
                    answer = -1;
                    break;
                }
                min = pq.poll();                                // 가장 작은 스코빌 지수
                secondMin = pq.poll();                          // 두번째로 작은 스코빌 지수
                pq.offer(min+secondMin*2);                   // 새로운 스코빌 지수 추가
                answer++;
            }

            return answer;
        }
    }
}
