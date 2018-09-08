import java.util.ArrayDeque;
import java.util.Scanner;

public class calc {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int cases = sc.nextInt();
		
		for (int i =0 ; i < cases; i++) {
			int start = sc.nextInt();
			
			boolean[] hit = new boolean[500];
			int[] moves = new int[500];
			
			ArrayDeque<Integer> dq = new ArrayDeque<>();
			
			dq.push(start);
			hit[start] = true;
			moves[start] = 0;
			
			while (dq.isEmpty() == false) {
				int pop = dq.pop();
				int lastmoves = moves[pop];
				
				if (pop + 1 < 500 && (!hit[pop+1] || moves[pop+1] > lastmoves+1)) {
					hit[pop+1] = true;
					moves[pop+1] = lastmoves+1;
					dq.push(pop+1);
				}
				if (pop * 3 < 500 && (!hit[pop*3] || moves[pop*3] > lastmoves+1)) {
					hit[pop*3] = true;
					moves[pop*3] = lastmoves+1;
					dq.push(pop*3);
				}
				if (pop /2 >= 0 && (!hit[pop/2] || moves[pop/2] > lastmoves+1)) {
					hit[pop/2] = true;
					moves[pop/2] = lastmoves+1;
					dq.push(pop/2);
				}
			}
			
			int max = 0;
			for (int j = 1; j < 100; j++) {
				max = Math.max(max, moves[j]);
			}
			
			System.out.println(max);
		}
	}

}

/*

3
1
73
99

*/