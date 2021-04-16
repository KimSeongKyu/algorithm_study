import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class No_2564_Security {
    private static final int NORTH = 1;
    private static final int SOUTH = 2;
    private static final int WEST = 3;
    private static final int EAST = 4;

    private static final int LEFT_FROM_ORIGIN = 0;
    private static final int RIGHT_FROM_ORIGIN = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int width = Integer.parseInt(st.nextToken());
        int height = Integer.parseInt(st.nextToken());

        int storeCount = Integer.parseInt(br.readLine());
        int dongeun = storeCount;
        int[][] location = new int[storeCount+1][2];

        for(int store = 0; store < storeCount+1; store++) {
            st = new StringTokenizer(br.readLine());
            int direction = Integer.parseInt(st.nextToken());
            int position = Integer.parseInt(st.nextToken());

            switch(direction) {
                case NORTH:
                    location[store][LEFT_FROM_ORIGIN] = -(height+position);
                    location[store][RIGHT_FROM_ORIGIN] = width+height+width-position;
                    break;

                case SOUTH:
                    location[store][LEFT_FROM_ORIGIN] = -(height+width+height+width-position);
                    location[store][RIGHT_FROM_ORIGIN] = position;
                    break;
                case WEST:
                    location[store][LEFT_FROM_ORIGIN] = -(height-position);
                    location[store][RIGHT_FROM_ORIGIN] = width+height+width+position;
                    break;
                case EAST:
                    location[store][LEFT_FROM_ORIGIN] = -(height+width+position);
                    location[store][RIGHT_FROM_ORIGIN] = width+height-position;
                    break;
            }
        }

        int totalMinDistance = 0;
        for(int store = 0; store < storeCount; store++) {
            totalMinDistance += Math.min(
                    Math.abs(location[dongeun][RIGHT_FROM_ORIGIN]-location[store][LEFT_FROM_ORIGIN]),
                    Math.abs(location[dongeun][RIGHT_FROM_ORIGIN]-location[store][RIGHT_FROM_ORIGIN])
            );
        }

        System.out.println(totalMinDistance);
    }
}
