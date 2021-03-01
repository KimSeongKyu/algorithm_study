/*
문제 설명
트럭 여러 대가 강을 가로지르는 일 차선 다리를 정해진 순으로 건너려 합니다.
모든 트럭이 다리를 건너려면 최소 몇 초가 걸리는지 알아내야 합니다.
트럭은 1초에 1만큼 움직이며, 다리 길이는 bridge_length이고 다리는 무게 weight까지 견딥니다.
※ 트럭이 다리에 완전히 오르지 않은 경우, 이 트럭의 무게는 고려하지 않습니다.

solution 함수의 매개변수로 다리 길이 bridge_length, 다리가 견딜 수 있는 무게 weight, 트럭별 무게 truck_weights가 주어집니다.
이때 모든 트럭이 다리를 건너려면 최소 몇 초가 걸리는지 return 하도록 solution 함수를 완성하세요.

제한 조건
bridge_length는 1 이상 10,000 이하입니다.
weight는 1 이상 10,000 이하입니다.
truck_weights의 길이는 1 이상 10,000 이하입니다.
모든 트럭의 무게는 1 이상 weight 이하입니다.
 */

import java.util.ArrayList;

public class TruckCrossTheBridge {
    class Solution {
        public int solution(int bridge_length, int weight, int[] truck_weights) {
            int answer = 1;
            int current = 0, next = 0;
            int on_weight = 0;
            int[] on_bridge = new int[truck_weights.length];
            ArrayList<Integer> pass_bridge = new ArrayList();

            while(pass_bridge.size() != truck_weights.length) {         // 모든 트럭이 다리를 건널 때 까지 반복
                answer++;                                               // 시간 증가
                if(next < truck_weights.length &&
                        on_weight + truck_weights[next] <= weight) {    // 트럭이 다리 위에 올라올 수 있는 경우
                    on_weight += truck_weights[next];                   // 다리 위로 트럭의 무게 증가
                    next++;                                             // 대상을 다음 트럭으로 이동
                }
                for(int i = current; i < next; i++)
                    on_bridge[i]++;                                     // 다리 위에 있는 트럭이 다리 위에 있는 시간 증가
                if(on_bridge[current] == bridge_length){                // 트럭이 다리를 통과한 경우
                    pass_bridge.add(current);                           // 다리를 통과한 트럭 증가
                    on_weight -= truck_weights[current];                // 다리 위 무게 감소
                    current++;
                }
            }

            return answer;
        }
    }
}
