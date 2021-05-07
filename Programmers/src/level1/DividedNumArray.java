package level1;

import java.util.*;

public class DividedNumArray {
    public int[] solution(int[] arr, int divisor) {
        int[] answer = {};
        ArrayList<Integer> arrayList = new ArrayList();

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % divisor == 0) arrayList.add(arr[i]);
        }

        if (arrayList.size() == 0) arrayList.add(-1);
        else Collections.sort(arrayList);

        answer = arrayList.stream().mapToInt(i -> i).toArray();

        return answer;
    }
}