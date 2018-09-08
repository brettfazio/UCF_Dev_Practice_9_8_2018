import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeSet;

public class shuffle {

	public static void main(String[] args) {
		Scanner sc =new Scanner(System.in);
		
		int cases = sc.nextInt();
		
		for (int i =0; i < cases; i++) {
			int c = sc.nextInt();
			
			TreeSet<String> seen = new TreeSet<>();
			
			char[] s1 = sc.next().toCharArray();
			char[] s2 = sc.next().toCharArray();
			
			char[] target = sc.next().toCharArray();
			
			char[] make = new char[c*2];
			
			int times = 0;
			boolean bad = false;
			while (Arrays.equals(make, target)==false) {
				
				
				
				for (int j = 0; j < c*2; j+=2) {
					make[j] = s2[j/2];
					make[j+1 ] = s1[j/2];
				}
				
				String get = Arrays.toString(make);
//				System.out.println(get);
				if (seen.contains(get)) {
					bad = true;
					break;
				}
				
				s2 = Arrays.copyOfRange(make, c, c*2);
				s1 = Arrays.copyOfRange(make, 0, c);
			
				seen.add(get);
				
				times++;
			}
			
			if (bad) {
				System.out.println((i+1) +" -1");
			}else {
				System.out.println((i+1) +" " + times);
			}
			
			
		}
	}

}

/*

2
4
AHAH
HAHA
HHAAAAHH
3
CDE
CDE
EEDDCC

*/
