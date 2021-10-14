import java.io.*;
import java.util.*;

public class No_1260_DfsAndBfs {

    private static boolean[] isVisited;
    private static List<Integer>[] graph;
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int vertexCount = Integer.valueOf(st.nextToken());
        int edgeCount = Integer.valueOf(st.nextToken());
        int startVertex = Integer.valueOf(st.nextToken());

        graph = new ArrayList[vertexCount + 1];
        for(int i = 1; i <= vertexCount; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i < edgeCount; i++) {
            st = new StringTokenizer(br.readLine());
            int vertex1 = Integer.valueOf(st.nextToken());
            int vertex2 = Integer.valueOf(st.nextToken());
            graph[vertex1].add(vertex2);
            graph[vertex2].add(vertex1);
        }

        for(int i = 1; i <= vertexCount; i++) {
            Collections.sort(graph[i]);
        }

        isVisited = new boolean[vertexCount + 1];
        isVisited[startVertex] = true;
        dfs(startVertex);
        bw.newLine();
        bw.flush();

        isVisited = new boolean[vertexCount + 1];
        bfs(startVertex);
        bw.flush();
        bw.close();
    }


    private static void dfs(int vertex) throws IOException {
        bw.write(vertex + " ");
        isVisited[vertex] = true;

        for(int connectedVertex : graph[vertex]) {
            if(!isVisited[connectedVertex]) {
                dfs(connectedVertex);
            }
        }
    }

    private static void bfs(int startVertex) throws IOException{
        Queue<Integer> queue = new LinkedList<>();

        queue.offer(startVertex);
        isVisited[startVertex] = true;

        while(!queue.isEmpty()) {
            int vertex = queue.poll();
            bw.write(vertex + " ");

            for(int connectedVertex : graph[vertex]) {
                if(!isVisited[connectedVertex]) {
                    queue.offer(connectedVertex);
                    isVisited[connectedVertex] = true;
                }
            }
        }

        bw.newLine();
    }
}
