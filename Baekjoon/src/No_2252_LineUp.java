import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class No_2252_LineUp {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] frontCount = new int[N+1];
        List<Integer>[] students = new ArrayList[N+1];
        for(int i = 1; i <= N; i++) {
            students[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int front = Integer.parseInt(st.nextToken());
            int back = Integer.parseInt(st.nextToken());

            ++frontCount[back];
            students[front].add(back);
        }

        Queue<Integer> queue = new LinkedList<>();
        for(int i = 1; i <= N; i++) {
            if(frontCount[i] == 0) queue.offer(i);
        }

        while(!queue.isEmpty()) {
            int current = queue.poll();
            sb.append(current+" ");

            for(int next = 0; next < students[current].size(); next++) {
                int nextStudent = students[current].get(next);
                if(--frontCount[nextStudent] == 0) {
                    queue.offer(nextStudent);
                }
            }
        }
        sb.setLength(sb.length()-1);
        System.out.println(sb);
        br.close();
    }
}
