import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class No_15686_ChickenDelivery {

    private final static int HOUSE = 1;
    private final static int CHICKEN_HOUSE = 2;

    private static int n;
    private static int m;
    private static int[][] city;
    private static Building[] combination;
    private static List<Building> houses = new ArrayList<>();
    private static List<Building> chickenHouses = new ArrayList<>();
    private static int sumOfMinChickenDistance = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.valueOf(st.nextToken());
        m = Integer.valueOf(st.nextToken());

        combination = new Building[m];
        city = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                city[i][j] = Integer.valueOf(st.nextToken());

                if (city[i][j] == HOUSE) {
                    houses.add(new Building(j, i));
                } else if (city[i][j] == CHICKEN_HOUSE) {
                    chickenHouses.add(new Building(j, i));
                }
            }
        }

        calculateChickenDistance(0, 0);
        System.out.println(sumOfMinChickenDistance);
    }

    private static void calculateChickenDistance(int step, int start) {
        if (step == m) {
            int sumOfChickenDistance = 0;

            for(Building house : houses) {
                int minChickenDistance = Integer.MAX_VALUE;

                for(int i = 0; i < m; i++) {
                    Building chickenHouse = combination[i];
                    int chickenDistance = Math.abs(house.x - chickenHouse.x) + Math.abs(house.y - chickenHouse.y);
                    minChickenDistance = minChickenDistance > chickenDistance ? chickenDistance : minChickenDistance;
                }

                sumOfChickenDistance += minChickenDistance;
            }

            sumOfMinChickenDistance = sumOfMinChickenDistance > sumOfChickenDistance ? sumOfChickenDistance : sumOfMinChickenDistance;
            return;
        }

        for (int i = start; i < chickenHouses.size(); i++) {
            combination[step] = chickenHouses.get(i);
            calculateChickenDistance(step + 1, i + 1);
        }
    }

    static class Building {
        int x, y;

        Building(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
