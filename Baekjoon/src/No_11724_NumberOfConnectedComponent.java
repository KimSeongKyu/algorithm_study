import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class No_11724_NumberOfConnectedComponent {

    private static int vertexCount;
    private static int edgeCount;
    private static boolean[] isVisited;
    private static List<Integer>[] connectedVertices;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(input.readLine());

        vertexCount = Integer.valueOf(st.nextToken());
        edgeCount = Integer.valueOf(st.nextToken());

        isVisited = new boolean[vertexCount + 1];
        connectedVertices = new ArrayList[vertexCount + 1];
        for (int i = 1; i <= vertexCount; i++) {
            connectedVertices[i] = new ArrayList<>();
        }

        for (int i = 0; i < edgeCount; i++) {
            st = new StringTokenizer(input.readLine());
            int from = Integer.valueOf(st.nextToken());
            int to = Integer.valueOf(st.nextToken());
            connectedVertices[from].add(to);
            connectedVertices[to].add(from);
        }

        int numberOfConnectedComponent = 0;
        for (int i = 1; i <= vertexCount; i++) {
            if (isVisited[i]) {
                continue;
            }

            dfs(i);
            ++numberOfConnectedComponent;
        }

        System.out.println(numberOfConnectedComponent);
    }

    private static void dfs(int vertex) {
        if (isVisited[vertex]) {
            return;
        }

        isVisited[vertex] = true;
        for (int connectedVertex : connectedVertices[vertex]) {
            dfs(connectedVertex);
        }
    }
}
