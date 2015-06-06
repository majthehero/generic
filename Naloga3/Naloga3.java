import java.io.IOException;
import java.util.Scanner;

public class Naloga3 {

	private static final int SIZE_INCREMENT = 10;

	// main
	public static void main(String[] args) {
		// naredi sebe z argumentov
		String alg = args[0];
		Naloga3 n3 = new Naloga3(alg);
		// reši
		n3.run();
	}

	// pomembne
	/** v resitev
	 * m pomeni modro pobarvano, še ne pobarvano
	 * z pomeni zeleno pobarvano, izbrano
	 * r pomeni rdeèe pobarvano, neizbrano
	 */
	void sestopanje (int index) {

// poglej, če je že preveč
		if (index >= nV) return;



// zeleno
		resitev[index] = 'z';
		System.out.println(index + "Z");
		// poglej, če je rešitev
		boolean done = true;
		for (int[] e : edges) {
			int v1 = e[0];
			int v2 = e[1];
			char c1 = resitev[v1];
			char c2 = resitev[v2];
			if (!(c1 == 'z' || c2 == 'z')) {
				done = false;
			}
		}
		// izpiši rešitev, če je rešitev
		if (done) {
			System.out.print("R: ");
			for (int i = 0; i < resitev.length; i++) {
				if (resitev[i] == 'z') 
					System.out.print(i + " ");
			}
			System.out.println();
			// če je idealna rešitev, jo shrani
			int stZel = 0;
			for (char z : resitev) {
				if (z == 'z') stZel++;
			}
			if (stZel < idealnaLen) {
				this.idealnaLen = stZel;
				this.idealna = this.resitev;
			}
		} else {
			// rekourzija
			sestopanje(index + 1);
		}


// rdeče
		resitev[index] = 'r';
		System.out.println(index + "R");
		// poglej, če je konec vsega
		boolean fail = false;
		for (int[] e : edges) {
			int v1 = e[0];
			int v2 = e[1];
			if (v2 > index) {
				break;
			}
			char c1 = resitev[v1];
			char c2 = resitev[v2];
			if (c1 == 'r' && c2 == 'r') {
				fail = true;
			}
		}
		// sporoči neuspeh pop potrebi
		if (fail) {
			System.out.println("-");
		} else {
			sestopanje(index + 1);
		}



	}
	
	
	
	
	char[] boljaResitev (char[] r1, char[] r2) {
		int q1 = 0;
		int q2 = 0;
		for (int i = 0; i < nV; i++) {
			if (r1[i] == 'z') q1++;
			if (r2[i] == 'z') q2++;
		}
		if (q1 < q2) return r1;
		else return r2;
	}
	
	void sestopanje () {
		// klièi reševanje
		sestopanje(0);
		// optimalna rešitev mora biti to
		System.out.print("R: ");
		int i = 0;
		for (char z : idealna) {
			if (z == 'z') {
				System.out.print(i + " ");
			}
			i++;
		}
		System.out.println();
	}
	
	void run () {
		switch (this.alg) {
		case "se":
			sestopanje();
			break;
		default:
			System.out.println("Not implemented or wrong first argument.");
			System.exit(2);
		}
	}
	
	// members
	String alg;
	
	int nV;
	int[][] edges;
	char[] resitev;
	char[] idealna;
	int idealnaLen;
	
	// konstruktor
	public Naloga3 (String alg) {
		this.alg = alg;
		Scanner sc = new Scanner(System.in);
		// preberi število vozlišè
		this.nV = sc.nextInt();
		// preberi pare toèk, ki so vozlišèa
		this.edges = new int[nV][2];
		try {
			preberiVhod(sc);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		sc.close();
		// pripravi rešitev
		this.resitev = new char[nV];
		for (int i = 0; i < resitev.length; i++) {
			resitev[i] = 'm'; 
		}
		idealna = new char[nV];
		idealnaLen = Integer.MAX_VALUE;
	}
	
	// preberi vhod
	void preberiVhod(Scanner sc) throws IOException {
		int index = 0;
		int i = 0;
		while (sc.hasNextInt()) {
			// dinamièno poveèaj array
			if (index % SIZE_INCREMENT == 0) {
				int[][] temp = this.edges;
				this.edges = new int[temp.length + SIZE_INCREMENT][3];
				System.arraycopy(temp, 0, this.edges, 0, temp.length);
			}
			// preberi eno povezavo
			for (i = 0; i < 2; i++) {
				// ena povezava v grafu so trije podatki
				this.edges[index][i] = sc.nextInt();
			}
			index++;
		}
		// zmanjšaj tabelo do potrebne velikosti
		int[][] temp = this.edges;
		this.edges = new int[index][3];
		System.arraycopy(temp, 0, this.edges, 0, this.edges.length);
	}
	
}
