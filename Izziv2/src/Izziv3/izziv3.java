//package Izziv3;

import java.util.Scanner;

/**
 * @author majst3r
 */
public class izziv3 {
    int[] table;
    /**
     * @param args number of elements
     */
    public static void main(String[] args) {
    	int n = Integer.parseInt(args[0]);
        izziv3 i = new izziv3();
        i.run(n);
    }
    
    void run(int n) {
    	table = new int[n];
        Scanner sc = new Scanner(System.in);
		for (int i=0; i<n; i++)
			table[i] = sc.nextInt();
        //
        for (int i=0; i<table.length; i++) {
        	System.out.print(table[i]);
        }
    	System.out.println();
    	
    	buildHeap();
        //
        for (int i=0; i<table.length; i++) {
        	System.out.print(table[i]);
        }
    	System.out.println();
        //
        heapSort();
        //
        for (int i=0; i<table.length; i++) {
        	System.out.print(table[i]);
        }
    	System.out.println();
        //
    }
    
    void heapSort() {
		for (int i = table.length-1; i > 1; i--) {
			swap(table[i], table[0]);
			sink(i);
		}
	}
    
    void buildHeap() {
		for (int i = table.length/2; i >= 0; i--)
			sink(i);
	}
	
	void sink(int index) {
		int left = 2*index+1;
		int right = 2*index+2;
		// ni otrok
		if (left >= table.length || right >= table.length)
			return;
		// pogrezni po potrebi
		if (table[left] < table[index] || table[right] < table[index]){
			// zamenjaj z najmanjšim
			if (table[left] < table[right]) {
				swap(left, index);
				// rekurzivno
				sink(left);
			} else {
				swap(right, index);
				// rekurzivno
				sink(left);
			}
		}
	}
    
    void swap(int i1, int i2) {
		int temp = table[i1];
		table[i1] = table[i2];
		table[i2] = temp;
	}
}
