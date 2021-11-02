import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class No_14621_LoveNotOnlyMe {

    private final static String MAN = "M";
    private static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(input.readLine());

        int schoolCount = Integer.valueOf(st.nextToken());
        int roadCount = Integer.valueOf(st.nextToken());

        boolean[] isMan = new boolean[schoolCount + 1];
        st = new StringTokenizer(input.readLine());
        for (int i = 1; i <= schoolCount; i++) {
            if (st.nextToken().equals(MAN)) {
                isMan[i] = true;
            }
        }

        List<Road> roads = new ArrayList<>();
        for (int i = 0; i < roadCount; i++) {
            st = new StringTokenizer(input.readLine());
            int from = Integer.valueOf(st.nextToken());
            int to = Integer.valueOf(st.nextToken());
            int distance = Integer.valueOf(st.nextToken());

            if ((isMan[from] && isMan[to]) || (!isMan[from] && !isMan[to])) {
                continue;
            }

            roads.add(new Road(from, to, distance));
        }

        makeSet(schoolCount);
        Collections.sort(roads);

        int totalDistance = 0;
        int connectedRoadCount = 0;
        for (Road road : roads) {
            if (union(road.from, road.to)) {
                totalDistance += road.distance;
                if (++connectedRoadCount == schoolCount - 1) {
                    break;
                }
            }
        }

        if (connectedRoadCount != schoolCount - 1) {
            System.out.println(-1);
        } else {
            System.out.println(totalDistance);
        }
    }

    private static void makeSet(int schoolCount) {
        parents = new int[schoolCount + 1];
        for (int i = 1; i <= schoolCount; i++) {
            parents[i] = i;
        }
    }

    private static int findSet(int i) {
        if (parents[i] == i) {
            return i;
        }
        return parents[i] = findSet(parents[i]);
    }

    private static boolean union(int a, int b) {
        int rootA = findSet(a);
        int rootB = findSet(b);

        if (rootA == rootB) {
            return false;
        }

        parents[rootA] = rootB;
        return true;
    }

    private static class Road implements Comparable<Road> {
        int from;
        int to;
        int distance;

        Road(int from, int to, int distance) {
            this.from = from;
            this.to = to;
            this.distance = distance;
        }

        @Override
        public int compareTo(Road other) {
            return this.distance - other.distance;
        }
    }
}
