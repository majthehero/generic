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
				quickSort(0, this.N);
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
	
	void selectionSort() {
		for (int j = 0; j < this.N - 1; j++) {
			int iX = j;
			for (int i = j + 1; i < this.N; i++) {
				if (needSwap(this.table[iX], this.table[i]))
					iX = i;
			}
			if (iX != j)
				swap(iX, j);
			///
			for (int k = 0; k < this.table.length; k++) {
				System.out.print(this.table[k] + " ");
			}
			System.out.println();
			///
		}
	}
	
	void insertionSort() {
		for (int i = 0; i < this.N; i++) {
			int j = i;
			while (j > 0 && needSwap(this.table[j - 1], this.table[j])) {
				swap(j - 1, j);
				j--;
			}
		}
	}
	
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
	
	void quickSort(int left, int right) { // TODO
		// izbira pivota
		int pivot = (left + right) / 2;
		
		// delitev tabele
		int i = left;
		int j = right - 1;
		while (i <= j) {
			while (needSwap(this.table[pivot], this.table[i]))
				i++;
			while (needSwap(this.table[j], this.table[pivot]))
				j--;
			if (i <= j) {
				swap(i, j);
				i++;
				j--;
			}
		}
		///
		for (i = left; i < pivot; i++)
			System.out.print(this.table[i] + " ");
		System.out.print("| ");
		for (i = pivot; i < right; i++)
			System.out.print(this.table[i] + " ");
		System.out.println();
		///
		// rekurzivni klic
		if (left < pivot)
			quickSort(left, pivot - 1);
		if (right > pivot)
			quickSort(pivot + 1, right);
	}
	
	void mergeSort() { // TODO
		
	}
	
	// za hs
	void hsSort () {
		buildHeap();
		printoutHS();
		while (N > 1) {
			swap(0, N - 1);
			N--;
			heapify(0);
			printoutHS();
		}
	}
	void printoutHS() {
		for (int i=0; i<N; i++) {
			if (((i+1) & (i)) == 0 && i > 0) {
				System.out.print("| ");
			}
			System.out.print(table[i] + " ");
		}
		System.out.println();
	}
	void buildHeap() {
        for (int i = N / 2; i >= 0; i--)
            heapify(i);
    }
	void heapify(int i) {
		int left = i * 2 + 1;
        int right = i * 2 + 2;
        int largest;
        if (left < N && needSwap(table[i], table[left]))
            largest = left;
        else
            largest = i;
        if (right < N && needSwap(table[largest], table[right]))
            largest = right;
            
        if (largest != i) {
            swap(i, largest);
            heapify(largest);
        }
	}
	// za bs oz splošno
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
