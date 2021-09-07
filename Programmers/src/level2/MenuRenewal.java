package level2;

import java.util.*;

public class MenuRenewal {
    private static Map<String, Integer>[] courseMenusWithCount;
    private char[] sequence;

    public String[] solution(String[] orders, int[] course) {

        for (int i = 0; i < orders.length; i++) {
            char[] order = orders[i].toCharArray();
            Arrays.sort(order);
            orders[i] = String.valueOf(order);
        }

        courseMenusWithCount = new HashMap[course.length];
        for (int i = 0; i < course.length; i++) {
            courseMenusWithCount[i] = new HashMap<>();
        }

        for (int i = 0; i < course.length; i++) {
            int r = course[i];
            for (String order : orders) {
                int n = order.length();
                sequence = new char[r];
                combination(order, n, r, i, 0, 0);
            }
        }

        List<String> courseMenus = new ArrayList<>();
        for(int i = 0; i < course.length; i++) {
            Map<String, Integer> courseMenuWithCount = courseMenusWithCount[i];
            int maxCount = Integer.MIN_VALUE;

            for(int count : courseMenuWithCount.values()) {
                maxCount = Math.max(maxCount, count);
            }

            for(String key : courseMenuWithCount.keySet()) {
                int count = courseMenuWithCount.get(key);
                if(count > 1 && count == maxCount) {
                    courseMenus.add(key);
                }
            }
        }

        Collections.sort(courseMenus);
        return courseMenus.toArray(String[]::new);
    }

    private void combination(String order, int n, int r, int i, int step, int start) {
        if (step == r) {
            String courseMenu = String.valueOf(sequence);
            if (!courseMenusWithCount[i].containsKey(courseMenu)) {
                courseMenusWithCount[i].put(courseMenu, 1);
            } else {
                int courseMenuCount = courseMenusWithCount[i].get(courseMenu);
                courseMenusWithCount[i].replace(courseMenu, courseMenuCount, courseMenuCount + 1);
            }
            return;
        }

        for (int j = start; j < n; j++) {
            sequence[step] = order.charAt(j);
            combination(order, n, r, i, step + 1, j + 1);
        }
    }
}
