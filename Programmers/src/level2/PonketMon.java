package level2;

import java.util.Arrays;

public class PonketMon {
    public int solution(int[] nums) {
        int answer = 1;
        int max = nums.length / 2;
        int type;

        Arrays.sort(nums);
        type = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == type) continue;
            if (answer == max) break;
            type = nums[i];
            answer++;
        }

        return answer;
    }
}