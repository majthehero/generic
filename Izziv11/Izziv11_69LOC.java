import java.util.Scanner;
import java.lang.System;
import java.io.IOException;
public class Izziv11 {
	static final int SIZE_INCREMENT = 50;
	public static void main(String[] args) {
		boolean DEBUG = true; 
		int n_vozl = Integer.parseInt(args[0]);
		Izziv11 i11 = new Izziv11(n_vozl);
		try {
			i11.preberiVhod();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		i11.belllmanFord();
		i11.izpis();
	}
	int n_vozl;
	int[][] povezave;
	int[][] cene;
	public Izziv11 (int n_vozl) {
		this.povezave = new int[Izziv11.SIZE_INCREMENT][3];
		this.n_vozl = n_vozl;
		this.cene = new int[n_vozl][n_vozl];
	}
	void izpis() {
		for (int i = 0; i < cene.length; i++) {
			System.out.printf("h%d: ", i);
			for (int j = 0; j < cene[i].length; j++) {
				if (cene[i][j] == 1073741823) {
					System.out.print("Inf ");
				} else {
					System.out.printf("%d ", cene[i][j]);
			}	}
			System.out.println();
	}	}
	void belllmanFord () {
		this.cene[0][0] = 0;
		for (int i = 1; i < n_vozl; i++)
			this.cene[0][i] = Integer.MAX_VALUE / 2;
		for (int maxPathLen = 1; maxPathLen < n_vozl; maxPathLen++) {
			for (int currVertex = 1; currVertex < n_vozl; currVertex++) {
				int novaCena = cene[maxPathLen - 1][currVertex];
				for (int edge = 0; edge < povezave.length; edge++) {
					if (this.povezave[edge][1] == currVertex) {
						int potencialnaCena = povezave[edge][2] + 
								cene[maxPathLen - 1][povezave[edge][0]];
						if (potencialnaCena < novaCena) novaCena = potencialnaCena;
	}	}	}	}	}
	void preberiVhod() throws IOException {
		Scanner sc = new Scanner(System.in);
		int index = 0;
		int i = 0;
		while (sc.hasNextInt()) {
			if (index % Izziv11.SIZE_INCREMENT == 0) {
				int[][] temp = this.povezave;
				this.povezave = new int[temp.length + Izziv11.SIZE_INCREMENT][3];
				System.arraycopy(temp, 0, this.povezave, 0, temp.length);
			}
			for (i = 0; i < 3; i++) {
				this.povezave[index][i] = sc.nextInt();
			}
			index++;
		}
		int[][] temp = this.povezave;
		this.povezave = new int[index][3];
		System.arraycopy(temp, 0, this.povezave, 0, this.povezave.length);
}	}