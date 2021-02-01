/*
문제
준규는 숫자 카드 N장을 가지고 있다. 숫자 카드에는 정수가 하나 적혀있는데,
적혀있는 수는 -2^62보다 크거나 같고, 2^62보다 작거나 같다.

준규가 가지고 있는 카드가 주어졌을 때, 가장 많이 가지고 있는 정수를 구하는 프로그램을 작성하시오.
만약, 가장 많이 가지고 있는 정수가 여러 가지라면, 작은 것을 출력한다.

입력
첫째 줄에 준규가 가지고 있는 숫자 카드의 개수 N (1 ≤ N ≤ 100,000)이 주어진다.
둘째 줄부터 N개 줄에는 숫자 카드에 적혀있는 정수가 주어진다.
 */

import java.io.*;
import java.util.HashMap;
import java.util.Arrays;

public class No_11652_Card {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Long[] cards = new Long[n]; //적혀있는 수의 범위 때문에 Long 타입으로 선언

        for(int i = 0; i < n; i++) {
            cards[i] = Long.parseLong(br.readLine());
        }

        Arrays.sort(cards);
        HashMap<Long, Integer> countMap = new HashMap<>();

        // 적혀있는 수의 개수를 세어 key:수, value: 개수 형태로 HashMap에 저장
        for(int i = 0; i < n; i++) {
            int count = (countMap.get(cards[i]) == null) ? 0 : countMap.get(cards[i]);
            countMap.put(cards[i], ++count);
        }


        int maxCount = -1;
        long maxCard = 0;
        // 개수가 가장 많은 카드 선택
        for(long card : countMap.keySet()) {
            int count = countMap.get(card);
            if(count > maxCount) {
                maxCount = count;
                maxCard = card;
            }
            // 개수가 같을 경우 작은 수 선택
            else if(count == maxCount)
                maxCard = (maxCard - card > 0) ? card : maxCard;
        }

        System.out.println(maxCard);
    }
}
