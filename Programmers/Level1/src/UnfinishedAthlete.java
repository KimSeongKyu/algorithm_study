/*
문제 설명
수많은 마라톤 선수들이 마라톤에 참여하였습니다. 단 한 명의 선수를 제외하고는 모든 선수가 마라톤을 완주하였습니다.

마라톤에 참여한 선수들의 이름이 담긴 배열 participant와 완주한 선수들의 이름이 담긴 배열 completion이 주어질 때,
완주하지 못한 선수의 이름을 return 하도록 solution 함수를 작성해주세요.

제한사항
마라톤 경기에 참여한 선수의 수는 1명 이상 100,000명 이하입니다.
completion의 길이는 participant의 길이보다 1 작습니다.
참가자의 이름은 1개 이상 20개 이하의 알파벳 소문자로 이루어져 있습니다.
참가자 중에는 동명이인이 있을 수 있습니다.
 */

import java.util.Arrays;

public class UnfinishedAthlete {

    public static void main(String[] args) {
        String[][] participants = {
                {"leo", "kiki", "eden"},
                {"marina", "josipa", "nikola", "vinko", "filipa"},
                {"mislav", "stanko", "mislav", "ana"}
        };
        String[][] completions = {
                {"eden", "kiki"},
                {"josipa", "filipa", "marina", "nikola"},
                {"stanko", "ana", "mislav"}
        };
        String[] answers = {"leo", "vinko", "mislav"};

        for(int i = 0; i < 3; i++) {
            String result = solution(participants[i], completions[i]);
            if(answers[i].equals(result))
                System.out.println("Correct");
            else
                System.out.println("Wrong");
        }

    }

    public static String solution(String[] participant, String[] completion) {
        String answer = "";
        Arrays.sort(participant);
        Arrays.sort(completion);
        for(int i = 0; i < participant.length; i++) {
            try {
                // 정렬을 했기 때문에 같은 index에 존재하지 않는다면 completion에
                // 해당 선수가 없다는 것을 의미하므로 완주하지 못한 선수가 됨
                if(!participant[i].equals(completion[i])) {
                    answer = participant[i];
                    break;
                }
            }
            // 정렬했을 때 participant의 가장 마지막에 존재하는 선수가 완주하지 못했을 경우
            // completion에서 ArrayIndexOutOfBoundsException 발생
            catch(ArrayIndexOutOfBoundsException e) {
                answer = participant[i];
            }
        }
        return answer;
    }
}
