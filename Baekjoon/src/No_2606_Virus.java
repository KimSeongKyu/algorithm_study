import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class No_2606_Virus {

    private static int computerCount;
    private static int networkCount;
    private static int infectedCount = 0;

    private static boolean[] isInfected;
    private static List<Computer>[] networks;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        computerCount = Integer.valueOf(br.readLine());
        isInfected = new boolean[computerCount + 1];
        networks = new ArrayList[computerCount + 1];
        for (int i = 1; i <= computerCount; i++) {
            networks[i] = new ArrayList<>();
        }

        networkCount = Integer.valueOf(br.readLine());
        for (int i = 0; i < networkCount; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int firstComputer = Integer.valueOf(st.nextToken());
            int secondComputer = Integer.valueOf(st.nextToken());
            networks[firstComputer].add(new Computer(secondComputer));
            networks[secondComputer].add(new Computer(firstComputer));
        }

        isInfected[1] = true;
        infect(1);

        System.out.println(infectedCount);
    }

    private static void infect(int computerNumber) {
        for (Computer connectedComputer : networks[computerNumber]) {
            int connectedComputerNumber = connectedComputer.number;

            if (!isInfected[connectedComputerNumber]) {
                isInfected[connectedComputerNumber] = true;
                ++infectedCount;
                infect(connectedComputerNumber);
            }
        }
    }

    static class Computer {
        int number;

        Computer(int number) {
            this.number = number;
        }
    }
}
