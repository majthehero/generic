import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;

import com.googlecode.charts4j.Data;
import com.googlecode.charts4j.DataUtil;
import com.googlecode.charts4j.GCharts;
import com.googlecode.charts4j.Line;
import com.googlecode.charts4j.LineChart;
import com.googlecode.charts4j.Plots;


public class Framex {
	
	static long[] lDatas;
	static long[] bDatas;
	
	public Framex (long[] Ls, long[] Bs) {
		lDatas = Ls;
		bDatas = Bs;
	}
	
	static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("FrameDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        long maxDat = 0;
        for (int i=0; i<lDatas.length; i++) {
        	if (lDatas[i] > maxDat) maxDat = lDatas[i];
        }
        for (int i=0; i<bDatas.length; i++) {
        	if (bDatas[i] > maxDat) maxDat = bDatas[i];
        }
        maxDat /= 100;
        for (int i=0; i<lDatas.length; i++) {
        	lDatas[i] /= maxDat;
        }
        for (int i=0; i<bDatas.length; i++) {
        	bDatas[i] /= maxDat;
        }
        
        double[] LD = new double[lDatas.length];
        double[] BD = new double[bDatas.length];
        
        Data linData = Data.newData(LD);
        Data bisData = Data.newData(BD);
        
        Line linLine = Plots.newLine(linData);
        Line bisLine = Plots.newLine(bisData);
        
        LineChart lc = GCharts.newLineChart(linLine, bisLine);
        
        
        
        
//        JLabel lLabel = new JLabel("Linearni èasi so rdeèi");
//        JLabel bLabel = new JLabel("Bisekcijski èasi so modri");
//        
        frame.setPreferredSize(new Dimension(600, 400));
//        frame.getContentPane().add(lLabel);
//        frame.getContentPane().add(bLabel);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
}/*
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}*/