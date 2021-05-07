package level3;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class PoorUser {
    private int N, R;
    private int bannedListCount = 0;
    private int[] permutation;
    private boolean[] isVisited;
    private String[] userIds, bannedIds;
    private List<int[]> doneSequenceList = new ArrayList<>();

    public int solution(String[] user_id, String[] banned_id) {
        N = user_id.length;
        R = banned_id.length;
        isVisited = new boolean[N];
        permutation = new int[R];
        userIds = user_id;
        bannedIds = banned_id;

        setPermutation(0);

        return bannedListCount;
    }

    public void setPermutation(int step) {
        if (step == R) {
            for (int i = 0; i < R; i++) {
                int userIndex = permutation[i];
                String userId = userIds[userIndex];
                String bannedId = bannedIds[i];
                if (userId.length() != bannedId.length() || !isSame(userId, bannedId)) {
                    return;
                }
            }

            int[] sequence = Arrays.copyOf(permutation, permutation.length);
            Arrays.sort(sequence);

            if (!isDoneSequence(sequence)) {
                doneSequenceList.add(sequence);
                ++bannedListCount;
            }

            return;
        }

        for (int i = 0; i < N; i++) {
            if (isVisited[i]) continue;

            isVisited[i] = true;
            permutation[step] = i;
            setPermutation(step + 1);
            isVisited[i] = false;
        }
    }

    public boolean isSame(String userId, String bannedId) {
        for (int j = 0; j < bannedId.length(); j++) {
            char bannedIdLetter = bannedId.charAt(j);
            if (bannedIdLetter == '*') continue;

            char userIdLetter = userId.charAt(j);
            if (userIdLetter != bannedIdLetter) return false;
        }
        return true;
    }

    public boolean isDoneSequence(int[] sequence) {
        checkIsDone:
        for (int[] doneSequence : doneSequenceList) {
            for (int i = 0; i < R; i++) {
                if (doneSequence[i] != sequence[i]) {
                    continue checkIsDone;
                }
            }
            return true;
        }
        return false;
    }

}