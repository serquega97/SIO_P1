package Main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

import Database.Database;

public class InsertDBMain {

	public static void main(String[] args) throws SQLException{
		int cont=0;
		Database db = new Database();
		db.connectBD();
		String line=null;
		try {
			BufferedReader reader = new BufferedReader(new FileReader("src/dataset.csv"));
			line = reader.readLine();
			String[] data1 = line.split(";");
			insertRestaurants(db, data1);
			line = reader.readLine();
			while(line != null) {
				cont=cont+1;
				String[] data = line.split(";");
				for(int i=0; i<data.length; i++) {
					if(i == 0)
						db.saveUser(cont, data[i]);
					else if(Float.parseFloat(data[i]) != 99)
						db.saveRelation(cont, i, Float.parseFloat(data[i]));
				}
				
				line = reader.readLine();
			}
			
			reader.close();
			db.disconnectBD();
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}	
	
	public static void insertRestaurants(Database db, String[] data) throws SQLException{
		for(int i=1; i<=data.length-1; i++) 
			db.saveRestaurant(i, data[i]);
	}

}
