import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class No_1339_WordMath {

    private static String[] words;
    private static int[] usedWords;
    private static int[] sequence = new int[26];
    private static boolean[] isVisited = new boolean[10];
    private static int usedWordCount;
    private static int maxSum = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        Set<Integer> usedWordSet = new HashSet<>();
        int wordCount = Integer.valueOf(input.readLine());
        words = new String[wordCount];
        for (int i = 0; i < wordCount; i++) {
            words[i] = input.readLine();
            int wordLength = words[i].length();
            for (int j = 0; j < wordLength; j++) {
                usedWordSet.add(words[i].charAt(j) - 'A');
            }
        }

        usedWordCount = usedWordSet.size();
        usedWords = usedWordSet.stream()
                .mapToInt(Integer::intValue)
                .toArray();

        permutation(0);
        System.out.println(maxSum);

        input.close();
    }

    private static void permutation(int index) {
        if (index == usedWordCount) {
            int sum = 0;
            for(String word : words) {
                int wordLength = word.length();
                int digit = 1;
                for(int i = wordLength - 1; i >= 0; i--) {
                    int sequenceIndex = word.charAt(i) - 'A';
                    sum += sequence[sequenceIndex] * digit;
                    digit *= 10;
                }
            }
            maxSum = Math.max(maxSum, sum);
            return;
        }

        for (int i = 0; i < 10; i++) {
            if (isVisited[i]) {
                continue;
            }

            sequence[usedWords[index]] = i;
            isVisited[i] = true;
            permutation(index + 1);
            sequence[usedWords[index]] = 0;
            isVisited[i] = false;
        }
    }
}
