import java.util.Scanner;
import java.util.TreeSet;

public class morecombos {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		
		for (int i =0 ;i  < n; i++) {
			int bags = sc.nextInt();
			int cap = sc.nextInt();
			
			int[] b = new int[bags];
			
			for (int j = 0; j < bags; j++) {
				int in = sc.nextInt();
				
				for (int k = 0; k < in; k++) {
					b[j] = b[j] | (1 << (sc.nextInt()));
				}
			}
			
			int max = 0;
			
			for (int subset = 0; subset < (1 << (bags+1)); subset++) {
				int current = 0;
				int gotten = 0;
//				System.out.println(Integer.toBinaryString(subset));
				for (int g = 0; g < b.length; g++) {
					int res = subset & (1 << g);
					if (res != 0) {
						
//						System.out.println("bef current = " + current + " oring with b[g] = " + b[g]);
						current |= b[g];
//						System.out.println("after current = " + current);
						
						gotten++;
						if (gotten >= cap) {
							break;
						}
					}
				}
//				System.out.println("current = " + Integer.toBinaryString(current));
				
				max = Math.max(Integer.bitCount(current), max);
				
			}
			
//			System.out.println("ANSFWER RENVIE SEFSEHFI "+max);
			System.out.println(max);
			
		}
	}

}
/*

2
3 2
4 1 1 1 1
2 2 3
2 4 5
4 1
10 6 5 5 5 4 6 5 5 5 4
4 1 2 3 4
5 2 2 2 3 3
6 7 7 1 1 3 3

*/