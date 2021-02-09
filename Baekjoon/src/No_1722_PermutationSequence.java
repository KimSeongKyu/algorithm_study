/*
문제
1부터 N까지의 수를 임의로 배열한 순열은 총 N! = N×(N-1)×…×2×1 가지가 있다.

임의의 순열은 정렬을 할 수 있다.
예를 들어  N=3인 경우 {1, 2, 3}, {1, 3, 2}, {2, 1, 3}, {2, 3, 1}, {3, 1, 2}, {3, 2, 1}의 순서로 생각할 수 있다.
첫 번째 수가 작은 것이 순서상에서 앞서며, 첫 번째 수가 같으면 두 번째 수가 작은 것이, 두 번째 수도 같으면 세 번째 수가 작은 것이….

N이 주어지면, 아래의 두 소문제 중에 하나를 풀어야 한다.
k가 주어지면 k번째 순열을 구하고, 임의의 순열이 주어지면 이 순열이 몇 번째 순열인지를 출력하는 프로그램을 작성하시오.

입력
첫째 줄에 N(1≤N≤20)이 주어진다. 둘째 줄의 첫 번째 수는 소문제 번호이다.
1인 경우 k(1≤k≤N!)를 입력받고, 2인 경우 임의의 순열을 나타내는 N개의 수를 입력받는다.
N개의 수에는 1부터 N까지의 정수가 한 번씩만 나타난다.
 */
import java.io.*;
import java.util.StringTokenizer;

public class No_1722_PermutationSequence {
    private static int n, number;
    private static long position = 0;
    private static int[] sequence;
    private static boolean[] isSelected;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        sequence = new int[n];
        isSelected = new boolean[n+1];

        st = new StringTokenizer(br.readLine());

        int command = Integer.parseInt(st.nextToken());

        switch (command) {
            case 1:
                position = Long.parseLong(st.nextToken());  // 찾고자 하는 순열의 위치
                findSequence(position, n, 1);

                //출력
                for(int i = 0; i < n; i++)
                    sb.append(sequence[i] + " ");
                sb.setLength(sb.length()-1);
                bw.write(sb.toString());
                break;
            case 2:
                for(int i = 0; i < n; i++)
                    sequence[i] = Integer.parseInt(st.nextToken()); // 위치를 찾고자 하는 순열

                findPosition(n);

                bw.write(String.valueOf(position));
                break;
        }

        br.close();
        bw.flush();
        bw.close();
    }

    private static void findSequence(long position, int k, int number) {
        // 선택하지 않은 수 찾기
        for(; number <= n; number++) if(!isSelected[number]) break;

        if(k == 1) {
            sequence[n-1] = number;
            return;
        }

        // 찾고자 하는 위치가 factorial(k-1)보다 크면
        // 실제 k번째 수가 현재의 탐색 중인 수보다 큼
        if(position > factorial(k-1)) {
            // k번째 수가 탐색하고자 하는 숫자인 경우를 제외하기 위해 position-factorial(k-1)
            // 다음 숫자를 탐색하기 위해 number+1
            findSequence(position-factorial(k-1), k, ++number);
        }
        // 탐색 중인 수가 k번째 수인 경우
        else {
            sequence[n-k] = number; // k번째 수 저장
            isSelected[number] = true;
            // k-1번째 수 찾기
            findSequence(position, k-1, 1);
        }
    }

    private static void findPosition(int k) {
        // 순열의 가장 마지막 숫자를 선택하는 경우
        if(k == 0) {
            ++position;
            return;
        }

        number = sequence[n-k]; // k번째 수
        int selectedCount = 1;

        // k+1까지의 단계에서 선택된 수의 개수 찾기
        for(int i = 1; i < number; i++) {
            if(isSelected[i]) ++selectedCount;
        }

        // k번째 숫자가 선택되기까지의 경우의 수 =
        // (k번째 숫자보다 작은 수들 중 k+1단계까지 선택되지 않은 수) * (k-1)!
        position += (number-selectedCount) * factorial(k-1);

        isSelected[number] = true;
        findPosition(k-1);  // k-1번째 수에 대해 경우의 수 찾기
    }

    private static long factorial(int k) {
        long result = 1;
        for(int i = 1; i <= k; i++)
            result *= i;
        return result;
    }
}
