package Main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import BaseClasses.UserRestaurant;


public class CalculationsDBMain {

	public static void main(String[] args) throws SQLException, IOException{
		ArrayList<UserRestaurant> relation = new ArrayList<UserRestaurant>();
		
		fillArray(relation);
		System.out.println("size:"+relation.size());
		// Obtenim una llista amb quantes vegades han ficat 99 cada user
		int noValorationsTotal[] = countNoValorations(relation);
		// Obtenim una llista amb quantes vegades hi ha 99 per cada restaurant
		int noValorationsTotalRest[] = countNoValorationsRest(relation);
		// Classifiquem els usuaris en quantes vegades no han qualificat 
		int countVal[] = countNum(noValorationsTotal);
		// Classifiquem els usuaris en quantes vegades no han qualificat 
		int countValRest[] = countNumRest(noValorationsTotalRest);
		// Representem
		crearHistogram(countVal);
		crearHistogramRest(countValRest);
		
		
	}	
	

	public static void fillArray(ArrayList<UserRestaurant> relation){
		String line=null;
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader("src/dataset.csv"));
			line = reader.readLine();
			String[] rest = line.split(";");
			line = reader.readLine();
			while(line != null) {
				int j=1;
				String[] data = line.split(";");
				String user=data[0];
				for(int i=0; i<data.length; i++) {
					if(i == 0) {
						j--;
					}else if(j<=rest.length-1) {
						relation.add(new UserRestaurant(user, rest[j], Float.parseFloat(data[i])));	
					}	
					j++;
				}
				//System.out.println(user+" finished....");
				line = reader.readLine();
			}
			reader.close();
			System.out.println("Finished storing data to database....");
			}catch(IOException e) {
			e.printStackTrace();
		}
	}

	// Mirar si tots els usuaris valoren i agruparlos per quantes valoracions fan
	public static int[] countNoValorations(ArrayList<UserRestaurant> relation) {
		int i=1, us=1, count=0;
		int noValorations[] = new int[73422];
		UserRestaurant rel= relation.get(i);
		while((us<73422) ) {
			while(rel.getUser_id().equals("User"+us) && (i < relation.size()-1)) {
				if(rel.getGrade() == 99 ) {
					count++;
				}
				i++;
				rel= relation.get(i);
			}
			noValorations[us] = count;
			count = 0;
			
			us++;
		}
		return noValorations;
	}
	
	// Mirar si tots els usuaris valoren i agruparlos per quantes valoracions fan
	public static int[] countNoValorationsRest(ArrayList<UserRestaurant> relation) {
		int i=1, res=1, count=0;
		int noValorations[] = new int[100];
		UserRestaurant rel= relation.get(i);
		while((res<100) ) {
			while(rel.getRestaurant_id().equals("Restaurant"+res) && (i < relation.size()-1)) {
				if(rel.getGrade() == 99 ) {
					count++;
				}
				i++;
				rel= relation.get(i);
			}
			noValorations[res] = count;
			count = 0;
			res++;
		}
		return noValorations;
	}
	
	// Contavilitza el numero de vegades que un usuari no a valorat
	public static int[] countNum(int noValorationsTotal[]) {
		int i=0, index ;
		int countVal[] = new int[100];
		while(i < noValorationsTotal.length) {
			index = noValorationsTotal[i];
			countVal[index] = countVal[index] + 1;
			i++;
		}
		return countVal;
	}
	
	// Contavilitza el numero de vegades que un usuari a valorat
		public static int[] countNumRest(int noValorationsTotalRest[]) {
			int i=0, index ;
			int countValRest[] = new int[100];
			while(i < noValorationsTotalRest.length) {
				index = noValorationsTotalRest[i];
				countValRest[index] = countValRest[index] + 1;
				i++;
			}
			return countValRest;
		}
	
	
	public static void crearHistogram(int countVal[]) throws IOException {
		XYSeriesCollection  dataset = new XYSeriesCollection ();
		XYSeries series1 = new XYSeries("Object1");
		for(int j=0; j<countVal.length; j++) {
			series1.add((double)(100-j), (double)countVal[j]);
		}
		dataset.addSeries(series1);
		JFreeChart chart= ChartFactory.createHistogram("Nombre de vegades que un usuari ha valorat", "Nº Vegades", "Nº Usuaris", (IntervalXYDataset) dataset, PlotOrientation.VERTICAL, false, false, false);  
		/*ChartPanel panel = new ChartPanel(chart);
		JFrame window = new JFrame("Gràfic");
		window.getContentPane().add(panel);
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);*/
		ChartUtilities.saveChartAsJPEG(new File("graficHistogramValoracio1.jpg"), chart, 500, 300);
	}
	
	public static void crearHistogramRest(int countValRest[]) throws IOException {
		XYSeriesCollection  dataset = new XYSeriesCollection ();
		XYSeries series1 = new XYSeries("Object1");
		for(int j=0; j<countValRest.length; j++) {
			series1.add((double)j, (double)countValRest[j]);
		}
		dataset.addSeries(series1);
		JFreeChart chart= ChartFactory.createHistogram("Nombre que un restaurant no ha estat valorat", "Nº Vegades", "Nº Restaurant", (IntervalXYDataset) dataset, PlotOrientation.VERTICAL, false, false, false);  
		/*ChartPanel panel = new ChartPanel(chart);
		JFrame window = new JFrame("Gràfic");
		window.getContentPane().add(panel);
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);*/
		ChartUtilities.saveChartAsJPEG(new File("graficHistogramValoracioRest.jpg"), chart, 500, 300);
	}
	
	
	
	
}
