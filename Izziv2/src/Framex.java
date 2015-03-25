import java.awt.Color;

public class Framex {
	
	static long[] lDatas;
	static long[] bDatas;
	
	public Framex (long[] Ls, long[] Bs) {
		lDatas = Ls;
		bDatas = Bs;
	}
	
	void createAndShowGUI() {
        long maxDat = 0;
        for (int i=0; i<lDatas.length; i++) {
        	if (lDatas[i] > maxDat) maxDat = lDatas[i];
        }
        for (int i=0; i<bDatas.length; i++) {
        	if (bDatas[i] > maxDat) maxDat = bDatas[i];
        }
//        for (int i=0; i<lDatas.length; i++) {
//        	lDatas[i] /= maxDat;
//        }
//        for (int i=0; i<bDatas.length; i++) {
//        	bDatas[i] /= maxDat;
//        }
        
        double[] LD = new double[lDatas.length];
        double[] BD = new double[bDatas.length];
        
        for (int i=0; i<lDatas.length; i++) {
        	LD[i] = (double)lDatas[i] / (double)maxDat;
        	BD[i] = (double)bDatas[i] / (double)maxDat;
        }
        
        //draw linear times
//        StdDraw.setPenColor(Color.BLUE);
        for (int i=0; i<LD.length-1; i++) {
        	StdDraw.line(  (double)i/LD.length  , LD[i],
        			(double)(i+1)/LD.length, LD[i+1]);
        }
        //draw bisection times
//        StdDraw.setPenColor(Color.RED);
        for (int i=0; i<BD.length-1; i++) {
        	StdDraw.line(  (double)i/BD.length  , BD[i],
        			(double)(i+1)/BD.length, BD[i+1]);
        }
    }
}
