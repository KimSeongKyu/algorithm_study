import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class No_11725_FindParentOfTree {
    private static boolean[] isVisited;
    private static List<Integer>[] edges;
    private static int[] parent;
    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        isVisited = new boolean[N+1];
        parent = new int[N+1];
        edges = new ArrayList[N+1];
        for(int i = 1; i <= N; i++) edges[i] = new ArrayList<>();

        for(int i = 0; i < N-1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());

            edges[node1].add(node2);
            edges[node2].add(node1);
        }

        DFS(1);

        for(int i = 2; i <= N; i++) {
            bw.write(String.valueOf(parent[i]));
            bw.newLine();
        }

        br.close();
        bw.flush();
        bw.close();
    }

    private static void DFS(int node) {
        isVisited[node] = true;

        for(int child : edges[node]) {
            if(!isVisited[child]) {
                parent[child] = node;
                DFS(child);
            }
        }
    }
}
