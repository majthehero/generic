/*
 * Asimptotična ocena za iterativni algoritem: THETA(n)
 * Asimptotična ocena za rekurzivni algoritem: 
 * 	po Master teoremu: 
 * 		št. podproblemov, ki jih rešimo: a = 2
 * 		št. podproblemov: p = 2
 * 		velikost prodproblema: n1 = n/2 => c = 2
 * 		združevanje in priprava podproblemov: O(f(n)) = 1
 * 		f(n) = b*n^r = 1 = b * n^0 => r = 0
 * 		c^r = 1 ;  a = 2 ;  c^r < a
 * 	THETA(n^log_c(a)) = THETA(n^log_2(2)) = THETA(n^1)
 * 
 * */


import java.util.Random;

public class Izziv6 {
	
	final static int maliN = 3000;
	
	int N;
	double[] table;
	
	public static void main (String args[]) {
		Izziv6 i = new Izziv6();
		i.N = maliN;
		i.table = new double[i.N];
		i.run();
	}
	void run() {
		table = new double[N];
		Random r = new Random();
		for (int i = 0; i < N; i++) {
			table[i] = r.nextDouble();
		}
		// rekurz
		double[] res = minmax(0, N - 1);
		System.out.printf("Max rekurzive: %f\nMin rekurzive: %f\n",
							res[0], res[1]);
		// iterat
		double maxI = -Double.MAX_VALUE;
		double minI = Double.MAX_VALUE;
		for (double d : table) {
			if (d > maxI)
				maxI = d;
			if (d < minI)
				minI = d;
		}
		System.out.printf("Max iterative: %f\nMin iterative: %f\n",
							maxI, minI);
	}
	double[] minmax(int l, int r) {
		// rešitev trivialnega problema - konstanten čas
		double[] result = new double[2];
		if (l == r) {
			result = new double[] {table[l], table[l]};
			return result;
		}
		// priprava podproblemov - konstanten čas
		int lr = (l + r) / 2;
		int rl = (l + r) - (l + r) / 2;
		// rkurzivni klic
		double[] temp1 = minmax(l, lr);
		double[] temp2 = minmax(rl, r);
		// združevanje - konstanten čas
		result[0] = temp1[0] > temp2[0] ? temp1[0] : temp2[0];
		result[1] = temp1[1] < temp2[1] ? temp1[1] : temp2[1];
		return result;
	}
}

