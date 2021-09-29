import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class No_2644_CalculateKinship {

    private static int totalKinship = 0;
    private static List<Integer>[] relations;
    private static boolean[] isVisited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int peopleCount = Integer.parseInt(br.readLine());
        relations = new ArrayList[peopleCount + 1];
        isVisited = new boolean[peopleCount + 1];

        for (int i = 0; i <= peopleCount; i++) {
            relations[i] = new ArrayList<>();
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int person1 = Integer.parseInt(st.nextToken());
        int person2 = Integer.parseInt(st.nextToken());

        int relationCount = Integer.parseInt(br.readLine());
        for (int i = 0; i < relationCount; i++) {
            st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());

            relations[parent].add(child);
            relations[child].add(parent);
        }

        DFS(person1, person2, 0);

        if (totalKinship == 0) {
            System.out.println(-1);
        } else {
            System.out.println(totalKinship);
        }

        br.close();
    }

    public static void DFS(int currentPerson, int targetPerson, int kinship) {
        if (currentPerson == targetPerson) {
            totalKinship = kinship;
            return;
        }
        isVisited[currentPerson] = true;

        for (int next : relations[currentPerson]) {
            if (!isVisited[next]) {
                DFS(next, targetPerson, kinship + 1);
            }
        }
    }
}
