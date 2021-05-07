package level2;

import java.util.ArrayList;

public class DevelopFuntion {
    public int[] solution(int[] progresses, int[] speeds) {
        int[] answer = {};
        int max = 0;
        int works = 0;
        int[] workDays = new int[progresses.length];
        ArrayList<Integer> answers = new ArrayList();

        for (int i = 0; i < progresses.length; i++) {
            workDays[i] = (int) Math.ceil((double) (100 - progresses[i]) / speeds[i]);
        }

        for (int i = 0; i < workDays.length; i++) {
            if (workDays[i] > max) {
                max = workDays[i];
                if (i > 0) {
                    answers.add(works);
                    works = 0;
                }
            }
            works++;
        }
        answers.add(works);

        answer = answers.stream().mapToInt(i -> i).toArray();
        return answer;
    }
}