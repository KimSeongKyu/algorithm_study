import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class No_1005_ACMCraft {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        int testCaseCount = Integer.valueOf(input.readLine());
        for(int no = 1; no <= testCaseCount; no++) {
            StringTokenizer st = new StringTokenizer(input.readLine());
            int buildingCount = Integer.valueOf(st.nextToken());
            int ruleCount = Integer.valueOf(st.nextToken());

            int[] constructTimes = new int[buildingCount + 1];
            st = new StringTokenizer(input.readLine());
            for (int i = 1; i <= buildingCount; i++) {
                constructTimes[i] = Integer.valueOf(st.nextToken());
            }

            List<Integer>[] connectedBuildings = new ArrayList[buildingCount + 1];
            for(int i = 1; i <= buildingCount; i++) {
                connectedBuildings[i] = new ArrayList<>();
            }

            int[] inDegrees = new int[buildingCount + 1];
            for(int i = 0; i < ruleCount; i++) {
                st = new StringTokenizer(input.readLine());
                int from = Integer.valueOf(st.nextToken());
                int to = Integer.valueOf(st.nextToken());
                connectedBuildings[from].add(to);
                ++inDegrees[to];
            }

            Queue<Integer> nextBuildings = new LinkedList<>();
            for(int i = 1; i <= buildingCount; i++) {
                if(inDegrees[i] == 0) {
                    nextBuildings.offer(i);
                }
            }

            int[] totalConstructTimes = new int[buildingCount + 1];
            int destination = Integer.valueOf(input.readLine());
            while(inDegrees[destination] > 0) {
                int nextBuilding = nextBuildings.poll();

                for(int connectedBuilding : connectedBuildings[nextBuilding]) {
                    totalConstructTimes[connectedBuilding] = Math.max(totalConstructTimes[connectedBuilding],
                            constructTimes[nextBuilding] + totalConstructTimes[nextBuilding]);
                    if(--inDegrees[connectedBuilding] == 0) {
                        nextBuildings.offer(connectedBuilding);
                    }
                }
            }

            System.out.println(constructTimes[destination] + totalConstructTimes[destination]);
        }

        input.close();
    }
}
