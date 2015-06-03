import java.util.Scanner;
import java.lang.System;
import java.io.IOException;

public class Izziv11 {

/**
	constatants - kao cjevski #define v statičnem inicializatorju, kar je kr lol
*/
	static final int SIZE_INCREMENT = 50;
	
/**
	main: argument je število vozlišč
*/
	public static void main(String[] args) {
		// DEBUG
		boolean DEBUG = true; 

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
		/* test branja
		for (int i = 0; i < i11.povezave.length; i++) {
			// debug
			for (int j = 0; j < 3; j++) {
				System.out.print(i11.povezave[i][j] + " ");
			}
			System.out.println();
		} //*/
		
		// reši
		i11.belllmanFord();
		
		// izpiši
		i11.izpis();
		
	}
	
/**
	members
*/
	int n_vozl;
	// int v1, int v2, int price: to je povezava
	int[][] povezave;
	// tabela rešitev
	int[][] cene;
	
/**
	konstruktor
*/
	public Izziv11 (int n_vozl) {
		this.povezave = new int[Izziv11.SIZE_INCREMENT][3];
		this.n_vozl = n_vozl;
		this.cene = new int[n_vozl][n_vozl];
	}

/**
	izpis tabele rezultatov
*/
	void izpis() {
		for (int i = 0; i < cene.length; i++) {
			System.out.printf("h%d: ", i);
			for (int j = 0; j < cene[i].length; j++) {
				if (cene[i][j] == 1073741823) {
					System.out.print("Inf ");
				} else {
					System.out.printf("%d ", cene[i][j]);
				}
			}
			System.out.println();
		}
	}

/**
	bellman ford algoritem
*/
	void belllmanFord () {
		// inicialozacija začetnih cen na neskončno
		this.cene[0][0] = 0;
		for (int i = 1; i < n_vozl; i++)
			this.cene[0][i] = Integer.MAX_VALUE / 2;
		
		// zunanja zanka gre gledena maksimalno možno pot
		// kar je seveda število vozlišč, da lahko še
		// preverimo za negativne cikle
		for (int maxPathLen = 1; maxPathLen < n_vozl; maxPathLen++) {
			
			// za vsako vozlišče pogledamo naračunano ceno
			// in minimalno izmed poti, ki vodi vanj
			for (int currVertex = 1; currVertex < n_vozl; currVertex++) {
				int novaCena = cene[maxPathLen - 1][currVertex]; // to minimiziramo
				
				/*/ test
				System.out.printf("Sem v tocki %d.\n", currVertex); */
				
				// preišči vse povezave, da vidiš, katere grejo v to vozl
				for (int edge = 0; edge < povezave.length; edge++) {
					
					if (this.povezave[edge][1] == currVertex) {
						/*/ test da res najde prave povezave
						System.out.printf(	"Povezava %d -> %d stane %d.\n",
								povezave[edge][0], 
								povezave[edge][1], 
								povezave[edge][2] ); */
						
						// za vsako povezavo seštejem in preverim
						// s prejšnjo rešitvijo do prejšnje točke
						int potencialnaCena = povezave[edge][2] + 
								cene[maxPathLen - 1][povezave[edge][0]];
						if (potencialnaCena < novaCena) novaCena = potencialnaCena;
					}
				}
				
				// imaš novo minimalno rešitev
				cene[maxPathLen][currVertex] = novaCena;
			}
			
		}
	}

/**
	preberiVhod - prebere podano vhodno datoteko
*/
	void preberiVhod() throws IOException {
		Scanner sc = new Scanner(System.in);
		int index = 0;
		int i = 0;
		while (sc.hasNextInt()) {
			// dinamično povečaj array
			if (index % Izziv11.SIZE_INCREMENT == 0) {
				int[][] temp = this.povezave;
				this.povezave = new int[temp.length + Izziv11.SIZE_INCREMENT][3];
				System.arraycopy(temp, 0, this.povezave, 0, temp.length);
			}
			// preberi eno točko
			for (i = 0; i < 3; i++) {
				// ena povezava v grafu so trije podatki
				this.povezave[index][i] = sc.nextInt();
			}
			index++;
		}
		// zmanjšaj tabelo do potrebne velikosti
		int[][] temp = this.povezave;
		this.povezave = new int[index][3];
		System.arraycopy(temp, 0, this.povezave, 0, this.povezave.length);
	}

}


