/**
 * Author: Maj Smerkol
 * Bom dodal še risanje grafov.
 */

import java.util.Random;

public class Izziv2 {
	
	int t[];
	int el;
	long timesL[];
	long timesB[];
	
	public static void main(String[] args) {
		Izziv2 i = new Izziv2();
		i.run();
	}
	
	public Izziv2() {
		timesL = new long[91];
		timesB = new long[91];
	}
	
	public void run() {
		Random r = new Random();
		int c = 0;
		for (int i = 100000; i <= 100000; i += 10000) { // TODO
			generate(i);
			long tL[] = new long[1000];
			long tB[] = new long[1000];
			long aL = 0;
			long aB = 0;
			for (int j = 0; j < 1000; j ++) {
				el = r.nextInt(i + 1);
				long startTime = System.nanoTime();
				findBisection(0, t.length - 1);
				long executionTime = System.nanoTime() - startTime;
				tB[j] = executionTime;
				startTime = System.nanoTime();
				findLinear();
				executionTime = System.nanoTime() - startTime;
				tL[j] = executionTime;
			}
			for (int j = 0; j < tL.length; j ++) {
				aL += tL[j];
				aB += tB[j];
			}
			timesL[c] = aL / tL.length;
			timesB[c] = aB / tB.length;
			// debug
			System.out.println("n:" + i + "   linearno: " + timesL[c] + "  bisekcija: " + timesB[c]);
			//
			c++;
		}
		
		// plot
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Framex.createAndShowGUI();
            }
        });
	}
	
	public void generate(int n) {
		this.t = new int[n]; 
		for (int i=0; i<n; i++) t[i] = i;
	}
	
	public boolean findBisection(int low, int high) {
        if (low >= high) return false;
        int center = (low + high)/2;
        if (t[center] > el) return findBisection(low, center);
        else if (t[center] < el) return findBisection(center+1, high );
        return true;
    }
	
	public boolean findLinear() {
		for (int i=0; i<t.length; i++) if (t[i] == el) return true;
		return false;
	}

}
