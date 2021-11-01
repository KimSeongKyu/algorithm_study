import java.io.*;
import java.util.*;

public class No_11779_CalculateMinimumCost2 {

    private final static int INFINITE = 100_000 * 1_000 + 1;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));

        int cityCount = Integer.valueOf(input.readLine());
        int busCount = Integer.valueOf(input.readLine());

        List<Way>[] connectedWays = new ArrayList[cityCount + 1];
        for(int i = 1; i <= cityCount; i++) {
            connectedWays[i] = new ArrayList<>();
        }

        for(int i = 0; i < busCount; i++) {
            StringTokenizer st = new StringTokenizer(input.readLine());
            int from = Integer.valueOf(st.nextToken());
            int to = Integer.valueOf(st.nextToken());
            int cost = Integer.valueOf(st.nextToken());
            connectedWays[from].add(new Way(to, cost));
        }

        StringTokenizer st = new StringTokenizer(input.readLine());
        int start = Integer.valueOf(st.nextToken());
        int end = Integer.valueOf(st.nextToken());

        int[] costs = new int[cityCount + 1];
        int[] nearestCities = new int[cityCount + 1];
        boolean[] isVisited = new boolean[cityCount + 1];
        Arrays.fill(costs, INFINITE);

        PriorityQueue<Way> ways = new PriorityQueue<>();
        ways.offer(new Way(start, 0));
        costs[start] = 0;

        while(!ways.isEmpty()) {
            Way way = ways.poll();

            if(!isVisited[way.to]) {
                isVisited[way.to] = true;

                for(Way connectedWay : connectedWays[way.to]) {
                    if(!isVisited[connectedWay.to] && costs[connectedWay.to] > costs[way.to] + connectedWay.cost) {
                        costs[connectedWay.to] = costs[way.to] + connectedWay.cost;
                        nearestCities[connectedWay.to] = way.to;
                        ways.offer(new Way(connectedWay.to, costs[connectedWay.to]));
                    }
                }
            }
        }

        ArrayList<Integer> routes = new ArrayList<>();
        for(int city = end; city != 0; city = nearestCities[city]) {
            routes.add(city);
        }

        output.write(String.valueOf(costs[end]));
        output.newLine();
        output.write(String.valueOf(routes.size()));
        output.newLine();

        int last = routes.size() - 1;
        for(int i = last; i >= 0; i--) {
            output.write(routes.get(i) + " ");
        }
        output.newLine();

        input.close();
        output.flush();
        output.close();
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
