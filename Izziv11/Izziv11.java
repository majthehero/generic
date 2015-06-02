import java.util.Scanner;

public class Izziv11 {

	/**
	main: argument je število vozlišč
	*/
	public static void main(String[] args) {

		// instanca s pravimi podatki
		int n_vozl = Integer.parseInt(args[0]);
		Izziv11 i11 = new Izziv11(n_vozl);
		// preberi vhod
		i11.preberiVhod();
		// reši

		// izpiši

	}
	/**
	members
	*/
	int n_vozl;
	Povezava[] povezave;

	/**
	preberiVhod - prebere podano vhodno datoteko
	*/
	void preberiVhod() {
		Scanner sc = new Scanner(System.in);
		while (sc.hasNextInt()) {
			for (int i = 0; i < 3; i++) {
				
			}
		}
	}

	/**
	konstruktor
	*/
	public Izziv11 (int n_vozl) {
		this.n_vozl = n_vozl;
	}

}


/**
RAZRED POVEZAVA
vključuje vhodne vertexe in izhodni vertex
*/
class PovezavaIn {
	/**
	MEMBERS
	*/
	int outVertex;
	int[] inVertices[];

	/**
	KONSTRUKTOR
	*/
	PovezavaIn(int outVertex, int[] inVertices) {
		this.inVertices = inVertices;
		this.outVertex = outVertex;
	}

	/**
	DODAJ VHODNO POVEZAVO
	*/
	void AddInVertex(int inVertex) {
		int[] temp = this.inVertices;
		this.inVertices = new int[temp.length +1];
		int i = 0;
		for (i = 0; i < temp.length; i++) {
			this.inVertices[i] = temp[i];
		}
		this.inVertices[i] = inVertex;
	}
}