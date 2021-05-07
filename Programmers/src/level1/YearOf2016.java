package level1;

public class YearOf2016 {
    public String solution(int a, int b) {
        String answer = "";
        String[] date = {"THU", "FRI", "SAT", "SUN", "MON", "TUE", "WED"};
        int today = (a - 1) * 31 + b;

        switch (a) {
            case 12:
                today -= 1;
            case 11:
            case 10:
                today -= 1;
            case 9:
            case 8:
            case 7:
                today -= 1;
            case 6:
            case 5:
                today -= 1;
            case 4:
            case 3:
                today -= 2;
            default:
                break;
        }

        today %= 7;

        answer = date[today];

        return answer;
    }
}