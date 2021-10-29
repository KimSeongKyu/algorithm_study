import java.io.*;
import java.util.StringTokenizer;

public class No_9466_TeamProject {

    private static int studentCountInTeam;
    private static int[] studentWantWith;
    private static boolean[] isVisited;
    private static boolean[] isInCycle;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCaseCount = Integer.valueOf(input.readLine());
        for (int no = 1; no <= testCaseCount; no++) {
            int studentCount = Integer.valueOf(input.readLine());
            studentCountInTeam = 0;
            studentWantWith = new int[studentCount + 1];
            isVisited = new boolean[studentCount + 1];
            isInCycle = new boolean[studentCount + 1];

            StringTokenizer st = new StringTokenizer(input.readLine());
            for (int i = 1; i <= studentCount; i++) {
                studentWantWith[i] = Integer.valueOf(st.nextToken());
            }

            for (int i = 1; i <= studentCount; i++) {
                dfs(i);
            }

            output.write(String.valueOf(studentCount - studentCountInTeam));
            output.newLine();
        }

        input.close();
        output.flush();
        output.close();
    }

    private static void dfs(int student) {
        if (isVisited[student]) {
            return;
        }

        isVisited[student] = true;

        int nextStudent = studentWantWith[student];
        if(!isVisited[nextStudent]) {
            dfs(nextStudent);
        } else if(!isInCycle[nextStudent]) {
            ++studentCountInTeam;
            for(int teamMember = nextStudent; teamMember != student; teamMember = studentWantWith[teamMember]) {
                ++studentCountInTeam;
            }
        }

        isInCycle[student] = true;
    }
}
