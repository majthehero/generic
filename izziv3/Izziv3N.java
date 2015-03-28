import java.util.Scanner;

public class Izziv3N {
	
	int[] tabela;
	int N;
	
	public static void main(String[] args) {
		Izziv3N izziv = new Izziv3N();
		izziv.N = Integer.parseInt(args[0]);
		izziv.run();
	}
	
	void run() {
		this.tabela = new int[N];
		Scanner sc = new Scanner(System.in);
		for (int i=0; i<N; i++) {
			tabela[i] = sc.nextInt();
		}
		buildHeap();
		heapSort();
	}
	
	void heapSort() {
		buildHeap();
		while (N > 1) {
			swap(0, N - 1);
			N--;
			heapify(0);
			printout();
		}
	}
	
	void printout() {
		for (int i=0; i<N; i++) {
			System.out.print(tabela[i] + " ");
			if (((i+1) & (i)) == 0) {
				System.out.print("| ");
			}
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
        if (left < N && tabela[left] > tabela[i])
            largest = left;
        else
            largest = i;
            
        if (right < N && tabela[right] > tabela[largest])
            largest = right;
            
        if (largest != i) {
            swap(i, largest);
            heapify(largest);
        }
	}
	
	void swap(int i1, int i2) {
		int temp = tabela[i1];
		tabela[i1] = tabela[i2];
		tabela[i2] = temp;
	}

}
