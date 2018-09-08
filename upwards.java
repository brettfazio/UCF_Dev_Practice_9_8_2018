import java.util.Arrays;
import java.util.Scanner;

public class upwards {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int cases = sc.nextInt();
		
		for (int i =0 ; i < cases; i++) {
			int level = sc.nextInt();
			int letters = sc.nextInt();
			int rank = sc.nextInt();
			
			char[] word = new char[letters];
			
			//make first word
			word[0] = 'a';
			for (int l = 1; l < letters; l++) {
				word[l] = (char) (word[l-1]+level+1);
			}
			
			for (int w = 2; w <= rank; w++) {
				
				int pos = letters-1;
				
				if (word[pos] == 'z') {
					
					outer : for (int j = pos-1; j >= 0; j--) {
						if (word[j] != 'z') {
							word[j]++;
							for (int n = j+1; n < letters; n++) {
								if (word[n-1]+level+1 > 'z') {
									continue outer;
								}
								word[n] = (char) (word[n-1]+level+1);
							}
							break outer;
						}
					}
					
				}else {
					word[pos]++;
				}
				
			}
			
			for (char c : word) {
				System.out.print(c);
			}
			System.out.println();
			
		}
	}

}
/*

2
1 3 16
0 25 24

*/