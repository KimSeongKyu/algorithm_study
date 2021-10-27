import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class No_1062_Teaching {

    private static boolean[] isTeachableAlphabet = new boolean[26];

    private static int maxTeachableWordCount = 0;
    private static int wordCount;
    private static int teachableAlphabetCount;
    private static char[][] words;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(input.readLine());

        wordCount = Integer.valueOf(st.nextToken());
        teachableAlphabetCount = Integer.valueOf(st.nextToken()) - 5;

        if (teachableAlphabetCount < 0) {
            System.out.println(0);
            input.close();
            return;
        }

        isTeachableAlphabet['a' - 'a'] = true;
        isTeachableAlphabet['c' - 'a'] = true;
        isTeachableAlphabet['i' - 'a'] = true;
        isTeachableAlphabet['n' - 'a'] = true;
        isTeachableAlphabet['t' - 'a'] = true;

        words = new char[wordCount][];
        for (int i = 0; i < wordCount; i++) {
            words[i] = input.readLine().toCharArray();
        }

        combination(0, 0);
        System.out.println(maxTeachableWordCount);
        input.close();
    }

    private static void combination(int step, int start) {
        if (step == teachableAlphabetCount) {
            int teachableWordCount = 0;

            LOOP:
            for (int i = 0; i < wordCount; i++) {
                int wordLength = words[i].length;
                for (int j = 0; j < wordLength; j++) {
                    int indexOfLetter = words[i][j] - 'a';
                    if (!isTeachableAlphabet[indexOfLetter]) {
                        continue LOOP;
                    }
                }

                ++teachableWordCount;
            }

            maxTeachableWordCount = Math.max(maxTeachableWordCount, teachableWordCount);
            return;
        }

        for (int i = start; i < 26; i++) {
            char alphabet = (char) (i + 'a');
            if (alphabet == 'a' || alphabet == 'c' || alphabet == 'i' || alphabet == 'n' || alphabet == 't') {
                continue;
            }

            isTeachableAlphabet[i] = true;
            combination(step + 1, i + 1);
            isTeachableAlphabet[i] = false;
        }
    }
}
