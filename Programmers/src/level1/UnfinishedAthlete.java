package level1;

import java.util.Arrays;

public class UnfinishedAthlete {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        Arrays.sort(participant);
        Arrays.sort(completion);
        for (int i = 0; i < participant.length; i++) {
            try {
                if (!participant[i].equals(completion[i])) {
                    answer = participant[i];
                    break;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                answer = participant[i];
            }
        }
        return answer;
    }
}