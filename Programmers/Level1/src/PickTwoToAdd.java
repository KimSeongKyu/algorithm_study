/*
 * 문제 설명: 정수 배열 numbers가 주어집니다.
 * numbers에서 서로 다른 인덱스에 있는 두 개의 수를 뽑아 더해서 만들 수 있는 모든 수를
 * 배열에 오름차순으로 담아 return 하도록 solution 함수를 완성해주세요.
 */

import java.util.ArrayList;
import java.util.Collections;

public class PickTwoToAdd {
    public static void main(String[] args) {
        int[][] numbers = {
                {2,1,3,4,1},
                {5,0,2,7}
        };
        int[][] answers = {
                {2,3,4,5,6,7},
                {2,5,7,9,12}
        };

        // score the answer logic
        for(int i = 0; i < numbers.length; i++) {
            int[] result = solution(numbers[i]);
            boolean isCorrect = true;

            for(int j = 0; j < result.length; j++) {
                if(result[j] != answers[i][j]) isCorrect = false;
            }

            if(isCorrect) System.out.println("Correct");
            else System.out.println("Wrong");
        }
    }

    public static int[] solution(int[] numbers) {
        int[] answer = {};
        int sum;
        ArrayList<Integer> arrayList = new ArrayList();

        for(int i = 0; i < numbers.length-1; i++) {
            for(int j = i+1; j < numbers.length; j++) {
                sum = numbers[i] + numbers[j];
                if(!arrayList.contains(sum)) arrayList.add(sum);

            }
        }
        Collections.sort(arrayList);
        answer = arrayList.stream().mapToInt(i->i).toArray();
        return answer;
    }
}
