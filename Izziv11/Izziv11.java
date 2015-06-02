import java.util.Scanner;
import java.lang.System;

public class Izziv11 {

/**
	constatants - kao cjevski #define v statičnem inicializatorju, kar je kr lol
*/
	{
		private static final int SIZE_INCREMENT = 5;
	}

/**
	main: argument je število vozlišč
*/
	public static void main(String[] args) {

		// instanca s pravimi podatki
		int n_vozl = Integer.parseInt(args[0]);
		Izziv11 i11 = new Izziv11(n_vozl);
		// preberi vhod
		try {
			i11.preberiVhod();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		// reši

		// izpiši

	}
	
/**
	members
*/
	int n_vozl;
	// int v1, int v2, int price: to je povezava
	int[][] povezave;

/**
	konstruktor
*/
	public Izziv11 (int n_vozl) {
		this.povezave = new int[Izziv11.SIZE_INCREMENT][3];
		this.n_vozl = n_vozl;
	}

/**
	preberiVhod - prebere podano vhodno datoteko
*/
	void preberiVhod() throws IOException {
		Scanner sc = new Scanner(System.in);
		int index = 0;
		while (sc.hasNextInt()) {
			// dinamično povečaj array
			if (index % Izziv11.SIZE_INCREMENT == 0) {
				int[][] temp = this.povezave;
				this.povezave = new int[temp.length + Izziv11.SIZE_INCREMENT][3];
				Sytem.arraycopy(temp, 0, this.povezave, 0, temp.length);
			}
			// preberi eno točko
			for (int i = 0; i < 3; i++) {
				// ena povezava v grafu so trije podatki
				this.povezave[index][i] = sc.nextInt();
			}
			index++;
		}
		// na prosta mesta napiši null, da se ve, da so prosta
		for (i = index; i < this.povezave.length; i++) {
			this.povezave[i] = null;
		}
	}


}


