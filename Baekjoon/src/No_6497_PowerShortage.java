import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class No_6497_PowerShortage {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            StringTokenizer st = new StringTokenizer(input.readLine());
            int vertexCount = Integer.valueOf(st.nextToken());
            int edgeCount = Integer.valueOf(st.nextToken());

            if (vertexCount == 0 && edgeCount == 0) {
                return;
            }

            List<Vertex>[] connectedVertices = new ArrayList[vertexCount];
            for (int i = 0; i < vertexCount; i++) {
                connectedVertices[i] = new ArrayList<>();
            }

            int totalWeight = 0;
            for (int i = 0; i < edgeCount; i++) {
                st = new StringTokenizer(input.readLine());
                int from = Integer.valueOf(st.nextToken());
                int to = Integer.valueOf(st.nextToken());
                int weight = Integer.valueOf(st.nextToken());
                connectedVertices[from].add(new Vertex(to, weight));
                connectedVertices[to].add(new Vertex(from, weight));
                totalWeight += weight;
            }

            int totalMinWeight = 0;
            boolean[] isVisited = new boolean[vertexCount];
            PriorityQueue<Vertex> vertices = new PriorityQueue<>();
            vertices.offer(new Vertex(0, 0));

            while (!vertices.isEmpty()) {
                Vertex vertex = vertices.poll();

                if (isVisited[vertex.no]) {
                    continue;
                }

                isVisited[vertex.no] = true;
                totalMinWeight += vertex.weight;

                for (Vertex connectedVertex : connectedVertices[vertex.no]) {
                    if (!isVisited[connectedVertex.no]) {
                        vertices.offer(connectedVertex);
                    }
                }
            }

            System.out.println(totalWeight - totalMinWeight);
        }
    }

    private static class Vertex implements Comparable<Vertex> {
        int no;
        int weight;

        Vertex(int no, int weight) {
            this.no = no;
            this.weight = weight;
        }

        @Override
        public int compareTo(Vertex other) {
            return this.weight - other.weight;
        }
    }
}
