import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class No_16167_AGreatWay {

    private final static int INFINITE = 1_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(input.readLine());

        int end = Integer.valueOf(st.nextToken());
        int wayCount = Integer.valueOf(st.nextToken());

        List<Way>[] ways = new ArrayList[end + 1];
        for (int i = 1; i <= end; i++) {
            ways[i] = new ArrayList<>();
        }

        for (int i = 0; i < wayCount; i++) {
            st = new StringTokenizer(input.readLine());
            int from = Integer.valueOf(st.nextToken());
            int to = Integer.valueOf(st.nextToken());
            int defaultCost = Integer.valueOf(st.nextToken());
            int extraCost = Integer.valueOf(st.nextToken());
            int time = Integer.valueOf(st.nextToken());
            int extraTime = time - 10 > 0 ? time - 10 : 0;
            int totalCost = defaultCost + extraCost * extraTime;

            ways[from].add(new Way(to, totalCost));
        }

        boolean[] isVisited = new boolean[end + 1];
        int[][] minCosts = new int[end + 1][2];
        for (int i = 1; i <= end; i++) {
            Arrays.fill(minCosts[i], INFINITE);
        }

        PriorityQueue<Way> nextWays = new PriorityQueue<>();
        nextWays.offer(new Way(1, 0));
        minCosts[1][0] = 0;
        minCosts[1][1] = 1;

        while (!nextWays.isEmpty()) {
            Way way = nextWays.poll();

            if (!isVisited[way.to]) {
                isVisited[way.to] = true;

                for (Way connectedWay : ways[way.to]) {
                    if (!isVisited[connectedWay.to] && minCosts[connectedWay.to][0] >= minCosts[way.to][0] + connectedWay.cost) {
                        if(minCosts[connectedWay.to][0] == minCosts[way.to][0] + connectedWay.cost) {
                            minCosts[connectedWay.to][1] = Math.min(minCosts[connectedWay.to][1], minCosts[way.to][1] + 1);
                        } else {
                            minCosts[connectedWay.to][1] = minCosts[way.to][1] + 1;
                        }

                        minCosts[connectedWay.to][0] = minCosts[way.to][0] + connectedWay.cost;
                        nextWays.offer(new Way(connectedWay.to, minCosts[connectedWay.to][0]));
                    }
                }
            }
        }

        if (minCosts[end][0] != INFINITE) {
            System.out.println(minCosts[end][0] + " " + minCosts[end][1]);
        } else {
            System.out.println("It is not a great way.");
        }

        input.close();
    }

    private static class Way implements Comparable<Way> {
        int to;
        int cost;

        Way(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Way other) {
            return this.cost - other.cost;
        }
    }
}
