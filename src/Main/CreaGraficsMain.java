package Main;


import java.io.*;
import java.sql.*;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


public class CreaGraficsMain {

	public static void main(String[] args) throws SQLException, IOException{
		String rest[] = new String[101];
		rest[0]="";
		float grades[] = new float[101];
		grades[0]=0;
		carregarLlista(rest, grades);
		//crearBarChart(rest, grades);
		crearDispersio(rest, grades);
		crearHistogram(rest, grades);
	}	
	
	public static void carregarLlista(String rest[], float grades[]) {
		int i=1;
		String line=null;
		try {
			BufferedReader reader = new BufferedReader(new FileReader("src/avgRest.txt"));
			//BufferedWriter writer1 = new BufferedWriter(new FileWriter("script.txt"));
			line = reader.readLine();
			line = reader.readLine();
			while((line != null) ) {
				rest[i]="Restaurant"+i;
				grades[i]=Float.parseFloat(line);
				i++;
				line = reader.readLine();
				line = reader.readLine();
			}
			reader.close();
			System.out.println("Data loaded....");
			}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void crearBarChart(String rest[], float grades[]) throws IOException {
		DefaultCategoryDataset  dataset = new DefaultCategoryDataset ();
		for(int j=1; j<=100; j++) {
			dataset.setValue(grades[j], "", rest[j]);
		}
		JFreeChart chart= ChartFactory.createBarChart("Mitjana de les valoracions dels Restaurants", "Restaurants", "Nota", dataset, PlotOrientation.VERTICAL, false, false, false);  
		//JFreeChart chart= ChartFactory.createHistogram("Mitjana de les valoracions dels Restaurants", "Restaurants", "Nota", (IntervalXYDataset) dataset, PlotOrientation.VERTICAL, false, false, false);  
		/*ChartPanel panel = new ChartPanel(chart);
		JFrame window = new JFrame("Gràfic");
		window.getContentPane().add(panel);
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);*/
		ChartUtilities.saveChartAsJPEG(new File("graficBarChart.jpg"), chart, 500, 300);
	}
	
	public static void crearDispersio(String rest[], float grades[]) throws IOException {
		XYSeriesCollection  dataset = new XYSeriesCollection ();
		XYSeries series1 = new XYSeries("Object1");
		for(int j=1; j<=100; j++) {
			series1.add((double)j, (double)grades[j]);
		}
		dataset.addSeries(series1);
		JFreeChart chart= ChartFactory.createScatterPlot("Dispersió de la mitjana de les valoracions dels Restaurants", "Restaurants", "Nota", (XYDataset) dataset, PlotOrientation.VERTICAL, false, false, false);  
		/*ChartPanel panel = new ChartPanel(chart);
		JFrame window = new JFrame("Gràfic");
		window.getContentPane().add(panel);
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);*/
		ChartUtilities.saveChartAsJPEG(new File("graficScatterPlot.jpg"), chart, 500, 300);
	}
	
	public static void crearHistogram(String rest[], float grades[]) throws IOException {
		XYSeriesCollection  dataset = new XYSeriesCollection ();
		XYSeries series1 = new XYSeries("Object1");
		for(int j=1; j<=100; j++) {
			series1.add((double)j, (double)grades[j]);
		}
		dataset.addSeries(series1);
		JFreeChart chart= ChartFactory.createHistogram("Histograma de la mitjana de les valoracions dels Restaurants", "Restaurants", "Nota", (IntervalXYDataset) dataset, PlotOrientation.VERTICAL, false, false, false);  
		/*ChartPanel panel = new ChartPanel(chart);
		JFrame window = new JFrame("Gràfic");
		window.getContentPane().add(panel);
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);*/
		ChartUtilities.saveChartAsJPEG(new File("graficHistogram.jpg"), chart, 500, 300);
	}
	


}
