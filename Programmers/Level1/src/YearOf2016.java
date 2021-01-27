/*
문제 설명
2016년 1월 1일은 금요일입니다. 2016년 a월 b일은 무슨 요일일까요?
두 수 a ,b를 입력받아 2016년 a월 b일이 무슨 요일인지 리턴하는 함수, solution을 완성하세요.
요일의 이름은 일요일부터 토요일까지 각각 SUN,MON,TUE,WED,THU,FRI,SAT입니다.
예를 들어 a=5, b=24라면 5월 24일은 화요일이므로 문자열 TUE를 반환하세요.

제한 조건
2016년은 윤년입니다.
2016년 a월 b일은 실제로 있는 날입니다. (13월 26일이나 2월 45일같은 날짜는 주어지지 않습니다)
 */

public class YearOf2016 {

    public static void main(String[] args) {
        int a = 5, b = 24;
        String answer = "TUE";

        if(solution(a, b).equals(answer))
            System.out.println("Correct");
        else
            System.out.println("Wrong");
    }

    public static String solution(int a, int b) {
        String answer = "";
        // 1월1일의 today = 1이므로 "FRI"는 index 1부터 시작
        String[] date = {"THU","FRI","SAT","SUN","MON","TUE","WED"};
        int today = (a-1)*31 + b;   // 오늘 날짜를 '일'로 계산

        if(a < 8) today -= ((a-1)/2+1); // 4, 6월: 30일
        else today -= ((a-2)/2+1);  // 9, 11월: 30일

        if(a <= 2) today++; // 위의 연산 결과 1월이 30일로 계산됨

        /*
        Enhanced Switch
        switch(a) {
            case 12: today -= 1;
            case 11, 10: today -= 1;
            case 9, 8, 7: today -= 1;
            case 6, 5: today -= 1;
            case 4, 3: today -= 2;
            default: break;
        }
        */

        today %= 7; // 요일 계산
        answer = date[today];

        return answer;
    }
}
