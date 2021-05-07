package level1;

import java.util.ArrayList;

public class HateSameDigit {
    public int[] solution(int[] arr) {
        int[] answer = {};
        ArrayList<Integer> temp = new ArrayList<>();

        int before = arr[0];
        temp.add(before);

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == before) continue;
            temp.add(arr[i]);
            before = arr[i];
        }

        answer = temp.stream().mapToInt(Integer::intValue).toArray();
        return answer;
    }
}