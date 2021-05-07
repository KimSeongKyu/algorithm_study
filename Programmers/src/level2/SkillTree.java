package level2;

public class SkillTree {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;

        Loop:
        for (String s : skill_trees) {
            int index = 0;
            for (int i = 0; i < s.length(); i++) {
                String now = s.substring(i, i + 1);
                if (skill.contains(now)) {
                    if (skill.indexOf(now) > index) continue Loop;
                    index++;
                }
            }
            answer++;
        }

        return answer;
    }
}