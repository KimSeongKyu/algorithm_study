/*
문제 설명
자연수 n이 주어졌을 때, n의 다음 큰 숫자는 다음과 같이 정의 합니다.

조건 1. n의 다음 큰 숫자는 n보다 큰 자연수 입니다.
조건 2. n의 다음 큰 숫자와 n은 2진수로 변환했을 때 1의 갯수가 같습니다.
조건 3. n의 다음 큰 숫자는 조건 1, 2를 만족하는 수 중 가장 작은 수 입니다.
예를 들어서 78(1001110)의 다음 큰 숫자는 83(1010011)입니다.

자연수 n이 매개변수로 주어질 때, n의 다음 큰 숫자를 return 하는 solution 함수를 완성해주세요.

제한 사항
n은 1,000,000 이하의 자연수 입니다.
 */

public class NextBiggerDigit {
    class Solution {
        public int solution(int n) {
            String number = Integer.toBinaryString(n);
            int ones = counts(number, '1');             // 숫자 n을 2진수로 바꿨을 때 1의 개수
            do {
                number = Integer.toBinaryString(++n);      // 숫자 n 증가
            } while(ones != counts(number, '1'));       // n과 증가된 숫자의 1의 수가 같을 때 까지 반복

            return toInt(number);                           // 10진수로 변환
        }

        public int toInt(String s) {
            int number = 0;
            int bit = 1;
            for(int i = s.length()-1; i >= 0; i--) {
                if(s.charAt(i) == '1') number += bit;
                bit *= 2;
            }
            return number;
        }

        public int counts(String s, char c) {
            int count = 0;
            for(int i = 0; i < s.length(); i++) {
                if(s.charAt(i) == c) count++;
            }
            return count;
        }
    }
}
