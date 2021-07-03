import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class No_1916_CalculateMinimumCost {

    static class Bus implements Comparable<Bus> {
        int to;
        int cost;

        Bus(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Bus bus) {
            return cost - bus.cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int cityCount = Integer.valueOf(br.readLine());
        int busCount = Integer.valueOf(br.readLine());

        int[] costs = new int[cityCount+1];
        boolean[] isVisited = new boolean[cityCount+1];
        List<List<Bus>> buses = new ArrayList<>();

        Arrays.fill(costs, Integer.MAX_VALUE);
        for(int i = 0; i <= cityCount; i++) {
            buses.add(new ArrayList<>());
        }

        for(int i = 0; i < busCount; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int from = Integer.valueOf(st.nextToken());
            int to = Integer.valueOf(st.nextToken());
            int cost = Integer.valueOf(st.nextToken());

            buses.get(from).add(new Bus(to, cost));
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.valueOf(st.nextToken());
        int end = Integer.valueOf(st.nextToken());
        costs[start] = 0;

        PriorityQueue<Bus> pq = new PriorityQueue<>();
        pq.offer(new Bus(start, 0));

        while(!pq.isEmpty()) {
            Bus currentBus = pq.poll();

            if(!isVisited[currentBus.to]) {
                isVisited[currentBus.to] = true;

                List<Bus> connectedBuses = buses.get(currentBus.to);
                for(Bus bus : connectedBuses) {
                    if(!isVisited[bus.to] && costs[bus.to] > costs[currentBus.to] + bus.cost) {
                        costs[bus.to] = costs[currentBus.to] + bus.cost;
                        pq.offer(new Bus(bus.to, costs[bus.to]));
                    }
                }
            }
        }

        System.out.println(costs[end]);
        br.close();
    }
}