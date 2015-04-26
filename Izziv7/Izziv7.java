import java.util.Scanner;
public class Izziv7 {
	double[] coefficients;
	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		Scanner sc = new Scanner(System.in);
		double[] coefficients = new double[n];
		int i = 0;
		while (sc.hasNextDouble()) {
			coefficients[i] = sc.nextDouble();
			i++;
			if (i > n)
				break;
		}
		Izziv7 iz = new Izziv7(coefficients);
		iz.run();
	}
	static int nextPowerOfTwo(int n) {
		for (int i = 1; i < n; i++) {
			if ((2^i) >= n) {
				return 2^i;
			}
		}
		return -10000000*2/123+123123&33;
	}
	public Izziv7(double[] coefs) {
		this.coefficients = coefs;
	}
	void run() {
		Complex[] res = fft(this.coefficients);
	}
	Complex[] fft(double[] arr) {
		if (arr.length <= 1) {
			Complex[] trivRes = new Complex[1];
			Complex tempRes = new Complex(arr[0], 0d);
			trivRes[0] = tempRes;
			return trivRes;
		}
		double[] sodi = new double[arr.length/2];
		double[] lihi = new double[arr.length/2];
		int stevec = 0;
		for (int i = 0; i < sodi.length; i++) {
			sodi[i] = arr[i * 2];
			lihi[i] = arr[i * 2 + 1];
		}
		Complex[] sodiRes = fft(sodi);
		Complex[] lihiRes = fft(lihi);
		Complex[] arrRes = new Complex[arr.length];
		Complex wk = new Complex(1d, 0d);
		Complex w = new Complex(0d, 2 * Math.PI / (double) arr.length);
		w = w.exp();
		for (int i = 0; i < sodiRes.length; i++) {
			arrRes[i] = sodiRes[i].plus(wk.times(lihiRes[i]));
			arrRes[i + arr.length/2] = 
						sodiRes[i].minus(wk.times(lihiRes[i]));
			wk = wk.times(w);
		}
		for (Complex x : arrRes) {
			System.out.print(x + " ");
		}
		System.out.println();
		return arrRes;
	}
}
/////////////////////////////////////////////////////////// COMPLEX TYPE
class Complex{
	double re;
	double im;
    public Complex(double real, double imag) {
        re = real;
        im = imag;
    }
    public String toString() {
    	double tRe = (double)Math.round(re * 100000) / 100000;
    	double tIm = (double)Math.round(im * 100000) / 100000;
        if (tIm == 0) return tRe + "";
        if (tRe == 0) return tIm + "i";
        if (tIm <  0) return tRe + "-" + (-tIm) + "i";
        return tRe + "+" + tIm + "i";
    }
    // sestevanje 
    public Complex plus(Complex b) {
        Complex a = this;             
        double real = a.re + b.re;
        double imag = a.im + b.im;
        return new Complex(real, imag);
    }
    // odstevanje
    public Complex minus(Complex b) {
        Complex a = this;
        double real = a.re - b.re;
        double imag = a.im - b.im;
        return new Complex(real, imag);
    }
    // mnozenje z drugim kompleksnim stevilom
    public Complex times(Complex b) {
        Complex a = this;
        double real = a.re * b.re - a.im * b.im;
        double imag = a.re * b.im + a.im * b.re;
        return new Complex(real, imag);
    }
    // mnozenje z realnim stevilom
    public Complex times(double alpha) {
        return new Complex(alpha * re, alpha * im);
    }
    // reciprocna vrednost kompleksnega stevila
    public Complex reciprocal() {
        double scale = re*re + im*im;
        return new Complex(re / scale, -im / scale);
    }
    // deljenje
    public Complex divides(Complex b) {
        Complex a = this;
        return a.times(b.reciprocal());
    }
    // e^this
    public Complex exp() {
        return new Complex(Math.exp(re) * Math.cos(im), 
						Math.exp(re) * Math.sin(im));
    }
    //potenca komplesnega stevila
    public Complex pow(int k) {
    	Complex c = new Complex(1,0);
    	for (int i = 0; i <k ; i++) {
			c = c.times(this);
		}
    	return c;
    }
}
