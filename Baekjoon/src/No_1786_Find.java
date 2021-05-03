import java.io.*;

public class No_1786_Find {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        String text = br.readLine();
        String pattern = br.readLine();

        int textLength = text.length();
        int patternLength = pattern.length();

        int[] failFunction = createFailFunction(pattern);

        int patternMatchingCount = 0;

        int j = 0;
        for(int i = 0; i < textLength; i++) {
            while(j > 0 && text.charAt(i) != pattern.charAt(j)) {
                j = failFunction[j-1];
            }

            if(text.charAt(i) == pattern.charAt(j)) {
                if (j == patternLength-1) {
                    sb.append((i+1-(patternLength-1)) + " ");
                    ++patternMatchingCount;
                    j = failFunction[j];
                } else {
                    ++j;
                }
            }
        }

        bw.write(String.valueOf(patternMatchingCount));
        bw.newLine();
        bw.write(sb.toString());
        bw.newLine();

        br.close();
        bw.flush();
        bw.close();
    }

    private static int[] createFailFunction(String pattern) {
        int patternLength = pattern.length();
        int[] failFunction = new int[patternLength];

        int j = 0;
        for(int i = 1; i < patternLength; i++) {
            while(j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = failFunction[j-1];
            }

            if(pattern.charAt(i) == pattern.charAt(j)) {
                failFunction[i] = ++j;
            }
        }

        return failFunction;
    }
}
