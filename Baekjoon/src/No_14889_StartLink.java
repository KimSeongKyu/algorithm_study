import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class No_14889_StartLink {
	private static int n;
	private static int[][] S;
	private static int[] startTeam;
	private static int[] linkTeam;
	private static boolean[] isStartTeam;
	private static int minAbility;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		S = new int[n][n];
		startTeam = new int[n/2];
		linkTeam = new int[n/2];
		isStartTeam = new boolean[n];
		minAbility = Integer.MAX_VALUE;

		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				S[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		makePair(0, 0);
		System.out.println(minAbility);
		br.close();
	}
	
	// 조합
	private static void makePair(int step, int start) {
		// startTeam과 linkTeam원 구성
		if(step == n/2) {
			int startAbility = 0, linkAbility = 0;
			int i = 0;
			// startTeam원이 아닌 사람을 linkTeam에 추가
			for(int j = 0; j < n; j++)
				if(!isStartTeam[j]) linkTeam[i++] = j;
			
			// startTeam과 linkTeam의 능력치 계산
			for(int j = 0; j < n/2; j++) {
				for(int k = j+1; k < n/2; k++) {
					startAbility += (S[startTeam[j]][startTeam[k]] + S[startTeam[k]][startTeam[j]]);
					linkAbility += (S[linkTeam[j]][linkTeam[k]] + S[linkTeam[k]][linkTeam[j]]);
				}
			}
			
			// startTeam의 능력치와 linkTeam의 능력치의 차이의 최소값 계산
			minAbility = Math.min(minAbility, Math.abs(startAbility-linkAbility));
			
			return;
		}
		
		for(int i = start; i < n; i++) {
			startTeam[step] = i;
			isStartTeam[i] = true;
			makePair(step+1, i+1);
			isStartTeam[i] = false;
		}
	}
}
