public class Izziv9 {
	
	int[][] x;
	int n;
	int k;
	int function;

	public static void main (String[] args) {
		Izziv9 i9 = new Izziv9(args);
		i9.run();
	}

	public Izziv9 (args) {
		this.n = Integer.parseInt(args[0]);
		try (IndexOutOdBoundsException e) {


	}

	void run() {
		switch (args[0]) {
			case "pascal":
				pascal();
				break;
			case "jajca":
				jajca();
				break;
			default:
				pomoc();
				break;
		}
	}
	
	void pascal() {
	}
	
	
	
	void pomoc() {
		System.out.printf("Napacna uporaba programa.\n");
		System.out.printf("java Izziv9 <funkcija> <arg0> <arg1>\n");
		System.out.printf("\n\tFunkcija je lahko 'pascal' za izpis pascalovega trikotnika\n");
		System.out.printf("\tali 'jajca' za izpis tabele strategij za metanje jajc.\n");
		System.out.printf("Funkcija 'pascal' sprejme kot argument globino, ");
		System.out.printf("ki naj jo program izpiše.\n\n");
		System.out.printf("Funkcija 'jajca' sprejme kot argument število nadstropij");
		System.out.printf("in število jajc\n\n");
	}

}
