import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class No_13418_VisitingSchool {

    private static List<Road>[] connectedRoads;
    private static int buildingCount;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(input.readLine());

        buildingCount = Integer.valueOf(st.nextToken());
        int roadCount = Integer.valueOf(st.nextToken());

        connectedRoads = new ArrayList[buildingCount + 1];
        for (int i = 0; i <= buildingCount; i++) {
            connectedRoads[i] = new ArrayList<>();
        }

        for (int i = 0; i <= roadCount; i++) {
            st = new StringTokenizer(input.readLine());
            int from = Integer.valueOf(st.nextToken());
            int to = Integer.valueOf(st.nextToken());
            int status = Integer.valueOf(st.nextToken()) == 0 ? 1 : 0;
            connectedRoads[from].add(new Road(to, status));
            connectedRoads[to].add(new Road(from, status));
        }

        int minFatigue = prim((road, other) -> road.status - other.status);
        int maxFatigue = prim((road, other) -> other.status - road.status);


        System.out.println((int) (Math.pow(maxFatigue, 2) - Math.pow(minFatigue, 2)));
        input.close();
    }

    private static int prim(Comparator<Road> comparator) {
        PriorityQueue<Road> roads = new PriorityQueue<>(comparator);
        boolean[] isVisited = new boolean[buildingCount + 1];
        isVisited[0] = true;
        int fatigue = 0;

        roads.offer(connectedRoads[0].get(0));
        while (!roads.isEmpty()) {
            Road road = roads.poll();

            if (isVisited[road.to]) {
                continue;
            }

            isVisited[road.to] = true;
            fatigue += road.status;

            for (Road connectedRoad : connectedRoads[road.to]) {
                if (!isVisited[connectedRoad.to]) {
                    roads.offer(connectedRoad);
                }
            }
        }

        return fatigue;
    }

    private static class Road {
        int to;
        int status;

        Road(int to, int status) {
            this.to = to;
            this.status = status;
        }
    }
}
