import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeSet;

public class change {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int sets = sc.nextInt();

		outt: for (int outer =0 ; outer < sets; outer++) {
			int coins = sc.nextInt();

			int[] c = new int[coins];

			for (int j =0; j < coins; j++) {
				c[j] = sc.nextInt();
			}

			Arrays.sort(c);
			int n =c.length;

			int num = 1;
			
			//1 2 4 8 12 100
			//1 -> 1+1 = 2
			//2 -> 2+2 = 4
			//4 -> 4+4 = 8
			//8 -> 8+8 = 16
			//16 (12 <= 16) -> 16+12 = 28
			//28 !< 100
			//ans = 28
			
			
			for (int i =0 ; i < n; i++) {
				if (c[i] <= num) {
					num += c[i];
				}
			}
			
			System.out.println("Set #" + (outer+1)+": "+num);
			System.out.println();

		}
	}

}

/*

3
6
12 8 1 2 4 100
3
1 2 3
6
3 1 3 2 3 3



1
31
1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31

 */
