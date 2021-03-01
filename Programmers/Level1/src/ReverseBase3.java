/*
문제 설명
자연수 n이 매개변수로 주어집니다.
n을 3진법 상에서 앞뒤로 뒤집은 후, 이를 다시 10진법으로 표현한 수를 return 하도록 solution 함수를 완성해주세요.

제한사항
n은 1 이상 100,000,000 이하인 자연수입니다.
 */

public class ReverseBase3 {

    class Solution {
        public int solution(int n) {
            int answer = 0;
            String base3 = "";

            // n을 3진법 수로 반전시켜 변환
            while(n != 0) {
                base3 += String.valueOf(n%3);
                n /= 3;
            }

            // 3진법 수를 10진법으로 변환
            for(int i = 0; i < base3.length(); i++) {
                answer += (Math.pow(3, i)*(base3.charAt(base3.length()-1-i)-48));
            }

            return answer;
        }
    }

}
