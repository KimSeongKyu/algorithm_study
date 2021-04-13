import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class No_1967_DiameterOfTree {

    static class Node {
        int no;
        int weight;

        Node(int no, int weight) {
            this.no = no;
            this.weight = weight;
        }
    }

    private static int farthestNode, maxDiameter;
    private static boolean[] isVisited;
    private static List<Node>[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        isVisited = new boolean[N+1];
        tree = new ArrayList[N+1];
        farthestNode = 0;
        maxDiameter = 0;
        for(int i = 1; i <= N; i++) tree[i] = new ArrayList<>();

        for(int i = 0; i < N-1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            tree[parent].add(new Node(child ,weight));
            tree[child].add(new Node(parent ,weight));
        }

        if(N == 1) System.out.println(0);
        else {
            DFS(1, 0);
            isVisited = new boolean[N+1];
            DFS(farthestNode, 0);

            System.out.println(maxDiameter);
        }
        br.close();
    }

    private static void DFS(int node, int weight) {
        isVisited[node] = true;

        if(weight > maxDiameter) {
            maxDiameter = weight;
            farthestNode = node;
        }

        for(Node child : tree[node]) {
            if(!isVisited[child.no]) {
                DFS(child.no, weight+child.weight);
            }
        }
    }
}
