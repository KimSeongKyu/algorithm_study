package level2;

import java.util.*;

public class Tuple {
    public int[] solution(String s) {
        String str = s.replace("{", "");
        String[] tupleSet = str.split("}");
        Arrays.sort(tupleSet, (t1, t2) -> t1.length() - t2.length());

        List<Integer> elementList = new ArrayList<Integer>();

        for (String tuple : tupleSet) {
            StringTokenizer st = new StringTokenizer(tuple, ",");
            while (st.hasMoreTokens()) {
                int element = Integer.parseInt(st.nextToken());
                if (!elementList.contains(element)) {
                    elementList.add(element);
                }
            }
        }
        int[] answer = elementList.stream().mapToInt(i -> i).toArray();
        return answer;
    }
}