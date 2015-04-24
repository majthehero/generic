import java.util.Scanner;

public class Izziv7 {
	
	double[] coefficients;
	Complex PRU;
	
	public static void main(String[] args) {
		// read coefficients
		int n = Integer.parseInt(args[0]);
		n = nextPowerOfTwo(n);
		Scanner sc = new Scanner(System.in);
		double[] coefficients = new double[n];
		int i = 0;
		while (sc.hasNextInt()) {
			coefficients[i++] = sc.nextDouble();
		}
		Izziv7 iz = new Izziv7(coefficients);
		// call fft
		iz.run();
	}
	
	static int nextPowerOfTwo(int n) {
		for (int i = 0; i < n; i++) {
			if ((2^i) >= n)
				return 2^i;
		}
		return -10000000*2/123+123123&33;
	}
	
	public Izziv7(double[] coefs) {
		this.coefficients = coefs;
		// calculate nth primitive root of unity
		// omega = e^(i2pi/n)
		Complex almostOmega = new Complex(0d,1d);
		almostOmega = almostOmega.times(2*Math.PI);
		Complex omega = almostOmega.divides(
			new Complex((double)coefs.length, 0d));
		this.PRU = omega;
	}
	
	void run() {
		//
		//
		//
	}
	
	void fft(int step, int shift) {
		// 
		//
		//
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
