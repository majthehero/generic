import java.util.Scanner;
import java.util.ArrayList;

public class Naloga2 {

	ArrayList<Integer> num1;
	ArrayList<Integer> num2;
	ArrayList<Integer> res;
	int op_count;

	String mode;
	String algorithm;
	
	/**
	MAIN
	**/
	public static void main(String[] args) {
		// args[0] - način delovanja - trace / count
		String mode = args[0];
		// args[1] - oznaka algoritma - os/ru/dv/ks
		String algorithm = args[1];
		
		Naloga2 n = new Naloga2(mode, algorithm);
		n.run();
	}

	/**
	CONSTRUCTOR
	**/
	public Naloga2 (String mode, String algorithm) {
		this.mode = mode;
		this.algorithm = algorithm;
		this.num1 = new ArrayList<Integer>();
		this.num2 = new ArrayList<Integer>();
		this.res = new ArrayList<Integer>();
		this.op_count = 0;
	}

	/**
	RUN
	**/
	public void run() {
		// preberi tabelo
		Scanner sc = new Scanner(System.in);
		String line1 = sc.nextLine();
		String line2 = sc.nextLine();
		for (char c : line2.toCharArray()) {
			this.num1.add(Character.getNumericValue(c));
		}
		for (char c : line1.toCharArray()) {
			this.num2.add(Character.getNumericValue(c));
		}
		// pokliči pravi sortirni algoritem
		switch  (this.algorithm) {
			case "os": 	
				osnovnosolski();
				break;
			case "ru":
				ruski();
				break;
			case "dv":
				naivniDV();
				break;
			case "ka":
				karatsuba();
				break;
			default:
				System.out.println("Napačen argument: algoritem: "
					+ this.mode);
				System.exit(1);
		}
	}

	
	/**
	
	NAIVNO DELI IN VLADAJ MNOŽENJE
	
	**/
	void naivniDV() {
		int n;
		if (this.num1.size() > this.num1.size()) {
			n = this.num1.size();
		} else {
			n = this.num2.size();
		}
		
		if (n % 2 == 1) n++;
		
		while (this.num1.size() < n) {
			this.num1.add(0,0);
		}
		while (this.num2.size() < n) {
			this.num2.add(0,0);
		}
		
		this.res = mnozi(this.num2, this.num1, n);
		
		if (this.mode.equals("count")) {
			System.out.println(this.op_count);
		}
		if (this.mode.equals("trace")) {
			boolean hasCherry = true;
			for (Integer o : this.res) {
				if (hasCherry && o.intValue() == 0)
					continue;
				hasCherry = false;
				System.out.print(o);
			}
			System.out.println();
		}
	}
	
	/**
	ZMNOZI Z NAIVNIM DELI IN VLADAJ DVE VELIKI STEVILI
	**/
	ArrayList<Integer> mnozi(	ArrayList<Integer> num1, 
								ArrayList<Integer> num2, int n) {
		if (n % 2 == 1) n++;
		
		num1 = stripZeros(num1);
		num2 = stripZeros(num2);
		// izpis
		if (this.mode.equals("trace")) {
			boolean hasCherry = true;
			for (Integer o : num1) {
				if (hasCherry && o.intValue() == 0)
					continue;
				hasCherry = false;
				System.out.print(o);
			}
			if (hasCherry) System.out.print(0);
			System.out.print(" ");
			hasCherry = true;
			for (Integer o : num2) {
				if (hasCherry && o.intValue() == 0)
					continue;
				hasCherry = false;
				System.out.print(o);
			}
			if (hasCherry) System.out.print(0);
			System.out.println();
		}
		
		/**/
		while (num1.size() < n) {
				num1.add(0,0);
		}
		while (num2.size() < n) {
				num2.add(0,0);
		}
		
		if (isZero(num1))
			return longZero();
		if (isZero(num2))
			return longZero();
		
		// preveri trivialnost
		if (num1.size() == 1) {
			return multiplicationWithLong(num2, num1.get(0).intValue());
		} else if (num2.size() == 1) {
			return multiplicationWithLong(num1, num2.get(0).intValue());
		}
		
		// rezdeli vsako stevilo na dve
		ArrayList<Integer> num1_1 = new ArrayList<Integer>();
		ArrayList<Integer> num1_2 = new ArrayList<Integer>();
		int i = 0;
		for (i = 0; i < n/2; i++) {
			num1_1.add(num1.get(i));
		}
		for (i = i; i < n/2; i++) {
			num1_2.add(num1.get(i));
		}
		
		ArrayList<Integer> num2_1 = new ArrayList<Integer>();
		ArrayList<Integer> num2_2 = new ArrayList<Integer>();
		i = 0;
		for (i = 0; i < n/2; i++) {
			num2_1.add(num2.get(i));
		}
		for (i = i; i < n/2; i++) {
			num2_2.add(num2.get(i));
		}
		
		// rek klic
		ArrayList<Integer> p1 = mnozi(num1_2, num2_2, n/2);
		
		
		ArrayList<Integer> p2_1 = mnozi(num1_2, num2_1, n/2);
		ArrayList<Integer> p2_2 = mnozi(num1_1, num2_2, n/2);
		
		ArrayList<Integer> p2 = addLong(p2_1, p2_2);
		for (i = 0; i < n/2; i++)
			p2.add(0);
		
		ArrayList<Integer> p3 = mnozi(num1_1, num2_1, n/2);
		for (i = 0; i < n; i++)
			p3.add(0);
					
		ArrayList<Integer> retval = addLong(p1, p2);
	
		retval = addLong(retval, p3);
		return retval;	
	}
	
	/**
	ALI JE DOLGO STEVILO NIC
	**/
	boolean isZero(ArrayList<Integer> num) {
		if (num.size() == 0) {
			return true;
		}
		for (Integer o : num) {
			if (o.intValue() != 0)
				return false;
		}
		return true;
	}
	
	/**
	POMNOZI VELIKO STEVILO Z ENO STEVKO (MALIM STEVILOM)
	**/
	ArrayList<Integer> multiplicationWithLong(ArrayList<Integer> num, int k) {
		ArrayList<Integer> retval = new ArrayList<Integer>();
		int carry = 0;
		int temp = 0;
		int mult = 0;
		for (int i = num.size() - 1; i >= 0; i--) {
			this.op_count++;
			temp = num.get(i).intValue() * k + carry;
			mult = temp % 10;
			carry = temp / 10;
			retval.add(0, new Integer(mult));
		}
		retval.add(0, new Integer(carry));
		retval.trimToSize();
		return retval;
	}
	
	/**
	NAREDI DOLGO NIČLO
	**/
	ArrayList<Integer> longZero() {
		ArrayList<Integer> retval = new ArrayList<Integer>();
		retval.add(0);
		return retval;
	}

	/**
	
	RUSKO MNOŽENJE
	
	**/
	void ruski() {
		if (this.mode.equals("count")) return;
		while (notOne(this.num2)) {
			boolean izpis = false;
			if (isOdd(this.num2)) {
				this.res = addLong(this.num1, this.res);
				izpis = true;
			}
			if (this.mode.equals("trace")) {
				// izpisi num2
				boolean hasCherry = true;
				for (Integer o : num2) {
					//if (hasCherry && o.intValue() == 0)
					//	continue;
					hasCherry = false;
					System.out.print(o.intValue());
				}
				System.out.print(" ");
				hasCherry = true;
				// izpisi num1
				for (Integer o : num1) {
					//if (hasCherry && o.intValue() == 0)
					//	continue;
					hasCherry = false;
					System.out.print(o.intValue());
				}
				System.out.print(" ");
				hasCherry = true;
				// izpisi boolean
				System.out.printf("%d\n", izpis ? 1 : 0);
			}
			this.num2 = stripZeros(halveLong(this.num2));
			this.num1 = stripZeros(doubleLong(this.num1));
		}
		boolean hasCherry = true;
		if (this.mode.equals("trace")) {
			for (Integer o : this.res) {
				if (hasCherry && o.intValue() == 0)
					continue;
				hasCherry = false;
				System.out.print(o);
			}
			System.out.println();
		}
		if (this.mode.equals("count"))
			System.out.println(this.op_count);
	}
	
	/**
	STRIP TRAILING ZEROS
	**/
	ArrayList<Integer> stripZeros(ArrayList<Integer> num) {
		boolean hasCherry = true;
		ArrayList<Integer> retval = new ArrayList<Integer>();
		for (Integer o : num) {
			if (hasCherry && o.intValue() == 0) {
				continue;
			}
			hasCherry = false;
			retval.add(o);
		}
		retval.trimToSize();
		return retval;
	}
	
	/**
	SESTEJ DVE VELIKI STEVILI
	**/
	ArrayList<Integer> addLong(	ArrayList<Integer> num1,
								ArrayList<Integer> num2) {
		ArrayList<Integer> retval = new ArrayList<Integer>();
		int res_n = (num1.size() > num2.size() ? num1.size() : num2.size()) + 1;
		int carry = 0;
		int sum = 0;
		int temp1 = 0;
		int temp2 = 0;
		for (int i = 0; i < res_n; i++) {
			if (i < num1.size()) {
				temp1 = num1.get(num1.size() - i -1);
			} else temp1 = 0;
			if (i < num2.size()) {
				temp2 = num2.get(num2.size() - i -1);
			} else temp2 = 0;
			
			sum = temp1 + temp2 + carry;
			carry = sum / 10;
			sum %= 10;
			
			retval.add(0, new Integer(sum));
		}
		retval.add(0, carry);
		retval.trimToSize();
		return retval;
	}
	
	/**
	RAZPOLOVI VELIKO STEVILO
	**/
	ArrayList<Integer> halveLong(ArrayList<Integer> num) {
		ArrayList<Integer> retval = new ArrayList<Integer>();
		int temp = 0;
		int carry = 0;
		for (int i = 0; i < num.size(); i++) {
			this.op_count++;
			temp = num.get(i).intValue() / 2 + carry;
			if (num.get(i).intValue() % 2 == 1) {
				carry = 5;
			} else {
				carry = 0;
			}
			retval.add(new Integer(temp));
		}
		if (temp == 1) retval.add(new Integer(1));
		//retval.add(new Integer(carry));
		retval.trimToSize();
		return retval;
	}
	
	/**
	PODVOJI VELIKO STEVILO
	**/
	ArrayList<Integer> doubleLong(ArrayList<Integer> num) {
		ArrayList<Integer> retval = new ArrayList<Integer>();
		int carry = 0;
		int temp = 0;
		int mult = 0;
		for (int i = num.size() - 1; i >= 0; i--) {
			this.op_count++;
			temp = num.get(i).intValue() * 2 + carry;
			mult = temp % 10;
			carry = temp / 10;
			retval.add(0, new Integer(mult));
		}
		retval.add(0, new Integer(carry));
		retval.trimToSize();
		return retval;
	}
	
	/**
	VELIKO STEVILO JE LIHO?
	**/
	boolean isOdd(ArrayList<Integer> num) {
		if (num.size() == 0) return false;
		return (num.get(num.size() - 1) % 2 == 1);
	}
	
	/**
	VELIKO STEVILO JE ENA
	**/
	boolean isOne (ArrayList<Integer> num) {
		boolean hasCherry = true;
		if (num.size() == 0) {
			return true;
		}
		if (num.get(num.size() - 1) != 1) {
			return false;
		}
		num.remove(num.size() - 1);
		for (Integer o : num) {
			if (o.intValue() != 0)
				return false;
		}
		return true;
	}
	
	/**
	VELIKO STEVILO NI ENA
	**/
	boolean notOne (ArrayList<Integer> num) {
		return !isOne(num);
	}
	
	/**
	
	KARATSUBA
	
	**/
	void karatsuba() { ;; }

	/**
	
	OSNONOSOLKSO MNOŽENJE
	
	**/
	void osnovnosolski() {
		int i = 0;
		int[] num1 = new int[this.num1.toArray().length];
		for (Integer o : this.num1) {
			num1[i++] = o.intValue();
		}
		i = 0;
		int[] num2 = new int[this.num2.toArray().length];
		for (Integer o : this.num2) {
			num2[i++] = o.intValue();
		}
		int res_n = num1.length + num2.length;
		int[][] result = new int[num1.length][res_n];
		int[] retval = new int[res_n];
		int shift	= 0;
		int mult 	= 0;
		int res 	= 0;
		int carry 	= 0;
		// po stevilkah levega stevila od leve proti desni
		for (i = 0; i < num1.length; i++) {
			if (num1[i] == 0)
				continue;
			// po stevilkah desnega, desne proti levi
			shift = num1.length - i - 1;
			int j;
			for (j = num2.length - 1; j >= 0; j--) {
				// zmozi
				this.op_count++;
				mult = num1[i] * num2[j] + carry;
				res = mult % 10;
				carry = mult / 10;
				result[i][j + num1.length - shift] = res;
				
			}
			result[i][j + num1.length - shift] = carry;
			carry = 0;
		}
		// izpis vmesnih rezultatov
		if (this.mode.equals("trace")) {
			for (i = 0; i < result.length; i++) {
				boolean hasCherry = true;
				shift = num1.length - i - 1;
				for (int j = 0; j < result[i].length - shift; j++) {
					if (result[i][j] == 0 && hasCherry) {
						continue;
					} else {
						hasCherry = false;
						System.out.print(result[i][j]);
					}
				}
				if (hasCherry)
					System.out.print("0");
				System.out.println();
			}
		}
		// po stolpcih rezultata seštevamo
		carry = 0;
		for (i = result[0].length - 1; i >= 0; i--) {
			int sum = carry;
			carry = 0;
			for (int j = 0; j < result.length; j++) {
				sum += result[j][i]; 
			}
			carry = sum / 10;
			sum %= 10;
			retval[i] = sum;
		}
		// izpis končnega rezultata
		if (this.mode.equals("trace")) {
			int count = 0;
			boolean hasCherry = true;
			for (int val : retval) {
				if (val == 0 && hasCherry) {
					continue;
				} else
				{
					hasCherry = false;
					this.res.add((Integer)val);
					count++;
				}
			}
			for (i = 0; i < count; i++) {
				System.out.print("-");
			}
			System.out.println();
			for (Integer val : this.res) {
				System.out.print(val.intValue());
			}
			System.out.println();
		}
		if (this.mode.equals("count")) {
			System.out.println(this.op_count);
		}
	}
}