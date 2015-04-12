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
		/*int N = 0;
		if (args.length > 3)
			N = Integer.parseInt(args[3]);*/
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
				quickSort(0, this.N - 1);
				break;
			case "ms":
				mergeSort(0, this.N - 1);
				break;
			default:
				System.out.println("Napačen argument: algoritem: "
					+ this.mode);
				System.exit(1);
		}
	}
	
	void bubbleSort() {
		int index;
		for (index = 0; index < this.N; index++) {
			if (this.mode.equals("trace")) {
				for (int i = 0; i < this.table.length; i++) {
					if (index == i)
						System.out.print("| ");
					System.out.print(this.table[i] + " ");
				}
				System.out.println();
			}
			for (int i = this.N - 1; i > 0; i--) {
				if (needSwap(this.table[i - 1], this.table[i])) {
					swap(i - 1, i);
				}
			}
		}
	}
	
	void selectionSort() {
		int j;
		for (j = 0; j < this.N - 1; j++) {
			if (this.mode.equals("trace")) {
				for (int i = 0; i < this.table.length; i++) {
					if (j == i)
						System.out.print("| ");
					System.out.print(this.table[i] + " ");
				}
				System.out.println();
			}
			int iX = j;
			for (int i = j + 1; i < this.N; i++) {
				if (needSwap(this.table[iX], this.table[i]))
					iX = i;
			}
			if (iX != j)
				swap(iX, j);
		}
		if (this.mode.equals("trace")) {
			for (int i = 0; i < this.table.length; i++) {
				if (j == i)
					System.out.print("| ");
				System.out.print(this.table[i] + " ");
			}
			System.out.println();
		}
	}
	
	void insertionSort() {
		for (int i = 0; i < this.N; i++) {
			int j = i;
			boolean crtaDone;
			while (j > 0 && needSwap(this.table[j - 1], this.table[j])) {
				swap(j - 1, j);
				j--;
			}
			if (this.mode.equals("trace")) {
				crtaDone = false;
				for (int k = 0; k < this.table.length; k++) {
					if (k == i + 1) {
						System.out.print("| ");
						crtaDone = true;
					}
					System.out.print(this.table[k] + " ");
				}
				if (!crtaDone) 
					System.out.print("| ");
				System.out.println();
			}
		}
	}
	
	void heapSort() {
		buildHeap();
		hsSort();
	}
	
	void quickSort(int left, int right) {
		if (left >= right)
			return;
		int pivot = (left + right) / 2;
		System.out.printf("Pivot: %d\n", pivot);
		int i = left;
		int j = right;
		while (i <= j) {
			while (needSwap(this.table[pivot], this.table[i]))
				i++;
			while (needSwap(this.table[j], this.table[pivot]))
				j--;
			if (i <= j) {
				System.out.printf("Pivot trenutno: %d:%d\t", 
									pivot,this.table[pivot]);
				System.out.printf("Swap: %d:%d  with  %d:%d\n", 
							i,this.table[i], j,this.table[j]);
				swap(i, j);
				i++;
				j--;
			}
		}
		for (int ii = left; ii <= right; ii++) {
			if (ii == i)
				System.out.print("| ");
			if (ii == j+1)
				System.out.print("| ");
			System.out.print(this.table[ii] + " ");
		}
		System.out.println();
		if (left < j)
			quickSort(left, j);
		if (i < right)
			quickSort(i, right);	
	}
	
	void mergeSort(int left, int right) {
		if (left == right) {
			return;
		}
		int leftright = (left + right) / 2;
		int rightleft = leftright + 1;
		if (this.mode.equals("trace")) {
			for (int i = left; i <= right; i++) {
				System.out.print(this.table[i] + " ");
				if (i == leftright)
					System.out.print("| ");
			}
			System.out.println();
		}
		mergeSort(left, leftright);
		mergeSort(rightleft, right);
		int[] copy = new int[right - left + 1];
		System.arraycopy(this.table, left, copy, 0, right - left + 1);
		int iL = 0;
		int iR = (copy.length) - ((copy.length) / 2);
		for (int i = 0; i < copy.length; i++) {
			 if (isBetter(copy[iR], copy[iL])) {
				this.table[i + left] = copy[iR];
				iR++;
			} else {
				this.table[i + left] = copy[iL];
				iL++;
			}
			if (iL >= (copy.length) - ((copy.length) / 2)) {
				iL = 0;
				copy[iL] = this.direction.equals("up") ? 
					Integer.MAX_VALUE : Integer.MIN_VALUE;
			}
			if (iR >= copy.length) {
				iR = (copy.length) - ((copy.length) / 2);
				copy[iR] = this.direction.equals("up") ? 
					Integer.MAX_VALUE : Integer.MIN_VALUE;
			}
		}
		if (this.mode.equals("trace")) {
			for (int i = left; i <= right; i++) {
				System.out.print(this.table[i] + " ");
			}
			System.out.println();
		}
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
        if (left < N && needSwap(table[left], table[i]))
            largest = left;
        else
            largest = i;
        if (right < N && needSwap(table[right], table[largest]))
            largest = right;
            
        if (largest != i) {
            swap(i, largest);
            heapify(largest);
        }
	}
	// za bs oz splošno
	boolean isBetter(int i1, int i2) {
		if (this.direction.equals("up")) {
			return (i1 < i2);
		} else if (this.direction.equals("down")) {
			return (i1 > i2);
		} else {
			System.out.println("Napačen argument: način urejanja: "
					+ this.direction);
			System.exit(2);
		}
		return false;
	}
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
