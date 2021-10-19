import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class No_1504_SpecificShortestPath {

    private final static int INFINITE = 200_000_000;

    private static int vertexCount, edgeCount;
    private static List<Vertex>[] connectedVertices;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(input.readLine());

        vertexCount = Integer.valueOf(st.nextToken());
        edgeCount = Integer.valueOf(st.nextToken());

        connectedVertices = new List[vertexCount + 1];
        for(int i = 1; i <= vertexCount; i++) {
            connectedVertices[i] = new ArrayList<>();
        }

        for(int i = 0; i < edgeCount; i++) {
            st = new StringTokenizer(input.readLine());
            int from = Integer.valueOf(st.nextToken());
            int to = Integer.valueOf(st.nextToken());
            int distance = Integer.valueOf(st.nextToken());

            connectedVertices[from].add(new Vertex(to, distance));
            connectedVertices[to].add(new Vertex(from, distance));
        }

        st = new StringTokenizer(input.readLine());
        int v1 = Integer.valueOf(st.nextToken());
        int v2 = Integer.valueOf(st.nextToken());

        int shortestPathFromV1ToV2 = dijkstra(1, v1) + dijkstra(v1, v2) + dijkstra(v2, vertexCount);
        int shortestPathFromV2ToV1 = dijkstra(1, v2) + dijkstra(v2, v1) + dijkstra(v1, vertexCount);

        int shortestPath = shortestPathFromV1ToV2 >= INFINITE && shortestPathFromV2ToV1 >= INFINITE
                ? -1 : Math.min(shortestPathFromV1ToV2, shortestPathFromV2ToV1);

        System.out.println(shortestPath);
        input.close();
    }

    private static int dijkstra(int v1, int v2) {
        boolean[] isVisited = new boolean[vertexCount + 1];
        int[] distances = new int[vertexCount + 1];
        Arrays.fill(distances, INFINITE);
        PriorityQueue<Vertex> vertices = new PriorityQueue<>();

        vertices.offer(new Vertex(v1, 0));
        distances[v1] = 0;

        while(!vertices.isEmpty()) {
            Vertex vertex = vertices.poll();

            if(!isVisited[vertex.no]) {
                isVisited[vertex.no] = true;

                for(Vertex connectedVertex : connectedVertices[vertex.no]) {
                    if(!isVisited[connectedVertex.no] &&
                            distances[connectedVertex.no] > distances[vertex.no] + connectedVertex.distance) {
                        distances[connectedVertex.no] = distances[vertex.no] + connectedVertex.distance;
                        vertices.offer(new Vertex(connectedVertex.no, distances[connectedVertex.no]));
                    }
                }
            }
        }

        return distances[v2];
    }

    private static class Vertex implements Comparable<Vertex> {
        int no;
        int distance;

        Vertex(int no, int distance) {
            this.no = no;
            this.distance = distance;
        }

        @Override
        public int compareTo(Vertex other) {
            return this.distance - other.distance;
        }
    }
}
