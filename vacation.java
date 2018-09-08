//wrong

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.TreeSet;

public class vacation {

	static int[] x, y;
	static double[][] edge;
	static boolean[] hit;
	static int num_hit;

	static double[][] mst;

	static double min;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int cases = sc.nextInt();

		for(int i =0 ;i < cases; i++) {
			int nodes = sc.nextInt();
			int blocked = sc.nextInt();

			min = Integer.MAX_VALUE;

			num_hit = 0;

			PriorityQueue<Edge> pq = new PriorityQueue<>();

			hit = new boolean[nodes+1];
			x = new int[nodes+1];
			y = new int[nodes+1];
			edge = new double[nodes+1][nodes+1];
			mst = new double[nodes+1][nodes+1];
			for (int k =0 ; k < nodes; k++) {
				x[k] = sc.nextInt();
				y[k] = sc.nextInt();
			}

			for (int k = 0; k < blocked; k++) {
				int a = sc.nextInt()-1;
				int b = sc.nextInt()-1;

				edge[a][b] = edge[b][a] = -1;
			}

			for (int o = 0; o < nodes; o++) {
				for (int j = o+1; j < nodes; j++) {
					if(edge[o][j] != 0) continue;
					edge[o][j] = edge[j][o] = dis(x[o],y[o],x[j],y[j]);
					pq.add(new Edge(j,o,edge[j][o]));
				}
			}


			DSU dsu = new DSU(nodes+1);

			double total = 0;

			for (int y = 0; y < mst.length; y++) {
				Arrays.fill(mst[y], -1);
			}

			while (pq.isEmpty() == false) {

				Edge pop = pq.poll();

				if (dsu.union(pop.a, pop.b)) {
					mst[pop.a][pop.b] = mst[pop.b][pop.a] = pop.w;
					total += pop.w;
				}

			}

			total += nodes*120;

			for (int o = 0; o < nodes; o++) {
				if (edge[o][nodes] != 0) continue;
				edge[o][nodes] = edge[nodes][o] = dis(0,0,x[o],y[o]);
				pq.add(new Edge(nodes,o,edge[nodes][o]));
			}
			while (pq.isEmpty() == false) {

				Edge pop = pq.poll();

				if (dsu.union(pop.a, pop.b)) {
					total += pop.w;
					mst[pop.a][pop.b] = mst[pop.b][pop.a] = pop.w;
					break;
				}

			}

			TreeSet<Integer> check = new TreeSet<>();

			for (int h = 0; h < dsu.n; h++) {
				check.add(dsu.findPar(h));
			}

			total = ((nodes*120)+dfs_mst(nodes,0,-1));
		
//			System.out.println(Arrays.toString(hit));
//			System.out.println(num_hit);
			System.out.println("Vacation #" + (i+1)+":");
			if (num_hit != mst.length-1) {
				System.out.println("Jimmy should plan this vacation a different day.");
			}else {
				System.out.printf("Jimmy can finish all of the rides in %.3f seconds.%n", total);
			}
			System.out.println();
		}
	}

	static double dfs_mst(int node, double dis, int par) {
//		System.out.println("top " + node);
		ArrayList<Double> gets = new ArrayList<>();

		for (int i = 0; i < mst.length; i++) {
			if (node != i && i != par && mst[node][i] != -1 && !hit[i] && i != mst.length-1) {
				hit[i] = true;
				num_hit++;
				int prevh = num_hit;
				//double get = dfs_mst(i,dis+edge[node][i],node);
				double get = dfs_mst(i,edge[node][i],node);
				//				if (prevh != mst.length && num_hit == mst.length) {
				//					
				//				}else {
				//					get *= 2;
				//				}
				//				
				//				dis += get;
				gets.add(get);
			}
		}

		Collections.sort(gets);

		for (int i = 0; i < gets.size()-1; i++) {
			dis += node == mst.length ? gets.get(i) : gets.get(i)*2;
		}
		if (gets.size()>0)
			dis += gets.get(gets.size()-1);

		return dis;

	}

	/*
	 	static int dfs(int i, int parent, int edgeid) {
		if (prenumber[i] != -1) {
			lowlink[parent] = Math.min(lowlink[parent], prenumber[i]);
			return lowlink[parent];
		}

		prenumber[i] = lowlink[i] = idcount++;
		boolean haspath = false;
		for (Edge e: adj[i]) {
			if (!used[e.id]) {
				used[e.id] = true;
			}
		}

		return -1;
	}
	 */

	static double dis(double x1, double y1, double x2, double y2) {
		return Math.sqrt(Math.pow(x1-x2, 2)+Math.pow(y1-y2, 2));
	}

}

/*

3
4 0
0 2
2 2
2 4
4 4
5 4
10 10
12 35
64 60
3 7
100 857
1 2
1 3
1 4
1 5
5 2
0 5
0 10
0 20
0 50
0 25
3 4
1 2

1
5 4
10 10
12 35
64 60
3 7
100 857
1 2
1 3
1 4
1 5

 */

class Edge implements Comparable<Edge> {
	int a, b;
	double w;

	public Edge(int aa, int bb, double ww) {
		a = aa;
		b =bb;
		w =ww;
	}

	@Override
	public int compareTo(Edge arg0) {
		return Double.compare(w, arg0.w);
	}

}

class DSU {
	int[] root;
	int[] height;
	boolean[] taken;
	int n;

	DSU (int n) {
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

