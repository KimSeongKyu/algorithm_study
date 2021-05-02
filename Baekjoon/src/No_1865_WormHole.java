import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.StringTokenizer;

public class No_1865_WormHole {

    static class Node {
        int to;
        int weight;

        Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    private static BufferedReader br;
    private static StringTokenizer st;
    private static final int INFINITE = 500 * 10_000;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        int testCases = Integer.parseInt(br.readLine());

        for(int no = 1; no <= testCases; no++) {
            st = new StringTokenizer(br.readLine());
            int nodeCount = Integer.parseInt(st.nextToken());
            int roadCount = Integer.parseInt(st.nextToken());
            int wormHoleCount = Integer.parseInt(st.nextToken());

            boolean hasNegativeCycle = bellmanFord(nodeCount, roadCount, wormHoleCount);

            if(hasNegativeCycle) System.out.println("YES");
            else System.out.println("NO");
        }
    }

    private static boolean bellmanFord(int nodeCount, int roadCount, int wormHoleCount) throws IOException {
        List<Node>[] nodeList = new ArrayList[nodeCount+1];
        for(int i = 1; i <= nodeCount; i++) {
            nodeList[i] = new ArrayList<>();
        }

        int from, to, weight;
        for(int i = 0; i < roadCount+wormHoleCount; i++) {
            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());
            weight = Integer.parseInt(st.nextToken());
            if(i < roadCount) {
                nodeList[from].add(new Node(to, weight));
                nodeList[to].add(new Node(from, weight));
            } else {
                nodeList[from].add(new Node(to, -weight));
            }
        }

        int[] distance = new int[nodeCount+1];
        Arrays.fill(distance, INFINITE);
        distance[1] = 0;
        for(int count = 1; count <= nodeCount; count++) {
            for(int i = 1; i <= nodeCount; i++) {
                for(Node node : nodeList[i]) {
                    if(distance[node.to] > distance[i] + node.weight) {
                        if(count == nodeCount) return true;
                        distance[node.to] = distance[i] + node.weight;
                    }
                }
            }
        }

        return false;
    }
}
