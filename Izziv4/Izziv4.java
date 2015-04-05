import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Vhodni trak. Omogoca zaporedno branje stevil in detekcijo konca zaporedja.
 * Podpora za zaporedja (angl. straight run) in cete (angl. natural run).<br><br>
 * Podporni razred pri predmetu Algoritmi in podatkovne strukture, FRI, Uni-Lj. 
 * @author jure
 */
class InTrack {
	Scanner in;			// bralnik stevil
	int current;		// zadnje prebrano stevilo
	int previous;		// predzadnje prebrano stevilo
	int pos;			// pozicija znotraj zaporedja, -1 pomeni konec traku

	/** Ustvari in odpre nov vhodni trak s podanim imenom.
	 * @param filename - ime traku
	 * @throws FileNotFoundException
	 */
	public InTrack(String filename) throws FileNotFoundException {
		open(filename);
	}

	/** Odpre trak s podanim imenom.
	 * @param filename - ime traku.
	 * @throws FileNotFoundException
	 */
	void open(String filename) throws FileNotFoundException {
		in = new Scanner(new BufferedReader(new FileReader(filename)));
		pos = -1;
		advance();
	}

	/** Zapre trak.
	 */
	void close() {
		in.close();
	}

	/** Premakne glavo traku za eno mesto naprej.
	 */
	void advance() {
		try {
			previous = current;
			current = in.nextInt();
			pos++;
		} catch (NoSuchElementException e) {
			pos = -1;
		}
	}

	/** Preveri, ce je konec traku.
	 */
	 boolean isEndOfTrack() {
		return pos < 0;
	}

	/** Resetira pozicijo na zazetek zaporedja/cete.
	 *  Ce je ze na koncu traku, potem ne naredi nic.
	 */
	void resetRun() {
		if (pos > 0) pos = 0;
	}
	
	/** Preveri, ce je konec zaporedja (angl. straight run)
	 */
	boolean isStraightEnd(int runLength) {
		return pos < 0 || pos >= runLength;
	}

	/** Preveri, ce je konec cete (angl. natural run)
	 */
	boolean isNaturalEnd() {
		return pos < 0 || pos > 0 && previous > current;   
	}

	/** Hkratno odpiranje vec vhodnih trakov.<br>
	 *  Imena trakov so trackName-pass-i, kjer je i od 0 do trackCount-1.
	 * @param trackName - predpona imena traku
	 * @param pass - zaporedna stevilka prehoda
	 * @param trackCount - stevilo trakov
	 * @return tabela trakov
	 * @throws FileNotFoundException
	 */
	static InTrack[] openTracks(String trackName, int pass, int trackCount) throws FileNotFoundException {
		InTrack[] ins = new InTrack[trackCount];
		for (int i = 0; i < trackCount; i++)
			ins[i] = new InTrack(trackName + '-' + pass + '-' + i);
		return ins;
	}
}


/**
 * Izhodni trak. Omogoca zaporedno pisanje stevil na trak.<br><br>
 * Podporni razred pri predmetu Algoritmi in podatkovne strukture, FRI, Uni-Lj.
 * @author jure
 */
class OutTrack {
	PrintWriter out;	// zapisovalnik stevil
	int last;			// zadnje zapisano stevilo

	/** Ustvari in odpre nov izhodni trak s podanim imenom.
	 * @param filename - ime traku
	 * @throws IOException
	 */
	public OutTrack(String filename) throws IOException {
		open(filename);
	}

	/** Odpre nov izhodni trak s podanim imenom.
	 * @param filename - ime traku
	 * @throws IOException
	 */
	void open(String filename) throws IOException {
		out = new PrintWriter(new BufferedWriter(new FileWriter(filename)));
	}

	/** Zapre trak
	 */
	void close() {
		out.close();
	}

	/** Na trak zapise stevilo in en presledek. Dodatno naredi se flush(), da je mozno
	 *  sprotno spremljanje vsebine traku.
	 * @param num - stevilo
	 */
	void write(int num) {
		last = num;
		out.print(num);
		out.print(' ');
		out.flush();
	}

	/** Hkratno odpiranje vec izhodnih trakov.<br>
	 *  Imena trakov so trackName-pass-i, kjer je i od 0 do trackCount-1.
	 * @param trackName - predpona imena traku
	 * @param pass - zaporedna stevilka prehoda
	 * @param trackCount - stevilo trakov
	 * @return tabela trakov
	 * @throws IOException
	 */
	static OutTrack[] createTracks(String trackName, int pass, int trackCount) throws IOException {
		OutTrack outs[] = new OutTrack[trackCount];
		for (int i = 0; i < trackCount; i++)
			outs[i] = new OutTrack(trackName + '-' + pass + '-' + i);
		return outs;
	}
}


public class Izziv4 {

	public static void main(String[] args) throws IOException {
		Izziv4 i = new Izziv4();
		i.run();
	}

	void run() {
		try {
			// test porazdeljevanje
			InTrack it = new InTrack("test_in.txt");
			OutTrack[] ots = OutTrack.createTracks("test_out", 1, 3);
			distribute(it, ots, 3);
			// test zlivanje
			InTrack its[] = InTrack.openTracks("test_out", 1, 3);
			OutTrack ot = new OutTrack("test_done.txt");
			merge(its, ot, 3);

		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	void sort_unbalanced(InTrack intrack, String trackName, int trackCount) {
		
	}
	
	void distribute(InTrack in, OutTrack[] outs, int runLen) {
		int currOutTrack = 0;
		int currEl = 0;
		while (!in.isEndOfTrack()) {
			if (in.isStraightEnd(runLen)) {
				currOutTrack++;
				currOutTrack %= outs.length;
				in.resetRun();
			}
			currEl = in.current;
			in.advance();
			outs[currOutTrack].write(currEl);
		}
	}
	
	void merge(InTrack[] ins, OutTrack out, int runLen) {
		boolean allEOF = false;
		boolean allEOT = false;
		int iMin = 0;
		int currEl = 0;
		while (!allEOF) {
			
			allEOF = true; // konec vseh file-ov
			allEOT = true; // konec vseh zaporedij
			
			// poišči najmanjšega
			currEl = Integer.MAX_VALUE;
			for (int i = 0; i < ins.length; i++) {
				if (!ins[i].isStraightEnd(runLen)) {
					if (ins[i].current < currEl) {
						iMin = i;
						currEl = ins[i].current;
					}
				}
			}
			
			// poglej konec fajlov
			allEOF = true;
			for (int i=0; i<ins.length; i++) {
				if (!ins[i].isEndOfTrack()) {
					allEOF = false;
				}
			}
			if (allEOF) {
				break;
			}
			
			// poglej konec zaporedij
			allEOT = true;
			for (int i=0; i<ins.length; i++) {
				if (!ins[i].isStraightEnd(runLen))
				{
					allEOT = false;
				}
			}
			if (allEOT) {
				for (int i=0; i<ins.length; i++) {
					if (!ins[i].isEndOfTrack()) {
						ins[i].resetRun();
					}
				}
				continue;
			}
			
			System.out.println("imin: " + iMin + " value: " + currEl);
			
			// prepiši premakni
			ins[iMin].advance();
			out.write(currEl);
		}
	}
}






















