package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
	private Connection connection;
	private Statement statement;
	private final String BD_NAME="yli3crNeNQ";
	private final String USER="yli3crNeNQ";
	private final String PASSWORD="ZNWBVTKOIc";
	private final String DRIVER="com.mysql.cj.jdbc.Driver";
	private final String SERVER="jdbc:mysql://remotemysql.com:3306";
	
	public Database() {
		this.connectDriver();
	}
	
	//Method to connect to mysql driver
	public void connectDriver() {
		try {
			Class.forName(DRIVER);	
			connection = DriverManager.getConnection(SERVER, USER, PASSWORD);
		}catch(Exception e) {
			System.out.println("Error: No s'ha pogut connectar amb el driver de MySQL");
			e.printStackTrace();
		}
	}
	
	// Method to connect with the database
	public void connectBD() {
		try {
			statement = (Statement) connection.createStatement();

		} catch (SQLException e) {
			System.out.println("Error: No s'ha pogut connectar amb la base de dades.");
			e.printStackTrace();
		}
	}

	// Method to disconnect de database
	public void disconnectBD(){
		try {
			statement.close();
			
		} catch (Exception e) {
			System.out.println("Error: No es possible tancar la base de dades.");
			e.printStackTrace();
		}
	}
	
	//Method to make a user query
	public Integer userQuery(int userId) throws SQLException{
		Integer user_id=null;
		PreparedStatement ps = connection.prepareStatement("SELECT id FROM yli3crNeNQ.User WHERE id LIKE ?");
		ps.setInt(1, userId);
		ResultSet rs = ps.executeQuery();
			
		if(rs.isBeforeFirst()) {
			rs.next();
			user_id = rs.getInt("id");
		} else 
			user_id = -1;
		
		return user_id;
	}
	
	//Method to make a restaurant query
	public Integer restaurantQuery(int restId) throws SQLException {
		Integer rest_id=null;
		PreparedStatement ps = connection.prepareStatement("SELECT id FROM yli3crNeNQ.Restaurant WHERE id LIKE ?");
		ps.setInt(1, restId);
		ResultSet rs = ps.executeQuery();
			
		if(rs.isBeforeFirst()) {
			rs.next();
			rest_id = rs.getInt("id");
		} else 
			rest_id = -1;
		
		return rest_id;
	}
	
	//Method to save a user-restaurant grade to the database
	public void saveRelation(int user, int restaurant, float grade) throws SQLException{
		PreparedStatement ps = connection.prepareStatement("INSERT INTO yli3crNeNQ.Relation(grade,id_user,id_rest) VALUES (?,?,?)");
		ps.setFloat(1, grade);
		ps.setInt(2, user);
		ps.setInt(3, restaurant);
		ps.executeUpdate();
	}
	
	//Method to save a user to the database
	public void saveUser(int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement("INSERT INTO yli3crNeNQ.User(id) VALUES (?)");
		ps.setInt(1, id);
		ps.executeUpdate();
	}
	
	//Method to save a restaurant grade to the database
	public void saveRestaurant(int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement("INSERT INTO yli3crNeNQ.Restaurant(id) VALUES (?)");
		ps.setInt(1, id);
		ps.executeUpdate();
	}
}
