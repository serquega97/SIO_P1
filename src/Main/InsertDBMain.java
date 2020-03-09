package Main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

import Database.Database;

public class InsertDBMain {

	public static void main(String[] args) throws SQLException{
		Database db = new Database();
		db.connectBD();
		String line=null;
		try {
			BufferedReader reader = new BufferedReader(new FileReader("src/dataset.csv"));
			line = reader.readLine();
			String[] rest = line.split(";");
			//insertRestaurants(db, rest);
			line = reader.readLine();
			while(line != null) {
				int j=1;
				String[] data = line.split(";");
				String user=data[0];
				for(int i=0; i<data.length; i++) {
					if(i == 0) {
						//db.saveUser(data[i]);
						j--;
					}else if(Float.parseFloat(data[i]) != 99 && j<=rest.length-1) {
						db.saveRelation(user, rest[j], Float.parseFloat(data[i]));	
					}	
					j++;
				}
				System.out.println(user+" finished....");
				line = reader.readLine();
			}
			
			reader.close();
			db.disconnectBD();
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
