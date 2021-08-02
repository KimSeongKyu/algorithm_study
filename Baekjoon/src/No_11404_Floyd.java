import java.io.*;
import java.util.*;

public class No_11404_Floyd {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int cityCount = Integer.valueOf(br.readLine());
        int busCount = Integer.valueOf(br.readLine());

        List<List<City>> connectedCities = new ArrayList<>();
        for (int i = 0; i <= cityCount; i++) {
            connectedCities.add(new ArrayList<>());
        }

        for (int i = 0; i < busCount; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int from = Integer.valueOf(st.nextToken());
            int to = Integer.valueOf(st.nextToken());
            int cost = Integer.valueOf(st.nextToken());

            connectedCities.get(from).add(new City(to, cost));
        }

        for (int i = 1; i <= cityCount; i++) {
            boolean[] isVisited = new boolean[cityCount + 1];
            int[] costs = new int[cityCount + 1];

            Arrays.fill(costs, Integer.MAX_VALUE);
            PriorityQueue<City> cities = new PriorityQueue<>();
            cities.offer(new City(i, 0));
            costs[i] = 0;

            while (!cities.isEmpty()) {
                City city = cities.poll();

                if (!isVisited[city.to]) {
                    isVisited[city.to] = true;

                    for (City connectedCity : connectedCities.get(city.to)) {
                        if (!isVisited[connectedCity.to] && costs[connectedCity.to] > costs[city.to] + connectedCity.cost) {
                            costs[connectedCity.to] = costs[city.to] + connectedCity.cost;
                            cities.offer(new City(connectedCity.to, costs[connectedCity.to]));
                        }
                    }
                }
            }

            for(int j = 1; j <= cityCount; j++) {
                if(costs[j] == Integer.MAX_VALUE) {
                    costs[j] = 0;
                }
                bw.write(costs[j] + " ");
            }
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }

    static class City implements Comparable<City>{
        int to;
        int cost;

        City(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(City other) {
            return cost - other.cost;
        }
    }
}
