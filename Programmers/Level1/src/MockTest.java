/*
문제 설명
수포자는 수학을 포기한 사람의 준말입니다. 수포자 삼인방은 모의고사에 수학 문제를 전부 찍으려 합니다.
수포자는 1번 문제부터 마지막 문제까지 다음과 같이 찍습니다.

1번 수포자가 찍는 방식: 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, ...
2번 수포자가 찍는 방식: 2, 1, 2, 3, 2, 4, 2, 5, 2, 1, 2, 3, 2, 4, 2, 5, ...
3번 수포자가 찍는 방식: 3, 3, 1, 1, 2, 2, 4, 4, 5, 5, 3, 3, 1, 1, 2, 2, 4, 4, 5, 5, ...

1번 문제부터 마지막 문제까지의 정답이 순서대로 들은 배열 answers가 주어졌을 때,
가장 많은 문제를 맞힌 사람이 누구인지 배열에 담아 return 하도록 solution 함수를 작성해주세요.

제한 조건
시험은 최대 10,000 문제로 구성되어있습니다.
문제의 정답은 1, 2, 3, 4, 5중 하나입니다.
가장 높은 점수를 받은 사람이 여럿일 경우, return하는 값을 오름차순 정렬해주세요.
 */

import java.util.ArrayList;
import java.util.Arrays;

public class MockTest {

    public static void main(String[] args) {
        int[][] answers = {{1, 2, 3, 4, 5}, {1, 3, 2, 4, 2}};
        int[][] returns = {{1}, {1, 2, 3}};

        for(int i = 0; i < 2; i++) {
            int[] result = solution(answers[i]);
            boolean isCorrect = true;

            for(int j = 0; j < returns[i].length; j++) {
                if(result[j] != returns[i][j]) {
                    isCorrect = false;
                    break;
                }
            }

            if(isCorrect) System.out.println("Correct");
            else System.out.println("Wrong");
        }

    }

    public static int[] solution(int[] answers) {
        int[] answer = {};
        int[] scores = new int[3];
        int[] answer1 = {1, 2, 3, 4, 5};
        int[] answer2 = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] answer3 = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
        int max;
        ArrayList<Integer> temp = new ArrayList();

        // 정답을 순회하며 각 학생의 점수 계산
        for(int i = 0; i < answers.length; i++) {
            if(answers[i] == answer1[i%5]) scores[0]++;
            if(answers[i] == answer2[i%8]) scores[1]++;
            if(answers[i] == answer3[i%10]) scores[2]++;
        }


        max = Arrays.stream(scores).max().getAsInt();
        for(int i = 0; i < 3; i++) {
            // 가장 점수가 높은 학생 선별(중복 시 오름차순으로 정렬)
            if(max != 0 && max == scores[i])
                temp.add(i+1);
        }

        answer = temp.stream().mapToInt(i->i).toArray();
        return answer;
    }
}
