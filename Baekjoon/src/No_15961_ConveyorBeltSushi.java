import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class No_15961_ConveyorBeltSushi {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int numOfPlate = Integer.parseInt(st.nextToken());
        int numOfSushiType = Integer.parseInt(st.nextToken());
        int sizeOfSequence = Integer.parseInt(st.nextToken());
        int coupon = Integer.parseInt(st.nextToken());

        int[] conveyorBelt = new int[numOfPlate + sizeOfSequence - 1];
        for(int n = 0; n < numOfPlate; n++) {
            conveyorBelt[n] = Integer.parseInt(br.readLine());
        }
        for(int n = 0; n < sizeOfSequence-1; n++) {
            conveyorBelt[n + numOfPlate] = conveyorBelt[n];
        }

        int[] numOfSushiInSequence = new int[numOfSushiType+1];
        int startSushi = 0, endSushi = 0;
        int maxSumOfSushiType = 0, sumOfSushiType = 0;

        while(endSushi < conveyorBelt.length) {
            int endSushiType = conveyorBelt[endSushi];

            ++numOfSushiInSequence[endSushiType];
            if(numOfSushiInSequence[endSushiType] == 1) {
                if(endSushiType != coupon) {
                    ++sumOfSushiType;
                }
            }

            if(endSushi++ >= sizeOfSequence-1) {
                maxSumOfSushiType = Math.max(maxSumOfSushiType, sumOfSushiType);

                int startSushiType = conveyorBelt[startSushi++];
                --numOfSushiInSequence[startSushiType];

                if(numOfSushiInSequence[startSushiType] == 0 && startSushiType != coupon) {
                    --sumOfSushiType;
                }
            }
        }

        System.out.println(maxSumOfSushiType+1);
    }
}
