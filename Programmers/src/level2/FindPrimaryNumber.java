package level2;

import java.util.ArrayList;

public class FindPrimaryNumber {
    private ArrayList<Integer> permutations = new ArrayList<>();

    public int solution(String numbers) {
        int answer = 0;
        int n = numbers.length();
        int[] numArr = new int[n];
        int[] permutation = new int[n];
        boolean[] isVisited = new boolean[n];

        for (int i = 0; i < n; i++)
            numArr[i] = numbers.charAt(i) - 48;

        for (int r = 1; r <= n; r++)
            permute(numArr, permutation, isVisited, 0, r);  // 순열 찾기

        for (int number : permutations) {
            if (number > 1 && isPrimary(number)) answer++;   // 소수 찾기
        }

        return answer;
    }

    public boolean isPrimary(int number) {
        for (int i = 2; i <= Math.sqrt(number); i++)
            if (number % i == 0) return false;
        return true;
    }

    public void permute(int[] numbers, int[] permutation, boolean[] isVisited, int current, int r) {
        if (r == current) {
            int number = translate(permutation);
            if (!permutations.contains(number)) permutations.add(number);
        } else {
            for (int i = 0; i < numbers.length; i++) {
                if (!isVisited[i]) {
                    isVisited[i] = true;
                    permutation[current] = numbers[i];
                    permute(numbers, permutation, isVisited, current + 1, r);
                    isVisited[i] = false;
                }
            }
        }
    }

    public int translate(int[] permutation) {
        int number = 0;
        for (int i = permutation.length - 1; i >= 0; i--) {
            number += permutation[i];
            number *= 10;
        }
        number /= 10;
        return number;
    }

}