import java.util.PriorityQueue;
import java.util.Scanner;

public class No_13549_HideAndSeek3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int start = sc.nextInt();
        int brother = sc.nextInt();

        PriorityQueue<Position> positions = new PriorityQueue<>();
        boolean[] isVisited = new boolean[100_001];

        positions.offer(new Position(start, 0));
        isVisited[start] = true;
        while (!positions.isEmpty()) {
            Position subin = positions.poll();
            isVisited[subin.no] = true;

            if (subin.no == brother) {
                System.out.println(subin.step);
                break;
            }

            int teleport = subin.no * 2;
            if (teleport <= 100_000 && !isVisited[teleport]) {
                positions.offer(new Position(teleport, subin.step));
            }

            int left = subin.no - 1;
            if (0 <= left && !isVisited[left]) {
                positions.offer(new Position(left, subin.step + 1));
            }

            int right = subin.no + 1;
            if (right <= brother && !isVisited[right]) {
                positions.offer(new Position(right, subin.step + 1));
            }
        }
    }

    private static class Position implements Comparable<Position> {
        int no;
        int step;

        Position(int no, int step) {
            this.no = no;
            this.step = step;
        }

        @Override
        public int compareTo(Position other) {
            return this.step - other.step;
        }
    }
}
