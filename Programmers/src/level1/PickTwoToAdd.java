package level1;

import java.util.ArrayList;
import java.util.Collections;

public class PickTwoToAdd {
    public int[] solution(int[] numbers) {
        int[] answer = {};
        int sum;
        ArrayList<Integer> arrayList = new ArrayList();

        for (int i = 0; i < numbers.length - 1; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                sum = numbers[i] + numbers[j];
                if (!arrayList.contains(sum)) arrayList.add(sum);

            }
        }

        Collections.sort(arrayList);
        answer = arrayList.stream().mapToInt(i -> i).toArray();
        return answer;
    }
}