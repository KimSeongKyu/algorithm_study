import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class No_5052_ListOfPhoneNumber {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCases = Integer.valueOf(br.readLine());
        LOOP:
        for (int no = 1; no <= testCases; no++) {
            List<String> phoneNumbers = new ArrayList<>();
            int phoneNumberCount = Integer.valueOf(br.readLine());
            for (int i = 0; i < phoneNumberCount; i++) {
                phoneNumbers.add(br.readLine());
            }

            Collections.sort(phoneNumbers);

            for (int i = 0; i < phoneNumberCount - 1; i++) {
                if (phoneNumbers.get(i + 1).startsWith(phoneNumbers.get(i))) {
                    bw.write("NO");
                    bw.newLine();
                    continue LOOP;
                }
            }

            bw.write("YES");
            bw.newLine();
        }

        br.close();
        bw.flush();
        bw.close();
    }
}
