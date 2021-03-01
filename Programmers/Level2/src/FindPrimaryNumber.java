/*
문제 설명
한자리 숫자가 적힌 종이 조각이 흩어져있습니다.
흩어진 종이 조각을 붙여 소수를 몇 개 만들 수 있는지 알아내려 합니다.

각 종이 조각에 적힌 숫자가 적힌 문자열 numbers가 주어졌을 때,
종이 조각으로 만들 수 있는 소수가 몇 개인지 return 하도록 solution 함수를 완성해주세요.

제한사항
numbers는 길이 1 이상 7 이하인 문자열입니다.
numbers는 0~9까지 숫자만으로 이루어져 있습니다.
"013"은 0, 1, 3 숫자가 적힌 종이 조각이 흩어져있다는 의미입니다.
 */

import java.util.ArrayList;

public class FindPrimaryNumber {
    class Solution {
        private ArrayList<Integer> permutations = new ArrayList<>();

        public int solution(String numbers) {
            int answer = 0;
            int n = numbers.length();
            int[] numArr = new int[n];
            int[] permutation = new int[n];
            boolean[] isVisited = new boolean[n];

            for(int i = 0; i < n; i++)
                numArr[i] = numbers.charAt(i)-48;

            for(int r = 1; r <= n; r++)
                permute(numArr, permutation, isVisited, 0, r);  // 순열 찾기

            for(int number : permutations) {
                if(number > 1 && isPrimary(number)) answer++;   // 소수 찾기
            }

            return answer;
        }

        public boolean isPrimary(int number) {
            for(int i = 2; i <= Math.sqrt(number); i++)
                if(number % i == 0) return false;
            return true;
        }

        public void permute(int[] numbers, int[] permutation, boolean[] isVisited, int current, int r) {
            if(r == current) {
                int number = translate(permutation);
                if(!permutations.contains(number)) permutations.add(number);
            }
            else {
                for(int i = 0; i < numbers.length; i++) {
                    if(!isVisited[i]) {
                        isVisited[i] = true;
                        permutation[current] = numbers[i];
                        permute(numbers, permutation, isVisited, current+1, r);
                        isVisited[i] = false;
                    }
                }
            }
        }

        public int translate(int[] permutation) {
            int number = 0;
            for(int i = permutation.length-1; i >= 0; i--) {
                number += permutation[i];
                number *= 10;
            }
            number /= 10;
            return number;
        }
    }
}
