import java.util.Scanner;

public class Naloga1_1 {
	
	int[] table;
	int N;
	
	String mode;
	String algorithm;
	String direction;
	
	public static void main(String[] args) {
		// args[0] - način delovanja - trace / count
		String mode = args[0];
		// args[1] - oznaka algoritma - bs/ss/is/hs/qs/ms
		String algorithm = args[1];
		// args[2] - smer urejanja - up/down
		String direction = args[2];
		// args[3] - optional - N - ignored
		int N = 0;
		if (args.length > 3)
			N = Integer.parseInt(args[3]);
		Naloga1_1 n = new Naloga1_1(mode, algorithm, direction);
		n.run();
	}
	
	public Naloga1_1 (String mod, String alg, String dir) {
		this.mode = mod;
		this.algorithm = alg;
		this.direction = dir;
		this.table = new int[1];
		this.N = 0;
	}
	
	void run() {
		// preberi tabelo
		Scanner sc = new Scanner(System.in);
		while (sc.hasNextInt()) {
			this.N++;
			int[] tt = new int[this.N];
			System.arraycopy(this.table, 0, tt, 0, this.N - 1);
			tt[this.N - 1] = sc.nextInt();
			this.table = tt;
			
		}
		///
		for (int i : this.table) {
			System.out.print(i);
		}
		System.out.println();
		///
		
		// pokliči pravi sortirni algoritem
		switch  (this.algorithm) {
			case "bs": 	
				bubbleSort();
				break;
			case "ss":
				selectionSort();
				break;
			case "is":
				insertionSort();
				break;
			case "hs":
				heapSort();
				break;
			case "qs":
				quickSort();
				break;
			case "ms":
				mergeSort();
				break;
			default:
				System.out.println("Napačen argument: algoritem: "
					+ this.mode);
				System.exit(1);
		}
	}
	
	void bubbleSort() {
		for (int index = 0; index < this.N; index++) {
			///
			for (int i = 0; i < this.table.length; i++) {
				if (index == i)
					System.out.print("| ");
				System.out.print(this.table[i] + " ");
			}
			System.out.println();
			///
			for (int i = 0; i < this.N - 1; i++) {
				if (needSwap(this.table[i], this.table[i + 1])) {
					swap(i, i + 1);
				}
			}
		}
	}
	
	void selectionSort() {;}
	void insertionSort() {;}
	
	void heapSort() {
		buildHeap();
		///
		for (int i = 0; i < this.table.length; i++) {
			System.out.print(this.table[i] + " ");
		}
		System.out.println();
		///
		hsSort();
	}
	
	
	void quickSort() {;}
	void mergeSort() {;}
	
	
	
	// za bs
	boolean needSwap(int i1, int i2) {
		if (this.direction.equals("up"))
			return (i1 > i2);
		else if (this.direction.equals("down"))
			return (i1 < i2);
		else {
			System.out.println("Napačen argument: način urejanja: "
					+ this.direction);
			System.exit(2);
		}
		return false;
	}
	
	void swap(int i1, int i2) {
		int temp = table[i1];
		table[i1] = table[i2];
		table[i2] = temp;
	}
}
