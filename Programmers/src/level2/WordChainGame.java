package level2;

import java.util.ArrayList;

public class WordChainGame {
    public int[] solution(int n, String[] words) {
        int[] answer = new int[2];
        int step = 0, turn = 1;
        ArrayList<String> beforeWords = new ArrayList<>();

        for (int i = 0; i < words.length; i++) {
            if (i % n == 0) {
                step++;
                turn = 1;
            }
            if (beforeWords.contains(words[i]) ||
                    (i > 0 && words[i].charAt(0) != words[i - 1].charAt(words[i - 1].length() - 1))) {
                answer[0] = turn;
                answer[1] = step;
                break;
            }
            beforeWords.add(words[i]);
            turn++;
        }

        return answer;
    }
}