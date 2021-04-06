import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class No_2644_CalculateKinship {
    private static void makeSet() {
        representatives[0] = -1;
        for(int i = 1; i <= n; i++) {
            representatives[i] = i;
        }
    }

    private static int findSet(int no) {
        if(representatives[no] == no) return no;
        return representatives[no] = findSet(representatives[no]);
    }

    private static boolean union(int no1, int no2) {
        int no1Root = findSet(no1);
        int no2Root = findSet(no2);

        if(no1Root == no2Root) return false;

        representatives[no2Root] = no1Root;
        return true;
    }

    private static ArrayList<Integer>[] kinship;
    private static boolean[] isVisited;
    private static int[] representatives;
    private static int n;
    private static int degreeOfKinship;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        degreeOfKinship = 0;

        n = Integer.parseInt(br.readLine());
        representatives = new int[n+1];
        kinship = new ArrayList[n+1];
        isVisited = new boolean[n+1];

        for (int i = 0; i <= n; i++) {
            kinship[i] = new ArrayList<>();
        }
        makeSet();

        st = new StringTokenizer(br.readLine());
        int no1 = Integer.parseInt(st.nextToken());
        int no2 = Integer.parseInt(st.nextToken());

        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());

            kinship[parent].add(child);
            kinship[child].add(parent);
            union(parent, child);
        }

        if(union(no1, no2)) System.out.println(-1);
        else {
            DFS(no1, no2, 0);
            System.out.println(degreeOfKinship);
        }
        br.close();
    }

    public static void DFS(int current, int target, int count) {
        if(current == target) {
            degreeOfKinship = count;
            return;
        }
        isVisited[current] = true;

        for(int next : kinship[current]) {
            if(!isVisited[next]){
                DFS(next, target, count+1);
            }
        }
    }
}
