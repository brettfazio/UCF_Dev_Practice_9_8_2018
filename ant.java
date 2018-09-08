import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.TreeSet;

public class ant {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int campuses = sc.nextInt();
		
		for (int i = 0; i  < campuses; i++) {
			int nodes = sc.nextInt();
			int edges = sc.nextInt();
			
			PriorityQueue<AEdge> pq = new PriorityQueue<>();
			
			ADSU d = new ADSU(nodes);
			
			for (int j =0 ; j < edges; j++) {
				int a = sc.nextInt()-1;
				int b = sc.nextInt()-1;
				int weight = sc.nextInt();
				pq.add(new AEdge(a,b,weight));
			}
			
			int total = 0;
			
			while (pq.isEmpty() == false) {
				AEdge pop = pq.poll();
				
				if (d.union(pop.a, pop.b)) {
					total += pop.w;
				}
			}
			
			TreeSet<Integer> check = new TreeSet<>();
			
			for (int h = 0; h < d.n; h++) {
				check.add(d.findPar(h));
			}
			
			System.out.print("Campus #" + (i+1) +": ");
			
			if (check.size() == 1) {
				System.out.println(total);
			}else {
				System.out.println("I'm a programmer, not a miracle worker!");
			}
		}
	}

}

/*
3
2 1
1 2 5
3 1
1 2 2
4 4
1 2 2
2 3 3
3 4 1
1 4 4
 */

class AEdge implements Comparable<AEdge> {
	int a, b, w;
	
	public AEdge(int aa, int bb, int ww) {
		a = aa;
		b =bb;
		w =ww;
	}

	@Override
	public int compareTo(AEdge arg0) {
		return w - arg0.w;
	}
	
}

class ADSU {
	int[] root;
	int[] height;
	int n;
	
	ADSU (int n) {
		this.n= n;
		root = new int[n];
		height = new int[n];
		for (int i =0 ; i < n; i++) {
			root[i] = i;
		}
	}
	
	int findPar(int idx) {
		if (root[idx] != idx) return findPar(root[idx]);
		return idx;
	}
	
	boolean union(int x, int y) {
		int px = findPar(x);
		int py = findPar(y);
		if (px == py) return false;
		root[py] = px;
		return true;
	}
}
