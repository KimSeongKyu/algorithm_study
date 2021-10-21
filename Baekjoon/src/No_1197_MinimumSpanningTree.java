import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class No_1197_MinimumSpanningTree {

    private static Edge[] edges;
    private static int[] parents;

    private static void makeSet(int vertexCount) {
        parents = new int[vertexCount + 1];
        for(int i = 1; i <= vertexCount; i++) {
            parents[i] = i;
        }
    }

    private static int findSet(int vertex) {
        if(parents[vertex] == vertex) {
            return vertex;
        }
        return parents[vertex] = findSet(parents[vertex]);
    }

    private static boolean union(int vertex1, int vertex2) {
        int root1 = findSet(vertex1);
        int root2 = findSet(vertex2);

        if(root1 == root2) {
            return false;
        }

        parents[root1] = root2;
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(input.readLine());

        int vertexCount = Integer.valueOf(st.nextToken());
        int edgeCount = Integer.valueOf(st.nextToken());

        edges = new Edge[edgeCount];
        for(int i = 0; i < edgeCount; i++) {
            st = new StringTokenizer(input.readLine());
            int from = Integer.valueOf(st.nextToken());
            int to = Integer.valueOf(st.nextToken());
            int weight = Integer.valueOf(st.nextToken());
            edges[i] = new Edge(from, to, weight);
        }

        Arrays.sort(edges);

        int totalWeight = 0;
        int unionedVertexCount = 0;
        makeSet(vertexCount);
        for(Edge edge : edges) {
            if(union(edge.from, edge.to)) {
                totalWeight += edge.weight;
                if(++unionedVertexCount == vertexCount - 1) {
                    break;
                }
            }
        }

        System.out.println(totalWeight);
    }

    private static class Edge implements Comparable<Edge>{
        int from;
        int to;
        int weight;

        Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge other) {
            return Integer.compare(this.weight, other.weight);
        }
    }
}
