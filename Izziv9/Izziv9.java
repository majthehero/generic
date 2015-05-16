public class Izziv9 {
	
	int[][] x;
	int n;
	int k;
	int function;

	public static void main (String[] args) {
		Izziv9 i9 = new Izziv9(args);
		i9.run();
	}

	public Izziv9 (String[] args) {
		try {
			this.n = Integer.parseInt(args[1]);
			if (args[0].equals("jajca")) {
				this.k = Integer.parseInt(args[2]);
				this.function = 1;
			}
			else {
				this.k = this.n;
				this.function = 0;
			}
		} catch (IndexOutOfBoundsException e) {
			pomoc();
			System.exit(1);
		}
	}

	void run() {
		switch (this.function) {
			case 0:
				pascal();
				break;
			case 1:
				jajca();
				break;
			default:
				pomoc();
				break;
		}
	}
	
	void pascal() {
		// initialize array according to n
		this.x = new int[n][n+1];
		// first number is trivial case
		this.x[0][1] = 1;
		for (int i = 1; i < n; i++) {
			for (int j = 1; j < n+1; j++) {
				this.x[i][j] = this.x[i-1][j-1] + this.x[i-1][j];
			}
		}
		// printout
		for (int i = 0; i < n; i++) {
			for (int j = 1; j < n + 1; j++) {
				if (j <= i+1)
					System.out.print(this.x[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	void jajca() {
		// initialize array according to n
		this.x = new int[n][n+1];
		// fill array with starting values
		for (int i = 0; i < n+1; i++) {
			this.x[1][i] = 1;
		}
		for (int i = 0; i < n; i++) {
			this.x[i][0] = i;
		}
		// calculate the rest
		for (int y = 2; y < n; y++) { // št vrstice
			int max = 0;
			for (int x = 1; x < n+1; x++) { // št stolpca
				// poišči najbolši par glede na slabšega v paru
				int ly = 1;
				int ry = y-1;
				int lx = x-1;
				int rx = x;
				while (ly < y && ry > 0) {
		//		System.out.printf("x: %d y: %d :: ly: %d ry: %d lx: %d rx: %d\n",
		//					x, y, ly, ry, lx, rx);
					int left = this.x[ry][lx];
					int right = this.x[ly][rx];
					int localmin;
					localmin = left < right ? left : right;
					if (localmin > max) {
						max = localmin;
					}
					ly++;
					ry--;
		//			System.out.printf("left: %d right: %d localmin: %d max: %d\n",
		//				left, right, localmin, max);
				}
				this.x[y][x] = max + 1;
		//		System.out.printf("%d %d  ", y, x);
			}
		//	System.out.printf("\n");
		}

		/*
		for (int i = 1; i < n; i++) { // št vrstice
			for (int j = 1; j < n + 1; j++) { // št stolpca
				// find worst pair
				int max = 0;
				int left_i = 0;
				int right_i = j - 1;
				for (int delta = 0; delta < j - 1; delta++) {
					int l = left_i + delta;
					int r = right_i - delta;
					if (min( x[l][j - 1], x[r][j]) > max)
						max = min( x[l][j - 1], x[r][j]);
				}
				
				// write worst + 1
				this.x[i][j] = max + 1;
			}
		}
		*/

		// printout
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n + 1; j++) {
				System.out.print(this.x[i][j] + " ");
			}
			System.out.println();
		}
	}

	int min(int a, int b) {
		return a < b ? a : b;
	}
	
	void pomoc() {
		System.out.printf("Napacna uporaba programa.\n");
		System.out.printf("java Izziv9 <funkcija> <arg0> <arg1>\n");
		System.out.printf("\n\tFunkcija je lahko 'pascal' za izpis ");
		System.out.printf("pascalovega trikotnika\n");
		System.out.printf("\tali 'jajca' za izpis tabele strategij za metanje jajc.\n");
		System.out.printf("Funkcija 'pascal' sprejme kot argument globino, ");
		System.out.printf("ki naj jo program izpiše.\n\n");
		System.out.printf("Funkcija 'jajca' sprejme kot argument število nadstropij");
		System.out.printf("in število jajc\n\n");
	}

}
