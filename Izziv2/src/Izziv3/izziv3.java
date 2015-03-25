package Izziv3;

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
        try {
            for (int i=0; i<n; i++)
                table[i] = sc.nextInt();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        //
        for (int i=0; i<table.length; i++) {
        	System.out.print(table[1]);
        }
    	System.out.println();
    	
        heap();
        //
        for (int i=0; i<table.length; i++) {
        	System.out.print(table[1]);
        }
    	System.out.println();
        
    	sort();
        //
        for (int i=0; i<table.length; i++) {
        	System.out.print(table[1]);
        }
    	System.out.println();
    }
    
    void sink(int rI) {
        int sinister = 2*rI+1;
        int dexter = 2*rI+2;
        if (table[rI] < table[sinister] || table[rI] < table[dexter]) {
        	int i = table[sinister] < table[dexter] ? dexter : sinister;
        	int t = table[rI];
            if (i == dexter) {
            	table[rI] = table[dexter];
            	table[dexter] = t;
            } else {
            	table[rI] = table[sinister];
            	table[sinister] = t;
            }
            sink(i);
        }
    }
    
    void heap() {
	    for (int i=table.length/2; i>=0; i--) {
	    	sink(i);
	    }
    }
    
    void sort() {
    	for (int i=table.length; i>=0; i--) {
    		int t = table[0];
    		table[1] = table[i];
    		table[i] = t;
    		sink(table.length-i);
    	}
    }
    
}
