import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class No_1068_Tree {

    private static Node[] nodes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int nodeCount = Integer.valueOf(br.readLine());
        nodes = new Node[nodeCount];
        for (int i = 0; i < nodeCount; i++) {
            nodes[i] = new Node();
        }

        int[] parentIndices = new int[nodeCount];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < nodeCount; i++) {
            int parent = Integer.valueOf(st.nextToken());
            parentIndices[i] = parent;
            nodes[i].no = i;
        }

        for (int i = 0; i < nodeCount; i++) {
            if (parentIndices[i] != -1) {
                Node parent = nodes[parentIndices[i]];
                Node child = nodes[i];
                nodes[i].parent = parent;
                parent.children.add(child);
            }
        }

        int removedNodeIndex = Integer.valueOf(br.readLine());
        Node removedNode = nodes[removedNodeIndex];

        removeChildren(removedNode);
        if(removedNode.parent != null) {
            removedNode.parent.children.remove(removedNode);
        }

        int leafNodeCount = 0;
        for (int i = 0; i < nodeCount; i++) {
            if (nodes[i] != null && nodes[i].children.size() == 0) {
                leafNodeCount++;
            }
        }
        System.out.println(leafNodeCount);

        br.close();
    }

    public static void removeChildren(Node node) {
        node.children.stream()
                .forEach(child -> removeChildren(child));
        nodes[node.no] = null;
    }

    static class Node {
        int no;
        Node parent;
        List<Node> children = new ArrayList<>();
    }
}