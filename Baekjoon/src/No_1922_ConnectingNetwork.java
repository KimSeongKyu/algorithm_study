import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class No_1922_ConnectingNetwork {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        int vertexCount = Integer.valueOf(input.readLine());
        int edgeCount = Integer.valueOf(input.readLine());

        int[][] adjMatrix = new int[vertexCount + 1][vertexCount + 1];

        for (int i = 0; i < edgeCount; i++) {
            StringTokenizer st = new StringTokenizer(input.readLine());
            int from = Integer.valueOf(st.nextToken());
            int to = Integer.valueOf(st.nextToken());
            int weight = Integer.valueOf(st.nextToken());
            adjMatrix[from][to] = weight;
            adjMatrix[to][from] = weight;
        }

        boolean[] isVisited = new boolean[vertexCount + 1];
        int[] minDistances = new int[vertexCount + 1];
        Arrays.fill(minDistances, 10_001);

        int totalWeight = 0;
        minDistances[1] = 0;
        for (int from = 1; from <= vertexCount; from++) {
            int minWeight = 10_001;
            int minVertex = 0;

            for (int to = 1; to <= vertexCount; to++) {
                if (!isVisited[to] && minWeight > minDistances[to]) {
                    minWeight = minDistances[to];
                    minVertex = to;
                }
            }

            totalWeight += minWeight;
            isVisited[minVertex] = true;

            for (int to = 1; to <= vertexCount; to++) {
                if (!isVisited[to] && adjMatrix[minVertex][to] != 0 && minDistances[to] > adjMatrix[minVertex][to]) {
                    minDistances[to] = adjMatrix[minVertex][to];
                }
            }
        }

        System.out.println(totalWeight);
    }
}
