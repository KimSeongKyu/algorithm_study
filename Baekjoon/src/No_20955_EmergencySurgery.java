import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class No_20955_EmergencySurgery {

    private static List<Integer>[] tree;
    private static boolean[] isVisited, isCyclable;
    private static int add, cut;
    private static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        isVisited = new boolean[N+1];
        isCyclable = new boolean[N+1];
        Arrays.fill(isCyclable, true);

        tree = new ArrayList[N+1];
        for(int i = 1; i <= N; i++) tree[i] = new ArrayList<>();

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());

            tree[node1].add(node2);
            tree[node2].add(node1);
        }

        add = -1;
        cut = 0;

        for(int node = 1; node <= N; node++) {
            if(!isVisited[node]) {
                ++add;
                DFS(node, 0);
            }
        }

        System.out.println(add+cut);
    }

    private static void DFS(int node, int parent) {
        isVisited[node] = true;

        for(int child : tree[node]) {
            if(!isVisited[child]) {
                DFS(child, node);
            }
            else if(parent != child && isCyclable[child]) {
                ++cut;
            }
        }

        isCyclable[node] = false;
    }
}
