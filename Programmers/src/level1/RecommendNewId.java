package level1;

public class RecommendNewId {

    public String solution(String new_id) {
        String stepOne = new_id.toLowerCase();

        String stepTwo = "";

        int stepOneLength = stepOne.length();
        for (int i = 0; i < stepOneLength; i++) {
            char stepOneChar = stepOne.charAt(i);
            if (('a' <= stepOneChar && stepOneChar <= 'z') || ('0' <= stepOneChar && stepOneChar <= '9') ||
                    stepOneChar == '-' || stepOneChar == '_' || stepOneChar == '.') {
                stepTwo = stepTwo.concat(String.valueOf(stepOneChar));
            }
        }

        String stepThree = stepTwo;
        while (stepThree.contains("..")) {
            stepThree = stepThree.replace("..", ".");
        }

        String stepFour = stepThree;
        if (stepFour.charAt(stepFour.length() - 1) == '.') {
            stepFour = stepFour.substring(0, stepFour.length() - 1);
        }

        if (stepFour.length() > 0 && stepFour.charAt(0) == '.') {
            stepFour = stepFour.substring(1);
        }

        String stepFive = stepFour;
        if ("".equals(stepFive)) {
            stepFive = "a";
        }

        String stepSix = stepFive;
        if (stepSix.length() >= 16) {
            stepSix = stepSix.substring(0, 15);
        }

        if (stepSix.charAt(stepSix.length() - 1) == '.') {
            stepSix = stepSix.substring(0, stepSix.length() - 1);
        }

        String stepSeven = stepSix;
        while (stepSeven.length() <= 2) {
            stepSeven = stepSeven.concat(String.valueOf(stepSeven.charAt(stepSeven.length() - 1)));
        }

        return stepSeven;
    }
}
