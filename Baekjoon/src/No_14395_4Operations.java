import java.util.*;

public class No_14395_4Operations {

    static class NumberInfo {
        long number;
        String operation;

        NumberInfo(long number, String operation) {
            this.number = number;
            this.operation = operation;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int sourceNumber = sc.nextInt();
        int targetNumber = sc.nextInt();
        String[] operations = {"*", "+", "/"};

        if (sourceNumber == targetNumber) {
            System.out.println("0");
            return;
        } else {
            Queue<NumberInfo> numberInfoQueue = new LinkedList<>();
            List<Long> calculatedNumberList = new ArrayList<>();

            numberInfoQueue.offer(new NumberInfo(sourceNumber, ""));
            calculatedNumberList.add((long) sourceNumber);

            while (!numberInfoQueue.isEmpty()) {
                NumberInfo numberInfo = numberInfoQueue.poll();
                if (numberInfo.number == targetNumber) {
                    System.out.println(numberInfo.operation);
                    return;
                }

                long calculatedNumber = numberInfo.number;
                for (String operation : operations) {
                    switch (operation) {
                        case "*":
                            calculatedNumber = numberInfo.number * numberInfo.number;
                            break;
                        case "+":
                            calculatedNumber = numberInfo.number + numberInfo.number;
                            break;
                        case "/":
                            calculatedNumber = 1;
                            break;
                    }

                    if (0 < calculatedNumber && calculatedNumber <= targetNumber &&
                            !calculatedNumberList.contains(calculatedNumber)) {
                        calculatedNumberList.add(calculatedNumber);
                        numberInfoQueue.offer(new NumberInfo(calculatedNumber, numberInfo.operation + operation));
                    }
                }
            }
        }
        System.out.println("-1");
    }
}
