package level1;

import java.util.Arrays;

public class KthNumber {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        int[] piece = {};
        for (int i = 0; i < answer.length; i++) {
            piece = Arrays.copyOfRange(array, commands[i][0] - 1, commands[i][1]);
            Arrays.sort(piece);
            answer[i] = piece[commands[i][2] - 1];
        }
        return answer;
    }
}