import java.util.Arrays;
import java.util.Scanner;

public class letters {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int c = 1;
		
		while (sc.hasNext()) {
			
			String a = sc.next();
			String b = sc.next();
			
			if (a.equals("END") && b.equals("END")) {
				break;
			}
			
			int[] cnta = new int[26];
			int[] cntb = new int[26];
			
			for (int i = 0; i < a.length(); i++) {
				cnta[a.charAt(i)-'a']++;
			}
			for (int i = 0; i < b.length(); i++) {
				cntb[b.charAt(i)-'a']++;
			}
			
			if (Arrays.equals(cnta, cntb)) {
				System.out.println("Case " + c + ": same");
			}else {
				System.out.println("Case " + c + ": different");
			}
			
			
			c++;
		}
	}

}
