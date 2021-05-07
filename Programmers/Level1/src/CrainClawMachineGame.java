import java.util.Stack;

public class CrainClawMachineGame {
    class Solution {
        private int[][] realBoard;
        private int row;

        public int solution(int[][] board, int[] moves) {
            int bumpCount = 0;
            realBoard = board;
            row = realBoard.length;
            Stack<Integer> basket = new Stack<Integer>();

            for(int move : moves) {
                int doll = pickUp(move-1);
                if(doll != 0) {
                    if(!basket.isEmpty() && basket.peek() == doll) {
                        basket.pop();
                        bumpCount+=2;
                    } else {
                        basket.push(doll);
                    }
                }
            }

            return bumpCount;
        }

        public int pickUp(int move) {
            int doll = 0;
            for (int i = 0; i < row; i++) {
                if (realBoard[i][move] != 0) {
                    doll = realBoard[i][move];
                    realBoard[i][move] = 0;
                    break;
                }
            }
            return doll;
        }
    }
}
