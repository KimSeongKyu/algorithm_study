package level2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RankSearching {

    private final static String NONE = "-";

    public int[] solution(String[] info, String[] query) {
        List<Applicant> applicants = new ArrayList<>();
        Arrays.stream(info)
                .forEachOrdered(infoString -> {
                    String[] splitInfo = infoString.split(" ");
                    applicants.add(new Applicant(Language.valueOf(splitInfo[0]),
                            Position.valueOf(splitInfo[1]),
                            Career.valueOf(splitInfo[2]),
                            SoulFood.valueOf(splitInfo[3]),
                            Integer.valueOf(splitInfo[4])));
                });

        List<Integer> passedApplicants = new ArrayList<>();
        Arrays.stream(query)
                .forEachOrdered(queryString -> {
                    String[] splitQuery = queryString.replace(" and", "").split(" ");
                    passedApplicants.add(getPassedApplicantCount(applicants, splitQuery));
                });

        return passedApplicants.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }

    private int getPassedApplicantCount(List<Applicant> applicants, String[] splitQuery) {
        return (int) applicants.stream()
                .filter(applicant -> {
                    if (splitQuery[0].equals(NONE)) {
                        return true;
                    }
                    return applicant.language.name().equals(splitQuery[0]);
                })
                .filter(applicant -> {
                    if (splitQuery[1].equals(NONE)) {
                        return true;
                    }
                    return applicant.position.name().equals(splitQuery[1]);
                })
                .filter(applicant -> {
                    if (splitQuery[2].equals(NONE)) {
                        return true;
                    }
                    return applicant.career.name().equals(splitQuery[2]);
                })
                .filter(applicant -> {
                    if (splitQuery[3].equals(NONE)) {
                        return true;
                    }
                    return applicant.soulFood.name().equals(splitQuery[3]);
                })
                .filter(applicant -> applicant.score >= Integer.valueOf(splitQuery[4]))
                .count();
    }

    enum Language {
        cpp, java, python
    }

    enum Position {
        backend, frontend
    }

    enum Career {
        junior, senior
    }

    enum SoulFood {
        chicken, pizza
    }

    static class Applicant {
        Language language;
        Position position;
        Career career;
        SoulFood soulFood;
        int score;

        Applicant(Language language, Position position, Career career, SoulFood soulFood, int score) {
            this.language = language;
            this.position = position;
            this.career = career;
            this.soulFood = soulFood;
            this.score = score;
        }
    }
}
