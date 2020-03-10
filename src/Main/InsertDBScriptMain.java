package Main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;

import Database.Database;

public class InsertDBScriptMain {

	public static void main(String[] args) throws SQLException{
		String line=null;
		try {
			BufferedReader reader = new BufferedReader(new FileReader("src/dataset.csv"));
			BufferedWriter writer = new BufferedWriter(new FileWriter("script.txt"));
			String str;
			str = "\nINSERT INTO Relation (grade, id_rest, id_user) VALUES";
			writer.write(str);
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
					}else if(Float.parseFloat(data[i]) != 99 && j<=rest.length-1) {
						str = "\n\t('"+Float.parseFloat(data[i])+"', '"+rest[j]+"', '"+user+"'),";
						writer.write(str);
					}	
					j++;
				}
				System.out.println(user+" finished....");
				line = reader.readLine();
			}
			
			reader.close();
			writer.close();
			System.out.println("Finished storing data to database....");
			}catch(IOException e) {
			e.printStackTrace();
		}
		
	}	
	
	public static void insertRestaurants(Database db, String[] data) throws SQLException{
		for(int i=1; i<=data.length-1; i++) 
			db.saveRestaurant(data[i]);
	}

}
