import java.io.*;

public class No_1032_CommandPrompt {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));

        int fileCount = Integer.valueOf(input.readLine());
        char[][] files = new char[fileCount][];

        for(int i = 0; i < fileCount; i++) {
            files[i] = input.readLine().toCharArray();
        }

        StringBuilder result = new StringBuilder();
        int fileNameLength = files[0].length;

        LOOP:
        for(int j = 0; j < fileNameLength; j++) {
            char letter = files[0][j];
            for(int i = 1; i < fileCount; i++) {
                if(letter != files[i][j]) {
                    result.append("?");
                    continue LOOP;
                }
            }
            result.append(letter);
        }

        output.write(result.toString());
        output.newLine();

        input.close();
        output.flush();
        output.close();
    }
}
