import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class No_19535_Dududunga {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        boolean[] isVisited = new boolean[N+1];
        List<Integer>[] tree = new ArrayList[N+1];
        for(int i = 1; i <= N; i++) tree[i] = new ArrayList<>();

        for(int i = 0; i < N-1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());

            tree[node1].add(node2);
            tree[node2].add(node1);
        }

        long DCount = 0L;
        long GCount = 0L;
        for(int node = 1; node <= N; node++) {
            isVisited[node] = true;

            long edgeCount = tree[node].size();
            if(edgeCount >= 2) {
                for(int connectedNode : tree[node]) {
                    if(isVisited[connectedNode]) continue;

                    long connectedEdgeCount = tree[connectedNode].size();
                    if(connectedEdgeCount >= 2) {
                        DCount += (edgeCount-1) * (connectedEdgeCount-1);
                    }
                }
            }
            if(edgeCount >= 3) {
                GCount += (edgeCount * (edgeCount-1) * (edgeCount-2) / 6);
            }
        }

        if(DCount == GCount*3) {
            System.out.println("DUDUDUNGA");
        }
        else if(DCount > GCount*3) {
            System.out.println("D");
        }
        else {
            System.out.println("G");
        }

        br.close();
    }
}
