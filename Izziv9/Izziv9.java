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
		for (int i = 0; i < n; i++) {
			for (int j = 1; j < n+1; j++) {
				if (j <= i+1)
					System.out.print(this.x[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	void jajca() { ;; }
	
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
