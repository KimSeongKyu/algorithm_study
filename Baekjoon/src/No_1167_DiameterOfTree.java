import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class No_1167_DiameterOfTree {

    private static int maxDistance;
    private static int farthestVertex;
    private static int vertexCount;
    private static List<Edge>[] edges;
    private static boolean[] isVisited;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        vertexCount = Integer.valueOf(input.readLine());
        edges = new ArrayList[vertexCount + 1];

        for (int i = 0; i < vertexCount; i++) {
            StringTokenizer st = new StringTokenizer(input.readLine());
            int from = Integer.valueOf(st.nextToken());
            edges[from] = new ArrayList<>();

            while (true) {
                int to = Integer.valueOf(st.nextToken());
                if (to == -1) {
                    break;
                }
                int distance = Integer.valueOf(st.nextToken());
                edges[from].add(new Edge(to, distance));
            }
        }

        isVisited = new boolean[vertexCount + 1];
        dfs(1, 0);

        maxDistance = 0;
        isVisited = new boolean[vertexCount + 1];
        dfs(farthestVertex, 0);

        System.out.println(maxDistance);

        input.close();
    }

    private static void dfs(int vertex, int distance) {
        isVisited[vertex] = true;
        if (distance > maxDistance) {
            maxDistance = distance;
            farthestVertex = vertex;
        }

        for (Edge edge : edges[vertex]) {
            if (!isVisited[edge.to]) {
                dfs(edge.to, edge.distance + distance);
            }
        }
    }

    private static class Edge {
        int to;
        int distance;

        Edge(int to, int distance) {
            this.to = to;
            this.distance = distance;
        }
    }
}
