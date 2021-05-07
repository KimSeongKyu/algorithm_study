package level2;

import java.util.ArrayList;

public class TruckCrossTheBridge {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 1;
        int current = 0, next = 0;
        int on_weight = 0;
        int[] on_bridge = new int[truck_weights.length];
        ArrayList<Integer> pass_bridge = new ArrayList();

        while (pass_bridge.size() != truck_weights.length) {
            answer++;
            if (next < truck_weights.length &&
                    on_weight + truck_weights[next] <= weight) {
                on_weight += truck_weights[next];
                next++;
            }
            for (int i = current; i < next; i++)
                on_bridge[i]++;
            if (on_bridge[current] == bridge_length) {
                pass_bridge.add(current);
                on_weight -= truck_weights[current];
                current++;
            }
        }

        return answer;
    }
}