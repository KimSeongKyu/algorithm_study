package level1;

import java.util.ArrayList;
import java.util.Arrays;

public class MockTest {
    public int[] solution(int[] answers) {
        int[] answer = {};
        int[] scores = new int[3];
        int[] answer1 = {1, 2, 3, 4, 5};
        int[] answer2 = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] answer3 = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
        int max;
        ArrayList<Integer> temp = new ArrayList();

        for (int i = 0; i < answers.length; i++) {
            if (answers[i] == answer1[i % 5]) scores[0]++;
            if (answers[i] == answer2[i % 8]) scores[1]++;
            if (answers[i] == answer3[i % 10]) scores[2]++;
        }

        max = Arrays.stream(scores).max().getAsInt();
        for (int i = 0; i < 3; i++) {
            if (max != 0 && max == scores[i])
                temp.add(i + 1);
        }

        answer = temp.stream().mapToInt(i -> i).toArray();
        return answer;
    }
}