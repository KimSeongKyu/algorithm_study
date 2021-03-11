import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class No_2374_MakeSameNumber {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> pivotList = new ArrayList<>();
        int n = Integer.parseInt(br.readLine());
        int count = 0;

        int pivot = 0;
        // pivot을 기준으로 다른 값이 나오면 리스트에 추가
        for(int i = 0; i < n; i++) {
            int target = Integer.parseInt(br.readLine());

            if(pivot != target) {
                pivot = target;
                pivotList.add(pivot);
            }
        }

        int min, minIndex;
        int left, right, target;
        Iterator<Integer> iterator = pivotList.iterator();

        // 일련의 연속된 값을 하나의 값으로 취급
        // 가장 작은 값을 찾아서 오른쪽과 왼쪽 중 더 작은 값까지 증가
        while(iterator.hasNext() && pivotList.size() > 1) {
            min = Collections.min(pivotList);                   // 가장 작은 값 찾기
            minIndex = pivotList.indexOf(min);                  // 가장 작은 값의 index 찾기

            if(minIndex == 0) {                                 // 가장 작은 값이 가장 왼쪽에 있는 경우
                right = pivotList.get(minIndex+1);              // 바로 오른쪽 값까지
                count += (right-min);                           // 가장 작은 값 증가
            }
            else if(minIndex == pivotList.size()-1) {           // 가장 작은 값이 가장 오른쪽에 있는 경우
                left = pivotList.get(minIndex-1);               // 바로 왼쪽 값까지
                count += (left-min);                            // 가장 작은 값 증가
            }
            else {                                              // 가장 작은 값이 1 ~ size-2에 있는 경우
                left = pivotList.get(minIndex-1);               // 가장 작은 값의 왼쪽 값
                right = pivotList.get(minIndex+1);              // 가장 작은 값의 오른쪽 값
                target = (left < right) ? left : right;         // 왼쪽과 오른쪽 중 더 작은 값 선택
                count += (target-min);                          // 해당 값만큼 가장 작은 값 증가
            }

            pivotList.remove(minIndex);                         // 가장 작은 값 삭제
        }

        System.out.println(count);
        br.close();
    }
}
